package erp.ws.sbo.client.swing.model;





public class QrColDocLine {
	/*
	 * 
	 * @�б���
	 */
    private String id="���";	
    private String docEntry="���ݴ���";
    private String lineId="�к�";  
	private String itemCode="���ϴ���";
	private String itemName="��������";
	private String UYmd="�Ƿ���ʾ�׶�";
	private String ULength="�����׶�";
	private String unitMsr="���۵�λ";
	private String numPerMsr="��λ����";
    private String UGs="��װ����";
    private String UGjjg="��װ����";
	private String quantity="����";
	private String price="����";
	private String UMjg="�׼۸�";
    private String lineTotal="���"; 
    private String issue="����"; 
    private String result="�������"; 
    private String dalloc="���λ���"; 
    private String resP="������"; 
    private String proRes="������"; 
    private String Impmeas="�Ľ���ʩ"; 
    private String memo="��ע";
    
    
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
