package erp.ws.sbo.client.swing.dao.abs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IWFlowDao;
import erp.ws.sbo.client.swing.model.wflist;
import erp.ws.sbo.client.swing.model.wflist1;
import erp.ws.sbo.client.swing.model.wflist1Id;

public abstract class AbsWFlowDao extends HibernateDaoSupport implements IWFlowDao {

	private String hql;
	private Object[][] ob,ob1;
	private wflist[] wfs;
	/**
     * set wflist
     * order
     */
	@SuppressWarnings("unchecked")
	public wflist setWflist(String doctype,String docn,String aptemplate,short apstage)
	{
		  Calendar cal = Calendar.getInstance();
		  int month = cal.get(Calendar.MONTH )+1;
		  SimpleDateFormat df=new SimpleDateFormat("hhmm");
		  java.util.Date   date   =   new   java.util.Date();   
		  String time=df.format(date);
		  java.sql.Date   dsql   =   new   java.sql.Date(date.getTime()); 
		  // get the code of terity
		  if(doctype.equals("17"))
		  {
		    hql = "SELECT isnull(u_slpcode1,-1) from Odrf where docentry='"+docn+"'";
		  }
		  if(doctype.equals("13"))
		  {
			hql = "SELECT  isnull(a.u_slpcode1,-1) from Odrf a inner join drf1 b on a.docentry=b.docentry where b.U_djno='"+docn+"'"; 
		  }
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
	       	  JOptionPane.showMessageDialog(null,"单据销售员没有设置，无法进入工作流");
	       	  return null;
          }
          System.out.println("销售员"+ob[0][0].toString());
          Integer wfline=Integer.valueOf(ob[0][0].toString());
         // get the id of first approval person
		  hql = "SELECT UAcode1 from wfdst where code='"+wfline.toString()+"'";
          ob = appMain.lt.clob(hql,0,1);
          if(ob==null||ob.length==0)
          {
	       	  JOptionPane.showMessageDialog(null,"销售员"+wfline.toString()+",第一审批人没有设置，无法进入工作流");
	       	  return null;
          }
          String uacode1=ob[0][0].toString();	
         // get the id of second approval person
		  hql = "SELECT UAcode2 from wfdst where code='"+wfline.toString()+"'";
          ob = appMain.lt.clob(hql,0,1);
          if(ob==null||ob.length==0)
          {
	      	   JOptionPane.showMessageDialog(null,"销售员"+wfline.toString()+",第二审批人没有设置，无法进入工作流");
	      	   return null;
          }
          String uacode2=ob[0][0].toString();
		  wflist wf=new wflist();
		  wf.setDocEntry(appMain.wfd.getMaxDocEntry());
		  wf.setDocNum(appMain.wfd.getMaxDocEntry());
		  wf.setPeriod(month);
		  wf.setInstance((short) 1);
		  wf.setSeries(1);
		  wf.setHandwrtten("N");
		  wf.setCanceled("N");
		  wf.setObject(doctype);
		  wf.setLogInst(1);
		  wf.setUserSign(appMain.oCompany.getUserSignature());
		  wf.setTransfered("-");
		  wf.setStatus("O");
		  wf.setCreateDate(dsql);	
		  wf.setCreateTime(Short.parseShort(time));
		  wf.setUpdateDate(dsql);	
		  wf.setUpdateTime(Short.parseShort(time));
		  wf.setDataSource("I");
		  wf.setUWfTable(aptemplate);
		  wf.setUWfLine(wfline);
		  wf.setUApStage(apstage);
		  wf.setUDraftid(Integer.valueOf(docn));		
		  wf.setUDocid(0);
		  wf.setUWfNode((short) 1);
		  //发起是2,，审批是1，拒绝是0
		  wf.setUWfStatus((short) 2);
		  wf.getWflist1().clear();
		 // Set wfls1=new HashSet(1);
		  // set values for wflist1
		  wflist1 wfl1=new wflist1();
		
		  //set value for wflist1Id
		 		  
		  wflist1Id wfl1id=new  wflist1Id();
		  wfl1id.setDocEntry(wf);
		  wfl1id.setLineId(0);		
		  //set value for wflist1Id end
		  //set value for wflist1
		  wfl1.setId(wfl1id);	
		  wfl1.setVisOrder(0);
		  wfl1.setObject(doctype);
		  wfl1.setUWfNode((short) 1);
		  wfl1.setUWfPerson(uacode1);
		  wfl1.setUApStatus("W");
		  wf.getWflist1().add(wfl1);
		  if(apstage>1)
		  {
			  wfl1=new wflist1();
			  wfl1id=new  wflist1Id();
			  wfl1id.setDocEntry(wf);
			  wfl1id.setLineId(1);						 
			  //set value for wflist1Id end
			  //set value for wflist1
			  wfl1.setId(wfl1id);	
			  wfl1.setVisOrder(0);
			  wfl1.setObject(doctype);
			  wfl1.setUWfNode((short) 2);
			  wfl1.setUWfPerson(uacode2);
			  wfl1.setUApStatus("W");
			  wf.getWflist1().add(wfl1);
		  }		 
		  //set value for wflist1 end
		  			    			  
		return wf;
		
	}
	/**
     * set wflist
     * oinvs 没有用到
     */
	@SuppressWarnings("unchecked")
	public wflist setWflistTer(Integer docentry,String terity,String aptemplate,short apstage)
	{
		  Calendar cal = Calendar.getInstance();
		  int month = cal.get(Calendar.MONTH )+1;
		  SimpleDateFormat df=new SimpleDateFormat("hhmm");
		  java.util.Date   date   =   new   java.util.Date();   
		  String time=df.format(date);
		  java.sql.Date   dsql   =   new   java.sql.Date(date.getTime()); 
		
         // get the id of first approval person
		  hql = "SELECT UAcode1 from wfdst where code='"+terity+"'";
          ob = appMain.lt.clob(hql,0,1);
          if(ob==null||ob.length==0)
          {
       	  JOptionPane.showMessageDialog(null,"地区码"+terity+",第一审批人没有设置，无法进入工作流");
       	  return null;
          }
          String uacode1=ob[0][0].toString();	
         // get the id of second approval person
		  hql = "SELECT UAcode2 from wfdst where code='"+terity+"'";
          ob = appMain.lt.clob(hql,0,1);
          if(ob==null||ob.length==0)
          {
      	   JOptionPane.showMessageDialog(null,"地区码"+terity+",第二审批人没有设置，无法进入工作流");
      	   return null;
          }
          String uacode2=ob[0][0].toString();
		  wflist wf=new wflist();
		  wf.setDocEntry(appMain.wfd.getMaxDocEntry());
		  wf.setDocNum(appMain.wfd.getMaxDocEntry());
		  wf.setPeriod(month);
		  wf.setInstance((short) 1);
		  wf.setSeries(1);
		  wf.setHandwrtten("N");
		  wf.setCanceled("N");
		  wf.setObject("13");
		  wf.setLogInst(1);
		  wf.setUserSign(appMain.oCompany.getUserSignature());
		  wf.setTransfered("-");
		  wf.setStatus("O");
		  wf.setCreateDate(dsql);	
		  wf.setCreateTime(Short.parseShort(time));
		  wf.setUpdateDate(dsql);	
		  wf.setUpdateTime(Short.parseShort(time));
		  wf.setDataSource("I");
		  wf.setUWfTable(aptemplate);
		  wf.setUWfLine(Integer.valueOf(terity));
		  wf.setUApStage(apstage);
		  wf.setUDraftid(Integer.valueOf(docentry.toString()));
		  wf.setUDocid(0);
		  wf.setUWfNode((short) 1);
		  wf.setUWfStatus((short) 2);
		  wf.getWflist1().clear();
		 // Set wfls1=new HashSet(1);
		  // set values for wflist1
		  wflist1 wfl1=new wflist1();
		
		  //set value for wflist1Id
		 		  
		  wflist1Id wfl1id=new  wflist1Id();
		  wfl1id.setDocEntry(wf);
		  wfl1id.setLineId(0);		
		  //set value for wflist1Id end
		  //set value for wflist1
		  wfl1.setId(wfl1id);				    		 
		  wfl1.setVisOrder(0);
		  wfl1.setObject("17");
		  wfl1.setUWfNode((short) 1);
		  wfl1.setUWfPerson(uacode1);
		  wfl1.setUApStatus("W");
		  wf.getWflist1().add(wfl1);
		  if(apstage>1)
		  {
			  wfl1=new wflist1();
			  wfl1id=new  wflist1Id();
			  wfl1id.setDocEntry(wf);
			  wfl1id.setLineId(1);						 
			  //set value for wflist1Id end
			  //set value for wflist1
			  wfl1.setId(wfl1id);				    		 
			  wfl1.setVisOrder(0);
			  wfl1.setObject("17");
			  wfl1.setUWfNode((short) apstage);
			  wfl1.setUWfPerson(uacode2);
			  wfl1.setUApStatus("W");
			  wf.getWflist1().add(wfl1);
		  }		 
		  //set value for wflist1 end
		  			    			  
		return wf;
		
	}
	//添加多个工作流没有用到
	public wflist[] setWflists(Integer docentry,String aptemplate,short apstage)
	{
		 /* Calendar cal = Calendar.getInstance();
		  int month = cal.get(Calendar.MONTH )+1;
		  SimpleDateFormat df=new SimpleDateFormat("hhmm");
		  java.util.Date   date   =   new   java.util.Date();   
		  String time=df.format(date);
		  java.sql.Date   dsql   =   new   java.sql.Date(date.getTime()); */
		  hql = "SELECT distinct c.UTerId from odrf a inner join drf1 b" +
		  		" on a.docentry=b.docentry " +
		  		" inner join ocrd c on c.cardcode=a.cardcodewhere b.U_djno='"+docentry+"'";
          ob1 = appMain.lt.sqlclob(hql,0,100);
          if(ob1==null||ob1.length==0)
          {
       	     JOptionPane.showMessageDialog(null,"业务伙伴的地区码没有设置，无法进入工作流");
       	     return null;
          }
		  // get the code of terity
          wfs=new wflist[ob1.length];
          for(int i=0;i<ob1.length;i++)
          {
			wfs[i]=this.setWflistTer(docentry,ob[i][0].toString(), aptemplate, apstage);
          }
           return wfs;
	}
	/**
     * transmit message
     */
	public void transMes()
	{
		
	}
	
}
