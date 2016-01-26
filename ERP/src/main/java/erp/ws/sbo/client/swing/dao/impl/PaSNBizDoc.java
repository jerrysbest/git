/**
 * JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposCancelled);//3
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposClosed);//2
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposPlanned);//0
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposReleased);//1
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderTypeEnum_bopotDisassembly);//2
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderTypeEnum_bopotSpecial);//1
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderTypeEnum_bopotStandard);//0
 * JOptionPane.showMessageDialog(null, SBOCOMConstants.BoIssueMethod_im_Backflush);//0
		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoIssueMethod_im_Manual);//1
 * 
 */

package erp.ws.sbo.client.swing.dao.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.PaSN.PaSNView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;

import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.SNL;

public class PaSNBizDoc implements IDoc<PaSNView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private PaSNView v;
	private String hql;
	private Object[][] ob;
	private Object[] ob1;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private Set<String> setsn=new HashSet<String>();	
    private snstatus sns=new snstatus();
    private SNL snl=new SNL();		
	public PaSNBizDoc(){
	
	}
	
	@Override
	public void create(PaSNView v) {
		// TODO Auto-generated method stub	
		//验证
		ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
		try {
			if(!snl.verificationPSN(v.getJta_SN()))
			{
				return;
			}
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Integer j=0;
		String warehouse=new String(""),itcode=new String("");
		BigDecimal length=new BigDecimal(0);
		for(int i=0;i<v.getOd().getRowCount();i++)
		{
			if(v.getOd().getValuethrheader(i, "序列号")==null||(v.getOd().getValuethrheader(i, "序列号")!=null&&v.getOd().getValuethrheader(i, "序列号").toString().equals("")))
			{
				continue;
			}
			j=j+1;
		
            sns=isn.queryByDocId(v.getOd().getValuethrheader(i, "序列号").toString());
            if(sns==null)
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getOd().getValuethrheader(i, "序列号").toString()+"不存在");
            	return;
            }
            if(!sns.getCardcode().toString().equals(""))
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getOd().getValuethrheader(i, "序列号").toString()+"已在客户"+sns.getCardcode().toString()+"手中");
            	return;
            }
            if(sns.isIfPsn())
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getOd().getValuethrheader(i, "序列号").toString()+"是大序列号，不允许打包");
            	return;
            }
            if(i>0)
            {
            	if((!warehouse.equals(sns.getWareHouse().toString()))||(!itcode.equals(sns.getItemcode().toString()))||(!length.equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))))
            	{
            		JOptionPane.showMessageDialog(null, "序列号"+v.getOd().getValuethrheader(i, "序列号").toString()+"与上一序列号的物料编码、米段或者仓库不同，不允许打包");
                	return;
            	}
            }
            if((sns.getPaSn()!=null&&!sns.getPaSn().equals(""))||sns.isIfInPsn())
            {
            	setsn.add(sns.getSn());
            	if(JOptionPane.showConfirmDialog(v,"序列号"+v.getOd().getValuethrheader(i, "序列号").toString()+"属于父序列号"+sns.getPaSn()+"父序列号将失效,是否继续")==1)
            	{
            		return;
            	}     
            }
	            warehouse=sns.getWareHouse();
	            itcode=sns.getItemcode();
	            length=new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}		
		if(j==0)
		{
			return;
		}
		this.v=v;
		
	    try {
			if(!snl.createPSN(v))
			{
				return;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		ob1=new Object[2];
		ob1[0]=v.getTxt_tsn().getText();
		ob1[1]=v.getJta_memo().getText();
		ob=new Object[v.getOd().getRowCount()][2];
		for(int i=0;i<v.getOd().getRowCount();i++)
		{
			if(v.getOd().getValuethrheader(i, "序列号")!=null&&!v.getOd().getValuethrheader(i, "序列号").toString().equals(""))
			{
				ob[i][0]=v.getOd().getValuethrheader(i, "序列号");
				ob[i][1]=v.getOd().getValuethrheader(i, "备注");		
			}
		}	
		appMain.psn.add(appMain.psn.setPasn(ob1, ob));
				
		v.getOd1().setDs(docTitleStatus.add);
		v.getOd1().setDocTitleStatus(v);
		v.getOd().setDocLineStatus(docLineStatus.add);
		v.getOd().setGridStatus(docLineStatus.add);
		dmv.setadd();		
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(PaSNView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[][] getDocLists(ParaList p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getfirst() {
		// TODO Auto-generated method stub				
		  hql = "SELECT min(a.docEntry) from [@pasn] a ";
          ob = appMain.lt.sqlclob(hql,0,1);
           if(ob==null||ob.length==0)
           {
        	   return 0;
           }
           else{
        	   return Integer.valueOf(ob[0][0].toString());
           }         		 
	}

	@Override
	public Integer getprev(int id) {
		// TODO Auto-generated method stub
		  hql = "SELECT  max(a.docEntry)  from [@pasn] a  where  a.docEntry<'"+id+"'";
          ob = appMain.lt.sqlclob(hql,0,1);
           if(ob==null||ob.length==0)
           {
           	  return 0;
           }
           else{
        	   return Integer.valueOf(ob[0][0].toString());
           }
	}

	@Override
	public Integer getnext(int id) {
		// TODO Auto-generated method stub
		  hql = "SELECT  a.docEntry  from [@pasn] a  where  a.docEntry>'"+id+"'";
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
          	  return 0;
          }
          else{
       	   return Integer.valueOf(ob[0][0].toString());
          }
	}

	@Override
	public Integer getlast() {
		// TODO Auto-generated method stub
		  hql = "SELECT max(a.docEntry) from [@pasn] a ";
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
          	  return 0;
          }
          else{
       	   return Integer.valueOf(ob[0][0].toString());
          }
	}

	@Override
	public void setValues(PaSNView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}
			hql="select a.docentry,createdate=convert(nvarchar(10),a.createDate,23),a.U_SN,a.U_Memo," +
			    "status=case when a.status='O' then '有效' when a.status='C' then '无效' else '' end from [@pasn] a " +
				"where a.docentry='"+id+"'";
			 ob = appMain.lt.sqlclob(hql,0,1); 
			 if(ob==null||ob.length==0)
			 {
				 return;
			 }
		     v.getOd1().setDs(docTitleStatus.addp);
		     v.getOd1().setDocTitleStatus(v);
		     v.getTxt_date().setText(ob[0][1].toString());
		     v.getTxt_status().setText(ob[0][4].toString());
		     v.getTxt_tsn().setText(ob[0][2].toString());
		     v.getJta_memo().setText(ob[0][3].toString());
			 v.getTxt_docn().setText(id.toString());
			 hql="select a.lineid+1, a.U_SN,a.U_Memo from [@asn1] a " +
				 "where  a.docentry='"+id+"'";
			 v.getOd().updatetable(hql,0);		
		 		
	}
	@Override
	public void close(PaSNView v)
	{
		hql="update [@pasn] set " +
		    "status='C'  " +
			"where docentry='"+v.getTxt_docn().getText()+"'";
		dbu.exeSql(hql);
		hql = "update  a  set a.ifdes='1' from dbo.[@SNStatus] a " +
			  " where a.sn='"+v.getTxt_tsn().getText()+"'";
		dbu.exeSql(hql);
       for(int i=0;i<v.getOd().getRowCount();i++)
       {
			 hql = "update  a  set a.pasn='',a.ifInPSN='0' from dbo.[@SNStatus] a " +
				   " where a.sn='"+v.getOd().getValuethrheader(i, "序列号")+"'";
             dbu.exeSql(hql);
       }
       JOptionPane.showMessageDialog(null,"关闭成功");	
       v.getTxt_status().setText("无效");
	}
	public PaSNView getV() {
		return v;
	}

	public void setV(PaSNView v) {
		this.v = v;
	}

	@Override
	public void print(PaSNView v) {
		// TODO Auto-generated method stub
	}

	@Override
	public void ctarget(PaSNView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(PaSNView v) {
		// TODO Auto-generated method stub
		
	}
	
}
