package erp.ws.sbo.client.swing.tablemodel;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.model.ColUAutherDocLine;
import erp.ws.sbo.client.swing.model.UAutherDocLine;
import erp.ws.sbo.client.swing.util.general.ComboBoxCellEditor;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.utils.DbUtils;


public  class UAutherDocLineModel extends AbstractDocLineModel<ColUAutherDocLine,UAutherDocLine>{
		
/**
	 * 
	 */
	private static final long serialVersionUID = -103667004279646161L;
/**
	 * 
	 */
public UAutherDocLineModel(ColUAutherDocLine colob, UAutherDocLine ob, DbUtils<ColUAutherDocLine, UAutherDocLine> dbu,
		String SQLQuery, Boolean b) {
	 super(colob,ob,dbu,SQLQuery,b);
	// TODO Auto-generated constructor stub
	
}
public UAutherDocLineModel(ColUAutherDocLine colob, UAutherDocLine ob, DbUtils<ColUAutherDocLine, UAutherDocLine> dbu,
		String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * @标准model
	 */
	    
   /**
    * @实现父类的抽象方法
    */
    //@Override
    public  boolean isCellEditable(int row,int col)
    {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.  
 
    		if(col==this.getcolumnindex("授权"))
	    	{  		
	    		return true;
	    	}   
    		else{
    			return false;
    		}
    
    	  	
     }

	@Override
	public double[] sum(int[] col) {
		//TODO Auto-generated method stub	
		if(dataSet==null)
		{
			return null;
		}
		double[] d=new double[col.length];
		for(int j=0;j<col.length;j++)
		{
			for(int i=0;i<dataSet.length;i++)
			{
				if(dataSet[i][col[j]]!=null)
				{
				  d[j]+=Double.valueOf(dataSet[i][col[j]].toString());
				}			
			}
		}
		return d;
	}
	
	@Override
	public void valueChanged(int row,int col,String itemCode,String doctype) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void itemEndEdit(int row, int col,String planQty) {
		// TODO Auto-generated method stub          
		
	}

	@Override
	public void setUpSportColumn(JTable table, TableColumn sportColumn,String hql,String hql1) {
		// TODO Auto-generated method stub
		 //Set up the editor for the sport cells.
        comboBox = new JAutoCompleteComboBox(hql,hql1);       
        sportColumn.setCellEditor(new ComboBoxCellEditor(comboBox));
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);		
	}
	@Override
	public void setUpStaSportColumn(JTable table, TableColumn sportColumn,
			List<Object[]> items) {
		// TODO Auto-generated method stub
		comboBox=new JComboBox();
		 for(Object[] object : items){		      
	           comboBox.addItem(new  ComboBoxItem(object[0],object[1].toString()));
	           }
		 comboBox.setSelectedIndex(0);
		 sportColumn.setCellEditor(new ComboBoxCellEditor(comboBox));
	        //Set up tool tips for the sport cells.
	        DefaultTableCellRenderer renderer =
	                new DefaultTableCellRenderer();
	        renderer.setToolTipText("Click for combo box");
	        sportColumn.setCellRenderer(renderer);	
	}

	@Override
	public double[] sum(int[] col, String title, String cardcode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean verification() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
 
}
