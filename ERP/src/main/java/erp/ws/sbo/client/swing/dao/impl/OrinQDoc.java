package erp.ws.sbo.client.swing.dao.impl;

import java.text.SimpleDateFormat;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OrinQDoc  implements IQDoc{

	private String hql;
	
	@Override
	public void doclist(ParaList plist,QueryWindowView<?> v) {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		
			 hql="select distinct 0,type='贷向',p2.U_DjNo,docdate=min(p.docDate)," + 				
 				"p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
	            " '', docStatus=case when p.docStatus='C' then '已清'  else '未清' end," +
	            "canceled=case when p.canceled='Y' then '是' else '否' end," +
	            "printed=case when p.printed='Y' then '是' else '否' end " + 		         
	            " from Orin p,Oslp p1,Rin1 p2 where p.slpCode=p1.slpCode and p.docEntry=p2.docEntry ";	 		           	
			 if(!plist.getBegindocid().equals(""))
		 		{
		 			hql+="and p2.U_DjNo>='"+plist.getBegindocid()+"' ";		 			
		 		}
		 		if(!plist.getEnddocid().equals(""))
		 		{
		 			hql+="and p2.U_DjNo<='"+plist.getEnddocid()+"' ";
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
		 		hql+=" group by p2.U_DjNo,p.cardCode,p.cardName,p.slpCode,p1.slpName,p.docStatus,p.canceled,p.printed";
		 
	 		v.getOd1().updatetable(hql,0);
	}

}
