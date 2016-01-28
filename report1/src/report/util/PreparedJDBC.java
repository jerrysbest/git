package report.util;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PreparedJDBC{	
    private Context initContext,envContext;
    private DataSource ds;
	public PreparedJDBC(){
		
	}
	
	public Connection get(String companycode) {
		// TODO Auto-generated method stub
		try {
			initContext = new InitialContext();		
			envContext  = (Context)initContext.lookup("java:/comp/env");
			String dd;
			if(companycode.equals("015")||companycode.equals("012"))
			{
				dd="_2012";
			}
			else
			{
				dd="_2013";
			}
			ds = (DataSource)envContext.lookup("jdbc/ufdata_"+companycode+dd);
			return ds.getConnection(); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}

