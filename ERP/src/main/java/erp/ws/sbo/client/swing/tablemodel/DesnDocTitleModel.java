package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.model.ColSNDocTitle;
import erp.ws.sbo.client.swing.model.SNDocTitle;
import erp.ws.sbo.utils.DbUtils;

public class DesnDocTitleModel extends AbstractDocTitleModel<ColSNDocTitle, SNDocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public DesnDocTitleModel(ColSNDocTitle colob, SNDocTitle ob, DbUtils<ColSNDocTitle, SNDocTitle> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}
	
	public DesnDocTitleModel(ColSNDocTitle colob, SNDocTitle ob, DbUtils<ColSNDocTitle, SNDocTitle> dbu,
			String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
	}	

	 //this class is to set  add,query,addplus,remend status to doctitle
	@Override
	public void setDocTitleStatus(Object view) {
		// TODO Auto-generated method stub
		
	}

	

	

}
