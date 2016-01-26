package erp.ws.sbo.client.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.userauther;
import erp.ws.sbo.client.swing.model.userautherId;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.UAuther.UAutherView;
import erp.ws.sbo.utils.DbUtils;


public class UAuthersController implements ActionListener, ListSelectionListener {

	private UAutherView v;
	private String hql;
	//这个变量 会 导致 出错
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();
	public UAuthersController(UAutherView v) {
		// TODO Auto-generated constructor stub
		this.setV(v);
	}
	public UAutherView getV() {
		return v;
	}
	public void setV(UAutherView v) {
		this.v = v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 hql = "insert into dbo.[@userauther](usercode,autherid,enable)"+ 
				"select a.user_code,b.code,0 from ousr a , dbo.[@auther] b "+
				"where convert(nvarchar(20),a.user_code)+','+convert(nvarchar(20),b.code) "+
				"not in  (select convert(nvarchar(20),usercode)+','+convert(nvarchar(20),autherid) from dbo.[@userauther])";
		 dbu.exeSql(hql);
		 
		if(e.getSource()==v.getJb_confirm()&&appMain.oCompany.getUserName().equals("manager"))
		{			
			userauther uau=new userauther();
			userautherId uauid=new userautherId();
			for(int i=0;i<v.getOd().getRowCount();i++)
			{
				if(v.getOd().getValuethrheader(i, "用户代码")==null||v.getOd().getValuethrheader(i, "用户代码").toString()=="")
				{
					continue;
				}				
				uau=new userauther();
				uauid=new userautherId();
				uauid.setAutherid(v.getOd().getValuethrheader(i, "权限代码").toString());
				uauid.setUsercode(v.getOd().getValuethrheader(i, "用户代码").toString());		
				uau=appMain.ua.queryByDocId(uauid);
				uau.setEnable(Boolean.valueOf(((ComboBoxItem)(v.getOd().getValuethrheader(i, "授权"))).getValue().toString()));				
				appMain.ua.update(uau);
			}
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		hql="select 0,a.usercode,b.u_name,a.autherid,c.name,a.enable from [@userauther] a " +
			"inner join ousr b on a.usercode=b.user_code " +
			"inner join [@auther] c on a.autherid=c.code " +
			"where a.usercode='"+v.getOd1().getValueAt(arg0.getFirstIndex(), 1).toString()+"'";	
		v.getOd().updatetable(hql, 0);
		ComboBoxItem  Cbi1=new ComboBoxItem("false","无权");
		ComboBoxItem  Cbi2=new ComboBoxItem("true","授权");
		for(int i=0;i<v.getOd().getRowCount();i++)
		{
			if(v.getOd().getValuethrheader(i, "授权").toString().equals("true")||v.getOd().getValuethrheader(i, "授权").toString().equals("1"))
			{
			   v.getOd().setValuethrheader(Cbi2,i, "授权");
			}
			else{
			   v.getOd().setValuethrheader(Cbi1,i, "授权");
			}
		}
		
	}

}
