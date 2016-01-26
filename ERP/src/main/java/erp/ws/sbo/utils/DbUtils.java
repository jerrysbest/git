package erp.ws.sbo.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class DbUtils<T1,T2> {
    private Statement sqlStatemen;
	public String[] getColumnNames(T1 ob) {
		// TODO Auto-generated method stub				
		java.lang.reflect.Field[] f1=ob.getClass().getDeclaredFields(); 
		String[] s =new String[f1.length-1];	
		//JOptionPane.showMessageDialog(null,f1.length);       
		for(int i=0;i<f1.length-1;i++)
		{
			 String name = f1[i].getName();    //��ȡ���Ե�����
			 String type = f1[i].getGenericType().toString();    //��ȡ���Ե�����
	         if(type.equals("class java.lang.String")){   //���type�������ͣ���ǰ�����"class "�����������
		     name=name.substring(0, 1).toUpperCase()+name.substring(1);
		    //JOptionPane.showMessageDialog(null,name);
		     Method m = null;
			try {
				m = ob.getClass().getMethod("get"+name);
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         String value = null;
			try {
				value = (String) m.invoke(ob);
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}    //����getter������ȡ����ֵ
	         if(value != null){	         
	             s[i]=value;
				 //JOptionPane.showMessageDialog(null,value);
	         }
				
			}	
		 }
		//JOptionPane.showMessageDialog(null,s.size());
	   return s;
		
     }

	public String[] getDataTypes(T2 ob) {
		// TODO Auto-generated method stub
		String[] s = null;		
		java.lang.reflect.Field[] f1=ob.getClass().getDeclaredFields(); 
		s=new String[f1.length];
		for(int i=1;i<f1.length;i++)
		{
		System.out.println(f1[i].getType().toString());	
		s[i]=f1[i].getType().toString();		
		}	
		return s;
	}
	@SuppressWarnings("deprecation")
	public void exeSql(String sQLQuery) {
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** ������������ *//  
	    	   Connection con = session.connection();	    	 
	    	   sqlStatemen= con.createStatement();
	    	   sqlStatemen.executeUpdate(sQLQuery);		      
	           t.commit();	        
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
		
	}
	}
	@SuppressWarnings("unchecked")
	public Object[][] executeQuery(String sQLQuery) {
		// TODO Auto-generated method stub
		Object[][]  ob = null; 	
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** ������������ *//  
			Query q=session.createQuery(sQLQuery);	  
				 
			q.setFirstResult(0);
			q.setMaxResults(1000);			
		    List<Object[]> list=q.list();
		  
		  // JOptionPane.showMessageDialog(null,list.size());  
		    //JOptionPane.showMessageDialog(null,list.get(0).length);  */
		    if(list.size()==0)
		    {
		    	return ob;
		    }
		    ob=new Object[list.size()][list.get(0).length];
		    for(int i=0;i<list.size();i++){
			     // ob1=new Object[list.get(0).length];
			    
		         for(int j=0;j<list.get(i).length;j++)
		         {
		        	//System.out.println(j);
		        	 ob[i][j]=(list.get(i))[j];
		         }
		    	
		    }		    
	        t.commit();	        
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
		
	}
	      return  ob;
   }
	
	@SuppressWarnings("unchecked")
	public Object[][] executeSQLQuery(String sQLQuery) {
		// TODO Auto-generated method stub
		Object[][]  ob = null; 	
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** ������������ *//  
			Query q=session.createSQLQuery(sQLQuery);	  
				 
			q.setFirstResult(0);
			q.setMaxResults(1000);			
		    List<Object[]> list=q.list();
		  
		    //JOptionPane.showMessageDialog(null,list.size());  
		    //JOptionPane.showMessageDialog(null,list.get(0).length);  
		    if(list.size()==0)
		    {
		    	return ob;
		    }
		    ob=new Object[list.size()][list.get(0).length];
		    for(int i=0;i<list.size();i++){
			     // ob1=new Object[list.get(0).length];
			    
		         for(int j=0;j<list.get(i).length;j++)
		         {
		        	//System.out.println(j);
		        	 ob[i][j]=(list.get(i))[j];
		         }
		    	
		    }		    
	        t.commit();	        
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
		
	}
	      return  ob;
   }
	@SuppressWarnings("unused")
	private   void   displayResultSet(List<?>  rs)              
   {  
         /*   //��λ�����һ����¼
            boolean   moreRecords   =   rs.next();    
            //���û�м�¼������ʾһ����Ϣ
            if   (   !   moreRecords   )   {  
                  JOptionPane.showMessageDialog(null,"��������޼�¼ ");  
                
                  return;  
            }  
            Vector   columnHeads   =   new   Vector();  
            Vector   rows   =   new   Vector();  
            try   {  
                  //��ȡ�ֶε�����
                  ResultSetMetaData   rsmd   =   rs.getMetaData();  
                  for   (   int   i   =   1;   i   <=   rsmd.getColumnCount();   ++i   )  
                        columnHeads.addElement(   rsmd.getColumnName(   i   )   );  
                  //��ȡ��¼��
                  do   {  
                        rows.addElement(   getNextRow(   rs,   rsmd   )   );  
                  }   while   (   rs.next()   );  
                  //�ڱ������ʾ��ѯ���
                  JTable    table   =   new   JTable(   rows,   columnHeads   );  
                  JScrollPane   scroller   =   new   JScrollPane(   table   );  
                  Container   c   =   getContentPane();  
                  c.remove(1);  
                  c.add(   scroller,   BorderLayout.CENTER   );  
                  //ˢ��Table
                  c.validate();  
            }  
            catch   (   SQLException   sqlex   )   {  
                  sqlex.printStackTrace();  
            }  */
      }   
}
