package erp.ws.sbo.client.swing.controller;


import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.view.LeftMenu.LeftMenuView;
import erp.ws.sbo.client.swing.view.OOige.OOigeView;
import erp.ws.sbo.client.swing.view.OOign.OOignView;
import erp.ws.sbo.client.swing.view.Oige.OigeView;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;
import erp.ws.sbo.client.swing.view.Orct.OrctView;
import erp.ws.sbo.client.swing.view.Ordr.OrdrView;
import erp.ws.sbo.client.swing.view.Orin.OrinView;
import erp.ws.sbo.client.swing.view.Owor.OworView;
import erp.ws.sbo.client.swing.view.PaSN.PaSNView;
import erp.ws.sbo.client.swing.view.Qr.QrView;
import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.utils.DbUtils;

public class LeftMenusController implements TreeSelectionListener 
          ,MouseListener{
   private LeftMenuView v;
   public LeftMenusController(LeftMenuView v)
   {
	   this.v=v;
   }
   private DefaultMutableTreeNode   selectionNode;
   private OrdrView ov;
   private OinvView oiv;
   private OrinView oriv;
   private OrctView orv;
   private OworView owv;
   private OignView oigv;
   private OOignView ooigv;
   private SninView sninv;
   private OigeView oigev;
   private OOigeView ooigev;
   private PaSNView pv;
   private QrView qv;
   private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	
   private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private String hql;
   private Object[][] ob;
   public void valueChanged(TreeSelectionEvent   e) {
		
	}
   
   public LeftMenuView getLeftMenuView() {
		return v;
	}

	public void setLeftMenuView(LeftMenuView v) {
		this.v = v;
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		  JTree   tree   =   (JTree)e.getSource(); 
		  selectionNode=
		        (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();//得到选中的节点 		
		 
		  if(selectionNode!=null&&selectionNode.getChildCount()==0) 
		  { 
		 	 hql="select user_code from ousr where userid='"+appMain.oCompany.getUserSignature().toString()+"'";
		 	 ob = appMain.lt.sqlclob(hql,0,1);
			 hql="select * from dbo.[@userauther] a inner join dbo.[@auther] b on a.autherid=b.code" +
		 	  	 " where a.usercode='"+ob[0][0].toString()+"' " +
		 	  	 "and b.name='"+selectionNode.getUserObject().toString()+"' and a.enable='1'";
		 	 ob = appMain.lt.sqlclob(hql,0,1);
             if(ob==null||ob.length==0)
             {
        	   JOptionPane.showMessageDialog(null, "无此权限");
        	   return;
             }
			 if(selectionNode.getUserObject().toString()=="销售订单")
		 	 { 		 			 		 		 					   
			      if(ov==null)
			      {
			    	ov=new OrdrView();
			 	    v.getParent().add(ov);	
			 	    ov.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(ov.isClosed())
			    	  {
			    		ov.dispose();
			    		ov=new OrdrView();
					 	v.getParent().add(ov);	
					 	ov.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!ov.isSelected())
			    	  {
			    		  try {
							ov.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }
			    	  else{
			    	  try {
						ov.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  ov.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  ov.getOd1().setDs(docTitleStatus.load);
					  ov.getOd1().setDocTitleStatus(ov);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="备货单")
		 	 { 		 	
		 						   
			      if(oiv==null)
			      {
			    	oiv=new OinvView();
			 	    v.getParent().add(oiv);	
			 	    oiv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(oiv.isClosed())
			    	  {
			    		oiv.dispose();
			    		oiv=new OinvView();
					 	v.getParent().add(oiv);	
					 	oiv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!oiv.isSelected())
			    	  {
			    		  try {
							oiv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						oiv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  oiv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  oiv.getOd1().setDs(docTitleStatus.load);
					  oiv.getOd1().setDocTitleStatus(oiv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="批量收款单")
		 	 { 		 	
		 						   
			      if(orv==null)
			      {
			    	orv=new OrctView();
			 	    v.getParent().add(orv);	
			 	    orv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(orv.isClosed())
			    	  {
			    		orv.dispose();
			    		orv=new OrctView();
					 	v.getParent().add(orv);	
					 	orv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!orv.isSelected())
			    	  {
			    		  try {
							orv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						orv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  orv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  orv.getOd1().setDs(docTitleStatus.load);
					  orv.getOd1().setDocTitleStatus(orv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="生产订单")
		 	 { 		 	
		 						   
			      if(owv==null)
			      {
			    	owv=new OworView();
			 	    v.getParent().add(owv);	
			 	    owv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(owv.isClosed())
			    	  {
			    		owv.dispose();
			    		owv=new OworView();
					 	v.getParent().add(owv);	
					 	owv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!owv.isSelected())
			    	  {
			    		  try {
							owv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						owv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  owv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  owv.getOd1().setDs(docTitleStatus.load);
					  owv.getOd1().setDocTitleStatus(owv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="生产收货")
		 	 { 		 	
		 						   
			      if(oigv==null)
			      {
			    	oigv=new OignView();
			 	    v.getParent().add(oigv);	
			 	    oigv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(oigv.isClosed())
			    	  {
			    		oigv.dispose();
			    		oigv=new OignView();
					 	v.getParent().add(oigv);	
					 	oigv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!oigv.isSelected())
			    	  {
			    		  try {
							oigv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						oigv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  oigv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  oigv.getOd1().setDs(docTitleStatus.load);
					  oigv.getOd1().setDocTitleStatus(oigv);
			    	  }			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="订单建议格式一")
		 	 { 		 			 		 
		 		 hql="select U_URL from [@REPORTURL] " +
			 	  	 " where name='订单建议格式一' ";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
	           	   return;
	             }
		 		 try {
					Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		 	    
		 	   
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="订单建议格式二")
		 	 { 		 			 		 
		 		 hql="select U_URL from [@REPORTURL] " +
			 	  	 " where name='订单建议格式二' ";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
	           	   return;
	             }
	             hql = "exec zdy_details_wm";
	 			 dbu.exeSql(hql);
		 		 try {
					Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 	    
		 	   
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="订单建议格式三")
		 	 { 		 			 		 
		 		 hql="select U_URL from [@REPORTURL] " +
			 	  	 " where name='订单建议格式三' ";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
	           	   return;
	             }
		 		 try {
					Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 	    
		 	   
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="质量反馈单列表")
		 	 { 		 			 		 
		 		 hql="select U_URL from [@REPORTURL] " +
			 	  	 " where name='质量反馈单列表' ";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
	           	   return;
	             }
		 		 try {
					Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 	    
		 	   
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="其它入库")
		 	 { 		 			 						   
			      if(ooigv==null)
			      {
			    	ooigv=new OOignView();
			 	    v.getParent().add(ooigv);	
			 	    ooigv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(ooigv.isClosed())
			    	  {
			    		ooigv.dispose();
			    		ooigv=new OOignView();
					 	v.getParent().add(ooigv);	
					 	ooigv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!ooigv.isSelected())
			    	  {
			    		  try {
							ooigv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						ooigv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  ooigv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  ooigv.getOd1().setDs(docTitleStatus.load);
					  ooigv.getOd1().setDocTitleStatus(ooigv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="序列号入库")
		 	 { 		 			 						   
		 		hql = "select U_enable from [@SMS] where code='CSTSN'";
				ob=appMain.lt.sqlclob(hql,0,1);
				if(!ob[0][0].toString().equals("Y"))
				{
					 JOptionPane.showMessageDialog(null,"库存转储序列号未启用,此单据无法使用");
					 return;
				}
		 		 if(sninv==null)
			      {
			    	sninv=new SninView();
			 	    v.getParent().add(sninv);	
			 	    sninv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(sninv.isClosed())
			    	  {
			    		sninv.dispose();
			    		sninv=new SninView();
					 	v.getParent().add(sninv);	
					 	sninv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!sninv.isSelected())
			    	  {
			    		  try {
							sninv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						sninv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  sninv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  sninv.getOd1().setDs(docTitleStatus.load);
					  sninv.getOd1().setDocTitleStatus(sninv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="应收贷向凭证")
		 	 { 		 			 						   
			      if(oriv==null)
			      {
			    	oriv=new OrinView();
			 	    v.getParent().add(oriv);	
			 	    oriv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(oriv.isClosed())
			    	  {
			    		oriv.dispose();
			    		oriv=new OrinView();
					 	v.getParent().add(oriv);	
					 	oriv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!oriv.isSelected())
			    	  {
			    		  try {
							oriv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						oriv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  oriv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  oriv.getOd1().setDs(docTitleStatus.load);
					  oriv.getOd1().setDocTitleStatus(oriv);
			    	  }
			      
			      }
		 	 }
			 else if(selectionNode.getUserObject().toString().equals("质量反馈单"))
		 	 { 		 			 						   
			      if(qv==null)
			      {
			    	qv=new QrView();
			 	    v.getParent().add(qv);	
			 	    qv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(qv.isClosed())
			    	  {
			    		qv.dispose();
			    		qv=new QrView();
					 	v.getParent().add(qv);	
					 	qv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!qv.isSelected())
			    	  {
			    		  try {
							qv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						qv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  qv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  qv.getOd1().setDs(docTitleStatus.load);
					  qv.getOd1().setDocTitleStatus(qv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="生产发货")
		 	 { 		 			 						   
			      if(oigev==null)
			      {
			    	oigev=new OigeView();
			 	    v.getParent().add(oigev);	
			 	    oigev.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(oigev.isClosed())
			    	  {
			    		oigev.dispose();
			    		oigev=new OigeView();
					 	v.getParent().add(oigev);	
					 	oigev.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!oigev.isSelected())
			    	  {
			    		  try {
							oigev.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						oigev.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  oigev.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  oigev.getOd1().setDs(docTitleStatus.load);
					  oigev.getOd1().setDocTitleStatus(oigev);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString()=="其它出库")
		 	 { 		 	
		 						   
			      if(ooigev==null)
			      {
			    	ooigev=new OOigeView();
			 	    v.getParent().add(ooigev);	
			 	    ooigev.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(ooigev.isClosed())
			    	  {
			    		ooigev.dispose();
			    		ooigev=new OOigeView();
					 	v.getParent().add(ooigev);	
					 	ooigev.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!ooigev.isSelected())
			    	  {
			    		  try {
							ooigev.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						ooigev.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  ooigev.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  ooigev.getOd1().setDs(docTitleStatus.load);
					  ooigev.getOd1().setDocTitleStatus(ooigev);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("序列号组合"))
		 	 { 		 	
		 						   
			      if(pv==null)
			      {
			    	pv=new PaSNView();
			 	    v.getParent().add(pv);	
			 	    pv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			      }
			      else
			      {			    	 
			    	  if(pv.isClosed())
			    	  {
			    		pv.dispose();
			    		pv=new PaSNView();
					 	v.getParent().add(pv);	
					 	pv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
			    	  }
			    	  else if(!pv.isSelected())
			    	  {
			    		  try {
							pv.setSelected(true);
							
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }			    	  
			    	  else{
			    	  try {
						pv.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	  pv.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
			    	  pv.getOd1().setDs(docTitleStatus.load);
					  pv.getOd1().setDocTitleStatus(pv);
			    	  }
			      
			      }
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("销售日报表"))
		 	 {
		 		 try{    
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\CrystalReportxscr1.rpt"+"+"+"Salelist";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。	  
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("延期发货表"))
		 	 {
		 		try{    
	 			    hql="select U_URL from [@REPORTURL] " +
				 	  	 " where name='延期发货表' ";
				 	 ob = appMain.lt.sqlclob(hql,0,1);
		             if(ob==null||ob.length==0)
		             {
		               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
		           	   return;
		             }
		             try {
							Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		 	    
			 				     			  
			      
	            } catch (Exception ex) {    
	                ex.printStackTrace();    
	            }   
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("生产日报表"))
		 	 {
		 		 try{    
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\CrystalReportsccr.rpt"+"+"+"Mlist";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("超生产订单入库表"))
		 	 {
		 		try{    
	 			    hql="select U_URL from [@REPORTURL] " +
				 	  	 " where name='超生产订单入库表' ";
				 	 ob = appMain.lt.sqlclob(hql,0,1);
		             if(ob==null||ob.length==0)
		             {
		               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
		           	   return;
		             }
		             try {
							Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		 	    
			 				     			  
			      
	            } catch (Exception ex) {    
	                ex.printStackTrace();    
	            }   
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("库存过账明细"))
		 	 {
		 		 try{    
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\库存过账明细.rpt"+"+"+"itemlist";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。 
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("分库存量表"))
		 	 {
		 		 try{    
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\分库存量表.rpt"+"+"+"Wms";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("序列号跟踪明细"))
		 	 {
		 		try{    
	 			    hql="select U_URL from [@REPORTURL] " +
				 	  	 " where name='序列号跟踪明细' ";
				 	 ob = appMain.lt.sqlclob(hql,0,1);
		             if(ob==null||ob.length==0)
		             {
		               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
		           	   return;
		             }
		             try {
							Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		 	    
			 				     			  
			      
	            } catch (Exception ex) {    
	                ex.printStackTrace();    
	            }   
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("序列号状态"))
		 	 {
		 		 try{    
		 			    hql="select U_URL from [@REPORTURL] " +
					 	  	 " where name='序列号状态' ";
					 	 ob = appMain.lt.sqlclob(hql,0,1);
			             if(ob==null||ob.length==0)
			             {
			               JOptionPane.showMessageDialog(null, "找不到此报表路径，请在后台表配置此报表的链接路径");
			           	   return;
			             }
			             try {
								Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (URISyntaxException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}		
				 		/* appMain.rpurl=ob[0][0].toString();
						//Desktop.getDesktop().browse(new URL(ob[0][0].toString()).toURI());
				        if(frame1==null)
					      {
					    	frame1=new JInternalFrame("REPORT",true,true,true,true);
	
					    	frame1.getContentPane().add(WebBrowser.createContent(ob[0][0].toString()), BorderLayout.CENTER);						       
					    	frame1.setVisible(true);
					    	v.getParent().add(frame1);	
					 	    frame1.setBounds(200, 0, screenSize.width-200, screenSize.height-150);
					      }
					      else
					      {			    	 
					    	  if(frame1.isClosed())
					    	  {
					    		frame1.dispose();
					    		
					    		frame1=new JInternalFrame("REPORT",true,true,true,true);
					
						    	frame1.getContentPane().add(WebBrowser.createContent(ob[0][0].toString()), BorderLayout.CENTER);
						    	frame1.setVisible(true);
						    	v.getParent().add(frame1);	
							 	frame1.setBounds(200, 0, screenSize.width-200, screenSize.height-150);			    		
					    	  }
					    	  else if(!frame1.isSelected())
					    	  {
					    		  try {
									frame1.setSelected(true);
									
								} catch (PropertyVetoException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    	  }			    	  
					    	  else{
					    	  try {
								frame1.setMaximum(true);
							} catch (PropertyVetoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    	  frame1.setBounds(200, 0, screenSize.width-200, screenSize.height-150);

					    	  }
					      
					      }*/
				     
				  
				      
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
		 	 }
		 	 else if(selectionNode.getUserObject().toString().equals("科目发生额表"))
		 	 {
		 		 try{    
					 	String tb=appMain.oCompany.getServer()+"+"+appMain.oCompany.getCompanyDB()+"+"
				        +appMain.oCompany.getDbUserName()+"+"+appMain.config.getDbuserpas()+"+"+
					 	"d:\\ncr\\fiance_period.rpt"+"+"+"RFinance";
					    ActiveXComponent dotnetCom = null;    
			            dotnetCom = new ActiveXComponent("ReportCenter.PushServerProvider");     //需要调用的C#代码中的命名空间名和类名。 
			            Variant var=Dispatch.call(dotnetCom,"ResponseRenderAsyc",new Variant(tb),null);
			            System.out.println(var);
			            dotnetCom.safeRelease();
		            } catch (Exception ex) {    
		                ex.printStackTrace();    
		            }    
		 	 }
		 	 else{}
			
		  }
	}
}
