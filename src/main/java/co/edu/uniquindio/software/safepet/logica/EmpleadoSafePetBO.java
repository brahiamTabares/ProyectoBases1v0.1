package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.EmpleadoSafePet;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Usuario;

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
public class EmpleadoSafePetBO  implements GenericBO<EmpleadoSafePet,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public EmpleadoSafePet create(EmpleadoSafePet entity) {

        String sql = "insert into empleadosafepet(ID,NOMBRE,CONTRASENIA,TELEFONO) values (?,?,?,?) ";
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
    public void delete(EmpleadoSafePet entity) {
        String sql = "delete from empleadosafepet where ID = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public EmpleadoSafePet find(String id) {
        String sql = "select ID,NOMBRE,CONTRASENIA,TELEFONO from empleadosafepet where ID = ? " ;
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
    public EmpleadoSafePet update(EmpleadoSafePet entity) {
        String sql = "UPDATE empleadosafepet SET NOMBRE=?, CONTRASENIA=?, TELEFONO=? where ID=? ";
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
    public List<EmpleadoSafePet> findAll() {
        String sql = "select ID,NOMBRE,CONTRASENIA,TELEFONO from empleadosafepet " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<EmpleadoSafePet> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private EmpleadoSafePet createFromResultSet(ResultSet resultSet) throws SQLException {
        EmpleadoSafePet empleadoSafePet = new EmpleadoSafePet();
        empleadoSafePet.setId(resultSet.getString("ID"));
        empleadoSafePet.setNombre(resultSet.getString("NOMBRE"));
        empleadoSafePet.setContrasenia(resultSet.getString("CONTRASENIA"));
        empleadoSafePet.setTelefono(resultSet.getString("TELEFONO"));
        return empleadoSafePet;
    }
}
