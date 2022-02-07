package com.termitpos.replicasyncserver.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.termitpos.replicasyncserver.entity.Section;
import com.termitpos.replicasyncserver.entity.SyncableEntity;

public class EntityUtil {
    private static final Gson gson = new Gson();

    public static String generateWhereClauseFromRowIdents(SyncableEntity syncableEntity) {
        JsonObject ri = gson.fromJson(syncableEntity.getRowIdentifiers(), JsonObject.class);
        StringBuilder sb = new StringBuilder();
        for (String s : ri.keySet()) {
            sb.append(s).append("=").append(ri.get(s).toString().replace("\"", "'")).append(" and ");
        }
        sb.append(" 1=1 ");
        return sb.toString();
    }

    public static String getSchemaNameFromSection(Section section) {
        return "section_" + section.getUuid().replace("-", "_");
    }
}
