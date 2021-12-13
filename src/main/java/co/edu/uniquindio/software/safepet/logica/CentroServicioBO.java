package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;
import co.edu.uniquindio.software.safepet.persistencia.entidades.CentroServicio;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CentroServicioBO implements GenericBO<CentroServicio, String> {
    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;
    @Override
    public CentroServicio create(CentroServicio entity) {
        String sql = "insert into usuario(id,nombre,contrasenia,telefono) values (?,?,?,?) ";
        String sql2 = "insert into centroservicio(usuario_id,tipocentro_codigo) values(?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql );PreparedStatement statement2 = connection.prepareStatement( sql2 ) ) {
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getContrasenia());
            statement.setString(4, entity.getTelefono());
            statement.executeUpdate();
            statement2.setString(1, entity.getId());
            statement2.setString(2,entity.getTipoCentro_codigo());
            statement2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public void delete(CentroServicio entity) {
        String sql = "delete from centroservicio where usuario_id = ? ";
        String sql2 = "delete from usuario where id = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql );PreparedStatement statement2 = connection.prepareStatement( sql2 ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public CentroServicio find(String id) {
        String sql = "select u.id,u.nombre,u.contrasenia,u.telefono from centroservicio c inner join usuario u on c.usuario_id=u.id where u.id = ? " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            statement.setObject(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public CentroServicio update(CentroServicio entity) {
        String sql = "UPDATE usuario SET nombre=?, contrasenia=?, telefono=?  where id=? ";
        String sql2 = " UPDATE centroservicio SET tipocentro_codigo=? where usuario_id=?";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql );PreparedStatement statement2 = connection.prepareStatement( sql2 ) ) {

            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getContrasenia());
            statement.setString(3, entity.getTelefono());
            statement.setString(4, entity.getId());
            statement.executeUpdate();
            statement2.setString(1, entity.getId());
            statement2.setString(2, entity.getTipoCentro_codigo());
            statement2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<CentroServicio> findAll() {
        String sql = "select u.id,u.nombre,u.contrasenia,u.telefono  from centroservicio c inner join usuario u on c.usuario_id=u.id ";
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<CentroServicio> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    private CentroServicio createFromResultSet(ResultSet resultSet) throws SQLException {
        CentroServicio centroServicio = new CentroServicio();
        centroServicio.setId(resultSet.getString("id"));
        centroServicio.setNombre(resultSet.getString("nombre"));
        centroServicio.setContrasenia(resultSet.getString("contrasenia"));
        centroServicio.setTelefono(resultSet.getString("telefono"));
        centroServicio.setTipoCentro_codigo(resultSet.getString("tipoCentro_codigo"));
        return  centroServicio;
    }
}
