package erp.ws.sbo.client.swing.tablemodel;

import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.view.Qr.QrView;
import erp.ws.sbo.utils.DbUtils;

public class QrDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;

	public QrDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
			hql="select isnull(max(docNum),0) from U_Qrm";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;
			 hql="select u_ifr from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
			 if(appMain.lt.sqlclob(hql, 0, 1)==null||appMain.lt.sqlclob(hql, 0, 1).length==0)
			 {
				 JOptionPane.showMessageDialog(null,"请定义用户是否退货");
		      	  return;
			 }
			 ((QrView)view).getTxt_rdocn().setText("");
		     if(appMain.lt.sqlclob(hql, 0, 1)[0][0].toString().equals("Y")){
		 
		    	 ((QrView)view).getTxt_rdocn().setEditable(true);
		    	 ((QrView)view).getTxt_rdocn().setText("");
		    	 
		     }
		     else{
		      	 ((QrView)view).getTxt_rdocn().setEditable(true);
		    	 ((QrView)view).getTxt_rdocn().setText("");
		    	 ((QrView)view).getTxt_rdocn().setEditable(false);
		     }
			((QrView)view).getTxt_docn().setText(Ndoce.toString());	
			
			((JTextField)((QrView)view).getTxt_cus().getEditor().getEditorComponent()).setText("");
		    ((JTextField)((QrView)view).getTxt_cuslxr().getEditor().getEditorComponent()).setText("");
		    ((QrView)view).getTxt_date().setDate(new Date());
		    ((QrView)view).getJta_memo().setText("");
		    ((QrView)view).getTxt_ter().setText("");
		    ((QrView)view).getTxt_cusn().setText("");
			((QrView)view).getTxt_cus().setEditable(true);
			((QrView)view).getTxt_cuslxr().setEditable(true);
			((QrView)view).getTxt_date().setEditorable(true);
			
			((QrView)view).getTxt_cus().setEnabled(true);
			((QrView)view).getTxt_cuslxr().setEnabled(true);
			((QrView)view).getTxt_date().setEnabled(true);
			((QrView)view).getCom_sales().setEnabled(false);
			((QrView)view).getCom_sales().setEditable(false);
			((QrView)view).getCom_sales1().setEnabled(false);	
			((QrView)view).getCom_sales1().setEditable(false);
		}
		else if(this.ds.getValue()==1)
		{
			((QrView)view).getTxt_cus().setEditable(false);
			((QrView)view).getTxt_cuslxr().setEditable(false);
			((QrView)view).getTxt_date().setEditorable(true);
			((QrView)view).getCom_sales().setEnabled(false);
			((QrView)view).getCom_sales().setEditable(false);
			((QrView)view).getCom_sales1().setEnabled(false);	
			((QrView)view).getCom_sales1().setEditable(false);
			((QrView)view).getTxt_cus().setEnabled(false);
			((QrView)view).getTxt_cuslxr().setEnabled(false);
			((QrView)view).getTxt_date().setEnabled(true);
		
		}
		else if(this.ds.getValue()==2)
		{
			((QrView)view).getTxt_cus().setEditable(true);
			((QrView)view).getTxt_cuslxr().setEditable(true);
			((QrView)view).getTxt_date().setEditorable(true);
			((QrView)view).getCom_sales().setEnabled(false);
			((QrView)view).getCom_sales().setEditable(false);
			((QrView)view).getCom_sales1().setEnabled(false);	
			((QrView)view).getCom_sales1().setEditable(false);
			((QrView)view).getTxt_cus().setEnabled(true);
			((QrView)view).getTxt_cuslxr().setEnabled(true);
			((QrView)view).getTxt_date().setEnabled(true);
		}
		else if(this.ds.getValue()==3)
		{
			((QrView)view).getTxt_cus().setEditable(false);
			((QrView)view).getTxt_cuslxr().setEditable(false);
			((QrView)view).getTxt_date().setEditorable(false);
			((QrView)view).getCom_sales().setEnabled(false);
			((QrView)view).getCom_sales().setEditable(false);
			((QrView)view).getCom_sales1().setEnabled(false);
			((QrView)view).getCom_sales1().setEditable(false);
		
			((QrView)view).getTxt_cus().setEnabled(false);
			((QrView)view).getTxt_cuslxr().setEnabled(false);
			((QrView)view).getTxt_date().setEnabled(false);
			((QrView)view).getTxt_rdocn().setEditable(false);
		}
		else if(this.ds.getValue()==4)
		{
			((QrView)view).getTxt_rdocn().setEditable(false);
			((QrView)view).getTxt_cus().setEditable(false);
			((QrView)view).getTxt_cuslxr().setEditable(false);
			((QrView)view).getTxt_date().setEditorable(false);
			
			((QrView)view).getTxt_cus().setEnabled(false);
			((QrView)view).getTxt_cuslxr().setEnabled(false);
			((QrView)view).getTxt_date().setEnabled(false);
			
			((QrView)view).getCom_sales().setEnabled(false);
			((QrView)view).getCom_sales().setEditable(false);
			((QrView)view).getCom_sales1().setEnabled(false);
			((QrView)view).getCom_sales1().setEditable(false);
			((QrView)view).getTxt_user().setEditable(true);
			hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((QrView)view).getTxt_user().setText(appMain.lt.sqlclob(hql, 0, 1)[0][1].toString());
			((QrView)view).getTxt_user().setEditable(false);
			
		}
		else
		{	
			
		}
	}

	

	

}
