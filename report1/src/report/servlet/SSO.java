package report.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSO extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2046537172129605500L;

	/**
	 * Constructor of the object.
	 */
	public SSO() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BeanCtx.init("Anonymous", request, response);
		response.setContentType("text/html;charset=utf-8");
		String docunid = request.getParameter("wf_docunid");
		String gourl = "../rule?wf_num=R_S003_B036&wf_docunid=" + docunid;
		// 鐢ㄦ埛id姝ｅ紡浣跨敤闇�瑕佽繘琛岃В瀵�
		//String userid = request.getParameter("uid");
		String uuid = request.getParameter("uuid");
		String[] userinfo = MailURLEncoder.decodeURLParam(uuid);
		String userid = null;
		if (userinfo!=null&&userinfo.length==2) {			
			String docuni = userinfo[1];
			if (docuni.equals(docunid)) {
				userid = userinfo[0];
			}
		}
		
		if (userid==null) {
			//楠岃瘉涓嶉�氳繃 杩涘叆鐧诲綍椤甸潰
			gourl = "../";		
		    response.sendRedirect(gourl);
		    BeanCtx.close();
		    return;
		}
		
		String sql = "select UserId,CnName from BPM_OrgUserList where Userid='"
				+ userid + "'";
		Document userDoc = Rdb.getDocumentBySql(sql);
		
		
		if (!userDoc.g("CnName").equals("")) {
			request.getSession().setAttribute("LoginUserid", userid);
			sql = "select WF_OrUnid,Userid,LastLoginIP,LastLoginTime from BPM_UserProfile where Userid='"
					+ userid + "'";
			Document doc = Rdb.getDocumentBySql(sql);

			doc.s("Userid", userid);
			doc.s("LastLoginIP", request.getRemoteAddr());
			doc.s("LastLoginTime", DateUtil.getNow());
			doc.save();

			if (Tools.isNotBlank(BeanCtx.getSystemConfig("DominoSecret"))) {
				String dominoCookie = LtpaLibrary.getcookie(userid, 30000);
				Cookie cookie = new Cookie("LtpaToken", dominoCookie);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		} else {
			//楠岃瘉涓嶉�氳繃 杩涘叆鐧诲綍椤甸潰
			gourl = "../";
		}
		response.sendRedirect(gourl);

		BeanCtx.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
