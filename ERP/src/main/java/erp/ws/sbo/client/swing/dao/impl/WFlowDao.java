package erp.ws.sbo.client.swing.dao.impl;


import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.abs.AbsWFlowDao;
import erp.ws.sbo.client.swing.model.wflist;

public class WFlowDao extends AbsWFlowDao {
   private String hql;
   private Object[][] ob;
   private Integer docEntry;
   @SuppressWarnings("unused")
   private LocalSessionFactoryBean sessionFactory;
   
	@Override
	public void add(wflist workflow) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(workflow);  
	}

	@Override
	public void update(wflist workflow) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().update(workflow); 
	}

	@Override
	public wflist queryByDocId(int id) {
		// TODO Auto-generated method stub
		return (wflist)super.getHibernateTemplate().get(wflist.class, id); 
	}

	@Override
	public Integer getMaxDocEntry() {
		// TODO Auto-generated method stub
		  hql = "SELECT max(docEntry) from wflist";
          ob = appMain.lt.clob(hql,0,1);
          
           if(ob==null||ob.length==0)
           {
         	  docEntry=1;
           }
           else
           {
        	  docEntry= Integer.valueOf(ob[0][0].toString())+1;
           }
           return docEntry;
		
	}

	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

}
