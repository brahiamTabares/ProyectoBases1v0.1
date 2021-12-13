package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Consulta;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Evaluacion;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConsultaBO implements GenericBO<Consulta,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public Consulta create(Consulta entity) {

        String sql = "insert into consulta(CODIGO,FECHA_CITA,DESCRIPCION,CENTROSERVICIO_ID,MASCOTA_ID) values (?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCodigo());
            statement.setDate(2, (Date) entity.getFecha_Cita());
            statement.setString(3, entity.getDescripcion());
            statement.setString(4, entity.getCentroServicio_id());
            statement.setString(5,entity.getMascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(Consulta entity) {
        String sql = "delete  where CODIGO= ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCodigo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public Consulta find(String id) {
        String sql = "select CODIGO,FECHA_CITA,DESCRIPCION,CENTROSERVICIO_ID,MASCOTA_ID from consulta where CODIGO = ? " ;
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
    public Consulta update(Consulta entity) {
        String sql = "UPDATE consulta SET FECHA_CITA=?,DESCRIPCION=?,CENTROSERVICIO_ID=?,MASCOTA_ID=? where CODIGO=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setString(1, entity.getCodigo());
            statement.setDate(2, (Date) entity.getFecha_Cita());
            statement.setString(3, entity.getDescripcion());
            statement.setString(4, entity.getCentroServicio_id());
            statement.setString(5,entity.getMascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Consulta> findAll() {
        String sql = "select CODIGO,FECHA_CITA,DESCRIPCION,CENTROSERVICIO_ID,MASCOTA_ID from consulta" ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<Consulta> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private Consulta createFromResultSet(ResultSet resultSet) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setCodigo(resultSet.getString("CODIGO"));
        consulta.setFecha_Cita(resultSet.getDate("FECHA_CITA"));
        consulta.setDescripcion(resultSet.getString("DESCRIPCION"));
        consulta.setCentroServicio_id(resultSet.getString("CENTROSERVICIO_ID"));
        consulta.setMascota_id(resultSet.getString("MASCOTA_ID"));

        return consulta;
    }
}
