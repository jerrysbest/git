package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OrctQDoc implements IQDoc{

	private String hql,hql1,hql2;
	private Object[][] ob1,ob;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		
			 hql="select 0,type='收款单',T0.U_djNo,T2.docDate,'','',T3.slpCode,T1.slpName,'', '','','' " +		         
	           " from RCT2 T0 " +
	           " INNER JOIN OINV T3 ON T0.DocEntry=T3.DocEntry " +
	           " INNER JOIN  OSLP T1 ON T3.SlpCode=T1.SlpCode" +
	           " INNER JOIN  ORCT T2 ON T0.DocNum=T2.DocEntry " +
	           " where T0.U_djNo is not null and T2.Canceled='N'";	
			 if(!plist.getBegindocid().equals(""))
		 		{
		 			hql+="and T0.U_djNo>='"+plist.getBegindocid()+"' ";
		 			
		 		}
		 		if(!plist.getEnddocid().equals(""))
		 		{
		 			hql+="and T0.U_djNo<='"+plist.getEnddocid()+"' ";
		 		}
		 		if(plist.getBegincardCode().length()!=0)
		 		{
		 			
		 		}
		 		if(plist.getEndcardCode().length()!=0)
		 		{
		 			
		 		}
		 		if(!plist.getBeginsaleperson().equals(""))
		 		{
		 			hql+="and T3.SlpCode>='"+plist.getBeginsaleperson()+"' ";
		 		}
		 		if(!plist.getEndsaleperson().equals(""))
		 		{
		 			hql+="and T3.SlpCode<='"+plist.getEndsaleperson()+"' ";
		 		}
		 		if(plist.getBegindate()!=null)
		 		{   
		 				
		 			//java.sql.Date bd=java.sql.Date.valueOf(plist.getBegindate().toString());  
		 			hql+="and T2.docDate>='"+f.format(plist.getBegindate())+"' ";
		 		}
		 		if(plist.getEnddate()!=null)
		 		{
		 			//java.sql.Date ed=java.sql.Date.valueOf(plist.getEnddate().toString());  
		 			hql+="and T2.docDate<='"+f.format(plist.getEnddate())+"' ";
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
		        	hql += " and  T3.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
			      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
		        }
		 		hql+=" union" +
		 			" select 0,type='收款单',T0.U_djNo,T2.docDate,'','',T3.slpCode,T1.slpName,'', '','','' " +		         
 		            " from vpm2 T0 " +
 		            " INNER JOIN ORIN T3 ON T0.DocEntry=T3.DocEntry " +
 		            " INNER JOIN  OSLP T1 ON T3.SlpCode=T1.SlpCode" +
 		            " INNER JOIN  OVPM T2 ON T0.DocNum=T2.DocEntry " +
 		            " where T0.U_djNo is not null and T2.Canceled='N' ";	
				    if(!plist.getBegindocid().equals(""))
			 		{
			 			hql+="and T0.U_djNo>='"+plist.getBegindocid()+"' ";			 			
			 		}
			 		if(!plist.getEnddocid().equals(""))
			 		{
			 			hql+="and T0.U_djNo<='"+plist.getEnddocid()+"' ";
			 		}
			 		if(plist.getBegincardCode().length()!=0)
			 		{
			 			
			 		}
			 		if(plist.getEndcardCode().length()!=0)
			 		{
			 			
			 		}
			 		if(!plist.getBeginsaleperson().equals(""))
			 		{
			 			hql+="and T3.SlpCode>='"+plist.getBeginsaleperson()+"' ";
			 		}
			 		if(!plist.getEndsaleperson().equals(""))
			 		{
			 			hql+="and T3.SlpCode<='"+plist.getEndsaleperson()+"' ";
			 		}
			 		if(plist.getBegindate()!=null)
			 		{   
			 				
			 			//java.sql.Date bd=java.sql.Date.valueOf(plist.getBegindate().toString());  
			 			hql+="and T2.docDate>='"+f.format(plist.getBegindate())+"' ";
			 		}
			 		if(plist.getEnddate()!=null)
			 		{
			 			//java.sql.Date ed=java.sql.Date.valueOf(plist.getEnddate().toString());  
			 			hql+="and T2.docDate<='"+f.format(plist.getEnddate())+"' ";
			 		}
		 		    if(ob==null||ob.length==0)
			        {
			      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
			      	  return;
			        }
			        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
			        {
			      	  
			        }
			        else{
			        	hql += " and  T3.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
				      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
			        }
 
	 		v.getOd1().updatetable(hql,0);
	}

}
