package erp.ws.sbo.client.swing.model;
// Generated Jun 27, 2013 2:41:56 PM by Hibernate Tools 3.4.0.CR1


import java.util.Date;

/**
 * @snstatus generated by hbm2java
 */
public class snstatus  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 4710053955508654413L;
	private String sn;
     private boolean ifWh;
     private String wareHouse;
     private String cardcode;
     private boolean ifPsn;
     private boolean ifInPsn;
     private String paSn;
     private Boolean ifdes;
     private String itemcode;
     private Double length;
     private Double sweight;
     private Double weight;
     private Double pweight;
     private Double cweight;
     private Date datetime;
     private Date updatetime;
     private String mno;
     private String qc;
     private String fwh;
     private String company;
     private Integer qsn;

    public snstatus() {
    }

	
    public snstatus(String sn, boolean ifWh, boolean ifPsn, boolean ifInPsn, Date datetime) {
        this.sn = sn;
        this.ifWh = ifWh;
        this.ifPsn = ifPsn;
        this.ifInPsn = ifInPsn;
        this.datetime = datetime;
    }
    public snstatus(String sn, boolean ifWh, String wareHouse, String cardcode, boolean ifPsn, boolean ifInPsn, String paSn, Boolean ifdes, String itemcode, Double length, Double sweight, Double weight, Double pweight, Double cweight, Date datetime, Date updatetime, String mno, String qc, String fwh, String company,Integer Qsn) {
       this.sn = sn;
       this.ifWh = ifWh;
       this.wareHouse = wareHouse;
       this.cardcode = cardcode;
       this.ifPsn = ifPsn;
       this.ifInPsn = ifInPsn;
       this.paSn = paSn;
       this.ifdes = ifdes;
       this.itemcode = itemcode;
       this.length = length;
       this.sweight = sweight;
       this.weight = weight;
       this.pweight = pweight;
       this.cweight = cweight;
       this.datetime = datetime;
       this.updatetime = updatetime;
       this.mno = mno;
       this.qc = qc;
       this.fwh = fwh;
       this.company = company;
       this.qsn=Qsn;
    }
   
    public String getSn() {
        return this.sn;
    }
    
    public void setSn(String sn) {
        this.sn = sn;
    }
    public boolean isIfWh() {
        return this.ifWh;
    }
    
    public void setIfWh(boolean ifWh) {
        this.ifWh = ifWh;
    }
    public String getWareHouse() {
        return this.wareHouse;
    }
    
    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }
    public String getCardcode() {
        return this.cardcode;
    }
    
    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }
    public boolean isIfPsn() {
        return this.ifPsn;
    }
    
    public void setIfPsn(boolean ifPsn) {
        this.ifPsn = ifPsn;
    }
    public boolean isIfInPsn() {
        return this.ifInPsn;
    }
    
    public void setIfInPsn(boolean ifInPsn) {
        this.ifInPsn = ifInPsn;
    }
    public String getPaSn() {
        return this.paSn;
    }
    
    public void setPaSn(String paSn) {
        this.paSn = paSn;
    }
    public Boolean getIfdes() {
        return this.ifdes;
    }
    
    public void setIfdes(Boolean ifdes) {
        this.ifdes = ifdes;
    }
    public String getItemcode() {
        return this.itemcode;
    }
    
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
    public Double getLength() {
        return this.length;
    }
    
    public void setLength(Double length) {
        this.length = length;
    }
    public Double getSweight() {
        return this.sweight;
    }
    
    public void setSweight(Double sweight) {
        this.sweight = sweight;
    }
    public Double getWeight() {
        return this.weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public Double getPweight() {
        return this.pweight;
    }
    
    public void setPweight(Double pweight) {
        this.pweight = pweight;
    }
    public Double getCweight() {
        return this.cweight;
    }
    
    public void setCweight(Double cweight) {
        this.cweight = cweight;
    }
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    public Date getUpdatetime() {
        return this.updatetime;
    }
    
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public String getMno() {
        return this.mno;
    }
    
    public void setMno(String mno) {
        this.mno = mno;
    }
    public String getQc() {
        return this.qc;
    }
    
    public void setQc(String qc) {
        this.qc = qc;
    }
    public String getFwh() {
        return this.fwh;
    }
    
    public void setFwh(String fwh) {
        this.fwh = fwh;
    }
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }


	public Integer getQsn() {
		return qsn;
	}


	public void setQsn(Integer qsn) {
		this.qsn = qsn;
	}




}


