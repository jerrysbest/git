package erp.ws.sbo.client.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public class QueryWindowsController implements ActionListener {
    private QueryWindowView<?> v;
    private IQDoc q;
   
    
	public QueryWindowsController(QueryWindowView<?> v,IQDoc q) {
		// TODO Auto-generated constructor stub
		this.v=v;		
		this.setQ(q);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		q.doclist(v.getPl(), v);
		v.dispose();
	}

	public IQDoc getQ() {
		return q;
	}

	public void setQ(IQDoc q) {
		this.q = q;
	}
	
	
}
