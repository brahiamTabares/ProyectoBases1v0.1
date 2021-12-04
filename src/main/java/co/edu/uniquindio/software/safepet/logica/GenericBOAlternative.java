package co.edu.uniquindio.software.safepet.logica;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GenericBOAlternative<T,E> implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Connection connection;
    private final Class<T> type;
    private String tableName;

    public GenericBOAlternative(String tableName){
        this.tableName = tableName;
        type = findGenericType(0);
    }

    private Class<T> findGenericType(int index) {
        Class<?> klass = getClass();
        while (klass.getSuperclass() != null && (!(klass.getGenericSuperclass() instanceof ParameterizedType) || !( ((ParameterizedType)klass
                .getGenericSuperclass()).getActualTypeArguments()[index] instanceof Class ) ) ) {
            klass = klass.getSuperclass();
        }
        return (Class<T>) ((ParameterizedType)klass.getGenericSuperclass()).getActualTypeArguments()[index];
    }

    public T create(T entity) {
        String sql = "insert into "+tableName+" values (%s);" ;
        Map<String,?> data = toMap(entity);
        try(PreparedStatement statement = connection.prepareStatement( String.format(sql, data.keySet().stream().map(t->"?").collect(Collectors.joining(",")) ) )) {
            Object[] dataValues = data.values().toArray();
            for (int i = 0; i < dataValues.length; i++) {
                statement.setObject(i, dataValues[i]);
            }

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    public void delete(final T entity){
        deleteById( getId(entity) );
    }

    public void deleteById(final E id){
        String sql = "delete from "+tableName+" where "+getIdName() + " = ? " ;
        try (PreparedStatement statement = connection.prepareStatement( sql )){
            statement.setObject(0,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    public T find(final E id){
        String sql = "select * from "+tableName+" where "+getIdName() + " = ? " ;
        try (PreparedStatement statement = connection.prepareStatement( sql )){
            statement.setObject(0,id);
            ResultSet resultSet = statement.executeQuery();
            return createFromResultSet(resultSet).stream().findFirst().orElse(null);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
    }

    public T update(final T entity){
        Map<String,?> data = toMap(entity);
        String sql = "update "+tableName+" set " + data.keySet().stream().map(s -> s+"=? ").collect(Collectors.joining(",")) ;

        try(PreparedStatement statement = connection.prepareStatement( sql )) {
            Object[] dataValues = data.values().toArray();
            for (int i = 0; i < dataValues.length; i++) {
                statement.setObject(i, dataValues[i]);
            }

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Operacion no completada:"+e.getMessage(),e);
        }
        return entity;
    }

    public List<T> findAll(){
        return entityManager.createQuery(listCriteriaQuery()).getResultList();
    }

    protected CriteriaQuery<T> listCriteriaQuery(){
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        return criteriaQuery.select(root);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected String getTableName() {
        return tableName;
    }

    protected abstract E getId(T entity);
    protected abstract String getIdName();

    public abstract Map<String,?> toMap(T entity);
    protected abstract Collection<T> createFromResultSet(ResultSet resultSet);
}
