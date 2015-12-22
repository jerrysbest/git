package erp.ws.sbo.client.swing.dao.impl;

import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.dao.IAdvSN;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.dao.IWFlowDao;
import erp.ws.sbo.client.swing.dao.abs.AbsDoc;
import erp.ws.sbo.client.swing.dao.abs.OrderAbsDoc;
import erp.ws.sbo.client.swing.dao.abs.SninAbsDoc;
import erp.ws.sbo.client.swing.view.Orct.OrctView;

public class OrctFactory extends DaoFactory<OrctView>{

	private OrctView v;
    private AbsDoc iabsdocm;
    private IDoc<OrctView> idocm;
    private IQDoc iqdocm;
    private IWFlowDao iwfd;
    public void setIdocm(IDoc<OrctView> idocm) {
		this.idocm = idocm;
	}
   
	public void setIabsdocm(AbsDoc iabsdocm) {
		this.iabsdocm = iabsdocm;
	}

	@Override
	public IDoc<OrctView> getDoc() {
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
	public OrctView getV() {
		return v;
	}
	public void setV(OrctView v) {
		this.v = v;
	}
	@Override
	public AbsDoc getAbsDoc() {
		// TODO Auto-generated method stub
		return iabsdocm;
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
	public IAdvSN<OrctView> getAdnSN() {
		// TODO Auto-generated method stub
		return null;
	}

}
