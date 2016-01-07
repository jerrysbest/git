package com.wg.test;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.wg.bean.User;
import com.wg.common.Config;
import com.wg.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public ModelAndView regist(HttpServletRequest request, User user) {
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("username", user.getUsername());
		request.setAttribute("password", user.getPassword());
		System.out.println(user.toString());
		return new ModelAndView("succ");
	}

	/***
	 * �û���½
	 * <p>
	 * ע�����ã�ֻ����POST�ύ���÷���
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,String username, String password) throws Exception {
		// ��֤���ݹ����Ĳ����Ƿ���ȷ�����򷵻ص���½ҳ�档
		if (this.checkParams(new String[] { username, password })) {
			User loginExit = userService.getUserInfo(username, password);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("password", password);
			if (loginExit == null) {
			    System.out.println("�û�������");

				request.getSession().setAttribute("login", null);
			} else {
				System.out.println(loginExit);			
				System.out.println("��¼�ɹ���");
				Config.user=loginExit;
				Config.Status=true;

				request.getSession().setAttribute("login", "Y");
				

			
				// ָ��Ҫ���ص�ҳ��Ϊsucc.jsp
				ModelAndView mav = new ModelAndView("succ");
				// ���������ظ�ҳ��
				mav.addObject("username", username);
				mav.addObject("password", password);
				System.out
						.println("username=" + username + " password=" + password);
				return mav;
			}
			
		}
		return new ModelAndView("home");
	}

	/***
	 * ��֤�����Ƿ�Ϊ��
	 * 
	 * @param params
	 * @return
	 */
	private boolean checkParams(String[] params) {
		for (String param : params) {
			if (param == "" || param == null || param.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}