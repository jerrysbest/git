package erp.ws.aop.permission;

import  java.lang.reflect.Method;
import  org.springframework.aop.MethodBeforeAdvice;

public class BeforeAction implements MethodBeforeAdvice{
	
	public   void  before(Method method,Object[] args, Object target)
	                 throws  Throwable {
		
	           System.out.println( " 这是BeforeAdvice类的before方法. " );
	           
	       } 
}
