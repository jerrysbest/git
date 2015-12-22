package erp.ws.sbo.client.swing.model;



public class ColOrctDocLine {
	
	private String id="���";	
	private String docNum="���ݴ���";
	private String docEntry="��Ʊ����";
	private String docDate="��������";
	private String lineNum="��Ʊ�к�";
    private String cardCode="������";
    private String cardName="�������"; 
	private String invType="��Ʊ���";
	private String sumApplied="Ӧ���ܼ�";
	private String sumRemain="Ӧ�����";
	private String nSumRemain="�������";
	private String applied="�տ���";
	private String ocrCode="�ɱ�����";	
    private String UDjNo="���ܵ���";
    
    private static ColOrctDocLine CLine;
	public ColOrctDocLine(){
		
	}
	 public static ColOrctDocLine getCLine()
	    {
	    	if(CLine==null)
	    	{
	    		CLine=new  ColOrctDocLine();
	    	}
	    	return CLine;
	    }
	public ColOrctDocLine(String id) {
		this.setId(id);
	}
	public ColOrctDocLine(String id,
			 String docNum,
			 String docEntry,
			 String docDate,
			 String lineNum,
		     String cardCode,
		     String cardName, 
			 String invType,
			 String sumApplied,
			 String sumRemain,
			 String nSumRemain,
			 String applied,
			 String ocrCode,	
		     String UDjNo
     )
	{
		this.id=id;		
		this.docNum=docNum;
		this.docEntry=docEntry;
		this.docDate=docDate;
		this.lineNum=lineNum;
	    this.cardCode=cardCode;
	    this.cardName=cardName; 
	    this.invType=invType;
	    this.sumApplied=sumApplied;
	    this.sumRemain=sumRemain;
	    this.nSumRemain=nSumRemain;
	    this.applied=applied;
	    this.ocrCode=ocrCode;
	    this.UDjNo=UDjNo;
	}

	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		
		else if(s=="docNum")
		{
			return docNum;
		}
		else if(s=="docEntry")
		{
			return docEntry;
		}
	
		else if(s=="docDate")
		{
			return docDate;
		}
		else if(s=="lineNum")
		{
			return lineNum;
		}
		else if(s=="cardCode")
		{
			return cardCode;
		}
	    else if(s=="cardName")
		{
			return cardName;
		}		
		else if(s=="invType")
		{
			return invType;
		}
		else if(s=="sumApplied")
		{
			return sumApplied;
		}
		else if(s=="sumRemain")
		{
			return sumRemain;
		}
		else if(s=="nSumRemain")
		{
			return nSumRemain;
		}
		else if(s=="applied")
		{
			return applied;
		}
		else if(s=="ocrCode")
		{
			return ocrCode;
		}
	
		else if(s=="UDjNo")
		{
			return UDjNo;
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
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
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
	public String getLineNum() {
		return lineNum;
	}
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
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
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getSumApplied() {
		return sumApplied;
	}
	public void setSumApplied(String sumApplied) {
		this.sumApplied = sumApplied;
	}
	public String getSumRemain() {
		return sumRemain;
	}
	public void setSumRemain(String sumRemain) {
		this.sumRemain = sumRemain;
	}
	public String getNSumRemain() {
		return nSumRemain;
	}
	public void setNSumRemain(String nSumRemain) {
		this.nSumRemain = nSumRemain;
	}
	public String getApplied() {
		return applied;
	}
	public void setApplied(String applied) {
		this.applied = applied;
	}
	public String getOcrCode() {
		return ocrCode;
	}
	public void setOcrCode(String ocrCode) {
		this.ocrCode = ocrCode;
	}
	public String getUDjNo() {
		return UDjNo;
	}
	public void setUDjNo(String uDjNo) {
		UDjNo = uDjNo;
	}
	
		
	
}
