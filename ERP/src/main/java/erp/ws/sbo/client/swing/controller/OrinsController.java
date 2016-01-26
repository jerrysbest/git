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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Orin.OrinView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
@SuppressWarnings("unused")
public class OrinsController implements ActionListener, MouseListener, TableModelListener, KeyListener, ListSelectionListener, InternalFrameListener {

	private OrinView v;
	private String hql,cc;//query string 
	private String[] sns;
    private Object[] obj;
 
	private Object[][] ob,ob1;
	private DocMenuView dmv=DocMenuView.getdmv();
	private List<Integer> csn=new ArrayList<Integer>();
	private String rsn1;
    @SuppressWarnings("unchecked")
	private DaoFactory<OrinView> docf=(DaoFactory<OrinView>)appMain.ctx.getBean("OrinFactory");	
	public OrinsController(OrinView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
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
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
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
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
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
			e0.printStackTrace();
			return;
		}

		try{
			if(v.getOd1().getValueAt(vi, 2)==null||v.getOd1().getRowCount()==0)
			{
				//JOptionPane.showMessageDialog(null,"没有汇总单号，无法查询 ");
				return;
			}		
            docf.getDoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(vi, 2).toString()),"");	                 
		    v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		 catch(NumberFormatException e1){			
				return;
		 }

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		if(e.getKeyCode()==10)
		{
			if(e.getSource()==v.getJta_SN())
			{
				hql = "select U_enable from [@SMS] where code='CRESN'";
				  ob=appMain.lt.sqlclob(hql,0,1);
				 if(ob==null||(ob!=null&&ob.length==0))
				 {
					 JOptionPane.showMessageDialog(null,"单据的序列号启用未设置");
					 return;
				 }
				 if(!ob[0][0].toString().equals("Y"))
				 {
					 return;
				 }
				 if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2"))
				 {
					 return;
				 }
				
				if(docf.getAdnSN().snverification(v))
				{
					for(int i=0;i<v.getOd().getRowCount();i++)
					{	if(v.getOd().getValuethrheader(i, "物料代码")!=null&&!v.getOd().getValuethrheader(i, "物料代码").toString().equals(""))
					    {
			        	  v.getOd().setValuethrheader(0,i,"扫描个数");	
					    }
					}
					String s =new String(v.getJta_SN().getText());   
				    String[] a = s.split(","); 
				    for(int i=0;i<a.length;i++)
				    {
				      docf.getAdnSN().add(v, a[i], false, "14", "I", false,0);
				    }
				}
			    
			}
		
		}
		
		else
		{}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		//JOptionPane.showMessageDialog(null,e.getKeyCode());
		obj=new Object[v.getOd().getColumnCount()];	
		//del键
		if(e.getKeyCode()==127&&e.getSource()==v.getJt())
		{   
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
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
						appMain.fv.setText("序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(生产数量)改成0");
						return;		
						
					}
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
				 JOptionPane.showMessageDialog(null, "orinscontroller-actionPerformed-delrow");
			     System.out.println("orinscontroller-actionPerformed-delrow");
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
		//c键
		else if(e.getKeyCode()==67&&v.getOd().ds.getCnValue().equals("无前置单据应收贷向"))
		{
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
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
						appMain.fv.setText("序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(生产数量)改成0");
						return;		
						
					}
				}
			}
			int si=v.getJt().getSelectedRow();
			v.getOd().copyRow(v.getJt().getSelectedRow()+1, obj);
			v.getJt().setRowSelectionInterval(si+1, si+1);
		}
		//下方向键
		else if(e.getKeyCode()==40&&(v.getJt().getSelectedRow()==v.getOd().getRowCount()-1)&&v.getOd().ds.getCnValue().equals("无前置单据应收贷向")&&e.getSource()==v.getJt())
		{  
			int i=v.getJt().getSelectedRow();
			obj=new Object[v.getOd().getColumnCount()];	
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
			v.getJt().setColumnSelectionInterval(7,7);
			v.getJt().setRowSelectionInterval(i+1, i+1);
			v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()<v.getOd().getRowCount()-1&&e.getSource()==v.getJt()&&v.getOd().ds.getCnValue().equals("无前置单据应收贷向")&&e.getSource()==v.getJt())
		{  
			int i=v.getJt().getSelectedRow();
			v.getJt().setColumnSelectionInterval(7,7);
			v.getJt().setRowSelectionInterval(i, i);
			v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else
		{
			
		}
	
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
        for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
        {
        	if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
    		{
    			continue;
    		}
        	if(lastRow>-1&&lastRow<v.getOd().getRowCount()&&v.getOd().getValuethrheader(lastRow, "序号").toString().equals(v.getDsv().getOd().getValuethrheader(i, "行号").toString()))
        	{
        	   v.getDsv().getOd().setValuethrheader(v.getOd().getValuethrheader(lastRow, "仓库代码"), i, "仓库");      
        	}
        }
        //JOptionPane.showMessageDialog(null,v.getOd().getValueAt(lastRow, mColIndex));	
        //deal with event of endedit,such as itemcode endedit,price enedit and so on;
        //whcih will fired when the focus left the cell	     
        if(lastRow<v.getOd().getRowCount()&&lastRow>=0&& mColIndex<v.getOd().getColumnCount()&&mColIndex>=0&&v.getOd().getValueAt(lastRow, mColIndex)!=""&&v.getOd().getValueAt(lastRow, mColIndex)!=null)
        {        
          v.getOd().valueChanged(lastRow, mColIndex,cc,((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString());
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
		 //如果是序列号行的话不允许编辑
		//String ic=v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "物料代码")==null?"":v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "物料代码").toString();		
		//SNL snl=new SNL();				    
	    //v.getOd().setrowStatus(snl.ifSNrow("INSN",ic));		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==v.getCom_type()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getJta_SN().setText("");
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
			{
			
				v.getOd().setDocLineStatus(docLineStatus.orin);
			}
			else if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2"))
			{
			
				 v.getOd().setDocLineStatus(docLineStatus.corin);
			}
			else
			{

		       v.getOd().setDocLineStatus(docLineStatus.add);			
			}
			  v.getOd().setGridStatus(docLineStatus.add);
		}
		else if(e.getSource()==v.getCom_sales()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getJta_SN().setText("");
	    //with the increase of the data，this respond speed is a little slow。I think update the field of order which will record the draft quantity after creating draft will be a solution。
		// this operation should be nonsynchronous,which means that is only one person can do this at the same time,
		//because when deleting or creating doc,especially on creating doc,that will create 2 and more  docs,which will beyond the quantity of oeder.
		//but I don't do this in order not to slow speed
	    //another solution is validating before save
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");	
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
	
				String cgt=((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString();
			}
			catch(ClassCastException e1){
				JOptionPane.showMessageDialog(null,"请选择销售员");
				return;
			}	
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))
			{				
				 v.getOd().setDocLineStatus(docLineStatus.add);
				 hql="select 0,a.u_snid,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
			 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
	                "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),0,a.U_Gjjg,"+
	                " quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),0,a.price," +
	                "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg," +
	                "lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0)))," +
	                "U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0))),"+
	                " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Inv1 a "+
			 		"left join Oinv b on b.docentry=a.docentry and  b.canceled='N' "+
	                "left join Rin1 c on c.u_baseEntry=a.docEntry and c.u_baseline=a.linenum "+
			 		"left join Oslp d on b.slpcode=d.slpcode "+
			 		"where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"' and "+
			 		"  a.LineStatus='O'  and a.U_Sfhh='N'  and convert(nvarchar(10),b.docdate,23)='"+f.format((Date)v.getTxt_date().getValue())+"' "+
			 	    " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,"+
			 		"a.itemCode,a.dscription,a.U_Ymd,a.U_Mtmd, a.UnitMsr,a.U_NumPerUnit,"+
	                "  a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,a.U_Gjjg,a.price,a.lineTotal,"+
	                "  a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.u_snid,a.Freetxt "+
	                " having (a.quantity-sum(isnull(c.quantity,0)))>0";
					
					 v.getOd().updatetable(hql,0);	
			}
			else if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
			{
				v.getDsv().getOd().setGridStatus(docLineStatus.add);
				v.getJta_SN().setText("");
				v.getOd().setDocLineStatus(docLineStatus.orin);
			}
			else{
				v.getDsv().getOd().setGridStatus(docLineStatus.add);
				v.getJta_SN().setText("");
				v.getOd().setDocLineStatus(docLineStatus.corin);
				hql="select 0,a.u_snid,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
			 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
	                "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),0,a.U_Gjjg,"+
	                " quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),0,a.price," +
	                "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg," +
	                "lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0)))," +
	                "U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0))),"+
	                " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Inv1 a "+
			 		"left join Oinv b on b.docentry=a.docentry  and b.canceled='N' "+
	                "left join Rin1 c on c.u_baseEntry=a.docEntry and c.u_baseline=a.linenum "+
			 		"left join Oslp d on b.slpcode=d.slpcode "+
			 		"where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"'  "+
			 		" and convert(nvarchar(10),b.docdate,23)='"+f.format((Date)v.getTxt_date().getValue())+"' and a.LineStatus='O'  and a.U_Sfhh='Y' "+
	                " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,"+
			 		"a.itemCode,a.dscription,a.U_Ymd,a.U_Mtmd, a.UnitMsr,a.U_NumPerUnit,"+
	                "  a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,a.U_Gjjg,a.price,a.lineTotal,"+
	                "  a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.u_snid,a.Freetxt "+
	                " having (a.quantity-sum(isnull(c.quantity,0)))>0";
							
					v.getOd().updatetable(hql,0);	
			}
		   
		}
		else if(e.getSource()==v.getCom_sales1()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
	    //with the increase of the data，this respond speed is a little slow。I think update the field of order which will record the draft quantity after creating draft will be a solution。
		// this operation should be nonsynchronous,which means that is only one person can do this at the same time,
		//because when deleting or creating doc,especially on creating doc,that will create 2 and more  docs,which will beyond the quantity of oeder.
		//but I don't do this in order not to slow speed
	    //another solution is validating before save
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
			v.getJta_SN().setText("");
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");	
			if(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()==null||((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()=="-1")
			{
				JOptionPane.showMessageDialog(null,"必须首先选择销售员");
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
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("0"))
			{ 
				v.getOd().setDocLineStatus(docLineStatus.add);
				 hql="select 0,a.u_snid,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
			 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
	                "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),0,a.U_Gjjg,"+
	                " quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),0,a.price," +
	                "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg," +
	                "lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0)))," +
	                "U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0))),"+
	                " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Inv1 a "+
			 		"left join Oinv b on b.docentry=a.docentry  and b.canceled='N' "+
	                "left join Rin1 c on c.u_baseEntry=a.docEntry and c.u_baseline=a.linenum "+
			 		"left join Oslp d on b.slpcode=d.slpcode "+
			 		"where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"' and "+
			 		"b.U_SlpCode1='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()+"'  "+
			 		" and convert(nvarchar(10),b.docdate,23)='"+f.format((Date)v.getTxt_date().getValue())+"' and a.LineStatus='O'  and a.U_Sfhh='N'  "+
			 	    " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,"+
			 		"a.itemCode,a.dscription,a.U_Ymd,a.U_Mtmd, a.UnitMsr,a.U_NumPerUnit,"+
	                "  a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,a.U_Gjjg,a.price,a.lineTotal,"+
	                "  a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.u_snid,a.Freetxt "+
	                " having (a.quantity-sum(isnull(c.quantity,0)))>0";
					
					 v.getOd().updatetable(hql,0);	
			}
			else if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
			{
				v.getDsv().getOd().setGridStatus(docLineStatus.add);
				v.getJta_SN().setText("");
				v.getOd().setDocLineStatus(docLineStatus.orin);
			}
			else{
				v.getDsv().getOd().setGridStatus(docLineStatus.add);
				v.getJta_SN().setText("");
				 v.getOd().setDocLineStatus(docLineStatus.corin);
				 hql="select 0,a.u_snid,0,0,a.docEntry,lineNum=(a.lineNum+1),b.cardCode,b.cardName,a.itemCode,a.dscription,"+
			 		"a.U_Ymd,a.U_Mtmd, a.UnitMsr,"+
	                "a.U_NumPerUnit,a.whsCode,a.unitmsr2,U_Gs=(isnull(a.U_Gs,0)-sum(isnull(c.U_Gs,0))),0,a.U_Gjjg,"+
	                " quantity=(isnull(a.quantity,0)-sum(isnull(c.quantity,0))),0,a.price," +
	                "U_Zc=(isnull(a.U_Zc,0)-sum(isnull(c.U_Zc,0))),a.U_Mjg," +
	                "lineTotal=(isnull(a.lineTotal,0)-sum(isnull(c.lineTotal,0)))," +
	                "U_Zz=(isnull(a.U_Zz,0)-sum(isnull(c.U_Zz,0))),"+
	                " a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                " a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdate,a.Freetxt from Inv1 a "+
			 		"left join Oinv b on b.docentry=a.docentry  and b.canceled='N' "+
	                "left join Rin1 c on c.u_baseEntry=a.docEntry and c.u_baseline=a.linenum "+
			 		"left join Oslp d on b.slpcode=d.slpcode "+
			 		"where b.slpCode='"+((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue()+"' and "+
			 		"b.[U_SlpCode1]='"+((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()+"' "+
			 		" and convert(nvarchar(10),b.docdate,23)='"+f.format((Date)v.getTxt_date().getValue())+"' and  a.LineStatus='O'  and a.U_Sfhh='Y'  "+
	                " group by a.docEntry,a.lineNum+1,a.baseEntry,a.baseLine+1,b.cardCode,b.cardName,"+
			 		"a.itemCode,a.dscription,a.U_Ymd,a.U_Mtmd, a.UnitMsr,a.U_NumPerUnit,"+
	                "  a.whsCode,a.unitmsr2,a.quantity,a.U_Mjg,a.U_Gjjg,a.price,a.lineTotal,"+
	                "  a.U_Zc,a.U_Zz,a.U_Sfhh,a.U_Tsjg,a.U_Mtdl,a.U_DjNo,a.U_DjNozj,a.U_Dydj,"+
	                "  a.U_Gs,a.U_Jgf,a.U_Ydy,a.U_Ckck,a.U_Czy,a.U_Scwc,a.U_Rinzz,a.U_Dhdatea,a.u_snid,,a.Freetxt "+
	                " having (a.quantity-sum(isnull(c.quantity,0)))>0";
							
					v.getOd().updatetable(hql,0);	
			}
		   
		}
		else if(e.getSource()==dmv.getjButtonadd())
		{
			v.getDsv().getOd().setGridStatus(docLineStatus.add);
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
			
					
		}
		else if(e.getSource()==dmv.getjButtonremend())
		{
		
		}
		else if(e.getSource()==dmv.getjButtonsave())
		{	  
			try{
			     v.getJt().getCellEditor().stopCellEditing();	
			    }
				catch(NullPointerException e0){
					//e0.printStackTrace();
				}		  						
			docf.getDoc().create(v);
		}
		else if(e.getSource()==dmv.getjButtonquery())
		{					
			QueryWindowView<OrinView> qwv=new QueryWindowView<OrinView>(v,docf.getQDoc(),v.getOd1());
			v.getParent().add(qwv);
			qwv.setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			qwv.setBounds(screenSize.width/2-240, 100, 480, 250);
		}	
		else if(e.getSource()==dmv.getjButtonaddrow())
		{
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
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
						appMain.fv.setText("序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(生产数量)改成0");
						return;		
						
					}
				}
			}
			obj=new Object[v.getOd().getColumnCount()];	
			//v.getJt().getSelectedRow();
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
		}	
		else if(e.getSource()==dmv.getjButtondelrow())
		{
			if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("1"))
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
						JOptionPane.showMessageDialog(null, "序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(生产数量)改成0");
						return;		
						
					}
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
				 JOptionPane.showMessageDialog(null, "orinscontroller-actionPerformed-delrow");
			     System.out.println("orinscontroller-actionPerformed-delrow");
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
			docf.getDoc().setValues(v, docf.getDoc().getfirst(),"");		
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
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();			
		}
		else if(e.getSource()==dmv.getjButtondel())
		{
	
		}
		else if(e.getSource()==dmv.getjButtonSN())
		{ 	
		   hql = "select U_enable from [@SMS] where code='CRESN'";
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
				if(v.getOd1().ds.getCnValue().equals("查询"))
				{
					hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,c.cardcode,a.memo,a.workcenter," +
						"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join rin1 b on a.docentry=b.U_djno and a.linenum=b.u_snid " +
				   		"inner join orin c on b.docentry=c.docentry " +
				   		"where c.objtype='14' and b.U_djno='"+v.getTxt_docn().getText().toString()+"' and a.Ifdraft='0' and a.objtype='14' " +
				   		"and a.linenum='"+v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "SN行号")+"'";
				   v.getDsv().getOd().updatetable(hql, 0);
				   v.getDsv().getOd().setDocLineStatus(docLineStatus.query);
				
				}
		   }	
		}	
		else if(e.getSource()==v.getBtn_upqulity())
		{ 	
			hql = "select U_enable from [@SMS] where code='CRESN'";
			ob=appMain.lt.sqlclob(hql,0,1);
			if(ob==null||(ob!=null&&ob.length==0)||(ob!=null&&!ob[0][0].toString().equals("Y")))
			{				
				 return;
			}
			 if(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString().equals("2"))
			 {
				 JOptionPane.showMessageDialog(null, "换货不需要更新包装数量");
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
		else{
			
		}
	}

	public OrinView getV() {
		return v;
	}

	public void setV(OrinView v) {
		this.v = v;
	}

}
