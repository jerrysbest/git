package erp.ws.sbo.dao;

import erp.ws.sbo.client.swing.model.snstatus;

public interface ISNStatus {
	/**
	 * add snstatus
	 * @param sns
	 */
	 void add(snstatus sns);
	/**
	 * update paSN
	 * @param paSN
	 */
	 void update(snstatus sns);
	/**
	 * find snstatus
	 * @param sns
	 */
	 snstatus queryByDocId(String sn);
}
