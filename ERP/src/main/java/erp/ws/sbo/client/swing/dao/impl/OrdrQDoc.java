package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OrdrQDoc implements IQDoc{

	private String hql,hql1,hql2;
	private Object[][] ob,ob1;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		 if(plist.getDraft().equals("0"))
		 {
	 		hql="select 0,单据类型='草稿',p.docEntry,p.docDate," + 				
 				"p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
	            " wfcode=p2.docEntry, wddStatus=(case when p.wddStatus='W' then '审批中' when p.wddStatus='Y' then '已审批' when p.wddStatus='N' then '已拒绝' else '无' end)," +
	            "canceled=(case when p.canceled='Y' then '是' else '否' end)," +
	            "printed=(case when p.printed='Y' then '是' else '否' end) " + 		         
	            " from Odrf p " +
	            " inner join  Oslp p1 on p1.slpcode=p.slpcode " +
	            " left join [@wflist] p2 on p2.U_Draftid=p.docEntry " +
	            " where  " +
	            " p.docStatus='O' and p.objtype='17' ";	 		           
	 		if(!plist.getBegindocid().equals(""))
	 		{
	 			hql+="and p.docEntry>='"+plist.getBegindocid()+"' ";	 			
	 		}
	 		if(!plist.getEnddocid().equals(""))
	 		{
	 			hql+="and p.docEntry<='"+plist.getEnddocid()+"' ";
	 		}
	 		if(plist.getBegincardCode().length()!=0)
	 		{
	 			hql+="and p.cardCode>='"+plist.getBegincardCode()+"' ";
	 		}
	 		if(plist.getEndcardCode().length()!=0)
	 		{
	 			hql+="and p.cardCode<='"+plist.getEndcardCode()+"' ";
	 		}
	 		if(!plist.getBeginsaleperson().equals(""))
	 		{
	 			hql+="and p.slpCode>='"+plist.getBeginsaleperson()+"' ";
	 		}
	 		if(!plist.getEndsaleperson().equals(""))
	 		{
	 			hql+="and p.slpCode<='"+plist.getEndsaleperson()+"' ";
	 		}
	 		if(plist.getBegindate()!=null)
	 		{   
	 			hql+="and p.docDate>='"+f.format(plist.getBegindate())+"' ";
	 		}
	 		if(plist.getEnddate()!=null)
	 		{
	 			hql+="and p.docDate<='"+f.format(plist.getEnddate())+"' ";
	 		}
	 		 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
			 ob1 = appMain.lt.sqlclob(hql1,0,1);
			 hql2="select u_enable from dbo.[@sms] where code='DPERM'";
			 ob = appMain.lt.sqlclob(hql2,0,1);
	        if(ob==null||ob.length==0)
	        {
	      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
	      	  return;
	        }
	        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
	        {
	      	  
	        }
	        else{
	        	hql += " and  p.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
		      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
	        }
	 		
		 }
		 else
		 {
			 hql="select 0,单据类型='订单',p.docNum,p.docDate," + 				
 				 "p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
 		          " '', case when p.docStatus='C' then '已清'  else '未清' end," +
 		          "case when p.canceled='Y' then '是' else '否' end," +
 		          "case when p.printed='Y' then '是' else '否' end " + 		         
 		          " from Ordr p,Oslp p1 where p.slpCode=p1.slpCode ";		
			 if(!plist.getBegindocid().equals(""))
		 		{
		 			hql+="and p.docNum>='"+plist.getBegindocid()+"' ";
		 			
		 		}
		 		if(!plist.getEnddocid().equals(""))
		 		{
		 			hql+="and p.docNum<='"+plist.getEnddocid()+"' ";
		 		}
		 		if(plist.getBegincardCode().length()!=0)
		 		{
		 			hql+="and p.cardCode>='"+plist.getBegincardCode()+"' ";
		 		}
		 		if(plist.getEndcardCode().length()!=0)
		 		{
		 			hql+="and p.cardCode<='"+plist.getEndcardCode()+"' ";
		 		}
		 		if(!plist.getBeginsaleperson().equals(""))
		 		{
		 			hql+="and p.slpCode>='"+plist.getBeginsaleperson()+"' ";
		 		}
		 		if(!plist.getEndsaleperson().equals(""))
		 		{
		 			hql+="and p.slpCode<='"+plist.getEndsaleperson()+"' ";
		 		}
		 		if(plist.getBegindate()!=null)
		 		{   
		 				
		 			//java.sql.Date bd=java.sql.Date.valueOf(plist.getBegindate().toString());  
		 			hql+="and p.docDate>='"+f.format(plist.getBegindate())+"' ";
		 		}
		 		if(plist.getEnddate()!=null)
		 		{
		 			//java.sql.Date ed=java.sql.Date.valueOf(plist.getEnddate().toString());  
		 			hql+="and p.docDate<='"+f.format(plist.getEnddate())+"' ";
		 		}
		 		hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
				ob1 = appMain.lt.sqlclob(hql1,0,1);
				hql2="select u_enable from dbo.[@sms] where code='DPERM'";
				ob = appMain.lt.sqlclob(hql2,0,1);
		        if(ob==null||ob.length==0)
		        {
		      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
		      	  return;
		        }
		        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
		        {
		      	  
		        }
		        else{
		        	hql += " and  p.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
			      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
		        }
		 }
	 		v.getOd1().updatetable(hql,0);
	}

}
