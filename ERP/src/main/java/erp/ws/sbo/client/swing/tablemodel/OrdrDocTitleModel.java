package erp.ws.sbo.client.swing.tablemodel;

import java.util.Date;

import javax.swing.JTextField;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.view.Ordr.OrdrView;
import erp.ws.sbo.utils.DbUtils;

public class OrdrDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public OrdrDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
			hql="select isnull(max(docNum),0) from Ordr";
			Integer Ndoce= (Integer) appMain.lt.clob(hql, 0, 1)[0][0]+1;
		
			((OrdrView)view).getTxt_docn().setText(Ndoce.toString());	
			
			((JTextField)((OrdrView)view).getTxt_cus().getEditor().getEditorComponent()).setText("");
		    ((JTextField)((OrdrView)view).getTxt_cuslxr().getEditor().getEditorComponent()).setText("");
		    ((OrdrView)view).getTxt_date().setDate(new Date());
		    ((OrdrView)view).getTxt_delidate().setDate(new Date());
		    ((OrdrView)view).getTxt_duedate().setDate(new Date());
		    ((OrdrView)view).getJta_memo().setText("");
		    ((OrdrView)view).getTxt_ter().setText("");
		    ((OrdrView)view).getTxt_cusn().setText("");
			((OrdrView)view).getTxt_cus().setEditable(true);
			((OrdrView)view).getTxt_cuslxr().setEditable(true);
			((OrdrView)view).getTxt_date().setEditorable(true);
			((OrdrView)view).getTxt_delidate().setEditorable(true);
			((OrdrView)view).getTxt_duedate().setEditorable(true);
			
			((OrdrView)view).getTxt_cus().setEnabled(true);
			((OrdrView)view).getTxt_cuslxr().setEnabled(true);
			((OrdrView)view).getTxt_date().setEnabled(true);
			((OrdrView)view).getTxt_delidate().setEnabled(true);
			((OrdrView)view).getTxt_duedate().setEnabled(true);
			((OrdrView)view).getCom_sales().setEnabled(false);
			((OrdrView)view).getCom_sales().setEditable(false);
			((OrdrView)view).getCom_sales1().setEnabled(false);	
			((OrdrView)view).getCom_sales1().setEditable(false);
		}
		else if(this.ds.getValue()==1)
		{
			((OrdrView)view).getTxt_cus().setEditable(false);
			((OrdrView)view).getTxt_cuslxr().setEditable(false);
			((OrdrView)view).getTxt_date().setEditorable(true);
			((OrdrView)view).getTxt_delidate().setEditorable(true);
			((OrdrView)view).getTxt_duedate().setEditorable(true);
			((OrdrView)view).getCom_sales().setEnabled(false);
			((OrdrView)view).getCom_sales().setEditable(false);
			((OrdrView)view).getCom_sales1().setEnabled(false);	
			((OrdrView)view).getCom_sales1().setEditable(false);
			((OrdrView)view).getTxt_cus().setEnabled(false);
			((OrdrView)view).getTxt_cuslxr().setEnabled(false);
			((OrdrView)view).getTxt_date().setEnabled(true);
			((OrdrView)view).getTxt_delidate().setEnabled(true);
			((OrdrView)view).getTxt_duedate().setEnabled(true);
		
		}
		else if(this.ds.getValue()==2)
		{
			((OrdrView)view).getTxt_cus().setEditable(true);
			((OrdrView)view).getTxt_cuslxr().setEditable(true);
			((OrdrView)view).getTxt_date().setEditorable(true);
			((OrdrView)view).getTxt_delidate().setEditorable(true);
			((OrdrView)view).getTxt_duedate().setEditorable(true);
			((OrdrView)view).getCom_sales().setEnabled(false);
			((OrdrView)view).getCom_sales().setEditable(false);
			((OrdrView)view).getCom_sales1().setEnabled(false);	
			((OrdrView)view).getCom_sales1().setEditable(false);
			((OrdrView)view).getTxt_cus().setEnabled(true);
			((OrdrView)view).getTxt_cuslxr().setEnabled(true);
			((OrdrView)view).getTxt_date().setEnabled(true);
			((OrdrView)view).getTxt_delidate().setEnabled(true);
			((OrdrView)view).getTxt_duedate().setEnabled(true);
		
		}
		else if(this.ds.getValue()==3)
		{
			((OrdrView)view).getTxt_cus().setEditable(false);
			((OrdrView)view).getTxt_cuslxr().setEditable(false);
			((OrdrView)view).getTxt_date().setEditorable(false);
			((OrdrView)view).getTxt_delidate().setEditorable(false);
			((OrdrView)view).getTxt_duedate().setEditorable(false);
			((OrdrView)view).getCom_sales().setEnabled(false);
			((OrdrView)view).getCom_sales().setEditable(false);
			((OrdrView)view).getCom_sales1().setEnabled(false);
			((OrdrView)view).getCom_sales1().setEditable(false);
		
			((OrdrView)view).getTxt_cus().setEnabled(false);
			((OrdrView)view).getTxt_cuslxr().setEnabled(false);
			((OrdrView)view).getTxt_date().setEnabled(false);
			((OrdrView)view).getTxt_delidate().setEnabled(false);
			((OrdrView)view).getTxt_duedate().setEnabled(false);
		}
		else if(this.ds.getValue()==4)
		{
			((OrdrView)view).getTxt_cus().setEditable(false);
			((OrdrView)view).getTxt_cuslxr().setEditable(false);
			((OrdrView)view).getTxt_date().setEditorable(false);
			((OrdrView)view).getTxt_delidate().setEditorable(false);
			((OrdrView)view).getTxt_duedate().setEditorable(false);
			
			((OrdrView)view).getTxt_cus().setEnabled(false);
			((OrdrView)view).getTxt_cuslxr().setEnabled(false);
			((OrdrView)view).getTxt_date().setEnabled(false);
			((OrdrView)view).getTxt_delidate().setEnabled(false);
			((OrdrView)view).getTxt_duedate().setEnabled(false);
			
			((OrdrView)view).getTxt_delidate().setEditorable(false);
			((OrdrView)view).getTxt_duedate().setEditorable(false);
			
			((OrdrView)view).getCom_sales().setEnabled(false);
			((OrdrView)view).getCom_sales().setEditable(false);
			((OrdrView)view).getCom_sales1().setEnabled(false);
			((OrdrView)view).getCom_sales1().setEditable(false);
		}
		else
		{	
			
		}
	}

	

	

}
