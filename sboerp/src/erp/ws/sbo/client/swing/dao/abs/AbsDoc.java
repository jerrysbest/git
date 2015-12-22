package erp.ws.sbo.client.swing.dao.abs;


import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.view.Oinv.OinvView;

public abstract class AbsDoc implements IDoc<OinvView>{

	  public abstract void ctarget(OinvView v,Integer dh);
	
}
