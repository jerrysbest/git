package com.wg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {  
	  
    private static final String LOGIN_URL = "index.jsp";  
  
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(true);  
        // ��session �����ȡ�û�������Ϣ  
        Object obj = session.getAttribute("login");  
        // �ж����û��ȡ���û���Ϣ������ת����½ҳ�棬��ʾ�û����е�½  
        if (obj == null || "".equals(obj.toString())) {  
            res.sendRedirect(LOGIN_URL);  
        }  
        return true;  
	}  
  
}  
