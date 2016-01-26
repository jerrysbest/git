package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class QrQDoc implements IQDoc{

	private String hql,hql1,hql2;
	private Object[][] ob,ob1;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		 if(plist.getDraft().equals("0"))
		 {	 		
	 		
		 }
		 else
		 {
			 hql="select 0,单据类型='质量反馈单',p.docNum,p.docDate," + 				
 				 "p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
 		          " '', case when p.Status='C' then '已清'  else '未清' end," +
 		          "case when p.canceled='Y' then '是' else '否' end," +
 		          "case when p.printed='Y' then '是' else '否' end " + 		         
 		          " from u_Qrm p,Oslp p1 where p.slpCode=p1.slpCode ";		
			 System.out.println(hql);
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
		        System.out.println(hql);
		 		v.getOd1().updatetable(hql,0);
		 }
		
	}

}
