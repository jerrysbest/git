package erp.ws.sbo.client.swing.view.Qr;
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
import erp.ws.sbo.client.swing.controller.QrsController;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.QrColDocLine;
import erp.ws.sbo.client.swing.model.URm1Id;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.QrDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.QrDocTitleModel;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.utils.DbUtils;

public class QrView extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private QrsController oc=new QrsController(this);
    private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private SpringLayout lay = new SpringLayout();
    private SpringLayout lay3 = new SpringLayout();
    private SpringLayout lay5 = new SpringLayout();
    private String hql="select p.slpCode,p.slpName from Oslp p where slpcode<>-1",hql1,hql2;   
    private Object[][] ob,ob1;
    private QrColDocLine cl=QrColDocLine.getCLine();
	private DocLine<URm1Id> dl=new DocLine<URm1Id>();
	private DbUtils<QrColDocLine,DocLine<URm1Id>> dbu=new DbUtils<QrColDocLine,DocLine<URm1Id>>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private AbstractDocLineModel<QrColDocLine,DocLine<URm1Id>> od;
    private AbstractDocTitleModel<ColDocTitle,DocTitle> od1;
   
    
    JLabel lab_cus = new JLabel("伙伴代码");
    JLabel lab_cusn = new JLabel("名称");
    JLabel lab_cuslxr = new JLabel("联系人");
    JLabel lab_docn = new JLabel("单号");
    JLabel lab_rdocn = new JLabel("退货单号");
    JLabel lab_status = new JLabel("状态");
    JLabel lab_date = new JLabel("过账日期");
    JLabel lab_user = new JLabel("操作员");
    JLabel lab_ifr = new JLabel("是否退货");
    JLabel lab_sales = new JLabel("销售员");
    JLabel lab_sales1 = new JLabel("销售员1");
    JLabel lab_memo = new JLabel("备注");
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
    JComboBox com_ifincomed; 
    JTextArea jta_memo=new JTextArea(6,20);
    JTextField txt_docn = new JTextField(13);
    JTextField txt_rdocn = new JTextField(13);
    JTextField txt_docnplus = new JTextField(5);
    JTextField txt_status = new JTextField(13);
    JTextField txt_user = new JTextField(13);
    JTextField txt_type = new JTextField(5);
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
    
    JTextField  editor,editorbzsl;
    List<Object[]> list=new ArrayList<Object[]>(); 
    Object[] obj;
	public QrView()
	{
		super("质量反馈单",true,true,true,true);	
		initComponents();
	}
	
	public void initComponents()
    {			
		 this.setName("Qr");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 appMain.fv.setText("");
		 jta_memo.setLineWrap(true);
		// txt_date.setLocale(Locale.US);//设置显示语言
		 txt_date.setPattern("yyyy-MM-dd");//设置日期格式化字符串
		 txt_date.setEditorable(true);//设置是否可编辑
		 txt_date.setBackground(Color.gray);//设置背景色
		 txt_date.setForeground(Color.yellow);//设置前景色
		 txt_date.setPreferredSize(new Dimension(225,24));//设置大小
		 txt_date.setTextAlign(DatePicker.LEFT);
		 	  		 
		 hql1="select u_ifdp from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		 ob1 = appMain.lt.sqlclob(hql1,0,1);
		 hql2="select u_enable from dbo.[@sms] where code='DPERM'";
		 ob = appMain.lt.sqlclob(hql2,0,1);
        if(ob==null||ob.length==0)
        {
      	  JOptionPane.showMessageDialog(null,"请在自定义表中设置数据所有权 ");
      	  return;
        }
        else if(ob[0][0].toString().equals("N")||ob1[0][0].toString().equals("Y"))
        {
    	 hql= "select p.cardCode+','+isnull(p.cardName,'') from Ocrd as p " +
  			  " where (p.cardCode like :str1 or p.cardName like :str2) " +
  			  " and p.cardType='C' and p.validfor='Y' and p.frozenfor='N' ";
  	   	 hql1="select p.cardCode+','+p.cardName from Ocrd as p " +
  			  " where p.cardCode=:str1 and p.cardType='C' and p.validfor='Y' and p.frozenfor='N'";
        }
        else{
		 hql= "select p.cardCode+','+isnull(p.cardName,'') from Ocrd as p " +
			  " where (p.cardCode like :str1 or p.cardName like :str2) " +
			  " and p.cardType='C' and p.validfor='Y' and p.frozenfor='N' " +
			  " and p.slpcode in (select u_sale from [@sale_usergroup] where u_usergroup=" +
			  "(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"'))";
	   	 hql1="select p.cardCode+','+p.cardName from Ocrd as p " +
			  " where p.cardCode=:str1 and p.cardType='C' and p.validfor='Y' and p.frozenfor='N'" +
			  " and p.slpcode in (select u_sale from [@sale_usergroup] where u_usergroup=" +
			  "(select u_usergroup from ousr where userid='"+appMain.oCompany.getUserSignature()+"'))";
        }
		 txt_cus = new JAutoCompleteComboBox(hql,hql1,2);
		 this.txt_docn.setEditable(false);
		 this.txt_docnplus.setEditable(false);
		 this.txt_status.setEditable(false);
		 this.txt_type.setEditable(false);
		 this.txt_cusn.setEditable(false);
		
		 com_ifincomed=new JComboBox();
		 com_ifincomed.setPrototypeDisplayValue("xxx");	
		 hql1="select u_ifr from dbo.[ousr] where userid='"+appMain.oCompany.getUserSignature()+"'";
		 ob1 = appMain.lt.sqlclob(hql1,0,1);
		 if(ob1==null||ob1.length==0)
		 {
			 JOptionPane.showMessageDialog(null,"请定义用户是否退货");
	      	  return;
		 }
		 if(ob1[0][0].toString().equals("N"))
		 {
		 com_ifincomed.addItem("否");
		 }
		 else{
		 com_ifincomed.addItem("是");
		 }
		 
		 pane1.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, lab_cus, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cus, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cus, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cus, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_cusn, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cusn, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cusn, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cusn, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_cuslxr,0,SpringLayout.WEST, lab_cusn);
		 lay.putConstraint(SpringLayout.NORTH, lab_cuslxr, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cuslxr, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cuslxr, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_user, 0,SpringLayout.WEST, lab_cusn);
		 lay.putConstraint(SpringLayout.NORTH, lab_user, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_user, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_user, 100, SpringLayout.NORTH, pane1);
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
		 lay.putConstraint(SpringLayout.WEST, lab_date, 0,SpringLayout.WEST, lab_docn);
		 lay.putConstraint(SpringLayout.NORTH, lab_date, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_date, -50,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_date, 70, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_ifr, 0,SpringLayout.WEST, lab_docn);
		 lay.putConstraint(SpringLayout.NORTH, lab_ifr, 100, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, com_ifincomed, 10,SpringLayout.EAST, lab_ifr);
		 lay.putConstraint(SpringLayout.NORTH,com_ifincomed, 100, SpringLayout.NORTH, pane1);	
		 lay.putConstraint(SpringLayout.WEST, lab_rdocn, 10,SpringLayout.EAST, com_ifincomed);
		 lay.putConstraint(SpringLayout.NORTH,lab_rdocn, 100, SpringLayout.NORTH, pane1);	
		 lay.putConstraint(SpringLayout.WEST, txt_rdocn, 10,SpringLayout.EAST, lab_rdocn);
		 lay.putConstraint(SpringLayout.NORTH,txt_rdocn, 100, SpringLayout.NORTH, pane1);	
		// txt_cus.setSize(4, 80);
		 txt_cus.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");	
		 txt_cuslxr.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
		 pane1.add(lab_cus);
		 pane1.add(lab_cusn);
		 pane1.add(lab_cuslxr);
		 pane1.add(lab_status);	
		 pane1.add(lab_date);	
		 pane1.add(lab_user);	
		 pane1.add(lab_ifr);	
		 pane1.add(lab_docn);	
		 pane1.add(lab_rdocn);	
		 pane1.add(txt_cus);
		 pane1.add(txt_user);
		 pane1.add(txt_cusn);
		 pane1.add(txt_cuslxr);
		 pane1.add(txt_status);	
		 pane1.add(txt_type);	
		 pane1.add(txt_date);				
		 pane1.add(txt_docn);
		 pane1.add(txt_rdocn);
		 pane1.add(txt_docnplus);	
		 pane1.add(com_ifincomed);
		 
		 pane3.setLayout(lay3);
		 lay3.putConstraint(SpringLayout.WEST, lab_ter, 42,SpringLayout.WEST, pane3);
		 lay3.putConstraint(SpringLayout.NORTH, lab_ter, 10, SpringLayout.NORTH, pane3);
		 lay3.putConstraint(SpringLayout.WEST, txt_ter, 100,SpringLayout.WEST, pane3);
		 lay3.putConstraint(SpringLayout.NORTH, txt_ter, 10, SpringLayout.NORTH, pane3);
		 
		 pane3.add(lab_ter);
		 pane3.add(txt_ter);
		 txt_ter.setEditable(false);
		 //单身主数据
	   
		hql="select '','','','','','','','', 0.000,0,0.000,0.000,'','','','','','','','','','','','','',''," +
			"'','','','','','','','','','','','' "+
            " from Rdr1 p where 1=2";	
	    od=new QrDocLineModel(cl,dl,dbu,hql,true);		   	   
		od.setDocLineStatus(docLineStatus.load);
		od.setGridStatus(docLineStatus.load);
	
		jt=new JMyTable(od);

		
		//绑定物料编码
		 hql= "select p.itemCode+','+p.itemName from Oitm as p " +
		    //" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
			 " where  " +
			 "(p.itemCode like :str1 or p.itemName like :str2) and p.validfor='Y' AND p.frozenfor='N'";
		 hql1="select p.itemCode from Oitm as p " +
			 //" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
			  " where " +
			  " p.itemCode=:str1  and validfor='Y' AND frozenfor='N'";
		
		 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("物料代码")), hql,hql1);
		
		 
	
		 
         list.clear();
	     obj=new Object[2];
	     obj[0]="Y";obj[1]="是";
	     list.add(obj);
	     obj=new Object[2];
	     obj[0]="N";obj[1]="否";
	     list.add(obj);
	     
		 od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("是否显示米段")), list);
		 
		jt.getColumnModel().getColumn(od.getcolumnindex("物料代码")).setPreferredWidth(120);
		jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("物料描述")).setPreferredWidth(120);
		jt.getColumnModel().getColumn(od.getcolumnindex("是否显示米段")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("物料米段")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("销售单位")).setPreferredWidth(30);
		//jt.getColumnModel().getColumn(od.getcolumnindex("单位数量")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("仓库代码")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("库存单位")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("包装数量")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("扫描数量")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("扫描个数")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("包装单价")).setPreferredWidth(50);
		jt.getColumnModel().getColumn(od.getcolumnindex("数量")).setPreferredWidth(60);
		jt.getColumnModel().getColumn(od.getcolumnindex("单价")).setPreferredWidth(50);
		//jt.getColumnModel().getColumn(od.getcolumnindex("总长")).setPreferredWidth(65);
		jt.getColumnModel().getColumn(od.getcolumnindex("米价格")).setPreferredWidth(50);
		
		
		
		jt.HiddenCell(od.getcolumnindex("单据代码"),0);
		//jt.HiddenCell(od.getcolumnindex("SN行号"),0);
		jt.HiddenCell(od.getcolumnindex("行号"),0);
		//jt.HiddenCell(od.getcolumnindex("基本单据"),0);
		//jt.HiddenCell(od.getcolumnindex("基本行号"),0);
		//jt.HiddenCell(od.getcolumnindex("伙伴代码"),0);
		//jt.HiddenCell(od.getcolumnindex("伙伴名称"),0);

		
		jt.setAutoCreateRowSorter(true);	
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
		jt.setFillsViewportHeight(true);
	
		JScrollPane scp=new JScrollPane(jt);
		//scp.setPreferredSize(new Dimension(500, 70));
        		 		
		 jtp1.add("基本信息",pane1);
		 jtp1.add("数据浏览",pane2);
		 jtp1.add("自定义",pane3);
         pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
         pane4.add(scp);
                 
         //单头数据浏览
         hql="select 0,'质量反馈单',p.docEntry,p.docDate," +
     			"p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
     	           " '', case when p.docStatus='C' then '关闭' else '打开' end," +
     	           "case when p.canceled='Y' then '是' else '否' end," +
     	           "case when p.printed='Y' then '是' else '否' end " + 		         
     	           " from UQrm p,Oslp p1 where p.slpCode=p1.slpCode and 1=2 ";   
        od1 = new QrDocTitleModel(ct,dt,dbu1,hql,true);
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
		//editorbzsl=(JTextField) od.getValuethrheader(row, colHeader)
		 //添加事件
		 editor.addFocusListener(oc);
		 com_sales.addActionListener(oc);
		 com_sales1.addActionListener(oc);
		 this.com_ifincomed.addActionListener(oc);
	     jt.addMouseListener(oc);	
	     jt.addKeyListener(oc);
	     jt.addFocusListener(oc);
	     jt1.addMouseListener(oc);
	     jt1.getSelectionModel().addListSelectionListener(oc);
	     od.addTableModelListener(oc);
	     this.addInternalFrameListener(oc);
	     	    	     
		 this.setVisible(true);
		 this.setResizable(true);	
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
		public AbstractDocLineModel<QrColDocLine, DocLine<URm1Id>> getOd() {
			return od;
		}
		public void setOd(AbstractDocLineModel<QrColDocLine, DocLine<URm1Id>> od) {
			this.od = od;
		}
		public AbstractDocTitleModel<ColDocTitle, DocTitle> getOd1() {
			return od1;
		}
		public void setOd1(AbstractDocTitleModel<ColDocTitle, DocTitle> od1) {
			this.od1 = (QrDocTitleModel) od1;
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
		public JLabel getLab_ter() {
			return lab_ter;
		}
		public void setLab_ter(JLabel lab_ter) {
			this.lab_ter = lab_ter;
		}
		public JTextField getTxt_ter() {
			return txt_ter;
		}
		public void setTxt_ter(JTextField txt_ter) {
			this.txt_ter = txt_ter;
		}
		public JLabel getLab_sales() {
			return lab_sales;
		}
		public void setLab_sales(JLabel lab_sales) {
			this.lab_sales = lab_sales;
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

		

		public JTextField getTxt_user() {
			return txt_user;
		}

		public void setTxt_user(JTextField txt_user) {
			this.txt_user = txt_user;
		}

		public JTextField getTxt_rdocn() {
			return txt_rdocn;
		}

		public void setTxt_rdocn(JTextField txt_rdocn) {
			this.txt_rdocn = txt_rdocn;
		}

		public JComboBox getCom_ifincomed() {
			return com_ifincomed;
		}

		public void setCom_ifincomed(JComboBox com_ifincomed) {
			this.com_ifincomed = com_ifincomed;
		}
}
