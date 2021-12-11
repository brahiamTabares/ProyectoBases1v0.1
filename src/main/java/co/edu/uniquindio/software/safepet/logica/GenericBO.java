package co.edu.uniquindio.software.safepet.logica;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericBO<T,E> extends Serializable {

    T create(T entity);

    void delete(final T entity);

    T find(final E id);

    T update(final T entity);

    List<T> findAll() throws SQLException;

}
