package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.OOige.OOigeView;
import erp.ws.sbo.utils.DbUtils;

public class OOigeDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public OOigeDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
			((OOigeView)view).getJta_SN().setText("");
			((OOigeView)view).getJta_SN().setEditable(true);
			((OOigeView)view).getTxt_date().setEditorable(true);
			((OOigeView)view).getCom_users().setEnabled(true);
			((OOigeView)view).getCom_users().setEditable(false);

			hql="select isnull(max(docnum),0) from Oige";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;
		
			((OOigeView)view).getTxt_docn().setText(Ndoce.toString());	
			((OOigeView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
		}
		else if(this.ds.getValue()==1)
		{
			((OOigeView)view).getTxt_date().setEditorable(true);			
			((OOigeView)view).getCom_users().setEnabled(true);	
			((OOigeView)view).getCom_users().setEditable(false);
		}
		else if(this.ds.getValue()==2)
		{
			((OOigeView)view).getTxt_date().setEditorable(true);			
			((OOigeView)view).getCom_users().setEnabled(true);
			((OOigeView)view).getCom_users().setEditable(false);
		
		}
		else if(this.ds.getValue()==3)
		{
			((OOigeView)view).getTxt_date().setEditorable(false);			
			((OOigeView)view).getCom_users().setEnabled(false);
			((OOigeView)view).getCom_users().setEditable(false);
		}
		else if(this.ds.getValue()==4)
		{
			((OOigeView)view).getTxt_date().setEditorable(false);
			((OOigeView)view).getCom_users().setEnabled(false);	
			((OOigeView)view).getCom_users().setEditable(false);					
			((OOigeView)view).getTxt_docn().setEditable(false);
				
			hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((OOigeView)view).getCom_users().setSelectedItem(new ComboBoxItem(appMain.lt.sqlclob(hql, 0, 1)[0][0],appMain.lt.sqlclob(hql, 0, 1)[0][1].toString()));
		}
		else
		{	
			
		}
	}

	

	

}
