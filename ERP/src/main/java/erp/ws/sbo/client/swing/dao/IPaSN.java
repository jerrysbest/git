package erp.ws.sbo.client.swing.dao;

import erp.ws.sbo.client.swing.model.pasn;

public interface IPaSN {
	/**
	 * get max docEntry
	 * 
	 */
	 Integer getMaxDocEntry();
	/**
	 * add paSN
	 * @param paSN
	 */
	 void add(pasn paSN);
	/**
	 * update paSN
	 * @param paSN
	 */
	 void update(pasn paSN);
	/**
	 * find paSN
	 * @param paSN
	 */
	 pasn queryByDocId(int id);
}
