package erp.ws.sbo.client.swing.dao.impl;


import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IQrDao;

import erp.ws.sbo.client.swing.model.UQrm;


public class QrDao extends HibernateDaoSupport implements  IQrDao {
   private String hql;
   private Object[][] ob;
   private Integer docEntry;
   @SuppressWarnings("unused")
   private LocalSessionFactoryBean sessionFactory;

	@Override
	public void add(UQrm uqrm) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(uqrm);  
	}

	@Override
	public void update(UQrm uqrm) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().update(uqrm); 
	}

	@Override
	public UQrm queryByDocId(int id) {
		// TODO Auto-generated method stub
		return (UQrm)super.getHibernateTemplate().get(UQrm.class, id); 
	}

	@Override
	public Integer getMaxDocEntry() {
		// TODO Auto-generated method stub
		  hql = "SELECT max(docEntry) from U_QRm";
          ob = appMain.lt.sqlclob(hql,0,1);
          
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

	@Override
	public void delete(UQrm uqrm) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().delete(uqrm); 
	}

	

}
