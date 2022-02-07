package com.termitpos.replicasyncserver.model;

import com.termitpos.replicasyncserver.entity.Section;
import com.termitpos.replicasyncserver.entity.SyncableEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SyncActionEvent implements Serializable {

    private List<List<SyncableEntity>> entitiesToSync = new ArrayList<>();
    private List<SyncableEntity> syncedEntities = new ArrayList<>();
    private List<SyncableEntity> deletedEntities = new ArrayList<>();
    private Section section;

    public SyncActionEvent() {

    }

    public SyncActionEvent(List<SyncableEntity> syncedEntities, List<SyncableEntity> deletedEntities) {
        this.syncedEntities = syncedEntities;
        this.deletedEntities = deletedEntities;
    }

    public SyncActionEvent(Section section) {
        this.section = section;
    }

    public void add(List<SyncableEntity> syncableEntities) {
        entitiesToSync.add(syncableEntities);
    }

    public List<List<SyncableEntity>> getEntitiesToSync() {
        return entitiesToSync;
    }

    public void setEntitiesToSync(List<List<SyncableEntity>> entitiesToSync) {
        this.entitiesToSync = entitiesToSync;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "SyncActionEvent{" +
                "entitiesToSync=" + entitiesToSync +
                ", syncedEntities=" + syncedEntities +
                ", deletedEntities=" + deletedEntities +
                ", section=" + section +
                '}';
    }

    public List<SyncableEntity> getSyncedEntities() {
        return syncedEntities;
    }

    public void setSyncedEntities(List<SyncableEntity> syncedEntities) {
        this.syncedEntities = syncedEntities;
    }

    public List<SyncableEntity> getDeletedEntities() {
        return deletedEntities;
    }

    public void setDeletedEntities(List<SyncableEntity> deletedEntities) {
        this.deletedEntities = deletedEntities;
    }
}
