package erp.ws.sbo.client.swing.view.Center;
import javax.swing.JInternalFrame;
public class Center extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Center()
	{		
		initComponents();
	}
	public void initComponents()
	{ 
		this.setTitle("jerry");
		this.setVisible(true);
		this.setResizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setSize(200, 200);
		
	}
}
