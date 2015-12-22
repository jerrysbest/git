/**
 * @auther: Jerry si
 * <p>
 * @date: 2012-06-13
 * <p>
 * Dependency Injection, which is the core of spring,will do the same as this factory.
 */
package erp.ws.sbo.client.swing.dao.impl;

import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.dao.IAdvSN;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.dao.IWFlowDao;
import erp.ws.sbo.client.swing.dao.abs.AbsDoc;
import erp.ws.sbo.client.swing.dao.abs.OrderAbsDoc;
import erp.ws.sbo.client.swing.dao.abs.SninAbsDoc;
import erp.ws.sbo.client.swing.view.Oign.OignView;

public class OignFactory extends DaoFactory<OignView> {
    private OignView v;
    private IDoc<OignView> idocm;
    private IQDoc iqdocm;
    private IWFlowDao iwfd;
    private IAdvSN<OignView> iasn;
    public void setIdocm(IDoc<OignView>  idocm) {
		this.idocm = idocm;
	}
	@Override
	public IDoc<OignView> getDoc() {
		// TODO Auto-generated method stub
		return idocm;
	}
	
	public void setIqdocm(IQDoc iqdocm) {
		this.iqdocm = iqdocm;
	}
	@Override
	public IQDoc getQDoc() {
		// TODO Auto-generated method stub
		return iqdocm;
	}
	public void setIwfd(IWFlowDao iwfd) {
		this.iwfd = iwfd;
	}
	@Override
	public IWFlowDao workflow() {
		// TODO Auto-generated method stub
		return iwfd;
	}
	public OignView getV() {
		return v;
	}
	public void setV(OignView v) {
		this.v = v;
	}
	@Override
	public AbsDoc getAbsDoc() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderAbsDoc getOrdrAbsDoc() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SninAbsDoc getSninAbsDoc() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IAdvSN<OignView> getAdnSN() {
		// TODO Auto-generated method stub
		return iasn;
	}
	public IAdvSN<OignView> getIasn() {
		return iasn;
	}
	public void setIasn(IAdvSN<OignView> iasn) {
		this.iasn = iasn;
	}
	
	
	
	
  
	
}
