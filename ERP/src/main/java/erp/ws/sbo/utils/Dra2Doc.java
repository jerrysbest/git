package erp.ws.sbo.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sap.smb.sbo.api.IStockTransfer;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.wrapper.com.ComFailException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;
import erp.ws.sbo.client.swing.view.Snin.SninView;

public class Dra2Doc {
	 private String hql;
	 private DeSNView v;
     private IStockTransfer oskt;
	 private Object[][] ob;
	 private Object[][] ob1;
	 private Object[][] ob3,ob4;
	 private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
     public Dra2Doc()
     {
    	 
     }
     @SuppressWarnings("deprecation")
	 public void createorder(Integer docentry)
     {
    		try {
    		   appMain.odelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
    		} catch (SBOCOMException e1) {
    			// TODO Auto-generated catch block			
    			e1.printStackTrace();			
    		}	
    		if(appMain.odelNotes.getByKey(docentry))
    		{
    			 try {
	    			appMain.odoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oOrders);
	    			} catch (SBOCOMException e1) {
	    				// TODO Auto-generated catch block			
	    				e1.printStackTrace();			
	    			}	
    			    hql = "select docstatus from odrf where docentry='"+appMain.odelNotes.getDocEntry()+"' ";
    			    ob = appMain.lt.sqlclob(hql,0,1); 
    			    if((ob==null||ob.length==0)||ob[0][0].toString().equals("C"))
    			    {
    			    	JOptionPane.showMessageDialog(null,"草稿"+appMain.odelNotes.getDocEntry()+"已关闭,不可生成订单");
    			        return;
    			    } 	    	   			
	    			appMain.odoc.setCardCode(appMain.odelNotes.getCardCode());			
	    			appMain.odoc.setCardName(appMain.odelNotes.getCardName());
	    			appMain.odoc.setContactPersonCode(appMain.odelNotes.getContactPersonCode());
	    			appMain.odoc.setSalesPersonCode(appMain.odelNotes.getSalesPersonCode());			    			
	    			appMain.odoc.setDocDate(appMain.odelNotes.getDocDate());
	    			appMain.odoc.setDocDueDate(appMain.odelNotes.getDocDueDate());
	    			appMain.odoc.setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oOrders);
	    			appMain.odoc.setComments(appMain.odelNotes.getComments());		
	    			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");	
	    			appMain.odoc.getUserFields().getFields().item("U_Dhdate").setValue(f.format((Date)appMain.odelNotes.getUserFields().getFields().item("U_Dhdate").getValue()));
	    			appMain.odoc.getUserFields().getFields().item("U_Dq").setValue(appMain.odelNotes.getUserFields().getFields().item("U_Dq").getValue());
	    			appMain.odoc.getUserFields().getFields().item("U_slpcode1").setValue(appMain.odelNotes.getUserFields().getFields().item("U_slpcode1").getValue());
	    			appMain.odoc.getUserFields().getFields().item("U_subuid").setValue(appMain.odelNotes.getUserFields().getFields().item("U_subuid").getValue());
		    		
	    			for(int i=0;i<appMain.odelNotes.getLines().getCount();i++)
	    			{		    				
	    				appMain.odelNotes.getLines().setCurrentLine(i);
	    				appMain.odoc.getLines().setShipDate(appMain.odelNotes.getLines().getShipDate());
	    				appMain.odoc.getLines().setItemCode(appMain.odelNotes.getLines().getItemCode());
	    				appMain.odoc.getLines().setItemDescription(appMain.odelNotes.getLines().getItemDescription());
	    				appMain.odoc.getLines().setSalesPersonCode(appMain.odelNotes.getLines().getSalesPersonCode());
	    				appMain.odoc.getLines().setWarehouseCode(appMain.odelNotes.getLines().getWarehouseCode());
	    				appMain.odoc.getLines().setQuantity(appMain.odelNotes.getLines().getQuantity());
	    				appMain.odoc.getLines().setUnitsOfMeasurment(appMain.odelNotes.getLines().getUnitsOfMeasurment());
	    				//appMain.odoc.getLines().setDiscountPercent(appMain.odelNotes.getLines().getDiscountPercent());
	    				//appMain.odoc.getLines().setPrice(appMain.odelNotes.getLines().getPrice());		    				
	    				appMain.odoc.getLines().setLineTotal(appMain.odelNotes.getLines().getLineTotal());
	    				appMain.odoc.getLines().setFreeText(appMain.odelNotes.getLines().getFreeText());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mtmd").getValue());	
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Ymd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ymd").getValue());			    			
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zc").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zc").getValue());	
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zz").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zz").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gs").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gs").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gjjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gjjg").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mjg").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Tsjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Tsjg").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Jgf").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Jgf").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Sfhh").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Sfhh").getValue());	  
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_NumPerUnit").getValue());
		    			
	    				appMain.odoc.getLines().add();
	    				
	    			}
	    		
	    			appMain.lRetCode=appMain.odoc.add();
	    			
	    			if(appMain.lRetCode!=0)
	    			{ 
	    				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
	    				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
	    				
	    				 if (appMain.lErrCode != -4006) // Incase adding an order failed
	    	             {
	    					 JOptionPane.showMessageDialog(null,"添加销售订单失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	    	             }
	    				 else
	    				 {
	    					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
	    				 }
	    			}
	    			else{
	    				 Session session1 = MdbHibernateUtils.getSession();
	    				 Transaction  t1 = session1.beginTransaction();{
	    			       try {
	    			    	   Connection con = session1.connection(); 
	    			    	   Statement sta;								
								sta = con.createStatement();
								hql = "update odrf set docStatus='C' WHERE docentry='"+docentry+"'";
								sta.execute(hql);
								hql = "update drf1 set lineStatus='C' WHERE docentry='"+docentry+"'";
								sta.execute(hql);		
	    				        t1.commit();	 		    	   
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
	    				 if(v!=null)
	    				 {
	    				 /*  new SNL(v,in, appMain.odoc.getCardCode().toString(),appMain.odoc.getDocEntry().toString());	
	    				   SNL snl=new SNL(v);		
	    				   snl.createsdra(v,false,appMain.odoc.getCardCode().toString(),String.valueOf(docentry));
	    					*/
	    				 
	    				 }
	    				 appMain.odelNotes.close();
	    				 appMain.odelNotes.release();	    				
	    				 appMain.odoc.release();	    			 	 
	    			}
    		}
     }
     @SuppressWarnings("deprecation")
   	 public void createoinvs(Integer docentry,Boolean in,Integer djNo)
        {
       		try {
       		  appMain.odelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
       		} catch (SBOCOMException e1) {
       			// TODO Auto-generated catch block			
       			e1.printStackTrace();			
       		}	
       		if(appMain.odelNotes.getByKey(docentry))
       		{
       			 try {
   	    			appMain.odoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInvoices);
   	    			} catch (SBOCOMException e1) {
   	    				// TODO Auto-generated catch block			
   	    				e1.printStackTrace();			
   	    			}		
   	    			appMain.odoc.setCardCode(appMain.odelNotes.getCardCode());			
   	    			appMain.odoc.setCardName(appMain.odelNotes.getCardName());
   	    			appMain.odoc.setContactPersonCode(appMain.odelNotes.getContactPersonCode());
   	    			appMain.odoc.setSalesPersonCode(appMain.odelNotes.getSalesPersonCode());			    			
   	    			appMain.odoc.setDocDate(new Date());
   	    			appMain.odoc.setDocDueDate(new Date());
   	    			appMain.odoc.setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oOrders);
   	    			appMain.odoc.setComments(appMain.odelNotes.getComments());		
   	    			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");	
	    			appMain.odoc.getUserFields().getFields().item("U_Dhdate").setValue(f.format((Date)appMain.odelNotes.getUserFields().getFields().item("U_Dhdate").getValue()));
   	    			appMain.odoc.getUserFields().getFields().item("U_Dq").setValue(appMain.odelNotes.getUserFields().getFields().item("U_Dq").getValue());
   	    			appMain.odoc.getUserFields().getFields().item("U_slpcode1").setValue(appMain.odelNotes.getUserFields().getFields().item("U_slpcode1").getValue());
   	    			appMain.odoc.getUserFields().getFields().item("U_subuid").setValue(appMain.odelNotes.getUserFields().getFields().item("U_subuid").getValue());
	    			
   	    			for(int i=0;i<appMain.odelNotes.getLines().getCount();i++)
   	    			{		    				
   	    				appMain.odelNotes.getLines().setCurrentLine(i);
   	    				hql = "select linestatus from rdr1 where docentry='"+appMain.odelNotes.getLines().getBaseEntry()+"' " +
   	    						" and linenum='"+appMain.odelNotes.getLines().getBaseLine()+"'";
   	    			    ob = appMain.lt.sqlclob(hql,0,1); 
   	    			    if((ob==null||ob.length==0)||ob[0][0].toString().equals("C"))
   	    			    {
   	    			    	JOptionPane.showMessageDialog(null,appMain.odelNotes.getLines().getBaseEntry()+","+appMain.odelNotes.getLines().getBaseLine());
   	    			    	continue;
   	    			    } 	    			
   	    				appMain.odoc.getLines().setShipDate(appMain.odelNotes.getLines().getShipDate());
   	    				appMain.odoc.getLines().setItemCode(appMain.odelNotes.getLines().getItemCode());
   	    				appMain.odoc.getLines().setItemDescription(appMain.odelNotes.getLines().getItemDescription());
   	    				appMain.odoc.getLines().setSalesPersonCode(appMain.odelNotes.getLines().getSalesPersonCode());
   	    				appMain.odoc.getLines().setWarehouseCode(appMain.odelNotes.getLines().getWarehouseCode());
   	    				appMain.odoc.getLines().setQuantity(appMain.odelNotes.getLines().getQuantity());
   	    				appMain.odoc.getLines().setUnitsOfMeasurment(appMain.odelNotes.getLines().getUnitsOfMeasurment());
   	    				//appMain.odoc.getLines().setDiscountPercent(appMain.odelNotes.getLines().getDiscountPercent());
   	    				//appMain.odoc.getLines().setPrice(appMain.odelNotes.getLines().getPrice());	
   	    				appMain.odoc.getLines().setBaseEntry(appMain.odelNotes.getLines().getBaseEntry());	
   	    				appMain.odoc.getLines().setBaseLine(appMain.odelNotes.getLines().getBaseLine());
   	    				appMain.odoc.getLines().setBaseType(appMain.odelNotes.getLines().getBaseType());
   	    				appMain.odoc.getLines().setLineTotal(appMain.odelNotes.getLines().getLineTotal());
   	    				appMain.odoc.getLines().setFreeText(appMain.odelNotes.getLines().getFreeText());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Price").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Price").getValue());	
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Linetotal").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Linetotal").getValue());			    				   	    							
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mtmd").getValue());	
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Ymd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ymd").getValue());			    			
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zc").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zc").getValue());	
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zz").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zz").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gs").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gs").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gjjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gjjg").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mjg").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Tsjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Tsjg").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Jgf").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Jgf").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Sfhh").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Sfhh").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_NumPerUnit").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_djNozj").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_djNozj").getValue());
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Snid").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Snid").getValue());  	   	    			
   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_djNo").setValue(djNo);
   	   		    		
   	    				appMain.odoc.getLines().add(); 	    				
   	    			}  	    		
   	    			appMain.lRetCode=appMain.odoc.add();
   	    			
   	    			if(appMain.lRetCode!=0)
   	    			{ 
   	    				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
   	    				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
   	    				
   	    				 if (appMain.lErrCode != -4006) // Incase adding an order failed
   	    	             {
   	    					 JOptionPane.showMessageDialog(null,"生成发票失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
   	    	             }
   	    				 else
   	    				 {
   	    					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
   	    				 }
   	    			}
   	    			else{
   	    				 Session session1 = MdbHibernateUtils.getSession();
   	    				 Transaction  t1 = session1.beginTransaction();{
   	    			       try {
   	    			    	   Connection con = session1.connection(); 
   	    			    	   Statement sta;								
   								sta = con.createStatement();
   								hql = "update odrf set docStatus='C' WHERE docentry='"+docentry+"'";
   								sta.execute(hql);
   								hql = "update drf1 set lineStatus='C' WHERE docentry='"+docentry+"'";
   								sta.execute(hql);		
   	    				        t1.commit();	 		    	   
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
   	    				 if(v!=null)
   	    				 {
   	    					 
   	    				 /* 序列号不能使用 
   	    				  * new SNL(v,in, appMain.odoc.getCardCode().toString(),appMain.odoc.getDocEntry().toString());	
   	    				   SNL snl=new SNL(v);		
   	    				   snl.createsdra(v,false,appMain.odoc.getCardCode().toString(),String.valueOf(docentry));
   	    					*/
   	    				 
   	    				 }
   	    				 //appMain.odelNotes.close();
   	    				 appMain.odelNotes.release();	    				
   	    				 appMain.odoc.release();
   	    			 	 
   	    			}
       		}
        }
     @SuppressWarnings("deprecation")
   	 public void createmergoinvs(OinvView v,Integer docentry,Boolean in,Integer djNo)
        {     		
       	     hql = "SELECT distinct a.cardcode from Drf1 b,Odrf a where a.docEntry=b.docEntry " +
			 	   "and  b.U_DjNo='"+djNo+"'  and a.docstatus='O' and a.objType='13'";
		     ob = appMain.lt.sqlclob(hql,0,1000); 
		     hql = "SELECT distinct a.docentry from Drf1 b,Odrf a where a.docEntry=b.docEntry " +
				  "and  b.U_DjNo='"+djNo+"'  and a.docstatus='O' and a.objType='13'";
			 ob1 = appMain.lt.sqlclob(hql,0,1000); 
			 for(int k=0;k<ob.length;k++)
		     {		
				 try {
	    			  appMain.odoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInvoices);
	    			} catch (SBOCOMException e1) {
	    				// TODO Auto-generated catch block			
	    				e1.printStackTrace();			
	    			}		
			     for(int j=0;j<ob1.length;j++)
			     {
			    	 try {
			       		appMain.odelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
		       		} catch (SBOCOMException e1) {
		       			// TODO Auto-generated catch block			
		       			e1.printStackTrace();			
		       		}	
		       		try{
		       			appMain.odelNotes.getByKey(Integer.valueOf(ob1[j][0].toString()));
		       		}
		       		catch(ClassCastException e0)
		       		{
		       			continue;
		       		}
			    	 if(appMain.odelNotes.getByKey(Integer.valueOf(ob1[j][0].toString())))
		       		 { 
		       			if(!appMain.odelNotes.getCardCode().equals(ob[k][0].toString()))
		       				continue;
		       			if(!appMain.odoc.getCardCode().equals(ob[k][0].toString()))
		       			{
		       				appMain.odoc.setCardCode(appMain.odelNotes.getCardCode());			
		   	    			appMain.odoc.setCardName(appMain.odelNotes.getCardName());
		   	    			appMain.odoc.setContactPersonCode(appMain.odelNotes.getContactPersonCode());
		   	    			appMain.odoc.setSalesPersonCode(appMain.odelNotes.getSalesPersonCode());			    			
		   	    			appMain.odoc.setDocDate(new Date());
		   	    			appMain.odoc.setDocDueDate(new Date());
		   	    			appMain.odoc.setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oOrders);
		   	    			appMain.odoc.setComments(appMain.odelNotes.getComments());		
		   	    			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");	
			    			appMain.odoc.getUserFields().getFields().item("U_Dhdate").setValue(f.format((Date)appMain.odelNotes.getUserFields().getFields().item("U_Dhdate").getValue()));
		   	    			appMain.odoc.getUserFields().getFields().item("U_Dq").setValue(appMain.odelNotes.getUserFields().getFields().item("U_Dq").getValue());
		   	    			appMain.odoc.getUserFields().getFields().item("U_slpcode1").setValue(appMain.odelNotes.getUserFields().getFields().item("U_slpcode1").getValue());
		   	    			appMain.odoc.getUserFields().getFields().item("U_subuid").setValue(appMain.odelNotes.getUserFields().getFields().item("U_subuid").getValue());
		   			       	
		       			}	
	   	    			for(int i=0;i<appMain.odelNotes.getLines().getCount();i++)
	   	    			{		    				
	   	    				appMain.odelNotes.getLines().setCurrentLine(i);
	   	    				hql = "select linestatus from rdr1 where docentry='"+appMain.odelNotes.getLines().getBaseEntry()+"' " +
	   	    						" and linenum='"+appMain.odelNotes.getLines().getBaseLine()+"'";
	   	    			    ob3 = appMain.lt.sqlclob(hql,0,1); 
	   	    			    if((ob3==null||ob3.length==0)||ob3[0][0].toString().equals("C"))
	   	    			    {
	   	    			    	//JOptionPane.showMessageDialog(null,appMain.odelNotes.getLines().getBaseEntry()+","+appMain.odelNotes.getLines().getBaseLine());
	   	    			    	continue;
	   	    			    } 	    			
	   	    				appMain.odoc.getLines().setShipDate(appMain.odelNotes.getLines().getShipDate());
	   	    				appMain.odoc.getLines().setItemCode(appMain.odelNotes.getLines().getItemCode());
	   	    				appMain.odoc.getLines().setItemDescription(appMain.odelNotes.getLines().getItemDescription());
	   	    				appMain.odoc.getLines().setSalesPersonCode(appMain.odelNotes.getLines().getSalesPersonCode());
	   	    				appMain.odoc.getLines().setWarehouseCode(appMain.odelNotes.getLines().getWarehouseCode());
	   	    				appMain.odoc.getLines().setQuantity(appMain.odelNotes.getLines().getQuantity());
	   	    				appMain.odoc.getLines().setUnitsOfMeasurment(appMain.odelNotes.getLines().getUnitsOfMeasurment());
	   	    				//appMain.odoc.getLines().setDiscountPercent(appMain.odelNotes.getLines().getDiscountPercent());
	   	    				//appMain.odoc.getLines().setPrice(appMain.odelNotes.getLines().getPrice());	
	   	    				appMain.odoc.getLines().setPriceAfterVAT(appMain.odelNotes.getLines().getPriceAfterVAT());	
	   	    				appMain.odoc.getLines().setBaseEntry(appMain.odelNotes.getLines().getBaseEntry());	
	   	    				appMain.odoc.getLines().setBaseLine(appMain.odelNotes.getLines().getBaseLine());
	   	    				appMain.odoc.getLines().setBaseType(appMain.odelNotes.getLines().getBaseType());
	   	    				appMain.odoc.getLines().setFreeText(appMain.odelNotes.getLines().getFreeText());
	   	    				//appMain.odoc.getLines().setLineTotal(appMain.odelNotes.getLines().getLineTotal());
	   	    				//appMain.odoc.getLines().getUserFields().getFields().item("U_Price").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Price").getValue());	
	   	    				//appMain.odoc.getLines().getUserFields().getFields().item("U_Linetotal").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Linetotal").getValue());			    				   	    			
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mtmd").getValue());	
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Ymd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ymd").getValue());			    			
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zc").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zc").getValue());	
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zz").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zz").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gs").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gs").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gjjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gjjg").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mjg").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Tsjg").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Tsjg").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Jgf").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Jgf").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Sfhh").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Sfhh").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_NumPerUnit").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_djNozj").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_djNozj").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_djNo").setValue(djNo);
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_SNid").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_SNid").getValue());
	   	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Snstatus").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Snstatus").getValue());
	   	    				
	   	   		    		
	   	    				appMain.odoc.getLines().add(); 	    				
	   	    			}  	    		
		   	    			
		       		}
			     }
			     appMain.lRetCode=appMain.odoc.add();
	    			
	    			if(appMain.lRetCode!=0)
	    			{ 
	    				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
	    				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
	    				
	    				 if (appMain.lErrCode != -4006) // Incase adding an order failed
	    	             {
	    					 JOptionPane.showMessageDialog(null,"生成发票失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	    	             }
	    				 else
	    				 {
	    					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
	    				 }
	    			}
	    			else{
	    				 hql = "SELECT distinct b.docentry from Drf1 b  " +
								"inner join inv1 a on a.baseentry=b.baseentry and a.baseline=b.baseline and a.U_DjNo=b.U_DjNo " +
	    						"inner join odrf c on c.docentry=b.docentry "+
								"where a.U_DjNo='"+djNo+"' and b.basetype='17' and c.objtype='13'" ;
					     ob3 = appMain.lt.sqlclob(hql,0,1000); 
					     for(int l=0;l<ob3.length;l++)
						 {
	    				 Session session1 = MdbHibernateUtils.getSession();
	    				 Transaction  t1 = session1.beginTransaction();{
	    			       try {
	    			    	   Connection con = session1.connection(); 
	    			    	   Statement sta;								
								sta = con.createStatement();	
								
								hql = "update odrf set docStatus='C' where docentry='"+ob3[l][0]+"'";
								sta.execute(hql);
								Date date = new Date(); 
								System.out.println(date.getTime());
								System.out.println("草稿号"+ob3[l][0].toString()+",汇总单号"+djNo.toString());
								hql = "update drf1 set lineStatus='C' WHERE docentry='"+ob3[l][0]+"'";
								sta.execute(hql);					
							         
	    				        t1.commit();	 		    	   
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
	    				 //appMain.odelNotes.close();
	    				 appMain.odelNotes.release();	    				
	    				 appMain.odoc.release();
	    			 	 
	    			}
		     }
			 hql = "select u_enable from dbo.[@sms] where code='DELSN' and u_enable='Y'";
             ob3 = appMain.lt.sqlclob(hql,0,1); 
           
			 if(ob3!=null&&ob3.length!=0&&ob3[0][0].toString().equals("Y")&&v!=null)
			 {
				 hql="select count(*) from dbo.[@desn] " +
					  "where ifdraft='0' and objtype='13' and docentry='"+djNo+"'";
				 ob4 = appMain.lt.sqlclob(hql,0,1); 
				 if(Integer.valueOf(ob4[0][0].toString())>0)
				 {
					 JOptionPane.showMessageDialog(null,"已有汇总单号为"+djNo.toString()+"的发票写入序列号明细表，不会再次写入发票的序列号明细");
					 return;
				 }
			  
				 hql="insert into dbo.[@desn](ifdraft,objtype,docentry,linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime) " +
			   		"select '0',objtype,'"+djNo+"',linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime from dbo.[@desn] " +
			   		"where ifdraft='1' and objtype='13' and docentry='"+djNo+"'";
			     dbu.exeSql(hql);	
			     hql="update a set a.ifwh='0',a.ifinpsn='0',a.warehouse='',a.fwh=b.warehouse,a.cardcode=b.cardcode,updatetime=getdate() from dbo.[@snstatus] a "+
					"inner join dbo.[@desn] b on a.sn=b.sn "+
					"where b.docentry='"+djNo+"' "+
			        "and b.Ifdraft='0' "+
			        " and b.objtype='13' "; 
			   dbu.exeSql(hql);	
			   hql="update a set a.status='C' FROM [@pasn] a INNER JOIN [@asn1] b on a.docentry=b.docentry inner join [@desn] c on b.u_sn=c.sn "+
				   " where c.docentry='"+djNo+"' "+
				   " and c.Ifdraft='0' "+
				   " and c.objtype='13' "; 
			   dbu.exeSql(hql);	
			   
			   hql="update a set a.ifwh='0',a.ifdes='1',a.warehouse='',updatetime=getdate(),a.fwh=d.warehouse FROM [@snstatus] a " +
			   		" INNER JOIN [@pasn] b on a.sn=b.u_sn  " +
			   		" INNER JOIN [@asn1] c on b.docentry=c.docentry " +
			   		" inner join dbo.[@desn] d on b.u_sn=d.sn"+
				    " where b.status='C'" +
				    " and d.docentry='"+djNo+"' "+
				    " and d.Ifdraft='0' "+
			        " and d.objtype='13' "; 
			   dbu.exeSql(hql);	
			   
			 }	    	
		    
        }

   	 public void createoskt(Integer docentry,SninView v)
     {
    	 try {
				appMain.odelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oStockTransferDraft);
				if(appMain.odelNotes.getByKey(docentry))
	    		{	    			
    				oskt=SBOCOMUtil.newStockTransfer(appMain.oCompany);	    				    			
	    			oskt.setDocDate(new Date());

	    			oskt.setDocObjectCode(SBOCOMConstants.BoObjectTypes_oStockTransfer);
	    			oskt.setComments(appMain.odelNotes.getComments());		
	    			oskt.getUserFields().getFields().item("U_subuid").setValue(appMain.odelNotes.getUserFields().getFields().item("U_subuid").getValue());	
	    			oskt.setPriceList(Integer.valueOf(((ComboBoxItem)v.getCom_plist().getSelectedItem()).getValue().toString()));
	    			oskt.setFromWarehouse(((ComboBoxItem)v.getCom_whs().getSelectedItem()).getValue().toString());
	    			
	    			for(int i=0;i<appMain.odelNotes.getLines().getCount();i++)
	    			{		    				
	    				appMain.odelNotes.getLines().setCurrentLine(i);
	    				
	    				oskt.getLines().setItemCode(appMain.odelNotes.getLines().getItemCode());
	    				oskt.getLines().setItemDescription(appMain.odelNotes.getLines().getItemDescription());

	    				oskt.getLines().setWarehouseCode(appMain.odelNotes.getLines().getWarehouseCode());
	    				oskt.getLines().setQuantity(appMain.odelNotes.getLines().getQuantity());		
	    				oskt.getLines().getUserFields().getFields().item("U_Mtmd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mtmd").getValue());	
	    				oskt.getLines().getUserFields().getFields().item("U_Ymd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ymd").getValue());			    			
	    				//oskt.getLines().getUserFields().getFields().item("U_Zc").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zc").getValue());	
	    				oskt.getLines().getUserFields().getFields().item("U_Zz").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zz").getValue());
	    				oskt.getLines().getUserFields().getFields().item("U_Gs").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gs").getValue());
	    				oskt.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_NumPerUnit").getValue());
	    				oskt.getLines().getUserFields().getFields().item("U_Scwc").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Scwc").getValue());
	    				oskt.getLines().getUserFields().getFields().item("U_SNid").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_SNid").getValue());
	    				
	    				oskt.getLines().add();
	    				
	    			}		    		
	    			appMain.lRetCode=oskt.add();
	    			
	    			if(appMain.lRetCode!=0)
	    			{ 
	    				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
	    				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
	    				
	    				 if (appMain.lErrCode != -4006) // Incase adding an order failed
	    	             {
	    					 JOptionPane.showMessageDialog(null,"添加库存转储单失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	    	             }
	    				 else
	    				 {
	    					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
	    				 }
	    			}
	    			else{
	    				
	    				 hql = "update odrf set docStatus='C' WHERE docentry='"+docentry+"'";
						 dbu.exeSql(hql);
						 hql = "update drf1 set lineStatus='C' WHERE docentry='"+docentry+"'";
						 dbu.exeSql(hql);	
	    				 appMain.odelNotes.release();	    				
	    				 oskt.release();	  
	    				 hql = "select u_enable from dbo.[@sms] where code='CSTSN' and u_enable='Y'";
                         ob = appMain.lt.sqlclob(hql,0,1); 
                         
	    				 if(ob!=null&&ob.length!=0&&ob[0][0].toString().equals("Y")&&v!=null)
	    				 {	    					 	    					 
	    					 hql="insert into dbo.[@desn](ifdraft,objtype,docentry,linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime) " +
	 	    				   	 "select '0',objtype,'"+appMain.oCompany.getNewObjectKey()+"',linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime from dbo.[@desn] " +
	 	    				   	 "where ifdraft='1' and objtype='67' and docentry='"+docentry+"'";
	 	    				 dbu.exeSql(hql);	
	 	    				 hql="update a set a.ifwh='1',a.warehouse=b.warehouse,a.fwh='',a.cardcode='',updatetime=getdate() from dbo.[@snstatus] a "+
    							"inner join dbo.[@desn] b on a.sn=b.sn "+
    							"where b.docentry='"+appMain.oCompany.getNewObjectKey()+"' "+
    					        "and b.Ifdraft='0' "+
    					        " and b.objtype='67' "; 
	 	    				 dbu.exeSql(hql);	
	 	    			 }
	    			}
	    		}   	 
    	 
    	   } catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
            catch (ComFailException e1) {
			// TODO Auto-generated catch block			
			   e1.printStackTrace();			
		   }
    	
     }
	 public void createoign(Integer docentry,OignView v)
     {
    	 try {	 
    		 //JOptionPane.showMessageDialog(null,"单号"+docentry);// Display error message
 	        
				appMain.odelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
				 
				if(appMain.odelNotes.getByKey(docentry))
	    		{	    			
					appMain.odoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInventoryGenEntry);	    				    			
					appMain.odoc.setDocDate(new Date());	
			
					appMain.odoc.setComments(appMain.odelNotes.getComments());		
					appMain.odoc.getUserFields().getFields().item("U_subuid").setValue(appMain.odelNotes.getUserFields().getFields().item("U_subuid").getValue());	
					appMain.odoc.getUserFields().getFields().item("U_Czy").setValue(appMain.odelNotes.getUserFields().getFields().item("U_Czy").getValue());
	    			for(int i=0;i<appMain.odelNotes.getLines().getCount();i++)
	    			{		    				
	    				appMain.odelNotes.getLines().setCurrentLine(i);
	    				try{
	    				//appMain.odoc.getLines().setLineType(200);//
	    				//appMain.odoc.getLines().setItemCode(appMain.odelNotes.getLines().getItemCode());//
	    				//appMain.odoc.getLines().setItemDescription(appMain.odelNotes.getLines().getItemDescription());//
	    				appMain.odoc.getLines().setWarehouseCode(appMain.odelNotes.getLines().getWarehouseCode());//
	    				appMain.odoc.getLines().setQuantity(appMain.odelNotes.getLines().getQuantity());//
	    				//JOptionPane.showMessageDialog(null,"生产单号"+appMain.odelNotes.getLines().getBaseEntry());// Display error message
		    	          
	    				appMain.odoc.getLines().setBaseEntry(appMain.odelNotes.getLines().getBaseEntry());
	    				appMain.odoc.getLines().setTransactionType(appMain.odelNotes.getLines().getTransactionType());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Mtmd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Mtmd").getValue());	
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Ymd").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ymd").getValue());			    			
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Ckck").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Ckck").getValue());	
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Zz").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Zz").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Gs").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Gs").getValue());
//	    				appMain.odoc.getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_NumPerUnit").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_Scwc").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_Scwc").getValue());
	    				appMain.odoc.getLines().getUserFields().getFields().item("U_SNid").setValue(appMain.odelNotes.getLines().getUserFields().getFields().item("U_SNid").getValue());

	    				appMain.odoc.getLines().add();
	    				}
	    				catch(ComFailException e0){
	    					e0.printStackTrace();
	    				}
	    				
	    			}		    		
	    			appMain.lRetCode=appMain.odoc.add();
	    			if(appMain.lRetCode!=0)
	    			{ 
	    				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
	    				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
	    				
	    				 if (appMain.lErrCode != -4006) // Incase adding an order failed
	    	             {
	    					 JOptionPane.showMessageDialog(null,"添加生产收货单失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	    	             }
	    				 else
	    				 {
	    					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
	    				 }
	    			}
	    			else{
	    				JOptionPane.showMessageDialog(null,"生产收货单"+appMain.oCompany.getNewObjectKey()+","+"生成成功!");	 
	    				 hql = "update odrf set docStatus='C' WHERE docentry='"+docentry+"'";
						 dbu.exeSql(hql);
						 hql = "update drf1 set lineStatus='C' WHERE docentry='"+docentry+"'";
						 dbu.exeSql(hql);	
	    				 appMain.odelNotes.release();	    				
	    				 appMain.odoc.release();	  
	    				 hql = "select u_enable from dbo.[@sms] where code='OIGNSN' and u_enable='Y'";
                         ob = appMain.lt.sqlclob(hql,0,1); 
                         
	    				 if(ob!=null&&ob.length!=0&&ob[0][0].toString().equals("Y")&&v!=null)
	    				 {	    					 	    					 
	    					 hql="insert into dbo.[@desn](ifdraft,objtype,docentry,linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime) " +
	 	    				   	 "select '0',objtype,'"+appMain.oCompany.getNewObjectKey()+"',linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime from dbo.[@desn] " +
	 	    				   	 "where ifdraft='1' and objtype='59' and docentry='"+docentry+"'";
	 	    				 dbu.exeSql(hql);	
	 	    				 hql="update a set a.ifwh='1',a.warehouse=b.warehouse,a.fwh='',a.cardcode='',updatetime=getdate() from dbo.[@snstatus] a "+
    							"inner join dbo.[@desn] b on a.sn=b.sn "+
    							"where b.docentry='"+appMain.oCompany.getNewObjectKey()+"' "+
    					        "and b.Ifdraft='0' "+
    					        " and b.objtype='59' "; 
	 	    				 dbu.exeSql(hql);	
	 	    			 }
	    				 //JOptionPane.showMessageDialog(null,"生产收货单"+appMain.oCompany.getNewObjectKey()+","+"生成成功!");	 
	    			}
	    		}   	 
    	 
    	   } catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
            catch (ComFailException e1) {
			// TODO Auto-generated catch block			
			   e1.printStackTrace();			
		   }
    	
     }
}
