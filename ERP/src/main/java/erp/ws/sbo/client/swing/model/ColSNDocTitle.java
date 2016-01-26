package erp.ws.sbo.client.swing.model;


public class ColSNDocTitle {
	
	private String  id="序号";	
	private String  objType="对象类型";
	private String  docEntry="单号";
	private String  lineNum="行号";
	private String  itemcode="物料代码";
	private String  itemname="物料名称";
   
	private ColSNDocTitle(){
		
	}
	private static ColSNDocTitle CLine;
    public static ColSNDocTitle getCLine()
    {
    	if(CLine==null)
    	{
    		CLine=new  ColSNDocTitle();
    	}
    	return CLine;
    }
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="objType")
		{
			return objType;
		}
		else if(s=="docEntry")
		{
			return docEntry;
		}		
		else if(s=="lineNum")
		{
			return lineNum;
		}
		else if(s=="itemcode")
		{
			return itemcode;
		}
		else if(s=="itemname")
		{
			return itemname;
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
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(String docEntry) {
		this.docEntry = docEntry;
	}
	public String getLineNum() {
		return lineNum;
	}
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	
		
}
