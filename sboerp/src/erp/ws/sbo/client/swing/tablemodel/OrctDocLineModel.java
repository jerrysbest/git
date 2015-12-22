package erp.ws.sbo.client.swing.tablemodel;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.model.ColOrctDocLine;
import erp.ws.sbo.client.swing.model.OrctDocLine;
import erp.ws.sbo.utils.DbUtils;

public class OrctDocLineModel extends
		AbstractDocLineModel<ColOrctDocLine, OrctDocLine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139957782369105932L;

	public OrctDocLineModel(ColOrctDocLine colob, OrctDocLine ob1,
			DbUtils<ColOrctDocLine, OrctDocLine> dbu, String SQLQuery, Boolean b) {
		super(colob, ob1, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}

	public OrctDocLineModel(ColOrctDocLine colob, OrctDocLine ob1,
			DbUtils<ColOrctDocLine, OrctDocLine> dbu, String SQLQuery) {
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
    	if(col==this.getcolumnindex("收款金额"))
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
	//批量收款单按照业务伙伴汇总
	public double[] sum(int[] col, String title,String cardcode) {
		// TODO Auto-generated method stub
		double[] d=new double[col.length];
		for(int j=0;j<col.length;j++)
		{
			if(dataSet!=null)
			{	
				for(int i=0;i<dataSet.length;i++)
				{
					if(dataSet[i][this.getcolumnindex(title)].toString().equals(cardcode))
					{
						if(dataSet[i][col[j]]!=null&&!dataSet[i][col[j]].toString().equals(""))
						{
						  d[j]+=Double.valueOf(dataSet[i][col[j]].toString());
						}	
					}
				}
			}
		}
		return d;
	}
	@Override
	public void valueChanged(int row, int col, String cardCode,String doctype) {
		// TODO Auto-generated method stub
		 if(getcolumnheader(col).equals("收款金额"))
		{	try{
			 //在abstractdoclinemodel中有验证
			 sl = Double.valueOf(dataSet[row][this.getcolumnindex("收款金额")].toString());
			 dwjg=Double.valueOf(dataSet[row][this.getcolumnindex("应收余额")].toString());	
			  if(Double.valueOf(dataSet[row][this.getcolumnindex("收款金额")].toString())>0&&Double.valueOf(dataSet[row][this.getcolumnindex("应收余额")].toString())>0&&(new BigDecimal(sl)).compareTo(new BigDecimal(dwjg))==1)
			 {
				 JOptionPane.showMessageDialog(null,"收款金额绝对值不能大于应收余额绝对值");
				 dataSet[row][this.getcolumnindex("收款金额")]=0;
				 return;
			 }
    		 else if(Double.valueOf(dataSet[row][this.getcolumnindex("收款金额")].toString())<0&&Double.valueOf(dataSet[row][this.getcolumnindex("应收余额")].toString())<0&&(new BigDecimal(sl)).compareTo(new BigDecimal(dwjg))==-1)
			 {
				 JOptionPane.showMessageDialog(null,"收款金额绝对值不能大于应收余额绝对值");
				 dataSet[row][this.getcolumnindex("收款金额")]=0;
				 return;
			 }
			 else{}
			
			 dataSet[row][this.getcolumnindex("最新余额")]=dwjg-sl;	       
			 this.fireTableDataChanged();
	         return;
		}
		catch(NullPointerException e1)
		{
			System.out.println("null值或者空值，无法转换");
			 dataSet[row][this.getcolumnindex("收款金额")]=0;
			return;    
		}
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
		
	}

	@Override
	public void setUpStaSportColumn(JTable table, TableColumn sportColumn,
			List<Object[]> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean verification() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
