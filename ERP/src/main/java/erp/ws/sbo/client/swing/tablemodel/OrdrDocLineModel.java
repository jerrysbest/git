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
import erp.ws.sbo.client.swing.model.Rdr1Id;
import erp.ws.sbo.client.swing.util.general.ComboBoxCellEditor;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.utils.DbUtils;


public  class OrdrDocLineModel extends AbstractDocLineModel<ColDocLine,DocLine<Rdr1Id>>{
		
/**
	 * 
	 */
	private static final long serialVersionUID = -103667004279646161L;
/**
	 * 
	 */
public OrdrDocLineModel(ColDocLine colob, DocLine<Rdr1Id> ob, DbUtils<ColDocLine, DocLine<Rdr1Id>> dbu,
		String SQLQuery, Boolean b) {
	 super(colob,ob,dbu,SQLQuery,b);
	// TODO Auto-generated constructor stub
	
}
public OrdrDocLineModel(ColDocLine colob, DocLine<Rdr1Id> ob, DbUtils<ColDocLine, DocLine<Rdr1Id>> dbu,
			String SQLQuery, Boolean b,String cc) {
		super(colob, ob, dbu, SQLQuery, b);
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
    	if(col==this.getcolumnindex("物料代码")||col==this.getcolumnindex("是否显示米段")||
    			col==this.getcolumnindex("仓库代码")||col==this.getcolumnindex("包装数量")||
    			col==this.getcolumnindex("单价")||col==this.getcolumnindex("是否换货")||
    			col==this.getcolumnindex("加工费")||col==this.getcolumnindex("到货日期")||
    			col==this.getcolumnindex("备注"))
    	{   		
    		return true;
    	}
    	else if(col==this.getcolumnindex("物料米段"))
    	{
    		if(this.getValuethrheader(row,"销售单位")!=null&&(this.getValuethrheader(row,"销售单位").toString().equals("盘")||this.getValuethrheader(row,"物料代码").toString().substring(0, 2).equals("TD")))
    		{
    		    return true;
    		}
    		else 
    		{
    			return false;
    		}
    	}
    	else if(col==this.getcolumnindex("米价格")||col==this.getcolumnindex("包装单价"))
    	{
    		if(this.getValuethrheader(row,"销售单位")!=null&&(this.getValuethrheader(row,"销售单位").toString().equals("盘")||this.getValuethrheader(row,"销售单位").toString().equals("公斤")))
    		{
    		    return true;
    		}
    		else 
    		{
    			return false;
    		}
    	}
    	else if(col==this.getcolumnindex("单位数量"))
    	{
    		if(this.getValuethrheader(row,"销售单位")!=null&&(this.getValuethrheader(row,"销售单位").toString().equals("盘")||this.getValuethrheader(row,"销售单位").toString().equals("公斤")))
    		{
    		    return true;
    		}
    		else 
    		{
    			return false;
    		}
    	}
    	else if(col==this.getcolumnindex("数量"))
    	{
    		if(this.getValuethrheader(row,"销售单位")!=null&&(!this.getValuethrheader(row,"销售单位").toString().equals("盘"))&&(!this.getValuethrheader(row,"销售单位").toString().equals("公斤")))
    		{
    		    return true;
    		}
    		else 
    		{
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
	public void valueChanged(int row,int col,String cardCode,String doctype) {
		// TODO Auto-generated method stub
		if((cardCode==null||cardCode.equals(""))&&(ds==docLineStatus.add||ds==docLineStatus.addp))
		{			
			JOptionPane.showMessageDialog(null, "业务伙伴不能为空");
			return;
		}		 
		//物料米段
		if(getcolumnheader(col).equals("物料米段")&&this.getValuethrheader(row,"数量")!=null&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{
			 
             /* NumberFormat nf = NumberFormat.getNumberInstance();
              nf.setGroupingUsed(false);     // don't group by threes
              nf.setMaximumFractionDigits(2);
              nf.setMinimumFractionDigits(2); 
              p = dataSet[row][1].toString();*/
			  Itcode=this.getValuethrheader(row,"物料代码").toString();
			  md = Double.valueOf(dataSet[row][col].toString());
			  dw=dataSet[row][this.getcolumnindex("销售单位")].toString();
			  hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
		         		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
		      ob=appMain.lt.clob(hql,0,1);
		      if (ob==null||ob.length == 0)
	          return;
              wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));              
              try{
            	  gs = Double.valueOf(this.getValuethrheader(row,"包装数量").toString()); 
               }
               catch(NumberFormatException e0)
               {
               	   JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("包装数量")].toString()+"包装数量输入不合法");               
                   return;
               }
              dwjg=Double.valueOf(this.getValuethrheader(row,"单价").toString());  
              dataSet[row][this.getcolumnindex("单位数量")]=wgtpm*md;   
              dataSet[row][this.getcolumnindex("包装单价")]=Double.valueOf(dataSet[row][this.getcolumnindex("包装单价")].toString())*wgtpm*md;   
              dataSet[row][this.getcolumnindex("总长")]=gs*md;           
              dataSet[row][this.getcolumnindex("总重")]=gs*wgtpm*md;          
              dataSet[row][this.getcolumnindex("数量")]=gs*wgtpm*md;               
              dataSet[row][this.getcolumnindex("金额")]=gs*wgtpm*md*dwjg;  
              this.fireTableDataChanged();  
              return;                                        
		}
		else if (getcolumnheader(col).equals("物料代码")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
        {
			 itemEndEdit(row,this.getcolumnindex("物料代码"),cardCode);
        }
		else if(getcolumnheader(col).equals("包装数量")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{			
			colidx=this.getcolumnindex("物料代码");                  
            Itcode = dataSet[row][colidx].toString();
            colidx=this.getcolumnindex("销售单位");  
            if(dataSet[row][colidx]==null)
            {
            	//dataSet[row][colidx]=Double.valueOf(0.000);
            	return;
            }
            dw = dataSet[row][colidx].toString();
            colidx=this.getcolumnindex("包装数量");  
           
            if(dataSet[row][colidx]==null)
            {
            	dataSet[row][colidx]=Double.valueOf(0.000);
            	//return;
            }
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
            	gs= Double.valueOf(gs.toString().substring(0,gs.toString().indexOf(".")));
            }
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString());
            sl=gs*dwsl;
            zj=gs*Double.valueOf(dataSet[row][this.getcolumnindex("包装单价")].toString());
            dwjg = zj/sl;
         
            md=Double.valueOf(dataSet[row][this.getcolumnindex("物料米段")].toString());
            zc=gs*md;
            
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	
                      
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
           // dataSet[row][this.getcolumnindex("单价")]=dwjg;
            dataSet[row][this.getcolumnindex("数量")]=sl;
            dataSet[row][this.getcolumnindex("总长")]=zc;
            dataSet[row][this.getcolumnindex("总重")]=zz;
            dataSet[row][this.getcolumnindex("金额")]=zj;
           // dataSet[row][this.getcolumnindex("包装数量")]=gs;
            this.fireTableDataChanged();
            return;
		}
		else if(getcolumnheader(col).equals("单位数量")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{			
			colidx=this.getcolumnindex("物料代码");                  
            Itcode = dataSet[row][colidx].toString();
            colidx=this.getcolumnindex("销售单位");  
            if(dataSet[row][colidx]==null)
            {
            	//dataSet[row][colidx]=Double.valueOf(0.000);
            	return;
            }
            dw = dataSet[row][colidx].toString();           
            colidx=this.getcolumnindex("单位数量");  
            if(dataSet[row][colidx]==null)
            {
            	dataSet[row][colidx]=Double.valueOf(0.000);
            	//return;
            }
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("单位数量")].toString());          
            dwjg=Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());  
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("包装数量")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("包装数量")].toString()+"包装数量输入不合法"); 
                return;
             }
            sl=dwsl*gs;
            //计算出总长
            hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	         		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
		      ob=appMain.lt.clob(hql,0,1);
		      if (ob==null||ob.length == 0)
	          return;
	          wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));     
	          md=dwsl/wgtpm;
	          zc=gs*md;
	          //计算出单价
         /*    hql="select a.id.itemCode,a.price from Itm1 a,Oitm b,Ospp c,Ocrd d "+ 
        			" where "+
        		 " a.id.itemCode=b.itemCode and c.id.itemCode=b.UMtdl and a.id.priceList=c.listNum "+ 
                  "and d.UJgqdz=c.id.cardCode "+
        		 " and d.cardCode='" + cardCode + "' and b.itemCode='"+Itcode+"' ";
             ob=appMain.lt.clob(hql,0,1);        
             if(ob.length!=0)
             {	*/                    	
            	
            	 gjjg=dwjg*dwsl; 	
            	 zj=sl*dwjg;
            /* }
             else
             {
            	 JOptionPane.showMessageDialog(null, dataSet[row][0].toString() + "找不到单位价格");   
            	 return;
             }*/
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
            	  
            	return;
            }	
           // if (!IsInt.isInt(sl.toString())&&dw.equals("盘"))            
            if (dw.equals("盘"))
            {              
                zz = sl;
            }
            else if (dw.equals("公斤"))
            {
                zz = sl;
            }
            else
            {             
                zz = Double.valueOf("0");
            }  
            dataSet[row][this.getcolumnindex("物料米段")]=md;
            dataSet[row][this.getcolumnindex("包装单价")]=gjjg;
            dataSet[row][this.getcolumnindex("数量")]=sl;
            dataSet[row][this.getcolumnindex("总长")]=zc;
            dataSet[row][this.getcolumnindex("总重")]=zz;
            dataSet[row][this.getcolumnindex("金额")]=zj;
            this.fireTableDataChanged();
            return;
		}
		else if(getcolumnheader(col).equals("单价")&&this.getValuethrheader(row,"单价")!=null&&this.getValuethrheader(row,"单价").toString()!=""&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{
			 colidx=this.getcolumnindex("单价");  
            if(dataSet[row][colidx]==null)
            {
            	dataSet[row][colidx]=Double.valueOf(0.000);
            	//return;
            }                       
             Itcode = this.getValuethrheader(row,"物料代码").toString();
             dw =this.getValuethrheader(row,"销售单位").toString();
             sl = Double.valueOf(this.getValuethrheader(row,"数量").toString());
             dwsl = Double.valueOf(this.getValuethrheader(row,"单位数量").toString());
             dwjg = Double.valueOf(this.getValuethrheader(row,"单价").toString());
             md=Double.valueOf(this.getValuethrheader(row,"物料米段").toString());
             zj=sl*dwjg;
             hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
             ob = appMain.lt.clob(hql,0,1);
              if(ob==null||ob.length==0)
              {
            	  return;
              }
             zlbmd=Double.valueOf(ob[0][1].toString())/Double.valueOf(ob[0][0].toString());
             if (dw.equals("盘")||dw.equals("公斤"))
             {                           	 	           
	             gjjg = dwjg*dwsl;     
	             mjg=gjjg/md;	            
             }          
             else
             {
                 mjg = Double.valueOf("0");
                 gjjg = dwjg*dwsl; 
             }
            
             dataSet[row][this.getcolumnindex("包装单价")]=gjjg;
             dataSet[row][this.getcolumnindex("米价格")]=mjg;
             dataSet[row][this.getcolumnindex("金额")]=zj;
             this.fireTableDataChanged();    
             return;
		}
		else if(this.getcolumnheader(col).equals("米价格")&&dataSet[row][this.getcolumnindex("米价格")]!=null&&dataSet[row][this.getcolumnindex("米价格")].toString()!=""&&dataSet[row][this.getcolumnindex("物料代码")]!=null&&!dataSet[row][this.getcolumnindex("物料代码")].toString().equals(""))
		{
			 colidx=this.getcolumnindex("米价格");  
             if(dataSet[row][colidx]==null)
             {
            	dataSet[row][colidx]=Double.valueOf(0.000);
            	//return;
             }           
              Itcode = dataSet[row][this.getcolumnindex("物料代码")].toString();
              dw = dataSet[row][this.getcolumnindex("销售单位")].toString();
              sl = Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());             
              mjg = Double.valueOf(dataSet[row][this.getcolumnindex("米价格")].toString());
              md=   Double.valueOf(dataSet[row][this.getcolumnindex("物料米段")].toString());          
              hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
              ob = appMain.lt.clob(hql,0,1);
              if(ob==null||ob.length==0)
              {
            	  return;
              }
              zlbmd=Double.valueOf(ob[0][1].toString())/Double.valueOf(ob[0][0].toString());
              dwjg = mjg/zlbmd; 
              if (dw.equals("盘")||dw.equals("公斤"))
              {
            	  gjjg = mjg*md;           	   
              }          
              else
              {
                 return;
              }            
              zj=sl*dwjg;
              dataSet[row][this.getcolumnindex("单价")]=dwjg;
              dataSet[row][this.getcolumnindex("包装单价")]=gjjg;
              dataSet[row][this.getcolumnindex("金额")]=zj;
              this.fireTableDataChanged();   
              return;            
		}
		else if(this.getcolumnheader(col).equals("包装单价")&&dataSet[row][this.getcolumnindex("包装单价")]!=null&&dataSet[row][this.getcolumnindex("包装单价")].toString()!=""&&dataSet[row][this.getcolumnindex("物料代码")]!=null&&!dataSet[row][this.getcolumnindex("物料代码")].toString().equals(""))
		{
			 colidx=this.getcolumnindex("包装单价");  
             if(dataSet[row][colidx]==null)
             {
            	dataSet[row][colidx]=Double.valueOf(0.000);
            	//return;
             }        
             Itcode = dataSet[row][this.getcolumnindex("物料代码")].toString();
             dw = dataSet[row][this.getcolumnindex("销售单位")].toString();
             sl = Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString()); 
             gjjg = Double.valueOf(dataSet[row][this.getcolumnindex("包装单价")].toString());
             
             hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
             ob = appMain.lt.clob(hql,0,1);
             if(ob==null||ob.length==0)
             {
           	  return;
             }
             if (dw.equals("盘")||dw.equals("公斤"))
             {	           
            	 dwjg = gjjg/(md*zlbmd);
            	 mjg=dwjg*zlbmd;
             }            
                      
             zj = sl*dwjg;
             dataSet[row][this.getcolumnindex("单价")]=dwjg;
             dataSet[row][this.getcolumnindex("米价格")]=mjg;
             dataSet[row][this.getcolumnindex("金额")]=zj;
             this.fireTableDataChanged();      
             return;
		}
		else if(this.getcolumnheader(col).equals("是否显示米段")&&dataSet[row][this.getcolumnindex("物料代码")]!=null&&!dataSet[row][this.getcolumnindex("物料代码")].toString().equals("")&&dataSet[row][this.getcolumnindex("销售单位")].toString().equals("盘"))
		{
			 colidx=this.getcolumnindex("是否显示米段");  	
			 //JOptionPane.showMessageDialog(null, dataSet[row][this.getcolumnindex("物料代码")].toString().substring(0, 2)); 
			 if(dataSet[row][this.getcolumnindex("是否显示米段")]!=null&&(dataSet[row][this.getcolumnindex("是否显示米段")].toString().equals("否")))
			 {
				 dataSet[row][this.getcolumnindex("仓库代码")]="2107";
			 }
			 else if(dataSet[row][this.getcolumnindex("物料代码")].toString().substring(0, 2).equals("TD"))
			 {				
				 dataSet[row][this.getcolumnindex("仓库代码")]="2109";
			 }
			 else
			 {
				 dataSet[row][this.getcolumnindex("仓库代码")]="2108";
			 }
             this.fireTableDataChanged();      
             return;
		}
		else if(this.getcolumnheader(col).equals("是否显示米段")&&dataSet[row][this.getcolumnindex("物料代码")]!=null&&!dataSet[row][this.getcolumnindex("物料代码")].toString().equals("")&&dataSet[row][this.getcolumnindex("销售单位")].toString().equals("公斤")){
			 if(dataSet[row][this.getcolumnindex("物料代码")].toString().substring(0, 2).equals("TD")){				
				 dataSet[row][this.getcolumnindex("仓库代码")]="2109";
			 }
		}
	    else if(getcolumnheader(col).equals("数量")&&this.getValuethrheader(row,"销售单位").equals("盘")&&(this.getValuethrheader(row,"是否显示米段").equals("否")||this.getValuethrheader(row,"物料代码").toString().substring(0, 2).equals("TD"))&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{		    
	    	 dataSet[row][this.getcolumnindex("金额")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());
	    	 dataSet[row][this.getcolumnindex("总重")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	 this.fireTableDataChanged();
	         return;
		}
	     else if(getcolumnheader(col).equals("数量")&&!this.getValuethrheader(row,"销售单位").equals("盘")&&this.getValuethrheader(row,"物料代码")!=null&&!this.getValuethrheader(row,"物料代码").toString().equals(""))
		{	
	    	 //dataSet[row][this.getcolumnindex("包装数量")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	 dataSet[row][this.getcolumnindex("金额")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("单价")].toString());
	    	 dataSet[row][this.getcolumnindex("总重")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString());
	    	 dataSet[row][this.getcolumnindex("单位数量")]=Double.valueOf(dataSet[row][this.getcolumnindex("数量")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("包装数量")].toString());
			 
	         this.fireTableDataChanged();
	         return;
		}
		else
		{
			 return;
		}
		
	}

	@Override
	public void itemEndEdit(int row, int col,String cardCode) {
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
         zlbmd=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));
         md=Double.valueOf(ob[0][5].toString())/(Double.valueOf(ob[0][6].toString()));
         hql="select a.itemCode,isnull(a.price,0) from Itm1 a,Oitm b,Ospp c "+
			 "where a.itemCode=b.itemCode and "+
			" c.itemCode=b.itemCode and a.priceList=c.listNum "+  
			" and c.cardCode='01ZCJG' and b.itemCode='"+Itcode+"'";
         ob=appMain.lt.sqlclob(hql,0,1);
            
         if (ob==null||ob.length == 0){
        	 JOptionPane.showMessageDialog(null,ob+","+"查不到特殊价格");
        	// this.setValuethrheader(null, row, "物料代码");
             return;
         }
         else{
           jzjg = Double.valueOf(appMain.lt.sqlclob(hql,0,1)[0][1].toString());
         }      
         hql="select a.itemCode,isnull(a.price,0)*isnull(d.U_discount,0) from Itm1 a,Oitm b,Ospp c,Ocrd d "+ 
			" where "+
		 " a.itemCode=b.itemCode and c.itemCode=b.itemCode and a.priceList=c.listNum "+ 
          "and d.U_Jgqdz=c.cardCode "+
		 " and d.cardCode='" + cardCode + "' and b.itemCode='"+Itcode+"' ";
         ob=appMain.lt.sqlclob(hql,0,1);        
         if(ob!=null&&ob.length!=0)
         {	
        	 dwjg=Double.valueOf(ob[0][1].toString());	    
        	 gjjg=Double.valueOf(ob[0][1].toString())*(dwsl); 	        	         
	        	         
         }
         else{
        	 JOptionPane.showMessageDialog(null,"找不出价格,"+"业务伙伴"+cardCode+"所属的价格清单组未填写或者不存在");
        	 return;
         }  	
       
         gs = Double.valueOf(1.00); 
         sl = Double.valueOf(1.00);
         
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
         if (dw.equals("盘"))
         {            
             zz =dwsl*(gs);                    
         }
         else if (dw.equals("公斤"))
         {
             zz = sl;
         }
         else
         {          
             zz = Double.valueOf("0");         
         }
      
        zj = sl*dwjg;
        zc=md*gs;  
        mjg=zj/zc;
        dataSet[row][this.getcolumnindex("物料描述")]=Itname; 
        dataSet[row][this.getcolumnindex("销售单位")]=dw;
        dataSet[row][this.getcolumnindex("单位数量")]=dwsl;
        dataSet[row][this.getcolumnindex("包装数量")]=gs;
        dataSet[row][this.getcolumnindex("数量")]=sl; 
        dataSet[row][this.getcolumnindex("物料米段")]=md;
        dataSet[row][this.getcolumnindex("仓库代码")]=ck;
        dataSet[row][this.getcolumnindex("库存单位")]=kcdw;
        dataSet[row][this.getcolumnindex("米价格")]=mjg;
        dataSet[row][this.getcolumnindex("单价")]=dwjg;
        dataSet[row][this.getcolumnindex("包装单价")]=gjjg;
        dataSet[row][this.getcolumnindex("金额")]=zj;
        dataSet[row][this.getcolumnindex("总长")]=zc;
        dataSet[row][this.getcolumnindex("总重")]=zz;
        ComboBoxItem  Cbi=new ComboBoxItem("N","否");
        dataSet[row][this.getcolumnindex("是否换货")]=Cbi;
        Cbi=new ComboBoxItem("Y","是");
        dataSet[row][this.getcolumnindex("是否显示米段")]=Cbi;
        dataSet[row][this.getcolumnindex("特殊价格")]=jzjg;
       // fireTableCellUpdated(row, col); 
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
		double a,b,c,d,e,g,h,j,k,l;
		String ck;
		for(int i=0;i<getRowCount();i++)
		{
		    if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段")!=null&&this.getValuethrheader(i,"是否显示米段").toString().equals("是")&&!this.getValuethrheader(i,"物料代码").toString().substring(0, 2).equals("TD")))	
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
		    else if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段")!=null&&this.getValuethrheader(i,"是否显示米段").toString().equals("否")&&!this.getValuethrheader(i,"物料代码").toString().substring(0, 2).equals("TD")))	
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
				
				 if(Math.abs(e/j-d)/d>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行包装单价除以物料米段不等于米价格");   	
					 return false;
				 }
				 else if(Math.abs(e/g-a)/a>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行包装单价除以单位数量不等于单价");   	
					 return false;
				 }
				
				 else{}
		    }
		    else  if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段").toString().equals("否")))	
		    {
		    	ck=dataSet[i][this.getcolumnindex("仓库代码")].toString();
		    	if(!ck.equals("2107"))
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行仓库不正确"); 					 
					 return false;
				 }
		    }
		    else  if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"销售单位").toString().equals("盘")&&(this.getValuethrheader(i,"是否显示米段")==null||(this.getValuethrheader(i,"是否显示米段")!=null&&this.getValuethrheader(i,"是否显示米段").toString().equals("是")&&!this.getValuethrheader(i,"物料代码").toString().substring(0, 2).equals("TD"))))	
		    {
		    	ck=dataSet[i][this.getcolumnindex("仓库代码")].toString();
		    	if(!ck.equals("2108"))
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行仓库不正确"); 					 
					 return false;
				 }
		    }
		    else if(this.getValuethrheader(i,"物料代码")!=null&&!this.getValuethrheader(i,"物料代码").toString().equals("")&&this.getValuethrheader(i,"物料代码").toString().substring(0, 2).equals("TD"))
		    {
		    	 ck=dataSet[i][this.getcolumnindex("仓库代码")].toString();
		    	 if(!ck.equals("2109"))
				 {
					 JOptionPane.showMessageDialog(null,"第"+String.valueOf(i+1)+"行仓库不正确"); 					 
					 return false;
				 }
		    }
		    else{
		    	
		    }
		
		}
		return true;
	}
	
	
	
	
 
}
