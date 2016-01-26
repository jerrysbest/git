package erp.ws.sbo.dao;

import erp.ws.sbo.client.swing.model.listsn;

public interface IListSN {
	/**
	 * getMaxDocEntry
	 * 
	 */
	 Integer getMaxDocEntry();
	/**
	 * add listsn
	 * @param sns
	 */
	 void add(listsn lsn);
	/**
	 * find snstatus
	 * @param id
	 */
	 listsn queryByDocId(int id);
}
