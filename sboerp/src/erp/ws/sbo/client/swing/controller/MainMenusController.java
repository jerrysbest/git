package erp.ws.sbo.client.swing.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDialog;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.view.MainMenu.AboutView;
import erp.ws.sbo.client.swing.view.MainMenu.MainMenuView;
import erp.ws.sbo.client.swing.view.UAuther.UAutherView;



public class MainMenusController implements ActionListener{

	private MainMenuView v;
	private UAutherView uv;
	private AboutView av;
	//private CreateUd cud;
	//private CreateUdf cudt;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public MainMenusController(MainMenuView v)
	{
		this.v=v;
	}
	public MainMenuView getV() {
		return v;
	}
	public void setV(MainMenuView v) {
		this.v = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==v.getInitud()&&appMain.oCompany.getUserName().equals("manager"))
		{
			//cud=new CreateUd();
			//cud.createTable();
		}
		else if(e.getSource()==v.getInitudt()&&appMain.oCompany.getUserName().equals("manager"))
		{
			//cudt=new CreateUdf();
			//cudt.createTable();
		}
		else if(e.getSource()==v.getAutherity()&&appMain.oCompany.getUserName().equals("manager"))
		{
			 if(uv==null)
		      {
		    	uv=new UAutherView();
		 	    v.getParent().add(uv);	
		 	    uv.setBounds(200, 60, screenSize.width-200, screenSize.height-150);
		      }
		      else
		      {			    	 
		    	  if(uv.isClosed())
		    	  {
		    		uv.dispose();
		    		uv=new UAutherView();
				 	v.getParent().add(uv);	
				 	uv.setBounds(200, 60, screenSize.width-200, screenSize.height-150);			    		
		    	  }
		    	  else if(!uv.isSelected())
		    	  {
		    		  try {
						uv.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	  }
		    	  else{
		    	  try {
					uv.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	  uv.setBounds(200, 60, screenSize.width-200, screenSize.height-150);
		    	  uv.getOd1().setDs(docTitleStatus.load);
				  uv.getOd1().setDocTitleStatus(uv);
		    	  }
		      
		      }
		}
		else if(e.getSource()==v.getAbout())
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
		}
		else{
			
		}
	}
}
