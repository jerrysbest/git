package erp.ws.aop.permission;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class PermissionThrowsAdvice implements ThrowsAdvice{  
    public void afterThrowing(Method method, Object[] args, Object target,  
            Throwable subclass) {  
        System.out.println("Logging that a " + subclass  
                + "Exception was thrown.");  
    }  
}  
