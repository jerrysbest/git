package erp.ws.sbo.client.swing.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IAdvSN;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.MainMenu.AboutView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.dao.impl.SNStatus;
import erp.ws.sbo.utils.SNL;

public class OignAdvSN implements IAdvSN<OignView> {

	private AboutView av;
	private String hql;
	private Object[][] ob;
	private SNL snl=new SNL();
	private snstatus sns1=new snstatus();
	//private ISNStatus snst=new SNStatus();
	@Override
    public void add(OignView v,String SN,boolean ifdraft,String objtype,String Direction,boolean ifpasn,int rowid)
    {		
		if(rowid==-1){
			JOptionPane.showMessageDialog(null,"û���б�ѡ��,��ѡ��ĳ��");	  
			return;
		}		
		//snst=new SNStatus();
		SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
        sns1=new snstatus();
        sns1=snst.queryByDocId(SN);
        Object[] obj;	
        Integer snid=0;
        String warehouse="";
        String cardcode="";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
        for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		 {
     	     if(v.getDsv().getOd().getValuethrheader(i, "���к�")!=null&&v.getDsv().getOd().getValuethrheader(i, "���к�").toString().equals(sns1.getSn().toString()))
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
				   av.getTxtpnQserpDevelopedBy().setText("���к�"+sns1.getSn()+"�ڿ������Ѵ���");
				   return;
			 }
		 }
          
		//for(int i=0;i<v.getOd().getRowCount();i++)
		//{
			if(!new BigDecimal(sns1.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&v.getOd().getValuethrheader(rowid, "���ϴ���")!=null&&v.getOd().getValuethrheader(rowid, "���ϴ���").toString().equals(sns1.getItemcode())
			 &&v.getOd().getValuethrheader(rowid, "�ֿ�")!=null&&v.getOd().getValuethrheader(rowid, "�ֿ�").toString().equals(sns1.getWareHouse())
			 &&new BigDecimal(v.getOd().getValuethrheader(rowid, "�׶�").toString()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns1.getLength()).setScale(3, BigDecimal.ROUND_HALF_UP))
			 &&(v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("Y")||v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("��")))
			{		
				Double sl=Double.valueOf(v.getOd().getValuethrheader(rowid, "ʵ�ʿ������").toString());
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(rowid, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,rowid,"ʵ���ջ�����");					
				v.getOd().setValuethrheader(sl+sns1.getCweight(), rowid, "ʵ�ʿ������");	
				snid=Integer.valueOf(v.getOd().getValuethrheader(rowid, "���").toString());
				//v.getOd().setValuethrheader(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString(), rowid, "�ֿ�");
				warehouse=v.getOd().getValuethrheader(rowid, "�ֿ�").toString();
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(rowid, "������").toString();
				}
			}
			else if(new BigDecimal(sns1.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
					 &&v.getOd().getValuethrheader(rowid, "���ϴ���")!=null&&v.getOd().getValuethrheader(rowid, "���ϴ���").toString().equals(sns1.getItemcode())
					 &&v.getOd().getValuethrheader(rowid, "�ֿ�")!=null&&v.getOd().getValuethrheader(rowid, "�ֿ�").toString().equals(sns1.getWareHouse())
					 &&(v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("N")||v.getOd().getValuethrheader(rowid, "�Ƿ��׶���").toString().equals("��")))
			{		
				Double sl=Double.valueOf(v.getOd().getValuethrheader(rowid, "ʵ�ʿ������").toString());				
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(rowid, "ʵ���ջ�����").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,rowid,"ʵ���ջ�����");					
				v.getOd().valueChanged(rowid, v.getOd().getcolumnindex("ʵ���ջ�����"), v.getOd().getValuethrheader(rowid, "���ϴ���").toString(), "");
				v.getOd().setValuethrheader(sl+sns1.getCweight(), rowid, "ʵ�ʿ������");	
				v.getOd().valueChanged(rowid, v.getOd().getcolumnindex("ʵ�ʿ������"), v.getOd().getValuethrheader(rowid, "���ϴ���").toString(), "");
				snid=Integer.valueOf(v.getOd().getValuethrheader(rowid, "���").toString());
				v.getOd().setValuethrheader(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString(), rowid, "�ֿ�");
				warehouse=v.getOd().getValuethrheader(rowid, "�ֿ�").toString();
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(rowid, "������").toString();
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"���к�"+sns1.getSn()+"������Ϣ"+sns1.getItemcode()+","+sns1.getWareHouse()+","+sns1.getLength().toString()+"����ѡ����Ϣ��һ��"+v.getOd().getValuethrheader(rowid, "���ϴ���").toString()+","+v.getOd().getValuethrheader(rowid, "�ֿ�").toString()+","+v.getOd().getValuethrheader(rowid, "�׶�").toString());
			    return;
			}
			
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
			if(v.getDsv().getOd().getValuethrheader(i, "���ϴ���")!=null&&v.getDsv().getOd().getValuethrheader(i, "���ϴ���").toString().equals(sns1.getItemcode())
			&&new BigDecimal(v.getDsv().getOd().getValuethrheader(i, "�׶�").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns1.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP)))
			{						
				v.getDsv().getOd().insertRow(i+1, obj);
				v.getDsv().getOd().setValuethrheader(sns1.getItemcode(), i+1, "���ϴ���");	
				v.getDsv().getOd().setValuethrheader(sns1.getLength(), i+1, "�׶�");	
				v.getDsv().getOd().setValuethrheader(sns1.getSn(), i+1, "���к�");
				v.getDsv().getOd().setValuethrheader(ifdraft, i+1, "�Ƿ�ݸ�");
				v.getDsv().getOd().setValuethrheader(objtype, i+1, "��������");
				v.getDsv().getOd().setValuethrheader(v.getTxt_docn().getText(), i+1, "����");
				v.getDsv().getOd().setValuethrheader(snid, i+1, "�к�");
				v.getDsv().getOd().setValuethrheader(sns1.getCweight(), i+1, "����");
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
				v.getDsv().getOd().setValuethrheader(sns1.getItemcode(),i, "���ϴ���");	
				v.getDsv().getOd().setValuethrheader(sns1.getLength(), i, "�׶�");	
				v.getDsv().getOd().setValuethrheader(sns1.getSn(),i, "���к�");
				v.getDsv().getOd().setValuethrheader(ifdraft,i, "�Ƿ�ݸ�");
				v.getDsv().getOd().setValuethrheader(objtype,i, "��������");
				v.getDsv().getOd().setValuethrheader(v.getTxt_docn().getText(),i, "����");
				v.getDsv().getOd().setValuethrheader(snid,i, "�к�");
				v.getDsv().getOd().setValuethrheader(sns1.getCweight(),i, "����");
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
	
	@Override
	//this method compare if the jta_sn equals with item details
	public boolean bfcverification(OignView v) {
		// TODO Auto-generated method stub
		snl=new SNL();
		try {
			if(!(snl.verificationSN(v.getJta_SN(), false,v.getDsv())&&snl.verificationSNA_dialog(v.getJta_SN(), v.getDsv(),snl.getSetsn())))
			{
				return false;
			}

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int c=0;
		BigDecimal weit=new BigDecimal("0.000"),weit1=new BigDecimal("0.000");
		for(String str : snl.getSetsn())
    	{
			SNStatus snst=(SNStatus)appMain.ctx.getBean("SNStatus");
            sns1=snst.queryByDocId(str);  
            weit=weit.add(new BigDecimal(sns1.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));  		
    	}
		 for(int i=0;i<v.getOd().getRowCount();i++)
         {
			 
			 if((v.getOd().getValuethrheader(i, "���ϴ���")!=null&&!v.getOd().getValuethrheader(i, "���ϴ���").toString().equals("")))
         	{
				hql="select isnull(U_usn,'N') from oitm where itemcode='"+v.getOd().getValuethrheader(i, "���ϴ���").toString()+"' ";
				ob=appMain.lt.sqlclob(hql,0,1);				
				if(ob[0][0].toString().equals("N"))
				{
				   continue;
				}
         		c+=new BigDecimal(v.getOd().getValuethrheader(i, "ʵ���ջ�����").toString()).intValue();
         		weit1=weit1.add(new BigDecimal(v.getOd().getValuethrheader(i, "ʵ�ʿ������").toString()).setScale(3, BigDecimal.ROUND_HALF_UP));        		
         	}
         }
		 if(!(c==snl.getSetsn().size()&&weit1.compareTo(weit)==0))
		 {			 
			JOptionPane.showMessageDialog(null,"���к����������ĸ���("+String.valueOf(snl.getSetsn().size())+","+String.valueOf(c)+")��������("+weit.toString()+","+weit1.toString()+")��һ��"); 
			return false;
		 }
    	
    	return true;
	}


	@Override
	public boolean verification(OignView v) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean snverification(OignView v) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
