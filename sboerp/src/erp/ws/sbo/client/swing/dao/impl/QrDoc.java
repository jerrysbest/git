package erp.ws.sbo.client.swing.dao.impl;

import java.awt.Desktop;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JOptionPane;
import javax.swing.JTextField;
import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.dao.IDoc;
import erp.ws.sbo.client.swing.model.ColDocTitle;
import erp.ws.sbo.client.swing.model.DocTitle;
import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.model.UQrm;
import erp.ws.sbo.client.swing.model.URm1;
import erp.ws.sbo.client.swing.model.URm1Id;
import erp.ws.sbo.client.swing.model.User;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocLineModel.docLineStatus;
import erp.ws.sbo.client.swing.tablemodel.AbstractDocTitleModel.docTitleStatus;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.view.DocMenu.DocMenuView;
import erp.ws.sbo.client.swing.view.Qr.QrView;
import erp.ws.sbo.utils.DbUtils;



public class QrDoc implements IDoc<QrView>{
	
	private DocMenuView dmv=DocMenuView.getdmv();
	private QrView v;
	private String hql;
	private Object[][] ob,ob1;
	private UQrm s=new UQrm();
	private URm1 sd=new URm1();
	private URm1Id sid=new URm1Id();
	private DbUtils<?,?> dbu=new DbUtils<ColDocTitle,DocTitle>();	


	public QrDoc(){
	
	}
	
	@Override
	public void create(QrView v) {
		// TODO Auto-generated method stub	
		this.v=v;
		//��֤
	    if(v.getOd().verification()==false)
	    {
	    	return;
	    }
	 
	    if(v.getOd1().ds.getCnValue().equals("�޸�"))
		{
	      s=appMain.QD.queryByDocId(Integer.valueOf(v.getTxt_docn().getText()));
		
		}
	    else if(v.getOd1().ds.getCnValue().equals("����"))
		{
	        s=new UQrm();
	        s.setDocEntry(appMain.QD.getMaxDocEntry());
		    s.setDocNum(appMain.QD.getMaxDocEntry());
		}
	    else{}
	
	
	    s.setDocDate((Date) v.getTxt_date().getValue());
	    s.setCanceled("N");
	    s.setHandwrtten("N");
	    s.setCreateDate(new Date());
	    s.setUpdateDate(new Date());
	    s.setInstance((short)112);
	    s.setLogInst(1);
	    s.setObject(112);
	    s.setPrinted("N");
	    s.setStatus("O");
	    s.setPeriod(1);
	    if(v.getCom_ifincomed().getSelectedItem().toString().equals("��"))
	    s.setIfR(true);
	    s.setRdocEntry(Integer.valueOf(v.getTxt_rdocn().getText().equals("")?"0":v.getTxt_rdocn().getText()));
	    s.setUserSign(appMain.oCompany.getUserSignature());
	    s.setSubuid(appMain.user1);
	    s.setTransfered("N");
	    s.setDataSource("I");
	    s.setCardCode(((JTextField)v.getTxt_cus().getEditor().getEditorComponent()).getText());
	    s.setUDq(v.getTxt_ter().getText());
	    if(v.getTxt_cusn().getText()==null||v.getTxt_cusn().getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"������Ʋ���Ϊ��");
			return;
		}
	    s.setCardName(v.getTxt_cusn().getText());
	    s.setCntctCode(Integer.valueOf(((ComboBoxItem)v.getTxt_cuslxr().getSelectedItem()).getValue().toString()));
	    s.setSlpCode(Integer.valueOf(((ComboBoxItem)v.getCom_sales().getSelectedItem()).getValue().toString()));
	    s.setUSlpCode1(Integer.valueOf(((ComboBoxItem)v.getCom_sales1().getSelectedItem()).getValue().toString()));
	    s.setComments(v.getJta_memo().getText());
		for(int i=0;i<v.getOd().dataSet.length;i++)
		{
			if(v.getOd().getValuethrheader(i,"���ϴ���")==null||v.getOd().getValuethrheader(i,"���ϴ���").toString().equals(""))
			{
				continue;
			}
			
			if(v.getOd().getValuethrheader(i,"���ϴ���")!=null&&!v.getOd().getValuethrheader(i,"���ϴ���").toString().equals("")&&
					(v.getOd().getValuethrheader(i,"����")==null||new BigDecimal(v.getOd().getValuethrheader(i,"����").toString()).setScale(2,BigDecimal.ROUND_UP).equals(new BigDecimal("0").setScale(2,BigDecimal.ROUND_UP))
					||v.getOd().getValuethrheader(i,"���")==null
					||v.getOd().getValuethrheader(i,"��װ����")==null||new BigDecimal(v.getOd().getValuethrheader(i,"��װ����").toString()).setScale(2,BigDecimal.ROUND_UP).equals(new BigDecimal("0").setScale(2,BigDecimal.ROUND_UP))))
			{
				int hh=i+1;					
				JOptionPane.showMessageDialog(null,v.getOd().getValuethrheader(i,"���ϴ���")+"��"+hh+"�а�װ�������������߽������Ƿ�,����,������Ϊ0����Ϊ��");	
				return;
			}
			boolean tf=false;
			 if(v.getOd1().ds.getCnValue().equals("�޸�"))
			{
				sd=null;
				for (URm1 str : s.getURm1s()) {  
				     if(v.getOd().getValuethrheader(i,"�к�")!=null&&str.getId().getLineId()==Integer.valueOf(v.getOd().getValuethrheader(i,"�к�").toString())) 
				     {
				    	sd=str;
				    	 break;
				     }
				} 
				 if(sd==null)
				 {
					 tf=true;
					 sd=new URm1();
					 sid=new URm1Id();
					 sid.setDocEntry(s);
					 sid.setLineId(i);
					 sd.setId(sid);
				 }
			
			}			
			else if(v.getOd1().ds.getCnValue().equals("����"))
			{
			    tf=true;
				sd=new URm1();
				sid=new URm1Id();
				sid.setDocEntry(s);
				sid.setLineId(i);
				sd.setId(sid);
			}
			else{}
						 
			sd.setItemCode(v.getOd().getValuethrheader(i, "���ϴ���").toString());
			sd.setItemName(v.getOd().getValuethrheader(i, "��������").toString());
			sd.setUYmd(v.getOd().getValuethrheader(i, "�Ƿ���ʾ�׶�").toString());
			sd.setULength(new BigDecimal(v.getOd().getValuethrheader(i, "�����׶�").toString()));
			sd.setUnitMsr(v.getOd().getValuethrheader(i, "���۵�λ").toString());
			sd.setNumPerMsr(new BigDecimal(v.getOd().getValuethrheader(i, "��λ����").toString()));
			if(v.getOd().getValuethrheader(i, "��װ����").toString().indexOf(".")!=-1){
			    sd.setUGs(Integer.valueOf(v.getOd().getValuethrheader(i, "��װ����").toString().substring(0,v.getOd().getValuethrheader(i, "��װ����").toString().indexOf("."))));
			}
			else{
				sd.setUGs(Integer.valueOf(v.getOd().getValuethrheader(i, "��װ����").toString()));	
			}
			sd.setUGjjg(new BigDecimal(v.getOd().getValuethrheader(i, "��װ����").toString()));
			sd.setQuantity(new BigDecimal(v.getOd().getValuethrheader(i, "����").toString()));
			sd.setPrice(new BigDecimal(v.getOd().getValuethrheader(i, "����").toString()));
			sd.setUMjg(new BigDecimal(v.getOd().getValuethrheader(i, "�׼۸�").toString()));
			sd.setLinetotal(new BigDecimal(v.getOd().getValuethrheader(i, "���").toString()));
			sd.setResult(v.getOd().getValuethrheader(i, "�������")==null?"":v.getOd().getValuethrheader(i, "�������").toString());
			sd.setResP( v.getOd().getValuethrheader(i, "������")==null?"":v.getOd().getValuethrheader(i, "������").toString());
			sd.setIssue(v.getOd().getValuethrheader(i, "����")==null?"":v.getOd().getValuethrheader(i, "����").toString());
			sd.setDalloc(v.getOd().getValuethrheader(i, "���λ���")==null?"":v.getOd().getValuethrheader(i, "���λ���").toString());
			sd.setProRes(v.getOd().getValuethrheader(i, "������")==null?"":v.getOd().getValuethrheader(i, "������").toString());
			sd.setMemo(v.getOd().getValuethrheader(i, "��ע")==null?"":v.getOd().getValuethrheader(i, "��ע").toString());
			sd.setImpmeas(v.getOd().getValuethrheader(i, "�Ľ���ʩ")==null?"":v.getOd().getValuethrheader(i, "�Ľ���ʩ").toString());
			 if(tf)
			 {		
			  s.getURm1s().add(sd);
			 }
	
		}
		
		if(v.getOd1().ds.getCnValue().equals("�޸�"))
		{
			appMain.QD.update(s);
			v.getOd1().setDs(docTitleStatus.query);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.query);
			v.getOd().setGridStatus(docLineStatus.query);
			dmv.setquery();
		}
		if(v.getOd1().ds.getCnValue().equals("����")){
			appMain.QD.add(s);
			v.getOd1().setDs(docTitleStatus.add);
			v.getOd1().setDocTitleStatus(v);
			v.getOd().setDocLineStatus(docLineStatus.add);
			v.getOd().setGridStatus(docLineStatus.add);
			dmv.setadd();
		}
		else{}
		
				      
	
					
		
	}

	@Override
	public Integer read(int id,String dod) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void update(QrView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[][] getDocLists(ParaList p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getfirst() {
		// TODO Auto-generated method stub	
		
        	  hql = "SELECT min(docNum) from U_QRM";
          
         
          ob = appMain.lt.sqlclob(hql,0,1);
           if(ob==null||ob.length==0)
           {
        	   return 0;
           }
           else{
        	   return Integer.valueOf(ob[0][0].toString());
           }         		 
	}

	@Override
	public Integer getprev(int id) {
		// TODO Auto-generated method stub
	
       	  hql = "SELECT max(docNum) from U_QRM where docNum<'"+id+"' ";
       
		
          ob = appMain.lt.sqlclob(hql,0,1);
           if(ob==null||ob.length==0)
           {
        	   return this.getlast();
           }
           else{
        	   return Integer.valueOf(ob[0][0].toString());
           }
	}

	@Override
	public Integer getnext(int id) {
		// TODO Auto-generated method stub
	
      	  hql = "SELECT docNum from U_QRM where docNum>'"+id+"' ";
       
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
        	  return this.getfirst();
          }
          else{
       	   return Integer.valueOf(ob[0][0].toString());
          }
	}

	@Override
	public Integer getlast() {
		// TODO Auto-generated method stub

      	  hql = "SELECT max(docNum) from U_QRM  ";
      
          ob = appMain.lt.sqlclob(hql,0,1);
          if(ob==null||ob.length==0)
          {
          	  return 0;
          }
          else{
       	   return Integer.valueOf(ob[0][0].toString());
          }
	}

	@Override
	public void setValues(QrView v,Integer id,String dod) {
		// TODO Auto-generated method stub		
		if(id==null||id==0)
		{
			return;
		}	
		
		 hql = "select rdocentry from u_qrm where docNum='"+id+"'";
		 ob = appMain.lt.sqlclob(hql,0,1); 
		
		 v.getCom_ifincomed().setEditable(true);
		 if( ob[0][0].toString().equals("0"))
		 {			
		     v.getCom_ifincomed().setSelectedItem("��");
		 }
		 else
		 {
			 v.getCom_ifincomed().setSelectedItem("��");
		 }
		 v.getCom_ifincomed().setEditable(false);
		 v.getTxt_rdocn().setEditable(true);
		 v.getTxt_rdocn().setText(ob[0][0].toString());
		 v.getTxt_rdocn().setEditable(false);
		 hql = "SELECT cardcode,cardname,cntctcode,slpcode,convert(varchar(10),docdate,23),comments,u_dq from U_QRM where docNum='"+id+"'";
		 ob = appMain.lt.sqlclob(hql,0,1); 
		 
		 v.getTxt_cus().setEditable(true);
		 v.getTxt_cus().setSelectedItem(ob[0][0].toString());
		 v.getTxt_cusn().setText(ob[0][1].toString());
				
		
		 hql = "SELECT name from Ocpr where cntctCode='"+ob[0][2].toString()+"'";
         ob1 = appMain.lt.sqlclob(hql,0,1);    
        if(!ob[0][2].toString().equals("0"))
        {
		 ComboBoxItem  Cbi=new ComboBoxItem(ob[0][2].toString(),ob1[0][0].toString());
		 v.getTxt_cuslxr().setEditable(true);
		 v.getTxt_cuslxr().setSelectedItem(Cbi);
        }
        else{
        	 v.getTxt_cuslxr().setEditable(true);
    		 v.getTxt_cuslxr().setSelectedItem(null);
        }	
        hql = "SELECT slpName from Oslp where slpCode='"+ob[0][3].toString()+"'";
        ob1 = appMain.lt.sqlclob(hql,0,1); 
        if(ob1!=null)
        {
		 ComboBoxItem  Cbi=new ComboBoxItem(ob[0][3].toString(),ob1[0][0].toString());
		 v.getCom_sales().setEditable(true);
		 v.getCom_sales().setSelectedItem(Cbi);
        }
        else{
        	 v.getCom_sales().setEditable(true);
    		 v.getCom_sales().setSelectedItem(null);
        }	
        v.getCom_sales().setEditable(false);
        SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
		 try {
			v.getTxt_date().setDate(sdf.parse(ob[0][4].toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 v.getTxt_docn().setText(id.toString());
		 v.getJta_memo().setText(ob[0][5].toString());
		 v.getTxt_ter().setText(ob[0][6].toString());
		hql = "SELECT a.U_slpcode1,b.slpname from U_QRM a inner join oslp b on a.U_slpcode1=b.slpcode where docnum='"+id+"'";
        ob = appMain.lt.sqlclob(hql,0,1); 
        if(!(ob==null||ob.length==0))
        {
          ComboBoxItem  Cbi=new ComboBoxItem(Integer.valueOf(ob[0][0].toString()),ob[0][1].toString());
          v.getCom_sales1().setEditable(true);
 		  v.getCom_sales1().setSelectedItem(Cbi);
 		  v.getCom_sales1().setEditable(false);
        }
        else{
        	 v.getCom_sales1().setEditable(true);
    		 v.getCom_sales1().setSelectedItem(null);
    		 v.getCom_sales1().setEditable(false);
        }
         hql = "SELECT printed from U_QRM where docNum='"+id+"'";
		 ob = appMain.lt.sqlclob(hql,0,1); 
		 v.getTxt_status().setText(ob[0][0].toString().equals("1")?"�Ѵ�ӡ":"δ��ӡ");
		 hql = "SELECT status from U_QRM where docNum='"+id+"'";
		 ob = appMain.lt.sqlclob(hql,0,1); 
		 v.getTxt_type().setText(ob[0][0].toString().equals("C")?"����":"δ��");
		
       
		 hql="select p.lineid+1, p.docentry,p.lineid,p.itemCode, p.itemname,p.U_Ymd,p.U_length,"+
            "p.unitMsr,p.NumPerMsr," +
            "p.U_Gs,p.U_Gjjg,p.quantity,p.price," +
            "p.U_Mjg,p.lineTotal,"+
	        "p.issue,p.result,p.dalloc,p.resp,p.prores,"+
            "p.impmeas,p.memo " +
            "from U_RM1 as p inner join U_QRM q on p.docentry=q.docentry where q.docnum='"+id+"'";			
	     v.getOd().updatetable(hql,0);	   	
		
	}
	@Override
	public void close(QrView v)
	{
		 hql = "update U_QRM  set status='C' where docentry='"+v.getTxt_docn().getText()+"'";
		 dbu.exeSql(hql);
		 v.getTxt_type().setText("����");
	}
	public QrView getV() {
		return v;
	}

	public void setV(QrView v) {
		this.v = v;
	}

	@Override
	public void print(QrView v) {
		// TODO Auto-generated method stub
		 try{    
			    hql="select U_URL from [@REPORTURL] " +
			 	  	 " where name='������������ӡ' ";
			 	 ob = appMain.lt.sqlclob(hql,0,1);
	             if(ob==null||ob.length==0)
	             {
	               JOptionPane.showMessageDialog(null, "�Ҳ����˱���·�������ں�̨�����ô˱��������·��");
	           	   return;
	             }
	             try {
						Desktop.getDesktop().browse(new URL(ob[0][0].toString()+"&docnum1="+String.valueOf(Integer.valueOf(v.getTxt_docn().getText())-1)).toURI());
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
	             hql = "update U_QRM  set printed='Y'";
				dbu.exeSql(hql);
         } catch (Exception ex) {    
             ex.printStackTrace();    
         }    
	 }

	@Override
	public void ctarget(QrView v, Integer docid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(User user,QrView v) {
		// TODO Auto-generated method stub
		
	}
	


	
}
