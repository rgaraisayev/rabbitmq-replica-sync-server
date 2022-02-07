CREATE OR REPLACE FUNCTION audit.updaterow_by_jsondata_with_id(par_table_name text, par_table_schema text, whereclause text, jsondata jsonb)
 RETURNS boolean
 LANGUAGE plpgsql
AS $function$DECLARE 
 inputstring text;
BEGIN

  
 SELECT string_agg(key || '=' || value, ',') into inputstring
   FROM jsonb_each(jsondata),
   information_schema.columns ic where ic.table_schema = par_table_schema
   AND ic.table_name = par_table_name and ic.column_name  = key;
    
   inputstring := replace(inputstring, '"', '''');
    raise info '%s', 'update ' || par_table_schema || '.' || par_table_name || ' set '
  ||  inputstring || ' where  ' || whereClause;
   
  EXECUTE 'update ' || par_table_schema || '.' || par_table_name || ' set '
  ||  inputstring || ' where ' || whereClause;
   return true;
END;
$function$
;
