package erp.ws.sbo.client.swing.view.MainMenu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import erp.ws.sbo.client.swing.controller.MainMenusController;


public class MainMenuView extends JMenuBar{

	private static final long serialVersionUID = 1L;

	// Menu
	//private JMenuBar jm= new JMenuBar();
	private JMenu jMenu1=new JMenu();
	private JMenu jMenu2=new JMenu();
	private JMenu jMenu3=new JMenu();
	private JMenuItem close;
	private JMenuItem lockscreen;
	private JMenuItem initud;
	private JMenuItem initudt;
	private JMenuItem autherity;
	private JMenuItem about;
	 private MainMenusController mc=new MainMenusController(this);
	public MainMenuView()
	{				
		initComponents();
	}
	private void initComponents() {
				
		
	  
		jMenu1.setText("�ļ�");
		close= new JMenuItem("�ر�", KeyEvent.VK_N);  
		close.addActionListener(mc);
		jMenu1.add(close);
		lockscreen= new JMenuItem("������Ļ", KeyEvent.VK_N); 
		lockscreen.addActionListener(mc);
		jMenu1.add(lockscreen);
		this.add(jMenu1);
				
		jMenu2.setText("�༭");
		this.add(jMenu2);
		
		jMenu3.setText("����");
		initud=new JMenuItem("��ʼ���Զ����", KeyEvent.VK_N); 
		initud.addActionListener(mc);
		initudt=new JMenuItem("��ʼ���Զ�����ֶ�", KeyEvent.VK_N); 
		initudt.addActionListener(mc);
		autherity=new JMenuItem("����Ȩ��", KeyEvent.VK_N); 
		autherity.addActionListener(mc);
		about=new JMenuItem("����", KeyEvent.VK_N); 
		about.addActionListener(mc);
		jMenu3.add(initud);
		jMenu3.add(initudt);
		jMenu3.add(autherity);
		jMenu3.add(about);
		this.add(jMenu3);
				
		setVisible(true);					
	}
	public JMenuItem getInitud() {
		return initud;
	}
	public void setInitud(JMenuItem initud) {
		this.initud = initud;
	}
	public JMenuItem getInitudt() {
		return initudt;
	}
	
	public void setInitudt(JMenuItem initudt) {
		this.initudt = initudt;
	}
	public JMenuItem getClose() {
		return close;
	}
	public void setClose(JMenuItem close) {
		this.close = close;
	}
	public JMenuItem getLockscreen() {
		return lockscreen;
	}
	public void setLockscreen(JMenuItem lockscreen) {
		this.lockscreen = lockscreen;
	}
	public JMenuItem getAutherity() {
		return autherity;
	}
	public void setAutherity(JMenuItem autherity) {
		this.autherity = autherity;
	}
	public JMenuItem getAbout() {
		return about;
	}
	public void setAbout(JMenuItem about) {
		this.about = about;
	}
}
