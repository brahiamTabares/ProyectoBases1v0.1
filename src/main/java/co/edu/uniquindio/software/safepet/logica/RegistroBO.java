package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Registro;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RegistroBO implements GenericBO<Registro,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public Registro create(Registro entity) {

        String sql = "insert into REGISTRO(CODIGO,OBSERVACIONES,CONCEPTO,DECISIONES,FECHAREGISTRO,EXAMENES_CODIGO,HISTORIACLINICA_MASCOTA_ID) values (?,?,?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCodigo());
            statement.setString(2, entity.getObservaciones());
            statement.setString(3, entity.getConcepto());
            statement.setString(4, entity.getDecisiones());
            statement.setDate(5,(Date) entity.getFecharegistro());
            statement.setString(6,entity.getExamenes_codigo());
            statement.setString(7,entity.getHistoriaclinica_mascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(Registro entity) {
        String sql = "delete from REGISTRO where CODIGO = ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCodigo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public Registro find(String id) {
        String sql = "select CODIGO,OBSERVACIONES,CONCEPTO,DECISIONES,FECHAREGISTRO,EXAMENES_CODIGO,HISTORIACLINICA_MASCOTA_ID from REGISTRO where CODIGO= ? " ;
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
    public Registro update(Registro entity) {
        String sql = "UPDATE  Registro SET ,OBSERVACIONES=?,CONCEPTO=?,DECISIONES=?,FECHAREGISTRO=?,EXAMENES_CODIGO=?,HISTORIACLINICA_MASCOTA_ID=? where CODIGO";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getObservaciones());
            statement.setString(2, entity.getConcepto());
            statement.setString(3,entity.getDecisiones());
            statement.setDate(4,(Date) entity.getFecharegistro());
            statement.setString(5, entity.getExamenes_codigo());
            statement.setString(6,entity.getHistoriaclinica_mascota_id());
            statement.setString(7, entity.getCodigo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Registro> findAll() {
        String sql = "select CODIGO,OBSERVACIONES,CONCEPTO,DECISIONES,FECHAREGISTRO,EXAMENES_CODIGO,HISTORIACLINICA_MASCOTA_ID from REGISTRO " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<Registro> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private Registro createFromResultSet(ResultSet resultSet) throws SQLException {
        Registro registro = new Registro();
        registro.setCodigo(resultSet.getString("CODIGO"));
        registro.setObservaciones(resultSet.getString("OBSERVACIONES"));
        registro.setConcepto(resultSet.getString("CONCEPTO"));
        registro.setDecisiones(resultSet.getString("DECISIONES"));
        registro.setFecharegistro(resultSet.getDate("FECHAREGISTRO"));
        registro.setExamenes_codigo(resultSet.getString("EXAMENES_CODIGO"));
        registro.setHistoriaclinica_mascota_id(resultSet.getString("HISTORIA_CLINICA"));
        return registro;
    }
}
