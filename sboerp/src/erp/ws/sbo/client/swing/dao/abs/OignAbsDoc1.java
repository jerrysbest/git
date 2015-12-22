package erp.ws.sbo.client.swing.dao.abs;


import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.view.Oign.OignView;

public abstract class OignAbsDoc1 implements IDoc<OignView>{

	  public abstract void ctarget(OignView v,Integer dh);
	  public abstract void snverification(OignView v);
}
