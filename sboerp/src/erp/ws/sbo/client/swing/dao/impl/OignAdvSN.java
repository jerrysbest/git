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
	private String hql;
	private Object[][] ob;
	private SNL snl=new SNL();
	private snstatus sns1=new snstatus();
	private ISNStatus snst=new SNStatus();
	@Override
    public void add(OignView v,String SN,boolean ifdraft,String objtype,String Direction,boolean ifpasn,int rowid)
    {		
		if(rowid==-1){
			JOptionPane.showMessageDialog(null,"没有行被选中,请选中某行");	  
			return;
		}
		snst=new SNStatus();	
        snstatus sns=new snstatus();
        sns=snst.queryByDocId(SN);
        Object[] obj;	
        Integer snid=0;
        String warehouse="";
        String cardcode="";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
        for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		 {
     	     if(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals(sns.getSn().toString()))
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
				   av.getTxtpnQserpDevelopedBy().setText("序列号"+sns.getSn()+"在开窗中已存在");
				   return;
			 }
		 }
		//for(int i=0;i<v.getOd().getRowCount();i++)
		//{
			if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&v.getOd().getValuethrheader(rowid, "物料代码")!=null&&v.getOd().getValuethrheader(rowid, "物料代码").toString().equals(sns.getItemcode())
			 &&v.getOd().getValuethrheader(rowid, "仓库")!=null&&v.getOd().getValuethrheader(rowid, "仓库").toString().equals(sns.getWareHouse())
			 &&new BigDecimal(v.getOd().getValuethrheader(rowid, "米段").toString()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(3, BigDecimal.ROUND_HALF_UP))
			 &&(v.getOd().getValuethrheader(rowid, "是否米段线").toString().equals("Y")||v.getOd().getValuethrheader(rowid, "是否米段线").toString().equals("是")))
			{		
				Double sl=Double.valueOf(v.getOd().getValuethrheader(rowid, "实际库存数量").toString());
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(rowid, "实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,rowid,"实际收货个数");					
				v.getOd().setValuethrheader(sl+sns.getCweight(), rowid, "实际库存数量");	
				snid=Integer.valueOf(v.getOd().getValuethrheader(rowid, "序号").toString());
				//v.getOd().setValuethrheader(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString(), rowid, "仓库");
				warehouse=v.getOd().getValuethrheader(rowid, "仓库").toString();
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(rowid, "伙伴代码").toString();
				}
			}
			else if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
					 &&v.getOd().getValuethrheader(rowid, "物料代码")!=null&&v.getOd().getValuethrheader(rowid, "物料代码").toString().equals(sns.getItemcode())
					 &&v.getOd().getValuethrheader(rowid, "仓库")!=null&&v.getOd().getValuethrheader(rowid, "仓库").toString().equals(sns.getWareHouse())
					 &&(v.getOd().getValuethrheader(rowid, "是否米段线").toString().equals("N")||v.getOd().getValuethrheader(rowid, "是否米段线").toString().equals("否")))
			{		
				Double sl=Double.valueOf(v.getOd().getValuethrheader(rowid, "实际库存数量").toString());				
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(rowid, "实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,rowid,"实际收货个数");					
				v.getOd().valueChanged(rowid, v.getOd().getcolumnindex("实际收货个数"), v.getOd().getValuethrheader(rowid, "物料代码").toString(), "");
				v.getOd().setValuethrheader(sl+sns.getCweight(), rowid, "实际库存数量");	
				v.getOd().valueChanged(rowid, v.getOd().getcolumnindex("实际库存数量"), v.getOd().getValuethrheader(rowid, "物料代码").toString(), "");
				snid=Integer.valueOf(v.getOd().getValuethrheader(rowid, "序号").toString());
				v.getOd().setValuethrheader(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString(), rowid, "仓库");
				warehouse=v.getOd().getValuethrheader(rowid, "仓库").toString();
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(rowid, "伙伴代码").toString();
				}
			}
			else{}
			
		 //}
		 obj=new Object[v.getDsv().getOd().getColumnCount()];	
	   	 int c=0;		
		 if(v.getDsv().getOd().getValuethrheader(v.getDsv().getOd().getRowCount()-1, "物料代码")==null||v.getDsv().getOd().getValuethrheader(v.getDsv().getOd().getRowCount()-1, "物料代码").toString().equals(""))
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
			if(v.getDsv().getOd().getValuethrheader(i, "物料代码")!=null&&v.getDsv().getOd().getValuethrheader(i, "物料代码").toString().equals(sns.getItemcode())
			&&new BigDecimal(v.getDsv().getOd().getValuethrheader(i, "米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP)))
			{						
				v.getDsv().getOd().insertRow(i+1, obj);
				v.getDsv().getOd().setValuethrheader(sns.getItemcode(), i+1, "物料代码");	
				v.getDsv().getOd().setValuethrheader(sns.getLength(), i+1, "米段");	
				v.getDsv().getOd().setValuethrheader(sns.getSn(), i+1, "序列号");
				v.getDsv().getOd().setValuethrheader(ifdraft, i+1, "是否草稿");
				v.getDsv().getOd().setValuethrheader(objtype, i+1, "对象类型");
				v.getDsv().getOd().setValuethrheader(v.getTxt_docn().getText(), i+1, "单号");
				v.getDsv().getOd().setValuethrheader(snid, i+1, "行号");
				v.getDsv().getOd().setValuethrheader(sns.getCweight(), i+1, "重量");
				v.getDsv().getOd().setValuethrheader(Direction, i+1, "方向");
				v.getDsv().getOd().setValuethrheader(ifpasn, i+1, "是否大序列号");
				v.getDsv().getOd().setValuethrheader("", i+1, "所属大序列号");
				v.getDsv().getOd().setValuethrheader(warehouse, i+1, "仓库");
				v.getDsv().getOd().setValuethrheader(cardcode, i+1, "业务伙伴");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "创建时间");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "更新时间");
				
				v.getJta_SN().setText(v.getJta_SN().getText().replace(v.getDsv().getOd().getValuethrheader(i, "序列号").toString(), v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+","+v.getTxt_createcode().getText()));												
				break;
			}
			else if(v.getDsv().getOd().getValuethrheader(i, "物料代码")==null||v.getDsv().getOd().getValuethrheader(i, "物料代码").toString().equals(""))
			{						
				v.getDsv().getOd().setValuethrheader(sns.getItemcode(),i, "物料代码");	
				v.getDsv().getOd().setValuethrheader(sns.getLength(), i, "米段");	
				v.getDsv().getOd().setValuethrheader(sns.getSn(),i, "序列号");
				v.getDsv().getOd().setValuethrheader(ifdraft,i, "是否草稿");
				v.getDsv().getOd().setValuethrheader(objtype,i, "对象类型");
				v.getDsv().getOd().setValuethrheader(v.getTxt_docn().getText(),i, "单号");
				v.getDsv().getOd().setValuethrheader(snid,i, "行号");
				v.getDsv().getOd().setValuethrheader(sns.getCweight(),i, "重量");
				v.getDsv().getOd().setValuethrheader(Direction,i, "方向");
				v.getDsv().getOd().setValuethrheader(ifpasn,i, "是否大序列号");
				v.getDsv().getOd().setValuethrheader("",i, "所属大序列号");
				v.getDsv().getOd().setValuethrheader(warehouse,i, "仓库");
				v.getDsv().getOd().setValuethrheader(cardcode,i, "业务伙伴");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "创建时间");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "更新时间");
				
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
		
		try {
			if(!(snl.verificationSN(v.getJta_SN(), false,v.getDsv())&&snl.verificationSNA_dialog(v.getJta_SN(), v.getDsv())))
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
            sns1=snst.queryByDocId(str);  
            weit=weit.add(new BigDecimal(sns1.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));  		
    	}
		 for(int i=0;i<v.getOd().getRowCount();i++)
         {
			 
			 if((v.getOd().getValuethrheader(i, "物料代码")!=null&&!v.getOd().getValuethrheader(i, "物料代码").toString().equals("")))
         	{
				hql="select isnull(U_usn,'N') from oitm where itemcode='"+v.getOd().getValuethrheader(i, "物料代码").toString()+"' ";
				ob=appMain.lt.sqlclob(hql,0,1);				
				if(ob[0][0].toString().equals("N"))
				{
				   continue;
				}
         		c+=Integer.valueOf(v.getOd().getValuethrheader(i, "实际收货个数").toString());
         		weit1=weit1.add(new BigDecimal(v.getOd().getValuethrheader(i, "实际库存个数").toString()).setScale(3, BigDecimal.ROUND_HALF_UP));        		
         	}
         }
		 if(!(c==snl.getSetsn().size()&&weit1.compareTo(weit)==0))
		 {			 
			JOptionPane.showMessageDialog(null,"序列号区域与表体的个数("+String.valueOf(snl.getSetsn().size())+","+String.valueOf(c)+")或者重量("+weit.toString()+","+weit1.toString()+")不一致"); 
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
