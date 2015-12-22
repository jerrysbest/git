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

import java.util.Date;

import javax.swing.JOptionPane;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.wrapper.com.ComFailException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Oige.OigeView;
//import erp.ws.sbo.utils.SNL;
import erp.ws.sbo.utils.DbUtils;

public class OigeDoc implements IDoc<OigeView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private OigeView v;
	private String hql;
	private Object[][] ob;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private IDocuments oidoc;
	public OigeDoc(){
	
	}
	
	@Override
	public void create(OigeView v) {
		// TODO Auto-generated method stub	
		this.v=v;
		
		try {
			oidoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInventoryGenExit);
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}	
		try{
		oidoc.setDocDate(new Date());	
		oidoc.getUserFields().getFields().item("U_Czy").setValue(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());
		oidoc.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
				
			if(v.getOd().getValuethrheader(i,"生产订单号")==null||v.getOd().getValuethrheader(i,"生产订单号").toString().equals(""))
			{
				continue;
			}	
			if(i>0&&v.getOd().getValuethrheader(i,"生产订单号").toString().equals(v.getOd().getValuethrheader(i-1,"生产订单号").toString()))
			{
				continue;
			}
			if(v.getOd().getValuethrheader(i,"物料代码")==null||v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
			{
				continue;
			}						
			oidoc.getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "仓库").toString());
			oidoc.getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "实际库存数量").toString()));
			oidoc.getLines().setBaseType(202);
			 hql = "SELECT a.docEntry from OWOR a  where a.docNum='"+ v.getOd().getValuethrheader(i, "生产订单号").toString()+"'";
	         ob = appMain.lt.sqlclob(hql,0,1);
	         if(ob==null||ob.length==0)
	         {
	        	 JOptionPane.showMessageDialog(null,v.getOd().getValuethrheader(i, "生产订单号").toString()+"生产订单号不正确");
	        	 return;
	         }		           
			oidoc.getLines().setBaseEntry(Integer.valueOf(ob[0][0].toString()));			
			//oidoc.getLines().setTransactionType(SBOCOMConstants.BoTransactionTypeEnum_botrntComplete);
			oidoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "米段").toString()));
			oidoc.getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "标准库存数量").toString()));
			try{
				oidoc.getLines().getUserFields().getFields().item("U_Gs").setValue(v.getOd().getValuethrheader(i, "实际收货个数").toString());
				}
				catch(ComFailException e0)
				{
					JOptionPane.showMessageDialog(null,"实际收货个数请输入整数");
					System.out.println("生产发货sbo接口出错");
				}
			
			oidoc.getLines().getUserFields().getFields().item("U_Scwc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "误差").toString()));
			oidoc.getLines().getUserFields().getFields().item("U_Ckck").setValue( v.getOd().getValuethrheader(i, "出库仓库")==null?"":v.getOd().getValuethrheader(i, "出库仓库").toString());
			
			oidoc.getLines().add();		
			
		}	
	
		 appMain.lRetCode=oidoc.add();
					
		if(appMain.lRetCode!=0)
		{ 
			appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
			appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
			
			 if (appMain.lErrCode != -4006) // Incase adding an order failed
             {
				 JOptionPane.showMessageDialog(null,"添加失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
             }
			 else
			 {
				 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
			 }
		}
		else
		{	
			/***
			SNL snl=new SNL(v.getDsv());		
		    snl.createdoc(v.getDsv(),false,"",appMain.oCompany.getNewObjectKey().toString());
			***/
			for(int i=0;i<v.getOd().dataSet.length;i++)
		    {
				if(v.getOd().getValuethrheader(i,"生产订单号")==null||v.getOd().getValuethrheader(i,"生产订单号").toString().equals(""))
				{
					continue;
				}	
				if(i>0&&v.getOd().getValuethrheader(i,"生产订单号").toString().equals(v.getOd().getValuethrheader(i-1,"生产订单号").toString()))
				{
					continue;
				}
				if(v.getOd().getValuethrheader(i,"物料代码")==null||v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
				{
					continue;
				}	
				 hql = "SELECT a.docEntry from OWOR a  where a.docNum='"+ v.getOd().getValuethrheader(i, "生产订单号").toString()+"'";
		         ob = appMain.lt.sqlclob(hql,0,1);
			     hql = "update owor set U_cqty='"+v.getOd().getValuethrheader(i, "实际收货个数").toString()+"' where docentry='"+ob[0][0].toString()+"'";
			     dbu.exeSql(hql);
		    }
			oidoc.release();
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
					
		}
		}
		catch(NullPointerException e0){				
			 JOptionPane.showMessageDialog(null,"行数量或者金额输入不合法，请检查");
			 System.out.println("生产发货表格输入不合法");
		}
		catch(ComFailException e0)
		{
			JOptionPane.showMessageDialog(null,"实际收货个数请输入整数");
			System.out.println("生产发货sbo接口出错");
		}
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(OigeView v) {
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
		
		  hql = "SELECT min(a.docNum) from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='202'";
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
		  hql = "SELECT  max(a.docNum)  from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='202' and a.docNum<'"+id+"'";
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
		  hql = "SELECT  a.docNum  from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='202' and a.docNum>'"+id+"'";
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
		  hql = "SELECT max(a.docNum) from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='202'";
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
	public void setValues(OigeView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}
			hql="select a.docNum,a.usersign,c.U_Name from oige a " +
				"inner join ige1 b on a.docentry=b.docentry " +
				"inner join ousr c on a.usersign=c.userid " +
				"where b.basetype='202' and a.docNum='"+id+"'";
			 ob = appMain.lt.sqlclob(hql,0,1); 
			 if(ob==null||ob.length==0)
			 {
				 return;
			 }
		     v.getOd1().setDs(docTitleStatus.addp);
		     v.getOd1().setDocTitleStatus(v);
		     ComboBoxItem  Cbi=new ComboBoxItem(ob[0][1].toString(),ob[0][2].toString());
			 v.getCom_users().setSelectedItem(Cbi); 
			 v.getTxt_docn().setText(id.toString());
			 hql="select 0,b.u_snid, d.docNum,c.itemcode,c.itemname,b.u_ymd,b.u_mtmd,c.salunitmsr," +
			 	 "unitQty=convert(decimal(18,3),isnull(d.U_length,0)*isnull(c.u_mtzl,0)/isnull(c.u_mtmd,0))," +
			 	 "0,gs=isnull(b.u_gs,0),b.unitmsr,b.u_zz,b.quantity,b.u_scwc," +
			 	 "'',b.whscode,b.u_ckck,d.plannedqty,d.cmpltqty,convert(nvarchar(10),d.duedate,23),e.U_name " +
			 	 "from oige a " +
			 	 "inner join ige1 b on a.docentry=b.docentry " +
			 	 "inner join oitm c on b.itemcode=c.itemcode " +
			 	 "inner join owor d on d.docNum=b.baseRef " +
			 	 "left join ousr e on e.userid=d.usersign " +
				  "where b.basetype='202' and a.docNum='"+id+"'";
			 v.getOd().updatetable(hql,0);		
	
	 		
	}
	@Override
	public void close(OigeView v)
	{
		
	}
	public OigeView getV() {
		return v;
	}

	public void setV(OigeView v) {
		this.v = v;
	}

	@Override
	public void print(OigeView v) {
		// TODO Auto-generated method stub
		 if(v.getOd1().ds.getCnValue().equals("查询"))
		 {
			 
			 try{   
				 String tb;
			     tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+"d:\\ncr\\生产发货.rpt"+"+"+"DH"+"+"+v.getTxt_docn().getText()+"+"+"Printscth";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();
	           // System.out.println(str);  //输出得到的字符串。检查结果是否正确。
	             } catch (Exception ex) {    
	                ex.printStackTrace();    
	             }    
	       }
	}

	@Override
	public void ctarget(OigeView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}


}
