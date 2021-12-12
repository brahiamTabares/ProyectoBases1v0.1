package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.logica.GenericBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.PlanServicio;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanServicioBO implements GenericBO<PlanServicio,String> {

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public PlanServicio create(PlanServicio entity) {

        String sql = "insert into PLANSERVICIO (PLAN_ID,SERVICIO_ID) values (?,?) ";
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
        String sql = "delete from PLANSERVICIO where PLAN_ID=? ";
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
        String sql = "select  PLAN_ID,SERVICIO_ID from PLANSERVICIO where PLAN_ID= ? " ;
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
        String sql = "UPDATE PLANSERVICIO SET SERVICIO_ID=? where PLAN_ID=? ";
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
        String sql = "select PLAN_ID,SERVICIO_ID from PLANSERVICIO" ;
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
        planServicio.setPlan_id(resultSet.getString("PLAN_ID"));
        planServicio.setServicio_id(resultSet.getString("SERVICIO_ID"));


        return planServicio;
    }
}


