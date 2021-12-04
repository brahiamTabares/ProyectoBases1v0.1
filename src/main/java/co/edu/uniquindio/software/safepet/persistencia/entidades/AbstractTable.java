package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Map;

public abstract class AbstractTable {
    private String tableName;

    public AbstractTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public abstract Map<String,?> toMap();

//    public abstract void fill();

}
