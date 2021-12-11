package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;

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
public class AfiliadoBO implements GenericBO<Afiliado,String>{
    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;
    @Override
    public Afiliado create(Afiliado entity) {
        String sql = "insert into AFILIADO(ID,NOMBRE,CONTRASENIA,TELEFONO) values (?,?,?,?) ";
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
    public void delete(Afiliado entity) {

        String sql = "delete from AFILIADO where ID = ? ";
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
        String sql = "select ID,NOMBRE,CONTRASENIA,TELEFONO from afiliado where ID = ? " ;
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
        String sql = "UPDATE AFILIADO SET NOMBRE=?, CONTRASENIA=?, TELEFONO=? where ID=? ";
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
        String sql = "select ID,NOMBRE,CONTRASENIA,TELEFONO from AFILIADO " ;
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
        afiliado.setId(resultSet.getString("ID"));
        afiliado.setNombre(resultSet.getString("NOMBRE"));
        afiliado.setContrasenia(resultSet.getString("CONTRASENIA"));
        afiliado.setTelefono(resultSet.getString("TELEFONO"));
        return afiliado;
    }
}
