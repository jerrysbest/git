package erp.ws.aop.permission;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyChecker implements MethodInterceptor {  
    
    /** 
     * 用户访问认证方法。 
     * 如果登录合法则开始执行服务，否则返回错误。 
     */  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        Method invokeMethod = invocation.getMethod();  
        Object[] args = invocation.getArguments();  
          
                if(1==1){  
                    return invocation.proceed(); // 检查OK，继续执行  
                }else{  
                    return null;  // 检查NG，阻断执行  
                }  
        }  
}  
