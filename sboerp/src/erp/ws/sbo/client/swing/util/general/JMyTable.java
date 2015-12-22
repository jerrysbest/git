package erp.ws.sbo.client.swing.util.general;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import erp.ws.sbo.utils.SNL;


public class JMyTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5562379959657458058L;
    
	private int colindex=0;
	private String doctype;
	private SNL snl=new SNL();
	public int getColindex() {
		return colindex;
	}
	public void setColindex(int colindex) {
		this.colindex = colindex;
	}
	public JMyTable(TableModel tm)
	{
	  super(tm);
	  //选中cell获得编辑状态
	  this.setSurrendersFocusOnKeystroke(true);	  
	}
	public JMyTable(TableModel tm,int colindex,String doctype)
	{
	  super(tm);
	  //选中cell获得编辑状态
	  this.setSurrendersFocusOnKeystroke(true);	 
	  this.setColindex(colindex);
	  this.setDoctype(doctype);
	}
	
	public boolean editCellAt(int row, int column)
	{
	  boolean ret=super.editCellAt(row, column);
	  if (ret) {
		 
		 /* if(getEditorComponent().getClass().toString().contains("ComBoBox"))
		  {*/
	         getEditorComponent().requestFocus();
		  //}
	   }
	   return(ret);
	}
	//点击单元格直接选中内容
	public void changeSelection(int rowIndex, int columnIndex,
            boolean toggle, boolean extend){
            super.changeSelection(rowIndex, columnIndex, toggle, extend);
            super.editCellAt(rowIndex, columnIndex, null);
            if(this.getValueAt(this.selectionModel.getAnchorSelectionIndex(), colindex)==null)
                return;
            try{
            
             JTextField jText = (JTextField) ( (DefaultCellEditor) this.getCellEditor(rowIndex,columnIndex)).getComponent();  
             //JOptionPane.showMessageDialog(null, snl.ifSNrow(doctype, this.getValueAt(this.selectionModel.getAnchorSelectionIndex(), colindex).toString()));
              if(snl.ifSNrow(doctype, this.getValueAt(this.selectionModel.getAnchorSelectionIndex(), colindex).toString()))
              {
            	  jText.setEditable(false);
            	  jText.setFocusable(false);
              }
              else{
            	  jText.setEditable(true);
            	  jText.setFocusable(true);
            	  jText.requestFocus(); 
  		          jText.selectAll();  
              }
		       
		      }
            catch(ClassCastException e0)
            {
               //e0.printStackTrace(); 
            	try{
	            	 ComboBoxCellEditor jcomb = (ComboBoxCellEditor) this.getCellEditor(rowIndex,columnIndex);
	                 //JOptionPane.showMessageDialog(null, snl.ifSNrow(doctype, this.getValueAt(this.selectionModel.getAnchorSelectionIndex(), colindex).toString()));
	                 if(snl.ifSNrow(doctype, this.getValueAt(this.selectionModel.getAnchorSelectionIndex(), colindex).toString()))
	                 {
	                	 jcomb.stopCellEditing();	                	
	                 }
	                 else{
	                	 //jcomb.isCellEditable(true);                 
	                 }
            	  }
            	  catch(ClassCastException e1)
            	  {
            		  
            	  }
            }
            
           
     }
	public void HiddenCell(int column,int width) {
        TableColumn tc = this.getTableHeader().getColumnModel().getColumn(column);
        tc.setMaxWidth(width);
        tc.setPreferredWidth(width);
        tc.setWidth(width);
        tc.setMinWidth(width);
        this.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(width);
        this.getTableHeader().getColumnModel().getColumn(column).setMinWidth(width);
    }
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	
	
	
}

