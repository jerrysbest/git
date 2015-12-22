package erp.ws.sbo.client.swing.model;


public class UAutherDocLine {
	
	private Integer id;	
	private String  usercode;
	private String  username;
	private String  authcode;
	private String  authname;
	private Boolean enable;
	public UAutherDocLine(){
		
	}
	public UAutherDocLine(Integer id) {
		this.setId(id);
	}
	public UAutherDocLine(Integer id,
	 String usercode,
	 String username,
	 String authcode,
	 String authname,
	 Boolean enable
     )
	{
		this.id=id;	
		this.usercode=usercode;
		this.username=username;
		this.authcode=authcode;
		this.authname=authname;
		this.enable=enable;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	
}
