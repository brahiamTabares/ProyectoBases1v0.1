package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;

import co.edu.uniquindio.software.safepet.persistencia.entidades.Evaluacion;

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
public class EvaluacionBO implements GenericBO<Evaluacion,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public Evaluacion create(Evaluacion entity) {

        String sql = "insert into evaluacion(id_evaluacion,puntuacion,planservicio_servicio_idcs,planservicio_servicioc_id,planservicio_id) values (?,?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId_evaluacion());
            statement.setInt(2, entity.getPuntacion());
            statement.setString(3, entity.getPlanservicio_servicio_idcs());
            statement.setString(4, entity.getPlanservicio_servicioc_id());
            statement.setString(5,entity.getAfiliado_id());
            statement.setString(6,entity.getPlanservicio_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(Evaluacion entity) {
        String sql = "delete from evaluacion where id_evaluacion = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId_evaluacion());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public Evaluacion find(String id) {
        String sql = "select id_evaluacion,puntuacion,planservicio_servicio_idcs,planservicio_servicioc_id,afiliado_usuario_id,planservicio_id from EVALUACION where ID_EVALUACION = ? " ;
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
    public Evaluacion update(Evaluacion entity) {
        String sql = "UPDATE evaluacion SET PUNTUACION=?,PLANSERVICIO_SERVICIO_IDCS=?,PLANSERVICIO_SERVICIOC_ID=?,AFILIADO_ID,PLANSERVICIO_ID=? where id_evaluacion=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setInt(1, entity.getPuntacion());
            statement.setString(2, entity.getPlanservicio_servicio_idcs());
            statement.setString(3, entity.getPlanservicio_servicioc_id());
            statement.setString(4,entity.getAfiliado_id());
            statement.setString(5,entity.getPlanservicio_id());
            statement.setString(6, entity.getId_evaluacion());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Evaluacion> findAll() {
        String sql = "select id_evaluacion,puntuacion,planservicio_servicio_idcs,planservicio_servicioc_id,afiliado_usuario_id,planservicio_id from evaluacion " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<Evaluacion> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private Evaluacion createFromResultSet(ResultSet resultSet) throws SQLException {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId_evaluacion(resultSet.getString("ID_EVALUACION"));
        evaluacion.setPuntacion(resultSet.getInt("PUNTUACION"));
        evaluacion.setPlanservicio_servicio_idcs(resultSet.getString("PLANSERVICIO_SERVICIO_IDCS"));
        evaluacion.setPlanservicio_servicioc_id(resultSet.getString("PLANSERVICIO_SERVICIOC_ID"));
        evaluacion.setAfiliado_id(resultSet.getString("AFILIADO_ID"));
        evaluacion.setPlanservicio_id(resultSet.getString("PLANSERVICIO_ID"));

        return evaluacion;
    }
}
