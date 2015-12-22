package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class SninQDoc implements IQDoc{

	private String hql;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		
		hql="select distinct 0,type='SNÈë¿â',p.docentry,p.docDate," + 				
			"p.filler,q.whsname,'','',"+
           " '', ''," +
           "''," +
           "'' " + 		         
           " from Odrf p " +
           "inner join Owhs q on p.filler=q.whscode " +
           " where p.objtype='67' ";		
		 if(!plist.getBegindocid().equals(""))
 		{
 			hql+="and p.docentry>='"+plist.getBegindocid()+"' ";			
 		}
 		if(!plist.getEnddocid().equals(""))
 		{
 			hql+="and p.docentry<='"+plist.getEnddocid()+"' ";
 		}
 		if(!plist.getBeginsaleperson().equals(""))
 		{
 			hql+="and p.u_subuid<='"+plist.getBeginsaleperson()+"' ";
 		}
 		if(!plist.getEndsaleperson().equals(""))
 		{
 			hql+="and p.u_subuid<='"+plist.getEndsaleperson()+"' ";
 		}
 		if(plist.getBegincardCode().length()!=0)
 		{
 			hql+="and p.filler>='"+plist.getBegincardCode()+"' ";
 		}
 		if(plist.getEndcardCode().length()!=0)
 		{
 			hql+="and p.filler<='"+plist.getEndcardCode()+"' ";
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
