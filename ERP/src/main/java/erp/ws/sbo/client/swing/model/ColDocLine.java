package erp.ws.sbo.client.swing.model;





public class ColDocLine {
	/*
	 * 
	 * @�б���
	 */
    private String id="���";	
    private String USnid="SN�к�";
    private String docEntry="���ݴ���";
    private String lineNum="�к�";
    private String baseEntry="��������";
    private String baseLine="�����к�";
    private String cardCode="������";
    private String cardName="�������";    
	private String itemCode="���ϴ���";
	private String dscription="��������";
	private String UYmd="�Ƿ���ʾ�׶�";
	private String UMtmd="�����׶�";
	private String unitMsr="���۵�λ";
	private String numPerMsr="��λ����";
	private String whsCode="�ֿ����";	
	private String unitMsr2="��浥λ";
    private String UGs="��װ����";
    private String USGs="ɨ�����";
	private String UGjjg="��װ����";
	private String quantity="����";
	private String USQuantity="ɨ������";
	private String price="����";
    private String UZc="�ܳ�";
	private String UMjg="�׼۸�";
    private String lineTotal="���"; 
    private String UZz="����";  
	private String USfhh="�Ƿ񻻻�";
    private String UTsjg="����۸�"; 
    private String UMtdl="���ϴ���";
    private String UDjNo="���ܵ���";
    private String UDjNozj="׷�ӵ���";
    private String UDydj="��ӡ����";
    private String UJgf="�ӹ���";
    private String UYdy="�Ѵ�ӡ"; 
    private String UCkck="����ֿ�";
    private String UCzy="����Ա";
    private String UScwc="�������";
	private String URinzz="Ӧ�մ�������";
    private String UDhdate="��������";
    private String Freetext="��ע";
    
    
    private static ColDocLine CLine;
    private ColDocLine(){
    	            
    }
    
    public static ColDocLine getCLine()
    {
    	if(CLine==null)
    	{
    		CLine=new  ColDocLine();
    	}
    	return CLine;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getBaseEntry() {
		return baseEntry;
	}

	public void setBaseEntry(String baseEntry) {
		this.baseEntry = baseEntry;
	}

	public String getBaseLine() {
		return baseLine;
	}

	public void setBaseLine(String baseLine) {
		this.baseLine = baseLine;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public void setLineTotal(String lineTotal) {
		this.lineTotal = lineTotal;
	}

	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getUMtmd() {
		return UMtmd;
	}
	public void setUMtmd(String uMtmd) {
		UMtmd = uMtmd;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUMjg() {
		return UMjg;
	}
	public void setUMjg(String UMjg) {
		this.UMjg = UMjg;
	}
	public String getUGjjg() {
		return UGjjg;
	}
	public void setUGjjg(String UGjjg) {
		this.UGjjg = UGjjg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLineTotal() {
		return lineTotal;
	}
	public void setClineTotal(String lineTotal) {
		this.lineTotal = lineTotal;
	}
	public String getUZc() {
		return UZc;
	}
	public void setUZc(String uZc) {
		UZc = uZc;
	}
	public String getUZz() {
		return UZz;
	}
	public void setUZz(String uZz) {
		UZz = uZz;
	}
	public String getUSfhh() {
		return USfhh;
	}
	public void setUSfhh(String uSfhh) {
		USfhh = uSfhh;
	}
	public String getUTsjg() {
		return UTsjg;
	}
	public void setUTsjg(String uTsjg) {
		UTsjg = uTsjg;
	}
	public String getUMtdl() {
		return UMtdl;
	}
	public void setUMtdl(String uMtdl) {
		UMtdl = uMtdl;
	}
	public String getUDjNo() {
		return UDjNo;
	}
	public void setUDjNo(String uDjNo) {
		UDjNo = uDjNo;
	}
	public String getUDjNozj() {
		return UDjNozj;
	}
	public void setUDjNozj(String uDjNozj) {
		UDjNozj = uDjNozj;
	}
	public String getUDydj() {
		return UDydj;
	}
	public void setUDydj(String uDydj) {
		UDydj = uDydj;
	}
	public String getUGs() {
		return UGs;
	}
	public void setUGs(String uGs) {
		UGs = uGs;
	}
	public String getUJgf() {
		return UJgf;
	}
	public void setUJgf(String uJgf) {
		UJgf = uJgf;
	}
	public String getUYdy() {
		return UYdy;
	}
	public void setUYdy(String uYdy) {
		UYdy = uYdy;
	}
	public String getUCkck() {
		return UCkck;
	}
	public void setUCkck(String uCkck) {
		UCkck = uCkck;
	}
	public String getUCzy() {
		return UCzy;
	}
	public void setUCzy(String uCzy) {
		UCzy = uCzy;
	}
	public String getUScwc() {
		return UScwc;
	}
	public void setUScwc(String uScwc) {
		UScwc = uScwc;
	}
	public String getURinzz() {
		return URinzz;
	}
	public void setURinzz(String uRinzz) {
		URinzz = uRinzz;
	}
	public String getUDhdate() {
		return UDhdate;
	}
	public void setUDhdate(String uDhdate) {
		UDhdate = uDhdate;
	}

	public String getWhsCode() {
		return whsCode;
	}

	public void setWhsCode(String whsCode) {
		this.whsCode = whsCode;
	}

	public String getUnitMsr() {
		return unitMsr;
	}

	public void setUnitMsr(String unitMsr) {
		this.unitMsr = unitMsr;
	}

	public String getUnitMsr2() {
		return unitMsr2;
	}

	public void setUnitMsr2(String unitMsr2) {
		this.unitMsr2 = unitMsr2;
	}

	public String getNumPerMsr() {
		return numPerMsr;
	}

	public void setNumPerMsr(String numPerMsr) {
		this.numPerMsr = numPerMsr;
	}

	public String getDscription() {
		return dscription;
	}

	public void setDscription(String dscription) {
		this.dscription = dscription;
	}

	public String getUYmd() {
		return UYmd;
	}

	public void setUYmd(String uYmd) {
		UYmd = uYmd;
	}

	public String getUSGs() {
		return USGs;
	}

	public void setUSGs(String uSGs) {
		USGs = uSGs;
	}

	public String getUSQuantity() {
		return USQuantity;
	}

	public void setUSQuantity(String uSQuantity) {
		USQuantity = uSQuantity;
	}

	public String getUSnid() {
		return USnid;
	}

	public void setUSnid(String uSnid) {
		USnid = uSnid;
	}

	public String getFreetext() {
		return Freetext;
	}

	public void setFreetext(String freetext) {
		Freetext = freetext;
	}
    
	
}
