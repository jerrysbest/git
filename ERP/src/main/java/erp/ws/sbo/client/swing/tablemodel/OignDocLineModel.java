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
	 * @��׼model
	 */
	    
   /**
    * @ʵ�ָ���ĳ�����
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
    		if(ifsn.equals("N")&&(col==this.getcolumnindex("ʵ���ջ�����")||col==this.getcolumnindex("ʵ�ʿ������")))
	    	{  		
	    		return true;
	    	} 
    		//��ʱ������Ϊ�ɱ༭��������ת��û����
    		else if(ifsn.equals("Y")&&(col==this.getcolumnindex("ʵ���ջ�����")||col==this.getcolumnindex("ʵ�ʿ������"))){
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    	else if(ds==docLineStatus.oige)
    	{
    		if(col==this.getcolumnindex("ʵ�ʿ������"))
	    	{  		
	    		return true;
	    	}   
    		else{
    			return false;
    		}
    	}
    	else if(ds==docLineStatus.tran)
    	{
    		if(col==this.getcolumnindex("���ϴ���")||col==this.getcolumnindex("�ֿ�")||col==this.getcolumnindex("�׶�")||col==this.getcolumnindex("�Ƿ��׶���")
	    	 ||col==this.getcolumnindex("ʵ���ջ�����")||col==this.getcolumnindex("��λ����")||col==this.getcolumnindex("ʵ�ʿ������"))
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
		//�����׶�
		if(getcolumnheader(col).equals("�׶�")&&this.getValuethrheader(row,"ʵ���ջ�����")!=null&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").equals(""))
		{
			  Itcode=this.getValuethrheader(row,"���ϴ���").toString();
			  md = Double.valueOf(dataSet[row][col].toString());
			  dw=dataSet[row][this.getcolumnindex("������λ")].toString();
			  hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
		         		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
		      ob=appMain.lt.clob(hql,0,1);
		      if (ob==null||ob.length == 0)
	          return;
              wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));
              sl = Double.valueOf(this.getValuethrheader(row,"ʵ���ջ�����").toString());                      
              dataSet[row][this.getcolumnindex("��λ����")]=wgtpm*md;           
              dataSet[row][this.getcolumnindex("��׼�������")]=sl*wgtpm*md;    
              //dataSet[row][this.getcolumnindex("ʵ�ʿ������")]=sl*wgtpm*md;    
              this.fireTableDataChanged();  
              return;                                        
		}
		else if (getcolumnheader(col).equals("���ϴ���")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").equals(""))
        {			
			itemEndEdit(row,this.getcolumnindex("���ϴ���"),"");
        }
		else if(getcolumnheader(col).equals("ʵ���ջ�����")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").equals(""))
		{					                
            Itcode = dataSet[row][this.getcolumnindex("���ϴ���")].toString();  
            dw=dataSet[row][this.getcolumnindex("������λ")].toString();
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("ʵ���ջ�����")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("ʵ���ջ�����")].toString()+"ʵ���ջ��������벻�Ϸ�");               
                 return;
             }
            if (Double.valueOf(gs.toString().substring(gs.toString().indexOf(".")+1))>Double.valueOf("0.0001"))
            {
            	JOptionPane.showMessageDialog(null, "ʵ���ջ�����ֻ��Ϊ��������" + dataSet[row][0].toString() + "�д���.�޷����棬��������������������");               
            	 gs=Double.valueOf(gs.toString().substring(0,gs.toString().indexOf(".")));
            
            }
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString());
            md=Double.valueOf(dataSet[row][this.getcolumnindex("�׶�")].toString());
            
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	
                            
                      
            zz = dwsl*gs;
            
           
            dataSet[row][this.getcolumnindex("ʵ���ջ�����")]=gs;
            if(dataSet[row][this.getcolumnindex("�Ƿ��׶���")].toString().equals("Y")||dataSet[row][this.getcolumnindex("�Ƿ��׶���")].toString().equals("��"))
            {
	           
	            dataSet[row][this.getcolumnindex("��׼�������")]=zz;
	            //dataSet[row][this.getcolumnindex("ʵ�ʿ������")]=zz;
            }
            try{
              this.fireTableDataChanged();
            }
            catch(ClassCastException e0)
            {
            	
            }
            return;
		}
		else if(getcolumnheader(col).equals("��λ����")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").equals(""))
		{			               
            Itcode = dataSet[row][this.getcolumnindex("���ϴ���")].toString();        
            dw=dataSet[row][this.getcolumnindex("������λ")].toString();
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString());          
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("ʵ���ջ�����")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("ʵ���ջ�����")].toString()+"ʵ���ջ��������벻�Ϸ�");               
                 return;
             }
            //������ܳ�
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
           
            dataSet[row][this.getcolumnindex("�׶�")]=md;           
            if(dataSet[row][this.getcolumnindex("�Ƿ��׶���")].toString().equals("Y")||dataSet[row][this.getcolumnindex("�Ƿ��׶���")].toString().equals("��"))
            {	           
	            dataSet[row][this.getcolumnindex("��׼�������")]=zz;
	            //dataSet[row][this.getcolumnindex("ʵ�ʿ������")]=zz;
            }
            this.fireTableDataChanged();
            return;
		}	
		else if(getcolumnheader(col).equals("ʵ�ʿ������")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").equals(""))
		{			                 
            if(new BigDecimal(dataSet[row][this.getcolumnindex("��׼�������")].toString()).compareTo(new BigDecimal("0"))==0)
            {
            	dataSet[row][this.getcolumnindex("���")]=0;
            }
            else{
			    dataSet[row][this.getcolumnindex("���")]=(Double.valueOf((dataSet[row][this.getcolumnindex("ʵ�ʿ������")]==null||(dataSet[row][this.getcolumnindex("ʵ�ʿ������")]!=null&&dataSet[row][this.getcolumnindex("ʵ�ʿ������")].toString().equals("")))
			    		?"0":dataSet[row][this.getcolumnindex("ʵ�ʿ������")].toString())- 
			    		Double.valueOf((dataSet[row][this.getcolumnindex("��׼�������")]==null||(dataSet[row][this.getcolumnindex("��׼�������")]!=null&&dataSet[row][this.getcolumnindex("��׼�������")].toString().equals("")))
					    		?"0":dataSet[row][this.getcolumnindex("��׼�������")].toString()))/
			    		Double.valueOf((dataSet[row][this.getcolumnindex("��׼�������")]==null||(dataSet[row][this.getcolumnindex("��׼�������")]!=null&&dataSet[row][this.getcolumnindex("��׼�������")].toString().equals("")))
					    		?"0":dataSet[row][this.getcolumnindex("��׼�������")].toString());         
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
         dataSet[row][this.getcolumnindex("������λ")]=dw;
         dataSet[row][this.getcolumnindex("��������")]=Itname;   
         dataSet[row][this.getcolumnindex("��λ����")]=dwsl;
         ComboBoxItem  Cbi=new ComboBoxItem("N","��");
         dataSet[row][this.getcolumnindex("�Ƿ��׶���")]=Cbi;
         dataSet[row][this.getcolumnindex("�׶�")]=md;
         dataSet[row][this.getcolumnindex("�ֿ�")]=ck;
         dataSet[row][this.getcolumnindex("��浥λ")]=kcdw;
         

         if (this.getValuethrheader(row, "ʵ���ջ�����") == null || this.getValuethrheader(row, "ʵ���ջ�����") == ""||new BigDecimal(this.getValuethrheader(row, "ʵ���ջ�����").toString())==new BigDecimal("0"))
         {       	
        	 this.setValuethrheader(Double.valueOf(1.00), row, "ʵ���ջ�����");       	     	
         }
                
         sl = Double.valueOf(this.getValuethrheader(row, "ʵ���ջ�����").toString());
         
         hql = "SELECT UMtmd,numInSale FROM Oitm WHERE itemCode='" + Itcode + "'";
         ob = appMain.lt.clob(hql,0,1);
         if(ob==null||ob.length==0)
         {
    	     return;
         }
         if (new BigDecimal(ob[0][0].toString()) == new BigDecimal("0") || new BigDecimal(ob[0][1].toString()) == new BigDecimal("0"))
         {
        	 JOptionPane.showMessageDialog(null,"�������׶λ�������Ϊ0,�޷�����");
             return;
         }
                   
        zz = Double.valueOf(ob[0][1].toString())*(sl);                    
        

        dataSet[row][this.getcolumnindex("��׼�������")]=zz;
        //dataSet[row][this.getcolumnindex("ʵ�ʿ������")]=zz;
        dataSet[row][this.getcolumnindex("���")]=0;

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