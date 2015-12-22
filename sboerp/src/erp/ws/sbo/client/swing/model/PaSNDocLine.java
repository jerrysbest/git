package erp.ws.sbo.client.swing.model;


public class PaSNDocLine {
	
	private Integer id;	
	private String sn;
	private String memo;

   

	public PaSNDocLine(){
		
	}
	public PaSNDocLine(Integer id) {
		this.setId(id);
	}
	public PaSNDocLine(Integer id,
	 String sn,
	 String memo
     )
	{
		this.id=id;	
		this.sn=sn;
		this.memo=memo;
	}
	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="sn")
		{
			return sn;
		}
		else if(s=="memo")
		{
			return memo;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
		
	
}
