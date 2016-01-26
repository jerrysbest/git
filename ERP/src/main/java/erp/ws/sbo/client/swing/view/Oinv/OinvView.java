package erp.ws.sbo.client.swing.view.Oinv;

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
import javax.swing.JOptionPane;
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
import erp.ws.sbo.client.swing.controller.OinvsController;
import erp.ws.sbo.client.swing.model.ColDocLine;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.Inv1Id;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.OinvDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.OinvDocTitleModel;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.utils.DbUtils;
import javax.swing.JButton;

public class OinvView extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6896750977097188243L;
    private OinvsController oc=new OinvsController(this);
    private OinvPrintView opv;
    private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private SpringLayout lay = new SpringLayout();
    private SpringLayout lay3 = new SpringLayout();
    private SpringLayout lay5 = new SpringLayout();
    private String hql="select p.slpCode,p.slpName from Oslp p where slpcode<>-1",hql1;    
    private Object[][] ob,ob1;
    private ColDocLine cl=ColDocLine.getCLine();
	private DocLine<Inv1Id> dl=new DocLine<Inv1Id>();
	private DbUtils<ColDocLine,DocLine<Inv1Id>> dbu=new DbUtils<ColDocLine,DocLine<Inv1Id>>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private AbstractDocLineModel<ColDocLine,DocLine<Inv1Id>> od;
    private AbstractDocTitleModel<ColDocTitle,DocTitle> od1;
    private DeSNView dsv;
    
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
    JLabel lab_sn = new JLabel("输入序列号");
    JLabel lab_asum = new JLabel("金额合计");
    JLabel lab_qsum = new JLabel("数量合计");
    JLabel lab_gjsum = new JLabel("公斤数合计");
    JLabel lab_mssum = new JLabel("米数合计");
    JLabel lab_sjzlsum = new JLabel("实际重量合计");
    JLabel lab_ter = new JLabel("地区");
    
    JComboBox txt_cus;
    JTextField txt_cusn = new JTextField(20);
    JComboBox txt_cuslxr=new  JComboBox();
    JUComboBox com_sales=new  JUComboBox(hql);
    JUComboBox com_sales1=new  JUComboBox(hql);
    JTextArea jta_memo=new JTextArea(6,20);
    JTextArea jta_SN=new JTextArea(8,25);
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
    
    JButton btn_upstatus = new JButton("更新为待扫描状态");
    JButton btn_upqulity = new JButton("更新包装数量");
    JButton btn_upsn = new JButton("保存序列号信息");
    JMyTable jt;
    JTable jt1;
    
    JTabbedPane jtp1=new JTabbedPane();	
    JTabbedPane jtp2=new JTabbedPane();	
    
    JTextField  editor;
    List<Object[]> list=new ArrayList<Object[]>(); 
    Object[] obj;
    public OinvView()
    {
    	super("备货单",true,true,true,true);	
		initComponents();
		dsv=new DeSNView(this);
		dsv.setVisible(false);
    }
    public void initComponents()
    {	
    	 this.setName("OINV");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 hql="from Oslp";
		 jta_SN.setLineWrap(true);
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
		 
		 hql= "select p.cardCode+','+p.cardName from Ocrd as p " +
			  " where (p.cardCode like :str1 or p.cardName like :str2) and p.cardType='C'";
	   	 hql1="select p.cardCode+','+p.cardName from Ocrd as p " +
			  " where p.cardCode=:str1 and p.cardType='C'";
		 txt_cus = new JAutoCompleteComboBox(hql,hql1);
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
		 lay.putConstraint(SpringLayout.WEST, btn_upstatus, 0, SpringLayout.WEST, btn_upqulity);
		 lay.putConstraint(SpringLayout.SOUTH, btn_upstatus, 0, SpringLayout.SOUTH, txt_cuslxr);
		 lay.putConstraint(SpringLayout.WEST, btn_upqulity, 6, SpringLayout.EAST, txt_delidate);
		 lay.putConstraint(SpringLayout.SOUTH, btn_upqulity, 0, SpringLayout.SOUTH, txt_delidate);
		 lay.putConstraint(SpringLayout.WEST, btn_upsn, 6, SpringLayout.EAST, btn_upqulity);
		 lay.putConstraint(SpringLayout.SOUTH, btn_upsn, 0, SpringLayout.SOUTH, btn_upqulity);
		 
		// txt_cus.setSize(4, 80);
		 txt_cus.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");	
		 txt_cuslxr.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 
		 
		 pane1.add(btn_upqulity);
		 pane1.add(btn_upsn);
		 pane1.add(btn_upstatus);
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
		 
		 pane3.setLayout(lay3);
		 lay3.putConstraint(SpringLayout.WEST, lab_ter, 42,SpringLayout.WEST, pane3);
		 lay3.putConstraint(SpringLayout.NORTH, lab_ter, 10, SpringLayout.NORTH, pane3);
		 lay3.putConstraint(SpringLayout.WEST, txt_ter, 100,SpringLayout.WEST, pane3);
		 lay3.putConstraint(SpringLayout.NORTH, txt_ter, 10, SpringLayout.NORTH, pane3);
		 
		 pane3.add(lab_ter);
		 pane3.add(txt_ter);
		 txt_ter.setEditable(false);
		 
		//单身主数据
		   
		 hql="select p.id.docEntry,'','','','','','',p.itemCode, p.dscription,'',p.UMtmd,"+
           "p.unitMsr,p.numPerMsr,p.whsCode," +
           "p.unitMsr2,p.UGs,'',p.quantity,'',p.UMjg,p.UGjjg,p.price," +
           "p.lineTotal,p.UZc,p.UZz,p.USfhh,"+
	       "p.UTsjg,p.UMtdl,p.UDjNo,p.UDjNozj,p.UDydj," +
           "p.UJgf,p.UYdy, p.UCkck,"+
           "p.UCzy,p.UScwc,p.URinzz,"+
           "p.UDhdate " +
           "from Inv1 p where 1=2";	
		    od=new OinvDocLineModel(cl,dl,dbu,hql,true);		   	   
			od.setDocLineStatus(docLineStatus.load);
			od.setGridStatus(docLineStatus.load);
			hql="select u_enable from dbo.[@sms] where code='DELSN'";
			ob = appMain.lt.sqlclob(hql,0,1);
	        if(ob==null||ob.length==0)
	        {
	      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置备货单序列号是否启用 ");
	      	  return;
	        }
	        hql1= "select name from [@USERGROUP] where name='仓库组' and code=" +
			 	  "(select u_usergroup from ousr where userid ='"+appMain.oCompany.getUserSignature()+"')";
	        ob1 = appMain.lt.sqlclob(hql1,0,1);
	        if(ob[0][0].toString().equals("Y")&&(ob1!=null&&ob1.length>0&&ob1[0][0].toString().equals("仓库组")))
	        {
			    jt=new JMyTable(od,od.getcolumnindex("物料代码"),"WDELSN");
	        }
	        else{
	        	jt=new JMyTable(od);
	        }
			 //绑定仓库
			 hql= "select p.whsCode+','+p.whsName from Owhs as p " +
						" where (p.whsCode like :str1 or p.whsName like :str2)";
			
			 hql1="select p.whsCode from Owhs as p " +
						" where p.whsCode=:str1";
			
			 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("仓库代码")),hql,hql1);
			
			 
			 obj=new Object[2];
		     obj[0]="N";obj[1]="否";
		     list.add(obj);
		     obj=new Object[2];
		     obj[0]="Y";obj[1]="是";
		     list.add(obj);
		     
			 od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("是否换货")), list);
			  list.clear();
		     obj=new Object[2];
		     obj[0]="Y";obj[1]="是";
		     list.add(obj);
		     obj=new Object[2];
		     obj[0]="N";obj[1]="否";
		     list.add(obj);
		     
			od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("是否显示米段")), list);
			jt.getColumnModel().getColumn(od.getcolumnindex("单据代码")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("行号")).setPreferredWidth(30);
			jt.getColumnModel().getColumn(od.getcolumnindex("基本单据")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("基本行号")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("物料代码")).setPreferredWidth(120);
			jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
			jt.getColumnModel().getColumn(od.getcolumnindex("物料描述")).setPreferredWidth(120);
			jt.getColumnModel().getColumn(od.getcolumnindex("是否显示米段")).setPreferredWidth(30);
			jt.getColumnModel().getColumn(od.getcolumnindex("物料米段")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("销售单位")).setPreferredWidth(30);
			jt.getColumnModel().getColumn(od.getcolumnindex("单位数量")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("仓库代码")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("库存单位")).setPreferredWidth(30);
			jt.getColumnModel().getColumn(od.getcolumnindex("包装数量")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("扫描数量")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("扫描个数")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("包装单价")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("数量")).setPreferredWidth(60);
			jt.getColumnModel().getColumn(od.getcolumnindex("单价")).setPreferredWidth(50);
			jt.getColumnModel().getColumn(od.getcolumnindex("总长")).setPreferredWidth(65);
			jt.getColumnModel().getColumn(od.getcolumnindex("米价格")).setPreferredWidth(50);
			
			
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
	         hql="select 0,'单据',p.docEntry,p.docDate," +
	     		  "p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
     	           " '', case when p.docStatus='C' then '关闭' else '打开' end," +
     	           "case when p.canceled='Y' then '是' else '否' end," +
     	           "case when p.printed='Y' then '是' else '否' end " + 		         
     	           " from Oinv p,Oslp p1 where p.slpCode=p1.slpCode and 1=2 ";   
	        od1 = new OinvDocTitleModel(ct,dt,dbu1,hql,true);
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
	       
			 pane5.setLayout(lay5);
			 lay5.putConstraint(SpringLayout.WEST, this.lab_sales, 42,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_sales, 5, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.com_sales, 100,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.com_sales, 5, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.lab_sales1, 42,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_sales1, 35, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.com_sales1, 100,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.com_sales1, 35, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.lab_memo, 42,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_memo, 65, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.jta_memo, 100,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.jta_memo, 65, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.lab_sn, 350,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_sn, 5, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.WEST, this.jta_SN, 350,SpringLayout.WEST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.jta_SN, 35, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.lab_asum, -300,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_asum, 5, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.txt_asum, -50,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.txt_asum, 5, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.lab_qsum, -300,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_qsum, 35, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.txt_qsum, -50,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.txt_qsum, 35, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.lab_gjsum, -300,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_gjsum, 65, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.txt_gjsum, -50,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.txt_gjsum, 65, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.lab_mssum, -300,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_mssum, 95, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.txt_mssum, -50,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.txt_mssum, 95, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.lab_sjzlsum, -300,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.lab_sjzlsum, 125, SpringLayout.NORTH, pane5);
			 lay5.putConstraint(SpringLayout.EAST, this.txt_sjzlsum, -50,SpringLayout.EAST, pane5);
			 lay5.putConstraint(SpringLayout.NORTH,this.txt_sjzlsum, 125, SpringLayout.NORTH, pane5);
						 
			 pane5.add(this.lab_sales);
			 pane5.add(this.com_sales);
			 pane5.add(this.lab_sales1);
			 pane5.add(this.com_sales1);
			 pane5.add(this.lab_memo);
			 pane5.add(this.jta_memo);
			 pane5.add(this.lab_sn);
			 pane5.add(this.jta_SN);
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
			 com_sales.addActionListener(oc);
			 com_sales1.addActionListener(oc);
			 btn_upstatus.addActionListener(oc);
			 btn_upqulity.addActionListener(oc);
			 btn_upsn.addActionListener(oc);
		     jt.addMouseListener(oc);	
		     jt.addKeyListener(oc);
		     jta_SN.addKeyListener(oc);
		     jt1.addMouseListener(oc);
		     jt1.getSelectionModel().addListSelectionListener(oc);
		     od.addTableModelListener(oc);
		     this.addInternalFrameListener(oc);
		     	    	     
			 this.setVisible(true);
			 this.setResizable(true);	
    }
	public OinvsController getOc() {
		return oc;
	}
	public void setOc(OinvsController oc) {
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
	public ColDocLine getCl() {
		return cl;
	}
	public void setCl(ColDocLine cl) {
		this.cl = cl;
	}
	public DocLine<Inv1Id> getDl() {
		return dl;
	}
	public void setDl(DocLine<Inv1Id> dl) {
		this.dl = dl;
	}
	public DbUtils<ColDocLine, DocLine<Inv1Id>> getDbu() {
		return dbu;
	}
	public void setDbu(DbUtils<ColDocLine, DocLine<Inv1Id>> dbu) {
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
	public AbstractDocLineModel<ColDocLine, DocLine<Inv1Id>> getOd() {
		return od;
	}
	public void setOd(AbstractDocLineModel<ColDocLine, DocLine<Inv1Id>> od) {
		this.od = od;
	}
	public AbstractDocTitleModel<ColDocTitle, DocTitle> getOd1() {
		return od1;
	}
	public void setOd1(AbstractDocTitleModel<ColDocTitle, DocTitle> od1) {
		this.od1 = od1;
	}
	public JLabel getLab_cus() {
		return lab_cus;
	}
	public void setLab_cus(JLabel lab_cus) {
		this.lab_cus = lab_cus;
	}
	public JLabel getLab_cusn() {
		return lab_cusn;
	}
	public void setLab_cusn(JLabel lab_cusn) {
		this.lab_cusn = lab_cusn;
	}
	public JLabel getLab_cuslxr() {
		return lab_cuslxr;
	}
	public void setLab_cuslxr(JLabel lab_cuslxr) {
		this.lab_cuslxr = lab_cuslxr;
	}
	public JLabel getLab_docn() {
		return lab_docn;
	}
	public void setLab_docn(JLabel lab_docn) {
		this.lab_docn = lab_docn;
	}
	public JLabel getLab_status() {
		return lab_status;
	}
	public void setLab_status(JLabel lab_status) {
		this.lab_status = lab_status;
	}
	public JLabel getLab_date() {
		return lab_date;
	}
	public void setLab_date(JLabel lab_date) {
		this.lab_date = lab_date;
	}
	public JLabel getLab_duedate() {
		return lab_duedate;
	}
	public void setLab_duedate(JLabel lab_duedate) {
		this.lab_duedate = lab_duedate;
	}
	public JLabel getLab_delidate() {
		return lab_delidate;
	}
	public void setLab_delidate(JLabel lab_delidate) {
		this.lab_delidate = lab_delidate;
	}
	public JLabel getLab_sales() {
		return lab_sales;
	}
	public void setLab_sales(JLabel lab_sales) {
		this.lab_sales = lab_sales;
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
	public JLabel getLab_memo() {
		return lab_memo;
	}
	public void setLab_memo(JLabel lab_memo) {
		this.lab_memo = lab_memo;
	}
	public JLabel getLab_asum() {
		return lab_asum;
	}
	public void setLab_asum(JLabel lab_asum) {
		this.lab_asum = lab_asum;
	}
	public JLabel getLab_qsum() {
		return lab_qsum;
	}
	public void setLab_qsum(JLabel lab_qsum) {
		this.lab_qsum = lab_qsum;
	}
	public JLabel getLab_gjsum() {
		return lab_gjsum;
	}
	public void setLab_gjsum(JLabel lab_gjsum) {
		this.lab_gjsum = lab_gjsum;
	}
	public JLabel getLab_mssum() {
		return lab_mssum;
	}
	public void setLab_mssum(JLabel lab_mssum) {
		this.lab_mssum = lab_mssum;
	}
	public JLabel getLab_sjzlsum() {
		return lab_sjzlsum;
	}
	public void setLab_sjzlsum(JLabel lab_sjzlsum) {
		this.lab_sjzlsum = lab_sjzlsum;
	}
	public JLabel getLab_ter() {
		return lab_ter;
	}
	public void setLab_ter(JLabel lab_ter) {
		this.lab_ter = lab_ter;
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
	public JTextField getTxt_status() {
		return txt_status;
	}
	public void setTxt_status(JTextField txt_status) {
		this.txt_status = txt_status;
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
	public JTextField getTxt_docnplus() {
		return txt_docnplus;
	}
	public void setTxt_docnplus(JTextField txt_docnplus) {
		this.txt_docnplus = txt_docnplus;
	}
	public JTextField getTxt_type() {
		return txt_type;
	}
	public void setTxt_type(JTextField txt_type) {
		this.txt_type = txt_type;
	}
	public OinvPrintView getOpv() {
		return opv;
	}
	public void setOpv(OinvPrintView opv) {
		this.opv = opv;
	}
	public JLabel getLab_sn() {
		return lab_sn;
	}
	public void setLab_sn(JLabel lab_sn) {
		this.lab_sn = lab_sn;
	}
	public DeSNView getDsv() {
		return dsv;
	}
	public void setDsv(DeSNView dsv) {
		this.dsv = dsv;
	}
	public JTextArea getJta_SN() {
		return jta_SN;
	}
	public void setJta_SN(JTextArea jta_SN) {
		this.jta_SN = jta_SN;
	}
	public JButton getBtn_upqulity() {
		return btn_upqulity;
	}
	public void setBtn_upqulity(JButton btn_upqulity) {
		this.btn_upqulity = btn_upqulity;
	}
	public JButton getBtn_upsn() {
		return btn_upsn;
	}
	public void setBtn_upsn(JButton btn_upsn) {
		this.btn_upsn = btn_upsn;
	}
	public JButton getBtn_upstatus() {
		return btn_upstatus;
	}
	public void setBtn_upstatus(JButton btn_upstatus) {
		this.btn_upstatus = btn_upstatus;
	}
}
