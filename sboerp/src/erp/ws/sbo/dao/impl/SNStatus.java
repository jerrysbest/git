package erp.ws.sbo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.dao.ISNStatus;

public class SNStatus extends HibernateDaoSupport implements ISNStatus {

	@Override
	public void add(snstatus sns) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(sns);  
	}

	@Override
	public void update(snstatus sns) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().update(sns); 
	}

	@Override
	public snstatus queryByDocId(String sn) {
		// TODO Auto-generated method stub
		return (snstatus)super.getHibernateTemplate().get(snstatus.class, sn); 
	}

}
