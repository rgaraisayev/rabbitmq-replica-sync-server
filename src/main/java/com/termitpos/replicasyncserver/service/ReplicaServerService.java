package com.termitpos.replicasyncserver.service;

import com.termitpos.replicasyncserver.entity.Section;
import com.termitpos.replicasyncserver.entity.SyncableEntity;
import com.termitpos.replicasyncserver.model.SyncActionEvent;
import com.termitpos.replicasyncserver.repository.GeneralRepository;
import com.termitpos.replicasyncserver.util.EntityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReplicaServerService {

    private final GeneralRepository generalRepository;


    public ReplicaServerService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public SyncActionEvent handleSyncEvent(SyncActionEvent event) {
        System.out.println(event);
        Section section = event.getSection();
        String schemaName = EntityUtil.getSchemaNameFromSection(section);
        List<SyncableEntity> syncedEntities = new ArrayList<>();
        List<SyncableEntity> deletedEntities = new ArrayList<>();
        for (List<SyncableEntity> syncableEntities : event.getEntitiesToSync()) {
            for (SyncableEntity syncableEntity : syncableEntities) {
                try {
                    String tableName = syncableEntity.getEntityName();
                    String whereClauseFromRowIdents = EntityUtil.generateWhereClauseFromRowIdents(syncableEntity);
                    if (syncableEntity.isDeleteEntity()) {
                        generalRepository.deleteRows(schemaName + "." + tableName, whereClauseFromRowIdents);
                        deletedEntities.add(syncableEntity);
                    } else {
                        boolean rowExists = generalRepository.isRowExists(schemaName, tableName, whereClauseFromRowIdents);
                        if (rowExists) {
                            generalRepository.updateRow(schemaName,
                                    tableName,
                                    whereClauseFromRowIdents,
                                    syncableEntity.getEntity());
                        } else {
                            generalRepository.insertRows(schemaName + "." + tableName, syncableEntity.getEntity());
                        }
                        syncedEntities.add(syncableEntity);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return new SyncActionEvent(syncedEntities, deletedEntities);
    }

}
