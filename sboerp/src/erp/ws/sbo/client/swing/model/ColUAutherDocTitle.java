package erp.ws.sbo.client.swing.model;


public class ColUAutherDocTitle {
	
	private String id="序号";	
	private String  code="用户代码";
	private String  name="用户名称";
	
    private static ColUAutherDocTitle CTitle;
	
	private ColUAutherDocTitle()
	{
		
	}
	
	public static ColUAutherDocTitle getCTitle()
	{  
		if(CTitle==null)
	   {
		 CTitle=new ColUAutherDocTitle();
	   }
		return CTitle;
	}

	
	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="code")
		{
			return code;
		}
		else if(s=="name")
		{
			return name;
		}		
		else {
			return null;
		}
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
}
