package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class MascotaBO implements GenericBO<Mascota,Integer>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public Mascota create(Mascota entity) {
        String sql = "insert into mascota(id,nombre,fecha_nacimiento,genero,plan_id,raza_codigo,tipomascota_id) values (?,?,?,?,?,?,?) ";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1,entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setDate(3,(Date) entity.getFecha_nacimiento());
            statement.setString(4, entity.getGenero());
            statement.setString(5,entity.getPlan_id());
            statement.setString(6,entity.getRaza_codigo());
            statement.setString(7,entity.getTipomascota_id());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public void delete(Mascota entity) {

        String sql = "delete from mascota where id=?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement( sql ) ) {statement.setString(1,entity.getId());
            statement.setString(1,entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }

    }

    @Override
    public Mascota find(Integer id) {
        String sql = "select id,nombre,fecha_nacimiento,genero,plan_id,raza_codigo,tipomascota_id from mascota where id=?" ;
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
    public Mascota update(Mascota entity) {
        String sql = "UPDATE mascota SET nombre=?, fecha_nacimiento=?,genero=?,plan_id=?,raza_codigo=?,tipomascota_id=? where id=? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql ) ) {
            statement.setString(1,entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setDate(3,(Date) entity.getFecha_nacimiento());
            statement.setString(4, entity.getGenero());
            statement.setString(5,entity.getPlan_id());
            statement.setString(6,entity.getRaza_codigo());
            statement.setString(7,entity.getTipomascota_id());
            statement.executeUpdate();
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Mascota> findAll() throws SQLException {
        String sql = "select ID,nombre,fecha_nacimiento,genero,plan_id,raza_codigo,tipomascota_id from mascota ";
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            List<Mascota> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:" + e.getMessage(), e);
        }
    }

        private Mascota createFromResultSet (ResultSet resultSet) throws SQLException {
            Mascota mascota = new Mascota();
            mascota.setId(resultSet.getString("id"));
            mascota.setNombre(resultSet.getString("nombre"));
            mascota.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
            mascota.setGenero(resultSet.getString("genero"));
            mascota.setPlan_id(resultSet.getString("plan_id"));
            mascota.setRaza_codigo(resultSet.getString("raza_codigo"));
            mascota.setTipomascota_id(resultSet.getString("tipomascota_id"));
            return mascota;
        }


    public List<Mascota> findByPlan(String id) {
        String sql = "select id,nombre,fecha_nacimiento,genero,plan_id,raza_codigo,tipomascota_id from mascota where plan_id=?" ;
        try (Connection connection = dataSource.getConnection();PreparedStatement statement = connection.prepareStatement( sql )){
            statement.setObject(1,id);
            ResultSet resultSet = statement.executeQuery();
            List<Mascota> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:" + e.getMessage(), e);
        }
    }
}
