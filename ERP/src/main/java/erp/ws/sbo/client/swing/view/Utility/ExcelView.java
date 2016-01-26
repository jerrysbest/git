package erp.ws.sbo.client.swing.view.Utility;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;


import javax.swing.ScrollPaneConstants;

import erp.ws.sbo.client.swing.controller.ExcelsController;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.DocLineModel;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
import erp.ws.sbo.utils.DbUtils;

public class ExcelView  extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3871695174631246799L;	
	private ExcelsController oc=new ExcelsController(this);
	private JButton btn_selexcelfile = new JButton("选择excel文档");
	private JFileChooser fc=new JFileChooser();
	private JSplitPane splitPane = new JSplitPane();	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);	
	private JPanel panel = new JPanel();
	private SpringLayout sl_panel = new SpringLayout();	
	private JLabel lbl_select = new JLabel("选择表");
	private String hql="select tablename,describle from table_list ";
	private JComboBox comboBox=new JUComboBox(hql);
	private JButton btn_export = new JButton("导出excel");
	private JTextField jt_selexcelfile=new JTextField(40);
	private JButton btn_importdb = new JButton("导入数据库");
	private MyFileFilter mff=new MyFileFilter();
	private JTable table=new JTable();
	private JPanel panel_1 = new JPanel();	
	private SpringLayout sl_panel_1 = new SpringLayout();	
	private JScrollPane scp;
	
	private DbUtils dbu1=new DbUtils();
    private AbstractDocLineModel od; 



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcelView window = new ExcelView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExcelView() {
		super("Excel数据导入导出");	
		sl_panel.putConstraint(SpringLayout.WEST, btn_export, 124, SpringLayout.EAST, comboBox);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		this.setBounds(100, 100, 854, 366);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    
				
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(60);
		this.getContentPane().add(splitPane);
		
		splitPane.setLeftComponent(tabbedPane);
		
		tabbedPane.addTab("操作", null, panel, null);
		panel.setLayout(sl_panel);
		jt_selexcelfile.setEditable(false);
		fc.setFileFilter(mff);
		
		sl_panel.putConstraint(SpringLayout.WEST, lbl_select, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lbl_select, 49, SpringLayout.WEST, panel);
		panel.add(lbl_select);
		

		sl_panel.putConstraint(SpringLayout.NORTH, comboBox, 0, SpringLayout.NORTH, lbl_select);
		sl_panel.putConstraint(SpringLayout.WEST, comboBox, 23, SpringLayout.EAST, lbl_select);
		panel.add(comboBox);
		
		comboBox.addActionListener(oc);
		

		sl_panel.putConstraint(SpringLayout.NORTH, btn_export, 0, SpringLayout.NORTH, lbl_select);
		panel.add(btn_export);
		btn_export.addActionListener(oc);
		
		
		sl_panel.putConstraint(SpringLayout.NORTH, btn_selexcelfile, 0, SpringLayout.NORTH, lbl_select);
		sl_panel.putConstraint(SpringLayout.WEST, btn_selexcelfile, 20, SpringLayout.EAST,btn_export);
		panel.add(btn_selexcelfile);
		btn_selexcelfile.addActionListener(oc);
		
		sl_panel.putConstraint(SpringLayout.NORTH, jt_selexcelfile, 0, SpringLayout.NORTH, lbl_select);
		sl_panel.putConstraint(SpringLayout.WEST, jt_selexcelfile, 20, SpringLayout.EAST, btn_selexcelfile);
		panel.add(jt_selexcelfile);
		
		sl_panel.putConstraint(SpringLayout.NORTH, btn_importdb, 0, SpringLayout.NORTH, lbl_select);
		sl_panel.putConstraint(SpringLayout.WEST, btn_importdb, 20, SpringLayout.EAST, jt_selexcelfile);
		panel.add(btn_importdb);
		btn_importdb.addActionListener(oc);
		
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane_1);
		
		tabbedPane_1.addTab("明细", null, panel_1, null);
		
		hql="select * from table_list";
				
	    od=new DocLineModel(dbu1,"table_list",hql);		   	   
				
		table=new JMyTable(od);
		
		scp=new JScrollPane(table);
		scp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.setLayout(sl_panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, scp, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, scp, 0, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, scp, 0, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, scp, 0, SpringLayout.EAST, panel_1);
		panel_1.add(scp);		
	}


	public ExcelsController getOc() {
		return oc;
	}

	public void setOc(ExcelsController oc) {
		this.oc = oc;
	}

	public JButton getBtn_selexcelfile() {
		return btn_selexcelfile;
	}

	public void setBtn_selexcelfile(JButton btn_selexcelfile) {
		this.btn_selexcelfile = btn_selexcelfile;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	

	public JButton getBtn_export() {
		return btn_export;
	}

	public void setBtn_export(JButton btn_export) {
		this.btn_export = btn_export;
	}

	public JTextField getJt_selexcelfile() {
		return jt_selexcelfile;
	}

	public void setJt_selexcelfile(JTextField jt_selexcelfile) {
		this.jt_selexcelfile = jt_selexcelfile;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	public DbUtils getDbu1() {
		return dbu1;
	}

	public void setDbu1(DbUtils dbu1) {
		this.dbu1 = dbu1;
	}

	public AbstractDocLineModel getOd() {
		return od;
	}

	public void setOd(AbstractDocLineModel od) {
		this.od = od;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public SpringLayout getSl_panel() {
		return sl_panel;
	}

	public void setSl_panel(SpringLayout sl_panel) {
		this.sl_panel = sl_panel;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JScrollPane getScp() {
		return scp;
	}

	public void setScp(JScrollPane scp) {
		this.scp = scp;
	}

	public SpringLayout getSl_panel_1() {
		return sl_panel_1;
	}

	public void setSl_panel_1(SpringLayout sl_panel_1) {
		this.sl_panel_1 = sl_panel_1;
	}

	public JButton getBtn_importdb() {
		return btn_importdb;
	}

	public void setBtn_importdb(JButton btn_importdb) {
		this.btn_importdb = btn_importdb;
	}


}
