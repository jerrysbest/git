package erp.ws.sbo.client.swing.tablemodel;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocLine;
import erp.ws.sbo.client.swing.model.DocLine;
import erp.ws.sbo.client.swing.model.Inv1Id;
import erp.ws.sbo.client.swing.util.general.ComboBoxCellEditor;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.utils.DbUtils;

public class OinvDocLineModel extends AbstractDocLineModel<ColDocLine,DocLine<Inv1Id>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7728156737959184210L;
	private String dw,Itcode,hql;
	private Double md,dwjg,sl, zc, zz, zj,dwsl; 
	private Integer colidx; 
	public OinvDocLineModel(ColDocLine colob, DocLine<Inv1Id> ob, DbUtils<ColDocLine, DocLine<Inv1Id>> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		// TODO Auto-generated method stub
		if(ds==docLineStatus.query||ds==docLineStatus.load||rows==rowStatus.sn)
    	{
    		return false;
    	}
    	if(col==this.getcolumnindex("仓库代码")||col==this.getcolumnindex("包装数量")||col==this.getcolumnindex("数量")||
    	   col==this.getcolumnindex("到货日期"))
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
				if(dataSet[i][col[j]]!=null)
				{
				  d[j]+=Double.valueOf(dataSet[i][col[j]].toString().equals("")?"0":dataSet[i][col[j]].toString());
					
				}			
			}
			}
		}
		return d;
	}

	@Override
	public void valueChanged(int row, int col, String cardCode,String doctype) {
		// TODO Auto-generated method stub
	     if(getcolumnheader(col).equals("包装数量")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{		    		               
            Itcode = dataSet[row][this.getcolumnindex("物料代码")].toString();
            if(dataSet[row][this.getcolumnindex("销售单位")]==null)
            {
            	return;
            }
            colidx=this.getcolumnindex("销售单位");
            dw = dataSet[row][colidx].toString();
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("包装数量")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("包装数量")].toString()+"包装数量输入不合法");               
                 return;
             }
            if (Double.valueOf(gs.toString().substring(gs.toString().indexOf(".")+1))>Double.valueOf("0.0001")&&dw.equals("盘"))
            {
        	  JOptionPane.showMessageDialog(null, gs.toString()+"计量单位为盘，包装数量只能为整数，第" + dataSet[row][0].toString() + "行错误.无法保存，如果想继续，请输入整数");               
        	  gs=Double.valueOf(gs.toString().substring(0,gs.toString().indexOf(".")));
        	//return;
            }	
          
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString());
            sl=gs*dwsl;
            zj=gs*Double.valueOf(dataSet[row][this.getcolumnindex("包装单价")].toString());
            if(!new BigDecimal(dataSet[row][this.getcolumnindex("包装数量")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	{
               dwjg = zj/sl;
	    	}
            md=Double.valueOf(dataSet[row][this.getcolumnindex("物料米段")].toString());
            zc=md*gs;
            
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	          
            zc=gs*md;
            if (dw.equals("盘"))
            {              
                zz = dwsl*gs;
            }
            else if (dw.equals("公斤"))
            {
                zz = sl;
            }
            else
            {              
                zz = Double.valueOf("0");
            }  
            if(!new BigDecimal(dataSet[row][this.getcolumnindex("包装数量")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	        {
             dataSet[row][this.getcolumnindex("单价")]=dwjg;
	    	}
            dataSet[row][this.getcolumnindex("数量")]=sl;
            //dataSet[row][this.getcolumnindex("包装数量")]=gs;
            dataSet[row][this.getcolumnindex("总长")]=zc;
            dataSet[row][this.getcolumnindex("总重")]=zz;
            dataSet[row][this.getcolumnindex("金额")]=zj;
            this.fireTableDataChanged();
            return;
		}
	     else if(getcolumnheader(col).equals("数量")&&this.getValuethrheader(row,"销售单位").equals("盘")&&this.getValuethrheader(row,"是否显示米段").equals("Y")&&!this.getValuethrheader(row,"物料代码").toString().substring(0, 2).equals("TD")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{	
	    	 if(!new BigDecimal(dataSet[row][this.getcolumnindex("包装数量")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	 {
	    	  dwjg = Double.valueOf(dataSet[row][this.getcolumnindex("金额")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());	    	 
	    	  dataSet[row][this.getcolumnindex("单价")]=dwjg;	    	 
	    	 //dataSet[row][this.getcolumnindex("总重")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	  dataSet[row][this.getcolumnindex("单位数量")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("包装数量")].toString());   	 
	    	  dataSet[row][this.getcolumnindex("包装单价")]=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString())*dwjg;	
	    
	    	  this.fireTableDataChanged();
	    	 }
	         return;
		}
	    else if(getcolumnheader(col).equals("数量")&&this.getValuethrheader(row,"销售单位").equals("盘")&&(this.getValuethrheader(row,"是否显示米段").equals("N")||this.getValuethrheader(row,"物料代码").toString().substring(0, 2).equals("TD"))&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{		    
	    	 dataSet[row][this.getcolumnindex("金额")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());
	    	 dataSet[row][this.getcolumnindex("总重")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	 if(!new BigDecimal(dataSet[row][this.getcolumnindex("包装数量")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	 {
	    	    dataSet[row][this.getcolumnindex("单位数量")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("包装数量")].toString());   	 
	    	    dataSet[row][this.getcolumnindex("包装单价")]=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());	
	    	 }
	    	 this.fireTableDataChanged();
	         return;
		}
	     else if(getcolumnheader(col).equals("数量")&&!this.getValuethrheader(row,"销售单位").equals("盘")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{	
	    	 //dataSet[row][this.getcolumnindex("包装数量")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	 dataSet[row][this.getcolumnindex("金额")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());
	    	 dataSet[row][this.getcolumnindex("总重")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	 if(!new BigDecimal(dataSet[row][this.getcolumnindex("包装数量")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	 {
	    	    dataSet[row][this.getcolumnindex("单位数量")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("包装数量")].toString());   	 
	    	    dataSet[row][this.getcolumnindex("包装单价")]=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());	
	    	 }
	    	 this.fireTableDataChanged();
	         return;
		}
	  
	     else{}	          
	}

	@Override
	public void itemEndEdit(int row, int col, String cardCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpSportColumn(JTable table, TableColumn sportColumn,
			String hql, String hql1) {
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

	@SuppressWarnings("unused")
	@Override
	public boolean verification() {
		// TODO Auto-generated method stub
		double a,b,c,d,e,g,h,j,k,l;
		String sunit,ifm,orderno,bline;
		for(int i=0;i<getRowCount();i++)
		{
		    if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段").toString().equals("Y")&&!this.getValuethrheader(i,"物料代码").toString().substring(0, 2).equals("TD")))	
		    {
		    	 a= Double.valueOf(dataSet[i][this.getcolumnindex("单价")].toString());
				 b=Double.valueOf(dataSet[i][this.getcolumnindex("数量")].toString());
				 c=Double.valueOf(dataSet[i][this.getcolumnindex("金额")].toString());
				 d=Double.valueOf(dataSet[i][this.getcolumnindex("米价格")].toString());
				 e=Double.valueOf(dataSet[i][this.getcolumnindex("包装单价")].toString());
				 g=Double.valueOf(dataSet[i][this.getcolumnindex("单位数量")].toString());
				 h=Double.valueOf(dataSet[i][this.getcolumnindex("包装数量")].toString());
				 j=Double.valueOf(dataSet[i][this.getcolumnindex("物料米段")].toString());
				 k=Double.valueOf(dataSet[i][this.getcolumnindex("总长")].toString());
				 l=Double.valueOf(dataSet[i][this.getcolumnindex("总重")].toString());
				 sunit=dataSet[i][this.getcolumnindex("销售单位")].toString();
				 ifm=dataSet[i][this.getcolumnindex("是否显示米段")].toString();
				 orderno=dataSet[i][this.getcolumnindex("基本单据")].toString();
				 bline=String.valueOf(Integer.valueOf(dataSet[i][this.getcolumnindex("基本行号")].toString())-1);
				 hql = "select price from rdr1 where docentry='"+orderno+"' and linenum='"+bline+"' ";
	 	         ob = appMain.lt.sqlclob(hql,0,1);
	 	         if(ob==null||ob.length==0)
	 	         {
	 	        	 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行订单单号和行号不正确"); 					 
					 return false; 
	 	         }	 	         
	 	        
				 if(!sunit.equals("盘")||(ifm.equals("N")||this.getValuethrheader(i,"物料代码").toString().substring(0, 2).equals("TD")))
				 {
					 if(Math.abs(Double.valueOf(ob[0][0].toString())-a)>0.001)
					 {
						 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行单位不是盘或者不是米段线单价和订单单价不一致,请检查"); 					 
						 return false; 
					 }
				 }
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
				 /*else if(Math.abs(e/g-a)/a>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行包装单价除以单位数量不等于单价");   	
					 return false;
				 }*/
				 else if(Math.abs(k/h-j)/j>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行总长除以包装数量不等于米段");   	
					 return false;
				 }
				 /*else if(Math.abs(l/h-g)/g>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行总重除以包装数量不等于单位数量");   	
					 return false;
				 }*/
				 else{}
		    }
		
		}
		return true;
	}

}
