package erp.ws.sbo.dao;

import erp.ws.sbo.client.swing.model.userauther;
import erp.ws.sbo.client.swing.model.userautherId;

public interface IUserauther {
	/**
	 * add listsn
	 * @param sns
	 */
	 void update(userauther lsn);
	/**
	 * find snstatus
	 * @param id
	 */
	 userauther queryByDocId(userautherId id);
}
