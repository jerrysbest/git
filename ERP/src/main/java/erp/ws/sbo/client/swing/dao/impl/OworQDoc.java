package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OworQDoc implements IQDoc{

	private String hql;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");

 		hql="select 0,type=case p.Type when 'S' then '标准' when 'D' then '拆分' when 'P' then '特殊' else '' end" +
 			",p.docEntry,p.postDate,p.itemcode,p1.itemname,p.warehouse,p2.whsname,"+
           " '', status=case p.Status when 'P' then '计划的' when 'R' then '批准' when 'C' then '关闭' else '' end," +
           "'',printed=case when p.printed='Y' then '是' else '否' end " + 		         
           " from Owor p ,Oitm p1,owhs p2 where p.itemCode=p1.itemCode and p.warehouse=p2.whscode ";
 		           
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
 			hql+="and p.itemCode>='"+plist.getBegincardCode()+"' ";
 		}
 		if(plist.getEndcardCode().length()!=0)
 		{
 			hql+="and p.itemCode<='"+plist.getEndcardCode()+"' ";
 		}
 		if(!plist.getBeginsaleperson().equals(""))
 		{
 			hql+="and p.warehouse>='"+plist.getBeginsaleperson()+"' ";
 		}
 		if(!plist.getEndsaleperson().equals(""))
 		{
 			hql+="and p.warehouse<='"+plist.getEndsaleperson()+"' ";
 		}
 		if(plist.getBegindate()!=null)
 		{   
 			hql+="and p.postDate>='"+f.format(plist.getBegindate())+"' ";
 		}
 		if(plist.getEnddate()!=null)
 		{
 			hql+="and p.postDate<='"+f.format(plist.getEnddate())+"' ";
 		}
 		
 		v.getOd1().updatetable(hql,0);
	}

}
