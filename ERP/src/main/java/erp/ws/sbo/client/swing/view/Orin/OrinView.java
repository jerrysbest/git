package erp.ws.sbo.client.swing.view.Orin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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

import com.eltima.components.ui.DatePicker;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.controller.OrinsController;
import erp.ws.sbo.client.swing.model.ColDocLine;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.Inv1Id;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.OrinDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.OrinDocTitleModel;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.utils.DbUtils;

public class OrinView extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6896750977097188243L;
    private OrinsController oc=new OrinsController(this);
    private OrinPrintView opv;
    private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private SpringLayout lay = new SpringLayout();
    private SpringLayout lay3 = new SpringLayout();
    private SpringLayout lay5 = new SpringLayout();
    private String hql="select p.slpCode,p.slpName from Oslp p where slpcode<>-1",hql1;    
    private ColDocLine cl=ColDocLine.getCLine();
	private DocLine<Inv1Id> dl=new DocLine<Inv1Id>();
	private DbUtils<ColDocLine,DocLine<Inv1Id>> dbu=new DbUtils<ColDocLine,DocLine<Inv1Id>>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private AbstractDocLineModel<ColDocLine,DocLine<Inv1Id>> od;
    private AbstractDocTitleModel<ColDocTitle,DocTitle> od1;
    private DeSNView dsv;
    
    JLabel lab_type = new JLabel("����");
    JLabel lab_cusn = new JLabel("����");
    JLabel lab_cuslxr = new JLabel("��ϵ��");
    JLabel lab_docn = new JLabel("����");
    JLabel lab_status = new JLabel("״̬");
    JLabel lab_date = new JLabel("��������");
    JLabel lab_duedate = new JLabel("��������");
    JLabel lab_delidate = new JLabel("��������");
    JLabel lab_sales = new JLabel("����Ա");
    JLabel lab_sales1 = new JLabel("����Ա");
    JLabel lab_memo = new JLabel("��ע");
    JLabel lab_sn = new JLabel("�������к�");
    JLabel lab_asum = new JLabel("���ϼ�");
    JLabel lab_qsum = new JLabel("�����ϼ�");
    JLabel lab_gjsum = new JLabel("�������ϼ�");
    JLabel lab_mssum = new JLabel("�����ϼ�");
    JLabel lab_sjzlsum = new JLabel("ʵ�������ϼ�");
    JLabel lab_ter = new JLabel("����");
    
    JComboBox com_type=new  JComboBox();
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

    JMyTable jt;
    JTable jt1;
    
    JTabbedPane jtp1=new JTabbedPane();	
    JTabbedPane jtp2=new JTabbedPane();	
    
    JTextField  editor;
    List<Object[]> list=new ArrayList<Object[]>(); 
    Object[] obj;
    JButton btn_upqulity = new JButton("���°�װ����");
    public OrinView()
    {
    	super("Ӧ�մ���ƾ֤",true,true,true,true);	
    	
		initComponents();
		dsv=new DeSNView(this);
		dsv.setVisible(false);
    }
    public void initComponents()
    {	
    	 this.setName("ORIN");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 appMain.fv.setText("");
		 hql="from Oslp";
		 jta_SN.setLineWrap(true);
		 jta_memo.setLineWrap(true);
		// txt_date.setLocale(Locale.US);//������ʾ����
		 txt_date.setPattern("yyyy-MM-dd");//�������ڸ�ʽ���ַ���
		 txt_date.setEditorable(true);//�����Ƿ�ɱ༭
		 txt_date.setBackground(Color.gray);//���ñ���ɫ
		 txt_date.setForeground(Color.yellow);//����ǰ��ɫ
		 txt_date.setPreferredSize(new Dimension(225,24));//���ô�С
		 txt_date.setTextAlign(DatePicker.LEFT);
		 
		 txt_duedate.setPattern("yyyy-MM-dd");//�������ڸ�ʽ���ַ���
		 txt_duedate.setEditorable(true);//�����Ƿ�ɱ༭
		 txt_duedate.setBackground(Color.gray);//���ñ���ɫ
		 txt_duedate.setForeground(Color.yellow);//����ǰ��ɫ
		 txt_duedate.setPreferredSize(new Dimension(225,24));//���ô�С
		 txt_duedate.setTextAlign(DatePicker.LEFT);
		 
		 txt_delidate.setPattern("yyyy-MM-dd");//�������ڸ�ʽ���ַ���
		 txt_delidate.setEditorable(true);//�����Ƿ�ɱ༭
		 txt_delidate.setBackground(Color.gray);//���ñ���ɫ
		 txt_delidate.setForeground(Color.yellow);//����ǰ��ɫ
		 txt_delidate.setPreferredSize(new Dimension(225,24));//���ô�С
		 txt_delidate.setTextAlign(DatePicker.LEFT);

		 com_type.addItem( new  ComboBoxItem(0,"�����˻�"));
		 com_type.addItem( new  ComboBoxItem(1,"�����˻�(û��ǰ�õ���)"));			
		 com_type.addItem( new  ComboBoxItem(2,"����(�ӹ�)"));
		 this.txt_docn.setEditable(false);
		 this.txt_docnplus.setEditable(false);
		 this.txt_status.setEditable(false);
		 this.txt_type.setEditable(false);
		 this.txt_cusn.setEditable(false);
		 
		 pane1.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, lab_type, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_type, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, com_type, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_type, 10, SpringLayout.NORTH, pane1);
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
		 lay.putConstraint(SpringLayout.WEST, btn_upqulity, 6, SpringLayout.EAST, txt_delidate);
	     lay.putConstraint(SpringLayout.SOUTH, btn_upqulity, 0, SpringLayout.SOUTH, txt_delidate);
		 
		// com_type.setSize(4, 80);
		 com_type.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");	
		 txt_cuslxr.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
	
	
		 pane1.add(lab_type);
		 pane1.add(lab_cusn);
		 pane1.add(lab_cuslxr);
		 pane1.add(lab_status);	
		 pane1.add(lab_date);	
		 pane1.add(lab_delidate);	
		 pane1.add(lab_duedate);	
		 pane1.add(lab_docn);	
		 pane1.add(com_type);
		 pane1.add(txt_cusn);
		 pane1.add(txt_cuslxr);
		 pane1.add(txt_status);	
		 pane1.add(txt_type);	
		 pane1.add(txt_date);			
		 pane1.add(txt_delidate);	
		 pane1.add(txt_duedate);	
		 pane1.add(txt_docn);	
		 pane1.add(txt_docnplus);	
		 pane1.add(btn_upqulity);	
		 
		 pane3.setLayout(lay3);
		 lay3.putConstraint(SpringLayout.WEST, lab_ter, 42,SpringLayout.WEST, pane3);
		 lay3.putConstraint(SpringLayout.NORTH, lab_ter, 10, SpringLayout.NORTH, pane3);
		 lay3.putConstraint(SpringLayout.WEST, txt_ter, 100,SpringLayout.WEST, pane3);
		 lay3.putConstraint(SpringLayout.NORTH, txt_ter, 10, SpringLayout.NORTH, pane3);
		 
		 pane3.add(lab_ter);
		 pane3.add(txt_ter);
		 txt_ter.setEditable(false);
		 
		  //����������		   
			hql="select '','','','','','','','', '','','','','','','','','','','','','','','','','',''," +
					"'','','','','','','','','','','','' "+
	           "from Rdr1 p where 1=2";	
		    od=new OrinDocLineModel(cl,dl,dbu,hql);		   	   
			od.setDocLineStatus(docLineStatus.load);
			od.setGridStatus(docLineStatus.load);
			
			jt=new JMyTable(od);
			//�󶨻�����
			 hql= "select p.cardcode+','+isnull(p.cardName,'') from Ocrd as p " +
				" where (p.cardcode like :str1 or p.cardName like :str2) " +
				" and p.cardType='C' and p.validfor='Y' and p.frozenfor='N'";
			
			 hql1="select p.cardCode from ocrd as p " +
				" where p.cardCode=:str1 "+
				" and p.cardType='C' and p.validfor='Y' and p.frozenfor='N'";
			
			 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("������")),hql,hql1);
			//�����ϱ���
			 hql= "select p.itemCode+','+p.itemName from Oitm as p " +
						//" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
						" where  " +
						"(p.itemCode like :str1 or p.itemName like :str2) and p.validfor='Y' AND p.frozenfor='N'";
			 hql1="select p.itemCode from Oitm as p " +
						//" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
						" where " +
						" p.itemCode=:str1  and validfor='Y' AND frozenfor='N'";
			
			 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("���ϴ���")), hql,hql1);
			//�󶨲ֿ�
			 hql= "select p.whsCode+','+p.whsName from Owhs as p " +
				" where (p.whsCode like :str1 or p.whsName like :str2)";
			
			 hql1="select p.whsCode from Owhs as p " +
				" where p.whsCode=:str1";
			
			 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("�ֿ����")),hql,hql1);
						 
			 obj=new Object[2];
		     obj[0]="N";obj[1]="��";
		     list.add(obj);
		     obj=new Object[2];
		     obj[0]="Y";obj[1]="��";
		     list.add(obj);
		     
			 od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("�Ƿ񻻻�")), list);
			 
			 list.clear();
		     obj=new Object[2];
		     obj[0]="Y";obj[1]="��";
		     list.add(obj);
		     obj=new Object[2];
		     obj[0]="N";obj[1]="��";
		     list.add(obj);
		     
			 od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("�Ƿ���ʾ�׶�")), list);
			 
			jt.getColumnModel().getColumn(od.getcolumnindex("������")).setPreferredWidth(200);
			jt.getColumnModel().getColumn(od.getcolumnindex("���ϴ���")).setPreferredWidth(200);
			jt.getColumnModel().getColumn(od.getcolumnindex("���")).setPreferredWidth(30);
			jt.getColumnModel().getColumn(od.getcolumnindex("��������")).setPreferredWidth(120);
			
			
			jt.setAutoCreateRowSorter(true);	
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
			jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
			jt.setFillsViewportHeight(true);
		
			JScrollPane scp=new JScrollPane(jt);
			
			 jtp1.add("������Ϣ",pane1);
			 
			 jtp1.add("�������",pane2);
			 jtp1.add("�Զ���",pane3);
	         pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
	         pane4.add(scp);
	                 
	         //��ͷ�������
	         hql="select 0,'����',p.docEntry,p.docDate," +
	     		  "p.cardCode,p.cardName,p.slpCode,p1.slpName,"+
     	           " '', case when p.docStatus='C' then '�ر�' else '��' end," +
     	           "case when p.canceled='Y' then '��' else '��' end," +
     	           "case when p.printed='Y' then '��' else '��' end " + 		         
     	           " from Orin p,Oslp p1 where p.slpCode=p1.slpCode and 1=2 ";   
	        od1 = new OrinDocTitleModel(ct,dt,dbu1,hql);
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
			
			 //pane5�Ĳ���
	       
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
			
			 jtp2.add("����",pane4);
				                
		     Container pane=this.getContentPane();
		     JSplitPane splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jtp1,jtp2);
		     /*����splitPane1�ķָ���λ�ã�0.3�������splitPane1�Ĵ�С������������ֵ�ķ�Χ��0.0��1.0
		      *�С�����ʹ������ֵ������splitPane�ķָ���λ�ã����34����ʾ�����������ֵ��pixelΪ���㵥λ
		      */
		     splitPane1.setDividerLocation(160);
			 pane.add(splitPane1, BorderLayout.CENTER);
						
			 //����¼�
			 com_type.addActionListener(oc);
			 com_sales.addActionListener(oc);
			 com_sales1.addActionListener(oc);
			 btn_upqulity.addActionListener(oc);
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
	public OrinsController getOc() {
		return oc;
	}
	public void setOc(OrinsController oc) {
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
	public JLabel getLab_type() {
		return lab_type;
	}
	public void setLab_type(JLabel lab_type) {
		this.lab_type = lab_type;
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
	public JComboBox getCom_type() {
		return com_type;
	}
	public void setCom_type(JComboBox com_type) {
		this.com_type = com_type;
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
	public OrinPrintView getOpv() {
		return opv;
	}
	public void setOpv(OrinPrintView opv) {
		this.opv = opv;
	}
	public JTextArea getJta_SN() {
		return jta_SN;
	}
	public void setJta_SN(JTextArea jta_SN) {
		this.jta_SN = jta_SN;
	}
	public DeSNView getDsv() {
		return dsv;
	}
	public void setDsv(DeSNView dsv) {
		this.dsv = dsv;
	}
	public JButton getBtn_upqulity() {
		return btn_upqulity;
	}
	public void setBtn_upqulity(JButton btn_upqulity) {
		this.btn_upqulity = btn_upqulity;
	}
    
}
