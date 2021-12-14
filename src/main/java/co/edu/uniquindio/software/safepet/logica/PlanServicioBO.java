package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.logica.GenericBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.PlanServicio;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlanServicioBO implements GenericBO<PlanServicio,String> {

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public PlanServicio create(PlanServicio entity) {

        String sql = "insert into planservicio (plan_id,servicio_id) values (?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getPlan_id());
            statement.setString(2, entity.getServicio_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(PlanServicio entity) {
        String sql = "delete from planservicio where plan_id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getPlan_id());
            statement.setString(2, entity.getServicio_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public  PlanServicio find(String id) {
        String sql = "select  plan_id,servicio_id from planservicio where plan_id= ? " ;
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
    public PlanServicio update(PlanServicio entity) {
        String sql = "UPDATE planservicio SET servicio_id=? where plan_id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setString(2, entity.getServicio_id());
            statement.setString(1, entity.getPlan_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<PlanServicio> findAll() {
        String sql = "select plan_id,servicio_id from planservicio" ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<PlanServicio> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private PlanServicio createFromResultSet(ResultSet resultSet) throws SQLException {
        PlanServicio planServicio = new PlanServicio();
        planServicio.setPlan_id(resultSet.getString("plan_id"));
        planServicio.setServicio_id(resultSet.getString("servicio_id"));


        return planServicio;
    }
}


