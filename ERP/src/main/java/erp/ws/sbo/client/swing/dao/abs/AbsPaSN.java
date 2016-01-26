package erp.ws.sbo.client.swing.dao.abs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IPaSN;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.asn1;
import erp.ws.sbo.client.swing.model.asn1Id;
import erp.ws.sbo.client.swing.model.pasn;
import erp.ws.sbo.utils.DbUtils;


public abstract class AbsPaSN extends HibernateDaoSupport implements IPaSN {

    private String hql;
    private Object[][] ob;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	@SuppressWarnings("unchecked")
	public pasn setPasn(Object[] ob1,Object[][] ob2)
	{
	    SimpleDateFormat df=new SimpleDateFormat("hhmm");
	    java.util.Date   date   =   new   java.util.Date();   
	    String time=df.format(date);
	    df=new SimpleDateFormat("MM");
	    String p=df.format(date);
		pasn pan=new pasn();
		pan.setDocEntry(appMain.psn.getMaxDocEntry());
		pan.setDocNum(pan.getDocEntry());
		pan.setCanceled("N");
		pan.setHandwrtten("N");
		pan.setStatus("O");		
		pan.setPeriod(Integer.valueOf(p));
		pan.setObject(200);
		pan.setInstance((short) 1);
		pan.setLogInst(1);
		pan.setUserSign(appMain.oCompany.getUserSignature());
		pan.setTransfered("-");
		pan.setCreateDate(new Timestamp(new Date().getTime()));	
		pan.setCreateTime(Short.parseShort(time));
		pan.setUpdateDate(new Timestamp(new Date().getTime()));	
		pan.setUpdateTime(Short.parseShort(time));
		pan.setDataSource("I");
		pan.setUSn(ob1[0].toString());
		pan.setUMemo(ob1[1].toString());
		pan.getAsn1().clear();
		asn1 an1;
		asn1Id id;
		for(int i=0;i<ob2.length;i++)
		{
			if(ob2[i][0]==null||(ob2[i][0]!=null&&ob2[i][0].toString().equals("")))
			{
				continue;
			}
			//JOptionPane.showMessageDialog(null,i);		
			an1=new asn1();
			id=new asn1Id();
			hql="select ifinpsn,pasn from [@SNStatus] where sn='"+ob2[i][0].toString()+"'";
			ob = appMain.lt.sqlclob(hql,0,1);
			String P=ob[0][0].toString();
			if(P.equals("1"))
			{
			  hql = "update  a  set a.status='C' from dbo.[@pasn] a " +
			  		"inner join [@asn1] b on a.docentry=b.docentry where b.u_sn='"+ob2[i][0].toString()+"'";
			  dbu.exeSql(hql);
			  hql = "update  a  set a.pasn='"+ob1[0].toString()+"',a.ifInPSN='1' from dbo.[@SNStatus] a " +
				  	" where a.u_sn='"+ob2[i][0].toString()+"'";
			  dbu.exeSql(hql);
			  hql = "update  dbo.[@snstatus] set ifdes='1' where SN='"+ob[0][1].toString()+"'";
  			  dbu.exeSql(hql);

			  hql="select a.u_sn from dbo.[@pasn] a " +
			  	  "inner join [@asn1] b on a.docentry=b.docentry where b.u_sn='"+ob2[i][0].toString()+"'";
			  ob = appMain.lt.sqlclob(hql,0,1);
			  an1.setUMemo(ob2[i][1].toString()+",使组合序列号"+ob[0][0].toString()+"失效");
			}	
			else{
				an1.setUMemo(ob2[i][1].toString());
				 hql = "update  a  set a.pasn='"+ob1[0].toString()+"',a.ifinpsn='1' from dbo.[@SNStatus] a " +
					  " where a.sn='"+ob2[i][0].toString()+"'";
					  dbu.exeSql(hql);
			}
			//JOptionPane.showMessageDialog(null,ob2[i][0].toString());		
			id.setDocEntry(pan);
			id.setLineId(i);
			an1.setId(id);
			an1.setVisOrder(0);			
			an1.setUSn(ob2[i][0].toString());
			pan.getAsn1().add(an1);	
			//JOptionPane.showMessageDialog(null,ob2[i][0].toString());	
			//JOptionPane.showMessageDialog(null,pan.getAsn1().size());	
		}		
	   		hql="update a set a.itemcode=c.itemcode,a.length=c.length,a.cweight=cast(b.cweight as decimal(15,3)),QSn=b.c from dbo.[@snstatus] a "+
	   			"inner join (select cweight=sum(cweight),c=count(*),pasn from dbo.[@snstatus] where ifinpsn='1' and pasn='"+ob1[0].toString()+"' " +
	   			"group by pasn) b on a.sn=b.pasn "+ 
	   			"inner join (select top 1 itemcode,length,pasn from dbo.[@snstatus] where ifinpsn='1' and pasn='"+ob1[0].toString()+"') c  on a.sn=c.pasn "+  
	   			"where a.sn='"+ob1[0].toString()+"' ";
	   	     dbu.exeSql(hql);
		return pan;		
	}
}
