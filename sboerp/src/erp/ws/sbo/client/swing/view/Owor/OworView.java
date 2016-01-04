package erp.ws.sbo.client.swing.view.Owor;

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
import erp.ws.sbo.client.swing.controller.OworsController;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.ColOworDocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.OworDocLine;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.OworDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.OworDocTitleModel;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.IntTextField;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.utils.DbUtils;

public class OworView extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8035864201045270040L;
	 private OworsController oc=new OworsController(this);
	    private JPanel  pane1=new JPanel();//base information
		private JPanel  pane2=new JPanel();//data-browser
	    private JPanel  pane3=new JPanel();//userdefined field
	    private JPanel  pane4=new JPanel();//the parent of pane5
	    private JPanel  pane5=new JPanel();//the bottom of the doc
	    private SpringLayout lay = new SpringLayout();
	    private String  hql="select p.userid,isnull(p.U_Name,'') from Ousr as p where p.userid='"+appMain.oCompany.getUserSignature()+"'",hql1,yon;    
	    private ColOworDocLine cl=ColOworDocLine.getCLine();
		private OworDocLine dl=new OworDocLine();
		private DbUtils<ColOworDocLine,OworDocLine> dbu=new DbUtils<ColOworDocLine,OworDocLine>();
	    private ColDocTitle ct=ColDocTitle.getCTitle();
	    private	DocTitle dt=new DocTitle();
	    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
	    private OworDocLineModel od;
	    private OworDocTitleModel od1;
	    
	    JLabel lab_type = new JLabel("类型");
	    JLabel lab_status = new JLabel("状态");
	    JLabel lab_itemcode = new JLabel("产品代码");
	    JLabel lab_itemname = new JLabel("产品名称");
	    JLabel lab_length = new JLabel("米段");
	    JLabel lab_planQty = new JLabel("库存数量");  
	    JLabel lab_Qty = new JLabel("生产数量");
	    JLabel lab_unit = new JLabel("生产单位");
	    JLabel lab_ounit = new JLabel("库存单位");
	    JLabel lab_whs = new JLabel("仓库");
	    JLabel lab_docn = new JLabel("单号");
	   
	    JLabel lab_date = new JLabel("过账日期");
	    JLabel lab_duedate = new JLabel("完工日期");
	    JLabel lab_user = new JLabel("操作员");
	    JLabel lab_MNo = new JLabel("机号");
	    JLabel lab_memo = new JLabel("备注");
	    JLabel lab_asum = new JLabel("发出数量");
	    JLabel lab_qsum = new JLabel("计划数量");
	    JLabel lab_form = new JLabel("是否米段线");

	    
	    JComboBox com_itemcode;
	    JUComboBox com_whs=new JUComboBox(hql);;
	    JTextField txt_itemname = new JTextField(20);	   
	    JUComboBox com_users=new JUComboBox(hql);
	    JUComboBox com_MNo=new JUComboBox(hql);
	    JComboBox com_type=new JComboBox();
	    JComboBox com_status=new JComboBox();
	    JComboBox com_form=new JComboBox();
	    JTextArea jta_memo=new JTextArea(6,20);
	    JTextField txt_docn = new JTextField(8);
	    JTextField txt_docnid = new JTextField(8);
	    IntTextField txt_length = new IntTextField(0, 8);
	    IntTextField txt_planQty = new IntTextField(0, 6);
	    IntTextField txt_Qty = new IntTextField(0, 6);
	    JTextField txt_unit = new JTextField(6);
	    JTextField txt_ounit = new JTextField(6);
	    DatePicker txt_date  = new DatePicker(pane1,new Date());
	    DatePicker txt_duedate  = new DatePicker(pane1,new Date());
	    JTextField txt_asum = new JTextField(15);
	    JTextField txt_qsum = new JTextField(15);

	    
	    JMyTable jt;
	    JTable jt1;
	    
	    JScrollPane scp;
	    JScrollPane scp1;
	    JTabbedPane jtp1=new JTabbedPane();	
	    JTabbedPane jtp2=new JTabbedPane();	
	    
	    JSplitPane splitPane1;
	    
	    JTextField  editor;
	    List<Object[]> list=new ArrayList<Object[]>(); 
	    Object[] obj;
	    
	    public OworView()
		{
			super("生产订单",true,true,true,true);	
			initComponents();
		}

		private void initComponents() {
			// TODO Auto-generated method stub
			 this.setName("OWOR");
			 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
			 appMain.fv.setText("");
			 jta_memo.setLineWrap(true);
			 txt_date.setPattern("yyyy-MM-dd");//设置日期格式化字符串
			 txt_date.setEditorable(true);//设置是否可编辑
			 txt_date.setBackground(Color.gray);//设置背景色
			 txt_date.setForeground(Color.yellow);//设置前景色
			 txt_date.setPreferredSize(new Dimension(145,24));//设置大小
			 txt_date.setTextAlign(DatePicker.LEFT);
			 
			 txt_duedate.setPattern("yyyy-MM-dd");//设置日期格式化字符串
			 txt_duedate.setEditorable(true);//设置是否可编辑
			 txt_duedate.setBackground(Color.gray);//设置背景色
			 txt_duedate.setForeground(Color.yellow);//设置前景色
			 txt_duedate.setPreferredSize(new Dimension(145,24));//设置大小
			 txt_duedate.setTextAlign(DatePicker.LEFT);

			 
			 hql= "select distinct a.code from Oitt a inner join oitm p on a.code=p.itemcode" +
					" where " +
					"(p.itemCode like :str1 or p.itemName like :str2) and p.validfor='Y' AND p.frozenfor='N'";
		   	 hql1="select distinct a.code from Oitt a inner join oitm p on a.code=p.itemcode " +
					" where  " +
					"p.itemCode collate Chinese_prc_cs_as_ws=:str1 and p.validfor='Y' AND p.frozenfor='N'";
			 com_itemcode = new JAutoCompleteComboBox(hql,hql1);
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
			 
			 com_type.addItem( new  ComboBoxItem(0,"标准"));
			 com_type.addItem( new  ComboBoxItem(1,"特殊"));			
			 com_type.addItem( new  ComboBoxItem(2,"拆分"));
			 com_status.addItem(new  ComboBoxItem(1,"批准"));
			 com_status.addItem(new  ComboBoxItem(2,"关闭"));
			 com_status.addItem(new  ComboBoxItem(0,"计划"));
			 com_form.addItem( new  ComboBoxItem("N","否"));
			 com_form.addItem( new  ComboBoxItem("Y","是"));
			 
			 com_itemcode.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");	
			 com_whs.setPrototypeDisplayValue("xxxxxxx");
			 com_users.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");
			 com_type.setPrototypeDisplayValue("xxxxxxxxxxxxxx");
			 com_status.setPrototypeDisplayValue("xxxxxxxxxxxxxx");
			 this.txt_docn.setEditable(false);
			 this.txt_docnid.setEditable(false);
			 this.txt_unit.setEditable(false);
			 this.txt_ounit.setEditable(false);
			 
			 pane1.setLayout(lay);
			 lay.putConstraint(SpringLayout.WEST, lab_type, 42,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_type, 10, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, com_type, 100,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, com_type, 10, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_status, 42,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_status, 40, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, com_status, 100,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, com_status, 40, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_itemcode, 42,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_itemcode, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, com_itemcode, 100,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, com_itemcode, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_itemname, 42,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_itemname, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, txt_itemname, 100,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_itemname, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_whs, 340,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_whs, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, com_whs, 390,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, com_whs, 100, SpringLayout.NORTH, pane1);			
			 lay.putConstraint(SpringLayout.WEST,  lab_form, 300,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_form, 40, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, com_form, 390,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, com_form, 40, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_length, 340,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_length, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, txt_length, 390,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_length, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_Qty, 560,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_Qty, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, txt_Qty, 620,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_Qty, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_planQty, 560,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_planQty, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, txt_planQty, 620,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_planQty, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_unit, 710,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_unit, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, txt_unit, 780,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_unit, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, lab_ounit, 710,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_ounit, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.WEST, txt_ounit, 780,SpringLayout.WEST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_ounit, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, lab_docn, -220,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_docn, 10, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, txt_docn, -100,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_docn, 10, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, txt_docnid, -10,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_docnid, 10, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, lab_date, -220,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_date, 40, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, txt_date, -70,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_date, 40, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, lab_duedate, -220,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_duedate, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, txt_duedate, -70,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, txt_duedate, 70, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, lab_user, -220,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, lab_user, 100, SpringLayout.NORTH, pane1);
			 lay.putConstraint(SpringLayout.EAST, com_users, -70,SpringLayout.EAST, pane1);
			 lay.putConstraint(SpringLayout.NORTH, com_users, 100, SpringLayout.NORTH, pane1);
			 
			pane1.add(lab_status);
			pane1.add(lab_type);
			pane1.add(com_status);
			pane1.add(com_type);
			pane1.add(lab_itemcode);
			pane1.add(com_itemcode);
			pane1.add(lab_itemname);
			pane1.add(txt_itemname);
			pane1.add(lab_whs);
			pane1.add(com_whs);
			pane1.add(lab_user);
			pane1.add(com_users);
			pane1.add(lab_unit);
			pane1.add(lab_ounit);
			pane1.add(txt_unit);
			pane1.add(txt_ounit);
			pane1.add(lab_date);
			pane1.add(txt_date);
			pane1.add(lab_duedate);
			pane1.add(txt_duedate);
			pane1.add(lab_docn);
			pane1.add(txt_docn);
			pane1.add(txt_docnid);
			pane1.add(lab_length);
			pane1.add(txt_length);
			pane1.add(lab_Qty);
			pane1.add(txt_Qty);
			pane1.add(lab_planQty);
			pane1.add(txt_planQty);
			pane1.add(lab_form);
			pane1.add(com_form);
            
			//单身主数据			   
			 hql="select '','','','','','','','','','' "+
		           "from Inv1 p where 1=2";	
			    od=new OworDocLineModel(cl,dl,dbu,hql);		   	   
				od.setDocLineStatus(docLineStatus.load);
				od.setGridStatus(docLineStatus.load);
				
				jt=new JMyTable(od);
				
				 
				jt.getColumnModel().getColumn(od.getcolumnindex("物料代码")).setPreferredWidth(200);
				jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
				jt.getColumnModel().getColumn(od.getcolumnindex("物料描述")).setPreferredWidth(120);

				
				jt.setAutoCreateRowSorter(true);	
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
				jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
				jt.setFillsViewportHeight(true);
			
				 scp=new JScrollPane(jt);
		        		 		
				 jtp1.add("基本信息",pane1);
				 jtp1.add("数据浏览",pane2);
				 jtp1.add("自定义",pane3);
		         pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
		         pane4.add(scp);
		         
		         //单头数据浏览
		         hql="select 0,'订单',p.docEntry,p.docDate," +
		     			"p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
		     	           " '', case when p.docStatus='C' then '关闭' else '打开' end," +
		     	           "case when p.canceled='Y' then '是' else '否' end," +
		     	           "case when p.printed='Y' then '是' else '否' end " + 		         
		     	           " from Ordr p,Oslp p1 where p.slpCode=p1.slpCode and 1=2 ";   
		        od1 = new OworDocTitleModel(ct,dt,dbu1,hql,true);
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
				 pane5.setLayout(lay);
				 lay.putConstraint(SpringLayout.WEST, this.lab_memo, 42,SpringLayout.WEST, pane5);
				 lay.putConstraint(SpringLayout.NORTH,this.lab_memo, 35, SpringLayout.NORTH, pane5);
				 lay.putConstraint(SpringLayout.WEST, this.jta_memo, 100,SpringLayout.WEST, pane5);
				 lay.putConstraint(SpringLayout.NORTH,this.jta_memo, 35, SpringLayout.NORTH, pane5);
				 lay.putConstraint(SpringLayout.EAST, this.lab_asum, -300,SpringLayout.EAST, pane5);
				 lay.putConstraint(SpringLayout.NORTH,this.lab_asum, 35, SpringLayout.NORTH, pane5);
				 lay.putConstraint(SpringLayout.EAST, this.txt_asum, -50,SpringLayout.EAST, pane5);
				 lay.putConstraint(SpringLayout.NORTH,this.txt_asum, 35, SpringLayout.NORTH, pane5);
				 lay.putConstraint(SpringLayout.EAST, this.lab_qsum, -300,SpringLayout.EAST, pane5);
				 lay.putConstraint(SpringLayout.NORTH,this.lab_qsum, 65, SpringLayout.NORTH, pane5);
				 lay.putConstraint(SpringLayout.EAST, this.txt_qsum, -50,SpringLayout.EAST, pane5);
				 lay.putConstraint(SpringLayout.NORTH,this.txt_qsum, 65, SpringLayout.NORTH, pane5);

				
				 pane5.add(this.lab_memo);
				 pane5.add(this.jta_memo);
				 pane5.add(this.lab_asum);
				 pane5.add(this.txt_asum);
				 pane5.add(this.lab_qsum);
				 pane5.add(this.txt_qsum);

				 
		         pane4.add(pane5);
				
				 jtp2.add("内容",pane4);
					                
			     Container pane=this.getContentPane();
			     splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jtp1,jtp2);
			     /*设置splitPane1的分隔线位置，0.3是相对于splitPane1的大小而定，因此这个值的范围在0.0～1.0
			      *中。若你使用整数值来设置splitPane的分隔线位置，如第34行所示，则所定义的值以pixel为计算单位
			      */
			     splitPane1.setDividerLocation(160);
				 pane.add(splitPane1, BorderLayout.CENTER);
				 
				 editor = (JTextField) com_itemcode.getEditor().getEditorComponent();
				 //添加事件
				 editor.addFocusListener(oc);
				 txt_planQty.addFocusListener(oc);
				 txt_Qty.addFocusListener(oc);
				 txt_length.addFocusListener(oc);
			     jt.addMouseListener(oc);	
			     jt.addKeyListener(oc);
			     jt1.addMouseListener(oc);
			     jt1.getSelectionModel().addListSelectionListener(oc);
			     od.addTableModelListener(oc);
			     com_form.addActionListener(oc);
			     this.addInternalFrameListener(oc);
			     	    	     
				 this.setVisible(true);
				 this.setResizable(true);	
	
		}

		public OworsController getOc() {
			return oc;
		}

		public void setOc(OworsController oc) {
			this.oc = oc;
		}

		public JTextField getEditor() {
			return editor;
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

		public OworDocLineModel getOd() {
			return od;
		}

		public void setOd(OworDocLineModel od) {
			this.od = od;
		}

		public OworDocTitleModel getOd1() {
			return od1;
		}

		public void setOd1(OworDocTitleModel od1) {
			this.od1 = od1;
		}

		public JComboBox getCom_itemcode() {
			return com_itemcode;
		}

		public void setCom_itemcode(JComboBox com_itemcode) {
			this.com_itemcode = com_itemcode;
		}

		public JUComboBox getCom_whs() {
			return com_whs;
		}

		public void setCom_whs(JUComboBox com_whs) {
			this.com_whs = com_whs;
		}

		public JTextField getTxt_itemname() {
			return txt_itemname;
		}

		public void setTxt_itemname(JTextField txt_itemname) {
			this.txt_itemname = txt_itemname;
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
		
		public JComboBox getCom_form() {
			return com_form;
		}

		public void setCom_form(JComboBox com_form) {
			this.com_form = com_form;
		}
		
		public JComboBox getCom_status() {
			return com_status;
		}

		public void setCom_status(JComboBox com_status) {
			this.com_status = com_status;
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

		public IntTextField getTxt_length() {
			return txt_length;
		}

		public void setTxt_length(IntTextField txt_length) {
			this.txt_length = txt_length;
		}

		public IntTextField getTxt_planQty() {
			return txt_planQty;
		}

		public void setTxt_planQty(IntTextField txt_planQty) {
			this.txt_planQty = txt_planQty;
		}

		public IntTextField getTxt_Qty() {
			return txt_Qty;
		}

		public void setTxt_Qty(IntTextField txt_Qty) {
			this.txt_Qty = txt_Qty;
		}

		public JTextField getTxt_unit() {
			return txt_unit;
		}

		public void setTxt_unit(JTextField txt_unit) {
			this.txt_unit = txt_unit;
		}

		public JTextField getTxt_ounit() {
			return txt_ounit;
		}

		public void setTxt_ounit(JTextField txt_ounit) {
			this.txt_ounit = txt_ounit;
		}

		public DatePicker getTxt_date() {
			return txt_date;
		}

		public void setTxt_date(DatePicker txt_date) {
			this.txt_date = txt_date;
		}

		public DatePicker getTxt_duedate() {
			return txt_duedate;
		}

		public void setTxt_duedate(DatePicker txt_duedate) {
			this.txt_duedate = txt_duedate;
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

		public ColOworDocLine getCl() {
			return cl;
		}

		public void setCl(ColOworDocLine cl) {
			this.cl = cl;
		}

		public OworDocLine getDl() {
			return dl;
		}

		public void setDl(OworDocLine dl) {
			this.dl = dl;
		}

		public DbUtils<ColOworDocLine, OworDocLine> getDbu() {
			return dbu;
		}

		public void setDbu(DbUtils<ColOworDocLine, OworDocLine> dbu) {
			this.dbu = dbu;
		}

		public ColDocTitle getCt() {
			return ct;
		}

		public void setCt(ColDocTitle ct) {
			this.ct = ct;
		}

		public DocTitle getDt() {
			return dt;
		}

		public void setDt(DocTitle dt) {
			this.dt = dt;
		}

		public DbUtils<ColDocTitle, DocTitle> getDbu1() {
			return dbu1;
		}

		public void setDbu1(DbUtils<ColDocTitle, DocTitle> dbu1) {
			this.dbu1 = dbu1;
		}

		public JUComboBox getCom_MNo() {
			return com_MNo;
		}

		public void setCom_MNo(JUComboBox com_MNo) {
			this.com_MNo = com_MNo;
		}

		public JTextField getTxt_docnid() {
			return txt_docnid;
		}

		public void setTxt_docnid(JTextField txt_docnid) {
			this.txt_docnid = txt_docnid;
		}
}
