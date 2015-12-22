package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Oitm entity. @author MyEclipse Persistence Tools
 */

public class Oitm implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7529871958102602030L;
	private String itemCode;
	private String itemName;
	private String frgnName;
	private Short itmsGrpCod;
	private Short cstGrpCode;
	private String vatGourpSa;
	private String codeBars;
	private String vatliable;
	private String prchseItem;
	private String sellItem;
	private String invntItem;
	private Double onHand;
	private Double isCommited;
	private Double onOrder;
	private String incomeAcct;
	private String exmptIncom;
	private Double maxLevel;
	private String dfltWh;
	private String cardCode;
	private String suppCatNum;
	private String buyUnitMsr;
	private Double numInBuy;
	private Double reorderQty;
	private Double minLevel;
	private Double lstEvlPric;
	private Timestamp lstEvlDate;
	private Double customPer;
	private String canceled;
	private Integer mnufctTime;
	private String wholSlsTax;
	private String retilrTax;
	private Double spcialDisc;
	private Short dscountCod;
	private String trackSales;
	private String salUnitMsr;
	private Double numInSale;
	private Double consig;
	private Integer queryGroup;
	private Double counted;
	private Double openBlnc;
	private String evalSystem;
	private Short userSign;
	private String free;
	private String picturName;
	private String transfered;
	private String blncTrnsfr;
	private String userText;
	private String serialNum;
	private Double commisPcnt;
	private Double commisSum;
	private Short commisGrp;
	private String treeType;
	private Double treeQty;
	private Double lastPurPrc;
	private String lastPurCur;
	private Timestamp lastPurDat;
	private String exitCur;
	private Double exitPrice;
	private String exitWh;
	private String assetItem;
	private String wasCounted;
	private String manSerNum;
	private Double sheight1;
	private Short shght1unit;
	private Double sheight2;
	private Short shght2unit;
	private Double swidth1;
	private Short swdth1unit;
	private Double swidth2;
	private Short swdth2unit;
	private Double slength1;
	private Short slen1unit;
	private Double slength2;
	private Short slen2unit;
	private Double svolume;
	private Short svolUnit;
	private Double sweight1;
	private Short swght1unit;
	private Double sweight2;
	private Short swght2unit;
	private Double bheight1;
	private Short bhght1unit;
	private Double bheight2;
	private Short bhght2unit;
	private Double bwidth1;
	private Short bwdth1unit;
	private Double bwidth2;
	private Short bwdth2unit;
	private Double blength1;
	private Short blen1unit;
	private Double blength2;
	private Short blen2unit;
	private Double bvolume;
	private Short bvolUnit;
	private Double bweight1;
	private Short bwght1unit;
	private Double bweight2;
	private Short bwght2unit;
	private String fixCurrCms;
	private Short firmCode;
	private Timestamp lstSalDate;
	private String qryGroup1;
	private String qryGroup2;
	private String qryGroup3;
	private String qryGroup4;
	private String qryGroup5;
	private String qryGroup6;
	private String qryGroup7;
	private String qryGroup8;
	private String qryGroup9;
	private String qryGroup10;
	private String qryGroup11;
	private String qryGroup12;
	private String qryGroup13;
	private String qryGroup14;
	private String qryGroup15;
	private String qryGroup16;
	private String qryGroup17;
	private String qryGroup18;
	private String qryGroup19;
	private String qryGroup20;
	private String qryGroup21;
	private String qryGroup22;
	private String qryGroup23;
	private String qryGroup24;
	private String qryGroup25;
	private String qryGroup26;
	private String qryGroup27;
	private String qryGroup28;
	private String qryGroup29;
	private String qryGroup30;
	private String qryGroup31;
	private String qryGroup32;
	private String qryGroup33;
	private String qryGroup34;
	private String qryGroup35;
	private String qryGroup36;
	private String qryGroup37;
	private String qryGroup38;
	private String qryGroup39;
	private String qryGroup40;
	private String qryGroup41;
	private String qryGroup42;
	private String qryGroup43;
	private String qryGroup44;
	private String qryGroup45;
	private String qryGroup46;
	private String qryGroup47;
	private String qryGroup48;
	private String qryGroup49;
	private String qryGroup50;
	private String qryGroup51;
	private String qryGroup52;
	private String qryGroup53;
	private String qryGroup54;
	private String qryGroup55;
	private String qryGroup56;
	private String qryGroup57;
	private String qryGroup58;
	private String qryGroup59;
	private String qryGroup60;
	private String qryGroup61;
	private String qryGroup62;
	private String qryGroup63;
	private String qryGroup64;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String exportCode;
	private Double salFactor1;
	private Double salFactor2;
	private Double salFactor3;
	private Double salFactor4;
	private Double purFactor1;
	private Double purFactor2;
	private Double purFactor3;
	private Double purFactor4;
	private String salFormula;
	private String purFormula;
	private String vatGroupPu;
	private Double avgPrice;
	private String purPackMsr;
	private Double purPackUn;
	private String salPackMsr;
	private Double salPackUn;
	private Short scncounter;
	private String manBtchNum;
	private String manOutOnly;
	private String dataSource;
	private String validFor;
	private Timestamp validFrom;
	private Timestamp validTo;
	private String frozenFor;
	private Timestamp frozenFrom;
	private Timestamp frozenTo;
	private String blockOut;
	private String validComm;
	private String frozenComm;
	private Short logInstanc;
	private String objType;
	private String sww;
	private String deleted;
	private Integer docEntry;
	private String expensAcct;
	private String frgnInAcct;
	private Short shipType;
	private String glmethod;
	private String ecinAcct;
	private String frgnExpAcc;
	private String ecexpAcc;
	private String taxType;
	private String byWh;
	private String wtliable;
	private String itemType;
	private String warrntTmpl;
	private String baseUnit;
	private String countryOrg;
	private Double stockValue;
	private String phantom;
	private String issueMthd;
	private String free1;
	private Double pricingPrc;
	private String mngMethod;
	private Double reorderPnt;
	private String invntryUom;
	private String planingSys;
	private String prcrmntMtd;
	private Short ordrIntrvl;
	private Double ordrMulti;
	private Double minOrdrQty;
	private Integer leadTime;
	private String indirctTax;
	private String taxCodeAr;
	private String taxCodeAp;
	private Integer osvcCode;
	private Integer isvcCode;
	private Integer serviceGrp;
	private Integer ncmcode;
	private String matType;
	private Integer matGrp;
	private String productSrc;
	private Integer serviceCtg;
	private String itemClass;
	private String excisable;
	private Integer chapterId;
	private String notifyAsn;
	private String proAssNum;
	private Double assblValue;
	private Integer dnfentry;
	private Short userSign2;
	private String spec;
	private String taxCtg;
	private String UMtdl;
	private Double UMtmd;
	private Double UMtzl;
	private Double UScddFz;
	private Double UScddwwgFz;

	// Constructors

	/** default constructor */
	public Oitm() {
	}

	/** minimal constructor */
	public Oitm(String itemCode) {
		this.itemCode = itemCode;
	}

	/** full constructor */
	public Oitm(String itemCode, String itemName, String frgnName,
			Short itmsGrpCod, Short cstGrpCode, String vatGourpSa,
			String codeBars, String vatliable, String prchseItem,
			String sellItem, String invntItem, Double onHand,
			Double isCommited, Double onOrder, String incomeAcct,
			String exmptIncom, Double maxLevel, String dfltWh, String cardCode,
			String suppCatNum, String buyUnitMsr, Double numInBuy,
			Double reorderQty, Double minLevel, Double lstEvlPric,
			Timestamp lstEvlDate, Double customPer, String canceled,
			Integer mnufctTime, String wholSlsTax, String retilrTax,
			Double spcialDisc, Short dscountCod, String trackSales,
			String salUnitMsr, Double numInSale, Double consig,
			Integer queryGroup, Double counted, Double openBlnc,
			String evalSystem, Short userSign, String free, String picturName,
			String transfered, String blncTrnsfr, String userText,
			String serialNum, Double commisPcnt, Double commisSum,
			Short commisGrp, String treeType, Double treeQty,
			Double lastPurPrc, String lastPurCur, Timestamp lastPurDat,
			String exitCur, Double exitPrice, String exitWh, String assetItem,
			String wasCounted, String manSerNum, Double sheight1,
			Short shght1unit, Double sheight2, Short shght2unit,
			Double swidth1, Short swdth1unit, Double swidth2, Short swdth2unit,
			Double slength1, Short slen1unit, Double slength2, Short slen2unit,
			Double svolume, Short svolUnit, Double sweight1, Short swght1unit,
			Double sweight2, Short swght2unit, Double bheight1,
			Short bhght1unit, Double bheight2, Short bhght2unit,
			Double bwidth1, Short bwdth1unit, Double bwidth2, Short bwdth2unit,
			Double blength1, Short blen1unit, Double blength2, Short blen2unit,
			Double bvolume, Short bvolUnit, Double bweight1, Short bwght1unit,
			Double bweight2, Short bwght2unit, String fixCurrCms,
			Short firmCode, Timestamp lstSalDate, String qryGroup1,
			String qryGroup2, String qryGroup3, String qryGroup4,
			String qryGroup5, String qryGroup6, String qryGroup7,
			String qryGroup8, String qryGroup9, String qryGroup10,
			String qryGroup11, String qryGroup12, String qryGroup13,
			String qryGroup14, String qryGroup15, String qryGroup16,
			String qryGroup17, String qryGroup18, String qryGroup19,
			String qryGroup20, String qryGroup21, String qryGroup22,
			String qryGroup23, String qryGroup24, String qryGroup25,
			String qryGroup26, String qryGroup27, String qryGroup28,
			String qryGroup29, String qryGroup30, String qryGroup31,
			String qryGroup32, String qryGroup33, String qryGroup34,
			String qryGroup35, String qryGroup36, String qryGroup37,
			String qryGroup38, String qryGroup39, String qryGroup40,
			String qryGroup41, String qryGroup42, String qryGroup43,
			String qryGroup44, String qryGroup45, String qryGroup46,
			String qryGroup47, String qryGroup48, String qryGroup49,
			String qryGroup50, String qryGroup51, String qryGroup52,
			String qryGroup53, String qryGroup54, String qryGroup55,
			String qryGroup56, String qryGroup57, String qryGroup58,
			String qryGroup59, String qryGroup60, String qryGroup61,
			String qryGroup62, String qryGroup63, String qryGroup64,
			Timestamp createDate, Timestamp updateDate, String exportCode,
			Double salFactor1, Double salFactor2, Double salFactor3,
			Double salFactor4, Double purFactor1, Double purFactor2,
			Double purFactor3, Double purFactor4, String salFormula,
			String purFormula, String vatGroupPu, Double avgPrice,
			String purPackMsr, Double purPackUn, String salPackMsr,
			Double salPackUn, Short scncounter, String manBtchNum,
			String manOutOnly, String dataSource, String validFor,
			Timestamp validFrom, Timestamp validTo, String frozenFor,
			Timestamp frozenFrom, Timestamp frozenTo, String blockOut,
			String validComm, String frozenComm, Short logInstanc,
			String objType, String sww, String deleted, Integer docEntry,
			String expensAcct, String frgnInAcct, Short shipType,
			String glmethod, String ecinAcct, String frgnExpAcc,
			String ecexpAcc, String taxType, String byWh, String wtliable,
			String itemType, String warrntTmpl, String baseUnit,
			String countryOrg, Double stockValue, String phantom,
			String issueMthd, String free1, Double pricingPrc,
			String mngMethod, Double reorderPnt, String invntryUom,
			String planingSys, String prcrmntMtd, Short ordrIntrvl,
			Double ordrMulti, Double minOrdrQty, Integer leadTime,
			String indirctTax, String taxCodeAr, String taxCodeAp,
			Integer osvcCode, Integer isvcCode, Integer serviceGrp,
			Integer ncmcode, String matType, Integer matGrp, String productSrc,
			Integer serviceCtg, String itemClass, String excisable,
			/*Integer chapterId, String notifyAsn, String proAssNum,
			Double assblValue, Integer dnfentry, Short userSign2, String spec,*/
			String taxCtg, String UMtdl, Double UMtmd, Double UMtzl,
			Double UScddFz, Double UScddwwgFz) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.frgnName = frgnName;
		this.itmsGrpCod = itmsGrpCod;
		this.cstGrpCode = cstGrpCode;
		this.vatGourpSa = vatGourpSa;
		this.codeBars = codeBars;
		this.vatliable = vatliable;
		this.prchseItem = prchseItem;
		this.sellItem = sellItem;
		this.invntItem = invntItem;
		this.onHand = onHand;
		this.isCommited = isCommited;
		this.onOrder = onOrder;
		this.incomeAcct = incomeAcct;
		this.exmptIncom = exmptIncom;
		this.maxLevel = maxLevel;
		this.dfltWh = dfltWh;
		this.cardCode = cardCode;
		this.suppCatNum = suppCatNum;
		this.buyUnitMsr = buyUnitMsr;
		this.numInBuy = numInBuy;
		this.reorderQty = reorderQty;
		this.minLevel = minLevel;
		this.lstEvlPric = lstEvlPric;
		this.lstEvlDate = lstEvlDate;
		this.customPer = customPer;
		this.canceled = canceled;
		this.mnufctTime = mnufctTime;
		this.wholSlsTax = wholSlsTax;
		this.retilrTax = retilrTax;
		this.spcialDisc = spcialDisc;
		this.dscountCod = dscountCod;
		this.trackSales = trackSales;
		this.salUnitMsr = salUnitMsr;
		this.numInSale = numInSale;
		this.consig = consig;
		this.queryGroup = queryGroup;
		this.counted = counted;
		this.openBlnc = openBlnc;
		this.evalSystem = evalSystem;
		this.userSign = userSign;
		this.free = free;
		this.picturName = picturName;
		this.transfered = transfered;
		this.blncTrnsfr = blncTrnsfr;
		this.userText = userText;
		this.serialNum = serialNum;
		this.commisPcnt = commisPcnt;
		this.commisSum = commisSum;
		this.commisGrp = commisGrp;
		this.treeType = treeType;
		this.treeQty = treeQty;
		this.lastPurPrc = lastPurPrc;
		this.lastPurCur = lastPurCur;
		this.lastPurDat = lastPurDat;
		this.exitCur = exitCur;
		this.exitPrice = exitPrice;
		this.exitWh = exitWh;
		this.assetItem = assetItem;
		this.wasCounted = wasCounted;
		this.manSerNum = manSerNum;
		this.sheight1 = sheight1;
		this.shght1unit = shght1unit;
		this.sheight2 = sheight2;
		this.shght2unit = shght2unit;
		this.swidth1 = swidth1;
		this.swdth1unit = swdth1unit;
		this.swidth2 = swidth2;
		this.swdth2unit = swdth2unit;
		this.slength1 = slength1;
		this.slen1unit = slen1unit;
		this.slength2 = slength2;
		this.slen2unit = slen2unit;
		this.svolume = svolume;
		this.svolUnit = svolUnit;
		this.sweight1 = sweight1;
		this.swght1unit = swght1unit;
		this.sweight2 = sweight2;
		this.swght2unit = swght2unit;
		this.bheight1 = bheight1;
		this.bhght1unit = bhght1unit;
		this.bheight2 = bheight2;
		this.bhght2unit = bhght2unit;
		this.bwidth1 = bwidth1;
		this.bwdth1unit = bwdth1unit;
		this.bwidth2 = bwidth2;
		this.bwdth2unit = bwdth2unit;
		this.blength1 = blength1;
		this.blen1unit = blen1unit;
		this.blength2 = blength2;
		this.blen2unit = blen2unit;
		this.bvolume = bvolume;
		this.bvolUnit = bvolUnit;
		this.bweight1 = bweight1;
		this.bwght1unit = bwght1unit;
		this.bweight2 = bweight2;
		this.bwght2unit = bwght2unit;
		this.fixCurrCms = fixCurrCms;
		this.firmCode = firmCode;
		this.lstSalDate = lstSalDate;
		this.qryGroup1 = qryGroup1;
		this.qryGroup2 = qryGroup2;
		this.qryGroup3 = qryGroup3;
		this.qryGroup4 = qryGroup4;
		this.qryGroup5 = qryGroup5;
		this.qryGroup6 = qryGroup6;
		this.qryGroup7 = qryGroup7;
		this.qryGroup8 = qryGroup8;
		this.qryGroup9 = qryGroup9;
		this.qryGroup10 = qryGroup10;
		this.qryGroup11 = qryGroup11;
		this.qryGroup12 = qryGroup12;
		this.qryGroup13 = qryGroup13;
		this.qryGroup14 = qryGroup14;
		this.qryGroup15 = qryGroup15;
		this.qryGroup16 = qryGroup16;
		this.qryGroup17 = qryGroup17;
		this.qryGroup18 = qryGroup18;
		this.qryGroup19 = qryGroup19;
		this.qryGroup20 = qryGroup20;
		this.qryGroup21 = qryGroup21;
		this.qryGroup22 = qryGroup22;
		this.qryGroup23 = qryGroup23;
		this.qryGroup24 = qryGroup24;
		this.qryGroup25 = qryGroup25;
		this.qryGroup26 = qryGroup26;
		this.qryGroup27 = qryGroup27;
		this.qryGroup28 = qryGroup28;
		this.qryGroup29 = qryGroup29;
		this.qryGroup30 = qryGroup30;
		this.qryGroup31 = qryGroup31;
		this.qryGroup32 = qryGroup32;
		this.qryGroup33 = qryGroup33;
		this.qryGroup34 = qryGroup34;
		this.qryGroup35 = qryGroup35;
		this.qryGroup36 = qryGroup36;
		this.qryGroup37 = qryGroup37;
		this.qryGroup38 = qryGroup38;
		this.qryGroup39 = qryGroup39;
		this.qryGroup40 = qryGroup40;
		this.qryGroup41 = qryGroup41;
		this.qryGroup42 = qryGroup42;
		this.qryGroup43 = qryGroup43;
		this.qryGroup44 = qryGroup44;
		this.qryGroup45 = qryGroup45;
		this.qryGroup46 = qryGroup46;
		this.qryGroup47 = qryGroup47;
		this.qryGroup48 = qryGroup48;
		this.qryGroup49 = qryGroup49;
		this.qryGroup50 = qryGroup50;
		this.qryGroup51 = qryGroup51;
		this.qryGroup52 = qryGroup52;
		this.qryGroup53 = qryGroup53;
		this.qryGroup54 = qryGroup54;
		this.qryGroup55 = qryGroup55;
		this.qryGroup56 = qryGroup56;
		this.qryGroup57 = qryGroup57;
		this.qryGroup58 = qryGroup58;
		this.qryGroup59 = qryGroup59;
		this.qryGroup60 = qryGroup60;
		this.qryGroup61 = qryGroup61;
		this.qryGroup62 = qryGroup62;
		this.qryGroup63 = qryGroup63;
		this.qryGroup64 = qryGroup64;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.exportCode = exportCode;
		this.salFactor1 = salFactor1;
		this.salFactor2 = salFactor2;
		this.salFactor3 = salFactor3;
		this.salFactor4 = salFactor4;
		this.purFactor1 = purFactor1;
		this.purFactor2 = purFactor2;
		this.purFactor3 = purFactor3;
		this.purFactor4 = purFactor4;
		this.salFormula = salFormula;
		this.purFormula = purFormula;
		this.vatGroupPu = vatGroupPu;
		this.avgPrice = avgPrice;
		this.purPackMsr = purPackMsr;
		this.purPackUn = purPackUn;
		this.salPackMsr = salPackMsr;
		this.salPackUn = salPackUn;
		this.scncounter = scncounter;
		this.manBtchNum = manBtchNum;
		this.manOutOnly = manOutOnly;
		this.dataSource = dataSource;
		this.validFor = validFor;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.frozenFor = frozenFor;
		this.frozenFrom = frozenFrom;
		this.frozenTo = frozenTo;
		this.blockOut = blockOut;
		this.validComm = validComm;
		this.frozenComm = frozenComm;
		this.logInstanc = logInstanc;
		this.objType = objType;
		this.sww = sww;
		this.deleted = deleted;
		this.docEntry = docEntry;
		this.expensAcct = expensAcct;
		this.frgnInAcct = frgnInAcct;
		this.shipType = shipType;
		this.glmethod = glmethod;
		this.ecinAcct = ecinAcct;
		this.frgnExpAcc = frgnExpAcc;
		this.ecexpAcc = ecexpAcc;
		this.taxType = taxType;
		this.byWh = byWh;
		this.wtliable = wtliable;
		this.itemType = itemType;
		this.warrntTmpl = warrntTmpl;
		this.baseUnit = baseUnit;
		this.countryOrg = countryOrg;
		this.stockValue = stockValue;
		this.phantom = phantom;
		this.issueMthd = issueMthd;
		this.free1 = free1;
		this.pricingPrc = pricingPrc;
		this.mngMethod = mngMethod;
		this.reorderPnt = reorderPnt;
		this.invntryUom = invntryUom;
		this.planingSys = planingSys;
		this.prcrmntMtd = prcrmntMtd;
		this.ordrIntrvl = ordrIntrvl;
		this.ordrMulti = ordrMulti;
		this.minOrdrQty = minOrdrQty;
		this.leadTime = leadTime;
		this.indirctTax = indirctTax;
		this.taxCodeAr = taxCodeAr;
		this.taxCodeAp = taxCodeAp;
		this.osvcCode = osvcCode;
		this.isvcCode = isvcCode;
		this.serviceGrp = serviceGrp;
		this.ncmcode = ncmcode;
		this.matType = matType;
		this.matGrp = matGrp;
		this.productSrc = productSrc;
		this.serviceCtg = serviceCtg;
		this.itemClass = itemClass;
		this.excisable = excisable;
/*		this.chapterId = chapterId;
		this.notifyAsn = notifyAsn;
		this.proAssNum = proAssNum;
		this.assblValue = assblValue;
		this.dnfentry = dnfentry;
		this.userSign2 = userSign2;
		this.spec = spec;*/
		this.taxCtg = taxCtg;
		this.UMtdl = UMtdl;
		this.UMtmd = UMtmd;
		this.UMtzl = UMtzl;
		this.UScddFz = UScddFz;
		this.UScddwwgFz = UScddwwgFz;
	}

	// Property accessors

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getFrgnName() {
		return this.frgnName;
	}

	public void setFrgnName(String frgnName) {
		this.frgnName = frgnName;
	}

	public Short getItmsGrpCod() {
		return this.itmsGrpCod;
	}

	public void setItmsGrpCod(Short itmsGrpCod) {
		this.itmsGrpCod = itmsGrpCod;
	}

	public Short getCstGrpCode() {
		return this.cstGrpCode;
	}

	public void setCstGrpCode(Short cstGrpCode) {
		this.cstGrpCode = cstGrpCode;
	}

	public String getVatGourpSa() {
		return this.vatGourpSa;
	}

	public void setVatGourpSa(String vatGourpSa) {
		this.vatGourpSa = vatGourpSa;
	}

	public String getCodeBars() {
		return this.codeBars;
	}

	public void setCodeBars(String codeBars) {
		this.codeBars = codeBars;
	}

	public String getVatliable() {
		return this.vatliable;
	}

	public void setVatliable(String vatliable) {
		this.vatliable = vatliable;
	}

	public String getPrchseItem() {
		return this.prchseItem;
	}

	public void setPrchseItem(String prchseItem) {
		this.prchseItem = prchseItem;
	}

	public String getSellItem() {
		return this.sellItem;
	}

	public void setSellItem(String sellItem) {
		this.sellItem = sellItem;
	}

	public String getInvntItem() {
		return this.invntItem;
	}

	public void setInvntItem(String invntItem) {
		this.invntItem = invntItem;
	}

	public Double getOnHand() {
		return this.onHand;
	}

	public void setOnHand(Double onHand) {
		this.onHand = onHand;
	}

	public Double getIsCommited() {
		return this.isCommited;
	}

	public void setIsCommited(Double isCommited) {
		this.isCommited = isCommited;
	}

	public Double getOnOrder() {
		return this.onOrder;
	}

	public void setOnOrder(Double onOrder) {
		this.onOrder = onOrder;
	}

	public String getIncomeAcct() {
		return this.incomeAcct;
	}

	public void setIncomeAcct(String incomeAcct) {
		this.incomeAcct = incomeAcct;
	}

	public String getExmptIncom() {
		return this.exmptIncom;
	}

	public void setExmptIncom(String exmptIncom) {
		this.exmptIncom = exmptIncom;
	}

	public Double getMaxLevel() {
		return this.maxLevel;
	}

	public void setMaxLevel(Double maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getDfltWh() {
		return this.dfltWh;
	}

	public void setDfltWh(String dfltWh) {
		this.dfltWh = dfltWh;
	}

	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getSuppCatNum() {
		return this.suppCatNum;
	}

	public void setSuppCatNum(String suppCatNum) {
		this.suppCatNum = suppCatNum;
	}

	public String getBuyUnitMsr() {
		return this.buyUnitMsr;
	}

	public void setBuyUnitMsr(String buyUnitMsr) {
		this.buyUnitMsr = buyUnitMsr;
	}

	public Double getNumInBuy() {
		return this.numInBuy;
	}

	public void setNumInBuy(Double numInBuy) {
		this.numInBuy = numInBuy;
	}

	public Double getReorderQty() {
		return this.reorderQty;
	}

	public void setReorderQty(Double reorderQty) {
		this.reorderQty = reorderQty;
	}

	public Double getMinLevel() {
		return this.minLevel;
	}

	public void setMinLevel(Double minLevel) {
		this.minLevel = minLevel;
	}

	public Double getLstEvlPric() {
		return this.lstEvlPric;
	}

	public void setLstEvlPric(Double lstEvlPric) {
		this.lstEvlPric = lstEvlPric;
	}

	public Timestamp getLstEvlDate() {
		return this.lstEvlDate;
	}

	public void setLstEvlDate(Timestamp lstEvlDate) {
		this.lstEvlDate = lstEvlDate;
	}

	public Double getCustomPer() {
		return this.customPer;
	}

	public void setCustomPer(Double customPer) {
		this.customPer = customPer;
	}

	public String getCanceled() {
		return this.canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public Integer getMnufctTime() {
		return this.mnufctTime;
	}

	public void setMnufctTime(Integer mnufctTime) {
		this.mnufctTime = mnufctTime;
	}

	public String getWholSlsTax() {
		return this.wholSlsTax;
	}

	public void setWholSlsTax(String wholSlsTax) {
		this.wholSlsTax = wholSlsTax;
	}

	public String getRetilrTax() {
		return this.retilrTax;
	}

	public void setRetilrTax(String retilrTax) {
		this.retilrTax = retilrTax;
	}

	public Double getSpcialDisc() {
		return this.spcialDisc;
	}

	public void setSpcialDisc(Double spcialDisc) {
		this.spcialDisc = spcialDisc;
	}

	public Short getDscountCod() {
		return this.dscountCod;
	}

	public void setDscountCod(Short dscountCod) {
		this.dscountCod = dscountCod;
	}

	public String getTrackSales() {
		return this.trackSales;
	}

	public void setTrackSales(String trackSales) {
		this.trackSales = trackSales;
	}

	public String getSalUnitMsr() {
		return this.salUnitMsr;
	}

	public void setSalUnitMsr(String salUnitMsr) {
		this.salUnitMsr = salUnitMsr;
	}

	public Double getNumInSale() {
		return this.numInSale;
	}

	public void setNumInSale(Double numInSale) {
		this.numInSale = numInSale;
	}

	public Double getConsig() {
		return this.consig;
	}

	public void setConsig(Double consig) {
		this.consig = consig;
	}

	public Integer getQueryGroup() {
		return this.queryGroup;
	}

	public void setQueryGroup(Integer queryGroup) {
		this.queryGroup = queryGroup;
	}

	public Double getCounted() {
		return this.counted;
	}

	public void setCounted(Double counted) {
		this.counted = counted;
	}

	public Double getOpenBlnc() {
		return this.openBlnc;
	}

	public void setOpenBlnc(Double openBlnc) {
		this.openBlnc = openBlnc;
	}

	public String getEvalSystem() {
		return this.evalSystem;
	}

	public void setEvalSystem(String evalSystem) {
		this.evalSystem = evalSystem;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

	public String getFree() {
		return this.free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getPicturName() {
		return this.picturName;
	}

	public void setPicturName(String picturName) {
		this.picturName = picturName;
	}

	public String getTransfered() {
		return this.transfered;
	}

	public void setTransfered(String transfered) {
		this.transfered = transfered;
	}

	public String getBlncTrnsfr() {
		return this.blncTrnsfr;
	}

	public void setBlncTrnsfr(String blncTrnsfr) {
		this.blncTrnsfr = blncTrnsfr;
	}

	public String getUserText() {
		return this.userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Double getCommisPcnt() {
		return this.commisPcnt;
	}

	public void setCommisPcnt(Double commisPcnt) {
		this.commisPcnt = commisPcnt;
	}

	public Double getCommisSum() {
		return this.commisSum;
	}

	public void setCommisSum(Double commisSum) {
		this.commisSum = commisSum;
	}

	public Short getCommisGrp() {
		return this.commisGrp;
	}

	public void setCommisGrp(Short commisGrp) {
		this.commisGrp = commisGrp;
	}

	public String getTreeType() {
		return this.treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public Double getTreeQty() {
		return this.treeQty;
	}

	public void setTreeQty(Double treeQty) {
		this.treeQty = treeQty;
	}

	public Double getLastPurPrc() {
		return this.lastPurPrc;
	}

	public void setLastPurPrc(Double lastPurPrc) {
		this.lastPurPrc = lastPurPrc;
	}

	public String getLastPurCur() {
		return this.lastPurCur;
	}

	public void setLastPurCur(String lastPurCur) {
		this.lastPurCur = lastPurCur;
	}

	public Timestamp getLastPurDat() {
		return this.lastPurDat;
	}

	public void setLastPurDat(Timestamp lastPurDat) {
		this.lastPurDat = lastPurDat;
	}

	public String getExitCur() {
		return this.exitCur;
	}

	public void setExitCur(String exitCur) {
		this.exitCur = exitCur;
	}

	public Double getExitPrice() {
		return this.exitPrice;
	}

	public void setExitPrice(Double exitPrice) {
		this.exitPrice = exitPrice;
	}

	public String getExitWh() {
		return this.exitWh;
	}

	public void setExitWh(String exitWh) {
		this.exitWh = exitWh;
	}

	public String getAssetItem() {
		return this.assetItem;
	}

	public void setAssetItem(String assetItem) {
		this.assetItem = assetItem;
	}

	public String getWasCounted() {
		return this.wasCounted;
	}

	public void setWasCounted(String wasCounted) {
		this.wasCounted = wasCounted;
	}

	public String getManSerNum() {
		return this.manSerNum;
	}

	public void setManSerNum(String manSerNum) {
		this.manSerNum = manSerNum;
	}

	public Double getSheight1() {
		return this.sheight1;
	}

	public void setSheight1(Double sheight1) {
		this.sheight1 = sheight1;
	}

	public Short getShght1unit() {
		return this.shght1unit;
	}

	public void setShght1unit(Short shght1unit) {
		this.shght1unit = shght1unit;
	}

	public Double getSheight2() {
		return this.sheight2;
	}

	public void setSheight2(Double sheight2) {
		this.sheight2 = sheight2;
	}

	public Short getShght2unit() {
		return this.shght2unit;
	}

	public void setShght2unit(Short shght2unit) {
		this.shght2unit = shght2unit;
	}

	public Double getSwidth1() {
		return this.swidth1;
	}

	public void setSwidth1(Double swidth1) {
		this.swidth1 = swidth1;
	}

	public Short getSwdth1unit() {
		return this.swdth1unit;
	}

	public void setSwdth1unit(Short swdth1unit) {
		this.swdth1unit = swdth1unit;
	}

	public Double getSwidth2() {
		return this.swidth2;
	}

	public void setSwidth2(Double swidth2) {
		this.swidth2 = swidth2;
	}

	public Short getSwdth2unit() {
		return this.swdth2unit;
	}

	public void setSwdth2unit(Short swdth2unit) {
		this.swdth2unit = swdth2unit;
	}

	public Double getSlength1() {
		return this.slength1;
	}

	public void setSlength1(Double slength1) {
		this.slength1 = slength1;
	}

	public Short getSlen1unit() {
		return this.slen1unit;
	}

	public void setSlen1unit(Short slen1unit) {
		this.slen1unit = slen1unit;
	}

	public Double getSlength2() {
		return this.slength2;
	}

	public void setSlength2(Double slength2) {
		this.slength2 = slength2;
	}

	public Short getSlen2unit() {
		return this.slen2unit;
	}

	public void setSlen2unit(Short slen2unit) {
		this.slen2unit = slen2unit;
	}

	public Double getSvolume() {
		return this.svolume;
	}

	public void setSvolume(Double svolume) {
		this.svolume = svolume;
	}

	public Short getSvolUnit() {
		return this.svolUnit;
	}

	public void setSvolUnit(Short svolUnit) {
		this.svolUnit = svolUnit;
	}

	public Double getSweight1() {
		return this.sweight1;
	}

	public void setSweight1(Double sweight1) {
		this.sweight1 = sweight1;
	}

	public Short getSwght1unit() {
		return this.swght1unit;
	}

	public void setSwght1unit(Short swght1unit) {
		this.swght1unit = swght1unit;
	}

	public Double getSweight2() {
		return this.sweight2;
	}

	public void setSweight2(Double sweight2) {
		this.sweight2 = sweight2;
	}

	public Short getSwght2unit() {
		return this.swght2unit;
	}

	public void setSwght2unit(Short swght2unit) {
		this.swght2unit = swght2unit;
	}

	public Double getBheight1() {
		return this.bheight1;
	}

	public void setBheight1(Double bheight1) {
		this.bheight1 = bheight1;
	}

	public Short getBhght1unit() {
		return this.bhght1unit;
	}

	public void setBhght1unit(Short bhght1unit) {
		this.bhght1unit = bhght1unit;
	}

	public Double getBheight2() {
		return this.bheight2;
	}

	public void setBheight2(Double bheight2) {
		this.bheight2 = bheight2;
	}

	public Short getBhght2unit() {
		return this.bhght2unit;
	}

	public void setBhght2unit(Short bhght2unit) {
		this.bhght2unit = bhght2unit;
	}

	public Double getBwidth1() {
		return this.bwidth1;
	}

	public void setBwidth1(Double bwidth1) {
		this.bwidth1 = bwidth1;
	}

	public Short getBwdth1unit() {
		return this.bwdth1unit;
	}

	public void setBwdth1unit(Short bwdth1unit) {
		this.bwdth1unit = bwdth1unit;
	}

	public Double getBwidth2() {
		return this.bwidth2;
	}

	public void setBwidth2(Double bwidth2) {
		this.bwidth2 = bwidth2;
	}

	public Short getBwdth2unit() {
		return this.bwdth2unit;
	}

	public void setBwdth2unit(Short bwdth2unit) {
		this.bwdth2unit = bwdth2unit;
	}

	public Double getBlength1() {
		return this.blength1;
	}

	public void setBlength1(Double blength1) {
		this.blength1 = blength1;
	}

	public Short getBlen1unit() {
		return this.blen1unit;
	}

	public void setBlen1unit(Short blen1unit) {
		this.blen1unit = blen1unit;
	}

	public Double getBlength2() {
		return this.blength2;
	}

	public void setBlength2(Double blength2) {
		this.blength2 = blength2;
	}

	public Short getBlen2unit() {
		return this.blen2unit;
	}

	public void setBlen2unit(Short blen2unit) {
		this.blen2unit = blen2unit;
	}

	public Double getBvolume() {
		return this.bvolume;
	}

	public void setBvolume(Double bvolume) {
		this.bvolume = bvolume;
	}

	public Short getBvolUnit() {
		return this.bvolUnit;
	}

	public void setBvolUnit(Short bvolUnit) {
		this.bvolUnit = bvolUnit;
	}

	public Double getBweight1() {
		return this.bweight1;
	}

	public void setBweight1(Double bweight1) {
		this.bweight1 = bweight1;
	}

	public Short getBwght1unit() {
		return this.bwght1unit;
	}

	public void setBwght1unit(Short bwght1unit) {
		this.bwght1unit = bwght1unit;
	}

	public Double getBweight2() {
		return this.bweight2;
	}

	public void setBweight2(Double bweight2) {
		this.bweight2 = bweight2;
	}

	public Short getBwght2unit() {
		return this.bwght2unit;
	}

	public void setBwght2unit(Short bwght2unit) {
		this.bwght2unit = bwght2unit;
	}

	public String getFixCurrCms() {
		return this.fixCurrCms;
	}

	public void setFixCurrCms(String fixCurrCms) {
		this.fixCurrCms = fixCurrCms;
	}

	public Short getFirmCode() {
		return this.firmCode;
	}

	public void setFirmCode(Short firmCode) {
		this.firmCode = firmCode;
	}

	public Timestamp getLstSalDate() {
		return this.lstSalDate;
	}

	public void setLstSalDate(Timestamp lstSalDate) {
		this.lstSalDate = lstSalDate;
	}

	public String getQryGroup1() {
		return this.qryGroup1;
	}

	public void setQryGroup1(String qryGroup1) {
		this.qryGroup1 = qryGroup1;
	}

	public String getQryGroup2() {
		return this.qryGroup2;
	}

	public void setQryGroup2(String qryGroup2) {
		this.qryGroup2 = qryGroup2;
	}

	public String getQryGroup3() {
		return this.qryGroup3;
	}

	public void setQryGroup3(String qryGroup3) {
		this.qryGroup3 = qryGroup3;
	}

	public String getQryGroup4() {
		return this.qryGroup4;
	}

	public void setQryGroup4(String qryGroup4) {
		this.qryGroup4 = qryGroup4;
	}

	public String getQryGroup5() {
		return this.qryGroup5;
	}

	public void setQryGroup5(String qryGroup5) {
		this.qryGroup5 = qryGroup5;
	}

	public String getQryGroup6() {
		return this.qryGroup6;
	}

	public void setQryGroup6(String qryGroup6) {
		this.qryGroup6 = qryGroup6;
	}

	public String getQryGroup7() {
		return this.qryGroup7;
	}

	public void setQryGroup7(String qryGroup7) {
		this.qryGroup7 = qryGroup7;
	}

	public String getQryGroup8() {
		return this.qryGroup8;
	}

	public void setQryGroup8(String qryGroup8) {
		this.qryGroup8 = qryGroup8;
	}

	public String getQryGroup9() {
		return this.qryGroup9;
	}

	public void setQryGroup9(String qryGroup9) {
		this.qryGroup9 = qryGroup9;
	}

	public String getQryGroup10() {
		return this.qryGroup10;
	}

	public void setQryGroup10(String qryGroup10) {
		this.qryGroup10 = qryGroup10;
	}

	public String getQryGroup11() {
		return this.qryGroup11;
	}

	public void setQryGroup11(String qryGroup11) {
		this.qryGroup11 = qryGroup11;
	}

	public String getQryGroup12() {
		return this.qryGroup12;
	}

	public void setQryGroup12(String qryGroup12) {
		this.qryGroup12 = qryGroup12;
	}

	public String getQryGroup13() {
		return this.qryGroup13;
	}

	public void setQryGroup13(String qryGroup13) {
		this.qryGroup13 = qryGroup13;
	}

	public String getQryGroup14() {
		return this.qryGroup14;
	}

	public void setQryGroup14(String qryGroup14) {
		this.qryGroup14 = qryGroup14;
	}

	public String getQryGroup15() {
		return this.qryGroup15;
	}

	public void setQryGroup15(String qryGroup15) {
		this.qryGroup15 = qryGroup15;
	}

	public String getQryGroup16() {
		return this.qryGroup16;
	}

	public void setQryGroup16(String qryGroup16) {
		this.qryGroup16 = qryGroup16;
	}

	public String getQryGroup17() {
		return this.qryGroup17;
	}

	public void setQryGroup17(String qryGroup17) {
		this.qryGroup17 = qryGroup17;
	}

	public String getQryGroup18() {
		return this.qryGroup18;
	}

	public void setQryGroup18(String qryGroup18) {
		this.qryGroup18 = qryGroup18;
	}

	public String getQryGroup19() {
		return this.qryGroup19;
	}

	public void setQryGroup19(String qryGroup19) {
		this.qryGroup19 = qryGroup19;
	}

	public String getQryGroup20() {
		return this.qryGroup20;
	}

	public void setQryGroup20(String qryGroup20) {
		this.qryGroup20 = qryGroup20;
	}

	public String getQryGroup21() {
		return this.qryGroup21;
	}

	public void setQryGroup21(String qryGroup21) {
		this.qryGroup21 = qryGroup21;
	}

	public String getQryGroup22() {
		return this.qryGroup22;
	}

	public void setQryGroup22(String qryGroup22) {
		this.qryGroup22 = qryGroup22;
	}

	public String getQryGroup23() {
		return this.qryGroup23;
	}

	public void setQryGroup23(String qryGroup23) {
		this.qryGroup23 = qryGroup23;
	}

	public String getQryGroup24() {
		return this.qryGroup24;
	}

	public void setQryGroup24(String qryGroup24) {
		this.qryGroup24 = qryGroup24;
	}

	public String getQryGroup25() {
		return this.qryGroup25;
	}

	public void setQryGroup25(String qryGroup25) {
		this.qryGroup25 = qryGroup25;
	}

	public String getQryGroup26() {
		return this.qryGroup26;
	}

	public void setQryGroup26(String qryGroup26) {
		this.qryGroup26 = qryGroup26;
	}

	public String getQryGroup27() {
		return this.qryGroup27;
	}

	public void setQryGroup27(String qryGroup27) {
		this.qryGroup27 = qryGroup27;
	}

	public String getQryGroup28() {
		return this.qryGroup28;
	}

	public void setQryGroup28(String qryGroup28) {
		this.qryGroup28 = qryGroup28;
	}

	public String getQryGroup29() {
		return this.qryGroup29;
	}

	public void setQryGroup29(String qryGroup29) {
		this.qryGroup29 = qryGroup29;
	}

	public String getQryGroup30() {
		return this.qryGroup30;
	}

	public void setQryGroup30(String qryGroup30) {
		this.qryGroup30 = qryGroup30;
	}

	public String getQryGroup31() {
		return this.qryGroup31;
	}

	public void setQryGroup31(String qryGroup31) {
		this.qryGroup31 = qryGroup31;
	}

	public String getQryGroup32() {
		return this.qryGroup32;
	}

	public void setQryGroup32(String qryGroup32) {
		this.qryGroup32 = qryGroup32;
	}

	public String getQryGroup33() {
		return this.qryGroup33;
	}

	public void setQryGroup33(String qryGroup33) {
		this.qryGroup33 = qryGroup33;
	}

	public String getQryGroup34() {
		return this.qryGroup34;
	}

	public void setQryGroup34(String qryGroup34) {
		this.qryGroup34 = qryGroup34;
	}

	public String getQryGroup35() {
		return this.qryGroup35;
	}

	public void setQryGroup35(String qryGroup35) {
		this.qryGroup35 = qryGroup35;
	}

	public String getQryGroup36() {
		return this.qryGroup36;
	}

	public void setQryGroup36(String qryGroup36) {
		this.qryGroup36 = qryGroup36;
	}

	public String getQryGroup37() {
		return this.qryGroup37;
	}

	public void setQryGroup37(String qryGroup37) {
		this.qryGroup37 = qryGroup37;
	}

	public String getQryGroup38() {
		return this.qryGroup38;
	}

	public void setQryGroup38(String qryGroup38) {
		this.qryGroup38 = qryGroup38;
	}

	public String getQryGroup39() {
		return this.qryGroup39;
	}

	public void setQryGroup39(String qryGroup39) {
		this.qryGroup39 = qryGroup39;
	}

	public String getQryGroup40() {
		return this.qryGroup40;
	}

	public void setQryGroup40(String qryGroup40) {
		this.qryGroup40 = qryGroup40;
	}

	public String getQryGroup41() {
		return this.qryGroup41;
	}

	public void setQryGroup41(String qryGroup41) {
		this.qryGroup41 = qryGroup41;
	}

	public String getQryGroup42() {
		return this.qryGroup42;
	}

	public void setQryGroup42(String qryGroup42) {
		this.qryGroup42 = qryGroup42;
	}

	public String getQryGroup43() {
		return this.qryGroup43;
	}

	public void setQryGroup43(String qryGroup43) {
		this.qryGroup43 = qryGroup43;
	}

	public String getQryGroup44() {
		return this.qryGroup44;
	}

	public void setQryGroup44(String qryGroup44) {
		this.qryGroup44 = qryGroup44;
	}

	public String getQryGroup45() {
		return this.qryGroup45;
	}

	public void setQryGroup45(String qryGroup45) {
		this.qryGroup45 = qryGroup45;
	}

	public String getQryGroup46() {
		return this.qryGroup46;
	}

	public void setQryGroup46(String qryGroup46) {
		this.qryGroup46 = qryGroup46;
	}

	public String getQryGroup47() {
		return this.qryGroup47;
	}

	public void setQryGroup47(String qryGroup47) {
		this.qryGroup47 = qryGroup47;
	}

	public String getQryGroup48() {
		return this.qryGroup48;
	}

	public void setQryGroup48(String qryGroup48) {
		this.qryGroup48 = qryGroup48;
	}

	public String getQryGroup49() {
		return this.qryGroup49;
	}

	public void setQryGroup49(String qryGroup49) {
		this.qryGroup49 = qryGroup49;
	}

	public String getQryGroup50() {
		return this.qryGroup50;
	}

	public void setQryGroup50(String qryGroup50) {
		this.qryGroup50 = qryGroup50;
	}

	public String getQryGroup51() {
		return this.qryGroup51;
	}

	public void setQryGroup51(String qryGroup51) {
		this.qryGroup51 = qryGroup51;
	}

	public String getQryGroup52() {
		return this.qryGroup52;
	}

	public void setQryGroup52(String qryGroup52) {
		this.qryGroup52 = qryGroup52;
	}

	public String getQryGroup53() {
		return this.qryGroup53;
	}

	public void setQryGroup53(String qryGroup53) {
		this.qryGroup53 = qryGroup53;
	}

	public String getQryGroup54() {
		return this.qryGroup54;
	}

	public void setQryGroup54(String qryGroup54) {
		this.qryGroup54 = qryGroup54;
	}

	public String getQryGroup55() {
		return this.qryGroup55;
	}

	public void setQryGroup55(String qryGroup55) {
		this.qryGroup55 = qryGroup55;
	}

	public String getQryGroup56() {
		return this.qryGroup56;
	}

	public void setQryGroup56(String qryGroup56) {
		this.qryGroup56 = qryGroup56;
	}

	public String getQryGroup57() {
		return this.qryGroup57;
	}

	public void setQryGroup57(String qryGroup57) {
		this.qryGroup57 = qryGroup57;
	}

	public String getQryGroup58() {
		return this.qryGroup58;
	}

	public void setQryGroup58(String qryGroup58) {
		this.qryGroup58 = qryGroup58;
	}

	public String getQryGroup59() {
		return this.qryGroup59;
	}

	public void setQryGroup59(String qryGroup59) {
		this.qryGroup59 = qryGroup59;
	}

	public String getQryGroup60() {
		return this.qryGroup60;
	}

	public void setQryGroup60(String qryGroup60) {
		this.qryGroup60 = qryGroup60;
	}

	public String getQryGroup61() {
		return this.qryGroup61;
	}

	public void setQryGroup61(String qryGroup61) {
		this.qryGroup61 = qryGroup61;
	}

	public String getQryGroup62() {
		return this.qryGroup62;
	}

	public void setQryGroup62(String qryGroup62) {
		this.qryGroup62 = qryGroup62;
	}

	public String getQryGroup63() {
		return this.qryGroup63;
	}

	public void setQryGroup63(String qryGroup63) {
		this.qryGroup63 = qryGroup63;
	}

	public String getQryGroup64() {
		return this.qryGroup64;
	}

	public void setQryGroup64(String qryGroup64) {
		this.qryGroup64 = qryGroup64;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getExportCode() {
		return this.exportCode;
	}

	public void setExportCode(String exportCode) {
		this.exportCode = exportCode;
	}

	public Double getSalFactor1() {
		return this.salFactor1;
	}

	public void setSalFactor1(Double salFactor1) {
		this.salFactor1 = salFactor1;
	}

	public Double getSalFactor2() {
		return this.salFactor2;
	}

	public void setSalFactor2(Double salFactor2) {
		this.salFactor2 = salFactor2;
	}

	public Double getSalFactor3() {
		return this.salFactor3;
	}

	public void setSalFactor3(Double salFactor3) {
		this.salFactor3 = salFactor3;
	}

	public Double getSalFactor4() {
		return this.salFactor4;
	}

	public void setSalFactor4(Double salFactor4) {
		this.salFactor4 = salFactor4;
	}

	public Double getPurFactor1() {
		return this.purFactor1;
	}

	public void setPurFactor1(Double purFactor1) {
		this.purFactor1 = purFactor1;
	}

	public Double getPurFactor2() {
		return this.purFactor2;
	}

	public void setPurFactor2(Double purFactor2) {
		this.purFactor2 = purFactor2;
	}

	public Double getPurFactor3() {
		return this.purFactor3;
	}

	public void setPurFactor3(Double purFactor3) {
		this.purFactor3 = purFactor3;
	}

	public Double getPurFactor4() {
		return this.purFactor4;
	}

	public void setPurFactor4(Double purFactor4) {
		this.purFactor4 = purFactor4;
	}

	public String getSalFormula() {
		return this.salFormula;
	}

	public void setSalFormula(String salFormula) {
		this.salFormula = salFormula;
	}

	public String getPurFormula() {
		return this.purFormula;
	}

	public void setPurFormula(String purFormula) {
		this.purFormula = purFormula;
	}

	public String getVatGroupPu() {
		return this.vatGroupPu;
	}

	public void setVatGroupPu(String vatGroupPu) {
		this.vatGroupPu = vatGroupPu;
	}

	public Double getAvgPrice() {
		return this.avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getPurPackMsr() {
		return this.purPackMsr;
	}

	public void setPurPackMsr(String purPackMsr) {
		this.purPackMsr = purPackMsr;
	}

	public Double getPurPackUn() {
		return this.purPackUn;
	}

	public void setPurPackUn(Double purPackUn) {
		this.purPackUn = purPackUn;
	}

	public String getSalPackMsr() {
		return this.salPackMsr;
	}

	public void setSalPackMsr(String salPackMsr) {
		this.salPackMsr = salPackMsr;
	}

	public Double getSalPackUn() {
		return this.salPackUn;
	}

	public void setSalPackUn(Double salPackUn) {
		this.salPackUn = salPackUn;
	}

	public Short getScncounter() {
		return this.scncounter;
	}

	public void setScncounter(Short scncounter) {
		this.scncounter = scncounter;
	}

	public String getManBtchNum() {
		return this.manBtchNum;
	}

	public void setManBtchNum(String manBtchNum) {
		this.manBtchNum = manBtchNum;
	}

	public String getManOutOnly() {
		return this.manOutOnly;
	}

	public void setManOutOnly(String manOutOnly) {
		this.manOutOnly = manOutOnly;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getValidFor() {
		return this.validFor;
	}

	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}

	public Timestamp getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public String getFrozenFor() {
		return this.frozenFor;
	}

	public void setFrozenFor(String frozenFor) {
		this.frozenFor = frozenFor;
	}

	public Timestamp getFrozenFrom() {
		return this.frozenFrom;
	}

	public void setFrozenFrom(Timestamp frozenFrom) {
		this.frozenFrom = frozenFrom;
	}

	public Timestamp getFrozenTo() {
		return this.frozenTo;
	}

	public void setFrozenTo(Timestamp frozenTo) {
		this.frozenTo = frozenTo;
	}

	public String getBlockOut() {
		return this.blockOut;
	}

	public void setBlockOut(String blockOut) {
		this.blockOut = blockOut;
	}

	public String getValidComm() {
		return this.validComm;
	}

	public void setValidComm(String validComm) {
		this.validComm = validComm;
	}

	public String getFrozenComm() {
		return this.frozenComm;
	}

	public void setFrozenComm(String frozenComm) {
		this.frozenComm = frozenComm;
	}

	public Short getLogInstanc() {
		return this.logInstanc;
	}

	public void setLogInstanc(Short logInstanc) {
		this.logInstanc = logInstanc;
	}

	public String getObjType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getSww() {
		return this.sww;
	}

	public void setSww(String sww) {
		this.sww = sww;
	}

	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Integer getDocEntry() {
		return this.docEntry;
	}

	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}

	public String getExpensAcct() {
		return this.expensAcct;
	}

	public void setExpensAcct(String expensAcct) {
		this.expensAcct = expensAcct;
	}

	public String getFrgnInAcct() {
		return this.frgnInAcct;
	}

	public void setFrgnInAcct(String frgnInAcct) {
		this.frgnInAcct = frgnInAcct;
	}

	public Short getShipType() {
		return this.shipType;
	}

	public void setShipType(Short shipType) {
		this.shipType = shipType;
	}

	public String getGlmethod() {
		return this.glmethod;
	}

	public void setGlmethod(String glmethod) {
		this.glmethod = glmethod;
	}

	public String getEcinAcct() {
		return this.ecinAcct;
	}

	public void setEcinAcct(String ecinAcct) {
		this.ecinAcct = ecinAcct;
	}

	public String getFrgnExpAcc() {
		return this.frgnExpAcc;
	}

	public void setFrgnExpAcc(String frgnExpAcc) {
		this.frgnExpAcc = frgnExpAcc;
	}

	public String getEcexpAcc() {
		return this.ecexpAcc;
	}

	public void setEcexpAcc(String ecexpAcc) {
		this.ecexpAcc = ecexpAcc;
	}

	public String getTaxType() {
		return this.taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getByWh() {
		return this.byWh;
	}

	public void setByWh(String byWh) {
		this.byWh = byWh;
	}

	public String getWtliable() {
		return this.wtliable;
	}

	public void setWtliable(String wtliable) {
		this.wtliable = wtliable;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getWarrntTmpl() {
		return this.warrntTmpl;
	}

	public void setWarrntTmpl(String warrntTmpl) {
		this.warrntTmpl = warrntTmpl;
	}

	public String getBaseUnit() {
		return this.baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getCountryOrg() {
		return this.countryOrg;
	}

	public void setCountryOrg(String countryOrg) {
		this.countryOrg = countryOrg;
	}

	public Double getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(Double stockValue) {
		this.stockValue = stockValue;
	}

	public String getPhantom() {
		return this.phantom;
	}

	public void setPhantom(String phantom) {
		this.phantom = phantom;
	}

	public String getIssueMthd() {
		return this.issueMthd;
	}

	public void setIssueMthd(String issueMthd) {
		this.issueMthd = issueMthd;
	}

	public String getFree1() {
		return this.free1;
	}

	public void setFree1(String free1) {
		this.free1 = free1;
	}

	public Double getPricingPrc() {
		return this.pricingPrc;
	}

	public void setPricingPrc(Double pricingPrc) {
		this.pricingPrc = pricingPrc;
	}

	public String getMngMethod() {
		return this.mngMethod;
	}

	public void setMngMethod(String mngMethod) {
		this.mngMethod = mngMethod;
	}

	public Double getReorderPnt() {
		return this.reorderPnt;
	}

	public void setReorderPnt(Double reorderPnt) {
		this.reorderPnt = reorderPnt;
	}

	public String getInvntryUom() {
		return this.invntryUom;
	}

	public void setInvntryUom(String invntryUom) {
		this.invntryUom = invntryUom;
	}

	public String getPlaningSys() {
		return this.planingSys;
	}

	public void setPlaningSys(String planingSys) {
		this.planingSys = planingSys;
	}

	public String getPrcrmntMtd() {
		return this.prcrmntMtd;
	}

	public void setPrcrmntMtd(String prcrmntMtd) {
		this.prcrmntMtd = prcrmntMtd;
	}

	public Short getOrdrIntrvl() {
		return this.ordrIntrvl;
	}

	public void setOrdrIntrvl(Short ordrIntrvl) {
		this.ordrIntrvl = ordrIntrvl;
	}

	public Double getOrdrMulti() {
		return this.ordrMulti;
	}

	public void setOrdrMulti(Double ordrMulti) {
		this.ordrMulti = ordrMulti;
	}

	public Double getMinOrdrQty() {
		return this.minOrdrQty;
	}

	public void setMinOrdrQty(Double minOrdrQty) {
		this.minOrdrQty = minOrdrQty;
	}

	public Integer getLeadTime() {
		return this.leadTime;
	}

	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	public String getIndirctTax() {
		return this.indirctTax;
	}

	public void setIndirctTax(String indirctTax) {
		this.indirctTax = indirctTax;
	}

	public String getTaxCodeAr() {
		return this.taxCodeAr;
	}

	public void setTaxCodeAr(String taxCodeAr) {
		this.taxCodeAr = taxCodeAr;
	}

	public String getTaxCodeAp() {
		return this.taxCodeAp;
	}

	public void setTaxCodeAp(String taxCodeAp) {
		this.taxCodeAp = taxCodeAp;
	}

	public Integer getOsvcCode() {
		return this.osvcCode;
	}

	public void setOsvcCode(Integer osvcCode) {
		this.osvcCode = osvcCode;
	}

	public Integer getIsvcCode() {
		return this.isvcCode;
	}

	public void setIsvcCode(Integer isvcCode) {
		this.isvcCode = isvcCode;
	}

	public Integer getServiceGrp() {
		return this.serviceGrp;
	}

	public void setServiceGrp(Integer serviceGrp) {
		this.serviceGrp = serviceGrp;
	}

	public Integer getNcmcode() {
		return this.ncmcode;
	}

	public void setNcmcode(Integer ncmcode) {
		this.ncmcode = ncmcode;
	}

	public String getMatType() {
		return this.matType;
	}

	public void setMatType(String matType) {
		this.matType = matType;
	}

	public Integer getMatGrp() {
		return this.matGrp;
	}

	public void setMatGrp(Integer matGrp) {
		this.matGrp = matGrp;
	}

	public String getProductSrc() {
		return this.productSrc;
	}

	public void setProductSrc(String productSrc) {
		this.productSrc = productSrc;
	}

	public Integer getServiceCtg() {
		return this.serviceCtg;
	}

	public void setServiceCtg(Integer serviceCtg) {
		this.serviceCtg = serviceCtg;
	}

	public String getItemClass() {
		return this.itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getExcisable() {
		return this.excisable;
	}

	public void setExcisable(String excisable) {
		this.excisable = excisable;
	}

	public Integer getChapterId() {
		return this.chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getNotifyAsn() {
		return this.notifyAsn;
	}

	public void setNotifyAsn(String notifyAsn) {
		this.notifyAsn = notifyAsn;
	}

	public String getProAssNum() {
		return this.proAssNum;
	}

	public void setProAssNum(String proAssNum) {
		this.proAssNum = proAssNum;
	}

	public Double getAssblValue() {
		return this.assblValue;
	}

	public void setAssblValue(Double assblValue) {
		this.assblValue = assblValue;
	}

	public Integer getDnfentry() {
		return this.dnfentry;
	}

	public void setDnfentry(Integer dnfentry) {
		this.dnfentry = dnfentry;
	}

	public Short getUserSign2() {
		return this.userSign2;
	}

	public void setUserSign2(Short userSign2) {
		this.userSign2 = userSign2;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getTaxCtg() {
		return this.taxCtg;
	}

	public void setTaxCtg(String taxCtg) {
		this.taxCtg = taxCtg;
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

	public Double getUMtzl() {
		return this.UMtzl;
	}

	public void setUMtzl(Double UMtzl) {
		this.UMtzl = UMtzl;
	}

	public Double getUScddFz() {
		return this.UScddFz;
	}

	public void setUScddFz(Double UScddFz) {
		this.UScddFz = UScddFz;
	}

	public Double getUScddwwgFz() {
		return this.UScddwwgFz;
	}

	public void setUScddwwgFz(Double UScddwwgFz) {
		this.UScddwwgFz = UScddwwgFz;
	}

}