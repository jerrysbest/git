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
        	JOptionPane.showMessageDialog(null,SN+"不存在");	  
			 return;
        }
        			
        Object[] obj;	
        Integer snid=0;
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
		for(int i=0;i<v.getOd().getRowCount();i++)
		{
			if(!new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&v.getOd().getValuethrheader(i, "物料代码")!=null&&v.getOd().getValuethrheader(i, "物料代码").toString().equals(sns.getItemcode())
			 &&new BigDecimal(v.getOd().getValuethrheader(i, "米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))
			 &&(v.getOd().getValuethrheader(i, "是否米段线").toString().equals("Y")||v.getOd().getValuethrheader(i, "是否米段线").toString().equals("是"))
			 &&v.getOd().getValuethrheader(i, "仓库")!=null&&v.getOd().getValuethrheader(i, "仓库").toString().equals(sns.getWareHouse()))
			{	
				Double sl=Double.valueOf(v.getOd().getValuethrheader(i, "实际库存数量").toString());									
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,i,"实际收货个数");					
				v.getOd().setValuethrheader(sl+sns.getCweight(), i, "实际库存数量");	
				snid=Integer.valueOf(v.getOd().getValuethrheader(i, "序号").toString());
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(i, "伙伴代码").toString();
				}
				break;
			}
			else if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
					 &&v.getOd().getValuethrheader(i, "物料代码")!=null&&v.getOd().getValuethrheader(i, "物料代码").toString().equals(sns.getItemcode())
					 &&(v.getOd().getValuethrheader(i, "是否米段线").toString().equals("N")||v.getOd().getValuethrheader(i, "是否米段线").toString().equals("否"))
					 &&v.getOd().getValuethrheader(i, "仓库")!=null&&v.getOd().getValuethrheader(i, "仓库").toString().equals(sns.getWareHouse()))
			{	
				Double sl=Double.valueOf(v.getOd().getValuethrheader(i, "实际库存数量").toString());
				v.getOd().setValuethrheader(Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())+1,i,"实际收货个数");					
				v.getOd().setValuethrheader(sl+sns.getCweight(), i, "实际库存数量");		
				snid=Integer.valueOf(v.getOd().getValuethrheader(i, "序号").toString());
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(i, "伙伴代码").toString();
				}
				break;
			}
			else if(v.getOd().getValuethrheader(i, "物料代码")==null||(v.getOd().getValuethrheader(i, "物料代码")!=null&&v.getOd().getValuethrheader(i, "物料代码").toString().equals("")))
			{						
				v.getOd().setValuethrheader(sns.getItemcode(),i,"物料代码");	
				v.getOd().itemEndEdit(i, v.getOd().getcolumnindex("物料代码"), "");	
				if(new BigDecimal(sns.getLength()).compareTo(new BigDecimal("0"))!=0)
				{
					 ComboBoxItem  Cbi=new ComboBoxItem("Y","是");
				     v.getOd().setValuethrheader(Cbi, i, "是否米段线");
				     v.getOd().setValuethrheader(sns.getLength(), i, "米段");	
				}
				else{
					ComboBoxItem  Cbi=new ComboBoxItem("N","否");
			        v.getOd().setValuethrheader(Cbi, i, "是否米段线");
				}
				v.getOd().setValuethrheader(sns.getCweight(), i, "实际库存数量");
				v.getOd().setValuethrheader(sns.getWareHouse(), i, "仓库");	
				v.getOd().valueChanged(i, v.getOd().getcolumnindex("实际库存数量"), v.getOd().getValuethrheader(i, "物料代码").toString(), "");
				snid=Integer.valueOf(v.getOd().getValuethrheader(i, "序号").toString());
				if(objtype.equals("13"))
				{
				  cardcode=v.getOd().getValuethrheader(i, "伙伴代码").toString();
				}
				break;
			}
			else{}
			
			
		 }
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
			&&new BigDecimal(v.getDsv().getOd().getValuethrheader(i, "米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP))
			&&v.getDsv().getOd().getValuethrheader(i, "仓库")!=null&&v.getDsv().getOd().getValuethrheader(i, "仓库").toString().equals(sns.getWareHouse()))
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
				v.getDsv().getOd().setValuethrheader(sns.getWareHouse(), i+1, "仓库");
				v.getDsv().getOd().setValuethrheader(cardcode, i+1, "业务伙伴");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "创建时间");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()), i+1, "更新时间");
				break;
				//v.getJta_SN().getText().replace(v.getDsv().getOd().getValuethrheader(i, "序列号").toString(), v.getDsv().getOd().getValuethrheader(i, "序列号").toString()+","+v.getTxt_createcode().getText());												
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
				v.getDsv().getOd().setValuethrheader(sns.getWareHouse(),i, "仓库");
				v.getDsv().getOd().setValuethrheader(cardcode,i, "业务伙伴");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "创建时间");
				v.getDsv().getOd().setValuethrheader(f.format(new java.util.Date()),i, "更新时间");
				break;
			}
			else{}
		  }
		
    }
	//DesnsController 已经有方法
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
			 JOptionPane.showMessageDialog(null,"SN一致性检查出错：sn"+a[i].toString()+"长度"+String.valueOf(a[i].toString().length())+"不正确");	  
			  return false;
		 }
	     if(!result.add(a[i].toString()))
		  {
			  JOptionPane.showMessageDialog(null,"SN一致性检查出错：SN显示区的sn"+a[i].toString()+"重复");	  
			  return false;
		  }
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
	    	BigDecimal zl=new BigDecimal(0.000);
	    	for(int j=0;j<v.getDsv().getOd().getRowCount();j++)
			{
	    		if(v.getDsv().getOd().getValuethrheader(j, "序列号")==null||(v.getDsv().getOd().getValuethrheader(j, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(j, "序列号").toString().equals("")))
				{
					 continue;
				}
	    		ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
	            snstatus sns=new snstatus();
	            sns=isn.queryByDocId(v.getDsv().getOd().getValuethrheader(j, "序列号").toString());
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "是否米段线").toString().equals("N")||v.getOd().getValuethrheader(i, "是否米段线").toString().equals("否"))
	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "物料代码").toString())
	    		   &&sns.getWareHouse().toString().equals(v.getOd().getValuethrheader(i, "仓库").toString())
	    		   &&v.getDsv().getOd().getValuethrheader(j, "行号").toString().equals(v.getOd().getValuethrheader(i, "序号").toString()))
	    		{
	    			gs=gs+1;
	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
	    		}
	    		if(new BigDecimal(sns.getLength()).setScale(2, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getOd().getValuethrheader(i, "米段").toString()).setScale(2, BigDecimal.ROUND_HALF_UP))
	    		   &&(v.getOd().getValuethrheader(i, "是否米段线").toString().equals("Y")||v.getOd().getValuethrheader(i, "是否米段线").toString().equals("是"))
 	    		   &&sns.getItemcode().toString().equals(v.getOd().getValuethrheader(i, "物料代码").toString())
 	    		   &&sns.getWareHouse().toString().equals(v.getOd().getValuethrheader(i, "仓库").toString())
 	    		   &&v.getDsv().getOd().getValuethrheader(j, "行号").toString().equals(v.getOd().getValuethrheader(i, "序号").toString()))
 	    		{
 	    			gs=gs+1;
 	    			zl=zl.add(new BigDecimal(sns.getCweight()).setScale(3, BigDecimal.ROUND_HALF_UP));
 	    		}    		
			} 
	    	if(!(gs==Integer.valueOf(new BigDecimal(v.getOd().getValuethrheader(i, "实际收货个数").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString())
	    	  &&zl.equals(new BigDecimal(v.getOd().getValuethrheader(i, "实际库存数量").toString()).setScale(3, BigDecimal.ROUND_HALF_UP))))
	    	{
	    		JOptionPane.showMessageDialog(null,"SN一致性检查出错：序号"+v.getOd().getValuethrheader(i, "序号").toString()+"物料"+v.getOd().getValuethrheader(i, "物料代码").toString()+"米段"+v.getOd().getValuethrheader(i, "米段").toString()+"个数或者重量与开窗中不一致");	  
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
