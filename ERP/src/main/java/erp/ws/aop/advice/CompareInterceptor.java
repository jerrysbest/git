package erp.ws.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CompareInterceptor implements MethodInterceptor {
	public Object invoke(MethodInvocation invocation) throws Throwable{
		          Object result = null;
		         String stu_name = invocation.getArguments()[0].toString();
		         if ( stu_name.equals("dragon")){
		             //���ѧ����dragonʱ,ִ��Ŀ�귽��,
		              result= invocation.proceed();
		              
		         } else{
		             System.out.println("��ѧ����"+stu_name+"������dragon,����׼�����.");
		         }
		        
		          return result;
		     }
}
