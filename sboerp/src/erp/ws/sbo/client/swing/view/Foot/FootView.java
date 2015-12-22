package erp.ws.sbo.client.swing.view.Foot;
import java.awt.Color;

import javax.swing.JTextPane;
public class FootView extends JTextPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FootView()
	{
		setEditable(false);	
		initComponents();
	}
	public void initComponents()
	{ 
		
		this.setBackground(Color.lightGray);
		this.setVisible(true);
		
	
	}
}
