package report.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Welcome extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8615763003090418949L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
	       
        HttpSession hs=req.getSession();
        String val=(String)hs.getAttribute("pass");
       
        if(val==null){
            try{
                System.out.print(1);
                res.sendRedirect("login");
            }catch(Exception e){
                e.printStackTrace();
            }
           
        }       
           
        String u=req.getParameter("uname");
        String p=req.getParameter("upass");
       
        try{//req.setCharacterEncoding("gb2312");
            PrintWriter pw=res.getWriter();
            pw.println("welcome! "+u+"&pass="+p);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   
    public void doPost(HttpServletRequest req,HttpServletResponse res){
        this.doGet(req,res);
    }

}
