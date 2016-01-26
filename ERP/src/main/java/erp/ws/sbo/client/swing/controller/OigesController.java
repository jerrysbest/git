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
import erp.ws.sbo.client.swing.view.Oige.OigeView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class OigesController implements MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener {
    private OigeView v;
    private DocMenuView dmv=DocMenuView.getdmv();
	private Object[] obj;
	private Object[][] ob;
	private Object[][] ob2;
	@SuppressWarnings("unused")
	private String hql,hql1,yon;
	@SuppressWarnings("unchecked")
	private DaoFactory<OigeView> docf=(DaoFactory<OigeView>)appMain.ctx.getBean("OigeFactory");	
	public OigesController(OigeView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}
	public OigeView getV() {
		return v;
	}
	public void setV(OigeView v) {
		this.v = v;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		hql = "select U_enable from [@SMS] where code='SN'";
		ob=appMain.lt.sqlclob(hql,0,1);
		
		if(e.getKeyCode()==32&&e.getSource()==v.getJt()&&!ob[0][0].toString().equals("Y"))
		{			
			int i=JOptionPane.showConfirmDialog(null, "是否保存");
			if(i==0)
			{
			  if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").toString().equals("")&&
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
		{  
			if(e.getSource()==v.getJt())
			{
				int[] i=v.getJt().getSelectedRows();
				for(int k=0;k<i.length;k++)
				{
					
				 try{		
				    i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
				 }
				 catch(ArrayIndexOutOfBoundsException e0)
				 {
					 System.out.println("oigescontroller-keyreleased-127");
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
		}
		else if(e.getKeyCode()==10)
		{
		   if(e.getSource()==v.getTxt_cppo())
		   {
			hql = "select distinct 0,snlineno=0,a.docNum,a.itemcode,b.itemname,u_Ymd=isnull(a.u_Ymd,'N'),length=isnull(a.u_length,0),b.salunitmsr," +
		    		"uqty=convert(decimal(18,3),(isnull(a.u_length,0)*b.u_mtzl/b.u_mtmd))," +
		    		"qty=convert(decimal(18,3),(isnull(a.u_qty,0)-isnull(a.u_cqty,0))),0,b.invntryuom," +
		    		" pqty=convert(decimal(18,3),(a.plannedQty-a.cmpltQty))," +
		    		"rqty=0.000," +
		    		"0,0,a.warehouse,cware=d.warehouse,a.plannedQty,a.cmpltQty,date=convert(nvarchar(10),a.duedate,23)," +
		    		"c.u_name from owor a " +
		    		"inner join wor1 d on a.docEntry=d.docEntry " +
		    		"inner join wor1 e on a.docEntry=e.docEntry and e.linenum=0 " +
		    		"inner join oitm b on a.itemcode=b.itemcode " +
		    		"left join ousr c on c.userid=a.usersign "+
		    		" where a.itemcode like '%'+'"+v.getTxt_cppo().getText().trim()+"'+'%' " +
		    		" and a.PlannedQty-a.CmpltQty>0 and a.u_qty>a.u_cqty " +
		    		"and a.Status='R' AND a.Type='D' ";
		     hql1="select u_enable from [@sms] where code='CKCZY'";
			 if(appMain.lt.sqlclob(hql1, 0, 1)[0][0].toString().equals("Y"))
			 {
		    	 hql+=" and e.warehouse in " +
					 "(select U_Dsck from dbo.[@CZYCK] where " +
					 "U_Usid=(select distinct branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='F')";					
			 }
		    v.getOd2().updatetable(hql, 0);		
		   }
		   else if(e.getSource()==v.getJta_SN())
		   {
			   
		   }
		   else{}
		}
		if(e.getKeyCode()==127&&e.getSource()==v.getJt())
		{ 
			
		  
		}
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
		if(e.getSource()==v.getBt_cppo())
		{
			int[] i=v.getJt2().getSelectedRows();
			for(int k=0;k<i.length;k++)
			{				
			 try{		
			    i[k]=Integer.valueOf(v.getJt2().convertRowIndexToModel(v.getJt2().getSelectedRows()[k]));
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 System.out.println("oigescontroller-actionPerformed-getbt_cppo");				 
			 }
			
			}
		    ob=v.getOd().getDataSet();
		    ob2=v.getOd2().getDataSet();
		    boolean tf=false;
		    int inx=v.getOd2().getcolumnindex("生产订单号");
		    for(int k=0;k<i.length;k++)
		    {  
		    	tf=false;
		    	for(int j=0;j<ob.length;j++)
		    	{
		    		if(ob[j][inx]!=null&&ob2[i[k]][inx]!=null&&ob[j][inx].toString().equals(ob2[i[k]][inx].toString()))
		    		{
		    			tf=true;
		    			break;		    	   
		    		}
		    	}
		    	if(tf)
		    	continue;
		    	for(int j=0;j<ob.length;j++)
		    	{
		    		if(ob[j][inx]==null||ob[j][inx].toString().equals(""))
			    	{   
		    			for(int l=0;l<v.getOd2().getColumnCount();l++)
				    	{			    		
				    		ob[j][l]=ob2[i[k]][l];
				    	}
		    			break;
			    	}
		    	}
		    }
		    v.getOd().setDataSet(ob);
		}
	   else if(e.getSource()==dmv.getjButtonadd())
		{
		   // something about doctitle
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.oige);
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
			QueryWindowView<OigeView> qwv=new QueryWindowView<OigeView>(v,docf.getQDoc(),v.getOd1(),hql,hql1);
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
			int[] i=v.getJt().getSelectedRows();
			for(int k=0;k<i.length;k++)
			{				
			 try{		
			     i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 System.out.println("oigescontroller-actionPerformed-delrow");
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
			hql = "select U_enable from [@SMS] where code='SN'";
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
					hql="select 0,a.objtype,a.docentry,a.linenum,a.snlinenum,a.sn,a.direction,a.ifpasn,a.pasn," +
							"a.warehouse,cardcode='',a.memo,a.workcenter," +
							"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join ige1 b on a.docentry=b.docentry and a.linenum=b.linenum " +
				   		"inner join oige c on b.docentry=c.docentry " +
				   		"where c.objtype='60' and c.docNum='"+v.getTxt_docn().getText().toString().trim()+"' " +
				   		"and b.linenum='"+ v.getOd().getValuethrheader(v.getJt().getSelectedRow(), "序号").toString()+"'";
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
