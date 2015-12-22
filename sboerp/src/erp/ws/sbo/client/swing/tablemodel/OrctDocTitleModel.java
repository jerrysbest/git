package erp.ws.sbo.client.swing.tablemodel;

import java.util.Date;

import javax.swing.JTextField;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.view.Orct.OrctView;
import erp.ws.sbo.utils.DbUtils;

public class OrctDocTitleModel extends
		AbstractDocTitleModel<ColDocTitle, DocTitle> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5353597668341289552L;
	private String hql,No;
    private Object[][] ob;
	public OrctDocTitleModel(ColDocTitle colob, DocTitle ob,
			DbUtils<ColDocTitle, DocTitle> dbu, String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}

	public OrctDocTitleModel(ColDocTitle colob, DocTitle ob,
			DbUtils<ColDocTitle, DocTitle> dbu, String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setDocTitleStatus(Object view) {
		// TODO Auto-generated method stub
		if(this.ds.getValue()==0)
		{			
			Integer Ndoce=0;		
			hql="select isnull(max(a.U_DjNo),0) from (select U_DjNo from Rct2 union select U_DjNo from Vpm2) a";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob[0][0].toString().equals("0"))
			{
				Ndoce+=1;			
			}
			else{
				hql="select substring(Convert(nvarchar(20),max(a.U_DjNo)),1,len(max(a.U_DjNo))-3) from (select U_DjNo from Rct2 union select U_DjNo from Vpm2) a";
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
					
			//((OrctView)view).getTxt_docn().setText(String.valueOf(Integer.valueOf(ob[0][0].toString())+1));	
			((OrctView)view).getTxt_docn().setText(No);	
			
			((OrctView)view).getTxt_docnplus().setText("1");
			((JTextField)((OrctView)view).getTxt_cus().getEditor().getEditorComponent()).setText("");
		    ((JTextField)((OrctView)view).getTxt_cuslxr().getEditor().getEditorComponent()).setText("");
		    ((OrctView)view).getTxt_date().setDate(new Date());
		    
		    ((OrctView)view).getJta_memo().setText("");
		    ((OrctView)view).getTxt_ter().setText("");
		    ((OrctView)view).getTxt_cusn().setText("");
		    ((OrctView)view).getTxt_status().setText("");
		    
			((OrctView)view).getTxt_date().setEditorable(true);			
			((OrctView)view).getTxt_date().setEnabled(true);
			((OrctView)view).getTxt_delidate().setEditorable(false);
			((OrctView)view).getTxt_delidate().setEnabled(false);
			((OrctView)view).getTxt_duedate().setEditorable(false);
			((OrctView)view).getTxt_duedate().setEnabled(false);
			((OrctView)view).getTxt_cus().setEditable(false);
			((OrctView)view).getTxt_cus().setEnabled(false);
			((OrctView)view).getCom_sales().setEnabled(true);
			((OrctView)view).getCom_sales().setEditable(false);
			((OrctView)view).getCom_sales1().setEnabled(true);
			((OrctView)view).getCom_sales1().setEditable(false);
		}
		else if(this.ds.getValue()==1)
		{
			((OrctView)view).getTxt_date().setEditorable(true);			
			((OrctView)view).getTxt_date().setEnabled(true);								
			((OrctView)view).getCom_sales().setEnabled(false);	
			((OrctView)view).getCom_sales().setEditable(false);
			((OrctView)view).getCom_sales1().setEnabled(false);	
			((OrctView)view).getCom_sales1().setEditable(false);
			((OrctView)view).getTxt_delidate().setEditorable(false);
			((OrctView)view).getTxt_delidate().setEnabled(false);
			((OrctView)view).getTxt_duedate().setEditorable(false);
			((OrctView)view).getTxt_duedate().setEnabled(false);
			((OrctView)view).getTxt_cus().setEditable(false);
			((OrctView)view).getTxt_cus().setEnabled(false);
		}
		else if(this.ds.getValue()==2)
		{
			((OrctView)view).getTxt_date().setEditorable(true);
			((OrctView)view).getTxt_date().setEnabled(true);
			((OrctView)view).getCom_sales().setEnabled(false);	
			((OrctView)view).getCom_sales().setEditable(false);
			((OrctView)view).getCom_sales1().setEnabled(false);	
			((OrctView)view).getCom_sales1().setEditable(false);
			((OrctView)view).getTxt_delidate().setEditorable(false);
			((OrctView)view).getTxt_delidate().setEnabled(false);
			((OrctView)view).getTxt_duedate().setEditorable(false);
			((OrctView)view).getTxt_duedate().setEnabled(false);
			((OrctView)view).getTxt_cus().setEditable(false);
			((OrctView)view).getTxt_cus().setEnabled(false);
		}
		else if(this.ds.getValue()==3)
		{
			((OrctView)view).getTxt_date().setEditorable(false);			
			((OrctView)view).getTxt_date().setEnabled(false);
			((OrctView)view).getCom_sales().setEnabled(false);
			((OrctView)view).getCom_sales().setEditable(false);
			((OrctView)view).getCom_sales1().setEnabled(false);	
			((OrctView)view).getCom_sales1().setEditable(false);
			((OrctView)view).getTxt_delidate().setEditorable(false);
			((OrctView)view).getTxt_delidate().setEnabled(false);
			((OrctView)view).getTxt_duedate().setEditorable(false);
			((OrctView)view).getTxt_duedate().setEnabled(false);
			((OrctView)view).getTxt_cus().setEditable(false);
			((OrctView)view).getTxt_cus().setEnabled(false);
		}
		else if(this.ds.getValue()==4)
		{
			((OrctView)view).getTxt_date().setEditorable(false);
			((OrctView)view).getTxt_date().setEnabled(false);					
			((OrctView)view).getCom_sales().setEnabled(false);
			((OrctView)view).getCom_sales().setEditable(false);
			((OrctView)view).getCom_sales1().setEnabled(false);
			((OrctView)view).getCom_sales1().setEditable(false);
			((OrctView)view).getTxt_delidate().setEditorable(false);
			((OrctView)view).getTxt_delidate().setEnabled(false);
			((OrctView)view).getTxt_duedate().setEditorable(false);
			((OrctView)view).getTxt_duedate().setEnabled(false);
			((OrctView)view).getTxt_cus().setEditable(false);
			((OrctView)view).getTxt_cus().setEnabled(false);
		}
		else
		{	
			
		}
	}

}
