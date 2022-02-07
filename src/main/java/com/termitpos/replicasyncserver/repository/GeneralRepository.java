package com.termitpos.replicasyncserver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;

@Repository
public class GeneralRepository {
    private final JdbcTemplate jdbcTemplate;

    public GeneralRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public boolean isRowExists(String schema, String name, String whereClause) {
        Integer integer = jdbcTemplate.queryForObject(
                "select count(*) from " + schema + "." + name + " where " + whereClause,
                Integer.class
        );
        return integer != null && integer == 1;
    }

    @Transactional
    public void insertRows(String name, String data) {
        jdbcTemplate.execute("insert into " + name + " select * from " +
                "jsonb_populate_record(NULL::" + name + ", '" + data + "')");
    }

    @Transactional
    public void deleteRows(String name,  String whereClause) {
        jdbcTemplate.execute("delete from " + name + " where " + whereClause);
    }

    @Transactional
    public void updateRow(String schema, String name, String whereClause, String data) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("audit.updaterow_by_jsondata_with_id").withoutProcedureColumnMetaDataAccess();
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("par_table_name", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("par_table_schema", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("whereClause", Types.VARCHAR));
        simpleJdbcCall.addDeclaredParameter(new SqlParameter("jsondata", Types.CLOB));

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("par_table_name", name)
                .addValue("par_table_schema", schema)
                .addValue("whereClause", whereClause)
                .addValue("jsondata", data);
        simpleJdbcCall.execute(in);
    }
}
