package erp.ws.sbo.dao;

import erp.ws.sbo.client.swing.model.log;

public interface ILog {
	/**
	 * add Log
	 * @param sns
	 */
	 void add(log log);
	/**
	 * find Log
	 * @param id
	 */
	 log queryByDocId(String id);
	 /**
	  * get userid
	  * @param id
	  */
	 Integer getusersign();

	 
}
