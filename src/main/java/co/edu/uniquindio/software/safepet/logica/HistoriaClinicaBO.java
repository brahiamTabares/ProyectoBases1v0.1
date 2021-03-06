package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.HistoriaClinica;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class HistoriaClinicaBO implements GenericBO<HistoriaClinica, String> {

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public HistoriaClinica create(HistoriaClinica entity) {

        String sql = "insert into historiaclinica(nombre,sexo,fecha_ingreso,fechasalida,mascota_id) values (?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getSexo());
            statement.setDate(3, (Date) entity.getFechaIngreso());
            statement.setDate(4, (Date) entity.getFechaSalida());
             statement.setString(5,entity.getMascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(HistoriaClinica entity) {
        String sql = "delete from historiaclinica where mascota_id=?";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getSexo());
            statement.setDate(3, (Date) entity.getFechaIngreso());
            statement.setDate(4, (Date) entity.getFechaSalida());
            statement.setString(5,entity.getMascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public HistoriaClinica find(String id) {
        String sql = "select nombre,SEXO,fecha_ingreso,fechasalida,mascota_id from historiaclinica where mascota_id=?" ;
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
    public HistoriaClinica update(HistoriaClinica entity) {
        String sql = "UPDATE historiaclinica SET nombre=?,sexo=?,fecha_ingreso=?,fechasalida=? where mascota_id ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getSexo());
            statement.setDate(3, (Date) entity.getFechaIngreso());
            statement.setDate(4, (Date) entity.getFechaSalida());
            statement.setString(5,entity.getMascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<HistoriaClinica> findAll() {
        String sql = "select nombre,sexo,fecha_ingreso,fechasalida,mascota_id from historiaclinica " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<HistoriaClinica> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private HistoriaClinica createFromResultSet(ResultSet resultSet) throws SQLException {
        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setNombre(resultSet.getString("nombre"));
        historiaClinica.setSexo(resultSet.getString("sexo"));
        historiaClinica.setFechaIngreso(resultSet.getDate("fecha_ingreso"));
        historiaClinica.setFechaSalida(resultSet.getDate("fechasalida"));
        historiaClinica.setMascota_id(resultSet.getString("mascota_id"));

        return historiaClinica;
    }
}
