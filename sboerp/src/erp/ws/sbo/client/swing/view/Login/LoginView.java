package erp.ws.sbo.client.swing.view.Login;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import erp.ws.sbo.client.swing.controller.LoginsController;
import erp.ws.sbo.client.swing.util.general.JUComboBox;
public class LoginView extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginView()
	{		
		super("登陆QS-ERP",false,false,false,false);
		initComponents();
	}
	private LoginsController c = new LoginsController(this);
	private SpringLayout lay = new SpringLayout();
	String hql="select isnull(u_mno,'00'),whsname from owhs where u_mno is not null and u_mno<>''";
	JLabel lable_lserver = new JLabel("许可服务器");	
	JLabel lable_dserver = new JLabel("数据服务器");
	JLabel lable_user = new JLabel("用户名");
	JLabel lable_pas = new JLabel("密码");
	JLabel lable_user1 = new JLabel("用户号");
	JLabel lable_pas1 = new JLabel("密码1");
	JLabel lable_MNo = new JLabel("机号");
	JLabel lable_comp = new JLabel("公司");
	JButton btn_login = new JButton("登陆");
	JTextField tf_lserver=new JTextField();	
	JTextField tf_dserver=new JTextField();
	JTextField tf_user=new JTextField();
	JPasswordField tf_pas=new JPasswordField();
	JTextField tf_user1=new JTextField();
	JPasswordField tf_pas1=new JPasswordField();
	JComboBox tf_comp=new JComboBox();
	JUComboBox MNo_comp=new JUComboBox(hql);
    public void initComponents()
    {   	   	
    	this.tf_dserver.setName("tf_dserver");
    	this.tf_lserver.setName("tf_lserver");
    	this.tf_user.setName("tf_user");
    	this.tf_pas.setName("tf_pas");
    	this.tf_comp.setName("tf_comp");
    	this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);	 
        this.setSize(400,430);
        this.setName("LOGV");
		Container con = getContentPane();		
		con.setLayout(lay);
		
		 lay.putConstraint(SpringLayout.WEST, lable_lserver, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_lserver, 30, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_lserver, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_lserver, 30, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, lable_dserver, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_dserver, 60, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_dserver, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_dserver, 60, SpringLayout.NORTH, con);	
		 lay.putConstraint(SpringLayout.WEST, lable_user, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_user, 90, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_user, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_user, 90, SpringLayout.NORTH, con);		 
		 lay.putConstraint(SpringLayout.WEST, lable_pas, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_pas, 120, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_pas, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_pas, 120, SpringLayout.NORTH, con);
		 
		 lay.putConstraint(SpringLayout.WEST, lable_user1, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_user1, 150, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_user1, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_user1, 150, SpringLayout.NORTH, con);		 
		 lay.putConstraint(SpringLayout.WEST, lable_pas1, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_pas1, 180, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_pas1, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_pas1, 180, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, lable_MNo, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_MNo, 210, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, MNo_comp, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, MNo_comp, 210, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, lable_comp, 60,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, lable_comp, 240, SpringLayout.NORTH, con);
		 lay.putConstraint(SpringLayout.WEST, tf_comp, 130,SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, tf_comp, 240, SpringLayout.NORTH, con);
		 
		
		 
		 lay.putConstraint(SpringLayout.WEST, btn_login, 180, SpringLayout.WEST, con);
		 lay.putConstraint(SpringLayout.NORTH, btn_login, 270, SpringLayout.NORTH,con);
       
		 tf_user.setColumns(15);
		 tf_pas.setColumns(15);
		 tf_user1.setColumns(15);
		 tf_pas1.setColumns(15);
		// tf_comp.s;
		 tf_lserver.setColumns(15);
		 tf_dserver.setColumns(15);
		 con.add(lable_lserver);
         con.add(lable_dserver);
		 con.add(lable_user);
         con.add(lable_pas);
         con.add(lable_user1);
         con.add(lable_pas1);
         con.add(lable_comp);
         con.add(lable_MNo);
         con.add(tf_user);
         con.add(tf_pas);
         con.add(tf_user1);
         con.add(tf_pas1);
         con.add(tf_comp);
         con.add(MNo_comp);
         con.add(tf_lserver);
         con.add(tf_dserver);
         con.add(btn_login);
        
         
         btn_login.addActionListener(c);       
         c.initview();
		 this.setVisible(true);
	  
    }
    public JTextField getLserver()
    {
       return tf_lserver;
    }
    public void setLserver(JTextField tf_lserver)
    {
    	this.tf_lserver=tf_lserver;
    }
    public JTextField getDserver()
    {
       return tf_dserver;
    }
    public void setDserver(JTextField tf_dserver)
    {
    	this.tf_dserver=tf_dserver;
    }
    public JTextField getUser()
    {
       return tf_user;
    }
    public void setUser(JTextField tf_user)
    {
    	this.tf_user=tf_user;
    }
    public JPasswordField getPas()
    {
       return tf_pas;
    }
    public void setLserver(JPasswordField tf_pas)
    {
    	this.tf_pas=tf_pas;
    }
    public JComboBox getComp()
    {
       return tf_comp;
    }
    public void setComp(JComboBox tf_comp)
    {
    	this.tf_comp=tf_comp;
    }
	public JTextField getTf_user1() {
		return tf_user1;
	}
	public void setTf_user1(JTextField tf_user1) {
		this.tf_user1 = tf_user1;
	}
	public JPasswordField getTf_pas1() {
		return tf_pas1;
	}
	public void setTf_pas1(JPasswordField tf_pas1) {
		this.tf_pas1 = tf_pas1;
	}
	public JUComboBox getMNo_comp() {
		return MNo_comp;
	}
	public void setMNo_comp(JUComboBox mNo_comp) {
		MNo_comp = mNo_comp;
	}
}
