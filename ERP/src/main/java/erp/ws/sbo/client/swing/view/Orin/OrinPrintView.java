package erp.ws.sbo.client.swing.view.Orin;

import javax.swing.JInternalFrame;

import erp.ws.sbo.client.swing.controller.OrinsController;

public class OrinPrintView extends JInternalFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5792803521849389456L;
	private OrinsController oc;
	public OrinPrintView(OrinsController oc) {
		// TODO Auto-generated constructor stub
		this.setOc(oc);
	}
	public OrinsController getOc() {
		return oc;
	}
	public void setOc(OrinsController oc) {
		this.oc = oc;
	}

}
