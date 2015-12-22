package erp.ws.sbo.client.swing.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IAdvSN;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.client.swing.view.MainMenu.AboutView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
import erp.ws.sbo.utils.SNL;

public class OignAdvSN implements IAdvSN<OignView> {

	private AboutView av;
	private SNL snl=(SNL)appMain.ctx.getBean("SNL");
	private snstatus sns1=new snstatus();
	private SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
	@Override
    public void add(OignView v,String SN,boolean ifdraft,String objtype,String Direction,boolean ifpasn,int rowid)
    {		
		if(rowid==-1){
			JOptionPane.showMessageDialog(null,"û���б�ѡ��,��ѡ��ĳ��");	  
			return;
		}
		ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
        snstatus sns=new snstatus();
        sns=isn.queryByDocId(SN);
        Object[] obj;	
        Integer snid=0;
        String warehouse="";
        String cardcode="";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
        for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		 {
     	     if(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals(sns.getSn().toString()))
			 {     	    	
     	    	  if(av==null)
			      {
			    	av=new AboutView();
			    	av.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					av.setVisible(true);
					av.setAlwaysOnTop(true);	
			      }
			      else
			      {			    	 
			    	  if(av.isActive()==false||av.isVisible()==false)
			    	  {   			    		 
			    		  av.setVisible(true);
			    		  av.setAlwaysOnTop(true);	
					 	//av.setBounds(200, 60, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	
			    	}
				   av.getTxtpnQserpDevelopedBy().setText("���к�"+sns.getSn()+"�ڿ������Ѵ���");
				   return;
			 }
		 }
		//for(int i=0;i<v.getOd().getRowCount();i++)
		//{
			if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&v.getOd().getValuethrheader(rowid, "���ϴ���")!=null&&v.getOd().getValuethrheader(rowid, "���ϴ���").toString().equals(sns.getItemcode())
			 &&v.getOd().getValuethrheader(rowid, "�ֿ�")!=null&&v.getOd().getValuethrheader(rowid, "�ֿ�").toString().equals(sns.getWareHouse())
			 &&new BigDecimal(v.getOd().getValuethrheader(rowid, "�׶�").toString()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(3, BigDecimal.ROUND_HALF_UP))
			 &&(v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("Y")||v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("��")))
			{		
				Double sl=Double.valueOf(v.getOd().getValuethrheader(rowid, "ʵ�ʿ������").toString());
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(rowid, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,rowid,"ʵ���ջ�����");					
				v.getOd().setValuethrheader(sl+sns.getCweight(), rowid, "ʵ�ʿ������");	
				snid=Integer.valueOf(v.getOd().getValuethrheader(rowid, "���").toString());
				//v.getOd().setValuethrheader(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString(), rowid, "�ֿ�");
				warehouse=v.getOd().getValuethrheader(rowid, "�ֿ�").toString();
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(rowid, "������").toString();
				}
			}
			else if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
					 &&v.getOd().getValuethrheader(rowid, "���ϴ���")!=null&&v.getOd().getValuethrheader(rowid, "���ϴ���").toString().equals(sns.getItemcode())
					 &&v.getOd().getValuethrheader(rowid, "�ֿ�")!=null&&v.getOd().getValuethrheader(rowid, "�ֿ�").toString().equals(sns.getWareHouse())
					 &&(v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("N")||v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("��")))
			{		
				Double sl=Double.valueOf(v.getOd().getValuethrheader(rowid, "ʵ�ʿ������").toString());				
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(rowid, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,rowid,"ʵ���ջ�����");					
				v.getOd().valueChanged(rowid, v.getOd().getcolumnindex("ʵ���ջ�����"), v.getOd().getValuethrheader(rowid, "���ϴ���").toString(), "");
				v.getOd().setValuethrheader(sl+sns.getCweight(), rowid, "ʵ�ʿ������");	
				v.getOd().valueChanged(rowid, v.getOd().getcolumnindex("ʵ�ʿ������"), v.getOd().getValuethrheader(rowid, "���ϴ���").toString(), "");
				snid=Integer.valueOf(v.getOd().getValuethrheader(rowid, "���").toString());
				v.getOd().setValuethrheader(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString(), rowid, "�ֿ�");
				warehouse=v.getOd().getValuethrheader(rowid, "�ֿ�").toString();
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(rowid, "������").toString();
				}
			}
			else{}
			
		 //}
		 obj=new Object[v.getDsv().getOd().getColumnCount()];	
	   	 int c=0;		
		 if(v.getDsv().getOd().getValuethrheader(v.getDsv().getOd().getRowCount()-1, "���ϴ���")==null||v.getDsv().getOd().getValuethrheader(v.getDsv().getOd().getRowCount()-1, "���ϴ���").toString().equals(""))
		 {
			 c=1;			
		 }
		 
		 if(c==0)
		 {
			 v.getDsv().getOd().insertRow(v.getDsv().getOd().getRowCount(), obj);
		 }
         obj=new Object[v.getDsv().getOd().getColumnCount()];	
		 for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		 {
			if(v.getDsv().getOd().getValuethrheader(i, "���ϴ���")!=null&&v.getDsv().getOd().getValuethrheader(i, "���ϴ���").toString().equals(sns.getItemcode())
			&&new BigDecimal(v.getDsv().getOd().getValuethrheader(i, "�׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP)))
			{						
				v.getDsv().getOd().insertRow(i+1, obj);
				v.getDsv().getOd().setValuethrheader(sns.getItemcode(), i+1, "���ϴ���");	
				v.getDsv().getOd().setValuethrheader(sns.getLength(), i+1, "�׶�");	
				v.getDsv().getOd().setValuethrheader(sns.getSn(), i+1, "���к�");
				v.getDsv().getOd().setValuethrheader(ifdraft, i+1, "�Ƿ�ݸ�");
				v.getDsv().getOd().setValuethrheader(objtype, i+1, "��������");
				v.getDsv().getOd().setValuethrheader(v.getTxt_docn().getText(), i+1, "����");
				v.getDsv().getOd().setValuethrheader(snid, i+1, "�к�");
				v.getDsv().getOd().setValuethrheader(sns.getCweight(), i+1, "����");
				v.getDsv().getOd().setValuethrheader(Direction, i+1, "����");
				v.getDsv().getOd().setValuethrheader(ifpasn, i+1, "�Ƿ�����к�");
				v.getDsv().getOd().setValuethrheader("", i+1, "���������к�");
				v.getDsv().getOd().setValuethrheader(warehouse, i+1, "�ֿ�");
				v.getDsv().getOd().setValuethrheader(cardcode, i+1, "ҵ����");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "����ʱ��");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "����ʱ��");
				
				v.getJta_SN().setText(v.getJta_SN().getText().replace(v.getDsv().getOd().getValuethrheader(i, "���к�").toString(), v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+","+v.getTxt_createcode().getText()));												
				break;
			}
			else if(v.getDsv().getOd().getValuethrheader(i, "���ϴ���")==null||v.getDsv().getOd().getValuethrheader(i, "���ϴ���").toString().equals(""))
			{						
				v.getDsv().getOd().setValuethrheader(sns.getItemcode(),i, "���ϴ���");	
				v.getDsv().getOd().setValuethrheader(sns.getLength(), i, "�׶�");	
				v.getDsv().getOd().setValuethrheader(sns.getSn(),i, "���к�");
				v.getDsv().getOd().setValuethrheader(ifdraft,i, "�Ƿ�ݸ�");
				v.getDsv().getOd().setValuethrheader(objtype,i, "��������");
				v.getDsv().getOd().setValuethrheader(v.getTxt_docn().getText(),i, "����");
				v.getDsv().getOd().setValuethrheader(snid,i, "�к�");
				v.getDsv().getOd().setValuethrheader(sns.getCweight(),i, "����");
				v.getDsv().getOd().setValuethrheader(Direction,i, "����");
				v.getDsv().getOd().setValuethrheader(ifpasn,i, "�Ƿ�����к�");
				v.getDsv().getOd().setValuethrheader("",i, "���������к�");
				v.getDsv().getOd().setValuethrheader(warehouse,i, "�ֿ�");
				v.getDsv().getOd().setValuethrheader(cardcode,i, "ҵ����");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "����ʱ��");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "����ʱ��");
				
				if(v.getJta_SN().getText().equals(""))
				{
					v.getJta_SN().append(v.getTxt_createcode().getText());	
				}
				else{
					v.getJta_SN().append(","+v.getTxt_createcode().getText());	
				}
				break;
			}
			else{}
		  }
		
    }
	/**
	 * �˷�����Ҫ����д
	 */
	public void delete(DeSNView v,String SN)
	{
	    
	}

	@Override
    public boolean verification(OignView v)
    {
    	//check  snstatus
    	SNL snl=new SNL();
    	try {
			if(!snl.verificationSN(v.getJta_SN(), false,v.getDsv()))
			{
				return false;
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
    	 * check if the jta_sn compatible with the itemcode details
    	 * 1.check if they have the same amount
    	 * 2.check if each sn in jta_sn has the right item with the right itemcode,length,warehouse,weight
    	 */
    	
		String s =new String(v.getJta_SN().getText()); 
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
	    String[] a = s.split(",");  
	    boolean p=false;
		HashSet<String> result = new HashSet<String>();		
		 
		//fist,compare the sn of jta_sn an desnview,check if it  has duplicate records
	    for(int i=0;i<a.length;i++)
	    {
	    	for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
			{
	    		if(v.getDsv().getOd().getValuethrheader(j, "���к�")==null||(v.getDsv().getOd().getValuethrheader(j, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(j, "���к�").toString().equals("")))
				{
					 continue;
				}
	    		if(a[i].toString().equals(v.getDsv().getOd().getValuethrheader(j, "���к�").toString()))
	    		{
	    			p=true;
	    			break;
	    		}
	    		if(!p)
	    		{
	    			JOptionPane.showMessageDialog(null,"SNһ���Լ�����SN��ʾ����sn"+v.getDsv().getOd().getValuethrheader(j, "���к�").toString()+"��SN�����в���������");	  
	    			return false;
	    		}
			}
	    }	
		result = new HashSet<String>();
		for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		{
			if(v.getDsv().getOd().getValuethrheader(i, "���к�")==null||(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals("")))
			{
				 continue;
			}
			if(!result.add(v.getDsv().getOd().getValuethrheader(i, "���к�").toString()))
			{
				JOptionPane.showMessageDialog(null,"SNһ���Լ�����SN������sn"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"�ظ�");	  
				return false;
			}
			if(!v.getJta_SN().getText().contains(v.getDsv().getOd().getValuethrheader(i, "���к�").toString()))
			{
				JOptionPane.showMessageDialog(null,"SNһ���Լ����������е�sn"+v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+"��SN��ʾ������������");	  
				return false;
			}			 		      			
		}
		 	  
		//if consistency verification,then compare desnview and v.getod()
	    for(int i=0;i<v.getOd().getRowCount();i++)
		{
	    	if(v.getOd().getValuethrheader(i, "���ϴ���")==null||(v.getOd().getValuethrheader(i, "���ϴ���")!=null&&v.getOd().getValuethrheader(i, "���ϴ���").toString().equals("")))
			{
				 continue;
			}
	    	Integer gs=0;
	    	BigDecimal zl=new BigDecimal(0);
	    	for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
			{
	    		if(v.getDsv().getOd().getValuethrheader(j, "���к�")==null||(v.getDsv().getOd().getValuethrheader(j, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(j, "���к�").toString().equals("")))
				{
					 continue;
				}
	    	    ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
	            snstatus sns=new snstatus();
	            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(j, "���к�").toString());
	            if(sns==null)
	 	       {
	 	    	   JOptionPane.showMessageDialog(null,v.getDsv().getOd().getValuethrheader(j, "���к�").toString()+"�����ڣ�����");
	 	    	   return false;
	 	       }
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("��"))
	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "���ϴ���").toString())
	    		   &&v.getDsv().getOd().getValuethrheader(j, "�к�").toString().equals(v.getOd().getValuethrheader(i, "���").toString()))
	    		{
	    			gs=gs+1;
	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
	    		}
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getOd().getValuethrheader(i, "�׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("Y")||v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("��"))
 	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "���ϴ���").toString())
 	    		   &&v.getDsv().getOd().getValuethrheader(j, "�к�").toString().equals(v.getOd().getValuethrheader(i, "���").toString()))
 	    		{
 	    			gs=gs+1;
 	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
 	    		}    		
			} 
	    	if(!(gs==Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "��������").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())
	    	  ||zl.equals(new BigDecimal(v.getOd().getValuethrheader(i, "ʵ�ʿ������").toString()).setScale(3, BigDecimal.ROUND_HALF_UP))))
	    	{
	    		JOptionPane.showMessageDialog(null,"SNһ���Լ��������"+v.getOd().getValuethrheader(i, "���").toString()+"����"+v.getOd().getValuethrheader(i, "���ϴ���").toString()+"�׶�"+v.getOd().getValuethrheader(i, "�׶�").toString()+"�������������뿪���в�һ��");	  
	    		return false;
	    	}
	    	
		      			
		}
	    return true;
    	
    }
	@Override
	public boolean snverification(OignView v) {
		// TODO Auto-generated method stub
		SNL snl=new SNL();
		try {
			if(!snl.verificationSN(v.getJta_SN(), true,v.getDsv()))
			{
				return false;
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean bfcverification(OignView v) {
		// TODO Auto-generated method stub
		if(!snl.verificationSNA_dialog(v.getJta_SN(), v.getDsv())){
			return false;
		}
		
		for(String str : snl.getSetsn())
    	{
            sns1=snst.queryByDocId(str);  
            b:
            for(int i=0;i<v.getOd().getRowCount();i++)
            {
            	if(sns1.getItemcode().equals(v.getOd().getValuethrheader(i, "���ϴ���"))
            		&&sns1.getWareHouse().equals(v.getOd().getValuethrheader(i, "�ֿ�"))
            		&&sns1.getLength().equals(v.getOd().getValuethrheader(i, "�׶�")))
            	{
            		break b;
            	}
            }
    		
    	}
    	
    	return true;
	}
	

}
