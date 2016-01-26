package erp.ws.sbo.client.swing.dao;

import erp.ws.sbo.client.swing.model.UQrm;

public interface IQrDao {

	void add(UQrm uqrm);

	void update(UQrm uqrm);
	
	void delete(UQrm uqrm);

	UQrm queryByDocId(int id);

	Integer getMaxDocEntry();

}
