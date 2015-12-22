package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

public class DocTitle {
	private Integer id;
	private String objType;
	private Integer docEntry;		
	private Timestamp docDate;
	private String cardCode;
	private String cardName;
	private String slpCode;
	private String  slpName;
	private String  wddCode;
	private String docStatus;
	private String canceled;
	private String printed;
	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="objType")
		{
			return objType;
		}
	    else if(s=="docEntry")
		{
			return docEntry;
		}
		else if(s=="docDate")
		{
			return docDate;
		}
		else if(s=="cardCode")
		{
			return cardCode;
		}
		else if(s=="cardName")
		{
			return cardName;
		}
		else if(s=="slpCode")
		{
			return slpCode;
		}
		else if(s=="slpName")
		{
			return slpName;
		}
		else if(s=="wddCode")
		{
			return wddCode;
		}
		else if(s=="docStatus")
		{
			return docStatus;
		}
		else if(s=="canceled")
		{
			return canceled;
		}
		else if(s=="printed")
		{
			return printed;
		}
		else
		{
			return null;
		}
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public Integer getDocEntry() {
		return docEntry;
	}

	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}

	public Timestamp getDocDate() {
		return docDate;
	}

	public void setDocDate(Timestamp docDate) {
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

	
}
