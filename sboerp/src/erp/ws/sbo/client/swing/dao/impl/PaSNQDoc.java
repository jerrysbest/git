package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class PaSNQDoc implements IQDoc{

	private String hql;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");

 		hql="select 0,''" +
 			",p.docEntry,p.createDate,p.U_SN,'','','',"+
           " '', status=case p.Status when 'O' then '有效'  else '无效' end," +
           "'','' " + 		         
           " from [@pasn] p where 1=1  ";
 		           
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
 			hql+="and p.U_SN>='"+plist.getBegincardCode()+"' ";
 		}
 		if(plist.getEndcardCode().length()!=0)
 		{
 			hql+="and p.U_SN<='"+plist.getEndcardCode()+"' ";
 		}
 		if(!plist.getBeginsaleperson().equals(""))
 		{
 			
 		}
 		if(!plist.getEndsaleperson().equals(""))
 		{
 			
 		}
 		if(plist.getBegindate()!=null)
 		{   
 			hql+="and convert(nvarchar(10),p.createDate,23)>='"+f.format(plist.getBegindate())+"' ";
 		}
 		if(plist.getEnddate()!=null)
 		{
 			hql+="and convert(nvarchar(10),p.createDate,23)<='"+f.format(plist.getEnddate())+"' ";
 		}
 		
 		v.getOd1().updatetable(hql,0);
	}

}
