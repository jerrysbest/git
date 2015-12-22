package erp.ws.sbo.client.swing.tablemodel;

import java.util.Date;

import javax.swing.JTextField;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.view.Orin.OrinView;
import erp.ws.sbo.utils.DbUtils;

public class OrinDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2901678340676856344L;
    private String hql,No;
    private Object[][] ob;
	public OrinDocTitleModel(ColDocTitle colob,DocTitle ob, DbUtils<ColDocTitle,DocTitle> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}
	public OrinDocTitleModel(ColDocTitle colob,DocTitle ob, DbUtils<ColDocTitle,DocTitle> dbu,
			String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setDocTitleStatus(Object view) {
		// TODO Auto-generated method stub
		if(this.ds.getValue()==0)
		{			
			
			
			Integer Ndoce=0;
			hql="select isnull(max(U_DjNo),0) from Rin1";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob[0][0].toString().equals("0"))
			{
				Ndoce+=1;			
			}
			else{
				hql="select substring(Convert(nvarchar(20),max(U_DjNo)),1,len(max(U_DjNo))-3) from Rin1";
				Ndoce= Integer.valueOf(appMain.lt.sqlclob(hql, 0, 1)[0][0].toString())+1;		
			}
		    if(appMain.oCompany.getUserSignature().toString().length()==1)
		    {
		    	No=Ndoce.toString()+"00"+appMain.oCompany.getUserSignature().toString();
		    }
		    else if(appMain.oCompany.getUserSignature().toString().length()==2)
		    {
		    	No=Ndoce.toString()+"0"+appMain.oCompany.getUserSignature().toString();
		    }
		    else{
		    	No=Ndoce.toString()+appMain.oCompany.getUserSignature().toString();
		    }
		
			((OrinView)view).getTxt_docn().setText(No);	
			((OrinView)view).getTxt_docnplus().setText("1");
		    ((JTextField)((OrinView)view).getTxt_cuslxr().getEditor().getEditorComponent()).setText("");
		    ((OrinView)view).getTxt_date().setDate(new Date());
		    
		    ((OrinView)view).getJta_memo().setText("");
		    ((OrinView)view).getTxt_ter().setText("");
		    ((OrinView)view).getTxt_cusn().setText("");
		    ((OrinView)view).getTxt_status().setText("");
		    ((OrinView)view).getJta_SN().setText("");
			((OrinView)view).getTxt_date().setEditorable(true);			
			((OrinView)view).getTxt_date().setEnabled(true);
			((OrinView)view).getTxt_delidate().setEditorable(false);
			((OrinView)view).getTxt_delidate().setEnabled(false);
			((OrinView)view).getTxt_duedate().setEditorable(false);
			((OrinView)view).getTxt_duedate().setEnabled(false);
			((OrinView)view).getCom_type().setEditable(false);
			((OrinView)view).getCom_type().setEnabled(true);
			((OrinView)view).getCom_sales().setEnabled(true);
			((OrinView)view).getCom_sales().setEditable(false);
			((OrinView)view).getCom_sales1().setEnabled(true);
			((OrinView)view).getCom_sales1().setEditable(false);
			((OrinView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
		}
		else if(this.ds.getValue()==1)
		{
			((OrinView)view).getTxt_date().setEditorable(true);			
			((OrinView)view).getTxt_date().setEnabled(true);								
			((OrinView)view).getCom_sales().setEnabled(false);	
			((OrinView)view).getCom_sales().setEditable(false);
			((OrinView)view).getCom_sales1().setEnabled(false);	
			((OrinView)view).getCom_sales1().setEditable(false);
			((OrinView)view).getTxt_delidate().setEditorable(false);
			((OrinView)view).getTxt_delidate().setEnabled(false);
			((OrinView)view).getTxt_duedate().setEditorable(false);
			((OrinView)view).getTxt_duedate().setEnabled(false);
			((OrinView)view).getCom_type().setEditable(true);
			((OrinView)view).getCom_type().setEnabled(true);
		}
		else if(this.ds.getValue()==2)
		{
			((OrinView)view).getTxt_date().setEditorable(true);
			((OrinView)view).getTxt_date().setEnabled(true);
			((OrinView)view).getCom_sales().setEnabled(false);	
			((OrinView)view).getCom_sales().setEditable(false);
			((OrinView)view).getCom_sales1().setEnabled(false);	
			((OrinView)view).getCom_sales1().setEditable(false);
			((OrinView)view).getTxt_delidate().setEditorable(false);
			((OrinView)view).getTxt_delidate().setEnabled(false);
			((OrinView)view).getTxt_duedate().setEditorable(false);
			((OrinView)view).getTxt_duedate().setEnabled(false);
			((OrinView)view).getCom_type().setEditable(false);
			((OrinView)view).getCom_type().setEnabled(true);
		}
		else if(this.ds.getValue()==3)
		{
			((OrinView)view).getTxt_date().setEditorable(false);			
			((OrinView)view).getTxt_date().setEnabled(false);
			((OrinView)view).getCom_sales().setEnabled(false);
			((OrinView)view).getCom_sales().setEditable(false);
			((OrinView)view).getCom_sales1().setEnabled(false);	
			((OrinView)view).getCom_sales1().setEditable(false);
			((OrinView)view).getTxt_delidate().setEditorable(false);
			((OrinView)view).getTxt_delidate().setEnabled(false);
			((OrinView)view).getTxt_duedate().setEditorable(false);
			((OrinView)view).getTxt_duedate().setEnabled(false);
			((OrinView)view).getCom_type().setEditable(false);
			((OrinView)view).getCom_type().setEnabled(false);
		}
		else if(this.ds.getValue()==4)
		{
			((OrinView)view).getTxt_date().setEditorable(false);
			((OrinView)view).getTxt_date().setEnabled(false);					
			((OrinView)view).getCom_sales().setEnabled(false);
			((OrinView)view).getCom_sales().setEditable(false);
			((OrinView)view).getCom_sales1().setEnabled(false);
			((OrinView)view).getCom_sales1().setEditable(false);
			((OrinView)view).getTxt_delidate().setEditorable(false);
			((OrinView)view).getTxt_delidate().setEnabled(false);
			((OrinView)view).getTxt_duedate().setEditorable(false);
			((OrinView)view).getTxt_duedate().setEnabled(false);
			((OrinView)view).getCom_type().setEditable(false);
			((OrinView)view).getCom_type().setEnabled(false);
		}
		else
		{	
			
		}
	}

}
