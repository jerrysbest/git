package com.wg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wg.bean.User;

public interface UserDao {
	 /**
	  *  ȡ�������û�
	  *  @author WG
	  *  @return �û��б�
	  */
	 public List<User> getAllUsers(); 
	 /**
	  *  �����û��˺���Ϣȡ���û�
	  *  @author WG
	  *  @return
	  */
	 public User getUser(@Param("username")String userName,@Param("password")String password);
	 
	 /**
	  *  ����û�
	  *  @author WG
	  *  @param user
	  *  @return
	  */
	 public void insertUser(User user);
	 /**
	  *  �����û���Ϣ
	  *  @author WG
	  *  @param user
	  *  @return
	  */
	 public int updateUser(User user);
	 /**
	  *  ɾ���û�
	  *  @author WG
	  *  @param id
	  *  @return
	  */
	 public int deleteUser(Long id);
}
