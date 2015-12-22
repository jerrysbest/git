package erp.ws.sbo.client.swing.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import erp.ws.sbo.client.swing.view.Foot.FootView;
public class FootsController implements ActionListener{
	 private FootView v;
	   public FootsController(FootView v)
	   {
		   this.setV(v);
	   }
	   public void actionPerformed(ActionEvent e) {
		   
	   }
	public FootView getV() {
		return v;
	}
	public void setV(FootView v) {
		this.v = v;
	}
}
