package erp.ws.sbo.utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.sun.jna.Library;
import com.sun.jna.Native;

import erp.ws.sbo.client.swing.app.appMain;
import erp.ws.sbo.client.swing.model.snstatus;
import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.Snin.SninView;
import erp.ws.sbo.dao.ISNStatus;
import erp.ws.sbo.dao.impl.SNStatus;
public class Snprint {
	private SninView v;
	private OignView v1;
	private String hql;//query string 
	private Object[][] ob,ob1;
	public interface TscLibDll extends Library {
        TscLibDll INSTANCE = (TscLibDll) Native.loadLibrary ("TSCLIB", TscLibDll.class);
        int about ();
        int openport (String pirnterName);
        int closeport ();
        int sendcommand (String printerCommand);
        int setup (String width,String height,String speed,String density,String sensor,String vertical,String offset);
        int downloadpcx (String filename,String image_name);
        int barcode (String x,String y,String type,String height,String readable,String rotation,String narrow,String wide,String code);
        int printerfont (String x,String y,String fonttype,String rotation,String xmul,String ymul,String text);
        int clearbuffer ();
        int printlabel (String set, String copy);
        int formfeed ();
        int nobackfeed ();
        int windowsfont (int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, String content);
    }
	public Snprint(SninView v){
		
		this.v=v;
	}
    public Snprint(OignView v){
		
		this.v1=v;
	}
   //only configure setup barcodetype barcode
    public void print(String width,String height,String speed,String density,String sensor,String vertical,String offset,
    		String codetype,String barcode,SninView v) {
        //TscLibDll.INSTANCE.about();
        TscLibDll.INSTANCE.openport("TSC TTP-342M Pro");
        
        //TscLibDll.INSTANCE.
        //TscLibDll.INSTANCE.downloadpcx("C:\\UL.PCX", "UL.PCX");
        //TscLibDll.INSTANCE.sendcommand("REM ***** This is a test by JAVA. *****");
        //TscLibDll.INSTANCE.setup("100", "100", "5", "8", "0", "0", "0");
        TscLibDll.INSTANCE.setup(width,height,speed,density,sensor,vertical,offset);
        TscLibDll.INSTANCE.clearbuffer();
        //TscLibDll.INSTANCE.sendcommand("PUTPCX 550,10,\"UL.PCX\"");
        //TscLibDll.INSTANCE.printerfont ("100", "10", "3", "0", "1", "1", "(JAVA) DLL Test!!");
        //TscLibDll.INSTANCE.barcode("100", "40", "128", "50", "1", "0", "2", "2", "123456789");
      
        
        hql = "select count(*) from [@SNPRINT] ";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||ob.length==0)
		 {					
			 JOptionPane.showMessageDialog(null,"没有在自定义表【@snprint】中设置打印内容");
			 return;
		 }
		 ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");							
		 snstatus sns=new snstatus();
		 try{
		      sns=isn.queryByDocId(barcode);
		   }
	       catch(NullPointerException e)
	       {
	        	e.printStackTrace();
	       }

        /*TscLibDll.INSTANCE.windowsfont(200, 20, 44, 0, 0, 0, "ARIAL", "公司名称:" + v.getCom_company().getSelectedItem().toString());        //Drawing printer font            
        TscLibDll.INSTANCE.windowsfont(10,80, 44, 0, 0, 0, "ARIAL", "规格:" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(420, 80, 44, 0, 0, 0, "ARIAL", "米段:" + v.getTxt_length().getText().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(10, 140, 44, 0, 0, 0, "ARIAL", "毛重:" + v.getTxt_weight().getText().toString().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(420, 140, 44, 0, 0, 0, "ARIAL", "净重:" + v.getTxt_cweight().getText().toString().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(10, 220, 44, 0, 0, 0, "ARIAL", "机号:" + v.getTxt_MNo().getText().toString().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(420, 220, 44, 0, 0, 0, "ARIAL", "检验员:" + v.getTxt_Qinspector().getText().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(10, 280, 44, 0, 0, 0, "ARIAL", "日期:" + v.getTxt_createcode().getText().substring(2, 6)+"-"+v.getTxt_createcode().getText().substring(6, 8)+"-"+v.getTxt_createcode().getText().substring(8, 10));          
        TscLibDll.INSTANCE.windowsfont(420, 280, 44, 0, 0, 0, "ARIAL", "防伪电话:15233911556");   
        */		
			 hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='公司名称'";
		     ob1=appMain.lt.sqlclob(hql,0,1);	    
	         TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getCompany());        //Drawing printer font            
	         hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='规格'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		   
	         TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getItemcode());        //Drawing printer font
		  
	         hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='米段'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		    
	         TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getLength().toString());        //Drawing printer font
		  
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='毛重'";
		    ob1=appMain.lt.sqlclob(hql,0,1);
	        if(!sns.isIfPsn())
	        {  
	        	if(new BigDecimal(sns.getLength().toString()).setScale(3, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.000).setScale(3, RoundingMode.HALF_UP))==0||sns.getItemcode().substring(0, 2).equals("TD"))
	        	{
	              TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getWeight().toString());        //Drawing printer font	        
	        	}
	        }
	        else 
	        {
	          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(),"数量:" + sns.getQsn().toString());        //Drawing printer font
	        }
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='净重'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		     if(new BigDecimal(sns.getLength().toString()).setScale(3, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.000).setScale(3, RoundingMode.HALF_UP))==0||sns.getItemcode().substring(0, 2).equals("TD"))
	        {
	          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getCweight().toString());        //Drawing printer font
	        }
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='机号'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getMno());        //Drawing printer font
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='检验员'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getQc());        //Drawing printer font
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='日期'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		     SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");		
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + f.format(sns.getDatetime()));
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='防伪电话'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString());   
	        
	        TscLibDll.INSTANCE.barcode(v.getTxt_left().getText(), v.getTxt_up().getText(), v.getTxt_codetype().getText(),  v.getTxt_codeheight().getText(), "1", "0", v.getTxt_codegap().getText(), v.getTxt_codewidth().getText(), barcode);
	        TscLibDll.INSTANCE.printlabel("1", "1");
	        TscLibDll.INSTANCE.closeport();
	        //TscLibDll.INSTANCE.sendcommand("QRCODE 100,100,H,4,A,0,M2,S7,\"沙河潜水电线厂\"") ;
	        //TscLibDll.INSTANCE.sendcommand("AZTEC 410,310,0,4,1,0,0,1,1,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\"");
    }
    public void print(String width,String height,String speed,String density,String sensor,String vertical,String offset,
    		String codetype,String barcode,OignView v) {
        //TscLibDll.INSTANCE.about();
        TscLibDll.INSTANCE.openport("TSC TTP-342M Pro");
        //TscLibDll.INSTANCE.
        //TscLibDll.INSTANCE.downloadpcx("C:\\UL.PCX", "UL.PCX");
        //TscLibDll.INSTANCE.sendcommand("REM ***** This is a test by JAVA. *****");
        //TscLibDll.INSTANCE.setup("100", "100", "5", "8", "0", "0", "0");
        TscLibDll.INSTANCE.setup(width,height,speed,density,sensor,vertical,offset);
        TscLibDll.INSTANCE.clearbuffer();
        //TscLibDll.INSTANCE.sendcommand("PUTPCX 550,10,\"UL.PCX\"");
        //TscLibDll.INSTANCE.printerfont ("100", "10", "3", "0", "1", "1", "(JAVA) DLL Test!!");
        //TscLibDll.INSTANCE.barcode("100", "40", "128", "50", "1", "0", "2", "2", "123456789");
      
        
        hql = "select count(*) from [@SNPRINT] ";
		 ob=appMain.lt.sqlclob(hql,0,1);
		 if(ob==null||ob.length==0)
		 {					
			 JOptionPane.showMessageDialog(null,"没有在自定义表【@snprint】中设置打印内容");
			 return;
		 }
		 ISNStatus isn=(SNStatus)appMain.ctx.getBean("SNStatus");							
		 snstatus sns=new snstatus();
		 try{
		      sns=isn.queryByDocId(barcode);
		   }
	       catch(NullPointerException e)
	       {
	        	e.printStackTrace();
	       }

        /*TscLibDll.INSTANCE.windowsfont(200, 20, 44, 0, 0, 0, "ARIAL", "公司名称:" + v.getCom_company().getSelectedItem().toString());        //Drawing printer font            
        TscLibDll.INSTANCE.windowsfont(10,80, 44, 0, 0, 0, "ARIAL", "规格:" + ((JTextField)v.getCom_specification().getEditor().getEditorComponent()).getText());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(420, 80, 44, 0, 0, 0, "ARIAL", "米段:" + v.getTxt_length().getText().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(10, 140, 44, 0, 0, 0, "ARIAL", "毛重:" + v.getTxt_weight().getText().toString().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(420, 140, 44, 0, 0, 0, "ARIAL", "净重:" + v.getTxt_cweight().getText().toString().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(10, 220, 44, 0, 0, 0, "ARIAL", "机号:" + v.getTxt_MNo().getText().toString().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(420, 220, 44, 0, 0, 0, "ARIAL", "检验员:" + v.getTxt_Qinspector().getText().trim());        //Drawing printer font
        TscLibDll.INSTANCE.windowsfont(10, 280, 44, 0, 0, 0, "ARIAL", "日期:" + v.getTxt_createcode().getText().substring(2, 6)+"-"+v.getTxt_createcode().getText().substring(6, 8)+"-"+v.getTxt_createcode().getText().substring(8, 10));          
        TscLibDll.INSTANCE.windowsfont(420, 280, 44, 0, 0, 0, "ARIAL", "防伪电话:15233911556");   
        */		
			 hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='公司名称'";
		     ob1=appMain.lt.sqlclob(hql,0,1);	    
	         TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getCompany());        //Drawing printer font            
	         hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='规格'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		   
	         TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getItemcode());        //Drawing printer font
		  
	         hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='米段'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		    
	         TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getLength().toString());        //Drawing printer font
		  
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='毛重'";
		    ob1=appMain.lt.sqlclob(hql,0,1);
	        if(!sns.isIfPsn())
	        {  
	        	if(new BigDecimal(sns.getLength().toString()).setScale(3, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.000).setScale(3, RoundingMode.HALF_UP))==0||sns.getItemcode().substring(0, 2).equals("TD"))
	        	{
	              TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getWeight().toString());        //Drawing printer font	        
	        	}
	        }
	        else 
	        {
	          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(),"数量:" + sns.getQsn().toString());        //Drawing printer font
	        }
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='净重'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		     if(new BigDecimal(sns.getLength().toString()).setScale(3, RoundingMode.HALF_UP).compareTo(new BigDecimal(0.000).setScale(3, RoundingMode.HALF_UP))==0||sns.getItemcode().substring(0, 2).equals("TD"))
	        {
	          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getCweight().toString());        //Drawing printer font
	        }
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='机号'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getMno());        //Drawing printer font
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='检验员'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + sns.getQc());        //Drawing printer font
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='日期'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
		     SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");		
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + f.format(sns.getDatetime()));
	        hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='防伪电话'";
		     ob1=appMain.lt.sqlclob(hql,0,1);
	        TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString());   
	        
	        TscLibDll.INSTANCE.barcode(v.getTxt_left().getText(), v.getTxt_up().getText(), v.getTxt_codetype().getText(),  v.getTxt_codeheight().getText(), "1", "0", v.getTxt_codegap().getText(), v.getTxt_codewidth().getText(), barcode);
	        TscLibDll.INSTANCE.printlabel("1", "1");
	        TscLibDll.INSTANCE.closeport();
		 
    }
    public void print(OignView v) {
    	  TscLibDll.INSTANCE.openport("TSC TTP-342M Pro");
    	  hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='公司名称'";
	      ob1=appMain.lt.sqlclob(hql,0,1);	    
          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + v.getTxt_docnid().getText());        //Drawing printer font            
          hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='规格'";
	      ob1=appMain.lt.sqlclob(hql,0,1);  
          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + v.getTxt_msum().getText());        //Drawing printer font	  
          hql="select u_left,u_up,u_fontheight,u_rotation,u_fontstyle,u_underline,u_szfaceneme,u_contents from [@SNPRINT] where name='米段'";
	      ob1=appMain.lt.sqlclob(hql,0,1);	    
          TscLibDll.INSTANCE.windowsfont(Integer.valueOf(ob1[0][0].toString()), Integer.valueOf(ob1[0][1].toString()), Integer.valueOf(ob1[0][2].toString()), Integer.valueOf(ob1[0][3].toString()), Integer.valueOf(ob1[0][4].toString()), Integer.valueOf(ob1[0][5].toString()),ob1[0][6].toString(), ob1[0][7].toString() + v.getTxt_rsum());        //Drawing printer font          
          TscLibDll.INSTANCE.barcode(v.getTxt_left().getText(), v.getTxt_up().getText(), v.getTxt_codetype().getText(),  v.getTxt_codeheight().getText(), "1", "0", v.getTxt_codegap().getText(), v.getTxt_codewidth().getText(), v.getTxt_docnid().getText());
	      TscLibDll.INSTANCE.printlabel("1", "1");
	      TscLibDll.INSTANCE.closeport();
    	  
    }
	public SninView getV() {
		return v;
	}
	public void setV(SninView v) {
		this.v = v;
	}
	public OignView getV1() {
		return v1;
	}
	public void setV1(OignView v1) {
		this.v1 = v1;
	}
}
