package erp.ws.sbo.client.swing.dao.abs;


import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.view.Snin.SninView;

public abstract class SninAbsDoc implements IDoc<SninView>{

	  public abstract void ctarget(SninView v,Integer dh);
	  public abstract void snverification(SninView v);
}
