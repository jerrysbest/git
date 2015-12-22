package erp.ws.sbo.client.swing.model;

import java.util.Date;

public class OrctDocLine {
	
	private Integer id;	
	private Integer docNum;
	private Integer docEntry;
	private Date docDate;
	private Integer lineNum;
    private String cardCode;
    private String cardName; 
	private String invType;
	private Double sumApplied;
	private Double sumRemain;
	private Double nSumRemain;
	private Double applied;
	private String ocrCode;	
    private Integer UDjNo;
    
   

	public OrctDocLine(){
		
	}
	public OrctDocLine(Integer id) {
		this.setId(id);
	}
	public OrctDocLine(Integer id,
			 Integer docNum,
			 Integer docEntry,
			 Date docDate,
			 Integer lineNum,
		     String cardCode,
		     String cardName, 
			 String invType,
			 Double sumApplied,
			 Double sumRemain,
			 Double nSumRemain,
			 Double applied,
			 String ocrCode,	
		     Integer UDjNo
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDocNum() {
		return docNum;
	}
	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}
	public Integer getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}
	public Date getDocDate() {
		return docDate;
	}
	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}
	public Integer getLineNum() {
		return lineNum;
	}
	public void setLineNum(Integer lineNum) {
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
	public Double getSumApplied() {
		return sumApplied;
	}
	public void setSumApplied(Double sumApplied) {
		this.sumApplied = sumApplied;
	}
	public Double getSumRemain() {
		return sumRemain;
	}
	public void setSumRemain(Double sumRemain) {
		this.sumRemain = sumRemain;
	}
	public Double getnSumRemain() {
		return nSumRemain;
	}
	public void setnSumRemain(Double nSumRemain) {
		this.nSumRemain = nSumRemain;
	}
	public Double getApplied() {
		return applied;
	}
	public void setApplied(Double applied) {
		this.applied = applied;
	}
	public String getOcrCode() {
		return ocrCode;
	}
	public void setOcrCode(String ocrCode) {
		this.ocrCode = ocrCode;
	}
	public Integer getUDjNo() {
		return UDjNo;
	}
	public void setUDjNo(Integer uDjNo) {
		UDjNo = uDjNo;
	}
	
	
	
	
}
