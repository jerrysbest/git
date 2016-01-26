package erp.ws.sbo.client.swing.view.LeftMenu;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import erp.ws.sbo.client.swing.controller.LeftMenusController;
import erp.ws.sbo.client.swing.store.Tree1s;
import erp.ws.sbo.client.swing.util.tree.ProcessHierarchy;
public class LeftMenuView extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	
  public LeftMenuView()
	{		
		initComponents();
	}
  private Tree1s t1s=Tree1s.getHierarchy();
  private ProcessHierarchy p=new ProcessHierarchy();
  private DefaultMutableTreeNode root = p.processHierarchy(t1s.getValue());
  private JTree jtree1=new JTree(root);
  private JTree jtree2=new JTree();
  private JTree jtree3=new JTree();
  private JPanel  pane1=new JPanel();
  private JPanel  pane2=new JPanel();
  private JPanel  pane3=new JPanel();
  private JTabbedPane  tpane=new JTabbedPane(); 
  private Container con = this.getContentPane();
  public void initComponents()
  {  LeftMenusController c = new LeftMenusController(this);
     this.setName("LMEU");
	//为jtree1增加单击事件	  
	  jtree1.addMouseListener(c);
	  this.setTitle("菜单");
	  con.add(new JScrollPane(jtree1), BorderLayout.CENTER);
	  pane1.add(jtree1);	  
	  pane2.add(jtree2);
	  pane3.add(jtree3);
	  tpane.add("模块",pane1);
	  tpane.add(pane2);
	  tpane.add(pane3);
	  con.add(tpane,BorderLayout.CENTER);
	  setSize(150, 750);  
	  this.setVisible(true);
	  this.setResizable(true);		  
  }

}
