package erp.ws.sbo.client.swing.model;


public class OworDocLine {
	private Integer lineNum;
	private String itemCode;
	private String dscription;
    private Double baseQty;
    private Double plannedQty;
    private Double issuedQty;
    private String issuedType;
    private String wareHouse;	
    private String oUnit;	
    private Double whQty;	
	public OworDocLine(){
		
	}
	public OworDocLine(
	 Integer lineNum,
	 String itemCode,
	 String dscription,
	 Double baseQty,
	 Double plannedQty,
	 Double issuedQty,
	 String issuedType,
	 String wareHouse,
	 String oUnit,
	 Double whQty	
     )
	{
		this.lineNum=lineNum;
		this.itemCode=itemCode;
		this.dscription=dscription;
		this.baseQty=baseQty;
		this.plannedQty=plannedQty;
		this.issuedQty=issuedQty;
		this.issuedType=issuedType;
		this.wareHouse=wareHouse;
		this.oUnit=oUnit;
		this.whQty=whQty;
	}

	
	public Object getByName(String s) {
		if(s=="lineNum")
		{
			return lineNum;
		}				
		else if(s=="itemCode")
		{
			return itemCode;
		}
		else if(s=="dscription")
		{
			return dscription;
		}
		else if(s=="baseQty")
		{
			return baseQty;
		}
		else if(s=="plannedQty")
		{
			return plannedQty;
		}
		else if(s=="issuedQty")
		{
			return issuedQty;
		}		
		else if(s=="issuedType")
		{
			return issuedType;
		}
		else if(s=="wareHouse")
		{
			return wareHouse;
		}
		else if(s=="oUnit")
		{
			return oUnit;
		}
		else if(s=="whQty")
		{
			return whQty;
		}
		else {
			return null;
		}
	}
	
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public Integer getLineNum() {
		return lineNum;
	}
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	public Double getBaseQty() {
		return baseQty;
	}
	public void setBaseQty(Double baseQty) {
		this.baseQty = baseQty;
	}
	public Double getPlannedQty() {
		return plannedQty;
	}
	public void setPlannedQty(Double plannedQty) {
		this.plannedQty = plannedQty;
	}
	public Double getIssuedQty() {
		return issuedQty;
	}
	public void setIssuedQty(Double issuedQty) {
		this.issuedQty = issuedQty;
	}
	public String getIssuedType() {
		return issuedType;
	}
	public void setIssuedType(String issuedType) {
		this.issuedType = issuedType;
	}
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getOUnit() {
		return oUnit;
	}
	public void setOUnit(String oUnit) {
		this.oUnit = oUnit;
	}
	public Double getWhQty() {
		return whQty;
	}
	public void setWhQty(Double whQty) {
		this.whQty = whQty;
	}
	
	
}
