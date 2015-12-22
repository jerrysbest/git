package erp.ws.sbo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.log;
import erp.ws.sbo.dao.ILog;

public class Log extends HibernateDaoSupport implements ILog {

	@Override
	public void add(log log) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(log);  
	}

	@Override
	public log queryByDocId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getusersign() {
		// TODO Auto-generated method stub
		return appMain.oCompany.getUserSignature();
	}

	

}
