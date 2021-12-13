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

        String sql = "insert into usuario (id,nombre,contrasenia,telefono) values (?,?,?,?) ";

        String sql2= "insert into empleadosafepet(usuario_id) values(?)";

        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql );PreparedStatement statement2 = connection.prepareStatement( sql2 ) ) {
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
    public void delete(EmpleadoSafePet entity) {
        String sql = "delete from empleadosafepet where usuario_id = ? ";
        String sql2 = "delete from usuario where id = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql );PreparedStatement statement2 = connection.prepareStatement( sql2 )  ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
            statement2.setString(1, entity.getId());
            statement2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public EmpleadoSafePet find(String id) {
        String sql = "select u.id,u.nombre,u.contrasenia,u.telefono from empleadosafepet e inner join usuario u on e.usuario_id=u.id where u.id = ? " ;
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
    public List<EmpleadoSafePet> findAll() {
        String sql = "select u.id,u.nombre,u.contrasenia,u.telefono from empleadosafepet e inner join usuario u on e.usuario_id=u.id ";
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
        empleadoSafePet.setId(resultSet.getString("id"));
        empleadoSafePet.setNombre(resultSet.getString("nombre"));
        empleadoSafePet.setContrasenia(resultSet.getString("contrasenia"));
        empleadoSafePet.setTelefono(resultSet.getString("telefono"));
        return empleadoSafePet;
    }
}
