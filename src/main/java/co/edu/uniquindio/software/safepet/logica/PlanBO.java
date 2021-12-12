package co.edu.uniquindio.software.safepet.logica;
import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Usuario;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    private ServicioBO servicioBO;
    @Inject
    private MascotaBO mascotaBO;
    @Inject
    private AfiliadoBO afiliadoBO;

    @Override
    public Plan create(Plan entity) {
        String sql = "insert into plan (id,mensualidad,copago,afiliado_usuario_id,empleadosafepet_usuario_id values (?,?,?,?,?) ";
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
        String sql = "delete from plan where id = ? ";
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
        String sql = "select id,mensualidad,copago,afiliado_usuario_id,empleadosafepet_usuario_id  from plan where id = ? " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            statement.setObject(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return completarPlan(createFromResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    @Override
    public Plan update(Plan entity) {
        String sql = "UPDATE plan SET mensualidad,copago,afiliado_usuario_id,empleadosafepet_usuario_id  where id=? ";
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
        String sql = "select id,mensualidad,copago,afiliado_usuario_id,empleadosafepet_usuario_id from plan " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<Plan> result = new ArrayList<>();
            while (resultSet.next()) {
                Plan plan = createFromResultSet(resultSet);

                completarPlan(plan);

                result.add( plan );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    private Plan completarPlan(Plan plan){
        plan.setServicios( servicioBO.findByPlan(plan.getId()) );
        plan.setMascotas( mascotaBO.findByPlan(plan.getId()) );
        plan.setAfiliado( afiliadoBO.find(plan.getAfiliado_id()) );
        return plan;
    }

    private Plan createFromResultSet(ResultSet resultSet) throws SQLException {
        Plan plan = new Plan();
        plan.setId(resultSet.getString("id"));
        plan.setMensualidad(resultSet.getDouble("mensualidad"));
        plan.setCopago(resultSet.getDouble("copago"));
        plan.setAfiliado_id(resultSet.getString("afiliado_usuario_id"));
        plan.setEmpleadoSafepet_id(resultSet.getString("empleadosafepet_usuario_id"));
        return plan;
    }
}
