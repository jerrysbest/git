package erp.ws.sbo.client.swing.util.general;

import java.awt.Component;

import javax.swing.JDesktopPane;
public class Ujdm extends JDesktopPane{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getByName(String name)
	{
	  Component[] cps=	super.getComponents();
	  for(int i=0;i<cps.length;i++)
	  {
		  if(cps[i].getName().equals(name)){
			  return cps[i];
		  }
	  }
	  return null;
	  
	}
	
}
