package erp.ws.sbo.client.swing.tablemodel;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColOignDocLine;
import erp.ws.sbo.client.swing.model.OignDocLine;
import erp.ws.sbo.client.swing.util.general.ComboBoxCellEditor;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.utils.DbUtils;


public  class OignDocLineModel extends AbstractDocLineModel<ColOignDocLine,OignDocLine>{
		
/**
	 * 
	 */
	private static final long serialVersionUID = -103667004279646161L;
/**
	 * 
	 */
public OignDocLineModel(ColOignDocLine colob, OignDocLine ob, DbUtils<ColOignDocLine, OignDocLine> dbu,
		String SQLQuery, Boolean b) {
	 super(colob,ob,dbu,SQLQuery,b);
	// TODO Auto-generated constructor stub
	
}
public OignDocLineModel(ColOignDocLine colob, OignDocLine ob, DbUtils<ColOignDocLine, OignDocLine> dbu,
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
    	if(ds==docLineStatus.query||ds==docLineStatus.load||rows==rowStatus.sn)
    	{
    		return false;
    	}
    	else if(ds==docLineStatus.oign)
    	{
    		hql="select u_enable from [@sms] where code='OIGNSN'";
   		    String ifsn=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();
    		if(ifsn.equals("N")&&(col==this.getcolumnindex("实际收货个数")||col==this.getcolumnindex("实际库存数量")))
	    	{  		
	    		return true;
	    	} 
    		//暂时都设置为可编辑，否则库存转储没法用
    		else if(ifsn.equals("Y")&&(col==this.getcolumnindex("实际收货个数")||col==this.getcolumnindex("实际库存数量"))){
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    	else if(ds==docLineStatus.oige)
    	{
    		if(col==this.getcolumnindex("实际库存数量"))
	    	{  		
	    		return true;
	    	}   
    		else{
    			return false;
    		}
    	}
    	else if(ds==docLineStatus.tran)
    	{
    		if(col==this.getcolumnindex("物料代码")||col==this.getcolumnindex("仓库")||col==this.getcolumnindex("米段")||col==this.getcolumnindex("是否米段线")
	    	 ||col==this.getcolumnindex("实际收货个数")||col==this.getcolumnindex("单位数量")||col==this.getcolumnindex("实际库存数量"))
	    	{  		
	    		return true;
	    	}   
    		else{
    			return false;
    		}
    	}   		
    	else
    	{
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
	
	@Override
	public void valueChanged(int row,int col,String itemCode,String doctype) {
		// TODO Auto-generated method stub
		//物料米段
		if(getcolumnheader(col).equals("米段")&&this.getValuethrheader(row,"实际收货个数")!=null&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").equals(""))
		{
			  Itcode=this.getValuethrheader(row,"物料代码").toString();
			  md = Double.valueOf(dataSet[row][col].toString());
			  dw=dataSet[row][this.getcolumnindex("生产单位")].toString();
			  hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
		         		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
		      ob=appMain.lt.clob(hql,0,1);
		      if (ob==null||ob.length == 0)
	          return;
              wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));
              sl = Double.valueOf(this.getValuethrheader(row,"实际收货个数").toString());                      
              dataSet[row][this.getcolumnindex("单位数量")]=wgtpm*md;           
              dataSet[row][this.getcolumnindex("标准库存数量")]=sl*wgtpm*md;    
              //dataSet[row][this.getcolumnindex("实际库存数量")]=sl*wgtpm*md;    
              this.fireTableDataChanged();  
              return;                                        
		}
		else if (getcolumnheader(col).equals("物料代码")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").equals(""))
        {			
			itemEndEdit(row,this.getcolumnindex("物料代码"),"");
        }
		else if(getcolumnheader(col).equals("实际收货个数")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").equals(""))
		{					                
            Itcode = dataSet[row][this.getcolumnindex("物料代码")].toString();  
            dw=dataSet[row][this.getcolumnindex("生产单位")].toString();
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("实际收货个数")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("实际收货个数")].toString()+"实际收货个数输入不合法");               
                 return;
             }
            if (Double.valueOf(gs.toString().substring(gs.toString().indexOf(".")+1))>Double.valueOf("0.0001"))
            {
            	JOptionPane.showMessageDialog(null, "实际收货个数只能为整数，第" + dataSet[row][0].toString() + "行错误.无法保存，如果想继续，请输入整数");               
            	 gs=Double.valueOf(gs.toString().substring(0,gs.toString().indexOf(".")));
            
            }
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString());
            md=Double.valueOf(dataSet[row][this.getcolumnindex("米段")].toString());
            
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	
                            
                      
            zz = dwsl*gs;
            
           
            dataSet[row][this.getcolumnindex("实际收货个数")]=gs;
            if(dataSet[row][this.getcolumnindex("是否米段线")].toString().equals("Y")||dataSet[row][this.getcolumnindex("是否米段线")].toString().equals("是"))
            {
	           
	            dataSet[row][this.getcolumnindex("标准库存数量")]=zz;
	            //dataSet[row][this.getcolumnindex("实际库存数量")]=zz;
            }
            try{
              this.fireTableDataChanged();
            }
            catch(ClassCastException e0)
            {
            	
            }
            return;
		}
		else if(getcolumnheader(col).equals("单位数量")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").equals(""))
		{			               
            Itcode = dataSet[row][this.getcolumnindex("物料代码")].toString();        
            dw=dataSet[row][this.getcolumnindex("生产单位")].toString();
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString());          
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("实际收货个数")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("实际收货个数")].toString()+"实际收货个数输入不合法");               
                 return;
             }
            //计算出总长
            hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	         		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
		    ob=appMain.lt.clob(hql,0,1);
		    if (ob==null||ob.length == 0)
	        return;
	        wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));     
	        md=dwsl/wgtpm;
            sl=gs*dwsl;
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	                          	  
            	return;
            }	
                                              
            zz = dwsl*sl;            
           
            dataSet[row][this.getcolumnindex("米段")]=md;           
            if(dataSet[row][this.getcolumnindex("是否米段线")].toString().equals("Y")||dataSet[row][this.getcolumnindex("是否米段线")].toString().equals("是"))
            {	           
	            dataSet[row][this.getcolumnindex("标准库存数量")]=zz;
	            //dataSet[row][this.getcolumnindex("实际库存数量")]=zz;
            }
            this.fireTableDataChanged();
            return;
		}	
		else if(getcolumnheader(col).equals("实际库存数量")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").equals(""))
		{			                 
            if(new BigDecimal(dataSet[row][this.getcolumnindex("标准库存数量")].toString()).compareTo(new BigDecimal("0"))==0)
            {
            	dataSet[row][this.getcolumnindex("误差")]=0;
            }
            else{
			    dataSet[row][this.getcolumnindex("误差")]=(Double.valueOf((dataSet[row][this.getcolumnindex("实际库存数量")]==null||(dataSet[row][this.getcolumnindex("实际库存数量")]!=null&&dataSet[row][this.getcolumnindex("实际库存数量")].toString().equals("")))
			    		?"0":dataSet[row][this.getcolumnindex("实际库存数量")].toString())- 
			    		Double.valueOf((dataSet[row][this.getcolumnindex("标准库存数量")]==null||(dataSet[row][this.getcolumnindex("标准库存数量")]!=null&&dataSet[row][this.getcolumnindex("标准库存数量")].toString().equals("")))
					    		?"0":dataSet[row][this.getcolumnindex("标准库存数量")].toString()))/
			    		Double.valueOf((dataSet[row][this.getcolumnindex("标准库存数量")]==null||(dataSet[row][this.getcolumnindex("标准库存数量")]!=null&&dataSet[row][this.getcolumnindex("标准库存数量")].toString().equals("")))
					    		?"0":dataSet[row][this.getcolumnindex("标准库存数量")].toString());         
            }
			try{
            	 this.fireTableDataChanged();    
              }
              catch(ClassCastException e0)
              {
              	
              }
           
		}
		else{
			
		}
	}

	@Override
	public void itemEndEdit(int row, int col,String planQty) {
		// TODO Auto-generated method stub          
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
         md=Double.valueOf(ob[0][5].toString())/(Double.valueOf(ob[0][6].toString()));
         dataSet[row][this.getcolumnindex("生产单位")]=dw;
         dataSet[row][this.getcolumnindex("物料描述")]=Itname;   
         dataSet[row][this.getcolumnindex("单位数量")]=dwsl;
         ComboBoxItem  Cbi=new ComboBoxItem("N","否");
         dataSet[row][this.getcolumnindex("是否米段线")]=Cbi;
         dataSet[row][this.getcolumnindex("米段")]=md;
         dataSet[row][this.getcolumnindex("仓库")]=ck;
         dataSet[row][this.getcolumnindex("库存单位")]=kcdw;
         

         if (this.getValuethrheader(row, "实际收货个数") == null || this.getValuethrheader(row, "实际收货个数") == ""||new BigDecimal(this.getValuethrheader(row, "实际收货个数").toString())==new BigDecimal("0"))
         {       	
        	 this.setValuethrheader(Double.valueOf(1.00), row, "实际收货个数");       	     	
         }
                
         sl = Double.valueOf(this.getValuethrheader(row, "实际收货个数").toString());
         
         hql = "SELECT UMtmd,numInSale FROM Oitm WHERE itemCode='" + Itcode + "'";
         ob = appMain.lt.clob(hql,0,1);
         if(ob==null||ob.length==0)
         {
    	     return;
         }
         if (new BigDecimal(ob[0][0].toString()) == new BigDecimal("0") || new BigDecimal(ob[0][1].toString()) == new BigDecimal("0"))
         {
        	 JOptionPane.showMessageDialog(null,"此物料米段或者重量为0,无法继续");
             return;
         }
                   
        zz = Double.valueOf(ob[0][1].toString())*(sl);                    
        

        dataSet[row][this.getcolumnindex("标准库存数量")]=zz;
        //dataSet[row][this.getcolumnindex("实际库存数量")]=zz;
        dataSet[row][this.getcolumnindex("误差")]=0;

        this.fireTableDataChanged();
        return;   
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
