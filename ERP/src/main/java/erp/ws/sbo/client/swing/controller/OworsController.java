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

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Owor.OworView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.utils.MdbHibernateUtils;

public class OworsController implements FocusListener,MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener {
	private OworView v;
	private String hql,hql1,hql2,hql3;//query string 
	private DocMenuView dmv=DocMenuView.getdmv();
	private Object[] obj;
	private String ic;//itemCode	
	@SuppressWarnings("unchecked")
	private DaoFactory<OworView> docf=(DaoFactory<OworView>)appMain.ctx.getBean("OworFactory");	
	public OworsController(OworView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
		  
		}
		else if(e.getKeyCode()==32)
		{			
			if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").equals(""))
			{
				int i=JOptionPane.showConfirmDialog(null, "是否保存");
				if(i==0)
				{										                    					
					docf.getDoc().create(v);
				}
			}
		}
		else
		{}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("物料代码")||v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("仓库"))
		{
	       v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	  if(e.getSource()==v.getCom_form()&&v.getOd1().ds.getCnValue().equals("增加"))
	  {
		  if(((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString().equals("Y")&&v.getTxt_unit().getText().equals("盘"))
		  {
			v.getTxt_length().setEditable(true);
			v.getTxt_Qty().setEditable(true);
			v.getTxt_planQty().setEditable(false);			
		  }
		  else if(((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString().equals("N"))
		  {
			  v.getTxt_length().setEditable(false);
			  v.getTxt_Qty().setEditable(true);
			  v.getTxt_planQty().setEditable(true);	
		  }
		  else if(!v.getTxt_unit().getText().equals("盘"))
		  {
			  v.getTxt_length().setEditable(false);
			  v.getTxt_Qty().setEditable(true);
			  v.getTxt_planQty().setEditable(true);	
		  }
		  else{}
	  }
	   else if(e.getSource()==dmv.getjButtonadd())
	   {
	   // something about doctitle
				
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.oworadd);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtonaddplus())
		{
		   // something about doctitle
			
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
			/*if(((JTextField)v.getCom_status().getEditor().getEditorComponent()).getText().equals("关闭"))
			{
				JOptionPane.showMessageDialog(null,"关闭的生产订单不能修改 ");
				return;
			}
			v.getOd1().setDs(docTitleStatus.remend);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.remend);
			
			dmv.setadd();*/
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
			hql = "select cast(p.itemcode as nvarchar(20))+','+p.itemName from oitm as p " +
				    " where cast(p.itemname as nvarchar(20)) like :str1 or p.u_name like :str2";
	   	    hql1="select p.itemcode from oitm as p " +
				" where cast(p.itemname as nvarchar(20))=:str1";	
	   	    hql2 = "select cast(p.whscode as nvarchar(10))+','+p.whsName from owhs as p " +
	 			" where cast(p.whscode as nvarchar(10)) like :str1 or p.whsname like :str2";
 	   	    hql3="select p.whscode from owhs as p " +
 				" where cast(p.whscode as nvarchar(10))=:str1";	
			QueryWindowView<OworView> qwv=new QueryWindowView<OworView>(v,docf.getQDoc(),v.getOd1(),hql,hql1,hql2,hql3);
		
			v.getParent().add(qwv);
			qwv.setVisible(true);
			qwv.getlab_Rsaleperson().setText("物料编码");
			qwv.getlab_Rcard().setText("仓库");
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
			
		}	
		else if(e.getSource()==dmv.getjButtonfirst())
		{			
			docf.getDoc().setValues(v, docf.getDoc().getfirst(),"草稿");		
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprev())
		{  
			if(v.getTxt_docnid().getText()!=null&&!v.getTxt_docnid().getText().equals(""))
			{			
				 try{
						docf.getDoc().setValues(v,docf.getDoc().getprev(Integer.valueOf(v.getTxt_docnid().getText())),"草稿");			
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
			if(v.getTxt_docnid().getText()!=null&&!v.getTxt_docnid().getText().equals(""))
			{			
				 try{
					 docf.getDoc().setValues(v,docf.getDoc().getnext(Integer.valueOf(v.getTxt_docnid().getText())),"草稿");			
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
			docf.getDoc().setValues(v, docf.getDoc().getlast(),"草稿");		
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);		
		    v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprint())
		{
				
			//docf.setAbsDoc().print(v);
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
			 JOptionPane.showMessageDialog(null,"没有单号，无法查询 ");
		     return;
		 }
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub						
		int[] col={v.getOd().getcolumnindex("计划数量"),v.getOd().getcolumnindex("发出数量")};
		double[] s=v.getOd().sum(col);
		if(s!=null)
		{			
			v.getTxt_qsum().setText(String.valueOf(s[0]));
			v.getTxt_asum().setText(String.valueOf(s[1]));			
		}

		 int lastRow = e.getLastRow();
         int mColIndex = e.getColumn();
        //JOptionPane.showMessageDialog(null,v.getOd().getValueAt(lastRow, mColIndex));	
        //deal with event of endedit,such as itemcode endedit,price enedit and so on;
        //whcih will fired when the focus left the cell	 
         if(v.getOd1().ds.getCnValue().equals("增加")&&
    		(Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString())==2||
    		Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString())==0))
           {
			    if(lastRow<v.getOd().getRowCount()&&lastRow>=0&& mColIndex<v.getOd().getColumnCount()&&mColIndex>=0&&v.getOd().getValueAt(lastRow, mColIndex)!=""&&v.getOd().getValueAt(lastRow, mColIndex)!=null)
			    {
			      ic=((JTextField)v.getCom_itemcode().getEditor().getEditorComponent()).getText();
			      v.getOd().valueChanged1(lastRow, mColIndex,ic,Double.valueOf(v.getTxt_planQty().getText().toString()));
			      //获得焦点
			      if(mColIndex<v.getOd().getColumnCount()-1){
			      v.getJt().setColumnSelectionInterval(mColIndex+1,mColIndex+1);
			      }
			      else{v.getJt().setColumnSelectionInterval(mColIndex,mColIndex);}
			      v.getJt().setRowSelectionInterval(lastRow,lastRow);          
			    }   
           }
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JTable)(e.getSource())).getName()!="data-browser")
		{					
			int[] col={v.getOd().getcolumnindex("计划数量"),v.getOd().getcolumnindex("发出数量")};
			double[] s=v.getOd().sum(col);
			if(s!=null)
			{			
				v.getTxt_qsum().setText(String.valueOf(s[0]));
				v.getTxt_asum().setText(String.valueOf(s[1]));			
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

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
	    boolean isNum = v.getTxt_Qty().getText().matches("[0-9]+");   
        if(!isNum)
        {
     	   v.getTxt_Qty().setText("1");  
        }
        if(v.getTxt_Qty().getText().toString().equals("0"))
        {
        	 v.getTxt_Qty().setText("1");  
        }
        if(e.getSource()==v.getEditor())
		{
		  String parm=((JTextField)e.getSource()).getText();
	      if(parm.length()==0)
	      {
	        return;
	      }
		  Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{	     
	    	try{		           	      
	        hql="select p.itemName,p.dfltwh,b.whsname,md=p.U_MTMd/p.U_MTZL,p.salunitmsr,p.invntryuom," +
        		"Qty='1', planQty='1' from Oitm p " +
        		"inner join owhs b on p.dfltwh=b.whscode " +    	        
				" where p.itemCode='"+parm+"'";
	    	Query q=session.createSQLQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings({"unchecked"})
			List<Object[]> list=q.list();
		    v.getTxt_itemname().setText(list.get(0)[0].toString());
		    ComboBoxItem  Cbi=new ComboBoxItem(list.get(0)[1].toString(),list.get(0)[2].toString());		
		    v.getCom_whs().setEditable(true);
		    v.getCom_whs().setSelectedItem(Cbi);
		    v.getCom_whs().setEditable(false);
		    v.getTxt_length().setText(new BigDecimal(list.get(0)[3].toString()).setScale(3,BigDecimal.ROUND_HALF_UP).toString());
		    v.getTxt_unit().setText(list.get(0)[4].toString());
		    v.getTxt_ounit().setText(list.get(0)[5].toString());
		    v.getTxt_Qty().setText("1");
		    v.getTxt_planQty().setText("1");
		    if(list.get(0)[4].toString().equals("盘")&&((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString().equals("Y"))
		    {
		    	v.getTxt_planQty().setEditable(false);
		    	v.getTxt_length().setEditable(true);
		    }
		    else{
		    	v.getTxt_planQty().setEditable(true);
		    	v.getTxt_length().setEditable(false);
		    }
		  
		    t.commit();	  
	        }catch (HibernateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				  t.rollback();
			}finally {
		       	  MdbHibernateUtils.closeSession(session);
		         } 
		      }
	      if(v.getOd1().ds.getCnValue().equals("增加")||v.getOd1().ds.getCnValue().equals("修改"))
		  {
			 hql="select 0,a.code,c.itemname,a.quantity," +
			 	"qty=a.quantity*convert(decimal(18,3),'"+v.getTxt_planQty().getText()+"')," +
		 		"'',issuetype=case when c.issuemthd='B' then '倒冲' else '领料' end," +
		 		"a.warehouse," +
		 		"c.invntryuom,c.onhand "+
		       "from itt1 a " +
		       "inner join oitt b on a.father=b.code " +
		 		"left join oitm c on c.itemcode=a.code "+
		 		"where b.code='"+v.getCom_itemcode().getSelectedItem().toString().split(",")[0]+"' " ;		 	
			
			    v.getOd().updatetable(hql,0);	
			}
		}
        else if(e.getSource()==v.getTxt_length()&&((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString().equals("Y")&&v.getTxt_unit().getText().equals("盘"))
		{
        	String parm=v.getEditor().getText();
	        if(parm.length()==0)
	        {
	        	return;
	        }
		  Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{	     
	    	try{		           	      
	        hql="select p.itemName,p.dfltwh,b.whsname,md=p.U_MTMd/p.U_MTZL,p.salunitmsr,p.invntryuom," +
        		"Qty='1', planQty='1' from Oitm p " +
        		"inner join owhs b on p.dfltwh=b.whscode " +    	        
				" where p.itemCode='"+parm+"'";
	    	Query q=session.createSQLQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings({"unchecked"})
			List<Object[]> list=q.list();
		    v.getTxt_planQty().setText(String.valueOf(Double.valueOf(v.getTxt_Qty().getText())*Double.valueOf(v.getTxt_length().getText())/Double.valueOf(list.get(0)[3].toString())));
		  
		    t.commit();	  
	        }catch (HibernateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				  t.rollback();
			}finally {
		       	  MdbHibernateUtils.closeSession(session);
		         } 
		      }
	      if(v.getOd1().ds.getCnValue().equals("增加")||v.getOd1().ds.getCnValue().equals("修改"))
		  {
			 hql="select 0,a.code,c.itemname,a.quantity," +
			 	"qty=a.quantity*convert(decimal(18,3),'"+v.getTxt_planQty().getText()+"')," +
		 		"'',issuetype=case when c.issuemthd='B' then '倒冲' else '领料' end," +
		 		"a.warehouse," +
		 		"c.invntryuom,c.onhand "+
		       "from itt1 a " +
		       "inner join oitt b on a.father=b.code " +
		 		"left join oitm c on c.itemcode=a.code "+
		 		"where b.code='"+v.getCom_itemcode().getSelectedItem().toString().split(",")[0]+"' " ;		 	
			
			    v.getOd().updatetable(hql,0);	
			}
		}
        else if(e.getSource()==v.getTxt_Qty()&&((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString().equals("Y")&&v.getTxt_unit().getText().equals("盘"))
      		{
                String parm=v.getEditor().getText();
      	        if(parm.length()==0)
      	        {
      	        	return;
      	        }
      		  Session session = MdbHibernateUtils.getSession();
      	      Transaction t = session.beginTransaction();{	     
      	    	try{		           	      
      	        hql="select p.itemName,p.dfltwh,b.whsname,md=p.U_MTMd/p.U_MTZL,p.salunitmsr,p.invntryuom," +
              		"Qty='1', planQty='1' from Oitm p " +
              		"inner join owhs b on p.dfltwh=b.whscode " +    	        
      				" where p.itemCode='"+parm+"'";
      	    	Query q=session.createSQLQuery(hql);	  
      			q.setFirstResult(0);
      			q.setMaxResults(1);
      		    @SuppressWarnings({"unchecked"})
      			List<Object[]> list=q.list();
      		    v.getTxt_planQty().setText(String.valueOf(Double.valueOf(v.getTxt_Qty().getText())*Double.valueOf(v.getTxt_length().getText())/Double.valueOf(list.get(0)[3].toString())));
      		  
      		    t.commit();	  
      	        }catch (HibernateException e1) {
      				// TODO Auto-generated catch block
      				e1.printStackTrace();
      				  t.rollback();
      			}finally {
      		       	  MdbHibernateUtils.closeSession(session);
      		         } 
      		      }
      	      if(v.getOd1().ds.getCnValue().equals("增加")||v.getOd1().ds.getCnValue().equals("修改"))
      		  {
      			 hql="select 0,a.code,c.itemname,a.quantity," +
      			 	"qty=a.quantity*convert(decimal(18,3),'"+v.getTxt_planQty().getText()+"')," +
      		 		"'',issuetype=case when c.issuemthd='B' then '倒冲' else '领料' end," +
      		 		"a.warehouse," +
      		 		"c.invntryuom,c.onhand "+
      		       "from itt1 a " +
      		       "inner join oitt b on a.father=b.code " +
      		 		"left join oitm c on c.itemcode=a.code "+
      		 		"where b.code='"+v.getCom_itemcode().getSelectedItem().toString().split(",")[0]+"' " ;		 	
      			
      			    v.getOd().updatetable(hql,0);	
      			}
      		}
        else if(e.getSource()!=v.getTxt_planQty()&&e.getSource()!=v.getTxt_Qty()&&e.getSource()!=v.getTxt_length()&&((ComboBoxItem)v.getCom_form().getSelectedItem()).getValue().toString().equals("Y")&&v.getTxt_unit().getText().equals("盘"))
		{
        	String parm=v.getEditor().getText();
	        if(parm.length()==0)
	        {
	        	return;
	        }
		  Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{	     
	    	try{		           	      
	        hql="select p.itemName,p.dfltwh,b.whsname,md=p.U_MTMd/p.U_MTZL,p.salunitmsr,p.invntryuom," +
        		"Qty='1', planQty='1' from Oitm p " +
        		"inner join owhs b on p.dfltwh=b.whscode " +    	        
				" where p.itemCode='"+parm+"'";
	    	Query q=session.createSQLQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings({"unchecked"})
			List<Object[]> list=q.list();
		    v.getTxt_itemname().setText(list.get(0)[0].toString());
		    ComboBoxItem  Cbi=new ComboBoxItem(list.get(0)[1].toString(),list.get(0)[2].toString());		
		    v.getCom_whs().setEditable(true);
		    v.getCom_whs().setSelectedItem(Cbi);
		    v.getCom_whs().setEditable(false);
		   // v.getTxt_length().setText(new BigDecimal(list.get(0)[3].toString()).setScale(3,BigDecimal.ROUND_HALF_UP).toString());
		    v.getTxt_unit().setText(list.get(0)[4].toString());
		    v.getTxt_ounit().setText(list.get(0)[5].toString());
		    //v.getTxt_Qty().setText("1");
		    v.getTxt_planQty().setText(String.valueOf(Double.valueOf(v.getTxt_Qty().getText())*Double.valueOf(v.getTxt_length().getText())/Double.valueOf(list.get(0)[3].toString())));
		  
		    t.commit();	  
	        }catch (HibernateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				  t.rollback();
			}finally {
		       	  MdbHibernateUtils.closeSession(session);
		         } 
		      }
	      if(v.getOd1().ds.getCnValue().equals("增加")||v.getOd1().ds.getCnValue().equals("修改"))
		  {
			 hql="select 0,a.code,c.itemname,a.quantity," +
			 	"qty=a.quantity*convert(decimal(18,3),'"+v.getTxt_planQty().getText()+"')," +
		 		"'',issuetype=case when c.issuemthd='B' then '倒冲' else '领料' end," +
		 		"a.warehouse," +
		 		"c.invntryuom,c.onhand "+
		       "from itt1 a " +
		       "inner join oitt b on a.father=b.code " +
		 		"left join oitm c on c.itemcode=a.code "+
		 		"where b.code='"+v.getCom_itemcode().getSelectedItem().toString().split(",")[0]+"' " ;		 	
			
			    v.getOd().updatetable(hql,0);	
			}
		}		
		else if(e.getSource()==v.getTxt_planQty())
		{
			if(v.getOd1().ds.getCnValue().equals("增加")||v.getOd1().ds.getCnValue().equals("修改"))
			  {
				
				 hql="select 0,a.code,c.itemname,a.quantity," +
				 	"qty=a.quantity*convert(decimal(18,3),'"+v.getTxt_planQty().getText()+"')," +
			 		"'',issuetype=case when c.issuemthd='B' then '倒冲' else '领料' end,a.warehouse," +
			 		"c.invntryuom,c.onhand "+
			       "from itt1 a " +
			       "inner join oitt b on a.father=b.code "+
			 		"left join oitm c on c.itemcode=a.code "+
			 		"where b.code='"+v.getCom_itemcode().getSelectedItem().toString()+"' " ;		 	
				
				    v.getOd().updatetable(hql,0);	
				}
		}
		else{}
	}

	public OworView getV() {
		return v;
	}

	public void setV(OworView v) {
		this.v = v;
	}

}
