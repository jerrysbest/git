package erp.ws.aop.permission;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyChecker implements MethodInterceptor {  
    
    /** 
     * �û�������֤������ 
     * �����¼�Ϸ���ʼִ�з��񣬷��򷵻ش��� 
     */  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        Method invokeMethod = invocation.getMethod();  
        Object[] args = invocation.getArguments();  
          
                if(1==1){  
                    return invocation.proceed(); // ���OK������ִ��  
                }else{  
                    return null;  // ���NG�����ִ��  
                }  
        }  
}  
