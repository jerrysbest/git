package com.wg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	/***
	 * ��ҳ ������/page/home.jspҳ��
	 * 
	 * @return
	 */
	@RequestMapping("hello")
	public ModelAndView index() {
		// ����ģ�͸���ͼ��������Ⱦҳ�档����ָ��Ҫ���ص�ҳ��Ϊhomeҳ��
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
}