package co.edu.uniquindio.software.safepet.logica;
import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;
import co.edu.uniquindio.software.safepet.persistencia.entidades.PlanServicio;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Stateless
@ApplicationScoped
public class PlanServicioBO implements GenericBO<PlanServicio,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;


    @Override
    public PlanServicio create(PlanServicio entity) {
        String sql = "insert into PLANSERVICIO (ID,FECHA_SERVICIO,SERVICIO_IDCS,SERVICIOC_ID,SERVICIOCENTRO_IDSER,SERVICIOCENTRO_IDCEN values (?,?,?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.setDate(2,(Date) entity.getFechaServicio());
            statement.setString(3, entity.getServicio_idcs());
            statement.setString(4, entity.getServicioc_id());
            statement.setString(5,entity.getServicioc_idser());
            statement.setString(6,entity.getServicioc_idcen());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public void delete(PlanServicio entity) {
        String sql = "delete from planservicio where ID = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    @Override
    public PlanServicio find(String id) {
        String sql = "select ID,,COPAGO,AFILIADO_ID,EMPLEADOSAFEPET_ID  from PLANSERVICIO where ID = ? " ;
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
    public Plan update(Plan entity) {
        String sql = "UPDATE PLANSERVICIO SET ID,FECHA_SERVICIO,SERVICIO_IDCS,SERVICIOC_ID,SERVICIOCENTRO_IDSER,SERVICIOCENTRO_IDCEN  where ID=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setDouble(1, entity.);
            statement.setDouble(2, entity.getCopago());
            statement.setString(3, entity.getAfiliado_id());
            statement.setString(4,entity.getEmpleadoSafepet_id());
            statement.setString(5, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Plan> findAll() {
        String sql = "select ID,MENSUALIDAD,COPAGO,AFILIADO_ID,EMPLEADOSAFEPET_ID from PLAN " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<Plan> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    private Plan createFromResultSet(ResultSet resultSet) throws SQLException {
        Plan plan = new Plan();
        plan.setId(resultSet.getString("ID"));
        plan.setMensualidad(resultSet.getDouble("MENSUALIDAD"));
        plan.setCopago(resultSet.getDouble("COPAGO"));
        plan.setAfiliado_id(resultSet.getString("AFILIADO_ID"));
        plan.setEmpleadoSafepet_id(resultSet.getString("EMPLEADO_SAFEPET"));
        return plan;
    }
}
