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

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.wrapper.com.ComFailException;

import erp.ws.aop.permission.PermissionDeniedException;
import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.abs.SninAbsDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;

import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.client.swing.dao.impl.SninAdvSN;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.Dra2Doc;
import erp.ws.sbo.utils.SNL;
import erp.ws.sbo.utils.Snprint;


public class SninDoc extends SninAbsDoc{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private SninView v;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private String hql,hql1,yon;
	private Object[][] ob;
	//private Object[] ob1;
	private IDocuments oskt;
	private SNL snl=new SNL();	
	private SninAdvSN advsn=new SninAdvSN();

	public SninDoc(){
	
	}
	
	@Override
	public void create(SninView v){
		// TODO Auto-generated method stub	
		if(!advsn.verification(v))
		{
			return;
		}
		if(v.getOd1().ds.getCnValue().equals("修改"))
		{
			this.delete(Integer.valueOf(v.getTxt_docn().getText()));
		}
		try{
		this.v=v;		
			try {
				oskt=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oStockTransferDraft);				
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}			
			oskt.setDocDate(new Date());		
			oskt.setDocObjectCode(67);
			//oskt.set(Integer.valueOf(((ComboBoxItem)v.getCom_plist().getSelectedItem()).getValue().toString()));
		   //oskt.set(((ComboBoxItem)v.getCom_whs().getSelectedItem()).getValue().toString());
		    oskt.getUserFields().getFields().item("U_Czy").setValue(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());		
		    oskt.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);		
		    for(int i=0;i<v.getOd().dataSet.length;i++)
			{ 
		    	try{
					if(v.getOd().getValuethrheader(i,"物料代码")==null||(v.getOd().getValuethrheader(i,"物料代码")!=null&&v.getOd().getValuethrheader(i,"物料代码").toString().equals("")))
					{
						continue;
					}
					if((v.getOd().getValuethrheader(i,"物料代码")!=null&&!v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
					&&Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())==0)
					{
						continue;
					}
												
					oskt.getLines().setItemCode(v.getOd().getValuethrheader(i, "物料代码").toString());
					oskt.getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "仓库").toString());					
					oskt.getLines().getUserFields().getFields().item("U_Gs").setValue(v.getOd().getValuethrheader(i, "实际收货个数").toString());		
					oskt.getLines().getUserFields().getFields().item("U_Mtmd").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "米段").toString()));				
					oskt.getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "实际库存数量").toString()));
					oskt.getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "标准库存数量").toString()));
					oskt.getLines().getUserFields().getFields().item("U_Scwc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "误差").toString()));	
					oskt.getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "是否米段线")==null||v.getOd().getValuethrheader(i, "是否米段线").toString()=="")?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "是否米段线")).getValue().toString());	
					//oskt.getLines().getUserFields().getFields().item("U_Zc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "总长").toString()));	
					oskt.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "单位数量").toString()));	
					oskt.getLines().getUserFields().getFields().item("U_SNid").setValue(Integer.valueOf( v.getOd().getValuethrheader(i, "序号").toString()));	
					
					oskt.getLines().add();	
				}
			    catch(NullPointerException e0){				
					 JOptionPane.showMessageDialog(null,"第"+Integer.valueOf(i+1).toString()+"行数量或者金额或者是否显示或其它字段输入不合法，请更正或者删除这一行");
					 return;
			    }
		    	catch(ComFailException e0)
		    	{
		    		System.out.println("生产收货或者库存转储sbo接口出错");
		    	}
		    	catch(ArrayIndexOutOfBoundsException e0)
		    	{
		    		
		    	}
		    	
			}			
			appMain.lRetCode=oskt.add();
						
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
				 oskt.release();
				 String ndocid=appMain.oCompany.getNewObjectKey().toString();
				 hql = "update  odrf set groupnum='"+((ComboBoxItem)v.getCom_plist().getSelectedItem()).getValue().toString()+"' " +
				 		"where docentry='"+appMain.oCompany.getNewObjectKey()+"'";
				 dbu.exeSql(hql);
				 hql = "update  odrf set filler='"+((ComboBoxItem)v.getCom_whs().getSelectedItem()).getValue().toString()+"' " +
					 	"where docentry='"+appMain.oCompany.getNewObjectKey()+"'";
				 dbu.exeSql(hql);
				 hql="select U_enable from dbo.[@SMS] where code='CSTSN' ";
				 ob=appMain.lt.sqlclob(hql,0,1);
				 if(ob==null||(ob!=null&&ob.length==0))
				 {										
					 return;
				 }
				 if(ob[0][0].toString().equals("Y"))
				 {
					snl=new SNL(v.getDsv());		
				    snl.createsdra(v.getDsv(),true,false,"","I","",appMain.oCompany.getNewObjectKey().toString());
				    Snprint snsp=new Snprint(v);
					JOptionPane.showMessageDialog(null,"打印草稿单"+ndocid);
					snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0",ndocid,v);	 
			     }
				
				//生成序列号组合单据	 
				/*if(v.getCom_ifseal().getSelectedItem().toString().equals("是"))
				{						    					
	                String temp=v.getTxt_MNo().getText() +v.getTxt_docn().getText();
	                for(int l=0;l<18-(v.getTxt_MNo().getText() +v.getTxt_docn().getText()).length();l++)
	                {
	                	temp=temp+"0";
	                }
					ob1=new Object[2];
					ob1[0]=temp;
					ob1[1]=v.getJta_memo().getText();
					if(v.getJta_SN().getText()==null||v.getJta_SN().getText().equals(""))
					{
						return;
					}
					if(!v.getJta_SN().getText().contains(",")&&v.getJta_SN().getText()!=null&&!v.getJta_SN().getText().equals(""))
					{
						ob=new Object[1][2];
						ob[0][0]=v.getJta_SN().getText();
						ob[0][1]="";
					}
					else{
						String[] sns=v.getJta_SN().getText().split(",");
						ob=new Object[sns.length][2];
						if(sns!=null)
						{
							 for(int k=0;k<sns.length;k++)
							 {
								 if(sns[k]!=null&&!sns[k].toString().equals(""))
								 {
									 ob[k][0]=sns[k].toString();
									 ob[k][1]="";		
								 }						
							 }
						}
					}
					try {
						 snl=new SNL();
						 if(snl.createPSN_afs(v,ob1[0].toString()))
						 {													 
							 appMain.psn.add(appMain.psn.setPasn(ob1, ob));
							 hql = "update  [@pasn] set status='C' " +
								   "where U_SN='"+ob1[0].toString()+"'";
							 dbu.exeSql(hql);	
							 try{
					             Snprint snsp=new Snprint(v);
					             snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", ob1[0].toString());	        
					             snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", ob1[0].toString());	        							       
							 }
					           catch(NoClassDefFoundError e){
					        	  e.printStackTrace();
					           }
					           catch(UnsatisfiedLinkError e){
					        	   e.printStackTrace();
					           }
						 }
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			    }
				if(!v.getCom_ifseal().getSelectedItem().toString().equals("是"))
				{
					int i=JOptionPane.showConfirmDialog(null, "将不生成大序列号，是否继续?点否或者取消或者关闭提示框还会生成大序列号");
					if(i==0)
					{
						v.getOd1().setDs(docTitleStatus.add);
						v.getOd1().setDocTitleStatus(v);
						v.getOd().setDocLineStatus(docLineStatus.tran);
						v.getOd().setGridStatus(docLineStatus.add);
						dmv.setadd();	
						return;	  
					}
				
	                String temp=v.getTxt_MNo().getText() +v.getTxt_docn().getText();
	                for(int l=0;l<18-(v.getTxt_MNo().getText() +v.getTxt_docn().getText()).length();l++)
	                {
	                	temp=temp+"0";
	                }
					ob1=new Object[2];
					ob1[0]=temp;
					ob1[1]=v.getJta_memo().getText();
					if(v.getJta_SN().getText()==null||v.getJta_SN().getText().equals(""))
					{
						return;
					}
					if(!v.getJta_SN().getText().contains(",")&&v.getJta_SN().getText()!=null&&!v.getJta_SN().getText().equals(""))
					{
						ob=new Object[1][2];
						ob[0][0]=v.getJta_SN().getText();
						ob[0][1]="";
					}
					else{
						String[] sns=v.getJta_SN().getText().split(",");
						ob=new Object[sns.length][2];
						if(sns!=null)
						{
							 for(int k=0;k<sns.length;k++)
							 {
								 if(sns[k]!=null&&!sns[k].toString().equals(""))
								 {
									 ob[k][0]=sns[k].toString();
									 ob[k][1]="";		
								 }						
							 }
						}
					}
					try {
						 snl=new SNL();
						 if(snl.createPSN_afs(v,ob1[0].toString()))
						 {																				 						
							 appMain.psn.add(appMain.psn.setPasn(ob1, ob));
							 hql = "update  [@pasn] set status='C' " +
								    "where U_SN='"+ob1[0].toString()+"'";
							 dbu.exeSql(hql);
							 try{
					             Snprint snsp=new Snprint(v);
					             snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", ob1[0].toString());	        
					             snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", ob1[0].toString());	        							       									
							 }
					           catch(NoClassDefFoundError e){
					        	  e.printStackTrace();
					           }
					           catch(UnsatisfiedLinkError e){
					        	   e.printStackTrace();
					           }
						 }
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			    }*/	
				v.getOd1().setDs(docTitleStatus.add);
				v.getOd1().setDocTitleStatus(v);
				v.getOd().setDocLineStatus(docLineStatus.tran);
				v.getOd().setGridStatus(docLineStatus.add);
				dmv.setadd();						
			}	
		}
		catch(NullPointerException e0)
		{
			System.out.println("生产收货或者库存转储找不到指针");
			e0.printStackTrace();
		}
		catch(ComFailException e0)
		{
			System.out.println("生产收货或者库存转储sbo接口出错");	
			e0.printStackTrace();
		}		
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(SninView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			oskt=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);				
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}		
		if(oskt.getByKey(id))
		{
			if(oskt.remove()!=0)
			{
				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode(); 
				JOptionPane.showMessageDialog(null,"删除草稿失败"+appMain.lErrCode + " " + appMain.sErrMsg);
				return;
			}
			else{
				JOptionPane.showMessageDialog(null,"删除成功");
				 hql = "delete from dbo.[@desn] " +
						"where Ifdraft=1 and ObjType='67' and docentry='"+String.valueOf(id)+"'";
				 dbu.exeSql(hql);
			}
		}
	}

	@Override
	public Object[][] getDocLists(ParaList p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getfirst() {
		// TODO Auto-generated method stub				
		  hql = "SELECT min(a.docentry) from odrf a inner join drf1 b on a.docentry=b.docentry where b.objtype='67'";
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
		  hql = "SELECT  max(a.docentry)  from odrf a inner join drf1 b on a.docentry=b.docentry where a.objtype='67' and a.docentry<'"+id+"'";
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
		  hql = "SELECT  a.docentry  from odrf a inner join drf1 b on a.docentry=b.docentry where a.objtype='67' and a.docentry>'"+id+"'";
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
		  hql = "SELECT max(a.docentry) from odrf a inner join drf1 b on a.docentry=b.docentry where a.objtype='67'";
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
	public void setValues(SninView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}				 
		hql="select a.docEntry,a.userSign,c.U_Name,a.filler,b.whsname,a.groupnum,d.listname," +
			"date=convert(nvarchar(10),a.docdate,23),a.docstatus  " +
			" from odrf a " +
			"inner join owhs b on b.whscode=a.filler " +
			"inner join ousr c on c.userid=a.usersign " +
			"left join opln d on d.listnum=a.groupnum " +
			"where a.objtype='67' and a.docentry='"+id+"'";
		 ob = appMain.lt.sqlclob(hql,0,1); 
		 if(ob==null||ob.length==0)
		 {
			 return;
		 }
	     v.getOd1().setDs(docTitleStatus.addp);
	     v.getOd1().setDocTitleStatus(v);
	     v.getCom_users().setEnabled(true); 
		 v.getCom_users().setEditable(true); 
		 v.getTxt_status().setEditable(true); 
	     ComboBoxItem  Cbi=new ComboBoxItem(ob[0][1].toString(),ob[0][2].toString());
	     v.getCom_whs().setEnabled(true);
	     v.getCom_whs().setEditable(true);
	     v.getCom_plist().setEnabled(true);
	     v.getCom_plist().setEditable(true);
		 v.getCom_users().setSelectedItem(Cbi); 
		 v.getCom_whs().setSelectedItem(new ComboBoxItem(ob[0][3].toString(),ob[0][4].toString()));
		 v.getCom_plist().setSelectedItem(new ComboBoxItem(ob[0][5].toString(),ob[0][6].toString()));
		 v.getTxt_docn().setText(id.toString());
		 v.getTxt_date().setText(ob[0][7].toString());
		 v.getTxt_status().setText(ob[0][8].toString().equals("C")?"已清":"未清");
		 JOptionPane.showMessageDialog(null,v.getJt1().getSelectedRow());
		 hql="select id=0, b.u_snid,0,b.itemcode,c.itemname,b.u_ymd,b.u_mtmd,c.salunitmsr," +
		 	 "unitQty=isnull(b.U_mtmd,0)*isnull(c.u_mtzl,0)/isnull(c.u_mtmd,0),0," +
		 	 "gs=isnull(b.u_gs,0),b.unitmsr,b.u_zz,b.quantity,b.u_scwc," +
		 	 "'',b.whscode,'','','','','' from odrf a " +
		 	 "inner join drf1 b on a.docentry=b.docentry " +
		 	 "inner join oitm c on b.itemcode=c.itemcode " +
			  "where  a.objtype='67' and a.docentry='"+id+"'";
		 System.out.println(hql);
		 v.getOd().updatetable(hql,0);		
		 JOptionPane.showMessageDialog(null,v.getJt1().getSelectedRow());
		 v.getCom_users().setEditable(false); 
		 v.getCom_users().setEnabled(false); 
		 v.getCom_whs().setEditable(false);
	 	 v.getCom_whs().setEnabled(false);	
	 	 v.getCom_plist().setEnabled(false);
	     v.getCom_plist().setEditable(false);
	 	
		
		 hql = "select U_enable from [@SMS] where code='CSTSN'";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||ob.length==0)
		 {										
			 return;
		 }
		 if(ob[0][0].toString().equals("Y"))
		 {
			 hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight," +
			 	"a.direction,a.ifpasn,a.pasn,a.warehouse,a.cardcode,a.memo,a.workcenter," +
				"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
		   		"from [@desn] a " +
		   		"inner join drf1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
		   		"inner join odrf c on b.docentry=c.docentry " +
		   		"where c.objtype='67' and c.docEntry='"+v.getTxt_docn().getText().trim()+"' and a.Ifdraft='1' and a.objtype='67'";
				v.getDsv().getOd().updatetable(hql, 0);
				v.getJta_SN().setText("");
				 ob=appMain.lt.sqlclob(hql,0,1000);
				 if(ob!=null&&ob.length!=0)
				 {   String s=new String("");
					 for(int k=0;k<ob.length;k++)
					 {
						s=s+ob[k][5].toString()+",";
					 }
					 v.getJta_SN().setText(s.substring(0,s.length()-1));
				 }				
		 }

		hql="select u_width,u_height,u_codewidth,u_codeheight,u_codetype,u_codegap,u_left," +
			"u_up,u_right,u_down " +
			" from dbo.[@SNPRINTER]  " +
			"where code='1' ";
		 ob = appMain.lt.sqlclob(hql,0,1); 
		 if(ob==null||ob.length==0)
		 {
			 return;
		 }
		 v.getTxt_width().setText(ob[0][0].toString());
		 v.getTxt_height().setText(ob[0][1].toString());
		 v.getTxt_codewidth().setText(ob[0][2].toString());
		 v.getTxt_codeheight().setText(ob[0][3].toString());
		 v.getTxt_codetype().setText(ob[0][4].toString());
		 v.getTxt_codegap().setText(ob[0][5].toString());
		 v.getTxt_left().setText(ob[0][6].toString());
		 v.getTxt_up().setText(ob[0][7].toString());
		 v.getTxt_right().setText(ob[0][8].toString());
		 v.getTxt_down().setText(ob[0][9].toString());		
	}
	@Override
	public void close(SninView v)
	{
		
	}
	public SninView getV() {
		return v;
	}

	public void setV(SninView v) {
		this.v = v;
	}

	@Override
	public void print(SninView v) {
		// TODO Auto-generated method stub
		 if(v.getOd1().ds.getCnValue().equals("查询"))
		 {
			 
			 try{   
				 String tb;				
			     tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+"d:\\ncr\\库存转储草稿.rpt"+"+"+"DH"+"+"+v.getTxt_docn().getText()+"+"+"Printkczc";
				 
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
	public void ctarget(SninView v, Integer dh) {
		// TODO Auto-generated method stub
		 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
	 	 ob = appMain.lt.sqlclob(hql,0,1);
		 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
	 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
	 	  	 "and b.name='生成库存转储单' and a.enable='1'";
	 	 ob = appMain.lt.sqlclob(hql,0,1);
         if(ob==null||ob.length==0)
         {
    	   JOptionPane.showMessageDialog(null, "无此权限");
    	   return;
         }
         else{        	       	 	
        	 Dra2Doc dd=new Dra2Doc();	
		     dd.createoskt(dh,v);		
		     v.getTxt_status().setText("已清");
    		 JOptionPane.showMessageDialog(null,"库存转储单"+appMain.oCompany.getNewObjectKey()+","+"添加成功!");	      	 
         }
	}

	@Override
	public void snverification(SninView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(SninView v) throws PermissionDeniedException{
		// TODO Auto-generated method stub
		v.getDsv().getOd().setGridStatus(docLineStatus.add);
		v.getJta_SN().setText("");
	    // something about doctitle
		v.getOd1().setDs(docTitleStatus.add);
		v.getOd1().setDocTitleStatus(v);
		//something about docline
		v.getOd().setDocLineStatus(docLineStatus.query);
		v.getOd().setGridStatus(docLineStatus.add);
		
		 hql="select u_enable from [@sms] where code='CKCZY'";
		 yon=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();				
								 
		 //绑定仓库
		 hql= "select p.whsCode+','+p.whsName from Owhs as p " +
			  " where (p.whsCode like :str1 or p.whsName like :str2) ";
		 if(yon.equals("Y"))
		 {
		    hql+=" and p.WhsCode in " +
			 	  "(select U_Dsck from dbo.[@CZYCK] where " +
			 	  "U_Usid=(select distinct branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='Z')";
		 }
		  hql1="select p.whsCode from Owhs as p " +
					" where p.whsCode=:str1 ";
		 if(yon.equals("Y"))
		 {
			hql1+=" and p.WhsCode in " +
			 	"(select U_Dsck from dbo.[@CZYCK] where " +
			 	"U_Usid=(select distinct branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='Z')";
		 }
		 v.getOd().setUpSportColumn(v.getJt(), v.getJt().getColumnModel().getColumn(v.getOd().getcolumnindex("仓库")), hql,hql1);		    
		 
		dmv.setadd();
	}
}
