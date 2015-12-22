package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.Oige.OigeView;
import erp.ws.sbo.utils.DbUtils;

public class OigeDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public OigeDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
			((OigeView)view).getTxt_date().setEditorable(true);
			((OigeView)view).getTxt_cppo().setEditable(true);
			
			((OigeView)view).getJta_SN().setText("");
			((OigeView)view).getJta_SN().setEditable(true);

			((OigeView)view).getCom_users().setEnabled(true);
			((OigeView)view).getCom_users().setEditable(false);

			hql="select isnull(max(docNum),0) from Oige";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;
		
			((OigeView)view).getTxt_docn().setText(Ndoce.toString());	
			((OigeView)view).getTxt_cppo().setText("");	
			((OigeView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
		}
		else if(this.ds.getValue()==1)
		{
			((OigeView)view).getTxt_date().setEditorable(true);			
			((OigeView)view).getTxt_cppo().setEditable(true);
			((OigeView)view).getCom_users().setEnabled(true);
			((OigeView)view).getCom_users().setEditable(false);
		}
		else if(this.ds.getValue()==2)
		{
			((OigeView)view).getTxt_date().setEditorable(true);			
			((OigeView)view).getTxt_cppo().setEditable(true);
			((OigeView)view).getCom_users().setEnabled(true);
			((OigeView)view).getCom_users().setEditable(false);
		
		}
		else if(this.ds.getValue()==3)
		{
			((OigeView)view).getTxt_date().setEditorable(false);			
			((OigeView)view).getTxt_cppo().setEditable(false);
			((OigeView)view).getCom_users().setEnabled(false);
			((OigeView)view).getCom_users().setEditable(false);
		}
		else if(this.ds.getValue()==4)
		{
			((OigeView)view).getTxt_date().setEditorable(false);			
			((OigeView)view).getTxt_cppo().setEditable(false);	
			((OigeView)view).getCom_users().setEnabled(false);
			((OigeView)view).getCom_users().setEditable(false);
			((OigeView)view).getTxt_docn().setEditable(false);
				
			hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((OigeView)view).getCom_users().setSelectedItem(new ComboBoxItem(appMain.lt.sqlclob(hql, 0, 1)[0][0],appMain.lt.sqlclob(hql, 0, 1)[0][1].toString()));
		}
		else
		{	
			
		}
	}

	

	

}
