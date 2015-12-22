package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.utils.DbUtils;

public class OignDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public OignDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
			((OignView)view).getTxt_cppo().setEditable(true);
			((OignView)view).getTxt_status().setEditable(true);
			((OignView)view).getJta_SN().setText("");
			((OignView)view).getJta_SN().setEditable(true);
			((OignView)view).getCom_type().setEnabled(true);
			((OignView)view).getCom_whs().setEnabled(true);
			((OignView)view).getCom_users().setEnabled(true);
			((OignView)view).getCom_plist().setEnabled(true);
			((OignView)view).getCom_snware().setEnabled(true);
			((OignView)view).getCom_type().setEditable(false);
			((OignView)view).getCom_whs().setEditable(false);
			((OignView)view).getCom_users().setEditable(false);
			((OignView)view).getCom_plist().setEditable(false);
			hql="select isnull(max(docEntry),0) from Odrf where objtype='59'";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;		
			((OignView)view).getTxt_docn().setText(Ndoce.toString());	
			((OignView)view).getTxt_cppo().setText("");	
			((OignView)view).getTxt_status().setText("");	
			((OignView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
			
			((OignView)view).getTxt_pweight().setText("0");
			((OignView)view).getCom_specification().setSelectedItem(null);
			((OignView)view).getTxt_cweight().setText("0");
			((OignView)view).getTxt_weight().setText("0");
			((OignView)view).getTxt_sweight().setText("0");
			((OignView)view).getTxt_deviation().setText("0");
			((OignView)view).getTxt_length().setText("0");
			((OignView)view).getTxt_status().setEditable(false);
		}
		else if(this.ds.getValue()==1)
		{
			((OignView)view).getTxt_cppo().setEditable(true);
			((OignView)view).getJta_SN().setEditable(true);
			((OignView)view).getCom_type().setEnabled(true);
			((OignView)view).getCom_whs().setEnabled(true);
			((OignView)view).getCom_users().setEnabled(true);
			((OignView)view).getCom_plist().setEnabled(true);
			((OignView)view).getCom_snware().setEnabled(true);
			((OignView)view).getCom_type().setEditable(false);
			((OignView)view).getCom_whs().setEditable(false);
			((OignView)view).getCom_users().setEditable(false);
			((OignView)view).getCom_plist().setEditable(false);			
		
		}
		else if(this.ds.getValue()==2)
		{
			((OignView)view).getTxt_cppo().setEditable(true);
			((OignView)view).getJta_SN().setEditable(true);
			((OignView)view).getCom_type().setEnabled(true);
			((OignView)view).getCom_whs().setEnabled(true);
			((OignView)view).getCom_users().setEnabled(true);
			((OignView)view).getCom_plist().setEnabled(true);
			((OignView)view).getCom_snware().setEnabled(true);
			((OignView)view).getCom_type().setEditable(false);
			((OignView)view).getCom_whs().setEditable(false);
			((OignView)view).getCom_users().setEditable(false);
			((OignView)view).getCom_plist().setEditable(false);
		
		}
		else if(this.ds.getValue()==3)
		{

			((OignView)view).getTxt_cppo().setEditable(false);
			((OignView)view).getJta_SN().setEditable(false);
			((OignView)view).getCom_type().setEnabled(false);
			((OignView)view).getCom_whs().setEnabled(false);
			((OignView)view).getCom_users().setEnabled(false);
			((OignView)view).getCom_plist().setEnabled(false);
			((OignView)view).getCom_snware().setEnabled(false);
			((OignView)view).getCom_type().setEditable(false);
			((OignView)view).getCom_whs().setEditable(false);
			((OignView)view).getCom_users().setEditable(false);
			((OignView)view).getCom_plist().setEditable(false);
			((OignView)view).getCom_snware().setEditable(false);
		}
		else if(this.ds.getValue()==4)
		{			
			((OignView)view).getTxt_cppo().setEditable(false);
			((OignView)view).getTxt_status().setEditable(false);
			((OignView)view).getJta_SN().setEditable(false);
			((OignView)view).getCom_type().setEnabled(false);
			((OignView)view).getCom_whs().setEnabled(false);
			((OignView)view).getCom_users().setEnabled(false);
			((OignView)view).getCom_plist().setEnabled(false);
			((OignView)view).getCom_snware().setEnabled(false);
			((OignView)view).getCom_type().setEditable(false);
			((OignView)view).getCom_whs().setEditable(false);
			((OignView)view).getCom_users().setEditable(false);
			((OignView)view).getCom_plist().setEditable(false);
			
			((OignView)view).getTxt_docn().setEditable(false);
			
			((OignView)view).getCom_whs().setVisible(false);
			((OignView)view).getCom_plist().setVisible(false);
			
			hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((OignView)view).getCom_users().setSelectedItem(new ComboBoxItem(appMain.lt.sqlclob(hql, 0, 1)[0][0],appMain.lt.sqlclob(hql, 0, 1)[0][1].toString()));
			
			((OignView)view).getTxt_MNo().setText((appMain.Mno.length()==2?appMain.Mno:"0"+appMain.Mno)+appMain.user1);
			//((SninView)view).getTxt_MNo().setText(name);
			((OignView)view).getTxt_Qinspector().setText(appMain.oCompany.getUserSignature().toString().length()==2?appMain.oCompany.getUserSignature().toString():"0"+appMain.oCompany.getUserSignature().toString());			
			((OignView)view).getTxt_MNo().setEditable(false);
		}
		else
		{	
			
		}
	}

	

	

}
