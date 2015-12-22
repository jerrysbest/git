package erp.ws.sbo.client.swing.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Orct.OrctView;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.MdbHibernateUtils;

import com.jacob.activeX.*;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class OrctDoc implements IDoc<OrctView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private OrctView v;
	private String hql,hql1,No;
	private Object[][] ob,ob1;
	private CallableStatement cstmt;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private String zzkm;
	private int docentry;
	private Integer Ndoce1;
	private Boolean tf;
	private BigDecimal[] pay,applied;
	private Double spayout=Double.valueOf(0),spayout1=Double.valueOf(0);
	public OrctDoc(){
	
	}
	
	@Override
	public void create(OrctView v) {
		// TODO Auto-generated method stub	
		dmv.setdeactive();
		v.setVisible(false);
		int ii=JOptionPane.showConfirmDialog(null, "保存后不可更改删除,是否保存,保存过程中主操作菜单将不能使用,请耐心等待");
		if(ii!=0)
		{
			v.setVisible(true);
			return;
		}
		/*  hql = "select  USkdSd from bcsd";
		  ob=appMain.lt.clob(hql,0,1);
		 if(ob[0][0].toString().equals("Y"))
		 {
			 JOptionPane.showMessageDialog(null,"其它用户正在操作，单据被锁定，请稍候");
			 return;
		 }
		 else
		 {
			  hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
				dbu.exeSql(hql);
		 }*/
		//检查输入是否全为0
        tf = true;
        for (int i=0;i<v.getOd().getRowCount();i++)
        {
            if (!Double.valueOf(v.getOd().getValueAt(i, v.getOd().getcolumnindex("收款金额")).toString()).equals(Double.valueOf("0")))
            {
                tf = false;
                break;
            }

        }
        if (tf)
        {
            //hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
  			//dbu.exeSql(hql);
            JOptionPane.showMessageDialog(null,"请输入核销金额!");       
    		v.setVisible(true);
            return ;
        }
               
        String[] s={"伙伴代码"};
        ob=v.getOd().Filterdrecords(true, s);
        //检查是否超过容差
        pay=new BigDecimal[ob.length];
        applied=new BigDecimal[ob.length];
        for(int i=0;i<ob.length;i++)
        {
        	pay[i]=new BigDecimal("0");
        	applied[i]=new BigDecimal("0");
        }
        for (int i=0;i<ob.length;i++)
        {
        	if(ob[i][0]==null||ob[i][0].toString().equals(""))
        	{
        		continue;
        	}
            //整个单据的收款情况应该由单身二的汇总决定 单身一和单身二汇总还要做比较，如果两者差距大于系统中的允许多付少付值，不允许添加
        	if(v.getOav()==null)
        	{   //hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
  			    //dbu.exeSql(hql);
        		JOptionPane.showMessageDialog(v, "请输入收款");
        		v.setVisible(true);
        		return;
        	}
        	for(int j=0;j<v.getOav().getOd().getRowCount();j++)
        	{
        		if(v.getOav().getOd().getValuethrheader(j, "伙伴代码")==null)
        		{
        			continue;
        		}
        		if(v.getOav().getOd().getValuethrheader(j,"伙伴代码").toString().equals(ob[i][0].toString()))
        		{
        			applied[i]=applied[i].add(new BigDecimal(v.getOav().getOd().getValuethrheader(j, "科目收款金额").toString()));
        		}
        	}
        	for(int j=0;j<v.getOd().getRowCount();j++)
        	{
        		if(v.getOd().getValuethrheader(j, "伙伴代码")==null)
        		{
        			continue;
        		}
        		if(v.getOd().getValuethrheader(j, "伙伴代码").toString().equals(ob[i][0].toString()))
        		{
        			pay[i]=pay[i].add(new BigDecimal(v.getOd().getValuethrheader(j, "收款金额").toString()));
        		}
        	}
        	if(applied[i].compareTo(pay[i])!=0)
        	{   //hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
			    //dbu.exeSql(hql);
        		JOptionPane.showMessageDialog(null, "业务伙伴"+ob[i][0]+"的实收款数:"+applied[i]+"与核销数:"+pay[i]+"不一致");      	   
        		v.setVisible(true);
        		return;
        	}
                 
        }
        //计算最大单号
        /*hql = "SELECT isnull(A.U_djNo,0) FROM (SELECT  U_djNo=isnull(U_djNo,0) FROM VPM2 UNION SELECT  U_djNo=isnull(U_djNo,0) FROM RCT2) A  ORDER BY A.U_djNo DESC";
        ob1=appMain.lt.sqlclob(hql, 0, 1);
        if (ob1==null||ob1.length==0)
        {
            docentry=1;
        }
        else
        {
           docentry=Integer.valueOf(ob1[0][0].toString())+1;
        }*/
        
        Ndoce1=0;
        hql = "SELECT isnull(A.U_djNo,0) FROM (SELECT  U_djNo=isnull(U_djNo,0) FROM VPM2 UNION SELECT  U_djNo=isnull(U_djNo,0) FROM RCT2) A  ORDER BY A.U_djNo DESC";
        ob1=appMain.lt.sqlclob(hql, 0, 1);
		if(ob1[0][0].toString().equals("0"))
		{
			Ndoce1+=1;			
		}
		else{
			hql="select substring(Convert(nvarchar(20),max(A.U_DjNo)),1,len(max(A.U_DjNo))-3) from (SELECT  U_djNo=isnull(U_djNo,0) FROM VPM2 UNION SELECT  U_djNo=isnull(U_djNo,0) FROM RCT2) A";
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
		
        //查找中转科目
        hql = "SELECT U_aCode FROM dbo.[@PAYUDT] where code='ZZKM'";
        ob1=appMain.lt.sqlclob(hql, 0, 1);
        if (ob1==null||ob1.length==0)
        {
        	//hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
		    //dbu.exeSql(hql);
            JOptionPane.showMessageDialog(null,"没有设置中转科目");
            v.setVisible(true);
        	return;
        }
        zzkm=ob1[0][0].toString();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //付款,这里生成负数的中转凭证只有一种情况，就是某个客户只有贷向凭证
        for(int i=0;i<ob.length;i++)
        {  spayout1=Double.valueOf(0);
        	for(int l=0;l<v.getOav().getOd().getRowCount();l++)
        	{
        		if(v.getOav().getOd().getValuethrheader(l, "伙伴代码")==null)
        		{
        			continue;
        		}
        		if(v.getOav().getOd().getValuethrheader(l,"伙伴代码").toString().equals(ob[i][0].toString()))
        		{
        			spayout1+=Double.valueOf(v.getOav().getOd().getValuethrheader(l, "科目收款金额").toString());
        		}
        	}
        	
        	try {
    			appMain.oinpay=SBOCOMUtil.newPayments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_oVendorPayments);
    		} catch (SBOCOMException e1) {
    			// TODO Auto-generated catch block			
    			e1.printStackTrace();	
    			//hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
    			//dbu.exeSql(hql);
    			v.setVisible(true);
    		}	
        	appMain.oinpay.setDocType(SBOCOMConstants.BoRcptTypes_rCustomer);
        	appMain.oinpay.setDocDate(new Date());
        	appMain.oinpay.setCardCode(ob[i][0].toString());
        	appMain.oinpay.setCashAccount(zzkm);
        	appMain.oinpay.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
        	spayout=Double.valueOf(0);
        	for(int j=0;j<v.getOd().getRowCount();j++)
        	{ 
        		if(v.getOd().getValuethrheader(j, "伙伴代码")==null)
        		{
        			continue;
        		}
        		if(v.getOd().getValuethrheader(j, "伙伴代码").toString().equals(ob[i][0].toString())
        		  &&v.getOd().getValuethrheader(j, "发票类别").toString().equals("应收贷向"))
        		{ 
        			if(new BigDecimal(v.getOd().getValuethrheader(j,"收款金额").toString()).compareTo(new BigDecimal(0))!=0)
     		       {
	        			spayout+=Double.valueOf(v.getOd().getValuethrheader(j,"收款金额").toString());
	        			appMain.oinpay.getInvoices().setDocEntry((Integer) v.getOd().getValuethrheader(j, "发票代码"));
	        			appMain.oinpay.getInvoices().setDocLine(0);
	        			appMain.oinpay.getInvoices().setInvoiceType(SBOCOMConstants.BoRcptInvTypes_it_CredItnote);
	        			appMain.oinpay.getInvoices().setSumApplied(-Double.valueOf(v.getOd().getValuethrheader(j, "收款金额").toString()));
	        			appMain.oinpay.getInvoices().getUserFields().getFields().item("U_djNo").setValue(docentry);
	        			appMain.oinpay.getInvoices().getUserFields().getFields().item("U_Ysye").setValue(-Double.valueOf(v.getOd().getValuethrheader(j, "应收余额").toString()));
	                    appMain.oinpay.getInvoices().add();
        		   }
        		   else{
        			  // JOptionPane.showMessageDialog(null,"插入收款为0的记录"+docentry);
        			   hql = "INSERT INTO [dbo].[@FPHZDH](U_Payhzdh,U_Fplb,U_Fpdm,U_Zxye,U_DocDate)VALUES" +
        			   		"('" + docentry + "','" + v.getOd().getValuethrheader(j, "发票类别").toString() + "','" + v.getOd().getValuethrheader(j,"发票代码").toString() + "','" + v.getOd().getValuethrheader(j,"应收余额").toString() + "','" + f.format(new Date())+ "')";
        			   dbu.exeSql(hql);              	
        		   }
        		}
        	}
        	appMain.oinpay.setCashSum(-spayout);
        	appMain.oinpay.setDocCurrency("RMB");
        	appMain.oinpay.setDocTypte(0);
        	appMain.oinpay.setDocRate(Double.valueOf(0));
        	appMain.oinpay.setJournalRemarks("Outgoing - " + ob[i][0].toString());
        	appMain.oinpay.setTransferAccount("100201");
        	appMain.oinpay.setTransferDate(new Date());
        	appMain.oinpay.setTransferSum(Double.valueOf(0));
        	appMain.oinpay.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
        	
        	if(appMain.oinpay.getInvoices().getCount()>1)
        	{
        		appMain.lRetCode=appMain.oinpay.add();
        		if(appMain.lRetCode!=0)
        		{ 
        			appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
        			appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
        			
        			 if (appMain.lErrCode != -4006) // Incase adding an order failed
                     {
        				// hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
        					//dbu.exeSql(hql);
        				 JOptionPane.showMessageDialog(null,"添加失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
        				 v.setVisible(true);
                     }
        			 else
        			 {
        				// hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
        					//dbu.exeSql(hql);
        				 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
        				 v.setVisible(true);
        			 }
        		}
        		else
        		{
        			//如果收款金额正好等于贷向金额，就只生成负数中转凭证
        			if(new BigDecimal(this.spayout).compareTo(new BigDecimal(this.spayout1))==0)
        			{ 
        				// JOptionPane.showMessageDialog(null,"中断了");
        				//hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
      			        //dbu.exeSql(hql);
        				v.setVisible(true);
        				    			
	        		   try {
						appMain.oje=SBOCOMUtil.newJournalEntries(appMain.oCompany);
						} catch (SBOCOMException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
							//dbu.exeSql(hql);
							v.setVisible(true);
						}        		
	        	       appMain.oje.setMemo(appMain.oCompany.getNewObjectKey().toString());
	        	       for(int k=0;k<v.getOav().getOd().getRowCount();k++)
	        	       {
	        	    	   if(v.getOav().getOd().getValuethrheader(k, "伙伴代码")==null||new BigDecimal(v.getOav().getOd().getValuethrheader(k, "科目收款金额").toString()).compareTo(new BigDecimal(0))==0)
			           		{
			           			continue;
			           		}
	        	    	   if(v.getOav().getOd().getValuethrheader(k, "伙伴代码").toString().equals(ob[i][0]))
	        	    	   {
	        	    		   appMain.oje.getLines().setAccountCode(v.getOav().getOd().getValuethrheader(k, "科目代码").toString());
	        	    		   appMain.oje.getLines().setCredit(-Double.valueOf(v.getOav().getOd().getValuethrheader(k, "科目收款金额").toString()));
	        	    		   appMain.oje.getLines().setDebit(Double.valueOf(0));
	        	    		   appMain.oje.getLines().setDueDate(new Date());
	        	    		   appMain.oje.getLines().add();      	    		   
	        	    	   }
	        	       }
	        	       appMain.oje.getLines().setAccountCode(zzkm);
	        	       appMain.oje.getLines().setCredit(Double.valueOf(0));
	        	       appMain.oje.getLines().setDebit(-spayout1);
	        	       appMain.oje.getLines().setDueDate(new Date());
	        	       appMain.oje.getLines().add();
	        	       appMain.lRetCode =  appMain.oje.add(); // Try to add the orer to the database
	                   if (appMain.lRetCode != 0)
	                   {  // hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
	       			      //dbu.exeSql(hql);
	                	   appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
	          			   appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
	          			   JOptionPane.showMessageDialog(null,"转账凭证添加失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message	                  
	          			   v.setVisible(true);
	                   }
        		}
        		}
        	}
        		
        }
        //收款
        for(int i=0;i<ob.length;i++)
        {
        	 //JOptionPane.showMessageDialog(null,"开始收款");
        	//收款的金额
        	spayout1=Double.valueOf(0);
        	for(int l=0;l<v.getOav().getOd().getRowCount();l++)
        	{
        		if(v.getOav().getOd().getValuethrheader(l, "伙伴代码")==null)
           		{
           			continue;
           		}
        		if(v.getOav().getOd().getValuethrheader(l,"伙伴代码").equals(ob[i][0].toString()))
        		{
        			spayout1+=Double.valueOf(v.getOav().getOd().getValuethrheader(l, "科目收款金额").toString());
        		}
        	}
        	//核销的应收贷向金额
        	spayout=Double.valueOf(0);
        	for(int j=0;j<v.getOd().getRowCount();j++)
        	{
        		if(v.getOd().getValuethrheader(j, "伙伴代码")==null)
           		{
           			continue;
           		}
        		if(v.getOd().getValuethrheader(j, "伙伴代码").toString().equals(ob[i][0].toString())
        		  &&v.getOd().getValuethrheader(j, "发票类别").toString().equals("应收贷向"))
        		{
        			spayout+=Double.valueOf(v.getOd().getValuethrheader(j, "收款金额").toString());
        		}
        	}
        	
        	try {
    			appMain.oinpay=SBOCOMUtil.newPayments(appMain.oCompany,SBOCOMConstants.BoObjectTypes_oIncomingPayments);
    		} catch (SBOCOMException e1) {
    			// TODO Auto-generated catch block			
    			e1.printStackTrace();
    			v.setVisible(true);
    		}	
        	appMain.oinpay.setDocType(SBOCOMConstants.BoRcptTypes_rCustomer);
        	appMain.oinpay.setDocDate(new Date());
        	appMain.oinpay.setCardCode(ob[i][0].toString());
        	appMain.oinpay.setCashAccount(zzkm);
        	appMain.oinpay.setCashSum(spayout1-spayout);
        	appMain.oinpay.setDocCurrency("RMB");
        	appMain.oinpay.setDocTypte(0);
        	appMain.oinpay.setDocRate(Double.valueOf(0));
        	appMain.oinpay.setJournalRemarks("Incoming - " + ob[i][0].toString());
        	appMain.oinpay.setTransferAccount("100201");
        	appMain.oinpay.setTransferDate(new Date());
        	appMain.oinpay.setTransferSum(Double.valueOf(0));
        	appMain.oinpay.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
        	for(int j=0;j<v.getOd().getRowCount();j++)
        	{
        		if(v.getOd().getValuethrheader(j, "伙伴代码")==null)
           		{
           			continue;
           		}
        		if(v.getOd().getValuethrheader(j, "伙伴代码").toString().equals(ob[i][0].toString())
        		  &&v.getOd().getValuethrheader(j, "发票类别").toString().equals("应收"))
        		{       			
        			 if(new BigDecimal(v.getOd().getValuethrheader(j,"收款金额").toString()).compareTo(new BigDecimal(0))!=0)
          		   {
        			appMain.oinpay.getInvoices().setDocEntry((Integer) v.getOd().getValuethrheader(j, "发票代码"));
        			appMain.oinpay.getInvoices().setDocLine(0);
        			appMain.oinpay.getInvoices().setInvoiceType(SBOCOMConstants.BoRcptInvTypes_it_Invoice);
        			appMain.oinpay.getInvoices().setSumApplied(Double.valueOf(v.getOd().getValuethrheader(j, "收款金额").toString()));
        			appMain.oinpay.getInvoices().getUserFields().getFields().item("U_djNo").setValue(docentry);
        			appMain.oinpay.getInvoices().getUserFields().getFields().item("U_Ysye").setValue(Double.valueOf(v.getOd().getValuethrheader(j, "应收余额").toString()));
        		  
        			appMain.oinpay.getInvoices().add();
        		   }
        		   else{
        			   hql = "INSERT INTO [dbo].[@FPHZDH](U_Payhzdh,U_Fplb,U_Fpdm,U_Zxye,U_DocDate)VALUES('" + docentry + "','" + v.getOd().getValuethrheader(j, "发票类别").toString() + "','" + v.getOd().getValuethrheader(j,"发票代码").toString() + "','" + v.getOd().getValuethrheader(j,"应收余额").toString() + "','" + f.format(new Date())+ "')";
        			   dbu.exeSql(hql);              	
        		   }
        		}
        	}
        	if(appMain.oinpay.getInvoices().getCount()>1)
        	{
        		appMain.lRetCode=appMain.oinpay.add();
        		if(appMain.lRetCode!=0)
        		{ 
        			appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
        			appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
        			
        			 if (appMain.lErrCode != -4006) // Incase adding an order failed
                     {
        				 /*hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
        				 dbu.exeSql(hql);*/
        				 JOptionPane.showMessageDialog(null,"添加失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message                   
        				 v.setVisible(true);
                     }
        			 else
        			 {   
        				/* hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
        				 dbu.exeSql(hql);*/
        				 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
        				 v.setVisible(true);
        			 }
        		}
        		else
        		{
        			if(new BigDecimal(this.spayout).compareTo(new BigDecimal(this.spayout1))==0)
        				return;
        		   try {
					appMain.oje=SBOCOMUtil.newJournalEntries(appMain.oCompany);
				} catch (SBOCOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
					//dbu.exeSql(hql);
					v.setVisible(true);
				}        		
        		  
        		   hql = "SELECT DocEntry FROM ORCT ORDER BY  DocEntry DESC";
    	           ob1=appMain.lt.sqlclob(hql, 0, 1);       	       
    	           String s1=ob1[0][0].toString();
    	           hql = "SELECT DocEntry FROM OVPM ORDER BY  DocEntry DESC";
    	           ob1=appMain.lt.sqlclob(hql, 0, 1);       	       
    	           String s2=ob1[0][0].toString();
        	       if(spayout!=0)
        	       {       	    	 
        	    	  // JOptionPane.showMessageDialog(null,"凭证关联借贷");
        	    	   appMain.oje.setMemo(s1+"&"+s2);
        	    	   appMain.oje.getLines().setAccountCode(zzkm);
            	       appMain.oje.getLines().setCredit(Double.valueOf(0));
            	       appMain.oje.getLines().setDebit(-spayout);
            	       System.out.println(-spayout);
            	       appMain.oje.getLines().setDueDate(new Date()); 
            	       appMain.oje.getLines().add();
        	       }
        	       else
        	       {
        	    	   appMain.oje.setMemo(s1);
        	       }
        	     
        	       for(int k=0;k<v.getOav().getOd().getRowCount();k++)
        	       {
        	    	        	    	  
        	    	   if(v.getOav().getOd().getValuethrheader(k, "伙伴代码")==null||v.getOav().getOd().getValuethrheader(k, "科目收款金额")==null||new BigDecimal(v.getOav().getOd().getValuethrheader(k, "科目收款金额").toString()).compareTo(new BigDecimal(0))==0)
		           		{
        	    		      continue;
		           		}
        	    	   if(v.getOav().getOd().getValuethrheader(k, "伙伴代码").equals(ob[i][0]))
        	    	   {       	    		  
        	    		   appMain.oje.getLines().setAccountCode(v.getOav().getOd().getValuethrheader(k, "科目代码").toString());
        	    		   appMain.oje.getLines().setDebit(Double.valueOf(v.getOav().getOd().getValuethrheader(k, "科目收款金额").toString()));
        	    		   //System.out.println("循环体"+v.getOav().getOd().getValuethrheader(k, "科目收款金额").toString());
        	    		   appMain.oje.getLines().setCredit(Double.valueOf(0));
        	    		   appMain.oje.getLines().setDueDate(new Date());
        	    		   appMain.oje.getLines().add();      	    		   
        	    	   }
        	       }
        	       appMain.oje.getLines().setAccountCode(zzkm);
        	       appMain.oje.getLines().setCredit(spayout1-spayout);
        	       System.out.println(spayout1-spayout);
        	       appMain.oje.getLines().setDebit(Double.valueOf(0));
        	       appMain.oje.getLines().setDueDate(new Date()); 
        	       appMain.oje.getLines().add();
        	     
        	       appMain.lRetCode =  appMain.oje.add(); // Try to add the orer to the database
                   if (appMain.lRetCode != 0)
                   {
                	   /*hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
           			   dbu.exeSql(hql);*/
                	   appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
           			   appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
           			   JOptionPane.showMessageDialog(null,"转账凭证添加失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message                 
           			   v.setVisible(true);
                   }
               
        		}
        	}
        	
        }
        if( v.getOav()!=null)
        {
          v.getOav().dispose();
          v.setOav(null);
        }

         // hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
		 // dbu.exeSql(hql);
          v.setVisible(true);
		  hql = "select U_enable from dbo.[@SMS] where code='CPAY'";
		  ob1=appMain.lt.sqlclob(hql, 0, 1);	
		  if(ob1==null||ob1.length==0)
		  {
			  //hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
				//dbu.exeSql(hql);
			  JOptionPane.showMessageDialog(null,"请在表sms中定义发对账明细");// Display error message
			  v.setVisible(true);
              
		  }
          //发对账明细
		  else if(ob1[0][0].toString().equals("Y"))
		  {
			 // hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
				//dbu.exeSql(hql);
			  v.setVisible(true);
			  Session session = MdbHibernateUtils.getSession();
		      Transaction t = session.beginTransaction();{
		       try {
		    	   @SuppressWarnings("deprecation")
				   Connection con = session.connection(); 	
		    	   
		    	   hql = "{call zdy_wldz(?)}";
			       try {	        	
			    	    cstmt = con.prepareCall(hql);				
						cstmt.setInt(1,docentry);				
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
		  }
		  else{}
		  //发预警
		  hql = "select U_enable from dbo.[@SMS] where code='PAY'";
		  ob1=appMain.lt.sqlclob(hql, 0, 1);	
		  if(ob1==null||ob1.length==0)
		  {
			  JOptionPane.showMessageDialog(null,"请在表sms中定义发对账预警");// Display error message
			  v.setVisible(true);
              
		  }
		  else if(ob1[0][0].toString().equals("Y"))
		  {
			  Session session = MdbHibernateUtils.getSession();
		      Transaction t = session.beginTransaction();{
		       try {
		    	   @SuppressWarnings("deprecation")
				   Connection con = session.connection(); 	
		    	   
		    	   hql = "{call zdy_pay_yj(?,?)}";
			       try {	        	
						cstmt = con.prepareCall(hql);				
						cstmt.setInt(1,docentry);	
						cstmt.setInt(2,Integer.valueOf(((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()));	
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
		  }
		  else{}
		/*  hql = "update  dbo.[@BCSD] set U_skd_sd='N'";
			dbu.exeSql(hql);*/
		    v.setVisible(true);
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
	public void update(OrctView v) {
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
		 hql = "SELECT min(a.U_djNo) from (select distinct a.U_djNo,b.usersign from rct2 a inner join orct b on a.docnum=b.docnum " +
		 		" union select distinct a.U_djNo,b.usersign from vpm2 a inner join ovpm b on a.docnum=b.docnum ) a where a.U_djNo>='1'";
        }
        else{      	  
         hql +=	"and a.U_DjNo is not null  " +
		 		" and  a.usersign in (select userid from ousr where u_usergroup=" +
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
		 hql = "SELECT max(a.U_djNo) from (select distinct a.U_djNo,b.usersign from rct2 a inner join orct b on a.docnum=b.docnum " +
		 		" union select distinct a.U_djNo,b.usersign from vpm2 a inner join ovpm b on a.docnum=b.docnum ) a where a.U_djNo<'"+id+"' ";
        } 	
        else{      	  
         hql +=	" and a.U_DjNo is not null  " +
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
      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
      	  return 0;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
		 hql = "SELECT min(a.U_djNo) from (select distinct a.U_djNo,b.usersign from rct2 a inner join orct b on a.docnum=b.docnum " +
		 		" union select distinct a.U_djNo,b.usersign from vpm2 a inner join ovpm b on a.docnum=b.docnum) a where a.U_djNo>'"+id+"'";
        }
        else{      	  
            hql +=	" and a.U_DjNo is not null  " +
      		 		" and  a.usersign in (select userid from ousr where u_usergroup=" +
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
		 hql = "SELECT max(a.U_djNo) from (select distinct a.U_djNo,b.usersign from rct2 a inner join orct b on a.docnum=b.docnum " +
		 		" union select distinct a.U_djNo,b.usersign from vpm2 a inner join ovpm b on a.docnum=b.docnum) a where a.U_djNo>='1'";
        }
        else{      	  
          hql +=" and a.U_DjNo is not null  " +
      		 	" and  a.usersign in (select userid from ousr where u_usergroup=" +
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
	public void setValues(OrctView v,Integer id,String dod) {
		// TODO Auto-generated method stub
		if(id==null)
		{
			return;
		}
		v.getTxt_docn().setText(id.toString());
		hql="select a.slpcode,c.slpname,docdate=convert(nvarchar(10),d.docdate,23),U_slpcode1=isnull(a.U_slpcode1,-1),slpname1=isnull(e.slpname,'无销售员工') from oinv a " +
			" inner join rct2 b " +
			" on a.docentry=b.docentry " +
			" inner join orct d " +
			"on d.docentry=b.docnum" +
			" inner join oslp c on a.slpcode=c.slpcode " +
			"left join oslp e on a.U_slpcode1=e.slpcode" +
			" where b.U_djNO='"+id+"'" +
			"union " +
			"select a.slpcode,c.slpname,docdate=convert(nvarchar(10),d.docdate,23),U_slpcode1=isnull(a.U_slpcode1,-1),slpname1=isnull(e.slpname,'无销售员工') from orin a " +
			" inner join vpm2 b " +
			" on a.docentry=b.docentry " +
			" inner join ovpm d " +
			"on d.docentry=b.docnum" +
			" inner join oslp c on a.slpcode=c.slpcode " +
			"left join oslp e on a.U_slpcode1=e.slpcode" +
			" where b.U_djNO='"+id+"'" ;
		 ob = appMain.lt.sqlclob(hql,0,1);	
		 if(ob==null||ob.length==0)
		 {
			 return;
		 }
		 ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
		 v.getCom_sales().setEditable(true);
		 v.getCom_sales().setSelectedItem(Cbi);
	     try{
			 if(!(ob[0][3].toString().equals("0")||ob[0][4]==null))
			 {
				 Cbi=new ComboBoxItem(Integer.valueOf(ob[0][3].toString()),ob[0][4].toString());
				 v.getCom_sales1().setEditable(true);
				 v.getCom_sales1().setSelectedItem(Cbi);
			 }
			 else{
				 v.getCom_sales1().setEditable(true);
				 v.getCom_sales1().setSelectedItem(null);
			 }
	     }
	     catch(NullPointerException e0){
	    	 
	     }
		 v.getTxt_date().setText(ob[0][2].toString());
		 hql="select * from (SELECT 0 as 序号,T0.[DocNum] AS '单据代码', "+
			" T1.[DocEntry] AS  '发票代码' , convert(varchar(10),T3.DocDate,120) AS '日期',T0.InvoiceId+1 AS '行号', "+
			"  T3.[CardCode] AS '伙伴代码',T4.CardName AS '伙伴名称',  '应收' AS '发票类别',T1.DocTotal AS '应收总计', "+
			" T0.U_Ysye AS '应收余额',  T0.U_Ysye-T0.SumApplied AS '最新余额',  "+ 
			" T0.SumApplied AS '收款金额', '' AS '分配规则',  T0.U_djNo AS '汇总单号'  "+  
			" FROM  [dbo].[RCT2] T0   "+ 
			" INNER JOIN ORCT T3 ON T3.[DocEntry]=T0.[DocNum]  "+  
			" INNER JOIN OCRD T4 ON T4.[CardCode]=T3.[CardCode]  "+  
			" INNER JOIN OINV T1 ON T1.[DocEntry]=T0.[DocEntry] "+   
			" INNER JOIN OSLP T2 ON T2.[SlpCode]=T1.[SlpCode] "+     
			" WHERE  T1.CANCELED='N'  AND T1.ObjType='13'   AND T3.Canceled='N' "+
			" AND  T1.[SlpCode]='"+ob[0][0].toString()+"' AND   T0.U_djNo= '"+id+"'  "+
			" UNION ALL "+   
			" SELECT 0 as 序号,T0.[DocNum] AS '单据代码',  T1.[DocEntry] AS  '发票代码' ,"+
			" convert(varchar(10),T3.DocDate,120) AS '日期',T0.InvoiceId+1 AS '行号',  T3.[CardCode] AS '伙伴代码',"+
			" T4.CardName AS '伙伴名称',  '应收贷向' AS '发票类别',-T1.DocTotal AS '应收总计',  -T0.U_Ysye AS '应收余额',"+  
			" T0.SumApplied-T0.U_Ysye AS '最新余额',  -T0.SumApplied AS '收款金额', '' AS '分配规则',"+  
			" T0.U_djNo AS '汇总单号' "+   
			" FROM  [dbo].[VPM2] T0  "+  
			" INNER JOIN OVPM T3 ON T3.[DocEntry]=T0.[DocNum]  "+  
			"  INNER JOIN OCRD T4 ON T4.[CardCode]=T3.[CardCode]  "+  
			" INNER JOIN ORIN T1 ON T1.[DocEntry]=T0.[DocEntry]  "+   
			" INNER JOIN OSLP T2 ON T2.[SlpCode]=T1.[SlpCode]  "+    
			" WHERE  T1.CANCELED='N'  AND T1.ObjType='14' AND T3.Canceled='N' "+
			" AND  T1.[SlpCode]='"+ob[0][0].toString()+"' AND   T0.U_djNo= '"+id+"' "+ 
			" UNION ALL  "+
			" SELECT 0 as 序号,'' AS '单据代码',T0.[DocEntry] AS  '发票代码' , "+
			" convert(varchar(10),T1.U_DocDate,120) AS '日期','' AS '行号',  T0.[CardCode] AS '伙伴代码',T4.CardName AS '伙伴名称',"+
			" '应收' AS '发票类别',T0.DocTotal AS '应收总计',T1.U_Zxye AS '应收余额', T1.U_Zxye AS '最新余额',  "+ 
			" '0' AS '收款金额', '' AS '分配规则',  T1.U_Payhzdh AS '汇总单号' "+  
			" FROM  [dbo].[OINV] T0 "+
			" INNER JOIN OCRD T4 ON T4.[CardCode]=T0.[CardCode]  "+
			" INNER JOIN [dbo].[@FPHZDH] T1 ON T1.[U_Fpdm]=T0.[DocEntry] AND (T1.[U_Fplb]='IN' or T1.[U_Fplb]='应收')"+
			" INNER JOIN OSLP T2 ON T2.[SlpCode]=T0.[SlpCode]   "+  
			" WHERE  T0.CANCELED='N'  AND T0.ObjType='13'  "+
			" AND  T0.[SlpCode]='"+ob[0][0].toString()+"' AND  T1.U_Payhzdh= '"+id+"'  "+
			" UNION ALL "+
			" SELECT 0 as 序号,'' AS '单据代码', T0.[DocEntry] AS  '发票代码' , "+
			" convert(varchar(10),T1.U_DocDate,120) AS '日期','' AS '行号', T0.[CardCode] AS '伙伴代码',T4.CardName AS '伙伴名称', "+
			" '应收贷向' AS '发票类别',-T0.DocTotal AS '应收总计',T1.U_Zxye AS '应收余额',T1.U_Zxye AS '最新余额', "+
			" '0' AS '收款金额',  '' AS '分配规则',  T1.U_Payhzdh AS '汇总单号'  "+
			" FROM  [dbo].[ORIN] T0 "+
			" INNER JOIN OCRD T4 ON T4.[CardCode]=T0.[CardCode]  "+
			" INNER JOIN [dbo].[@FPHZDH] T1 ON T1.[U_Fpdm]=T0.[DocEntry] AND  (T1.[U_Fplb]='CN' or T1.[U_Fplb]='应收贷向')"+
			" INNER JOIN OSLP T2 ON T2.[SlpCode]=T0.[SlpCode] "+
			" WHERE  T0.CANCELED='N'  AND T0.ObjType='14' "+
			" AND  T0.[SlpCode]='"+ob[0][0].toString()+"' AND  T1.U_Payhzdh= '"+id+"' ) a order by 伙伴代码 ";
		 v.getOd().updatetable(hql,0);	
	}
	
	@Override
	public void close(OrctView v)
	{
		
	}
	public OrctView getV() {
		return v;
	}

	public void setV(OrctView v) {
		this.v = v;
	}

	@Override
	public void print(OrctView v) {
		// TODO Auto-generated method stub
		 if(v.getOd1().ds.getCnValue().equals("查询"))
		 {
			  try{ 
				    hql="select isnull(a.U_Ydy,'N') from (select U_Ydy from rct2 where U_djNo='" + v.getTxt_docn().getText() + "' " +
				    		"union select U_Ydy from vpm2 where U_djNo='" + v.getTxt_docn().getText() + "') a";
		            ob = appMain.lt.sqlclob(hql,0,1);
		            if(ob==null||ob.length==0)
		            {
		        	   JOptionPane.showMessageDialog(null, "查询异常，批量收款单号"+v.getTxt_docn().getText()+"没查询到是否已打印");
		        	   return;
		            }	
		            else if(ob[0][0].toString().equals("N"))
		            {		            
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\CrystalReportPlskcx.rpt"+"+"+"djNo"+"+"+v.getTxt_docn().getText()+"+"+
				        "saID"+"+"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+
				        "+"+"PrintPay";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。
	//		            Object o = dotnetCom.getObject();
	//		            SensorEvents event = new SensorEvents();
			            // hook it up to the sControl source
			           // DispatchEvents de = new DispatchEvents((Dispatch)o, event);	  
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
	
				    	hql = "update rct2 set U_Ydy='Y' WHERE U_djNo='" + v.getTxt_docn().getText() + "'";
						dbu.exeSql(hql);
						hql = "update vpm2 set U_Ydy='Y' WHERE U_djNo='" + v.getTxt_docn().getText() + "'";
						dbu.exeSql(hql);
		            }
		            else if(ob[0][0].toString().equals("Y"))
					 {
						 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
					 	 ob = appMain.lt.sqlclob(hql,0,1);
						 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
					 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
					 	  	 "and b.code='ORCTDOCPM' and a.enable='1'";
					 	 ob = appMain.lt.sqlclob(hql,0,1);
					 	 if(ob==null||ob.length==0)
			             {
			        	   JOptionPane.showMessageDialog(null, "无多次打印批量收款单权限，请联系系统管理员");
			        	   return;
			             }	
					 	 else{
					 		String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
					        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
						 	"d:\\ncr\\CrystalReportPlskcx.rpt"+"+"+"djNo"+"+"+v.getTxt_docn().getText()+"+"+
					        "saID"+"+"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+
					        "+"+"PrintPay";
						    ActiveXComponent dotnetCom = null;    
				            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。
		//		            Object o = dotnetCom.getObject();
		//		            SensorEvents event = new SensorEvents();
				            // hook it up to the sControl source
				           // DispatchEvents de = new DispatchEvents((Dispatch)o, event);	  
				            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
				            System.out.println(var);
				            dotnetCom.safeRelease();
					 	 }
					 }
		           // System.out.println(str);  //输出得到的字符串。检查结果是否正确。
	            } catch (Exception ex) {    
	                ex.printStackTrace();    
	            }    
	       }
			else if(v.getOd1().ds.getCnValue().equals("增加"))
			{
				 try{    
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\CrystalReportPlsk.rpt"+"+"+"Slpcode"+"+"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+"+"+
				        "slpcode1"+"+"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()+	      
				        "+"+"PrintBfPay";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。
			            //Object o = dotnetCom.getObject();
			            //SensorEvents event = new SensorEvents();
			            // hook it up to the sControl source
			           // DispatchEvents de = new DispatchEvents((Dispatch)o, event);	  
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
			           // System.out.println(str);  //输出得到的字符串。检查结果是否正确。
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
				/*ClassicEngineBoot.getInstance().start();
				
				try {
					final DriverConnectionProvider provider = new DriverConnectionProvider();
					 URL reportDefinitionURL = null;
					
					 reportDefinitionURL = new URL("file:///d:/preport/批量收款单保存前打印.prpt");
												
					 // Parse the report file
				     final ResourceManager resourceManager = new ResourceManager();
				     resourceManager.registerDefaults();
				     final Resource directly;
				     resourceManager.registerDefaults();
					 directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
					 //JOptionPane.showMessageDialog(null,appMain.config.getDserver()+":1433; DatabaseName="+appMain.oCompany.getCompanyDB());
				     MasterReport report = (MasterReport) directly.getResource();
				     provider.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				     provider.setProperty("user",appMain.config.getDbusername());
				     provider.setProperty("password", appMain.config.getDbuserpas());
				     provider.setUrl(appMain.config.getDserver()+":1433; DatabaseName="+appMain.oCompany.getCompanyDB());	
			
				     report.getParameterValues().put("slpcode",((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()); 
				     report.getParameterValues().put("slpcode1",((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()); 
						
				    
				     final PreviewDialog dialog = new PreviewDialog();
				
				     dialog.setReportJob(report);
				     dialog.setBounds(0, 0, 1366, 768);
				     dialog.setModal(true);
				    // dialog.pack();
				     dialog.setVisible(true);		
					    
					  
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
				}*/
			}
					  	
	}

	@Override
	public void ctarget(OrctView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}
}
