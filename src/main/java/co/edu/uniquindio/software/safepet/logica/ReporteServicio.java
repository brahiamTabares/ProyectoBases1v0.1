package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.dtos.DatoDTO;

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
public class ReporteServicio {
    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    public List<DatoDTO> generarReportePlanesPorVendedor() {
        String sql = "select u.nombre, count(1) cantidad from usuario u inner join empleadosafepet e on u.id = e.usuario_id left join plan p on e.usuario_id = p.empleadosafepet_usuario_id group by u.nombre " ;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<DatoDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    private DatoDTO createFromResultSet(ResultSet resultSet) throws SQLException {
        return new DatoDTO(resultSet.getString(1),resultSet.getInt(2));
    }

    public List<DatoDTO> generarReporteMascotasPorPlan() {
        String sql = "select p.id, count(1) cantidad from plan p left join mascota m on p.id = m.plan_id group by p.id " ;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<DatoDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    public List<DatoDTO> generarReporteMascotasPorRaza() {
        String sql = "select r.nombre, count(1) cantidad from raza r left join mascota m on r.codigo = m.raza_codigo group by r.nombre " ;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<DatoDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    public List<DatoDTO> generarReporteMascotasPorTipo() {
        String sql = "select t.tipo, count(1) cantidad from tipomascota t left join mascota m on t.id = m.tipomascota_id group by t.tipo " ;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<DatoDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

}
