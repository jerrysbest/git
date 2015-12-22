package erp.ws.sbo.utils;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.desn;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.PaSN.PaSNView;
import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.DeSN;
import erp.ws.sbo.dao.impl.SNStatus;

public class SNL {
	private DeSNView v;
	private String hql;
	private Object[][] ob;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
	private snstatus sns=new snstatus();
	private Set<String> setsn=new HashSet<String>(),setsn1=new HashSet<String>();	
	private snstatus sns1=new snstatus();
	private SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
	public SNL()
    {
    	
    	
    }
    public SNL(DeSNView v)
    {
    	this.setV(v);    	
    }

    public void createsdra(DeSNView v,boolean Ifdraft,boolean ifpasn,String pasn,String in,String cardCode,String docid){
    	for(int i=0;i<v.getOd().getRowCount();i++)
    	{   		
    		if(v.getOd().getValuethrheader(i, "���к�")==null||(v.getOd().getValuethrheader(i, "���к�")!=null&&v.getOd().getValuethrheader(i, "���к�").toString().equals("")))
    		{
    			continue;
    		}
    	    if(!cardCode.equals("")&&!cardCode.equals(v.getOd().getValuethrheader(i, "ҵ����")))
    	    {
    	    	continue;
    	    }
    		desn ds=new desn();
    		
    		DeSN sn=(DeSN)appMain.ctx.getBean("DeSN");
    		ds.setIfdraft(Ifdraft);
    		ds.setObjType(Integer.valueOf(v.getOd().getValuethrheader(i, "��������").toString()));
    		ds.setDocEntry(Integer.valueOf(docid));
    		ds.setLineNum(Integer.valueOf(v.getOd().getValuethrheader(i, "�к�").toString()));
    		ds.setSn(v.getOd().getValuethrheader(i, "���к�").toString());
    		ds.setItemcode(v.getOd().getValuethrheader(i, "���ϴ���").toString());
    		ds.setLength(new BigDecimal(v.getOd().getValuethrheader(i, "�׶�").toString()));
    		ds.setWeight(new BigDecimal(v.getOd().getValuethrheader(i, "����").toString()));
    		ds.setCardCode(v.getOd().getValuethrheader(i, "ҵ����").toString());
    		ds.setDirection(in);
    		ds.setIfPaSn(ifpasn);
    		ds.setPaSn("");
    		//ds.setMemo();
    		ds.setWareHouse(v.getOd().getValuethrheader(i, "�ֿ�").toString());
    		ds.setCardCode(cardCode);
    		ds.setCdatetime(new Timestamp(new Date().getTime()));
    		ds.setUdatetime(new Timestamp(new Date().getTime())); 		
    		//ds.setWorkCenter(v.getOd().getValuethrheader(i, "��������").toString());
    		sn.add(ds);	
    		
    		//�������к�״̬���ݸ�Ļ���Ӧ�ø���
            if(!Ifdraft)
            {
        	  snstatus sns=new snstatus();
        	  SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
           	  sns=snst.queryByDocId(v.getOd().getValuethrheader(i, "���к�").toString());
           	  if(in.equals("I"))
           	  {
           	    sns.setIfWh(true);
           	    sns.setWareHouse(v.getOd().getValuethrheader(i, "�ֿ�").toString());
           	    sns.setCardcode("");
           	    sns.setFwh("");
           	  }
           	  else{
           		 sns.setIfWh(false);
           		 sns.setWareHouse("");
           		 sns.setCardcode(cardCode);
           		 sns.setFwh(v.getOd().getValuethrheader(i, "�ֿ�").toString());
           	  }
           	  sns.setIfPsn(ifpasn);
           	  //sns.setIfInPsn(false);
           	  //sns.setWareHouse(v.getOd().getValuethrheader(i, "�ֿ�").toString());
           	  //sns.setCardcode(cardCode);
           	  sns.setUpdatetime(new Timestamp(new Date().getTime()));
           	  snst.update(sns);	
            }
          
    	}
    }
   
    //create sn
    public boolean createSN(SninView v) throws ParseException{
    	if(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().equals("")||v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("���ϴ�����߹�˾����Ϊ��,�������������к�");
    		return false;
    	}
    	if(v.getTxt_cweight().getText().equals("0")||v.getTxt_sweight().getText().equals("0"))
    	{
    		appMain.fv.setText("��׼�������߾���Ϊ0,�������������к�");
    		return false; 
    	}
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ  Ĭ��ʱ���ʽ��yyyy-MM-dd HH:mm:ss  
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");  
    	Date passUtilDate = null;
    	
	    passUtilDate = f.parse(f.format(new Date()));
	 
    	snstatus sns=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
     	sns.setSn(v.getTxt_createcode().getText().toString());
     	sns.setIfWh(false);
     	sns.setIfPsn(false);
     	sns.setIfInPsn(false);
     	sns.setWareHouse(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString());
     	sns.setCardcode(v.getCom_ifincomed().getSelectedItem().toString().equals("��")?((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText():"");
     	sns.setDatetime(new Timestamp(passUtilDate.getTime()));
     	sns.setCweight(Double.valueOf(v.getTxt_cweight().getText()));
     	sns.setWeight(Double.valueOf(v.getTxt_weight().getText()));
     	sns.setPweight(Double.valueOf(v.getTxt_pweight().getText()));
     	sns.setSweight(Double.valueOf(v.getTxt_sweight().getText()));   	
     	sns.setItemcode(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText());
     	sns.setLength(Double.valueOf(v.getTxt_length().getText()));
     	sns.setMno(v.getTxt_MNo().getText());
     	sns.setCompany(v.getCom_company().getSelectedItem().toString());
     	sns.setQc(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());
     	snst.add(sns);	
     	return true;
    }
  //create sn
    public boolean createSN(OignView v) throws ParseException{
    	if(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().equals("")||v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("���ϴ�����߹�˾����Ϊ��,�������������к�");
    		return false;
    	}
    	if(v.getTxt_cweight().getText().equals("0")||v.getTxt_sweight().getText().equals("0"))
    	{
    		appMain.fv.setText("��׼�������߾���Ϊ0,�������������к�");
    		return false; 
    	}
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ  Ĭ��ʱ���ʽ��yyyy-MM-dd HH:mm:ss  
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");  
    	Date passUtilDate = null;
    	
	    passUtilDate = f.parse(f.format(new Date()));
	 
    	snstatus sns=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
     	sns.setSn(v.getTxt_createcode().getText().toString());
     	sns.setIfWh(false);
     	sns.setIfPsn(false);
     	sns.setIfInPsn(false);
     	sns.setWareHouse(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString());
     	sns.setCardcode("");
     	sns.setDatetime(new Timestamp(passUtilDate.getTime()));
     	sns.setCweight(Double.valueOf(v.getTxt_cweight().getText()));
     	sns.setWeight(Double.valueOf(v.getTxt_weight().getText()));
     	sns.setPweight(Double.valueOf(v.getTxt_pweight().getText()));
     	sns.setSweight(Double.valueOf(v.getTxt_sweight().getText()));   	
     	sns.setItemcode(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText());
     	sns.setLength(Double.valueOf(v.getTxt_length().getText()));
     	sns.setMno(v.getTxt_MNo().getText());
     	sns.setCompany(v.getCom_company().getSelectedItem().toString());
     	sns.setQc(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString());
     	snst.add(sns);	
     	return true;
    }
    //create psn
    public boolean createPSN_afs(SninView v,String Psn) throws ParseException{
    	if(v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("���ϴ�����߹�˾����Ϊ��,�������������к�");
    		return false;
    	}   
    	//��֤
    	setsn=new HashSet<String>();	
    	ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");
    	try {
			if(!verificationPSN(v.getJta_SN()))
			{
				return false;
			}
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Integer j=0;
		String warehouse=new String(""),itcode=new String("");
		BigDecimal length=new BigDecimal(0.00);
		for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		{
			if(v.getDsv().getOd().getValuethrheader(i, "���к�")==null||(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals("")))
			{
				continue;
			}
			j=j+1;
		
            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(i, "���к�").toString());
            if(sns==null)
            {
            	JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"������");
            	return false;
            }
            if(!sns.getCardcode().toString().equals(""))
            {
            	JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"���ڿͻ�"+sns.getCardcode().toString()+"����");
            	return false;
            }
            if(sns.isIfPsn())
            {
            	JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"�Ǵ����кţ���������");
            	return false;
            }
            if(i>0)
            {
            	if((!warehouse.equals(sns.getWareHouse().toString()))||(!itcode.equals(sns.getItemcode().toString()))||(!length.equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))))
            	{
            		JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"����һ���кŵ����ϱ��롢�׶λ��ֿ߲ⲻͬ����������");
                	return false;
            	}
            }
            if((sns.getPaSn()!=null&&!sns.getPaSn().equals(""))||sns.isIfInPsn())
            {
            	setsn.add(sns.getSn());
            	if(JOptionPane.showConfirmDialog(v,"���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"���ڸ����к�"+sns.getPaSn()+"�����кŽ�ʧЧ,�Ƿ����")==1)
            	{
            		return false;
            	}     
            }
	            warehouse=sns.getWareHouse();
	            itcode=sns.getItemcode();
	            length=new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}		
		if(j==0)
		{
			return false;
		}
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ  Ĭ��ʱ���ʽ��yyyy-MM-dd HH:mm:ss  
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");  
    	Date passUtilDate = null;
    	
	    passUtilDate = f.parse(f.format(new Date()));
	 
    	snstatus sns=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
     	sns.setSn(Psn);
     	sns.setIfWh(false);
     	sns.setIfPsn(true);
     	sns.setIfInPsn(false);
     	sns.setWareHouse("");
     	sns.setCardcode("");
     	sns.setDatetime(new Timestamp(passUtilDate.getTime()));
     	sns.setCweight(Double.valueOf(0));
     	sns.setWeight(Double.valueOf(0));
     	sns.setPweight(Double.valueOf(0));
     	sns.setSweight(Double.valueOf(0));
     	sns.setItemcode("");
     	sns.setLength(Double.valueOf(0));
     	sns.setMno(v.getTxt_MNo().getText());
     	sns.setCompany(v.getCom_company().getSelectedItem().toString());
     	sns.setQc(v.getTxt_MNo().getText());
     
     	snst.add(sns);
     	for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		{
			if(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&!v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals(""))
			{
			    hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+Psn+"',updatetime=getdate() "+		
				    "where sn='"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"' "; 
				     dbu.exeSql(hql);	
			}
		}	
     	return true;
    }
    //create psn
    public boolean createPSN_afs(OignView v,String Psn) throws ParseException{
    	if(v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("���ϴ�����߹�˾����Ϊ��,�������������к�");
    		return false;
    	}   
    	//��֤
    	setsn=new HashSet<String>();	
    	ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");
    	try {
			if(!verificationPSN(v.getJta_SN()))
			{
				return false;
			}
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Integer j=0;
		String warehouse=new String(""),itcode=new String("");
		BigDecimal length=new BigDecimal(0.00);
		for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		{
			if(v.getDsv().getOd().getValuethrheader(i, "���к�")==null||(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals("")))
			{
				continue;
			}
			j=j+1;
		
            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(i, "���к�").toString());
            if(sns==null)
            {
            	JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"������");
            	return false;
            }
            if(!sns.getCardcode().toString().equals(""))
            {
            	JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"���ڿͻ�"+sns.getCardcode().toString()+"����");
            	return false;
            }
            if(sns.isIfPsn())
            {
            	JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"�Ǵ����кţ���������");
            	return false;
            }
            if(i>0)
            {
            	if((!warehouse.equals(sns.getWareHouse().toString()))||(!itcode.equals(sns.getItemcode().toString()))||(!length.equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))))
            	{
            		JOptionPane.showMessageDialog(null, "���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"����һ���кŵ����ϱ��롢�׶λ��ֿ߲ⲻͬ����������");
                	return false;
            	}
            }
            if((sns.getPaSn()!=null&&!sns.getPaSn().equals(""))||sns.isIfInPsn())
            {
            	setsn.add(sns.getSn());
            	if(JOptionPane.showConfirmDialog(v,"���к�"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"���ڸ����к�"+sns.getPaSn()+"�����кŽ�ʧЧ,�Ƿ����")==1)
            	{
            		return false;
            	}     
            }
	            warehouse=sns.getWareHouse();
	            itcode=sns.getItemcode();
	            length=new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}		
		if(j==0)
		{
			return false;
		}
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ  Ĭ��ʱ���ʽ��yyyy-MM-dd HH:mm:ss  
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");  
    	Date passUtilDate = null;
    	
	    passUtilDate = f.parse(f.format(new Date()));
	 
    	snstatus sns=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
     	sns.setSn(Psn);
     	sns.setIfWh(false);
     	sns.setIfPsn(true);
     	sns.setIfInPsn(false);
     	sns.setWareHouse("");
     	sns.setCardcode("");
     	sns.setDatetime(new Timestamp(passUtilDate.getTime()));
     	sns.setCweight(Double.valueOf(0));
     	sns.setWeight(Double.valueOf(0));
     	sns.setPweight(Double.valueOf(0));
     	sns.setSweight(Double.valueOf(0));
     	sns.setItemcode("");
     	sns.setLength(Double.valueOf(0));
     	sns.setMno(v.getTxt_MNo().getText());
     	sns.setCompany(v.getCom_company().getSelectedItem().toString());
     	sns.setQc(v.getTxt_MNo().getText());
     
     	snst.add(sns);
     	for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		{
			if(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&!v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals(""))
			{
			    hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+Psn+"',updatetime=getdate() "+		
				    "where sn='"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"' "; 
				     dbu.exeSql(hql);	
			}
		}	
     	return true;
    }
    public boolean createPSN(PaSNView v) throws ParseException{
    	if(v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("���ϴ�����߹�˾����Ϊ��,�������������к�");
    		return false;
    	}   	
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ  Ĭ��ʱ���ʽ��yyyy-MM-dd HH:mm:ss  
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");  
    	Date passUtilDate = null;
    	
	    passUtilDate = f.parse(f.format(new Date()));
	 
    	snstatus sns=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
     	sns.setSn(v.getTxt_tsn().getText().toString());
     	sns.setIfWh(false);
     	sns.setIfPsn(true);
     	sns.setIfInPsn(false);
     	sns.setWareHouse("");
     	sns.setCardcode("");
     	sns.setDatetime(new Timestamp(passUtilDate.getTime()));
     	sns.setCweight(Double.valueOf(0));
     	sns.setWeight(Double.valueOf(0));
     	sns.setPweight(Double.valueOf(0));
     	sns.setSweight(Double.valueOf(0));
     	sns.setItemcode("");
     	sns.setLength(Double.valueOf(0));
     	sns.setMno(v.getTxt_MNo().getText());
     	sns.setCompany(v.getCom_company().getSelectedItem().toString());
     	sns.setQc(v.getTxt_MNo().getText());
     
     	snst.add(sns);
     	for(int i=0;i<v.getOd().getRowCount();i++)
		{
     		
			if(v.getOd().getValuethrheader(i, "���к�")!=null&&!v.getOd().getValuethrheader(i, "���к�").toString().equals(""))
			{
				  hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+v.getTxt_tsn().getText().toString()+"',updatetime=getdate() "+		
					  "where sn='"+v.getOd().getValuethrheader(i, "���к�").toString()+"' "; 
					   dbu.exeSql(hql);	
			}
		}
     
     	
     	return true;
    }
    public boolean createPSN(DeSNView v) throws ParseException{
    	if(v.getCompany().equals(""))
    	{
    		appMain.fv.setText("��˾����Ϊ��,�������������к�");
    		return false;
    	}   	
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ  Ĭ��ʱ���ʽ��yyyy-MM-dd HH:mm:ss  
    	//SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");  
    	Date passUtilDate = null;
    	
	    passUtilDate = f.parse(f.format(new Date()));
	 
    	snstatus sns=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
     	String Psn="";
     	sns.setSn(Psn);
     	sns.setIfWh(false);
     	sns.setIfPsn(true);
     	sns.setIfInPsn(false);
     	sns.setWareHouse("");
     	sns.setCardcode("");
     	sns.setDatetime(new Timestamp(passUtilDate.getTime()));
     	sns.setCweight(Double.valueOf(0));
     	sns.setWeight(Double.valueOf(0));
     	sns.setPweight(Double.valueOf(0));
     	sns.setSweight(Double.valueOf(0));
     	sns.setItemcode("");
     	sns.setLength(Double.valueOf(0));
     	sns.setMno(v.getMNo());
     	sns.setCompany(v.getCompany());
     	sns.setQc(v.getQc());
     
     	snst.add(sns);
     	int[] ik=v.getJt().getSelectedRows();
     	for(int i=0;i<ik.length;i++)
		{     		
			if(v.getOd().getValuethrheader(ik[i], "���к�")!=null&&!v.getOd().getValuethrheader(i, "���к�").toString().equals("")
					&&v.getOd().getValuethrheader(ik[i], "�Ƿ�����к�").toString().equals("false")
					&&((v.getOd().getValuethrheader(ik[i], "���������к�").toString().equals("")&&v.getOd().getValuethrheader(ik[i], "���������к�")!=null)
					||v.getOd().getValuethrheader(ik[i], "���������к�")==null)
					)
			{
				 v.getOd().setDocLineStatus(docLineStatus.remend);  
				 v.getOd().setValuethrheader(Psn, ik[i], "���������к�");
				 v.getOd().setDocLineStatus(docLineStatus.query);
				
				hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+Psn+"',updatetime=getdate() "+		
					  "where sn='"+v.getOd().getValuethrheader(ik[i], "���к�").toString()+"' "; 
					   dbu.exeSql(hql);	
			}
		}
     
     	
     	return true;
    }
    //������к��������кű����Ƿ������⣬��ȥ���ظ���
    public boolean verificationSN(JTextArea SN,boolean ifout,DeSNView v) throws BadLocationException{
    	
    	Highlighter highlighter=null;
    	highlighter=SN.getHighlighter();
    	highlighter.removeAllHighlights();
    	SN.setSelectionColor(Color.LIGHT_GRAY);
    	String s =new String(SN.getText());  
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
		
    	String[] sns=s.split(",");
    	setsn=new HashSet<String>();//����ȥ���ظ����кŵļ���
    	setsn1=new HashSet<String>();//�����ظ������кż���
    	for(int i=0;i<sns.length;i++)
    	{    
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
    	    {
    		  continue;
    	    }
        	sns1=snst.queryByDocId(sns[i]);  
        	if(sns1==null)
        	{
        		JOptionPane.showMessageDialog(null,sns[i]+"�����ڣ�����");
				return false;
        	}
	    	if(sns1.isIfPsn()&&sns1.getIfdes()!=null&&sns1.getIfdes()==true)
			{
				highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
				JOptionPane.showMessageDialog(null,sns[i]+"���Ѿ�ʧЧ�Ĵ����к�,�޷�ʹ��");
				return false;
			}
			else if(sns1.isIfPsn()&&(sns1.getIfdes()==null||sns1.getIfdes()==false))
			{
			   highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
			   JOptionPane.showMessageDialog(null,sns[i]+"�Ǵ����кţ��������");
			   String ss=this.devidePSN(sns[i]);
			   if(ss!="")
			   {
	             SN.setText(SN.getText().replace(sns[i],  this.devidePSN(sns[i])));
	         	
			   }
			}
			else{}
    	}
    	
    	 s =new String(SN.getText());  
		 pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		 m = pat.matcher(s); 
		 s = m.replaceAll(""); 
    	 sns=s.split(",");
    	for(int i=0;i<sns.length;i++)
    	{
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
    	    {
    		  continue;
    	    }
    		if(!setsn.add(sns[i]))
    		{
    			setsn1.add(sns[i]);
    		}
         	sns1=snst.queryByDocId(sns[i]);     
         	hql="select a.sn,a.objtype,a.docentry from [@desn] a "+
				"inner join drf1 b on a.docentry=b.docentry "+
				"inner join odrf c on b.docentry=c.docentry and a.objtype=c.objtype "+
				"where  c.docstatus='O' and a.ifdraft='1' and c.objtype='67' and a.sn='"+sns[i].toString()+"' "+
				"union "+
				"select a.sn,a.objtype,a.docentry from [@desn] a "+ 
				"inner join drf1 b on a.docentry=b.u_djno " + 
				"inner join odrf c on b.docentry=c.docentry and a.objtype=c.objtype "+
				"where  c.docstatus='O'  and a.ifdraft='1' "+ 
				//"and c.objtype='13' "+
				"and a.sn='"+sns[i].toString()+"'"; 
         	ob=appMain.lt.sqlclob(hql, 0, 1);
         	    	        
    		if(snst.queryByDocId(sns[i])==null){    			
	    		highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    				
	    		JOptionPane.showMessageDialog(null,sns[i]+"�����ڣ�����");	
				return false;
    		}
    		
    		if(sns[i].length()!=18)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+sns[i].toString()+"����"+String.valueOf(sns[i].toString().length())+"����ȷ");	  
   			    return false;
    		}
    		else if(ob!=null&&ob.length>0)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			if(ob[0][1].toString().equals("59"))
         		{
         		  JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+sns[i].toString()+"�������ջ��ݸ���ʹ��,����Ϊ"+ob[0][2].toString());	  
         		}
    			else if(ob[0][1].toString().equals("67"))
         		{
         		  JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+sns[i].toString()+"�����к����ݸ���ʹ��,����Ϊ"+ob[0][2].toString());	  
         		}
         		else if(ob[0][1].toString().equals("13"))
         		{
         		  JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+sns[i].toString()+"�ڱ������ݸ���ʹ��,����Ϊ"+ob[0][2].toString());	  	    		    	
         		}
         		else
         		{         			
         		}
    			return false;
    		}
    		else if(ifout==true&&sns1.isIfWh()==false&&!sns1.isIfPsn())
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,sns[i]+"δ�ڲֿ��У��޷�����");	
    			return false;
    		}
    		else if(ifout==false&&sns1.isIfWh()==true&&!sns1.isIfPsn())
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,sns[i]+"�ڲֿ��У��޷��ٴ����");	
    			return false;
    		}    		
    		else{}  		         	
    	}
    	String text=new String("");
    	if(setsn1.size()>0)
    	{
    		for (String str : setsn1) {
    			text+=str+",";
    		}
    		JOptionPane.showInputDialog(null,"��Щ���к��ظ�¼��:"+text);	
    		return false;
    	}
    	
    	return true;
    }
    //������к������뿪���Ƿ�һ��
    public boolean verificationSNA_dialog(JTextArea SN,DeSNView v){

    	boolean ifh=false;
    	for(String str : setsn)
    	{
    	    ifh=false;
    		b: 
    		for(int i=0;i<v.getOd().getRowCount();i++)
			{	
	        	if(v.getOd().getValuethrheader(i, "���к�").toString().equals(str))
	        	{
	        		ifh=true;
	        		break b;
	        	}
			}
    	    if(!ifh)
    	    {
    		   JOptionPane.showInputDialog(null,"���к�:"+str+"�ڿ�����û��");	
       		   return false;
    	    }
    	}
    	int ci=0;
    	for(int i=0;i<v.getOd().getRowCount();i++)
		{
    		if((v.getOd().getValuethrheader(i, "���к�")!=null&&!v.getOd().getValuethrheader(i, "���к�").toString().equals("")))
    		{
    			ci++;
    		}
		}
    	if(ci!=setsn.size()){
    		JOptionPane.showInputDialog(null,"���к������뿪�������кŸ�����һ��,���к���������:"+String.valueOf(setsn.size())+","
    				+"�ڿ�������"+String.valueOf(ci));	
    		return false;
    	}
    	
    	
    	return true;
    	
    }
  //������к������뵥��
    public boolean verificationSNA_details(JTextArea SN,DeSNView v){
		
    	for(String str : setsn)
    	{
    		sns1=snst.queryByDocId(str);   
    		
    	}
    	
    	return true;
    	
    }
    
    public boolean verificationPSN(JTextArea SN) throws BadLocationException{
    	snstatus sns1=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
    	Highlighter highlighter=null;
    	highlighter=SN.getHighlighter();
    	highlighter.removeAllHighlights();
    	SN.setSelectionColor(Color.LIGHT_GRAY);
    	String s =new String(SN.getText());  
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
    	String[] sns=s.split(",");
    	Set<String> setsn=new HashSet<String>();
    	Set<String> setsn1=new HashSet<String>();
    	for(int i=0;i<sns.length;i++)
    	{    	
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
	        {
		       continue;
	        }
        	sns1=snst.queryByDocId(sns[i]);     
	    	if(sns1.isIfPsn()&&sns1.getIfdes()!=null&&sns1.getIfdes()==false)
			{
			   highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
			   JOptionPane.showMessageDialog(null,sns[i]+"�Ǵ����к�,����������");
			    return false;
			}
    	}
    	for(int i=0;i<sns.length;i++)
    	{
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
    	    {
    		  continue;
    	    }
    		if(!setsn.add(sns[i]))
    		{
    			setsn1.add(sns[i]);
    		}
         	sns1=snst.queryByDocId(sns[i]);     
         	hql="select a.sn,a.objtype,a.docentry from [@desn] a "+
				"inner join drf1 b on a.docentry=b.docentry "+
				"inner join odrf c on b.docentry=c.docentry and a.objtype=c.objtype "+
				"where  c.docstatus='O' and a.ifdraft='1' and c.objtype='67' and a.sn='"+sns[i].toString()+"' "+
				"union "+
				"select a.sn,a.objtype,a.docentry from [@desn] a "+ 
				"inner join drf1 b on a.docentry=b.u_djno " + 
				"inner join odrf c on b.docentry=c.docentry and a.objtype=c.objtype "+
				"where  c.docstatus='O'  and a.ifdraft='1' and c.objtype='13' and a.sn='"+sns[i].toString()+"'"; 
         	ob=appMain.lt.sqlclob(hql, 0, 1);        	    	        
    		if(snst.queryByDocId(sns[i])==null){   			
	    		highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    				
	    		JOptionPane.showMessageDialog(null,sns[i]+"�����ڣ�����");	
				return false;
    		}
    		
    		if(sns[i].length()!=18)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+sns[i].toString()+"����"+String.valueOf(sns[i].toString().length())+"����ȷ");	  
   			    return false;
    		}
    		
    		
    		else{}
    		         	
    	}
    	String text=new String("");
    	if(setsn1.size()>0)
    	{
    		for (String str : setsn1) {
    			text+=str+",";
    		}
    		JOptionPane.showInputDialog(null,"��Щ���к��ظ�¼��:"+text);	
    		return false;
    	}
    	return true;
    }
    //Ŀǰ�Ǻ�verificationPSN����һ��
    public boolean verificationPSN_afs(JTextArea SN) throws BadLocationException{
    	snstatus sns1=new snstatus();
     	SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
    	Highlighter highlighter=null;
    	highlighter=SN.getHighlighter();
    	highlighter.removeAllHighlights();
    	SN.setSelectionColor(Color.LIGHT_GRAY);
    	String s =new String(SN.getText());  
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
    	String[] sns=s.split(",");
    	Set<String> setsn=new HashSet<String>();
    	Set<String> setsn1=new HashSet<String>();
    	for(int i=0;i<sns.length;i++)
    	{    	
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
	        {
		       continue;
	        }
        	sns1=snst.queryByDocId(sns[i]);     
	    	if(sns1.isIfPsn()&&sns1.getIfdes()!=null&&sns1.getIfdes()==false)
			{
			   highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
			   JOptionPane.showMessageDialog(null,sns[i]+"�Ǵ����к�,����������");
			    return false;
			}
    	}
    	for(int i=0;i<sns.length;i++)
    	{
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
    	    {
    		  continue;
    	    }
    		if(!setsn.add(sns[i]))
    		{
    			setsn1.add(sns[i]);
    		}
         	sns1=snst.queryByDocId(sns[i]);     
         	hql="select a.sn,a.objtype,a.docentry from [@desn] a "+
				"inner join drf1 b on a.docentry=b.docentry "+
				"inner join odrf c on b.docentry=c.docentry and a.objtype=c.objtype "+
				"where  c.docstatus='O' and a.ifdraft='1' and c.objtype='67' and a.sn='"+sns[i].toString()+"' "+
				"union "+
				"select a.sn,a.objtype,a.docentry from [@desn] a "+ 
				"inner join drf1 b on a.docentry=b.u_djno " + 
				"inner join odrf c on b.docentry=c.docentry and a.objtype=c.objtype "+
				"where  c.docstatus='O'  and a.ifdraft='1' and c.objtype='13' and a.sn='"+sns[i].toString()+"'"; 
         	ob=appMain.lt.sqlclob(hql, 0, 1);        	    	        
    		if(snst.queryByDocId(sns[i])==null){   			
	    		highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    				
	    		JOptionPane.showMessageDialog(null,sns[i]+"�����ڣ�����");	
				return false;
    		}
    		
    		if(sns[i].length()!=18)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+sns[i].toString()+"����"+String.valueOf(sns[i].toString().length())+"����ȷ");	  
   			    return false;
    		}
    		
    		
    		else{}
    		         	
    	}
    	String text=new String("");
    	if(setsn1.size()>0)
    	{
    		for (String str : setsn1) {
    			text+=str+",";
    		}
    		JOptionPane.showInputDialog(null,"��Щ���к��ظ�¼��:"+text);	
    		return false;
    	}
    	return true;	
    	
    	
    }
    public String devidePSN(String SN){
    	 hql = "SELECT a.U_sn from [@asn1] a inner join [@pasn] b on a.docEntry=b.docEntry " +
			   "where  B.U_SN='"+SN+"' and b.status<>'C' order by a.U_SN";
         ob = appMain.lt.sqlclob(hql,0,90000);
         if(ob==null||(ob!=null&&ob.length==0))
         {     	 
      	   JOptionPane.showMessageDialog(null,SN+"�����к������ٻ��߲�����");	
      	   return "";
         }
         String ks=new String("");
         for(Object[]  k: ob)
         {
      	   ks+=k[0].toString()+",";
         }
         //JOptionPane.showMessageDialog(null,ks);
         if(!ks.equals(""))
         ks=ks.substring(0,ks.length()-1);
         //JOptionPane.showMessageDialog(null,ks);
        
         return ks;
    }
    public boolean ifSNrow(String doctype,String itemcode){
    	//��������к��еĻ�������༭
    	if(doctype==null)
    	{
    		 return false;
    	}
		hql="select U_enable from dbo.[@SMS] where code='"+doctype+"' ";
		ob=appMain.lt.sqlclob(hql,0,1);
		if((ob==null||ob.length==0)&&!doctype.equals("WDELSN"))
		{										
			JOptionPane.showMessageDialog(null, "�����õ����Ƿ��������к�");
			return true;
		}
		//Ŀǰֻ�вֿ��鱸������Ҫ�Ա���������к�����
		if(doctype.equals("WDELSN"))
		{
			hql="select U_Usn from dbo.[OITM] where itemcode='"+itemcode+"' ";
			ob=appMain.lt.sqlclob(hql,0,1);
			 
			if(ob!=null&&ob.length!=0&&ob[0][0].toString().equals("Y"))
			{
				return true;
			}
			else{
			    return false;
			}
			 
		}
		else{
			return false;
		}
    }
	
    public DeSNView getV() {
		return v;
	}
	public void setV(DeSNView v) {
		this.v = v;
	}
	public snstatus getSns() {
		return sns;
	}
	public void setSns(snstatus sns) {
		this.sns = sns;
	}
	public Set<String> getSetsn() {
		return setsn;
	}
	public void setSetsn(Set<String> setsn) {
		this.setsn = setsn;
	}
	public Set<String> getSetsn1() {
		return setsn1;
	}
	public void setSetsn1(Set<String> setsn1) {
		this.setsn1 = setsn1;
	}
	public snstatus getSns1() {
		return sns1;
	}
	public void setSns1(snstatus sns1) {
		this.sns1 = sns1;
	}
	public SNStatus getSnst() {
		return snst;
	}
	public void setSnst(SNStatus snst) {
		this.snst = snst;
	}
	
	
}

