package erp.ws.sbo.client.swing.view.Oige;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import erp.ws.sbo.client.swing.controller.OigesController;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.ColOignDocLine;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.OignDocLine;
import erp.ws.sbo.client.swing.tablemodel.OigeDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.OignDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.client.swing.view.DeSN.DeSNView;
import erp.ws.sbo.utils.DbUtils;

public class OigeView extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5386813674444011307L;
	private OigesController oc=new OigesController(this);
    private JPanel  pane1=new JPanel();//base information
	private JPanel  pane2=new JPanel();//data-browser
    private JPanel  pane3=new JPanel();//userdefined field
    private JPanel  pane4=new JPanel();//the parent of pane5
    private JPanel  pane5=new JPanel();//the bottom of the doc
    private JPanel  pane6=new JPanel();//the query of the doc
    private SpringLayout lay = new SpringLayout();
    @SuppressWarnings("unused")
	private String  hql="select p.userid,isnull(p.U_Name,'') from Ousr as p where p.userid='"+appMain.oCompany.getUserSignature()+"'",hql1,yon;    
    private ColOignDocLine cl=ColOignDocLine.getCLine();
	private OignDocLine dl=new OignDocLine();
	private DbUtils<ColOignDocLine,OignDocLine> dbu=new DbUtils<ColOignDocLine,OignDocLine>();
    private ColDocTitle ct=ColDocTitle.getCTitle();
    private	DocTitle dt=new DocTitle();
    private	DbUtils<ColDocTitle,DocTitle> dbu1=new DbUtils<ColDocTitle,DocTitle>();
    private OignDocLineModel od;
    private OignDocLineModel od2;
    private OigeDocTitleModel od1;
    
    private DeSNView dsv;
    
    JLabel lab_date = new JLabel("����");
    JLabel lab_user = new JLabel("����Ա");
    JLabel lab_cppo = new JLabel("��ѯ����");
    JLabel lab_docn = new JLabel("����");
    JLabel lab_memo = new JLabel("��ע");
    JLabel lab_sn = new JLabel("�������к�");
    JLabel lab_msum = new JLabel("��������");
    JLabel lab_wsum = new JLabel("�������");
    JLabel lab_rsum = new JLabel("ʵ������");
    JLabel lab_psum = new JLabel("�ƻ�����");
    JLabel lab_csum = new JLabel("�������");

    JUComboBox com_users=new JUComboBox(hql);
    DatePicker txt_date  = new DatePicker(pane1,new Date());
    JTextArea jta_memo=new JTextArea(6,20);
    JTextArea jta_SN=new JTextArea(8,25);
    JTextField txt_docn = new JTextField(13);
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
    
    public OigeView()
	{
		super("��������",true,true,true,true);	
		initComponents();
		bt_cppo.setText("���ƶ���");
		dsv=new DeSNView(this);
		dsv.setVisible(false);
	}

	private void initComponents() {
		 this.setName("OIGE");
		 this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		 appMain.fv.setText("");
		 jta_SN.setLineWrap(true);
		 jta_memo.setLineWrap(true);
		 hql="select u_enable from [@sms] where code='CKCZY'";
		 yon=appMain.lt.sqlclob(hql, 0, 1)[0][0].toString();

		 txt_date.setPattern("yyyy-MM-dd");//�������ڸ�ʽ���ַ���
		 txt_date.setEditorable(true);//�����Ƿ�ɱ༭
		 txt_date.setBackground(Color.gray);//���ñ���ɫ
		 txt_date.setForeground(Color.yellow);//����ǰ��ɫ
		 txt_date.setPreferredSize(new Dimension(225,24));//���ô�С
		 txt_date.setTextAlign(DatePicker.LEFT);

		 com_users.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxx");

		 
		 this.txt_docn.setEditable(false);
		 
		 pane1.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, lab_date, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_date, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_date, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_date, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, lab_cppo, 42,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_cppo, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, txt_cppo, 100,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_cppo, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.WEST, bt_cppo, 300,SpringLayout.WEST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, bt_cppo, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_docn, -250,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, txt_docn, -100,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, txt_docn, 10, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, lab_user, -250,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, lab_user, 40, SpringLayout.NORTH, pane1);
		 lay.putConstraint(SpringLayout.EAST, com_users, -100,SpringLayout.EAST, pane1);
		 lay.putConstraint(SpringLayout.NORTH, com_users, 40, SpringLayout.NORTH, pane1);
		 

		 pane1.add(lab_user);
		 pane1.add(com_users);
		 pane1.add(lab_date);
		 pane1.add(txt_date);
		 pane1.add(lab_docn);
		 pane1.add(txt_docn);
		 pane1.add(lab_cppo);
		 pane1.add(txt_cppo);
		 pane1.add(bt_cppo);
		 
		//����������			   
		 hql="select '','','','','','','','','','','','','','','','','','','' "+
	           "from Inv1 p where 1=2";	
	     od=new OignDocLineModel(cl,dl,dbu,hql);		   	   
		 od.setDocLineStatus(docLineStatus.load);
		 od.setGridStatus(docLineStatus.load);
		 //����������			   
		 hql="select '','','','','','','','','','','','','','','','','','','' "+
	           "from Inv1 p where 1=2";	
	     od2=new OignDocLineModel(cl,dl,dbu,hql);		   	   
		 od2.setDocLineStatus(docLineStatus.load);
		 od2.setGridStatus(docLineStatus.load);	
		 jt=new JMyTable(od);
		 jt2=new JMyTable(od2);
		 hql= "select p.itemCode+','+p.itemName from Oitm as p " +
					//" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
					" where " +
					"(p.itemCode like :str1 or p.itemName like :str2) and p.validfor='Y' AND p.frozenfor='N'";
		 hql1="select p.itemCode from Oitm as p " +
					//" where charindex('#',itemcode)<>0 and charindex('/',itemcode)=0 AND " +
					" where " +
					"p.itemCode=:str1  and validfor='Y' AND frozenfor='N'";
		
		 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("���ϴ���")), hql,hql1);
		 //�󶨲ֿ�
		 hql= "select p.whsCode+','+p.whsName from Owhs as p " +
					" where (p.whsCode like :str1 or p.whsName like :str2)";
		
		 hql1="select p.whsCode from Owhs as p " +
					" where p.whsCode=:str1";
		
		 od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("�ֿ�")), hql,hql1);
		 
		 jt.HiddenCell(od.getcolumnindex("�ƻ���������"),0);
		 
		 jt.getColumnModel().getColumn(od.getcolumnindex("���")).setPreferredWidth(30);
		 jt.getColumnModel().getColumn(od.getcolumnindex("��������")).setPreferredWidth(120);
		 jt.getColumnModel().getColumn(od.getcolumnindex("���ϴ���")).setPreferredWidth(140);
		 jt.setAutoCreateRowSorter(true);	
		 jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		 jt.setPreferredScrollableViewportSize(new Dimension(500, 170));
		 jt.setFillsViewportHeight(true);
	
		 jt2.getColumnModel().getColumn(od.getcolumnindex("���")).setPreferredWidth(30);
		 jt2.getColumnModel().getColumn(od.getcolumnindex("��������")).setPreferredWidth(120);
		 jt2.getColumnModel().getColumn(od.getcolumnindex("���ϴ���")).setPreferredWidth(140);
		 jt2.setAutoCreateRowSorter(true);	
		 jt2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		 jt2.setPreferredScrollableViewportSize(new Dimension(500, 170));
		 jt2.setFillsViewportHeight(true);
		
		 scp=new JScrollPane(jt);
		 scp2=new JScrollPane(jt2);		 		
		 jtp1.add("������Ϣ",pane1);
		 jtp1.add("�������",pane2);
		 jtp1.add("�Զ���",pane3);
         pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
         pane4.add(scp);
         pane6.setLayout(new BoxLayout(pane6, BoxLayout.Y_AXIS));
         pane6.add(scp2);
         
         //��ͷ�������
         hql="select '','','','','','','','','','','',''" +	         
     	           " from Ordr where 1=2 ";   
        od1 = new OigeDocTitleModel(ct,dt,dbu1,hql,true);
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
		
		 //pane5�Ĳ���
		 pane5.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, this.lab_memo, 42,SpringLayout.WEST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_memo, 5, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.WEST, this.jta_memo, 42,SpringLayout.WEST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.jta_memo, 35, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.WEST, this.lab_sn, 280,SpringLayout.WEST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_sn, 5, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.WEST, this.jta_SN, 280,SpringLayout.WEST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.jta_SN, 35, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.lab_msum, -300,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_msum, 5, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.txt_msum, -50,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.txt_msum, 5, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.lab_wsum, -300,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_wsum, 35, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.txt_wsum, -50,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.txt_wsum, 35, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.lab_rsum, -300,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_rsum, 65, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.txt_rsum, -50,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.txt_rsum, 65, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.lab_psum, -300,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_psum, 95, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.txt_psum, -50,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.txt_psum, 95, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.lab_csum, -300,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.lab_csum, 125, SpringLayout.NORTH, pane5);
		 lay.putConstraint(SpringLayout.EAST, this.txt_csum, -50,SpringLayout.EAST, pane5);
		 lay.putConstraint(SpringLayout.NORTH,this.txt_csum, 125, SpringLayout.NORTH, pane5);
		
		 pane5.add(this.lab_memo);
		 pane5.add(this.jta_memo);
		 pane5.add(this.lab_sn);
		 pane5.add(this.jta_SN);
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

		 
         pane4.add(pane5);
		
		 jtp2.add("����",pane4);
		 jtp2.add("��ѯ����",pane6);	 	                
	     Container pane=this.getContentPane();
	     splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jtp1,jtp2);
	     /*����splitPane1�ķָ���λ�ã�0.3�������splitPane1�Ĵ�С������������ֵ�ķ�Χ��0.0��1.0
	      *�С�����ʹ������ֵ������splitPane�ķָ���λ�ã����34����ʾ�����������ֵ��pixelΪ���㵥λ
	      */
	     splitPane1.setDividerLocation(160);
		 pane.add(splitPane1, BorderLayout.CENTER);
		 
		 //����¼�
		 txt_cppo.addKeyListener(oc);
		 bt_cppo.addActionListener(oc);
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
	public OigesController getOc() {
		return oc;
	}
	public void setOc(OigesController oc) {
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

	public JUComboBox getCom_users() {
		return com_users;
	}
	public void setCom_users(JUComboBox com_users) {
		this.com_users = com_users;
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
	public OigeDocTitleModel getOd1() {
		return od1;
	}

	public void setOd1(OigeDocTitleModel od1) {
		this.od1 = od1;
	}

	public JTextField getTxt_cppo() {
		return txt_cppo;
	}

	public void setTxt_cppo(JTextField txt_cppo) {
		this.txt_cppo = txt_cppo;
	}
	public void setBt_cppo(JButton bt_cppo) {
		this.bt_cppo = bt_cppo;
	}

	public JButton getBt_cppo() {
		return bt_cppo;
	}
	public DatePicker getTxt_date() {
		return txt_date;
	}

	public void setTxt_date(DatePicker txt_date) {
		this.txt_date = txt_date;
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

}
