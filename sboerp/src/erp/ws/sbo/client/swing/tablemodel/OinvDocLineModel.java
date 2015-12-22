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
    	if(col==this.getcolumnindex("�ֿ����")||col==this.getcolumnindex("��װ����")||col==this.getcolumnindex("����")||
    	   col==this.getcolumnindex("��������"))
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
	     if(getcolumnheader(col).equals("��װ����")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{		    		               
            Itcode = dataSet[row][this.getcolumnindex("���ϴ���")].toString();
            if(dataSet[row][this.getcolumnindex("���۵�λ")]==null)
            {
            	return;
            }
            colidx=this.getcolumnindex("���۵�λ");
            dw = dataSet[row][colidx].toString();
            try{
                gs = Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString()); 
             }
             catch(NumberFormatException e0)
             {
             	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("��װ����")].toString()+"��װ�������벻�Ϸ�");               
                 return;
             }
            if (Double.valueOf(gs.toString().substring(gs.toString().indexOf(".")+1))>Double.valueOf("0.0001")&&dw.equals("��"))
            {
        	  JOptionPane.showMessageDialog(null, gs.toString()+"������λΪ�̣���װ����ֻ��Ϊ��������" + dataSet[row][0].toString() + "�д���.�޷����棬��������������������");               
        	  gs=Double.valueOf(gs.toString().substring(0,gs.toString().indexOf(".")));
        	//return;
            }	
          
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString());
            sl=gs*dwsl;
            zj=gs*Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());
            if(!new BigDecimal(dataSet[row][this.getcolumnindex("��װ����")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	{
               dwjg = zj/sl;
	    	}
            md=Double.valueOf(dataSet[row][this.getcolumnindex("�����׶�")].toString());
            zc=md*gs;
            
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	          
            zc=gs*md;
            if (dw.equals("��"))
            {              
                zz = dwsl*gs;
            }
            else if (dw.equals("����"))
            {
                zz = sl;
            }
            else
            {              
                zz = Double.valueOf("0");
            }  
            if(!new BigDecimal(dataSet[row][this.getcolumnindex("��װ����")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	        {
             dataSet[row][this.getcolumnindex("����")]=dwjg;
	    	}
            dataSet[row][this.getcolumnindex("����")]=sl;
            //dataSet[row][this.getcolumnindex("��װ����")]=gs;
            dataSet[row][this.getcolumnindex("�ܳ�")]=zc;
            dataSet[row][this.getcolumnindex("����")]=zz;
            dataSet[row][this.getcolumnindex("���")]=zj;
            this.fireTableDataChanged();
            return;
		}
	     else if(getcolumnheader(col).equals("����")&&this.getValuethrheader(row,"���۵�λ").equals("��")&&this.getValuethrheader(row,"�Ƿ���ʾ�׶�").equals("Y")&&!this.getValuethrheader(row,"���ϴ���").toString().substring(0, 2).equals("TD")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{	
	    	 if(!new BigDecimal(dataSet[row][this.getcolumnindex("��װ����")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	 {
	    	  dwjg = Double.valueOf(dataSet[row][this.getcolumnindex("���")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());	    	 
	    	  dataSet[row][this.getcolumnindex("����")]=dwjg;	    	 
	    	 //dataSet[row][this.getcolumnindex("����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	  dataSet[row][this.getcolumnindex("��λ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());   	 
	    	  dataSet[row][this.getcolumnindex("��װ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString())*dwjg;	
	    
	    	  this.fireTableDataChanged();
	    	 }
	         return;
		}
	    else if(getcolumnheader(col).equals("����")&&this.getValuethrheader(row,"���۵�λ").equals("��")&&(this.getValuethrheader(row,"�Ƿ���ʾ�׶�").equals("N")||this.getValuethrheader(row,"���ϴ���").toString().substring(0, 2).equals("TD"))&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{		    
	    	 dataSet[row][this.getcolumnindex("���")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 dataSet[row][this.getcolumnindex("����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 if(!new BigDecimal(dataSet[row][this.getcolumnindex("��װ����")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	 {
	    	    dataSet[row][this.getcolumnindex("��λ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());   	 
	    	    dataSet[row][this.getcolumnindex("��װ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());	
	    	 }
	    	 this.fireTableDataChanged();
	         return;
		}
	     else if(getcolumnheader(col).equals("����")&&!this.getValuethrheader(row,"���۵�λ").equals("��")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{	
	    	 //dataSet[row][this.getcolumnindex("��װ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 dataSet[row][this.getcolumnindex("���")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 dataSet[row][this.getcolumnindex("����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 if(!new BigDecimal(dataSet[row][this.getcolumnindex("��װ����")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	 {
	    	    dataSet[row][this.getcolumnindex("��λ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());   	 
	    	    dataSet[row][this.getcolumnindex("��װ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());	
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
		    if(this.getValuethrheader(i,"���ϴ���")!=null&&!this.getValuethrheader(i,"���ϴ���").toString().equals("")&&this.getValuethrheader(i,"���۵�λ").toString().equals("��")&&(this.getValuethrheader(i,"�Ƿ���ʾ�׶�").toString().equals("Y")&&!this.getValuethrheader(i,"���ϴ���").toString().substring(0, 2).equals("TD")))	
		    {
		    	 a= Double.valueOf(dataSet[i][this.getcolumnindex("����")].toString());
				 b=Double.valueOf(dataSet[i][this.getcolumnindex("����")].toString());
				 c=Double.valueOf(dataSet[i][this.getcolumnindex("���")].toString());
				 d=Double.valueOf(dataSet[i][this.getcolumnindex("�׼۸�")].toString());
				 e=Double.valueOf(dataSet[i][this.getcolumnindex("��װ����")].toString());
				 g=Double.valueOf(dataSet[i][this.getcolumnindex("��λ����")].toString());
				 h=Double.valueOf(dataSet[i][this.getcolumnindex("��װ����")].toString());
				 j=Double.valueOf(dataSet[i][this.getcolumnindex("�����׶�")].toString());
				 k=Double.valueOf(dataSet[i][this.getcolumnindex("�ܳ�")].toString());
				 l=Double.valueOf(dataSet[i][this.getcolumnindex("����")].toString());
				 sunit=dataSet[i][this.getcolumnindex("���۵�λ")].toString();
				 ifm=dataSet[i][this.getcolumnindex("�Ƿ���ʾ�׶�")].toString();
				 orderno=dataSet[i][this.getcolumnindex("��������")].toString();
				 bline=String.valueOf(Integer.valueOf(dataSet[i][this.getcolumnindex("�����к�")].toString())-1);
				 hql = "select price from rdr1 where docentry='"+orderno+"' and linenum='"+bline+"' ";
	 	         ob = appMain.lt.sqlclob(hql,0,1);
	 	         if(ob==null||ob.length==0)
	 	         {
	 	        	 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�ж������ź��кŲ���ȷ"); 					 
					 return false; 
	 	         }	 	         
	 	        
				 if(!sunit.equals("��")||(ifm.equals("N")||this.getValuethrheader(i,"���ϴ���").toString().substring(0, 2).equals("TD")))
				 {
					 if(Math.abs(Double.valueOf(ob[0][0].toString())-a)>0.001)
					 {
						 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�е�λ�����̻��߲����׶��ߵ��ۺͶ������۲�һ��,����"); 					 
						 return false; 
					 }
				 }
				 if(Math.abs(c/a-b)/b>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�н����Ե��۲���������"); 					 
					 return false;
				 }
				 else if(Math.abs(c/k-d)/d>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�н������ܳ��������׼۸�");   	
					 return false;
				 }
				 else if(Math.abs(e/j-d)/d>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�а�װ���۳��������׶β������׼۸�");   	
					 return false;
				 }
				 /*else if(Math.abs(e/g-a)/a>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�а�װ���۳��Ե�λ���������ڵ���");   	
					 return false;
				 }*/
				 else if(Math.abs(k/h-j)/j>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"���ܳ����԰�װ�����������׶�");   	
					 return false;
				 }
				 /*else if(Math.abs(l/h-g)/g>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�����س��԰�װ���������ڵ�λ����");   	
					 return false;
				 }*/
				 else{}
		    }
		
		}
		return true;
	}

}
