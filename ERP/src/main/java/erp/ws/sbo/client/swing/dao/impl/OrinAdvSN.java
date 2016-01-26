package erp.ws.sbo.client.swing.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IAdvSN;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.client.swing.view.MainMenu.AboutView;
import erp.ws.sbo.client.swing.view.Orin.OrinView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
import erp.ws.sbo.utils.SNL;

public class OrinAdvSN implements IAdvSN<OrinView> {
	

	private AboutView av;
	@Override
    public void add(OrinView v,String SN,boolean ifdraft,String objtype,String Direction,boolean ifpasn,int rowid)
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
        String warehouse="";
        String cardcode="";
        /*Highlighter highlighter=null;
    	highlighter=v.getJta_SN().getHighlighter();
    	highlighter.removeAllHighlights();
    	v.getJta_SN().setSelectionColor(Color.LIGHT_GRAY);*/
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
     
    	for(int j=0;j<v.getOd().getRowCount();j++)
		{			
			if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&v.getOd().getValuethrheader(j, "���ϴ���")!=null&&v.getOd().getValuethrheader(j, "���ϴ���").toString().equals(sns.getItemcode())
			 &&new BigDecimal(v.getOd().getValuethrheader(j, "�����׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&(v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("Y")||v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("��"))
			 &&v.getOd().getValuethrheader(j, "������").equals(sns.getCardcode()))
			//&&(sns.getCardcode()==null||sns.getCardcode().toString().equals(""))||v.getOd().getValuethrheader(j, "������").equals(sns.getCardcode()))
			{										
				if(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())<Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString()))
				{
					v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1, j,"ɨ�����");
					snid=Integer.valueOf(v.getOd().getValuethrheader(j, "���").toString());
					warehouse=v.getOd().getValuethrheader(j, "�ֿ����").toString();
					if(objtype.equals("13")||objtype.equals("14"))
					{
					  cardcode=v.getOd().getValuethrheader(j, "������").toString();
					}
					break;
				}
			  
				
			}
			else if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
					 &&v.getOd().getValuethrheader(j, "���ϴ���")!=null&&v.getOd().getValuethrheader(j, "���ϴ���").toString().equals(sns.getItemcode())
					 &&(v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("N")||v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("��"))
					 &&v.getOd().getValuethrheader(j, "������").equals(sns.getCardcode()))
			{						
				if(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())<Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString()))
				{
					v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1, j,"ɨ�����");
					snid=Integer.valueOf(v.getOd().getValuethrheader(j, "���").toString());
					warehouse=v.getOd().getValuethrheader(j, "�ֿ����").toString();
					if(objtype.equals("13")||objtype.equals("14"))
					{
					  cardcode=v.getOd().getValuethrheader(j, "������").toString();
					}
					break;	
				}
																														
			}
			else{
				//list.add(sns.getSn());
				//break;
			}
			
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
					v.getDsv().getOd().setValuethrheader(warehouse,i, "�ֿ�");
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
    public boolean verification(OrinView v)
    {
    	//check  snstatus
    	String s =new String(v.getJta_SN().getText());   
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
		v.getJta_SN().setText(s);
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
    	// check if the jta_sn has duplicate records
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
	    	BigDecimal zl=new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP);
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
	    		   &&(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("N")||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("��"))
	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "���ϴ���").toString())
	    		   &&v.getDsv().getOd().getValuethrheader(j, "�к�").toString().equals(v.getOd().getValuethrheader(i, "���").toString()))
	    		{
	    			gs=gs+1;
	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
	    		}
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getOd().getValuethrheader(i, "�����׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("Y")||v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString().equals("��"))
 	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "���ϴ���").toString())
 	    		   &&v.getDsv().getOd().getValuethrheader(j, "�к�").toString().equals(v.getOd().getValuethrheader(i, "���").toString()))
 	    		{
 	    			gs=gs+1;
 	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
 	    		}    		
			} 
	    	if(!(gs==Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())
	    	  &&zl.equals(new BigDecimal(v.getOd().getValuethrheader(i, "����").toString()).setScale(3, BigDecimal.ROUND_HALF_UP))))
	    	{
	    		JOptionPane.showMessageDialog(null,"SNһ���Լ��������"+v.getOd().getValuethrheader(i, "���").toString()+"����"+v.getOd().getValuethrheader(i, "���ϴ���").toString()+"�׶�"+v.getOd().getValuethrheader(i, "�����׶�").toString()+"�������������뿪���в�һ��");	  
		    	return false;
	    	}
	    	
		      			
		}
	    return true;
    	
    }
	@Override
	public boolean snverification(OrinView v) {
		// TODO Auto-generated method stub
		String s =new String(v.getJta_SN().getText());   
		Pattern pat = Pattern.compile("\\s*|\t|\r|\n"); 		   
		Matcher m = pat.matcher(s); 
		s = m.replaceAll(""); 
		v.getJta_SN().setText(s);
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
	    String[] a = s.split(",");  	
	    ArrayList<String> list = new ArrayList<String>();
	    ArrayList<String> list1 = new ArrayList<String>();
		//fist,compare the sn of jta_sn an desnview,check if it  has duplicate records
	    for(int i=0;i<a.length;i++)
	    {
		    ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
	        snstatus sns=new snstatus();
	        sns=isn.queryByDocId(a[i]);
	        if(sns==null)
 	       {
 	    	   JOptionPane.showMessageDialog(null,a[i]+"�����ڣ�����");
 	    	   return false;
 	       }
	        /*Highlighter highlighter=null;
	    	highlighter=v.getJta_SN().getHighlighter();
	    	highlighter.removeAllHighlights();
	    	v.getJta_SN().setSelectionColor(Color.LIGHT_GRAY);*/
	        for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
			 {
	       	     if(v.getDsv().getOd().getValuethrheader(j, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(j, "���к�").toString().equals(sns.getSn().toString()))
				 {       	    	
					list1.add(a[i]);
				 }
			 }
	        if(list1.size()>0)
		    {
		    	String s1=new String("");
				
				for(int k=0;k<list1.size();k++)
				{
					s1=s1+list1.get(k)+",";
				}
				if(!s1.equals(""))
				
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
				   av.getTxtpnQserpDevelopedBy().setText(s1+"���к������кſ������Ѵ��ڣ���ɾ��");
		    	  return false;
		    }
	        boolean tf=false;
			inner:for(int j=0;j<v.getOd().getRowCount();j++)
			{			
				if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
				 &&v.getOd().getValuethrheader(j, "���ϴ���")!=null&&v.getOd().getValuethrheader(j, "���ϴ���").toString().equals(sns.getItemcode())
				 &&new BigDecimal(v.getOd().getValuethrheader(j, "�����׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))
				 &&(v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("Y")||v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("��"))
				 &&v.getOd().getValuethrheader(j, "������").equals(sns.getCardcode()))
				{										
					if(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())<Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString()))
					{
						v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1, j,"ɨ�����");
						list.remove(sns.getSn());
						tf=true;
						break inner;
					}
					else{
					
					}
					
				}
				else if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
						 &&v.getOd().getValuethrheader(j, "���ϴ���")!=null&&v.getOd().getValuethrheader(j, "���ϴ���").toString().equals(sns.getItemcode())
						 &&(v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("N")||v.getOd().getValuethrheader(j, "�Ƿ���ʾ�׶�").toString().equals("��"))
						 &&v.getOd().getValuethrheader(j, "������").equals(sns.getCardcode()))
				{						
					if(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())<Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "��װ����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString()))
					{
						v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(j, "ɨ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1, j,"ɨ�����");
						list.remove(sns.getSn());
						tf=true;
						break inner;
					}
					else{

					}	
																											
				}
				else{
					//list.add(sns.getSn());
					//break;
				}
				
			 }
	        if(!tf)
	        {
	        	list.add(sns.getSn());
	        }
	     }
	    
	    if(list.size()>0)
	    {
	    	String s1=new String("");
			for(int k=0;k<list.size();k++)
			{
				s1=s1+list.get(k)+",";
			}
			if(!s1.equals(""))
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
			   av.getTxtpnQserpDevelopedBy().setText(s1+"���кų���������������ɾ��");
	    	   return false;
	    }
		return true;
	}
	@Override
	public boolean bfcverification(OrinView v) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
