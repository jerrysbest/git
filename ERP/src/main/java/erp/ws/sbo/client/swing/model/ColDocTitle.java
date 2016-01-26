package erp.ws.sbo.client.swing.model;

public class ColDocTitle {
	/*
	 * 
	 * @列标题
	 */
	private String id="序号";
    private String objType="单据类型";
    private String docEntry="单号";
	private String docDate="日期";
	private String cardCode="伙伴代码";
	private String cardName="伙伴名称";
	private String slpCode="销售员代码";
	private String slpName="销售员名称";
	private String wddCode="工作流号";
	private String docStatus="单据状态";
	private String canceled="已取消";
	private String printed="已打印";
	
	private static ColDocTitle CTitle;
	
	private ColDocTitle()
	{
		
	}
	
	public static ColDocTitle getCTitle()
	{  
		if(CTitle==null)
	   {
		CTitle=new ColDocTitle();
	   }
		return CTitle;
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

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
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

	public String getSlpCode() {
		return slpCode;
	}

	public void setSlpCode(String slpCode) {
		this.slpCode = slpCode;
	}

	public String getSlpName() {
		return slpName;
	}

	public void setSlpName(String slpName) {
		this.slpName = slpName;
	}

	public String getWddCode() {
		return wddCode;
	}

	public void setWddCode(String wddCode) {
		this.wddCode = wddCode;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getPrinted() {
		return printed;
	}

	public void setPrinted(String printed) {
		this.printed = printed;
	}

	public static void setCTitle(ColDocTitle cTitle) {
		CTitle = cTitle;
	}

	
}
