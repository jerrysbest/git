package erp.ws.sbo.client.swing.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.MainMenu.AboutView;
import erp.ws.sbo.client.swing.view.Oinv.OinvPrintView;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
import erp.ws.sbo.utils.DbUtils;
@SuppressWarnings("unused")
public class OinvsController implements FocusListener,MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener{
  private OinvView v;
 
  private String hql,hql1,hql2,cc;//query string 
  private DocMenuView dmv=DocMenuView.getdmv();
  private String[] sns;
  private static List<String> lsn=new  ArrayList<String>();
  private Object[][] ob,ob1;
  private List<Integer> csn=new ArrayList<Integer>();
  private String wh,rsn,rsn1;
  private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
  protected List<Object[]> list=new ArrayList<Object[]>(); 
  @SuppressWarnings("unchecked")
  private DaoFactory<OinvView> docf=(DaoFactory<OinvView>)appMain.ctx.getBean("OinvFactory");	
  public OinvsController(OinvView v)
  {
	  this.setV(v);
  }
  
	public OinvView getV() {
		return v;
	}
	public void setV(OinvView v) {
		this.v = v;
	}
	public DaoFactory<OinvView> getDocf() {
		return docf;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10)
		{
			if(e.getSource()==v.getJta_SN()&&v.getOd1().ds.getCnValue().equals("查询"))
			{				
				if(!v.getTxt_type().getText().equals("草稿"))
				{
					JOptionPane.showMessageDialog(null,"只有草稿可以添加序列号信息");
					return;
				}
				 hql = "select U_enable from [@SMS] where code='DELSN'";
				 ob=appMain.lt.sqlclob(hql,0,1);
				 if(ob==null||(ob!=null&&ob.length==0))
				 {
					 JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
					 return;
				 }
				 if(!ob[0][0].toString().equals("Y"))
				 {
					 JOptionPane.showMessageDialog(null,"单据的序列号未启用");
					 return;
				 }
				 hql = "select name from [@USERGROUP] where name<>'仓库组' and code=" +
				 		"(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
				 ob=appMain.lt.sqlclob(hql,0,1);
				 if(ob!=null&&ob.length!=0)
				 {					
					 JOptionPane.showMessageDialog(null,"只有仓库组用户可以在备货单添加序列号");
					 return;
				 }
				v.getDsv().getOd().setGridStatus(docLineStatus.add);
				v.getDsv().getOd1().setDs(docTitleStatus.add);
				if(docf.getAdnSN().snverification(v))
				{
					for(int i=0;i<v.getOd().getRowCount();i++)
					{	
			        	v.getOd().setValuethrheader(0,i,"扫描个数");	
					}
					String s =new String(v.getJta_SN().getText());   
				    String[] a = s.split(","); 
				    for(int i=0;i<a.length;i++)
				    {
				    	//JOptionPane.showMessageDialog(null,a[i]);
				    	docf.getAdnSN().add(v, a[i], true, "13", "O", false,0);
				    }
				}
			    
			}
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		//del
		if(e.getKeyCode()==127)
		{  
			if(e.getSource()==v.getJt())
			{
				Integer rows=0;
				for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
				{
					if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
					{
						continue;
					}
					rows=rows+1;
					if(rows>0)
					{
						JOptionPane.showMessageDialog(null,"序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(生产数量)改成0");
						return;		
						
					}
				}
				int[] i=v.getJt().getSelectedRows();
				for(int k=0;k<i.length;k++)
				{					
				 try{		
				 i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
				 }
				 catch(ArrayIndexOutOfBoundsException e0)
				 {
					 JOptionPane.showMessageDialog(null, "oinvscontroller-keyreleased-127");
				     System.out.println("oinvscontroller-keyreleased-127");
				 }
				
				}
			    v.getOd().delRow(i);	
			    v.getOd().fireTableDataChanged();
			    try{
					  v.getJt().getCellEditor().stopCellEditing();	
				    }
					catch(NullPointerException e0){
						//e0.printStackTrace();
						//return;
					}
			    
			    try{
				    if(i[0]>1)
				    {
					   v.getJt().setRowSelectionInterval(i[0]-1, i[0]-1);
				    }
				    else
				    {
				    	v.getJt().setRowSelectionInterval(0, 0);
				    }	
			    }
			    catch(ArrayIndexOutOfBoundsException e0){
			    	
			    }
			    catch(IllegalArgumentException e1){
			    	
			    }
			}
		}
		else if(e.getKeyCode()==32)
		{							 
		}
		else
		{}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("物料代码")||v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("仓库代码")||v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("是否换货"))
		{
	       v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if(e.getSource()==v.getCom_sales()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
	    //with the increase of the data，this respond speed is a little slow。I think update the field of order which will record the draft quantity after creating draft will be a solution。
		// this operation should be nonsynchronous,which means that is only one person can do this at the same time,
		//because when deleting or creating doc,especially on creating doc,that will create 2 and more  docs,which will beyond the quantity of oeder.
		//but I don't do this in order not to slow speed
	    //another solution is validating before save
	    //v.getCom_sales1().setSelectedItem(null);
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getJta_SN().setText("");
		    hql = "SELECT slpcode,slpname from oslp where slpcode='-1'";
	        ob = appMain.lt.sqlclob(hql,0,1); 
	        if(!(ob==null||ob.length==0))
	        {
	         // ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
	          v.getCom_sales1().setEnabled(true);
	          v.getCom_sales1().setEditable(true);
	          //v.getCom_sales1().setSelectedItem(Cbi);
	          v.getCom_sales1().setEditable(false);
	        }
		try{
			String cgt=((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString();
		}
		catch(ClassCastException e1){
			JOptionPane.showMessageDialog(null,"请选择销售员");
			return;
		}			
		 hql="select 0,0,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
	 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
            "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),0, a.U_Gjjg," +
            "quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),0,"+
            "a.price," +
            "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg,lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0)))," +
            "U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0))),"+
            " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
            " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Rdr1 a "+
            //不应该带c.linestatus='O'
	 		"left join Drf1 c on  a.docEntry=c.baseEntry and a.lineNum=c.baseline and c.BaseType='17' "+
            "left JOIN ODRF e ON e.DocEntry=c.DocEntry  and e.CANCELED='N'"+
	 		"left join Ordr b on b.docentry=a.docentry and b.docStatus='O' and b.canceled='N' "+
	 		"left join Oslp d on a.slpcode=d.slpcode "+
	 		"where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"' and "+
	 		"  b.U_slpCode1='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue()+"' and " +
	 		"a.LineStatus='O'   ";
           
		 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		 ob1 = appMain.lt.sqlclob(hql1,0,1);
		 hql2="select u_enable from dbo.[@sms] where code='DPERM'";
		 ob = appMain.lt.sqlclob(hql2,0,1);
        if(ob==null||ob.length==0)
        {
      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
      	  return;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
      	  
        }
        else{
          hql += "  and  b.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
	      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
        }
          hql+= " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,a.itemCode,a.dscription,"+
      	 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
                "  a.U_NumPerUnit,a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,"+
                "  a.U_Gjjg,a.price,a.lineTotal,a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
                "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt "+
                " having (isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0)))>0 "+
    		   " order by b.cardcode,a.itemcode,a.quantity ";
		 v.getOd().updatetable(hql,0);	
		
		}
		else if(e.getSource()==v.getCom_sales1()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getJta_SN().setText("");
			if(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()==null||((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()=="-1")
			{
				JOptionPane.showMessageDialog(null,"必须首先输入销售员");
				return;
			}
			
			try{
				String cgt=((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString();
			}
			catch(ClassCastException e1){
				JOptionPane.showMessageDialog(null,"请选择销售员1");
				return;
			}	
			catch(NullPointerException e2){
				//JOptionPane.showMessageDialog(null,"请选择销售员1");
				return;
			}
			 hql="select 0,0,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
		 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
	            "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),0, a.U_Gjjg," +
	            "quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),0,"+
	            "a.price," +
	            "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg,lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0)))," +
	            "U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0))),"+
	            " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	            " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Rdr1 a "+
		 		"left join Drf1 c on  a.docEntry=c.baseEntry and a.lineNum=c.baseline and c.BaseType='17' "+
	            "left JOIN ODRF e ON e.DocEntry=c.DocEntry  and e.CANCELED='N'"+
		 		"left join Ordr b on b.docentry=a.docentry and b.docStatus='O' and b.canceled='N' "+
		 		"left join Oslp d on a.slpcode=d.slpcode "+
		 		"where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"' and "+
		 		"  b.U_slpCode1='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue()+"' and a.LineStatus='O' ";
	         
			 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
			 ob1 = appMain.lt.sqlclob(hql1,0,1);
			 hql2="select u_enable from dbo.[@sms] where code='DPERM'";
			 ob = appMain.lt.sqlclob(hql2,0,1);
	        if(ob==null||ob.length==0)
	        {
	      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
	      	  return;
	        }
	        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
	        {
	      	  
	        }
	        else{
	      	  hql += " and  b.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
	      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
	        }
	          hql+=   " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,a.itemCode,a.dscription,"+
	  		 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
		            "  a.U_NumPerUnit,a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,"+
		            "  a.U_Gjjg,a.price,a.lineTotal,a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
		            "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt "+
		            " having (isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0)))>0 "+
		            " order by b.cardCode,a.itemCode,a.quantity";
			   v.getOd().updatetable(hql,0);				
		}			
		else if(e.getSource()==dmv.getjButtonadd())			
		{
			 hql = "select name from [@USERGROUP] where name<>'仓库组' and code=" +
				   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 hql1 = "select U_enable from [@SMS] where code='DELSN'";
			 ob1=appMain.lt.sqlclob(hql1,0,1);
			 if(ob1==null||(ob1!=null&&ob1.length==0))
			 {
				 JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
				 return;
			 }
			 if(ob1[0][0].toString().equals("Y")&&(ob==null))
			 {	
				 JOptionPane.showMessageDialog(null,"备货单序列号启用后仓库组操作人员不允许新增备货单");
				 return;
			 }
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getDsv().getOd1().setDs(docTitleStatus.add);
			v.getJta_SN().setText("");
		   // something about doctitle
				
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtonaddplus())
		{
			if(!v.getBtn_upstatus().isEnabled())
			{
				JOptionPane.showMessageDialog(null,"待扫描状态不可进行此操作");
				return;
			}
			if(!v.getTxt_type().getText().equals("草稿"))
			{
				JOptionPane.showMessageDialog(null,"只有草稿才能追加");
				return;
			}
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getJta_SN().setText("");
			 hql = "select name from [@USERGROUP] where name<>'仓库组' and code=" +
				   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 hql1 = "select U_enable from [@SMS] where code='DELSN'";
			 ob1=appMain.lt.sqlclob(hql1,0,1);
			 if(ob1==null||(ob1!=null&&ob1.length==0))
			 {
				 JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
				 return;
			 }
			 if(ob1[0][0].toString().equals("Y")&&(ob==null))
			 {	
				 JOptionPane.showMessageDialog(null,"备货单序列号启用后仓库组操作人员不允许追加备货单");
				 return;
			 }
			 Integer rows=0;
			 for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
			 {
				if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
				{
					continue;
				}
				rows=rows+1;
				if(rows>0)
				{
					JOptionPane.showMessageDialog(null,"备货单录入序列号信息后不允许追加");
					return;							
				}
			 }
		   // something about doctitle
			if(!(v.getTxt_type().getText().equals("草稿")&&v.getTxt_status().getText().split("-")[0].equals("未清")&&
					v.getTxt_status().getText().split("-")[1].equals("无")))
			{
				hql = "SELECT distinct a.wddstatus from odrf a inner join drf1 b on a.docentry=b.docentry where b.u_djno='"+v.getTxt_docn().getText().toString()+"'";
			    ob = appMain.lt.sqlclob(hql,0,10); 
			    for(int i=0;i<ob.length;i++)
			    {
			    	if(ob[i][0].toString().equals("Y")||ob[i][0].toString().equals("N"))
			    	{
			    		JOptionPane.showMessageDialog(null,"只有未进入审批流的草稿可以追加");
						return;
			    	}
			    }				
			}
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getOd1().setDs(docTitleStatus.addp);
			v.getOd1().setDocTitleStatus(v);
			//something about docline	
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			 hql="select 0,0,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
		 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
                "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),U_YGs=0,a.U_Gjjg,"+
                " quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),U_SQuantity=0,a.price," +
                "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg," +
                "lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0))),U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0)))," +
                " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
                " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Rdr1 a "+
		 		"left join Drf1 c on  a.docEntry=c.baseEntry and a.lineNum=c.baseline and c.BaseType='17' "+
                "left JOIN ODRF e ON e.DocEntry=c.DocEntry  and e.CANCELED='N'"+
		 		"left join Ordr b on b.docentry=a.docentry and b.docStatus='O' and b.canceled='N' "+
		 		"left join Oslp d on a.slpcode=d.slpcode "+
			    " where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"' and";
			 if(((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue()!=null&&((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()!="-1")
			 {	
		 	    hql+=" b.U_slpCode1='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue()+"' and ";
			 }			
			    hql+="   a.LineStatus='O'   ";
				 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
				 ob1 = appMain.lt.sqlclob(hql1,0,1);
				 hql2="select u_enable from dbo.[@sms] where code='DPERM'";
				 ob = appMain.lt.sqlclob(hql2,0,1);
		        if(ob==null||ob.length==0)
		        {
		      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
		      	  return;
		        }
		        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
		        {
		      	  
		        }
		        else{
		        	hql += "  and  b.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
			      	  		"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
		        }
			    
               hql+= " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,a.itemCode,a.dscription,"+
		 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
                "  a.U_NumPerUnit,a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,"+
                "  a.U_Gjjg,a.price,a.lineTotal,a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
                "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt "+
                " having (a.U_Gs-sum(isnull(c.U_Gs,0)))>0 "+
                " order by b.cardCode,a.itemCode,a.quantity";				
				 v.getOd().updatetable(hql,0);				
				
			dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtoncpsource())
		{
			
		}
		else if(e.getSource()==dmv.getjButtoncancel())
		{			
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonctarget())
		{
			//create invoice from draft 
			if(v.getBtn_upstatus().isEnabled())
			{
				JOptionPane.showMessageDialog(null,"待扫描状态才可进行此操作");
				return;
			}
			docf.getAbsDoc().ctarget(v,Integer.valueOf(v.getTxt_docn().getText()));				
			v.getDsv().getOd1().setDs(docTitleStatus.query);
		}
		else if(e.getSource()==dmv.getjButtonremend())
		{
			if(!v.getBtn_upstatus().isEnabled())
			{
				JOptionPane.showMessageDialog(null,"待扫描状态不可进行此操作");
				return;
			}
			if(!v.getTxt_type().getText().equals("草稿"))
			{
				JOptionPane.showMessageDialog(null,"只有草稿可以修改");
				return;
			}
			 hql = "select name from [@USERGROUP] where name<>'仓库组' and code=" +
				   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 hql1 = "select U_enable from [@SMS] where code='DELSN'";
			 ob1=appMain.lt.sqlclob(hql1,0,1);
			 if(ob1==null||(ob1!=null&&ob1.length==0))
			 {
				 JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
				 return;
			 }
			 if(ob1[0][0].toString().equals("Y")&&(ob==null))
			 {	
				 JOptionPane.showMessageDialog(null,"序列号启用后仓库组操作人员不允许修改备货单，只允许删除");
				 return;
			 }
			 Integer rows=0;
			 for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
			 {
				if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
				{
					continue;
				}
				rows=rows+1;
				if(rows>0)
				{
					JOptionPane.showMessageDialog(null,"备货单录入序列号信息后不允许修改");
					return;							
				}
			 }
			 v.getOd1().setDs(docTitleStatus.remend);
			 v.getOd1().setDocTitleStatus(v);
			//something about docline
			 v.getOd().setDocLineStatus(docLineStatus.remend);			
			 dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtonsave())
		{	
			try{
				  v.getJt().getCellEditor().stopCellEditing();	
			    }
				catch(NullPointerException e0){
					//e0.printStackTrace();
				}
			docf.getAbsDoc().create(v);
		}
		else if(e.getSource()==dmv.getjButtonquery())
		{					
			QueryWindowView<OinvView> qwv=new QueryWindowView<OinvView>(v,docf.getQDoc(),v.getOd1());
			v.getParent().add(qwv);
			qwv.setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			qwv.setBounds(screenSize.width/2-240, 100, 480, 250);
		}	
		else if(e.getSource()==dmv.getjButtonaddrow())
		{
						
		}	
		else if(e.getSource()==dmv.getjButtondelrow())
		{
			Integer rows=0;
			for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
			{
				if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
				{
					continue;
				}
				rows=rows+1;
				if(rows>0)
				{
					JOptionPane.showMessageDialog(null,"序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(生产数量)改成0");
					return;		
					
				}
			}
			int[] i=v.getJt().getSelectedRows();
			for(int k=0;k<i.length;k++)
			{
				
			 try{		
			 i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 JOptionPane.showMessageDialog(null, "oinvscontroller-actionPerformed-delrow");
			     System.out.println("oinvscontroller-actionPerformed-delrow");
			 }
			
			}
		    v.getOd().delRow(i);	
		    try{
			    if(i[0]>0)
			    {
				   v.getJt().setRowSelectionInterval(i[0]-1, i[0]-1);
			    }
			    else
			    {
			    	v.getJt().setRowSelectionInterval(0, 0);
			    }	
		    }
		    catch(ArrayIndexOutOfBoundsException e0){
		    	
		    }
		    catch(IllegalArgumentException e1){
		    	
		    }
		}	
		else if(e.getSource()==dmv.getjButtonfirst())
		{			
			docf.getAbsDoc().setValues(v, docf.getAbsDoc().getfirst(),"草稿");	
			v.getDsv().getOd().setGridStatus(docLineStatus.query);
			v.getDsv().getOd1().setDs(docTitleStatus.query);
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprev())
		{  
			if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
			{			
				try{
					docf.getAbsDoc().setValues(v,docf.getAbsDoc().getprev(Integer.valueOf(v.getTxt_docn().getText())),"草稿");	
				}
				catch(NumberFormatException e0){
					
				}	
			  v.getDsv().getOd().setGridStatus(docLineStatus.query);
			  v.getDsv().getOd1().setDs(docTitleStatus.query);		
			  v.getOd().setDocLineStatus(docLineStatus.query);
			  v.getOd1().setDs(docTitleStatus.query);
			  v.getOd1().setDocTitleStatus(v);
			  dmv.setquery();
			}
		}
		else if(e.getSource()==dmv.getjButtonnext())
		{  
			if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
			{	
				try{
					docf.getAbsDoc().setValues(v,docf.getAbsDoc().getnext(Integer.valueOf(v.getTxt_docn().getText())),"草稿");	
				}
				catch(NumberFormatException e0){
					
				}	
				v.getDsv().getOd().setGridStatus(docLineStatus.query);
				v.getDsv().getOd1().setDs(docTitleStatus.query);
				v.getOd().setDocLineStatus(docLineStatus.query);
				v.getOd1().setDs(docTitleStatus.query);
			    v.getOd1().setDocTitleStatus(v);
				dmv.setquery();
			}
		}
		else if(e.getSource()==dmv.getjButtonlast())
		{   		   
			docf.getAbsDoc().setValues(v, docf.getAbsDoc().getlast(),"草稿");	
			v.getDsv().getOd().setGridStatus(docLineStatus.query);
			v.getDsv().getOd1().setDs(docTitleStatus.query);
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);		
		    v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprint())
		{
			OinvPrintView opv=new OinvPrintView(this);
			v.getParent().add(opv);
			opv.setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			opv.setBounds(screenSize.width/2-100, 160, 300, 150);			
			
			//docf.setAbsDoc().print(v);
		}
		else if(e.getSource()==dmv.getjButtonclose())
		{			
			//docf.getAbsDoc().close(v);
		}
		else if(e.getSource()==dmv.getjButtoncancel())
		{
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();			
		}
		else if(e.getSource()==dmv.getjButtondel())
		{
			if(!v.getBtn_upstatus().isEnabled())
			{
				JOptionPane.showMessageDialog(null,"待扫描状态不可进行此操作");
				return;
			}
			if(!v.getTxt_type().getText().equals("草稿"))
			{
				JOptionPane.showMessageDialog(null,"只有草稿才能删除");
				return;
			}
			docf.getAbsDoc().delete(Integer.valueOf(v.getTxt_docn().getText()));
			v.getDsv().getOd().setGridStatus(docLineStatus.load);
			v.getOd1().setDs(docTitleStatus.load);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.load);
			v.getOd().setGridStatus(docLineStatus.load);
			dmv.setquery();			
		}
		else if(e.getSource()==dmv.getjButtonSN())
		{ 		
			hql = "select U_enable from [@SMS] where code='DELSN'";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 if(!ob[0][0].toString().equals("Y"))
			 {
				 JOptionPane.showMessageDialog(null,"序列号未启用");
				 return;
			 }
			if(v.getDsv()!=null)
		   {
				 if(v.getDsv().isActive()==false||v.getDsv().isVisible()==false)
		    	  {   		    		 
					 v.getDsv().setVisible(true);
					 v.getDsv().setAlwaysOnTop(true);	
				 	//av.setBounds(200, 60, screenSize.width-200, screenSize.height-150);			    		
		    	  }
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				v.getDsv().setBounds(screenSize.width/2-310, 100, 620, 450);
				if(v.getOd1().ds.getCnValue().equals("查询")&&v.getTxt_type().getText().equals("发票"))
				{
					hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,c.cardcode,a.memo,a.workcenter," +
				   		"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join inv1 b on a.docentry=b.U_djno and a.linenum=b.u_snid " +
				   		"inner join oinv c on b.docentry=c.docentry " +
				   		"where c.objtype='13' and b.U_djno='"+v.getTxt_docn().getText().toString()+"' and a.Ifdraft='0' and a.objtype='13' " +
				   		"and a.linenum='"+v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "SN行号")+"'";
				   v.getDsv().getOd().updatetable(hql, 0);
				   v.getDsv().getOd().setDocLineStatus(docLineStatus.query);
				}
				if(v.getOd1().ds.getCnValue().equals("查询")&&v.getTxt_type().getText().equals("草稿")&&v.getDsv().getOd1().ds.getCnValue().equals("查询"))
				{
					hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,c.cardcode,a.memo,a.workcenter," +
				   		"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join drf1 b on a.docentry=b.U_djno and a.linenum=b.u_snid " +
				   		"inner join odrf c on b.docentry=c.docentry " +
				   		"where c.objtype='13' and b.U_djno='"+v.getTxt_docn().getText().toString()+"' and a.Ifdraft='1' and a.objtype='13' " +
				   		"and a.linenum='"+v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "SN行号")+"'";
				   v.getDsv().getOd().updatetable(hql, 0);
				   v.getDsv().getOd().setDocLineStatus(docLineStatus.query);
				}
		   }	
		}	
		else if(e.getSource()==v.getBtn_upstatus())
		{ 	
			 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
		 	 ob = appMain.lt.sqlclob(hql,0,1);
			 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
		 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
		 	  	 "and b.code='UPDES' and a.enable='1'";
		 	 ob = appMain.lt.sqlclob(hql,0,1);
             if(ob==null||ob.length==0)
             {
        	   JOptionPane.showMessageDialog(null, "无此权限");
        	   return;
             }
			hql = "select U_enable from [@SMS] where code='DELSN'";
			ob=appMain.lt.sqlclob(hql,0,1);
			//无论是否启用序列号，都可以使用
			if(ob==null||(ob!=null&&ob.length==0)||(ob!=null&&!ob[0][0].toString().equals("Y")))
			{
				//JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
				//return;
			}		
			hql1 = "select name from [@USERGROUP] where name='仓库组' and code=" +
				   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
		    ob1=appMain.lt.sqlclob(hql1,0,1);
			if(ob1!=null&&ob1.length>0)
			{	
				JOptionPane.showMessageDialog(null,"仓库组操作员不可以进行此操作");
				return;
			}

	    	hql = "update drf1 set U_Snstatus='Y' WHERE U_djNo='" + v.getTxt_docn().getText() + "'";
			dbu.exeSql(hql);
			
			v.getBtn_upstatus().setEnabled(false);
			
		}
		else if(e.getSource()==v.getBtn_upqulity())
		{ 	
			if(v.getBtn_upstatus().isEnabled())
			{
				JOptionPane.showMessageDialog(null,"只有待扫描状态可进行此操作");
				return;
			}
			hql = "select U_enable from [@SMS] where code='DELSN'";
			ob=appMain.lt.sqlclob(hql,0,1);
			if(ob==null||(ob!=null&&ob.length==0)||(ob!=null&&!ob[0][0].toString().equals("Y")))
			{
				JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
				return;
			}		
			hql1 = "select name from [@USERGROUP] where name<>'仓库组' and code=" +
				   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
		    ob1=appMain.lt.sqlclob(hql1,0,1);
			if(ob1!=null&&ob1.length>0)
			{	
				JOptionPane.showMessageDialog(null,"只有仓库组操作员可以进行此操作");
				return;
			}
			Double sl=Double.valueOf(0);
			ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
	        snstatus sns=new snstatus();
	       // sns=isn.queryByDocId(SN);
			for(int i=0;i<v.getOd().getRowCount();i++)
			{
				if(v.getOd().getValuethrheader(i, "物料代码")!=null&&!v.getOd().getValuethrheader(i, "物料代码").toString().equals(""))
				{
					 hql="select U_Usn from dbo.[OITM] where itemcode='"+v.getOd().getValuethrheader(i, "物料代码").toString()+"' ";
					 ob=appMain.lt.sqlclob(hql,0,1);
					 
					 if(ob!=null&&ob.length!=0&&ob[0][0].toString().equals("Y")&&(v.getOd().getValuethrheader(i, "是否显示米段").toString().equals("Y")||v.getOd().getValuethrheader(i, "是否显示米段").toString().equals("是")))
					 {
						 sl=Double.valueOf(0);
						 for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
						 {
							if(v.getDsv().getOd().getValuethrheader(j, "序列号")!=null&&!v.getDsv().getOd().getValuethrheader(j, "序列号").toString().equals(""))
							{
								 sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(j, "序列号").toString());
								 if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
									 &&v.getOd().getValuethrheader(i, "物料代码").toString().equals(sns.getItemcode())
									 &&new BigDecimal(v.getDsv().getOd().getValuethrheader(j, "米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getOd().getValuethrheader(i, "物料米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP))
									 &&v.getDsv().getOd().getValuethrheader(j, "行号").toString().equals(v.getOd().getValuethrheader(i, "序号").toString()))									 
									{
								        sl=sl+sns.getCweight();								      
									}
							}
						 }
						 v.getOd().setValuethrheader(v.getOd().getValuethrheader(i, "扫描个数").toString(), i, "包装数量");
						 v.getOd().setValuethrheader(sl, i, "数量");
					 }
					 if(ob!=null&&ob.length!=0&&ob[0][0].toString().equals("Y")&&(v.getOd().getValuethrheader(i, "是否显示米段").toString().equals("N")||v.getOd().getValuethrheader(i, "是否显示米段").toString().equals("否")))
					 {   
						 sl=Double.valueOf(0);
						 for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
						 {
							 if(v.getDsv().getOd().getValuethrheader(j, "序列号")!=null&&!v.getDsv().getOd().getValuethrheader(j, "序列号").toString().equals(""))					 
							{
								 sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(j, "序列号").toString());
								 if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
									 &&v.getOd().getValuethrheader(i, "物料代码").toString().equals(sns.getItemcode())
									 &&v.getDsv().getOd().getValuethrheader(j, "行号").toString().equals(v.getOd().getValuethrheader(i, "序号").toString()))
									{
								       sl=sl+sns.getCweight();
									}
							}
						 }
						 v.getOd().setValuethrheader(v.getOd().getValuethrheader(i, "扫描个数").toString(), i, "包装数量");
						 v.getOd().setValuethrheader(sl, i, "数量");
					 }
					 
								
				}
			}
		}
		else if(e.getSource()==v.getBtn_upsn())
		{ 
			if(v.getBtn_upstatus().isEnabled())
			{
				JOptionPane.showMessageDialog(null,"只有待扫描状态可进行此操作");
				return;
			}
			if(!v.getTxt_type().getText().equals("草稿"))
			{
				JOptionPane.showMessageDialog(null,"只有草稿可以添加序列号信息");
				return;
			}
			hql = "select U_enable from [@SMS] where code='DELSN'";
			ob=appMain.lt.sqlclob(hql,0,1);
			if(ob==null||(ob!=null&&ob.length==0)||(ob!=null&&!ob[0][0].toString().equals("Y")))
			{
				JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
				return;
			}			
			hql1 = "select name from [@USERGROUP] where name<>'仓库组' and code=" +
				   "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
		    ob1=appMain.lt.sqlclob(hql1,0,1);
			if(ob1!=null&&ob1.length>0)
			{				
				JOptionPane.showMessageDialog(null,"只有仓库组操作员可以进行此操作");
				return;
			}
			v.getOd1().setDs(docTitleStatus.remend);
			v.getOd1().setDocTitleStatus(v);
			
			try{
				  v.getJt().getCellEditor().stopCellEditing();	
			    }
				catch(NullPointerException e0){
					//e0.printStackTrace();
				}
			docf.getAbsDoc().create(v);
		}
		else{
			
		}
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		for(Component cpt:dmv.getComponents())
		{
			if(cpt.getClass().equals(javax.swing.JButton.class))
			{
				((JButton)cpt).addActionListener(this);
			}			
		}
		if(v.getOd1().ds.getValue()==0)
		{
			dmv.setadd();
		}
		else if(v.getOd1().ds.getValue()==1)
		{
			dmv.setremend();
		}
		else if(v.getOd1().ds.getValue()==2)
		{
			dmv.setremend();
		}
		else if(v.getOd1().ds.getValue()==3)
		{
			dmv.setquery();
		}
		else if(v.getOd1().ds.getValue()==4)
		{
			dmv.setload();
		}
		else{
			
		}
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		for(Component cpt:dmv.getComponents())
		{
			if(cpt.getClass().equals(javax.swing.JButton.class))
			{
				((JButton)cpt).removeActionListener(this);
			}			
		}
		dmv.setdeactive();
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub	
		Integer vi;
		try{
		  //JOptionPane.showMessageDialog(null,v.getJt1().getSelectedRow());
		  vi=v.getJt1().getSelectedRow();		  
		}
		catch(ClassCastException e0){
			//e0.printStackTrace();
			return;
		}
		try{		
			if(v.getOd1().getValueAt(vi, 2)==null||v.getOd1().getRowCount()==0)
			{
				//JOptionPane.showMessageDialog(null,"没有汇总单号，无法查询 ");
				return;
			}					  	
	         docf.getAbsDoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(vi, 2).toString()),v.getOd1().getValueAt(vi, 1).toString());	
	         v.getDsv().getOd().setGridStatus(docLineStatus.query);
	         v.getOd1().setDs(docTitleStatus.query);
			 v.getOd1().setDocTitleStatus(v);
		}
	    catch(NumberFormatException e1){			
			return;
	    }
	   }

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		int[] col={v.getOd().getcolumnindex("数量"),v.getOd().getcolumnindex("金额"),v.getOd().getcolumnindex("总长"),v.getOd().getcolumnindex("总重")};
		double[] s=v.getOd().sum(col);
		v.getTxt_qsum().setText(String.valueOf(s[0]));
		v.getTxt_asum().setText(String.valueOf(s[1]));			
		v.getTxt_mssum().setText(String.valueOf(s[2]));
		v.getTxt_gjsum().setText(String.valueOf(s[3]));
		
        int lastRow = arg0.getLastRow();
        int mColIndex = arg0.getColumn();
        //JOptionPane.showMessageDialog(null,v.getOd().getValueAt(lastRow, mColIndex));	
        //deal with event of endedit,such as itemcode endedit,price enedit and so on;
        //whcih will fired when the focus left the cell	     
        if(lastRow<v.getOd().getRowCount()&&lastRow>=0&& mColIndex<v.getOd().getColumnCount()&&mColIndex>=0&&v.getOd().getValueAt(lastRow, mColIndex)!=""&&v.getOd().getValueAt(lastRow, mColIndex)!=null)
        {
          cc=((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText();
          v.getOd().valueChanged(lastRow, mColIndex,cc,"");
          //获得焦点
          if(mColIndex<v.getOd().getColumnCount()-1){
          v.getJt().setColumnSelectionInterval(mColIndex+1,mColIndex+1);
          }
          else{v.getJt().setColumnSelectionInterval(mColIndex,mColIndex);}
          v.getJt().setRowSelectionInterval(lastRow,lastRow);            
        }   
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(((JTable)(arg0.getSource())).getName()!="data-browser")
		{
					
			int[] col={v.getOd().getcolumnindex("数量"),v.getOd().getcolumnindex("金额"),v.getOd().getcolumnindex("总长"),v.getOd().getcolumnindex("总重")};
			double[] s=v.getOd().sum(col);
			
			v.getTxt_qsum().setText(String.valueOf(s[0]));
			v.getTxt_asum().setText(String.valueOf(s[1]));			
			v.getTxt_mssum().setText(String.valueOf(s[2]));
			v.getTxt_gjsum().setText(String.valueOf(s[3]));					
		
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
