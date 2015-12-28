package erp.ws.sbo.client.swing.dao.impl;


import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.wrapper.com.ComFailException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.abs.AbsDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.Dra2Doc;
import erp.ws.sbo.utils.MdbHibernateUtils;
import erp.ws.sbo.utils.SNL;


import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.PreviewDialog;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceCreationException;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceKeyCreationException;
import org.pentaho.reporting.libraries.resourceloader.ResourceLoadingException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;



public class OinvDoc extends AbsDoc{

	private DocMenuView dmv=DocMenuView.getdmv();
	private OinvView v;
	private String hql,hql1,hql2,No;
	private Object[][] ob;
	private Object[][] ob1,ob2;
	private int docentry;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private CallableStatement cstmt;
	private String result = null;
	private Integer rev=0,Ndoce,Ndoce1;
	private IDocuments idoc;
	private OinvAdvSN advsn=new OinvAdvSN();
   /**
    * this operation is nonsynchronous,which means that is only one person can do this at the same time
    * because computer must Determine the max docentry 
    * 
    */
	@Override
	public void create(OinvView v) {
		// TODO Auto-generated method stub
		 hql = "select U_enable from [@SMS] where code='DELSN'";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||(ob!=null&&ob.length==0))
		 {
			 JOptionPane.showMessageDialog(null,"���ݵ����к�����δ����");
			 return;
		 }
		 if(ob[0][0].toString().equals("Y"))
		 {
			 if(!advsn.verification(v))
			 {
				JOptionPane.showMessageDialog(null,"���к���֤δͨ��,�޷�����");
				return;
			 }
		 }
		 this.setV(v);
		 if(v.getOd().verification()==false)
	     {
			JOptionPane.showMessageDialog(null,"������֤δͨ�����޷�����");
	    	return;
	     }
		if(v.getOd1().ds.getCnValue().equals("�޸�"))
		{
			this.delete(Integer.valueOf(v.getTxt_docn().getText()));	
			docentry=Integer.valueOf(v.getTxt_docn().getText().toString());
		}		
		if(v.getOd1().ds.getCnValue().equals("׷��"))
		{
			hql="select isnull(max(a.U_DjNozj),0) from Drf1 a,Odrf b where a.docEntry=b.docEntry and a.U_DjNo='"+v.getTxt_docn().getText()+"' and b.objType='13'";
			Ndoce= Integer.valueOf(appMain.lt.sqlclob(hql, 0, 1)[0][0].toString())+1;		
		    docentry=Integer.valueOf(v.getTxt_docn().getText().toString());
		}
		if(v.getOd1().ds.getCnValue().equals("����"))
		{
			Ndoce=1;
			Ndoce1=0;
			hql="select isnull(max(U_DjNo),0) from Drf1";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob[0][0].toString().equals("0"))
			{
				Ndoce1+=1;			
			}
			else{
				hql="select substring(Convert(nvarchar(20),max(U_DjNo)),1,len(max(U_DjNo))-3) from drf1";
				Ndoce1= Integer.valueOf(appMain.lt.sqlclob(hql, 0, 1)[0][0].toString())+1;			
			}
		    if(appMain.oCompany.getUserSignature().toString().length()==1)
		    {
		    	No=Ndoce1.toString()+"00"+appMain.oCompany.getUserSignature().toString();
		    }
		    else if(appMain.oCompany.getUserSignature().toString().length()==2)
		    {
		    	No=Ndoce1.toString()+"0"+appMain.oCompany.getUserSignature().toString();
		    }
		    else{
		    	No=Ndoce1.toString()+appMain.oCompany.getUserSignature().toString();
		    }
	
			docentry=Integer.valueOf(No);
			//JOptionPane.showMessageDialog(null,docentry+","+Ndoce);	
		}
		String[] s={"������"};		
		ob=v.getOd().Filterdrecords(true, s);
		//JOptionPane.showMessageDialog(null,"��Ҫ���"+ob.length+"�ŵ���");
	    appMain.lidoc=new IDocuments[ob.length];

		for(int k=0;k<appMain.lidoc.length;k++)
		{
			try {
				appMain.lidoc[k]=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block	
				e1.printStackTrace();			
			}	
			appMain.lidoc[k].setCardCode(ob[k][0].toString());
			try{
			    appMain.lidoc[k].setSalesPersonCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));				
			}
			catch(ClassCastException e2)
			{
				JOptionPane.showMessageDialog(null,"��ѡ������Ա");	
				return;
			}
			appMain.lidoc[k].setDocDate((Date) v.getTxt_date().getValue());		
			appMain.lidoc[k].setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oInvoices);
			appMain.lidoc[k].setComments(v.getJta_memo().getText());
			try{
			   appMain.lidoc[k].getUserFields().getFields().item("U_slpcode1").setValue(Integer.valueOf(v.getCom_sales1().getSelectedItem()==null?"0":((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()));					
			}
			catch(ClassCastException e2)
			{
				JOptionPane.showMessageDialog(null,"��ѡ������Ա1������ѡ��������Ա��");	
				return;
			}
			appMain.lidoc[k].getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		}
						
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
			//validating before create
			if(v.getOd().getValuethrheader(i, "���۵�λ").equals("��"))
			{
			hql="select quantity=(isnull(a.U_Gs,0)-sum(isnull(b.U_Gs,0))) from rdr1 a left join drf1 b on a.docentry=b.baseentry" +
				" and a.linenum=b.baseline where a.docentry='"+v.getOd().getValuethrheader(i,"��������")+"' " +
				" and a.linenum='"+(Integer.valueOf(v.getOd().getValuethrheader(i,"�����к�").toString())-1)+"' " +
				" and (a.unitMsr='��' or a.unitMsr='����') "+
				" group by a.U_Gs "; 
			 ob1=appMain.lt.sqlclob(hql,0,1);
			 
			 if(new BigDecimal(v.getOd().getValuethrheader(i,"��װ����")==null?"0":v.getOd().getValuethrheader(i,"��װ����").toString()).compareTo(new BigDecimal(ob1[0][0].toString()))>0)
			 {
				 JOptionPane.showMessageDialog(null,"��"+Integer.valueOf(i+1).toString()+"�����۵�λΪ��,������������װ��������������װ����,���������롣��������Ϊ"+ob1[0][0].toString());				
				 return;
			 }
			}
			 if(v.getOd().getValuethrheader(i,"���ϴ���")==null||v.getOd().getValuethrheader(i,"���ϴ���").toString().equals("")
			  ||v.getOd().getValuethrheader(i,"��װ����")==null||v.getOd().getValuethrheader(i,"��װ����").toString().equals("")
			  ||new BigDecimal(v.getOd().getValuethrheader(i,"��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
			{
				continue;
			}
			 if(v.getOd().getValuethrheader(i,"���ϴ���")!=null&&!v.getOd().getValuethrheader(i,"���ϴ���").toString().equals("")&&
				(v.getOd().getValuethrheader(i,"����")==null||new BigDecimal(v.getOd().getValuethrheader(i,"����").toString()).equals(new BigDecimal("0"))
				||v.getOd().getValuethrheader(i,"���")==null||new BigDecimal(v.getOd().getValuethrheader(i,"���").toString()).equals(new BigDecimal("0"))))
			{
				int hh=i+1;	
				JOptionPane.showMessageDialog(null,v.getOd().getValuethrheader(i,"���ϴ���"));	
				JOptionPane.showMessageDialog(null,"��"+hh+"���������߽������Ƿ�,����");	
				return;
			}	
			 for(int k=0;k<ob.length;k++)
			{	
				if(v.getOd().getValuethrheader(i,"������").toString().equals(ob[k][0].toString()))
				{	
					try{										
					appMain.lidoc[k].getLines().setShipDate((Date) v.getTxt_date().getValue());
					appMain.lidoc[k].getLines().setItemCode( v.getOd().getValuethrheader(i, "���ϴ���").toString());
					appMain.lidoc[k].getLines().setItemDescription( v.getOd().getValuethrheader(i, "��������").toString());
					if(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")!=null&&(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("Y")))
					{
						appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")==null||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString()=="")?"N":v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString());	
					}
					else{
						appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")==null||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString()=="")?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")).getValue().toString());
					}
					appMain.lidoc[k].getLines().setSalesPersonCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));
					appMain.lidoc[k].getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "�ֿ����").toString());
					appMain.lidoc[k].getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "����").toString()));
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(Double.valueOf((v.getOd().getValuethrheader(i, "��λ����")==null||v.getOd().getValuethrheader(i, "��λ����").toString()=="")?"1":v.getOd().getValuethrheader(i, "��λ����").toString()));
					appMain.lidoc[k].getLines().setPriceAfterVAT(Double.valueOf(v.getOd().getValuethrheader(i, "����").toString()));					
					appMain.lidoc[k].getLines().setBaseType(SBOCOMConstants.BoObjectTypes_Document_oOrders);
					appMain.lidoc[k].getLines().setBaseEntry(Integer.valueOf(v.getOd().getValuethrheader(i, "��������").toString()));
					appMain.lidoc[k].getLines().setBaseLine(Integer.valueOf(v.getOd().getValuethrheader(i, "�����к�").toString())-1);
					//JOptionPane.showMessageDialog(null,Double.valueOf(v.getOd().getValuethrheader(i, "���").toString()));
					//appMain.lidoc[k].getLines().setLineTotal(Double.valueOf(v.getOd().getValuethrheader(i, "���").toString()));				
					//appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Price").setValue(Double.valueOf(v.getOd().getValuethrheader(i, "����").toString()));	
					//appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Linetotal").setValue(Double.valueOf(v.getOd().getValuethrheader(i, "���").toString()));	
					appMain.lidoc[k].getLines().setFreeText( v.getOd().getValuethrheader(i, "��ע")==null?"":v.getOd().getValuethrheader(i, "��ע").toString());
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Mtmd").setValue((v.getOd().getValuethrheader(i, "�����׶�")==null||v.getOd().getValuethrheader(i, "�����׶�").toString()=="")?"0":v.getOd().getValuethrheader(i, "�����׶�").toString());						
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "����").toString()));
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Zc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "�ܳ�").toString()));
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Snstatus").setValue("N");
					
					try{
						appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Gs").setValue( v.getOd().getValuethrheader(i, "��װ����").toString());
						}
						catch(ComFailException e0)
						{ 
							JOptionPane.showMessageDialog(null,"������������������");
							System.out.println("��Ʊsbo�ӿڳ���");
						}		
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_SNid").setValue(Integer.valueOf( v.getOd().getValuethrheader(i, "���").toString()));	
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Gjjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "��װ����").toString()));
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Mjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "�׼۸�")==null?"0":v.getOd().getValuethrheader(i, "�׼۸�").toString()));
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Tsjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "����۸�").toString()));
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Jgf").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "�ӹ���")==null?"0":v.getOd().getValuethrheader(i, "�ӹ���").toString()));
					if(v.getOd().getValuethrheader(i, "�Ƿ񻻻�")!=null&&(v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString().equals("Y")))
					{
						appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Sfhh").setValue((v.getOd().getValuethrheader(i, "�Ƿ񻻻�")==null||v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString()=="")?"N":v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString());	
					}
					else{
					    appMain.lidoc[k].getLines().getUserFields().getFields().item("U_Sfhh").setValue((v.getOd().getValuethrheader(i, "�Ƿ񻻻�")==null||v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString()=="")?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "�Ƿ񻻻�")).getValue().toString());
					}
					appMain.lidoc[k].getLines().getUserFields().getFields().item("U_djNo").setValue(String.valueOf(docentry));
					if(v.getOd1().ds.getCnValue().equals("�޸�"))
					{
                    	appMain.lidoc[k].getLines().getUserFields().getFields().item("U_djNozj").setValue(v.getOd().getValuethrheader(i, "׷�ӵ���").toString());		
					}
					else{
					    appMain.lidoc[k].getLines().getUserFields().getFields().item("U_djNozj").setValue(Ndoce);	
					}				
					appMain.lidoc[k].getLines().add();	
				   }
				catch(NullPointerException e0){	
					 JOptionPane.showMessageDialog(null,"��"+Integer.valueOf(i+1)+"���������߽�����벻�Ϸ�������");
					 return;
				}
				catch(ComFailException e0)
				{					
					System.out.println("��Ʊsbo�ӿڳ���");
				}		
				break;				
				}	
			}
		}
		for(int k=0;k<appMain.lidoc.length;k++)
		{
			appMain.lRetCode=appMain.lidoc[k].add();		
			if(appMain.lRetCode!=0)
			{ 
				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
				 hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
	 			  dbu.exeSql(hql);
				 if (appMain.lErrCode != -4006) // Incase adding an order failed
	             {
					 JOptionPane.showMessageDialog(null,"��Ӳݸ�ʧ��"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	             }
				 else
				 {					
					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
				 }
			}
			else
			{				
				 hql1="select U_enable from dbo.[@SMS] where code='DELSN' ";
				 ob1=appMain.lt.sqlclob(hql1,0,1);
			
				 hql2 = "select name from [@USERGROUP] where name<>'�ֿ���' and code=" +
					   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
				 ob2=appMain.lt.sqlclob(hql2,0,1);
				
				 if(ob1[0][0].toString().equals("Y")&&(ob2==null||ob2.length==0))
				 {
					//JOptionPane.showMessageDialog(null,docentry+"������к�");
					SNL snl=new SNL(v.getDsv());		
				    snl.createsdra(v.getDsv(),true,false,"","O",ob[k][0].toString(),String.valueOf(docentry));
				    JOptionPane.showMessageDialog(null,docentry+"���к���ӳɹ�");
			     }				
				//JOptionPane.showMessageDialog(null,docentry+"��ӳɹ�");
				System.out.println("�ɹ�"+appMain.oCompany.getNewObjectKey()+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				appMain.lidoc[k].release();
				//JOptionPane.showMessageDialog(null,appMain.odelNotes==null);
			}	
		}	
		JOptionPane.showMessageDialog(null,docentry+"��ӳɹ�");
		
	
		/*if(appMain.oCompany.isInTransaction())
			appMain.oCompany.endTransaction(SBOCOMConstants.BoWfTransOpt_wf_Commit);*/
		
		v.getOd1().setDs(docTitleStatus.add);
		v.getOd1().setDocTitleStatus(v);
		v.getOd().setDocLineStatus(docLineStatus.add);
		v.getOd().setGridStatus(docLineStatus.add);
		dmv.setadd();
	}

	@Override
	public Integer read(int id, String dod) {
		// TODO Auto-generated method stub
	
	   return id;
	}

	@Override
	public void update(OinvView V) {
		// TODO Auto-generated method stub
		
	}
	/**
	    * this operation is nonsynchronous,which means that is only one person can do this at the same time
	    */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 hql = "SELECT distinct a.docStatus from Odrf a,Drf1 b where a.docEntry=b.docEntry  and b.U_DjNo='"+id+"'";
	     ob = appMain.lt.sqlclob(hql,0,1); 
	     if(ob==null||ob.length==0)
	     {
	    	 JOptionPane.showMessageDialog(null,"��ɾ���ݸ岻����ɾ��");
			  return;
	     }
		if(ob[0][0].toString().equals("C"))
		{
		   JOptionPane.showMessageDialog(null,"�ѹرղݸ岻����ɾ��");
		   return;
		}
		 /* hql = "select  UBhdSd from bcsd";
		  ob=appMain.lt.clob(hql,0,1);
		 if(ob[0][0].toString().equals("Y"))
		 {
			 JOptionPane.showMessageDialog(null,"�����û����ڲ��������ݱ����������Ժ�");
			 return;
		 }
		 else{
			  hql = "update  dbo.[@BCSD] set U_bhd_sd='Y'";
 			  dbu.exeSql(hql);
		 }*/
		 hql = "SELECT distinct a.docEntry from Odrf a,Drf1 b where a.docEntry=b.docEntry  and b.U_DjNo='"+id+"'";
	     ob = appMain.lt.sqlclob(hql,0,500); 
	    
	     for(int i=0;i<ob.length;i++)
	     { 
	    	 try {
		    	 appMain.qodelNotes=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
				} catch (SBOCOMException e1) {
					// TODO Auto-generated catch block	
					// hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
		 			 // dbu.exeSql(hql);
					e1.printStackTrace();			
				}	
	    	 try{
	          if(appMain.qodelNotes.getByKey(Integer.valueOf(ob[i][0].toString())))
	    	  {
	        	  appMain.lRetCode= appMain.qodelNotes.remove();	
	        	  if(appMain.lRetCode!=0)
	        	  {
	        		 appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
	  				 appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();	  					  				
	  				 JOptionPane.showMessageDialog(null,"ɾ���ݸ�ʧ��"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	  	           
	        	  }
	        	  else
	        	  {
	        		  appMain.qodelNotes.release();
	        		  // JOptionPane.showMessageDialog(null,ob[i][0].toString()+"ɾ��"); 
	        	  }
	        	
	    	  }
	    	 }
	    	 catch(ClassCastException e0){
	    		// hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
	 			// dbu.exeSql(hql);
	    	 }
	     }
	     hql = "delete from  dbo.[@DESN] where ifdraft='1' and docentry='"+id+"'";
		 dbu.exeSql(hql);
	    // appMain.qodelNotes.release();
	    // hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
		// dbu.exeSql(hql);
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
      	  JOptionPane.showMessageDialog(null,"�����Զ������������������Ȩ ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
        	 hql = "SELECT a.U_DjNo from Drf1 a,Odrf b where a.docEntry=b.docEntry and b.objType='13' " +
     		 		"and a.U_DjNo is not null and b.docstatus<>'C' order by a.U_DjNo";
        }
        else{
        	 hql = "SELECT a.U_DjNo from Drf1 a,Odrf b where a.docEntry=b.docEntry and b.objType='13' " +
     		 		"and a.U_DjNo is not null and b.docstatus<>'C' " +
     		 		" and  b.usersign in (select userid from ousr where u_usergroup=" +
	      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) "+
	 				" order by a.U_DjNo";
        }		
        ob = appMain.lt.sqlclob(hql,0,1);
        if(ob==null||ob.length==0)
        {     	 
           return null;
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
      	  JOptionPane.showMessageDialog(null,"�����Զ������������������Ȩ ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
        	hql = "SELECT  max(b.U_DjNo) from Odrf a,Drf1 b where a.docEntry=b.docEntry and b.U_DjNo<'"+id+"'" +
    			  " and b.U_DjNo is not null and a.docstatus<>'C' ";
        }
        else{
        	hql = "SELECT  max(b.U_DjNo) from Odrf a,Drf1 b where a.docEntry=b.docEntry and b.U_DjNo<'"+id+"'" +
    			  " and b.U_DjNo is not null and a.docstatus<>'C' "+
    			  " and  a.usersign in (select userid from ousr where u_usergroup=" +
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
      	  JOptionPane.showMessageDialog(null,"�����Զ������������������Ȩ ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
        	hql = "SELECT  b.U_DjNo from Odrf a,Drf1 b where a.docEntry=b.docEntry and b.U_DjNo>'"+id+"' " +
    			  "and b.U_DjNo is not null  and a.docstatus<>'C' order by " +
    			  "b.U_DjNo ";
        }
        else{
        	hql = "SELECT  b.U_DjNo from Odrf a,Drf1 b where a.docEntry=b.docEntry and b.U_DjNo>'"+id+"' " +
    			  "and b.U_DjNo is not null  and a.docstatus<>'C' " +
    			  " and  a.usersign in (select userid from ousr where u_usergroup=" +
	      	  	  "(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) " +
    			  " order by b.U_DjNo "; 
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
      	  JOptionPane.showMessageDialog(null,"�����Զ������������������Ȩ ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
        	hql = "SELECT a.U_DjNo from Drf1 a,Odrf b  where a.docEntry=b.docEntry and b.objType='13' and b.docstatus<>'C' " +
    		  	  " order by a.U_DjNo desc";
        }
        else{
        	hql = "SELECT a.U_DjNo from Drf1 a,Odrf b  where a.docEntry=b.docEntry and b.objType='13' and b.docstatus<>'C' " +
        		  " and  b.usersign in (select userid from ousr where u_usergroup=" +
   	      	  	  "(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) "+
    		  	  " order by a.U_DjNo desc";
        }
		  
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {     	 
              return null;
          }
          else{ 
       	   return Integer.valueOf(ob[0][0].toString());
          }
	}

	@Override
	public void setValues(OinvView v, Integer id,String dod) {
		// TODO Auto-generated method stub
		if(id==null)
		{
			return;
		}		
		if(dod.equals("�ݸ�"))	  
		{
			try {
				idoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oDrafts);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
			
			hql="select docentry from drf1 where U_djNo='"+id+"'";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob==null||ob.length==0)
			{
				return;
			}
			docentry=Integer.valueOf(ob[0][0].toString());
		    System.out.println(docentry);
			idoc.getByKey(docentry);			
			idoc.getLines().setCurrentLine(0);		
		    v.getTxt_type().setText("�ݸ�");
			 hql="select 0,a.u_snid,a.docEntry,lineNum=(a.lineNum+1),a.baseEntry,baseLine=(a.baseLine+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
		 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
                "a.U_NumPerUnit,a.whsCode,a.unitmsr2,a.U_Gs,U_SGs=0,a.U_Gjjg,a.quantity,U_SQuantity=0,a.price,a.U_Zc,a.U_Mjg,"+
                " a.lineTotal,a.U_Zz,"+
                " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
                " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Drf1 a "+	
                " inner join Odrf b on a.docEntry=b.docEntry "+
		 		"where a.U_DjNo='"+id+"' and a.basetype='17' "+
			    " and a.linestatus='O' ";
				 v.getOd().updatetable(hql,0);	
				 hql = "select docStatus,case when wddStatus='W' then '������' when wddStatus='Y' then '������' " +
				 		"when wddStatus='N' then '�Ѿܾ�' else '��' end " +
				 		" from Odrf where docEntry='"+idoc.getDocEntry()+"'";
			     ob = appMain.lt.sqlclob(hql,0,1); 
			     v.getTxt_status().setText(ob[0][0].toString().equals("C")?"����":"δ��"+"-"+ob[0][1].toString());	
				 hql = "SELECT a.U_slpcode1,b.slpname from odrf a inner join oslp b on a.U_slpcode1=b.slpcode where docEntry='"+idoc.getDocEntry()+"'";
			     ob = appMain.lt.sqlclob(hql,0,1); 
		         if(!(ob==null||ob.length==0))
		         {
			          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
			          v.getCom_sales1().setEditable(true);
			 		  v.getCom_sales1().setSelectedItem(Cbi);
		         }
		         else{
		        	ComboBoxItem  Cbi=new ComboBoxItem(-1,"-������Ա��-");
		        	v.getCom_sales1().setEditable(true);
		    		v.getCom_sales1().setSelectedItem(Cbi);
		         }
		         v.getCom_sales1().setEditable(false);
		         hql = "SELECT u_snstatus from drf1 where u_djno='"+id+"'";
		         ob = appMain.lt.sqlclob(hql,0,1); 
		         if(!(ob==null||ob.length==0))
		         {
		        	if(ob[0][0].toString().equals("Y"))
		        	{
		        		v.getBtn_upstatus().setEnabled(false);
		        	}
		        	else{
		        		v.getBtn_upstatus().setEnabled(true);
		        	}
		        		
		         }
		         else{
		        	JOptionPane.showMessageDialog(null,"û�д�ɨ��״̬��Ϣ ");
		        	return;
		         }
		         hql = "select U_enable from [@SMS] where code='DELSN'";
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
					   		"inner join drf1 b on a.docentry=b.U_djno and a.linenum=b.u_snid " +
					   		"inner join odrf c on b.docentry=c.docentry " +
					   		"where c.objtype='13' and b.u_djno='"+v.getTxt_docn().getText().trim()+"' and a.Ifdraft='1' and  a.objtype='13' ";

							v.getDsv().getOd().updatetable(hql, 0);	
						    v.getJta_SN().setText("");
							 ob=appMain.lt.sqlclob(hql,0,3000);
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
		else
		{
			try {
				idoc=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oInvoices);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();			
			}	
			hql="select docentry from inv1 where U_djNo='"+id+"'";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob==null||ob.length==0)
			{
				return;
			}
			docentry=Integer.valueOf(ob[0][0].toString());
			idoc.getByKey(docentry);
			idoc.getLines().setCurrentLine(0);
			docentry=Integer.valueOf(idoc.getLines().getUserFields().getFields().item("U_djNo").getValue().toString());
			v.getTxt_type().setText("��Ʊ");
			 hql="select 0,a.u_snid,a.docEntry,lineNum=(a.lineNum+1),a.baseEntry,baseLine=(a.baseLine+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
			     "a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
                 "a.U_NumPerUnit,a.whsCode,a.unitmsr2,a.U_Gs,U_SGs=0,a.U_Gjjg,a.quantity,U_SQuantity=0,a.price,a.U_Zc,a.U_Mjg,"+
                 " a.lineTotal,a.U_Zz,"+
                 " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
                 " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Inv1 a "+		
                 " inner join Oinv b on a.docEntry=b.docEntry "+
		 	     "where U_DjNo='"+id+"' ";			
				 v.getOd().updatetable(hql,0);	
				 hql = "select docStatus,case when wddStatus='W' then '������' when wddStatus='Y' then '������' " +
				 		"when wddStatus='N' then '�Ѿܾ�' else '��' end " +
				 		" from Oinv where docEntry='"+idoc.getDocEntry()+"'";
			         ob = appMain.lt.sqlclob(hql,0,1); 
					 v.getTxt_status().setText(ob[0][0].toString()=="C"?"����":"δ��");
				 hql = "SELECT a.U_slpcode1,b.slpname from oinv a inner join oslp b on a.U_slpcode1=b.slpcode where docEntry='"+idoc.getDocEntry()+"'";
			        ob = appMain.lt.sqlclob(hql,0,1); 
			        if(!(ob==null||ob.length==0))
			        {
			          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
			          v.getCom_sales1().setEditable(true);
			 		  v.getCom_sales1().setSelectedItem(Cbi);
			        }
			        else{
			        	ComboBoxItem  Cbi=new ComboBoxItem(-1,"-������Ա��-");
			        	 v.getCom_sales1().setEditable(true);
			    		 v.getCom_sales1().setSelectedItem(Cbi);
			        }
			        v.getCom_sales1().setEditable(false);
			        //���кſ���
			        hql = "select U_enable from [@SMS] where code='DELSN'";
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
					   		"inner join inv1 b on a.docentry=b.U_djno and a.linenum=b.u_snid " +
					   		"inner join oinv c on b.docentry=c.docentry " +
					   		"where c.objtype='13' and b.u_djno='"+v.getTxt_docn().getText().trim()+"' and a.Ifdraft='0' and a.objtype='13' ";						  
							
						   v.getDsv().getOd().updatetable(hql, 0);		
						   v.getJta_SN().setText("");
							 ob=appMain.lt.sqlclob(hql,0,3000);
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
		 v.getTxt_date().setDate(idoc.getDocDate());	
		 idoc.getLines().setCurrentLine(0);
		
		 v.getTxt_docn().setText(String.valueOf(id));
		 v.getJta_memo().setText(idoc.getComments());
			 
		 hql = "SELECT slpName from Oslp where slpCode='"+idoc.getSalesPersonCode()+"'";
        ob = appMain.lt.sqlclob(hql,0,1); 
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
		 v.getTxt_ter().setText(idoc.getUserFields().getFields().item("U_Dq").getValue().toString());
				
		 idoc.release();
	  //   ComThread.Release();
		 if(v.getOd().verification()==false)
	     {
	    	return;
	     }
	}

	@Override
	public void close(OinvView V) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ctarget(OinvView v,Integer dh) {
		// TODO Auto-generated method stub	
		 if(v.getOd().verification()==false)
	     {
	    	return;
	     }	
		 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
	 	 ob = appMain.lt.sqlclob(hql,0,1);
		 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
	 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
	 	  	 "and b.name='���ɷ�����' and a.enable='1'";
	 	 ob = appMain.lt.sqlclob(hql,0,1);
         if(ob==null||ob.length==0)
         {
        	 JOptionPane.showMessageDialog(null, "�޴�Ȩ��");
         	  return;
         }	
		//��֤���кţ������ܽ���������
		 hql = "select U_enable from [@SMS] where code='DELSN'";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||(ob!=null&&ob.length==0))
		 {
			 JOptionPane.showMessageDialog(null,"���ݵ����к�����δ����");
			 return;
		 }
		 if(ob[0][0].toString().equals("Y"))
		 {
			 if(!advsn.bfcverification(v))
			 {
				JOptionPane.showMessageDialog(null,"���к���֤δͨ��,�޷�������ʽ����");
				return;
			 }
		 }
		if(v.getTxt_type().getText().equals("��Ʊ"))
		{
			JOptionPane.showMessageDialog(null,"��Ʊ�����Խ������ɲ���");
			return;
		}
		//��ֱ���ٴη��Ͷ���
		else if(v.getTxt_type().getText().equals("�ݸ�")&&v.getTxt_status().getText().split("-").length>1&&
			   (v.getTxt_status().getText().split("-")[1].equals("������")
			     ||v.getTxt_status().getText().split("-")[1].equals("�Ѿܾ�")))
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
		     JOptionPane.showMessageDialog(null,"�����������ٴη���");		     
		}
		else if((v.getTxt_type().getText().equals("�ݸ�")&&v.getTxt_status().getText().split("-").length>1&&
			  v.getTxt_status().getText().split("-")[1].equals("������"))
			  ||(v.getTxt_type().getText().equals("�ݸ�")&&v.getTxt_status().getText().equals("����")))
		{			
			 /* hql = "select UBhdSd from bcsd";
			  ob=appMain.lt.clob(hql,0,1);
			 if(ob[0][0].toString().equals("Y"))
			 {
				 JOptionPane.showMessageDialog(null,"�����û����ڲ��������ݱ����������Ժ�");
				 return;
			 }			
		    */
		
	    	 Dra2Doc dd=new Dra2Doc();	
	    	 dd.createmergoinvs(v,0, false, Integer.valueOf(dh));		
	    	 JOptionPane.showMessageDialog(null,"���ɷ�Ʊ�ɹ�");
		
		     v.getTxt_type().setText("��Ʊ");
		    // hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
			 //dbu.exeSql(hql);
			 
		}
		else if(v.getTxt_type().getText().equals("�ݸ�")&&v.getTxt_status().getText().split("-").length>1&&v.getTxt_status().getText().split("-")[1].equals("��"))
		{
			//execute workflow
			
			 Session session = MdbHibernateUtils.getSession();
		      Transaction t = session.beginTransaction();{
		       try {
		    	   @SuppressWarnings("deprecation")
				   Connection con = session.connection(); 		    	
		    	   hql = "{call zdy_workflow(?,?,?,?)}";
			         try {	        	
						cstmt = con.prepareCall(hql);				
						cstmt.setInt(1,dh);	
						cstmt.setInt(2,13);	
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
		 			/* hql = "select UBhdSd from bcsd";
					  ob=appMain.lt.clob(hql,0,1);
					 if(ob[0][0].toString().equals("Y"))
					 {
						 JOptionPane.showMessageDialog(null,"�����û����ڲ��������ݱ����������Ժ�");
						 return;
					 }	*/
		 				
		 			     Dra2Doc dd=new Dra2Doc();	
				    	 dd.createmergoinvs(v,0, false, Integer.valueOf(dh));		
				    	 JOptionPane.showMessageDialog(null,"���ɷ�Ʊ�ɹ�");
		 			
		 			     v.getTxt_type().setText("��Ʊ");
		 			     //hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
		 				 //dbu.exeSql(hql);
		 		  }
		 		  else if(rev==1||rev==2)
		 		  {		    		 		    		 		    		  		    		 
		 			  //set value for wflist1 end
		 			  // add  wflist	
		 			  //wfs=appMain.wfd.setWflists(Integer.valueOf(v.getTxt_docn().getText()), "@WFDST", rev.shortValue());
		 			  //for(int i=0;i<wfs.length;i++)
		 			 // {
		 			 // hql = "update  dbo.[@BCSD] set U_bhd_sd='N'";
		 			  //dbu.exeSql(hql);
		 			  if(appMain.wfd.setWflist("13",String.valueOf(dh), "@WFDST", rev.shortValue())==null)
		    		  {
		    			  return;
		    		  }		 		 			 
		 	   		  appMain.wfd.add(appMain.wfd.setWflist("13",String.valueOf(dh), "@WFDST", rev.shortValue()));

		 	   	      hql = "update a  set a.wddstatus='W'  from odrf a inner join drf1 b on a.docentry=b.docentry "+
                        " where b.U_djNo='"+String.valueOf(dh)+"'";
	 			      dbu.exeSql(hql);
		 	   		  hql = "SELECT  isnull(max(docentry),0) FROM  [@wflist] ";
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
		 			       JOptionPane.showMessageDialog(null,"����"+rev.toString()+"������");
		 			  //}		 		       		 			
		 		  }
		 		  else
		 		  {
		 			  
		 		  }
		 	 }
		}
				
	
			
	}
	@Override
	public void print(OinvView v){
		// TODO Auto-generated method stub	
		 if(v.getOd().verification()==false)
	     {
			 int i= JOptionPane.showConfirmDialog(v.getParent(), "�Ƿ������ӡ");
			 if(i!=0)
			 {
				 return;
			 }			 
	     }
		ClassicEngineBoot.getInstance().start();
		
		 // final ClassLoader classloader = this.getClass().getClassLoader();
	      //final URL reportDefinitionURL = classloader.getResource("file://d:/preport/���۵��ݸ�.prpt");	     
	      
		try {
			final DriverConnectionProvider provider = new DriverConnectionProvider();
			 URL reportDefinitionURL = null;
			if(v.getOpv().getComb_print().getSelectedItem().toString().equals("�ݸ�")&&v.getTxt_type().getText().equals("�ݸ�"))
			{
			  reportDefinitionURL = new URL("file:///d:/preport/���۵��ݸ�.prpt");
			}
			else if(v.getOpv().getComb_print().getSelectedItem().toString().equals("����")&&v.getTxt_type().getText().equals("��Ʊ"))
			{
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='OINVDOCP' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "�޴�Ȩ��");
	        	   return;
	             }	
				
				String tb;				
				 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
				   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\CrystalReportjhlx.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
				   "Printfhdoc";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();
			}
			else if(v.getOpv().getComb_print().getSelectedItem().toString().equals("��׷�ӺŴ�ӡ�ݸ�")&&v.getTxt_type().getText().equals("�ݸ�"))
			{
				//reportDefinitionURL = new URL("file:///d:/preport/��׷�ӺŴ�ӡ�ݸ�.prpt");
				String tb;				
				 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
				   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\��׷�ӺŴ�ӡ�ݸ�.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
				   "Printpcgzjdoc";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();	
	             hql = "update  a set a.U_Ydy='Y' from drf1 a inner join odrf b on a.docentry=b.docentry " +
			    	  	"WHERE a.U_djNo='" + v.getTxt_docn().getText() + "' and b.objtype='13'";
				    dbu.exeSql(hql);

			}
			else if(v.getOpv().getComb_print().getSelectedItem().toString().equals("��׷�ӺŴ�ӡ����")&&v.getTxt_type().getText().equals("��Ʊ"))
			{
				//reportDefinitionURL = new URL("file:///d:/preport/��׷�ӺŴ�ӡ����.prpt");
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='OINVDOCP' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "�޴�Ȩ��");
	        	   return;
	             }	
	             hql="select isnull(U_Ydy,'N') from inv1 where U_djNo='" + v.getTxt_docn().getText() + "'";
	             ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "��ѯ�쳣�����ܵ���"+v.getTxt_docn().getText()+"û��ѯ���Ƿ��Ѵ�ӡ");
	        	   return;
	             }	
	             else if(ob[0][0].toString().equals("N"))
				 {
	            	 String tb;				
					 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
					   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\��׷�ӺŴ�ӡ����.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
					   "Printpjhzjdoc";				
					 ActiveXComponent dotnetCom = null;    
		             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
		             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
		             System.out.println(var);
		             dotnetCom.safeRelease();	
	
			    	 hql = "update INV1 set U_Ydy='Y' WHERE U_djNo='" + v.getTxt_docn().getText() + "'";
					 dbu.exeSql(hql);
				 }
				 else if(ob[0][0].toString().equals("Y"))
				 {
					 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
				 	 ob = appMain.lt.sqlclob(hql,0,1);
					 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
				 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
				 	  	 "and b.code='OINVDOCPM' and a.enable='1'";
				 	 ob = appMain.lt.sqlclob(hql,0,1);
				 	 if(ob==null||ob.length==0)
		             {
		        	   JOptionPane.showMessageDialog(null, "�޶�δ�ӡ�������ܵ�Ȩ�ޣ�����ϵϵͳ����Ա");
		        	   return;
		             }	
				 	 else{
				 		String tb;				
						 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
						   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\��׷�ӺŴ�ӡ����.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
						   "Printpjhzjdoc";				
						 ActiveXComponent dotnetCom = null;    
			             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
			             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			             System.out.println(var);
			             dotnetCom.safeRelease();	 
				 	 }
				 }
				 else{}
				
			}
			else if(v.getOpv().getComb_print().getSelectedItem().toString().equals("���ܵ�����ݸ�")&&v.getTxt_type().getText().equals("�ݸ�"))
			{				   
				String tb;				
				 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
				   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\���ܵ�����ݸ�.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
				   "Printpdra";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();	         
			}
			else if(v.getOpv().getComb_print().getSelectedItem().toString().equals("���ܵ�������")&&v.getTxt_type().getText().equals("��Ʊ"))
			{			
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='OINVDOCP' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "�޴�Ȩ��");
	        	   return;
	             }	
				String tb;				
				 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
				   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\���ܵ�������.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
				   "Printpdoc";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();	           
			}
			else if(v.getOpv().getComb_print().getSelectedItem().toString().equals("���ܵ�������2")&&v.getTxt_type().getText().equals("��Ʊ"))
			{		
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='OINVDOCP' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "�޴�Ȩ��");
	        	   return;
	             }	
				 String tb;				
				 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"
				   +appMain.config.getDbuserpas()+"+"+"d:\\ncr\\���ܵ�������2.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+
				   "Printpdoc2";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();	             
			}
			else{}
			 // Parse the report file
		     final ResourceManager resourceManager = new ResourceManager();
		     resourceManager.registerDefaults();
		     final Resource directly;
		     resourceManager.registerDefaults();
			 directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
			 
			     MasterReport report = (MasterReport) directly.getResource();
			     provider.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			     provider.setProperty("user",appMain.config.getDbusername());
			     provider.setProperty("password", appMain.config.getDbuserpas());		
			     provider.setUrl(appMain.config.getDserver()+":1433; DatabaseName="+appMain.oCompany.getCompanyDB());	
					
			     /*SQLReportDataFactory df = new SQLReportDataFactory(provider);	
			     df.setQuery("dra_oinv",
		         "SELECT T0.[SlpCode] AS '����Ա����',T1.SlpName AS  '����Ա' ,T0.[DocEntry] AS '���ݴ���', T0.BaseEntry AS  '��������' ," +
		         "convert(varchar(100),T0.DocDate,23) AS '����',"+
				" T0.BaseLine+1 AS '�����к�',T5.[CardCode] AS '������',T2.CardName AS '�������', T0.[LineNum]+1 AS '�к�', "+
				" T0.[ItemCode] AS '���Ϻ�',   T0.[unitMsr] AS '��λ', T0.[WhsCode] AS '�ֿ�', cast(T0.Quantity as decimal(12,2)) AS '����', "+
				" cast(T0.[Price] as decimal(12,2)) AS '��λ�۸�',cast(T0.U_Zc AS decimal(12,2)) AS '�ܳ�', "+
				" cast(T0.U_Mjg as decimal(12,2)) AS '�׼۸�',cast(T0.U_Zz as decimal(12,3)) AS '����',"+
				" cast(T0.U_Gjjg as decimal(12,2)) AS '����۸�',"+
				" cast(T0.Quantity*T0.[Price] as decimal(12,2)) AS '���',  T0.[U_Mtdl] AS '���ϴ���',  T0.[U_Mtmd] AS '�׶�', T0.CogsOcrCod AS '��������', "+ 
				" T0.[U_djNo] AS '���ܵ���',  T0.[U_djNozj] AS '׷�Ӻ�' "+ 
				" FROM  [dbo].[DRF1] T0 "+
				" INNER JOIN OSLP T1 ON T1.[SlpCode]=T0.[SlpCode]   "+
				" INNER JOIN ODRF T5 ON T5.[DocEntry]=T0.[DocEntry] "+
				" INNER JOIN OCRD T2 ON  T2.CardCode=T5.[CardCode]  "+ 
				" WHERE   T5.DocStatus='O'  AND  T0.U_djNo=${U_djNo} AND T5.cardcode  like ${cardcode}  AND  T5.CANCELED='N'");			     
			     report.setDataFactory(df);*/
			     report.getParameterValues().put("U_djNo", v.getTxt_docn().getText());
			     report.getParameterValues().put("cardcode", "%");
			     report.getParameterValues().put("continuous", "true");
			     final PreviewDialog dialog = new PreviewDialog();
			
			     dialog.setReportJob(report);
			     dialog.setBounds(0, 0, 1366, 768);
			     dialog.setModal(true);
			    // dialog.pack();
			     dialog.setVisible(true);		
			    			   
			     v.getOpv().dispose();
		} catch (ResourceLoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceKeyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}catch(NullPointerException e){
			
		}catch (Exception ex) {    
           ex.printStackTrace();    
       }  finally{
    	   
       }
		
		/****birt report
		 IReportEngine engine=null;
	     EngineConfig config = null;
	     IReportRunnable design = null;  
	     
	     try
	     {
           //Configure the Engine and start the Platform
           config = new EngineConfig( );
           config.setEngineHome( "D:\\birt-runtime-4_2_1\\ReportEngine" );
           config.setLogConfig("D:\\birt-runtime-4_2_1\\logs", java.util.logging.Level.FINEST);
        
           Platform.startup( config );
           IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
           engine = factory.createReportEngine( config );
           engine.changeLogLevel( Level.WARNING );
	     }
	     catch( Exception ex )
	     {
	         ex.printStackTrace();
	     }
	     
	    

	       //Open the report design 
	     try {
	    	 if(v.getOpv().getComb_print().getSelectedItem().toString()=="���ܵ�����ݸ�")
	    	 {
			  design = engine.openReportDesign("D:/birt-runtime-4_2_1/WebViewerExample/report/draft_of_summary_sheet_schedule.rptdesign");
			 
	    	 }
	    	 ReportDesignHandle report = (ReportDesignHandle) design.getDesignHandle( );
	    	 Breport.buildReport(report, "org.eclipse.birt.report.data.oda.jdbc", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://localhost:1433; DatabaseName="+appMain.oCompany.getCompanyDB(), appMain.config.getDbusername(), appMain.config.getDbuserpas() ); 
		        
		       //Create task to run and render the report,
		       IRunAndRenderTask task = engine.createRunAndRenderTask(design); 
		        
		       //Set parameters for the report
		      // task.setParameterValues(parameters);
		       //Alternatively set each seperately
		       task.setParameterValue("U_djNo", v.getTxt_docn().getText());
		       task.setParameterValue("U_djNozj", "%");
		       task.validateParameters();
		        
		       HTMLRenderOption options = new HTMLRenderOption();
		        
		       //Set ouptut location
		       options.setOutputFileName("D:/birt-runtime-4_2_1/WebViewerExample/report/draft_of_summary_sheet_schedule.pdf");
		        
		       //Set output format
		       options.setOutputFormat("PDF");
		       task.setRenderOption(options);
		        
		       //run the report and destroy the engine
		       //Note - If the program stays resident do not shutdown the Platform or the Engine
		       task.run();
		       //ViewerPlugin.getDefault( ).getPluginPreferences( ).setValue("APPCONTEXT_EXTENSION_KEY", "MyAppContext");
		      // Browser browser = new Browser(parent, SWT.NONE);
		       // Use the filename of your report
		      // WebViewer.startup();
		       //WebViewer.display("D:/birt-runtime-4_2_1/WebViewerExample/report/draft_of_summary_sheet_schedule.rptdesign", WebViewer.HTML,true);
		    
		       task.close();
		       engine.destroy();
		       Platform.shutdown();
		       
		       //Browser browser; 
		      // Shell sShell = null;
		       //browser= new Browser(sShell, SWT.NONE); 
		      // WebViewer.display(report, "PDF");	
		       // final PreviewDialog dialog = new PreviewDialog();
		       v.getOpv().dispose();
		       System.out.println("Finished"); 
	       } catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   } 
	        
	     ****/ 
	}
	public OinvView getV() {
		return v;
	}

	public void setV(OinvView v) {
		this.v = v;
	}

	
}
