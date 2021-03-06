package erp.ws.sbo.client.swing.model;
// Generated Jul 14, 2013 5:55:40 PM by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @pasn generated by hbm2java
 */
@SuppressWarnings("rawtypes")
public class pasn  implements java.io.Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = -2609820992926029298L;
	private int docEntry;
    private int docNum;
    private Integer period;
    private Short instance;
    private Integer series;
    private String handwrtten;
    private String canceled;
    private Integer object;
    private Integer logInst;
    private Integer userSign;
    private String transfered;
    private String status;
    private Date createDate;
    private Short createTime;
    private Date updateDate;
    private Short updateTime;
    private String dataSource;
    private String USn;
    private String UMemo;
   
	private Set asn1 = new HashSet(0);

    public pasn() {
    }



    public pasn(int docEntry, int docNum, Date createDate, Date updateDate, String USn) {
        this.docEntry = docEntry;
        this.docNum = docNum;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.USn = USn;
    }
    public pasn(int docEntry, int docNum, Integer period, Short instance, Integer series, String handwrtten, String canceled, Integer object, Integer logInst, Integer userSign, String transfered, String status, Date createDate, Short createTime, Date updateDate, Short updateTime, String dataSource, String USn, String UMemo, Set asn1) {
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
       this.USn = USn;
       this.UMemo = UMemo;
       this.setAsn1(asn1);
    }
   
    public int getDocEntry() {
        return this.docEntry;
    }
    
    public void setDocEntry(int docEntry) {
        this.docEntry = docEntry;
    }
    public int getDocNum() {
        return this.docNum;
    }
    
    public void setDocNum(int docNum) {
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
    public Integer getObject() {
        return this.object;
    }
    
    public void setObject(Integer object) {
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
    public String getUSn() {
        return this.USn;
    }
    
    public void setUSn(String USn) {
        this.USn = USn;
    }
    public String getUMemo() {
        return this.UMemo;
    }
    
    public void setUMemo(String UMemo) {
        this.UMemo = UMemo;
    }


	public Set getAsn1() {
		return asn1;
	}



	public void setAsn1(Set asn1) {
		this.asn1 = asn1;
	}




}


