package erp.ws.sbo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.listsn;
import erp.ws.sbo.dao.IListSN;

public class ListSN extends HibernateDaoSupport implements IListSN {
     private String hql;
     private Object[][] nob;
	@Override
	public void add(listsn lsn) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(lsn);  
	}

	@Override
	public listsn queryByDocId(int id) {
		// TODO Auto-generated method stub
		return (listsn)super.getHibernateTemplate().get(listsn.class, id); 
	}

	@Override
	public Integer getMaxDocEntry() {
		// TODO Auto-generated method stub
		hql="select isnull(max(id),0) from [@listsn]";
		nob = appMain.lt.sqlclob(hql,0,1);
        return Integer.valueOf(nob[0][0].toString());
	}

}
