package erp.ws.sbo.dao.impl;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import erp.ws.sbo.client.swing.model.userauther;
import erp.ws.sbo.client.swing.model.userautherId;
import erp.ws.sbo.dao.IUserauther;

public class Userauther extends HibernateDaoSupport implements IUserauther {
	 @SuppressWarnings("unused")
	 private LocalSessionFactoryBean sessionFactory;
	@Override
	public void update(userauther lsn) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().update(lsn);  
	}

	@Override
	public userauther queryByDocId(userautherId id) {
		// TODO Auto-generated method stub
		return (userauther)super.getHibernateTemplate().get(userauther.class, id);  
	}
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
