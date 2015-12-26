package erp.ws.sbo.client.swing.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.dom4j.DocumentException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.api.SBOErrorMessage;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.DwXml;
import erp.ws.sbo.client.swing.view.Foot.FootView;
import erp.ws.sbo.client.swing.view.LeftMenu.LeftMenuView;
import erp.ws.sbo.client.swing.view.Login.LoginView;
import erp.ws.sbo.utils.HibernateUtils;
import java.net.*;

public class LoginsController implements ActionListener{
	private LoginView v;
	private String hql;
	private Object[][] ob;	
	
    public LoginsController(LoginView v)
    {
	   this.setV(v);
    }
    public void actionPerformed(ActionEvent e) { 	
    	   
    	DwXml dx=new DwXml();
    	
    		String[] ser={v.getLserver().getText().toString().trim(),v.getDserver().getText().toString().trim(),((ComboBoxItem)v.getComp().getSelectedItem()).getValue().toString()};
    	
    	try {
			dx.editXML(appMain.xmlPath,ser);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	InetAddress ia=null;
		try {
			ia=InetAddress.getLocalHost();
			
			String localname=ia.getHostName();
			//String localip=ia.getHostAddress();
			if(localname.equals("jerrys2008")&&v.getUser().getText().toString().trim().equals(""))
			{
				appMain.oCompany=SBOCOMUtil.newCompany();   
		    	appMain.oCompany.setDbServerType(SBOCOMConstants.BoDataServerTypes_dst_MSSQL2005);
		    	appMain.oCompany.setUseTrusted(false);
		    	appMain.oCompany.setServer("127.0.0.1");	
		    	appMain.oCompany.setLicenseServer("127.0.0.1");    
		    	appMain.oCompany.setCompanyDB("NQS");   
		    	appMain.oCompany.setLanguage(SBOCOMConstants.BoSuppLangs_ln_Chinese);   	
		    	appMain.oCompany.setDbUserName(appMain.config.getDbusername());
		    	appMain.oCompany.setDbPassword(appMain.config.getDbuserpas());
		    	appMain.oCompany.setUserName("manager");
		    	appMain.oCompany.setPassword("qwer"); 
		    	JOptionPane.showMessageDialog(null,"0");
		    	JOptionPane.showMessageDialog(null,appMain.oCompany);
		    	int success = appMain.oCompany.connect();  
		    	JOptionPane.showMessageDialog(null,"success"+success);
		    	if(success != 0){  
		            SBOErrorMessage errorMessage = appMain.oCompany.getLastError();  
		            System.out.println(errorMessage.getErrorMessage());  
		            JOptionPane.showMessageDialog(null,errorMessage.getErrorMessage());           
		        }else{   
		        	JOptionPane.showMessageDialog(null,"1");
			        appMain.user1=v.getTf_user1().getText().toString().trim();
			        //JOptionPane.showMessageDialog(null,v.getMNo_comp().getSelectedItem());
			        appMain.Mno=((ComboBoxItem)v.getMNo_comp().getSelectedItem()).getValue().toString();
		            JOptionPane.showMessageDialog(null,"登陆成功");
		            LeftMenuView lm=new LeftMenuView();
		    		FootView foot=new FootView();
				    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    	
				    v.getParent().add(lm);
		    		appMain.v.setTitle("QS-ERP"+v.getDserver().getText().toString().trim()+appMain.oCompany.getCompanyName()+appMain.oCompany.getUserName()+"用户(1)"+appMain.user1+"机号"+appMain.Mno);
		    		v.dispose();
		    	    
		    	    lm.setBounds(0, 0, 200, screenSize.height-220);
		 		    foot.setBounds(0, screenSize.height-120, screenSize.width, 40);
		 		    
		 		  /* I don't know how to call wsdl in java,so  I use c#  to transmit workflow  message
		 		   *  WfTimerTask wft=new WfTimerTask(foot,1);
		 		    wft.start();*/
		        }    
		    	
		    	
			}
			else{
				appMain.oCompany=SBOCOMUtil.newCompany();   
		    	appMain.oCompany.setDbServerType(SBOCOMConstants.BoDataServerTypes_dst_MSSQL2005);
		    	appMain.oCompany.setUseTrusted(false);
		    	appMain.oCompany.setServer(v.getDserver().getText().toString().trim());	
		    	appMain.oCompany.setLicenseServer(v.getLserver().getText().toString().trim());    
		    	appMain.oCompany.setCompanyDB(((ComboBoxItem)(v.getComp().getSelectedItem())).getValue().toString());   
		    	appMain.oCompany.setLanguage(SBOCOMConstants.BoSuppLangs_ln_Chinese);   	
		    	appMain.oCompany.setDbUserName(appMain.config.getDbusername());
		    	appMain.oCompany.setDbPassword(appMain.config.getDbuserpas());
		    	appMain.oCompany.setUserName(v.getUser().getText().toString().trim());
		    	appMain.oCompany.setPassword(String.valueOf(v.getPas().getPassword()).trim());    	
		    	
		    	int success = appMain.oCompany.connect();  
		        if(success != 0){  
		            SBOErrorMessage errorMessage = appMain.oCompany.getLastError();  
		            System.out.println(errorMessage.getErrorMessage());  
		            JOptionPane.showMessageDialog(null,errorMessage.getErrorMessage());           
		        }else{   
			    	hql="select * from [@USERS]  "+
						"where u_uid='"+appMain.oCompany.getUserSignature()+"' and u_subuid='"+v.getTf_user1().getText().toString().trim()+"' " +
						"and u_pwd='"+String.valueOf(v.getTf_pas1().getPassword())+"' and u_ifuse='Y'";
			         	ob=appMain.lt.sqlclob(hql, 0, 1);
			         	if(ob==null||(ob!=null&&ob.length==0))
			         	{
			         		 JOptionPane.showMessageDialog(null,"密码1错误或者用户未启用");
			         		 return;
			         	}
		         	hql="select count(*) from [@USERS]  "+
						"where u_ifuse='Y'";
			         	ob=appMain.lt.sqlclob(hql, 0, 1);
			         	if(ob!=null&&Integer.valueOf(ob[0][0].toString())>100)
			         	{
			         		JOptionPane.showMessageDialog(null,"启用用户超过100，请停用部分用户!");
			         		return;
			         	}	
			        appMain.user1=v.getTf_user1().getText().toString().trim();
			        appMain.Mno=((ComboBoxItem)v.getMNo_comp().getSelectedItem()).getValue().toString();
		            JOptionPane.showMessageDialog(null,"登陆成功");
		            LeftMenuView lm=new LeftMenuView();
		    		FootView foot=new FootView();
				    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    	
				    v.getParent().add(lm);
				    appMain.v.setTitle("QS-ERP"+v.getDserver().getText().toString().trim()+appMain.oCompany.getCompanyName()+appMain.oCompany.getUserName()+"用户(1)"+appMain.user1+"机号"+appMain.Mno);
			    	v.dispose();
		    	    
		    	    lm.setBounds(0, 0, 200, screenSize.height-220);
		 		    foot.setBounds(0, screenSize.height-120, screenSize.width, 40);
		 		    
		 		  /* I don't know how to call wsdl in java,so  I use c#  to transmit workflow  message
		 		   *  WfTimerTask wft=new WfTimerTask(foot,1);
		 		    wft.start();*/
		        }    
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    		
		   
    }
   public void initview()
   {   
	   v.getLserver().setText(appMain.config.getLserver());
	   v.getDserver().setText(appMain.config.getDserver());
	 //绑定公司	   
   	Session session = HibernateUtils.getSession();
      Transaction t = session.beginTransaction();{
       try {
           //** 生成主键对象 *//        
           Query query=(Query)session.createQuery("select p.id.dbName,p.cmpName from Srgc p where p.id.dbName='NQS'");
    
           @SuppressWarnings("unchecked")
		List<Object[]> list=query.list();
           t.commit();
    
           @SuppressWarnings("unused")
		Iterator<?> it=list.iterator();
      
           for(Object[] object : list){
      
           v.getComp().addItem(new  ComboBoxItem(object[0],object[1].toString()));
           }
         } catch (HibernateException e1) {
           e1.printStackTrace();
           t.rollback();
         } finally {
       	  HibernateUtils.closeSession(session);
         } 
   	 }
	   
   }
	public LoginView getV() {
		return v;
	}
	public void setV(LoginView v) {
		this.v = v;
	}
}
