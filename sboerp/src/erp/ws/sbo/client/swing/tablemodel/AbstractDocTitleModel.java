package erp.ws.sbo.client.swing.tablemodel;


import javax.swing.table.AbstractTableModel;

import erp.ws.sbo.utils.DbUtils;

public abstract class AbstractDocTitleModel<T1,T2>  extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8010040408715142343L;
	/**
	 * 
	 */
	public T1 colob;
	public T2 ob;
	public DbUtils<T1,T2> dbu;
	public String   SQLQuery; 
	public Boolean   b; 
	public String[]  colNames;
	public String[] dataTypes;  
    public Object[][]   dataSet; 
  
    public  enum  docTitleStatus {
    	add(0), addp(1), remend(2), query(3),load(4);
    	private int value;   
    	private docTitleStatus(int value){
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
    	   default: return null;
    	   }
       }   	
    
    };
 
    public docTitleStatus ds;
    /*
     * @T1 columnheader
     * @T2 docdetails
     * @T3 the view which is to use
     * @DbUtils<T1,T2> the method is to get columnheader and docdetails
     * @SQLQuery to get docdetails through hql query
     * @b 
     * 
     */
	public AbstractDocTitleModel(T1 colob,T2 ob,DbUtils<T1,T2> dbu,String SQLQuery,Boolean b)
	{
		this.ob=ob;
		this.dbu=dbu;
		this.SQLQuery=SQLQuery;
		this.b=b;		
		this.colNames =dbu.getColumnNames(colob);
		this.dataTypes = dbu.getDataTypes(ob); 		
		this.dataSet = dbu.executeQuery(SQLQuery); 		
	}
	public AbstractDocTitleModel(T1 colob,T2 ob,DbUtils<T1,T2> dbu,String SQLQuery)
	{
		this.ob=ob;
		this.dbu=dbu;
		this.SQLQuery=SQLQuery;	
		this.colNames =dbu.getColumnNames(colob);
		this.dataTypes = dbu.getDataTypes(ob); 		
		this.dataSet = dbu.executeSQLQuery(SQLQuery); 		
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
	public T1 getColob() {
		return colob;
	}

	public void setColob(T1 colob) {
		this.colob = colob;
	}
	public T2 getOb() {
		return ob;
	}

	public void setOb(T2 ob) {
		this.ob = ob;
	}

	public DbUtils<T1,T2> getDbu() {
		return (DbUtils<T1,T2>) dbu;
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

	public void setDs(docTitleStatus ds) {
		this.ds = ds;
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
    	   if(this.dataSet!=null)
    	   {
	        return this.dataSet.length;
    	   }
    	   return 0;
	    }
	  
	    @Override
	    public Object getValueAt(int row, int col) {
	    	//JOptionPane.showMessageDialog(null,col);	
	    	
	    	if(this.dataSet==null||this.dataSet.length==0)
	    	{
	    		return "";
	    	}
	    	try{
		    	if(dataSet[row][col]!=null&&dataSet[row][col].toString().length()>=10&&(dataSet[row][col].getClass().equals(java.sql.Timestamp.class)||dataSet[row][col].getClass().equals(java.sql.Date.class)))
		    	{
		    		return dataSet[row][col].toString().substring(0, 10);
		    	}
		    	else{
		            return dataSet[row][col];
		    	}	 
	    	}
	    	catch(ArrayIndexOutOfBoundsException e0){
	    		//JOptionPane.showMessageDialog(null,"absdoctitlemodel-getvalueat:请选择销售员");	    		
				return null;
	    	}
	    	   	
	    }
	    
	    /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
                   	
        	dataSet[row][col]=value;         
            fireTableCellUpdated(row, col);    
        }

        @Override
    	public boolean isCellEditable(int row, int col) {
    		// TODO Auto-generated method stub
    		return false;
    	}
	    public String getColumnName(int col) {
	        return this.colNames[col];
	    }
	    
	    public Class<?> getColumnClass(int c) {	    
	    		
	    	if(getValueAt(0, c)==null||getValueAt(0, c).getClass().equals(java.sql.Timestamp.class))
	    		{
	    		
	    			return java.lang.String.class;
	    		}
	    		else{
	              return getValueAt(0, c).getClass();
	    		}
	    	
	    }
	/*
	 * 
	 * abstract class
	 */
	    public abstract void setDocTitleStatus(Object V);
		
	  
}