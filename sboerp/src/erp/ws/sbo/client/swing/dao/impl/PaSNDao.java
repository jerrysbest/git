package erp.ws.sbo.client.swing.dao.impl;

import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.abs.AbsPaSN;
import erp.ws.sbo.client.swing.model.pasn;

public class PaSNDao extends AbsPaSN {
	private String hql;
    private Object[][] ob;
    private Integer docEntry;
    @SuppressWarnings("unused")
    private LocalSessionFactoryBean sessionFactory;
	@Override
	public Integer getMaxDocEntry() {
		// TODO Auto-generated method stub
		hql = "SELECT max(docEntry) from [@PASN]";
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

	@Override
	public void add(pasn paSN) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(paSN);  
	}

	@Override
	public void update(pasn paSN) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().update(paSN); 
	}

	@Override
	public pasn queryByDocId(int id) {
		// TODO Auto-generated method stub
		return (pasn)super.getHibernateTemplate().get(pasn.class, id); 
	}
	
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
