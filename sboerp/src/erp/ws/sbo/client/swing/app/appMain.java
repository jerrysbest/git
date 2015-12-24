package erp.ws.sbo.client.swing.app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.IItems;
import com.sap.smb.sbo.api.IJournalEntries;
import com.sap.smb.sbo.api.IPayments;
import com.sap.smb.sbo.api.IProductTrees;
import com.sap.smb.sbo.api.IProductionOrders;
import com.sap.smb.sbo.api.IUserFieldsMD;
import com.sap.smb.sbo.api.IUserTablesMD;

import erp.ws.sbo.client.swing.dao.abs.AbsWFlowDao;
import erp.ws.sbo.client.swing.dao.impl.PaSNDao;
import erp.ws.sbo.client.swing.dao.impl.QrDao;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Foot.FootView;
import erp.ws.sbo.client.swing.view.Login.LoginView;
import erp.ws.sbo.client.swing.view.MainForm.MainFormView;
import erp.ws.sbo.client.swing.view.MainMenu.MainMenuView;
import erp.ws.sbo.dao.impl.Userauther;
import erp.ws.sbo.utils.ListTab;


public final class appMain {
	/**
	 * @param args
	 */
	public static  IDocuments odelNotes; // draft object
	public static IDocuments[] lidoc;
	//public static ThreadLocal<IDocuments>  odelNotes=new ThreadLocal<IDocuments>(); // draft object
	public static IPayments oinpay; // doc object
	public static IJournalEntries oje; // doc object
	public static IDocuments odoc; // doc object
	public static IProductionOrders opdoc; // doc object
	public static IDocuments qodelNotes; // draft object
	public static IDocuments qodoc; // draft object
    public static ICompany oCompany; // The company object
    public static IItems  oOitm;
    public static IProductTrees  oProductTree;
    public static  int lRetCode,lErrCode;
    public static String sErrMsg,user1;
    
    public static IUserTablesMD  oUserTableMD;
    public static IUserFieldsMD  oUserFieldsMD;
    
    public static String xmlPath=System.getProperty("user.dir").toString().replace("\\","/")+"/WebRoot/WEB-INF/applicationContext.xml";   
    public static ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlPath);
    public static appConfigBean config = (appConfigBean)ctx.getBean("appconfig"); 
    public static AbsWFlowDao wfd = (AbsWFlowDao)appMain.ctx.getBean("WFlowDao");
    public static Userauther ua = (Userauther)appMain.ctx.getBean("Userauther");
	public static PaSNDao psn = (PaSNDao)appMain.ctx.getBean("PaSNDao");
	public static QrDao QD = (QrDao)appMain.ctx.getBean("QrDao");
	public static JDesktopPane jd;
	public static MainFormView v;
	public static MainMenuView mm;
	public static DocMenuView  dmv;
	public static LoginView lg;
	public static FootView fv;
	public static String rpurl,Mno;
	public static ListTab lt=new ListTab();//Container of hql select result which similar with a table in c#
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*NativeInterface.open();
	    UIUtils.setPreferredLookAndFeel();
	    SwingUtilities.invokeLater(new Runnable() {
		public void run() {*/
		
			jd=new JDesktopPane();
			v=new MainFormView();
			mm=new MainMenuView();
			dmv= DocMenuView.getdmv();
			lg=new LoginView();
			fv=new FootView();
			
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		    v.setJMenuBar(mm);
		    
		    Container pane=v.getContentPane();
		    pane.add(dmv,BorderLayout.PAGE_START);
		    pane.add(jd,BorderLayout.CENTER);
		    pane.add(fv,BorderLayout.PAGE_END);
			    
			
		    jd.add(lg);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    lg.setBounds(screenSize.width/2-200, screenSize.height/5, 400,330);
		    v.setVisible(true);
		   /* }
	    });
	    NativeInterface.runEventPump();*/
 	    	
	}
	public void addComponent(JInternalFrame jf) {
		
	}
	public static JDesktopPane getJd() {
		return jd;
	}
	public static void setJd(JDesktopPane jd) {
		appMain.jd = jd;
	}
	public static MainMenuView getMm() {
		return mm;
	}
	public static void setMm(MainMenuView mm) {
		appMain.mm = mm;
	}
	public static DocMenuView getDmv() {
		return dmv;
	}
	public static void setDmv(DocMenuView dmv) {
		appMain.dmv = dmv;
	}
	public static LoginView getLg() {
		return lg;
	}
	public static void setLg(LoginView lg) {
		appMain.lg = lg;
	}
	public static FootView getFv() {
		return fv;
	}
	public static void setFv(FootView fv) {
		appMain.fv = fv;
	}
}
