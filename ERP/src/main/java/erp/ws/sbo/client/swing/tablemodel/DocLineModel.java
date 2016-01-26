package erp.ws.sbo.client.swing.tablemodel;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import erp.ws.sbo.utils.DbUtils;




@SuppressWarnings({ "unchecked", "rawtypes" })
public  class DocLineModel extends AbstractDocLineModel{
		
/**
	 * 
	 */
	private static final long serialVersionUID = -103667004279646161L;
/**
	 * 
	 */
public DocLineModel( DbUtils dbu,String table,
		String SQLQuery, Boolean b) {
	 super(dbu,table,dbu, SQLQuery,b);
	// TODO Auto-generated constructor stub
	
}
public DocLineModel( DbUtils dbu,String table,
			String SQLQuery) {
		super(dbu, table,dbu, SQLQuery);
		// TODO Auto-generated constructor stub
		
	}
		    
   /**
    * @实现父类的抽象类
    */
    //@Override
    public  boolean isCellEditable(int row,int col)
    {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.   	
    	return false;    	
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

	@Override
	public double[] sum(int[] col, String title, String cardcode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean verification() {
		// TODO Auto-generated method stub
		double a,b,c,d,e,g,h,j,k,l;
		String ck;
		for(int i=0;i<getRowCount();i++)
		{
		    if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段")!=null&&this.getValuethrheader(i,"是否显示米段").toString().equals("是")))	
		    {
		    	 a=Double.valueOf(dataSet[i][this.getcolumnindex("单价")].toString());
				 b=Double.valueOf(dataSet[i][this.getcolumnindex("数量")].toString());
				 c=Double.valueOf(dataSet[i][this.getcolumnindex("金额")].toString());
				 d=Double.valueOf(dataSet[i][this.getcolumnindex("米价格")].toString());
				 e=Double.valueOf(dataSet[i][this.getcolumnindex("包装单价")].toString());
				 g=Double.valueOf(dataSet[i][this.getcolumnindex("单位数量")].toString());
				 h=Double.valueOf(dataSet[i][this.getcolumnindex("包装数量")].toString());
				 j=Double.valueOf(dataSet[i][this.getcolumnindex("物料米段")].toString());
				 k=Double.valueOf(dataSet[i][this.getcolumnindex("总长")].toString());
				 l=Double.valueOf(dataSet[i][this.getcolumnindex("总重")].toString());
				 ck=dataSet[i][this.getcolumnindex("仓库代码")].toString();
				 if(Math.abs(c/a-b)/b>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行金额除以单价不等于数量"); 					 
					 return false;
				 }
				 else if(Math.abs(c/k-d)/d>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行金额除以总长不等于米价格");   	
					 return false;
				 }
				 else if(Math.abs(e/j-d)/d>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行包装单价除以物料米段不等于米价格");   	
					 return false;
				 }
				 else if(Math.abs(e/g-a)/a>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行包装单价除以单位数量不等于单价");   	
					 return false;
				 }
				 else if(Math.abs(k/h-j)/j>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行总长除以包装数量不等于米段");   	
					 return false;
				 }
				 else if(Math.abs(l/h-g)/g>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行总重除以包装数量不等于单位数量");   	
					 return false;
				 }
				 else if(ck!="2108")
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行仓库不正确"); 					 
					 return false;
				 }
				 else{}
		    }
		    else  if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&this.getValuethrheader(i,"是否显示米段").toString().equals("否"))	
		    {
		    	ck=dataSet[i][this.getcolumnindex("仓库代码")].toString();
		    	if(!ck.equals("2107"))
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行仓库不正确"); 					 
					 return false;
				 }
		    }
		    else  if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段")==null||(this.getValuethrheader(i,"是否显示米段")!=null&&this.getValuethrheader(i,"是否显示米段").toString().equals(""))))	
		    {
		    	ck=dataSet[i][this.getcolumnindex("仓库代码")].toString();
		    	if(!ck.equals("2108"))
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行仓库不正确"); 					 
					 return false;
				 }
		    }
		
		}
		return true;
	}
	@Override
	public void valueChanged(int row, int col, String cardCode, String doctype) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemEndEdit(int row, int col, String cardCode) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUpSportColumn(JTable table, TableColumn sportColumn,
			String hql, String hql1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUpStaSportColumn(JTable table, TableColumn sportColumn,
			List items) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
 
}
