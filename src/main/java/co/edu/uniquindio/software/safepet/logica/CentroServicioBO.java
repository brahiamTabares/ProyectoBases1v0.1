package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;
import co.edu.uniquindio.software.safepet.persistencia.entidades.CentroServicio;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CentroServicioBO implements GenericBO<CentroServicio, String> {
    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;
    @Override
    public CentroServicio create(CentroServicio entity) {
        String sql = "insert into centroservicio(ID,NOMBRE,CONTRASENIA,TELEFONO,TIPOCENTRO_CODIGO) values (?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getContrasenia());
            statement.setString(4, entity.getTelefono());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public void delete(CentroServicio entity) {

        String sql = "delete from centroservicio where ID = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public CentroServicio find(String id) {
        String sql = "select ID,NOMBRE,CONTRASENIA,TELEFONO,TIPOCENTRO_CODIGO from centroservicio where ID = ? " ;
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
        String sql = "UPDATE CENTROSERVICIO SET NOMBRE=?, CONTRASENIA=?, TELEFONO=? ,TIPOCENTRO_CODIGO=? where ID=? ";
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
    public List<CentroServicio> findAll() {
        String sql = "select ID,NOMBRE,CONTRASENIA,TELEFONO,TIPOCENTRO_CODIGO from centroservicio " ;
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
        centroServicio.setId(resultSet.getString("ID"));
        centroServicio.setNombre(resultSet.getString("NOMBRE"));
        centroServicio.setContrasenia(resultSet.getString("CONTRASENIA"));
        centroServicio.setTelefono(resultSet.getString("TELEFONO"));
        centroServicio.setTipoCentro_codigo(resultSet.getString("TIPOCENTRO_CODIGO"));
        return  centroServicio;
    }
}
