package erp.ws.aop.permission;

import erp.ws.sbo.client.swing.model.User;

public class PermissionDeniedException extends Exception {  
    
  /**
	 * 
	 */
	private static final long serialVersionUID = 9184352410862459990L;
/** 
   *  
   */  
  public PermissionDeniedException(){  
      super();  
  }  
  /** 
   *  
   * @param user 
   * @param pri 
   */  
  public PermissionDeniedException(User user,String pri){  
      super("Doc:"+user+"don't have the Permission to Operate:"+pri);  
  }  

}
