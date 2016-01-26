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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
import erp.ws.sbo.client.swing.view.Ordr.OrdrView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.utils.MdbHibernateUtils;



public class OrdrsController implements FocusListener,MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener{
	private OrdrView v;
	private String hql;//query string 
	private DocMenuView dmv=DocMenuView.getdmv();
	private Object[] obj;
	private Object[][] ob;
	private String cc;//cardCode
	@SuppressWarnings("unchecked")
	private DaoFactory<OrdrView> docf=(DaoFactory<OrdrView>)appMain.ctx.getBean("OrdrFactory");				
	public OrdrsController(OrdrView v)
	{		 
       this.setV(v);
	}
	
	public OrdrView getV() {
		return v;
	}
	public void setV(OrdrView v) {
		this.v = v;
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,((JTextField)e.getSource()).getText()); 
	    if(e.getSource()!=v.getJt())
	    {
		  String parm=((JTextField)e.getSource()).getText();
          if(parm.length()==0)
          {
        	return;
          }
		  Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {	    	  
			@SuppressWarnings("deprecation")
			Connection con = session.connection(); 
			CallableStatement cstmt;
			String result = null;	           	      
	        hql="select p.cardName from Ocrd p " +	    	        
				" where p.cardCode='"+parm+"' and p.cardType='C'";
	    	Query q=session.createQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings("rawtypes")
			List list=q.list();
		    v.getTxt_cusn().setText(list.get(0).toString());
		    hql="select p.cntctCode,p.name from Ocpr p " +	    	        
				" where p.cardCode='"+parm+"'";
  	        q=session.createQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(100);
		    @SuppressWarnings("unchecked")
			List<Object[]> lst=q.list();
		   		   
		    v.getTxt_cuslxr().removeAllItems();
           for(Object[] object : lst){
	        v.getTxt_cuslxr().addItem(new  ComboBoxItem(object[0],object[1].toString()));
           }
           hql="select p.slpcode,q.slpname from Ocrd p inner join oslp q on p.slpcode=q.slpcode  " +	    	        
				" where p.cardCode='"+parm+"' and  p.cardType='C'";
            ob = appMain.lt.sqlclob(hql,0,1); 
	        ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
	        v.getCom_sales().setEnabled(true);
            v.getCom_sales().setEditable(true);
 		    v.getCom_sales().setSelectedItem(Cbi);
 		    v.getCom_sales().setEditable(false);
 		    v.getCom_sales().setEnabled(false);
 		   hql="select p.u_slpcode1,q.slpname from Ocrd p inner join oslp q on p.u_slpcode1=q.slpcode  " +	    	        
 			   " where p.cardCode='"+parm+"' and  p.cardType='C'";
 		   ob = appMain.lt.sqlclob(hql,0,1); 
 		    if(!(ob==null||ob.length==0))
	        {	            
 		        Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
 		        v.getCom_sales1().setEnabled(true);
 	            v.getCom_sales1().setEditable(true);
 	 		    v.getCom_sales1().setSelectedItem(Cbi);
 	 		    v.getCom_sales1().setEditable(false);
 	 		    v.getCom_sales1().setEnabled(false);
	        }
 		    else{
 		    	//Cbi=new ComboBoxItem(-1,"-无销售员工-");
 		    	v.getCom_sales1().setEnabled(true);
  	            v.getCom_sales1().setEditable(true);
  	 		    v.getCom_sales1().setSelectedItem(Cbi);
  	 		    v.getCom_sales1().setEditable(false);
  	 		    v.getCom_sales1().setEnabled(false);
 		    }
 		
	        hql = "select territory from Ocrd where cardCode='"+((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText()+"'"; 
	        q=session.createQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings("rawtypes")
			List lst1=q.list();   
		    if(lst1.get(0)==null)
		    {
		    	JOptionPane.showMessageDialog(null,"客户没有所属地区");	
		    	return;
		    }
	        Integer dzid=(Integer) lst1.get(0);  
	         hql = "{call zdy_cxdq(?,?)}";
	         try {	        	
				cstmt = con.prepareCall(hql);				
				cstmt.setInt(1,dzid);				
				cstmt.registerOutParameter(2, java.sql.Types.NVARCHAR);				
				cstmt.execute();			
				result = cstmt.getString(2);
			    v.getTxt_ter().setText(result);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	        t.commit();	       	      	          
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } 
	        catch (NullPointerException e1) {
	           e1.printStackTrace();	       
	         } 
	       finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
	      }
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
				
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,((JTable)(e.getSource())).getName());
		//鼠标停留不需要点击，只要鼠标坐标到达就会触发，但是反过来鼠标坐标没有到，用键盘是不起作用的
		if(((JTable)(e.getSource())).getName()!="data-browser")
		{					
			int[] col={v.getOd().getcolumnindex("数量"),v.getOd().getcolumnindex("金额"),v.getOd().getcolumnindex("总长"),v.getOd().getcolumnindex("总重")};
			double[] s=v.getOd().sum(col);
			if(s!=null)
			{			
				v.getTxt_qsum().setText(String.valueOf(s[0]));
				v.getTxt_asum().setText(String.valueOf(s[1]));			
				v.getTxt_mssum().setText(String.valueOf(s[2]));
				v.getTxt_gjsum().setText(String.valueOf(s[3]));			
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
		/*JOptionPane.showMessageDialog(null,v.getJt().rowAtPoint(e.getPoint()));
		JOptionPane.showMessageDialog(null,v.getJt().columnAtPoint(e.getPoint()));*/
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub	
		int[] col={v.getOd().getcolumnindex("数量"),v.getOd().getcolumnindex("金额"),v.getOd().getcolumnindex("总长"),v.getOd().getcolumnindex("总重")};
		double[] s=v.getOd().sum(col);
		if(s!=null)
		{		
			v.getTxt_qsum().setText(String.valueOf(s[0]));
			v.getTxt_asum().setText(String.valueOf(s[1]));			
			v.getTxt_mssum().setText(String.valueOf(s[2]));
			v.getTxt_gjsum().setText(String.valueOf(s[3]));
		}
        int lastRow = e.getLastRow();
        int mColIndex = e.getColumn();
        
        //deal with event of endedit,such as itemcode endedit,price enedit and so on;
        //whcih will fired when the focus left the cell	     
        if(lastRow<v.getOd().getRowCount()&&lastRow>=0&& mColIndex<v.getOd().getColumnCount()&&
        mColIndex>=0&&v.getOd().getValueAt(lastRow, mColIndex)!=""&&
        v.getOd().getValueAt(lastRow, mColIndex)!=null&&
        v.getOd1().ds.getCnValue().equals("增加"))
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
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub	
		//JOptionPane.showMessageDialog(null,e.getLastIndex());
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
		    docf.getOrdrAbsDoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(vi, 2).toString()),v.getOd1().getValueAt(vi, 1).toString());		       
		    v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		 catch(NumberFormatException e1){	
			 JOptionPane.showMessageDialog(null,"没有单号，无法查询 "+v.getOd1().getValueAt(vi, 2).toString()+v.getOd1().getValueAt(vi, 1).toString());
				return;
		 }
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
		//dmv.getjButtonadd().addActionListener(this);
		//JOptionPane.showMessageDialog(null,"激活");
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
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,"非激活");
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==v.getCom_sales()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
			 hql = "SELECT slpcode,slpname from oslp where slpcode='-1'";
		        ob = appMain.lt.sqlclob(hql,0,1); 
		        if(!(ob==null||ob.length==0))
		        {
		          //ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
		          //v.getCom_sales1().setSelectedItem(Cbi);
		        }
		}
		else if(e.getSource()==v.getCom_sales1()&&v.getOd1().ds.getCnValue().equals("增加"))
		{
			if(v.getCom_sales().getSelectedItem()==null||((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()=="-1")
			{
				JOptionPane.showMessageDialog(null,"必须首先输入销售员");
				 hql = "SELECT slpcode,slpname from oslp where slpcode='-1'";
			        ob = appMain.lt.sqlclob(hql,0,1); 
			        if(!(ob==null||ob.length==0))
			        {
			         // ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
			          //v.getCom_sales().setSelectedItem(Cbi);
			          //v.getCom_sales1().setSelectedItem(Cbi);
			        }
				return;
			}
		}
		else if(e.getSource()==dmv.getjButtonadd())
		{
			 
		   // something about doctitle		
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
			String parm=((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText();
	        if(parm.length()==0)
	        {
	          return;
	        }
			 Session session = MdbHibernateUtils.getSession();
		     Transaction t = session.beginTransaction();{
		     try {
		    	  
			 @SuppressWarnings("deprecation")
			 Connection con = session.connection(); 
			 CallableStatement cstmt;
			 String result = null;
			
		           	      
	        hql="select p.cardName from Ocrd p " +	    	        
						" where p.cardCode='"+parm+"' and p.cardType='C'";
	    	Query q=session.createQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings("rawtypes")
			List list=q.list();
		    v.getTxt_cusn().setText(list.get(0).toString());
		    hql="select p.cntctCode,p.name from Ocpr p " +	    	        
					" where p.cardCode='"+parm+"'";
  	        q=session.createQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(100);
		    @SuppressWarnings("unchecked")
			List<Object[]> lst=q.list();
		   		   
		    v.getTxt_cuslxr().removeAllItems();
           for(Object[] object : lst){
	        v.getTxt_cuslxr().addItem(new  ComboBoxItem(object[0],object[1].toString()));
           }
           hql="select p.slpcode,q.slpname from Ocrd p inner join oslp q on p.slpcode=q.slpcode  " +	    	        
				" where p.cardCode='"+parm+"' and  p.cardType='C'";
            ob = appMain.lt.sqlclob(hql,0,1); 
	        ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
            v.getCom_sales().setEditable(true);
 		    v.getCom_sales().setSelectedItem(Cbi);
 		    v.getCom_sales().setEditable(false);
	        hql = "select territory from Ocrd where cardCode='"+((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText()+"'"; 
	        q=session.createQuery(hql);	  
			q.setFirstResult(0);
			q.setMaxResults(1);
		    @SuppressWarnings("rawtypes")
			List lst1=q.list();   
		    if(lst1.get(0)==null)
		    {
		    	JOptionPane.showMessageDialog(null,"客户没有所属地区");	
		    	return;
		    }
	        Integer dzid=(Integer) lst1.get(0);  
	         hql = "{call zdy_cxdq(?,?)}";
	         try {	        	
				cstmt = con.prepareCall(hql);				
				cstmt.setInt(1,dzid);				
				cstmt.registerOutParameter(2, java.sql.Types.NVARCHAR);				
				cstmt.execute();			
				result = cstmt.getString(2);
			    v.getTxt_ter().setText(result);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	        t.commit();	       	      	          
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
	      }
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
			docf.getOrdrAbsDoc().ctarget(v,Integer.valueOf(v.getTxt_docn().getText().toString()));		
		}
		else if(e.getSource()==dmv.getjButtonremend())
		{
			/*v.getOd1().setDs(docTitleStatus.remend);
			v.getOd1().setDocTitleStatus(v);
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.remend);
			
			dmv.setadd();*/
		}
		else if(e.getSource()==dmv.getjButtonsave())
		{	try{
			  v.getJt().getCellEditor().stopCellEditing();	
		    }
			catch(NullPointerException e0){
				//e0.printStackTrace();
				//return;
			}
			docf.getOrdrAbsDoc().create(v);
		}
		else if(e.getSource()==dmv.getjButtonquery())
		{					
			QueryWindowView<OrdrView> qwv=new QueryWindowView<OrdrView>(v,docf.getQDoc(),v.getOd1());
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
				 JOptionPane.showMessageDialog(null, "ordrscontroller-actionPerformed-delrow");
				 System.out.println("ordrscontroller-actionPerformed-delrow");
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
			v.getOd().setDocLineStatus(docLineStatus.query);	
			docf.getOrdrAbsDoc().setValues(v, docf.getOrdrAbsDoc().getfirst(),"订单");	
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprev())
		{  
			if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
			{					
				v.getOd().setDocLineStatus(docLineStatus.query);									  
				 try{
					 docf.getOrdrAbsDoc().setValues(v,docf.getOrdrAbsDoc().getprev(Integer.valueOf(v.getTxt_docn().getText())),"订单");
					}
				catch(NumberFormatException e0){
					
				}	
				
				v.getOd1().setDs(docTitleStatus.query);
				v.getOd1().setDocTitleStatus(v);
				dmv.setquery();				
			}
		}
		else if(e.getSource()==dmv.getjButtonnext())
		{  
			if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
			{			
				v.getOd().setDocLineStatus(docLineStatus.query);
				 try{
					 docf.getOrdrAbsDoc().setValues(v,docf.getOrdrAbsDoc().getnext(Integer.valueOf(v.getTxt_docn().getText())),"订单");
					}
				catch(NumberFormatException e0){
					
				}			
				v.getOd1().setDs(docTitleStatus.query);
			    v.getOd1().setDocTitleStatus(v);
				dmv.setquery();
			}
		}
		else if(e.getSource()==dmv.getjButtonlast())
		{  
			v.getOd().setDocLineStatus(docLineStatus.query);		   
			docf.getOrdrAbsDoc().setValues(v, docf.getOrdrAbsDoc().getlast(),"订单");					
			v.getOd1().setDs(docTitleStatus.query);		
		    v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprint())
		{
		
		}
		else if(e.getSource()==dmv.getjButtonclose())
		{			
			docf.getOrdrAbsDoc().close(v);
		}
		else if(e.getSource()==dmv.getjButtoncancel())
		{
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();			
		}
		else{
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("物料代码")||v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("仓库代码")||v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("是否换货"))
		{
	       v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());	       
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
		if(e.getKeyCode()==127&&e.getSource()==v.getJt())
		{  
			int[] i=v.getJt().getSelectedRows();
			for(int k=0;k<i.length;k++)
			{				
			 try{		
			 i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 JOptionPane.showMessageDialog(null, "Ordrscontroller-keyreleased-127");
			     System.out.println("Ordrscontroller-keyreleased-127");
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
			   
		}
		else if(e.getKeyCode()==67&&e.getSource()==v.getJt())
		{
			int si=v.getJt().getSelectedRow();
			v.getOd().copyRow(v.getJt().getSelectedRow()+1, obj);
			v.getJt().setRowSelectionInterval(si+1, si+1);
		}
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()==v.getOd().getRowCount()-1&&e.getSource()==v.getJt())
		{  
			int i=v.getJt().getSelectedRow();
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
			v.getJt().setColumnSelectionInterval(7,7);
			v.getJt().setRowSelectionInterval(i+1, i+1);
			 v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()<v.getOd().getRowCount()-1&&e.getSource()==v.getJt())
		{  
			int i=v.getJt().getSelectedRow();
			v.getJt().setColumnSelectionInterval(7,7);
			v.getJt().setRowSelectionInterval(i, i);
			 v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else if(e.getKeyCode()==32)
		{			
			if(((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText()!=null&&!((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText().equals("")
					&&v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").equals(""))
			{
				int i=JOptionPane.showConfirmDialog(null, "是否保存");
				if(i==0)
				{										                    					
					  try{
						  v.getJt().getCellEditor().stopCellEditing();	
					    }
						catch(NullPointerException e0){
							//e0.printStackTrace();
							
						}
					   docf.getOrdrAbsDoc().create(v);
				}
			}
		}
	
		else
		{
			
		}
	}

}	

