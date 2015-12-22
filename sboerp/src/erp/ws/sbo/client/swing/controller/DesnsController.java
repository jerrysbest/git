package erp.ws.sbo.client.swing.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.client.swing.view.OOige.OOigeView;
import erp.ws.sbo.client.swing.view.OOign.OOignView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;
import erp.ws.sbo.client.swing.view.Orin.OrinView;
import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.utils.SNL;
public class DesnsController implements TreeSelectionListener,ActionListener 
          ,KeyListener{
   private DeSNView v;
   private SNL snl=new SNL();	
   public DesnsController(DeSNView v)
   {
	   this.v=v;
   }
 
   public void valueChanged(TreeSelectionEvent   e) {
		
	}
   
   public DeSNView getDeSNView() {
		return v;
	}

	public void setDeSNView(DeSNView v) {
		this.v = v;
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
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
					 JOptionPane.showMessageDialog(null, "Desnscontroller-keyreleased-127");
				     System.out.println("Desnscontroller-keyreleased-127");
				 }			
			}
			//ɾ��jta_sn,ɾ����������
		    String s=new String("");
		    for(int l=0;l<i.length;l++)
		    {		    
			    if(v.getV().getClass().toString().contains("SninView"))
			    {
			    		s=((SninView)v.getV()).getJta_SN().getText();
			     	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((SninView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==v.getOd().getValuethrheader(i[l], "���к�").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((SninView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "���к�").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((SninView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((SninView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "�к�").toString().equals(((SninView)v.getV()).getOd().getValuethrheader(xh, "���").toString()))
		    			 {	
				    		 Double sl=Double.valueOf(((SninView)v.getV()).getOd().getValuethrheader(xh, "ʵ�ʿ������").toString());
				    		((SninView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "��������").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"��������");	
		    				
		    				((SninView)v.getV()).getOd().valueChanged(xh, ((SninView)v.getV()).getOd().getcolumnindex("��������"), ((SninView)v.getV()).getOd().getValuethrheader(xh, "���ϴ���").toString(), "");
		    				((SninView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "����").toString()), xh, "ʵ�ʿ������");			    				
		    			 }	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OignView"))
			    {
			    		s=((OignView)v.getV()).getJta_SN().getText();
			     	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==v.getOd().getValuethrheader(i[l], "���к�").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "���к�").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	
			    	 if(v.getOd().getValuethrheader(i[l], "�к�").toString().equals(((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "���").toString()))
	    			 {	
			    		Double sl=Double.valueOf(((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "ʵ�ʿ������").toString());
			    		((OignView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,((OignView)v.getV()).getJt().getSelectedRow(),"ʵ���ջ�����");			    				
	    				//((OignView)v.getV()).getOd().valueChanged(((OignView)v.getV()).getJt().getSelectedRow(), ((OignView)v.getV()).getOd().getcolumnindex("��������"), ((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "���ϴ���").toString(), "");
	    				((OignView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "����").toString()), ((OignView)v.getV()).getJt().getSelectedRow(), "ʵ�ʿ������");			    				
	    			 }	    			
			    	 
			     }
			    if(v.getV().getClass().toString().contains("OOignView"))
			    {
			    	 s=((OOignView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==v.getOd().getValuethrheader(i[l], "���к�").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "���к�").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OOignView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "�к�").toString().equals(((OOignView)v.getV()).getOd().getValuethrheader(xh, "���").toString()))
		    			 {	
				    		 Double sl=Double.valueOf(((OOignView)v.getV()).getOd().getValuethrheader(xh, "ʵ�ʿ������").toString());
				    		((OOignView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "��������").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"��������");	
		    				
		    				((OOignView)v.getV()).getOd().valueChanged(xh, ((OOignView)v.getV()).getOd().getcolumnindex("��������"), ((OOignView)v.getV()).getOd().getValuethrheader(xh, "���ϴ���").toString(), "");
		    				((OOignView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "����").toString()), xh, "ʵ�ʿ������");			    				
		    			 }	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OOigeView"))
			    {
			    	 s=((OOigeView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOigeView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==v.getOd().getValuethrheader(i[l], "���к�").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOigeView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "���к�").toString()+",","");
			    		 ((OOigeView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OOigeView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "�к�").toString().equals(((OOigeView)v.getV()).getOd().getValuethrheader(xh, "���").toString()))
		    			 {		
				    		 Double sl=Double.valueOf(((OOigeView)v.getV()).getOd().getValuethrheader(xh, "ʵ�ʿ������").toString());
				    		((OOigeView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "��������").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"��������");	
		    				
		    				((OOigeView)v.getV()).getOd().valueChanged(xh, ((OOigeView)v.getV()).getOd().getcolumnindex("��������"), ((OOigeView)v.getV()).getOd().getValuethrheader(xh, "���ϴ���").toString(), "");
		    				((OOigeView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "����").toString()), xh, "ʵ�ʿ������");			    				
		    			 }	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OinvView"))
			    {
			    	 s=((OinvView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OinvView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==v.getOd().getValuethrheader(i[l], "���к�").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OinvView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "���к�").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OinvView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OinvView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "�к�").toString().equals(((OinvView)v.getV()).getOd().getValuethrheader(xh, "���").toString()))
		    			 {		
				    		 Double sl=Double.valueOf(((OinvView)v.getV()).getOd().getValuethrheader(xh, "����").toString());
				    		((OinvView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"��װ����");	
		    				
		    				((OinvView)v.getV()).getOd().valueChanged(xh, ((OinvView)v.getV()).getOd().getcolumnindex("��װ����"), ((OinvView)v.getV()).getOd().getValuethrheader(xh, "���ϴ���").toString(), "");
		    				((OinvView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "����").toString()), xh, "����");	
		    				
		    			 }
	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OrinView"))
			    {
			    	 s=((OrinView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OrinView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "���к�").toString())+v.getOd().getValuethrheader(i[l], "���к�").toString().length()==v.getOd().getValuethrheader(i[l], "���к�").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "���к�").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OrinView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "���к�").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OrinView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OrinView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "�к�").toString().equals(((OrinView)v.getV()).getOd().getValuethrheader(xh, "���").toString()))
		    			 {	
				    		 Double sl=Double.valueOf(((OrinView)v.getV()).getOd().getValuethrheader(xh, "����").toString());
				    		((OrinView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"��װ����");	
		    				
		    				((OrinView)v.getV()).getOd().valueChanged(xh, ((OrinView)v.getV()).getOd().getcolumnindex("��װ����"), ((OrinView)v.getV()).getOd().getValuethrheader(xh, "���ϴ���").toString(), "");
		    				((OrinView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "����").toString()), xh, "����");	
		    				
		    			 }
	    			
			    	 }
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		   if(v.getV().getClass().toString().contains("OignView"))
		   {
			    try {
					snl.createPSN(v);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		   }
	}
}
