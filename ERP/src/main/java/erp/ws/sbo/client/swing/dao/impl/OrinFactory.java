package erp.ws.sbo.client.swing.dao.impl;

import erp.ws.sbo.client.swing.dao.DaoFactory;
import erp.ws.sbo.client.swing.dao.IAdvSN;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.dao.IWFlowDao;
import erp.ws.sbo.client.swing.dao.abs.AbsDoc;
import erp.ws.sbo.client.swing.dao.abs.OrderAbsDoc;
import erp.ws.sbo.client.swing.dao.abs.SninAbsDoc;
import erp.ws.sbo.client.swing.view.Orin.OrinView;

public class OrinFactory extends DaoFactory<OrinView>{

	private OrinView v;
    private AbsDoc iabsdocm;
    private IDoc<OrinView> idocm;
    private IQDoc iqdocm;
    private IWFlowDao iwfd;
    private IAdvSN<OrinView> iasn;
    public void setIdocm(IDoc<OrinView> idocm) {
		this.idocm = idocm;
	}
   
	public void setIabsdocm(AbsDoc iabsdocm) {
		this.iabsdocm = iabsdocm;
	}

	@Override
	public IDoc<OrinView> getDoc() {
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
	public OrinView getV() {
		return v;
	}
	public void setV(OrinView v) {
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
	public IAdvSN<OrinView> getAdnSN() {
		// TODO Auto-generated method stub
		return iasn;
	}


	public void setIasn(IAdvSN<OrinView> iasn) {
		this.iasn = iasn;
	}

}
