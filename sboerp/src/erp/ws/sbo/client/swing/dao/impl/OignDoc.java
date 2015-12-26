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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.IStockTransfer;
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
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.Dra2Doc;
import erp.ws.sbo.utils.MdbHibernateUtils;
import erp.ws.sbo.utils.SNL;


public class OignDoc implements IDoc<OignView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private OignView v;
	private String hql;
	private Object[][] ob;
	private IDocuments oidoc;
	private IStockTransfer oskt;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private SNL snl=new SNL();	
	public OignDoc(){
	
	}
	
	@Override
	public void create(OignView v){
		// TODO Auto-generated method stub	
		try{
		this.v=v;	
		if(Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString())==0)
		{
			try {
				//oidoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInventoryGenEntry);
				oidoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}		
			oidoc.setDocDate(new Date());	
			oidoc.setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oInventoryGenEntry);
			oidoc.getUserFields().getFields().item("U_Czy").setValue(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());
			oidoc.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
			for(int i=0;i<v.getOd().dataSet.length;i++)
			{
				try{
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
					if(v.getOd().getValuethrheader(i,"实际收货个数").toString().equals("0")||new BigDecimal(v.getOd().getValuethrheader(i,"实际收货个数").toString()).compareTo(new BigDecimal(v.getOd().getValuethrheader(i,"计划生产个数").toString()))>0)
					{
						JOptionPane.showMessageDialog(null,"实际收货个数要大于0并且不大于计划生产个数");
						return;
			        	
					}
					//oidoc.getLines().setItemCode(v.getOd().getValuethrheader(i, "物料代码").toString());
					oidoc.getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "仓库").toString());
					oidoc.getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "实际库存数量").toString()));
					//oidoc.getLines().setBaseType(SBOCOMConstants.bo);202不合法
					hql = "SELECT a.docEntry from OWOR a where a.docNum='"+ v.getOd().getValuethrheader(i, "生产订单号").toString()+"'";
			        ob = appMain.lt.sqlclob(hql,0,1);
			        if(ob==null||ob.length==0)
			        {
			        	 JOptionPane.showMessageDialog(null,v.getOd().getValuethrheader(i, "生产订单号").toString()+"生产订单号不正确");
			        	 return;
			        }		           
			        JOptionPane.showMessageDialog(null,ob[0][0].toString());
			        oidoc.getLines().setBaseEntry(Integer.valueOf(ob[0][0].toString()));
					oidoc.getLines().setTransactionType(SBOCOMConstants.BoTransactionTypeEnum_botrntComplete);
					oidoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "米段").toString()));
					oidoc.getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "标准库存数量").toString()));
					try{
						oidoc.getLines().getUserFields().getFields().item("U_Gs").setValue(v.getOd().getValuethrheader(i, "实际收货个数").toString());
						}
						catch(ComFailException e0)
						{
							JOptionPane.showMessageDialog(null,"实际收货个数请输入整数");
							System.out.println("生产收货或者库存转储sbo接口出错");
						}
					
					oidoc.getLines().getUserFields().getFields().item("U_Scwc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "误差").toString()));
					oidoc.getLines().getUserFields().getFields().item("U_Ckck").setValue( v.getOd().getValuethrheader(i, "出库仓库")==null?"":v.getOd().getValuethrheader(i, "出库仓库").toString());
					oidoc.getLines().getUserFields().getFields().item("U_Ymd").setValue(v.getOd().getValuethrheader(i, "是否米段线").toString());
					oidoc.getLines().getUserFields().getFields().item("U_SNid").setValue(Integer.valueOf( v.getOd().getValuethrheader(i, "序号").toString()));					
					oidoc.getLines().add();		
				}
				catch(NullPointerException e0){				
					 JOptionPane.showMessageDialog(null,"第"+Integer.valueOf(i+1).toString()+"行数量或者金额或其它字段输入不合法，请更正或者删除这一行");
					 return;
				}
				catch(ComFailException e0)
				{					
					e0.printStackTrace();
					System.out.println("生产收货或者库存转储sbo接口出错1");
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
			{		
				oidoc.release();
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
				    Session session1 = MdbHibernateUtils.getSession();
   				    Transaction  t1 = session1.beginTransaction();{
   			        try {
   			    	   @SuppressWarnings("deprecation")
					   Connection con = session1.connection(); 
   			    	   Statement sta;								
							sta = con.createStatement();
//							hql = "update a  set a.basetype='202' from drf1 a inner join "
//									+ " odrf b "
//									+ "on b.docentry=a.docentry where a.docentry='"+appMain.oCompany.getNewObjectKey()+"'";								
//							sta.execute(hql);	
							hql = "update a  set a.U_cqty=b.gs from owor a inner join "
								+ " (select baseentry,gs=sum(U_Gs) from drf1 where basetype='202' group by baseentry) b "
								+ "on b.baseentry=a.docentry where a.docentry='"+ob[0][0].toString()+"'";								
							sta.execute(hql);							
   				            t1.commit();
	   				         hql="select U_enable from dbo.[@SMS] where code='OIGNSN' ";
							 ob=appMain.lt.sqlclob(hql,0,1);
							 if(ob==null||(ob!=null&&ob.length==0))
							 {										
								 return;
							 }
							 if(ob[0][0].toString().equals("Y"))
							 {
								snl=new SNL(v.getDsv());		
							    snl.createsdra(v.getDsv(),true,false,"","I","",appMain.oCompany.getNewObjectKey().toString());
						     }
   			       }catch (HibernateException e1) {
   			    	   
   			           e1.printStackTrace();
   			           t1.rollback();
   			         } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					  } finally {
   			       	  MdbHibernateUtils.closeSession(session1);
   			         } 
   			       }
			    }								
				v.getOd1().setDs(docTitleStatus.add);
				v.getOd1().setDocTitleStatus(v);
				v.getOd().setDocLineStatus(docLineStatus.oign);
				v.getOd().setGridStatus(docLineStatus.add);
				dmv.setadd();					
			}			
		}
		else{

			try {
				oskt=SBOCOMUtil.newStockTransfer(appMain.oCompany);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
		
			oskt.setDocDate(new Date());		
			oskt.setPriceList(Integer.valueOf(((ComboBoxItem)v.getCom_plist().getSelectedItem()).getValue().toString()));
		    oskt.setFromWarehouse(((ComboBoxItem)v.getCom_whs().getSelectedItem()).getValue().toString());
		    oskt.getUserFields().getFields().item("U_Czy").setValue(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());			
		    oskt.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		    for(int i=0;i<v.getOd().dataSet.length;i++)
			{ 
		    	try{
					if(v.getOd().getValuethrheader(i,"物料代码")==null||v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
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
		    		System.out.println("生产收货或者库存转储sbo接口出错2");
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
				/*SNL snl=new SNL(v.getDsv());		
			    snl.createdoc(v.getDsv(),true,"",appMain.oCompany.getNewObjectKey().toString());
			    */
				v.getOd1().setDs(docTitleStatus.add);
				v.getOd1().setDocTitleStatus(v);
				v.getOd().setDocLineStatus(docLineStatus.tran);
				v.getOd().setGridStatus(docLineStatus.add);
				dmv.setadd();						
			}
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
			
		}
		
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(OignView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//暂时没有删除功能
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
		
		hql = "SELECT min(a.docentry) from odrf a inner join drf1 b on a.docentry=b.docentry where b.objtype='59'";
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
		 hql = "SELECT  max(a.docentry)  from odrf a inner join drf1 b on a.docentry=b.docentry where a.objtype='59' and a.docentry<'"+id+"'";
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
		hql = "SELECT  a.docentry  from odrf a inner join drf1 b on a.docentry=b.docentry where a.objtype='59' and a.docentry>'"+id+"'";
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
		  hql = "SELECT max(a.docentry) from odrf a inner join drf1 b on a.docentry=b.docentry where a.objtype='59'";
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
	public void setValues(OignView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}		
		if(dod.equals("生产收货"))
		{			
			hql="select a.docNum,a.usersign,c.U_Name,a.docdate,a.docEntry from oign a " +
				"inner join ign1 b on a.docentry=b.docentry " +
				"inner join ousr c on a.usersign=c.userid " +
				"where b.basetype='202' and a.docEntry='"+id+"'";
			 ob = appMain.lt.sqlclob(hql,0,1); 
			 if(ob==null||ob.length==0)
			 {
				 return;
			 }
		     v.getOd1().setDs(docTitleStatus.addp);
		     v.getOd1().setDocTitleStatus(v);
		     v.getCom_users().setEnabled(true); 
			 v.getCom_type().setEnabled(true); 
			 v.getCom_snware().setEnabled(true); 
			 v.getCom_users().setEditable(true); 
			 v.getCom_type().setEditable(true); 
			 v.getCom_snware().setEditable(true); 
			 v.getTxt_status().setEditable(true); 
		     ComboBoxItem  Cbi=new ComboBoxItem(ob[0][1].toString(),ob[0][2].toString());		     
			 v.getCom_users().setSelectedItem(Cbi); 
			 v.getCom_type().setSelectedItem(new ComboBoxItem(0,"生产收货"));		
			 v.getTxt_docn().setText(id.toString());
			 v.getTxt_docnid().setText(ob[0][4].toString());
			 v.getTxt_date().setText(ob[0][3].toString());
			 v.getTxt_status().setText("生产收货");
			 v.getTxt_status().setEditable(false); 
			 hql="select 0, b.u_snid,d.docNum,c.itemcode,c.itemname,b.u_Ymd,b.u_mtmd,c.salunitmsr," +
			 	 "unitQty=convert(decimal(18,3),isnull(d.U_length,0)*isnull(c.u_mtzl,0)/isnull(c.u_mtmd,0))," +
			 	 "d.u_qty,gs=isnull(b.u_gs,0),b.unitmsr,b.u_zz,b.quantity,b.u_scwc," +
			 	 "'',b.whscode,b.u_ckck,d.plannedqty,d.cmpltqty,convert(nvarchar(10),d.duedate,23),e.U_name " +
			 	 "from oign a " +
			 	 "inner join ign1 b on a.docentry=b.docentry " +
			 	 "inner join oitm c on b.itemcode=c.itemcode " +
			 	 "inner join owor d on d.docentry=b.baseEntry " +
			 	 "left join ousr e on e.userid=d.usersign " +
				 "where b.basetype='202' and a.docEntry='"+id+"'";
			 v.getOd().updatetable(hql,0);		
			 v.getCom_plist().setVisible(false);
			 v.getCom_whs().setVisible(false);
			 hql = "select U_enable from [@SMS] where code='OIGNSN'";
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
			   		"inner join ign1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
			   		"inner join oign c on b.docentry=c.docentry " +
			   		"where c.docEntry='"+v.getTxt_docnid().getText().trim()+"' and a.Ifdraft='0' and a.objtype='59' and b.basetype='202'";
					v.getDsv().getOd().updatetable(hql, 0);
					v.getJta_SN().setText("");
					 ob=appMain.lt.sqlclob(hql,0,1000);
					 if(ob!=null&&ob.length!=0)
					 {   StringBuffer s=new StringBuffer();
						 for(int k=0;k<ob.length;k++)
						 {
							s=s.append(ob[k][5].toString()+",");
						 }
						 v.getJta_SN().setText(s.toString().substring(0,s.length()-1));
					 }			
			 }
		}
		else if(dod.equals("收货草稿")){
			hql="select a.docNum,a.usersign,c.U_Name,a.docdate,a.docEntry from odrf a " +
				"inner join drf1 b on a.docentry=b.docentry " +
				"inner join ousr c on a.usersign=c.userid " +
				"where b.basetype='202' and a.docEntry='"+id+"'";
				 ob = appMain.lt.sqlclob(hql,0,1); 
				 if(ob==null||ob.length==0)
				 {
					 return;
				 }
			     v.getOd1().setDs(docTitleStatus.addp);
			     v.getOd1().setDocTitleStatus(v);
			     v.getCom_users().setEnabled(true); 
				 v.getCom_type().setEnabled(true); 
				 v.getCom_snware().setEnabled(true); 
				 v.getCom_users().setEditable(true); 
				 v.getCom_type().setEditable(true); 
				 v.getCom_snware().setEditable(true); 
				 v.getTxt_status().setEditable(true); 
			     ComboBoxItem  Cbi=new ComboBoxItem(ob[0][1].toString(),ob[0][2].toString());		     
				 v.getCom_users().setSelectedItem(Cbi); 
				 v.getCom_type().setSelectedItem(new ComboBoxItem(0,"生产收货"));		
				 v.getTxt_docn().setText(id.toString());
				 v.getTxt_docnid().setText(ob[0][4].toString());
				 v.getTxt_date().setText(ob[0][3].toString());
				 v.getTxt_status().setText("收货草稿");
				 v.getTxt_status().setEditable(false); 
				 hql="select 0, b.u_snid,d.docEntry,c.itemcode,c.itemname,b.u_Ymd,b.u_mtmd,c.salunitmsr," +
				 	 "unitQty=convert(decimal(18,3),isnull(d.U_length,0)*isnull(c.u_mtzl,0)/isnull(c.u_mtmd,0))," +
				 	 "d.u_qty,gs=isnull(b.u_gs,0),b.unitmsr,b.u_zz,b.quantity,b.u_scwc," +
				 	 "'',b.whscode,b.u_ckck,d.plannedqty,d.cmpltqty,convert(nvarchar(10),d.duedate,23),e.U_name " +
				 	 "from odrf a " +
				 	 "inner join drf1 b on a.docentry=b.docentry " +
				 	 "inner join oitm c on b.itemcode=c.itemcode " +
				 	 "inner join owor d on d.docentry=b.baseEntry " +
				 	 "left join ousr e on e.userid=d.usersign " +
					 "where b.basetype='202' and a.docEntry='"+id+"'";
				 v.getOd().updatetable(hql,0);		
				 v.getCom_plist().setVisible(false);
				 v.getCom_whs().setVisible(false);
				 hql = "select U_enable from [@SMS] where code='OIGNSN'";
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
				   		"inner join drf1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
				   		"inner join odrf c on b.docentry=c.docentry " +
				   		"where c.docEntry='"+v.getTxt_docnid().getText().trim()+"' and a.Ifdraft='1' and a.objtype='59' and b.basetype='202'";
						v.getDsv().getOd().updatetable(hql, 0);
						v.getJta_SN().setText("");
						 ob=appMain.lt.sqlclob(hql,0,1000);
						 if(ob!=null&&ob.length!=0)
						 {   StringBuffer s=new StringBuffer();
							 for(int k=0;k<ob.length;k++)
							 {
								s=s.append(ob[k][5].toString()+",");
							 }
							 v.getJta_SN().setText(s.toString().substring(0,s.length()-1));
						 }			
				 }
		}
		else{
			hql="select a.docNum,a.userSign,c.U_Name,a.filler,b.whsname,a.groupnum,d.listname," +
				"date=convert(nvarchar(10),a.docdate,23),a.docEntry " +
				" from owtr a " +
				"inner join owhs b on b.whscode=a.filler " +
				"inner join ousr c on c.userid=a.usersign " +
				"left join opln d on d.listnum=a.groupnum " +
				"where a.docEntry='"+id+"'";
			 ob = appMain.lt.sqlclob(hql,0,1); 
			 if(ob==null||ob.length==0)
			 {
				 return;
			 }
		     v.getOd1().setDs(docTitleStatus.addp);
		     v.getOd1().setDocTitleStatus(v);
		     v.getCom_users().setEnabled(true); 
			 v.getCom_type().setEnabled(true); 
			 v.getCom_users().setEditable(true); 
			 v.getCom_type().setEditable(true); 
		     ComboBoxItem  Cbi=new ComboBoxItem(ob[0][1].toString(),ob[0][2].toString());
		     v.getCom_whs().setEnabled(true);
		     v.getCom_whs().setEditable(true);
		     v.getCom_whs().setVisible(true);
		     v.getCom_plist().setEnabled(true);
		     v.getCom_plist().setEditable(true);
		     v.getCom_plist().setVisible(true);
			 v.getCom_users().setSelectedItem(Cbi); 
			 v.getTxt_status().setEditable(true); 
			 v.getCom_type().setSelectedItem(new ComboBoxItem(1,"库存转储"));
			 v.getCom_whs().setSelectedItem(new ComboBoxItem(ob[0][3].toString(),ob[0][4].toString()));
			 v.getCom_plist().setSelectedItem(new ComboBoxItem(ob[0][5].toString(),ob[0][6].toString()));
			 v.getTxt_docn().setText(id.toString());
			 v.getTxt_docnid().setText(ob[0][8].toString());
			 v.getTxt_date().setText(ob[0][7].toString());
			 v.getTxt_status().setText("库存转储");
			 v.getTxt_status().setEditable(false); 
			 hql="select 0, b.u_snid,0,b.itemcode,c.itemname,b.u_ymd,b.u_mtmd,c.salunitmsr," +
			 	 "unitQty=isnull(b.U_mtmd,0)*isnull(c.u_mtzl,0)/isnull(c.u_mtmd,0)," +
			 	 "0,gs=isnull(b.u_gs,0),b.unitmsr,b.u_zz,b.quantity,b.u_scwc," +
			 	 "'',b.whscode,'','','','','' from owtr a " +
			 	 "inner join wtr1 b on a.docentry=b.docentry " +
			 	 "inner join oitm c on b.itemcode=c.itemcode " +
				  "where  a.docEntry='"+id+"'";
			 v.getOd().updatetable(hql,0);		
			 hql = "select U_enable from [@SMS] where code='OIGNSN'";
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
			   		"inner join wtr1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
			   		"inner join owtr c on b.docentry=c.docentry " +
			   		"where c.docEntry='"+v.getTxt_docnid().getText().trim()+"' and a.Ifdraft='0' and a.objtype='59'";
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
		}
		 v.getCom_users().setEditable(false); 
		 v.getCom_type().setEditable(false); 
		 v.getCom_users().setEnabled(false); 
		 v.getCom_type().setEnabled(false); 
		 v.getCom_snware().setEnabled(false); 
		 v.getCom_whs().setEditable(false);
	 	 v.getCom_whs().setEnabled(false);	
	 	 v.getCom_plist().setEnabled(false);
	     v.getCom_plist().setEditable(false);
	     v.getCom_snware().setEditable(false); 
	}
	@Override
	public void close(OignView v)
	{
		
	}
	public OignView getV() {
		return v;
	}

	public void setV(OignView v) {
		this.v = v;
	}

	@Override
	public void print(OignView v) {
		// TODO Auto-generated method stub
		 if(v.getOd1().ds.getCnValue().equals("查询"))
		 {
			 
			 try{   
				 String tb;
				 if(Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString())==0){
				     tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+"d:\\ncr\\生产收货.rpt"+"+"+"DH"+"+"+v.getTxt_docn().getText()+"+"+"Printscsh";
				 }
				 else{
					 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+"d:\\ncr\\库存转储.rpt"+"+"+"DH"+"+"+v.getTxt_docn().getText()+"+"+"Printkczc";
				 }
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
	public void ctarget(OignView v, Integer dh) {
		// TODO Auto-generated method stub     
              	       	 	
        	 Dra2Doc dd=new Dra2Doc();	
		     dd.createoign(dh, v);	
		     v.getTxt_status().setEditable(true);
		     v.getTxt_status().setText("生产收货");
		     v.getTxt_status().setEditable(false);
		
	}

	public DbUtils<?,?> getDbu() {
		return dbu;
	}

	public void setDbu(DbUtils<?,?> dbu) {
		this.dbu = dbu;
	}

	


}
