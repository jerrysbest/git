package erp.ws.sbo.client.swing.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.wrapper.com.ComFailException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.abs.OrderAbsDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.model.User;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Ordr.OrdrView;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.Dra2Doc;
import erp.ws.sbo.utils.MdbHibernateUtils;

public class OrdrDoc extends OrderAbsDoc{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private OrdrView v;
	private String hql,hql1;
	private Object[][] ob,ob1;
	private CallableStatement cstmt;
	private String result = null;
	private Integer rev=0;
	private int docentry;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private IDocuments idoc; 
	public OrdrDoc(){
	
	}
	
	@Override
	public void create(OrdrView v) {
		// TODO Auto-generated method stub	
		this.v=v;
		//验证
	    if(v.getOd().verification()==false)
	    {
	    	return;
	    }
		try {
			appMain.odelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}	
		try{

		    appMain.odelNotes.setCardCode(((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText());	
		if(v.getTxt_cusn().getText()==null||v.getTxt_cusn().getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"伙伴名称不能为空");
			return;
		}
		appMain.odelNotes.setCardName(v.getTxt_cusn().getText());
		appMain.odelNotes.setContactPersonCode(Integer.valueOf(((ComboBoxItem)v.getTxt_cuslxr().getSelectedItem()).getValue().toString()));
		appMain.odelNotes.setSalesPersonCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));	
		if(v.getTxt_delidate().getValue()==null|| v.getTxt_date().getValue()==null||v.getTxt_duedate().getValue()==null)
		{
			 JOptionPane.showMessageDialog(null,"日期不能为空");
			 return;
		}
		appMain.odelNotes.setDocDate((Date) v.getTxt_date().getValue());
		appMain.odelNotes.setDocDueDate((Date) v.getTxt_duedate().getValue());
		appMain.odelNotes.setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oOrders);
		appMain.odelNotes.setComments(v.getJta_memo().getText());		
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");		
		appMain.odelNotes.getUserFields().getFields().item("U_Dhdate").setValue(f.format((Date)v.getTxt_delidate().getValue()));
		appMain.odelNotes.getUserFields().getFields().item("U_Dq").setValue(v.getTxt_ter().getText());		
		appMain.odelNotes.getUserFields().getFields().item("U_slpcode1").setValue(Integer.valueOf(v.getCom_sales1().getSelectedItem()==null?"-1":((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()));
		appMain.odelNotes.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		}
		catch(ClassCastException e2)
		{
			JOptionPane.showMessageDialog(null,"销售员、销售员1、联系人等选择或者输入不正确，请检查");	
			return;
		}
		
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
			if(v.getOd().getValuethrheader(i,"物料代码")==null||v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
			{
				continue;
			}
			
			if(v.getOd().getValuethrheader(i,"物料代码")!=null&&!v.getOd().getValuethrheader(i,"物料代码").toString().equals("")&&
					(v.getOd().getValuethrheader(i,"数量")==null||new BigDecimal(v.getOd().getValuethrheader(i,"数量").toString()).setScale(2,BigDecimal.ROUND_UP).equals(new BigDecimal("0").setScale(2,BigDecimal.ROUND_UP))
					||v.getOd().getValuethrheader(i,"金额")==null
					||v.getOd().getValuethrheader(i,"包装数量")==null||new BigDecimal(v.getOd().getValuethrheader(i,"包装数量").toString()).setScale(2,BigDecimal.ROUND_UP).equals(new BigDecimal("0").setScale(2,BigDecimal.ROUND_UP))))
			{
				int hh=i+1;					
				JOptionPane.showMessageDialog(null,v.getOd().getValuethrheader(i,"物料代码")+"第"+hh+"行包装数量，数量或者金额输入非法,请检查,金额可以为0不能为空");	
				return;
			}
			try{
			appMain.odelNotes.getLines().setFreeText( v.getOd().getValuethrheader(i, "备注")==null?"":v.getOd().getValuethrheader(i, "备注").toString());
			appMain.odelNotes.getLines().setShipDate((Date) v.getTxt_duedate().getValue());
			appMain.odelNotes.getLines().setItemCode( v.getOd().getValuethrheader(i, "物料代码").toString());
			appMain.odelNotes.getLines().setItemDescription( v.getOd().getValuethrheader(i, "物料描述").toString());
			appMain.odelNotes.getLines().setSalesPersonCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));
			appMain.odelNotes.getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "仓库代码").toString());
			appMain.odelNotes.getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "数量").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(Double.valueOf(v.getOd().getValuethrheader(i, "单位数量").toString()));
			//必须给金额赋值，只给单价赋值不给金额赋值，最后写入的价格是标准价格。
			//appMain.odelNotes.getLines().setDiscountPercent(Double.valueOf("0"));
		    //appMain.odelNotes.getLines().setPrice(Double.valueOf(v.getOd().getValuethrheader(i, "单价").toString()));			
		   	appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "是否显示米段")==null||v.getOd().getValuethrheader(i, "是否显示米段").toString()=="")?"Y":((ComboBoxItem)v.getOd().getValuethrheader(i, "是否显示米段")).getValue().toString());
			appMain.odelNotes.getLines().setLineTotal(Double.valueOf( v.getOd().getValuethrheader(i, "金额").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mtmd").setValue(Double.valueOf((v.getOd().getValuethrheader(i, "物料米段")==null||v.getOd().getValuethrheader(i, "物料米段").toString().equals(""))?"0":v.getOd().getValuethrheader(i, "物料米段").toString()));	
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "总长").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "总重").toString()));
			try{
				appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gs").setValue(v.getOd().getValuethrheader(i, "包装数量").toString());
				}
				catch(ComFailException e0)
				{
					JOptionPane.showMessageDialog(null,"销售数量请输入整数");
					System.out.println("生产发货sbo接口出错");
				}		
			
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gjjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "包装单价").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "米价格")==null?"0": v.getOd().getValuethrheader(i, "米价格").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Tsjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "特殊价格").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Jgf").setValue(Double.valueOf( (v.getOd().getValuethrheader(i, "加工费")==null||v.getOd().getValuethrheader(i, "加工费").toString().equals(""))?"0":v.getOd().getValuethrheader(i, "加工费").toString()));
			appMain.odelNotes.getLines().getUserFields().getFields().item("U_Sfhh").setValue((v.getOd().getValuethrheader(i, "是否换货")==null||v.getOd().getValuethrheader(i, "是否换货").toString().equals(""))?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "是否换货")).getValue().toString());
			
			appMain.odelNotes.getLines().add();	
			}
			catch(NullPointerException e0){				
				 JOptionPane.showMessageDialog(null,"第"+Integer.valueOf(i+1).toString()+"行数量或者金额输入不合法，请检查");
				 return;
			}
			catch(ComFailException e0)
			{
				JOptionPane.showMessageDialog(null,"销售数量请输入整数");
				System.out.println("销售订单sbo接口出错");
			}	
		}	
		appMain.lRetCode=appMain.odelNotes.add();
		if(appMain.lRetCode!=0)
		{ 
			appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
			appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
			
			 if (appMain.lErrCode != -4006) // Incase adding an order failed
             {
				 JOptionPane.showMessageDialog(null,"添加草稿失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
             }
			 else
			 {
				 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
			 }
		}
		else
		{
			 docentry=Integer.valueOf(appMain.oCompany.getNewObjectKey());		
			//with reference of the price and templates of workflow,decide whether or not encroach 
			// workflow .If this draft was encroached workflow,decide which template and how many 
			//stages. All of  this was completed in database which use a store procedures named 
			//"zdy_workflow"
			  Session session = MdbHibernateUtils.getSession();
		      Transaction t = session.beginTransaction();{
		       try {
		    	   @SuppressWarnings("deprecation")
				Connection con = session.connection(); 
		    	
		    	   hql = "{call zdy_workflow(?,?,?,?)}";
			         try {	        	
						cstmt = con.prepareCall(hql);				
						cstmt.setInt(1,docentry);	
						cstmt.setInt(2,17);				
						cstmt.registerOutParameter(3, java.sql.Types.NVARCHAR);		
						cstmt.registerOutParameter(4, java.sql.Types.INTEGER);	
						cstmt.execute();			
						result = cstmt.getString(3);
						rev=cstmt.getInt(4);					    	     
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			         
			        t.commit();	 		    	   
		       }catch (HibernateException e1) {
		           e1.printStackTrace();
		           t.rollback();
		         } finally {
		       	  MdbHibernateUtils.closeSession(session);
		         } 
		      }
		    //cteate order or get into workflow,according to the return values
		      if(result.equals("@WFDST"))
		      {
		    	  if(rev==0)
		    	  {		
		    		  Dra2Doc dd=new Dra2Doc();	
				      dd.createorder(docentry);					    		  
		    		  JOptionPane.showMessageDialog(null,"销售订单"+appMain.oCompany.getNewObjectKey()+","+"添加成功!");		    					    			
		    	  }
		    	  else if(rev==1||rev==2)
		    	  {		    		 		    		 		    		  		    		 
		    		  //set value for wflist1 end
		    		  // add  wflist
		    		  if(appMain.wfd.setWflist("17",String.valueOf(docentry), "@WFDST", rev.shortValue())==null)
		    		  {
		    			  return;
		    		  }
		    		  appMain.wfd.add(appMain.wfd.setWflist("17",String.valueOf(docentry), "@WFDST", rev.shortValue()));
		    		  hql="update odrf set wddstatus='W' where docentry='"+String.valueOf(docentry)+"'";
		 			  dbu.exeSql(hql);
		    		  hql = "SELECT  isnull(max(docentry),0) FROM [@wflist] ";
		              ob = appMain.lt.sqlclob(hql,0,1);
		               if(ob.length==0)
		               {
		             	  return;
		               }
		    		  
		    		  session = MdbHibernateUtils.getSession();
				       t = session.beginTransaction();{
				       try {
				    	   @SuppressWarnings("deprecation")
						Connection con = session.connection(); 
				    	
				    	   hql = "{call zdy_wfmessage(?,?,?,?)}";
					         try {	        	
								cstmt = con.prepareCall(hql);				
								cstmt.setInt(1,Integer.valueOf(ob[0][0].toString()));	
								cstmt.setString(2,"2");	
								cstmt.registerOutParameter(3, java.sql.Types.INTEGER);		
								cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);	
								cstmt.execute();																		    	     
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}			         
					        t.commit();	 		    	   
				       }catch (HibernateException e1) {
				           e1.printStackTrace();
				           t.rollback();
				         } finally {
				       	  MdbHibernateUtils.closeSession(session);
				         } 
				      }				       
		    		  JOptionPane.showMessageDialog(null,"进入"+rev.toString()+"级审批");
		    	  }		    	
		    	  else
		    	  {
		    		  
		    	  }
		    	  
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
	public void update(OrdrView v) {
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
		  hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		  ob1 = appMain.lt.sqlclob(hql1,0,1);
		  hql="select u_enable from dbo.[@sms] where code='DPERM'";
		  ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
        	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
        	  return 0;
          }
          else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
          {
        	  hql = "SELECT min(docNum) from Ordr";
          }
          else{
        	  hql = "SELECT min(docNum) from Ordr where " +
        			" usersign in (select userid from ousr where u_usergroup=" +
       	      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
          }
		 
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
		 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		 ob1 = appMain.lt.sqlclob(hql1,0,1);
		 hql="select u_enable from dbo.[@sms] where code='DPERM'";
		 ob = appMain.lt.sqlclob(hql,0,1);
         if(ob==null||ob.length==0)
         {
       	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
       	  return 0;
         }
         else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
         {
       	  hql = "SELECT max(docNum) from Ordr where docNum<'"+id+"' ";
         }
         else{
       	  hql = "SELECT max(docNum) from Ordr where  docNum<'"+id+"' " +
       			" and usersign in (select userid from ousr where u_usergroup=" +
  	      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
         }
		
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
		 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		  ob1 = appMain.lt.sqlclob(hql1,0,1);
		 hql="select u_enable from dbo.[@sms] where code='DPERM'";
		 ob = appMain.lt.sqlclob(hql,0,1);
        if(ob==null||ob.length==0)
        {
      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
      	  hql = "SELECT docNum from Ordr where docNum>'"+id+"' ";
        }
        else{
      	  hql = "SELECT docNum from Ordr where  docNum>'"+id+"' " +
      			" and  usersign in (select userid from ousr where u_usergroup=" +
  	      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
        }
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
		 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		 ob1 = appMain.lt.sqlclob(hql1,0,1);
		 hql="select u_enable from dbo.[@sms] where code='DPERM'";
		 ob = appMain.lt.sqlclob(hql,0,1);
        if(ob==null||ob.length==0)
        {
      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
      	  hql = "SELECT max(docNum) from Ordr  ";
        }
        else{
      	  hql = "SELECT max(docNum) from Ordr where  " +
      			" usersign in (select userid from ousr where u_usergroup=" +
  	      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
        }
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
	public void setValues(OrdrView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}
		if(dod.equals("草稿"))	  
		{
			try {
				idoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
			
			hql="select docentry from odrf where docentry='"+id+"'";
			ob = appMain.lt.sqlclob(hql,0,1); 
			 if(ob==null||ob.length==0)
	        {	
		     JOptionPane.showMessageDialog(null,"草稿单号不存在");		    						    	
			 return;
	        }
			else{
			idoc.getByKey(Integer.valueOf(ob[0][0].toString()));
			 }	  
			hql = "SELECT case when docStatus='C' then '已清' else '未清' end+'-'+case when wddstatus='W' " +
					" then '审批中' when wddstatus='Y' then '已审批' when wddstatus='N' then '已拒绝' else '无' end from Odrf where docEntry='"+id+"'";
			 ob = appMain.lt.clob(hql,0,1); 
			 v.getTxt_status().setText(ob[0][0].toString());
			 v.getTxt_cus().setEditable(true);
			 v.getTxt_cus().setSelectedItem(idoc.getCardCode());
			 v.getTxt_cusn().setText(idoc.getCardName());
			v.getTxt_type().setText("草稿");
			hql="select lineNum=p.lineNum+1,'','','','','','','',p.itemCode, p.dscription,p.U_Ymd,p.U_Mtmd,"+
	           "p.unitMsr,p.U_NumPerUnit,p.whsCode," +
	           "p.unitMsr2,p.U_Gs,U_YGs=0,p.U_Gjjg,p.quantity,U_SQuantity=0,p.price," +
	           "p.U_Zc,p.U_Mjg,p.lineTotal,p.U_Zz,p.U_Sfhh,"+
		       "p.U_Tsjg,p.U_Mtdl,p.U_DjNo,p.U_DjNozj,p.U_Dydj," +
		       "p.U_Jgf,p.U_Ydy, p.U_Ckck,"+
	           "p.U_Czy,p.U_Scwc,p.U_Rinzz,p.U_Dhdate,p.Freetxt " +
	           "from Drf1 as p where p.docEntry='"+idoc.getDocEntry()+"'"+
			   " and p.linestatus='O'";
		     v.getOd().updatetable(hql,0);	
		     hql = "SELECT a.U_slpcode1,b.slpname from odrf a inner join oslp b on a.U_slpcode1=b.slpcode where docEntry='"+idoc.getDocEntry()+"'";
	        ob = appMain.lt.sqlclob(hql,0,1); 
	        if(!(ob==null||ob.length==0))
	        {
	          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
	          v.getCom_sales1().setEditable(true);
	 		  v.getCom_sales1().setSelectedItem(Cbi);
	        }
	        else{
	        	ComboBoxItem  Cbi=new ComboBoxItem(-1,"-无销售员工-");
	        	 v.getCom_sales1().setEditable(true);
	    		 v.getCom_sales1().setSelectedItem(Cbi);
	        }
	        v.getCom_sales1().setEditable(false);

		}
		else{
			try {
				idoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oOrders);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
			hql="select docentry from ordr where docnum='"+id+"'";
			ob = appMain.lt.sqlclob(hql,0,1); 
			 if(ob==null||ob.length==0)
	        {	
		     JOptionPane.showMessageDialog(null,"单号不存在");		    						    	
			 return;
	        }
			else{
			   idoc.getByKey(Integer.valueOf(ob[0][0].toString()));
			 }		
			v.getTxt_type().setText("订单");
			 hql="select p.lineNum+1,'','','','','','','',p.itemCode, p.dscription,p.U_Ymd,p.U_Mtmd,"+
	           "p.unitMsr,p.U_NumPerUnit,p.whsCode," +
	           "p.unitMsr2,p.U_Gs,U_YGs=0,p.U_Gjjg,p.quantity,U_SQuantity=0,p.price," +
	           "p.U_Zc,p.U_Mjg,p.lineTotal,p.U_Zz,p.U_Sfhh,"+
		       "p.U_Tsjg,p.U_Mtdl,p.U_DjNo,p.U_DjNozj,p.U_Dydj,"+
	           "p.U_Jgf,p.U_Ydy, p.U_Ckck,p.U_Czy,p.U_Scwc,p.U_Rinzz,p.U_Dhdate,p.Freetxt " +
	           "from Rdr1 as p where p.docEntry='"+idoc.getDocEntry()+"'";			
		     v.getOd().updatetable(hql,0);	   	
			 
			hql = "SELECT a.U_slpcode1,b.slpname from ordr a inner join oslp b on a.U_slpcode1=b.slpcode where docEntry='"+idoc.getDocEntry()+"'";
	        ob = appMain.lt.sqlclob(hql,0,1); 
	        if(!(ob==null||ob.length==0))
	        {
	          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
	          v.getCom_sales1().setEditable(true);
	 		  v.getCom_sales1().setSelectedItem(Cbi);
	 		 v.getCom_sales1().setEditable(false);
	        }
	        else{
	        	 v.getCom_sales1().setEditable(true);
	    		 v.getCom_sales1().setSelectedItem(null);
	    		 v.getCom_sales1().setEditable(false);
	        }
	        hql = "SELECT docStatus from Ordr where docNum='"+id+"'";
			 ob = appMain.lt.clob(hql,0,1); 
			 v.getTxt_status().setText(ob[0][0].toString().equals("C")?"已清":"未清");
			 v.getTxt_cus().setEditable(true);
			 v.getTxt_cus().setSelectedItem(idoc.getCardCode());
			 v.getTxt_cusn().setText(idoc.getCardName());
		}		
		
		 hql = "SELECT name from Ocpr where cntctCode='"+idoc.getContactPersonCode()+"'";
         ob = appMain.lt.clob(hql,0,1);    
        if(idoc.getContactPersonCode()!=0)
        {
		 ComboBoxItem  Cbi=new ComboBoxItem(idoc.getContactPersonCode(),ob[0][0].toString());
		 v.getTxt_cuslxr().setEditable(true);
		 v.getTxt_cuslxr().setSelectedItem(Cbi);
        }
        else{
        	 v.getTxt_cuslxr().setEditable(true);
    		 v.getTxt_cuslxr().setSelectedItem(null);
        }	
        hql = "SELECT slpName from Oslp where slpCode='"+idoc.getSalesPersonCode()+"'";
        ob = appMain.lt.clob(hql,0,1); 
        if(idoc.getSalesPersonCode()!=0)
        {
		 ComboBoxItem  Cbi=new ComboBoxItem(idoc.getSalesPersonCode(),ob[0][0].toString());
		 v.getCom_sales().setEditable(true);
		 v.getCom_sales().setSelectedItem(Cbi);
        }
        else{
        	 v.getCom_sales().setEditable(true);
    		 v.getCom_sales().setSelectedItem(null);
        }	
        v.getCom_sales().setEditable(false);
		 v.getTxt_date().setDate(idoc.getDocDate());
		 v.getTxt_duedate().setDate(idoc.getDocDueDate());
		 v.getTxt_delidate().setDate((Date)idoc.getUserFields().getFields().item("U_Dhdate").getValue());
		 v.getTxt_docn().setText(id.toString());
		 v.getTxt_docnplus().setText(idoc.getDocEntry().toString());
		 v.getJta_memo().setText(idoc.getComments());
		 v.getTxt_ter().setText(idoc.getUserFields().getFields().item("U_Dq").getValue().toString());
		 idoc.release();
		 if(v.getOd().verification()==false)
	     {
	    	return;
	     }
	}
	@Override
	public void close(OrdrView v)
	{
		if(v.getTxt_type().getText().equals("订单"))
		{		
			try {
				appMain.odoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oOrders);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
		}
		else{
			try {
				appMain.odoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
		}
		 if(appMain.odoc.getByKey(Integer.valueOf(v.getTxt_docnplus().getText())))
         {
           appMain.odoc.close();
           v.getTxt_status().setText("已清");
         }
	}
	public OrdrView getV() {
		return v;
	}

	public void setV(OrdrView v) {
		this.v = v;
	}

	@Override
	public void print(OrdrView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ctarget(OrdrView v, Integer dh) {
		// TODO Auto-generated method stub
		if(v.getOd().verification()==false)
	    {
	    	return;
	    }
		if(v.getTxt_type().getText().equals("订单"))
		{
			JOptionPane.showMessageDialog(null,"订单不可以进行生成操作");
			return;
		}
		if(v.getTxt_type().getText().equals("草稿")&&v.getTxt_status().getText().equals("已清"))
		{
			JOptionPane.showMessageDialog(null,"已清状态不可以进行生成操作");
			return;
		}
		//会直接再次发送短信
		else if(v.getTxt_type().getText().equals("草稿")&&v.getTxt_status().getText().split("-").length>1&&
			   (v.getTxt_status().getText().split("-")[1].equals("审批中")
			     ||v.getTxt_status().getText().split("-")[1].equals("已拒绝")))
		{
			    int k=0;
				hql="select isnull(max(code),0)+1 from [@wfsms]";
			    ob=appMain.lt.sqlclob(hql,0,1);
			    if(ob==null||ob.length==0)
			    {
			    	k=1;
			    }
			    else{
			    	k=Integer.valueOf(ob[0][0].toString());
			    }
			   hql="insert into [@wfsms](Code,Name,U_Wfid,U_Dfid,U_Spjb,U_Fqid,U_Fqphone,U_Previewid," +
		     		"U_Previewphone,U_Bid,U_Bphone,U_Nid,U_Nphone,U_Fsnr,U_DateTime,U_Fscs,U_FsTime) " +
		     		" select top 1 '"+k+"',Name,U_Wfid,U_Dfid,U_Spjb,U_Fqid,U_Fqphone,U_Previewid,U_Previewphone," +
		     		"U_Bid,U_Bphone,U_Nid,U_Nphone,U_Fsnr,getdate(),0,'' from [@wfsms] " +
		     		"where U_Dfid='"+v.getTxt_docn().getText()+"' order by code desc";
		      dbu.exeSql(hql);	
		      JOptionPane.showMessageDialog(null,"审批请求已再次发送");
		}
		else if(v.getTxt_type().getText().equals("草稿")&&v.getTxt_status().getText().split("-").length>1&&
				   (v.getTxt_status().getText().split("-")[1].equals("无")))
		{
		docentry=Integer.valueOf(v.getTxt_docnplus().getText());
		 Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	    	   @SuppressWarnings("deprecation")
			Connection con = session.connection(); 
	    	
	    	   hql = "{call zdy_workflow(?,?,?,?)}";
		         try {	        	
					cstmt = con.prepareCall(hql);				
					cstmt.setInt(1,docentry);	
					cstmt.setInt(2,17);				
					cstmt.registerOutParameter(3, java.sql.Types.NVARCHAR);		
					cstmt.registerOutParameter(4, java.sql.Types.INTEGER);	
					cstmt.execute();			
					result = cstmt.getString(3);
					rev=cstmt.getInt(4);					    	     
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			         
		        t.commit();	 		    	   
	       }catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
	      }
	    //cteate order or get into workflow,according to the return values
	      if(result.equals("@WFDST"))
	      {
	    	  if(rev==0)
	    	  {		
	    		  Dra2Doc dd=new Dra2Doc();	
			      dd.createorder(docentry);					    		  
	    		  JOptionPane.showMessageDialog(null,"销售订单"+appMain.oCompany.getNewObjectKey()+","+"添加成功!");		    				    			
	    	  }
	    	  else if(rev==1||rev==2)
	    	  {		    		 		    		 		    		  		    		 
	    		  //set value for wflist1 end
	    		  // add  wflist
	    		  if(appMain.wfd.setWflist("17",String.valueOf(docentry), "@WFDST", rev.shortValue())==null)
	    		  {
	    			  return;
	    		  }
	    		  appMain.wfd.add(appMain.wfd.setWflist("17",String.valueOf(docentry), "@WFDST", rev.shortValue()));
	    		  hql="update odrf set wddstatus='W' where docentry='"+String.valueOf(docentry)+"'";
	 			  dbu.exeSql(hql);
	    		  hql = "SELECT  isnull(max(docentry),0) FROM [@wflist] ";
	              ob = appMain.lt.sqlclob(hql,0,1);
	               if(ob.length==0)
	               {
	             	  return;
	               }	    		  
	    		  session = MdbHibernateUtils.getSession();
			       t = session.beginTransaction();{
			       try {
			    	   @SuppressWarnings("deprecation")
					Connection con = session.connection(); 
			    	
			    	   hql = "{call zdy_wfmessage(?,?,?,?)}";
				         try {	        	
							cstmt = con.prepareCall(hql);				
							cstmt.setInt(1,Integer.valueOf(ob[0][0].toString()));	
							cstmt.setString(2,"2");	
							cstmt.registerOutParameter(3, java.sql.Types.INTEGER);		
							cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);	
							cstmt.execute();																		    	     
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}			         
				        t.commit();	 		    	   
			       }catch (HibernateException e1) {
			           e1.printStackTrace();
			           t.rollback();
			         } finally {
			       	  MdbHibernateUtils.closeSession(session);
			         } 
			      }				       
	    		  JOptionPane.showMessageDialog(null,"进入"+rev.toString()+"级审批");
	    	  }
	    	  else{}
	      }
		}
		else if((v.getTxt_type().getText().equals("草稿")&&v.getTxt_status().getText().split("-").length>1&&
			  v.getTxt_status().getText().split("-")[1].equals("已审批"))
			  ||(v.getTxt_type().getText().equals("草稿")&&v.getTxt_status().getText().equals("已清")))
		{										
		    	 Dra2Doc dd=new Dra2Doc();	
		    	 dd.createorder(Integer.valueOf(dh));		
		    	 JOptionPane.showMessageDialog(null,"生成订单成功");
		         v.getTxt_type().setText("订单");			 
		}
		else{}
	}

	@Override
	public void add(User user,OrdrView v) {
		// TODO Auto-generated method stub
		
	}
}
