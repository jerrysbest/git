package erp.ws.sbo.client.swing.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;

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
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Orct.OrctAppliedView;
import erp.ws.sbo.client.swing.view.Orct.OrctView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OrctsController implements MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener{

	private OrctView v;
	private OrctAppliedView oav;
	private String hql,hql1,hql2;//query string 
	private DocMenuView dmv=DocMenuView.getdmv();
	private Object[] obj;
	private Object[][] ob1,ob;	
	private String cc,zzkm;//cardCode
	private DecimalFormat df = new DecimalFormat("#.000");
	@SuppressWarnings("unchecked")
	private DaoFactory<OrctView> docf=(DaoFactory<OrctView>)appMain.ctx.getBean("OrctFactory");	
				
	public OrctsController(OrctView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		if(e.getKeyCode()==32)
		{			
			if(v.getOd().getValuethrheader(0, "收款金额")!=null&&!v.getOd().getValuethrheader(0, "收款金额").toString().equals("0")&&v.getOd1().ds.getCnValue().equals("增加"))
			{
				int i=JOptionPane.showConfirmDialog(null, "是否保存");
				if(i==0)
				{										                    					
					docf.getDoc().create(v);
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
		if(e.getKeyCode()==127)
		{   /*if(v.getJt().getSelectedRows()[v.getJt().getSelectedRows().length-1]>0)
		    {
			  v.getJt().setRowSelectionInterval(v.getJt().getSelectedRows()[v.getJt().getSelectedRows().length-1]-1, v.getJt().getSelectedRows()[v.getJt().getSelectedRows().length-1]-1);
		    }
		    else
		    {
		    	v.getJt().setRowSelectionInterval(0, 0);
		    }
	        v.getOd().delRow(v.getJt().getSelectedRows());	*/	      
		}
		else
		{}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("收款金额"))
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
	    hql = "SELECT slpcode,slpname from oslp where slpcode='-1'";
        ob = appMain.lt.sqlclob(hql,0,1); 
        if(!(ob==null||ob.length==0))
        {
          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
          v.getCom_sales1().setEnabled(true);
          v.getCom_sales1().setEditable(true);
          v.getCom_sales1().setSelectedItem(Cbi);
          v.getCom_sales1().setEditable(false);
        }
		try{
			@SuppressWarnings("unused")
			String cgt=((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString();
		}
		catch(ClassCastException e1){
			JOptionPane.showMessageDialog(null,"请选择销售员");
			return;
		}	
		 hql=" select * from (SELECT 0 as '序号',0 AS '单据代码', T0.[DocEntry] AS  '发票代码' ,"+ 
				"T0.DocDate AS '日期',0 AS '行号',T0.[CardCode] AS '伙伴代码',T2.CardName AS '伙伴名称',"+  
				"'应收贷向' AS '发票类别',-T0.DocTotal AS '应收总计',  -(T0.DocTotal- T0.paidtodate) AS '应收余额', "+
				"-(T0.DocTotal- T0.paidtodate) AS '最新余额',   0.000 AS '收款金额', '' AS '分配规则',0 AS '汇总单号'  "+  
				" FROM  [dbo].[ORIN] T0   "+ 
				"INNER JOIN OSLP T1 ON T1.[SlpCode]=T0.[SlpCode]  "+   
				"INNER JOIN OCRD T2 ON T2.[CardCode]=T0.[CardCode]  "+  
				" WHERE T0.[SlpCode]='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+"' AND  T0.CANCELED='N' AND T0.DocStatus='O' AND T0.ObjType='14'   "+
				" UNION   "+   
				"SELECT 0 as '序号',0 AS '单据代码', T0.[DocEntry] AS  '发票代码' , "+ 
				"T0.DocDate AS '日期',0 AS '行号',T0.[CardCode] AS '伙伴代码',T2.CardName AS '伙伴名称',  "+ 
				"'应收' AS '发票类别',T0.DocTotal AS '应收总计',  T0.DocTotal- T0.paidtodate AS '应收余额', "+
				"T0.DocTotal- T0.paidtodate AS '最新余额',   0.000  AS '收款金额', '' AS '分配规则',0 AS '汇总单号'  "+   
				"FROM  [dbo].[OINV] T0   "+ 
				"INNER JOIN OSLP T1 ON T1.[SlpCode]=T0.[SlpCode]   "+  
				"INNER JOIN OCRD T2 ON T2.[CardCode]=T0.[CardCode]  "+  
				 "WHERE T0.[SlpCode]='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+"' " +
				 "AND  T0.CANCELED='N' AND T0.DocStatus='O' AND T0.ObjType='13'  ";		
				          
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
		          hql += "  and  T0.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
			      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
		        }
		          hql+= " ) a  order by a.伙伴代码 ,a.日期";
				 v.getOd().updatetable(hql,0);	
		 
		 if( v.getOav()!=null)
         {
           v.getOav().dispose();
           v.setOav(null);
         }
			
		}	
		if(e.getSource()==v.getCom_sales1()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
	    //with the increase of the data，this respond speed is a little slow。I think update the field of order which will record the draft quantity after creating draft will be a solution。
		// this operation should be nonsynchronous,which means that is only one person can do this at the same time,
		//because when deleting or creating doc,especially on creating doc,that will create 2 and more  docs,which will beyond the quantity of oeder.
		//but I don't do this in order not to slow speed
	    //another solution is validating before save
			if(v.getCom_sales().getSelectedItem()==null||((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()=="-1")
			{
				JOptionPane.showMessageDialog(null,"必须首先选择销售员");
				return;
			}
			
			try{
				@SuppressWarnings("unused")
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
		 hql=" select * from (SELECT 0 as '序号',0 AS '单据代码', T0.[DocEntry] AS  '发票代码' ,"+ 
				"T0.DocDate AS '日期',0 AS '行号',T0.[CardCode] AS '伙伴代码',T2.CardName AS '伙伴名称',"+  
				"'应收贷向' AS '发票类别',-T0.DocTotal AS '应收总计',  -(T0.DocTotal- T0.paidtodate) AS '应收余额', "+
				"-(T0.DocTotal- T0.paidtodate) AS '最新余额',   0.000 AS '收款金额', '' AS '分配规则',0 AS '汇总单号'  "+  
				" FROM  [dbo].[ORIN] T0   "+ 
				"INNER JOIN OSLP T1 ON T1.[SlpCode]=T0.[SlpCode]  "+   
				"INNER JOIN OCRD T2 ON T2.[CardCode]=T0.[CardCode]  "+  
				" WHERE T0.[SlpCode]='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+"' AND  " +
				"T0.[U_SlpCode1]='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()+"' AND "+
				"T0.CANCELED='N' AND T0.DocStatus='O' AND T0.ObjType='14'   "+
				" UNION  "+   
				"SELECT 0 as '序号',0 AS '单据代码', T0.[DocEntry] AS  '发票代码' , "+ 
				"T0.DocDate AS '日期',0 AS '行号',T0.[CardCode] AS '伙伴代码',T2.CardName AS '伙伴名称',  "+ 
				"'应收' AS '发票类别',T0.DocTotal AS '应收总计',  T0.DocTotal- T0.paidtodate AS '应收余额', "+
				"T0.DocTotal- T0.paidtodate AS '最新余额',   0.000  AS '收款金额', '' AS '分配规则',0 AS '汇总单号'  "+   
				"FROM  [dbo].[OINV] T0   "+ 
				"INNER JOIN OSLP T1 ON T1.[SlpCode]=T0.[SlpCode]   "+  
				"INNER JOIN OCRD T2 ON T2.[CardCode]=T0.[CardCode]  "+  
				 "WHERE T0.[SlpCode]='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()+"' AND  " +
				 "T0.[U_SlpCode1]='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()+"' AND  "+
				 "T0.CANCELED='N' AND T0.DocStatus='O' AND T0.ObjType='13' ";
				
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
          hql += "  and  T0.slpcode in (select u_sale from [@SALE_USERGROUP] where u_usergroup=" +
	      	  	"(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"') ) ";
        }
          hql+= " ) a order by a.伙伴代码,a.日期 ";
		
		 v.getOd().updatetable(hql,0);	
		 
		 if( v.getOav()!=null)
         {
           v.getOav().dispose();
           v.setOav(null);
         }
		}	
	    else if(e.getSource()==dmv.getjButtonadd())
		{
		   // something about doctitle
	    	 if( v.getOav()!=null)
	         {
	           v.getOav().dispose();
	           v.setOav(null);
	         }
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtoncpsource())
		{
			
		}
		else if(e.getSource()==dmv.getjButtonctarget())
		{
			
		}
		else if(e.getSource()==dmv.getjButtonremend())
		{
			/*v.getOd1().setDs(docTitleStatus.remend);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.remend);
			
			dmv.setadd();
			*/
		}
		else if(e.getSource()==dmv.getjButtonsave())
		{			  				
			try{
				  v.getJt().getCellEditor().stopCellEditing();	
				  v.getOav().getJt().getCellEditor().stopCellEditing();
			    }
				catch(NullPointerException e0){
					//e0.printStackTrace();
				}
			docf.getDoc().create(v);
			 
		}
		else if(e.getSource()==dmv.getjButtonquery())
		{					
			QueryWindowView<OrctView> qwv=new QueryWindowView<OrctView>(v,docf.getQDoc(),v.getOd1());
			v.getParent().add(qwv);
			qwv.setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			qwv.setBounds(screenSize.width/2-240, 100, 480, 250);
		}	
		else if(e.getSource()==dmv.getjButtonaddrow())
		{
			obj=new Object[v.getOd().getColumnCount()];	
			//v.getJt().getSelectedRow();
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
		}	
		else if(e.getSource()==dmv.getjButtondelrow())
		{
			/* if(v.getJt().getSelectedRows()[v.getJt().getSelectedRows().length-1]>0)
		    {
			  v.getJt().setRowSelectionInterval(v.getJt().getSelectedRows()[v.getJt().getSelectedRows().length-1]-1, v.getJt().getSelectedRows()[v.getJt().getSelectedRows().length-1]-1);
		    }
		    else
		    {
		    	v.getJt().setRowSelectionInterval(0, 0);
		    }
		    v.getOd().delRow(v.getJt().getSelectedRows());	*/	   
		   
		}	
		else if(e.getSource()==dmv.getjButtonfirst())
		{			
			docf.getDoc().setValues(v, docf.getDoc().getfirst(),"");	
			 if( v.getOav()!=null)
	         {
	           v.getOav().dispose();
	           v.setOav(null);
	         }
			 v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprev())
		{  
			if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
			{	
				 if( v.getOav()!=null)
		         {
		           v.getOav().dispose();
		           v.setOav(null);
		         }
					try{
						  docf.getDoc().setValues(v,docf.getDoc().getprev(Integer.valueOf(v.getTxt_docn().getText())),"");
						}
					catch(NumberFormatException e0){
						
					}	
			  
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
				 if( v.getOav()!=null)
		         {
		           v.getOav().dispose();
		           v.setOav(null);
		         }
				 try{
					 docf.getDoc().setValues(v,docf.getDoc().getnext(Integer.valueOf(v.getTxt_docn().getText())),"");
					}
				catch(NumberFormatException e0){
					
				}	
			
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
		    v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
			}
		}
		else if(e.getSource()==dmv.getjButtonlast())
		{   		   
			docf.getDoc().setValues(v, docf.getDoc().getlast(),"");		
			 if( v.getOav()!=null)
	         {
	           v.getOav().dispose();
	           v.setOav(null);
	         }
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);		
		    v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprint())
		{
			docf.getDoc().print(v);
		}
		else if(e.getSource()==dmv.getjButtonclose())
		{			
			docf.getDoc().close(v);
		}
		else if(e.getSource()==dmv.getjButtoncancel())
		{ 
			if( v.getOav()!=null)
            {
	           v.getOav().dispose();
	           v.setOav(null);
	         }
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();			
		}
		else if(e.getSource()==dmv.getjButtonapplied())
		{  
			if(v.getOd1().ds.getCnValue().equals("增加"))
			{
				if(v.getOav()==null)
				{  
					oav=new OrctAppliedView(this.getV());	
					v.setOav(oav);
					v.getParent().add(oav);
					v.getOav().setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					v.getOav().setBounds(screenSize.width/2-265, 100, 600, 500);	
				}
				else if(!v.getOav().isVisible()){	
					v.getParent().remove(v.getOav());	
					v.getParent().add(v.getOav());	
					v.getOav().setVisible(true);	
					try {
						v.getOav().setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					v.getOav().setBounds(screenSize.width/2-265, 150, 600, 500);	
				}
	
				else{
					
				}
			}
			else if(v.getOd1().ds.getCnValue().equals("查询"))
			{
				if(v.getOav()==null)
				{   //查找中转科目
			        hql = "SELECT U_aCode FROM dbo.[@PAYUDT] where name='中转科目'";
			        ob1=appMain.lt.sqlclob(hql, 0, 1);
			        if (ob1==null||ob1.length==0)
			        {
			            JOptionPane.showMessageDialog(null,"没有设置中转科目");
			        	return;
			        }
			        zzkm=ob1[0][0].toString();
					oav=new OrctAppliedView(this.getV());	
					v.setOav(oav);
					v.getParent().add(oav);
					v.getOav().setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					v.getOav().setBounds(screenSize.width/2-265, 100, 600, 500);	
					hql=" SELECT T0.Line_ID+1 AS 序号,T3.[CardCode] AS 伙伴代码,T4.[CardName] AS 伙伴名称,T0.Account AS 科目编号,"+
						"	T1.AcctName AS 科目名称, T0.Debit AS 金额  FROM JDT1 T0  "+
						"	INNER JOIN OACT T1 ON T1.AcctCode=T0.Account "+ 
						"	INNER JOIN RCT2 T2 ON T2.DocNum=left(T0.LineMemo, charindex('&',T0.LineMemo) - 1)  "+ 
						"	INNER JOIN ORCT T3 ON T3.DocNum=T2.DocNum  "+ 
						"	INNER JOIN OCRD T4 ON T4.CardCode=T3.CardCode  "+ 
						"	where charindex('&',T0.LineMemo)<>0 AND T0.Debit<>0  AND T0.Account<>'"+zzkm+"' "+
						"	AND left(T0.LineMemo, charindex('&',T0.LineMemo) - 1)  "+
						"	IN  (SELECT DocNum FROM RCT2 WHERE U_djNo='"+v.getTxt_docn().getText()+"') "+
						"	UNION   "+
						"	SELECT T0.Line_ID+1 AS 序号,T3.[CardCode] AS 伙伴代码,T4.[CardName] AS 伙伴名称,T0.Account AS 科目编号,"+
						"	T1.AcctName AS 科目名称, T0.Debit AS 金额   "+
						"	FROM JDT1 T0  "+
						"	INNER JOIN OACT T1 ON T1.AcctCode=T0.Account  "+
						"	INNER JOIN RCT2 T2 ON Convert(VARCHAR(30),T2.DocNum)=T0.LineMemo  "+ 
						"	INNER JOIN ORCT T3 ON T3.DocNum=T2.DocNum  "+ 
						"	INNER JOIN OCRD T4 ON T4.CardCode=T3.CardCode  "+ 
						"	where charindex('&',T0.LineMemo)=0 AND  T0.Account<>'"+zzkm+"' AND T0.LineMemo is not null AND T0.LineMemo<>'' "+
						"	and T0.Debit<>0 AND T0.LineMemo IN  (SELECT Convert(VARCHAR(30),DocNum) FROM RCT2 " +
						"   WHERE U_djNo='"+v.getTxt_docn().getText()+"') "+
						"	UNION   "+
						"	SELECT T0.Line_ID+1 AS 序号,T3.[CardCode] AS 伙伴代码,T4.[CardName] AS 伙伴名称,T0.Account AS 科目编号,"+
						"	T1.AcctName AS 科目名称, -T0.Credit AS 金额   "+
						"	FROM JDT1 T0  "+
						"	INNER JOIN OACT T1 ON T1.AcctCode=T0.Account  "+
						"	INNER JOIN VPM2 T2 ON T2.DocNum=right(T0.LineMemo, charindex('&',T0.LineMemo) - 1)  "+ 
						"	INNER JOIN OVPM T3 ON T3.DocNum=T2.DocNum "+  
						"	INNER JOIN OCRD T4 ON T4.CardCode=T3.CardCode   "+
						"	where charindex('&',T0.LineMemo)<>0 AND T0.Credit<>0  AND T0.Account<>'"+zzkm+"' "+
						"	AND left(T0.LineMemo, charindex('&',T0.LineMemo) - 1)  "+
						"	IN  (SELECT DocNum FROM VPM2 WHERE U_djNo='"+v.getTxt_docn().getText()+"') "+
						"	UNION  "+
						"	SELECT T0.Line_ID+1 AS 序号,T3.[CardCode] AS 伙伴代码,T4.[CardName] AS 伙伴名称,T0.Account AS 科目编号,"+
						"	T1.AcctName AS 科目名称, -T0.Credit AS 金额   "+
						"	FROM JDT1 T0  "+
						"	INNER JOIN OACT T1 ON T1.AcctCode=T0.Account  "+
						"	INNER JOIN VPM2 T2 ON Convert(VARCHAR(30),T2.DocNum)=T0.LineMemo  "+ 
						"	INNER JOIN OVPM T3 ON T3.DocNum=T2.DocNum  "+ 
						"	INNER JOIN OCRD T4 ON T4.CardCode=T3.CardCode  "+ 
						"	where charindex('&',T0.LineMemo)=0 AND  T0.Account<>'"+zzkm+"' AND T0.LineMemo is not null AND T0.LineMemo<>'' "+
						"	and T0.Credit<>0 AND T0.LineMemo IN  (SELECT Convert(VARCHAR(30),DocNum) FROM VPM2 WHERE U_djNo='"+v.getTxt_docn().getText()+"')";
					v.getOav().getOd().updatetable(hql, 0);
				}
				else if(!v.getOav().isVisible()){	
					v.getParent().remove(v.getOav());	
					v.getParent().add(v.getOav());	
					v.getOav().setVisible(true);	
					try {
						v.getOav().setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					v.getOav().setBounds(screenSize.width/2-265, 150, 600, 500);	
				}
	
				else{
					
				}
			}
			else
			{
				
			}
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
	public void valueChanged(ListSelectionEvent e) {
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
				//JOptionPane.showMessageDialog(null,"没有单号，无法查询 ");
				return;
			}
		    docf.getDoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(vi, 2).toString()),v.getOd1().getValueAt(vi, 1).toString());				   
		    v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		 catch(NumberFormatException e1){			
			 JOptionPane.showMessageDialog(null,"销售员或者霍总单号格式不合法或者为空，无法查询 ");
			 return;
		 }
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
		int[] col={v.getOd().getcolumnindex("应收总计"),v.getOd().getcolumnindex("应收余额"),v.getOd().getcolumnindex("最新余额"),v.getOd().getcolumnindex("收款金额")};	
		double[] s=v.getOd().sum(col);
		if(s!=null)
		{			
			v.getTxt_qsum().setText(String.valueOf(df.format(s[0])));
			v.getTxt_asum().setText(String.valueOf(df.format(s[1])));			
			v.getTxt_mssum().setText(String.valueOf(df.format(s[2])));
			v.getTxt_gjsum().setText(String.valueOf(df.format(s[3])));		
		}
	
	
        int lastRow = e.getLastRow();
        int mColIndex = e.getColumn();
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JTable)(e.getSource())).getName()!="data-browser")
		{	
			
			if(v.getJt().getSelectedColumn()==v.getOd().getcolumnindex("伙伴代码"))
		    {
				int[] col={v.getOd().getcolumnindex("应收总计"),v.getOd().getcolumnindex("应收余额"),v.getOd().getcolumnindex("最新余额"),v.getOd().getcolumnindex("收款金额")};	
				double[] s=v.getOd().sum(col,"伙伴代码",(String) v.getJt().getValueAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn()));
				if(s!=null)
				{			
					v.getTxt_qsum().setText(String.valueOf(df.format(s[0])));
					v.getTxt_asum().setText(String.valueOf(df.format(s[1])));			
					v.getTxt_mssum().setText(String.valueOf(df.format(s[2])));
					v.getTxt_gjsum().setText(String.valueOf(df.format(s[3])));			
				}
				
		    }
			else
			{	
				int[] col={v.getOd().getcolumnindex("应收总计"),v.getOd().getcolumnindex("应收余额"),v.getOd().getcolumnindex("最新余额"),v.getOd().getcolumnindex("收款金额")};
				double[] s=v.getOd().sum(col);
				if(s!=null)
				{			
					v.getTxt_qsum().setText(String.valueOf(df.format(s[0])));
					v.getTxt_asum().setText(String.valueOf(df.format(s[1])));			
					v.getTxt_mssum().setText(String.valueOf(df.format(s[2])));
					v.getTxt_gjsum().setText(String.valueOf(df.format(s[3])));				
				}
			}
			
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JTable)(e.getSource())).getName()!="data-browser")
		{											
			int[] col={v.getOd().getcolumnindex("应收总计"),v.getOd().getcolumnindex("应收余额"),v.getOd().getcolumnindex("最新余额"),v.getOd().getcolumnindex("收款金额")};
			double[] s=v.getOd().sum(col);
			if(s!=null)
			{			
				v.getTxt_qsum().setText(String.valueOf(df.format(s[0])));
				v.getTxt_asum().setText(String.valueOf(df.format(s[1])));			
				v.getTxt_mssum().setText(String.valueOf(df.format(s[2])));
				v.getTxt_gjsum().setText(String.valueOf(df.format(s[3])));				
			}				    
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
	public OrctView getV() {
		return v;
	}
	public void setV(OrctView v) {
		this.v = v;
	}
}
