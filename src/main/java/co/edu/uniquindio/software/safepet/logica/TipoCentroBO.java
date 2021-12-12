package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Examen;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoCentro;

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
public class TipoCentroBO implements GenericBO<TipoCentro,String> {

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public TipoCentro create(TipoCentro entity) {

        String sql = "insert into tipocentro (CODIGO,NOMBRE) values (?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCodigo());
            statement.setString(2, entity.getCodigo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(TipoCentro entity) {
        String sql = "delete from tipocentro where CODIGO=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getCodigo());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public TipoCentro find(String id) {
        String sql = "select  CODIGO,NOMBRE from tipocentro where CODIGO= ? " ;
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
    public TipoCentro update(TipoCentro entity) {
        String sql = "UPDATE tipocentro SET nombre=? where codigo=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getCodigo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<TipoCentro> findAll() {
        String sql = "select CODIGO,NOMBRE from tipocentro" ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<TipoCentro> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }


    private TipoCentro createFromResultSet(ResultSet resultSet) throws SQLException {
        TipoCentro tipoCentro = new TipoCentro();
        tipoCentro.setCodigo(resultSet.getString("CODIGO"));
        tipoCentro.setNombre(resultSet.getString("NOMBRE"));


        return tipoCentro;
    }
}
