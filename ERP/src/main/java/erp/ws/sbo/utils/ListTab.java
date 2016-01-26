package erp.ws.sbo.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ListTab {
    
	public ListTab()
	{
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[][] clob(String hql,int start,int end)
	{  Object[][] ob = null;
	    int col;
	    List<Object[]> list1;
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** 生成主键对象 *//        
			Query q=session.createQuery(hql);	  
				 
			q.setFirstResult(start);
			q.setMaxResults(end);			
		    List  list=q.list();		    
		    if(list.size()==0||list.get(0)==null)
		    {			    	
		    	return ob;
		    }
		    if(list.get(0).getClass().toString().contains("Object"))
		    {
		    	list1=q.list();
		    	col=list1.get(0).length;
		    	ob=new Object[list1.size()][col];
		    	 for(int i=0;i<list1.size();i++){				    
			         for(int j=0;j<col;j++)
			         {
			        	//System.out.println(j);
			        	 ob[i][j]=(list1.get(i))[j];
			         }			    	
			    }	
		    }
		    else{
		    	col=1;
		    	ob=new Object[list.size()][col];
		    	 for(int i=0;i<list.size();i++){			  
			        	 ob[i][0]=list.get(i);		    	
			    }	
		    }
		    
		   
		   /* JOptionPane.showMessageDialog(null,list.size());  
		    JOptionPane.showMessageDialog(null,list.get(0).length);  */
		 
	        t.commit();	        
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 		
	      }
	      return ob;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object[][] sqlclob(String hql,int start,int end)
	{  Object[][] ob = null;
	    int col;
	    List<Object[]> list1;
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** 生成主键对象 *//        
			Query q=session.createSQLQuery(hql);	  
				 
			q.setFirstResult(start);
			q.setMaxResults(end);			
		    List  list=q.list();		    
		    if(list.size()==0||list.get(0)==null)
		    {			    	
		    	return ob;
		    }
		    if(list.get(0).getClass().toString().contains("Object"))
		    {
		    	list1=q.list();
		    	col=list1.get(0).length;
		    	ob=new Object[list1.size()][col];
		    	 for(int i=0;i<list1.size();i++){				    
			         for(int j=0;j<col;j++)
			         {
			        	//System.out.println(j);
			        	 ob[i][j]=(list1.get(i))[j];
			         }			    	
			    }	
		    }
		    else{
		    	col=1;
		    	ob=new Object[list.size()][col];
		    	 for(int i=0;i<list.size();i++){			  
			        	 ob[i][0]=list.get(i);		    	
			    }	
		    }
		    
		   
		   /* JOptionPane.showMessageDialog(null,list.size());  
		    JOptionPane.showMessageDialog(null,list.get(0).length);  */
		 
	        t.commit();	        
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 		
	      }
	      return ob;
	}
}
