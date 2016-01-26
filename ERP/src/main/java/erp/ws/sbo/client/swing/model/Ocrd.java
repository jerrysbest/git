package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Ocrd entity. @author MyEclipse Persistence Tools
 */

public class Ocrd implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5994964928143120235L;
	private String cardCode;
	private String cardName;
	private String cardType;
	private Short groupCode;
	private String cmpPrivate;
	private String address;
	private String zipCode;
	private String mailAddres;
	private String mailZipCod;
	private String phone1;
	private String phone2;
	private String fax;
	private String cntctPrsn;
	private String notes;
	private Double balance;
	private Double checksBal;
	private Double dnotesBal;
	private Double ordersBal;
	private Short groupNum;
	private Double creditLine;
	private Double debtLine;
	private Double discount;
	private String vatStatus;
	private String licTradNum;
	private String ddctStatus;
	private Double ddctPrcnt;
	private Timestamp validUntil;
	private Integer chrctrstcs;
	private Integer exMatchNum;
	private Integer inMatchNum;
	private Short listNum;
	private Double dnoteBalFc;
	private Double orderBalFc;
	private Double dnoteBalSy;
	private Double orderBalSy;
	private String transfered;
	private String balTrnsfrd;
	private Double intrstRate;
	private Double commission;
	private Short commGrCode;
	private String freeText;
	private Short slpCode;
	private String prevYearAc;
	private String currency;
	private String rateDifAct;
	private Double balanceSys;
	private Double balanceFc;
	private String protected_;
	private String cellular;
	private Short avrageLate;
	private String city;
	private String county;
	private String country;
	private String mailCity;
	private String mailCounty;
	private String mailCountr;
	private String EMail;
	private String picture;
	private String dflAccount;
	private String dflBranch;
	private String bankCode;
	private String addId;
	private String pager;
	private String fatherCard;
	private String cardFname;
	private String fatherType;
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
	private String ddctOffice;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String exportCode;
	private Short dscntObjct;
	private String dscntRel;
	private Short spgcounter;
	private Integer sppcounter;
	private String ddctFileNo;
	private Short scncounter;
	private Double minIntrst;
	private String dataSource;
	private Integer oprCount;
	private String exemptNo;
	private Integer priority;
	private Short creditCard;
	private String crCardNum;
	private Timestamp cardValid;
	private Short userSign;
	private String locMth;
	private String validFor;
	private Timestamp validFrom;
	private Timestamp validTo;
	private String frozenFor;
	private Timestamp frozenFrom;
	private Timestamp frozenTo;
	private String semployed;
	private Integer mthcounter;
	private Integer bnkcounter;
	private Integer ddgKey;
	private Integer ddtKey;
	private String validComm;
	private String frozenComm;
	private String chainStore;
	private String discInRet;
	private String state1;
	private String state2;
	private String vatGroup;
	private Short logInstanc;
	private String objType;
	private String indicator;
	private Short shipType;
	private String debPayAcct;
	private String shipToDef;
	private String block;
	private String mailBlock;
	private String password;
	private String ecvatGroup;
	private String deleted;
	private String iban;
	private Integer docEntry;
	private Integer formCode;
	private String box1099;
	private String pymCode;
	private String backOrder;
	private String partDelivr;
	private Integer dunnLevel;
	private Timestamp dunnDate;
	private String blockDunn;
	private String bankCountr;
	private String collecAuth;
	private String dme;
	private String instrucKey;
	private String singlePaym;
	private String isrbillId;
	private String paymBlock;
	private String refDetails;
	private String houseBank;
	private String ownerIdNum;
	private Integer pyBlckDesc;
	private String housBnkCry;
	private String housBnkAct;
	private String housBnkBrn;
	private String projectCod;
	private Integer sysMatchNo;
	private String vatIdUnCmp;
	private String agentCode;
	private Short tolrncDays;
	private String selfInvoic;
	private String deferrTax;
	private String letterNum;
	private Double maxAmount;
	private Timestamp fromDate;
	private Timestamp toDate;
	private String wtliable;
	private String crtfcateNo;
	private Timestamp expireDate;
	private String ninum;
	private String accCritria;
	private String wtcode;
	private String equ;
	private String hldCode;
	private String connBp;
	private Integer mltMthNum;
	private String typWtreprt;
	private String vatregNum;
	private String repName;
	private String industry;
	private String business;
	private String wttaxCat;
	private String isDomestic;
	private String isResident;
	private String autoCalBcg;
	private String otrCtlAcct;
	private String aliasName;
	private String building;
	private String mailBuildi;
	private String boEprsnt;
	private String boEdiscnt;
	private String boEonClct;
	private String unpaidBoE;
	private String itwtcode;
	private String dunTerm;
	private String channlBp;
	private Integer dfTcnician;
	private Integer territory;
	private String billToDef;
	private String dpmClear;
	private String intrntSite;
	private Integer langCode;
	private Integer housActKey;
	private String profession;
	private Short cdpnum;
	private Integer dflBankKey;
	private String bcacode;
	private String useShpdGd;
	private String regNum;
	private String verifNum;
	private String bankCtlKey;
	private String housCtlKey;
	private String addrType;
	private String insurOp347;
	private String mailAddrTy;
	private String streetNo;
	private String mailStrNo;
	private String taxRndRule;
	private Integer vendTid;
	private String threshOver;
	private String surOver;
	private String vendorOcup;
	private String opCode347;
	private String dpmIntAct;
	private String residenNum;
	private Short userSign2;
	private String plngGroup;
	private String vatIdnum;
	private String affiliate;
	private String mivzExpSts;
	private String hierchDdct;
	private String certWht;
	private String certBkeep;
	private String whshaamGrp;
	private Integer industryC;
	private Integer datevAcct;
	private String datevFirst;
	private String gtsregNum;
	private String gtsbankAct;
	private String gtsbilAddr;
	private Short ntswebSite;
	private Integer edocExpFrm;
	private String taxIdIdent;
	private String UJgqdz;
	private Double USpfz1;
	private Double USpfz2;
	private Double UYjfz1;
	private Double UYjfz2;
	private Double UZxgls;
	private String UTerId;

	// Constructors

	/** default constructor */
	public Ocrd() {
	}

	/** minimal constructor */
	public Ocrd(String cardCode, Integer docEntry, Double UZxgls) {
		this.cardCode = cardCode;
		this.docEntry = docEntry;
		this.UZxgls = UZxgls;
	}

	/** full constructor */
	public Ocrd(String cardCode, String cardName, String cardType,
			Short groupCode, String cmpPrivate, String address, String zipCode,
			String mailAddres, String mailZipCod, String phone1, String phone2,
			String fax, String cntctPrsn, String notes, Double balance,
			Double checksBal, Double dnotesBal, Double ordersBal,
			Short groupNum, Double creditLine, Double debtLine,
			Double discount, String vatStatus, String licTradNum,
			String ddctStatus, Double ddctPrcnt, Timestamp validUntil,
			Integer chrctrstcs, Integer exMatchNum, Integer inMatchNum,
			Short listNum, Double dnoteBalFc, Double orderBalFc,
			Double dnoteBalSy, Double orderBalSy, String transfered,
			String balTrnsfrd, Double intrstRate, Double commission,
			Short commGrCode, String freeText, Short slpCode,
			String prevYearAc, String currency, String rateDifAct,
			Double balanceSys, Double balanceFc, String protected_,
			String cellular, Short avrageLate, String city, String county,
			String country, String mailCity, String mailCounty,
			String mailCountr, String EMail, String picture, String dflAccount,
			String dflBranch, String bankCode, String addId, String pager,
			String fatherCard, String cardFname, String fatherType,
			String qryGroup1, String qryGroup2, String qryGroup3,
			String qryGroup4, String qryGroup5, String qryGroup6,
			String qryGroup7, String qryGroup8, String qryGroup9,
			String qryGroup10, String qryGroup11, String qryGroup12,
			String qryGroup13, String qryGroup14, String qryGroup15,
			String qryGroup16, String qryGroup17, String qryGroup18,
			String qryGroup19, String qryGroup20, String qryGroup21,
			String qryGroup22, String qryGroup23, String qryGroup24,
			String qryGroup25, String qryGroup26, String qryGroup27,
			String qryGroup28, String qryGroup29, String qryGroup30,
			String qryGroup31, String qryGroup32, String qryGroup33,
			String qryGroup34, String qryGroup35, String qryGroup36,
			String qryGroup37, String qryGroup38, String qryGroup39,
			String qryGroup40, String qryGroup41, String qryGroup42,
			String qryGroup43, String qryGroup44, String qryGroup45,
			String qryGroup46, String qryGroup47, String qryGroup48,
			String qryGroup49, String qryGroup50, String qryGroup51,
			String qryGroup52, String qryGroup53, String qryGroup54,
			String qryGroup55, String qryGroup56, String qryGroup57,
			String qryGroup58, String qryGroup59, String qryGroup60,
			String qryGroup61, String qryGroup62, String qryGroup63,
			String qryGroup64, String ddctOffice, Timestamp createDate,
			Timestamp updateDate, String exportCode, Short dscntObjct,
			String dscntRel, Short spgcounter, Integer sppcounter,
			String ddctFileNo, Short scncounter, Double minIntrst,
			String dataSource, Integer oprCount, String exemptNo,
			Integer priority, Short creditCard, String crCardNum,
			Timestamp cardValid, Short userSign, String locMth,
			String validFor, Timestamp validFrom, Timestamp validTo,
			String frozenFor, Timestamp frozenFrom, Timestamp frozenTo,
			String semployed, Integer mthcounter, Integer bnkcounter,
			Integer ddgKey, Integer ddtKey, String validComm,
			String frozenComm, String chainStore, String discInRet,
			String state1, String state2, String vatGroup, Short logInstanc,
			String objType, String indicator, Short shipType,
			String debPayAcct, String shipToDef, String block,
			String mailBlock, String password, String ecvatGroup,
			String deleted, String iban, Integer docEntry, Integer formCode,
			String box1099, String pymCode, String backOrder,
			String partDelivr, Integer dunnLevel, Timestamp dunnDate,
			String blockDunn, String bankCountr, String collecAuth, String dme,
			String instrucKey, String singlePaym, String isrbillId,
			String paymBlock, String refDetails, String houseBank,
			String ownerIdNum, Integer pyBlckDesc, String housBnkCry,
			String housBnkAct, String housBnkBrn, String projectCod,
			Integer sysMatchNo, String vatIdUnCmp, String agentCode,
			Short tolrncDays, String selfInvoic, String deferrTax,
			String letterNum, Double maxAmount, Timestamp fromDate,
			Timestamp toDate, String wtliable, String crtfcateNo,
			Timestamp expireDate, String ninum, String accCritria,
			String wtcode, String equ, String hldCode, String connBp,
			Integer mltMthNum, String typWtreprt, String vatregNum,
			String repName, String industry, String business, String wttaxCat,
			String isDomestic, String isResident, String autoCalBcg,
			String otrCtlAcct, String aliasName, String building,
			String mailBuildi, String boEprsnt, String boEdiscnt,
			String boEonClct, String unpaidBoE, String itwtcode,
			String dunTerm, String channlBp, Integer dfTcnician,
			Integer territory, 
			/*String billToDef, String dpmClear,
			String intrntSite, Integer langCode, Integer housActKey,
			String profession, Short cdpnum, Integer dflBankKey,
			String bcacode, String useShpdGd, String regNum, String verifNum,
			String bankCtlKey, String housCtlKey, String addrType,
			String insurOp347, String mailAddrTy, String streetNo,
			String mailStrNo, String taxRndRule, Integer vendTid,
			String threshOver, String surOver, String vendorOcup,
			String opCode347, String dpmIntAct, String residenNum,
			Short userSign2, String plngGroup, String vatIdnum,
			String affiliate, String mivzExpSts, String hierchDdct,
			String certWht, String certBkeep, String whshaamGrp,
			Integer industryC, Integer datevAcct, String datevFirst,
			String gtsregNum, String gtsbankAct, String gtsbilAddr,
			Short ntswebSite, Integer edocExpFrm, String taxIdIdent,*/
			String UJgqdz, Double USpfz1, Double USpfz2, Double UYjfz1,
			Double UYjfz2, Double UZxgls, String UTerId) {
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.cardType = cardType;
		this.groupCode = groupCode;
		this.cmpPrivate = cmpPrivate;
		this.address = address;
		this.zipCode = zipCode;
		this.mailAddres = mailAddres;
		this.mailZipCod = mailZipCod;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.cntctPrsn = cntctPrsn;
		this.notes = notes;
		this.balance = balance;
		this.checksBal = checksBal;
		this.dnotesBal = dnotesBal;
		this.ordersBal = ordersBal;
		this.groupNum = groupNum;
		this.creditLine = creditLine;
		this.debtLine = debtLine;
		this.discount = discount;
		this.vatStatus = vatStatus;
		this.licTradNum = licTradNum;
		this.ddctStatus = ddctStatus;
		this.ddctPrcnt = ddctPrcnt;
		this.validUntil = validUntil;
		this.chrctrstcs = chrctrstcs;
		this.exMatchNum = exMatchNum;
		this.inMatchNum = inMatchNum;
		this.listNum = listNum;
		this.dnoteBalFc = dnoteBalFc;
		this.orderBalFc = orderBalFc;
		this.dnoteBalSy = dnoteBalSy;
		this.orderBalSy = orderBalSy;
		this.transfered = transfered;
		this.balTrnsfrd = balTrnsfrd;
		this.intrstRate = intrstRate;
		this.commission = commission;
		this.commGrCode = commGrCode;
		this.freeText = freeText;
		this.slpCode = slpCode;
		this.prevYearAc = prevYearAc;
		this.currency = currency;
		this.rateDifAct = rateDifAct;
		this.balanceSys = balanceSys;
		this.balanceFc = balanceFc;
		this.protected_ = protected_;
		this.cellular = cellular;
		this.avrageLate = avrageLate;
		this.city = city;
		this.county = county;
		this.country = country;
		this.mailCity = mailCity;
		this.mailCounty = mailCounty;
		this.mailCountr = mailCountr;
		this.EMail = EMail;
		this.picture = picture;
		this.dflAccount = dflAccount;
		this.dflBranch = dflBranch;
		this.bankCode = bankCode;
		this.addId = addId;
		this.pager = pager;
		this.fatherCard = fatherCard;
		this.cardFname = cardFname;
		this.fatherType = fatherType;
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
		this.ddctOffice = ddctOffice;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.exportCode = exportCode;
		this.dscntObjct = dscntObjct;
		this.dscntRel = dscntRel;
		this.spgcounter = spgcounter;
		this.sppcounter = sppcounter;
		this.ddctFileNo = ddctFileNo;
		this.scncounter = scncounter;
		this.minIntrst = minIntrst;
		this.dataSource = dataSource;
		this.oprCount = oprCount;
		this.exemptNo = exemptNo;
		this.priority = priority;
		this.creditCard = creditCard;
		this.crCardNum = crCardNum;
		this.cardValid = cardValid;
		this.userSign = userSign;
		this.locMth = locMth;
		this.validFor = validFor;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.frozenFor = frozenFor;
		this.frozenFrom = frozenFrom;
		this.frozenTo = frozenTo;
		this.semployed = semployed;
		this.mthcounter = mthcounter;
		this.bnkcounter = bnkcounter;
		this.ddgKey = ddgKey;
		this.ddtKey = ddtKey;
		this.validComm = validComm;
		this.frozenComm = frozenComm;
		this.chainStore = chainStore;
		this.discInRet = discInRet;
		this.state1 = state1;
		this.state2 = state2;
		this.vatGroup = vatGroup;
		this.logInstanc = logInstanc;
		this.objType = objType;
		this.indicator = indicator;
		this.shipType = shipType;
		this.debPayAcct = debPayAcct;
		this.shipToDef = shipToDef;
		this.block = block;
		this.mailBlock = mailBlock;
		this.password = password;
		this.ecvatGroup = ecvatGroup;
		this.deleted = deleted;
		this.iban = iban;
		this.docEntry = docEntry;
		this.formCode = formCode;
		this.box1099 = box1099;
		this.pymCode = pymCode;
		this.backOrder = backOrder;
		this.partDelivr = partDelivr;
		this.dunnLevel = dunnLevel;
		this.dunnDate = dunnDate;
		this.blockDunn = blockDunn;
		this.bankCountr = bankCountr;
		this.collecAuth = collecAuth;
		this.dme = dme;
		this.instrucKey = instrucKey;
		this.singlePaym = singlePaym;
		this.isrbillId = isrbillId;
		this.paymBlock = paymBlock;
		this.refDetails = refDetails;
		this.houseBank = houseBank;
		this.ownerIdNum = ownerIdNum;
		this.pyBlckDesc = pyBlckDesc;
		this.housBnkCry = housBnkCry;
		this.housBnkAct = housBnkAct;
		this.housBnkBrn = housBnkBrn;
		this.projectCod = projectCod;
		this.sysMatchNo = sysMatchNo;
		this.vatIdUnCmp = vatIdUnCmp;
		this.agentCode = agentCode;
		this.tolrncDays = tolrncDays;
		this.selfInvoic = selfInvoic;
		this.deferrTax = deferrTax;
		this.letterNum = letterNum;
		this.maxAmount = maxAmount;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.wtliable = wtliable;
		this.crtfcateNo = crtfcateNo;
		this.expireDate = expireDate;
		this.ninum = ninum;
		this.accCritria = accCritria;
		this.wtcode = wtcode;
		this.equ = equ;
		this.hldCode = hldCode;
		this.connBp = connBp;
		this.mltMthNum = mltMthNum;
		this.typWtreprt = typWtreprt;
		this.vatregNum = vatregNum;
		this.repName = repName;
		this.industry = industry;
		this.business = business;
		this.wttaxCat = wttaxCat;
		this.isDomestic = isDomestic;
		this.isResident = isResident;
		this.autoCalBcg = autoCalBcg;
		this.otrCtlAcct = otrCtlAcct;
		this.aliasName = aliasName;
		this.building = building;
		this.mailBuildi = mailBuildi;
		this.boEprsnt = boEprsnt;
		this.boEdiscnt = boEdiscnt;
		this.boEonClct = boEonClct;
		this.unpaidBoE = unpaidBoE;
		this.itwtcode = itwtcode;
		this.dunTerm = dunTerm;
		this.channlBp = channlBp;
		this.dfTcnician = dfTcnician;
		this.territory = territory;
		/*this.billToDef = billToDef;
		this.dpmClear = dpmClear;
		this.intrntSite = intrntSite;
		this.langCode = langCode;
		this.housActKey = housActKey;
		this.profession = profession;
		this.cdpnum = cdpnum;
		this.dflBankKey = dflBankKey;
		this.bcacode = bcacode;
		this.useShpdGd = useShpdGd;
		this.regNum = regNum;
		this.verifNum = verifNum;
		this.bankCtlKey = bankCtlKey;
		this.housCtlKey = housCtlKey;
		this.addrType = addrType;
		this.insurOp347 = insurOp347;
		this.mailAddrTy = mailAddrTy;
		this.streetNo = streetNo;
		this.mailStrNo = mailStrNo;
		this.taxRndRule = taxRndRule;
		this.vendTid = vendTid;
		this.threshOver = threshOver;
		this.surOver = surOver;
		this.vendorOcup = vendorOcup;
		this.opCode347 = opCode347;
		this.dpmIntAct = dpmIntAct;
		this.residenNum = residenNum;
		this.userSign2 = userSign2;
		this.plngGroup = plngGroup;
		this.vatIdnum = vatIdnum;
		this.affiliate = affiliate;
		this.mivzExpSts = mivzExpSts;
		this.hierchDdct = hierchDdct;
		this.certWht = certWht;
		this.certBkeep = certBkeep;
		this.whshaamGrp = whshaamGrp;
		this.industryC = industryC;
		this.datevAcct = datevAcct;
		this.datevFirst = datevFirst;
		this.gtsregNum = gtsregNum;
		this.gtsbankAct = gtsbankAct;
		this.gtsbilAddr = gtsbilAddr;
		this.ntswebSite = ntswebSite;
		this.edocExpFrm = edocExpFrm;
		this.taxIdIdent = taxIdIdent;*/
		this.UJgqdz = UJgqdz;
		this.USpfz1 = USpfz1;
		this.USpfz2 = USpfz2;
		this.UYjfz1 = UYjfz1;
		this.UYjfz2 = UYjfz2;
		this.UZxgls = UZxgls;
		this.UTerId = UTerId;
	}

	// Property accessors

	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Short getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(Short groupCode) {
		this.groupCode = groupCode;
	}

	public String getCmpPrivate() {
		return this.cmpPrivate;
	}

	public void setCmpPrivate(String cmpPrivate) {
		this.cmpPrivate = cmpPrivate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMailAddres() {
		return this.mailAddres;
	}

	public void setMailAddres(String mailAddres) {
		this.mailAddres = mailAddres;
	}

	public String getMailZipCod() {
		return this.mailZipCod;
	}

	public void setMailZipCod(String mailZipCod) {
		this.mailZipCod = mailZipCod;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCntctPrsn() {
		return this.cntctPrsn;
	}

	public void setCntctPrsn(String cntctPrsn) {
		this.cntctPrsn = cntctPrsn;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getChecksBal() {
		return this.checksBal;
	}

	public void setChecksBal(Double checksBal) {
		this.checksBal = checksBal;
	}

	public Double getDnotesBal() {
		return this.dnotesBal;
	}

	public void setDnotesBal(Double dnotesBal) {
		this.dnotesBal = dnotesBal;
	}

	public Double getOrdersBal() {
		return this.ordersBal;
	}

	public void setOrdersBal(Double ordersBal) {
		this.ordersBal = ordersBal;
	}

	public Short getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Short groupNum) {
		this.groupNum = groupNum;
	}

	public Double getCreditLine() {
		return this.creditLine;
	}

	public void setCreditLine(Double creditLine) {
		this.creditLine = creditLine;
	}

	public Double getDebtLine() {
		return this.debtLine;
	}

	public void setDebtLine(Double debtLine) {
		this.debtLine = debtLine;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getVatStatus() {
		return this.vatStatus;
	}

	public void setVatStatus(String vatStatus) {
		this.vatStatus = vatStatus;
	}

	public String getLicTradNum() {
		return this.licTradNum;
	}

	public void setLicTradNum(String licTradNum) {
		this.licTradNum = licTradNum;
	}

	public String getDdctStatus() {
		return this.ddctStatus;
	}

	public void setDdctStatus(String ddctStatus) {
		this.ddctStatus = ddctStatus;
	}

	public Double getDdctPrcnt() {
		return this.ddctPrcnt;
	}

	public void setDdctPrcnt(Double ddctPrcnt) {
		this.ddctPrcnt = ddctPrcnt;
	}

	public Timestamp getValidUntil() {
		return this.validUntil;
	}

	public void setValidUntil(Timestamp validUntil) {
		this.validUntil = validUntil;
	}

	public Integer getChrctrstcs() {
		return this.chrctrstcs;
	}

	public void setChrctrstcs(Integer chrctrstcs) {
		this.chrctrstcs = chrctrstcs;
	}

	public Integer getExMatchNum() {
		return this.exMatchNum;
	}

	public void setExMatchNum(Integer exMatchNum) {
		this.exMatchNum = exMatchNum;
	}

	public Integer getInMatchNum() {
		return this.inMatchNum;
	}

	public void setInMatchNum(Integer inMatchNum) {
		this.inMatchNum = inMatchNum;
	}

	public Short getListNum() {
		return this.listNum;
	}

	public void setListNum(Short listNum) {
		this.listNum = listNum;
	}

	public Double getDnoteBalFc() {
		return this.dnoteBalFc;
	}

	public void setDnoteBalFc(Double dnoteBalFc) {
		this.dnoteBalFc = dnoteBalFc;
	}

	public Double getOrderBalFc() {
		return this.orderBalFc;
	}

	public void setOrderBalFc(Double orderBalFc) {
		this.orderBalFc = orderBalFc;
	}

	public Double getDnoteBalSy() {
		return this.dnoteBalSy;
	}

	public void setDnoteBalSy(Double dnoteBalSy) {
		this.dnoteBalSy = dnoteBalSy;
	}

	public Double getOrderBalSy() {
		return this.orderBalSy;
	}

	public void setOrderBalSy(Double orderBalSy) {
		this.orderBalSy = orderBalSy;
	}

	public String getTransfered() {
		return this.transfered;
	}

	public void setTransfered(String transfered) {
		this.transfered = transfered;
	}

	public String getBalTrnsfrd() {
		return this.balTrnsfrd;
	}

	public void setBalTrnsfrd(String balTrnsfrd) {
		this.balTrnsfrd = balTrnsfrd;
	}

	public Double getIntrstRate() {
		return this.intrstRate;
	}

	public void setIntrstRate(Double intrstRate) {
		this.intrstRate = intrstRate;
	}

	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Short getCommGrCode() {
		return this.commGrCode;
	}

	public void setCommGrCode(Short commGrCode) {
		this.commGrCode = commGrCode;
	}

	public String getFreeText() {
		return this.freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public Short getSlpCode() {
		return this.slpCode;
	}

	public void setSlpCode(Short slpCode) {
		this.slpCode = slpCode;
	}

	public String getPrevYearAc() {
		return this.prevYearAc;
	}

	public void setPrevYearAc(String prevYearAc) {
		this.prevYearAc = prevYearAc;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRateDifAct() {
		return this.rateDifAct;
	}

	public void setRateDifAct(String rateDifAct) {
		this.rateDifAct = rateDifAct;
	}

	public Double getBalanceSys() {
		return this.balanceSys;
	}

	public void setBalanceSys(Double balanceSys) {
		this.balanceSys = balanceSys;
	}

	public Double getBalanceFc() {
		return this.balanceFc;
	}

	public void setBalanceFc(Double balanceFc) {
		this.balanceFc = balanceFc;
	}

	public String getProtected_() {
		return this.protected_;
	}

	public void setProtected_(String protected_) {
		this.protected_ = protected_;
	}

	public String getCellular() {
		return this.cellular;
	}

	public void setCellular(String cellular) {
		this.cellular = cellular;
	}

	public Short getAvrageLate() {
		return this.avrageLate;
	}

	public void setAvrageLate(Short avrageLate) {
		this.avrageLate = avrageLate;
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

	public String getMailCity() {
		return this.mailCity;
	}

	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	public String getMailCounty() {
		return this.mailCounty;
	}

	public void setMailCounty(String mailCounty) {
		this.mailCounty = mailCounty;
	}

	public String getMailCountr() {
		return this.mailCountr;
	}

	public void setMailCountr(String mailCountr) {
		this.mailCountr = mailCountr;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDflAccount() {
		return this.dflAccount;
	}

	public void setDflAccount(String dflAccount) {
		this.dflAccount = dflAccount;
	}

	public String getDflBranch() {
		return this.dflBranch;
	}

	public void setDflBranch(String dflBranch) {
		this.dflBranch = dflBranch;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAddId() {
		return this.addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	public String getPager() {
		return this.pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getFatherCard() {
		return this.fatherCard;
	}

	public void setFatherCard(String fatherCard) {
		this.fatherCard = fatherCard;
	}

	public String getCardFname() {
		return this.cardFname;
	}

	public void setCardFname(String cardFname) {
		this.cardFname = cardFname;
	}

	public String getFatherType() {
		return this.fatherType;
	}

	public void setFatherType(String fatherType) {
		this.fatherType = fatherType;
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

	public String getDdctOffice() {
		return this.ddctOffice;
	}

	public void setDdctOffice(String ddctOffice) {
		this.ddctOffice = ddctOffice;
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

	public Short getDscntObjct() {
		return this.dscntObjct;
	}

	public void setDscntObjct(Short dscntObjct) {
		this.dscntObjct = dscntObjct;
	}

	public String getDscntRel() {
		return this.dscntRel;
	}

	public void setDscntRel(String dscntRel) {
		this.dscntRel = dscntRel;
	}

	public Short getSpgcounter() {
		return this.spgcounter;
	}

	public void setSpgcounter(Short spgcounter) {
		this.spgcounter = spgcounter;
	}

	public Integer getSppcounter() {
		return this.sppcounter;
	}

	public void setSppcounter(Integer sppcounter) {
		this.sppcounter = sppcounter;
	}

	public String getDdctFileNo() {
		return this.ddctFileNo;
	}

	public void setDdctFileNo(String ddctFileNo) {
		this.ddctFileNo = ddctFileNo;
	}

	public Short getScncounter() {
		return this.scncounter;
	}

	public void setScncounter(Short scncounter) {
		this.scncounter = scncounter;
	}

	public Double getMinIntrst() {
		return this.minIntrst;
	}

	public void setMinIntrst(Double minIntrst) {
		this.minIntrst = minIntrst;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getOprCount() {
		return this.oprCount;
	}

	public void setOprCount(Integer oprCount) {
		this.oprCount = oprCount;
	}

	public String getExemptNo() {
		return this.exemptNo;
	}

	public void setExemptNo(String exemptNo) {
		this.exemptNo = exemptNo;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Short getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(Short creditCard) {
		this.creditCard = creditCard;
	}

	public String getCrCardNum() {
		return this.crCardNum;
	}

	public void setCrCardNum(String crCardNum) {
		this.crCardNum = crCardNum;
	}

	public Timestamp getCardValid() {
		return this.cardValid;
	}

	public void setCardValid(Timestamp cardValid) {
		this.cardValid = cardValid;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

	public String getLocMth() {
		return this.locMth;
	}

	public void setLocMth(String locMth) {
		this.locMth = locMth;
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

	public String getSemployed() {
		return this.semployed;
	}

	public void setSemployed(String semployed) {
		this.semployed = semployed;
	}

	public Integer getMthcounter() {
		return this.mthcounter;
	}

	public void setMthcounter(Integer mthcounter) {
		this.mthcounter = mthcounter;
	}

	public Integer getBnkcounter() {
		return this.bnkcounter;
	}

	public void setBnkcounter(Integer bnkcounter) {
		this.bnkcounter = bnkcounter;
	}

	public Integer getDdgKey() {
		return this.ddgKey;
	}

	public void setDdgKey(Integer ddgKey) {
		this.ddgKey = ddgKey;
	}

	public Integer getDdtKey() {
		return this.ddtKey;
	}

	public void setDdtKey(Integer ddtKey) {
		this.ddtKey = ddtKey;
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

	public String getChainStore() {
		return this.chainStore;
	}

	public void setChainStore(String chainStore) {
		this.chainStore = chainStore;
	}

	public String getDiscInRet() {
		return this.discInRet;
	}

	public void setDiscInRet(String discInRet) {
		this.discInRet = discInRet;
	}

	public String getState1() {
		return this.state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return this.state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getVatGroup() {
		return this.vatGroup;
	}

	public void setVatGroup(String vatGroup) {
		this.vatGroup = vatGroup;
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

	public String getIndicator() {
		return this.indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public Short getShipType() {
		return this.shipType;
	}

	public void setShipType(Short shipType) {
		this.shipType = shipType;
	}

	public String getDebPayAcct() {
		return this.debPayAcct;
	}

	public void setDebPayAcct(String debPayAcct) {
		this.debPayAcct = debPayAcct;
	}

	public String getShipToDef() {
		return this.shipToDef;
	}

	public void setShipToDef(String shipToDef) {
		this.shipToDef = shipToDef;
	}

	public String getBlock() {
		return this.block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getMailBlock() {
		return this.mailBlock;
	}

	public void setMailBlock(String mailBlock) {
		this.mailBlock = mailBlock;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEcvatGroup() {
		return this.ecvatGroup;
	}

	public void setEcvatGroup(String ecvatGroup) {
		this.ecvatGroup = ecvatGroup;
	}

	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Integer getDocEntry() {
		return this.docEntry;
	}

	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}

	public Integer getFormCode() {
		return this.formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public String getBox1099() {
		return this.box1099;
	}

	public void setBox1099(String box1099) {
		this.box1099 = box1099;
	}

	public String getPymCode() {
		return this.pymCode;
	}

	public void setPymCode(String pymCode) {
		this.pymCode = pymCode;
	}

	public String getBackOrder() {
		return this.backOrder;
	}

	public void setBackOrder(String backOrder) {
		this.backOrder = backOrder;
	}

	public String getPartDelivr() {
		return this.partDelivr;
	}

	public void setPartDelivr(String partDelivr) {
		this.partDelivr = partDelivr;
	}

	public Integer getDunnLevel() {
		return this.dunnLevel;
	}

	public void setDunnLevel(Integer dunnLevel) {
		this.dunnLevel = dunnLevel;
	}

	public Timestamp getDunnDate() {
		return this.dunnDate;
	}

	public void setDunnDate(Timestamp dunnDate) {
		this.dunnDate = dunnDate;
	}

	public String getBlockDunn() {
		return this.blockDunn;
	}

	public void setBlockDunn(String blockDunn) {
		this.blockDunn = blockDunn;
	}

	public String getBankCountr() {
		return this.bankCountr;
	}

	public void setBankCountr(String bankCountr) {
		this.bankCountr = bankCountr;
	}

	public String getCollecAuth() {
		return this.collecAuth;
	}

	public void setCollecAuth(String collecAuth) {
		this.collecAuth = collecAuth;
	}

	public String getDme() {
		return this.dme;
	}

	public void setDme(String dme) {
		this.dme = dme;
	}

	public String getInstrucKey() {
		return this.instrucKey;
	}

	public void setInstrucKey(String instrucKey) {
		this.instrucKey = instrucKey;
	}

	public String getSinglePaym() {
		return this.singlePaym;
	}

	public void setSinglePaym(String singlePaym) {
		this.singlePaym = singlePaym;
	}

	public String getIsrbillId() {
		return this.isrbillId;
	}

	public void setIsrbillId(String isrbillId) {
		this.isrbillId = isrbillId;
	}

	public String getPaymBlock() {
		return this.paymBlock;
	}

	public void setPaymBlock(String paymBlock) {
		this.paymBlock = paymBlock;
	}

	public String getRefDetails() {
		return this.refDetails;
	}

	public void setRefDetails(String refDetails) {
		this.refDetails = refDetails;
	}

	public String getHouseBank() {
		return this.houseBank;
	}

	public void setHouseBank(String houseBank) {
		this.houseBank = houseBank;
	}

	public String getOwnerIdNum() {
		return this.ownerIdNum;
	}

	public void setOwnerIdNum(String ownerIdNum) {
		this.ownerIdNum = ownerIdNum;
	}

	public Integer getPyBlckDesc() {
		return this.pyBlckDesc;
	}

	public void setPyBlckDesc(Integer pyBlckDesc) {
		this.pyBlckDesc = pyBlckDesc;
	}

	public String getHousBnkCry() {
		return this.housBnkCry;
	}

	public void setHousBnkCry(String housBnkCry) {
		this.housBnkCry = housBnkCry;
	}

	public String getHousBnkAct() {
		return this.housBnkAct;
	}

	public void setHousBnkAct(String housBnkAct) {
		this.housBnkAct = housBnkAct;
	}

	public String getHousBnkBrn() {
		return this.housBnkBrn;
	}

	public void setHousBnkBrn(String housBnkBrn) {
		this.housBnkBrn = housBnkBrn;
	}

	public String getProjectCod() {
		return this.projectCod;
	}

	public void setProjectCod(String projectCod) {
		this.projectCod = projectCod;
	}

	public Integer getSysMatchNo() {
		return this.sysMatchNo;
	}

	public void setSysMatchNo(Integer sysMatchNo) {
		this.sysMatchNo = sysMatchNo;
	}

	public String getVatIdUnCmp() {
		return this.vatIdUnCmp;
	}

	public void setVatIdUnCmp(String vatIdUnCmp) {
		this.vatIdUnCmp = vatIdUnCmp;
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public Short getTolrncDays() {
		return this.tolrncDays;
	}

	public void setTolrncDays(Short tolrncDays) {
		this.tolrncDays = tolrncDays;
	}

	public String getSelfInvoic() {
		return this.selfInvoic;
	}

	public void setSelfInvoic(String selfInvoic) {
		this.selfInvoic = selfInvoic;
	}

	public String getDeferrTax() {
		return this.deferrTax;
	}

	public void setDeferrTax(String deferrTax) {
		this.deferrTax = deferrTax;
	}

	public String getLetterNum() {
		return this.letterNum;
	}

	public void setLetterNum(String letterNum) {
		this.letterNum = letterNum;
	}

	public Double getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Timestamp getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return this.toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

	public String getWtliable() {
		return this.wtliable;
	}

	public void setWtliable(String wtliable) {
		this.wtliable = wtliable;
	}

	public String getCrtfcateNo() {
		return this.crtfcateNo;
	}

	public void setCrtfcateNo(String crtfcateNo) {
		this.crtfcateNo = crtfcateNo;
	}

	public Timestamp getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}

	public String getNinum() {
		return this.ninum;
	}

	public void setNinum(String ninum) {
		this.ninum = ninum;
	}

	public String getAccCritria() {
		return this.accCritria;
	}

	public void setAccCritria(String accCritria) {
		this.accCritria = accCritria;
	}

	public String getWtcode() {
		return this.wtcode;
	}

	public void setWtcode(String wtcode) {
		this.wtcode = wtcode;
	}

	public String getEqu() {
		return this.equ;
	}

	public void setEqu(String equ) {
		this.equ = equ;
	}

	public String getHldCode() {
		return this.hldCode;
	}

	public void setHldCode(String hldCode) {
		this.hldCode = hldCode;
	}

	public String getConnBp() {
		return this.connBp;
	}

	public void setConnBp(String connBp) {
		this.connBp = connBp;
	}

	public Integer getMltMthNum() {
		return this.mltMthNum;
	}

	public void setMltMthNum(Integer mltMthNum) {
		this.mltMthNum = mltMthNum;
	}

	public String getTypWtreprt() {
		return this.typWtreprt;
	}

	public void setTypWtreprt(String typWtreprt) {
		this.typWtreprt = typWtreprt;
	}

	public String getVatregNum() {
		return this.vatregNum;
	}

	public void setVatregNum(String vatregNum) {
		this.vatregNum = vatregNum;
	}

	public String getRepName() {
		return this.repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getWttaxCat() {
		return this.wttaxCat;
	}

	public void setWttaxCat(String wttaxCat) {
		this.wttaxCat = wttaxCat;
	}

	public String getIsDomestic() {
		return this.isDomestic;
	}

	public void setIsDomestic(String isDomestic) {
		this.isDomestic = isDomestic;
	}

	public String getIsResident() {
		return this.isResident;
	}

	public void setIsResident(String isResident) {
		this.isResident = isResident;
	}

	public String getAutoCalBcg() {
		return this.autoCalBcg;
	}

	public void setAutoCalBcg(String autoCalBcg) {
		this.autoCalBcg = autoCalBcg;
	}

	public String getOtrCtlAcct() {
		return this.otrCtlAcct;
	}

	public void setOtrCtlAcct(String otrCtlAcct) {
		this.otrCtlAcct = otrCtlAcct;
	}

	public String getAliasName() {
		return this.aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getMailBuildi() {
		return this.mailBuildi;
	}

	public void setMailBuildi(String mailBuildi) {
		this.mailBuildi = mailBuildi;
	}

	public String getBoEprsnt() {
		return this.boEprsnt;
	}

	public void setBoEprsnt(String boEprsnt) {
		this.boEprsnt = boEprsnt;
	}

	public String getBoEdiscnt() {
		return this.boEdiscnt;
	}

	public void setBoEdiscnt(String boEdiscnt) {
		this.boEdiscnt = boEdiscnt;
	}

	public String getBoEonClct() {
		return this.boEonClct;
	}

	public void setBoEonClct(String boEonClct) {
		this.boEonClct = boEonClct;
	}

	public String getUnpaidBoE() {
		return this.unpaidBoE;
	}

	public void setUnpaidBoE(String unpaidBoE) {
		this.unpaidBoE = unpaidBoE;
	}

	public String getItwtcode() {
		return this.itwtcode;
	}

	public void setItwtcode(String itwtcode) {
		this.itwtcode = itwtcode;
	}

	public String getDunTerm() {
		return this.dunTerm;
	}

	public void setDunTerm(String dunTerm) {
		this.dunTerm = dunTerm;
	}

	public String getChannlBp() {
		return this.channlBp;
	}

	public void setChannlBp(String channlBp) {
		this.channlBp = channlBp;
	}

	public Integer getDfTcnician() {
		return this.dfTcnician;
	}

	public void setDfTcnician(Integer dfTcnician) {
		this.dfTcnician = dfTcnician;
	}

	public Integer getTerritory() {
		return this.territory;
	}

	public void setTerritory(Integer territory) {
		this.territory = territory;
	}

	public String getBillToDef() {
		return this.billToDef;
	}

	public void setBillToDef(String billToDef) {
		this.billToDef = billToDef;
	}

	public String getDpmClear() {
		return this.dpmClear;
	}

	public void setDpmClear(String dpmClear) {
		this.dpmClear = dpmClear;
	}

	public String getIntrntSite() {
		return this.intrntSite;
	}

	public void setIntrntSite(String intrntSite) {
		this.intrntSite = intrntSite;
	}

	public Integer getLangCode() {
		return this.langCode;
	}

	public void setLangCode(Integer langCode) {
		this.langCode = langCode;
	}

	public Integer getHousActKey() {
		return this.housActKey;
	}

	public void setHousActKey(Integer housActKey) {
		this.housActKey = housActKey;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Short getCdpnum() {
		return this.cdpnum;
	}

	public void setCdpnum(Short cdpnum) {
		this.cdpnum = cdpnum;
	}

	public Integer getDflBankKey() {
		return this.dflBankKey;
	}

	public void setDflBankKey(Integer dflBankKey) {
		this.dflBankKey = dflBankKey;
	}

	public String getBcacode() {
		return this.bcacode;
	}

	public void setBcacode(String bcacode) {
		this.bcacode = bcacode;
	}

	public String getUseShpdGd() {
		return this.useShpdGd;
	}

	public void setUseShpdGd(String useShpdGd) {
		this.useShpdGd = useShpdGd;
	}

	public String getRegNum() {
		return this.regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getVerifNum() {
		return this.verifNum;
	}

	public void setVerifNum(String verifNum) {
		this.verifNum = verifNum;
	}

	public String getBankCtlKey() {
		return this.bankCtlKey;
	}

	public void setBankCtlKey(String bankCtlKey) {
		this.bankCtlKey = bankCtlKey;
	}

	public String getHousCtlKey() {
		return this.housCtlKey;
	}

	public void setHousCtlKey(String housCtlKey) {
		this.housCtlKey = housCtlKey;
	}

	public String getAddrType() {
		return this.addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public String getInsurOp347() {
		return this.insurOp347;
	}

	public void setInsurOp347(String insurOp347) {
		this.insurOp347 = insurOp347;
	}

	public String getMailAddrTy() {
		return this.mailAddrTy;
	}

	public void setMailAddrTy(String mailAddrTy) {
		this.mailAddrTy = mailAddrTy;
	}

	public String getStreetNo() {
		return this.streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getMailStrNo() {
		return this.mailStrNo;
	}

	public void setMailStrNo(String mailStrNo) {
		this.mailStrNo = mailStrNo;
	}

	public String getTaxRndRule() {
		return this.taxRndRule;
	}

	public void setTaxRndRule(String taxRndRule) {
		this.taxRndRule = taxRndRule;
	}

	public Integer getVendTid() {
		return this.vendTid;
	}

	public void setVendTid(Integer vendTid) {
		this.vendTid = vendTid;
	}

	public String getThreshOver() {
		return this.threshOver;
	}

	public void setThreshOver(String threshOver) {
		this.threshOver = threshOver;
	}

	public String getSurOver() {
		return this.surOver;
	}

	public void setSurOver(String surOver) {
		this.surOver = surOver;
	}

	public String getVendorOcup() {
		return this.vendorOcup;
	}

	public void setVendorOcup(String vendorOcup) {
		this.vendorOcup = vendorOcup;
	}

	public String getOpCode347() {
		return this.opCode347;
	}

	public void setOpCode347(String opCode347) {
		this.opCode347 = opCode347;
	}

	public String getDpmIntAct() {
		return this.dpmIntAct;
	}

	public void setDpmIntAct(String dpmIntAct) {
		this.dpmIntAct = dpmIntAct;
	}

	public String getResidenNum() {
		return this.residenNum;
	}

	public void setResidenNum(String residenNum) {
		this.residenNum = residenNum;
	}

	public Short getUserSign2() {
		return this.userSign2;
	}

	public void setUserSign2(Short userSign2) {
		this.userSign2 = userSign2;
	}

	public String getPlngGroup() {
		return this.plngGroup;
	}

	public void setPlngGroup(String plngGroup) {
		this.plngGroup = plngGroup;
	}

	public String getVatIdnum() {
		return this.vatIdnum;
	}

	public void setVatIdnum(String vatIdnum) {
		this.vatIdnum = vatIdnum;
	}

	public String getAffiliate() {
		return this.affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public String getMivzExpSts() {
		return this.mivzExpSts;
	}

	public void setMivzExpSts(String mivzExpSts) {
		this.mivzExpSts = mivzExpSts;
	}

	public String getHierchDdct() {
		return this.hierchDdct;
	}

	public void setHierchDdct(String hierchDdct) {
		this.hierchDdct = hierchDdct;
	}

	public String getCertWht() {
		return this.certWht;
	}

	public void setCertWht(String certWht) {
		this.certWht = certWht;
	}

	public String getCertBkeep() {
		return this.certBkeep;
	}

	public void setCertBkeep(String certBkeep) {
		this.certBkeep = certBkeep;
	}

	public String getWhshaamGrp() {
		return this.whshaamGrp;
	}

	public void setWhshaamGrp(String whshaamGrp) {
		this.whshaamGrp = whshaamGrp;
	}

	public Integer getIndustryC() {
		return this.industryC;
	}

	public void setIndustryC(Integer industryC) {
		this.industryC = industryC;
	}

	public Integer getDatevAcct() {
		return this.datevAcct;
	}

	public void setDatevAcct(Integer datevAcct) {
		this.datevAcct = datevAcct;
	}

	public String getDatevFirst() {
		return this.datevFirst;
	}

	public void setDatevFirst(String datevFirst) {
		this.datevFirst = datevFirst;
	}

	public String getGtsregNum() {
		return this.gtsregNum;
	}

	public void setGtsregNum(String gtsregNum) {
		this.gtsregNum = gtsregNum;
	}

	public String getGtsbankAct() {
		return this.gtsbankAct;
	}

	public void setGtsbankAct(String gtsbankAct) {
		this.gtsbankAct = gtsbankAct;
	}

	public String getGtsbilAddr() {
		return this.gtsbilAddr;
	}

	public void setGtsbilAddr(String gtsbilAddr) {
		this.gtsbilAddr = gtsbilAddr;
	}

	public Short getNtswebSite() {
		return this.ntswebSite;
	}

	public void setNtswebSite(Short ntswebSite) {
		this.ntswebSite = ntswebSite;
	}

	public Integer getEdocExpFrm() {
		return this.edocExpFrm;
	}

	public void setEdocExpFrm(Integer edocExpFrm) {
		this.edocExpFrm = edocExpFrm;
	}

	public String getTaxIdIdent() {
		return this.taxIdIdent;
	}

	public void setTaxIdIdent(String taxIdIdent) {
		this.taxIdIdent = taxIdIdent;
	}

	public String getUJgqdz() {
		return this.UJgqdz;
	}

	public void setUJgqdz(String UJgqdz) {
		this.UJgqdz = UJgqdz;
	}

	public Double getUSpfz1() {
		return this.USpfz1;
	}

	public void setUSpfz1(Double USpfz1) {
		this.USpfz1 = USpfz1;
	}

	public Double getUSpfz2() {
		return this.USpfz2;
	}

	public void setUSpfz2(Double USpfz2) {
		this.USpfz2 = USpfz2;
	}

	public Double getUYjfz1() {
		return this.UYjfz1;
	}

	public void setUYjfz1(Double UYjfz1) {
		this.UYjfz1 = UYjfz1;
	}

	public Double getUYjfz2() {
		return this.UYjfz2;
	}

	public void setUYjfz2(Double UYjfz2) {
		this.UYjfz2 = UYjfz2;
	}

	public Double getUZxgls() {
		return this.UZxgls;
	}

	public void setUZxgls(Double UZxgls) {
		this.UZxgls = UZxgls;
	}

	public String getUTerId() {
		return this.UTerId;
	}

	public void setUTerId(String UTerId) {
		this.UTerId = UTerId;
	}

}