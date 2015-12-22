/**
 * JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposCancelled);//3
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposClosed);//2
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposPlanned);//0
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderStatusEnum_boposReleased);//1
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderTypeEnum_bopotDisassembly);//2
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderTypeEnum_bopotSpecial);//1
 *		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoProductionOrderTypeEnum_bopotStandard);//0
 * JOptionPane.showMessageDialog(null, SBOCOMConstants.BoIssueMethod_im_Backflush);//0
		JOptionPane.showMessageDialog(null, SBOCOMConstants.BoIssueMethod_im_Manual);//1
 * 
 */

package erp.ws.sbo.client.swing.dao.impl;

import java.awt.Desktop;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Owor.OworView;

public class OworDoc implements IDoc<OworView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private OworView v;
	private String hql;
	private Object[][] ob;
	private int docentry;
	public OworDoc(){
	
	}
	
	@Override
	public void create(OworView v) {
		// TODO Auto-generated method stub	
		this.v=v;
		try {
			appMain.opdoc=SBOCOMUtil.newProductionOrders(appMain.oCompany);
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}		
		appMain.opdoc.setProductionOrderType(Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString()));			
		appMain.opdoc.setProductionOrderStatus(0);		
		appMain.opdoc.setItemNo(((JTextField)v.getCom_itemcode().getEditor().getEditorComponent()).getText());
		appMain.opdoc.setPlannedQuantity(Double.valueOf(v.getTxt_planQty().getText().toString()));	
		appMain.opdoc.setWarehouse(((ComboBoxItem)v.getCom_whs().getSelectedItem()).getValue().toString());
		if(v.getTxt_date().getValue()==null|| v.getTxt_duedate().getValue()==null)
		{
			 JOptionPane.showMessageDialog(null,"日期不能为空");
			 return;
		}
		appMain.opdoc.setPostingDate((Date) v.getTxt_date().getValue());
		appMain.opdoc.setDueDate((Date) v.getTxt_duedate().getValue());
		appMain.opdoc.setJournalRemarks(v.getJta_memo().getText());		
		appMain.opdoc.getUserFields().getFields().item("U_Qty").setValue(v.getTxt_Qty().getText());
		appMain.opdoc.getUserFields().getFields().item("U_Length").setValue(v.getTxt_length().getText());
		appMain.opdoc.getUserFields().getFields().item("U_Ymd").setValue(((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString());
		appMain.opdoc.getUserFields().getFields().item("U_subuid").setValue(appMain.user1);	
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{			
			if(v.getOd().getValuethrheader(i,"物料代码")==null||v.getOd().getValuethrheader(i,"物料代码").toString().equals(""))
			{
				continue;
			}
			if(v.getOd().getValuethrheader(i,"物料代码")!=null&&v.getOd().getValuethrheader(i,"物料代码").toString()!=""&&
					(v.getOd().getValuethrheader(i,"基本数量")==null||new BigDecimal(v.getOd().getValuethrheader(i,"基本数量").toString())==new BigDecimal("0")
					||v.getOd().getValuethrheader(i,"计划数量")==null||new BigDecimal(v.getOd().getValuethrheader(i,"计划数量").toString())==new BigDecimal("0")))
			{
				int hh=i+1;					
				JOptionPane.showMessageDialog(null,"第"+hh+"行数量输入非法,请检查");	
				return;
			}
			try{
			appMain.opdoc.getLines().setWarehouse(v.getOd().getValuethrheader(i, "仓库").toString());
			appMain.opdoc.getLines().setItemNo( v.getOd().getValuethrheader(i, "物料代码").toString());
			appMain.opdoc.getLines().setPlannedQuantity(Double.valueOf( v.getOd().getValuethrheader(i, "计划数量").toString()));
			appMain.opdoc.getLines().setBaseQuantity(Double.valueOf(v.getOd().getValuethrheader(i, "基本数量").toString()));
			appMain.opdoc.getLines().setProductionOrderIssueType(Integer.valueOf(v.getOd().getValuethrheader(i, "发出类型").toString().equals("倒冲")?"0":"1"));		
			appMain.opdoc.getLines().add();		
			}
			catch(NullPointerException e0){				
				 JOptionPane.showMessageDialog(null,"第"+i+1+"行数量或者金额输入不合法，请检查");
				 return;
			}
		}	
		if(v.getOd1().ds.getCnValue()=="增加")
		{
		appMain.lRetCode=appMain.opdoc.add();
		}
		/*else if(v.getOd1().ds.getCnValue()=="修改")
		{
			appMain.lRetCode=appMain.opdoc.update();
		}
		else{}*/
		
		if(appMain.lRetCode!=0)
		{ 
			appMain.sErrMsg= appMain.oCompany.getLastError().getErrorMessage();
			appMain.lErrCode=appMain.oCompany.getLastError().getErrorCode();
			
			 if (appMain.lErrCode != -4006) // Incase adding an order failed
             {
				 JOptionPane.showMessageDialog(null,"添加失败"+appMain.lErrCode + " " + appMain.sErrMsg);// Display error message
             }
			 else
			 {
				 JOptionPane.showMessageDialog(null,"Invalid Value to Currency Exchange");
			 }
		}
		else
		{			
			docentry=Integer.valueOf(appMain.oCompany.getNewObjectKey());				
			v.getDbu().exeSql("update owor set usersign='"+Integer.valueOf(((ComboBoxItem)v.getCom_users().getSelectedItem()).getValue().toString())+"' where docentry='"+docentry+"'");
			if(appMain.opdoc.getByKey(docentry))
			{
				appMain.opdoc.setProductionOrderStatus(SBOCOMConstants.BoProductionOrderStatusEnum_boposReleased);
				appMain.opdoc.update();
			}
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.oworadd);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();					
		}
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(OworView v) {
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
		
		  hql = "SELECT min(docNum) from Owor";
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
		  hql = "SELECT  docNum from Owor where docNum<'"+id+"' order by docNum desc";
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
		  hql = "SELECT  docNum  from Owor where docNum>'"+id+"'";
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
		  hql = "SELECT max(docNum) from Owor";
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
	public void setValues(OworView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null)
		{
			return;
		}
	    hql = "SELECT a.itemcode," +
     		"status=(case a.Status when 'P' then '计划' when 'R' then '批准' when 'C' then '关闭' else '' end)," +
     		"type=(case a.Type when 'S' then '标准' when 'D' then '拆分' when 'P' then '特殊' else '' end)," +
     		"a.plannedQty,a.cmpltQty,a.postdate,a.duedate,a.usersign," +
     		"a.comments,a.wareHouse, b.invntryuom,b.salunitmsr,c.U_Name,b.itemname,d.whsname," +
     		"U_Length=isnull(a.U_Length,'0'),U_Qty=isnull(a.U_Qty,'0'),u_ymd=isnull(U_Ymd,'N') from Owor a " +
     		"inner join oitm b on a.itemcode=b.itemcode " +
     		"inner join ousr c on a.usersign=c.userid " +
     		"inner join owhs d on a.warehouse=d.whscode " +
     		"where a.docNum='"+id+"'";			
		 ob = appMain.lt.sqlclob(hql,0,1); 
		 if(ob==null||ob.length==0)
		 {
			 return;
		 }
	     v.getOd1().setDs(docTitleStatus.addp);
	     v.getOd1().setDocTitleStatus(v);
	     v.getCom_status().setEnabled(true);
	     v.getCom_status().setEditable(true);
	     v.getCom_type().setEnabled(true);
	     v.getCom_type().setEditable(true);
	     v.getCom_itemcode().setEnabled(true);
	     v.getCom_itemcode().setEditable(true);
	     v.getCom_whs().setEnabled(true);
	     v.getCom_whs().setEditable(true);
	     v.getCom_users().setEnabled(true);
	     v.getCom_users().setEditable(true);
	     v.getCom_form().setEnabled(true);
	     v.getCom_form().setEditable(true);
		 if(ob[0][1].toString().equals("计划"))
		 {
			 v.getCom_status().setSelectedItem(new  ComboBoxItem(0,"计划"));
		 }
		 else if(ob[0][1].toString().equals("批准"))
		 {
			 v.getCom_status().setSelectedItem(new  ComboBoxItem(1,"批准"));
		 }
		 else if(ob[0][1].toString().equals("关闭"))
		 {
			 v.getCom_type().setSelectedItem(new  ComboBoxItem(2,"关闭"));
		 }
		 else{}
		 if(ob[0][2].toString().equals("标准"))
		 {
			 v.getCom_type().setSelectedItem(new  ComboBoxItem(0,"标准"));
		 }
		 else if(ob[0][2].toString().equals("特殊"))
		 {
			 v.getCom_type().setSelectedItem(new  ComboBoxItem(1,"特殊"));
		 }
		 else if(ob[0][2].toString().equals("拆分"))
		 {
			 v.getCom_type().setSelectedItem(new  ComboBoxItem(2,"拆分"));
		 }
		 else{}

		 if(ob[0][17].toString().equals("Y"))
		 {
			 v.getCom_form().setSelectedItem(new  ComboBoxItem("Y","是"));
		 }
		 else if(ob[0][17].toString().equals("N"))
		 {
			 v.getCom_form().setSelectedItem(new  ComboBoxItem("N","否"));
		 }
		 else{}
		
		 v.getCom_itemcode().setSelectedItem(ob[0][0].toString());	
		 ComboBoxItem  Cbi=new ComboBoxItem(ob[0][9].toString(),ob[0][14].toString());	
		 v.getCom_whs().setSelectedItem(ob[0][9].toString());
		 Cbi=new ComboBoxItem(ob[0][7].toString(),ob[0][12].toString());	
		 v.getCom_users().setSelectedItem(Cbi);
		 
		 v.getCom_status().setEnabled(false);
	     v.getCom_status().setEditable(false);
	     v.getCom_type().setEnabled(false);
	     v.getCom_type().setEditable(false);
	     v.getCom_itemcode().setEnabled(false);
	     v.getCom_itemcode().setEditable(false);
	     v.getCom_whs().setEnabled(false);
	     v.getCom_whs().setEditable(false);
	     v.getCom_users().setEnabled(false);
	     v.getCom_users().setEditable(false);
	     v.getCom_form().setEnabled(false);
	     v.getCom_form().setEditable(false);
		 v.getTxt_docn().setText(id.toString());
		 v.getTxt_date().setText(ob[0][5].toString());
		 v.getTxt_duedate().setText(ob[0][6].toString());
		 v.getTxt_itemname().setText(ob[0][13].toString());
		 v.getTxt_unit().setText(ob[0][11].toString());
		 v.getTxt_ounit().setText(ob[0][10].toString());
		 v.getTxt_planQty().setText(ob[0][3].toString());
		 v.getTxt_Qty().setText(ob[0][16].toString());
		 v.getTxt_length().setText(ob[0][15].toString());
		 hql="select distinct linenum=p.lineNum+1,p.itemcode, b.itemname,baseQty=isnull(p.baseQty,0)," +
			"plannedQty=isnull(p.plannedQty,0),issuedQty=isnull(p.issuedQty,0)," +
			"issuetype=(case when p.issuetype='B' then '倒冲' else '领料' end),"+
           "p.warehouse,b.invntryUom,b.onhand " +
           "from Wor1 p " +
           "inner join owor a on a.docentry=p.docentry "+
           "inner join Oitm b on p.itemcode=b.itemcode " +
           "where a.docNum='"+id+"'";			
	     v.getOd().updatetable(hql,0);			
	}
	@Override
	public void close(OworView v)
	{
		try {
			appMain.opdoc=SBOCOMUtil.newProductionOrders(appMain.oCompany);
		} catch (SBOCOMException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();			
		}	
		 if(appMain.opdoc.getByKey(Integer.valueOf(v.getTxt_docn().getText())))
         {
           appMain.odoc.close();
           ((JTextField)v.getCom_status().getEditor().getEditorComponent()).setText("关闭");	
         
         }
	}
	public OworView getV() {
		return v;
	}

	public void setV(OworView v) {
		this.v = v;
	}

	@Override
	public void print(OworView v) {
		// TODO Auto-generated method stub
		hql="select U_URL from [@REPORTURL] " +
		 	  	 " where name='生产订单打印' ";
		 	 ob = appMain.lt.sqlclob(hql,0,1);
            if(ob==null||ob.length==0)
            {
              JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
          	   return;
            }
	 		 try {
				Desktop.getDesktop().browse(new URL(ob[0][0].toString()+"&DocNum="+v.getTxt_docn().getText()).toURI());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}

	@Override
	public void ctarget(OworView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}
}
