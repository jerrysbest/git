package erp.ws.sbo.client.swing.model;

import java.sql.Date;


public class OignDocLine {
	private Integer lineNum;
	private Integer USnid;
	private Integer docEntry;
	private String itemCode;
	private String dscription;
	private Double length;
    private String unit;
    private Double unitQty;
    private Integer UGs;
    private Integer USGs;
    private String OUnit;	
    private Double planQty;
    private Double realQty;
    private Double deviation;
    private Double unitprice;
    private String wareHouse;	
    private String outWh;	
    private Double plannedQty;
    private Double cpltQty;
    private Date duedate;
    private String user;	

	public OignDocLine(){
		
	}
	public OignDocLine(
			 Integer lineNum,
			 Integer USnid,
			 Integer docEntry,
			 String itemCode,
			 String dscription,
			 Double length,
		     String unit,
		     Double unitQty,
		     Integer UGs,
		     Integer USGs,
		     String OUnit,	
		     Double planQty,
		     Double realQty,
		     Double deviation,
		     Double unitprice,
		     String wareHouse,	
		     String outWh,
		     Double plannedQty,
		     Double cpltQty,
		     Date duedate,
		     String user
     )
	{
		this.lineNum=lineNum;
		this.USnid=USnid;
		this.docEntry=docEntry;
		this.itemCode=itemCode;
		this.dscription=dscription;
		this.length=length;
		this.unit=unit;
		this.unitQty=unitQty;
		this.UGs=UGs;
		this.USGs=USGs;
		this.OUnit=OUnit;
		this.planQty=planQty;
		this.realQty=realQty;
		this.deviation= deviation;
		this.unitprice=unitprice;
		this.wareHouse=wareHouse;
		this.outWh=outWh;
		this.plannedQty=plannedQty;
		this.cpltQty=cpltQty;
		this.duedate=duedate;
		this.user=user;		
	}
	
	public Object getByName(String s) {
		if(s=="lineNum")
		{
			return lineNum;
		}	
		else if(s=="USnid")
		{
			return USnid;
		}
		else if(s=="docEntry")
		{
			return docEntry;
		}
		else if(s=="itemCode")
		{
			return itemCode;
		}
		else if(s=="dscription")
		{
			return dscription;
		}
		else if(s=="length")
		{
			return length;
		}
		else if(s=="unit")
		{
			return unit;
		}
		else if(s=="unitQty")
		{
			return unitQty;
		}		
		else if(s=="UGs")
		{
			return UGs;
		}	
		else if(s=="USGs")
		{
			return USGs;
		}	
		else if(s=="OUnit")
		{
			return OUnit;
		}
		else if(s=="planQty")
		{
			return planQty;
		}
		else if(s=="realQty")
		{
			return realQty;
		}
		else if(s=="deviation")
		{
			return deviation;
		}
		else if(s=="unitprice")
		{
			return unitprice;
		}
		else if(s=="wareHouse")
		{
			return wareHouse;
		}
		else if(s=="outWh")
		{
			return outWh;
		}
		else if(s=="plannedQty")
		{
			return plannedQty;
		}
		else if(s=="cpltQty")
		{
			return cpltQty;
		}
		else if(s=="duedate")
		{
			return duedate;
		}
		else {
			return null;
		}
	}
	public Integer getLineNum() {
		return lineNum;
	}
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	public Integer getUSnid() {
		return USnid;
	}
	public void setUSnid(Integer uSnid) {
		USnid = uSnid;
	}
	public Integer getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
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
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String Unit) {
		unit = Unit;
	}
	public String getOUnit() {
		return OUnit;
	}
	public void setOUnit(String oUnit) {
		OUnit = oUnit;
	}
	public Double getPlanQty() {
		return planQty;
	}
	public void setPlanQty(Double planQty) {
		this.planQty = planQty;
	}
	public Double getRealQty() {
		return realQty;
	}
	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}
	public Double getDeviation() {
		return deviation;
	}
	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public Double getPlannedQty() {
		return plannedQty;
	}
	public void setPlannedQty(Double plannedQty) {
		this.plannedQty = plannedQty;
	}
	public Double getCpltQty() {
		return cpltQty;
	}
	public void setCpltQty(Double cpltQty) {
		this.cpltQty = cpltQty;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Double getUnitQty() {
		return unitQty;
	}
	public void setUnitQty(Double unitQty) {
		this.unitQty = unitQty;
	}
	public String getOutWh() {
		return outWh;
	}
	public void setOutWh(String outWh) {
		this.outWh = outWh;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getUGs() {
		return UGs;
	}
	public void setUGs(Integer uGs) {
		UGs = uGs;
	}
	public Integer getUSGs() {
		return USGs;
	}
	public void setUSGs(Integer uSGs) {
		USGs = uSGs;
	}
	
		
	
}
