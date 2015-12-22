package erp.ws.sbo.client.swing.model;


public class ColPaSNDocLine {
	
	private String  id="ÐòºÅ";	
	private String  sn="ÐòÁÐºÅ";
	private String  memo="±¸×¢";
   
	private ColPaSNDocLine(){
		
	}
	private static ColPaSNDocLine CLine;
    public static ColPaSNDocLine getCLine()
    {
    	if(CLine==null)
    	{
    		CLine=new  ColPaSNDocLine();
    	}
    	return CLine;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
