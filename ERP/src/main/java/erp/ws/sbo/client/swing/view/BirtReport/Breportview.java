package erp.ws.sbo.client.swing.view.BirtReport;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;





import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.utils.WebBrowser;


public class Breportview extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8718886682364090266L;
    private String URL;

	public Breportview()
	{
		super("±¨±í",true,true,true,true);	
		this.setURL(appMain.rpurl);
		initComponents();
	}
	
	private void initComponents() {
		 this.getContentPane().add(WebBrowser.createContent(URL), BorderLayout.CENTER);  
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
}
