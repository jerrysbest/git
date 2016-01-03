package erp.ws.sbo.client.swing.controller;

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

import javax.comm.CommPortIdentifier;
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
import javax.swing.text.BadLocationException;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.rowStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.SNL;
import erp.ws.sbo.utils.SerialConnection;
import erp.ws.sbo.utils.SerialConnectionException;
import erp.ws.sbo.utils.Snprint;

public class OignsController  implements MouseListener,TableModelListener,
ListSelectionListener,InternalFrameListener,ActionListener,KeyListener,FocusListener {

	private OignView v;
	private DocMenuView dmv=DocMenuView.getdmv();
	private SerialConnection connection;
	private OutputStream os= null;  
	private CommPortIdentifier portId=null;
	private Object[] obj;
	private Object[][] ob;
	private Object[][] ob2;
	private String hql,hql1,hql2,hql3,yon,ifsn;
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();
	@SuppressWarnings("unchecked")
	private DaoFactory<OignView> docf=(DaoFactory<OignView>)appMain.ctx.getBean("OignFactory");	
	public OignsController(OignView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);		
	}
	public OignView getV() {
		return v;
	}
	public void setV(OignView v) {
		this.v = v;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(v.getOd().ds.getCnValue().equals("查询")||v.getOd().ds.getCnValue().equals("加载"))
		{
			return;
		}
		if(e.getKeyCode()==10&&(e.getSource()==v.getTxt_pweight()||e.getSource()==v.getTxt_length()))
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
	        	if(((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().equals("")
			    ||v.getTxt_MNo().getText().equals("")||v.getTxt_Qinspector().getText().equals("")||
	             new BigDecimal(v.getTxt_cweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            || new BigDecimal(v.getTxt_weight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            ||(new BigDecimal(v.getTxt_pweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0)
	            ||new BigDecimal(v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
	            ||new BigDecimal(v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0
	            )           
				{
	                JOptionPane.showMessageDialog(v,"物料代码,机号,净重,毛重,公司名称,不能为空或0,当米段为0时,"+v.getTxt_pweight().getText()+"皮重不能小于0,"+v.getTxt_sweight().getText()+"标准重必须大于0");
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
	 			    	 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));	 					
	 			    	 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText().toString()).setScale(3, BigDecimal.ROUND_HALF_UP)));	 			        
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 			 
					}  
					else{
		 	             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 	 	           
						 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
						 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText().toString())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 	          
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
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
	 			     if(new BigDecimal(v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
					{
	 			    	 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));	 					
	 			    	 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText().toString()).setScale(3, BigDecimal.ROUND_HALF_UP)));	 			        
						 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
		 			 
					}  
					else{
	 	             Double wgtpm=Double.valueOf(ob[0][6].toString())/(Double.valueOf(ob[0][5].toString())); 
	 	           
					 v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
					 v.getTxt_sweight().setText(String.valueOf(new BigDecimal(wgtpm*Double.valueOf(v.getTxt_length().getText().toString())).setScale(3, BigDecimal.ROUND_HALF_UP)));		 	          
					 v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));
	 			     }
					v.getTxt_createcode().setText("");
				}
				if(new BigDecimal(Math.abs(Double.valueOf(v.getTxt_deviation().getText()))/Double.valueOf(v.getTxt_sweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.003))>0)
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
				        docf.getIAdvSN().add(v, v.getTxt_createcode().getText(),true,"59","I",false,v.getJt().getSelectedRow());	
				        v.getJta_SN().setEditable(false);
				        Snprint snsp=new Snprint(v);
			            snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        
			            snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	  			             
			            //重置
			            if(new BigDecimal(v.getTxt_length().getText()).equals(new BigDecimal(0)))
			            {
			            	v.getTxt_pweight().setText("-1");	
			                v.getTxt_weight().setText("-1");	
				            v.getTxt_cweight().setText("0");	 					
		 			    	v.getTxt_sweight().setText("0"); 			        
							v.getTxt_deviation().setText("0");
							v.getTxt_createcode().setText("");
			            }
			            else{
				            v.getTxt_pweight().setText("0");	
				            v.getTxt_weight().setText("0");	
				            v.getTxt_cweight().setText("0");	 					
		 			    	v.getTxt_sweight().setText("0"); 			        
							v.getTxt_deviation().setText("0");
							v.getTxt_createcode().setText("");
			            }
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
				
		        } 
				else{
					JOptionPane.showMessageDialog(null, "序列号位数不正确或者没有序列号，不允许打印");
				}
			}
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
				   docf.getIdoc().create(v);
					
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
		if(e.getKeyCode()==10)
		{
			if(e.getSource()==v.getTxt_cppo())
			{
				
				hql = "select distinct 0,snlineno=0,a.docNum,a.itemcode,b.itemname,u_Ymd=isnull(a.u_Ymd,'N'),length=isnull(a.u_length,0),b.salunitmsr," +
			    		"unitqty=convert(decimal(18,3),(isnull(a.u_length,0)*b.u_mtzl/b.u_mtmd))," +
			    		"qty=convert(decimal(18,3),(isnull(a.u_qty,0)-isnull(a.u_cqty,0))),"+
			    		"cqty=0,"+
			    		"b.invntryuom," +   		
			    		" pqty=convert(decimal(18,3),a.plannedQty-a.cmpltQty-isnull(g.sqty,0))," +
			    		"rqty=0.000," +
			    		"0,0,a.warehouse,warec=d.warehouse,a.plannedQty,a.cmpltQty,date=convert(nvarchar(10),a.duedate,23)," +
			    		"c.u_name from owor a " +
			    		"inner join wor1 d on a.docEntry=d.docEntry " +
			    		"inner join wor1 e on a.docEntry=e.docEntry and e.linenum=0 " +
			    		"inner join oitm b on a.itemcode=b.itemcode " +
			    		"inner join owhs f on a.warehouse=f.whscode " +
			    		"left join (select sqty=sum(a.quantity), a.basetype,a.baseentry from drf1 a inner join owor b on a.baseentry=b.docentry group by a.basetype,a.baseentry) g on a.docentry=g.baseentry and g.basetype='202' " +	    		
			    		"left join ousr c on c.userid=a.usersign "+
			    		" where a.itemcode like '%'+'"+v.getTxt_cppo().getText().trim()+"'+'%' " ;
			    		if(Integer.valueOf(((ComboBoxItem)v.getCom_snware().getSelectedItem()).getValue().toString())==0)
						{ 
						  hql+=" and (f.u_sn is null or (f.u_sn is not null and f.u_sn='N')) ";
						  hql1="select u_enable from [@sms] where code='OIGNSN'";
						  if(!appMain.lt.sqlclob(hql1, 0, 1)[0][0].toString().equals("Y"))
						  {
					    	 hql+=" and b.U_Usn ='N' " ;			
						  }
						}
			    		else{
			    			 hql+=" and f.u_sn ='Y' ";
			    			 hql1="select u_enable from [@sms] where code='OIGNSN'";
							 if(appMain.lt.sqlclob(hql1, 0, 1)[0][0].toString().equals("Y"))
							 {
						    	 hql+=" and b.U_Usn ='Y' " ;			
							 }
			    		}
			    		
			    	hql+=" and a.plannedQty-a.cmpltQty-isnull(g.sqty,0)>0  and a.u_qty>a.u_cqty " +
			    		"and a.Status='R' AND a.Type='S' ";
			     hql1="select u_enable from [@sms] where code='CKCZY'";
				 if(appMain.lt.sqlclob(hql1, 0, 1)[0][0].toString().equals("Y"))
				 {
			    	 hql+=" and e.warehouse in " +
						 "(select U_Dsck from dbo.[@CZYCK] where " +
						  "U_Usid=(select distinct branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='R')";			
				 }
				 
				 hql1="select u_enable from [@sms] where code='MNO'";
				 if(appMain.lt.sqlclob(hql1, 0, 1)[0][0].toString().equals("Y"))
				 {
			    	 hql+=" and e.warehouse = " +
						 "(select whscode from owhs where " +
						  "U_mno='"+appMain.Mno+"' )";			
				 }
				
				 System.out.println("return:"+hql);
			    v.getOd2().updatetable(hql, 0);		
			}
			else if(e.getSource()==v.getJta_SN())
			{
				
			}
			else
			{}
		}		
		if(e.getKeyCode()==127&&e.getSource()==v.getJt())
		{ 
			int[] i=v.getJt().getSelectedRows();
			for(int k=0;k<i.length;k++)
			{				
			 try{		
			     i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 JOptionPane.showMessageDialog(null, "oignscontroller-keyreased-127");
				 System.out.println("oignscontroller-keyreased-127");
			 }			
			}
		    v.getOd().delRow(i);		   
		    try{
			    if(i[0]>0)
			    {
				   v.getJt().setRowSelectionInterval(i[0]-1, i[0]-1);
			    }
			    else
			    {
			    	v.getJt().setRowSelectionInterval(0, 0);
			    }	
		    }
		    catch(ArrayIndexOutOfBoundsException e0){
		    	
		    }
		    catch(IllegalArgumentException e1){
		    	
		    }
		  
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
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("实际收货个数"))
		{
	       //v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
	      
		}
		if(v.getJt().getSelectedColumn()== v.getOd().getcolumnindex("实际库存数量"))
		{
			 //v.getJt().editCellAt(v.getJt().getSelectedRow(), v.getJt().getSelectedColumn());
	      
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 //序列号相关
		//JOptionPane.showMessageDialog(v, v.getOd().ds.getValue());
	    if(e.getActionCommand().equals("打开串口")&&v.getOd().ds.getValue()==5)
		{		 
	    	JOptionPane.showMessageDialog(v, "串口打开");
	    	v.getCom_port().removeAllItems();	  
	        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
	        
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
		else if(e.getActionCommand().equals("关闭串口")&&v.getOd().ds.getValue()==5)
		{
			JOptionPane.showMessageDialog(v, "串口关闭");
			if(connection!=null&&connection.isOpen())
			{
			  connection.closeConnection();
			}
			v.getCom_port().removeAllItems();
			v.getBt_open().setEnabled(true);
			v.getBt_close().setEnabled(false);
			v.getBt_cweight().setEnabled(false);
			v.getBt_weight().setEnabled(false);
			
		}		
		else if(e.getActionCommand().equals("净重")&&v.getOd().ds.getValue()==5)
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
		else if(e.getActionCommand().equals("毛重")&&v.getOd().ds.getValue()==5)
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
	             } catch (IOException e1) {  
	            e1.printStackTrace();  
	        } 
	        catch (NullPointerException e1) {  
	            e1.printStackTrace();  
	        }  
		}
		else if(e.getActionCommand().equals("保存打印设置")&&v.getOd().ds.getValue()==5)
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
		else if(e.getActionCommand().equals("生成序列号")&&v.getOd().ds.getValue()==5)
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
            ||(new BigDecimal(v.getTxt_pweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0)
            ||new BigDecimal(v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))
            ||new BigDecimal(v.getTxt_sweight().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP))<0          
			 )           					
			{
                JOptionPane.showMessageDialog(v,"物料代码,机号,净重,毛重,公司名称,不能为空或0,皮重不能小于0,标准重必须大于0");
                return;
             }	
			if(new BigDecimal(Math.abs(Double.valueOf(v.getTxt_deviation().getText()))/Double.valueOf(v.getTxt_sweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.003))>0)
			{
				int i0=JOptionPane.showConfirmDialog(null, "误差大于千分之三,是否继续?");
				if(i0!=0)
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
		else if(e.getActionCommand().equals("选择序列号")&&v.getOd().ds.getValue()==5)
		{						
			SNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");	
            snstatus sns=new snstatus(); 
            sns=isn.queryByDocId(((JTextField)v.getCom_selcode().getEditor().getEditorComponent()).getText());   
            v.getTxt_MNo().setText(sns.getMno());
            v.getTxt_length().setText(sns.getLength().toString());
            v.getTxt_pweight().setText(sns.getPweight().toString());
            v.getTxt_weight().setText(sns.getWeight().toString());
            v.getTxt_cweight().setText(sns.getCweight().toString());
            v.getTxt_sweight().setText(sns.getSweight().toString());
            v.getTxt_Qinspector().setText(sns.getQc());
            v.getTxt_deviation().setText(String.valueOf(new BigDecimal(sns.getWeight()-sns.getSweight()).setScale(3, BigDecimal.ROUND_HALF_UP)));
            v.getCom_specification().setSelectedItem(sns.getItemcode());  
            v.getTxt_createcode().setText(((JTextField)v.getCom_selcode().getEditor().getEditorComponent()).getText());
		}
		
		else if(e.getSource()==v.getBt_print()&&v.getOd().ds.getValue()==5)
		{
			if(v.getTxt_createcode().getText()!=null&&!v.getTxt_createcode().getText().equals("")&&v.getTxt_createcode().getText().length()==18)
			{			
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
			        docf.getIAdvSN().add(v, v.getTxt_createcode().getText(),true,"59","I",false,v.getJt().getSelectedRow());	
			        v.getJta_SN().setEditable(false);		
					Snprint snsp=new Snprint(v);
			        snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        
			        snsp.print(v.getTxt_width().getText(), v.getTxt_height().getText(), "5", "8", "0", "0", "0", "128", v.getTxt_createcode().getText(),v);	        					          	        
			        //重置
		            if(new BigDecimal(v.getTxt_length().getText()).equals(new BigDecimal(0)))
		            {
		            	v.getTxt_pweight().setText("-1");	
		                v.getTxt_weight().setText("-1");	
			            v.getTxt_cweight().setText("0");	 					
	 			    	v.getTxt_sweight().setText("0"); 			        
						v.getTxt_deviation().setText("0");
						v.getTxt_createcode().setText("");
		            }
		            else{
			            v.getTxt_pweight().setText("0");	
			            v.getTxt_weight().setText("0");	
			            v.getTxt_cweight().setText("0");	 					
	 			    	v.getTxt_sweight().setText("0"); 			        
						v.getTxt_deviation().setText("0");
						v.getTxt_createcode().setText("");
		            }
		        }
		        catch(NullPointerException e1)
		        {
		        	e1.printStackTrace();
		        }
		        catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        catch(NoClassDefFoundError e1){
		        	  e1.printStackTrace();
		        }
		        catch(UnsatisfiedLinkError e1){
		        	   e1.printStackTrace();
		        }
			    finally{
			    	 
			    }
	          } 
			else{
				JOptionPane.showMessageDialog(null, "序列号位数不正确或者没有序列号，不允许打印");
			}
		}
		else if(e.getSource()==v.getCom_type())
		{			
			if(Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString())==0)
			{			    
				v.getTxt_cppo().setVisible(true);				       	
				v.getCom_plist().setVisible(false);					
				v.getCom_whs().setVisible(false);
				v.getJta_SN().setVisible(true);
				v.getBt_cppo().setVisible(true);
				hql="select u_enable from [@sms] where code='OIGNSN'";
				 ifsn=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();
				 if(ifsn.equals("Y"))
				 {
						v.getCom_snware().setVisible(true);
				 }

				v.getJt().HiddenCell(v.getOd().getcolumnindex("生产订单号"),80);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("计划库存数量"),80);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("完成库存数量"),80);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("计划完工日期"),80);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("出库仓库"),80);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("操作员"),80);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("SN行号"),60);
				v.getOd().setGridStatus(docLineStatus.add);
				v.getOd().setDocLineStatus(docLineStatus.oign);
				hql="select isnull(max(docNum),0) from Oign";
				Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;			
				v.getTxt_docn().setText(Ndoce.toString());	
				hql="select isnull(max(docEntry),0) from Oign";
				Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;			
				v.getTxt_docnid().setText(Ndoce.toString());	
				
			}
			else{	
				v.getTxt_cppo().setVisible(false);
				v.getCom_plist().setVisible(true);
				v.getCom_snware().setVisible(false);
				v.getCom_whs().setVisible(true);
				v.getJta_SN().setVisible(false);
				v.getBt_cppo().setVisible(false);
				 hql="select u_enable from [@sms] where code='OIGNSN'";
				 ifsn=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();
				 if(ifsn.equals("Y"))
				 {
					 v.getPane7().removeAll();
					 v.getPane8().removeAll();
				 }
				v.getJt().HiddenCell(v.getOd().getcolumnindex("生产订单号"),0);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("计划库存数量"),0);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("完成库存数量"),0);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("计划完工日期"),0);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("出库仓库"),0);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("操作员"),30);
				v.getJt().HiddenCell(v.getOd().getcolumnindex("SN行号"),40);
				v.getOd().setGridStatus(docLineStatus.add);
			    v.getOd().setDocLineStatus(docLineStatus.tran);
			    hql="select isnull(max(docNum),0) from Owtr";
				Integer Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;			
				v.getTxt_docn().setText(Ndoce.toString());	
				hql="select isnull(max(docEntry),0) from Owtr";
				Ndoce= (Integer) appMain.lt.sqlclob(hql, 0, 1)[0][0]+1;			
				v.getTxt_docnid().setText(Ndoce.toString());	
				
				 hql="select u_enable from [@sms] where code='CKCZY'";
				 yon=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();				
										 
				 //绑定仓库
				 hql= "select p.whsCode+','+p.whsName from Owhs as p " +
							" where (p.whsCode like :str1 or p.whsName like :str2) ";
				 if(yon.equals("Y"))
				 {
				    hql+=" and p.WhsCode in " +
					 	  "(select U_Dsck from dbo.[@CZYCK] where " +
					 	  "U_Usid=(select distinct branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='Z')";
				 }
				  hql1="select p.whsCode from Owhs as p " +
							" where p.whsCode=:str1 ";
				 if(yon.equals("Y"))
				 {
					hql1+=" and p.WhsCode in " +
					 	"(select U_Dsck from dbo.[@CZYCK] where " +
					 	"U_Usid=(select distinct branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='Z')";
				 }
				 v.getOd().setUpSportColumn(v.getJt(), v.getJt().getColumnModel().getColumn(v.getOd().getcolumnindex("仓库")), hql,hql1);
			 
		   }
		}
		else if(e.getSource()==v.getCom_snware())
		{
			//是序列号设置表体不可编辑
			if(Integer.valueOf(((ComboBoxItem)v.getCom_snware().getSelectedItem()).getValue().toString())==1)
			{
				v.getOd().setGridStatus(docLineStatus.add);
				v.getOd().setrowStatus(rowStatus.sn);				
			}
			else{
				v.getOd().setGridStatus(docLineStatus.add);
				v.getOd().setrowStatus(rowStatus.nsn);			
			}
			
		}
		else if(e.getSource()==v.getBt_cppo())
		{  		
			int[] i=v.getJt2().getSelectedRows();
			if(Integer.valueOf(((ComboBoxItem)v.getCom_snware().getSelectedItem()).getValue().toString())==1)
			{   
				if(i.length>1)
				{
					JOptionPane.showMessageDialog(null, "选择序列号仓库，只能选择一个生产订单，请重新选择");
					return;
				}
				for(int ik=0;ik<v.getOd().getRowCount();ik++)
				{
					if(v.getOd().getValuethrheader(ik, "物料代码")!=null&&!v.getOd().getValuethrheader(ik, "物料代码").toString().equals(""))
					{
						JOptionPane.showMessageDialog(null, "选择序列号仓库，只能选择一个生产订单,不允许追加");
						return;
					}					
				}
				
			}
			for(int k=0;k<i.length;k++)
			{				
			 try{		
			    i[k]=Integer.valueOf(v.getJt2().convertRowIndexToModel(v.getJt2().getSelectedRows()[k]));			   
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 e0.printStackTrace();
				 System.out.println("oignscontroller-actionPerformed-getbt_cppo");
			 }			
			}
			if(Integer.valueOf(((ComboBoxItem)v.getCom_snware().getSelectedItem()).getValue().toString())==1)
			{	  
				v.getOd().setGridStatus(docLineStatus.add);
			}
		    ob=v.getOd().getDataSet();
		    ob2=v.getOd2().getDataSet();
		    boolean tf=false;
		    int inx=v.getOd2().getcolumnindex("生产订单号");
		    for(int k=0;k<i.length;k++)
		    {  
		    	tf=false;
		    	for(int j=0;j<ob.length;j++)
		    	{
		    		if(ob[j][inx]!=null&&ob2[i[k]][inx]!=null&&ob[j][inx].toString().equals(ob2[i[k]][inx].toString()))
		    		{
		    			tf=true;
		    			break;		    	   
		    		}
		    	}
		    	if(tf)
		    	continue;
		    	for(int j=0;j<ob.length;j++)
		    	{
		    		if(ob[j][inx]==null||ob[j][inx].toString().equals(""))
			    	{   
		    			for(int l=0;l<v.getOd2().getColumnCount();l++)
				    	{			    		
				    		ob[j][l]=ob2[i[k]][l];
				    	}
		    			break;
			    	}
		    	}
		    }
		    v.getOd().setDataSet(ob);
		    v.getCom_specification().setEnabled(true);
		    v.getCom_specification().setSelectedItem( new  ComboBoxItem(v.getOd().getValuethrheader(0,"物料代码").toString(),v.getOd().getValuethrheader(0,"物料代码").toString()));
		    v.getCom_specification().setEnabled(false);
		   
		    v.getCom_whsin().setEnabled(true);
		    v.getCom_whsin().setSelectedItem( new  ComboBoxItem(v.getOd().getValuethrheader(0,"仓库").toString(),v.getOd().getValuethrheader(0,"仓库").toString()));
		    v.getCom_whsin().setEnabled(false);
		    
		    v.getTxt_length().setEditable(true);	
		    v.getTxt_length().setText("0");
		    if(v.getOd().getValuethrheader(0,"是否米段线").toString().equals("否")||v.getOd().getValuethrheader(0,"是否米段线").toString().equals("N"))
			{
		       v.getTxt_length().setEditable(false);
			}
		    
		   
		    v.getJt().setRowSelectionInterval(0, 0);
		}
		else if(e.getSource()==dmv.getjButtonadd())
		{
			//JOptionPane.showMessageDialog(null, "1");
		   // something about doctitle
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			//JOptionPane.showMessageDialog(null, "2");
			//something about docline
			v.getOd().setDocLineStatus(docLineStatus.oign);
			v.getOd().setGridStatus(docLineStatus.add);
			//JOptionPane.showMessageDialog(null, "3");
			
			
			dmv.setadd();
		}
		else if(e.getSource()==dmv.getjButtoncpsource())
		{
			
		}
		else if(e.getSource()==dmv.getjButtonctarget())			
		{
			hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
		 	ob = appMain.lt.sqlclob(hql,0,1);
			hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
		 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
		 	  	 "and b.name='生成生产收货单' and a.enable='1'";
		 	ob = appMain.lt.sqlclob(hql,0,1);
	        if(ob==null||ob.length==0)
	        {
	    	   JOptionPane.showMessageDialog(null, "无此权限");
	    	   return;
	        }
			if(v.getTxt_status().getText().equals("收货草稿")&&v.getTxt_status1().getText().equals("未清"))
			{		   
			   docf.getIdoc().ctarget(v, Integer.valueOf(v.getTxt_docnid().getText()));			   
			}
			else{
			   JOptionPane.showMessageDialog(null, "只有未清的收货草稿才能生成生产收货单");
			}
		}
		else if(e.getSource()==dmv.getjButtonremend())
		{
			
		}
		else if(e.getSource()==dmv.getjButtonsave())
		{	
			if(v.getOd().getValuethrheader(0, "物料代码")!=null&&!v.getOd().getValuethrheader(0, "物料代码").toString().equals("")&&
			v.getOd().getValuethrheader(0, "实际库存数量")!=null&&!v.getOd().getValuethrheader(0, "实际库存数量").toString().equals(""))
			{		  				
				try{
					  v.getJt().getCellEditor().stopCellEditing();	
				    }
					catch(NullPointerException e0){
						//e0.printStackTrace();
						//return;
					}
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
				hql = "select U_enable from [@SMS] where code='OIGNSN'";
				ob=appMain.lt.sqlclob(hql,0,1);
				if(ob[0][0].toString().equals("Y"))
				 {
					SNL snl=new SNL();
					 try {
						if(snl.verificationSN(v.getJta_SN(), false, v.getDsv()))
						 {
						     docf.getIdoc().create(v);
						 }
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				else{
					 docf.getIdoc().create(v);
				}
				 
			}
		    else{JOptionPane.showMessageDialog(null, "单身没有数据，请录入数据");}
		}
		else if(e.getSource()==dmv.getjButtonquery())
	   {	
			hql = "select cast(p.user_code as nvarchar(10))+','+p.U_Name from ousr as p " +
			    " where cast(p.user_code as nvarchar(10)) like :str1 or p.u_name like :str2";
	   	    hql1="select p.user_code from ousr as p " +
				" where cast(p.user_code as nvarchar(10))=:str1";	
	   	    hql2 = "select cast(p.whscode as nvarchar(10))+','+p.whsName from owhs as p " +
	 			" where cast(p.whscode as nvarchar(10)) like :str1 or p.whsname like :str2";
 	   	    hql3="select p.whscode from owhs as p " +
 				" where cast(p.whscode as nvarchar(10))=:str1";	
			QueryWindowView<OignView> qwv=new QueryWindowView<OignView>(v,docf.getQDoc(),v.getOd1(),hql,hql1,hql2,hql3);
			
			v.getParent().add(qwv);
			qwv.setVisible(true);
			qwv.getlab_Rsaleperson().setText("操作员");
			qwv.getlab_Rcard().setText("仓库");
			qwv.getLab_draft().setText("单据类型");
			qwv.getJcb_draft().removeAllItems();
			ComboBoxItem cbm=new ComboBoxItem(0,"生产收货草稿");
		   	qwv.getJcb_draft().addItem(cbm);
		    cbm=new ComboBoxItem(1,"生产收货");
		    qwv.getJcb_draft().addItem(cbm);
		    cbm=new ComboBoxItem(2,"库存转储");
		    qwv.getJcb_draft().addItem(cbm);
		
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			qwv.setBounds(screenSize.width/2-240, 100, 480, 250);
		
		    	  
		}	
		else if(e.getSource()==dmv.getjButtonaddrow())
		{
			obj=new Object[v.getOd().getColumnCount()];	
			//v.getJt().getSelectedRow();
			v.getOd().insertRow(v.getJt().getSelectedRow()+1, obj);
		}	
		else if(e.getSource()==dmv.getjButtondelrow())
		{
			int[] i=v.getJt().getSelectedRows();
			for(int k=0;k<i.length;k++)
			{				
			 try{		
			 i[k]=Integer.valueOf(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRows()[k]));
			 }
			 catch(ArrayIndexOutOfBoundsException e0)
			 {
				 JOptionPane.showMessageDialog(null, "oignscontroller-actionPerformed-delrow");
			 }
			
			}
		    v.getOd().delRow(i);		   
		    try{
			    if(i[0]>0)
			    {
				   v.getJt().setRowSelectionInterval(i[0]-1, i[0]-1);
			    }
			    else
			    {
			    	v.getJt().setRowSelectionInterval(0, 0);
			    }	
		    }
		    catch(ArrayIndexOutOfBoundsException e0){
		    	
		    }
		    catch(IllegalArgumentException e1){
		    	
		    }
		   
		}	
		else if(e.getSource()==dmv.getjButtonfirst())
		{			
			docf.getIdoc().setValues(v, docf.getIdoc().getfirst(),"收货草稿");	
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprev())
		{  
			if(v.getTxt_docnid().getText()!=null&&!v.getTxt_docnid().getText().equals(""))
			{	
				try{
				    docf.getIdoc().setValues(v,docf.getIdoc().getprev(Integer.valueOf(v.getTxt_docnid().getText())),"收货草稿");
				}
				catch(NumberFormatException e0){
					
				}			    
				v.getOd().setDocLineStatus(docLineStatus.query);
				v.getOd1().setDs(docTitleStatus.query);
				v.getOd1().setDocTitleStatus(v);
				dmv.setquery();
			}
		}
		else if(e.getSource()==dmv.getjButtonnext())
		{  
			if(v.getTxt_docnid().getText()!=null&&!v.getTxt_docnid().getText().equals(""))
			{							
				try{
					docf.getIdoc().setValues(v,docf.getIdoc().getnext(Integer.valueOf(v.getTxt_docnid().getText())),"收货草稿");
				}
				catch(NumberFormatException e0){
					
				}
				v.getOd().setDocLineStatus(docLineStatus.query);
				v.getOd1().setDs(docTitleStatus.query);
			    v.getOd1().setDocTitleStatus(v);
				dmv.setquery();
			}
		}
		else if(e.getSource()==dmv.getjButtonlast())
		{   		   
			docf.getIdoc().setValues(v, docf.getIdoc().getlast(),"收货草稿");		
	
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd1().setDs(docTitleStatus.query);		
		    v.getOd1().setDocTitleStatus(v);
			dmv.setquery();
		}
		else if(e.getSource()==dmv.getjButtonprint())
		{
			docf.getIdoc().print(v);
		}
		else if(e.getSource()==dmv.getjButtonclose())
		{			
			docf.getIdoc().close(v);
		}
		else if(e.getSource()==dmv.getjButtoncancel())
		{ 	
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();			
		}			
		else if(e.getSource()==dmv.getjButtonSN())
		{ 		
			if(Integer.valueOf(((ComboBoxItem)v.getCom_type().getSelectedItem()).getValue().toString())==1)
			{			
				return;
			}
			hql = "select U_enable from [@SMS] where code='OIGNSN'";
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
				v.getDsv().getBt_package().setVisible(false);
				if(v.getOd1().ds.getCnValue().equals("查询")&&v.getTxt_status().getText().equals("生产收货"))
				{
					hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,cardcode='',a.memo,a.workcenter," +
						"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join ign1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
				   		"inner join oign c on b.docentry=c.docentry " +
				   		"where b.basetype='202' and c.docEntry='"+v.getTxt_docnid().getText().trim()+"' and a.Ifdraft='0' " +
				   		"and b.u_snid='"+ v.getOd().getValuethrheader(v.getJt().getSelectedRow(), "SN行号").toString()+"'";
				   v.getDsv().getOd().updatetable(hql, 0);
				}	
				else if(v.getOd1().ds.getCnValue().equals("查询")&&v.getTxt_status().getText().equals("收货草稿")){
					hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,cardcode='',a.memo,a.workcenter," +
						"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join drf1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
				   		"inner join odrf c on b.docentry=c.docentry " +
				   		"where b.basetype='202' and c.docEntry='"+v.getTxt_docnid().getText().trim()+"' and a.Ifdraft='1' " +
				   		"and b.u_snid='"+ v.getOd().getValuethrheader(v.getJt().getSelectedRow(), "SN行号").toString()+"'";
				   v.getDsv().getOd().updatetable(hql, 0);
				}
				else if(v.getOd1().ds.getCnValue().equals("查询")&&v.getTxt_status().getText().equals("库存转储")){
					hql="select 0,a.Ifdraft,a.objtype,a.docentry,a.linenum,a.sn,a.itemcode,a.length,a.weight,a.direction,a.ifpasn,a.pasn," +
						"a.warehouse,a.cardcode,a.memo,a.workcenter," +
						"cdatetime=convert(nvarchar(10),a.cdatetime,23),udatetime=convert(nvarchar(10),a.udatetime,23) " +
				   		"from [@desn] a " +
				   		"inner join wtr1 b on a.docentry=b.docentry and a.linenum=b.u_snid " +
				   		"inner join owtr c on b.docentry=c.docentry " +
				   		"where c.objtype='67' and c.docEntry='"+v.getTxt_docnid().getText().trim()+"' and a.Ifdraft='0' and a.objtype='67' "+
					    "and b.u_snid='"+v.getOd().getValuethrheader(v.getJt().convertRowIndexToModel(v.getJt().getSelectedRow()), "SN行号")+"'";
					   v.getDsv().getOd().updatetable(hql, 0);
				}
				else{}
		   }
			
		}
		else if(e.getSource()==v.getTxt_pweight())
		{			
			v.getTxt_cweight().setText(String.valueOf(new BigDecimal(v.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
		}
		else if(e.getSource()==v.getTxt_cweight())
		{			
			v.getTxt_weight().setText(String.valueOf(new BigDecimal(v.getTxt_cweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(v.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));
		}
		else if(e.getSource()==v.getCom_specification())
		{
			v.getTxt_cweight().setText("0");
			v.getTxt_pweight().setText("0");
			v.getTxt_weight().setText("0");
			v.getTxt_sweight().setText("0");
			v.getTxt_createcode().setText("");
			v.getTxt_deviation().setText(String.valueOf(new BigDecimal(Double.valueOf(v.getTxt_sweight().getText())-Double.valueOf(v.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));				       
			 /*try{
				 if(((JTextField)v.getCom_specification().getEditor().getEditorComponent())!=null&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().length()>=2&&((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText().substring(0, 2).equals("TD")){
		    		  v.getCom_whsin().setEditable(true);
			    	  v.getCom_whsin().setSelectedItem(new ComboBoxItem("2109","2109"));
			    	  v.getCom_whsin().setEditable(false);
			    	  return;
		    	  }
		          else if(new BigDecimal(v.getTxt_length().getText()).compareTo(new BigDecimal("0"))==0)
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
			      }
		      }
		      catch (NumberFormatException e1) {  
		            e1.printStackTrace();  
		        }*/  	   
		}
		else if(e.getSource()==v.getBt_addsn())
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
	            	JOptionPane.showMessageDialog(null, "数据库中无此序列号,不允许添加,必须先打印才可以生成此序列号");
	            	return;
	            }
	                v.getJta_SN().setEditable(true);
	                docf.getIAdvSN().add(v, v.getTxt_createcode().getText(),true,"59","I",false,v.getJt().getSelectedRow());
	                v.getJta_SN().setEditable(false);
		        }
	        catch(NullPointerException e1)
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
		//JOptionPane.showMessageDialog(v, "串口关闭");
		if(connection!=null&&connection.isOpen())
		{
		  connection.closeConnection();
		}
		v.getCom_port().removeAllItems();
		v.getBt_open().setEnabled(true);
		v.getBt_close().setEnabled(false);
		v.getBt_cweight().setEnabled(false);
		v.getBt_weight().setEnabled(false);
		
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
	public void valueChanged(ListSelectionEvent e) {
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
			docf.getIdoc().setValues(v,Integer.valueOf(v.getOd1().getValueAt(vi, 2).toString()),v.getOd1().getValueAt(vi, 1).toString());				   
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
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	public DaoFactory<OignView> getDocf() {
		return docf;
	}
	public void setDocf(DaoFactory<OignView> docf) {
		this.docf = docf;
	}

}
