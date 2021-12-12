package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;

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
public class AfiliadoBO implements GenericBO<Afiliado,String>{
    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;
    @Override
    public Afiliado create(Afiliado entity) {
        String sql = "insert into usuario(id,nombre,contrasenia,telefono) values (?,?,?,?) ";
        String sql2 = "insert into afiliado(usuario_id) values(?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ); PreparedStatement statement2 = connection.prepareStatement( sql2 ) ) {
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getContrasenia());
            statement.setString(4, entity.getTelefono());
            statement.executeUpdate();
            statement2.setString(1, entity.getId());
            statement2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(Afiliado entity) {

        String sql = "delete from afiliado where usuario_id = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }



    @Override
    public Afiliado find(String id) {
        String sql = "select u.id,u.nombre,u.contrasenia,u.telefono from afiliado a inner join usuario u on a.usuario_id=u.id where u.id = ? " ;
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
    public Afiliado update(Afiliado entity) {
        String sql = "UPDATE usuario SET nombre=?, contrasenia=?, telefono=? where id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getContrasenia());
            statement.setString(3, entity.getTelefono());

            statement.setString(4, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Afiliado> findAll() {
        String sql = "select u.id,u.nombre,u.contrasenia,u.telefono from afiliado a inner join usuario u on a.usuario_id=u.id " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<Afiliado> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    private Afiliado createFromResultSet(ResultSet resultSet) throws SQLException {
        Afiliado afiliado = new Afiliado();
        afiliado.setId(resultSet.getString("id"));
        afiliado.setNombre(resultSet.getString("nombre"));
        afiliado.setContrasenia(resultSet.getString("contrasenia"));
        afiliado.setTelefono(resultSet.getString("telefono"));
        return afiliado;
    }
}
