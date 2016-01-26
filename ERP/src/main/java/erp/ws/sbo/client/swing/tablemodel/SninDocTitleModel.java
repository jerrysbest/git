package erp.ws.sbo.client.swing.tablemodel;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.utils.DbUtils;

public class SninDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public SninDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
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
			if(appMain.branch.equals("-2"))
			{
				((SninView)view).getTxt_weight().setEditable(true);
			}
						
			((SninView)view).getJta_SN().setText("");
			((SninView)view).getJta_SN().setEditable(false);		
			((SninView)view).getCom_whs().setEnabled(true);
			((SninView)view).getCom_users().setEnabled(true);
			((SninView)view).getCom_plist().setEnabled(true);
			((SninView)view).getCom_whs().setEditable(false);
			((SninView)view).getCom_users().setEditable(false);
			((SninView)view).getCom_plist().setEditable(false);
			((SninView)view).getTxt_pweight().setText("0");
			((SninView)view).getCom_specification().setSelectedItem(null);
			((SninView)view).getTxt_cweight().setText("0");
			((SninView)view).getTxt_weight().setText("0");
			((SninView)view).getTxt_sweight().setText("0");
			((SninView)view).getTxt_deviation().setText("0");
			((SninView)view).getTxt_length().setText("0");
			hql="select isnull(max(docEntry),0) from Odrf where objtype='67'";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;
			((SninView)view).getTxt_status().setText("草稿");	
			((SninView)view).getTxt_docn().setText(Ndoce.toString());	
			((SninView)view).getDsv().getOd().setGridStatus(docLineStatus.add);
			((SninView)view).getIfseal().setVisible(false);
		}
		else if(this.ds.getValue()==1)
		{
			((SninView)view).getJta_SN().setEditable(false);	
			((SninView)view).getCom_whs().setEnabled(true);
			((SninView)view).getCom_users().setEnabled(true);
			((SninView)view).getCom_plist().setEnabled(true);
			((SninView)view).getCom_whs().setEditable(false);
			((SninView)view).getCom_users().setEditable(false);
			((SninView)view).getCom_plist().setEditable(false);	
			((SninView)view).getIfseal().setVisible(false);
		}
		else if(this.ds.getValue()==2)
		{
			((SninView)view).getJta_SN().setEditable(false);	
			((SninView)view).getCom_whs().setEnabled(true);
			((SninView)view).getCom_users().setEnabled(true);
			((SninView)view).getCom_plist().setEnabled(true);
			((SninView)view).getCom_whs().setEditable(false);
			((SninView)view).getCom_users().setEditable(false);
			((SninView)view).getCom_plist().setEditable(false);	
			((SninView)view).getIfseal().setVisible(false);
		}
		else if(this.ds.getValue()==3)
		{

			((SninView)view).getJta_SN().setEditable(false);	
			((SninView)view).getCom_whs().setEnabled(false);
			((SninView)view).getCom_users().setEnabled(false);
			((SninView)view).getCom_plist().setEnabled(false);
			((SninView)view).getCom_whs().setEditable(false);
			((SninView)view).getCom_users().setEditable(false);
			((SninView)view).getCom_plist().setEditable(false);
			((SninView)view).getIfseal().setVisible(false);
		}
		else if(this.ds.getValue()==4)
		{			
			((SninView)view).getJta_SN().setEditable(false);
			((SninView)view).getCom_whs().setEnabled(false);
			((SninView)view).getCom_users().setEnabled(false);
			((SninView)view).getCom_plist().setEnabled(false);
			((SninView)view).getCom_whs().setEditable(false);
			((SninView)view).getCom_users().setEditable(false);
			((SninView)view).getCom_plist().setEditable(false);			
			((SninView)view).getTxt_docn().setEditable(false);
			/*hql="select userid,u_name from Ousr where userid='"+appMain.oCompany.getUserSignature()+"'";
			((SninView)view).getCom_users().setSelectedItem(new ComboBoxItem(appMain.lt.sqlclob(hql, 0, 1)[0][0],appMain.lt.sqlclob(hql, 0, 1)[0][1].toString()));
			try{
			    addr = InetAddress.getLocalHost();
			   // String ip=addr.getHostAddress().toString();// 获得本机IP
			    name=addr.getHostName().toString();// 获得本机名称			   
			   } catch(Exception e){
			     System.out.println("Bad IP Address!"+e);
			   } 
			*/
			//机号四位
			((SninView)view).getTxt_MNo().setText((appMain.Mno.length()==2?appMain.Mno:"0"+appMain.Mno)+appMain.user1);
			//((SninView)view).getTxt_MNo().setText(name);
			((SninView)view).getTxt_Qinspector().setText(appMain.oCompany.getUserSignature().toString().length()==2?appMain.oCompany.getUserSignature().toString():"0"+appMain.oCompany.getUserSignature().toString());			
			((SninView)view).getTxt_MNo().setEditable(false);
			((SninView)view).getIfseal().setVisible(false);
						 
		}
		else
		{	
			
		}
	}

	

	

}
