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
    * @ʵ�ָ���ĳ�����
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
		    if(this.getValuethrheader(i,"���ϴ���")!=null&&!this.getValuethrheader(i,"���ϴ���").toString().equals("")&&this.getValuethrheader(i,"���۵�λ").toString().equals("��")&&(this.getValuethrheader(i,"�Ƿ���ʾ�׶�")!=null&&this.getValuethrheader(i,"�Ƿ���ʾ�׶�").toString().equals("��")))	
		    {
		    	 a=Double.valueOf(dataSet[i][this.getcolumnindex("����")].toString());
				 b=Double.valueOf(dataSet[i][this.getcolumnindex("����")].toString());
				 c=Double.valueOf(dataSet[i][this.getcolumnindex("���")].toString());
				 d=Double.valueOf(dataSet[i][this.getcolumnindex("�׼۸�")].toString());
				 e=Double.valueOf(dataSet[i][this.getcolumnindex("��װ����")].toString());
				 g=Double.valueOf(dataSet[i][this.getcolumnindex("��λ����")].toString());
				 h=Double.valueOf(dataSet[i][this.getcolumnindex("��װ����")].toString());
				 j=Double.valueOf(dataSet[i][this.getcolumnindex("�����׶�")].toString());
				 k=Double.valueOf(dataSet[i][this.getcolumnindex("�ܳ�")].toString());
				 l=Double.valueOf(dataSet[i][this.getcolumnindex("����")].toString());
				 ck=dataSet[i][this.getcolumnindex("�ֿ����")].toString();
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
				 else if(Math.abs(e/g-a)/a>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�а�װ���۳��Ե�λ���������ڵ���");   	
					 return false;
				 }
				 else if(Math.abs(k/h-j)/j>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"���ܳ����԰�װ�����������׶�");   	
					 return false;
				 }
				 else if(Math.abs(l/h-g)/g>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�����س��԰�װ���������ڵ�λ����");   	
					 return false;
				 }
				 else if(ck!="2108")
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�вֿⲻ��ȷ"); 					 
					 return false;
				 }
				 else{}
		    }
		    else  if(this.getValuethrheader(i,"���ϴ���")!=null&&!this.getValuethrheader(i,"���ϴ���").toString().equals("")&&this.getValuethrheader(i,"���۵�λ").toString().equals("��")&&this.getValuethrheader(i,"�Ƿ���ʾ�׶�").toString().equals("��"))	
		    {
		    	ck=dataSet[i][this.getcolumnindex("�ֿ����")].toString();
		    	if(!ck.equals("2107"))
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�вֿⲻ��ȷ"); 					 
					 return false;
				 }
		    }
		    else  if(this.getValuethrheader(i,"���ϴ���")!=null&&!this.getValuethrheader(i,"���ϴ���").toString().equals("")&&this.getValuethrheader(i,"���۵�λ").toString().equals("��")&&(this.getValuethrheader(i,"�Ƿ���ʾ�׶�")==null||(this.getValuethrheader(i,"�Ƿ���ʾ�׶�")!=null&&this.getValuethrheader(i,"�Ƿ���ʾ�׶�").toString().equals(""))))	
		    {
		    	ck=dataSet[i][this.getcolumnindex("�ֿ����")].toString();
		    	if(!ck.equals("2108"))
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�вֿⲻ��ȷ"); 					 
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
