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
			JOptionPane.showMessageDialog(null,"没有行被选中,请选中某行");	  
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
	/**
	 * 此方法需要被重写
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
	    		if(v.getDsv().getOd().getValuethrheader(j, "序列号")==null||(v.getDsv().getOd().getValuethrheader(j, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(j, "序列号").toString().equals("")))
				{
					 continue;
				}
	    		if(a[i].toString().equals(v.getDsv().getOd().getValuethrheader(j, "序列号").toString()))
	    		{
	    			p=true;
	    			break;
	    		}
	    		if(!p)
	    		{
	    			JOptionPane.showMessageDialog(null,"SN一致性检查出错：SN显示区的sn"+v.getDsv().getOd().getValuethrheader(j, "序列号").toString()+"在SN开窗中不被包含。");	  
	    			return false;
	    		}
			}
	    }	
		result = new HashSet<String>();
		for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
		{
			if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
			{
				 continue;
			}
			if(!result.add(v.getDsv().getOd().getValuethrheader(i, "序列号").toString()))
			{
				JOptionPane.showMessageDialog(null,"SN一致性检查出错：SN开窗的sn"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"重复");	  
				return false;
			}
			if(!v.getJta_SN().getText().contains(v.getDsv().getOd().getValuethrheader(i, "序列号").toString()))
			{
				JOptionPane.showMessageDialog(null,"SN一致性检查出错：开窗中的sn"+v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+"在SN显示区不被包含。");	  
				return false;
			}			 		      			
		}
		 	  
		//if consistency verification,then compare desnview and v.getod()
	    for(int i=0;i<v.getOd().getRowCount();i++)
		{
	    	if(v.getOd().getValuethrheader(i, "物料代码")==null||(v.getOd().getValuethrheader(i, "物料代码")!=null&&v.getOd().getValuethrheader(i, "物料代码").toString().equals("")))
			{
				 continue;
			}
	    	Integer gs=0;
	    	BigDecimal zl=new BigDecimal(0);
	    	for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
			{
	    		if(v.getDsv().getOd().getValuethrheader(j, "序列号")==null||(v.getDsv().getOd().getValuethrheader(j, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(j, "序列号").toString().equals("")))
				{
					 continue;
				}
	    	    ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
	            snstatus sns=new snstatus();
	            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(j, "序列号").toString());
	            if(sns==null)
	 	       {
	 	    	   JOptionPane.showMessageDialog(null,v.getDsv().getOd().getValuethrheader(j, "序列号").toString()+"不存在，请检查");
	 	    	   return false;
	 	       }
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "是否米段线").toString().equals("N")||v.getOd().getValuethrheader(i, "是否米段线").toString().equals("否"))
	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "物料代码").toString())
	    		   &&v.getDsv().getOd().getValuethrheader(j, "行号").toString().equals(v.getOd().getValuethrheader(i, "序号").toString()))
	    		{
	    			gs=gs+1;
	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
	    		}
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getOd().getValuethrheader(i, "米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "是否米段线").toString().equals("Y")||v.getOd().getValuethrheader(i, "是否米段线").toString().equals("是"))
 	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "物料代码").toString())
 	    		   &&v.getDsv().getOd().getValuethrheader(j, "行号").toString().equals(v.getOd().getValuethrheader(i, "序号").toString()))
 	    		{
 	    			gs=gs+1;
 	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
 	    		}    		
			} 
	    	if(!(gs==Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "生产数量").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())
	    	  ||zl.equals(new BigDecimal(v.getOd().getValuethrheader(i, "实际库存数量").toString()).setScale(3, BigDecimal.ROUND_HALF_UP))))
	    	{
	    		JOptionPane.showMessageDialog(null,"SN一致性检查出错：序号"+v.getOd().getValuethrheader(i, "序号").toString()+"物料"+v.getOd().getValuethrheader(i, "物料代码").toString()+"米段"+v.getOd().getValuethrheader(i, "米段").toString()+"个数或者重量与开窗中不一致");	  
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
            	if(sns1.getItemcode().equals(v.getOd().getValuethrheader(i, "物料代码"))
            		&&sns1.getWareHouse().equals(v.getOd().getValuethrheader(i, "仓库"))
            		&&sns1.getLength().equals(v.getOd().getValuethrheader(i, "米段")))
            	{
            		break b;
            	}
            }
    		
    	}
    	
    	return true;
	}
	

}
