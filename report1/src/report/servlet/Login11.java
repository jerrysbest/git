package report.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login11 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2823542514443188375L;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
        try{req.setCharacterEncoding("gb2312");
        res.setContentType("text/html;charset=gb2312");
            PrintWriter pw=res.getWriter();
            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1>��½����</h1>");
            pw.println("<form action=logincl method=post>");
            pw.println("�û���:<input type=text name=username><br>");
            pw.println("����:<input type=password name=passwd><br>");
            pw.println("<input type=submit value=login><br>");
            pw.println("</form>");
            pw.println("</body>");
            pw.println("</html>");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   
    public void doPost(HttpServletRequest req,HttpServletResponse res){
        this.doGet(req,res);
    }
}
