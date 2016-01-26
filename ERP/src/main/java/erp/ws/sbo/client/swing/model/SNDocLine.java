package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

public class SNDocLine {
	
	private Integer id;	
	private String  objType;
	private Integer docEntry;
	private Integer lineNum;
	private String sn;
	private Boolean direction;
	private Boolean ifpasn;
	private String  pasn;
	private String  warehouse;
	private String cardcode;
	private String memo;
	private String workcenter;
	private Timestamp cdatetime;
    private Timestamp udatetime;
   

	public SNDocLine(){
		
	}
	public SNDocLine(Integer id) {
		this.setId(id);
	}
	public SNDocLine(Integer id,
	 String  objType,
	 Integer docEntry,
	 Integer lineNum,
	 String sn,
	 Boolean direction,
	 Boolean ifpasn,
	 String  pasn,
	 String  warehouse,
	 String cardcode,
	 String memo,
	 String workcenter,
	 Timestamp cdatetime,
	 Timestamp udatetime
     )
	{
		this.id=id;	
		this.objType=objType;
		this.docEntry=docEntry;
		this.lineNum=lineNum;
		this.sn=sn;
		this.direction=direction;
		this.ifpasn=ifpasn;
		this.pasn=pasn;
		this.warehouse=warehouse;
		this.cardcode=cardcode;
		this.memo=memo;
		this.workcenter=workcenter;
		this.cdatetime=cdatetime;
		this.udatetime=udatetime;
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
		else if(s=="sn")
		{
			return sn;
		}
		else if(s=="direction")
		{
			return direction;
		}
		else if(s=="ifpasn")
		{
			return ifpasn;
		}
		else if(s=="pasn")
		{
			return pasn;
		}
		else if(s=="warehouse")
		{
			return warehouse;
		}
		else if(s=="cardcode")
		{
			return cardcode;
		}
		else if(s=="memo")
		{
			return memo;
		}
		else if(s=="workcenter")
		{
			return workcenter;
		}
	    else if(s=="cdatetime")
		{
			return cdatetime;
		}		
	    else if(s=="udatetime")
		{
			return udatetime;
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
	public String getWorkcenter() {
		return workcenter;
	}
	public void setWorkcenter(String workcenter) {
		this.workcenter = workcenter;
	}
	public Timestamp getCdatetime() {
		return cdatetime;
	}
	public void setCdatetime(Timestamp cdatetime) {
		this.cdatetime = cdatetime;
	}
	public Timestamp getUdatetime() {
		return udatetime;
	}
	public void setUdatetime(Timestamp udatetime) {
		this.udatetime = udatetime;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public Boolean getDirection() {
		return direction;
	}
	public void setDirection(Boolean direction) {
		this.direction = direction;
	}
	public Boolean getIfpasn() {
		return ifpasn;
	}
	public void setIfpasn(Boolean ifpasn) {
		this.ifpasn = ifpasn;
	}
	public String getPasn() {
		return pasn;
	}
	public void setPasn(String pasn) {
		this.pasn = pasn;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}	
	
}
