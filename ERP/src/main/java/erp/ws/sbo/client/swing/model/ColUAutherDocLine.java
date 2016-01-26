package erp.ws.sbo.client.swing.model;


public class ColUAutherDocLine {
	
	private String  id="序号";	
	private String  usercode="用户代码";
	private String  username="用户名称";
	private String  authcode="权限代码";
	private String  authname="权限名称";
	private String  enable="授权";
    private static ColUAutherDocLine CLine;
	
	private ColUAutherDocLine()
	{
		
	}
	
	public static ColUAutherDocLine getCLine()
	{  
		if(CLine==null)
	   {
			CLine=new ColUAutherDocLine();
	   }
		return CLine;
	}
	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="usercode")
		{
			return usercode;
		}
		else if(s=="username")
		{
			return username;
		}		
		else if(s=="authcode")
		{
			return authcode;
		}
		else if(s=="authname")
		{
			return authname;
		}
		else if(s=="enable")
		{
			return enable;
		}		
		else {
			return null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getAuthname() {
		return authname;
	}

	public void setAuthname(String authname) {
		this.authname = authname;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}


	
	
}
