package erp.ws.sbo.client.swing.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import erp.ws.sbo.client.swing.view.MainForm.MainFormView;

public class MainFormsController implements ActionListener{
	private MainFormView v;
	
	public MainFormsController(MainFormView v){
		this.v = v;	
	}

	public void actionPerformed(ActionEvent e) {

	}
	public void addComponent(JInternalFrame jf) {
		JDesktopPane jd=new JDesktopPane();
	    jd.setLayout(new   BorderLayout()); 
		MainFormView v=new MainFormView();
	
	
	
	    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setExtendedState(JFrame.MAXIMIZED_BOTH);

	    Container pane=v.getContentPane();
	    pane.add(jd, BorderLayout.CENTER);
	    
	
	
	 
	
	 
	}
	public MainFormView getMainFormView() {
		return v;
	}

	public void setMainFormView(MainFormView v) {
		this.v = v;
	}

}
