package co.edu.uniquindio.software.safepet.logica;
import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.HistorialServicio;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Stateless
@ApplicationScoped
public class HistorialServicioBO implements GenericBO<HistorialServicio,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;


    @Override
    public HistorialServicio create(HistorialServicio entity) {
        String sql = "insert into historialservicio (ID,FECHA_SERVICIO,SERVICIO_IDCS,SERVICIOC_ID,SERVICIOCENTRO_IDSER,SERVICIOCENTRO_IDCEN values (?,?,?,?,?,?,?) ";
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
    public void delete(HistorialServicio entity) {
        String sql = "delete from historialservicio where ID = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    @Override
    public HistorialServicio find(String id) {
        String sql = "select ID,,COPAGO,AFILIADO_ID,EMPLEADOSAFEPET_ID  from historialservicio where ID = ? " ;
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
    public HistorialServicio update(HistorialServicio entity) {
        String sql = "UPDATE historialservicio SET FECHA_SERVICIO=?,SERVICIO_IDCS=?,SERVICIOC_ID=?,SERVICIOCENTRO_IDSER=?,SERVICIOCENTRO_IDCEN=?  where ID=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setDate(1, (Date) entity.getFechaServicio());
            statement.setString(2,entity.getServicio_idcs());
            statement.setString(3,entity.getServicioc_id());
            statement.setString(4,entity.getServicioc_idser());
            statement.setString(5, entity.getServicioc_idcen());
            statement.setString(6, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<HistorialServicio> findAll() {
        String sql = "select  from historialservicio ";
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<HistorialServicio> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    private HistorialServicio createFromResultSet(ResultSet resultSet) throws SQLException {
        HistorialServicio planServicio = new HistorialServicio();
        planServicio.setId(resultSet.getString("ID"));
        planServicio.setFechaServicio(resultSet.getDate("FECHA_SERVICIO"));
        planServicio.setServicio_idcs(resultSet.getString("SERVICIO_IDCS"));
        planServicio.setServicioc_id(resultSet.getString("SERVICIO_ID"));
        planServicio.setServicioc_idser(resultSet.getString("SERVICIOC_IDSER"));
        planServicio.setServicioc_idcen(resultSet.getString("SERVICIO_IDCEN"));

        return planServicio;
    }
}
