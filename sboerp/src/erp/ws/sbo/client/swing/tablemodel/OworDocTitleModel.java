package erp.ws.sbo.client.swing.tablemodel;

import java.util.Date;

import javax.swing.JTextField;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.Owor.OworView;
import erp.ws.sbo.utils.DbUtils;

public class OworDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public OworDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
						
			((OworView)view).getTxt_Qty().setEditable(true);
			((OworView)view).getTxt_planQty().setEditable(true);
			((OworView)view).getTxt_length().setEditable(true);
			((OworView)view).getTxt_date().setEditorable(true);
			((OworView)view).getTxt_duedate().setEditorable(true);
			
			((OworView)view).getCom_status().setEnabled(true);
			((OworView)view).getCom_form().setEnabled(true);
			((OworView)view).getCom_type().setEnabled(true);
			((OworView)view).getCom_whs().setEnabled(true);
			((OworView)view).getCom_itemcode().setEnabled(true);
			((OworView)view).getTxt_date().setEnabled(true);
			((OworView)view).getTxt_duedate().setEnabled(true);
			((OworView)view).getCom_users().setEnabled(true);
			((OworView)view).getCom_itemcode().setEditable(true);
			((OworView)view).getTxt_itemname().setEditable(false);
			((OworView)view).getCom_status().setEditable(false);
			((OworView)view).getCom_type().setEditable(false);
			((OworView)view).getCom_whs().setEditable(false);
			((OworView)view).getCom_form().setEditable(false);
			
			hql="select isnull(max(docNum),0) from Owor";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;
		
			((OworView)view).getTxt_docn().setText(Ndoce.toString());	
			
			((JTextField)((OworView)view).getCom_itemcode().getEditor().getEditorComponent()).setText("");		  
		    ((JTextField)((OworView)view).getCom_whs().getEditor().getEditorComponent()).setText("");
		    ((OworView)view).getTxt_date().setDate(new Date());
		    ((OworView)view).getTxt_duedate().setDate(new Date());
		    ((OworView)view).getTxt_Qty().setText("0");
		    ((OworView)view).getTxt_planQty().setText("0");
		    ((OworView)view).getTxt_length().setText("0");
		    ((OworView)view).getTxt_unit().setText("");
		    ((OworView)view).getTxt_ounit().setText("");
		    ((OworView)view).getJta_memo().setText("");
		    ((OworView)view).getTxt_asum().setText("");
		    ((OworView)view).getTxt_itemname().setText("");
			
		}
		else if(this.ds.getValue()==1)
		{	
			((OworView)view).getTxt_Qty().setEditable(true);
			((OworView)view).getTxt_planQty().setEditable(true);
			((OworView)view).getTxt_length().setEditable(true);
			((OworView)view).getTxt_date().setEditorable(true);
			((OworView)view).getTxt_duedate().setEditorable(true);
				
			((OworView)view).getCom_form().setEnabled(true);
			((OworView)view).getCom_form().setEditable(false);
			((OworView)view).getCom_users().setEnabled(true);
			((OworView)view).getCom_users().setEditable(false);
			((OworView)view).getCom_status().setEnabled(true);
			((OworView)view).getCom_type().setEnabled(true);
			((OworView)view).getCom_whs().setEnabled(true);
			((OworView)view).getCom_itemcode().setEnabled(true);
			((OworView)view).getTxt_itemname().setEnabled(false);
			((OworView)view).getCom_itemcode().setEditable(true);
			((OworView)view).getTxt_itemname().setEditable(false);
			((OworView)view).getCom_status().setEditable(false);
			((OworView)view).getCom_type().setEditable(false);
			((OworView)view).getCom_whs().setEditable(false);
			((OworView)view).getCom_users().setEditable(false);
			((OworView)view).getTxt_date().setEnabled(true);
			((OworView)view).getTxt_duedate().setEnabled(true);
			
		
		}
		else if(this.ds.getValue()==2)
		{
		
			((OworView)view).getTxt_Qty().setEditable(true);
			((OworView)view).getTxt_planQty().setEditable(true);
			((OworView)view).getTxt_length().setEditable(true);
			((OworView)view).getTxt_date().setEditorable(true);
			((OworView)view).getTxt_duedate().setEditorable(true);
			((OworView)view).getCom_users().setEnabled(true);
            ((OworView)view).getCom_users().setEditable(false);
						
        	((OworView)view).getCom_form().setEnabled(true);
			((OworView)view).getCom_form().setEditable(false);
            ((OworView)view).getCom_status().setEnabled(true);
			((OworView)view).getCom_type().setEnabled(true);
			((OworView)view).getCom_whs().setEnabled(true);
			((OworView)view).getCom_itemcode().setEnabled(true);
			((OworView)view).getCom_itemcode().setEditable(true);
			((OworView)view).getTxt_itemname().setEditable(false);
			((OworView)view).getCom_status().setEditable(false);
			((OworView)view).getCom_type().setEditable(false);
			((OworView)view).getCom_whs().setEditable(false);
			((OworView)view).getTxt_date().setEnabled(true);
			((OworView)view).getTxt_duedate().setEnabled(true);
		
		}
		else if(this.ds.getValue()==3)
		{

			((OworView)view).getCom_status().setEditable(false);
			((OworView)view).getCom_type().setEditable(false);
			((OworView)view).getCom_itemcode().setEditable(false);
			((OworView)view).getTxt_itemname().setEditable(false);
			((OworView)view).getCom_whs().setEditable(false);
			((OworView)view).getTxt_Qty().setEditable(false);
			((OworView)view).getTxt_planQty().setEditable(false);
			((OworView)view).getTxt_length().setEditable(false);
			((OworView)view).getTxt_date().setEditorable(false);
			((OworView)view).getTxt_duedate().setEditorable(false);
            ((OworView)view).getCom_users().setEditable(false);			
			((OworView)view).getCom_users().setEnabled(false);
			((OworView)view).getCom_form().setEnabled(false);
			((OworView)view).getCom_form().setEditable(false);
			((OworView)view).getCom_status().setEnabled(false);
			((OworView)view).getCom_type().setEnabled(false);
			((OworView)view).getCom_whs().setEnabled(false);
			((OworView)view).getCom_itemcode().setEnabled(false);
			((OworView)view).getTxt_date().setEnabled(false);
			((OworView)view).getTxt_duedate().setEnabled(false);
			((OworView)view).getTxt_itemname().setEnabled(false);
		}
		else if(this.ds.getValue()==4)
		{
			((JTextField)((OworView)view).getCom_itemcode().getEditor().getEditorComponent()).setText("");		  
		    ((JTextField)((OworView)view).getCom_whs().getEditor().getEditorComponent()).setText("");
		    ((OworView)view).getTxt_date().setDate(new Date());
		    ((OworView)view).getTxt_duedate().setDate(new Date());
		    ((OworView)view).getTxt_Qty().setText("0");
		    ((OworView)view).getTxt_planQty().setText("0");
		    ((OworView)view).getTxt_length().setText("0");
		    ((OworView)view).getTxt_unit().setText("");
		    ((OworView)view).getTxt_ounit().setText("");
		    ((OworView)view).getJta_memo().setText("");
		    ((OworView)view).getTxt_asum().setText("");
		    ((OworView)view).getTxt_itemname().setText("");
			((OworView)view).getCom_status().setEditable(false);
			((OworView)view).getCom_type().setEditable(false);
			((OworView)view).getCom_itemcode().setEditable(false);
			((OworView)view).getTxt_itemname().setEditable(false);
			((OworView)view).getCom_whs().setEditable(false);
			((OworView)view).getTxt_Qty().setEditable(false);
			((OworView)view).getTxt_planQty().setEditable(false);
			((OworView)view).getTxt_length().setEditable(false);
			((OworView)view).getTxt_date().setEditorable(false);
			((OworView)view).getTxt_duedate().setEditorable(false);
            ((OworView)view).getCom_users().setEditable(false);			
			((OworView)view).getCom_users().setEnabled(false);
			((OworView)view).getCom_form().setEnabled(false);
			((OworView)view).getCom_form().setEditable(false);
			((OworView)view).getCom_status().setEnabled(false);
			((OworView)view).getCom_type().setEnabled(false);
			((OworView)view).getCom_whs().setEnabled(false);
			((OworView)view).getCom_itemcode().setEnabled(false);
			((OworView)view).getTxt_date().setEnabled(false);
			((OworView)view).getTxt_duedate().setEnabled(false);
			
			hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((OworView)view).getCom_users().setSelectedItem(new ComboBoxItem(appMain.lt.sqlclob(hql, 0, 1)[0][0],appMain.lt.sqlclob(hql, 0, 1)[0][1].toString()));

		}
		else
		{	
			
		}
	}

	

	

}
