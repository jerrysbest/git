package erp.ws.aop.permission;

import java.lang.reflect.Method;
import erp.ws.sbo.client.swing.model.User;

public class PermissionCheckAdvice {
	 public void before(Method m, Object[] args, Object target) throws Throwable {  
         String privilege=target.getClass().getName()+"." +m.getName();  

         System.out.println("��ӡ������ʼ=======================");  
         User user=(User) args[0];  
         //System.out.println(doc.getName());  
         System.out.println("��ӡ��������=======================");  
         if (!user.isPermission(privilege)) {  
             throw new PermissionDeniedException(user, privilege);  
         }  
         System.out.println("by " + this.getClass().getName()+"::"  
         + "Target:"+target.getClass().getName()+"." +"Method:"+m.getName() +")");  
     }  
}
