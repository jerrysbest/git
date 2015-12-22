package erp.ws.sbo.client.swing.view.PaSN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.eltima.components.ui.DatePicker;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.controller.PaSNsController;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.ColPaSNDocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.PaSNDocLine;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.PasnDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.PasnDocTitleModel;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.utils.DbUtils;
import javax.swing.JComboBox;


public class PaSNView extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3086686932067569320L;
	private PaSNsController pc=new PaSNsController(this);
    private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private SpringLayout lay = new SpringLayout();
    private SpringLayout lay5 = new SpringLayout();
    private String  hql="select p.userid,isnull(p.U_Name,'') from Ousr as p";  
    private ColPaSNDocLine cl=ColPaSNDocLine.getCLine();
  	private PaSNDocLine dl=new PaSNDocLine();
  	private DbUtils<ColPaSNDocLine,PaSNDocLine> dbu=new DbUtils<ColPaSNDocLine,PaSNDocLine>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private AbstractDocLineModel<ColPaSNDocLine,PaSNDocLine> od;
    private AbstractDocTitleModel<ColDocTitle,DocTitle> od1;
    
    JLabel lab_docn = new JLabel("单号");
    JLabel lab_status = new JLabel("状态");
    JLabel lab_date = new JLabel("过账日期");
    JLabel lab_tsn = new JLabel("父序列号");
    JLabel lab_sn = new JLabel("输入序列号");
    JLabel lab_memo = new JLabel("备注");
    JLabel lab_MNo = new JLabel("机号");
    JTextField txt_MNo = new JTextField(8);
    
    
    JTextField txt_docn = new JTextField(15);
    JTextField txt_status = new JTextField(15);
    JTextField txt_tsn = new JTextField(15);
    DatePicker txt_date  = new DatePicker(pane1,new Date());
    JTextArea jta_memo=new JTextArea(6,20);
    JTextArea jta_SN=new JTextArea(8,40);
    
    JMyTable jt;
    JTable jt1;
    JComboBox com_company = new JComboBox();
    JLabel label = new JLabel("公司名称");
    JTabbedPane jtp1=new JTabbedPane();	
    JTabbedPane jtp2=new JTabbedPane();	
   
    
    public PaSNView()
    {
    	super("序列号组合",true,true,true,true);	    	
		initComponents();
    }

	private void initComponents() {
		// TODO Auto-generated method stub
		 this.setName("PASN");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 appMain.fv.setText("");
		 jta_SN.setLineWrap(true);
		 jta_memo.setLineWrap(true);
		 txt_date.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_date.setEditorable(true);//设置是否可编辑
		 txt_date.setBackground(Color.gray);//设置背景色
		 txt_date.setForeground(Color.yellow);//设置前景色
		 txt_date.setPreferredSize(new Dimension(170,24));//设置大小
		 txt_date.setTextAlign(DatePicker.LEFT);
		 
		 this.txt_docn.setEditable(false);
		 this.txt_status.setEditable(false);
		 this.txt_MNo.setEditable(false);
		 com_company.addItem( new  ComboBoxItem("0","沙河市潜水电线厂"));
		 com_company.addItem( new  ComboBoxItem("1","国 潜"));
		 pane1.setLayout(lay);
		 lay.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, com_company);
    	 lay.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, lab_tsn);
    	 lay.putConstraint(SpringLayout.NORTH, com_company, 6, SpringLayout.SOUTH, txt_tsn);
    	 lay.putConstraint(SpringLayout.WEST, com_company, 0, SpringLayout.WEST, txt_docn);
    	 lay.putConstraint(SpringLayout.EAST, com_company, 119, SpringLayout.WEST, txt_docn);
		 lay.putConstraint(SpringLayout.WEST, lab_docn, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_docn, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_tsn, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_tsn, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_tsn, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_tsn, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_status, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_status, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_status, -125,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_status, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_date, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_date, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_date, -125,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_date, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_MNo, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_MNo, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_MNo, -125,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_MNo, 70, SpringLayout.NORTH, pane1);
		 pane1.add(lab_docn);
		 pane1.add(lab_tsn);
		 pane1.add(lab_status);	
		 pane1.add(lab_date);	
		 pane1.add(txt_docn);
		 pane1.add(txt_tsn);
		 pane1.add(txt_status);	
		 pane1.add(txt_date);	
		 pane1.add(com_company);
		 pane1.add(lab_MNo);	
		 pane1.add(txt_MNo);
		 
		 hql="select '','','' " +
           "from Inv1 p where 1=2";	
	    od=new PasnDocLineModel(cl,dl,dbu,hql);		   	   
		od.setDocLineStatus(docLineStatus.load);
		od.setGridStatus(docLineStatus.load);
		
		jt=new JMyTable(od);
		
		jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("序列号")).setPreferredWidth(200);
		jt.getColumnModel().getColumn(od.getcolumnindex("备注")).setPreferredWidth(200);
		
		jt.setAutoCreateRowSorter(true);	
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
		jt.setFillsViewportHeight(true);
	
		JScrollPane scp=new JScrollPane(jt);
		
		jtp1.add("基本信息",pane1);
		 
		
		 
		pane1.add(label);
		 
		 
		 
		jtp1.add("数据浏览",pane2);
		jtp1.add("自定义",pane3);
        pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
        pane4.add(scp);
         
         //单头数据浏览
        hql="select '','','','','','','','','','',''" +	         
 	        " from Oinv p,Oslp p1 where p.slpCode=p1.slpCode and 1=2 ";   
        od1 = new PasnDocTitleModel(ct,dt,dbu1,hql);
 		od1.setDs(docTitleStatus.load);
 	    od1.setDocTitleStatus(this);
 		
 		jt1=new JTable(od1);
 		
 		jt1.setName("data-browser");
 		jt1.setAutoCreateRowSorter(true);	
		jt1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt1.setPreferredScrollableViewportSize(new Dimension(500, 170));
		jt1.setFillsViewportHeight(true);
		
		
		JScrollPane scp1=new JScrollPane(jt1);
		pane2.setLayout(new BoxLayout(pane2, BoxLayout.Y_AXIS));
		pane2.add(scp1);
		
		pane5.setLayout(lay5);
		lay5.putConstraint(SpringLayout.WEST, this.lab_memo, 42,SpringLayout.WEST, pane5);
		lay5.putConstraint(SpringLayout.NORTH,this.lab_memo, 5, SpringLayout.NORTH, pane5);
		lay5.putConstraint(SpringLayout.WEST, this.jta_memo, 100,SpringLayout.WEST, pane5);
		lay5.putConstraint(SpringLayout.NORTH,this.jta_memo, 5, SpringLayout.NORTH, pane5);
		lay5.putConstraint(SpringLayout.WEST, this.lab_sn, 350,SpringLayout.WEST, pane5);
		lay5.putConstraint(SpringLayout.NORTH,this.lab_sn, 5, SpringLayout.NORTH, pane5);
		lay5.putConstraint(SpringLayout.WEST, this.jta_SN, 420,SpringLayout.WEST, pane5);
		lay5.putConstraint(SpringLayout.NORTH,this.jta_SN, 5, SpringLayout.NORTH, pane5);
		pane5.add(this.lab_memo);
		pane5.add(this.jta_memo);
		pane5.add(this.lab_sn);
		pane5.add(this.jta_SN);
		 
		pane4.add(pane5);
			
		jtp2.add("内容",pane4);
				                
	    Container pane=this.getContentPane();
	    JSplitPane splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jtp1,jtp2);
	     /*设置splitPane1的分隔线位置，0.3是相对于splitPane1的大小而定，因此这个值的范围在0.0～1.0
	      *中。若你使用整数值来设置splitPane的分隔线位置，如第34行所示，则所定义的值以pixel为计算单位
	      */
	     splitPane1.setDividerLocation(160);
		 pane.add(splitPane1, BorderLayout.CENTER);
		 
		 //添加事件
	     jt.addMouseListener(pc);	
	     jt.addKeyListener(pc);
	     jta_SN.addKeyListener(pc);
	     jt1.addMouseListener(pc);
	     jt1.getSelectionModel().addListSelectionListener(pc);
	     od.addTableModelListener(pc);
	     this.txt_tsn.addFocusListener(pc);
	     this.addInternalFrameListener(pc);
	     	    	     
		 this.setVisible(true);
		 this.setResizable(true);	
	}

	public JTextField getTxt_docn() {
		return txt_docn;
	}

	public void setTxt_docn(JTextField txt_docn) {
		this.txt_docn = txt_docn;
	}

	public JTextField getTxt_status() {
		return txt_status;
	}

	public void setTxt_status(JTextField txt_status) {
		this.txt_status = txt_status;
	}

	public JTextField getTxt_tsn() {
		return txt_tsn;
	}

	public void setTxt_tsn(JTextField txt_tsn) {
		this.txt_tsn = txt_tsn;
	}

	public DatePicker getTxt_date() {
		return txt_date;
	}

	public void setTxt_date(DatePicker txt_date) {
		this.txt_date = txt_date;
	}

	public JTextArea getJta_memo() {
		return jta_memo;
	}

	public void setJta_memo(JTextArea jta_memo) {
		this.jta_memo = jta_memo;
	}

	public JTextArea getJta_SN() {
		return jta_SN;
	}

	public void setJta_SN(JTextArea jta_SN) {
		this.jta_SN = jta_SN;
	}

	public JMyTable getJt() {
		return jt;
	}

	public void setJt(JMyTable jt) {
		this.jt = jt;
	}

	public JTable getJt1() {
		return jt1;
	}

	public void setJt1(JTable jt1) {
		this.jt1 = jt1;
	}

	public PaSNsController getPc() {
		return pc;
	}

	public void setPc(PaSNsController pc) {
		this.pc = pc;
	}

	public AbstractDocLineModel<ColPaSNDocLine, PaSNDocLine> getOd() {
		return od;
	}

	public void setOd(AbstractDocLineModel<ColPaSNDocLine, PaSNDocLine> od) {
		this.od = od;
	}

	public AbstractDocTitleModel<ColDocTitle, DocTitle> getOd1() {
		return od1;
	}

	public void setOd1(AbstractDocTitleModel<ColDocTitle, DocTitle> od1) {
		this.od1 = od1;
	}

	public JComboBox getCom_company() {
		return com_company;
	}

	public void setCom_company(JComboBox com_company) {
		this.com_company = com_company;
	}

	public JTextField getTxt_MNo() {
		return txt_MNo;
	}

	public void setTxt_MNo(JTextField txt_MNo) {
		this.txt_MNo = txt_MNo;
	}

}
