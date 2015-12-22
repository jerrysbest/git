package erp.ws.sbo.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Sqlclass {

	public boolean exesql(String hql)
	{
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	    	   
	    	   
	    	   
	       }catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 		
	       
	      }
		return false;
		
	}
}
