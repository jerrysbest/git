package erp.ws.sbo.client.swing.model;


public class UAutherDocTitle {
	
	private Integer id;	
	private String  code;
	private String  name;
	
	public UAutherDocTitle(){
		
	}
	public UAutherDocTitle(Integer id) {
		this.setId(id);
	}
	public UAutherDocTitle(Integer id,
	 String  code,
	 String name
     )
	{
		this.id=id;	
		this.code=code;
		this.name=name;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
