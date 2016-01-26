package erp.ws.sbo.client.swing.view.Orct;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
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
import erp.ws.sbo.client.swing.controller.OrctsController;
import erp.ws.sbo.client.swing.model.ColOrctDocLine;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.OrctDocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.OrctDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.OrctDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.utils.DbUtils;

public class OrctView extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5168139947074972099L;
	private OrctsController oc=new OrctsController(this);
	private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private SpringLayout lay = new SpringLayout();
	private String hql="select p.slpCode,p.slpName from Oslp p where slpcode<>-1";    
    private ColOrctDocLine cl=ColOrctDocLine.getCLine();
	private OrctDocLine dl=new OrctDocLine();
	private DbUtils<ColOrctDocLine,OrctDocLine> dbu=new DbUtils<ColOrctDocLine,OrctDocLine>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private AbstractDocLineModel<ColOrctDocLine,OrctDocLine> od;
    private AbstractDocTitleModel<ColDocTitle,DocTitle> od1;
    private OrctAppliedView oav;
    
    JLabel lab_cus = new JLabel("客户");
    JLabel lab_cusn = new JLabel("名称");
    JLabel lab_cuslxr = new JLabel("联系人");
    JLabel lab_docn = new JLabel("单号");
    JLabel lab_status = new JLabel("状态");
    JLabel lab_date = new JLabel("过账日期");
    JLabel lab_duedate = new JLabel("交货日期");
    JLabel lab_delidate = new JLabel("出厂日期");
    JLabel lab_sales = new JLabel("销售员");
    JLabel lab_sales1 = new JLabel("销售员1");
    JLabel lab_memo = new JLabel("备注");
    JLabel lab_asum = new JLabel("应收余额合计");
    JLabel lab_qsum = new JLabel("应收总计合计");
    JLabel lab_gjsum = new JLabel("收款金额合计");
    JLabel lab_mssum = new JLabel("最新余额合计");
    JLabel lab_sjzlsum = new JLabel("实际重量合计");
    JLabel lab_ter = new JLabel("地区");
    
    JComboBox txt_cus=new JComboBox();
    JTextField txt_cusn = new JTextField(20);
    JComboBox txt_cuslxr=new  JComboBox();
    JUComboBox com_sales=new JUComboBox(hql);
    JUComboBox com_sales1=new JUComboBox(hql);
    JTextArea jta_memo=new JTextArea(6,20);
    JTextField txt_docn = new JTextField(13);
    JTextField txt_docnplus = new JTextField(5);
    JTextField txt_status = new JTextField(13);
    JTextField txt_type = new JTextField(5);
    DatePicker txt_duedate = new DatePicker(pane1);
    DatePicker txt_delidate =new DatePicker(pane1);
    DatePicker txt_date  = new DatePicker(pane1,new Date());
    JTextField txt_asum = new JTextField(15);
    JTextField txt_qsum = new JTextField(15);
    JTextField txt_gjsum = new JTextField(15);
    JTextField txt_mssum = new JTextField(15);
    JTextField txt_sjzlsum = new JTextField(15);
    JTextField txt_ter = new JTextField(25);
    
    JMyTable jt;
    JTable jt1;
    
    JTabbedPane jtp1=new JTabbedPane();	
    JTabbedPane jtp2=new JTabbedPane();	
    
    JTextField  editor;
    List<Object[]> list=new ArrayList<Object[]>(); 
    Object[] obj;
    public OrctView()
    {
    	super("批量收款单",true,true,true,true);	
		initComponents();
    }
	private void initComponents() {
		// TODO Auto-generated method stub
		 this.setName("ORCT");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 appMain.fv.setText("");
		 hql="from Oslp";
		 jta_memo.setLineWrap(true);
		// txt_date.setLocale(Locale.US);//设置显示语言
		 txt_date.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_date.setEditorable(true);//设置是否可编辑
		 txt_date.setBackground(Color.gray);//设置背景色
		 txt_date.setForeground(Color.yellow);//设置前景色
		 txt_date.setPreferredSize(new Dimension(225,24));//设置大小
		 txt_date.setTextAlign(DatePicker.LEFT);
		 
		 txt_duedate.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_duedate.setEditorable(true);//设置是否可编辑
		 txt_duedate.setBackground(Color.gray);//设置背景色
		 txt_duedate.setForeground(Color.yellow);//设置前景色
		 txt_duedate.setPreferredSize(new Dimension(225,24));//设置大小
		 txt_duedate.setTextAlign(DatePicker.LEFT);
		 
		 txt_delidate.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_delidate.setEditorable(true);//设置是否可编辑
		 txt_delidate.setBackground(Color.gray);//设置背景色
		 txt_delidate.setForeground(Color.yellow);//设置前景色
		 txt_delidate.setPreferredSize(new Dimension(225,24));//设置大小
		 txt_delidate.setTextAlign(DatePicker.LEFT);
		 

		 this.txt_docn.setEditable(false);
		 this.txt_docnplus.setEditable(false);
		 this.txt_status.setEditable(false);
		 this.txt_type.setEditable(false);
		 this.txt_cusn.setEditable(false);
		 
		 pane1.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, lab_cus, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cus, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cus, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cus, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_cusn, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cusn, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cusn, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cusn, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_cuslxr, 33,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cuslxr, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cuslxr, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cuslxr, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_delidate, 20,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_delidate, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_delidate, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_delidate, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_docn, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_docn, -125,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_docnplus, -50,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_docnplus, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_status, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_status, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_status, -125,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_status, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_type, -50,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_type, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_date, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_date, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_date, -50,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_date, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_duedate, -300,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_duedate, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_duedate, -50,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_duedate, 100, SpringLayout.NORTH, pane1);
		 
		 
		// txt_cus.setSize(4, 80);
		 txt_cus.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");	
		 txt_cuslxr.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
	
	
		 pane1.add(lab_cus);
		 pane1.add(lab_cusn);
		 pane1.add(lab_cuslxr);
		 pane1.add(lab_status);	
		 pane1.add(lab_date);	
		 pane1.add(lab_delidate);	
		 pane1.add(lab_duedate);	
		 pane1.add(lab_docn);	
		 pane1.add(txt_cus);
		 pane1.add(txt_cusn);
		 pane1.add(txt_cuslxr);
		 pane1.add(txt_status);	
		 pane1.add(txt_type);	
		 pane1.add(txt_date);			
		 pane1.add(txt_delidate);	
		 pane1.add(txt_duedate);	
		 pane1.add(txt_docn);	
		 pane1.add(txt_docnplus);	
		 
		 pane3.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, lab_ter, 42,SpringLayout.WEST, pane3);
		 lay.putConstraint(SpringLayout.NORTH, lab_ter, 10, SpringLayout.NORTH, pane3);
		 lay.putConstraint(SpringLayout.WEST, txt_ter, 100,SpringLayout.WEST, pane3);
		 lay.putConstraint(SpringLayout.NORTH, txt_ter, 10, SpringLayout.NORTH, pane3);
		 
		 pane3.add(lab_ter);
		 pane3.add(txt_ter);
		 txt_ter.setEditable(false);
		 
		//单身主数据
		   
		 hql="select 0,0,0,'',0,'','',0,0,0,0,'',0 "+
		           "from Inv1 p where 1=2";	
		    od=new OrctDocLineModel(cl,dl,dbu,hql);		   	   
			od.setDocLineStatus(docLineStatus.load);
			od.setGridStatus(docLineStatus.load);
			
			jt=new JMyTable(od);
			
			jt.getColumnModel().getColumn(od.getcolumnindex("伙伴名称")).setPreferredWidth(200);
			jt.setAutoCreateRowSorter(true);	
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
			jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
			jt.setFillsViewportHeight(true);
		
			JScrollPane scp=new JScrollPane(jt);
			
			 jtp1.add("基本信息",pane1);
			 jtp1.add("数据浏览",pane2);
			 jtp1.add("自定义",pane3);
	         pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
	         pane4.add(scp);
	                 
	         //单头数据浏览
	         hql="select 0,'单据',0,'','','','','','','','','' " +	         
	     	           " from Oinv p,Oslp p1 where p.slpCode=p1.slpCode and 1=2 ";   
	        od1 = new OrctDocTitleModel(ct,dt,dbu1,hql);
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
			
			 //pane5的布局
	       
			 pane5.setLayout(lay);
			 lay.putConstraint(SpringLayout.WEST, this.lab_sales, 42,SpringLayout.WEST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_sales, 5, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.WEST, this.com_sales, 100,SpringLayout.WEST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.com_sales, 5, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.WEST, this.lab_sales1, 42,SpringLayout.WEST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_sales1, 35, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.WEST, this.com_sales1, 100,SpringLayout.WEST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.com_sales1, 35, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.WEST, this.lab_memo, 42,SpringLayout.WEST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_memo, 65, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.WEST, this.jta_memo, 100,SpringLayout.WEST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.jta_memo, 65, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.lab_asum, -300,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_asum, 5, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.txt_asum, -50,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.txt_asum, 5, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.lab_qsum, -300,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_qsum, 35, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.txt_qsum, -50,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.txt_qsum, 35, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.lab_gjsum, -300,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_gjsum, 65, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.txt_gjsum, -50,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.txt_gjsum, 65, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.lab_mssum, -300,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_mssum, 95, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.txt_mssum, -50,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.txt_mssum, 95, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.lab_sjzlsum, -300,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.lab_sjzlsum, 125, SpringLayout.NORTH, pane5);
			 lay.putConstraint(SpringLayout.EAST, this.txt_sjzlsum, -50,SpringLayout.EAST, pane5);
			 lay.putConstraint(SpringLayout.NORTH,this.txt_sjzlsum, 125, SpringLayout.NORTH, pane5);
			
			 
			 pane5.add(this.lab_sales);
			 pane5.add(this.com_sales);
			 pane5.add(this.lab_sales1);
			 pane5.add(this.com_sales1);
			 pane5.add(this.lab_memo);
			 pane5.add(this.jta_memo);
			 pane5.add(this.lab_asum);
			 pane5.add(this.txt_asum);
			 pane5.add(this.lab_qsum);
			 pane5.add(this.txt_qsum);
			 pane5.add(this.lab_gjsum);
			 pane5.add(this.txt_gjsum);
			 pane5.add(this.lab_mssum);
			 //pane5.add(this.txt_sjzlsum);
			 pane5.add(this.txt_mssum);
			 //pane5.add(this.lab_sjzlsum);
			 
	         pane4.add(pane5);
			
			 jtp2.add("内容",pane4);
				                
		     Container pane=this.getContentPane();
		     JSplitPane splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jtp1,jtp2);
		     /*设置splitPane1的分隔线位置，0.3是相对于splitPane1的大小而定，因此这个值的范围在0.0～1.0
		      *中。若你使用整数值来设置splitPane的分隔线位置，如第34行所示，则所定义的值以pixel为计算单位
		      */
		     splitPane1.setDividerLocation(160);
			 pane.add(splitPane1, BorderLayout.CENTER);
			
			 editor = (JTextField) txt_cus.getEditor().getEditorComponent();
			
			 //添加事件
			 this.com_sales.addActionListener(oc);
			 this.com_sales1.addActionListener(oc);
		     jt.addMouseListener(oc);	
		     jt.addKeyListener(oc);
		     jt1.addMouseListener(oc);
		     jt1.getSelectionModel().addListSelectionListener(oc);
		     od.addTableModelListener(oc);
		     this.addInternalFrameListener(oc);
		     	    	     
			 this.setVisible(true);
			 this.setResizable(true);	
	}
	public OrctsController getOc() {
		return oc;
	}
	public void setOc(OrctsController oc) {
		this.oc = oc;
	}
	public JPanel getPane1() {
		return pane1;
	}
	public void setPane1(JPanel pane1) {
		this.pane1 = pane1;
	}
	public JPanel getPane2() {
		return pane2;
	}
	public void setPane2(JPanel pane2) {
		this.pane2 = pane2;
	}
	public JPanel getPane3() {
		return pane3;
	}
	public void setPane3(JPanel pane3) {
		this.pane3 = pane3;
	}
	public JPanel getPane4() {
		return pane4;
	}
	public void setPane4(JPanel pane4) {
		this.pane4 = pane4;
	}
	public JPanel getPane5() {
		return pane5;
	}
	public void setPane5(JPanel pane5) {
		this.pane5 = pane5;
	}
	public SpringLayout getLay() {
		return lay;
	}
	public void setLay(SpringLayout lay) {
		this.lay = lay;
	}
	public AbstractDocLineModel<ColOrctDocLine, OrctDocLine> getOd() {
		return od;
	}
	public void setOd(AbstractDocLineModel<ColOrctDocLine, OrctDocLine> od) {
		this.od = od;
	}
	public AbstractDocTitleModel<ColDocTitle, DocTitle> getOd1() {
		return od1;
	}
	public void setOd1(AbstractDocTitleModel<ColDocTitle, DocTitle> od1) {
		this.od1 = od1;
	}
	public JComboBox getTxt_cus() {
		return txt_cus;
	}
	public void setTxt_cus(JComboBox txt_cus) {
		this.txt_cus = txt_cus;
	}
	public JTextField getTxt_cusn() {
		return txt_cusn;
	}
	public void setTxt_cusn(JTextField txt_cusn) {
		this.txt_cusn = txt_cusn;
	}
	public JComboBox getTxt_cuslxr() {
		return txt_cuslxr;
	}
	public void setTxt_cuslxr(JComboBox txt_cuslxr) {
		this.txt_cuslxr = txt_cuslxr;
	}
	public JUComboBox getCom_sales() {
		return com_sales;
	}
	public void setCom_sales(JUComboBox com_sales) {
		this.com_sales = com_sales;
	}
	public JLabel getLab_sales1() {
		return lab_sales1;
	}
	public void setLab_sales1(JLabel lab_sales) {
		this.lab_sales1 = lab_sales;
	}
	public JUComboBox getCom_sales1() {
		return com_sales1;
	}
	public void setCom_sales1(JUComboBox com_sales) {
		this.com_sales1 = com_sales;
	}
	public JTextArea getJta_memo() {
		return jta_memo;
	}
	public void setJta_memo(JTextArea jta_memo) {
		this.jta_memo = jta_memo;
	}
	public JTextField getTxt_docn() {
		return txt_docn;
	}
	public void setTxt_docn(JTextField txt_docn) {
		this.txt_docn = txt_docn;
	}
	public JTextField getTxt_docnplus() {
		return txt_docnplus;
	}
	public void setTxt_docnplus(JTextField txt_docnplus) {
		this.txt_docnplus = txt_docnplus;
	}
	public JTextField getTxt_status() {
		return txt_status;
	}
	public void setTxt_status(JTextField txt_status) {
		this.txt_status = txt_status;
	}
	public JTextField getTxt_type() {
		return txt_type;
	}
	public void setTxt_type(JTextField txt_type) {
		this.txt_type = txt_type;
	}
	public DatePicker getTxt_duedate() {
		return txt_duedate;
	}
	public void setTxt_duedate(DatePicker txt_duedate) {
		this.txt_duedate = txt_duedate;
	}
	public DatePicker getTxt_delidate() {
		return txt_delidate;
	}
	public void setTxt_delidate(DatePicker txt_delidate) {
		this.txt_delidate = txt_delidate;
	}
	public DatePicker getTxt_date() {
		return txt_date;
	}
	public void setTxt_date(DatePicker txt_date) {
		this.txt_date = txt_date;
	}
	public JTextField getTxt_asum() {
		return txt_asum;
	}
	public void setTxt_asum(JTextField txt_asum) {
		this.txt_asum = txt_asum;
	}
	public JTextField getTxt_qsum() {
		return txt_qsum;
	}
	public void setTxt_qsum(JTextField txt_qsum) {
		this.txt_qsum = txt_qsum;
	}
	public JTextField getTxt_gjsum() {
		return txt_gjsum;
	}
	public void setTxt_gjsum(JTextField txt_gjsum) {
		this.txt_gjsum = txt_gjsum;
	}
	public JTextField getTxt_mssum() {
		return txt_mssum;
	}
	public void setTxt_mssum(JTextField txt_mssum) {
		this.txt_mssum = txt_mssum;
	}
	public JTextField getTxt_sjzlsum() {
		return txt_sjzlsum;
	}
	public void setTxt_sjzlsum(JTextField txt_sjzlsum) {
		this.txt_sjzlsum = txt_sjzlsum;
	}
	public JTextField getTxt_ter() {
		return txt_ter;
	}
	public void setTxt_ter(JTextField txt_ter) {
		this.txt_ter = txt_ter;
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
	public OrctAppliedView getOav() {
		return oav;
	}
	public void setOav(OrctAppliedView oav) {
		this.oav = oav;
	}
}
