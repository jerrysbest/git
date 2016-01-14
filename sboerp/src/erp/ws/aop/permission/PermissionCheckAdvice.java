package erp.ws.aop.permission;

import java.lang.reflect.Method;
import erp.ws.sbo.client.swing.model.User;

public class PermissionCheckAdvice {
	 public void before(Method m, Object[] args, Object target) throws Throwable {  
         String privilege=target.getClass().getName()+"." +m.getName();  

         System.out.println("打印参数开始=======================");  
         User user=(User) args[0];  
         //System.out.println(doc.getName());  
         System.out.println("打印参数结束=======================");  
         if (!user.isPermission(privilege)) {  
             throw new PermissionDeniedException(user, privilege);  
         }  
         System.out.println("by " + this.getClass().getName()+"::"  
         + "Target:"+target.getClass().getName()+"." +"Method:"+m.getName() +")");  
     }  
}
