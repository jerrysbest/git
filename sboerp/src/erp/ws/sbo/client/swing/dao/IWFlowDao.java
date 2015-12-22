package erp.ws.sbo.client.swing.dao;

import erp.ws.sbo.client.swing.model.wflist;

public interface IWFlowDao {
	/**
	 * get max docEntry
	 * 
	 */
	 Integer getMaxDocEntry();
	/**
	 * add workflow
	 * @param workflow
	 */
	 void add(wflist workflow); 
	/**
	 * update workflow
	 * @param workflow
	 */
	 void update(wflist workflow);
	/**
	 * find workflow
	 * @param workflow
	 */
     wflist queryByDocId(int id);
}
