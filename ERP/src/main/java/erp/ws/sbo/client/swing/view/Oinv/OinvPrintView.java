package erp.ws.sbo.client.swing.view.Oinv;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import erp.ws.sbo.client.swing.controller.OinvsController;

public class OinvPrintView extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885438772757826711L;
	private SpringLayout lay = new SpringLayout();
	JLabel lable_print = new JLabel("选择打印格式");	
	JComboBox comb_print=new JComboBox();
	public JLabel getLable_print() {
		return lable_print;
	}
	public void setLable_print(JLabel lable_print) {
		this.lable_print = lable_print;
	}
	public JComboBox getComb_print() {
		return comb_print;
	}
	public void setComb_print(JComboBox comb_print) {
		this.comb_print = comb_print;
	}
	public JButton getJb_confirm() {
		return jb_confirm;
	}
	public void setJb_confirm(JButton jb_confirm) {
		this.jb_confirm = jb_confirm;
	}
	JButton jb_confirm=new JButton("确定");
	
	private OinvsController oc;
   public OinvPrintView(OinvsController oc)
   {super("打印",false,true,false,false);
	   this.setOc(oc);
	   
		initComponents();
   }
    private void initComponents() {
	    // TODO Auto-generated method stub
    	  this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);	
    	   this.setSize(300,150);
    		Container con = getContentPane();		
    		con.setLayout(lay);
    		comb_print.setPrototypeDisplayValue("xxxxxxxxxxxxxx");
    		 lay.putConstraint(SpringLayout.WEST, lable_print, 30,SpringLayout.WEST, con);
    		 lay.putConstraint(SpringLayout.NORTH, lable_print, 30, SpringLayout.NORTH, con);
    		 lay.putConstraint(SpringLayout.WEST, comb_print, 120,SpringLayout.WEST, con);
    		 lay.putConstraint(SpringLayout.NORTH, comb_print, 30, SpringLayout.NORTH, con);
    		 lay.putConstraint(SpringLayout.WEST, jb_confirm, 100,SpringLayout.WEST, con);
    		 lay.putConstraint(SpringLayout.NORTH, jb_confirm, 70, SpringLayout.NORTH, con);
    		 comb_print.addItem("草稿");
    		 comb_print.addItem("交货");
    		 comb_print.addItem("按追加号打印草稿");
    		 comb_print.addItem("按追加号打印交货");
    		 comb_print.addItem("汇总单副表草稿");
    		 comb_print.addItem("汇总单副表交货");
    		 comb_print.addItem("汇总单副表交货2");
    		 con.add(lable_print);
    		 con.add(comb_print);
    		 con.add(jb_confirm);  		
    		 oc.getV().setOpv(this);
    		 jb_confirm.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub					
					oc.getDocf().getAbsDoc().print(oc.getV());
				
				}});
    }
    
	public OinvsController getOc() {
		return oc;
	}
	public void setOc(OinvsController oc) {
		this.oc = oc;
	}
}
