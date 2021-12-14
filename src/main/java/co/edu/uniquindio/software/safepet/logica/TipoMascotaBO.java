package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Examen;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoCentro;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoMascota;

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
public class TipoMascotaBO implements GenericBO<TipoMascota,String>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public TipoMascota create(TipoMascota entity) {

        String sql = "insert into tipomascota (id,tipo) values (?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getTipo());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;

    }

    @Override
    public void delete(TipoMascota entity) {
        String sql = "delete from tipomascota where id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public TipoMascota find(String id) {
        String sql = "select  id,tipo from tipomascota where id= ? " ;
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
    public TipoMascota update(TipoMascota entity) {
        String sql = "UPDATE tipomascota SET tipo=? where id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {

            statement.setString(1, entity.getTipo());
            statement.setString(2, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<TipoMascota> findAll() {
        String sql = "select id,tipo from  tipomascota";
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            ResultSet resultSet = statement.executeQuery();
            List<TipoMascota> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add( createFromResultSet(resultSet) );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }



    private TipoMascota createFromResultSet(ResultSet resultSet) throws SQLException {
        TipoMascota tipoMascota= new TipoMascota();
        tipoMascota.setId(resultSet.getString("id"));
        tipoMascota.setTipo(resultSet.getString("tipo"));


        return tipoMascota;
    }



}
