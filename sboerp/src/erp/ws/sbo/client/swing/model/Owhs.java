package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Owhs entity. @author MyEclipse Persistence Tools
 */

public class Owhs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7072975461711921045L;
	private String whsCode;
	private String whsName;
	private Integer intrnalKey;
	private String grpCode;
	private String balInvntAc;
	private String saleCostAc;
	private String transferAc;
	private String locked;
	private String dataSource;
	private Short userSign;
	private String revenuesAc;
	private String varianceAc;
	private String decreasAc;
	private String increasAc;
	private String returnAc;
	private String expensesAc;
	private String eurevenuAc;
	private String euexpensAc;
	private String frRevenuAc;
	private String frExpensAc;
	private String vatGroup;
	private String street;
	private String block;
	private String zipCode;
	private String city;
	private String county;
	private String country;
	private String state;
	private Integer location;
	private String dropShip;
	private String exmptIncom;
	private String useTax;
	private String priceDifAc;
	private String exchangeAc;
	private String balanceAcc;
	private String purchaseAc;
	private String pareturnAc;
	private String purchOfsAc;
	private String fedTaxId;
	private String building;
	private String shpdGdsAct;
	private String vatRevAct;
	private String decresGlAc;
	private String incresGlAc;
	private String nettable;
	private String stokRvlAct;
	private String stkOffsAct;
	private String wipAcct;
	private String wipVarAcct;
	private String costRvlAct;
	private String cstOffsAct;
	private String expClrAct;
	private String expOfstAct;
	private String objType;
	private Short logInstanc;
	private Timestamp createDate;
	private Short userSign2;
	private Timestamp updateDate;
	private String arcmact;
	private String arcmfrnAct;
	private String arcmeuact;
	private String arcmexpAct;
	private String apcmact;
	private String apcmfrnAct;
	private String apcmeuact;
	private String revRetAct;
	private Integer bplid;
	private String ownerCode;
	private String negStckAct;
	private String stkInTnAct;
	private String addrType;
	private String streetNo;
	private String purBalAct;
	private String excisable;
	private String whIcenAct;
	private String whOcenAct;
	private String whShipTo;
	private String wipOffset;
	private String stockOffst;

	// Constructors

	/** default constructor */
	public Owhs() {
	}

	/** minimal constructor */
	public Owhs(String whsCode) {
		this.whsCode = whsCode;
	}

	/** full constructor */
	public Owhs(String whsCode, String whsName, Integer intrnalKey,
			String grpCode, String balInvntAc, String saleCostAc,
			String transferAc, String locked, String dataSource,
			Short userSign, String revenuesAc, String varianceAc,
			String decreasAc, String increasAc, String returnAc,
			String expensesAc, String eurevenuAc, String euexpensAc,
			String frRevenuAc, String frExpensAc, String vatGroup,
			String street, String block, String zipCode, String city,
			String county, String country, String state, Integer location,
			String dropShip, String exmptIncom, String useTax,
			String priceDifAc, String exchangeAc, String balanceAcc,
			String purchaseAc, String pareturnAc, String purchOfsAc,
			String fedTaxId, String building, String shpdGdsAct,
			String vatRevAct, String decresGlAc, String incresGlAc,
			String nettable, String stokRvlAct, String stkOffsAct,
			String wipAcct, String wipVarAcct, String costRvlAct,
			String cstOffsAct, String expClrAct, String expOfstAct,
			String objType, Short logInstanc, Timestamp createDate,
			Short userSign2, Timestamp updateDate, String arcmact,
			String arcmfrnAct, String arcmeuact, String arcmexpAct,
			String apcmact, String apcmfrnAct, String apcmeuact,
			String revRetAct, Integer bplid, String ownerCode,
			String negStckAct, String stkInTnAct, String addrType,
			String streetNo, String purBalAct, String excisable,
			String whIcenAct, String whOcenAct, String whShipTo,
			String wipOffset, String stockOffst) {
		this.whsCode = whsCode;
		this.whsName = whsName;
		this.intrnalKey = intrnalKey;
		this.grpCode = grpCode;
		this.balInvntAc = balInvntAc;
		this.saleCostAc = saleCostAc;
		this.transferAc = transferAc;
		this.locked = locked;
		this.dataSource = dataSource;
		this.userSign = userSign;
		this.revenuesAc = revenuesAc;
		this.varianceAc = varianceAc;
		this.decreasAc = decreasAc;
		this.increasAc = increasAc;
		this.returnAc = returnAc;
		this.expensesAc = expensesAc;
		this.eurevenuAc = eurevenuAc;
		this.euexpensAc = euexpensAc;
		this.frRevenuAc = frRevenuAc;
		this.frExpensAc = frExpensAc;
		this.vatGroup = vatGroup;
		this.street = street;
		this.block = block;
		this.zipCode = zipCode;
		this.city = city;
		this.county = county;
		this.country = country;
		this.state = state;
		this.location = location;
		this.dropShip = dropShip;
		this.exmptIncom = exmptIncom;
		this.useTax = useTax;
		this.priceDifAc = priceDifAc;
		this.exchangeAc = exchangeAc;
		this.balanceAcc = balanceAcc;
		this.purchaseAc = purchaseAc;
		this.pareturnAc = pareturnAc;
		this.purchOfsAc = purchOfsAc;
		this.fedTaxId = fedTaxId;
		this.building = building;
		this.shpdGdsAct = shpdGdsAct;
		this.vatRevAct = vatRevAct;
		this.decresGlAc = decresGlAc;
		this.incresGlAc = incresGlAc;
		this.nettable = nettable;
		this.stokRvlAct = stokRvlAct;
		this.stkOffsAct = stkOffsAct;
		this.wipAcct = wipAcct;
		this.wipVarAcct = wipVarAcct;
		this.costRvlAct = costRvlAct;
		this.cstOffsAct = cstOffsAct;
		this.expClrAct = expClrAct;
		this.expOfstAct = expOfstAct;
		this.objType = objType;
		this.logInstanc = logInstanc;
		this.createDate = createDate;
		this.userSign2 = userSign2;
		this.updateDate = updateDate;
		this.arcmact = arcmact;
		this.arcmfrnAct = arcmfrnAct;
		this.arcmeuact = arcmeuact;
		this.arcmexpAct = arcmexpAct;
		this.apcmact = apcmact;
		this.apcmfrnAct = apcmfrnAct;
		this.apcmeuact = apcmeuact;
		this.revRetAct = revRetAct;
		this.bplid = bplid;
		this.ownerCode = ownerCode;
		this.negStckAct = negStckAct;
		this.stkInTnAct = stkInTnAct;
		this.addrType = addrType;
		this.streetNo = streetNo;
		this.purBalAct = purBalAct;
		this.excisable = excisable;
		this.whIcenAct = whIcenAct;
		this.whOcenAct = whOcenAct;
		this.whShipTo = whShipTo;
		this.wipOffset = wipOffset;
		this.stockOffst = stockOffst;
	}

	// Property accessors

	public String getWhsCode() {
		return this.whsCode;
	}

	public void setWhsCode(String whsCode) {
		this.whsCode = whsCode;
	}

	public String getWhsName() {
		return this.whsName;
	}

	public void setWhsName(String whsName) {
		this.whsName = whsName;
	}

	public Integer getIntrnalKey() {
		return this.intrnalKey;
	}

	public void setIntrnalKey(Integer intrnalKey) {
		this.intrnalKey = intrnalKey;
	}

	public String getGrpCode() {
		return this.grpCode;
	}

	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}

	public String getBalInvntAc() {
		return this.balInvntAc;
	}

	public void setBalInvntAc(String balInvntAc) {
		this.balInvntAc = balInvntAc;
	}

	public String getSaleCostAc() {
		return this.saleCostAc;
	}

	public void setSaleCostAc(String saleCostAc) {
		this.saleCostAc = saleCostAc;
	}

	public String getTransferAc() {
		return this.transferAc;
	}

	public void setTransferAc(String transferAc) {
		this.transferAc = transferAc;
	}

	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

	public String getRevenuesAc() {
		return this.revenuesAc;
	}

	public void setRevenuesAc(String revenuesAc) {
		this.revenuesAc = revenuesAc;
	}

	public String getVarianceAc() {
		return this.varianceAc;
	}

	public void setVarianceAc(String varianceAc) {
		this.varianceAc = varianceAc;
	}

	public String getDecreasAc() {
		return this.decreasAc;
	}

	public void setDecreasAc(String decreasAc) {
		this.decreasAc = decreasAc;
	}

	public String getIncreasAc() {
		return this.increasAc;
	}

	public void setIncreasAc(String increasAc) {
		this.increasAc = increasAc;
	}

	public String getReturnAc() {
		return this.returnAc;
	}

	public void setReturnAc(String returnAc) {
		this.returnAc = returnAc;
	}

	public String getExpensesAc() {
		return this.expensesAc;
	}

	public void setExpensesAc(String expensesAc) {
		this.expensesAc = expensesAc;
	}

	public String getEurevenuAc() {
		return this.eurevenuAc;
	}

	public void setEurevenuAc(String eurevenuAc) {
		this.eurevenuAc = eurevenuAc;
	}

	public String getEuexpensAc() {
		return this.euexpensAc;
	}

	public void setEuexpensAc(String euexpensAc) {
		this.euexpensAc = euexpensAc;
	}

	public String getFrRevenuAc() {
		return this.frRevenuAc;
	}

	public void setFrRevenuAc(String frRevenuAc) {
		this.frRevenuAc = frRevenuAc;
	}

	public String getFrExpensAc() {
		return this.frExpensAc;
	}

	public void setFrExpensAc(String frExpensAc) {
		this.frExpensAc = frExpensAc;
	}

	public String getVatGroup() {
		return this.vatGroup;
	}

	public void setVatGroup(String vatGroup) {
		this.vatGroup = vatGroup;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBlock() {
		return this.block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getLocation() {
		return this.location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getDropShip() {
		return this.dropShip;
	}

	public void setDropShip(String dropShip) {
		this.dropShip = dropShip;
	}

	public String getExmptIncom() {
		return this.exmptIncom;
	}

	public void setExmptIncom(String exmptIncom) {
		this.exmptIncom = exmptIncom;
	}

	public String getUseTax() {
		return this.useTax;
	}

	public void setUseTax(String useTax) {
		this.useTax = useTax;
	}

	public String getPriceDifAc() {
		return this.priceDifAc;
	}

	public void setPriceDifAc(String priceDifAc) {
		this.priceDifAc = priceDifAc;
	}

	public String getExchangeAc() {
		return this.exchangeAc;
	}

	public void setExchangeAc(String exchangeAc) {
		this.exchangeAc = exchangeAc;
	}

	public String getBalanceAcc() {
		return this.balanceAcc;
	}

	public void setBalanceAcc(String balanceAcc) {
		this.balanceAcc = balanceAcc;
	}

	public String getPurchaseAc() {
		return this.purchaseAc;
	}

	public void setPurchaseAc(String purchaseAc) {
		this.purchaseAc = purchaseAc;
	}

	public String getPareturnAc() {
		return this.pareturnAc;
	}

	public void setPareturnAc(String pareturnAc) {
		this.pareturnAc = pareturnAc;
	}

	public String getPurchOfsAc() {
		return this.purchOfsAc;
	}

	public void setPurchOfsAc(String purchOfsAc) {
		this.purchOfsAc = purchOfsAc;
	}

	public String getFedTaxId() {
		return this.fedTaxId;
	}

	public void setFedTaxId(String fedTaxId) {
		this.fedTaxId = fedTaxId;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getShpdGdsAct() {
		return this.shpdGdsAct;
	}

	public void setShpdGdsAct(String shpdGdsAct) {
		this.shpdGdsAct = shpdGdsAct;
	}

	public String getVatRevAct() {
		return this.vatRevAct;
	}

	public void setVatRevAct(String vatRevAct) {
		this.vatRevAct = vatRevAct;
	}

	public String getDecresGlAc() {
		return this.decresGlAc;
	}

	public void setDecresGlAc(String decresGlAc) {
		this.decresGlAc = decresGlAc;
	}

	public String getIncresGlAc() {
		return this.incresGlAc;
	}

	public void setIncresGlAc(String incresGlAc) {
		this.incresGlAc = incresGlAc;
	}

	public String getNettable() {
		return this.nettable;
	}

	public void setNettable(String nettable) {
		this.nettable = nettable;
	}

	public String getStokRvlAct() {
		return this.stokRvlAct;
	}

	public void setStokRvlAct(String stokRvlAct) {
		this.stokRvlAct = stokRvlAct;
	}

	public String getStkOffsAct() {
		return this.stkOffsAct;
	}

	public void setStkOffsAct(String stkOffsAct) {
		this.stkOffsAct = stkOffsAct;
	}

	public String getWipAcct() {
		return this.wipAcct;
	}

	public void setWipAcct(String wipAcct) {
		this.wipAcct = wipAcct;
	}

	public String getWipVarAcct() {
		return this.wipVarAcct;
	}

	public void setWipVarAcct(String wipVarAcct) {
		this.wipVarAcct = wipVarAcct;
	}

	public String getCostRvlAct() {
		return this.costRvlAct;
	}

	public void setCostRvlAct(String costRvlAct) {
		this.costRvlAct = costRvlAct;
	}

	public String getCstOffsAct() {
		return this.cstOffsAct;
	}

	public void setCstOffsAct(String cstOffsAct) {
		this.cstOffsAct = cstOffsAct;
	}

	public String getExpClrAct() {
		return this.expClrAct;
	}

	public void setExpClrAct(String expClrAct) {
		this.expClrAct = expClrAct;
	}

	public String getExpOfstAct() {
		return this.expOfstAct;
	}

	public void setExpOfstAct(String expOfstAct) {
		this.expOfstAct = expOfstAct;
	}

	public String getObjType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public Short getLogInstanc() {
		return this.logInstanc;
	}

	public void setLogInstanc(Short logInstanc) {
		this.logInstanc = logInstanc;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Short getUserSign2() {
		return this.userSign2;
	}

	public void setUserSign2(Short userSign2) {
		this.userSign2 = userSign2;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getArcmact() {
		return this.arcmact;
	}

	public void setArcmact(String arcmact) {
		this.arcmact = arcmact;
	}

	public String getArcmfrnAct() {
		return this.arcmfrnAct;
	}

	public void setArcmfrnAct(String arcmfrnAct) {
		this.arcmfrnAct = arcmfrnAct;
	}

	public String getArcmeuact() {
		return this.arcmeuact;
	}

	public void setArcmeuact(String arcmeuact) {
		this.arcmeuact = arcmeuact;
	}

	public String getArcmexpAct() {
		return this.arcmexpAct;
	}

	public void setArcmexpAct(String arcmexpAct) {
		this.arcmexpAct = arcmexpAct;
	}

	public String getApcmact() {
		return this.apcmact;
	}

	public void setApcmact(String apcmact) {
		this.apcmact = apcmact;
	}

	public String getApcmfrnAct() {
		return this.apcmfrnAct;
	}

	public void setApcmfrnAct(String apcmfrnAct) {
		this.apcmfrnAct = apcmfrnAct;
	}

	public String getApcmeuact() {
		return this.apcmeuact;
	}

	public void setApcmeuact(String apcmeuact) {
		this.apcmeuact = apcmeuact;
	}

	public String getRevRetAct() {
		return this.revRetAct;
	}

	public void setRevRetAct(String revRetAct) {
		this.revRetAct = revRetAct;
	}

	public Integer getBplid() {
		return this.bplid;
	}

	public void setBplid(Integer bplid) {
		this.bplid = bplid;
	}

	public String getOwnerCode() {
		return this.ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getNegStckAct() {
		return this.negStckAct;
	}

	public void setNegStckAct(String negStckAct) {
		this.negStckAct = negStckAct;
	}

	public String getStkInTnAct() {
		return this.stkInTnAct;
	}

	public void setStkInTnAct(String stkInTnAct) {
		this.stkInTnAct = stkInTnAct;
	}

	public String getAddrType() {
		return this.addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public String getStreetNo() {
		return this.streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getPurBalAct() {
		return this.purBalAct;
	}

	public void setPurBalAct(String purBalAct) {
		this.purBalAct = purBalAct;
	}

	public String getExcisable() {
		return this.excisable;
	}

	public void setExcisable(String excisable) {
		this.excisable = excisable;
	}

	public String getWhIcenAct() {
		return this.whIcenAct;
	}

	public void setWhIcenAct(String whIcenAct) {
		this.whIcenAct = whIcenAct;
	}

	public String getWhOcenAct() {
		return this.whOcenAct;
	}

	public void setWhOcenAct(String whOcenAct) {
		this.whOcenAct = whOcenAct;
	}

	public String getWhShipTo() {
		return this.whShipTo;
	}

	public void setWhShipTo(String whShipTo) {
		this.whShipTo = whShipTo;
	}

	public String getWipOffset() {
		return this.wipOffset;
	}

	public void setWipOffset(String wipOffset) {
		this.wipOffset = wipOffset;
	}

	public String getStockOffst() {
		return this.stockOffst;
	}

	public void setStockOffst(String stockOffst) {
		this.stockOffst = stockOffst;
	}

}