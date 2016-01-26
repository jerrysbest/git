package erp.ws.sbo.client.swing.view.UAuther;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import erp.ws.sbo.client.swing.controller.UAuthersController;
import erp.ws.sbo.client.swing.model.ColUAutherDocLine;
import erp.ws.sbo.client.swing.model.ColUAutherDocTitle;
import erp.ws.sbo.client.swing.model.UAutherDocLine;
import erp.ws.sbo.client.swing.model.UAutherDocTitle;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.UAutherDocLineModel;
import erp.ws.sbo.client.swing.tablemodel.UAutherDocTitleModel;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.utils.DbUtils;

public class UAutherView extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6160201571597845043L;
	private UAuthersController uc=new UAuthersController(this);
    private String hql;
    private JButton jb_confirm=new JButton("提交");   
    private ColUAutherDocLine cl=ColUAutherDocLine.getCLine();
    private UAutherDocLine dl=new UAutherDocLine();
	private DbUtils<ColUAutherDocLine,UAutherDocLine> dbu=new DbUtils<ColUAutherDocLine,UAutherDocLine>();
    private AbstractDocLineModel<ColUAutherDocLine,UAutherDocLine> od;
    private JMyTable jt;
    private JScrollPane scp;
    private ColUAutherDocTitle cl1=ColUAutherDocTitle.getCTitle();
    private UAutherDocTitle dl1=new UAutherDocTitle();
	private DbUtils<ColUAutherDocTitle,UAutherDocTitle> dbu1=new DbUtils<ColUAutherDocTitle,UAutherDocTitle>();
    private AbstractDocTitleModel<ColUAutherDocTitle,UAutherDocTitle> od1;
    private JMyTable jt1;
    private JScrollPane scp1;
    private JPanel  pane1=new JPanel();//base information
    private Container pane=this.getContentPane();
    private Object[] obj;
    private List<Object[]> list=new ArrayList<Object[]>(); 
    public  UAutherView()
	{
		super("设置权限",false,true,false,false);
		initComponents();
	}
	private void initComponents() {
		// TODO Auto-generated method stub
		this.setName("UAUTHER");
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		hql="select 0,'','','','','' from Inv1 p where 1=2";	
	    od=new UAutherDocLineModel(cl,dl,dbu,hql);		   	   
		od.setDocLineStatus(docLineStatus.query);
		od.setGridStatus(docLineStatus.add);
		
		jt=new JMyTable(od);		    
	    jt.setAutoCreateRowSorter(true);	
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt.setPreferredScrollableViewportSize(new Dimension(620, 200));
		jt.setFillsViewportHeight(true);
		jt.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);

		 obj=new Object[2];
	     obj[0]="false";obj[1]="无权";
	     list.add(obj);
	     obj=new Object[2];
	     obj[0]="true";obj[1]="授权";
	     list.add(obj);
	     
	    od.setUpStaSportColumn(jt, jt.getColumnModel().getColumn(od.getcolumnindex("授权")), list);
	    
		scp=new JScrollPane(jt);
		hql="select 0,user_code,u_name from ousr";	
	    od1=new UAutherDocTitleModel(cl1,dl1,dbu1,hql);		   	   
		od1.setDocTitleStatus(docTitleStatus.query);
		
		jt1=new JMyTable(od1);		    
	    jt1.setAutoCreateRowSorter(true);	
		jt1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		jt1.setPreferredScrollableViewportSize(new Dimension(620, 100));
		jt1.setFillsViewportHeight(true);
		jt1.getColumnModel().getColumn(od.getcolumnindex("序号")).setPreferredWidth(30);

		
		jb_confirm.addActionListener(uc);
		jt1.getSelectionModel().addListSelectionListener(uc);
		scp1=new JScrollPane(jt1);
		    
		
	    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));				    
	    pane1.setLayout(new BoxLayout(pane1, BoxLayout.Y_AXIS));
	    pane1.add(jb_confirm);
	    
	    pane.add(scp1);
	    pane.add(scp);	    
	   
	    pane.add(pane1);
	    this.setVisible(true);
		this.setResizable(true);	
	}
	public JButton getJb_confirm() {
		return jb_confirm;
	}
	public void setJb_confirm(JButton jb_confirm) {
		this.jb_confirm = jb_confirm;
	}
	public AbstractDocLineModel<ColUAutherDocLine, UAutherDocLine> getOd() {
		return od;
	}
	public void setOd(AbstractDocLineModel<ColUAutherDocLine, UAutherDocLine> od) {
		this.od = od;
	}
	public JMyTable getJt() {
		return jt;
	}
	public void setJt(JMyTable jt) {
		this.jt = jt;
	}
	public AbstractDocTitleModel<ColUAutherDocTitle, UAutherDocTitle> getOd1() {
		return od1;
	}
	public void setOd1(AbstractDocTitleModel<ColUAutherDocTitle, UAutherDocTitle> od1) {
		this.od1 = od1;
	}
	public JMyTable getJt1() {
		return jt1;
	}
	public void setJt1(JMyTable jt1) {
		this.jt1 = jt1;
	}
	public UAuthersController getUc() {
		return uc;
	}
	public void setUc(UAuthersController uc) {
		this.uc = uc;
	}
}
