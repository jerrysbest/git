package erp.ws.sbo.client.swing.view.Oign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;

import com.eltima.components.ui.DatePicker;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.controller.OignsController;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.ColOignDocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.OignDocLine;
import erp.ws.sbo.client.swing.tablemodel.OignDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.OignDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.IntTextField;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.utils.DbUtils;
import erp.ws.sbo.utils.SerialParameters;

public class OignView extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5386813674444011307L;
	private OignsController oc=new OignsController(this);
	private SerialParameters parameters= new SerialParameters();
    private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private JPanel  pane6=new JPanel();//the query of the doc
    private JPanel  pane7=new JPanel();//set up the port and printer
    private JPanel  pane8=new JPanel();//sn
    private SpringLayout lay = new SpringLayout();
    private SpringLayout lay5 = new SpringLayout();
  
    private SpringLayout lay7 = new SpringLayout();
    private SpringLayout lay8 = new SpringLayout();
    private String  hql="select p.userid,isnull(p.U_Name,'') from Ousr as p where p.userid='"+appMain.oCompany.getUserSignature()+"'",hql1,yon,ifsn;    
    private ColOignDocLine cl=ColOignDocLine.getCLine();
	private OignDocLine dl=new OignDocLine();
	private DbUtils<ColOignDocLine,OignDocLine> dbu=new DbUtils<ColOignDocLine,OignDocLine>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private OignDocLineModel od;
    private OignDocLineModel od2;
    private OignDocTitleModel od1;
    private Object[][] ob;
    private DeSNView dsv;
    JLabel lab_type = new JLabel("类型");
    JLabel lab_status = new JLabel("状态");
    JLabel lab_whs = new JLabel("仓库");   
    JLabel lab_user = new JLabel("操作员");
    JLabel lab_date = new JLabel("日期");
    JLabel lab_SN = new JLabel("SN码");
    JLabel lab_cppo = new JLabel("查询订单");
    JLabel lab_plist = new JLabel("价格清单");
    JLabel lab_docn = new JLabel("单号,id号");
    JLabel lab_memo = new JLabel("备注");
    JLabel lab_msum = new JLabel("实际收货个数");
    JLabel lab_wsum = new JLabel("标准库存数量");
    JLabel lab_rsum = new JLabel("实际库存数量");
    JLabel lab_psum = new JLabel("计划库存数量");
    JLabel lab_csum = new JLabel("完成库存数量");
    
    //端口号和打印机设置
    JLabel lab_port = new JLabel("端口号");
    JComboBox com_port; 
    JLabel lab_baudr = new JLabel("波特率");
    JComboBox com_baudr; 
    JLabel lab_flowcin = new JLabel("流入控制");
    JComboBox com_flowcin; 
    JLabel lab_flowcout = new JLabel("流出控制");
    JComboBox com_flowcout; 
   
    JLabel lab_databits = new JLabel("数据位");
    JComboBox com_databits; 
    JLabel lab_checkbits = new JLabel("校验位");
    JComboBox com_checkbits; 
    JLabel lab_stopbits = new JLabel("停止位");
    JComboBox com_stopbits; 
    
    JLabel lab_width = new JLabel("纸张宽度");
    IntTextField txt_width = new IntTextField(0, 8);
    JLabel lab_height = new JLabel("纸张高度");
    IntTextField txt_height = new IntTextField(0, 8);
    JLabel lab_left = new JLabel("左边距");
    IntTextField txt_left = new IntTextField(0, 8);
    JLabel lab_up = new JLabel("上边距");
    IntTextField txt_up = new IntTextField(0, 8);
    JLabel lab_right = new JLabel("右边距");
    IntTextField txt_right = new IntTextField(0, 8);
    JLabel lab_down = new JLabel("下边距");
    IntTextField txt_down = new IntTextField(0, 8);
    JLabel lab_codetype = new JLabel("条码类型");
    JTextField txt_codetype = new JTextField(8);
    JLabel lab_codewidth = new JLabel("条码宽度");
    IntTextField txt_codewidth = new IntTextField(0, 8);
    JLabel lab_codeheight = new JLabel("条码高度");
    IntTextField txt_codeheight = new IntTextField(0, 8);
    JLabel lab_codegap = new JLabel("条码间隙");
    IntTextField txt_codegap = new IntTextField(0, 8);
    //生成序列号
    JLabel lab_specification = new JLabel("规格");
    JComboBox com_specification;     
    JLabel lab_length = new JLabel("米段");
    IntTextField txt_length = new IntTextField(0, 8);
    JLabel lab_weight = new JLabel("毛重");
    IntTextField txt_weight = new IntTextField(0, 8);
    JLabel lab_cweight = new JLabel("净重");
    IntTextField txt_cweight = new IntTextField(0, 8);
    JLabel lab_pweight = new JLabel("皮重");
    JTextField txt_pweight=new JTextField(8);
    JLabel lab_sweight = new JLabel("标准重");
    IntTextField txt_sweight = new IntTextField(0, 8);
    JLabel lab_deviation = new JLabel("误差");
    IntTextField txt_deviation = new IntTextField(0, 8);
    JLabel lab_MNo = new JLabel("机号");
    JTextField txt_MNo = new JTextField(8);
    JLabel lab_Qinspector = new JLabel("质检员");
    JTextField txt_Qinspector = new JTextField(8);
    JLabel lab_company = new JLabel("公司名称");
    JComboBox com_company; 
    JButton bt_selcode = new JButton("选择序列号");
    JComboBox com_selcode; 
    JLabel lab_receivew = new JLabel("接收窗口");
    JTextArea jta_receivew=new JTextArea(3,15);
    
    JLabel lab_sendw = new JLabel("发送窗口");
    JTextArea jta_sendw=new JTextArea(1,15);
    
    JButton bt_weight=new JButton("毛重");
    JButton bt_cweight=new JButton("净重");
    JButton bt_open=new JButton("打开串口");
    JButton bt_close=new JButton("关闭串口");
    JButton bt_createcode=new JButton("生成序列号");    
    JTextField txt_createcode = new JTextField(12);
    JButton bt_savep=new JButton("保存打印设置");
    JButton bt_addsn= new JButton("添加SN");
    JButton bt_print = new JButton("打印");
  
    JLabel lab_wareh = new JLabel("仓库");
    JUComboBox com_whsin=new JUComboBox(hql); 
    //
    JLabel lab_snware = new JLabel("是否序列号仓库");
    JComboBox com_snware=new JComboBox(); 
    JUComboBox com_whs=new JUComboBox(hql); 
    JUComboBox com_users=new JUComboBox(hql);
    JUComboBox com_plist=new JUComboBox(hql);
    JComboBox com_type=new JComboBox();
    JTextArea jta_memo=new JTextArea(6,20);
    DatePicker txt_date  = new DatePicker(pane1,new Date());
    JTextField txt_docn = new JTextField(8);
    JTextField txt_docnid = new JTextField(8);
    JTextField txt_status = new JTextField(8);
    JTextField txt_status1 = new JTextField(8);
    JTextArea jta_SN=new JTextArea(8,25);
    JTextField txt_cppo = new JTextField(13);
    JTextField txt_msum = new JTextField(15);
    JTextField txt_wsum = new JTextField(15);
    JTextField txt_rsum = new JTextField(15);
    JTextField txt_psum = new JTextField(15);
    JTextField txt_csum = new JTextField(15);
    JButton bt_cppo=new JButton();    
    
    JMyTable jt;
    JTable jt1;
    JMyTable jt2;
    JScrollPane scp;
    JScrollPane scp1;
    JScrollPane scp2;
    JTabbedPane jtp1=new JTabbedPane();	
    JTabbedPane jtp2=new JTabbedPane();	
    
    JSplitPane splitPane1;
    List<Object[]> list=new ArrayList<Object[]>(); 
    Object[] obj;
    JTextField  editor;
    
    public OignView()
	{
		super("生产收货和库存转储",true,true,true,true);	
		initComponents();
		dsv=new DeSNView(this);
		dsv.setVisible(false);
	}
   
	private void initComponents() {
		 this.setName("OIGN");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 appMain.fv.setText("");
		
		 bt_cppo.setText("复制订单");
		 jta_SN.setLineWrap(true);
		 jta_memo.setLineWrap(true);
		 
		 txt_date.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_date.setEditorable(true);//设置是否可编辑
		 txt_date.setBackground(Color.gray);//设置背景色
		 txt_date.setForeground(Color.yellow);//设置前景色
		 txt_date.setPreferredSize(new Dimension(150,24));//设置大小
		 txt_date.setTextAlign(DatePicker.LEFT);
		 
		 
		 hql="select u_enable from [@sms] where code='CKCZY'";
		 yon=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();
		 hql="select whscode,whsname from owhs ";	
		 if(yon.equals("Y"))
		 {
		 	hql+="where WhsCode in " +
		 	"(select U_Dtck from dbo.[@CZYCK] where " +
		 	"U_Usid=(select branch from ousr where userid='"+appMain.oCompany.getUserSignature() + "') and U_Djlx='Z')";
		 }
		 com_whs=new JUComboBox(hql);
		 hql="SELECT ListNum,ListName FROM OPLN";
		 com_plist=new JUComboBox(hql);
		 com_type.addItem( new  ComboBoxItem(0,"生产收货"));
		 com_type.addItem( new  ComboBoxItem(1,"库存转储"));
		 com_snware.addItem( new  ComboBoxItem(0,"非2107"));
		 com_snware.addItem( new  ComboBoxItem(1,"2107"));
		 com_whs.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
		 com_users.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
		 com_type.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
		 com_plist.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
		 com_snware.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
		 
		 this.txt_docn.setEditable(false);
		 this.txt_docnid.setEditable(false);
		 this.txt_cweight.setEditable(false);
		 this.txt_deviation.setEditable(false);
		 this.txt_createcode.setEditable(false);
		 this.txt_createcode.setEnabled(true);
		 this.txt_MNo.setEditable(false);
		 this.txt_pweight.setEditable(true);
		 this.txt_pweight.setEnabled(true);
		 this.txt_pweight.setText("0");
		 
		 //JOptionPane.showConfirmDialog(null, this.com_pweight.getSelectedItem().toString());
		 this.txt_Qinspector.setEditable(false);
		 this.txt_sweight.setEditable(false);
		 this.txt_weight.setEditable(false);
		 this.txt_status.setEditable(false);
		 this.txt_status1.setEditable(false);
		 this.bt_cweight.setEnabled(false);
		 this.bt_weight.setEnabled(false);
		 
		 pane1.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, lab_type, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_type, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, com_type, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_type, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_whs, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_whs, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, com_whs, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_whs, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_plist, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_plist, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, com_plist, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_plist, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_snware, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_snware, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, com_snware, 150,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_snware, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_cppo, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cppo, 130, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cppo, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cppo, 130, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, bt_cppo, 300,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, bt_cppo, 130, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_docn, -250,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_docn, -150,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_docnid, -40,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_docnid, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_user, -250,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_user, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, com_users, -100,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_users, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_date, -250,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_date, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_date, -100,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_date, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_status, -250,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_status, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_status, -130,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_status, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_status1, -10,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_status1, 100, SpringLayout.NORTH, pane1);

		 pane1.add(lab_date);
		 pane1.add(txt_date);
		 pane1.add(lab_type);
		 pane1.add(com_type);
		 pane1.add(lab_whs);
		 pane1.add(com_whs);
		 pane1.add(lab_user);
		 pane1.add(com_users);
		 pane1.add(lab_plist);
		 pane1.add(com_plist);
		 pane1.add(lab_snware);
		 pane1.add(com_snware);
		 pane1.add(lab_docn);
		 pane1.add(txt_docn);
		 pane1.add(txt_docnid);
		 pane1.add(lab_cppo);
		 pane1.add(txt_cppo);
		 pane1.add(bt_cppo);
		 pane1.add(lab_status);
		 pane1.add(txt_status);
		 pane1.add(txt_status1);
		 
		 pane7.setLayout(lay7);
		 com_port=new JComboBox();
		 com_baudr=new JComboBox();
		 
		 com_baudr.addItem("1200");
		 com_baudr.addItem("2400");	 
		 com_baudr.addItem("9600");
		 com_baudr.addItem("14400");
		 com_baudr.addItem("28800");
		 com_baudr.addItem("38400");
		 com_baudr.addItem("57600");
		 com_baudr.addItem("152000");
		  
		 com_databits=new JComboBox();		  
		 com_databits.addItem("8");
		 com_databits.addItem("7");
		 com_databits.addItem("6");
		 com_databits.addItem("5");
		  
		 com_checkbits=new JComboBox();
		 com_checkbits.addItem("None");
		 com_checkbits.addItem("Even");
		 com_checkbits.addItem("Odd");
				   
	   	 com_stopbits=new JComboBox();
	   	 com_stopbits.addItem("1");
	   	 com_stopbits.addItem("1.5");
	   	 com_stopbits.addItem("2");
	   	  	   	  
	   	 com_flowcin=new JComboBox();	   	 
	   	 com_flowcin.addItem("None");
	   	 com_flowcin.addItem("Xon/Xoff In");
	   	 com_flowcin.addItem("RTS/CTS In");
	   	  
	   	 com_flowcout=new JComboBox();
	   	 com_flowcout.addItem("None");
	   	 com_flowcout.addItem("Xon/Xoff In");
	   	 com_flowcout.addItem("RTS/CTS In");
		 //第一行
	     lay7.putConstraint(SpringLayout.WEST, lab_port, 10,SpringLayout.WEST, pane7);
   	     lay7.putConstraint(SpringLayout.NORTH, lab_port, 10, SpringLayout.NORTH, pane7);
   	     lay7.putConstraint(SpringLayout.WEST, com_port, 5,SpringLayout.EAST, lab_port);
   	     lay7.putConstraint(SpringLayout.NORTH, com_port, 10, SpringLayout.NORTH, pane7);
   	     lay7.putConstraint(SpringLayout.WEST, lab_stopbits, 5,SpringLayout.EAST, com_port);
		 lay7.putConstraint(SpringLayout.NORTH, lab_stopbits, 10, SpringLayout.NORTH, pane7);
		 lay7.putConstraint(SpringLayout.WEST, com_stopbits, 5,SpringLayout.EAST, lab_stopbits);
		 lay7.putConstraint(SpringLayout.NORTH, com_stopbits, 10, SpringLayout.NORTH, pane7);
		 lay7.putConstraint(SpringLayout.WEST, lab_width, 350,SpringLayout.WEST, pane7);
   	     lay7.putConstraint(SpringLayout.NORTH, lab_width, 10, SpringLayout.NORTH, pane7);
   	     lay7.putConstraint(SpringLayout.WEST, txt_width, 5,SpringLayout.EAST, lab_width);
		 lay7.putConstraint(SpringLayout.NORTH, txt_width, 10, SpringLayout.NORTH, pane7);
		 lay7.putConstraint(SpringLayout.WEST, lab_codetype, 5,SpringLayout.EAST, txt_width);
		 lay7.putConstraint(SpringLayout.NORTH, lab_codetype, 10, SpringLayout.NORTH, pane7);
		 lay7.putConstraint(SpringLayout.WEST, txt_codetype, 5,SpringLayout.EAST, lab_codetype);
		 lay7.putConstraint(SpringLayout.NORTH, txt_codetype, 10, SpringLayout.NORTH, pane7);
		 lay7.putConstraint(SpringLayout.WEST, lab_left, 5,SpringLayout.EAST, txt_codetype);
		 lay7.putConstraint(SpringLayout.NORTH, lab_left, 10, SpringLayout.NORTH, pane7);
		 lay7.putConstraint(SpringLayout.WEST, txt_left, 5,SpringLayout.EAST, lab_left);
		 lay7.putConstraint(SpringLayout.NORTH, txt_left, 10, SpringLayout.NORTH, pane7);
		 
		 //第二行
		 lay7.putConstraint(SpringLayout.WEST, lab_baudr, 10,SpringLayout.WEST, pane7);
   	     lay7.putConstraint(SpringLayout.NORTH, lab_baudr, 3, SpringLayout.SOUTH, com_stopbits);
   	     lay7.putConstraint(SpringLayout.WEST, com_baudr, 5,SpringLayout.EAST, lab_baudr);
   	     lay7.putConstraint(SpringLayout.NORTH, com_baudr, 3, SpringLayout.SOUTH, com_stopbits);
		 lay7.putConstraint(SpringLayout.WEST, lab_flowcin, 5, SpringLayout.EAST, com_baudr);
   	     lay7.putConstraint(SpringLayout.NORTH, lab_flowcin, 3, SpringLayout.SOUTH, com_stopbits);
   	     lay7.putConstraint(SpringLayout.WEST, com_flowcin, 5, SpringLayout.EAST, lab_flowcin);
   	     lay7.putConstraint(SpringLayout.NORTH, com_flowcin, 3, SpringLayout.SOUTH, com_stopbits);
   	     lay7.putConstraint(SpringLayout.WEST, lab_height, 350,SpringLayout.WEST, pane7);
	     lay7.putConstraint(SpringLayout.NORTH, lab_height, 3, SpringLayout.SOUTH, com_stopbits);
	     lay7.putConstraint(SpringLayout.WEST, txt_height, 5,SpringLayout.EAST, lab_height);
		 lay7.putConstraint(SpringLayout.NORTH, txt_height, 3, SpringLayout.SOUTH, com_stopbits);
		 lay7.putConstraint(SpringLayout.WEST, lab_codegap, 5,SpringLayout.EAST, txt_height);
		 lay7.putConstraint(SpringLayout.NORTH, lab_codegap, 3, SpringLayout.SOUTH, com_stopbits);
		 lay7.putConstraint(SpringLayout.WEST, txt_codegap, 5,SpringLayout.EAST, lab_codegap);
		 lay7.putConstraint(SpringLayout.NORTH, txt_codegap, 3, SpringLayout.SOUTH, com_stopbits);
		 lay7.putConstraint(SpringLayout.WEST, lab_right, 5,SpringLayout.EAST, txt_codegap);
		 lay7.putConstraint(SpringLayout.NORTH, lab_right, 3, SpringLayout.SOUTH, com_stopbits);
		 lay7.putConstraint(SpringLayout.WEST, txt_right, 5,SpringLayout.EAST, lab_right);
		 lay7.putConstraint(SpringLayout.NORTH, txt_right, 3, SpringLayout.SOUTH, com_stopbits);
	     
   	     
   	     //第三行
   	     lay7.putConstraint(SpringLayout.WEST, lab_databits, 10,SpringLayout.WEST, pane7);
	     lay7.putConstraint(SpringLayout.NORTH, lab_databits, 3, SpringLayout.SOUTH, com_flowcin);
	     lay7.putConstraint(SpringLayout.WEST, com_databits, 5,SpringLayout.EAST, lab_databits);
	     lay7.putConstraint(SpringLayout.NORTH, com_databits, 3, SpringLayout.SOUTH, com_flowcin);
	     lay7.putConstraint(SpringLayout.WEST, lab_flowcout, 0, SpringLayout.WEST, lab_flowcin);
	     lay7.putConstraint(SpringLayout.NORTH, lab_flowcout, 3, SpringLayout.SOUTH, com_flowcin);
	     lay7.putConstraint(SpringLayout.WEST, com_flowcout, 5, SpringLayout.EAST, lab_flowcout);	     
   	     lay7.putConstraint(SpringLayout.NORTH, com_flowcout, 3, SpringLayout.SOUTH, com_flowcin);
   	     lay7.putConstraint(SpringLayout.WEST, lab_codewidth, 350,SpringLayout.WEST, pane7);
	     lay7.putConstraint(SpringLayout.NORTH, lab_codewidth, 3, SpringLayout.SOUTH, com_flowcin);
	     lay7.putConstraint(SpringLayout.WEST, txt_codewidth, 5,SpringLayout.EAST, lab_codewidth);
	     lay7.putConstraint(SpringLayout.NORTH, txt_codewidth, 3, SpringLayout.SOUTH, com_flowcin);
	     lay7.putConstraint(SpringLayout.WEST, lab_up, 5, SpringLayout.EAST, txt_codewidth);
	     lay7.putConstraint(SpringLayout.NORTH, lab_up, 3, SpringLayout.SOUTH, com_flowcin);
	     lay7.putConstraint(SpringLayout.WEST, txt_up, 5, SpringLayout.EAST, lab_up);	     
	     lay7.putConstraint(SpringLayout.NORTH, txt_up, 3, SpringLayout.SOUTH, com_flowcin);
   	    	     
   	     //第四行
   	     lay7.putConstraint(SpringLayout.WEST, lab_checkbits, 10,SpringLayout.WEST, pane7);
   	     lay7.putConstraint(SpringLayout.NORTH, lab_checkbits, 3, SpringLayout.SOUTH, com_flowcout);
   	     lay7.putConstraint(SpringLayout.WEST, com_checkbits, 5,SpringLayout.EAST, lab_checkbits);
   	     lay7.putConstraint(SpringLayout.NORTH, com_checkbits, 3, SpringLayout.SOUTH, com_flowcout);
   	     lay7.putConstraint(SpringLayout.WEST, lab_codeheight, 350,SpringLayout.WEST, pane7);
	     lay7.putConstraint(SpringLayout.NORTH, lab_codeheight, 3, SpringLayout.SOUTH, com_flowcout);
	     lay7.putConstraint(SpringLayout.WEST, txt_codeheight, 5,SpringLayout.EAST, lab_codeheight);
	     lay7.putConstraint(SpringLayout.NORTH, txt_codeheight, 3, SpringLayout.SOUTH, com_flowcout);
	     lay7.putConstraint(SpringLayout.WEST, lab_down, 5, SpringLayout.EAST, txt_codeheight);
	     lay7.putConstraint(SpringLayout.NORTH, lab_down, 3, SpringLayout.SOUTH, com_flowcout);
	     lay7.putConstraint(SpringLayout.WEST, txt_down, 5, SpringLayout.EAST, lab_down);	     
	     lay7.putConstraint(SpringLayout.NORTH, txt_down, 3, SpringLayout.SOUTH, com_flowcout);
			   	   		
		 //第五行
   	     lay7.putConstraint(SpringLayout.WEST, bt_open, 10,SpringLayout.WEST, pane7);
	     lay7.putConstraint(SpringLayout.NORTH, bt_open, 3, SpringLayout.SOUTH, com_checkbits);
	     lay7.putConstraint(SpringLayout.WEST, bt_close, 5,SpringLayout.EAST, bt_open);
	     lay7.putConstraint(SpringLayout.NORTH, bt_close, 3, SpringLayout.SOUTH, com_checkbits);
	     lay7.putConstraint(SpringLayout.WEST, bt_savep, 350,SpringLayout.WEST, pane7);
	     lay7.putConstraint(SpringLayout.NORTH, bt_savep, 3, SpringLayout.SOUTH, com_checkbits);
	     
	     hql="select u_width,u_height,u_codewidth,u_codeheight,u_codetype,u_codegap,u_left," +
 			"u_up,u_right,u_down " +
 			" from dbo.[@SNPRINTER]  " +
 			"where code='1' ";
 		 ob = appMain.lt.sqlclob(hql,0,1); 
 		 if(ob==null||ob.length==0)
 		 {
 			 return;
 		 }
		 
		 pane7.add(lab_port);
		 pane7.add(com_port);
		 pane7.add(lab_baudr);
		 pane7.add(com_baudr);
		 pane7.add(lab_databits);
		 pane7.add(com_databits);
		 pane7.add(lab_checkbits);
		 pane7.add(com_checkbits);
		 pane7.add(lab_stopbits);
		 pane7.add(com_stopbits);
		 pane7.add(lab_flowcin);
		 pane7.add(com_flowcin);
		 pane7.add(lab_flowcout);
		 pane7.add(com_flowcout);
		 bt_open.setHorizontalAlignment(SwingConstants.LEADING);
		 pane7.add(bt_open);
		 bt_close.setHorizontalAlignment(SwingConstants.LEADING);
		 pane7.add(bt_close);
		 pane7.add(lab_width);
		 txt_width.setEditable(false);
		 pane7.add(txt_width);
		 pane7.add(lab_height);
		 txt_height.setEditable(false);
		 pane7.add(txt_height);
		 pane7.add(lab_codewidth);
		 txt_codewidth.setEditable(false);
		 pane7.add(txt_codewidth);
		 pane7.add(lab_codeheight);
		 txt_codeheight.setEditable(false);
		 pane7.add(txt_codeheight);
		 pane7.add(lab_codegap);
		 txt_codegap.setEditable(false);
		 pane7.add(txt_codegap);
		 pane7.add(lab_codetype);
		 txt_codetype.setEditable(false);
		 pane7.add(txt_codetype);
		 pane7.add(lab_left);
		 txt_left.setEditable(false);
		 pane7.add(txt_left);
		 pane7.add(lab_right);
		 txt_right.setEditable(false);
		 pane7.add(txt_right);
		 pane7.add(lab_up);
		 txt_up.setEditable(false);
		 pane7.add(txt_up);
		 pane7.add(lab_down);
		 txt_down.setEditable(false);
		 pane7.add(txt_down);
		 getTxt_width().setText(ob[0][0].toString());
		 getTxt_height().setText(ob[0][1].toString());
		 getTxt_codewidth().setText(ob[0][2].toString());
		 getTxt_codeheight().setText(ob[0][3].toString());
		 getTxt_codetype().setText(ob[0][4].toString());
		 getTxt_codegap().setText(ob[0][5].toString());
		 getTxt_left().setText(ob[0][6].toString());
		 getTxt_up().setText(ob[0][7].toString());
		 getTxt_right().setText(ob[0][8].toString());
		 getTxt_down().setText(ob[0][9].toString());	
		 bt_savep.setEnabled(false);
		 bt_savep.setHorizontalAlignment(SwingConstants.LEADING);
		 pane7.add(bt_savep);
		 
		 pane8.setLayout(lay8);
		 
		 com_company=new JComboBox();
		 com_company.addItem( new  ComboBoxItem("0","沙河市潜水电线厂"));
		 com_company.addItem( new  ComboBoxItem("1","国潜"));
		 
		 hql= "select itemCode+','+itemName from  oitm  " +
				" where " +
				"(itemCode like :str1 or itemName like :str2) and validfor='Y' AND frozenfor='N' and u_usn='Y'";
	   	 hql1="select itemcode from  oitm " +
				" where  " +
				"itemCode collate Chinese_prc_cs_as_ws=:str1 and validfor='Y' AND frozenfor='N' and u_usn='Y'";
		 com_specification = new JAutoCompleteComboBox(hql,hql1,2);
		 hql= "select  sn from [@snstatus] " +
					" where " +
					"(sn like :str1 or sn like :str2) and (ifdes<>'1' or ifdes is null) " +
					"order by datetime desc";
	   	 hql1="select  sn from [@snstatus] " +
				" where  " +
				"sn=:str1 and (ifdes<>'1' or ifdes is null)";
		 com_selcode= new JAutoCompleteComboBox(hql,hql1,2);
		 
		 
		 bt_weight.setText("毛重");
		 jta_SN.setLineWrap(true);
		 jta_memo.setLineWrap(true);
		 
		 txt_date.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_date.setEditorable(true);//设置是否可编辑
		 txt_date.setBackground(Color.gray);//设置背景色
		 txt_date.setForeground(Color.yellow);//设置前景色
		 txt_date.setPreferredSize(new Dimension(150,24));//设置大小
		 txt_date.setTextAlign(DatePicker.LEFT);
		 hql="select u_enable from [@sms] where code='CKCZY'";
		 yon=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();
		
		 hql="select whscode,whscode from owhs where whscode like '2107%'";	
		 com_whsin=new JUComboBox(hql);
		
		 hql="select p.userid,isnull(p.U_Name,'') from Ousr as p where p.userid='"+appMain.oCompany.getUserSignature()+"'";
		 com_users=new JUComboBox(hql);
		
		 com_whsin.setPrototypeDisplayValue("xxxxxx");
		 com_users.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
		
		
		 
		 lay8.putConstraint(SpringLayout.WEST, lab_specification, 10,SpringLayout.WEST, pane8);
		 lay8.putConstraint(SpringLayout.NORTH, lab_specification, 10, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, com_specification, 5,SpringLayout.EAST, lab_specification);
		 lay8.putConstraint(SpringLayout.NORTH, com_specification, 10, SpringLayout.NORTH, pane8);	
		 lay8.putConstraint(SpringLayout.WEST, lab_pweight, 5,SpringLayout.EAST, com_specification);
		 lay8.putConstraint(SpringLayout.NORTH, lab_pweight, 10, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_pweight, 5,SpringLayout.EAST, lab_pweight);
		 lay8.putConstraint(SpringLayout.NORTH, txt_pweight, 10, SpringLayout.NORTH, pane8);		
		 lay8.putConstraint(SpringLayout.WEST, jta_receivew, 5,SpringLayout.EAST, txt_pweight);
		 lay8.putConstraint(SpringLayout.NORTH, jta_receivew, 10, SpringLayout.NORTH, pane8);
		 //第二行
		 lay8.putConstraint(SpringLayout.WEST, lab_length, 10,SpringLayout.WEST, pane8);
		 lay8.putConstraint(SpringLayout.NORTH, lab_length, 40, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_length, 5,SpringLayout.EAST, lab_length);
		 lay8.putConstraint(SpringLayout.NORTH, txt_length, 40, SpringLayout.NORTH, pane8);	
		 lay8.putConstraint(SpringLayout.WEST, lab_sweight, 5,SpringLayout.EAST, txt_length);
		 lay8.putConstraint(SpringLayout.NORTH, lab_sweight, 40, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_sweight, 5,SpringLayout.EAST, lab_sweight);
		 lay8.putConstraint(SpringLayout.NORTH, txt_sweight, 40, SpringLayout.NORTH, pane8);
		
		 //第三行
		 lay8.putConstraint(SpringLayout.WEST, lab_weight, 10,SpringLayout.WEST, pane8);
		 lay8.putConstraint(SpringLayout.NORTH, lab_weight, 70, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_weight, 5,SpringLayout.EAST, lab_weight);
		 lay8.putConstraint(SpringLayout.NORTH, txt_weight, 70, SpringLayout.NORTH, pane8);	
		 lay8.putConstraint(SpringLayout.WEST, lab_deviation, 5,SpringLayout.EAST, txt_weight);
		 lay8.putConstraint(SpringLayout.NORTH, lab_deviation, 70, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_deviation, 5,SpringLayout.EAST,lab_deviation);
		 lay8.putConstraint(SpringLayout.NORTH, txt_deviation, 70, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, bt_weight, 5,SpringLayout.EAST, txt_deviation);
		 lay8.putConstraint(SpringLayout.NORTH, bt_weight, 70, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, bt_cweight, 5,SpringLayout.EAST, bt_weight);
		 lay8.putConstraint(SpringLayout.NORTH, bt_cweight, 70, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, jta_sendw, 5,SpringLayout.EAST, txt_pweight);
		 lay8.putConstraint(SpringLayout.NORTH, jta_sendw, 70, SpringLayout.NORTH, pane8);
		 
		 //第四行
		 lay8.putConstraint(SpringLayout.WEST, lab_cweight, 10,SpringLayout.WEST, pane8);
		 lay8.putConstraint(SpringLayout.NORTH, lab_cweight, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_cweight, 5,SpringLayout.EAST,lab_cweight);
		 lay8.putConstraint(SpringLayout.NORTH, txt_cweight, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, lab_company, 5,SpringLayout.EAST,txt_cweight);
		 lay8.putConstraint(SpringLayout.NORTH, lab_company, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, com_company, 5,SpringLayout.EAST, lab_company);
		 lay8.putConstraint(SpringLayout.NORTH, com_company, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, bt_createcode, 5,SpringLayout.EAST, com_company);
		 lay8.putConstraint(SpringLayout.NORTH, bt_createcode, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_createcode, 5,SpringLayout.EAST,bt_createcode);
		 lay8.putConstraint(SpringLayout.NORTH, txt_createcode, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, lab_MNo, 5,SpringLayout.EAST, txt_createcode);
		 lay8.putConstraint(SpringLayout.NORTH, lab_MNo, 100, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST,  txt_MNo, 5,SpringLayout.EAST,lab_MNo);
		 lay8.putConstraint(SpringLayout.NORTH, txt_MNo, 100, SpringLayout.NORTH, pane8);
		 //第五行 
		 lay8.putConstraint(SpringLayout.WEST, bt_addsn, 10,SpringLayout.WEST, pane8);
		 lay8.putConstraint(SpringLayout.NORTH, bt_addsn, 130, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, bt_print, 5,SpringLayout.EAST, bt_addsn);
		 lay8.putConstraint(SpringLayout.NORTH, bt_print, 130, SpringLayout.NORTH, pane8);
		
		 lay8.putConstraint(SpringLayout.EAST, lab_wareh, 25,SpringLayout.EAST, bt_print);
		 lay8.putConstraint(SpringLayout.NORTH, lab_wareh, 130, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, com_whsin,5,SpringLayout.EAST,  lab_wareh);
		 lay8.putConstraint(SpringLayout.NORTH, com_whsin, 130, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, bt_selcode, 5,SpringLayout.EAST, com_whsin);
		 lay8.putConstraint(SpringLayout.NORTH, bt_selcode, 130, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, com_selcode, 5,SpringLayout.EAST,bt_selcode);
		 lay8.putConstraint(SpringLayout.NORTH, com_selcode, 130, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, lab_Qinspector,5,SpringLayout.EAST, com_selcode);
		 lay8.putConstraint(SpringLayout.NORTH, lab_Qinspector,130, SpringLayout.NORTH, pane8);
		 lay8.putConstraint(SpringLayout.WEST, txt_Qinspector, 5,SpringLayout.EAST,lab_Qinspector);
		 lay8.putConstraint(SpringLayout.NORTH, txt_Qinspector, 130, SpringLayout.NORTH, pane8);
		 		 
		 pane8.add(lab_specification);
		 pane8.add(com_specification);
		 pane8.add(lab_length);
		 pane8.add(txt_length);
		 pane8.add(lab_weight);
		 pane8.add(txt_weight);
		 pane8.add(lab_cweight);
		 pane8.add(txt_cweight);
		 pane8.add(lab_sweight);
		 pane8.add(txt_sweight);
		 pane8.add(bt_weight);
		 pane8.add(bt_cweight);
		 pane8.add(bt_addsn);
		 pane8.add(bt_print);
		 pane8.add(lab_pweight);
		 pane8.add(txt_pweight);
		 pane8.add(lab_deviation);
		 pane8.add(txt_deviation);
		 pane8.add(jta_receivew);
		 pane8.add(jta_sendw);
		 pane8.add(lab_MNo);
		 pane8.add(txt_MNo);
		 pane8.add(lab_Qinspector);
		 pane8.add(txt_Qinspector);
		 pane8.add(bt_createcode);
		 pane8.add(txt_createcode);
		 pane8.add(bt_selcode);
		 pane8.add(com_selcode);
		 pane8.add(lab_company);
		 pane8.add(com_company);
		 pane8.add(lab_wareh);
		 pane8.add(com_whsin);
		 //序列号是否开启
		 hql="select u_enable from [@sms] where code='OIGNSN'";
		 ifsn=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();
		 if(!ifsn.equals("Y"))
		 {
			 pane7.setVisible(false);
			 pane8.setVisible(false);
			 com_snware.setVisible(false);
		 }
		 
		 //单身主数据			   
		 hql="select '','','','','','','','','','','','','','','','','','','' "+
	           "from Inv1 p where 1=2";	
	     od=new OignDocLineModel(cl,dl,dbu,hql);		   	   
		 od.setDocLineStatus(docLineStatus.load);
		 od.setGridStatus(docLineStatus.load);
		 //单身主数据			   
		 hql="select '','','','','','','','','','','','','','','','','','','' "+
	           "from Inv1 p where 1=2";	
	     od2=new OignDocLineModel(cl,dl,dbu,hql);		   	   
		 od2.setDocLineStatus(docLineStatus.load);
		 od2.setGridStatus(docLineStatus.load);
		 
		 jt=new JMyTable(od);
		 jt2=new JMyTable(od2);
		 //如果库存转储序列号单据启用的话，只能输入不是序列号管理的的单据
		 hql = "select U_enable from [@SMS] where code='CSTSN'";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 hql= "select p.itemCode+','+p.itemName from Oitm as p " +
					//" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
					" where  "+		
					"(p.itemCode like :str1 or p.itemName like :str2) and p.validfor='Y' AND p.frozenfor='N'";
		 if(ob[0][0].toString().equals("Y"))
		 {
			//hql+=" and p.U_Usn='N'";
		 }
		 hql1="select p.itemCode from Oitm as p " +
					//" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
					" where " +
					"p.itemCode=:str1  and validfor='Y' AND frozenfor='N'";
		
		 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("物料代码")), hql,hql1);
		 //绑定仓库
		 hql= "select p.whsCode+','+p.whsName from Owhs as p " +
					" where (p.whsCode like :str1 or p.whsName like :str2)";
		
		 hql1="select p.whsCode from Owhs as p " +
					" where p.whsCode=:str1";
		
		 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("仓库")), hql,hql1);
		 
		 obj=new Object[2];
	     obj[0]="Y";obj[1]="是";
	     list.add(obj);
	     obj=new Object[2];
	     obj[0]="N";obj[1]="否";
	     list.add(obj);
	     
		 od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("是否米段线")), list);
		 
		 jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
		 jt.getColumnModel().getColumn(od.getcolumnindex("物料描述")).setPreferredWidth(120);
		 jt.getColumnModel().getColumn(od.getcolumnindex("物料代码")).setPreferredWidth(140);
		 jt.setAutoCreateRowSorter(true);	
		 jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		 jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
		 jt.setFillsViewportHeight(true);
		 
		 jt2.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
		 jt2.getColumnModel().getColumn(od.getcolumnindex("物料描述")).setPreferredWidth(120);
		 jt2.getColumnModel().getColumn(od.getcolumnindex("物料代码")).setPreferredWidth(140);
		 jt2.setAutoCreateRowSorter(true);	
		 jt2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		 jt2.setPreferredScrollableViewportSize(new Dimension(500, 170));
		 jt2.setFillsViewportHeight(true);
	
		 scp=new JScrollPane(jt);
		 scp2=new JScrollPane(jt2);		 		
		 jtp1.add("基本信息",pane1);
		 if(ifsn.equals("Y"))
		 {
			 jtp1.add("生成序列号",pane8); 
			 jtp1.add("端口和打印机设置",pane7);	  
		 }
		 jtp1.add("数据浏览",pane2);
		 jtp1.add("自定义",pane3);
         pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
         pane4.add(scp);
         pane6.setLayout(new BoxLayout(pane6, BoxLayout.Y_AXIS));
         pane6.add(scp2);
         
         //单头数据浏览
         hql="select '','','','','','','','','','','',''" +	         
     	           " from Ordr where 1=2 ";   
        od1 = new OignDocTitleModel(ct,dt,dbu1,hql,true);
 		od1.setDs(docTitleStatus.load);
 	    od1.setDocTitleStatus(this);
 		
 		jt1=new JTable(od1);
 		
 		jt1.setName("data-browser");
 		jt1.setAutoCreateRowSorter(true);	
		jt1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt1.setPreferredScrollableViewportSize(new Dimension(500, 170));
		jt1.setFillsViewportHeight(true);
		
		
		scp1=new JScrollPane(jt1);
		
		pane2.setLayout(new BoxLayout(pane2, BoxLayout.Y_AXIS));
		pane2.add(scp1);
		
		 //pane5的布局
		 pane5.setLayout(lay5);
		 lay5.putConstraint(SpringLayout.WEST, this.lab_memo, 42,SpringLayout.WEST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_memo, 5, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.WEST, this.jta_memo, 42,SpringLayout.WEST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.jta_memo, 35, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.WEST, this.lab_SN, 280,SpringLayout.WEST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_SN, 5, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.WEST, this.jta_SN, 280,SpringLayout.WEST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.jta_SN, 35, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.lab_msum, -300,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_msum, 5, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.txt_msum, -50,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.txt_msum, 5, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.lab_wsum, -300,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_wsum, 35, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.txt_wsum, -50,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.txt_wsum, 35, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.lab_rsum, -300,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_rsum, 65, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.txt_rsum, -50,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.txt_rsum, 65, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.lab_psum, -300,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_psum, 95, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.txt_psum, -50,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.txt_psum, 95, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.lab_csum, -300,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.lab_csum, 125, SpringLayout.NORTH, pane5);
		 lay5.putConstraint(SpringLayout.EAST, this.txt_csum, -50,SpringLayout.EAST, pane5);
		 lay5.putConstraint(SpringLayout.NORTH,this.txt_csum, 125, SpringLayout.NORTH, pane5);
		
		 pane5.add(this.lab_memo);
		 pane5.add(this.jta_memo);
		 pane5.add(this.lab_msum);
		 pane5.add(this.txt_msum);
		 pane5.add(this.lab_wsum);
		 pane5.add(this.txt_wsum);
		 pane5.add(this.lab_rsum);
		 pane5.add(this.txt_rsum);
		 pane5.add(this.lab_psum);
		 pane5.add(this.txt_psum);
		 pane5.add(this.lab_csum);
		 pane5.add(this.txt_csum);
		 pane5.add(this.lab_SN);
		 pane5.add(this.jta_SN);
         pane4.add(pane5);
		
		 jtp2.add("内容",pane4);
		 jtp2.add("查询订单",pane6);	                
	     Container pane=this.getContentPane();
	     splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jtp1,jtp2);
	     /*设置splitPane1的分隔线位置，0.3是相对于splitPane1的大小而定，因此这个值的范围在0.0～1.0
	      *中。若你使用整数值来设置splitPane的分隔线位置，如第34行所示，则所定义的值以pixel为计算单位
	      */
	     splitPane1.setDividerLocation(190);
		 pane.add(splitPane1, BorderLayout.CENTER);
		 
		 //添加事件
		 com_type.addActionListener(oc);
		 com_snware.addActionListener(oc);
		 bt_cppo.addActionListener(oc);
		 txt_cppo.addKeyListener(oc);
		
	 
	     jt1.addMouseListener(oc);
	     jt1.getSelectionModel().addListSelectionListener(oc);
	  		 
	     jt.addMouseListener(oc);	
	     jt.addKeyListener(oc);
	     od.addTableModelListener(oc);
	     //序列号是否开启
		
		 if(ifsn.equals("Y"))
		 {			 
		     this.bt_close.addActionListener(oc);
			 this.bt_open.addActionListener(oc);
			 this.bt_savep.addActionListener(oc);
		     this.bt_selcode.addActionListener(oc);
		     this.bt_createcode.addActionListener(oc);
		     this.bt_addsn.addActionListener(oc);
		     this.bt_weight.addActionListener(oc);
		     this.bt_cweight.addActionListener(oc);
		     this.bt_print.addActionListener(oc);
		     this.txt_length.addFocusListener(oc);
		     this.txt_length.addKeyListener(oc);
		     this.com_specification.addActionListener(oc);
		     this.com_snware.addActionListener(oc);
		     this.txt_pweight.addKeyListener(oc);		    
		     jta_SN.addKeyListener(oc);
	      }	     
		 this.addInternalFrameListener(oc); 	     
		 this.setVisible(true);
		 this.setResizable(true);	
		 
	}
	public OignsController getOc() {
		return oc;
	}
	public void setOc(OignsController oc) {
		this.oc = oc;
	}
	public DbUtils<ColOignDocLine, OignDocLine> getDbu() {
		return dbu;
	}
	public void setDbu(DbUtils<ColOignDocLine, OignDocLine> dbu) {
		this.dbu = dbu;
	}
	public DbUtils<ColDocTitle, DocTitle> getDbu1() {
		return dbu1;
	}
	public void setDbu1(DbUtils<ColDocTitle, DocTitle> dbu1) {
		this.dbu1 = dbu1;
	}
	public JUComboBox getCom_whs() {
		return com_whs;
	}
	public void setCom_whs(JUComboBox com_whs) {
		this.com_whs = com_whs;
	}
	public JUComboBox getCom_users() {
		return com_users;
	}
	public void setCom_users(JUComboBox com_users) {
		this.com_users = com_users;
	}
	public JComboBox getCom_type() {
		return com_type;
	}
	public void setCom_type(JComboBox com_type) {
		this.com_type = com_type;
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
	public JMyTable getJt() {
		return jt;
	}
	public void setJt(JMyTable jt) {
		this.jt = jt;
	}
	public JMyTable getJt2() {
		return jt2;
	}
	public void setJt2(JMyTable jt) {
		this.jt2 = jt;
	}
	public JTable getJt1() {
		return jt1;
	}
	public void setJt1(JTable jt1) {
		this.jt1 = jt1;
	}
	public JTabbedPane getJtp1() {
		return jtp1;
	}
	public void setJtp1(JTabbedPane jtp1) {
		this.jtp1 = jtp1;
	}
	public JTabbedPane getJtp2() {
		return jtp2;
	}
	public void setJtp2(JTabbedPane jtp2) {
		this.jtp2 = jtp2;
	}

	public JTextField getTxt_msum() {
		return txt_msum;
	}

	public void setTxt_msum(JTextField txt_msum) {
		this.txt_msum = txt_msum;
	}

	public JTextField getTxt_wsum() {
		return txt_wsum;
	}

	public void setTxt_wsum(JTextField txt_wsum) {
		this.txt_wsum = txt_wsum;
	}

	public JTextField getTxt_rsum() {
		return txt_rsum;
	}

	public void setTxt_rsum(JTextField txt_rsum) {
		this.txt_rsum = txt_rsum;
	}

	public JTextField getTxt_psum() {
		return txt_psum;
	}

	public void setTxt_psum(JTextField txt_psum) {
		this.txt_psum = txt_psum;
	}

	public JTextField getTxt_csum() {
		return txt_csum;
	}

	public void setTxt_csum(JTextField txt_csum) {
		this.txt_csum = txt_csum;
	}

	public OignDocLineModel getOd() {
		return od;
	}

	public void setOd(OignDocLineModel od) {
		this.od = od;
	}
	
	public OignDocLineModel getOd2() {
		return od2;
	}

	public void setOd2(OignDocLineModel od) {
		this.od2 = od;
	}

	public OignDocTitleModel getOd1() {
		return od1;
	}

	public void setOd1(OignDocTitleModel od1) {
		this.od1 = od1;
	}

	public JUComboBox getCom_plist() {
		return com_plist;
	}

	public void setCom_plist(JUComboBox com_plist) {
		this.com_plist = com_plist;
	}

	public JTextField getTxt_cppo() {
		return txt_cppo;
	}

	public void setBt_cppo(JButton bt_cppo) {
		this.bt_cppo = bt_cppo;
	}

	public JButton getBt_cppo() {
		return bt_cppo;
	}

	public void setTxt_cppo(JTextField txt_cppo) {
		this.txt_cppo = txt_cppo;
	}

	public DeSNView getDsv() {
		return dsv;
	}

	public void setDsv(DeSNView dsv) {
		this.dsv = dsv;
	}

	public DatePicker getTxt_date() {
		return txt_date;
	}

	public void setTxt_date(DatePicker txt_date) {
		this.txt_date = txt_date;
	}

	public JTextArea getJta_SN() {
		return jta_SN;
	}

	public void setJta_SN(JTextArea jta_SN) {
		this.jta_SN = jta_SN;
	}

	public JComboBox getCom_flowcin() {
		return com_flowcin;
	}

	public void setCom_flowcin(JComboBox com_flowcin) {
		this.com_flowcin = com_flowcin;
	}

	public JComboBox getCom_flowcout() {
		return com_flowcout;
	}

	public void setCom_flowcout(JComboBox com_flowcout) {
		this.com_flowcout = com_flowcout;
	}
	public void setNewCursor(Cursor c) {
		setCursor(c);
		this.jta_receivew.setCursor(c);
		this.jta_sendw.setCursor(c);
	}
	public SerialParameters getParameters() {
		return parameters;
	}

	public void setParameters() {
		appMain.fv.setText(this.com_port.getSelectedItem().toString());
        parameters.setPortName(this.com_port.getSelectedItem().toString());
        parameters.setBaudRate(this.com_baudr.getSelectedItem().toString());
        parameters.setFlowControlIn(this.com_flowcin.getSelectedItem().toString());
        parameters.setFlowControlOut(this.com_flowcout.getSelectedItem().toString());
        parameters.setDatabits(this.com_databits.getSelectedItem().toString());
        parameters.setStopbits(this.com_stopbits.getSelectedItem().toString());
        parameters.setParity(this.com_checkbits.getSelectedItem().toString());
    }
	 public void portOpened() {
		getBt_open().setEnabled(false);
		getBt_close().setEnabled(true);
		getBt_cweight().setEnabled(false);
		getBt_weight().setEnabled(true);
	}

    public JButton getBt_weight() {
			return bt_weight;
	}
    public JButton getBt_cweight() {
		return bt_cweight;
	}
	public JButton getBt_addsn() {
		return bt_addsn;
	}

	public void setBt_addsn(JButton bt_addsn) {
		this.bt_addsn = bt_addsn;
	}

	public JTextField getTxt_pweight() {
		return txt_pweight;
	}

	public void setTxt_pweight(JTextField txt_pweight) {
		this.txt_pweight =txt_pweight;
	}

	public JButton getBt_open() {
		return bt_open;
	}

	public void setBt_open(JButton bt_open) {
		this.bt_open = bt_open;
	}

	public JButton getBt_close() {
		return bt_close;
	}

	public void setBt_close(JButton bt_close) {
		this.bt_close = bt_close;
	}

	public JButton getBt_createcode() {
		return bt_createcode;
	}

	public void setBt_createcode(JButton bt_createcode) {
		this.bt_createcode = bt_createcode;
	}
	
	public JComboBox getCom_baudr() {
		return com_baudr;
	}

	public void setCom_baudr(JComboBox com_baudr) {
		this.com_baudr = com_baudr;
	}

	public JLabel getLab_databits() {
		return lab_databits;
	}

	public void setLab_databits(JLabel lab_databits) {
		this.lab_databits = lab_databits;
	}

	public JComboBox getCom_databits() {
		return com_databits;
	}

	public void setCom_databits(JComboBox com_databits) {
		this.com_databits = com_databits;
	}

	public JLabel getLab_checkbits() {
		return lab_checkbits;
	}

	public void setLab_checkbits(JLabel lab_checkbits) {
		this.lab_checkbits = lab_checkbits;
	}

	public JComboBox getCom_checkbits() {
		return com_checkbits;
	}

	public void setCom_checkbits(JComboBox com_checkbits) {
		this.com_checkbits = com_checkbits;
	}

	public JLabel getLab_stopbits() {
		return lab_stopbits;
	}

	public void setLab_stopbits(JLabel lab_stopbits) {
		this.lab_stopbits = lab_stopbits;
	}

	public JComboBox getCom_stopbits() {
		return com_stopbits;
	}

	public void setCom_stopbits(JComboBox com_stopbits) {
		this.com_stopbits = com_stopbits;
	}

	public JLabel getLab_width() {
		return lab_width;
	}

	public void setLab_width(JLabel lab_width) {
		this.lab_width = lab_width;
	}

	public JComboBox getCom_port() {
		return com_port;
	}

	public void setCom_port(JComboBox com_port) {
		this.com_port = com_port;
	}

	public IntTextField getTxt_width() {
		return txt_width;
	}

	public void setTxt_width(IntTextField txt_width) {
		this.txt_width = txt_width;
	}

	public IntTextField getTxt_height() {
		return txt_height;
	}

	public void setTxt_height(IntTextField txt_height) {
		this.txt_height = txt_height;
	}

	public IntTextField getTxt_left() {
		return txt_left;
	}

	public void setTxt_left(IntTextField txt_left) {
		this.txt_left = txt_left;
	}

	public IntTextField getTxt_up() {
		return txt_up;
	}

	public void setTxt_up(IntTextField txt_up) {
		this.txt_up = txt_up;
	}

	public IntTextField getTxt_right() {
		return txt_right;
	}

	public void setTxt_right(IntTextField txt_right) {
		this.txt_right = txt_right;
	}

	public IntTextField getTxt_down() {
		return txt_down;
	}

	public void setTxt_down(IntTextField txt_down) {
		this.txt_down = txt_down;
	}

	public JTextField getTxt_codetype() {
		return txt_codetype;
	}

	public void setTxt_codetype(JTextField txt_codetype) {
		this.txt_codetype = txt_codetype;
	}

	public IntTextField getTxt_codewidth() {
		return txt_codewidth;
	}

	public void setTxt_codewidth(IntTextField txt_codewidth) {
		this.txt_codewidth = txt_codewidth;
	}

	public IntTextField getTxt_codeheight() {
		return txt_codeheight;
	}

	public void setTxt_codeheight(IntTextField txt_codeheight) {
		this.txt_codeheight = txt_codeheight;
	}

	public IntTextField getTxt_codegap() {
		return txt_codegap;
	}

	public void setTxt_codegap(IntTextField txt_codegap) {
		this.txt_codegap = txt_codegap;
	}

	public JComboBox getCom_specification() {
		return com_specification;
	}

	public void setCom_specification(JComboBox com_specification) {
		this.com_specification = com_specification;
	}

	public IntTextField getTxt_length() {
		return txt_length;
	}

	public void setTxt_length(IntTextField txt_length) {
		this.txt_length = txt_length;
	}

	public IntTextField getTxt_weight() {
		return txt_weight;
	}

	public void setTxt_weight(IntTextField txt_weight) {
		this.txt_weight = txt_weight;
	}

	public IntTextField getTxt_cweight() {
		return txt_cweight;
	}

	public void setTxt_cweight(IntTextField txt_cweight) {
		this.txt_cweight = txt_cweight;
	}

	public IntTextField getTxt_sweight() {
		return txt_sweight;
	}

	public void setTxt_sweight(IntTextField txt_sweight) {
		this.txt_sweight = txt_sweight;
	}

	public IntTextField getTxt_deviation() {
		return txt_deviation;
	}

	public void setTxt_deviation(IntTextField txt_deviation) {
		this.txt_deviation = txt_deviation;
	}

	public JTextField getTxt_MNo() {
		return txt_MNo;
	}

	public void setTxt_MNo(JTextField txt_MNo) {
		this.txt_MNo = txt_MNo;
	}

	public JTextField getTxt_Qinspector() {
		return txt_Qinspector;
	}

	public void setTxt_Qinspector(JTextField txt_Qinspector) {
		this.txt_Qinspector = txt_Qinspector;
	}

	public JComboBox getCom_company() {
		return com_company;
	}

	public void setCom_company(JComboBox com_company) {
		this.com_company = com_company;
	}

	public JButton getBt_selcode() {
		return bt_selcode;
	}

	public void setBt_selcode(JButton bt_selcode) {
		this.bt_selcode = bt_selcode;
	}

	public JComboBox getCom_selcode() {
		return com_selcode;
	}

	public void setCom_selcode(JComboBox com_selcode) {
		this.com_selcode = com_selcode;
	}

	public JTextArea getJta_receivew() {
		return jta_receivew;
	}

	public void setJta_receivew(JTextArea jta_receivew) {
		this.jta_receivew = jta_receivew;
	}

	public JTextArea getJta_sendw() {
		return jta_sendw;
	}

	public void setJta_sendw(JTextArea jta_sendw) {
		this.jta_sendw = jta_sendw;
	}

	public JTextField getTxt_createcode() {
		return txt_createcode;
	}

	public void setTxt_createcode(JTextField txt_createcode) {
		this.txt_createcode = txt_createcode;
	}

	public JButton getBt_savep() {
		return bt_savep;
	}

	public void setBt_savep(JButton bt_savep) {
		this.bt_savep = bt_savep;
	}

	public JButton getBt_print() {
		return bt_print;
	}

	public void setBt_print(JButton bt_print) {
		this.bt_print = bt_print;
	}

	public JUComboBox getCom_whsin() {
		return com_whsin;
	}

	public void setCom_whsin(JUComboBox com_whsin) {
		this.com_whsin = com_whsin;
	}

	public void setBt_weight(JButton bt_weight) {
		this.bt_weight = bt_weight;
	}

	public void setBt_cweight(JButton bt_cweight) {
		this.bt_cweight = bt_cweight;
	}

	public JLabel getLab_type() {
		return lab_type;
	}

	public void setLab_type(JLabel lab_type) {
		this.lab_type = lab_type;
	}

	public JLabel getLab_whs() {
		return lab_whs;
	}

	public void setLab_whs(JLabel lab_whs) {
		this.lab_whs = lab_whs;
	}

	public JLabel getLab_user() {
		return lab_user;
	}

	public void setLab_user(JLabel lab_user) {
		this.lab_user = lab_user;
	}

	public JLabel getLab_date() {
		return lab_date;
	}

	public void setLab_date(JLabel lab_date) {
		this.lab_date = lab_date;
	}

	public JLabel getLab_SN() {
		return lab_SN;
	}

	public void setLab_SN(JLabel lab_SN) {
		this.lab_SN = lab_SN;
	}

	public JLabel getLab_cppo() {
		return lab_cppo;
	}

	public void setLab_cppo(JLabel lab_cppo) {
		this.lab_cppo = lab_cppo;
	}

	public JLabel getLab_plist() {
		return lab_plist;
	}

	public void setLab_plist(JLabel lab_plist) {
		this.lab_plist = lab_plist;
	}

	public JLabel getLab_docn() {
		return lab_docn;
	}

	public void setLab_docn(JLabel lab_docn) {
		this.lab_docn = lab_docn;
	}

	public JLabel getLab_memo() {
		return lab_memo;
	}

	public void setLab_memo(JLabel lab_memo) {
		this.lab_memo = lab_memo;
	}

	public JLabel getLab_msum() {
		return lab_msum;
	}

	public void setLab_msum(JLabel lab_msum) {
		this.lab_msum = lab_msum;
	}

	public JLabel getLab_wsum() {
		return lab_wsum;
	}

	public void setLab_wsum(JLabel lab_wsum) {
		this.lab_wsum = lab_wsum;
	}

	public JLabel getLab_rsum() {
		return lab_rsum;
	}

	public void setLab_rsum(JLabel lab_rsum) {
		this.lab_rsum = lab_rsum;
	}

	public JLabel getLab_psum() {
		return lab_psum;
	}

	public void setLab_psum(JLabel lab_psum) {
		this.lab_psum = lab_psum;
	}

	public JLabel getLab_csum() {
		return lab_csum;
	}

	public void setLab_csum(JLabel lab_csum) {
		this.lab_csum = lab_csum;
	}

	public JLabel getLab_port() {
		return lab_port;
	}

	public void setLab_port(JLabel lab_port) {
		this.lab_port = lab_port;
	}

	public JLabel getLab_baudr() {
		return lab_baudr;
	}

	public void setLab_baudr(JLabel lab_baudr) {
		this.lab_baudr = lab_baudr;
	}

	public JLabel getLab_flowcin() {
		return lab_flowcin;
	}

	public void setLab_flowcin(JLabel lab_flowcin) {
		this.lab_flowcin = lab_flowcin;
	}

	public JLabel getLab_flowcout() {
		return lab_flowcout;
	}

	public void setLab_flowcout(JLabel lab_flowcout) {
		this.lab_flowcout = lab_flowcout;
	}

	public JLabel getLab_height() {
		return lab_height;
	}

	public void setLab_height(JLabel lab_height) {
		this.lab_height = lab_height;
	}

	public JLabel getLab_left() {
		return lab_left;
	}

	public void setLab_left(JLabel lab_left) {
		this.lab_left = lab_left;
	}

	public JLabel getLab_up() {
		return lab_up;
	}

	public void setLab_up(JLabel lab_up) {
		this.lab_up = lab_up;
	}

	public JLabel getLab_right() {
		return lab_right;
	}

	public void setLab_right(JLabel lab_right) {
		this.lab_right = lab_right;
	}

	public JLabel getLab_down() {
		return lab_down;
	}

	public void setLab_down(JLabel lab_down) {
		this.lab_down = lab_down;
	}

	public JLabel getLab_codetype() {
		return lab_codetype;
	}

	public void setLab_codetype(JLabel lab_codetype) {
		this.lab_codetype = lab_codetype;
	}

	public JLabel getLab_codewidth() {
		return lab_codewidth;
	}

	public void setLab_codewidth(JLabel lab_codewidth) {
		this.lab_codewidth = lab_codewidth;
	}

	public JLabel getLab_codeheight() {
		return lab_codeheight;
	}

	public void setLab_codeheight(JLabel lab_codeheight) {
		this.lab_codeheight = lab_codeheight;
	}

	public JLabel getLab_codegap() {
		return lab_codegap;
	}

	public void setLab_codegap(JLabel lab_codegap) {
		this.lab_codegap = lab_codegap;
	}

	public JLabel getLab_specification() {
		return lab_specification;
	}

	public void setLab_specification(JLabel lab_specification) {
		this.lab_specification = lab_specification;
	}

	public JLabel getLab_length() {
		return lab_length;
	}

	public void setLab_length(JLabel lab_length) {
		this.lab_length = lab_length;
	}

	public JLabel getLab_weight() {
		return lab_weight;
	}

	public void setLab_weight(JLabel lab_weight) {
		this.lab_weight = lab_weight;
	}

	public JLabel getLab_cweight() {
		return lab_cweight;
	}

	public void setLab_cweight(JLabel lab_cweight) {
		this.lab_cweight = lab_cweight;
	}

	public JLabel getLab_pweight() {
		return lab_pweight;
	}

	public void setLab_pweight(JLabel lab_pweight) {
		this.lab_pweight = lab_pweight;
	}

	public JLabel getLab_sweight() {
		return lab_sweight;
	}

	public void setLab_sweight(JLabel lab_sweight) {
		this.lab_sweight = lab_sweight;
	}

	public JLabel getLab_deviation() {
		return lab_deviation;
	}

	public void setLab_deviation(JLabel lab_deviation) {
		this.lab_deviation = lab_deviation;
	}

	public JLabel getLab_MNo() {
		return lab_MNo;
	}

	public void setLab_MNo(JLabel lab_MNo) {
		this.lab_MNo = lab_MNo;
	}

	public JLabel getLab_Qinspector() {
		return lab_Qinspector;
	}

	public void setLab_Qinspector(JLabel lab_Qinspector) {
		this.lab_Qinspector = lab_Qinspector;
	}

	public JLabel getLab_company() {
		return lab_company;
	}

	public void setLab_company(JLabel lab_company) {
		this.lab_company = lab_company;
	}

	public JLabel getLab_receivew() {
		return lab_receivew;
	}

	public void setLab_receivew(JLabel lab_receivew) {
		this.lab_receivew = lab_receivew;
	}

	public JLabel getLab_sendw() {
		return lab_sendw;
	}

	public void setLab_sendw(JLabel lab_sendw) {
		this.lab_sendw = lab_sendw;
	}
	
	public JLabel getLab_wareh() {
		return lab_wareh;
	}

	public void setLab_wareh(JLabel lab_wareh) {
		this.lab_wareh = lab_wareh;
	}

	public JPanel getPane7() {
		return pane7;
	}

	public void setPane7(JPanel pane7) {
		this.pane7 = pane7;
	}

	public JPanel getPane8() {
		return pane8;
	}

	public void setPane8(JPanel pane8) {
		this.pane8 = pane8;
	}

	public SpringLayout getLay7() {
		return lay7;
	}

	public void setLay7(SpringLayout lay7) {
		this.lay7 = lay7;
	}

	public SpringLayout getLay8() {
		return lay8;
	}

	public void setLay8(SpringLayout lay8) {
		this.lay8 = lay8;
	}

	public JComboBox getCom_snware() {
		return com_snware;
	}

	public void setCom_snware(JComboBox com_snware) {
		this.com_snware = com_snware;
	}

	public JTextField getTxt_status() {
		return txt_status;
	}

	public void setTxt_status(JTextField txt_status) {
		this.txt_status = txt_status;
	}

	public JTextField getTxt_status1() {
		return txt_status1;
	}

	public void setTxt_status1(JTextField txt_status1) {
		this.txt_status1 = txt_status1;
	}

	public JTextField getTxt_docnid() {
		return txt_docnid;
	}

	public void setTxt_docnid(JTextField txt_docnid) {
		this.txt_docnid = txt_docnid;
	}

}
