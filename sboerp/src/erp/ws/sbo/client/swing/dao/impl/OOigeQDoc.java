package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OOigeQDoc implements IQDoc{

	private String hql;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");

 		hql="select distinct 0,type='ÆäËü³ö¿â',p.docNum,p.docDate,'','',p.usersign,q.u_name ,"+
           " '', ''," +
           "''," +
           "'' " + 		         
           " from Oige p " +
           " inner join ousr q on q.userid=p.usersign " +
           " inner join ige1 b on p.docentry=b.docentry and b.basetype='-1'" +
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
	
	 
	
 		v.getOd1().updatetable(hql,0);
	}

}
