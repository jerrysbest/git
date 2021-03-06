package erp.ws.aop.permission;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import erp.ws.sbo.client.swing.app.appMain;

public class PermissionCheckAdvice implements MethodBeforeAdvice {
	 public void before(Method m, Object[] args, Object target) throws Throwable {  
         String privilege=(target.getClass().getName()+"." +m.getName()).toUpperCase();  

         System.out.println("打印参数开始=======================");  
         //User user=(User) args[0];  
         System.out.println(appMain.user);  
         System.out.println("打印参数结束=======================");  
         System.out.println("by " + this.getClass().getName()+"::"  
                 + "Target:"+target.getClass().getName()+"." +"Method:"+m.getName() +")"); 
         if(!(privilege.equals("ERP.WS.SBO.CLIENT.SWING.DAO.IMPL.SNINDOC.ADD")||privilege.equals("ERP.WS.SBO.CLIENT.SWING.DAO.IMPL.OIGNDOC.ADD")))
         {
        	 System.out.println("无需AOP权限拦截");  
         }
         else if (!appMain.user.isPermission(privilege)&&(privilege.equals("ERP.WS.SBO.CLIENT.SWING.DAO.IMPL.SNINDOC.ADD")||privilege.equals("ERP.WS.SBO.CLIENT.SWING.DAO.IMPL.OIGNDOC.ADD"))) {  
             throw new PermissionDeniedException(appMain.user, privilege);  
         }  
         else{
        	 System.out.println("有权限");  
         }
       
     }  
}
