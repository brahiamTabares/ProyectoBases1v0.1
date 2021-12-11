package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.config.Datasource;
import co.edu.uniquindio.software.safepet.persistencia.entidades.HistoriaClinica;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MascotaBO implements GenericBO<Mascota,Integer>{

    @Resource(lookup= Datasource.DATASOURCE )
    private DataSource dataSource;

    @Override
    public Mascota create(Mascota entity) {
        String sql = "insert into mascota(ID,nombre,fecha_nacimiento,GENERO,PLAN_ID,RAZA_CODIGO,TIPOMASCOTA_ID) values (?,?,?,?,?,?,?) ";
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

        String sql = "delete from MASCOTA where ID=?";
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
        String sql = "select ID,nombre,fecha_nacimiento,GENERO,PLAN_ID,RAZA_CODIGO,TIPOMASCOTA_ID from MASCOTA where ID=?" ;
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
        String sql = "UPDATE MASCOTA SET NOMBRE=?, FECHA_NACIMIENTO=?,GENERO=?,PLAN_ID=?,RAZA_CODIGO=?,TIPOMASCOTA_ID=? where ID=? ";
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
        String sql = "select ID,nombre,fecha_nacimiento,GENERO,PLAN_ID,RAZA_CODIGO,TIPOMASCOTA_ID from MASCOTA ";
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
            mascota.setId(resultSet.getString("ID"));
            mascota.setNombre(resultSet.getString("NOMBRE"));
            mascota.setFecha_nacimiento(resultSet.getDate("FECHA_NACIMIENTO"));
            mascota.setGenero(resultSet.getString("GENERO"));
            mascota.setPlan_id(resultSet.getString("PLAN_ID"));
            mascota.setRaza_codigo(resultSet.getString("RAZA_CODIGO"));
            mascota.setTipomascota_id(resultSet.getString("TIPOMASCOTA_ID"));
            return mascota;
        }


}
