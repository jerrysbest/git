package erp.ws.sbo.init;



import javax.swing.JOptionPane;

import com.sap.smb.sbo.api.SBOCOMConstants;
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
public class CreateUdf {	
	private String hql;
	private Object[][] ob=null;
	private Integer kid;

	public void createTable()
	 {		
		 
		/**  Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	    	   Connection con = session.connection(); 
				CallableStatement cstmt;
				hql="{call zdy_Sqlexe}";
				try{
					//create userdefined tables and execute many other operations
					cstmt = con.prepareCall(hql);												
					cstmt.execute();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hql = "{call zdy_udt(?,?,?)}";
		         try {	
		        
		        	//create table @WFDST
					cstmt = con.prepareCall(hql);				
					cstmt.setString(1,"WFDST");		
					cstmt.setString(2,"������Ӧ������");	
					cstmt.setString(3,"0");									
					cstmt.execute();
					
					//create table @WFLIST
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"WFLIST");		
					cstmt.setString(2,"������ִ��״��");	
					cstmt.setString(3,"3");									
					cstmt.execute();
					//create table @WFLIST1
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"WFLIST1");		
					cstmt.setString(2,"������ִ��״��-��");	
					cstmt.setString(3,"4");	
					//create table @PAYUFDS
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"PAYUDT");		
					cstmt.setString(2,"�տ��Զ����");	
					cstmt.setString(3,"0");		
					cstmt.execute();
					//create table @PASN
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"PASN");		
					cstmt.setString(2,"���к����");	
					cstmt.setString(3,"3");		
					cstmt.execute();
					//create table @ASN1
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"ASN1");		
					cstmt.setString(2,"���к����-��");	
					cstmt.setString(3,"4");		
					cstmt.execute();
					//create table @MNO
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"MNO");		
					cstmt.setString(2,"IP��ַ���Ŷ�Ӧ��");	
					cstmt.setString(3,"0");		
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
	      }***/
	      //add columns for tables
		 try {
			 
			//add columns for table @WFDST
			appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);		 
			if(!appMain.oUserFieldsMD.getByKey("@WFDST",0))
			 {
				 appMain.oUserFieldsMD.setTableName("WFDST");
				 appMain.oUserFieldsMD.setName("wfvalue");
				 appMain.oUserFieldsMD.setDescription("��������ֵ");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
				 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Rate);
				// appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ���������Ӧ�������ֶι�������ֵ���ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFDST",1))
			 {
				 appMain.oUserFieldsMD.setTableName("WFDST");
				 appMain.oUserFieldsMD.setName("aCode1");
				 appMain.oUserFieldsMD.setDescription("һ��������");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(50);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ���������Ӧ�������ֶ�һ�����������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFDST",2))
			 {
				 appMain.oUserFieldsMD.setTableName("WFDST");
				 appMain.oUserFieldsMD.setName("aCode2");
				 appMain.oUserFieldsMD.setDescription("����������");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(50);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ���������Ӧ�������ֶζ������������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 
			//add columns for OCRD
			    appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
				if(!appMain.oUserFieldsMD.getByKey("OCRD",6))
				 {
					 appMain.oUserFieldsMD.setTableName("OCRD");
					 appMain.oUserFieldsMD.setName("terID");
					 appMain.oUserFieldsMD.setDescription("���۵���");
					 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
					 appMain.oUserFieldsMD.setEditSize(20);
					 appMain.oUserFieldsMD.setLinkedTable("WFDST");
					 appMain.lRetCode=appMain.oUserFieldsMD.add();
					 if(appMain.lRetCode!=0)
					 {
						 JOptionPane.showMessageDialog(null,"ҵ�����ֶ����۵������ʧ��"+appMain.oCompany.getLastErrorDescription());
					 }
					 else
					 {
						 appMain.oUserFieldsMD.release();
					 }
				 }
				//add columns for Owor
			    appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
				if(!appMain.oUserFieldsMD.getByKey("OWOR",0))
				 {
					 appMain.oUserFieldsMD.setTableName("OWOR");
					 appMain.oUserFieldsMD.setName("Gxyh");
					 appMain.oUserFieldsMD.setDescription("�Ƿ�����û�");
					 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
					 appMain.oUserFieldsMD.setEditSize(20);
					 appMain.lRetCode=appMain.oUserFieldsMD.add();
					 if(appMain.lRetCode!=0)
					 {
						 JOptionPane.showMessageDialog(null,"�������������ֶ��Ƿ�����û����ʧ��"+appMain.oCompany.getLastErrorDescription());
					 }
					 else
					 {
						 appMain.oUserFieldsMD.release();
					 }
				 }
				
				  appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
					if(!appMain.oUserFieldsMD.getByKey("OWOR",1))
					 {
						 appMain.oUserFieldsMD.setTableName("OWOR");
						 appMain.oUserFieldsMD.setName("UCode");
						 appMain.oUserFieldsMD.setDescription("�û�����");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"�������������ֶ��û��������ʧ��"+appMain.oCompany.getLastErrorDescription());
						 }
						 else
						 {
							 appMain.oUserFieldsMD.release();
						 }
					 }
					 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
					if(!appMain.oUserFieldsMD.getByKey("OWOR",2))
					 {
						 appMain.oUserFieldsMD.setTableName("OWOR");
						 appMain.oUserFieldsMD.setName("Yjfs");
						 appMain.oUserFieldsMD.setDescription("�Ƿ���Ԥ��");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"�������������ֶ��Ƿ���Ԥ�����ʧ��"+appMain.oCompany.getLastErrorDescription());
						 }
						 else
						 {
							 appMain.oUserFieldsMD.release();
						 }
					 }
					 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
					if(!appMain.oUserFieldsMD.getByKey("OWOR",3))
					 {
						 appMain.oUserFieldsMD.setTableName("OWOR");
						 appMain.oUserFieldsMD.setName("Qty");
						 appMain.oUserFieldsMD.setDescription("��������");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
						 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"�������������ֶ������������ʧ��"+appMain.oCompany.getLastErrorDescription());
						 }
						 else
						 {
							 appMain.oUserFieldsMD.release();
						 }
					 }
					 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
					if(!appMain.oUserFieldsMD.getByKey("OWOR",4))
					 {
						 appMain.oUserFieldsMD.setTableName("OWOR");
						 appMain.oUserFieldsMD.setName("Length");
						 appMain.oUserFieldsMD.setDescription("�׶�");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
						 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"�������������ֶ��׶����ʧ��"+appMain.oCompany.getLastErrorDescription());
						 }
						 else
						 {
							 appMain.oUserFieldsMD.release();
						 }
					 }
					 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
						if(!appMain.oUserFieldsMD.getByKey("OWOR",5))
						 {
							 appMain.oUserFieldsMD.setTableName("OWOR");
							 appMain.oUserFieldsMD.setName("CQty");
							 appMain.oUserFieldsMD.setDescription("�����������");
							 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
							 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
							 appMain.oUserFieldsMD.setEditSize(20);
							 appMain.lRetCode=appMain.oUserFieldsMD.add();
							 if(appMain.lRetCode!=0)
							 {
								 JOptionPane.showMessageDialog(null,"�������������ֶ���������������ʧ��"+appMain.oCompany.getLastErrorDescription());
							 }
							 else
							 {
								 appMain.oUserFieldsMD.release();
							 }
						 }
			/**
			 //add columns for table @WFLIST
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",0))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("wfTable");
				 appMain.oUserFieldsMD.setDescription("����ģ��");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶ�����ģ�����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",1))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("wfLine");
				 appMain.oUserFieldsMD.setDescription("����ģ����");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶ�����ģ�������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",2))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("apStage");
				 appMain.oUserFieldsMD.setDescription("��������");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(2);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶ������������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",3))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("draftid");
				 appMain.oUserFieldsMD.setDescription("�ݸ��");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶβݸ�����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",4))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("docid");
				 appMain.oUserFieldsMD.setDescription("���ݺ�");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶε��ݺ����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",5))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("wfNode");
				 appMain.oUserFieldsMD.setDescription("�����ڵ�");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(2);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶ������ڵ����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST",6))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST");
				 appMain.oUserFieldsMD.setName("wfStatus");
				 appMain.oUserFieldsMD.setDescription("����״̬");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(1);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״���ֶ�����״̬���ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			//add columns for table @WFLIST1
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST1",0))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST1");
				 appMain.oUserFieldsMD.setName("wfNode");
				 appMain.oUserFieldsMD.setDescription("�����ڵ�");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(2);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״�����ֶ������ڵ����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST1",1))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST1");
				 appMain.oUserFieldsMD.setName("wfPerson");
				 appMain.oUserFieldsMD.setDescription("������");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ��״�����ֶ����������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@WFLIST1",2))
			 {
				 appMain.oUserFieldsMD.setTableName("WFLIST1");
				 appMain.oUserFieldsMD.setName("apStatus");
				 appMain.oUserFieldsMD.setDescription("����״̬");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(1);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ��������ִ״�����ֶ�����״̬���ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }**/
			//add columns for table @PAYUDT
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@PAYUDT",0))
			 {
				 appMain.oUserFieldsMD.setTableName("PAYUDT");
				 appMain.oUserFieldsMD.setName("aCode");
				 appMain.oUserFieldsMD.setDescription("��Ŀ����");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(20);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ�����տ��Զ�����ֶο�Ŀ�������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			//add columns for table @BCSD
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@BCSD",0))
			 {
				 appMain.oUserFieldsMD.setTableName("BCSD");
				 appMain.oUserFieldsMD.setName("bhd_sd");
				 appMain.oUserFieldsMD.setDescription("����������");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ�����������Զ�����ֶα������������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@BCSD",1))
			 {
				 appMain.oUserFieldsMD.setTableName("BCSD");
				 appMain.oUserFieldsMD.setName("skd_sd");
				 appMain.oUserFieldsMD.setDescription("�տ����");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ�����������Զ�����ֶ��տ�������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);
			 if(!appMain.oUserFieldsMD.getByKey("@BCSD",2))
			 {
				 appMain.oUserFieldsMD.setTableName("BCSD");
				 appMain.oUserFieldsMD.setName("ysdx_sd");
				 appMain.oUserFieldsMD.setDescription("Ӧ�մ�������");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.getValidValues().setValue("N");
				 appMain.oUserFieldsMD.getValidValues().add();
				 appMain.oUserFieldsMD.getValidValues().setValue("Y");
				 appMain.oUserFieldsMD.getValidValues().add();
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"�Զ�����������Զ�����ֶ�Ӧ�մ����������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			 }
			//add columns for marketing document rows
			 hql="select  FieldID from CUFD where TableID='ORDR' order by FieldID desc";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 if(ob==null||ob.length==0)
			 {
				 kid=0;
			 }
			 else
			 {
				 kid=Integer.valueOf(ob[0][0].toString())+1;
			 }
				 
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("ORDR",kid))
			 {
				 appMain.oUserFieldsMD.setTableName("ORDR");
				 appMain.oUserFieldsMD.setName("slpcode1");
				 appMain.oUserFieldsMD.setDescription("����Ա1");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(4);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"Ӫ��ƾ֤�ֶ�����Ա1���ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("RDR1",23))
			 {
				 appMain.oUserFieldsMD.setTableName("RDR1");
				 appMain.oUserFieldsMD.setName("NumPerUnit");
				 appMain.oUserFieldsMD.setDescription("��λ����");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
				 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
				 appMain.oUserFieldsMD.setEditSize(20);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"Ӫ��ƾ֤�ֶε�λ�������ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
				
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("RDR1",24))
			 {
				 appMain.oUserFieldsMD.setTableName("RDR1");
				 appMain.oUserFieldsMD.setName("Ymd");
				 appMain.oUserFieldsMD.setDescription("�Ƿ���ʾ�׶�");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"Ӫ��ƾ֤�ֶ��Ƿ���ʾ�׶����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
			 
			 /****
			//add columns for @PASN 
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("PASN",0))
			 {
				 appMain.oUserFieldsMD.setTableName("PASN");
				 appMain.oUserFieldsMD.setName("SN");
				 appMain.oUserFieldsMD.setDescription("�����к�");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(40);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"���к�����ֶθ����к����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }			
			//add columns for @PASN 
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("PASN",1))
			 {
				 appMain.oUserFieldsMD.setTableName("PASN");
				 appMain.oUserFieldsMD.setName("Memo");
				 appMain.oUserFieldsMD.setDescription("��ע");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(200);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"���к�����ֶα�ע���ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
			 //add columns for @ASN1 
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("ASN1",0))
			 {
				 appMain.oUserFieldsMD.setTableName("ASN1");
				 appMain.oUserFieldsMD.setName("SN");
				 appMain.oUserFieldsMD.setDescription("�����к�");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(40);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"���к�������ֶ��Ӹ����к����ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
			 //add columns for @ASN1 
			 appMain.oUserFieldsMD=SBOCOMUtil.newUserFieldsMD(appMain.oCompany);	
			 if(!appMain.oUserFieldsMD.getByKey("ASN1",1))
			 {
				 appMain.oUserFieldsMD.setTableName("ASN1");
				 appMain.oUserFieldsMD.setName("Memo");
				 appMain.oUserFieldsMD.setDescription("��ע");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(200);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"���к�������ֶα�ע���ʧ��"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
			 ****/
			 JOptionPane.showMessageDialog(null,"��ʼ���Զ�����ֶ����");
		} catch (SBOCOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		 
	 }
}

