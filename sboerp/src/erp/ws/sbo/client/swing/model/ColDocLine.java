package erp.ws.sbo.client.swing.model;





public class ColDocLine {
	/*
	 * 
	 * @列标题
	 */
    private String id="序号";	
    private String USnid="SN行号";
    private String docEntry="单据代码";
    private String lineNum="行号";
    private String baseEntry="基本单据";
    private String baseLine="基本行号";
    private String cardCode="伙伴代码";
    private String cardName="伙伴名称";    
	private String itemCode="物料代码";
	private String dscription="物料描述";
	private String UYmd="是否显示米段";
	private String UMtmd="物料米段";
	private String unitMsr="销售单位";
	private String numPerMsr="单位数量";
	private String whsCode="仓库代码";	
	private String unitMsr2="库存单位";
    private String UGs="包装数量";
    private String USGs="扫描个数";
	private String UGjjg="包装单价";
	private String quantity="数量";
	private String USQuantity="扫描数量";
	private String price="单价";
    private String UZc="总长";
	private String UMjg="米价格";
    private String lineTotal="金额"; 
    private String UZz="总重";  
	private String USfhh="是否换货";
    private String UTsjg="特殊价格"; 
    private String UMtdl="物料大类";
    private String UDjNo="汇总单号";
    private String UDjNozj="追加单号";
    private String UDydj="打印单价";
    private String UJgf="加工费";
    private String UYdy="已打印"; 
    private String UCkck="出库仓库";
    private String UCzy="操作员";
    private String UScwc="生产误差";
	private String URinzz="应收贷向总重";
    private String UDhdate="到货日期";
    private String Freetext="备注";
    
    
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
