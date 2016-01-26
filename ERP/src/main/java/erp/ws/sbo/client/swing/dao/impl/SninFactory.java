/**
 * @auther: Jerry si
 * <p>
 * @date: 2013-06-15
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
import erp.ws.sbo.client.swing.view.Snin.SninView;

public class SninFactory extends DaoFactory<SninView> {
    private SninView v;
    private SninAbsDoc isabsdocm;
    private IDoc<SninView> idocm;
    private IQDoc iqdocm;
    private IWFlowDao iwfd;
    private IAdvSN<SninView> iasn;
    public void setIdocm(IDoc<SninView>  idocm) {
		this.idocm = idocm;
	}
	@Override
	public IDoc<SninView> getDoc() {
		// TODO Auto-generated method stub
		return idocm;
	}
	public void setIsabsdocm(SninAbsDoc isabsdocm) {
		this.isabsdocm = isabsdocm;
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
	public SninView getV() {
		return v;
	}
	public void setV(SninView v) {
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
		return isabsdocm;
	}
	@Override
	public IAdvSN<SninView> getAdnSN() {
		// TODO Auto-generated method stub
		return iasn;
	}
	public void setIasn(IAdvSN<SninView> iasn) {
		this.iasn = iasn;
	}
	
	
	
	
  
	
}
