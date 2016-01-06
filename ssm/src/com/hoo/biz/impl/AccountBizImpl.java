package com.hoo.biz.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.hoo.biz.AccountBiz;
import com.hoo.entity.*;
import javax.inject.Inject;
import com.hoo.dao.*;
import org.springframework.stereotype.Service;
import com.hoo.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
@Service("accountBizImpl")
public class AccountBizImpl<T extends Account> implements AccountBiz<T> {

	@Autowired
	@Qualifier("accountDaoImpl")
	private AccountDao<T> dao;
	public boolean addAccount(T entity) throws DataAccessException {
		if(entity==null){
			throw new BizException(Account.class.getName()+"对象参数为empty!");
			
		}
		return dao.addAccount(entity);
	}

	public T getAccount(Integer id) throws DataAccessException {
		 return dao.getAccount(id);
	}

	public List getList() throws DataAccessException {
		return dao.getList();
	}

}
