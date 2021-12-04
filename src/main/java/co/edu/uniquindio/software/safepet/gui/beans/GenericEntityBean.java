package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.GenericBO;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericEntityBean<E,K,T extends GenericBO<E,K>> extends AbstracBean {
    protected E selectedEntity;
    private final T bo;
    private final Class<E> type;
    private List<E> entities;
    private List<E> selectedEntities;
    protected boolean updating = false;

    public GenericEntityBean(T bo){
        this.bo = bo;
        type = findGenericType(0);
    }

    private Class<E> findGenericType(int index) {
        Class<?> klass = getClass();
        while (klass.getSuperclass() != null && (!(klass.getGenericSuperclass() instanceof ParameterizedType) || !( ((ParameterizedType)klass
                .getGenericSuperclass()).getActualTypeArguments()[index] instanceof Class ) ) ) {
            klass = klass.getSuperclass();
        }
        return (Class<E>) ((ParameterizedType)klass.getGenericSuperclass()).getActualTypeArguments()[index];
    }

    @PostConstruct
    public void initialize(){
        entities = bo.findAll();
        newEntity();
    }

    public void newEntity() {
        try {
            selectedEntity = type.getConstructor().newInstance();
            updating = false;
        } catch (Exception e) {
            showMessageError(null,"Error inesperado en el servidor");
        }
    }

    public void save(){
        if( updating ){
            update();
        } else {
            create();
        }
    }
    public void create(){
        try {
            bo.create(selectedEntity);
            entities.add(selectedEntity);
            showMessageInfo(null,"Operación exitosa");
        }catch (Exception e){
            showMessageError(null,"No se pudo completar la operación");
        }
    }

    public void update(){
        try {
            bo.update(selectedEntity);
            showMessageInfo(null,"Operación exitosa");
        }catch (Exception e){
            showMessageError(null,"No se pudo completar la operación");
        }
    }

    public void delete(){
        try {
            bo.delete(selectedEntity);
            entities.remove(selectedEntity);
            selectedEntity = null;
            showMessageInfo(null,"Operación exitosa");
        }catch (Exception e){
            showMessageError(null,"No se pudo completar la operación");
        }
    }


    protected T getBo() {
        return bo;
    }

    public E getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(E selectedEntity) {
        this.selectedEntity = selectedEntity;
        updating = true;
    }

    public List<E> getEntities() {
        return entities;
    }

    public void setEntities(List<E> entities) {
        this.entities = entities;
    }

    public List<E> getSelectedEntities() {
        return selectedEntities;
    }

    public void setSelectedEntities(List<E> selectedEntities) {
        this.selectedEntities = selectedEntities;
    }

    public boolean isUpdating() {
        return updating;
    }
}
