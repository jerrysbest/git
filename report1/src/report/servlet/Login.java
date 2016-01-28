package report.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import report.util.PreparedJDBC;


public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3753203723085691422L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
	     
		this.doPost(req,res);
    }
   
    public void doPost(HttpServletRequest req,HttpServletResponse res){
        //this.doGet(req,res);
        
        PreparedJDBC pjdbc=new PreparedJDBC();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql = "select userid,password from UFDATA_TEST.dbo.users where userid = ? and password = ?";
        try{//req.setCharacterEncoding("gb2312");
            String user=req.getParameter("username");
            String password=req.getParameter("password");
     
            conn=pjdbc.get("015");  
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                HttpSession hs=req.getSession(true);
                hs.setMaxInactiveInterval(60);
                hs.setAttribute("Login", "OK");
                hs.setAttribute("myCount", new Integer(1)); 
                req.getRequestDispatcher("welcome.jsp").forward(req, res); //校验用户名密码正确，跳转到welcome.jsp
            }
            else{
                //res.sendRedirect("login"); //url
            	req.getRequestDispatcher("index.jsp").forward(req, res); //
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs!=null){
                rs.close();
                }
                if(stmt!=null){
                    stmt.close();
                }
                if(conn!=null){
                    conn.close();
                }   
            }catch(Exception e){
                e.printStackTrace();
            }       
        }
    }

}
