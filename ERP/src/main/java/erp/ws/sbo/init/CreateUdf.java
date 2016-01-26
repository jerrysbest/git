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
					cstmt.setString(2,"地区对应审批人");	
					cstmt.setString(3,"0");									
					cstmt.execute();
					
					//create table @WFLIST
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"WFLIST");		
					cstmt.setString(2,"审批流执行状况");	
					cstmt.setString(3,"3");									
					cstmt.execute();
					//create table @WFLIST1
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"WFLIST1");		
					cstmt.setString(2,"审批流执行状况-行");	
					cstmt.setString(3,"4");	
					//create table @PAYUFDS
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"PAYUDT");		
					cstmt.setString(2,"收款自定义表");	
					cstmt.setString(3,"0");		
					cstmt.execute();
					//create table @PASN
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"PASN");		
					cstmt.setString(2,"序列号组合");	
					cstmt.setString(3,"3");		
					cstmt.execute();
					//create table @ASN1
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"ASN1");		
					cstmt.setString(2,"序列号组合-行");	
					cstmt.setString(3,"4");		
					cstmt.execute();
					//create table @MNO
					cstmt = con.prepareCall(hql);	
					cstmt.setString(1,"MNO");		
					cstmt.setString(2,"IP地址机号对应表");	
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
				 appMain.oUserFieldsMD.setDescription("工作流阀值");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
				 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Rate);
				// appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表地区对应审批人字段工作流阀值添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("一级审批人");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(50);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表地区对应审批人字段一级审批人添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("二级审批人");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(50);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表地区对应审批人字段二级审批人添加失败"+appMain.oCompany.getLastErrorDescription());
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
					 appMain.oUserFieldsMD.setDescription("销售地区");
					 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
					 appMain.oUserFieldsMD.setEditSize(20);
					 appMain.oUserFieldsMD.setLinkedTable("WFDST");
					 appMain.lRetCode=appMain.oUserFieldsMD.add();
					 if(appMain.lRetCode!=0)
					 {
						 JOptionPane.showMessageDialog(null,"业务伙伴字段销售地区添加失败"+appMain.oCompany.getLastErrorDescription());
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
					 appMain.oUserFieldsMD.setDescription("是否更新用户");
					 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
					 appMain.oUserFieldsMD.setEditSize(20);
					 appMain.lRetCode=appMain.oUserFieldsMD.add();
					 if(appMain.lRetCode!=0)
					 {
						 JOptionPane.showMessageDialog(null,"生产订单标题字段是否更新用户添加失败"+appMain.oCompany.getLastErrorDescription());
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
						 appMain.oUserFieldsMD.setDescription("用户代码");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"生产订单标题字段用户代码添加失败"+appMain.oCompany.getLastErrorDescription());
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
						 appMain.oUserFieldsMD.setDescription("是否发送预警");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"生产订单标题字段是否发送预警添加失败"+appMain.oCompany.getLastErrorDescription());
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
						 appMain.oUserFieldsMD.setDescription("生产数量");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
						 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"生产订单标题字段生产数量添加失败"+appMain.oCompany.getLastErrorDescription());
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
						 appMain.oUserFieldsMD.setDescription("米段");
						 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
						 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
						 appMain.oUserFieldsMD.setEditSize(20);
						 appMain.lRetCode=appMain.oUserFieldsMD.add();
						 if(appMain.lRetCode!=0)
						 {
							 JOptionPane.showMessageDialog(null,"生产订单标题字段米段添加失败"+appMain.oCompany.getLastErrorDescription());
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
							 appMain.oUserFieldsMD.setDescription("生产完成数量");
							 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
							 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
							 appMain.oUserFieldsMD.setEditSize(20);
							 appMain.lRetCode=appMain.oUserFieldsMD.add();
							 if(appMain.lRetCode!=0)
							 {
								 JOptionPane.showMessageDialog(null,"生产订单标题字段生产完成数量添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批模板");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段审批模板添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批模板行");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段审批模板行添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批级别");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(2);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段审批级别添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("草稿号");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段草稿号添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("单据号");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段单据号添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批节点");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(2);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段审批节点添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批状态");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(1);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况字段审批状态添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批节点");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(2);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况行字段审批节点添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批人");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执行状况行字段审批人添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("审批状态");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(1);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表工作流执状况行字段审批状态添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("科目编码");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(20);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表收款自定义表字段科目编码添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("备货单锁定");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表保存锁定自定义表字段备货单锁定添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("收款单锁定");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"自定义表保存锁定自定义表字段收款单锁定添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("应收贷向锁定");
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
					 JOptionPane.showMessageDialog(null,"自定义表保存锁定自定义表字段应收贷向锁定添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("销售员1");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Numeric);
				 appMain.oUserFieldsMD.setEditSize(4);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"营销凭证字段销售员1添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("单位数量");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Float);
				 appMain.oUserFieldsMD.setSubType(SBOCOMConstants.BoFldSubTypes_st_Quantity);
				 appMain.oUserFieldsMD.setEditSize(20);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"营销凭证字段单位数量添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("是否显示米段");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(10);
				 appMain.oUserFieldsMD.setDefaultValue("N");
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"营销凭证字段是否显示米段添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("父序列号");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(40);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"序列号组合字段父序列号添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("备注");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(200);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"序列号组合字段备注添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("子序列号");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(40);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"序列号组合行字段子父序列号添加失败"+appMain.oCompany.getLastErrorDescription());
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
				 appMain.oUserFieldsMD.setDescription("备注");
				 appMain.oUserFieldsMD.setType(SBOCOMConstants.BoFieldTypes_db_Alpha);
				 appMain.oUserFieldsMD.setEditSize(200);
				 appMain.lRetCode=appMain.oUserFieldsMD.add();
				 if(appMain.lRetCode!=0)
				 {
					 JOptionPane.showMessageDialog(null,"序列号组合行字段备注添加失败"+appMain.oCompany.getLastErrorDescription());
				 }
				 else
				 {
					 appMain.oUserFieldsMD.release();
				 }
			  }
			 ****/
			 JOptionPane.showMessageDialog(null,"初始化自定义表字段完成");
		} catch (SBOCOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		 
	 }
}

