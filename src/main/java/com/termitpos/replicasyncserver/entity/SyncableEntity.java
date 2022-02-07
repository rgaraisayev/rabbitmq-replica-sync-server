package com.termitpos.replicasyncserver.entity;


import java.io.Serializable;

public class SyncableEntity implements Serializable {

    private String rowIdentifiers;
    private String logRowIdentifiers;
    private String entityName;
    private String entity;
    private boolean deleteEntity;

    public SyncableEntity() {

    }

    public SyncableEntity(String rowIdentifiers, String entity, String entityName) {
        this.rowIdentifiers = rowIdentifiers;
        this.entity = entity;
        this.entityName = entityName;
    }

    public SyncableEntity(String rowIdentifiers, String logRowIdentifiers, String entity, String entityName) {
        this.rowIdentifiers = rowIdentifiers;
        this.logRowIdentifiers = logRowIdentifiers;
        this.entity = entity;
        this.entityName = entityName;
    }


    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getRowIdentifiers() {
        return rowIdentifiers;
    }

    public void setRowIdentifiers(String rowIdentifiers) {
        this.rowIdentifiers = rowIdentifiers;
    }

    @Override
    public String toString() {
        return "SyncableEntity{" +
                "rowIdentifiers='" + rowIdentifiers + '\'' +
                ", logRowIdentifiers='" + logRowIdentifiers + '\'' +
                ", entityName='" + entityName + '\'' +
                ", entity='" + entity + '\'' +
                ", deleteEntity=" + deleteEntity +
                '}';
    }

    public String getLogRowIdentifiers() {
        return logRowIdentifiers;
    }

    public void setLogRowIdentifiers(String logRowIdentifiers) {
        this.logRowIdentifiers = logRowIdentifiers;
    }

    public boolean isDeleteEntity() {
        return deleteEntity;
    }

    public void setDeleteEntity(boolean deleteEntity) {
        this.deleteEntity = deleteEntity;
    }
}

