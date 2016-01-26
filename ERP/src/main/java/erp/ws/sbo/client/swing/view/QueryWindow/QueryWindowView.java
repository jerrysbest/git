package erp.ws.sbo.client.swing.view.QueryWindow;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.eltima.components.ui.DatePicker;

import erp.ws.sbo.client.swing.controller.QueryWindowsController;
import erp.ws.sbo.client.swing.dao.IQDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JAutoCompleteComboBox;

public class QueryWindowView<T> extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -189996093319359089L;
	private  QueryWindowsController qc;
	private  JLabel lab_Rdate=new JLabel("日期");
	private  JLabel lab_Rdocid=new JLabel("单号");
	private  JLabel lab_Rsaleperson=new JLabel("销售员");
	private  JLabel lab_Rcard=new JLabel("业务伙伴");
	private  JLabel lab_draft=new JLabel("是否草稿");
	private  Container con=this.getContentPane();
	private  DatePicker date1=new DatePicker(con,new Date());
	private  DatePicker date2=new DatePicker(con,new Date());
	private  JTextField txt_docid1=new JTextField();
	private  JTextField txt_docid2=new JTextField();
	private  JAutoCompleteComboBox Acb_saleid1;
	private  JAutoCompleteComboBox Acb_saleid2;
	private  JAutoCompleteComboBox Acb_card1;
	private  JAutoCompleteComboBox Acb_card2;
	private  JComboBox jcb_draft=new JComboBox();
	
	private AbstractDocTitleModel<ColDocTitle,DocTitle> od1;
	
	private JButton Jb_confirm=new JButton("查询");
	
	private SpringLayout lay = new SpringLayout();
	
	private  String hql = "select cast(p.slpCode as nvarchar(10))+','+p.slpName from Oslp as p " +
			" where cast(p.slpCode as nvarchar(10)) like :str1 or p.slpName like :str2",
	          hql1="select p.slpCode from Oslp as p " +
		      " where cast(p.slpCode as nvarchar(10))=:str1",
		      hql2= "select p.cardCode+','+p.cardName from Ocrd as p " +
					" where (p.cardCode like :str1 or p.cardName like :str2) " +
					" and p.cardType='C' and p.validfor='Y' and p.frozenfor='N'",
		   	  hql3="select p.cardCode+','+p.cardName from Ocrd as p " +
					" where p.cardCode=:str1 and p.cardType='C' and p.validfor='Y' and p.frozenfor='N'";
	private ParaList pl=new ParaList();
	
	private T v;
	public QueryWindowView(T v,IQDoc q,AbstractDocTitleModel<ColDocTitle,DocTitle> od1)
	{
		super("单据查询",true,true,true,true);	
		this.setQc(new QueryWindowsController(this,q));	
		this.setName("Qdoc");
	    this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);	 
	    this.v=v;
	    this.setOd1(od1);
	    //this.qc=new QueryWindowsController(this,q);
		initComponents();
	}
	public QueryWindowView(T v,IQDoc q,AbstractDocTitleModel<ColDocTitle,DocTitle> od1,String hql,String hql1,String hql2,String hql3)
	{
		super("单据查询",true,true,true,true);	
		this.setQc(new QueryWindowsController(this,q));	
		this.setName("Qdoc");
	    this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);	 
	    this.v=v;
	    this.setOd1(od1);
	    //this.qc=new QueryWindowsController(this,q);
	    this.hql=hql;
	    this.hql1=hql1;
	    this.hql2=hql2;
	    this.hql3=hql3;
		initComponents();
	}
	public QueryWindowView(T v,IQDoc q,AbstractDocTitleModel<ColDocTitle,DocTitle> od1,String hql,String hql1)
	{
		super("单据查询",true,true,true,true);	
		this.setQc(new QueryWindowsController(this,q));	
		this.setName("Qdoc");
	    this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);	 
	    this.v=v;
	    this.setOd1(od1);
	    //this.qc=new QueryWindowsController(this,q);
	    this.hql=hql;
	    this.hql1=hql1;
		initComponents();
	}
	private void initComponents() {
		// TODO Auto-generated method stub	
		
	   	Acb_saleid1 = new JAutoCompleteComboBox(hql,hql1,1);
	   	Acb_saleid2 = new JAutoCompleteComboBox(hql,hql1,1);
	   
   	    Acb_card1 = new JAutoCompleteComboBox(hql2,hql3,1);
	   	Acb_card2 = new JAutoCompleteComboBox(hql2,hql3,1);
	   	
	   	con=this.getContentPane();
	   		   	
	    this.date1.setPattern("yyyy-MM-dd");//设置日期格式化字符串
	    this.date1.setEditorable(true);//设置是否可编辑
	    this.date1.setBackground(Color.gray);//设置背景色
	    this.date1.setForeground(Color.yellow);//设置前景色
	    this.date1.setPreferredSize(new Dimension(150,24));//设置大小
	    this.date1.setTextAlign(DatePicker.LEFT);
	    
	    this.date2.setPattern("yyyy-MM-dd");//设置日期格式化字符串
	    this.date2.setEditorable(true);//设置是否可编辑
	    this.date2.setBackground(Color.gray);//设置背景色
	    this.date2.setForeground(Color.yellow);//设置前景色
	    this.date2.setPreferredSize(new Dimension(150,24));//设置大小
	    this.date2.setTextAlign(DatePicker.LEFT);
	    
	   	this.txt_docid1.setColumns(13);
	   	this.txt_docid2.setColumns(13);
	   	this.Acb_card1.setPrototypeDisplayValue("************************");
	   	this.Acb_card2.setPrototypeDisplayValue("************************");
	   	this.Acb_saleid1.setPrototypeDisplayValue("************************");
	   	this.Acb_saleid2.setPrototypeDisplayValue("************************");
	   	this.jcb_draft.setPrototypeDisplayValue("************************");
	   	
	   	ComboBoxItem cbm=new ComboBoxItem("0","是");
	   	this.jcb_draft.addItem(cbm);
	    cbm=new ComboBoxItem("1","否");
	   	this.jcb_draft.addItem(cbm);
	     con.setLayout(lay);
		 lay.putConstraint(SpringLayout.WEST, this.lab_Rdate, 20,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.lab_Rdate, 5, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.date1, 80,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.date1, 5, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.date2, 260,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.date2, 5, SpringLayout.NORTH, con);
		 
		 lay.putConstraint(SpringLayout.WEST, this.lab_Rdocid, 20,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.lab_Rdocid, 35, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.txt_docid1, 80,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.txt_docid1, 35, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.txt_docid2, 260,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.txt_docid2, 35, SpringLayout.NORTH, con);
		 
		 lay.putConstraint(SpringLayout.WEST, this.lab_Rsaleperson, 20,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.lab_Rsaleperson, 65, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.Acb_saleid1, 80,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.Acb_saleid1, 65, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.Acb_saleid2, 260,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.Acb_saleid2, 65, SpringLayout.NORTH, con);
		 
		 lay.putConstraint(SpringLayout.WEST, this.lab_Rcard, 20,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.lab_Rcard, 95, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.Acb_card1, 80,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.Acb_card1, 95, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.Acb_card2, 260,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.Acb_card2, 95, SpringLayout.NORTH, con);
		 
		 lay.putConstraint(SpringLayout.WEST, this.lab_draft, 20,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.lab_draft, 125, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, this.jcb_draft, 80,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.jcb_draft, 125, SpringLayout.NORTH, con);
		 
		 lay.putConstraint(SpringLayout.WEST, this.Jb_confirm, 220,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, this.Jb_confirm, 180, SpringLayout.NORTH, con);
		 
		 con.add(this.lab_Rdate);
		 con.add(this.lab_Rdocid);
		 con.add(this.lab_Rsaleperson);
		 con.add(this.lab_Rcard);
		 con.add(this.txt_docid1);
		 con.add(this.txt_docid2);
		 con.add(this.Acb_card1);
		 con.add(this.Acb_card2);
		 con.add(this.Acb_saleid1);
		 con.add(this.Acb_saleid2);
		 con.add(this.date1);
		 con.add(this.date2);
		 con.add(this.lab_draft);
		 con.add(this.jcb_draft);
		 con.add(this.Jb_confirm);
		 
		 this.Jb_confirm.addActionListener(qc);
	}

 
	public QueryWindowsController getQc() {
		return qc;
	}
	public void setQc(QueryWindowsController qc) {
		this.qc = qc;
	}

	public DatePicker getDate1() {
		return date1;
	}

	public void setDate1(DatePicker date1) {
		this.date1 = date1;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql= hql;
	}
	public String getHql1() {
		return hql1;
	}

	public void setHql1(String hql1) {
		this.hql1= hql1;
	}
	
	public void resetAcb_saleid1(String hql,String hql1){
		this.Acb_saleid1 = new JAutoCompleteComboBox(hql,hql1,1);
	   
	}
	
    public void resetAcb_saleid2(String hql,String hql1){   	
	   	this.Acb_saleid2 = new JAutoCompleteComboBox(hql,hql1,1);
	}
	
	public  JLabel  getlab_Rsaleperson() {
		return lab_Rsaleperson;
	}

	public void setlab_Rsaleperson(JLabel lab) {
		this.lab_Rsaleperson = lab;
	}
	
	public  JLabel  getlab_Rcard() {
		return lab_Rcard;
	}

	public void setlab_Rcard(JLabel lab) {
		this.lab_Rcard = lab;
	}

	public DatePicker getDate2() {
		return date2;
	}

	public void setDate2(DatePicker date2) {
		this.date2 = date2;
	}

	public JTextField getTxt_docid1() {
		return txt_docid1;
	}

	public void setTxt_docid1(JTextField txt_docid1) {
		this.txt_docid1 = txt_docid1;
	}

	public JTextField getTxt_docid2() {
		return txt_docid2;
	}

	public void setTxt_docid2(JTextField txt_docid2) {
		this.txt_docid2 = txt_docid2;
	}

	public JAutoCompleteComboBox getAcb_saleid1() {
		return Acb_saleid1;
	}

	public void setAcb_saleid1(JAutoCompleteComboBox acb_saleid1) {
		Acb_saleid1 = acb_saleid1;
	}

	public JAutoCompleteComboBox getAcb_saleid2() {
		return Acb_saleid2;
	}

	public void setAcb_saleid2(JAutoCompleteComboBox acb_saleid2) {
		Acb_saleid2 = acb_saleid2;
	}

	public JAutoCompleteComboBox getAcb_card1() {
		return Acb_card1;
	}

	public void setAcb_card1(JAutoCompleteComboBox acb_card1) {
		Acb_card1 = acb_card1;
	}

	public JAutoCompleteComboBox getAcb_card2() {
		return Acb_card2;
	}

	public void setAcb_card2(JAutoCompleteComboBox acb_card2) {
		Acb_card2 = acb_card2;
	}

	public JButton getJb_confirm() {
		return Jb_confirm;
	}

	public void setJb_confirm(JButton jb_confirm) {
		Jb_confirm = jb_confirm;
	}

	public ParaList getPl() {				
		pl.setBegincardCode(this.getAcb_card1().getText().trim());
		pl.setEndcardCode(this.getAcb_card2().getText().trim());
		pl.setBeginsaleperson(this.getAcb_saleid1().getText().length()==0?"":this.getAcb_saleid1().getText().trim());
		pl.setEndsaleperson(this.getAcb_saleid2().getText().length()==0?"":this.getAcb_saleid2().getText().trim());
		pl.setBegindocid((this.getTxt_docid1().getText().equals("")||this.getTxt_docid1().getText().length()==0)?"":this.getTxt_docid1().getText().trim());
		pl.setEnddocid((this.getTxt_docid2().getText().equals("")||this.getTxt_docid2().getText().length()==0)?"":this.getTxt_docid2().getText().trim());
		pl.setBegindate((Date)this.getDate1().getValue());
		pl.setEnddate((Date)this.getDate2().getValue());
		pl.setDraft(((ComboBoxItem)this.getJcb_draft().getSelectedItem()).getValue().toString());
		return pl;
	}

	public void setPl(ParaList pl) {		
		this.pl = pl;
	}

	public JComboBox getJcb_draft() {
		return jcb_draft;
	}

	public void setJcb_draft(JComboBox jcb_draft) {
		this.jcb_draft = jcb_draft;
	}

	public T getV() {
		return v;
	}

	public void setV(T v) {
		this.v = v;
	}

	public AbstractDocTitleModel<ColDocTitle,DocTitle> getOd1() {
		return od1;
	}

	public void setOd1(AbstractDocTitleModel<ColDocTitle,DocTitle> od1) {
		this.od1 = od1;
	}
	public JLabel getLab_draft() {
		return lab_draft;
	}
	public void setLab_draft(JLabel lab_draft) {
		this.lab_draft = lab_draft;
	}

	
	
	
	
}
