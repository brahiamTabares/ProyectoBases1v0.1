package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.ExamenesCentro;

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
public class ExamenesCentroBO implements GenericBO<ExamenesCentro, String> {

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public ExamenesCentro create(ExamenesCentro entity) {

        String sql = "insert into examenes_centro(CENTROSERVICIO_ID,EXAMENES_CODIGO) values (?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCentroServicio_id());
            statement.setString(2, entity.getExamenes_codigo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(ExamenesCentro entity) {
        String sql = "delete from examenes_centtro where CENTROSERVICIO_ID = ?,EXAMENES_CODIGO=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCentroServicio_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public ExamenesCentro find(String id) {
        String sql = "select  CENTROSERVICIO_ID,EXAMENES_CODIGO from examenes_centro where CENTROSERVICIO_ID= ? " ;
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
    public ExamenesCentro update(ExamenesCentro entity) {
        String sql = "UPDATE examenes_centro SET EXAMENES_CODIGO where CENTROSERVICIO_ID ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setString(1, entity.getExamenes_codigo());
            statement.setString(2, entity.getCentroServicio_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<ExamenesCentro> findAll() {
        String sql = "select CENTROSERVICIO_ID,EXAMENENES_CODIGO from examenes_centro " ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<ExamenesCentro> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private ExamenesCentro createFromResultSet(ResultSet resultSet) throws SQLException {
        ExamenesCentro examenesCentro = new ExamenesCentro();
        examenesCentro.setCentroServicio_id(resultSet.getString("CENTROSERVICIO_ID"));
        examenesCentro.setExamenes_codigo(resultSet.getString("EXAMENES_CODIGO"));


        return examenesCentro;
    }
}
