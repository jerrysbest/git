package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OignQDoc2 implements IQDoc{

	private String hql;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		 if(plist.getDraft().equals("0"))
		 {
	 		hql="select distinct 0,type='生产收货',p.docNum,p.docDate,'','',q.user_code,q.u_name ,"+
	           " '', ''," +
	           "''," +
	           "'' " + 		         
	           " from Oign p " +
	           "inner join ousr q on q.userid=p.usersign " +
	           " inner join ign1 b on p.docentry=b.docentry and b.basetype='202'" +
	           " where 1=1 ";
	 		           
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
	 			
	 		}
	 		if(plist.getEndcardCode().length()!=0)
	 		{
	 		
	 		}
	 		if(!plist.getBeginsaleperson().equals(""))
	 		{
	 			hql+="and q.user_code>='"+plist.getBeginsaleperson()+"' ";
	 		}
	 		if(!plist.getEndsaleperson().equals(""))
	 		{
	 			hql+="and q.user_code<='"+plist.getEndsaleperson()+"' ";
	 		}
	 		if(plist.getBegindate()!=null)
	 		{   
	 			hql+="and p.docDate>='"+f.format(plist.getBegindate())+"' ";
	 		}
	 		if(plist.getEnddate()!=null)
	 		{
	 			hql+="and p.docDate<='"+f.format(plist.getEnddate())+"' ";
	 		}
 		
		 }
		 else
		 {
			 hql="select distinct 0,type='库存转储',p.docNum,p.docDate," + 				
 				"p.filler,q.whsname,'','',"+
	           " '', ''," +
	           "''," +
	           "'' " + 		         
	           " from Owtr p " +
	           "inner join Owhs q on p.filler=q.whscode " +
	           " where 1=1 ";		
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
	 			hql+="and p.filler>='"+plist.getBegincardCode()+"' ";
	 		}
	 		if(plist.getEndcardCode().length()!=0)
	 		{
	 			hql+="and p.filler<='"+plist.getEndcardCode()+"' ";
	 		}
	 		if(!plist.getBeginsaleperson().equals(""))
	 		{
	 			
	 		}
	 		if(!plist.getEndsaleperson().equals(""))
	 		{
	 			
	 		}
	 		if(plist.getBegindate()!=null)
	 		{   		 			
	 			hql+="and p.docDate>='"+f.format(plist.getBegindate())+"' ";
	 		}
	 		if(plist.getEnddate()!=null)
	 		{		 			
	 			hql+="and p.docDate<='"+f.format(plist.getEnddate())+"' ";
	 		}
		 }
	 		v.getOd1().updatetable(hql,0);
	}

}
