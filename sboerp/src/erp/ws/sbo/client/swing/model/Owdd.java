package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Owdd entity. @author MyEclipse Persistence Tools
 */

public class Owdd implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -457821532132676231L;
	private Integer wddCode;
	private Integer wtmCode;
	private Integer ownerId;
	private Integer docEntry;
	private String objType;
	private Timestamp docDate;
	private Integer currStep;
	private String status;
	private String remarks;
	private Short userSign;
	private Timestamp createDate;
	private Short createTime;
	private String isDraft;
	private Short maxReqr;

	// Constructors

	/** default constructor */
	public Owdd() {
	}

	/** minimal constructor */
	public Owdd(Integer wddCode) {
		this.wddCode = wddCode;
	}

	/** full constructor */
	public Owdd(Integer wddCode, Integer wtmCode, Integer ownerId,
			Integer docEntry, String objType, Timestamp docDate,
			Integer currStep, String status, String remarks, Short userSign,
			Timestamp createDate, Short createTime, String isDraft,
			Short maxReqr) {
		this.wddCode = wddCode;
		this.wtmCode = wtmCode;
		this.ownerId = ownerId;
		this.docEntry = docEntry;
		this.objType = objType;
		this.docDate = docDate;
		this.currStep = currStep;
		this.status = status;
		this.remarks = remarks;
		this.userSign = userSign;
		this.createDate = createDate;
		this.createTime = createTime;
		this.isDraft = isDraft;
		this.maxReqr = maxReqr;
	}

	// Property accessors

	public Integer getWddCode() {
		return this.wddCode;
	}

	public void setWddCode(Integer wddCode) {
		this.wddCode = wddCode;
	}

	public Integer getWtmCode() {
		return this.wtmCode;
	}

	public void setWtmCode(Integer wtmCode) {
		this.wtmCode = wtmCode;
	}

	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getDocEntry() {
		return this.docEntry;
	}

	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}

	public String getObjType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public Timestamp getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Timestamp docDate) {
		this.docDate = docDate;
	}

	public Integer getCurrStep() {
		return this.currStep;
	}

	public void setCurrStep(Integer currStep) {
		this.currStep = currStep;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Short getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Short createTime) {
		this.createTime = createTime;
	}

	public String getIsDraft() {
		return this.isDraft;
	}

	public void setIsDraft(String isDraft) {
		this.isDraft = isDraft;
	}

	public Short getMaxReqr() {
		return this.maxReqr;
	}

	public void setMaxReqr(Short maxReqr) {
		this.maxReqr = maxReqr;
	}

}