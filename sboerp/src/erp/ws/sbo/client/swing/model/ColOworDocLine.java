package erp.ws.sbo.client.swing.model;


public class ColOworDocLine {
	private String lineNum="���";
	private String itemCode="���ϴ���";
	private String dscription="��������";
    private String baseQty="��������";
    private String plannedQty="�ƻ�����";
    private String issuedQty="��������";
    private String issuedType="��������";
    private String wareHouse="�ֿ�";	
	private String oUnit="��λ";
	private String whQty="������";
    
    private static ColOworDocLine CLine;
	private ColOworDocLine(){
		
	}
	public static ColOworDocLine getCLine()
	{
    	if(CLine==null)
    	{
    		CLine=new  ColOworDocLine();
    	}
    	return CLine;
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
	public String getLineNum() {
		return lineNum;
	}
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
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
	public String getBaseQty() {
		return baseQty;
	}
	public void setBaseQty(String baseQty) {
		this.baseQty = baseQty;
	}
	public String getPlannedQty() {
		return plannedQty;
	}
	public void setPlannedQty(String plannedQty) {
		this.plannedQty = plannedQty;
	}
	public String getIssuedQty() {
		return issuedQty;
	}
	public void setIssuedQty(String issuedQty) {
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
	public String getWhQty() {
		return whQty;
	}
	public void setWhQty(String whQty) {
		this.whQty = whQty;
	}		
	
		
}
