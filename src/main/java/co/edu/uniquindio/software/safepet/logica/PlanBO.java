package co.edu.uniquindio.software.safepet.logica;
import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Usuario;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Stateless
@ApplicationScoped
public class PlanBO implements GenericBO<Plan,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;


    @Override
    public Plan create(Plan entity) {
        String sql = "insert into PLAN (ID,MENSUALIDAD,COPAGO,AFILIADO_ID,EMPLEADOSAFEPET_ID values (?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.setDouble(2, entity.getMensualidad());
            statement.setDouble(3, entity.getCopago());
            statement.setString(4, entity.getAfiliado_id());
            statement.setString(5,entity.getEmpleadoSafepet_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public void delete(Plan entity) {
        String sql = "delete from PLAN where ID = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    @Override
    public Plan find(String id) {
        String sql = "select ID,MENSUALIDAD,COPAGO,AFILIADO_ID,EMPLEADOSAFEPET_ID  from PLAN where ID = ? " ;
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
        String sql = "UPDATE PLAN SET MENSUALIDAD,COPAGO,AFILIADO_ID,EMPLEADOSAFEPET_ID  where ID=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setDouble(1, entity.getMensualidad());
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
