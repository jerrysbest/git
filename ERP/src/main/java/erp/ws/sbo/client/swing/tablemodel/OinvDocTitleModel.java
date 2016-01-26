package erp.ws.sbo.client.swing.tablemodel;

import java.util.Date;

import javax.swing.JTextField;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;
import erp.ws.sbo.utils.DbUtils;

public class OinvDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2901678340676856344L;
    private String hql,No;
    private Object[][] ob;
	public OinvDocTitleModel(ColDocTitle colob,DocTitle ob, DbUtils<ColDocTitle,DocTitle> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDocTitleStatus(Object view) {
		// TODO Auto-generated method stub
		if(this.ds.getValue()==0)
		{	
			Integer Ndoce=0;
			hql="select isnull(max(U_DjNo),0) from Drf1";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob[0][0].toString().equals("0"))
			{
				Ndoce+=1;			
			}
			else{
				hql="select substring(Convert(nvarchar(20),max(U_DjNo)),1,len(max(U_DjNo))-3) from drf1";
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
		
			//((OinvView)view).getTxt_docn().setText(Ndoce.toString());	
		    ((OinvView)view).getJta_SN().setText("");
		    ((OinvView)view).getTxt_docn().setText(No);	
			((OinvView)view).getTxt_docnplus().setText("1");
			((JTextField)((OinvView)view).getTxt_cus().getEditor().getEditorComponent()).setText("");
		    ((JTextField)((OinvView)view).getTxt_cuslxr().getEditor().getEditorComponent()).setText("");
		    ((OinvView)view).getTxt_date().setDate(new Date());
		    
		    ((OinvView)view).getJta_memo().setText("");
		    ((OinvView)view).getTxt_ter().setText("");
		    ((OinvView)view).getTxt_cusn().setText("");
		    ((OinvView)view).getTxt_status().setText("");
		    
			((OinvView)view).getTxt_date().setEditorable(true);			
			((OinvView)view).getTxt_date().setEnabled(true);
			((OinvView)view).getTxt_delidate().setEditorable(false);
			((OinvView)view).getTxt_delidate().setEnabled(false);
			((OinvView)view).getTxt_duedate().setEditorable(false);
			((OinvView)view).getTxt_duedate().setEnabled(false);
			((OinvView)view).getTxt_cus().setEditable(false);
			((OinvView)view).getTxt_cus().setEnabled(false);
			((OinvView)view).getCom_sales().setEnabled(true);
			((OinvView)view).getCom_sales().setEditable(false);
			((OinvView)view).getCom_sales1().setEnabled(true);
			((OinvView)view).getCom_sales1().setEditable(false);
			((OinvView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
		}
		else if(this.ds.getValue()==1)
		{
			((OinvView)view).getTxt_date().setEditorable(true);			
			((OinvView)view).getTxt_date().setEnabled(true);								
			((OinvView)view).getCom_sales().setEnabled(false);	
			((OinvView)view).getCom_sales().setEditable(false);
			((OinvView)view).getCom_sales1().setEnabled(false);	
			((OinvView)view).getCom_sales1().setEditable(false);
			((OinvView)view).getTxt_delidate().setEditorable(false);
			((OinvView)view).getTxt_delidate().setEnabled(false);
			((OinvView)view).getTxt_duedate().setEditorable(false);
			((OinvView)view).getTxt_duedate().setEnabled(false);
			((OinvView)view).getTxt_cus().setEditable(false);
			((OinvView)view).getTxt_cus().setEnabled(false);
		}
		else if(this.ds.getValue()==2)
		{
			((OinvView)view).getTxt_date().setEditorable(true);
			((OinvView)view).getTxt_date().setEnabled(true);
			((OinvView)view).getCom_sales().setEnabled(false);	
			((OinvView)view).getCom_sales().setEditable(false);
			((OinvView)view).getCom_sales1().setEnabled(false);	
			((OinvView)view).getCom_sales1().setEditable(false);
			((OinvView)view).getTxt_delidate().setEditorable(false);
			((OinvView)view).getTxt_delidate().setEnabled(false);
			((OinvView)view).getTxt_duedate().setEditorable(false);
			((OinvView)view).getTxt_duedate().setEnabled(false);
			((OinvView)view).getTxt_cus().setEditable(false);
			((OinvView)view).getTxt_cus().setEnabled(false);
		}
		else if(this.ds.getValue()==3)
		{
			((OinvView)view).getTxt_date().setEditorable(false);			
			((OinvView)view).getTxt_date().setEnabled(false);
			((OinvView)view).getCom_sales().setEnabled(false);
			((OinvView)view).getCom_sales().setEditable(false);
			((OinvView)view).getCom_sales1().setEnabled(false);
			((OinvView)view).getCom_sales1().setEditable(false);
			((OinvView)view).getTxt_delidate().setEditorable(false);
			((OinvView)view).getTxt_delidate().setEnabled(false);
			((OinvView)view).getTxt_duedate().setEditorable(false);
			((OinvView)view).getTxt_duedate().setEnabled(false);
			((OinvView)view).getTxt_cus().setEditable(false);
			((OinvView)view).getTxt_cus().setEnabled(false);
		}
		else if(this.ds.getValue()==4)
		{
			((OinvView)view).getTxt_date().setEditorable(false);
			((OinvView)view).getTxt_date().setEnabled(false);					
			((OinvView)view).getCom_sales().setEnabled(false);
			((OinvView)view).getCom_sales().setEditable(false);
			((OinvView)view).getCom_sales1().setEnabled(false);
			((OinvView)view).getCom_sales1().setEditable(false);
			((OinvView)view).getTxt_delidate().setEditorable(false);
			((OinvView)view).getTxt_delidate().setEnabled(false);
			((OinvView)view).getTxt_duedate().setEditorable(false);
			((OinvView)view).getTxt_duedate().setEnabled(false);
			((OinvView)view).getTxt_cus().setEditable(false);
			((OinvView)view).getTxt_cus().setEnabled(false);
		}
		else
		{	
			
		}
	}

}
