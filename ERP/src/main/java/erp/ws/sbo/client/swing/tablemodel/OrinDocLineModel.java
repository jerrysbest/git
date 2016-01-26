package erp.ws.sbo.client.swing.tablemodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

public class OrinDocLineModel extends AbstractDocLineModel<ColDocLine,DocLine<Inv1Id>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7728156737959184210L;
	private String dw,Itcode,hql,cc,cn;
	private Double md,dwjg,sl, zc, zz, zj,dwsl; 
	private Integer colidx;
	public OrinDocLineModel(ColDocLine colob, DocLine<Inv1Id> ob, DbUtils<ColDocLine, DocLine<Inv1Id>> dbu,
			String SQLQuery, Boolean b) {
		super(colob, ob, dbu, SQLQuery, b);
		// TODO Auto-generated constructor stub
	}
	public OrinDocLineModel(ColDocLine colob, DocLine<Inv1Id> ob, DbUtils<ColDocLine, DocLine<Inv1Id>> dbu,
			String SQLQuery) {
		super(colob, ob, dbu, SQLQuery);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		// TODO Auto-generated method stub
		if(ds==docLineStatus.query||ds==docLineStatus.load||rows==rowStatus.sn)
    	{
    		return false;
    	}
		else if(ds==docLineStatus.corin)
		{
		    if(col==this.getcolumnindex("���ϴ���")||col==this.getcolumnindex("�ֿ����")||col==this.getcolumnindex("��װ����")||col==this.getcolumnindex("����")||
		    col==this.getcolumnindex("��װ����")||col==this.getcolumnindex("����")||col==this.getcolumnindex("��������"))
    	    {    		
    		  return true;
    	    } 	
			else
			{
			  return false;
			}
		}		
		else if(ds==docLineStatus.orin)
		{
		    if(col==this.getcolumnindex("������")||col==this.getcolumnindex("���ϴ���")||col==this.getcolumnindex("�Ƿ���ʾ�׶�")||
			col==this.getcolumnindex("�ֿ����")||col==this.getcolumnindex("����")||col==this.getcolumnindex("����")||
			col==this.getcolumnindex("��װ����")||col==this.getcolumnindex("�Ƿ񻻻�")||
    		col==this.getcolumnindex("�ӹ���")||col==this.getcolumnindex("��������"))
    	    {    		
    		  return true;
    	    } 	
		    else if(col==this.getcolumnindex("�����׶�"))
	    	{
	    		if(this.getValuethrheader(row,"���۵�λ")!=null&&this.getValuethrheader(row,"���۵�λ").toString().equals("��"))
	    		{
	    		    return true;
	    		}
	    		else 
	    		{
	    			return false;
	    		}
	    	}
			else if(col==this.getcolumnindex("�׼۸�")||col==this.getcolumnindex("��װ����"))
	    	{
	    		if(this.getValuethrheader(row,"���۵�λ")!=null&&(this.getValuethrheader(row,"���۵�λ").toString().equals("��")||this.getValuethrheader(row,"���۵�λ").toString().equals("����")))
	    		{
	    		    return true;
	    		}
	    		else 
	    		{
	    			return false;
	    		}
	    	}
			else if(col==this.getcolumnindex("��λ����"))
	    	{
	    		if(this.getValuethrheader(row,"���۵�λ")!=null&&(this.getValuethrheader(row,"���۵�λ").toString().equals("��")||this.getValuethrheader(row,"���۵�λ").toString().equals("����")))
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
		
		else if(ds==docLineStatus.add)
		{
			if(col==this.getcolumnindex("�ֿ����")||col==this.getcolumnindex("��װ����")||col==this.getcolumnindex("����")||col==this.getcolumnindex("��������"))
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
		if(dataSet[row][this.getcolumnindex("������")]==null){
		  JOptionPane.showMessageDialog(null, "���������");
		  return;
		}
		else{
			cc=dataSet[row][this.getcolumnindex("������")].toString();
			 hql="select cardname from OCRD where cardcode='"+cc+"'";
	         ob=appMain.lt.sqlclob(hql,0,1);	            
	         if (ob==null||ob.length == 0)
	         {
		         //JOptionPane.showMessageDialog(null,ob+","+"û�����������");
		         return;
	         }
	         cn=ob[0][0].toString();
			  dataSet[row][this.getcolumnindex("�������")]=cn;
		  }
		if(getcolumnheader(col).equals("������")&&this.getValuethrheader(row,"�����׶�")!=null&&this.getValuethrheader(row,"����")!=null&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{
			 Itcode=this.getValuethrheader(row,"���ϴ���").toString(); 
	         if(Itcode==null||Itcode.length()==0)
	         {
	        	 return;
	         }
	         hql="select cardname from OCRD where cardcode='"+cc+"'";
	         ob=appMain.lt.sqlclob(hql,0,1);
	            
	         if (ob==null||ob.length == 0)
	         {
		         //JOptionPane.showMessageDialog(null,ob+","+"û�����������");
		         return;
	         }
	         cn=ob[0][0].toString();
	         hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	         		" U_Mtmd,U_Mtzl from Oitm where itemCode='" + Itcode + "'";     
	         ob=appMain.lt.sqlclob(hql,0,1);
	         if (ob==null||ob.length == 0)         
	         return;
	         dw = ob[0][0].toString();
	         dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString());       
	         zlbmd=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));
	         md=Double.valueOf(ob[0][5].toString())/(Double.valueOf(ob[0][6].toString()));
	         gs=Double.valueOf(this.getValuethrheader(row,"��װ����").toString()); 
	         sl=Double.valueOf(this.getValuethrheader(row,"����").toString()); 
	         hql="select a.id.itemCode,isnull(a.price,0) from Itm1 a,Oitm b,Ospp c "+
				 "where a.id.itemCode=b.itemCode and "+
				" c.id.itemCode=b.itemCode and a.id.priceList=c.listNum "+  
				" and c.id.cardCode='01ZCJG' and b.itemCode='"+Itcode+"'";
	         ob=appMain.lt.clob(hql,0,1);
	            
	         if (ob==null||ob.length == 0)
	         {
		         JOptionPane.showMessageDialog(null,ob+","+"�鲻������۸�");
		         return;
	         }
	        
	         jzjg = Double.valueOf(appMain.lt.clob(hql,0,1)[0][1].toString());
	                     
	         hql="select a.itemCode,isnull(a.price,0)*isnull(d.U_discount,0)  from Itm1 a,Oitm b,Ospp c,Ocrd d "+ 
				" where "+
			 " a.itemCode=b.itemCode and c.itemCode=b.itemCode and a.priceList=c.listNum "+ 
	          "and d.U_Jgqdz=c.cardCode "+
			 " and d.cardCode='" + cc + "' and b.itemCode='"+Itcode+"' ";
	         ob=appMain.lt.sqlclob(hql,0,1);        
	         if(ob==null||ob.length!=0)
	         {	         
	        	 dwjg=Double.valueOf(ob[0][1].toString());
	        	 gjjg=Double.valueOf(ob[0][1].toString())*(dwsl); 	        	         
		         if(dw.equals("����")||dw.equals("��"))
		         {	        	 		        	
		             mjg=dwjg*zlbmd;	   	           
		         }  	         
	         }
	         else{
	        	 JOptionPane.showMessageDialog(null,"�Ҳ����۸�");
	        	 return;
	         }
	        
	                
	        // gs = Double.valueOf(1.00);      
	         //sl = Double.valueOf(1.00);
	         
	         hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
	         ob = appMain.lt.clob(hql,0,1);
	         if(ob==null||ob.length==0)
	         {
	    	   return;
	         }
	         if (new BigDecimal(ob[0][0].toString()) == new BigDecimal("0") || new BigDecimal(ob[0][1].toString()) == new BigDecimal("0"))
	         {
	        	 JOptionPane.showMessageDialog(null,"���������������׶λ�����������Ϊ0,���������������۸��޷�����");
	             return;
	         }
	         zj = sl*dwjg;
	        dataSet[row][this.getcolumnindex("�������")]=cn;
	        dataSet[row][this.getcolumnindex("�׼۸�")]=mjg;
	        dataSet[row][this.getcolumnindex("��װ����")]=gjjg;
	        dataSet[row][this.getcolumnindex("����")]=dwjg;
	        dataSet[row][this.getcolumnindex("���")]=zj;
	        dataSet[row][this.getcolumnindex("����۸�")]=jzjg;        
            this.fireTableDataChanged();  
            return;                                        
		}
		//�����׶�
		else if(getcolumnheader(col).equals("�����׶�")&&this.getValuethrheader(row,"����")!=null&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{
			 
             /* NumberFormat nf = NumberFormat.getNumberInstance();
              nf.setGroupingUsed(false);     // don't group by threes
              nf.setMaximumFractionDigits(2);
              nf.setMinimumFractionDigits(2); 
              p = dataSet[row][1].toString();*/
			  Itcode=this.getValuethrheader(row,"���ϴ���").toString();
			  md = Double.valueOf(dataSet[row][col].toString());
			  dw=dataSet[row][this.getcolumnindex("���۵�λ")].toString();
			  hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
		         		" UMtmd,UMtzl from Oitm where itemCode='" + Itcode + "'";     
		      ob=appMain.lt.clob(hql,0,1);
		      if (ob==null||ob.length == 0)
	          return;
		     /* if(new BigDecimal(ob[0][6].toString()).setScale(5, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.00000).setScale(5, RoundingMode.HALF_UP))==0)
	           {
	            	dataSet[row][this.getcolumnindex("�����׶�")]=1;
	            	return;
		              
	           }*/
              wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString()));            
              try{
            	  gs = Double.valueOf(this.getValuethrheader(row,"��װ����").toString()); 
               }
               catch(NumberFormatException e0)
               {
               	JOptionPane.showMessageDialog(null,dataSet[row][this.getcolumnindex("��װ����")].toString()+"��װ�������벻�Ϸ�");               
                   return;
               }
              dwjg=Double.valueOf(this.getValuethrheader(row,"����").toString());  
              dataSet[row][this.getcolumnindex("��λ����")]=wgtpm*md;   
              dataSet[row][this.getcolumnindex("��װ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString())*wgtpm*md;   
              dataSet[row][this.getcolumnindex("�ܳ�")]=gs*md;           
              dataSet[row][this.getcolumnindex("����")]=gs*wgtpm*md;          
              dataSet[row][this.getcolumnindex("����")]=gs*wgtpm*md;     
              dataSet[row][this.getcolumnindex("���")]=dwjg*gs*wgtpm*md;       
              this.fireTableDataChanged();  
              return;                                        
		}
		else if (getcolumnheader(col).equals("���ϴ���")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
        {
			 if(doctype.equals("2"))
			 {
				 itemEndEdit_corin(row,this.getcolumnindex("���ϴ���"),cc,doctype);
			 }
			 else{
			     itemEndEdit(row,this.getcolumnindex("���ϴ���"),cc);
			 }
        }
		else if(getcolumnheader(col).equals("��װ����")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{			
			colidx=this.getcolumnindex("���ϴ���");                  
            Itcode = dataSet[row][colidx].toString();
            colidx=this.getcolumnindex("���۵�λ");  
            if(dataSet[row][colidx]==null)
            {
            	return;
            }
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
            	gs= Double.valueOf(gs.toString().substring(0,gs.toString().indexOf(".")));
            	
            }       
            //dwjg = Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString());
            sl=gs*dwsl;
            zj=gs*Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());
            if(!new BigDecimal(dataSet[row][this.getcolumnindex("��װ����")].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP)))
	    	{
               dwjg = zj/sl;
	    	}
            md=Double.valueOf(dataSet[row][this.getcolumnindex("�����׶�")].toString());
            zc=gs*md;
                       
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	               
                return;
            }	
                     
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
		else if(getcolumnheader(col).equals("��λ����")&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{			
			
			colidx=this.getcolumnindex("���ϴ���");                  
            Itcode = dataSet[row][colidx].toString();
            colidx=this.getcolumnindex("���۵�λ");  
            if(dataSet[row][colidx]==null)
            {
            	return;
            }
            dw = dataSet[row][colidx].toString();           
           
            dwsl=Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString());          
            dwjg=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());  
            sl=dwsl*gs;
            //������ܳ�           
	         md=Double.valueOf(dataSet[row][this.getcolumnindex("�����׶�")].toString()); 
	         zc=gs*md;
	         //���������
                   	           	
            gjjg=dwjg*dwsl; 	
            zj=sl*dwjg;
            hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
            ob =appMain.lt.clob(hql,0,1);
            if(ob==null||ob.length==0)
            {           	                           	  
            	return;
            }	
           /* if(new BigDecimal(ob[0][1].toString()).setScale(5, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.00000).setScale(5, RoundingMode.HALF_UP))==0)
            {
            	dataSet[row][this.getcolumnindex("��λ����")]=1;
            	return;
	              
            }*/
                 
            if (dw.equals("��"))
            {              
                zz = sl;
            }
            else if (dw.equals("����"))
            {
                zz = sl;
            }
            else
            {             
                zz = Double.valueOf("0");
            }  
            dataSet[row][this.getcolumnindex("�����׶�")]=md;
            dataSet[row][this.getcolumnindex("��װ����")]=gjjg;
            dataSet[row][this.getcolumnindex("����")]=sl;
            dataSet[row][this.getcolumnindex("�ܳ�")]=zc;
            dataSet[row][this.getcolumnindex("����")]=zz;
            dataSet[row][this.getcolumnindex("���")]=zj;
            this.fireTableDataChanged();
            return;
		}
		else if(getcolumnheader(col).equals("����")&&this.getValuethrheader(row,"����")!=null&&this.getValuethrheader(row,"����").toString()!=""&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{			                       
             Itcode = this.getValuethrheader(row,"���ϴ���").toString();
             dw =this.getValuethrheader(row,"���۵�λ").toString();
             sl = Double.valueOf(this.getValuethrheader(row,"����").toString());
             dwsl = Double.valueOf(this.getValuethrheader(row,"��λ����").toString());
             dwjg = Double.valueOf(this.getValuethrheader(row,"����").toString());
             md=Double.valueOf(this.getValuethrheader(row,"�����׶�").toString());
             zj=sl*dwjg;
     
             if (dw.equals("��")||dw.equals("����"))
             {                           	 	           
	             gjjg = dwjg*dwsl;     
	             mjg=gjjg/md;	            
             }          
             else
             {
                 mjg = Double.valueOf("0");
                 gjjg = dwjg*dwsl; 
             }
            
             dataSet[row][this.getcolumnindex("��װ����")]=gjjg;
             dataSet[row][this.getcolumnindex("�׼۸�")]=mjg;
             dataSet[row][this.getcolumnindex("���")]=zj;
             this.fireTableDataChanged();    
             return;
		}
		else if(this.getcolumnheader(col).equals("�׼۸�")&&dataSet[row][this.getcolumnindex("�׼۸�")]!=null&&dataSet[row][this.getcolumnindex("�׼۸�")].toString()!=""&&dataSet[row][this.getcolumnindex("���ϴ���")]!=null&&!dataSet[row][this.getcolumnindex("���ϴ���")].toString().equals(""))
		{	
             
              Itcode = dataSet[row][this.getcolumnindex("���ϴ���")].toString();
              dw = dataSet[row][this.getcolumnindex("���۵�λ")].toString();
              sl = Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());             
              mjg = Double.valueOf(dataSet[row][this.getcolumnindex("�׼۸�")].toString());
              md=   Double.valueOf(dataSet[row][this.getcolumnindex("�����׶�")].toString());          
              hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
              ob = appMain.lt.clob(hql,0,1);
              if(ob==null||ob.length==0)
              {
            	  return;
              }
            	  
              if(new BigDecimal(ob[0][1].toString()).setScale(5, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.00000).setScale(5, RoundingMode.HALF_UP))!=0)
              {
	              zlbmd=Double.valueOf(ob[0][1].toString())/Double.valueOf(ob[0][0].toString());
	              dwjg = mjg/zlbmd; 
              }
              else
              {
            	  return;
              }
              if (dw.equals("��")||dw.equals("����"))
              {
            	  gjjg = mjg*md;           	   
              }          
              else
              {
                 return;
              }            
              zj=sl*dwjg;
               
              dataSet[row][this.getcolumnindex("����")]=dwjg;              
              dataSet[row][this.getcolumnindex("��װ����")]=gjjg;
              dataSet[row][this.getcolumnindex("���")]=zj;
              this.fireTableDataChanged();   
              return;            
		}
		else if(this.getcolumnheader(col).equals("��װ����")&&dataSet[row][this.getcolumnindex("��װ����")]!=null&&dataSet[row][this.getcolumnindex("��װ����")].toString()!=""&&dataSet[row][this.getcolumnindex("���ϴ���")]!=null&&!dataSet[row][this.getcolumnindex("���ϴ���")].toString().equals(""))
		{			       
             Itcode = dataSet[row][this.getcolumnindex("���ϴ���")].toString();
             dw = dataSet[row][this.getcolumnindex("���۵�λ")].toString();
             sl = Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString()); 
             dwsl = Double.valueOf(dataSet[row][this.getcolumnindex("��λ����")].toString()); 
             gjjg = Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());
             md= Double.valueOf(dataSet[row][this.getcolumnindex("�����׶�")].toString());
             hql = "SELECT UMtmd,UMtzl FROM Oitm WHERE itemCode='" + Itcode + "'";
             ob = appMain.lt.clob(hql,0,1);
             if(ob==null||ob.length==0)
             {
           	  return;
             }
             if (dw.equals("��")||dw.equals("����"))
             {	           
            	 dwjg = gjjg/dwsl;
            	 mjg=gjjg/md;
             }            
                      
             zj = sl*dwjg;
             dataSet[row][this.getcolumnindex("����")]=dwjg;
             dataSet[row][this.getcolumnindex("�׼۸�")]=mjg;
             dataSet[row][this.getcolumnindex("���")]=zj;
             this.fireTableDataChanged();      
             return;
		}
		else if(getcolumnheader(col).equals("����")&&this.getValuethrheader(row,"���۵�λ").equals("��")&&((this.getValuethrheader(row,"�Ƿ���ʾ�׶�").toString().equals("��")||this.getValuethrheader(row,"�Ƿ���ʾ�׶�").toString().equals("Y"))&&!this.getValuethrheader(row,"���ϴ���").toString().substring(0, 2).equals("TD"))&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{		
	    	 dwjg = Double.valueOf(dataSet[row][this.getcolumnindex("���")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 dataSet[row][this.getcolumnindex("����")]=dwjg;
	    	 
	    	 //dataSet[row][this.getcolumnindex("����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	         this.fireTableDataChanged();
	         return;
		}
	     else if(getcolumnheader(col).equals("����")&&this.getValuethrheader(row,"���۵�λ").equals("��")&&(this.getValuethrheader(row,"�Ƿ���ʾ�׶�").toString().equals("��")||this.getValuethrheader(row,"�Ƿ���ʾ�׶�").toString().equals("N")||this.getValuethrheader(row,"���ϴ���").toString().substring(0, 2).equals("TD"))&&this.getValuethrheader(row,"���ϴ���")!=null&&!this.getValuethrheader(row,"���ϴ���").toString().equals(""))
		{	    	    	
	    	 dataSet[row][this.getcolumnindex("���")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())*Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 dataSet[row][this.getcolumnindex("����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString());
	    	 //dataSet[row][this.getcolumnindex("��λ����")]=Double.valueOf(dataSet[row][this.getcolumnindex("����")].toString())/Double.valueOf(dataSet[row][this.getcolumnindex("��װ����")].toString());
				
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
	    	 }
	         this.fireTableDataChanged();
	         return;
		}
		else
		{
			 return;
		}
	}

	@Override
	public void itemEndEdit(int row, int col, String cardCode) {
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
            
         if (ob==null||ob.length == 0)
         return;
         jzjg = Double.valueOf(appMain.lt.sqlclob(hql,0,1)[0][1].toString());
                     
         hql="select a.itemCode,isnull(a.price,0)*isnull(d.U_discount,0)  from Itm1 a,Oitm b,Ospp c,Ocrd d "+ 
			" where "+
		 " a.itemCode=b.itemCode and c.itemCode=b.itemCode and a.priceList=c.listNum "+ 
          "and d.U_Jgqdz=c.cardCode "+
		 " and d.cardCode='" + cardCode + "' and b.itemCode='"+Itcode+"' ";
         ob=appMain.lt.sqlclob(hql,0,1);        
         if(ob!=null||ob.length!=0)
         {	
        	 dwjg=Double.valueOf(ob[0][1].toString());	    
        	 gjjg=Double.valueOf(ob[0][1].toString())*(dwsl); 	        	         
	         if(dw.equals("����")||dw.equals("��"))
	         {	        	 	        	
	             mjg=dwjg*zlbmd;	   	           
	         }  	         
         }
         else{
        	 JOptionPane.showMessageDialog(null,"�Ҳ����۸�,"+"ҵ����"+cardCode+"�����ļ۸��嵥��δ��д���߲�����");
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
        	 JOptionPane.showMessageDialog(null,"���������������׶λ�����������Ϊ0,���������������۸��޷�����");
             return;
         }
         if (dw.equals("��"))
         {            
             zz =dwsl*(gs);                    
         }
         else if (dw.equals("����"))
         {
             zz = sl;
         }
         else
         {          
             zz = Double.valueOf("0");         
         }
        
         zj = sl*dwjg;
        zc=md*gs;     
        dataSet[row][this.getcolumnindex("��������")]=Itname; 
        dataSet[row][this.getcolumnindex("���۵�λ")]=dw;
        dataSet[row][this.getcolumnindex("��λ����")]=dwsl;
        dataSet[row][this.getcolumnindex("����")]=sl; 
        dataSet[row][this.getcolumnindex("��װ����")]=gs;
        dataSet[row][this.getcolumnindex("ɨ�����")]=0;
        dataSet[row][this.getcolumnindex("�����׶�")]=md;
        dataSet[row][this.getcolumnindex("�ֿ����")]=ck;
        dataSet[row][this.getcolumnindex("��浥λ")]=kcdw;
        dataSet[row][this.getcolumnindex("�׼۸�")]=mjg;
        dataSet[row][this.getcolumnindex("����")]=dwjg;
        dataSet[row][this.getcolumnindex("��װ����")]=gjjg;
        dataSet[row][this.getcolumnindex("���")]=zj;
        dataSet[row][this.getcolumnindex("�ܳ�")]=zc;
        dataSet[row][this.getcolumnindex("����")]=zz;
        ComboBoxItem  Cbi=new ComboBoxItem("N","��");
        dataSet[row][this.getcolumnindex("�Ƿ񻻻�")]=Cbi;
        Cbi=new ComboBoxItem("Y","��");
        dataSet[row][this.getcolumnindex("�Ƿ���ʾ�׶�")]=Cbi;
        dataSet[row][this.getcolumnindex("����۸�")]=jzjg;
       // fireTableCellUpdated(row, col); 
        this.fireTableDataChanged();
        return;         
	}

	@Override
	public void setUpSportColumn(JTable table, TableColumn sportColumn,
			String hql, String hql1) {
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
		@SuppressWarnings("unused")
		double a,b,c,d,e,g,h,j,k,l;
		for(int i=0;i<getRowCount();i++)
		{
		    if(this.getValuethrheader(i,"���ϴ���")!=null&&!this.getValuethrheader(i,"���ϴ���").toString().equals("")&&this.getValuethrheader(i,"���۵�λ").toString().equals("��")&&((this.getValuethrheader(i,"�Ƿ���ʾ�׶�").toString().equals("Y")||this.getValuethrheader(i,"�Ƿ���ʾ�׶�").toString().equals("��"))&&!this.getValuethrheader(i,"���ϴ���").toString().substring(0, 2).equals("TD")))	
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
				/* else if(Math.abs(e/g-a)/a>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"�а�װ���۳��Ե�λ���������ڵ���");   	
					 return false;
				 }*/
				 else if(Math.abs(k/h-j)/j>0.001)
				 {
					 JOptionPane.showMessageDialog(null,"��"+String.valueOf(i+1)+"���ܳ����԰�װ�����������׶�");   	
					 return false;
				 }
				/* else if(Math.abs(l/h-g)/g>0.001)
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
