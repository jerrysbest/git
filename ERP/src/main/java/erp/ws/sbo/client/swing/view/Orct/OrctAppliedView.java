package erp.ws.sbo.client.swing.view.Orct;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import erp.ws.sbo.client.swing.controller.OrctAppliedsController;
import erp.ws.sbo.client.swing.model.ColAppliedOrctDocLine;
import erp.ws.sbo.client.swing.model.appliedOrctDocLine;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.appliedOrctDocLineModel;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.utils.DbUtils;

public class OrctAppliedView  extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1110573782878810125L;
	private OrctAppliedsController oac=new OrctAppliedsController(this);
	private String hql,hql1;
	private Object[][] ob;
    private OrctView v;
    private JButton jb_confirm=new JButton("提交");   
    private JTextArea jtf_sum=new JTextArea();
    private ColAppliedOrctDocLine cl=ColAppliedOrctDocLine.getCLine();
    private appliedOrctDocLine dl=new appliedOrctDocLine();
	private DbUtils<ColAppliedOrctDocLine,appliedOrctDocLine> dbu=new DbUtils<ColAppliedOrctDocLine,appliedOrctDocLine>();
    private AbstractDocLineModel<ColAppliedOrctDocLine,appliedOrctDocLine> od;
    private JMyTable jt;
    private JScrollPane scp;
    private JScrollPane scp1 ;
    private JPanel  pane1=new JPanel();//base information
    private Container pane=this.getContentPane();
	public OrctAppliedView(OrctView v)
	{
		super("输入收款金额",false,true,true,false);
		this.setV(v);
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		this.setName("ORCTA");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		jtf_sum.setLineWrap(true);
		hql="select '','','','','','' from Inv1 p where 1=2";	
	    od=new appliedOrctDocLineModel(cl,dl,dbu,hql);		   	   
		od.setDocLineStatus(docLineStatus.add);
		od.setGridStatus(docLineStatus.add);
		String[] s={"伙伴代码","伙伴名称"};		
	    ob = v.getOd().Filterdrecords(true, s);
	    for(int i=0;i<ob.length;i++)
	    {
	    	od.setValueAt(ob[i][0].toString(), i, od.getcolumnindex("伙伴代码"));
	    	od.setValueAt(ob[i][1].toString(), i, od.getcolumnindex("伙伴名称"));
	    	od.setValueAt("0", i, od.getcolumnindex("科目收款金额"));
	    }
	  
	    jtf_sum.setColumns(50);
	    jtf_sum.setRows(10);
	    jt=new JMyTable(od);		    
	    jt.setAutoCreateRowSorter(true);	
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt.setPreferredScrollableViewportSize(new Dimension(520, 300));
		jt.setFillsViewportHeight(true);
		jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);
		jt.getColumnModel().getColumn(od.getcolumnindex("伙伴代码")).setPreferredWidth(90);
		jt.getColumnModel().getColumn(od.getcolumnindex("伙伴名称")).setPreferredWidth(140);
		jt.getColumnModel().getColumn(od.getcolumnindex("科目代码")).setPreferredWidth(140);
		jt.getColumnModel().getColumn(od.getcolumnindex("科目名称")).setPreferredWidth(100);
	
	    hql="SELECT AcctCode+','+AcctName FROM OACT WHERE Frozen='N' AND Levels>'3' AND RealAcct='N' " +
    		"AND Postable='Y' AND AcctCode in (select U_aCode from [@PAYUDT] where name like '收款科目%') " +
    		/*"(AcctCode='112101' OR AcctCode='100101' OR AcctCode='10020401' " +
    		"OR AcctCode='10020402' OR AcctCode='10020403' OR  AcctCode='10020410' " +
    		"OR AcctCode='10020420' OR AcctCode='10020430' OR AcctCode='10020440' OR AcctCode='10020450' " +
    		"OR AcctCode='660110' OR AcctCode='660111' OR AcctCode='66010911' OR AcctCode='10020451' " +
    		"OR AcctCode='66010912') " +*/
    		" and  (AcctCode like :str1 or AcctName like :str2)";
	    hql1="SELECT AcctCode FROM OACT WHERE Frozen='N' AND Levels>'3' AND RealAcct='N' " +
    		"AND Postable='Y' AND AcctCode in (select U_aCode from [@PAYUDT] where name like '收款科目%') " +
    		/*"(AcctCode='112101' OR AcctCode='100101' OR AcctCode='10020401' OR AcctCode='10020450' " +
    		"OR AcctCode='10020402' OR AcctCode='10020403' OR  AcctCode='10020410' OR AcctCode='10020451' " +
    		"OR AcctCode='10020420' OR AcctCode='10020430' OR AcctCode='10020440' " +
    		"OR AcctCode='660110' OR AcctCode='660111' OR AcctCode='66010911' " +
    		"OR AcctCode='66010912') " +*/
    		" and  AcctCode= :str1 ";
		od.setUpSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("科目代码")), hql,hql1);
						
	    scp=new JScrollPane(jt);
	    scp1=new JScrollPane(jtf_sum);
	    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));				    
	    pane1.setLayout(new BoxLayout(pane1, BoxLayout.Y_AXIS));
	    pane1.add(scp1);
	    pane1.add(jb_confirm);
	    
	    pane.add(scp);
	    pane.add(pane1);
	    
	    jb_confirm.addActionListener(oac);
	    jt.addMouseListener(oac);
	    jt.addKeyListener(oac);
	    od.addTableModelListener(oac);
	}


	public OrctView getV() {
		return v;
	}


	public void setV(OrctView v) {
		this.v = v;
	}

	public OrctAppliedsController getOac() {
		return oac;
	}

	public void setOac(OrctAppliedsController oac) {
		this.oac = oac;
	}

	public JTextArea getJtf_sum() {
		return jtf_sum;
	}

	public void setJtf_sum(JTextArea jtf_sum) {
		this.jtf_sum = jtf_sum;
	}

	public AbstractDocLineModel<ColAppliedOrctDocLine, appliedOrctDocLine> getOd() {
		return od;
	}

	public void setOd(
			AbstractDocLineModel<ColAppliedOrctDocLine, appliedOrctDocLine> od) {
		this.od = od;
	}

	public JMyTable getJt() {
		return jt;
	}

	public void setJt(JMyTable jt) {
		this.jt = jt;
	}
}
