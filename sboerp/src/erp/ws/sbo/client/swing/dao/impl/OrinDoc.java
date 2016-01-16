package erp.ws.sbo.client.swing.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import erp.ws.sbo.client.swing.view.Orin.OrinView;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.SNL;

public class OrinDoc implements IDoc<OrinView>{

	private DocMenuView dmv=DocMenuView.getdmv();
	private OrinView v;
	private String hql,No;
	private Object[][] ob;
	private Object[][] ob1;
	private int docentry;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private Integer Ndoce,Ndoce1;
	private IDocuments[] idoc;
	private IDocuments idoc1;
	private OrinAdvSN advsn=new OrinAdvSN();
   /**
    * this operation is nonsynchronous,which means that is only one person can do this at the same time
    * because computer must Determine the max docentry 
    * 
    */
	@Override
	public void create(OrinView v) {
		// TODO Auto-generated method stub
		hql = "select U_enable from [@SMS] where code='CRESN'";
		  ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||(ob!=null&&ob.length==0))
		 {
			 JOptionPane.showMessageDialog(null,"���ݵ����к�����δ����");
			 return;
		 }
		 if(ob[0][0].toString().equals("Y")&&(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0")
		   ||((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1")))
		 {
			
			 if(!advsn.verification(v))
			 {
				return;
			 }
		 }
		
		 if(v.getOd().verification()==false)
	     {
	    	return;
	     }

		if(v.getCom_sales().getSelectedItem()==null)
		{
			JOptionPane.showMessageDialog(null,"����������Ա");
			 return;			
		}
		if(v.getOd1().ds.getCnValue().equals("����"))
		{
			Ndoce=1;
			Ndoce1=0;
			hql="select isnull(max(U_DjNo),0) from Rin1";
			ob=appMain.lt.sqlclob(hql, 0, 1);
			if(ob[0][0].toString().equals("0"))
			{
				Ndoce1+=1;			
			}
			else{
				hql="select substring(Convert(nvarchar(20),max(U_DjNo)),1,len(max(U_DjNo))-3) from Rin1";
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
		}
		if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0")||((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2"))
		{
		 String[] s={"��������"};		
		 ob=v.getOd().Filterdrecords(true, s);
		}
		else if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
		{
		  String[] s={"������"};		
		  ob=v.getOd().Filterdrecords(true, s);			
		}
		//JOptionPane.showMessageDialog(null,"��Ҫ���"+ob.length+"�ŵ���");		
		idoc=new IDocuments[ob.length];
	   
		for(int k=0;k<idoc.length;k++)
		{
			if(ob[k][0]==null||ob[k][0].toString().equals(""))
			{
				continue;
			}
			try {
				idoc[k]=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oCreditNotes);
			} catch (SBOCOMException e1) {
				// TODO Auto-generated catch block			
				e1.printStackTrace();		
			}	
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0")||((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2"))
			{
			   hql = "select  cardcode from oinv where docentry='"+ob[k][0].toString()+"'";
			   ob1=appMain.lt.sqlclob(hql,0,1);
			   idoc[k].setCardCode(ob1[0][0].toString());	
			}
			else{
				idoc[k].setCardCode(ob[k][0].toString());	
			}
			try{
			    idoc[k].setSalesPersonCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));				
			}
			catch(ClassCastException e2)
			{
				JOptionPane.showMessageDialog(null,"��ѡ������Ա");	
				return;
			}
			idoc[k].setDocDate(new Date());		
			idoc[k].setDocObjectCode(SBOCOMConstants.BoObjectTypes_Document_oCreditNotes);
			idoc[k].setComments(v.getJta_memo().getText());	
			idoc[k].getUserFields().getFields().item("U_Ysdxdjlx").setValue(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString());
			try{
			idoc[k].getUserFields().getFields().item("U_slpcode1").setValue(Integer.valueOf(v.getCom_sales1().getSelectedItem()==null?"0":((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()));			
			}
			catch(ClassCastException e2)
			{
				JOptionPane.showMessageDialog(null,"��ѡ������Ա1,����ѡ��������Ա��");	
				return;
			}
			idoc[k].getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		}
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
			 if(v.getOd().getValuethrheader(i,"��װ����")==null||(v.getOd().getValuethrheader(i,"��װ����")!=null&&new BigDecimal(v.getOd().getValuethrheader(i,"��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP))))
			 {
				continue;
			 }
		}
						
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
			
			//validating before create
			if(v.getOd().getValuethrheader(i,"�����к�")!=null&&((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))
			{
				hql="select quantity=(isnull(a.U_Gs,0)-sum(isnull(b.U_Gs,0))) from inv1 a left join rin1 b on a.docentry=b.u_baseentry" +
					" and a.linenum=b.u_baseline where a.docentry='"+v.getOd().getValuethrheader(i,"��������")+"' " +
					" and a.linenum='"+(Integer.valueOf(v.getOd().getValuethrheader(i,"�����к�").toString())-1)+"' " +
					" group by a.U_Gs"; 
				 ob1=appMain.lt.sqlclob(hql,0,1);			
				 if(Integer.valueOf(v.getOd().getValuethrheader(i,"��װ����").toString()).compareTo(Integer.valueOf(ob1[0][0].toString()))>0)
				 {  
					 JOptionPane.showMessageDialog(null,"��"+Integer.valueOf(i+1).toString()+"�а�װ����������Ʊ��װ����,����������");					
					 return;
				 }
			}
			if(v.getOd().getValuethrheader(i,"�����к�")!=null&&((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2"))
			{
				hql="select price=a.price-a.u_jgf from inv1 a " +
					" where a.docentry='"+v.getOd().getValuethrheader(i,"��������")+"' " +
					" and a.linenum='"+(Integer.valueOf(v.getOd().getValuethrheader(i,"�����к�").toString())-1)+"' "; 
				 ob1=appMain.lt.sqlclob(hql,0,1);			
				 if(new BigDecimal(v.getOd().getValuethrheader(i,"����").toString()).compareTo(new BigDecimal(ob1[0][0].toString()))>0)
				 {  
					JOptionPane.showMessageDialog(null,"��"+Integer.valueOf(i+1).toString()+"�м۸񳬳����۶�������-�ӹ���,����������");					
					return;
				 }
				 hql="select quantity=(isnull(a.quantity,0)-sum(isnull(b.quantity,0))) from inv1 a left join rin1 b on a.docentry=b.u_baseentry" +
					 " and a.linenum=b.u_baseline where a.docentry='"+v.getOd().getValuethrheader(i,"��������")+"' " +
					 " and a.linenum='"+(Integer.valueOf(v.getOd().getValuethrheader(i,"�����к�").toString())-1)+"' " +
					 " group by a.quantity"; 
				 ob1=appMain.lt.sqlclob(hql,0,1);			
				 if(Integer.valueOf(v.getOd().getValuethrheader(i,"����").toString()).compareTo(Integer.valueOf(ob1[0][0].toString()))>0)
				 {  
					JOptionPane.showMessageDialog(null,"��"+Integer.valueOf(i+1).toString()+"����������Ʊ����,����������");					
					return;
				 }
			}
			if((v.getOd().getValuethrheader(i,"��������")==null||v.getOd().getValuethrheader(i,"��������").toString().equals("")||
				v.getOd().getValuethrheader(i,"��������").toString().equals("0"))&&((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))
			{
				continue;
			}
			 if(v.getOd().getValuethrheader(i,"���ϴ���")==null||v.getOd().getValuethrheader(i,"���ϴ���").toString().equals("")
				||v.getOd().getValuethrheader(i,"������")==null||v.getOd().getValuethrheader(i,"������").toString().equals(""))
			 {
					continue;
			 }			 
			 if(v.getOd().getValuethrheader(i,"��װ����")==null||(v.getOd().getValuethrheader(i,"��װ����")!=null&&new BigDecimal(v.getOd().getValuethrheader(i,"��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP))))
			 {
				continue;
			 }
			 if(v.getOd().getValuethrheader(i,"���ϴ���")!=null&&v.getOd().getValuethrheader(i,"���ϴ���").toString()!=""&&
			   (v.getOd().getValuethrheader(i,"����")==null||new BigDecimal(v.getOd().getValuethrheader(i,"����").toString())==new BigDecimal("0")
			   ||v.getOd().getValuethrheader(i,"���")==null||new BigDecimal(v.getOd().getValuethrheader(i,"���").toString())==new BigDecimal("0")))
			{
				int hh=i+1;	
				JOptionPane.showMessageDialog(null,"��"+hh+"���������߽������Ƿ�,����");					
				return;
			}	
			 //JOptionPane.showMessageDialog(null,v.getOd().getValuethrheader(i,"������").toString()+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString());	
			 hql="select p.slpcode,q.slpname from Ocrd p inner join oslp q on p.slpcode=q.slpcode  " +	    	        
				" where p.cardCode='"+v.getOd().getValuethrheader(i,"������").toString()+"' and  p.cardType='C'"+
				" and p.slpcode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+"' " +
				" and p.u_slpcode1='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()+"' ";
	        
			 ob1 = appMain.lt.sqlclob(hql,0,1); 
	            if(ob1==null||(ob1!=null&&ob1.length==0))
		   		 {
		   			 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"��ҵ�����Ӧ��������������Ա����ȷ����������");
		   			 return;
		   		 }
			 for(int k=0;k<ob.length;k++)
			{	if(ob[k][0]==null||ob[k][0].toString().equals("")||ob[k][0].toString().equals("0"))
				{
					continue;
				}				
				 if((v.getOd().getValuethrheader(i,"��������")!=null&&v.getOd().getValuethrheader(i,"��������").toString().equals(ob[k][0].toString())&&((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))||
					(v.getOd().getValuethrheader(i,"��������")==null&&v.getOd().getValuethrheader(i,"������").toString().equals(ob[k][0].toString())&&!((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))||
					(v.getOd().getValuethrheader(i,"��������")!=null&&v.getOd().getValuethrheader(i,"��������").toString().equals(ob[k][0].toString())&&((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2")))
				{	
					 try{
					idoc[k].getLines().setShipDate((Date) v.getTxt_date().getValue());
					idoc[k].getLines().setItemCode( v.getOd().getValuethrheader(i, "���ϴ���").toString());
					idoc[k].getLines().setItemDescription( v.getOd().getValuethrheader(i, "��������").toString());
					idoc[k].getLines().setSalesPersonCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));
					idoc[k].getLines().setWarehouseCode(v.getOd().getValuethrheader(i, "�ֿ����").toString());
					idoc[k].getLines().setQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "����").toString()));
					idoc[k].getLines().getUserFields().getFields().item("U_NumPerUnit").setValue(Double.valueOf((v.getOd().getValuethrheader(i, "��λ����")==null||v.getOd().getValuethrheader(i, "��λ����").toString()=="")?"1":v.getOd().getValuethrheader(i, "��λ����").toString()));
					//idoc[k].getLines().setDiscountPercent(Double.valueOf("0"));
					idoc[k].getLines().setPriceAfterVAT(Double.valueOf(v.getOd().getValuethrheader(i, "����").toString()));					
					if(v.getOd().getValuethrheader(i, "��������")!=null&&!v.getOd().getValuethrheader(i, "��������").toString().equals("")&&!v.getOd().getValuethrheader(i, "��������").toString().equals("0"))
					{
						//idoc[k].getLines().setBaseType(SBOCOMConstants.BoObjectTypes_Document_oInvoices);
						//idoc[k].getLines().setBaseEntry(Integer.valueOf(v.getOd().getValuethrheader(i, "��������").toString()));
						//idoc[k].getLines().setBaseLine(Integer.valueOf(v.getOd().getValuethrheader(i, "�����к�").toString())-1);
						idoc[k].getLines().getUserFields().getFields().item("U_baseentry").setValue(Integer.valueOf(v.getOd().getValuethrheader(i, "��������").toString()));
						idoc[k].getLines().getUserFields().getFields().item("U_baseline").setValue(Integer.valueOf(v.getOd().getValuethrheader(i, "�����к�").toString())-1);
						
					}					
					//idoc[k].getLines().setLineTotal(Double.valueOf( v.getOd().getValuethrheader(i, "���").toString()));
					idoc[k].getLines().setFreeText( v.getOd().getValuethrheader(i, "��ע")==null?"":v.getOd().getValuethrheader(i, "��ע").toString());
					idoc[k].getLines().getUserFields().getFields().item("U_Mtmd").setValue((v.getOd().getValuethrheader(i, "�����׶�")==null||v.getOd().getValuethrheader(i, "�����׶�").toString()=="")?"0":v.getOd().getValuethrheader(i, "�����׶�").toString());						
					if(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")!=null&&(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("Y")))
					{
						idoc[k].getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")==null||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString()=="")?"N":v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString());	
					}
					else{
					    idoc[k].getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")==null||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString()=="")?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")).getValue().toString());
					}
					//idoc[k].getLines().getUserFields().getFields().item("U_Ymd").setValue((v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�")==null||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString()=="")?"Y":v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString());
					idoc[k].getLines().getUserFields().getFields().item("U_Zc").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "�ܳ�").toString()));
					idoc[k].getLines().getUserFields().getFields().item("U_Zz").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "����").toString()));				
					idoc[k].getLines().getUserFields().getFields().item("U_Gs").setValue(v.getOd().getValuethrheader(i, "��װ����").toString());						
					idoc[k].getLines().getUserFields().getFields().item("U_Gjjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "��װ����").toString()));
					idoc[k].getLines().getUserFields().getFields().item("U_Mjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "�׼۸�").toString()));
					idoc[k].getLines().getUserFields().getFields().item("U_Tsjg").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "����۸�").toString()));
					idoc[k].getLines().getUserFields().getFields().item("U_Jgf").setValue(Double.valueOf( v.getOd().getValuethrheader(i, "�ӹ���")==null?"0":v.getOd().getValuethrheader(i, "�ӹ���").toString()));
					if(v.getOd().getValuethrheader(i, "�Ƿ񻻻�")!=null&&(v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString().equals("Y")))
					{
						idoc[k].getLines().getUserFields().getFields().item("U_Sfhh").setValue((v.getOd().getValuethrheader(i, "�Ƿ񻻻�")==null||v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString()=="")?"N":v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString());	
					}
					else{
					    idoc[k].getLines().getUserFields().getFields().item("U_Sfhh").setValue((v.getOd().getValuethrheader(i, "�Ƿ񻻻�")==null||v.getOd().getValuethrheader(i, "�Ƿ񻻻�").toString()=="")?"N":((ComboBoxItem)v.getOd().getValuethrheader(i, "�Ƿ񻻻�")).getValue().toString());
					}
					if(v.getOd().getValuethrheader(i, "�ӹ���")!=null&&!v.getOd().getValuethrheader(i, "�ӹ���").toString().equals(""))
					{
						idoc[k].getLines().getUserFields().getFields().item("U_Jgf").setValue(Double.valueOf(v.getOd().getValuethrheader(i, "�ӹ���").toString()));
					}
					idoc[k].getLines().getUserFields().getFields().item("U_djNo").setValue(String.valueOf(docentry));
					idoc[k].getLines().getUserFields().getFields().item("U_djNozj").setValue(Ndoce);	
					idoc[k].getLines().getUserFields().getFields().item("U_SNid").setValue(Integer.valueOf( v.getOd().getValuethrheader(i, "���").toString()));					
					
					idoc[k].getLines().add();	
					 }
					 catch(NullPointerException e0){	
						 //hql = "update  dbo.[@BCSD] set U_ysdx_sd='N'";
						// dbu.exeSql(hql);
						 JOptionPane.showMessageDialog(null,"��"+Integer.valueOf(i+1).toString()+"���������߽�����벻�Ϸ�������");;
						 return;
					}
					catch(ComFailException e0)
					{	
						JOptionPane.showMessageDialog(null,"��װ��������������");
						System.out.println("Ӧ�մ���sbo�ӿڳ���");
					}		
					
					break;				
				}	
			}
		}
		for(int k=0;k<idoc.length;k++)
		{
			
			if(idoc[k]==null||(idoc[k]!=null&&idoc[k].getLines().getCount()<=1))
			{
				continue;
			}
			appMain.lRetCode=idoc[k].add();		
			if(appMain.lRetCode!=0)
			{ 
				appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
				appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
				
				 if (appMain.lErrCode != -4006) // Incase adding an order failed
	             {
					 JOptionPane.showMessageDialog(null,"���ʧ��"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
	             }
				 else
				 {
					 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
				 }
			}
			else
			{				
				 hql="select U_enable from dbo.[@SMS] where code='CRESN' ";
				 ob1=appMain.lt.sqlclob(hql,0,1);
				 if(ob1==null||ob1.length==0)
				 {		
					 JOptionPane.showMessageDialog(null,"�����Զ�����������˻����Ƿ��������к�");
					 return;
				 }
				 if(ob1[0][0].toString().equals("Y"))
				 {				
					 if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))
					 {
						 String s =new String(v.getJta_SN().getText());   
							Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
							Matcher m = pat.matcher(s); 
							s = m.replaceAll(""); 
						
						 hql="insert into dbo.[@desn](ifdraft,objtype,docentry,linenum,itemcode,length,sn,weight,direction,ifpasn,pasn,warehouse,cardcode,memo,workcenter,cdatetime,udatetime) "+ 
							 "select '0','14','"+String.valueOf(docentry)+"',a.linenum,a.itemcode,a.length,a.sn,a.weight,'I',a.ifpasn,a.pasn,a.warehouse,a.cardcode,a.memo,a.workcenter,a.cdatetime,getdate() from dbo.[@desn] a "+ 
							 "inner join inv1 b on a.docentry=b.u_djno and a.linenum=b.u_snid "+ 
							 "inner join rin1 c on c.u_baseentry=b.docentry and c.u_baseline=b.linenum "+
							 "where a.ifdraft='0' and a.objtype='13' and c.u_djno='"+String.valueOf(docentry)+"' " +
							 "and a.sn in ('"+s+"')";
					    dbu.exeSql(hql);	
					    System.out.println(hql);
						
					    hql="update a set a.ifwh='1',a.warehouse=b.warehouse,a.fwh='',a.cardcode='',updatetime=getdate() from dbo.[@snstatus] a "+
							"inner join dbo.[@desn] b on a.sn=b.sn "+
							"where b.docentry='"+String.valueOf(docentry)+"' "+
					        "and b.Ifdraft='0' "+
					        " and b.objtype='14' "; 
					    dbu.exeSql(hql);	
					    System.out.println(hql);
					 }
					 else if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
					 {
							SNL snl=new SNL(v.getDsv());		
						    snl.createsdra(v.getDsv(),false,false,"","I",ob[k][0].toString(),String.valueOf(docentry));
					     
					 }
					 else{}
			     }
				//JOptionPane.showMessageDialog(null,docentry+"��ӳɹ�");
				 System.out.println("�ɹ�"+appMain.oCompany.getNewObjectKey()+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				 idoc[k].release();				    
				
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
	public void update(OrinView V) {
		// TODO Auto-generated method stub
		
	}
	/**
	    * this operation is nonsynchronous,which means that is only one person can do this at the same time
	    */
	@Override
	public void delete(int id) {}

	@Override
	public Object[][] getDocLists(ParaList p) {
		// TODO Auto-generated method stub
		
		  return null;
	}

	@Override
	public Integer getfirst() {
		// TODO Auto-generated method stub
		
		 hql = "SELECT a.U_DjNo from rin1 a,Orin b where a.docEntry=b.docEntry and b.objType='14' and a.U_DjNo is not null " +
		 		"order by a.U_DjNo";
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
		hql = "SELECT  max(b.U_DjNo) from Orin a,Rin1 b where a.docEntry=b.docEntry and b.U_DjNo<'"+id+"'" +
				" and b.U_DjNo is not null ";
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
		hql = "SELECT  b.U_DjNo from Orin a,Rin1 b where a.docEntry=b.docEntry and b.U_DjNo>'"+id+"' " +
				"and b.U_DjNo is not null order by b.U_DjNo";
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
		  hql = "SELECT a.U_DjNo from Rin1 a,Orin b  where a.docEntry=b.docEntry and b.objType='14' order by " +
		  		" a.U_DjNo desc";
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
	public void setValues(OrinView v, Integer id,String dod) {
		// TODO Auto-generated method stub
		if(id==null)
		{
			return;
		}		
	
		try {
			idoc1=SBOCOMUtil.newDocuments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_Document_oCreditNotes);
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}	
		hql="select docentry from rin1 where U_djNo='"+id+"'";
		ob=appMain.lt.sqlclob(hql, 0, 1);
		if(ob==null||ob.length==0)
		{
			return;
		}
		docentry=Integer.valueOf(ob[0][0].toString());
		idoc1.getByKey(docentry);
		idoc1.getLines().setCurrentLine(0);
		String djlx=idoc1.getUserFields().getFields().item("U_Ysdxdjlx").getValue().toString();
		
		hql="select 0,a.u_snid,a.docEntry,lineNum=(a.lineNum+1),a.u_baseEntry,baseLine=(a.u_baseLine+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
	 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,a.U_NumPerUnit,a.whsCode,a.unitmsr2,a.U_Gs,U_SGs=0,a.U_Gjjg,"+
            " a.quantity,U_SQuantity=0,a.price,a.U_Zc,a.U_Mjg,a.lineTotal,U_Zz," +
            " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
            " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Rin1 a "+	
            " inner join Orin b on a.docEntry=b.docEntry "+
	 		"where a.U_DjNo='"+id+"' ";			
		 v.getOd().updatetable(hql,0);	
		 hql = "select docStatus,case when wddStatus='W' then '������' when wddStatus='Y' then '������' " +
		 		"when wddStatus='N' then '�Ѿܾ�' else '��' end " +
		 		" from Orin where docEntry='"+idoc1.getDocEntry()+"'";
         ob = appMain.lt.sqlclob(hql,0,1); 
		 v.getTxt_status().setText(ob[0][0].toString().equals("C")?"����":"δ��"+"-"+ob[0][1].toString());
	
	    
		 v.getTxt_date().setDate(idoc1.getDocDate());	
		 idoc1.getLines().setCurrentLine(0);
		
		 v.getTxt_docn().setText(String.valueOf(id));
		 v.getJta_memo().setText(idoc1.getComments());
	
		 
		 hql = "SELECT slpName from Oslp where slpCode='"+idoc1.getSalesPersonCode()+"'";
        ob = appMain.lt.sqlclob(hql,0,1); 
        if(idoc1.getSalesPersonCode()!=0)
        {
		 ComboBoxItem  Cbi=new ComboBoxItem(idoc1.getSalesPersonCode(),ob[0][0].toString());
		 v.getCom_sales().setEditable(true);
		 v.getCom_sales().setSelectedItem(Cbi);
        }
        else{
        	 v.getCom_sales().setEditable(true);
    		 v.getCom_sales().setSelectedItem(null);
        }
        hql = "SELECT a.U_slpcode1,b.slpname from orin a inner join oslp b on a.U_slpcode1=b.slpcode where docEntry='"+idoc1.getDocEntry()+"'";
        ob = appMain.lt.sqlclob(hql,0,1); 
        if(!(ob==null||ob.length==0))
        {
          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
          v.getCom_sales1().setEditable(true);
 		  v.getCom_sales1().setSelectedItem(Cbi);
        }
        else{
        	 v.getCom_sales1().setEditable(true);
    		 v.getCom_sales1().setSelectedItem(null);
        }
        v.getCom_type().setEditable(true);
        if(djlx.equals("0"))
        {        	
        	v.getCom_type().setSelectedItem(new ComboBoxItem(djlx,"�����˻�"));
        }
        else if(djlx.equals("1"))
        {
        	 v.getCom_type().setSelectedItem(new ComboBoxItem(djlx,"�����˻�(û��ǰ�õ���)"));
        }
        else if(djlx.equals("2"))
        {
        	 v.getCom_type().setSelectedItem(new ComboBoxItem(djlx,"����(�ӹ�)"));
        }
        else{
        	 JOptionPane.showMessageDialog(null,djlx+"Ӧ�մ���ҵ������ֵ�Ƿ�������");
        }
	    v.getTxt_ter().setText(idoc1.getUserFields().getFields().item("U_Dq").getValue().toString());
				
		 idoc1.release();	
		 hql = "select U_enable from [@SMS] where code='CRESN'";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||ob.length==0)
		 {										
			 return;
		 }
		 if(ob[0][0].toString().equals("Y"))
		 {
			 hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight," +
			 	"a.direction,a.ifpasn,a.pasn," +
				"a.warehouse,a.cardcode,a.memo,a.workcenter," +
				"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
		   		"from [@desn] a " +
		   		"inner join rin1 b on a.docentry=b.U_djno and a.linenum=b.u_snid " +
		   		"inner join orin c on b.docentry=c.docentry " +
		   		"where b.u_djno='"+v.getTxt_docn().getText().trim()+"' and a.Ifdraft='0' and a.objtype='14'";		   		
				v.getDsv().getOd().updatetable(hql, 0);
				v.getJta_SN().setText("");
				 ob=appMain.lt.sqlclob(hql,0,3000);
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

	@Override
	public void close(OrinView V) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void print(OrinView v) {
		// TODO Auto-generated method stub	
		 if(v.getOd().verification()==false)
	     {
			 int i= JOptionPane.showConfirmDialog(v.getParent(), "�Ƿ������ӡ");
			 if(i!=0)
			 {
				 return;
			 }
			 
	     }
		 if(v.getOd1().ds.getCnValue().equals("��ѯ"))
		 {			 
			 try{   
				 String tb;				
				 tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"+appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+"d:\\ncr\\Ӧ�մ���ƾ֤��ӡ.rpt"+"+"+"U_djNo"+"+"+v.getTxt_docn().getText()+"+"+"Printysdx";				
				 ActiveXComponent dotnetCom = null;    
	             dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //��Ҫ���õ�C#�����е������ռ�����������  
	             Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
	             System.out.println(var);
	             dotnetCom.safeRelease();
	             hql = "update a set a.printed='Y' FROM orin a inner join rin1 b on a.docentry=b.docentry " +
	             		" where b.u_djNo='"+v.getTxt_docn().getText().trim()+"'";
	    		 dbu.exeSql(hql);
	           // System.out.println(str);  //����õ����ַ�����������Ƿ���ȷ��
	             } catch (Exception ex) {    
	                ex.printStackTrace();    
	             }    
	       }
	
	}

	public OrinView getV() {
		return v;
	}

	public void setV(OrinView v) {
		this.v = v;
	}

	@Override
	public void ctarget(OrinView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(OrinView v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
