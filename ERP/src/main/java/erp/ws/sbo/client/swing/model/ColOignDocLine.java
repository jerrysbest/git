package erp.ws.sbo.client.swing.model;


public class ColOignDocLine {
	private String lineNum="���";
	private String USnid="SN�к�";
	private String docEntry="����������";
	private String itemCode="���ϴ���";
	private String dscription="��������";
	private String Ymd="�Ƿ��׶���";
	private String length="�׶�";
    private String Unit="������λ";	
    private String unitQty="��λ����";
    private String UGs="�ƻ���������";
    private String USGs="ʵ���ջ�����";
    private String OUnit="��浥λ";	
    private String planQty="��׼�������";
    private String realQty="ʵ�ʿ������";
    private String deviation="���";
    private String unitprice="�ɱ�";
    private String wareHouse="�ֿ�";	
    private String outWh="����ֿ�";	
    private String plannedQty="�ƻ��������";
    private String cpltQty="��ɿ������";
    private String duedate="�ƻ��깤����";
    private String user="����Ա";	

    private static ColOignDocLine CLine;
	private ColOignDocLine(){
		
	}
	public static ColOignDocLine getCLine()
	{
    	if(CLine==null)
    	{
    		CLine=new  ColOignDocLine();
    	}
    	return CLine;
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
		else if(s=="Ymd")
		{
			return dscription;
		}
		else if(s=="length")
		{
			return length;
		}
		else if(s=="Unit")
		{
			return Unit;
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
		else if(s=="user")
		{
			return user;
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
	public String getUSnid() {
		return USnid;
	}
	public void setUSnid(String uSnid) {
		USnid = uSnid;
	}
	public String getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(String docEntry) {
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
	public String getYmd() {
		return Ymd;
	}
	public void setYmd(String Ymd) {
		this.Ymd =Ymd;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getUnitQty() {
		return unitQty;
	}
	public void setUnitQty(String unitQty) {
		this.unitQty = unitQty;
	}
	public String getUGs() {
		return UGs;
	}
	public void setUGs(String ugs) {
		UGs = ugs;
	}
	public String getUSGs() {
		return USGs;
	}
	public void setUSGs(String USGs) {
		this.USGs = USGs;
	}
	public String getOUnit() {
		return OUnit;
	}
	public void setOUnit(String oUnit) {
		this.OUnit = oUnit;
	}
	public String getPlanQty() {
		return planQty;
	}
	public void setPlanQty(String planQty) {
		this.planQty = planQty;
	}
	public String getRealQty() {
		return realQty;
	}
	public void setRealQty(String realQty) {
		this.realQty = realQty;
	}
	public String getDeviation() {
		return deviation;
	}
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getPlannedQty() {
		return plannedQty;
	}
	public void setPlannedQty(String plannedQty) {
		this.plannedQty = plannedQty;
	}
	public String getCpltQty() {
		return cpltQty;
	}
	public void setCpltQty(String cpltQty) {
		this.cpltQty = cpltQty;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
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

	
	
	
	
	
	
}
