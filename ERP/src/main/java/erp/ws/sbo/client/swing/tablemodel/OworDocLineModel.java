package erp.ws.sbo.client.swing.tablemodel;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColOworDocLine;
import erp.ws.sbo.client.swing.model.OworDocLine;
import erp.ws.sbo.client.swing.util.general.ComboBoxCellEditor;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.utils.DbUtils;


public  class OworDocLineModel extends AbstractDocLineModel<ColOworDocLine,OworDocLine>{
		
/**
	 * 
	 */
	private static final long serialVersionUID = -103667004279646161L;
/**
	 * 
	 */
public OworDocLineModel(ColOworDocLine colob, OworDocLine ob, DbUtils<ColOworDocLine, OworDocLine> dbu,
		String SQLQuery, Boolean b) {
	 super(colob,ob,dbu,SQLQuery,b);
	// TODO Auto-generated constructor stub
	
}
public OworDocLineModel(ColOworDocLine colob, OworDocLine ob, DbUtils<ColOworDocLine, OworDocLine> dbu,
		String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * @标准model
	 */
	    
   /**
    * @实现父类的抽象类
    */
    //@Override
    public  boolean isCellEditable(int row,int col)
    {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
   
    	if(ds==docLineStatus.query||ds==docLineStatus.load)
    	{
    		return false;
    	}
    	if(ds==docLineStatus.oworadd)
    	{
	    	if(col==this.getcolumnindex("仓库"))
	    	{  		
	    		return true;
	    	}   
	    	else
	    	{
	    		return false;  				 
	    	}
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
					 d[j]+=Double.valueOf(dataSet[i][col[j]].toString().equals("")?"0":dataSet[i][col[j]].toString());						
				}			
			}
		}
		return d;
	}
	
	public void valueChanged1(int row,int col,String itemCode,Double planQty) {
		if(itemCode==null||itemCode.equals(""))
		{
			JOptionPane.showMessageDialog(null, "产品代码不能为空");
			return;
		}
		 	
		 if(getcolumnheader(col).equals("基本数量")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").equals(""))
		{			

			Itcode=dataSet[row][this.getcolumnindex("物料代码")].toString();
            dwsl = Double.valueOf(dataSet[row][this.getcolumnindex("基本数量")].toString());          
            
            hql = "select a.Quantity/b.Qauntity from itt1 a inner join oitt b on a.father=b.code where b.code='" + itemCode + "' " +
            		"and a.code='"+Itcode+"'";
            ob =appMain.lt.sqlclob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	
            wgtpm=Double.valueOf(ob[0][0].toString());
          
            dataSet[row][this.getcolumnindex("计划数量")]=dwsl*planQty;
            this.fireTableDataChanged();
            return;
		}
		else if(getcolumnheader(col).equals("计划数量")&&this.getValuethrheader(row,"物料代码")!=null&&this.getValuethrheader(row,"物料代码")!="")
		{			
			Itcode=dataSet[row][this.getcolumnindex("物料代码")].toString();
            sl=Double.valueOf(dataSet[row][this.getcolumnindex("计划数量")].toString());            
            hql = "select a.Quantity/b.Qauntity from itt1 a inner join oitt b on a.father=b.code where b.code='" + itemCode + "' " +
            		"and a.code='"+Itcode+"'";
            ob =appMain.lt.sqlclob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	
            wgtpm=Double.valueOf(ob[0][0].toString());         
            dataSet[row][this.getcolumnindex("基本数量")]=sl/planQty;
            this.fireTableDataChanged();
            return;
		}	
		else if(getcolumnheader(col).equals("物料代码")&&this.getValuethrheader(row,"物料代码")!=null&&this.getValuethrheader(row,"物料代码")!="")
		{	
			itemEndEdit1(row,col,itemCode,planQty);
		}
		else{
			return;
		}
	}
	@Override
	public void valueChanged(int row,int col,String itemCode,String doctype) {
		// TODO Auto-generated method stub
		
		
	}
	public void itemEndEdit1(int row, int col,String itemCode,Double planQty) {
	
		 Itcode=getValueAt(row,col).toString();       
         if(Itcode==null||Itcode.length()==0)
         {
        	 return;
         }
         hql = "select a.Quantity/b.Qauntity from itt1 a inner join oitt b on a.father=b.code where b.code='" + itemCode + "' " +
         		"and a.code='"+Itcode+"'";
         ob =appMain.lt.sqlclob(hql,0,1);
         if(ob==null||ob.length==0)
         {           	               
             return;
         }	
         dwsl=Double.valueOf(ob[0][0].toString());        
         sl=dwsl*Double.valueOf(planQty);
         dataSet[row][this.getcolumnindex("基本数量")]=dwsl;
         dataSet[row][this.getcolumnindex("计划数量")]=sl;
         this.fireTableDataChanged();
         return;
	}
	@Override
	public void itemEndEdit(int row, int col,String planQty) {
		// TODO Auto-generated method stub          
           
	}

	@Override
	public void setUpSportColumn(JTable table, TableColumn sportColumn,String hql,String hql1) {
		// TODO Auto-generated method stub
		 //Set up the editor for the sport cells.
        comboBox = new JAutoCompleteComboBox(hql,hql1,2);       
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
