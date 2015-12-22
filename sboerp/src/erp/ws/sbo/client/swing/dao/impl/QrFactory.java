/**
 * @auther: Jerry si
 * <p>
 * @date: 2014-03-29
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
import erp.ws.sbo.client.swing.view.Qr.QrView;

public class QrFactory extends DaoFactory<QrView> {
    private QrView v;
    private OrderAbsDoc ioabsdocm;
    private IDoc<QrView> idocm;
    private IQDoc iqdocm;
    private IWFlowDao iwfd;
    public void setIdocm(IDoc<QrView>  idocm) {
		this.idocm = idocm;
	}
	@Override
	public IDoc<QrView> getDoc() {
		// TODO Auto-generated method stub
		return idocm;
	}
	public void setIoabsdocm(OrderAbsDoc ioabsdocm) {
		this.ioabsdocm = ioabsdocm;
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
	public QrView getV() {
		return v;
	}
	public void setV(QrView v) {
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
		return ioabsdocm;
	}
	@Override
	public SninAbsDoc getSninAbsDoc() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IAdvSN<QrView> getAdnSN() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
  
	
}
