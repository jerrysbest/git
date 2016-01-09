package erp.ws.sbo.client.swing.view.DocMenu;



import javax.swing.JButton;
import javax.swing.JToolBar;


public class DocMenuView extends JToolBar{
 /**
	 * 
	 */
private static final long serialVersionUID = 1L;
private JButton jButtonadd;
private JButton jButtonaddplus;
private JButton jButtonremend;
private JButton jButtonsave;
private JButton jButtonprint;
private JButton jButtonfirst;
private JButton jButtonnext;
private JButton jButtonprev;
private JButton jButtonlast;
private JButton jButtonquery;
private JButton jButtoncopy;
private JButton jButtoncancel;
private JButton jButtonclose;
private JButton jButtoncpsource;
private JButton jButtonctarget;
private JButton jButtonaddrow;
private JButton jButtondelrow;
private JButton jButtondel;
private JButton jButtonapplied;
private JButton jButtonSN;

private static DocMenuView dmv;
private DocMenuView()
 {
   	
	initComponents();
 }
public static DocMenuView getdmv()
{
	if(dmv==null)
	{
		dmv=new DocMenuView();
	}
	return dmv;
	
}

public  enum  docType{
	ordr(17),oinv(13),orin(14),orct(24),ovpm(46),owor(99),oign(59),oige(60),owtr(67),qrre(100);
	private int value;
	private docType(int value){
		this.value=value;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value){
		this.value=value;
	}
	
};


    public docType dt;
  

//private javax.swing.JButtonItem jButtonitem;
	 public void initComponents()
	 {	
	    jButtonadd = new JButton();	  
	    jButtonaddplus = new JButton();	   
		jButtonremend = new JButton();		
		jButtoncpsource = new JButton();		
	    jButtonctarget = new JButton();	   
	    jButtonsave = new JButton();	   
		jButtonquery = new JButton();
		jButtonaddrow = new JButton();		
	    jButtondelrow = new JButton();
		jButtoncopy = new JButton();		
		jButtonfirst = new JButton();		
		jButtonprev = new JButton();		
		jButtonnext = new JButton();		
		jButtonlast = new JButton();		
	    jButtonprint = new JButton();	    
		jButtonclose = new JButton();		
	    jButtoncancel = new JButton();
	    setjButtondel(new JButton());
	    jButtonapplied=new JButton(); 
	    jButtonSN=new JButton(); 
	    jButtonSN.setText("SN");
	    
		this.add(jButtonSN);
		jButtonadd.setText("新增");
		this.add(jButtonadd);
		
		jButtonaddplus.setText("追加");
		this.add(jButtonaddplus);
				
		jButtoncpsource.setText("复制来源单据");
		this.add(jButtoncpsource);
		
		jButtonctarget.setText("生成目标单据");
		this.add(jButtonctarget);
		
		jButtonremend.setText("修改");
		this.add(jButtonremend);
		
		jButtonsave.setText("保存");
		this.add(jButtonsave);
		
		jButtonquery.setText("查询");
		this.add(jButtonquery); 
		
		jButtonaddrow.setText("增行");
		this.add(jButtonaddrow); 
		
		jButtondelrow.setText("删行");
		this.add(jButtondelrow); 
		
		jButtoncopy.setText("复制");
		this.add(jButtoncopy);
		
		jButtonfirst.setText("第一个");
		this.add(jButtonfirst);
		
		jButtonprev.setText("上一个");
		this.add(jButtonprev);
		
		jButtonnext.setText("下一个");
		this.add(jButtonnext);
		
		jButtonlast.setText("末一个");
		this.add(jButtonlast);
		
		
		jButtonprint.setText("打印");
		this.add(jButtonprint);
		
		jButtondel.setText("删除");
		this.add(jButtondel);
	
	/*	jButtonitem= new JButtonItem("关闭", KeyEvent.VK_N);  
		jButtonprint.add(jButtonitem);
		jButtonitem= new JButtonItem("锁定屏幕", KeyEvent.VK_N); 
		jButtonprint.add(jButtonitem);*/
		
		jButtonclose.setText("关闭");
		this.add(jButtonclose);
		
		jButtoncancel.setText("取消");
		this.add(jButtoncancel);
				
		jButtonapplied.setText("收款");
		this.add(jButtonapplied);
		
		
		
		setVisible(true);	
	
 	}

	public void setDt(docType dt) {
		this.dt = dt;
	}

	public JButton getjButtonadd() {
		return jButtonadd;
	}

	public void setjButtonadd(JButton jButtonadd) {
		this.jButtonadd = jButtonadd;
	}

	public JButton getjButtonaddplus() {
		return jButtonaddplus;
	}
	public void setjButtonaddplus(JButton jButtonaddplus) {
		this.jButtonaddplus = jButtonaddplus;
	}
	public JButton getjButtonremend() {
		return jButtonremend;
	}

	public void setjButtonremend(JButton jButtonremend) {
		this.jButtonremend = jButtonremend;
	}

	public JButton getjButtonsave() {
		return jButtonsave;
	}

	public void setjButtonsave(JButton jButtonsave) {
		this.jButtonsave = jButtonsave;
	}

	public JButton getjButtonprint() {
		return jButtonprint;
	}

	public void setjButtonprint(JButton jButtonprint) {
		this.jButtonprint = jButtonprint;
	}

	public JButton getjButtonfirst() {
		return jButtonfirst;
	}

	public void setjButtonfirst(JButton jButtonfirst) {
		this.jButtonfirst = jButtonfirst;
	}

	public JButton getjButtonnext() {
		return jButtonnext;
	}

	public void setjButtonnext(JButton jButtonnext) {
		this.jButtonnext = jButtonnext;
	}

	public JButton getjButtonprev() {
		return jButtonprev;
	}

	public void setjButtonprev(JButton jButtonprev) {
		this.jButtonprev = jButtonprev;
	}

	public JButton getjButtonlast() {
		return jButtonlast;
	}

	public void setjButtonlast(JButton jButtonlast) {
		this.jButtonlast = jButtonlast;
	}

	public JButton getjButtonquery() {
		return jButtonquery;
	}

	public void setjButtonquery(JButton jButtonquery) {
		this.jButtonquery = jButtonquery;
	}

	public JButton getjButtoncopy() {
		return jButtoncopy;
	}

	public void setjButtoncopy(JButton jButtoncopy) {
		this.jButtoncopy = jButtoncopy;
	}

	public JButton getjButtoncancel() {
		return jButtoncancel;
	}

	public void setjButtoncancel(JButton jButtoncancel) {
		this.jButtoncancel = jButtoncancel;
	}

	public JButton getjButtonclose() {
		return jButtonclose;
	}

	public void setjButtonclose(JButton jButtonclose) {
		this.jButtonclose = jButtonclose;
	}

	public JButton getjButtoncpsource() {
		return jButtoncpsource;
	}

	public void setjButtoncpsource(JButton jButtoncpsource) {
		this.jButtoncpsource = jButtoncpsource;
	}

	public JButton getjButtonctarget() {
		return jButtonctarget;
	}

	public void setjButtonctarget(JButton jButtonctarget) {
		this.jButtonctarget = jButtonctarget;
	}
	/*
	 * docstatus of adding,remending,quering,plus
	 */
	public void setadd()
	{
		this.jButtonadd.setEnabled(false);

		this.jButtoncpsource.setEnabled(true);
		this.jButtonctarget.setEnabled(false);
		this.jButtonremend.setEnabled(false);
		this.jButtonsave.setEnabled(true);
		this.jButtonquery.setEnabled(true);
		this.jButtoncopy.setEnabled(false);
		this.jButtonfirst.setEnabled(true);
		this.jButtonprev.setEnabled(true);
		this.jButtonnext.setEnabled(true);
		this.jButtonlast.setEnabled(true);
		this.jButtonprint.setEnabled(true);
		this.jButtonclose.setEnabled(false);
		this.jButtoncancel.setEnabled(true);	
		this.jButtonaddrow.setEnabled(true);		
	    this.jButtondelrow.setEnabled(true);
	    this.jButtondel.setEnabled(false);
	    this.jButtonaddplus.setEnabled(false);
	    this.jButtonapplied.setEnabled(true);	  
	    this.jButtonSN.setEnabled(true);	  
	}

	public void setremend()
	{
		this.jButtonadd.setEnabled(false);
		this.jButtoncpsource.setEnabled(true);
		this.jButtonctarget.setEnabled(false);
		this.jButtonremend.setEnabled(false);
		this.jButtonsave.setEnabled(true);
		this.jButtonquery.setEnabled(true);
		this.jButtoncopy.setEnabled(false);
		this.jButtonfirst.setEnabled(true);
		this.jButtonprev.setEnabled(true);
		this.jButtonnext.setEnabled(true);
		this.jButtonlast.setEnabled(true);
		this.jButtonprint.setEnabled(false);
		this.jButtonclose.setEnabled(false);
		this.jButtoncancel.setEnabled(true);	
		this.jButtonaddrow.setEnabled(true);		
	    this.jButtondelrow.setEnabled(true);
	    this.jButtondel.setEnabled(false);
	    this.jButtonaddplus.setEnabled(false);
	    this.jButtonapplied.setEnabled(false);	 
	    this.jButtonSN.setEnabled(true);
	}
	public void setquery()
	{
		this.jButtonadd.setEnabled(true);
		this.jButtoncpsource.setEnabled(false);
		this.jButtonctarget.setEnabled(true);
		this.jButtonremend.setEnabled(true);
		this.jButtonsave.setEnabled(false);
		this.jButtonquery.setEnabled(true);
		this.jButtoncopy.setEnabled(true);
		this.jButtonfirst.setEnabled(true);
		this.jButtonprev.setEnabled(true);
		this.jButtonnext.setEnabled(true);
		this.jButtonlast.setEnabled(true);
		this.jButtonprint.setEnabled(true);
		this.jButtonclose.setEnabled(true);
		this.jButtoncancel.setEnabled(false);	
		this.jButtonaddrow.setEnabled(false);		
	    this.jButtondelrow.setEnabled(false);
	    this.jButtondel.setEnabled(true);
	    this.jButtonaddplus.setEnabled(true);
	    this.jButtonapplied.setEnabled(true);	
	    this.jButtonSN.setEnabled(true);
	}
	
	public void setload()
	{
		this.jButtonadd.setEnabled(true);
		this.jButtoncpsource.setEnabled(false);
		this.jButtonctarget.setEnabled(false);
		this.jButtonremend.setEnabled(false);
		this.jButtonsave.setEnabled(false);
		this.jButtonquery.setEnabled(true);
		this.jButtoncopy.setEnabled(false);
		this.jButtonfirst.setEnabled(true);
		this.jButtonprev.setEnabled(true);
		this.jButtonnext.setEnabled(true);
		this.jButtonlast.setEnabled(true);
		this.jButtonprint.setEnabled(false);
		this.jButtonclose.setEnabled(false);
		this.jButtoncancel.setEnabled(false);	
		this.jButtonaddrow.setEnabled(false);		
	    this.jButtondelrow.setEnabled(false);
	    this.jButtondel.setEnabled(false);
	    this.jButtonaddplus.setEnabled(false);
	    this.jButtonapplied.setEnabled(false);	
	    this.jButtonSN.setEnabled(false);
	}
	
	public void setdeactive()
	{
		this.jButtonadd.setEnabled(false);
		this.jButtoncpsource.setEnabled(false);
		this.jButtonctarget.setEnabled(false);
		this.jButtonremend.setEnabled(false);
		this.jButtonsave.setEnabled(false);
		this.jButtonquery.setEnabled(false);
		this.jButtoncopy.setEnabled(false);
		this.jButtonfirst.setEnabled(false);
		this.jButtonprev.setEnabled(false);
		this.jButtonnext.setEnabled(false);
		this.jButtonlast.setEnabled(false);
		this.jButtonprint.setEnabled(false);
		this.jButtonclose.setEnabled(false);
		this.jButtoncancel.setEnabled(false);	
		this.jButtonaddrow.setEnabled(false);		
	    this.jButtondelrow.setEnabled(false);
	    this.jButtonSN.setEnabled(false);
	}
	public JButton getjButtonaddrow() {
		return jButtonaddrow;
	}
	public void setjButtonaddrow(JButton jButtonaddrow) {
		this.jButtonaddrow = jButtonaddrow;
	}
	public JButton getjButtondelrow() {
		return jButtondelrow;
	}
	public void setjButtondelrow(JButton jButtondelrow) {
		this.jButtondelrow = jButtondelrow;
	}
	public JButton getjButtondel() {
		return jButtondel;
	}
	public void setjButtondel(JButton jButtondel) {
		this.jButtondel = jButtondel;
	}
	public JButton getjButtonapplied() {
		return jButtonapplied;
	}
	public void setjButtonapplied(JButton jButtonapplied) {
		this.jButtonapplied = jButtonapplied;
	}
	public JButton getjButtonSN() {
		return jButtonSN;
	}
	public void setjButtonSN(JButton jButtonSN) {
		this.jButtonSN = jButtonSN;
	}
}
