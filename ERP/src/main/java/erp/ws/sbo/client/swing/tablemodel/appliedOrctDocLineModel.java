package erp.ws.sbo.client.swing.tablemodel;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColAppliedOrctDocLine;
import erp.ws.sbo.client.swing.model.appliedOrctDocLine;
import erp.ws.sbo.client.swing.util.general.ComboBoxCellEditor;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.utils.DbUtils;

public class appliedOrctDocLineModel extends
		AbstractDocLineModel<ColAppliedOrctDocLine, appliedOrctDocLine> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4139957782369105932L;

	public appliedOrctDocLineModel(ColAppliedOrctDocLine colob, appliedOrctDocLine ob1,
			DbUtils<ColAppliedOrctDocLine, appliedOrctDocLine> dbu, String SQLQuery, Boolean b) {
		super(colob, ob1, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}

	public appliedOrctDocLineModel(ColAppliedOrctDocLine colob, appliedOrctDocLine ob1,
			DbUtils<ColAppliedOrctDocLine, appliedOrctDocLine> dbu, String SQLQuery) {
		super(colob, ob1, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isCellEditable(int row, int col) {
		// TODO Auto-generated method stub
		if(ds==docLineStatus.query||ds==docLineStatus.load)
    	{
    		return false;
    	}
    	if(col==this.getcolumnindex("科目收款金额")||col==this.getcolumnindex("科目代码"))
    	{   		
    		return true;
    	} 	
    	else
    	{
    		return false;  				 
    	}
	}

	@Override
	public double[] sum(int[] col) {
		// TODO Auto-generated method stub
		double[] d=new double[col.length];
		for(int j=0;j<col.length;j++)
		{
			if(dataSet!=null)
			{	
			for(int i=0;i<dataSet.length;i++)
			{
				if(dataSet[i][col[j]]!=null&&!dataSet[i][col[j]].toString().equals(""))
				{
				  d[j]+=Double.valueOf(dataSet[i][col[j]].toString());
				}			
			}
			}
		}
		return d;
	}

	@Override
	public void valueChanged(int row, int col, String cardCode,String doctype) {
		// TODO Auto-generated method stub
		try{
		if(getcolumnheader(col).equals("科目代码"))
		{	
			 Itcode=this.getValuethrheader(row,"科目代码").toString();
			 hql = "SELECT AcctName FROM OACT where AcctCode='"+Itcode+"' "; 
		     ob=appMain.lt.sqlclob(hql,0,1);
		     if (ob==null||ob.length == 0)
	         return;
		     dataSet[row][this.getcolumnindex("科目名称")]=ob[0][0].toString();	       
			 this.fireTableDataChanged();
		}
	  else if(getcolumnheader(col).equals("科目收款金额"))
	  {
		  if((this.getValuethrheader(row, "科目代码")==null||this.getValuethrheader(row, "科目代码").toString()=="")&&new BigDecimal(this.getValueAt(row, col).toString()).compareTo(new BigDecimal(0))!=0)
		  {
			  JOptionPane.showMessageDialog(null, "请输入科目");
			  this.setValueAt("0", row, col);
			  this.fireTableDataChanged();
			  return;
		  }
		  
	  }
	  else{}
	  }
	  catch(NumberFormatException e0){
			  System.out.println("appliedOrctDocLineModel-valuecahanged-科目收款金额");
	  }
	}

	@Override
	public void itemEndEdit(int row, int col, String cardCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpSportColumn(JTable table, TableColumn sportColumn,
			String hql, String hql1) {
		// TODO Auto-generated method stub
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
