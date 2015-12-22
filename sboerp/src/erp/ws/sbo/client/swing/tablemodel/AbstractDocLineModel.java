package erp.ws.sbo.client.swing.tablemodel;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.utils.DbUtils;

public abstract class AbstractDocLineModel<T1,T2> extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8010040408715142343L;
	
	 protected Object[][] ob=null;
	 protected  JComboBox comboBox;
	//that’s the Variables of edit event  
	 protected String dw,Itcode,hql,Itname, ck,kcdw;
	 protected Double md,dwjg,wgtpm,sl, zc, zz, zj,mjg, gjjg,jzjg,zlbmd,dwsl,gs; 
	 protected Integer colidx;	
	/**
	 * 
	 */
	public T1 colob;
	public T2 ob1;
	public DbUtils<T1,T2> dbu;
	public String   SQLQuery; 
	public Boolean   b; 
	public String[]  colNames;
	public String[] dataTypes;  
    public Object[][]   dataSet; 
    public Object[][]   ndataSet; 
    public  enum  docLineStatus {
    	add(0), addp(1), remend(2), query(3),load(4),oign(5),tran(6),orin(7),corin(8),oige(9),oworadd(10);
    	private int value;
    
    	private docLineStatus(int value){
    		this.value=value;
    	}

		public int getValue() {
			return value;
		}
		public void settValue(int value) {
			this.value=value;
		}
       public String getCnValue()
       {
    	   switch (this.value){
    	   case 0: return "增加";
    	   case 1: return "追加";
    	   case 2: return "修改";
    	   case 3: return "查询";
    	   case 4: return "加载";
    	   case 5: return "生产收货";
    	   case 6: return "库存转储";
    	   case 7: return "无前置单据应收贷向";
    	   case 8: return "换货(加工)";
    	   case 9: return "生产发货";
    	   case 10: return "生产订单";
    	   default: return null;
    	   }
       }   	
    
    };
    public  enum  rowStatus {
    	sn(0), nsn(1);
    	private int value;
    
    	private rowStatus(int value){
    		this.value=value;
    	}

		public int getValue() {
			return value;
		}
		public void settValue(int value) {
			this.value=value;
		}
       public String getCnValue()
       {
    	   switch (this.value){
    	   case 0: return "序列号";
    	   case 1: return "非序列号";
    	
    	   default: return null;
    	   }
       }   	
    
    };
 
    public docLineStatus ds;
    public rowStatus rows;
    /*
     * @T1 columnheader
     * @T2 docdetails
     * @DbUtils<T1,T2> the method is to get columnheader and docdetails
     * @SQLQuery to get docdetails through hql query
     * @b 
     * 
     */
	public AbstractDocLineModel(T1 colob,T2 ob1,DbUtils<T1,T2> dbu,String SQLQuery,Boolean b)
	{
		this.ob1=ob1;
		this.dbu=dbu;
		this.SQLQuery=SQLQuery;
		this.b=b;		
		this.colNames =dbu.getColumnNames(colob);
		this.dataTypes = dbu.getDataTypes(ob1); 
		this.dataSet   = dbu.executeQuery(SQLQuery); 
	}
	
	public AbstractDocLineModel(T1 colob,T2 ob1,DbUtils<T1,T2> dbu,String SQLQuery)
	{
		this.ob1=ob1;
		this.dbu=dbu;
		this.SQLQuery=SQLQuery;	
		this.colNames =dbu.getColumnNames(colob);		
		this.dataTypes = dbu.getDataTypes(ob1); 
		this.dataSet   = dbu.executeSQLQuery(SQLQuery); 
	}
	
	public void setGridStatus(docLineStatus ds)
	{
		if(ds.getValue()==0)
		{
			ndataSet=new Object[500][getColumnCount()];
			for(int i=0;i<500;i++)
			{
				ndataSet[i][0]=i+1;
			}
			dataSet=ndataSet;
			this.fireTableDataChanged();  
		}
		else if(ds.getValue()==1)
		{
			ndataSet=new Object[500][getColumnCount()];
			for(int i=0;i<500;i++)
			{
				ndataSet[i][0]=i+1;
			}
			dataSet=ndataSet;
			this.fireTableDataChanged();  
		}
		else if(ds.getValue()==2)
		{
			
		}
		else if(ds.getValue()==3)
		{
			
		}
		else if(ds.getValue()==4)
		{
			ndataSet=new Object[500][getColumnCount()];
			for(int i=0;i<500;i++)
			{
				ndataSet[i][0]=i+1;
			}
			dataSet=ndataSet;
			this.fireTableDataChanged(); 
		}
		else
		{
			 
		}
		
	}
	/*
	 * get the columnindex through columnheader
	 * 
	 */
	public int getcolumnindex(String title)
	{
		for(int i=0;i<colNames.length;i++)
		{
			if(colNames[i].equals(title))
			 return i;
		}
		  return -1;
	}
	
	/*
	 * get the columnheader through columnindex
	 * 
	 */
	public String getcolumnheader(int index)
	{
		
		  return colNames[index];
	}
	/*
	 * get the columnheader through columnindex
	 * 
	 */
	public Object getValuethrheader(int row, String colHeader)
	{
		  int col=getcolumnindex(colHeader);
		  return dataSet[row][col];
	}
	
	/*
	 * get the columnheader through columnindex
	 * 
	 */
	public void setValuethrheader(Object value,int row, String colHeader)
	{
		int col=getcolumnindex(colHeader);
		this.setValueAt(value, row, col);
	}
	/*
	 * update table
	 * 
	 */
	public void updatetable(String hql,int sqltype)
	{   
	
		if(sqltype==1)
		{
			this.dataSet = dbu.executeQuery(hql); 
		}
		else{
		   this.dataSet   = dbu.executeSQLQuery(hql); 
		}	
		  this.fireTableDataChanged();  
		  if(dataSet!=null)
		  {
		  for(int i=0;i<dataSet.length;i++)
			{
				setValueAt(i+1, i, 0);
			}
		  }		
	}
	/*
	 * Filter duplicate records
	 * It can be handled like this:
	 * 1.convert every rowdata as string and devided by ","
	 * 2.add the string to a hashset,and the hashset will filter the same data
	 * 3.last devide every row-string by "," and convert it to object[],copy the data to a new object[][]
	 */
	public Object[][] Filterdrecords(boolean t,String[] s)
	{
		boolean tof=false;
		Object[][] defob;
		Object[] lob=new Object[s.length];
		List<Object[]> list=new ArrayList<Object[]>();
	
	   for(int i=0;i<this.dataSet.length;i++)
	   {
		 /* if(dataSet[i][1]==null)
			  continue;*/
		   lob=new Object[s.length];
		   boolean tof1=false;
		   for(int j=0;j<s.length;j++)
		   {		      
			   lob[j]=this.getValuethrheader(i, s[j]);
			   if(this.getValuethrheader(i, s[j])!=null)
			   {
				   tof1=true;
			   }
		   }
		   if(!tof1)
		   {
			   continue; 
		   }
		   if(t)
		   {
			   for(int j=0;j<list.size();j++)
			   {
				  for(int k=0;k<s.length;k++)
				  {
				   if(list.get(j)[k]!=null&&lob[k]!=null&&!list.get(j)[k].toString().equals(lob[k].toString()))
				   {
					   tof=false;					   
				   }
				   else if(list.get(j)[k]==null&&lob[k]==null)
				   {
					   tof=true;
				   }
				   else
				   {
					   tof=true;
					  
				   }
				  }
				  if(tof)
				 break;
			   }
			   if(!tof)
			   {
				   list.add(lob);
			   }
		   }
		   else
		   {
			   list.add(lob);
		   }
		   
	   }
		 
			  
		 defob=new Object[list.size()][s.length];
		 for(int i=0;i<defob.length;i++)
		 {
			 for(int j=0;j<s.length;j++)
			 {
				 defob[i][j]=list.get(i)[j];
			 }
		 }
		
		return defob;
		
	}
/**
 * @标准model,继承自 AbstractTableModel
 */
	    @Override
	    public int getColumnCount() {
	        return this.colNames.length;
	    }
       @Override
	    public int getRowCount() {
    	   if(dataSet!=null)
    	   {
	        return this.dataSet.length;
    	   }
    	   else
    	   {
    		   return 0;
    	   }
	    }
	  
	    @Override
	    public Object getValueAt(int row, int col) {
	    	//JOptionPane.showMessageDialog(null,col);	 
	    	if(this.dataSet==null||this.dataSet.length==0)
	    	{
	    		return "";
	    	}
	    	//System.out.println(dataSet[row][col]);
	    	if(dataSet[row][col]!=null&&dataSet[row][col].toString().length()>=11&&(dataSet[row][col].getClass().equals(java.sql.Timestamp.class)||dataSet[row][col].getClass().equals(java.sql.Date.class)))
	    	{
	    		return dataSet[row][col].toString().substring(0, 10);
	    	}
	    	else{
	            return dataSet[row][col];
	    	}	    	
	    }
	    
	    /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
        	if(this.getcolumnheader(col).equals("收款金额"))
        	{
        		try{
        		   
	        		if(Double.valueOf(this.getValuethrheader(row, "应收余额").toString())*Double.valueOf(value.toString())<Double.valueOf(0))
	        		{
	        			JOptionPane.showMessageDialog(null, "收款金额与应收异号,重新输入");
	        			dataSet[row][col]=0;
	        			return;       					
	        		}	 	        		
					
        		}
        		catch(NullPointerException e1)
        		{
        			System.out.println("null值或者空值，无法转换");
        			 dataSet[row][this.getcolumnindex("收款金额")]=0;
        			return;    
        		}
	        		 		        				 
				
			
        	}
        	if(this.getcolumnheader(col)=="伙伴代码"||this.getcolumnheader(col)=="物料代码"||this.getcolumnheader(col)=="仓库代码"||this.getcolumnheader(col)=="物料大类"||this.getcolumnheader(col)=="科目代码")
        	{
        		dataSet[row][col]=value.toString().split(",")[0];
        	}
        	else{
        	dataSet[row][col]=value;
        	}
        	 try{
        		 fireTableCellUpdated(row, col);     
               }
               catch(ClassCastException e0)
               {
               	
               }
             
        }
        
	    public String getColumnName(int col) {
	        return this.colNames[col];
	    }
	    
	   
		public Class<?> getColumnClass(int c) {	    	    		
	    	if(getValueAt(0, c)==null)
	    		{
	    			return java.lang.String.class;
	    		}
	    		else{
	              return getValueAt(0, c).getClass();
	    		}	    	
	    }	
	    public void insertRow(int row,Object[] ob) {  	       
	    	//dataSet.addElement(row);     	
	    	ndataSet=new Object[getRowCount()+1][getColumnCount()];
	    	for(int i=0;i<dataSet.length;i++)
	    	{
	    		if(i<row)
	    		{
	    			for(int j=0;j<getColumnCount();j++)
		    		{
		    			ndataSet[i][j]=dataSet[i][j];
		    		}
	    		}
	    		if(i==row)
	    		{
	    			for(int j=0;j<getColumnCount();j++)
		    		{
		    			ndataSet[i][j]=ob[j];
		    			ndataSet[i+1][j]=dataSet[i][j];
		    		}
	    		}
	    		if(i>row)
	    		{
	    			for(int j=0;j<getColumnCount();j++)
		    		{
		    			ndataSet[i+1][j]=dataSet[i][j];
		    		}
	    		}
	    		
	    	}
	    	for(int i=0;i<ndataSet.length;i++)
	    	{
	    		ndataSet[i][0]=i+1;
	    	}
	    	dataSet=ndataSet;
	    		    	
	        this.fireTableDataChanged();  
	       // this.fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);  	         
	    }  	          
        public void delRow(int[] rows) {            
        	boolean tf=false;
        	ndataSet=new Object[getRowCount()-rows.length][getColumnCount()];
        	int j1=0;
        	for(int i=0;i<dataSet.length;i++)
	    	{	
        		tf=false;
	    		for(int k=0;k<rows.length;k++)
	        	{
	    			if(rows[k]==i)
	    			{
	    				tf=true;
	    				break;
	    			}
	        	}
	    		if(tf)
        		{	        		         		
	        		continue;  	    			
	        	}
	    		for(int l=0;l<getColumnCount();l++)
     		   {
     		     ndataSet[j1][l]=dataSet[i][l];
     		   }
	    		j1++;
	    	}
	        for(int k=0;k<ndataSet.length;k++)
	        {
	        	 ndataSet[k][0]=k+1;
	        }
        	
        	dataSet=ndataSet;
        this.fireTableDataChanged();           
        } 
     
        public void copyRow(int row,Object[] ob) {  	       
	    	//dataSet.addElement(row);     	
	    	ndataSet=new Object[getRowCount()+1][getColumnCount()];
	    	for(int i=0;i<dataSet.length;i++)
	    	{
	    		if(i<row)
	    		{
	    			for(int j=0;j<getColumnCount();j++)
		    		{
		    			ndataSet[i][j]=dataSet[i][j];
		    		}
	    		}
	    		if(i==row)
	    		{
	    			for(int j=0;j<getColumnCount();j++)
		    		{
	    				if(i>0)
	    				{
		    			 ndataSet[i][j]=dataSet[i-1][j];
	    				}
		    			ndataSet[i+1][j]=dataSet[i][j];
		    		}
	    		}
	    		if(i>row)
	    		{
	    			for(int j=0;j<getColumnCount();j++)
		    		{
		    			ndataSet[i+1][j]=dataSet[i][j];
		    		}
	    		}
	    		
	    	}
	    	for(int i=0;i<ndataSet.length;i++)
	    	{
	    		ndataSet[i][0]=i+1;
	    	}
	    	dataSet=ndataSet;
	    		    	
	        this.fireTableDataChanged();  
	       // this.fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);  	         
	    }  
        public T1 getColob() {
    		return colob;
    	}

    	public void setColob(T1 colob) {
    		this.colob = colob;
    	}
    	public T2 getOb() {
    		return ob1;
    	}

    	public void setOb(T2 ob) {
    		this.ob1 = ob;
    	}

    	public DbUtils<T1,T2> getDbu() {
    		return  dbu;
    	}

    	public void setDbu(DbUtils<T1,T2> dbu) {
    		this.dbu = dbu;
    	}

    	public String getSQLQuery() {
    		return SQLQuery;
    	}

    	public void setSQLQuery(String sQLQuery) {
    		SQLQuery = sQLQuery;
    	}

    	public Boolean getB() {
    		return b;
    	}

    	public void setB(Boolean b) {
    		this.b = b;
    	}


    	public Object[] getColNames() {
    		return colNames;
    	}

    	public void setColNames(String[] colNames) {
    		this.colNames = colNames;
    	}

    	public String[] getDataTypes() {
    		return dataTypes;
    	}

    	public void setDataTypes(String[] dataTypes) {
    		this.dataTypes = dataTypes;
    	}

    	public Object[][] getDataSet() {
    		return dataSet;
    	}

    	public void setDataSet(Object[][] dataSet) {
    		this.dataSet = dataSet;
    	}
    	
    	public void setDocLineStatus(docLineStatus ds)
    	{
    		this.ds=ds;
    	}
    	public void setrowStatus(rowStatus rows)
    	{
    		this.rows=rows;
    	}
        public  void itemEndEdit_corin(int row,int col,String cardCode,String doctype)
    	{
        	if(doctype.equals("2"))
        	{
	        	Itcode=getValueAt(row,col).toString();       
	            if(Itcode==null||Itcode.length()==0)
	            {
	           	 return;
	            }
	            hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	            		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
	            ob=appMain.lt.clob(hql,0,1);
	            if (ob==null||ob.length == 0)         
	            return;
	            dw = ob[0][0].toString();
	            ck = ob[0][1].toString();
	            kcdw=ob[0][2].toString();
	            dwsl=Double.valueOf("1");
	            Itname=ob[0][4].toString();
	            zlbmd=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));
	            md=Double.valueOf(ob[0][5].toString())/(Double.valueOf(ob[0][6].toString()));
	            
	            hql="select a.itemCode,isnull(a.price,0) from Itm1 a,Oitm b,Ospp c "+
	   			 "where a.itemCode=b.itemCode and "+
	   			" c.itemCode=b.itemCode and a.priceList=c.listNum "+  
	   			" and c.cardCode='01ZCJG' and b.itemCode='"+Itcode+"'";
	            ob=appMain.lt.sqlclob(hql,0,1);
	               
	            if (ob==null||ob.length == 0)
	            return;
	            jzjg = Double.valueOf(appMain.lt.sqlclob(hql,0,1)[0][1].toString());
	                             
	            gs = Double.valueOf(1.00);    
	            
	            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
	            ob = appMain.lt.clob(hql,0,1);
	            if(ob==null||ob.length==0)
	            {
	       	   return;
	            }
	            if (new BigDecimal(ob[0][0].toString()) == new BigDecimal("0") || new BigDecimal(ob[0][1].toString()) == new BigDecimal("0"))
	            {
	           	 JOptionPane.showMessageDialog(null,"此物料物料物料米段或者物料重量为0,辅助数量，辅助价格无法生成");
	                return;
	            }
	          	           
	           zc=md*gs;  
	           ComboBoxItem  Cbi=new ComboBoxItem("N","否");
		       dataSet[row][this.getcolumnindex("是否显示米段")]=Cbi;
	           dataSet[row][this.getcolumnindex("物料描述")]=Itname; 
	           dataSet[row][this.getcolumnindex("销售单位")]=dw;
	           dataSet[row][this.getcolumnindex("单位数量")]=dwsl;
	          // dataSet[row][this.getcolumnindex("数量")]=sl; 
	           dataSet[row][this.getcolumnindex("包装数量")]=gs;
	           dataSet[row][this.getcolumnindex("物料米段")]=md;
	          // dataSet[row][this.getcolumnindex("仓库代码")]=ck;
	           //dataSet[row][this.getcolumnindex("库存单位")]=kcdw;
	           //dataSet[row][this.getcolumnindex("米价格")]=mjg;
	           //dataSet[row][this.getcolumnindex("单价")]=dwjg;
	          // dataSet[row][this.getcolumnindex("包装单价")]=gjjg;
	           //dataSet[row][this.getcolumnindex("金额")]=zj;
	           dataSet[row][this.getcolumnindex("总长")]=zc;
	           //dataSet[row][this.getcolumnindex("总重")]=zz;
	          
	           dataSet[row][this.getcolumnindex("特殊价格")]=jzjg;
	          // fireTableCellUpdated(row, col); 
	           this.fireTableDataChanged();
	           return;    
           }
    	}
	 /*
	  * @抽象方法
	  * 
	  */
	    public abstract boolean isCellEditable(int row,int col); 
	    public abstract double[]  sum(int[] col); 
	    public abstract double[]  sum(int[] col,String title,String cardcode); 
	    public abstract void valueChanged(int row,int col,String cardCode,String doctype); 
	    public abstract void itemEndEdit(int row,int col,String cardCode); 	   
	    public abstract boolean verification();
	    /*
	     * @table the table which should be deal with
	     * @tablecolumn the column index which should be deal with 
	     * @hql which can query data from database
	     * @hql1 which will be used to validating 
	     */
	    public abstract void setUpSportColumn(JTable table,TableColumn sportColumn,String hql,String hql1);
	    /*
	     * this function is used to 
	     * @table the table which should be deal with
	     * @tablecolumn the column index which should be deal with 
	     * 
	     */
	    public abstract void setUpStaSportColumn(JTable table,TableColumn sportColumn,List<Object[]> items);
	    
	    
}
 

