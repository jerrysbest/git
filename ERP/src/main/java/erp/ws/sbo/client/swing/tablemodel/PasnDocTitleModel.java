package erp.ws.sbo.client.swing.tablemodel;

import java.text.SimpleDateFormat;
import java.util.Date;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.view.PaSN.PaSNView;
import erp.ws.sbo.utils.DbUtils;

public class PasnDocTitleModel extends AbstractDocTitleModel<ColDocTitle, DocTitle>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852602242087403590L;



	public PasnDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}
	public PasnDocTitleModel(ColDocTitle colob, DocTitle ob, DbUtils<ColDocTitle, DocTitle> dbu,
			String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
	}
    private String hql;   
    
	

	 //this class is to set  add,query,addplus,remend status to doctitle
	@Override
	public void setDocTitleStatus(Object view) {
		// TODO Auto-generated method stub
		if(this.ds.getValue()==0)
		{
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		    java.util.Date   date   =   new   java.util.Date();   
		    String time=df.format(date);
		   
			((PaSNView)view).getTxt_date().setEditorable(true);

			hql="select isnull(max(docEntry),0) from [@pasn]";
			Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;		
			((PaSNView)view).getTxt_docn().setText(Ndoce.toString());	
		    ((PaSNView)view).getTxt_date().setDate(new Date());
		    ((PaSNView)view).getTxt_tsn().setText((appMain.oCompany.getUserSignature().toString().length()==2?appMain.oCompany.getUserSignature().toString():"0"+appMain.oCompany.getUserSignature().toString())+time);
		    ((PaSNView)view).getJta_memo().setText("");
		    ((PaSNView)view).getJta_SN().setText("");
		    ((PaSNView)view).getTxt_status().setText("");	
		}
		else if(this.ds.getValue()==1)
		{

			((PaSNView)view).getTxt_tsn().setEditable(true);
			((PaSNView)view).getTxt_date().setEditorable(true);

			
		
		}
		else if(this.ds.getValue()==2)
		{
			((PaSNView)view).getTxt_tsn().setEditable(true);
			((PaSNView)view).getTxt_date().setEditorable(true);

		
		}
		else if(this.ds.getValue()==3)
		{
			((PaSNView)view).getTxt_tsn().setEditable(false);
			((PaSNView)view).getTxt_date().setEditorable(false);

		}
		else if(this.ds.getValue()==4)
		{			
			((PaSNView)view).getTxt_status().setEditable(false);
			((PaSNView)view).getTxt_tsn().setEditable(false);
			((PaSNView)view).getTxt_date().setEditorable(false);

		    ((PaSNView)view).getTxt_date().setDate(new Date());
		    ((PaSNView)view).getTxt_tsn().setText("");
		    ((PaSNView)view).getJta_memo().setText("");
		    ((PaSNView)view).getJta_SN().setText("");
		    ((PaSNView)view).getTxt_MNo().setText((appMain.oCompany.getUserSignature().toString().length()==2?appMain.oCompany.getUserSignature().toString():"0"+appMain.oCompany.getUserSignature().toString())+appMain.user1);
		    ((PaSNView)view).getTxt_MNo().setEditable(false);
		}
		else
		{	
			
		}
	}

	

	

}
