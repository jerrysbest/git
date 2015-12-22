package erp.ws.sbo.client.swing.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @wflist entity. @author MyEclipse Persistence Tools
 */

public class wflist  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = -6514963254241283521L;
	 private Integer docEntry;
     private Integer docNum;
     private Integer period;
     private Short instance;
     private Integer series;
     private String handwrtten;
     private String canceled;
     private String object;
     private Integer logInst;
     private Integer userSign;
     private String transfered;
     private String status;
     private Date createDate;
     private Short createTime;
     private Date updateDate;
     private Short updateTime;
     private String dataSource;
     private String UWfTable;
     private Integer UWfLine;
     private Short UApStage;
     private Integer UDraftid;
     private Integer UDocid;
     private Short UWfNode;
     private Short UWfStatus;        
     @SuppressWarnings("rawtypes")
	 private Set wflist1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public wflist() {
    }

	/** minimal constructor */
    public wflist(Integer docEntry) {
        this.docEntry = docEntry;
    }
    
    /** full constructor */
    public wflist(Integer docEntry, Integer docNum, Integer period, Short instance, Integer series, String handwrtten, 
    		String canceled, String object, Integer logInst, Integer userSign, String transfered, String status, 
    		Date createDate, Short createTime, Date updateDate, Short updateTime, String dataSource, 
    		String UWfTable, Integer UWfLine, Short UApStage, Integer UDraftid, Integer UDocid, Short UWfNode, Short UWfStatus,
    		@SuppressWarnings("rawtypes") Set wflist1) {
        this.docEntry = docEntry;
        this.docNum = docNum;
        this.period = period;
        this.instance = instance;
        this.series = series;
        this.handwrtten = handwrtten;
        this.canceled = canceled;
        this.object = object;
        this.logInst = logInst;
        this.userSign = userSign;
        this.transfered = transfered;
        this.status = status;
        this.createDate = createDate;
        this.createTime = createTime;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.dataSource = dataSource;
        this.UWfTable = UWfTable;
        this.UWfLine = UWfLine;
        this.UApStage = UApStage;
        this.UDraftid = UDraftid;
        this.UDocid = UDocid;
        this.UWfNode = UWfNode;
        this.UWfStatus = UWfStatus;
        this.setWflist1(wflist1);
    }

   
    // Property accessors

    public Integer getDocEntry() {
        return this.docEntry;
    }
    
    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public Integer getDocNum() {
        return this.docNum;
    }
    
    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    public Integer getPeriod() {
        return this.period;
    }
    
    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Short getInstance() {
        return this.instance;
    }
    
    public void setInstance(Short instance) {
        this.instance = instance;
    }

    public Integer getSeries() {
        return this.series;
    }
    
    public void setSeries(Integer series) {
        this.series = series;
    }

    public String getHandwrtten() {
        return this.handwrtten;
    }
    
    public void setHandwrtten(String handwrtten) {
        this.handwrtten = handwrtten;
    }

    public String getCanceled() {
        return this.canceled;
    }
    
    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public String getObject() {
        return this.object;
    }
    
    public void setObject(String object) {
        this.object = object;
    }

    public Integer getLogInst() {
        return this.logInst;
    }
    
    public void setLogInst(Integer logInst) {
        this.logInst = logInst;
    }

    public Integer getUserSign() {
        return this.userSign;
    }
    
    public void setUserSign(Integer userSign) {
        this.userSign = userSign;
    }

    public String getTransfered() {
        return this.transfered;
    }
    
    public void setTransfered(String transfered) {
        this.transfered = transfered;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Short createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Short getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Short updateTime) {
        this.updateTime = updateTime;
    }

    public String getDataSource() {
        return this.dataSource;
    }
    
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getUWfTable() {
        return this.UWfTable;
    }
    
    public void setUWfTable(String UWfTable) {
        this.UWfTable = UWfTable;
    }

    public Integer getUWfLine() {
        return this.UWfLine;
    }
    
    public void setUWfLine(Integer UWfLine) {
        this.UWfLine = UWfLine;
    }

    public Short getUApStage() {
        return this.UApStage;
    }
    
    public void setUApStage(Short UApStage) {
        this.UApStage = UApStage;
    }

    public Integer getUDraftid() {
        return this.UDraftid;
    }
    
    public void setUDraftid(Integer UDraftid) {
        this.UDraftid = UDraftid;
    }

    public Integer getUDocid() {
        return this.UDocid;
    }
    
    public void setUDocid(Integer UDocid) {
        this.UDocid = UDocid;
    }

    public Short getUWfNode() {
        return this.UWfNode;
    }
    
    public void setUWfNode(Short UWfNode) {
        this.UWfNode = UWfNode;
    }

    public Short getUWfStatus() {
        return this.UWfStatus;
    }
    
    public void setUWfStatus(Short UWfStatus) {
        this.UWfStatus = UWfStatus;
    }

	@SuppressWarnings("rawtypes")
	public Set getWflist1() {
		return wflist1;
	}

	@SuppressWarnings("rawtypes")
	public void setWflist1(Set wflist1) {
		this.wflist1 = wflist1;
	}

	
   








}