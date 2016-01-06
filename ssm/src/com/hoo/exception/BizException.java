package com.hoo.exception;
import org.springframework.dao.DataAccessException;
public class BizException extends DataAccessException {
   private static final long serialVersionUID=1L;
   public BizException(String msg){
	   super(msg);
   }
   public BizException(String msg,Throwable cause)
   {
	   super(msg,cause);
   }
}
