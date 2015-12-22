package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.model.ColUAutherDocTitle;
import erp.ws.sbo.client.swing.model.UAutherDocTitle;
import erp.ws.sbo.utils.DbUtils;

public class UAutherDocTitleModel extends AbstractDocTitleModel<ColUAutherDocTitle, UAutherDocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public UAutherDocTitleModel(ColUAutherDocTitle colob, UAutherDocTitle ob, DbUtils<ColUAutherDocTitle, UAutherDocTitle> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}
	
	public UAutherDocTitleModel(ColUAutherDocTitle colob, UAutherDocTitle ob, DbUtils<ColUAutherDocTitle,UAutherDocTitle> dbu,
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
