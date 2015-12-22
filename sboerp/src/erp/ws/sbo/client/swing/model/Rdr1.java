package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Rdr1 entity. @author MyEclipse Persistence Tools
 */

public class Rdr1 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rdr1Id id;
	private Integer targetType;
	private Integer trgetEntry;
	private String baseRef;
	private Integer baseType;
	private Integer baseEntry;
	private Integer baseLine;
	private String lineStatus;
	private String itemCode;
	private String dscription;
	private Double quantity;
	private Timestamp shipDate;
	private Double openQty;
	private Double price;
	private String currency;
	private Double rate;
	private Double discPrcnt;
	private Double lineTotal;
	private Double totalFrgn;
	private Double openSum;
	private Double openSumFc;
	private String vendorNum;
	private String serialNum;
	private String whsCode;
	private Short slpCode;
	private Double commission;
	private String treeType;
	private String acctCode;
	private String taxStatus;
	private Double grossBuyPr;
	private Double priceBefDi;
	private Timestamp docDate;
	private Integer flags;
	private Double openCreQty;
	private String useBaseUn;
	private String subCatNum;
	private String baseCard;
	private Double totalSumSy;
	private Double openSumSys;
	private String invntSttus;
	private String ocrCode;
	private String project;
	private String codeBars;
	private Double vatPrcnt;
	private String vatGroup;
	private Double priceAfVat;
	private Double height1;
	private Short hght1unit;
	private Double height2;
	private Short hght2unit;
	private Double width1;
	private Short wdth1unit;
	private Double width2;
	private Short wdth2unit;
	private Double length1;
	private Short len1unit;
	private Double length2;
	private Short len2unit;
	private Double volume;
	private Short volUnit;
	private Double weight1;
	private Short wght1unit;
	private Double weight2;
	private Short wght2unit;
	private Double factor1;
	private Double factor2;
	private Double factor3;
	private Double factor4;
	private Double packQty;
	private String updInvntry;
	private Integer baseDocNum;
	private String baseAtCard;
	private String sww;
	private Double vatSum;
	private Double vatSumFrgn;
	private Double vatSumSy;
	private Integer finncPriod;
	private String objType;
	private Short logInstanc;
	private String blockNum;
	private String importLog;
	private Double dedVatSum;
	private Double dedVatSumF;
	private Double dedVatSumS;
	private String isAqcuistn;
	private Double distribSum;
	private Double dstrbSumFc;
	private Double dstrbSumSc;
	private Double grssProfit;
	private Double grssProfSc;
	private Double grssProfFc;
	private Integer visOrder;
	private Double inmprice;
	private Integer poTrgNum;
	private String poTrgEntry;
	private String dropShip;
	private Integer poLineNum;
	private String address;
	private String taxCode;
	private String taxType;
	private String origItem;
	private String backOrdr;
	private String freeTxt;
	private String pickStatus;
	private Double pickOty;
	private Integer pickIdNo;
	private Short trnsCode;
	private Double vatAppld;
	private Double vatAppldFc;
	private Double vatAppldSc;
	private Double baseQty;
	private Double baseOpnQty;
	private Double vatDscntPr;
	private String wtLiable;
	private String deferrTax;
	private Double equVatPer;
	private Double equVatSum;
	private Double equVatSumF;
	private Double equVatSumS;
	private Double lineVat;
	private Double lineVatlF;
	private Double lineVatS;
	private String unitMsr;
	private Double numPerMsr;
	private String ceecflag;
	private Double toStock;
	private Double toDiff;
	private Double exciseAmt;
	private Double taxPerUnit;
	private Double totInclTax;
	private String countryOrg;
	private Double stckDstSum;
	private Double releasQtty;
	private String lineType;
	private String tranType;
	private String text;
	private Integer ownerCode;
	private Double stockPrice;
	private String consumeFct;
	private Double lstByDsSum;
	private Double stckInmpr;
	private Double lstBinmpr;
	private Double stckDstFc;
	private Double stckDstSc;
	private Double lstByDsFc;
	private Double lstByDsSc;
	private Double stockSum;
	private Double stockSumFc;
	private Double stockSumSc;
	private Double stckSumApp;
	private Double stckAppFc;
	private Double stckAppSc;
	private String shipToCode;
	private String shipToDesc;
	private Double stckAppD;
	private Double stckAppDfc;
	private Double stckAppDsc;
	private String basePrice;
	private Double gtotal;
	private Double gtotalFc;
	private Double gtotalSc;
	private String distribExp;
	private String descOw;
	private String detailsOw;
	private Short grossBase;
	private Double vatWoDpm;
	private Double vatWoDpmFc;
	private Double vatWoDpmSc;
	private String cfopcode;
	private String cstcode;
	private Integer usage;
	private String taxOnly;
	private String wtCalced;
	private Double qtyToShip;
	private Double delivrdQty;
	private Double orderedQty;
	private String cogsOcrCod;
	private Integer ciOppLineN;
	private String cogsAcct;
	private String chgAsmBoMw;
	private Timestamp actDelDate;
	private String ocrCode2;
	private String ocrCode3;
	private String ocrCode4;
	private String ocrCode5;
	private Double taxDistSum;
	private Double taxDistSfc;
	private Double taxDistSsc;
	private String postTax;
	private String excisable;
	private Double assblValue;
	private Integer rg23apart1;
	private Integer rg23apart2;
	private Integer rg23cpart1;
	private Integer rg23cpart2;
	private String cogsOcrCo2;
	private String cogsOcrCo3;
	private String cogsOcrCo4;
	private String cogsOcrCo5;
	private String lnExcised;
	private Integer locCode;
	private Double stockValue;
	private Double gpttlBasPr;
	private String unitMsr2;
	private Double numPerMsr2;
	private String specPrice;
	private String cstfIpi;
	private String cstfPis;
	private String cstfCofins;
	private String exLineNo;
	private String isSrvCall;
	private String UMtdl;
	private Double UMtmd;
	private Double UZc;
	private Double UZz;
	private Double UMjg;
	private Double UGjjg;
	private Double UTsjg;
	private Integer UDjNo;
	private Short UDjNozj;
	private String UCkck;
	private Double UScwc;
	private Double USjzl;
	private String UCzy;
	private Integer UGs;
	private Double UDydj;
	private String URinzz;
	private String USfhh;
	private Double UJgf;
	private String UYdy;
	private Timestamp UDhdate;

	// Constructors

	/** default constructor */
	public Rdr1() {
	}

	/** minimal constructor */
	public Rdr1(Rdr1Id id) {
		this.id = id;
	}

	/** full constructor */
	public Rdr1(Rdr1Id id, Integer targetType, Integer trgetEntry,
			String baseRef, Integer baseType, Integer baseEntry,
			Integer baseLine, String lineStatus, String itemCode,
			String dscription, Double quantity, Timestamp shipDate,
			Double openQty, Double price, String currency, Double rate,
			Double discPrcnt, Double lineTotal, Double totalFrgn,
			Double openSum, Double openSumFc, String vendorNum,
			String serialNum, String whsCode, Short slpCode, Double commission,
			String treeType, String acctCode, String taxStatus,
			Double grossBuyPr, Double priceBefDi, Timestamp docDate,
			Integer flags, Double openCreQty, String useBaseUn,
			String subCatNum, String baseCard, Double totalSumSy,
			Double openSumSys, String invntSttus, String ocrCode,
			String project, String codeBars, Double vatPrcnt, String vatGroup,
			Double priceAfVat, Double height1, Short hght1unit, Double height2,
			Short hght2unit, Double width1, Short wdth1unit, Double width2,
			Short wdth2unit, Double length1, Short len1unit, Double length2,
			Short len2unit, Double volume, Short volUnit, Double weight1,
			Short wght1unit, Double weight2, Short wght2unit, Double factor1,
			Double factor2, Double factor3, Double factor4, Double packQty,
			String updInvntry, Integer baseDocNum, String baseAtCard,
			String sww, Double vatSum, Double vatSumFrgn, Double vatSumSy,
			Integer finncPriod, String objType, Short logInstanc,
			String blockNum, String importLog, Double dedVatSum,
			Double dedVatSumF, Double dedVatSumS, String isAqcuistn,
			Double distribSum, Double dstrbSumFc, Double dstrbSumSc,
			Double grssProfit, Double grssProfSc, Double grssProfFc,
			Integer visOrder, Double inmprice, Integer poTrgNum,
			String poTrgEntry, String dropShip, Integer poLineNum,
			String address, String taxCode, String taxType, String origItem,
			String backOrdr, String freeTxt, String pickStatus, Double pickOty,
			Integer pickIdNo, Short trnsCode, Double vatAppld,
			Double vatAppldFc, Double vatAppldSc, Double baseQty,
			Double baseOpnQty, Double vatDscntPr, String wtLiable,
			String deferrTax, Double equVatPer, Double equVatSum,
			Double equVatSumF, Double equVatSumS, Double lineVat,
			Double lineVatlF, Double lineVatS, String unitMsr,
			Double numPerMsr, String ceecflag, Double toStock, Double toDiff,
			Double exciseAmt, Double taxPerUnit, Double totInclTax,
			String countryOrg, Double stckDstSum, Double releasQtty,
			String lineType, String tranType, String text, Integer ownerCode,
			Double stockPrice, String consumeFct, Double lstByDsSum,
			Double stckInmpr, Double lstBinmpr, Double stckDstFc,
			Double stckDstSc, Double lstByDsFc, Double lstByDsSc,
			Double stockSum, Double stockSumFc, Double stockSumSc,
			Double stckSumApp, Double stckAppFc, Double stckAppSc,
			String shipToCode, String shipToDesc, Double stckAppD,
			Double stckAppDfc, Double stckAppDsc, String basePrice,
			Double gtotal, Double gtotalFc, Double gtotalSc, String distribExp,
			String descOw, String detailsOw, Short grossBase, Double vatWoDpm,
			Double vatWoDpmFc, Double vatWoDpmSc, String cfopcode,
			String cstcode, Integer usage, String taxOnly, String wtCalced,
			Double qtyToShip, Double delivrdQty, Double orderedQty,
			String cogsOcrCod, Integer ciOppLineN, String cogsAcct,
			String chgAsmBoMw, Timestamp actDelDate, String ocrCode2,
			String ocrCode3, String ocrCode4, String ocrCode5,
			Double taxDistSum, Double taxDistSfc, Double taxDistSsc,
			String postTax, String excisable, Double assblValue,
			Integer rg23apart1, Integer rg23apart2, Integer rg23cpart1,
			Integer rg23cpart2, String cogsOcrCo2, String cogsOcrCo3,
			String cogsOcrCo4, String cogsOcrCo5, String lnExcised,
			Integer locCode, Double stockValue, Double gpttlBasPr,
			String unitMsr2, Double numPerMsr2, String specPrice,
			String cstfIpi, String cstfPis, String cstfCofins, String exLineNo,
			String isSrvCall, String UMtdl, Double UMtmd, Double UZc,
			Double UZz, Double UMjg, Double UGjjg, Double UTsjg, Integer UDjNo,
			Short UDjNozj, String UCkck, Double UScwc, Double USjzl,
			String UCzy, Integer UGs, Double UDydj, String URinzz,
			String USfhh, Double UJgf, String UYdy, Timestamp UDhdate) {
		this.id = id;
		this.targetType = targetType;
		this.trgetEntry = trgetEntry;
		this.baseRef = baseRef;
		this.baseType = baseType;
		this.baseEntry = baseEntry;
		this.baseLine = baseLine;
		this.lineStatus = lineStatus;
		this.itemCode = itemCode;
		this.dscription = dscription;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.openQty = openQty;
		this.price = price;
		this.currency = currency;
		this.rate = rate;
		this.discPrcnt = discPrcnt;
		this.lineTotal = lineTotal;
		this.totalFrgn = totalFrgn;
		this.openSum = openSum;
		this.openSumFc = openSumFc;
		this.vendorNum = vendorNum;
		this.serialNum = serialNum;
		this.whsCode = whsCode;
		this.slpCode = slpCode;
		this.commission = commission;
		this.treeType = treeType;
		this.acctCode = acctCode;
		this.taxStatus = taxStatus;
		this.grossBuyPr = grossBuyPr;
		this.priceBefDi = priceBefDi;
		this.docDate = docDate;
		this.flags = flags;
		this.openCreQty = openCreQty;
		this.useBaseUn = useBaseUn;
		this.subCatNum = subCatNum;
		this.baseCard = baseCard;
		this.totalSumSy = totalSumSy;
		this.openSumSys = openSumSys;
		this.invntSttus = invntSttus;
		this.ocrCode = ocrCode;
		this.project = project;
		this.codeBars = codeBars;
		this.vatPrcnt = vatPrcnt;
		this.vatGroup = vatGroup;
		this.priceAfVat = priceAfVat;
		this.height1 = height1;
		this.hght1unit = hght1unit;
		this.height2 = height2;
		this.hght2unit = hght2unit;
		this.width1 = width1;
		this.wdth1unit = wdth1unit;
		this.width2 = width2;
		this.wdth2unit = wdth2unit;
		this.length1 = length1;
		this.len1unit = len1unit;
		this.length2 = length2;
		this.len2unit = len2unit;
		this.volume = volume;
		this.volUnit = volUnit;
		this.weight1 = weight1;
		this.wght1unit = wght1unit;
		this.weight2 = weight2;
		this.wght2unit = wght2unit;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.factor3 = factor3;
		this.factor4 = factor4;
		this.packQty = packQty;
		this.updInvntry = updInvntry;
		this.baseDocNum = baseDocNum;
		this.baseAtCard = baseAtCard;
		this.sww = sww;
		this.vatSum = vatSum;
		this.vatSumFrgn = vatSumFrgn;
		this.vatSumSy = vatSumSy;
		this.finncPriod = finncPriod;
		this.objType = objType;
		this.logInstanc = logInstanc;
		this.blockNum = blockNum;
		this.importLog = importLog;
		this.dedVatSum = dedVatSum;
		this.dedVatSumF = dedVatSumF;
		this.dedVatSumS = dedVatSumS;
		this.isAqcuistn = isAqcuistn;
		this.distribSum = distribSum;
		this.dstrbSumFc = dstrbSumFc;
		this.dstrbSumSc = dstrbSumSc;
		this.grssProfit = grssProfit;
		this.grssProfSc = grssProfSc;
		this.grssProfFc = grssProfFc;
		this.visOrder = visOrder;
		this.inmprice = inmprice;
		this.poTrgNum = poTrgNum;
		this.poTrgEntry = poTrgEntry;
		this.dropShip = dropShip;
		this.poLineNum = poLineNum;
		this.address = address;
		this.taxCode = taxCode;
		this.taxType = taxType;
		this.origItem = origItem;
		this.backOrdr = backOrdr;
		this.freeTxt = freeTxt;
		this.pickStatus = pickStatus;
		this.pickOty = pickOty;
		this.pickIdNo = pickIdNo;
		this.trnsCode = trnsCode;
		this.vatAppld = vatAppld;
		this.vatAppldFc = vatAppldFc;
		this.vatAppldSc = vatAppldSc;
		this.baseQty = baseQty;
		this.baseOpnQty = baseOpnQty;
		this.vatDscntPr = vatDscntPr;
		this.wtLiable = wtLiable;
		this.deferrTax = deferrTax;
		this.equVatPer = equVatPer;
		this.equVatSum = equVatSum;
		this.equVatSumF = equVatSumF;
		this.equVatSumS = equVatSumS;
		this.lineVat = lineVat;
		this.lineVatlF = lineVatlF;
		this.lineVatS = lineVatS;
		this.unitMsr = unitMsr;
		this.numPerMsr = numPerMsr;
		this.ceecflag = ceecflag;
		this.toStock = toStock;
		this.toDiff = toDiff;
		this.exciseAmt = exciseAmt;
		this.taxPerUnit = taxPerUnit;
		this.totInclTax = totInclTax;
		this.countryOrg = countryOrg;
		this.stckDstSum = stckDstSum;
		this.releasQtty = releasQtty;
		this.lineType = lineType;
		this.tranType = tranType;
		this.text = text;
		this.ownerCode = ownerCode;
		this.stockPrice = stockPrice;
		this.consumeFct = consumeFct;
		this.lstByDsSum = lstByDsSum;
		this.stckInmpr = stckInmpr;
		this.lstBinmpr = lstBinmpr;
		this.stckDstFc = stckDstFc;
		this.stckDstSc = stckDstSc;
		this.lstByDsFc = lstByDsFc;
		this.lstByDsSc = lstByDsSc;
		this.stockSum = stockSum;
		this.stockSumFc = stockSumFc;
		this.stockSumSc = stockSumSc;
		this.stckSumApp = stckSumApp;
		this.stckAppFc = stckAppFc;
		this.stckAppSc = stckAppSc;
		this.shipToCode = shipToCode;
		this.shipToDesc = shipToDesc;
		this.stckAppD = stckAppD;
		this.stckAppDfc = stckAppDfc;
		this.stckAppDsc = stckAppDsc;
		this.basePrice = basePrice;
		this.gtotal = gtotal;
		this.gtotalFc = gtotalFc;
		this.gtotalSc = gtotalSc;
		this.distribExp = distribExp;
		this.descOw = descOw;
		this.detailsOw = detailsOw;
		this.grossBase = grossBase;
		this.vatWoDpm = vatWoDpm;
		this.vatWoDpmFc = vatWoDpmFc;
		this.vatWoDpmSc = vatWoDpmSc;
		this.cfopcode = cfopcode;
		this.cstcode = cstcode;
		this.usage = usage;
		this.taxOnly = taxOnly;
		this.wtCalced = wtCalced;
		this.qtyToShip = qtyToShip;
		this.delivrdQty = delivrdQty;
		this.orderedQty = orderedQty;
		this.cogsOcrCod = cogsOcrCod;
		this.ciOppLineN = ciOppLineN;
		this.cogsAcct = cogsAcct;
		this.chgAsmBoMw = chgAsmBoMw;
		this.actDelDate = actDelDate;
		this.ocrCode2 = ocrCode2;
		this.ocrCode3 = ocrCode3;
		this.ocrCode4 = ocrCode4;
		this.ocrCode5 = ocrCode5;
		this.taxDistSum = taxDistSum;
		this.taxDistSfc = taxDistSfc;
		this.taxDistSsc = taxDistSsc;
		this.postTax = postTax;
		this.excisable = excisable;
		this.assblValue = assblValue;
		this.rg23apart1 = rg23apart1;
		this.rg23apart2 = rg23apart2;
		this.rg23cpart1 = rg23cpart1;
		this.rg23cpart2 = rg23cpart2;
		this.cogsOcrCo2 = cogsOcrCo2;
		this.cogsOcrCo3 = cogsOcrCo3;
		this.cogsOcrCo4 = cogsOcrCo4;
		this.cogsOcrCo5 = cogsOcrCo5;
		this.lnExcised = lnExcised;
		this.locCode = locCode;
		this.stockValue = stockValue;
		this.gpttlBasPr = gpttlBasPr;
		this.unitMsr2 = unitMsr2;
		this.numPerMsr2 = numPerMsr2;
		this.specPrice = specPrice;
		this.cstfIpi = cstfIpi;
		this.cstfPis = cstfPis;
		this.cstfCofins = cstfCofins;
		this.exLineNo = exLineNo;
		this.isSrvCall = isSrvCall;
		this.UMtdl = UMtdl;
		this.UMtmd = UMtmd;
		this.UZc = UZc;
		this.UZz = UZz;
		this.UMjg = UMjg;
		this.UGjjg = UGjjg;
		this.UTsjg = UTsjg;
		this.UDjNo = UDjNo;
		this.UDjNozj = UDjNozj;
		this.UCkck = UCkck;
		this.UScwc = UScwc;
		this.USjzl = USjzl;
		this.UCzy = UCzy;
		this.UGs = UGs;
		this.UDydj = UDydj;
		this.URinzz = URinzz;
		this.USfhh = USfhh;
		this.UJgf = UJgf;
		this.UYdy = UYdy;
		this.UDhdate = UDhdate;
	}

	// Property accessors

	public Rdr1Id getId() {
		return this.id;
	}

	public void setId(Rdr1Id id) {
		this.id = id;
	}

	public Integer getTargetType() {
		return this.targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public Integer getTrgetEntry() {
		return this.trgetEntry;
	}

	public void setTrgetEntry(Integer trgetEntry) {
		this.trgetEntry = trgetEntry;
	}

	public String getBaseRef() {
		return this.baseRef;
	}

	public void setBaseRef(String baseRef) {
		this.baseRef = baseRef;
	}

	public Integer getBaseType() {
		return this.baseType;
	}

	public void setBaseType(Integer baseType) {
		this.baseType = baseType;
	}

	public Integer getBaseEntry() {
		return this.baseEntry;
	}

	public void setBaseEntry(Integer baseEntry) {
		this.baseEntry = baseEntry;
	}

	public Integer getBaseLine() {
		return this.baseLine;
	}

	public void setBaseLine(Integer baseLine) {
		this.baseLine = baseLine;
	}

	public String getLineStatus() {
		return this.lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDscription() {
		return this.dscription;
	}

	public void setDscription(String dscription) {
		this.dscription = dscription;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Timestamp getShipDate() {
		return this.shipDate;
	}

	public void setShipDate(Timestamp shipDate) {
		this.shipDate = shipDate;
	}

	public Double getOpenQty() {
		return this.openQty;
	}

	public void setOpenQty(Double openQty) {
		this.openQty = openQty;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getDiscPrcnt() {
		return this.discPrcnt;
	}

	public void setDiscPrcnt(Double discPrcnt) {
		this.discPrcnt = discPrcnt;
	}

	public Double getLineTotal() {
		return this.lineTotal;
	}

	public void setLineTotal(Double lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Double getTotalFrgn() {
		return this.totalFrgn;
	}

	public void setTotalFrgn(Double totalFrgn) {
		this.totalFrgn = totalFrgn;
	}

	public Double getOpenSum() {
		return this.openSum;
	}

	public void setOpenSum(Double openSum) {
		this.openSum = openSum;
	}

	public Double getOpenSumFc() {
		return this.openSumFc;
	}

	public void setOpenSumFc(Double openSumFc) {
		this.openSumFc = openSumFc;
	}

	public String getVendorNum() {
		return this.vendorNum;
	}

	public void setVendorNum(String vendorNum) {
		this.vendorNum = vendorNum;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getWhsCode() {
		return this.whsCode;
	}

	public void setWhsCode(String whsCode) {
		this.whsCode = whsCode;
	}

	public Short getSlpCode() {
		return this.slpCode;
	}

	public void setSlpCode(Short slpCode) {
		this.slpCode = slpCode;
	}

	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getTreeType() {
		return this.treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getAcctCode() {
		return this.acctCode;
	}

	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}

	public String getTaxStatus() {
		return this.taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public Double getGrossBuyPr() {
		return this.grossBuyPr;
	}

	public void setGrossBuyPr(Double grossBuyPr) {
		this.grossBuyPr = grossBuyPr;
	}

	public Double getPriceBefDi() {
		return this.priceBefDi;
	}

	public void setPriceBefDi(Double priceBefDi) {
		this.priceBefDi = priceBefDi;
	}

	public Timestamp getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Timestamp docDate) {
		this.docDate = docDate;
	}

	public Integer getFlags() {
		return this.flags;
	}

	public void setFlags(Integer flags) {
		this.flags = flags;
	}

	public Double getOpenCreQty() {
		return this.openCreQty;
	}

	public void setOpenCreQty(Double openCreQty) {
		this.openCreQty = openCreQty;
	}

	public String getUseBaseUn() {
		return this.useBaseUn;
	}

	public void setUseBaseUn(String useBaseUn) {
		this.useBaseUn = useBaseUn;
	}

	public String getSubCatNum() {
		return this.subCatNum;
	}

	public void setSubCatNum(String subCatNum) {
		this.subCatNum = subCatNum;
	}

	public String getBaseCard() {
		return this.baseCard;
	}

	public void setBaseCard(String baseCard) {
		this.baseCard = baseCard;
	}

	public Double getTotalSumSy() {
		return this.totalSumSy;
	}

	public void setTotalSumSy(Double totalSumSy) {
		this.totalSumSy = totalSumSy;
	}

	public Double getOpenSumSys() {
		return this.openSumSys;
	}

	public void setOpenSumSys(Double openSumSys) {
		this.openSumSys = openSumSys;
	}

	public String getInvntSttus() {
		return this.invntSttus;
	}

	public void setInvntSttus(String invntSttus) {
		this.invntSttus = invntSttus;
	}

	public String getOcrCode() {
		return this.ocrCode;
	}

	public void setOcrCode(String ocrCode) {
		this.ocrCode = ocrCode;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCodeBars() {
		return this.codeBars;
	}

	public void setCodeBars(String codeBars) {
		this.codeBars = codeBars;
	}

	public Double getVatPrcnt() {
		return this.vatPrcnt;
	}

	public void setVatPrcnt(Double vatPrcnt) {
		this.vatPrcnt = vatPrcnt;
	}

	public String getVatGroup() {
		return this.vatGroup;
	}

	public void setVatGroup(String vatGroup) {
		this.vatGroup = vatGroup;
	}

	public Double getPriceAfVat() {
		return this.priceAfVat;
	}

	public void setPriceAfVat(Double priceAfVat) {
		this.priceAfVat = priceAfVat;
	}

	public Double getHeight1() {
		return this.height1;
	}

	public void setHeight1(Double height1) {
		this.height1 = height1;
	}

	public Short getHght1unit() {
		return this.hght1unit;
	}

	public void setHght1unit(Short hght1unit) {
		this.hght1unit = hght1unit;
	}

	public Double getHeight2() {
		return this.height2;
	}

	public void setHeight2(Double height2) {
		this.height2 = height2;
	}

	public Short getHght2unit() {
		return this.hght2unit;
	}

	public void setHght2unit(Short hght2unit) {
		this.hght2unit = hght2unit;
	}

	public Double getWidth1() {
		return this.width1;
	}

	public void setWidth1(Double width1) {
		this.width1 = width1;
	}

	public Short getWdth1unit() {
		return this.wdth1unit;
	}

	public void setWdth1unit(Short wdth1unit) {
		this.wdth1unit = wdth1unit;
	}

	public Double getWidth2() {
		return this.width2;
	}

	public void setWidth2(Double width2) {
		this.width2 = width2;
	}

	public Short getWdth2unit() {
		return this.wdth2unit;
	}

	public void setWdth2unit(Short wdth2unit) {
		this.wdth2unit = wdth2unit;
	}

	public Double getLength1() {
		return this.length1;
	}

	public void setLength1(Double length1) {
		this.length1 = length1;
	}

	public Short getLen1unit() {
		return this.len1unit;
	}

	public void setLen1unit(Short len1unit) {
		this.len1unit = len1unit;
	}

	public Double getLength2() {
		return this.length2;
	}

	public void setLength2(Double length2) {
		this.length2 = length2;
	}

	public Short getLen2unit() {
		return this.len2unit;
	}

	public void setLen2unit(Short len2unit) {
		this.len2unit = len2unit;
	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Short getVolUnit() {
		return this.volUnit;
	}

	public void setVolUnit(Short volUnit) {
		this.volUnit = volUnit;
	}

	public Double getWeight1() {
		return this.weight1;
	}

	public void setWeight1(Double weight1) {
		this.weight1 = weight1;
	}

	public Short getWght1unit() {
		return this.wght1unit;
	}

	public void setWght1unit(Short wght1unit) {
		this.wght1unit = wght1unit;
	}

	public Double getWeight2() {
		return this.weight2;
	}

	public void setWeight2(Double weight2) {
		this.weight2 = weight2;
	}

	public Short getWght2unit() {
		return this.wght2unit;
	}

	public void setWght2unit(Short wght2unit) {
		this.wght2unit = wght2unit;
	}

	public Double getFactor1() {
		return this.factor1;
	}

	public void setFactor1(Double factor1) {
		this.factor1 = factor1;
	}

	public Double getFactor2() {
		return this.factor2;
	}

	public void setFactor2(Double factor2) {
		this.factor2 = factor2;
	}

	public Double getFactor3() {
		return this.factor3;
	}

	public void setFactor3(Double factor3) {
		this.factor3 = factor3;
	}

	public Double getFactor4() {
		return this.factor4;
	}

	public void setFactor4(Double factor4) {
		this.factor4 = factor4;
	}

	public Double getPackQty() {
		return this.packQty;
	}

	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}

	public String getUpdInvntry() {
		return this.updInvntry;
	}

	public void setUpdInvntry(String updInvntry) {
		this.updInvntry = updInvntry;
	}

	public Integer getBaseDocNum() {
		return this.baseDocNum;
	}

	public void setBaseDocNum(Integer baseDocNum) {
		this.baseDocNum = baseDocNum;
	}

	public String getBaseAtCard() {
		return this.baseAtCard;
	}

	public void setBaseAtCard(String baseAtCard) {
		this.baseAtCard = baseAtCard;
	}

	public String getSww() {
		return this.sww;
	}

	public void setSww(String sww) {
		this.sww = sww;
	}

	public Double getVatSum() {
		return this.vatSum;
	}

	public void setVatSum(Double vatSum) {
		this.vatSum = vatSum;
	}

	public Double getVatSumFrgn() {
		return this.vatSumFrgn;
	}

	public void setVatSumFrgn(Double vatSumFrgn) {
		this.vatSumFrgn = vatSumFrgn;
	}

	public Double getVatSumSy() {
		return this.vatSumSy;
	}

	public void setVatSumSy(Double vatSumSy) {
		this.vatSumSy = vatSumSy;
	}

	public Integer getFinncPriod() {
		return this.finncPriod;
	}

	public void setFinncPriod(Integer finncPriod) {
		this.finncPriod = finncPriod;
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

	public String getBlockNum() {
		return this.blockNum;
	}

	public void setBlockNum(String blockNum) {
		this.blockNum = blockNum;
	}

	public String getImportLog() {
		return this.importLog;
	}

	public void setImportLog(String importLog) {
		this.importLog = importLog;
	}

	public Double getDedVatSum() {
		return this.dedVatSum;
	}

	public void setDedVatSum(Double dedVatSum) {
		this.dedVatSum = dedVatSum;
	}

	public Double getDedVatSumF() {
		return this.dedVatSumF;
	}

	public void setDedVatSumF(Double dedVatSumF) {
		this.dedVatSumF = dedVatSumF;
	}

	public Double getDedVatSumS() {
		return this.dedVatSumS;
	}

	public void setDedVatSumS(Double dedVatSumS) {
		this.dedVatSumS = dedVatSumS;
	}

	public String getIsAqcuistn() {
		return this.isAqcuistn;
	}

	public void setIsAqcuistn(String isAqcuistn) {
		this.isAqcuistn = isAqcuistn;
	}

	public Double getDistribSum() {
		return this.distribSum;
	}

	public void setDistribSum(Double distribSum) {
		this.distribSum = distribSum;
	}

	public Double getDstrbSumFc() {
		return this.dstrbSumFc;
	}

	public void setDstrbSumFc(Double dstrbSumFc) {
		this.dstrbSumFc = dstrbSumFc;
	}

	public Double getDstrbSumSc() {
		return this.dstrbSumSc;
	}

	public void setDstrbSumSc(Double dstrbSumSc) {
		this.dstrbSumSc = dstrbSumSc;
	}

	public Double getGrssProfit() {
		return this.grssProfit;
	}

	public void setGrssProfit(Double grssProfit) {
		this.grssProfit = grssProfit;
	}

	public Double getGrssProfSc() {
		return this.grssProfSc;
	}

	public void setGrssProfSc(Double grssProfSc) {
		this.grssProfSc = grssProfSc;
	}

	public Double getGrssProfFc() {
		return this.grssProfFc;
	}

	public void setGrssProfFc(Double grssProfFc) {
		this.grssProfFc = grssProfFc;
	}

	public Integer getVisOrder() {
		return this.visOrder;
	}

	public void setVisOrder(Integer visOrder) {
		this.visOrder = visOrder;
	}

	public Double getInmprice() {
		return this.inmprice;
	}

	public void setInmprice(Double inmprice) {
		this.inmprice = inmprice;
	}

	public Integer getPoTrgNum() {
		return this.poTrgNum;
	}

	public void setPoTrgNum(Integer poTrgNum) {
		this.poTrgNum = poTrgNum;
	}

	public String getPoTrgEntry() {
		return this.poTrgEntry;
	}

	public void setPoTrgEntry(String poTrgEntry) {
		this.poTrgEntry = poTrgEntry;
	}

	public String getDropShip() {
		return this.dropShip;
	}

	public void setDropShip(String dropShip) {
		this.dropShip = dropShip;
	}

	public Integer getPoLineNum() {
		return this.poLineNum;
	}

	public void setPoLineNum(Integer poLineNum) {
		this.poLineNum = poLineNum;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxType() {
		return this.taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getOrigItem() {
		return this.origItem;
	}

	public void setOrigItem(String origItem) {
		this.origItem = origItem;
	}

	public String getBackOrdr() {
		return this.backOrdr;
	}

	public void setBackOrdr(String backOrdr) {
		this.backOrdr = backOrdr;
	}

	public String getFreeTxt() {
		return this.freeTxt;
	}

	public void setFreeTxt(String freeTxt) {
		this.freeTxt = freeTxt;
	}

	public String getPickStatus() {
		return this.pickStatus;
	}

	public void setPickStatus(String pickStatus) {
		this.pickStatus = pickStatus;
	}

	public Double getPickOty() {
		return this.pickOty;
	}

	public void setPickOty(Double pickOty) {
		this.pickOty = pickOty;
	}

	public Integer getPickIdNo() {
		return this.pickIdNo;
	}

	public void setPickIdNo(Integer pickIdNo) {
		this.pickIdNo = pickIdNo;
	}

	public Short getTrnsCode() {
		return this.trnsCode;
	}

	public void setTrnsCode(Short trnsCode) {
		this.trnsCode = trnsCode;
	}

	public Double getVatAppld() {
		return this.vatAppld;
	}

	public void setVatAppld(Double vatAppld) {
		this.vatAppld = vatAppld;
	}

	public Double getVatAppldFc() {
		return this.vatAppldFc;
	}

	public void setVatAppldFc(Double vatAppldFc) {
		this.vatAppldFc = vatAppldFc;
	}

	public Double getVatAppldSc() {
		return this.vatAppldSc;
	}

	public void setVatAppldSc(Double vatAppldSc) {
		this.vatAppldSc = vatAppldSc;
	}

	public Double getBaseQty() {
		return this.baseQty;
	}

	public void setBaseQty(Double baseQty) {
		this.baseQty = baseQty;
	}

	public Double getBaseOpnQty() {
		return this.baseOpnQty;
	}

	public void setBaseOpnQty(Double baseOpnQty) {
		this.baseOpnQty = baseOpnQty;
	}

	public Double getVatDscntPr() {
		return this.vatDscntPr;
	}

	public void setVatDscntPr(Double vatDscntPr) {
		this.vatDscntPr = vatDscntPr;
	}

	public String getWtLiable() {
		return this.wtLiable;
	}

	public void setWtLiable(String wtLiable) {
		this.wtLiable = wtLiable;
	}

	public String getDeferrTax() {
		return this.deferrTax;
	}

	public void setDeferrTax(String deferrTax) {
		this.deferrTax = deferrTax;
	}

	public Double getEquVatPer() {
		return this.equVatPer;
	}

	public void setEquVatPer(Double equVatPer) {
		this.equVatPer = equVatPer;
	}

	public Double getEquVatSum() {
		return this.equVatSum;
	}

	public void setEquVatSum(Double equVatSum) {
		this.equVatSum = equVatSum;
	}

	public Double getEquVatSumF() {
		return this.equVatSumF;
	}

	public void setEquVatSumF(Double equVatSumF) {
		this.equVatSumF = equVatSumF;
	}

	public Double getEquVatSumS() {
		return this.equVatSumS;
	}

	public void setEquVatSumS(Double equVatSumS) {
		this.equVatSumS = equVatSumS;
	}

	public Double getLineVat() {
		return this.lineVat;
	}

	public void setLineVat(Double lineVat) {
		this.lineVat = lineVat;
	}

	public Double getLineVatlF() {
		return this.lineVatlF;
	}

	public void setLineVatlF(Double lineVatlF) {
		this.lineVatlF = lineVatlF;
	}

	public Double getLineVatS() {
		return this.lineVatS;
	}

	public void setLineVatS(Double lineVatS) {
		this.lineVatS = lineVatS;
	}

	public String getUnitMsr() {
		return this.unitMsr;
	}

	public void setUnitMsr(String unitMsr) {
		this.unitMsr = unitMsr;
	}

	public Double getNumPerMsr() {
		return this.numPerMsr;
	}

	public void setNumPerMsr(Double numPerMsr) {
		this.numPerMsr = numPerMsr;
	}

	public String getCeecflag() {
		return this.ceecflag;
	}

	public void setCeecflag(String ceecflag) {
		this.ceecflag = ceecflag;
	}

	public Double getToStock() {
		return this.toStock;
	}

	public void setToStock(Double toStock) {
		this.toStock = toStock;
	}

	public Double getToDiff() {
		return this.toDiff;
	}

	public void setToDiff(Double toDiff) {
		this.toDiff = toDiff;
	}

	public Double getExciseAmt() {
		return this.exciseAmt;
	}

	public void setExciseAmt(Double exciseAmt) {
		this.exciseAmt = exciseAmt;
	}

	public Double getTaxPerUnit() {
		return this.taxPerUnit;
	}

	public void setTaxPerUnit(Double taxPerUnit) {
		this.taxPerUnit = taxPerUnit;
	}

	public Double getTotInclTax() {
		return this.totInclTax;
	}

	public void setTotInclTax(Double totInclTax) {
		this.totInclTax = totInclTax;
	}

	public String getCountryOrg() {
		return this.countryOrg;
	}

	public void setCountryOrg(String countryOrg) {
		this.countryOrg = countryOrg;
	}

	public Double getStckDstSum() {
		return this.stckDstSum;
	}

	public void setStckDstSum(Double stckDstSum) {
		this.stckDstSum = stckDstSum;
	}

	public Double getReleasQtty() {
		return this.releasQtty;
	}

	public void setReleasQtty(Double releasQtty) {
		this.releasQtty = releasQtty;
	}

	public String getLineType() {
		return this.lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOwnerCode() {
		return this.ownerCode;
	}

	public void setOwnerCode(Integer ownerCode) {
		this.ownerCode = ownerCode;
	}

	public Double getStockPrice() {
		return this.stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getConsumeFct() {
		return this.consumeFct;
	}

	public void setConsumeFct(String consumeFct) {
		this.consumeFct = consumeFct;
	}

	public Double getLstByDsSum() {
		return this.lstByDsSum;
	}

	public void setLstByDsSum(Double lstByDsSum) {
		this.lstByDsSum = lstByDsSum;
	}

	public Double getStckInmpr() {
		return this.stckInmpr;
	}

	public void setStckInmpr(Double stckInmpr) {
		this.stckInmpr = stckInmpr;
	}

	public Double getLstBinmpr() {
		return this.lstBinmpr;
	}

	public void setLstBinmpr(Double lstBinmpr) {
		this.lstBinmpr = lstBinmpr;
	}

	public Double getStckDstFc() {
		return this.stckDstFc;
	}

	public void setStckDstFc(Double stckDstFc) {
		this.stckDstFc = stckDstFc;
	}

	public Double getStckDstSc() {
		return this.stckDstSc;
	}

	public void setStckDstSc(Double stckDstSc) {
		this.stckDstSc = stckDstSc;
	}

	public Double getLstByDsFc() {
		return this.lstByDsFc;
	}

	public void setLstByDsFc(Double lstByDsFc) {
		this.lstByDsFc = lstByDsFc;
	}

	public Double getLstByDsSc() {
		return this.lstByDsSc;
	}

	public void setLstByDsSc(Double lstByDsSc) {
		this.lstByDsSc = lstByDsSc;
	}

	public Double getStockSum() {
		return this.stockSum;
	}

	public void setStockSum(Double stockSum) {
		this.stockSum = stockSum;
	}

	public Double getStockSumFc() {
		return this.stockSumFc;
	}

	public void setStockSumFc(Double stockSumFc) {
		this.stockSumFc = stockSumFc;
	}

	public Double getStockSumSc() {
		return this.stockSumSc;
	}

	public void setStockSumSc(Double stockSumSc) {
		this.stockSumSc = stockSumSc;
	}

	public Double getStckSumApp() {
		return this.stckSumApp;
	}

	public void setStckSumApp(Double stckSumApp) {
		this.stckSumApp = stckSumApp;
	}

	public Double getStckAppFc() {
		return this.stckAppFc;
	}

	public void setStckAppFc(Double stckAppFc) {
		this.stckAppFc = stckAppFc;
	}

	public Double getStckAppSc() {
		return this.stckAppSc;
	}

	public void setStckAppSc(Double stckAppSc) {
		this.stckAppSc = stckAppSc;
	}

	public String getShipToCode() {
		return this.shipToCode;
	}

	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}

	public String getShipToDesc() {
		return this.shipToDesc;
	}

	public void setShipToDesc(String shipToDesc) {
		this.shipToDesc = shipToDesc;
	}

	public Double getStckAppD() {
		return this.stckAppD;
	}

	public void setStckAppD(Double stckAppD) {
		this.stckAppD = stckAppD;
	}

	public Double getStckAppDfc() {
		return this.stckAppDfc;
	}

	public void setStckAppDfc(Double stckAppDfc) {
		this.stckAppDfc = stckAppDfc;
	}

	public Double getStckAppDsc() {
		return this.stckAppDsc;
	}

	public void setStckAppDsc(Double stckAppDsc) {
		this.stckAppDsc = stckAppDsc;
	}

	public String getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public Double getGtotal() {
		return this.gtotal;
	}

	public void setGtotal(Double gtotal) {
		this.gtotal = gtotal;
	}

	public Double getGtotalFc() {
		return this.gtotalFc;
	}

	public void setGtotalFc(Double gtotalFc) {
		this.gtotalFc = gtotalFc;
	}

	public Double getGtotalSc() {
		return this.gtotalSc;
	}

	public void setGtotalSc(Double gtotalSc) {
		this.gtotalSc = gtotalSc;
	}

	public String getDistribExp() {
		return this.distribExp;
	}

	public void setDistribExp(String distribExp) {
		this.distribExp = distribExp;
	}

	public String getDescOw() {
		return this.descOw;
	}

	public void setDescOw(String descOw) {
		this.descOw = descOw;
	}

	public String getDetailsOw() {
		return this.detailsOw;
	}

	public void setDetailsOw(String detailsOw) {
		this.detailsOw = detailsOw;
	}

	public Short getGrossBase() {
		return this.grossBase;
	}

	public void setGrossBase(Short grossBase) {
		this.grossBase = grossBase;
	}

	public Double getVatWoDpm() {
		return this.vatWoDpm;
	}

	public void setVatWoDpm(Double vatWoDpm) {
		this.vatWoDpm = vatWoDpm;
	}

	public Double getVatWoDpmFc() {
		return this.vatWoDpmFc;
	}

	public void setVatWoDpmFc(Double vatWoDpmFc) {
		this.vatWoDpmFc = vatWoDpmFc;
	}

	public Double getVatWoDpmSc() {
		return this.vatWoDpmSc;
	}

	public void setVatWoDpmSc(Double vatWoDpmSc) {
		this.vatWoDpmSc = vatWoDpmSc;
	}

	public String getCfopcode() {
		return this.cfopcode;
	}

	public void setCfopcode(String cfopcode) {
		this.cfopcode = cfopcode;
	}

	public String getCstcode() {
		return this.cstcode;
	}

	public void setCstcode(String cstcode) {
		this.cstcode = cstcode;
	}

	public Integer getUsage() {
		return this.usage;
	}

	public void setUsage(Integer usage) {
		this.usage = usage;
	}

	public String getTaxOnly() {
		return this.taxOnly;
	}

	public void setTaxOnly(String taxOnly) {
		this.taxOnly = taxOnly;
	}

	public String getWtCalced() {
		return this.wtCalced;
	}

	public void setWtCalced(String wtCalced) {
		this.wtCalced = wtCalced;
	}

	public Double getQtyToShip() {
		return this.qtyToShip;
	}

	public void setQtyToShip(Double qtyToShip) {
		this.qtyToShip = qtyToShip;
	}

	public Double getDelivrdQty() {
		return this.delivrdQty;
	}

	public void setDelivrdQty(Double delivrdQty) {
		this.delivrdQty = delivrdQty;
	}

	public Double getOrderedQty() {
		return this.orderedQty;
	}

	public void setOrderedQty(Double orderedQty) {
		this.orderedQty = orderedQty;
	}

	public String getCogsOcrCod() {
		return this.cogsOcrCod;
	}

	public void setCogsOcrCod(String cogsOcrCod) {
		this.cogsOcrCod = cogsOcrCod;
	}

	public Integer getCiOppLineN() {
		return this.ciOppLineN;
	}

	public void setCiOppLineN(Integer ciOppLineN) {
		this.ciOppLineN = ciOppLineN;
	}

	public String getCogsAcct() {
		return this.cogsAcct;
	}

	public void setCogsAcct(String cogsAcct) {
		this.cogsAcct = cogsAcct;
	}

	public String getChgAsmBoMw() {
		return this.chgAsmBoMw;
	}

	public void setChgAsmBoMw(String chgAsmBoMw) {
		this.chgAsmBoMw = chgAsmBoMw;
	}

	public Timestamp getActDelDate() {
		return this.actDelDate;
	}

	public void setActDelDate(Timestamp actDelDate) {
		this.actDelDate = actDelDate;
	}

	public String getOcrCode2() {
		return this.ocrCode2;
	}

	public void setOcrCode2(String ocrCode2) {
		this.ocrCode2 = ocrCode2;
	}

	public String getOcrCode3() {
		return this.ocrCode3;
	}

	public void setOcrCode3(String ocrCode3) {
		this.ocrCode3 = ocrCode3;
	}

	public String getOcrCode4() {
		return this.ocrCode4;
	}

	public void setOcrCode4(String ocrCode4) {
		this.ocrCode4 = ocrCode4;
	}

	public String getOcrCode5() {
		return this.ocrCode5;
	}

	public void setOcrCode5(String ocrCode5) {
		this.ocrCode5 = ocrCode5;
	}

	public Double getTaxDistSum() {
		return this.taxDistSum;
	}

	public void setTaxDistSum(Double taxDistSum) {
		this.taxDistSum = taxDistSum;
	}

	public Double getTaxDistSfc() {
		return this.taxDistSfc;
	}

	public void setTaxDistSfc(Double taxDistSfc) {
		this.taxDistSfc = taxDistSfc;
	}

	public Double getTaxDistSsc() {
		return this.taxDistSsc;
	}

	public void setTaxDistSsc(Double taxDistSsc) {
		this.taxDistSsc = taxDistSsc;
	}

	public String getPostTax() {
		return this.postTax;
	}

	public void setPostTax(String postTax) {
		this.postTax = postTax;
	}

	public String getExcisable() {
		return this.excisable;
	}

	public void setExcisable(String excisable) {
		this.excisable = excisable;
	}

	public Double getAssblValue() {
		return this.assblValue;
	}

	public void setAssblValue(Double assblValue) {
		this.assblValue = assblValue;
	}

	public Integer getRg23apart1() {
		return this.rg23apart1;
	}

	public void setRg23apart1(Integer rg23apart1) {
		this.rg23apart1 = rg23apart1;
	}

	public Integer getRg23apart2() {
		return this.rg23apart2;
	}

	public void setRg23apart2(Integer rg23apart2) {
		this.rg23apart2 = rg23apart2;
	}

	public Integer getRg23cpart1() {
		return this.rg23cpart1;
	}

	public void setRg23cpart1(Integer rg23cpart1) {
		this.rg23cpart1 = rg23cpart1;
	}

	public Integer getRg23cpart2() {
		return this.rg23cpart2;
	}

	public void setRg23cpart2(Integer rg23cpart2) {
		this.rg23cpart2 = rg23cpart2;
	}

	public String getCogsOcrCo2() {
		return this.cogsOcrCo2;
	}

	public void setCogsOcrCo2(String cogsOcrCo2) {
		this.cogsOcrCo2 = cogsOcrCo2;
	}

	public String getCogsOcrCo3() {
		return this.cogsOcrCo3;
	}

	public void setCogsOcrCo3(String cogsOcrCo3) {
		this.cogsOcrCo3 = cogsOcrCo3;
	}

	public String getCogsOcrCo4() {
		return this.cogsOcrCo4;
	}

	public void setCogsOcrCo4(String cogsOcrCo4) {
		this.cogsOcrCo4 = cogsOcrCo4;
	}

	public String getCogsOcrCo5() {
		return this.cogsOcrCo5;
	}

	public void setCogsOcrCo5(String cogsOcrCo5) {
		this.cogsOcrCo5 = cogsOcrCo5;
	}

	public String getLnExcised() {
		return this.lnExcised;
	}

	public void setLnExcised(String lnExcised) {
		this.lnExcised = lnExcised;
	}

	public Integer getLocCode() {
		return this.locCode;
	}

	public void setLocCode(Integer locCode) {
		this.locCode = locCode;
	}

	public Double getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(Double stockValue) {
		this.stockValue = stockValue;
	}

	public Double getGpttlBasPr() {
		return this.gpttlBasPr;
	}

	public void setGpttlBasPr(Double gpttlBasPr) {
		this.gpttlBasPr = gpttlBasPr;
	}

	public String getUnitMsr2() {
		return this.unitMsr2;
	}

	public void setUnitMsr2(String unitMsr2) {
		this.unitMsr2 = unitMsr2;
	}

	public Double getNumPerMsr2() {
		return this.numPerMsr2;
	}

	public void setNumPerMsr2(Double numPerMsr2) {
		this.numPerMsr2 = numPerMsr2;
	}

	public String getSpecPrice() {
		return this.specPrice;
	}

	public void setSpecPrice(String specPrice) {
		this.specPrice = specPrice;
	}

	public String getCstfIpi() {
		return this.cstfIpi;
	}

	public void setCstfIpi(String cstfIpi) {
		this.cstfIpi = cstfIpi;
	}

	public String getCstfPis() {
		return this.cstfPis;
	}

	public void setCstfPis(String cstfPis) {
		this.cstfPis = cstfPis;
	}

	public String getCstfCofins() {
		return this.cstfCofins;
	}

	public void setCstfCofins(String cstfCofins) {
		this.cstfCofins = cstfCofins;
	}

	public String getExLineNo() {
		return this.exLineNo;
	}

	public void setExLineNo(String exLineNo) {
		this.exLineNo = exLineNo;
	}

	public String getIsSrvCall() {
		return this.isSrvCall;
	}

	public void setIsSrvCall(String isSrvCall) {
		this.isSrvCall = isSrvCall;
	}

	public String getUMtdl() {
		return this.UMtdl;
	}

	public void setUMtdl(String UMtdl) {
		this.UMtdl = UMtdl;
	}

	public Double getUMtmd() {
		return this.UMtmd;
	}

	public void setUMtmd(Double UMtmd) {
		this.UMtmd = UMtmd;
	}

	public Double getUZc() {
		return this.UZc;
	}

	public void setUZc(Double UZc) {
		this.UZc = UZc;
	}

	public Double getUZz() {
		return this.UZz;
	}

	public void setUZz(Double UZz) {
		this.UZz = UZz;
	}

	public Double getUMjg() {
		return this.UMjg;
	}

	public void setUMjg(Double UMjg) {
		this.UMjg = UMjg;
	}

	public Double getUGjjg() {
		return this.UGjjg;
	}

	public void setUGjjg(Double UGjjg) {
		this.UGjjg = UGjjg;
	}

	public Double getUTsjg() {
		return this.UTsjg;
	}

	public void setUTsjg(Double UTsjg) {
		this.UTsjg = UTsjg;
	}

	public Integer getUDjNo() {
		return this.UDjNo;
	}

	public void setUDjNo(Integer UDjNo) {
		this.UDjNo = UDjNo;
	}

	public Short getUDjNozj() {
		return this.UDjNozj;
	}

	public void setUDjNozj(Short UDjNozj) {
		this.UDjNozj = UDjNozj;
	}

	public String getUCkck() {
		return this.UCkck;
	}

	public void setUCkck(String UCkck) {
		this.UCkck = UCkck;
	}

	public Double getUScwc() {
		return this.UScwc;
	}

	public void setUScwc(Double UScwc) {
		this.UScwc = UScwc;
	}

	public Double getUSjzl() {
		return this.USjzl;
	}

	public void setUSjzl(Double USjzl) {
		this.USjzl = USjzl;
	}

	public String getUCzy() {
		return this.UCzy;
	}

	public void setUCzy(String UCzy) {
		this.UCzy = UCzy;
	}

	public Integer getUGs() {
		return this.UGs;
	}

	public void setUGs(Integer UGs) {
		this.UGs = UGs;
	}

	public Double getUDydj() {
		return this.UDydj;
	}

	public void setUDydj(Double UDydj) {
		this.UDydj = UDydj;
	}

	public String getURinzz() {
		return this.URinzz;
	}

	public void setURinzz(String URinzz) {
		this.URinzz = URinzz;
	}

	public String getUSfhh() {
		return this.USfhh;
	}

	public void setUSfhh(String USfhh) {
		this.USfhh = USfhh;
	}

	public Double getUJgf() {
		return this.UJgf;
	}

	public void setUJgf(Double UJgf) {
		this.UJgf = UJgf;
	}

	public String getUYdy() {
		return this.UYdy;
	}

	public void setUYdy(String UYdy) {
		this.UYdy = UYdy;
	}

	public Timestamp getUDhdate() {
		return this.UDhdate;
	}

	public void setUDhdate(Timestamp UDhdate) {
		this.UDhdate = UDhdate;
	}

}