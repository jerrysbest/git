package erp.ws.sbo.client.swing.model;





public class QrColDocLine {
	/*
	 * 
	 * @列标题
	 */
    private String id="序号";	
    private String docEntry="单据代码";
    private String lineId="行号";  
	private String itemCode="物料代码";
	private String itemName="物料描述";
	private String UYmd="是否显示米段";
	private String ULength="物料米段";
	private String unitMsr="销售单位";
	private String numPerMsr="单位数量";
    private String UGs="包装数量";
    private String UGjjg="包装单价";
	private String quantity="数量";
	private String price="单价";
	private String UMjg="米价格";
    private String lineTotal="金额"; 
    private String issue="问题"; 
    private String result="鉴定结果"; 
    private String dalloc="责任划分"; 
    private String resP="责任人"; 
    private String proRes="处理结果"; 
    private String Impmeas="改进措施"; 
    private String memo="备注";
    
    
    private static QrColDocLine CLine;
    private QrColDocLine(){
    	            
    }
    
    public static QrColDocLine getCLine()
    {
    	if(CLine==null)
    	{
    		CLine=new  QrColDocLine();
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

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
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
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getULength() {
		return ULength;
	}
	public void setULength(String uLength) {
		ULength = uLength;
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

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDalloc() {
		return dalloc;
	}

	public void setDalloc(String dalloc) {
		this.dalloc = dalloc;
	}

	public String getResP() {
		return resP;
	}

	public void setResP(String resP) {
		this.resP = resP;
	}

	public String getProRes() {
		return proRes;
	}

	public void setProRes(String proRes) {
		this.proRes = proRes;
	}

	public String getImpmeas() {
		return Impmeas;
	}

	public void setImpmeas(String impmeas) {
		Impmeas = impmeas;
	}

	public String getUGs() {
		return UGs;
	}
	public void setUGs(String uGs) {
		UGs = uGs;
	}

	public String getUYmd() {
		return UYmd;
	}

	public void setUYmd(String uYmd) {
		UYmd = uYmd;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUnitMsr() {
		return unitMsr;
	}

	public void setUnitMsr(String unitMsr) {
		this.unitMsr = unitMsr;
	}

	public String getNumPerMsr() {
		return numPerMsr;
	}

	public void setNumPerMsr(String numPerMsr) {
		this.numPerMsr = numPerMsr;
	}

	public String getUGjjg() {
		return UGjjg;
	}

	public void setUGjjg(String uGjjg) {
		UGjjg = uGjjg;
	}

	
    
	
}
