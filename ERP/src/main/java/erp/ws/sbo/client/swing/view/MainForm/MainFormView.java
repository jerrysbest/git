package erp.ws.sbo.client.swing.view.MainForm;

import javax.swing.JFrame;

public class MainFormView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainFormView()
	{				
		initComponents();
	}
	// TITLE
		final private static String TITLE = "QS-ERP";
		

		public void initComponents() {
			this.setTitle(TITLE);
			
		}
	
}
