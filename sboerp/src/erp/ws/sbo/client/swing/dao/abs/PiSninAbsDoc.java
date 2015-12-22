package erp.ws.sbo.client.swing.dao.abs;


import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.view.PiSnin.PiSninView;

public abstract class PiSninAbsDoc implements IDoc<PiSninView>{

	  public abstract void ctarget(PiSninView v,Integer dh);
	  public abstract void snverification(PiSninView v);
}
