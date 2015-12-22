package erp.ws.sbo.init;


import javax.swing.JOptionPane;


import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;

import erp.ws.sbo.client.swing.app.appMain;

/**
 * This program is used to create user defined tables and fields of these tables.
 * Originally,I want to use DI API TO CREATE tables.But I can't release the  UserTablesMD Object;
 * So,I use a database procedure to create tables,and use DI to add fields
 * 
 * @see erp.ws.sbo.init
 * @author Jerry Si
 * @date 2012-06-12
 */
public class CreateUd {	

	public void createTable()
	 {
		try {
			appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);			
			if(!appMain.oUserTableMD.getByKey("WFDST"))
			 {
				
				appMain.oUserTableMD.setTableName("WFDST");
				 appMain.oUserTableMD.setTableDescription("地区对应审批人");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表地区对应审批人添加失败"+appMain.oCompany.getLastErrorDescription());
				 }			

			 }
					
			 appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);
			if(!appMain.oUserTableMD.getByKey("PAYUDT"))
			 {
				 appMain.oUserTableMD.setTableName("PAYUDT");
				 appMain.oUserTableMD.setTableDescription("收款自定义表");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表收款自定义表添加失败"+appMain.oCompany.getLastErrorDescription());
				 }	 
			 }
			 appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);
			if(!appMain.oUserTableMD.getByKey("PAYUDT"))
			 {
				 appMain.oUserTableMD.setTableName("PAYUDT");
				 appMain.oUserTableMD.setTableDescription("收款自定义表");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表收款自定义表添加失败"+appMain.oCompany.getLastErrorDescription());
				 }	 
			 }
			 appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);
			if(!appMain.oUserTableMD.getByKey("MNO"))
			 {
				 appMain.oUserTableMD.setTableName("MNO");
				 appMain.oUserTableMD.setTableDescription("IP地址机号对应表");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表IP地址机号对应表添加失败"+appMain.oCompany.getLastErrorDescription());
				 }	 
			 }
		
			 
		} catch (SBOCOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
			appMain.oUserTableMD=null;
		     // com.linar.jintegra.Cleaner.release(appMain.oUserTableMD); 
	    } 
		 
		
			 JOptionPane.showMessageDialog(null,"初始化自定义 表完成，请退出重新 登录 进行自定义表字段 的 初始 化");
		
			
		
		 
	 }
}

