package erp.ws.sbo.client.swing.model;


public class SNDocTitle {
	
	private Integer id;	
	private String  objType;
	private Integer docEntry;
	private Integer lineNum;
	private String itemcode;
	private String itemname;
	public SNDocTitle(){
		
	}
	public SNDocTitle(Integer id) {
		this.setId(id);
	}
	public SNDocTitle(Integer id,
	 String  objType,
	 Integer docEntry,
	 Integer lineNum,
	 String itemcode,
	 String itemname
     )
	{
		this.id=id;	
		this.objType=objType;
		this.docEntry=docEntry;
		this.lineNum=lineNum;
		this.itemcode=itemcode;
		this.itemname=itemname;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public Integer getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}
	public Integer getLineNum() {
		return lineNum;
	}
	public void setLineNum(Integer lineNum) {
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
