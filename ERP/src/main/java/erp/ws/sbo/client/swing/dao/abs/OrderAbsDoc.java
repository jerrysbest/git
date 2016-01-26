package erp.ws.sbo.client.swing.dao.abs;


import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.view.Ordr.OrdrView;

public abstract class OrderAbsDoc implements IDoc<OrdrView>{

	  public abstract void ctarget(OrdrView v,Integer dh);
	
}
