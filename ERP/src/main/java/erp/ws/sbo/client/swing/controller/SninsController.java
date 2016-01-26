package erp.ws.sbo.client.swing.controller;


import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.SNL;
import erp.ws.sbo.utils.SerialConnection;
import erp.ws.sbo.utils.SerialConnectionException;
import erp.ws.sbo.utils.Snprint;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import erp.ws.aop.permission.PermissionDeniedException;
import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus; 

import javax.comm.*;


public class SninsController implements MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener,FocusListener {
	
	private SninView v;
	private DocMenuView dmv=DocMenuView.getdmv();
	private SerialConnection connection;
	private OutputStream os= null;  
	private CommPortIdentifier portId=null;
	private Object[] obj;
	private Object[][] ob;
	private String hql,hql1,hql2,hql3;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	

	@SuppressWarnings("unchecked")
	private DaoFactory<SninView> docf=(DaoFactory<SninView>)appMain.ctx.getBean("SninFactory");	
	public SninsController(SninView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);		
	}
	public SninView getV() {
		return v;
	}
	public void setV(SninView v) {
		this.v = v;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if((v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))&&(e.getSource()==v.getJt()||e.getSource()==v.getJta_SN()))
		{
			return;
		}
		//打序列号
		if(!appMain.branch.equals("-2")&&e.getKeyCode()==10&&(e.getSource()==v.getTxt_pweight()||e.getSource()==v.getTxt_length()))
		{
			if(v.getCom_port().getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(v,"请先打开串口");	              
				return;
			}
			//自动称重
			v.getJta_sendw().setText("R");
			v.getJta_receivew().setText("");
			v.getTxt_weight().setText("0");
			 //十六进制指令  
	        byte[] baKeyword = new byte[6];  
	   	      
	        baKeyword=v.getJta_sendw().getText().getBytes();
	               	              
	        try {  
	            //向串口发送指令  
	            os = connection.getsPort().getOutputStream();  
	            os.write(baKeyword, 0, baKeyword.length);  
	            } catch (IOException e2) {  
	            e2.printStackTrace();  
	        } 
	        catch (NullPointerException e1) {  
	            e1.printStackTrace();  
	        }  	       
	        //自动生成序列号
	        int i=JOptionPane.showConfirmDialog(null, "是否生成序列号并打印？");
	        if(i==0)
	        {			        	
	        	if(v.getCom_ifincomed().getSelectedItem().toString().equals("")
	        	||(v.getCom_ifincomed().getSelectedItem().toString().equals("否")
	        	&&dmv.getjButtonadd().isEnabled())
	        	||(v.getCom_ifincomed().getSelectedItem().toString().equals("是")
	        	&&!dmv.getjButtonadd().isEnabled()))
	        	{
	        		 JOptionPane.showMessageDialog(v,"是退货并且不是新增状态或者是新增状态不是退货才可以打印,是否退货不能为空");
		             return;
	        	}	        			
	        	if(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().equals("")
			    ||v.getTxt_MNo().getText().equals("")||v.getTxt_Qinspector().getText().equals("")||
	             new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            || new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            ||(new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP))
	            &&new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
	            ||new BigDecimal(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            ||new BigDecimal(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0
	            ||(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString().equals("2107")&&!new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	              &&!new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))))           
				{
	                JOptionPane.showMessageDialog(v,"物料代码,机号,净重,毛重,公司名称,不能为空或0,入库仓库为2107时米段和皮重不能同时大于0,当米段为0时,"+v.getTxt_pweight().getText()+"皮重不能为0,"+v.getTxt_sweight().getText()+"标准重必须大于0");
	                return;
	             }	
	        	if(e.getSource()==v.getTxt_length())
	 			{
	 				 hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	 	         		" UMtmd,UMtzl from Oitm where itemCode='" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText() + "'";     
	 			     ob=appMain.lt.clob(hql,0,1);
	 			     if (ob==null)
	 		         return;
	 			    if(new BigDecimal(v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
					{
	 			    	 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));	 					
	 			    	 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP)));	 			        
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 			 
					}  
					else{
		 	             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 	 	           
						 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
						 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 	          
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
	 			     }
					v.getTxt_createcode().setText("");
	 			}
	        	if(e.getSource()==v.getTxt_pweight())
				{	
	        		hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	 	         		" UMtmd,UMtzl from Oitm where itemCode='" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText() + "'";     
	 			     ob=appMain.lt.clob(hql,0,1);
	 			     if (ob==null)
	 		         return;
	 			     if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
					{
	 			    	 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));	 					
	 			    	 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP)));	 			        
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 			 
					}  
					else{
	 	             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 	 	           
					 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
					 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 	          
					 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
	 			     }
					v.getTxt_createcode().setText("");
				}
				if(new BigDecimal(Math.abs(Double.valueOf(v.getTxt_deviation().getText()==null?"0":v.getTxt_deviation().getText()))/Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.003))>0)
				{
					int j=JOptionPane.showConfirmDialog(null, "误差大于千分之三,是否继续?");
					if(j!=0)
					return;	            
				}
				
				hql = "select convert(nvarchar(10),getdate(),112)+ replace(convert(nvarchar(10),getdate(),8),':','')";
                ob=appMain.lt.sqlclob(hql,0,1);
                v.getTxt_createcode().setText(v.getTxt_MNo().getText() +ob[0][0].toString());
                
				hql = "SELECT sn FROM [@snstatus] where sn ='" + v.getTxt_createcode().getText() + "'";
				ob=appMain.lt.sqlclob(hql,0,1);
	            if (ob==null)
	            {	            	
	            
	            }
	            else
	            {
	            	JOptionPane.showMessageDialog(null,"数据库中已存在此序列号，不允许生成");
	            	v.getTxt_createcode().setText("");
	                return;
	            }
	           //打印
				if(v.getTxt_createcode().getText()!=null&&!v.getTxt_createcode().getText().equals("")&&v.getTxt_createcode().getText().length()==18)
				{			
					if(v.getCom_ifincomed().getSelectedItem().toString().equals("是")&&(v.getTxt_cus().getSelectedItem()==null||((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText().equals("")))
					{
						JOptionPane.showMessageDialog(null,"退回产品生成序列号必须选择业务伙伴");
		                return;
					}
					ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");							
					snstatus sns=new snstatus();
			        try{
			            sns=isn.queryByDocId(v.getTxt_createcode().getText());
			            if(sns==null)
			            {
			                SNL snl=new SNL(v.getDsv());								  
							if(snl.createSN(v))
							{
							  sns=isn.queryByDocId(v.getTxt_createcode().getText());
							}								      					       						
						    
			            }
			            v.getJta_SN().setEditable(true);
				        docf.getAdnSN().add(v, v.getTxt_createcode().getText(),true,"67","I",false,0);	
				        v.getJta_SN().setEditable(false);
				        Snprint snsp=new Snprint(v);
			            snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        
			            snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	  
			            v.getTxt_pweight().setText("0");	
			            v.getTxt_weight().setText("0");	
			            v.getTxt_cweight().setText("0");	 					
	 			    	v.getTxt_sweight().setText("0"); 			        
						v.getTxt_deviation().setText("0");
						v.getTxt_createcode().setText("");
			        }
			        catch(NullPointerException e3)
			        {
			        	e3.printStackTrace();
			        }	
			        catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        catch(NoClassDefFoundError e3){
		        	  e3.printStackTrace();
		            }
		            catch(UnsatisfiedLinkError e3){
		        	   e3.printStackTrace();
		            }
				    finally{
				    	 v.getTxt_cweight().setText("0");	 					
	 			    	 v.getTxt_sweight().setText("0");	 			        
						 v.getTxt_deviation().setText("0");
		 			 
				    }
		        } 
				else{
					JOptionPane.showMessageDialog(null, "序列号位数不正确或者没有序列号，不允许打印");
				}
			}
		}
		//if the user belongs to branch -2,he can  type weight manually
		if(appMain.branch.equals("-2")&&e.getKeyCode()==10&&(e.getSource()==v.getTxt_pweight()||e.getSource()==v.getTxt_length()||e.getSource()==v.getTxt_weight()))
		{			 	       
	        if(e.getSource()==v.getTxt_weight())
	        {
	        	 hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	              		" UMtmd,UMtzl from Oitm where itemCode='" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText() + "'";     
     		      ob=appMain.lt.clob(hql,0,1);
     		      if (ob==null)
     	          return;
     		      try{
     		    	  if(((JTextField)v.getCom_specification().getEditor().getEditorComponent())!=null&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().length()>=2&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().substring(0, 2).equals("TD")){
     		    		  v.getCom_whsin().setEditable(true);
     			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2109","2109"));
     			    	  v.getCom_whsin().setEditable(false);
     			    	  return;
     		    	  }
     		          /*else if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
     			      {
     			    	  v.getCom_whsin().setEditable(true);
     			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2107","2107"));
     			    	  v.getCom_whsin().setEditable(false);
     			    	  return;
     			      }
     			      else{
     			    	  v.getCom_whsin().setEditable(true);
     			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2108","2108"));
     			    	  v.getCom_whsin().setEditable(false);
     			      }*/
     		      }
     		      catch (NumberFormatException e1) {  
     		            e1.printStackTrace();  
     		        }  	
                  Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 
                  v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
                  v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
                  //v.getTxt_createcode().setText("");
                  //重量重算
                  v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
	   			   if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
	   			   {
	   				  v.getTxt_sweight().setText(v.getTxt_cweight().getText());			  
	   			   }			   
	   			   v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 		  
   		
	        }
			//自动生成序列号
	        int i=JOptionPane.showConfirmDialog(null, "是否生成序列号并打印？");
	        if(i==0)
	        {			        	
	        	if(v.getCom_ifincomed().getSelectedItem().toString().equals("")
	        	||(v.getCom_ifincomed().getSelectedItem().toString().equals("否")
	        	&&dmv.getjButtonadd().isEnabled())
	        	||(v.getCom_ifincomed().getSelectedItem().toString().equals("是")
	        	&&!dmv.getjButtonadd().isEnabled()))
	        	{
	        		 JOptionPane.showMessageDialog(v,"是退货并且不是新增状态或者是新增状态不是退货才可以打印,是否退货不能为空");
		             return;
	        	}	        			
	        	if(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().equals("")
			    ||v.getTxt_MNo().getText().equals("")||v.getTxt_Qinspector().getText().equals("")||
	             new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            || new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            ||(new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP))
	            &&new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
	            ||new BigDecimal(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            ||new BigDecimal(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0
	            ||(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString().equals("2107")&&!new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	              &&!new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))))           
				{
	                JOptionPane.showMessageDialog(v,"物料代码,机号,净重,毛重,公司名称,不能为空或0,入库仓库为2107时米段和皮重不能同时大于0,当米段为0时,"+v.getTxt_pweight().getText()+"皮重不能为0,"+v.getTxt_sweight().getText()+"标准重必须大于0");
	                return;
	             }	
	        	if(e.getSource()==v.getTxt_length())
	 			{
	 				 hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	 	         		" UMtmd,UMtzl from Oitm where itemCode='" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText() + "'";     
	 			     ob=appMain.lt.clob(hql,0,1);
	 			     if (ob==null)
	 		         return;
	 			    if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
					{
	 			    	 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));	 					
	 			    	 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP)));	 			        
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 			 
					}  
					else{
		 	             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 	 	           
						 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
						 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 	          
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
	 			     }
					v.getTxt_createcode().setText("");
	 			}
	        	if(e.getSource()==v.getTxt_pweight())
				{	
	        		hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
	 	         		" UMtmd,UMtzl from Oitm where itemCode='" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText() + "'";     
	 			     ob=appMain.lt.clob(hql,0,1);
	 			     if (ob==null)
	 		         return;
	 			     if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
					{
	 			    	 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));	 					
	 			    	 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP)));	 			        
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
		 			 
					}  
					else{
	 	             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 
	 	           
					 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
					 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 	          
					 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
	 			     }
					v.getTxt_createcode().setText("");
				}
				if(new BigDecimal(Math.abs(Double.valueOf(v.getTxt_deviation().getText()==null?"0":v.getTxt_deviation().getText()))/Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.003))>0)
				{
					int j=JOptionPane.showConfirmDialog(null, "误差大于千分之三,是否继续?");
					if(j!=0)
					return;	            
				}
				
				hql = "select convert(nvarchar(10),getdate(),112)+ replace(convert(nvarchar(10),getdate(),8),':','')";
                ob=appMain.lt.sqlclob(hql,0,1);
                v.getTxt_createcode().setText(v.getTxt_MNo().getText() +ob[0][0].toString());
                
				hql = "SELECT sn FROM [@snstatus] where sn ='" + v.getTxt_createcode().getText() + "'";
				ob=appMain.lt.sqlclob(hql,0,1);
	            if (ob==null)
	            {	            	
	            
	            }
	            else
	            {
	            	JOptionPane.showMessageDialog(null,"数据库中已存在此序列号，不允许生成");
	            	v.getTxt_createcode().setText("");
	                return;
	            }
	           //打印
				if(v.getTxt_createcode().getText()!=null&&!v.getTxt_createcode().getText().equals("")&&v.getTxt_createcode().getText().length()==18)
				{			
					if(v.getCom_ifincomed().getSelectedItem().toString().equals("是")&&(v.getTxt_cus().getSelectedItem()==null||((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText().equals("")))
					{
						JOptionPane.showMessageDialog(null,"退回产品生成序列号必须选择业务伙伴");
		                return;
					}
					ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");							
					snstatus sns=new snstatus();
			        try{
			            sns=isn.queryByDocId(v.getTxt_createcode().getText());
			            if(sns==null)
			            {
			                SNL snl=new SNL(v.getDsv());								  
							if(snl.createSN(v))
							{
							  sns=isn.queryByDocId(v.getTxt_createcode().getText());
							}								      					       						
						    
			            }
			            v.getJta_SN().setEditable(true);
				        docf.getAdnSN().add(v, v.getTxt_createcode().getText(),true,"67","I",false,0);	
				        v.getJta_SN().setEditable(false);
				        Snprint snsp=new Snprint(v);
			            snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        
			            snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	  
			            v.getTxt_pweight().setText("0");	
			            v.getTxt_weight().setText("0");	
			            v.getTxt_cweight().setText("0");	 					
	 			    	v.getTxt_sweight().setText("0"); 			        
						v.getTxt_deviation().setText("0");
						v.getTxt_createcode().setText("");
			        }
			        catch(NullPointerException e3)
			        {
			        	e3.printStackTrace();
			        }	
			        catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        catch(NoClassDefFoundError e3){
		        	  e3.printStackTrace();
		            }
		            catch(UnsatisfiedLinkError e3){
		        	   e3.printStackTrace();
		            }
				    finally{
				    	 v.getTxt_cweight().setText("0");	 					
	 			    	 v.getTxt_sweight().setText("0");	 			        
						 v.getTxt_deviation().setText("0");
		 			 
				    }
		        } 
				else{
					JOptionPane.showMessageDialog(null, "序列号位数不正确或者没有序列号，不允许打印");
				}
			}
		}
		if(e.getKeyCode()==10)
		{
			 if(e.getSource()==v.getJta_SN())
			{
				
			}
			 //序列号回车后，添加到jta_SN,SN开窗，表体
			else if(e.getSource()==v.getTxt_createcode())
			{		
			   
			}
			else
			{}
		}	
			
		if(e.getKeyCode()==32&&e.getSource()==v.getJt())
		{						
			int i=JOptionPane.showConfirmDialog(null, "是否保存");
			if(i==0)
			{
			  if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").toString().equals("")&&
				 v.getOd().getValuethrheader(0, "实际库存数量")!=null&&!v.getOd().getValuethrheader(0, "实际库存数量").toString().equals("")){		  													 
				  try{
					  v.getJt().getCellEditor().stopCellEditing();	
				    }
					catch(NullPointerException e0){
						//e0.printStackTrace();
					}
				  for(int j=0;j<v.getOd().getRowCount();j++)
				  {
					  if(v.getOd().getValuethrheader(j, "仓库")!=null&&v.getOd().getValuethrheader(j, "仓库").toString().contains(","))
					  {
						 v.getOd().setValuethrheader(v.getOd().getValuethrheader(j, "仓库").toString().split(",")[0], j, "仓库");
					  }
					  else{
						  continue;
					  }
				  }
				   docf.getSninAbsDoc().create(v);
					
				 }
				else{JOptionPane.showMessageDialog(null, "单身没有数据，请录入数据");}
			}
						
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		
		if(e.getKeyCode()==127&&e.getSource()==v.getJt())
		{ 
			
		  
		}
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()<v.getOd().getRowCount()-1&&e.getSource()==v.getJt()&&v.getOd().ds.getCnValue().equals("库存转储"))
		{  
			int i=v.getJt().getSelectedRow();
			v.getJt().setColumnSelectionInterval(2,2);			
			v.getJt().setRowSelectionInterval(i, i);
			v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else if(e.getKeyCode()==40&&v.getJt().getSelectedRow()==v.getOd().getRowCount()-1&&e.getSource()==v.getJt()&&v.getOd().ds.getCnValue().equals("库存转储"))
		{  
			int i=v.getJt().getSelectedRow();
			obj=new Object[v.getOd().getColumnCount()];	
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
			v.getJt().setColumnSelectionInterval(2,2);
			v.getJt().setRowSelectionInterval(i+1, i+1);
			v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}
		else{}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("实际收货个数")&&e.getSource()==v.getJt())
		{
	       v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
		}		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("打开串口")&&v.getOd().ds.getValue()==3)
		{		    			
	    	v.getCom_port().removeAllItems();	  
	        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
	        //JOptionPane.showMessageDialog(v, en);
	        // iterate through the ports.
	        while (en.hasMoreElements()) {
	        	//JOptionPane.showMessageDialog(v, en.nextElement());
	            portId = (CommPortIdentifier) en.nextElement();	            
	            if (portId!=null&&portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
	            	v.getCom_port().addItem(portId.getName());
	            }
	        }	       
			Cursor previousCursor = v.getCursor();
		    v.setNewCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    v.setParameters();
		    		   
		    try {
		    	 connection=new SerialConnection(v, v.getParameters(), 
						 v.getJta_sendw(), v.getJta_receivew(),v.getTxt_weight(),v.getTxt_cweight(),v.getTxt_deviation(),v);
		    	connection.openConnection(v.getCom_port().getSelectedItem().toString());
		    	 v.portOpened();
		    } catch (SerialConnectionException e2) {
		
			 JOptionPane.showMessageDialog(null,"端口打开错误,"+ e2.getMessage()+"重新设定端口以及相关参数:操作请注意序列号入库单关闭前先关闭串口，否则只能退出程序重新登录");
			 v.getBt_open().setEnabled(true);
			 v.getBt_close().setEnabled(false);
			 v.getBt_cweight().setEnabled(false);
			 v.getBt_weight().setEnabled(false);
			 v.setNewCursor(previousCursor);
			 return;
		    }
		 
		    v.setNewCursor(previousCursor);
			
		}
		else if(arg0.getActionCommand().equals("关闭串口")&&v.getOd().ds.getValue()==3)
		{
			connection.closeConnection();
			v.getCom_port().removeAllItems();
			v.getBt_open().setEnabled(true);
			v.getBt_close().setEnabled(false);
			v.getBt_cweight().setEnabled(false);
			v.getBt_weight().setEnabled(false);
			
		}		
		else if(arg0.getActionCommand().equals("净重")&&v.getOd().ds.getValue()==3)
		{
			/*v.getJta_sendw().setText("R");
			v.getJta_receivew().setText("");
			v.getTxt_cweight().setText("0");
			 //十六进制指令  
	        byte[] baKeyword = new byte[6];  
	   	      
	        baKeyword=v.getJta_sendw().getText().getBytes();
	               	              
	        try {  
	            //向串口发送指令  
	            os = connection.getsPort().getOutputStream();  
	            os.write(baKeyword, 0, baKeyword.length);  

	             } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	        catch (NullPointerException e1) {  
	            e1.printStackTrace();  
	        }  */
	         
		}
		else if(arg0.getActionCommand().equals("毛重")&&v.getOd().ds.getValue()==3)
		{
			/*v.getJta_sendw().setText("S");
			v.getJta_receivew().setText("");
			v.getTxt_weight().setText("0");
			 //十六进制指令  
	        byte[] baKeyword = new byte[6];  
	   	      
	        baKeyword=v.getJta_sendw().getText().getBytes();
	            
	        try {  
	            //向串口发送指令  
	            os = connection.getsPort().getOutputStream();  
	            os.write(baKeyword, 0, baKeyword.length);  
	                             
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  */
			if(v.getCom_port().getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(v,"请先打开串口");	              
				return;
			}
			v.getJta_sendw().setText("R");
			v.getJta_receivew().setText("");
			v.getTxt_weight().setText("0");
			 //十六进制指令  
	        byte[] baKeyword = new byte[6];  
	   	      
	        baKeyword=v.getJta_sendw().getText().getBytes();
	               	              
	        try {  
	            //向串口发送指令  
	            os = connection.getsPort().getOutputStream();  
	            os.write(baKeyword, 0, baKeyword.length);  
	             } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	        catch (NullPointerException e1) {  
	            e1.printStackTrace();  
	        }  
		}
		else if(arg0.getActionCommand().equals("保存打印设置")&&v.getOd().ds.getValue()==3)
		{
			 hql="select * from dbo.[@SNPRINTER] where code='1' and name='打印机设置'";
			 ob=appMain.lt.sqlclob(hql,0,1);
			 if(ob!=null&&ob.length==1)
			 {					
			   hql = "update  dbo.[@SNPRINTER] set U_Width='"+v.getTxt_width().getText()+"',U_Height='"+v.getTxt_height().getText()+"'," +
			   		"U_codewidth='"+v.getTxt_codewidth().getText()+"',U_codeheight='"+v.getTxt_codeheight()+"'," +
			   		"U_codegap='"+v.getTxt_codegap().getText()+"',U_codetype='"+v.getTxt_codetype().getText()+"'," +
			   		"U_up='"+v.getTxt_up().getText()+"',U_down='"+v.getTxt_down().getText()+"'" +
			   		"U_left='"+v.getTxt_left().getText()+"',U_right='"+v.getTxt_right().getText()+"'";
			   dbu.exeSql(hql);
			 }
			 else{
			   hql ="insert into dbo.[@SNPRINTER](code,name,U_Width,U_Height," +
			   		"U_codewidth,U_codeheight,U_codegap,U_codetype,U_up,U_down,U_left,U_right)" +
			   		"values(1,'打印机设置','"+v.getTxt_width().getText()+"','"+v.getTxt_height().getText()+"'," +
			   		 "'"+v.getTxt_codewidth().getText()+"','"+v.getTxt_codeheight()+"','"+v.getTxt_codegap().getText()+"'," +
			   		 "'"+v.getTxt_codetype().getText()+"','"+v.getTxt_up().getText()+"','"+v.getTxt_down().getText()+"'," +
			   		 "'"+v.getTxt_left().getText()+"','"+v.getTxt_right().getText()+"')";
			   dbu.exeSql(hql);
			 }
		}
		else if(arg0.getActionCommand().equals("生成序列号")&&v.getOd().ds.getValue()==3)
		{
			if(v.getCom_port().getSelectedItem()==null)
			{
				 JOptionPane.showMessageDialog(v,"请先打开串口");	              
				return;
			}
			if(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().equals("")
		    ||v.getTxt_MNo().getText().equals("")||v.getTxt_Qinspector().getText().equals("")||
             new BigDecimal(v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
            || new BigDecimal(v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
            ||(new BigDecimal(v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP))
            &&new BigDecimal(v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
            ||new BigDecimal(v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
            ||new BigDecimal(v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0          
			 ||(((ComboBoxItem)v.getCom_whsin().getSelectedItem()).getValue().toString().equals("2107")&&!new BigDecimal(v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
		         &&!new BigDecimal(v.getTxt_pweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))))           					
			{
                JOptionPane.showMessageDialog(v,"物料代码,机号,净重,毛重,公司名称,不能为空或0,仓库为2107时米段和皮重不能同时大于0,当米段为0时,皮重不能为0,标准重必须大于0");
                return;
             }	
			if(new BigDecimal(Math.abs(Double.valueOf(v.getTxt_deviation().getText()))/Double.valueOf(v.getTxt_sweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.003))>0)
			{
				int i=JOptionPane.showConfirmDialog(null, "误差大于千分之三,是否继续?");
				if(i!=0)
				return;	            
			}
			hql = "select convert(nvarchar(10),getdate(),112)+ replace(convert(nvarchar(10),getdate(),8),':','')";
            ob=appMain.lt.sqlclob(hql,0,1);
            v.getTxt_createcode().setText(v.getTxt_MNo().getText() +ob[0][0].toString());
            
			 hql = "SELECT sn FROM [@snstatus] where sn ='" + v.getTxt_createcode().getText() + "'";
			 ob=appMain.lt.sqlclob(hql,0,1);
             if (ob==null)
             {	            	
            	
             }
             else
             {
            	JOptionPane.showMessageDialog(null,"数据库中已存在此序列号，不允许生成");
            	 v.getTxt_createcode().setText("");
                return;
             }
		}
		else if(arg0.getActionCommand().equals("选择序列号")&&v.getOd().ds.getValue()==3)
		{
			SNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
            snstatus sns=new snstatus(); 
            sns=isn.queryByDocId(((JTextField)v.getCom_selcode().getEditor().getEditorComponent()).getText());   
            if(!sns.getSn().substring(0, 4).equals(v.getTxt_MNo().getText()))
			{
            	JOptionPane.showMessageDialog(null,"只能选择本机号序列号");
				return;
			}
            //v.getTxt_MNo().setText(sns.getMno());
            v.getTxt_length().setEditable(true);
            v.getTxt_pweight().setEditable(true);
            v.getTxt_weight().setEditable(true);
            v.getTxt_cweight().setEditable(true);
            v.getTxt_sweight().setEditable(true);
            v.getTxt_Qinspector().setEditable(true);
            v.getTxt_deviation().setEditable(true);
            v.getCom_specification().setEnabled(true);
            v.getCom_specification().setEditable(true); 
           
            v.getCom_specification().setSelectedItem(sns.getItemcode());  
            v.getTxt_createcode().setText(((JTextField)v.getCom_selcode().getEditor().getEditorComponent()).getText());
            v.getTxt_length().setText(sns.getLength().toString());
            v.getTxt_pweight().setText(sns.getPweight().toString());
            v.getTxt_weight().setText(sns.getWeight().toString());
            v.getTxt_cweight().setText(sns.getCweight().toString());           
            v.getTxt_sweight().setText(sns.getSweight().toString());
            v.getTxt_Qinspector().setText(sns.getQc());
            v.getTxt_deviation().setText(String.valueOf(new BigDecimal(sns.getWeight()-sns.getSweight()).setScale(3, BigDecimal.ROUND_HALF_UP)));
           
            v.getTxt_length().setEditable(false);
            v.getTxt_pweight().setEditable(false);
            v.getTxt_weight().setEditable(false);
            v.getTxt_cweight().setEditable(false);
            v.getTxt_sweight().setEditable(false);
            v.getTxt_Qinspector().setEditable(false);
            v.getTxt_deviation().setEditable(false);
            v.getCom_specification().setEnabled(false);
            v.getCom_specification().setEditable(false); 			
		}
		else if(arg0.getSource()==v.getCom_ifincomed()&&v.getOd().ds.getValue()==3)
		{			
			if(v.getCom_ifincomed().getSelectedItem().toString().equals("是"))
			{
				v.getTxt_cus().setEditable(true);				
			}
			else{
				v.getTxt_cus().setSelectedItem(null);
				v.getTxt_cus().setEditable(false);
			}
		}
		else if(arg0.getSource()==v.getBt_print()&&v.getOd().ds.getValue()==3)
		{
			if(v.getTxt_createcode().getText()!=null&&!v.getTxt_createcode().getText().equals("")&&v.getTxt_createcode().getText().length()==18)
			{			
				if(v.getCom_ifincomed().getSelectedItem().toString().equals("是")&&(v.getTxt_cus().getSelectedItem()==null||((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText().equals("")))
				{
					JOptionPane.showMessageDialog(null,"退回产品生成序列号必须选择业务伙伴");
	                return;
				}
				ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");							
				snstatus sns=new snstatus();
		        try{
		            sns=isn.queryByDocId(v.getTxt_createcode().getText());
		            if(sns==null)
		            {
		                SNL snl=new SNL(v.getDsv());							   
						if(snl.createSN(v))
						{
						  sns=isn.queryByDocId(v.getTxt_createcode().getText());							 			         	
						}					 
						
		            }
		          //  JOptionPane.showMessageDialog(v, v.getTxt_createcode().getText());
		            v.getJta_SN().setEditable(true);
			        docf.getAdnSN().add(v, v.getTxt_createcode().getText(),true,"67","I",false,0);	
			        v.getJta_SN().setEditable(false);		
					Snprint snsp=new Snprint(v);
			        snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        
			        snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        					          
			        v.getTxt_pweight().setText("0");	
		            v.getTxt_weight().setText("0");	
		            v.getTxt_cweight().setText("0");	 					
 			    	v.getTxt_sweight().setText("0"); 			        
					v.getTxt_deviation().setText("0");
					v.getTxt_createcode().setText("");
		        }
		        catch(NullPointerException e)
		        {
		        	e.printStackTrace();
		        }
		        catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        catch(NoClassDefFoundError e){
		        	  e.printStackTrace();
		        }
		        catch(UnsatisfiedLinkError e){
		        	   e.printStackTrace();
		        }
			    finally{
			    	 v.getTxt_cweight().setText("0");	 					
 			    	 v.getTxt_sweight().setText("0");	 			        
					 v.getTxt_deviation().setText("0");
			    }
	          } 
			else{
				JOptionPane.showMessageDialog(null, "序列号位数不正确或者没有序列号，不允许打印");
			}
		}
		else if(arg0.getActionCommand().equals("封箱")
				//&&v.getOd().ds.getValue()==0
				)
		{
			hql = "select U_enable from [@SMS] where code='CSTSN'";
			ob=appMain.lt.sqlclob(hql,0,1);
			if(!ob[0][0].toString().equals("Y"))
			{
				 JOptionPane.showMessageDialog(null,"序列号未启用");
				 return;
			}
			if(v.getDsv()!=null)
		   {
				v.getDsv().setCompany(v.getCom_company().getSelectedItem().toString());
			    if(v.getDsv().isActive()==false||v.getDsv().isVisible()==false)
	    	    {   		    		 
					 v.getDsv().setVisible(true);
					 v.getDsv().setAlwaysOnTop(true);				    		
	    	    }	
			    //JOptionPane.showMessageDialog(null, "0");
			    v.getDsv().getOd().setDocLineStatus(docLineStatus.remend);
				//v.getDsv().getOd().setGridStatus(docLineStatus.add);
			    
			    v.getDsv().getOd().setValuethrheader("123", 0, "所属大序列号");
			    v.getDsv().getOd().setDocLineStatus(docLineStatus.query);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				v.getDsv().setBounds(screenSize.width/2-310, 100, 620, 450);
				
		   }	
		}
		
		else if(arg0.getSource()==dmv.getjButtonadd())
		{		
			//test AOP permission management
			 //IDoc doc = (IDoc)appMain.ctx.getBean("bean");	
			 try {
				docf.getIdoc().add(v);
			} catch (PermissionDeniedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "无此权限");
			}
			//JOptionPane.showConfirmDialog(null, v.getCom_pweight().getSelectedItem().toString());			
			}
			else if(arg0.getSource()==dmv.getjButtoncpsource())
			{
				
			}
			else if(arg0.getSource()==dmv.getjButtonctarget())
			{
				if(v.getTxt_status().getText().equals("未清"))
				{
				   docf.getSninAbsDoc().ctarget(v, Integer.valueOf(v.getTxt_docn().getText()));
				}
				else{
					JOptionPane.showMessageDialog(null, "已清单据不能生成库存转储单");
				}
			}
			else if(arg0.getSource()==dmv.getjButtonremend())
			{
				if(v.getTxt_status().getText().equals("未清"))
				{
					v.getOd().setDocLineStatus(docLineStatus.remend);
					v.getOd().setGridStatus(docLineStatus.query);
					v.getOd1().setDs(docTitleStatus.remend);
					v.getOd1().setDocTitleStatus(v);
				}
				else{
					JOptionPane.showMessageDialog(null, "已清单据不能修改");
				}
			}
			else if(arg0.getSource()==dmv.getjButtondel())
			{
				if(v.getTxt_status().getText().equals("未清"))
				{
				   docf.getSninAbsDoc().delete(Integer.valueOf(v.getTxt_docn().getText()));
				}
				else{
					JOptionPane.showMessageDialog(null, "已清单据不能删除");
				}
			}
			else if(arg0.getSource()==dmv.getjButtonsave())
			{	
				if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").toString().equals("")&&
				v.getOd().getValuethrheader(0, "实际库存数量")!=null&&!v.getOd().getValuethrheader(0, "实际库存数量").toString().equals("")){		  				
				try{
					  v.getJt().getCellEditor().stopCellEditing();	
				    }
					catch(NullPointerException e0){
						//e0.printStackTrace();
						//return;
					}
				 for(int j=0;j<v.getOd().getRowCount();j++)
				  {
					  if(v.getOd().getValuethrheader(j, "仓库")!=null&&v.getOd().getValuethrheader(j, "仓库").toString().contains(","))
					  {
						  v.getOd().setValuethrheader(v.getOd().getValuethrheader(j, "仓库").toString().split(",")[0], j, "仓库");
					  }
					  else{
						  continue;
					  }
				  }
				 
				docf.getSninAbsDoc().create(v);
				}
			    else{JOptionPane.showMessageDialog(null, "单身没有数据，请录入数据");}
			}
			else if(arg0.getSource()==dmv.getjButtonquery())
		   {	
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='SNINQUE' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "无此权限");
	        	   return;
	             }					
				hql = "select case when len(cast(p.u_uid as nvarchar(10)))=1 then '0'+cast(p.u_uid as nvarchar(10)) " +
					  "else cast(p.u_uid as nvarchar(10)) end+p.u_subuid+','+p.u_name from [@USERS] as p " +
				      " where case when len(cast(p.u_uid as nvarchar(10)))=1 then '0'+cast(p.u_uid as nvarchar(10)) " +
				      "else cast(p.u_uid as nvarchar(10)) end" +
				      "+p.u_subuid like :str1 or p.u_name like :str2";
		   	    hql1="select case when len(cast(p.u_uid as nvarchar(10)))=1 then '0'+cast(p.u_uid as nvarchar(10)) " +
		   	    	 "else cast(p.u_uid as nvarchar(10)) end+p.u_subuid from [@USERS] as p " +
					 " where case when len(cast(p.u_uid as nvarchar(10)))=1 then '0'+cast(p.u_uid as nvarchar(10)) " +
				      "else cast(p.u_uid as nvarchar(10)) end+p.u_subuid=:str1";	
		   	    hql2 = "select cast(p.whscode as nvarchar(10))+','+p.whsName from owhs as p " +
		 			" where cast(p.whscode as nvarchar(10)) like :str1 or p.whsname like :str2";
	 	   	    hql3="select p.whscode from owhs as p " +
	 				" where cast(p.whscode as nvarchar(10))=:str1";	
				QueryWindowView<SninView> qwv=new QueryWindowView<SninView>(v,docf.getQDoc(),v.getOd1(),hql,hql1,hql2,hql3);
				
				v.getParent().add(qwv);
				qwv.setVisible(true);
				qwv.getlab_Rsaleperson().setText("机号");
				qwv.getlab_Rcard().setText("仓库");
							
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				qwv.setBounds(screenSize.width/2-240, 100, 480, 250);			
			    	  
			}	
			else if(arg0.getSource()==dmv.getjButtonaddrow())
			{
				
			}	
			else if(arg0.getSource()==dmv.getjButtondelrow())
			{				
			   
			}	
			else if(arg0.getSource()==dmv.getjButtonfirst())
			{	
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='SNINQUE' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "无此权限");
	        	   return;
	             }	
				docf.getSninAbsDoc().setValues(v, docf.getSninAbsDoc().getfirst(),"库存转储");	
				v.getOd().setDocLineStatus(docLineStatus.query);
				v.getOd1().setDs(docTitleStatus.query);
				v.getOd1().setDocTitleStatus(v);
				dmv.setquery();
			}
			else if(arg0.getSource()==dmv.getjButtonprev())
			{  
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 " and b.code='SNINQUE' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "无此权限");
	        	   return;
	             }	
				if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
				{	
					try{
					    docf.getSninAbsDoc().setValues(v,docf.getSninAbsDoc().getprev(Integer.valueOf(v.getTxt_docn().getText())),"库存转储");
					}
					catch(NumberFormatException e0){
						
					}			    
					v.getOd().setDocLineStatus(docLineStatus.query);
					v.getOd1().setDs(docTitleStatus.query);
					v.getOd1().setDocTitleStatus(v);
					dmv.setquery();
				}
			}
			else if(arg0.getSource()==dmv.getjButtonnext())
			{  
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='SNINQUE' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "无此权限");
	        	   return;
	             }	
				if(v.getTxt_docn().getText()!=null&&!v.getTxt_docn().getText().equals(""))
				{							
					try{
						docf.getSninAbsDoc().setValues(v,docf.getSninAbsDoc().getnext(Integer.valueOf(v.getTxt_docn().getText())),"库存转储");
					}
					catch(NumberFormatException e0){
						
					}
					v.getOd().setDocLineStatus(docLineStatus.query);
					v.getOd1().setDs(docTitleStatus.query);
				    v.getOd1().setDocTitleStatus(v);
					dmv.setquery();
				}
			}
			else if(arg0.getSource()==dmv.getjButtonlast())
			{   	
				 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
				 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
			 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
			 	  	 "and b.code='SNINQUE' and a.enable='1'";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	        	   JOptionPane.showMessageDialog(null, "无此权限");
	        	   return;
	             }	
				docf.getSninAbsDoc().setValues(v, docf.getSninAbsDoc().getlast(),"库存转储");		
		
				v.getOd().setDocLineStatus(docLineStatus.query);
				v.getOd1().setDs(docTitleStatus.query);		
			    v.getOd1().setDocTitleStatus(v);
				dmv.setquery();
			}
			else if(arg0.getSource()==dmv.getjButtonprint())
			{
				docf.getSninAbsDoc().print(v);
			}
			else if(arg0.getSource()==dmv.getjButtonclose())
			{			
				docf.getSninAbsDoc().close(v);
			}
			else if(arg0.getSource()==dmv.getjButtoncancel())
			{ 	
				v.getOd1().setDs(docTitleStatus.add);
				v.getOd1().setDocTitleStatus(v);
				v.getOd().setDocLineStatus(docLineStatus.add);
				v.getOd().setGridStatus(docLineStatus.add);
				dmv.setadd();			
			}			
			else if(arg0.getSource()==dmv.getjButtonSN())
			{ 		
				hql = "select U_enable from [@SMS] where code='CSTSN'";
				ob=appMain.lt.sqlclob(hql,0,1);
				if(!ob[0][0].toString().equals("Y"))
				{
					 JOptionPane.showMessageDialog(null,"序列号未启用");
					 return;
				}
				if(v.getDsv()!=null)
			   {
				    if(v.getDsv().isActive()==false||v.getDsv().isVisible()==false)
		    	    {   		    		 
						 v.getDsv().setVisible(true);
						 v.getDsv().setAlwaysOnTop(true);	
					 	//av.setBounds(200, 60, screenSize.width-200, screenSize.height-150);			    		
		    	    }
				   
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					v.getDsv().setBounds(screenSize.width/2-310, 100, 620, 450);
					if(v.getOd1().ds.getCnValue().equals("查询"))
					{
						hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
							"a.warehouse,a.cardcode,a.memo,a.workcenter," +
							"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
					   		"from [@desn] a " +
					   		"inner join drf1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
					   		"inner join odrf c on b.docentry=c.docentry " +
					   		"where c.objtype='67' and c.docEntry='"+v.getTxt_docn().getText().trim()+"' and a.Ifdraft='1' and a.objtype='67' "+
						    "and b.u_snid='"+v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "SN行号")+"'";
					   v.getDsv().getOd().updatetable(hql, 0);
					}
					v.getDsv().getJt().setRowSelectionInterval(0, v.getDsv().getOd().getRowCount()-1);
					for(int i=0;i<v.getDsv().getOd().getRowCount();i++)
				    {
				    	if(v.getDsv().getOd().getValuethrheader(i, "序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "序列号").toString().equals("")))
						{
				    		v.getDsv().getJt().removeRowSelectionInterval(i, i);
							continue;
						}	
				    	if(!(v.getDsv().getOd().getValuethrheader(i, "所属大序列号")==null||(v.getDsv().getOd().getValuethrheader(i, "所属大序列号")!=null&&v.getDsv().getOd().getValuethrheader(i, "所属大序列号").toString().equals(""))))
				    	{
				    		v.getDsv().getJt().removeRowSelectionInterval(i, i);
				    	}
				    }
			   }	
			}
			else if(arg0.getSource()==v.getTxt_pweight())
			{			
				v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
			}
			else if(arg0.getSource()==v.getTxt_cweight())
			{			
				v.getTxt_weight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
			}
			else if(arg0.getSource()==v.getCom_specification())
			{
				v.getTxt_cweight().setText("0");
				v.getTxt_pweight().setText("0");
				v.getTxt_weight().setText("0");
				v.getTxt_sweight().setText("0");
				v.getTxt_createcode().setText("");
				v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));				       
				 try{
					 if(((JTextField)v.getCom_specification().getEditor().getEditorComponent())!=null&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().length()>=2&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().substring(0, 2).equals("TD")){
			    		  v.getCom_whsin().setEditable(true);
				    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2109","2109"));
				    	  v.getCom_whsin().setEditable(false);
				    	  return;
			    	  }
					 /*else if(new BigDecimal(v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
				      {
				    	  v.getCom_whsin().setEditable(true);
				          v.getCom_whsin().setSelectedItem(new ComboBoxItem("2107","2107"));
				          v.getCom_whsin().setEditable(false);
				    	  return;
				      }
				      else{
				    	  v.getCom_whsin().setEditable(true);
				    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2108","2108"));
				    	  v.getCom_whsin().setEditable(false);
				      }*/
			      }
			      catch (NumberFormatException e1) {  
			            e1.printStackTrace();  
			        }    
			}
			else if(arg0.getSource()==v.getBt_addsn())
			{
		        if(v.getJta_SN().getText().contains(v.getTxt_createcode().getText()))
		        {
		    	   return;
		        }
		        if(v.getTxt_cweight().getText().equals("0"))
		        {
		        	JOptionPane.showMessageDialog(null, "净重为0，不允许添加");		            
		        	return;
		        }
		        ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
				
		        snstatus sns=new snstatus();
		        try{
		            sns=isn.queryByDocId(v.getTxt_createcode().getText());
		            if(sns==null)
		            {
		            	JOptionPane.showMessageDialog(null, "数据库中无此序列号，不允许添加,必须先打印才可以生成此序列号");
		            	return;
		            }
		                v.getJta_SN().setEditable(true);
		                docf.getAdnSN().add(v, v.getTxt_createcode().getText(),true,"67","I",false,0);
		                v.getJta_SN().setEditable(false);
			        }
		        catch(NullPointerException e)
		        {
		        	
		        }
				
			}
			else{
				
			}
		
	}
	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		for(Component cpt:dmv.getComponents())
		{
			if(cpt.getClass().equals(javax.swing.JButton.class))
			{
				((JButton)cpt).addActionListener(this);
			}
			
		}
		if(v.getOd1().ds.getValue()==0)
		{
			dmv.setadd();
		}
		else if(v.getOd1().ds.getValue()==1)
		{
			dmv.setremend();
		}
		else if(v.getOd1().ds.getValue()==2)
		{
			dmv.setremend();
		}
		else if(v.getOd1().ds.getValue()==3)
		{
			dmv.setquery();
		}
		else if(v.getOd1().ds.getValue()==4)
		{
			dmv.setload();
		}
		else{
			
		}
	}
	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		v.getDsv().dispose();
	}
	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		for(Component cpt:dmv.getComponents())
		{
			if(cpt.getClass().equals(javax.swing.JButton.class))
			{
				((JButton)cpt).removeActionListener(this);
			}			
		}
		dmv.setdeactive();
	}
	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		Integer vi;
		try{
		  //JOptionPane.showMessageDialog(null,v.getJt1().getSelectedRow());
		  vi=v.getJt1().getSelectedRow();		  
		}
		catch(ClassCastException e0){
			//e0.printStackTrace();
			return;
		}

		try{
			if(v.getOd1().getValueAt(vi, 2)==null||v.getOd1().getRowCount()==0)
			{
				//JOptionPane.showMessageDialog(null,"没有单号，无法查询 ");
				return;
			}
			docf.getSninAbsDoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(vi, 2).toString()),v.getOd1().getValueAt(vi, 1).toString());				   
			 v.getOd().setDocLineStatus(docLineStatus.query);
			 v.getOd1().setDs(docTitleStatus.query);
			 v.getOd1().setDocTitleStatus(v);
			 dmv.setquery();
		}
		 catch(NumberFormatException e1){			
			 JOptionPane.showMessageDialog(null,"单据类型或者单号格式不合法或者为空，无法查询 ");
			 return;
		 }
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int[] col={v.getOd().getcolumnindex("实际收货个数"),v.getOd().getcolumnindex("标准库存数量"),v.getOd().getcolumnindex("实际库存数量"),v.getOd().getcolumnindex("计划库存数量"),v.getOd().getcolumnindex("完成库存数量")};	
		double[] s=v.getOd().sum(col);
		if(s!=null)
		{			
			v.getTxt_msum().setText(String.valueOf(s[0]));
			v.getTxt_wsum().setText(String.valueOf(s[1]));			
			v.getTxt_rsum().setText(String.valueOf(s[2]));
			v.getTxt_psum().setText(String.valueOf(s[3]));	
			v.getTxt_csum().setText(String.valueOf(s[4]));	
		}
        int lastRow = e.getLastRow();
        int mColIndex = e.getColumn();
        //JOptionPane.showMessageDialog(null,v.getOd().getValueAt(lastRow, mColIndex));	
        //deal with event of endedit,such as itemcode endedit,price enedit and so on;
        //whcih will fired when the focus left the cell	     
        if(lastRow<v.getOd().getRowCount()&&lastRow>=0&& mColIndex<v.getOd().getColumnCount()&&mColIndex>=0&&v.getOd().getValueAt(lastRow, mColIndex)!=""&&v.getOd().getValueAt(lastRow, mColIndex)!=null)
        {         
          v.getOd().valueChanged(lastRow, mColIndex,"","");
          //获得焦点
          if(mColIndex<v.getOd().getColumnCount()-1){
          v.getJt().setColumnSelectionInterval(mColIndex+1,mColIndex+1);
          }
          else{v.getJt().setColumnSelectionInterval(mColIndex,mColIndex);}
          v.getJt().setRowSelectionInterval(lastRow,lastRow);            
        }    
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
	   					
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JTable)(e.getSource())).getName()!="data-browser")
		{					
			int[] col={v.getOd().getcolumnindex("实际收货个数"),v.getOd().getcolumnindex("标准库存数量"),v.getOd().getcolumnindex("实际库存数量"),v.getOd().getcolumnindex("计划库存数量"),v.getOd().getcolumnindex("完成库存数量")};	
			double[] s=v.getOd().sum(col);
			if(s!=null)
			{			
				v.getTxt_msum().setText(String.valueOf(s[0]));
				v.getTxt_wsum().setText(String.valueOf(s[1]));			
				v.getTxt_rsum().setText(String.valueOf(s[2]));
				v.getTxt_psum().setText(String.valueOf(s[3]));	
				v.getTxt_csum().setText(String.valueOf(s[4]));	
			}
		
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==v.getTxt_length())
		{
			  hql = "select salUnitMsr,dfltWh,invntryUom,numInSale,itemName, " +
         		" UMtmd,UMtzl from Oitm where itemCode='" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText() + "'";     
		      ob=appMain.lt.clob(hql,0,1);
		      if (ob==null)
	          return;
		      try{
		    	  if(((JTextField)v.getCom_specification().getEditor().getEditorComponent())!=null&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().length()>=2&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().substring(0, 2).equals("TD")){
		    		  v.getCom_whsin().setEditable(true);
			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2109","2109"));
			    	  v.getCom_whsin().setEditable(false);
			    	  return;
		    	  }
		          /*else if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
			      {
			    	  v.getCom_whsin().setEditable(true);
			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2107","2107"));
			    	  v.getCom_whsin().setEditable(false);
			    	  return;
			      }
			      else{
			    	  v.getCom_whsin().setEditable(true);
			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2108","2108"));
			    	  v.getCom_whsin().setEditable(false);
			      }*/
		      }
		      catch (NumberFormatException e1) {  
		            e1.printStackTrace();  
		        }  	
             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 
             v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
             v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
             //v.getTxt_createcode().setText("");
		}
		else if(e.getSource()==v.getCom_specification())
		{
			v.getTxt_cweight().setText("0");
			v.getTxt_pweight().setText("0");
			v.getTxt_weight().setText("0");
			v.getTxt_sweight().setText("0");
			v.getTxt_createcode().setText("");
			v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
			 try{
				 if(((JTextField)v.getCom_specification().getEditor().getEditorComponent())!=null&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().length()>=2&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().substring(0, 2).equals("TD")){
		    		  v.getCom_whsin().setEditable(true);
			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2109","2109"));
			    	  v.getCom_whsin().setEditable(false);
			    	  return;
		    	  }
		          /*else if(new BigDecimal(v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
			      {
			    	  v.getCom_whsin().setEditable(true);
			          v.getCom_whsin().setSelectedItem(new ComboBoxItem("2107","2107"));
			          v.getCom_whsin().setEditable(false);
			    	  return;
			      }
			      else{
			    	  v.getCom_whsin().setEditable(true);
			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2108","2108"));
			    	  v.getCom_whsin().setEditable(false);
			      }*/
		      }
		      catch (NumberFormatException e1) {  
		            e1.printStackTrace();  
		        }  	   
		}
		else if(appMain.branch.equals("-2")&&e.getSource()==v.getTxt_weight())
		{
			  v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()==null?"0":v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()==null?"0":v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
			   if(new BigDecimal(v.getTxt_length().getText()==null?"0":v.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
			   {
				  v.getTxt_sweight().setText(v.getTxt_cweight().getText());			  
			   }			   
			   v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText()==null?"0":v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText()==null?"0":v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 		  
		
		}
		else{}
	}
}
