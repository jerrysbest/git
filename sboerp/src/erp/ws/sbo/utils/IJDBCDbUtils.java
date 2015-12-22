package erp.ws.sbo.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface IJDBCDbUtils {
	
	  Boolean gettable(Connection con,String tablename);
	  void setkey(String field,String value);
	  void s(String field,String value);
	  Object[][] executeSQLQuery(Connection con,String sQLQuery,int c);
	  void recordMappingToMap(String fieldClassName, String fieldName, ResultSet rs, Map<String, Object> fieldValue) throws SQLException;
	  void update(Connection con,String sQLQuery) throws SQLException;
	  void save(Connection con,String sQLQuery) throws SQLException;
}
