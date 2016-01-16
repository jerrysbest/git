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
import java.util.Date;

import javax.swing.JOptionPane;

import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.wrapper.com.ComFailException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.OOige.OOigeView;
import erp.ws.sbo.utils.SNL;

public class OOigeDoc implements IDoc<OOigeView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private OOigeView v;
	private String hql;
	private Object[][] ob;
	private IDocuments oidoc;
	private OOigeAdvSN advsn=new OOigeAdvSN();
	public OOigeDoc(){
	
	}
	
	@Override
	public void create(OOigeView v) {
		// TODO Auto-generated method stub			
		hql = "select U_enable from [@SMS] where code='OUTSN'";
		  ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||(ob!=null&&ob.length==0))
		 {
			 JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
			 return;
		 }
		 if(ob[0][0].toString().equals("Y"))
		 {
			 if(!advsn.verification(v))
			{
				return;
			}
		 }
		this.v=v;
		
		try {
			oidoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInventoryGenExit);
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}		
		oidoc.setDocDate(new Date());		
		oidoc.getUserFields().getFields().item("U_Czy").setValue(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());
		oidoc.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
			if(v.getOd().getValuethrheader(i,"物料代码")==null||v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
			{
				continue;
			}	
			if(v.getOd().getValuethrheader(i,"实际收货个数")==null||(v.getOd().getValuethrheader(i,"实际收货个数")!=null&&new BigDecimal(v.getOd().getValuethrheader(i,"实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP))))
			{
				continue;
			}
			try{
			oidoc.getLines().setItemCode(v.getOd().getValuethrheader(i, "物料代码").toString());
			oidoc.getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "仓库").toString());
			oidoc.getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "实际库存数量").toString()));
			oidoc.getLines().setBaseType(-1);
			oidoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "米段").toString()));
			oidoc.getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "标准库存数量").toString()));
			try{
				oidoc.getLines().getUserFields().getFields().item("U_Gs").setValue( v.getOd().getValuethrheader(i, "实际收货个数").toString());
				}
				catch(ComFailException e0)
				{
					JOptionPane.showMessageDialog(null,"实际收货个数请输入整数");
					System.out.println("其它出库单sbo接口出错");
				}
			
			oidoc.getLines().getUserFields().getFields().item("U_Scwc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "误差")==null?"0":v.getOd().getValuethrheader(i, "误差").toString()));			
			oidoc.getLines().getUserFields().getFields().item("U_Ckck").setValue( v.getOd().getValuethrheader(i, "出库仓库")==null?"":v.getOd().getValuethrheader(i, "出库仓库").toString());			
			oidoc.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(Double.valueOf((v.getOd().getValuethrheader(i, "单位数量")==null||v.getOd().getValuethrheader(i, "单位数量").toString()=="")?"1":v.getOd().getValuethrheader(i, "单位数量").toString()));
			oidoc.getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "是否米段线")==null||v.getOd().getValuethrheader(i, "是否米段线").toString()=="")?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "是否米段线")).getValue().toString());
			oidoc.getLines().getUserFields().getFields().item("U_SNid").setValue(Integer.valueOf( v.getOd().getValuethrheader(i, "序号").toString()));					
			
			oidoc.getLines().add();		
			}
			catch(NullPointerException e0){				
				 JOptionPane.showMessageDialog(null,"第"+Integer.valueOf(i+1).toString()+"行数量或者金额输入不合法，请检查");
				 return;
			}
			catch(ComFailException e0)
			{
				System.out.println("其它出库单sbo接口出错");
			}
			
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
		{   hql="select U_enable from dbo.[@SMS] where code='OUTSN' ";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 if(ob==null||ob.length==0)
			 {										
				 return;
			 }
			 if(ob[0][0].toString().equals("Y"))
			 {
				SNL snl=new SNL(v.getDsv());		
			    snl.createsdra(v.getDsv(),false,false,"","O","",appMain.oCompany.getNewObjectKey().toString());
		    }
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
					
		}
		
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(OOigeView v) {
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
		
		  hql = "SELECT min(a.docnum) from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='-1'";
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
		  hql = "SELECT  max(a.docnum)  from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='-1' and a.docnum<'"+id+"'";
          ob = appMain.lt.sqlclob(hql,0,1);
           if(ob==null||ob.length==0)
           {
        	   return this.getlast();
           }
           else{
        	   return Integer.valueOf(ob[0][0].toString());
           }
	}

	@Override
	public Integer getnext(int id) {
		// TODO Auto-generated method stub
		  hql = "SELECT  a.docnum  from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='-1' and a.docnum>'"+id+"'";
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
        	  return this.getfirst();
          }
          else{
       	   return Integer.valueOf(ob[0][0].toString());
          }
	}

	@Override
	public Integer getlast() {
		// TODO Auto-generated method stub
		  hql = "SELECT max(a.docnum) from Oige a inner join ige1 b on a.docentry=b.docentry where b.basetype='-1'";
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
	public void setValues(OOigeView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}
			hql="select a.docnum,a.usersign,c.U_Name from oige a " +
				"inner join ige1 b on a.docentry=b.docentry " +
				"inner join ousr c on a.usersign=c.userid " +
				"where b.basetype='-1' and a.docnum='"+id+"'";
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
			 hql="select 0, b.u_snid,d.docnum,b.itemcode,c.itemname,b.u_Ymd,b.u_mtmd,c.salunitmsr," +
			 	 "b.U_NumPerUnit,0," +
			 	 "gs=isnull(b.u_gs,0),b.unitmsr,b.u_zz,b.quantity,b.u_scwc," +
			 	 "'',b.whscode,b.u_ckck,d.plannedqty,d.cmpltqty,convert(nvarchar(10),d.duedate,23),e.U_name " +
			 	 "from oige a " +
			 	 "inner join ige1 b on a.docentry=b.docentry " +
			 	 "inner join oitm c on b.itemcode=c.itemcode " +
			 	 "left join owor d on d.docnum=b.baseRef " +
			 	 "left join ousr e on e.userid=d.usersign " +
				  "where b.basetype='-1' and a.docnum='"+id+"'";
			 v.getOd().updatetable(hql,0);	
			  //序列号开窗
		        hql = "select U_enable from [@SMS] where code='OUTSN'";
				 ob=appMain.lt.sqlclob(hql,0,1);
				 if(ob==null||ob.length==0)
				 {										
					 return;
				 }
				 if(ob[0][0].toString().equals("Y"))
				 {
					 hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,a.cardcode,a.memo,a.workcenter," +
						"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join ige1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
				   		"inner join oige c on b.docentry=c.docentry " +
				   		"where  c.docnum='"+v.getTxt_docn().getText().trim()+"' and a.objtype='60'";
						v.getDsv().getOd().updatetable(hql, 0);		
						v.getJta_SN().setText("");
						 ob=appMain.lt.sqlclob(hql,0,1000);
						 if(ob!=null&&ob.length!=0)
						 {   String s=new String("");
							 for(int k=0;k<ob.length;k++)
							 {
								s=s+ob[k][5].toString()+",";
							 }
							 appMain.fv.setText(s);
							 v.getJta_SN().setText(s.substring(0,s.length()-1));
						 }			
				 }
	
	 		
	}
	@Override
	public void close(OOigeView v)
	{
		
	}
	public OOigeView getV() {
		return v;
	}

	public void setV(OOigeView v) {
		this.v = v;
	}

	@Override
	public void print(OOigeView v) {
		// TODO Auto-generated method stub
		 if(v.getOd1().ds.getCnValue().equals("查询"))
		 {
			 /*** 暂不需打印
			 try{   
				 String tb;
			     tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+"d:\\ncr\\生产发货.rpt"+"+"+"DH"+"+"+v.getTxt_docn().getText();				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();
	           // System.out.println(str);  //输出得到的字符串。检查结果是否正确。
	             } catch (Exception ex) {    
	                ex.printStackTrace();    
	             } 
	             ***/   
	       }
	}

	@Override
	public void ctarget(OOigeView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(OOigeView v) {
		// TODO Auto-generated method stub
		
	}
}
