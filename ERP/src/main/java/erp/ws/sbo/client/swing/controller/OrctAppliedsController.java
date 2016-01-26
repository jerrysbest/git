package erp.ws.sbo.client.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import erp.ws.sbo.client.swing.view.Orct.OrctAppliedView;

public class OrctAppliedsController implements ActionListener,TableModelListener,MouseListener,KeyListener {
    private OrctAppliedView v;
    private Object[][] ob;

   
	public OrctAppliedsController(OrctAppliedView v) {
		// TODO Auto-generated constructor stub
		this.v=v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//v.setClosed(true);
		v.setVisible(false);
		try{
			  v.getJt().getCellEditor().stopCellEditing();				
		    }
			catch(NullPointerException e0){
				//e0.printStackTrace();
			}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		String[] s={"伙伴代码"};
		ob=v.getOd().Filterdrecords(true, s);
		Double[] sum=new Double[ob.length];
		for(int i=0;i<sum.length;i++)
		{
			sum[i]=Double.valueOf("0");
		}
		String ss="";
		for(int j=0;j<v.getOd().getRowCount();j++)
		{
			if(v.getOd().getValueAt(j, v.getOd().getcolumnindex("伙伴代码"))==null||v.getOd().getValueAt(j, v.getOd().getcolumnindex("伙伴代码")).toString().equals(""))
			break;
			for(int i=0;i<ob.length;i++)
			{			
				if(v.getOd().getValueAt(j, v.getOd().getcolumnindex("伙伴代码")).toString().equals(ob[i][0].toString()))
				{
				   sum[i]+=Double.valueOf(v.getOd().getValueAt(j, v.getOd().getcolumnindex("科目收款金额")).toString());				
				   break;	
				}
			}
		}
		for(int i=0;i<ob.length;i++)
		{
			if(i!=0&&i%4==0)
			{
			 ss+=ob[i][0].toString()+":"+sum[i]+";"+"\n";
			}
			else
			{
			 ss+=ob[i][0].toString()+":"+sum[i]+";";
			}
		}
		v.getJtf_sum().setText(ss);
		
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		String[] s={"伙伴代码"};
		ob=v.getOd().Filterdrecords(true, s);
		Double[] sum=new Double[ob.length];
		for(int i=0;i<sum.length;i++)
		{
			sum[i]=Double.valueOf("0");
		}
		String ss="";
		for(int j=0;j<v.getOd().getRowCount();j++)
		{
			if(v.getOd().getValueAt(j, v.getOd().getcolumnindex("伙伴代码"))==null||v.getOd().getValueAt(j, v.getOd().getcolumnindex("伙伴代码")).toString().equals(""))
			break;
			for(int i=0;i<ob.length;i++)
			{			
				if(ob[i][0]!=null&&v.getOd().getValueAt(j, v.getOd().getcolumnindex("伙伴代码")).toString().equals(ob[i][0].toString()))
				{
				   sum[i]+=Double.valueOf(v.getOd().getValueAt(j, v.getOd().getcolumnindex("科目收款金额")).toString());				
				   break;	
				}
			}
		}
		for(int i=0;i<ob.length;i++)
		{
			if(i!=0&&i%4==0)
			{
			 ss+=ob[i][0].toString()+":"+sum[i]+";"+"\n";
			}
			else
			{
			 ss+=ob[i][0].toString()+":"+sum[i]+";";
			}
		}
		v.getJtf_sum().setText(ss);
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub	
		Object[] obj=new Object[v.getOd().getColumnCount()];
		//insert键
		if(e.getKeyCode()==155&&e.getSource()==v.getJt())
		{
			int row=v.getJt().getSelectedRow();
			Object o=v.getOd().getValueAt(row,v.getOd().getcolumnindex("伙伴代码"));
			v.getOd().insertRow(row+1, obj);		 
		    v.getOd().setValueAt(Double.valueOf("0"), row+1,v.getOd().getcolumnindex("科目收款金额"));
		    v.getOd().setValueAt(o, row+1,v.getOd().getcolumnindex("伙伴代码"));
		    o=v.getOd().getValueAt(row,v.getOd().getcolumnindex("伙伴名称"));
		    v.getOd().setValueAt(o, row+1,v.getOd().getcolumnindex("伙伴名称"));
		}
		else if(e.getKeyCode()==127&&e.getSource()==v.getJt())
		{  
			
	    }
		else{}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
