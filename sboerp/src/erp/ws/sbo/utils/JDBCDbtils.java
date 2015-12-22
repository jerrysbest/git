package erp.ws.sbo.utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class JDBCDbtils implements IJDBCDbUtils {

	private String sql="",sql1="",sql2="",sql3="";
	private PreparedStatement pstmt = null;
	@SuppressWarnings("unused")
	private ResultSet rs;
	@Override
	public Boolean gettable(Connection con, String tablename) {
		// TODO Auto-generated method stub
		sql ="select  top 10 *  from "+tablename;
		try {
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery();
			sql1="insert into ";
			sql2=" (";
			sql3=") values (";
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public void setkey(String field, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void s(String field, String value) {
		// TODO Auto-generated method stub
		if(sql2.substring(sql2.length()-1).equals("("))
		{
		    sql2=sql2+field;
		}
		else
		{
			sql2=sql2+","+field;
		}
		if(sql3.substring(sql3.length()-1).equals("("))
		{
		    sql3=sql3+value;
		}
		else
		{
			sql2=sql2+","+value;
		}
		
	}

	@Override
	public Object[][] executeSQLQuery(Connection con, String sQLQuery, int c) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * 将ResultSet结果集中的记录映射到Map对象�?.
     * @param fieldClassName 是JDBC API中的类型名称,
     * @param fieldName 是字段名�?
     * @param rs 是一个ResultSet查询结果�?,
     * @param fieldValue Map对象,用于存贮�?条记�?.
     * @throws SQLException
     */
	@Override
	public void recordMappingToMap(String fieldClassName, String fieldName, ResultSet rs, Map<String, Object> fieldValue) throws SQLException {
        fieldName = fieldName.toLowerCase();

        //优先规则：常用类型靠�?
        if (fieldClassName.equals("java.lang.String")) {
            String s = rs.getString(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Integer")) {
            int s = rs.getInt(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);//早期jdk�?要包装，jdk1.5后不�?要包�?
            }
        } else if (fieldClassName.equals("java.lang.Long")) {
            long s = rs.getLong(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Boolean")) {
            boolean s = rs.getBoolean(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Short")) {
            short s = rs.getShort(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Float")) {
            float s = rs.getFloat(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Double")) {
            double s = rs.getDouble(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Timestamp")) {
            java.sql.Timestamp s = rs.getTimestamp(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Date") || fieldClassName.equals("java.util.Date")) {
            java.util.Date s = rs.getDate(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Time")) {
            java.sql.Time s = rs.getTime(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Byte")) {
            byte s = rs.getByte(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, new Byte(s));
            }
        } else if (fieldClassName.equals("[B") || fieldClassName.equals("byte[]")) {
            //byte[]出现在SQL Server�?
            byte[] s = rs.getBytes(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.math.BigDecimal")) {
            BigDecimal s = rs.getBigDecimal(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Object")
                || fieldClassName.equals("oracle.sql.STRUCT")) {
            Object s = rs.getObject(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Array")
                || fieldClassName.equals("oracle.sql.ARRAY")) {
            java.sql.Array s = rs.getArray(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Clob")) {
            java.sql.Clob s = rs.getClob(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Blob")) {
            java.sql.Blob s = rs.getBlob(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else {//对于其它任何未知类型的处�?
            Object s = rs.getObject(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        }

    }

	@Override
	public void update(Connection con, String tablename) throws SQLException{
		// TODO Auto-generated method stub
		
	}
    /*
     * (non-Javadoc)
     * @see com.omi.jaxws.utils.IJDBCDbUtils#save(java.sql.Connection, java.lang.String)
     * Use con.commit() do transaction,http://blog.csdn.net/caomiao2006/article/details/22412755;
     */
	@Override
	public void save(Connection con, String tablename) throws SQLException {
		// TODO Auto-generated method stub
        sql=sql1+tablename+sql2+sql3+")";
		pstmt=con.prepareStatement(sql);
		pstmt.executeUpdate();						
	}

}
