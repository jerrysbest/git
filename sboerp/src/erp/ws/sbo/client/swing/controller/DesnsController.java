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
			//删除jta_sn,删除表体数据
		    String s=new String("");
		    for(int l=0;l<i.length;l++)
		    {		    
			    if(v.getV().getClass().toString().contains("SninView"))
			    {
			    		s=((SninView)v.getV()).getJta_SN().getText();
			     	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((SninView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==v.getOd().getValuethrheader(i[l], "序列号").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((SninView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "序列号").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((SninView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((SninView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "行号").toString().equals(((SninView)v.getV()).getOd().getValuethrheader(xh, "序号").toString()))
		    			 {	
				    		 Double sl=Double.valueOf(((SninView)v.getV()).getOd().getValuethrheader(xh, "实际库存数量").toString());
				    		((SninView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "生产数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"生产数量");	
		    				
		    				((SninView)v.getV()).getOd().valueChanged(xh, ((SninView)v.getV()).getOd().getcolumnindex("生产数量"), ((SninView)v.getV()).getOd().getValuethrheader(xh, "物料代码").toString(), "");
		    				((SninView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "重量").toString()), xh, "实际库存数量");			    				
		    			 }	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OignView"))
			    {
			    		s=((OignView)v.getV()).getJta_SN().getText();
			     	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==v.getOd().getValuethrheader(i[l], "序列号").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "序列号").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	
			    	 if(v.getOd().getValuethrheader(i[l], "行号").toString().equals(((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "序号").toString()))
	    			 {	
			    		Double sl=Double.valueOf(((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "实际库存数量").toString());
			    		((OignView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "实际收货数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,((OignView)v.getV()).getJt().getSelectedRow(),"实际收货数量");			    				
	    				//((OignView)v.getV()).getOd().valueChanged(((OignView)v.getV()).getJt().getSelectedRow(), ((OignView)v.getV()).getOd().getcolumnindex("生产数量"), ((OignView)v.getV()).getOd().getValuethrheader(((OignView)v.getV()).getJt().getSelectedRow(), "物料代码").toString(), "");
	    				((OignView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "重量").toString()), ((OignView)v.getV()).getJt().getSelectedRow(), "实际库存数量");			    				
	    			 }	    			
			    	 
			     }
			    if(v.getV().getClass().toString().contains("OOignView"))
			    {
			    	 s=((OOignView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==v.getOd().getValuethrheader(i[l], "序列号").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "序列号").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOignView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OOignView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "行号").toString().equals(((OOignView)v.getV()).getOd().getValuethrheader(xh, "序号").toString()))
		    			 {	
				    		 Double sl=Double.valueOf(((OOignView)v.getV()).getOd().getValuethrheader(xh, "实际库存数量").toString());
				    		((OOignView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "生产数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"生产数量");	
		    				
		    				((OOignView)v.getV()).getOd().valueChanged(xh, ((OOignView)v.getV()).getOd().getcolumnindex("生产数量"), ((OOignView)v.getV()).getOd().getValuethrheader(xh, "物料代码").toString(), "");
		    				((OOignView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "重量").toString()), xh, "实际库存数量");			    				
		    			 }	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OOigeView"))
			    {
			    	 s=((OOigeView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOigeView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==v.getOd().getValuethrheader(i[l], "序列号").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OOigeView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "序列号").toString()+",","");
			    		 ((OOigeView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OOigeView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "行号").toString().equals(((OOigeView)v.getV()).getOd().getValuethrheader(xh, "序号").toString()))
		    			 {		
				    		 Double sl=Double.valueOf(((OOigeView)v.getV()).getOd().getValuethrheader(xh, "实际库存数量").toString());
				    		((OOigeView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "生产数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"生产数量");	
		    				
		    				((OOigeView)v.getV()).getOd().valueChanged(xh, ((OOigeView)v.getV()).getOd().getcolumnindex("生产数量"), ((OOigeView)v.getV()).getOd().getValuethrheader(xh, "物料代码").toString(), "");
		    				((OOigeView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "重量").toString()), xh, "实际库存数量");			    				
		    			 }	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OinvView"))
			    {
			    	 s=((OinvView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OinvView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==v.getOd().getValuethrheader(i[l], "序列号").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OinvView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "序列号").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OinvView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OinvView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "行号").toString().equals(((OinvView)v.getV()).getOd().getValuethrheader(xh, "序号").toString()))
		    			 {		
				    		 Double sl=Double.valueOf(((OinvView)v.getV()).getOd().getValuethrheader(xh, "数量").toString());
				    		((OinvView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "包装数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"包装数量");	
		    				
		    				((OinvView)v.getV()).getOd().valueChanged(xh, ((OinvView)v.getV()).getOd().getcolumnindex("包装数量"), ((OinvView)v.getV()).getOd().getValuethrheader(xh, "物料代码").toString(), "");
		    				((OinvView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "重量").toString()), xh, "数量");	
		    				
		    			 }
	    			
			    	 }
			     }
			    if(v.getV().getClass().toString().contains("OrinView"))
			    {
			    	 s=((OrinView)v.getV()).getJta_SN().getText();
			    	 if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==s.length())
			    	 {
			    		 s=s.substring(0,s.length()-v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OrinView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else  if(s.lastIndexOf(v.getOd().getValuethrheader(i[l], "序列号").toString())+v.getOd().getValuethrheader(i[l], "序列号").toString().length()==v.getOd().getValuethrheader(i[l], "序列号").toString().length())
			    	 {
			    		 s=s.substring(v.getOd().getValuethrheader(i[l], "序列号").toString().length());
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OrinView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 else{
			    		 s=s.replace(v.getOd().getValuethrheader(i[l], "序列号").toString()+",","");
			    		 if(s.length()==1)
			    		 {
			    			 s=new String("");
			    		 }
			    		 ((OrinView)v.getV()).getJta_SN().setText(s);
			    	 }
			    	 for(int xh=0;xh<((OrinView)v.getV()).getOd().getRowCount();xh++)
			    	 {
				    	 if(v.getOd().getValuethrheader(i[l], "行号").toString().equals(((OrinView)v.getV()).getOd().getValuethrheader(xh, "序号").toString()))
		    			 {	
				    		 Double sl=Double.valueOf(((OrinView)v.getV()).getOd().getValuethrheader(xh, "数量").toString());
				    		((OrinView)v.getV()).getOd().setValuethrheader(Integer.valueOf(new BigDecimal(((SninView)v.getV()).getOd().getValuethrheader(xh, "包装数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())-1,xh,"包装数量");	
		    				
		    				((OrinView)v.getV()).getOd().valueChanged(xh, ((OrinView)v.getV()).getOd().getcolumnindex("包装数量"), ((OrinView)v.getV()).getOd().getValuethrheader(xh, "物料代码").toString(), "");
		    				((OrinView)v.getV()).getOd().setValuethrheader(sl-Double.valueOf(v.getOd().getValuethrheader(i[l], "重量").toString()), xh, "数量");	
		    				
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
