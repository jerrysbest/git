package com.wg.service;

import java.util.List;

import com.wg.bean.User;

public interface UserService {
	/**
	 * 取得所有用户
	 * 
	 * @author JLC
	 * @return 用户列表
	 */
	public List<User> getUsers() throws Exception;

	/**
	 * 取得用户信息
	 * 
	 * @author JLC
	 * @return User对象
	 * @throws Exception
	 */
	public User getUserInfo(User user) throws Exception;

	/**
	 * 保存用户
	 * 
	 * @author JLC
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void saveUser(User user) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @author JLC
	 * @param id
	 * @return
	 */
	public int deleteUser(Long id) throws Exception;
}
