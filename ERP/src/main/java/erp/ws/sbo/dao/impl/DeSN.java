package erp.ws.sbo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.model.desn;
import erp.ws.sbo.dao.IDeSN;

public class DeSN extends HibernateDaoSupport implements IDeSN {

	@Override
	public void add(desn lsn) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(lsn);  
	}

	@Override
	public desn[] queryByDocId(String[] id) {
		// TODO Auto-generated method stub
		return null;
	}

}
