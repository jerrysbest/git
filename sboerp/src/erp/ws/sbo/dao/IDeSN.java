package erp.ws.sbo.dao;

import erp.ws.sbo.client.swing.model.desn;

public interface IDeSN {
	/**
	 * add listsn
	 * @param sns
	 */
	 void add(desn lsn);
	/**
	 * find snstatus
	 * @param id
	 */
	 desn[] queryByDocId(String[] id);
}
