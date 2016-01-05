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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.BadLocationException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.PaSN.PaSNView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.utils.SNL;
import erp.ws.sbo.utils.Snprint;

public class PaSNsController implements KeyListener, MouseListener, InternalFrameListener, TableModelListener, ListSelectionListener, ActionListener, FocusListener {

	private PaSNView v;
	private DocMenuView dmv=DocMenuView.getdmv();
	private Object[] obj;
	private String hql;//query string 
	private Object[][] ob1;
	@SuppressWarnings("unchecked")
	private DaoFactory<PaSNView> docf=(DaoFactory<PaSNView>)appMain.ctx.getBean("PaSNFactory");	
	public PaSNsController(PaSNView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}
	public PaSNView getV() {
		return v;
	}
	public void setV(PaSNView v) {
		this.v = v;
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		 docf.getDoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(e.getLastIndex(), 2).toString()),v.getOd1().getValueAt(e.getLastIndex(), 1).toString());		
		 v.getOd().setDocLineStatus(docLineStatus.query);
		 v.getOd1().setDs(docTitleStatus.query);
		 v.getOd1().setDocTitleStatus(v);
		 dmv.setquery();
	}
	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
			int[] i=v.getJt().getSelectedRows();
		    v.getOd().delRow(i);		   
		    if(i[0]>0)
		    {
			   v.getJt().setRowSelectionInterval(i[0]-1, i[0]-1);
		    }
		    else
		    {
		    	v.getJt().setRowSelectionInterval(0, 0);
		    }    
		}
		else if(e.getKeyCode()==32)
		{					
			if(e.getSource()==v.getJt())
			{
				if(v.getOd().getValuethrheader(0, "序列号")!=null&&!v.getOd().getValuethrheader(0, "序列号").equals(""))
				{
					int i=JOptionPane.showConfirmDialog(null, "是否保存");
					if(i==0)
					{										                    					
						docf.getDoc().create(v);
					}
				}
			}
			else{}
		}
		else if(e.getKeyCode()==10)
		{
			if(e.getSource()==v.getJta_SN())
			{	
				SNL snl=new SNL();
				try {
					if(snl.verificationPSN(v.getJta_SN()))
					{
						String[] sns=v.getJta_SN().getText().trim().split(",");
						v.getOd().setGridStatus(docLineStatus.add);
						for(int i=0;i<sns.length;i++)
						{				  
							v.getOd().setValuethrheader(sns[i], i, "序列号");
							v.getOd().setValuethrheader("", i, "备注");
						}
					}
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==v.getJta_memo())
			{	
				 JOptionPane.showMessageDialog(null,"打印二维码序列号");
				 Snprint snsp=new Snprint();
				 hql="select u_width,u_height from [@SNPRINTER] where code='1'";
			     ob1=appMain.lt.sqlclob(hql,0,1);
		         snsp.print(ob1[0][0].toString(), ob1[0][1].toString(), "5", "8", "0", "0", "0", "128", v.getJta_memo().getText(),v);	        
		            				 
			}
		}
		else
		{}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getSource()==dmv.getjButtonadd())
			{
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
				
			}
			else if(e.getSource()==dmv.getjButtonsave())
			{	if(v.getOd().getValuethrheader(0, "序列号")!=null&&!v.getOd().getValuethrheader(0, "序列号").toString().equals("")){		  				
					docf.getDoc().create(v);
				}
			    else{JOptionPane.showMessageDialog(null, "单身没有数据，请录入数据");}
			}
			else if(e.getSource()==dmv.getjButtonquery())
			{					
				QueryWindowView<PaSNView> qwv=new QueryWindowView<PaSNView>(v,docf.getQDoc(),v.getOd1());
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
			    v.getOd().delRow(i);		   
			    if(i[0]>0)
			    {
				   v.getJt().setRowSelectionInterval(i[0]-1, i[0]-1);
			    }
			    else
			    {
			    	v.getJt().setRowSelectionInterval(0, 0);
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
				if(v.getTxt_docn().getText()!=null&&v.getTxt_docn().getText()!="")
				{	
				    docf.getDoc().setValues(v,docf.getDoc().getprev(Integer.valueOf(v.getTxt_docn().getText())),"");
					v.getOd().setDocLineStatus(docLineStatus.query);
					v.getOd1().setDs(docTitleStatus.query);
					v.getOd1().setDocTitleStatus(v);
					dmv.setquery();
				}
			}
			else if(e.getSource()==dmv.getjButtonnext())
			{  
				if(v.getTxt_docn().getText()!=null&&v.getTxt_docn().getText()!="")
				{			
					docf.getDoc().setValues(v,docf.getDoc().getnext(Integer.valueOf(v.getTxt_docn().getText())),"");
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

			}		
			else{
				
			}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==v.getTxt_tsn()&&!v.getTxt_tsn().getText().equals(""))
		{
			/*hql="select * from [@SNStatus] where sn='"+v.getTxt_tsn().getText()+"'";
			ob = appMain.lt.sqlclob(hql,0,1);
           if(!(ob==null||ob.length==0))
           {
        	   JOptionPane.showMessageDialog(null, "单头序列号已存在，请重新输入");
        	   v.getTxt_tsn().setText("");
        	   return;
           }*/
	           
		}
	}

}
