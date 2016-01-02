package erp.ws.sbo.client.swing.view.DeSN;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import erp.ws.sbo.client.swing.controller.DesnsController;
import erp.ws.sbo.client.swing.model.ColSNDocLine;
import erp.ws.sbo.client.swing.model.ColSNDocTitle;
import erp.ws.sbo.client.swing.model.SNDocLine;
import erp.ws.sbo.client.swing.model.SNDocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.DesnDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.DesnDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.utils.DbUtils;

public class DeSNView extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4245123741894632921L;
    private Object v;
    private DesnsController oc=new DesnsController(this);
    private String hql; 
    private ColSNDocLine cl=ColSNDocLine.getCLine();
    private SNDocLine dl=new SNDocLine();
	private DbUtils<ColSNDocLine,SNDocLine> dbu=new DbUtils<ColSNDocLine,SNDocLine>();
    private AbstractDocLineModel<ColSNDocLine,SNDocLine> od;
    private JMyTable jt;
    private JScrollPane scp;
    private ColSNDocTitle cl1=ColSNDocTitle.getCLine();
    private SNDocTitle dl1=new SNDocTitle();
	private DbUtils<ColSNDocTitle,SNDocTitle> dbu1=new DbUtils<ColSNDocTitle,SNDocTitle>();
    private AbstractDocTitleModel<ColSNDocTitle,SNDocTitle> od1;
    private JMyTable jt1;
    private JScrollPane scp1;
    private JPanel  pane1=new JPanel();//base information
    private Container pane=this.getContentPane();
    private JButton bt_package=new JButton("´ò°ü");
    private String MNo="";
    private String company="";
    private String Qc="";
    public  DeSNView(Object v)
	{
		super();
		this.setV(v);
		initComponents();
	}
    public  DeSNView(Object v,String MNo,String company,String Qc)
	{
		super();
		this.setV(v);
		this.MNo=MNo;
		this.company=company;
		this.Qc=Qc;
		initComponents();
	}
	private void initComponents() {
		// TODO Auto-generated method stub
		this.setName("DESN");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);		
		
		hql="select '','','','','','','','' from Inv1 p where 1=2";	
	    od=new DesnDocLineModel(cl,dl,dbu,hql);		   	   
		od.setDocLineStatus(docLineStatus.query);
		od.setGridStatus(docLineStatus.add);
		
		jt=new JMyTable(od);		    
	    jt.setAutoCreateRowSorter(true);	
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt.setPreferredScrollableViewportSize(new Dimension(620, 200));
		jt.setFillsViewportHeight(true);
		jt.getColumnModel().getColumn(od.getcolumnindex("ÐòºÅ")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("ÐÐºÅ")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("ÐòÁÐºÅ")).setPreferredWidth(180);
		scp=new JScrollPane(jt);
		hql="select '','','','','','','' from Inv1 p where 1=2";	
	    od1=new DesnDocTitleModel(cl1,dl1,dbu1,hql);		   	   
		od1.setDocTitleStatus(docTitleStatus.query);
		
		jt1=new JMyTable(od1);		    
	    jt1.setAutoCreateRowSorter(true);	
		jt1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt1.setPreferredScrollableViewportSize(new Dimension(620, 100));
		jt1.setFillsViewportHeight(true);
		jt1.getColumnModel().getColumn(od.getcolumnindex("ÐòºÅ")).setPreferredWidth(30);
		jt1.getColumnModel().getColumn(od.getcolumnindex("ÐÐºÅ")).setPreferredWidth(30);
	
		
		scp1=new JScrollPane(jt1);
		    
	    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));				    
	    pane1.setLayout(new BoxLayout(pane1, BoxLayout.Y_AXIS));
	    
	    pane.add(scp1);
	    pane.add(scp);	    
	   
	    pane.add(pane1);
	    pane.add(bt_package);
	    jt.addKeyListener(oc);
	    bt_package.addActionListener(oc);
		
	}
	public Object getV() {
		return v;
	}
	public void setV(Object v) {
		this.v = v;
	}
	public AbstractDocLineModel<ColSNDocLine, SNDocLine> getOd() {
		return od;
	}
	public void setOd(AbstractDocLineModel<ColSNDocLine, SNDocLine> od) {
		this.od = od;
	}
	public JMyTable getJt() {
		return jt;
	}
	public void setJt(JMyTable jt) {
		this.jt = jt;
	}
	public AbstractDocTitleModel<ColSNDocTitle, SNDocTitle> getOd1() {
		return od1;
	}
	public void setOd1(AbstractDocTitleModel<ColSNDocTitle, SNDocTitle> od1) {
		this.od1 = od1;
	}
	public JMyTable getJt1() {
		return jt1;
	}
	public void setJt1(JMyTable jt1) {
		this.jt1 = jt1;
	}
	public JButton getBt_package() {
		return bt_package;
	}
	public void setBt_package(JButton bt_package) {
		this.bt_package = bt_package;
	}
	public String getMNo() {
		return MNo;
	}
	public void setMNo(String mNo) {
		MNo = mNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getQc() {
		return Qc;
	}
	public void setQc(String qc) {
		Qc = qc;
	}
}
