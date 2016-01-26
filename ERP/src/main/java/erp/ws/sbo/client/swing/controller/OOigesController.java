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
import java.util.ArrayList;
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
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.OOige.OOigeView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
@SuppressWarnings("unused")
public class OOigesController implements MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener {
    private OOigeView v;
    private DocMenuView dmv=DocMenuView.getdmv();
	private Object[] obj;
	private String[] sns;
	private static List<String> lsn=new  ArrayList<String>();
	private Object[][] ob,ob1;
	private List<Integer> csn=new ArrayList<Integer>();
    private String wh,rsn,rsn1;
	private String hql,hql1,yon;
	@SuppressWarnings("unchecked")
	private DaoFactory<OOigeView> docf=(DaoFactory<OOigeView>)appMain.ctx.getBean("OOigeFactory");	
	public OOigesController(OOigeView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}
	public OOigeView getV() {
		return v;
	}
	public void setV(OOigeView v) {
		this.v = v;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		appMain.fv.setText(String.valueOf(e.getKeyCode()));
		if(e.getKeyCode()==10)
		{
			appMain.fv.setText(String.valueOf(e.getKeyCode()));
			if(e.getSource()==v.getJta_SN())
			{
				appMain.fv.setText("1");
				hql = "select U_enable from [@SMS] where code='OUTSN'";
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
				if(docf.getAdnSN().snverification(v))
				{
					String s =new String(v.getJta_SN().getText());   
				    String[] a = s.split(","); 
				    for(int i=0;i<a.length;i++)
				    {
				      docf.getAdnSN().add(v, a[i], false, "60", "O", false,0);
				    }
				}
			    
			}
		
		}
	
	
		if(e.getKeyCode()==32&&e.getSource()==v.getJt())
		{			
			  if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").toString().equals("")&&
					v.getOd().getValuethrheader(0, "实际库存数量")!=null&&!v.getOd().getValuethrheader(0, "实际库存数量").toString().equals(""))
			{
				int i=JOptionPane.showConfirmDialog(null, "是否保存");
				if(i==0)
				{										                    					
					  try{
						  v.getJt().getCellEditor().stopCellEditing();	
					    }
						catch(NullPointerException e0){
							//e0.printStackTrace();
							//return;
						}
					  for(int j=0;j<v.getOd().getRowCount();j++)
					  {
						  if(v.getOd().getValuethrheader(j, "仓库")!=null&&v.getOd().getValuethrheader(j, "仓库").toString().contains(","))
						  {
							  v.getOd().setValuethrheader(v.getOd().getValuethrheader(j, "仓库").toString().split(",")[0], j, "仓库");
						  }
						  else{
							  continue;
						  }
					  }
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
		if(e.getKeyCode()==127&&e.getSource()==v.getJt())
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
					JOptionPane.showMessageDialog(null, "序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(实际收货个数)改成0");
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
				 JOptionPane.showMessageDialog(null, "ooigescontroller-keyreleased-127");
			     System.out.println("ooigescontroller-keyreleased-127");
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
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()<v.getOd().getRowCount()-1&&e.getSource()==v.getJt())
		{  
			int i=v.getJt().getSelectedRow();
			v.getJt().setColumnSelectionInterval(v.getOd().getcolumnindex("物料代码"),v.getOd().getcolumnindex("物料代码"));
			v.getJt().setRowSelectionInterval(i, i);
			v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()==v.getOd().getRowCount()-1&&e.getSource()==v.getJt())
		{  
			int i=v.getJt().getSelectedRow();
			obj=new Object[v.getOd().getColumnCount()];	
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
			v.getJt().setColumnSelectionInterval(v.getOd().getcolumnindex("物料代码"),v.getOd().getcolumnindex("物料代码"));
			v.getJt().setRowSelectionInterval(i+1, i+1);
			 v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else{}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("实际收货个数"))
		{
	       v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==dmv.getjButtonadd())
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
		else if(e.getSource()==dmv.getjButtoncpsource())
		{
			
		}
		else if(e.getSource()==dmv.getjButtonctarget())
		{
			
		}
		else if(e.getSource()==dmv.getjButtonremend())
		{
			v.getOd1().setDs(docTitleStatus.remend);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.remend);
			
			dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtonsave())
		{	if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").toString().equals("")&&
			v.getOd().getValuethrheader(0, "实际库存数量")!=null&&!v.getOd().getValuethrheader(0, "实际库存数量").toString().equals("")){		  				
			  try{
				  v.getJt().getCellEditor().stopCellEditing();	
			    }
				catch(NullPointerException e0){
					//e0.printStackTrace();
				}
			  for(int j=0;j<v.getOd().getRowCount();j++)
			  {
				  if(v.getOd().getValuethrheader(j, "仓库")!=null&&v.getOd().getValuethrheader(j, "仓库").toString().contains(","))
				  {
					  v.getOd().setValuethrheader(v.getOd().getValuethrheader(j, "仓库").toString().split(",")[0], j, "仓库");
				  }
				  else{
					  continue;
				  }
			  }
			docf.getDoc().create(v);
			}
		    else{JOptionPane.showMessageDialog(null, "单身没有数据，请录入数据");}
		}
		else if(e.getSource()==dmv.getjButtonquery())
		{					
			hql = "select cast(p.user_code as nvarchar(10))+','+p.U_Name from ousr as p " +
				    " where cast(p.user_code as nvarchar(10)) like :str1 or p.u_name like :str2";
	   	    hql1="select p.user_code from ousr as p " +
				" where cast(p.user_code as nvarchar(10))=:str1";	
			QueryWindowView<OOigeView> qwv=new QueryWindowView<OOigeView>(v,docf.getQDoc(),v.getOd1(),hql,hql1);
			v.getParent().add(qwv);
			qwv.setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			qwv.setBounds(screenSize.width/2-240, 100, 480, 250);
		}	
		else if(e.getSource()==dmv.getjButtonaddrow())
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
					JOptionPane.showMessageDialog(null, "序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(实际收货个数)改成0");
					return;		
					
				}
			}
			obj=new Object[v.getOd().getColumnCount()];	
			//v.getJt().getSelectedRow();
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
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
					JOptionPane.showMessageDialog(null, "序列号管理不允许增删行操作，如果需要增删行，您必须把序列号开窗中的数据全部删除，删行的替代操作也可以是把包装数量(实际收货个数)改成0");
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
				 JOptionPane.showMessageDialog(null, "ooigescontroller-actionPerformed-delrow");
			     System.out.println("ooigescontroller-actionPerformed-delrow");
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
		else if(e.getSource()==dmv.getjButtonSN())
		{ 		
			hql = "select U_enable from [@SMS] where code='OUTSN'";
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
					hql="select 0,a.ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
							"a.warehouse,cardcode='',a.memo,a.workcenter," +
							"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join ige1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
				   		"inner join oige c on b.docentry=c.docentry " +
				   		"where c.objtype='60' and c.docnum='"+v.getTxt_docn().getText().toString().trim()+"' and a.objtype='60' " +
				   		"and a.linenum='"+v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "SN行号")+"'";
				   v.getDsv().getOd().updatetable(hql, 0);
				}
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
			 JOptionPane.showMessageDialog(null,"单据类型或者单号格式不合法或者为空，无法查询 ");
			 return;
		 }
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int[] col={v.getOd().getcolumnindex("实际收货个数"),v.getOd().getcolumnindex("标准库存数量"),v.getOd().getcolumnindex("实际库存数量"),v.getOd().getcolumnindex("计划库存数量"),v.getOd().getcolumnindex("完成库存数量")};	
		double[] s=v.getOd().sum(col);
		if(s!=null)
		{			
			v.getTxt_msum().setText(String.valueOf(s[0]));
			v.getTxt_wsum().setText(String.valueOf(s[1]));			
			v.getTxt_rsum().setText(String.valueOf(s[2]));
			v.getTxt_psum().setText(String.valueOf(s[3]));	
			v.getTxt_csum().setText(String.valueOf(s[4]));	
		}
        int lastRow = e.getLastRow();
        int mColIndex = e.getColumn();
        //JOptionPane.showMessageDialog(null,v.getOd().getValueAt(lastRow, mColIndex));	
        //deal with event of endedit,such as itemcode endedit,price enedit and so on;
        //whcih will fired when the focus left the cell	     
        if(lastRow<v.getOd().getRowCount()&&lastRow>=0&& mColIndex<v.getOd().getColumnCount()&&mColIndex>=0&&v.getOd().getValueAt(lastRow, mColIndex)!=""&&v.getOd().getValueAt(lastRow, mColIndex)!=null)
        {         
          v.getOd().valueChanged(lastRow, mColIndex,"","");
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
			
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JTable)(e.getSource())).getName()!="data-browser")
		{					
			int[] col={v.getOd().getcolumnindex("实际收货个数"),v.getOd().getcolumnindex("标准库存数量"),v.getOd().getcolumnindex("实际库存数量"),v.getOd().getcolumnindex("计划库存数量"),v.getOd().getcolumnindex("完成库存数量")};	
			double[] s=v.getOd().sum(col);
			if(s!=null)
			{			
				v.getTxt_msum().setText(String.valueOf(s[0]));
				v.getTxt_wsum().setText(String.valueOf(s[1]));			
				v.getTxt_rsum().setText(String.valueOf(s[2]));
				v.getTxt_psum().setText(String.valueOf(s[3]));	
				v.getTxt_csum().setText(String.valueOf(s[4]));	
			}
		
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
