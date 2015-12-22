package erp.ws.sbo.client.swing.util.general;

import java.util.List;

import javax.swing.JComboBox;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import erp.ws.sbo.utils.MdbHibernateUtils;

public class JUComboBox extends JComboBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JUComboBox(String hql) {
		// TODO Auto-generated constructor stub
		this.binding(hql);
	}
    
	@SuppressWarnings("unchecked")
	private void binding(String hql){
	 //绑定公司	   
		Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** 生成主键对象 *//        
	           Query query=(Query)session.createSQLQuery(hql);
	    
	           List<Object[]> list=query.list();
	           if(list.size()==0)
	           {
	        	   return;
	           }
	           t.commit();
	    	      
	           for(Object[] object : list){
	        	  if(object[1]==null)
	        	  {
	        		  object[1]="";
	        	  }
	           this.addItem(new  ComboBoxItem(object[0],object[1].toString()));
	           }
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
	   	 }

      
	}
}
