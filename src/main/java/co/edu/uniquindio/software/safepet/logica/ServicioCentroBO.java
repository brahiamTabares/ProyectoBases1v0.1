package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;
import co.edu.uniquindio.software.safepet.persistencia.entidades.ServicioCentro;

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
public class ServicioCentroBO implements GenericBO<ServicioCentro,String> {

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public ServicioCentro create(ServicioCentro entity) {

        String sql = "insert into serviciocentro (servicio_id, centroservicio_usuario_id) values (?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getIdser());
            statement.setString(2, entity.getIdcen());
            statement.setString(3,entity.getServicio_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(ServicioCentro entity) {
        String sql = "delete from serviciocentro  where servicio_id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1,entity.getServicio_id()); ;
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public ServicioCentro find(String id) {
        String sql = "select servicio_id,centroservicio_usuario_id from serviciocentro where servicio_id= ? " ;
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
    public ServicioCentro update(ServicioCentro entity) {
        String sql = "UPDATE serviciocentro SET servicio_id,centroservicio_usuario_id=? where servicio_id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {


            statement.setString(2, entity.getServicio_id());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<ServicioCentro> findAll() {
        String sql = "select  IDSER,idcen,SERVICIO_ID from serviciocentro" ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<ServicioCentro> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private ServicioCentro createFromResultSet(ResultSet resultSet) throws SQLException {
        ServicioCentro servicioCentro = new ServicioCentro();
        servicioCentro.setIdser(resultSet.getString("IDSER"));
        servicioCentro.setIdcen(resultSet.getString("IDCEN"));
        servicioCentro.setServicio_id(resultSet.getString("SERVICIO_ID"));


        return servicioCentro;
    }
}
