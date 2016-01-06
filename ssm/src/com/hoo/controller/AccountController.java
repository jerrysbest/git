package com.hoo.controller;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.hoo.biz.AccountBiz;
import com.hoo.entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
@Controller("accountController")
@RequestMapping("/account")
public class AccountController {
   @Autowired
   @Qualifier("accountBizImpl")
   private AccountBiz<Account> biz;
   
   @RequestMapping("/add")
   public String add(@RequestParam String username, @RequestParam String password, @RequestParam String status)
   {   
	   Integer stat=Integer.valueOf(status);
	   Account acc=new Account(username,password,stat);
	   
	   System.out.println(acc);
	   biz.addAccount(acc);
	   return "redirect:/account/list.do";
   }
   @RequestMapping("/get")
   public String get(Integer id,Model model)
   {
	   System.out.println("###ID:"+id);
	   model.addAttribute(biz.getAccount(id));
	   return "/show.jsp";
   }
   @RequestMapping("/list")
   public String list(Model model)
   {
	   model.addAttribute("list",biz.getList());
	   return "/list.jsp";
   }
   @ExceptionHandler(Exception.class)
   public String exception(Exception e,HttpServletRequest request)
   {
	   request.setAttribute("exception", e);
	   return "/error.jsp";
   }
}
