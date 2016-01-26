package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.OOign.OOignView;
import erp.ws.sbo.utils.DbUtils;

public class OOignDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public OOignDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}
    private String hql;   
    
	

	 //this class is to set  add,query,addplus,remend status to doctitle
	@Override
	public void setDocTitleStatus(Object view) {
		// TODO Auto-generated method stub
		if(this.ds.getValue()==0)
		{	
			((OOignView)view).getJta_SN().setText("");
			((OOignView)view).getJta_SN().setEditable(true);
			((OOignView)view).getCom_users().setEnabled(true);
			((OOignView)view).getCom_users().setEditable(false);
			((OOignView)view).getTxt_date().setEditorable(true);
		
			hql="select isnull(max(docnum),0) from Oign";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;
		
			((OOignView)view).getTxt_docn().setText(Ndoce.toString());	
			((OOignView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
			
		}
		else if(this.ds.getValue()==1)
		{			
			((OOignView)view).getJta_SN().setEditable(true);
			((OOignView)view).getCom_users().setEnabled(true);	
			((OOignView)view).getCom_users().setEditable(false);
			((OOignView)view).getTxt_date().setEditorable(true);
		}
		else if(this.ds.getValue()==2)
		{			
			((OOignView)view).getJta_SN().setEditable(true);
			((OOignView)view).getCom_users().setEnabled(true);
			((OOignView)view).getCom_users().setEditable(false);
			((OOignView)view).getTxt_date().setEditorable(true);
		}
		else if(this.ds.getValue()==3)
		{			
			((OOignView)view).getJta_SN().setEditable(false);
			((OOignView)view).getCom_users().setEnabled(false);
			((OOignView)view).getCom_users().setEditable(false);
			((OOignView)view).getTxt_date().setEditorable(false);
		}
		else if(this.ds.getValue()==4)
		{			
			((OOignView)view).getJta_SN().setEditable(false);
			((OOignView)view).getCom_users().setEnabled(false);
			((OOignView)view).getCom_users().setEditable(false);
			((OOignView)view).getTxt_date().setEditorable(false);
			((OOignView)view).getTxt_docn().setEditable(false);
			
			hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((OOignView)view).getCom_users().setSelectedItem(new ComboBoxItem(appMain.lt.sqlclob(hql, 0, 1)[0][0],appMain.lt.sqlclob(hql, 0, 1)[0][1].toString()));
		}
		else
		{	
			
		}
	}

	

	

}
