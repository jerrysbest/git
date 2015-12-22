package erp.ws.sbo.client.swing.util.general;

import java.lang.reflect.Constructor;


public class NewInstance {
	public NewInstance()
	{
		
	}
	public Object newInstance(String className, Object[] args) throws Exception {
		      Class<?> newoneClass = Class.forName(className);
		  if(args!=null)
		  {
		      @SuppressWarnings("rawtypes")
			Class[] argsClass = new Class[args.length];
		  
		      for (int i = 0, j = args.length; i < j; i++) {
		          argsClass[i] = args[i].getClass();
		      }
		 
		     Constructor<?> cons = newoneClass.getConstructor(argsClass);
		 
		     return cons.newInstance(args);
		  }
		  else
		  {
			  return newoneClass.newInstance();
		  }
		 
		 }
}
