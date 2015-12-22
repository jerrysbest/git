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
				 appMain.oUserTableMD.setTableDescription("������Ӧ������");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ���������Ӧ���������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }			

			 }
					
			 appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);
			if(!appMain.oUserTableMD.getByKey("PAYUDT"))
			 {
				 appMain.oUserTableMD.setTableName("PAYUDT");
				 appMain.oUserTableMD.setTableDescription("�տ��Զ����");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ�����տ��Զ�������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }	 
			 }
			 appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);
			if(!appMain.oUserTableMD.getByKey("PAYUDT"))
			 {
				 appMain.oUserTableMD.setTableName("PAYUDT");
				 appMain.oUserTableMD.setTableDescription("�տ��Զ����");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ�����տ��Զ�������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }	 
			 }
			 appMain.oUserTableMD=SBOCOMUtil.newUserTablesMD(appMain.oCompany);
			if(!appMain.oUserTableMD.getByKey("MNO"))
			 {
				 appMain.oUserTableMD.setTableName("MNO");
				 appMain.oUserTableMD.setTableDescription("IP��ַ���Ŷ�Ӧ��");		 
				 appMain.lRetCode=appMain.oUserTableMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ����IP��ַ���Ŷ�Ӧ�����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }	 
			 }
		
			 
		} catch (SBOCOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
			appMain.oUserTableMD=null;
		     // com.linar.jintegra.Cleaner.release(appMain.oUserTableMD); 
	    } 
		 
		
			 JOptionPane.showMessageDialog(null,"��ʼ���Զ��� ����ɣ����˳����� ��¼ �����Զ�����ֶ� �� ��ʼ ��");
		
			
		
		 
	 }
}

