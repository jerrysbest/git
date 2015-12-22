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
    		if(v.getOd().getValuethrheader(i, "序列号")==null||(v.getOd().getValuethrheader(i, "序列号")!=null&&v.getOd().getValuethrheader(i, "序列号").toString().equals("")))
    		{
    			continue;
    		}
    	    if(!cardCode.equals("")&&!cardCode.equals(v.getOd().getValuethrheader(i, "业务伙伴")))
    	    {
    	    	continue;
    	    }
    		desn ds=new desn();
    		
    		DeSN sn=(DeSN)appMain.ctx.getBean("DeSN");
    		ds.setIfdraft(Ifdraft);
    		ds.setObjType(Integer.valueOf(v.getOd().getValuethrheader(i, "对象类型").toString()));
    		ds.setDocEntry(Integer.valueOf(docid));
    		ds.setLineNum(Integer.valueOf(v.getOd().getValuethrheader(i, "行号").toString()));
    		ds.setSn(v.getOd().getValuethrheader(i, "序列号").toString());
    		ds.setItemcode(v.getOd().getValuethrheader(i, "物料代码").toString());
    		ds.setLength(new BigDecimal(v.getOd().getValuethrheader(i, "米段").toString()));
    		ds.setWeight(new BigDecimal(v.getOd().getValuethrheader(i, "重量").toString()));
    		ds.setCardCode(v.getOd().getValuethrheader(i, "业务伙伴").toString());
    		ds.setDirection(in);
    		ds.setIfPaSn(ifpasn);
    		ds.setPaSn("");
    		//ds.setMemo();
    		ds.setWareHouse(v.getOd().getValuethrheader(i, "仓库").toString());
    		ds.setCardCode(cardCode);
    		ds.setCdatetime(new Timestamp(new Date().getTime()));
    		ds.setUdatetime(new Timestamp(new Date().getTime())); 		
    		//ds.setWorkCenter(v.getOd().getValuethrheader(i, "工作中心").toString());
    		sn.add(ds);	
    		
    		//更新序列号状态，草稿的话不应该更新
            if(!Ifdraft)
            {
        	  snstatus sns=new snstatus();
        	  SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
           	  sns=snst.queryByDocId(v.getOd().getValuethrheader(i, "序列号").toString());
           	  if(in.equals("I"))
           	  {
           	    sns.setIfWh(true);
           	    sns.setWareHouse(v.getOd().getValuethrheader(i, "仓库").toString());
           	    sns.setCardcode("");
           	    sns.setFwh("");
           	  }
           	  else{
           		 sns.setIfWh(false);
           		 sns.setWareHouse("");
           		 sns.setCardcode(cardCode);
           		 sns.setFwh(v.getOd().getValuethrheader(i, "仓库").toString());
           	  }
           	  sns.setIfPsn(ifpasn);
           	  //sns.setIfInPsn(false);
           	  //sns.setWareHouse(v.getOd().getValuethrheader(i, "仓库").toString());
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
    		appMain.fv.setText("物料代码或者公司名称为空,不允许生成序列号");
    		return false;
    	}
    	if(v.getTxt_cweight().getText().equals("0")||v.getTxt_sweight().getText().equals("0"))
    	{
    		appMain.fv.setText("标准重量或者净重为0,不允许生成序列号");
    		return false; 
    	}
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss  
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
     	sns.setCardcode(v.getCom_ifincomed().getSelectedItem().toString().equals("是")?((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText():"");
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
    		appMain.fv.setText("物料代码或者公司名称为空,不允许生成序列号");
    		return false;
    	}
    	if(v.getTxt_cweight().getText().equals("0")||v.getTxt_sweight().getText().equals("0"))
    	{
    		appMain.fv.setText("标准重量或者净重为0,不允许生成序列号");
    		return false; 
    	}
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss  
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
    		appMain.fv.setText("物料代码或者公司名称为空,不允许生成序列号");
    		return false;
    	}   
    	//验证
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
			if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
			{
				continue;
			}
			j=j+1;
		
            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(i, "序列号").toString());
            if(sns==null)
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"不存在");
            	return false;
            }
            if(!sns.getCardcode().toString().equals(""))
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"已在客户"+sns.getCardcode().toString()+"手中");
            	return false;
            }
            if(sns.isIfPsn())
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"是大序列号，不允许打包");
            	return false;
            }
            if(i>0)
            {
            	if((!warehouse.equals(sns.getWareHouse().toString()))||(!itcode.equals(sns.getItemcode().toString()))||(!length.equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))))
            	{
            		JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"与上一序列号的物料编码、米段或者仓库不同，不允许打包");
                	return false;
            	}
            }
            if((sns.getPaSn()!=null&&!sns.getPaSn().equals(""))||sns.isIfInPsn())
            {
            	setsn.add(sns.getSn());
            	if(JOptionPane.showConfirmDialog(v,"序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"属于父序列号"+sns.getPaSn()+"父序列号将失效,是否继续")==1)
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
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss  
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
			if(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&!v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals(""))
			{
			    hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+Psn+"',updatetime=getdate() "+		
				    "where sn='"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"' "; 
				     dbu.exeSql(hql);	
			}
		}	
     	return true;
    }
    //create psn
    public boolean createPSN_afs(OignView v,String Psn) throws ParseException{
    	if(v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("物料代码或者公司名称为空,不允许生成序列号");
    		return false;
    	}   
    	//验证
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
			if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
			{
				continue;
			}
			j=j+1;
		
            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(i, "序列号").toString());
            if(sns==null)
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"不存在");
            	return false;
            }
            if(!sns.getCardcode().toString().equals(""))
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"已在客户"+sns.getCardcode().toString()+"手中");
            	return false;
            }
            if(sns.isIfPsn())
            {
            	JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"是大序列号，不允许打包");
            	return false;
            }
            if(i>0)
            {
            	if((!warehouse.equals(sns.getWareHouse().toString()))||(!itcode.equals(sns.getItemcode().toString()))||(!length.equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))))
            	{
            		JOptionPane.showMessageDialog(null, "序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"与上一序列号的物料编码、米段或者仓库不同，不允许打包");
                	return false;
            	}
            }
            if((sns.getPaSn()!=null&&!sns.getPaSn().equals(""))||sns.isIfInPsn())
            {
            	setsn.add(sns.getSn());
            	if(JOptionPane.showConfirmDialog(v,"序列号"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"属于父序列号"+sns.getPaSn()+"父序列号将失效,是否继续")==1)
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
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss  
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
			if(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&!v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals(""))
			{
			    hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+Psn+"',updatetime=getdate() "+		
				    "where sn='"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"' "; 
				     dbu.exeSql(hql);	
			}
		}	
     	return true;
    }
    public boolean createPSN(PaSNView v) throws ParseException{
    	if(v.getCom_company().getSelectedItem().toString().equals(""))
    	{
    		appMain.fv.setText("物料代码或者公司名称为空,不允许生成序列号");
    		return false;
    	}   	
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss  
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
     		
			if(v.getOd().getValuethrheader(i, "序列号")!=null&&!v.getOd().getValuethrheader(i, "序列号").toString().equals(""))
			{
				  hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+v.getTxt_tsn().getText().toString()+"',updatetime=getdate() "+		
					  "where sn='"+v.getOd().getValuethrheader(i, "序列号").toString()+"' "; 
					   dbu.exeSql(hql);	
			}
		}
     
     	
     	return true;
    }
    public boolean createPSN(DeSNView v) throws ParseException{
    	if(v.getCompany().equals(""))
    	{
    		appMain.fv.setText("公司名称为空,不允许生成序列号");
    		return false;
    	}   	
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss  
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
			if(v.getOd().getValuethrheader(ik[i], "序列号")!=null&&!v.getOd().getValuethrheader(i, "序列号").toString().equals("")
					&&v.getOd().getValuethrheader(ik[i], "是否大序列号").toString().equals("false")
					&&((v.getOd().getValuethrheader(ik[i], "所属大序列号").toString().equals("")&&v.getOd().getValuethrheader(ik[i], "所属大序列号")!=null)
					||v.getOd().getValuethrheader(ik[i], "所属大序列号")==null)
					)
			{
				 v.getOd().setDocLineStatus(docLineStatus.remend);  
				 v.getOd().setValuethrheader(Psn, ik[i], "所属大序列号");
				 v.getOd().setDocLineStatus(docLineStatus.query);
				
				hql="update dbo.[@snstatus]  set ifinpsn='1',PaSn='"+Psn+"',updatetime=getdate() "+		
					  "where sn='"+v.getOd().getValuethrheader(ik[i], "序列号").toString()+"' "; 
					   dbu.exeSql(hql);	
			}
		}
     
     	
     	return true;
    }
    //检查序列号区域序列号本身是否有问题，并去除重复项
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
    	setsn=new HashSet<String>();//所有去除重复序列号的集合
    	setsn1=new HashSet<String>();//所有重复项序列号集合
    	for(int i=0;i<sns.length;i++)
    	{    
    		if(sns[i]==null||(sns[i]!=null&&sns[i].equals("")))
    	    {
    		  continue;
    	    }
        	sns1=snst.queryByDocId(sns[i]);  
        	if(sns1==null)
        	{
        		JOptionPane.showMessageDialog(null,sns[i]+"不存在，请检查");
				return false;
        	}
	    	if(sns1.isIfPsn()&&sns1.getIfdes()!=null&&sns1.getIfdes()==true)
			{
				highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
				JOptionPane.showMessageDialog(null,sns[i]+"是已经失效的大序列号,无法使用");
				return false;
			}
			else if(sns1.isIfPsn()&&(sns1.getIfdes()==null||sns1.getIfdes()==false))
			{
			   highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
			   JOptionPane.showMessageDialog(null,sns[i]+"是大序列号，将被拆分");
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
	    		JOptionPane.showMessageDialog(null,sns[i]+"不存在，请检查");	
				return false;
    		}
    		
    		if(sns[i].length()!=18)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+sns[i].toString()+"长度"+String.valueOf(sns[i].toString().length())+"不正确");	  
   			    return false;
    		}
    		else if(ob!=null&&ob.length>0)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			if(ob[0][1].toString().equals("59"))
         		{
         		  JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+sns[i].toString()+"在生产收货草稿中使用,单号为"+ob[0][2].toString());	  
         		}
    			else if(ob[0][1].toString().equals("67"))
         		{
         		  JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+sns[i].toString()+"在序列号入库草稿中使用,单号为"+ob[0][2].toString());	  
         		}
         		else if(ob[0][1].toString().equals("13"))
         		{
         		  JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+sns[i].toString()+"在备货单草稿中使用,单号为"+ob[0][2].toString());	  	    		    	
         		}
         		else
         		{         			
         		}
    			return false;
    		}
    		else if(ifout==true&&sns1.isIfWh()==false&&!sns1.isIfPsn())
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,sns[i]+"未在仓库中，无法出库");	
    			return false;
    		}
    		else if(ifout==false&&sns1.isIfWh()==true&&!sns1.isIfPsn())
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,sns[i]+"在仓库中，无法再次入库");	
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
    		JOptionPane.showInputDialog(null,"这些序列号重复录入:"+text);	
    		return false;
    	}
    	
    	return true;
    }
    //检查序列号区域与开窗是否一致
    public boolean verificationSNA_dialog(JTextArea SN,DeSNView v){

    	boolean ifh=false;
    	for(String str : setsn)
    	{
    	    ifh=false;
    		b: 
    		for(int i=0;i<v.getOd().getRowCount();i++)
			{	
	        	if(v.getOd().getValuethrheader(i, "序列号").toString().equals(str))
	        	{
	        		ifh=true;
	        		break b;
	        	}
			}
    	    if(!ifh)
    	    {
    		   JOptionPane.showInputDialog(null,"序列号:"+str+"在开窗中没有");	
       		   return false;
    	    }
    	}
    	int ci=0;
    	for(int i=0;i<v.getOd().getRowCount();i++)
		{
    		if((v.getOd().getValuethrheader(i, "序列号")!=null&&!v.getOd().getValuethrheader(i, "序列号").toString().equals("")))
    		{
    			ci++;
    		}
		}
    	if(ci!=setsn.size()){
    		JOptionPane.showInputDialog(null,"序列号区域与开窗中序列号个数不一致,序列号区域中有:"+String.valueOf(setsn.size())+","
    				+"在开窗中有"+String.valueOf(ci));	
    		return false;
    	}
    	
    	
    	return true;
    	
    }
  //检查序列号区域与单身
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
			   JOptionPane.showMessageDialog(null,sns[i]+"是大序列号,不允许输入");
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
	    		JOptionPane.showMessageDialog(null,sns[i]+"不存在，请检查");	
				return false;
    		}
    		
    		if(sns[i].length()!=18)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+sns[i].toString()+"长度"+String.valueOf(sns[i].toString().length())+"不正确");	  
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
    		JOptionPane.showInputDialog(null,"这些序列号重复录入:"+text);	
    		return false;
    	}
    	return true;
    }
    //目前是和verificationPSN功能一样
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
			   JOptionPane.showMessageDialog(null,sns[i]+"是大序列号,不允许输入");
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
	    		JOptionPane.showMessageDialog(null,sns[i]+"不存在，请检查");	
				return false;
    		}
    		
    		if(sns[i].length()!=18)
    		{
    			highlighter.addHighlight(SN.getText().lastIndexOf(sns[i]),SN.getText().lastIndexOf(sns[i])+sns[i].length(),DefaultHighlighter.DefaultPainter);		    							    	
    			JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+sns[i].toString()+"长度"+String.valueOf(sns[i].toString().length())+"不正确");	  
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
    		JOptionPane.showInputDialog(null,"这些序列号重复录入:"+text);	
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
      	   JOptionPane.showMessageDialog(null,SN+"大序列号已销毁或者不存在");	
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
    	//如果是序列号行的话不允许编辑
    	if(doctype==null)
    	{
    		 return false;
    	}
		hql="select U_enable from dbo.[@SMS] where code='"+doctype+"' ";
		ob=appMain.lt.sqlclob(hql,0,1);
		if((ob==null||ob.length==0)&&!doctype.equals("WDELSN"))
		{										
			JOptionPane.showMessageDialog(null, "请设置单据是否启用序列号");
			return true;
		}
		//目前只有仓库组备货单需要对表体进行序列号设置
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

