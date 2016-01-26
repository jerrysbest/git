package erp.ws.sbo.client.swing.model;


public class ColSNDocLine {
	
	private String  id="���";
	private String  ifdraft="�Ƿ�ݸ�";
	private String  objType="��������";
	private String  docEntry="����";
	private String  lineNum="�к�";
	private String  sn="���к�";
	private String  itemcode="���ϴ���";
	private String  length="�׶�";
	private String  weight="����";
	private String  direction="����";
	private String  ifpasn="�Ƿ�����к�";
	private String  pasn="���������к�";
	private String  warehouse="�ֿ�";
	private String  cardcode="ҵ����";
	private String  memo="��ע";
	private String  workcenter="��������";
	private String  cdatetime="����ʱ��";
    private String  udatetime="����ʱ��";
   
	private ColSNDocLine(){
		
	}
	private static ColSNDocLine CLine;
    public static ColSNDocLine getCLine()
    {
    	if(CLine==null)
    	{
    		CLine=new  ColSNDocLine();
    	}
    	return CLine;
    }
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="ifdraft")
		{
			return ifdraft;
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
		else if(s=="itemcode")
		{
			return itemcode;
		}
		else if(s=="length")
		{
			return length;
		}
		else if(s=="weight")
		{
			return weight;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
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
	public String getCdatetime() {
		return cdatetime;
	}
	public void setCdatetime(String cdatetime) {
		this.cdatetime = cdatetime;
	}
	public String getUdatetime() {
		return udatetime;
	}
	public void setUdatetime(String udatetime) {
		this.udatetime = udatetime;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getIfpasn() {
		return ifpasn;
	}
	public void setIfpasn(String ifpasn) {
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
	public String getIfdraft() {
		return ifdraft;
	}
	public void setIfdraft(String ifdraft) {
		this.ifdraft = ifdraft;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
		
}
