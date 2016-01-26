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
import erp.ws.sbo.client.swing.view.OOige.OOigeView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
import erp.ws.sbo.utils.SNL;

public class OOigeAdvSN implements IAdvSN<OOigeView> {
	
	private AboutView av;
	@Override
    public void add(OOigeView v,String SN,boolean ifdraft,String objtype,String Direction,boolean ifpasn,int rowid)
    {
		ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
        snstatus sns=new snstatus();
        sns=isn.queryByDocId(SN);
        if(sns==null)
        {
        	JOptionPane.showMessageDialog(null,SN+"������");	  
			 return;
        }
        			
        Object[] obj;	
        Integer snid=0;
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
		for(int i=0;i<v.getOd().getRowCount();i++)
		{
			if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&v.getOd().getValuethrheader(i, "���ϴ���")!=null&&v.getOd().getValuethrheader(i, "���ϴ���").toString().equals(sns.getItemcode())
			 &&new BigDecimal(v.getOd().getValuethrheader(i, "�׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&(v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("Y")||v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("��"))
			 &&v.getOd().getValuethrheader(i, "�ֿ�")!=null&&v.getOd().getValuethrheader(i, "�ֿ�").toString().equals(sns.getWareHouse()))
			{	
				Double sl=Double.valueOf(v.getOd().getValuethrheader(i, "ʵ�ʿ������").toString());									
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,i,"ʵ���ջ�����");					
				v.getOd().setValuethrheader(sl+sns.getCweight(), i, "ʵ�ʿ������");	
				snid=Integer.valueOf(v.getOd().getValuethrheader(i, "���").toString());
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(i, "������").toString();
				}
				break;
			}
			else if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
					 &&v.getOd().getValuethrheader(i, "���ϴ���")!=null&&v.getOd().getValuethrheader(i, "���ϴ���").toString().equals(sns.getItemcode())
					 &&(v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("��"))
					 &&v.getOd().getValuethrheader(i, "�ֿ�")!=null&&v.getOd().getValuethrheader(i, "�ֿ�").toString().equals(sns.getWareHouse()))
			{	
				Double sl=Double.valueOf(v.getOd().getValuethrheader(i, "ʵ�ʿ������").toString());
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,i,"ʵ���ջ�����");					
				v.getOd().setValuethrheader(sl+sns.getCweight(), i, "ʵ�ʿ������");		
				snid=Integer.valueOf(v.getOd().getValuethrheader(i, "���").toString());
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(i, "������").toString();
				}
				break;
			}
			else if(v.getOd().getValuethrheader(i, "���ϴ���")==null||(v.getOd().getValuethrheader(i, "���ϴ���")!=null&&v.getOd().getValuethrheader(i, "���ϴ���").toString().equals("")))
			{						
				v.getOd().setValuethrheader(sns.getItemcode(),i,"���ϴ���");	
				v.getOd().itemEndEdit(i, v.getOd().getcolumnindex("���ϴ���"), "");	
				if(new BigDecimal(sns.getLength()).compareTo(new BigDecimal("0"))!=0)
				{
					 ComboBoxItem  Cbi=new ComboBoxItem("Y","��");
				     v.getOd().setValuethrheader(Cbi, i, "�Ƿ��׶���");
				     v.getOd().setValuethrheader(sns.getLength(), i, "�׶�");	
				}
				else{
					ComboBoxItem  Cbi=new ComboBoxItem("N","��");
			        v.getOd().setValuethrheader(Cbi, i, "�Ƿ��׶���");
				}
				v.getOd().setValuethrheader(sns.getCweight(), i, "ʵ�ʿ������");
				v.getOd().setValuethrheader(sns.getWareHouse(), i, "�ֿ�");	
				v.getOd().valueChanged(i, v.getOd().getcolumnindex("ʵ�ʿ������"), v.getOd().getValuethrheader(i, "���ϴ���").toString(), "");
				snid=Integer.valueOf(v.getOd().getValuethrheader(i, "���").toString());
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(i, "������").toString();
				}
				break;
			}
			else{}
			
			
		 }
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
			&&new BigDecimal(v.getDsv().getOd().getValuethrheader(i, "�׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))
			&&v.getDsv().getOd().getValuethrheader(i, "�ֿ�")!=null&&v.getDsv().getOd().getValuethrheader(i, "�ֿ�").toString().equals(sns.getWareHouse()))
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
				v.getDsv().getOd().setValuethrheader(sns.getWareHouse(), i+1, "�ֿ�");
				v.getDsv().getOd().setValuethrheader(cardcode, i+1, "ҵ����");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "����ʱ��");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "����ʱ��");
				break;
				//v.getJta_SN().getText().replace(v.getDsv().getOd().getValuethrheader(i, "���к�").toString(), v.getDsv().getOd().getValuethrheader(i, "���к�").toString()+","+v.getTxt_createcode().getText());												
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
				v.getDsv().getOd().setValuethrheader(sns.getWareHouse(),i, "�ֿ�");
				v.getDsv().getOd().setValuethrheader(cardcode,i, "ҵ����");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "����ʱ��");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "����ʱ��");
				break;
			}
			else{}
		  }
		
    }
	//DesnsController �Ѿ��з���
    public void delete(DeSNView v,String SN)
    {
    	
		
    }
    public boolean verification(OOigeView v)
    {
    	//check  snstatus
    	String s =new String(v.getJta_SN().getText());   
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
		v.getJta_SN().setText(s);
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
    	// check if the jta_sn has duplicate records    	
	    String[] a = s.split(",");  
	    boolean p=false;
		HashSet<String> result = new HashSet<String>();				 
		//fist,compare the sn of jta_sn an desnview,check if it  has duplicate records
	    for(int i=0;i<a.length;i++)
	    {
		 if(a[i].toString().length()!=18)
		 {
			 JOptionPane.showMessageDialog(null,"SNһ���Լ�����sn"+a[i].toString()+"����"+String.valueOf(a[i].toString().length())+"����ȷ");	  
			  return false;
		 }
	     if(!result.add(a[i].toString()))
		  {
			  JOptionPane.showMessageDialog(null,"SNһ���Լ�����SN��ʾ����sn"+a[i].toString()+"�ظ�");	  
			  return false;
		  }
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
	    	BigDecimal zl=new BigDecimal(0.000);
	    	for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
			{
	    		if(v.getDsv().getOd().getValuethrheader(j, "���к�")==null||(v.getDsv().getOd().getValuethrheader(j, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(j, "���к�").toString().equals("")))
				{
					 continue;
				}
	    		ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
	            snstatus sns=new snstatus();
	            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(j, "���к�").toString());
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("��"))
	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "���ϴ���").toString())
	    		   &&sns.getWareHouse().toString().equals(v.getOd().getValuethrheader(i, "�ֿ�").toString())
	    		   &&v.getDsv().getOd().getValuethrheader(j, "�к�").toString().equals(v.getOd().getValuethrheader(i, "���").toString()))
	    		{
	    			gs=gs+1;
	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
	    		}
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getOd().getValuethrheader(i, "�׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("Y")||v.getOd().getValuethrheader(i, "�Ƿ��׶���").toString().equals("��"))
 	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "���ϴ���").toString())
 	    		   &&sns.getWareHouse().toString().equals(v.getOd().getValuethrheader(i, "�ֿ�").toString())
 	    		   &&v.getDsv().getOd().getValuethrheader(j, "�к�").toString().equals(v.getOd().getValuethrheader(i, "���").toString()))
 	    		{
 	    			gs=gs+1;
 	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
 	    		}    		
			} 
	    	if(!(gs==Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())
	    	  &&zl.equals(new BigDecimal(v.getOd().getValuethrheader(i, "ʵ�ʿ������").toString()).setScale(3, BigDecimal.ROUND_HALF_UP))))
	    	{
	    		JOptionPane.showMessageDialog(null,"SNһ���Լ��������"+v.getOd().getValuethrheader(i, "���").toString()+"����"+v.getOd().getValuethrheader(i, "���ϴ���").toString()+"�׶�"+v.getOd().getValuethrheader(i, "�׶�").toString()+"�������������뿪���в�һ��");	  
		    	return false;
	    	}
	    	
		      			
		}
	    return true;
    	
    }
	@Override
	public boolean snverification(OOigeView v) {
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
	public boolean bfcverification(OOigeView v) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
