package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Odrf entity. @author MyEclipse Persistence Tools
 */

public class Odrf implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3704193261797423902L;
	private Integer docEntry;
	private Integer docNum;
	private String docType;
	private String canceled;
	private String handwrtten;
	private String printed;
	private String docStatus;
	private String invntSttus;
	private String transfered;
	private String objType;
	private Timestamp docDate;
	private Timestamp docDueDate;
	private String cardCode;
	private String cardName;
	private String address;
	private String numAtCard;
	private Double vatPercent;
	private Double vatSum;
	private Double vatSumFc;
	private Double discPrcnt;
	private Double discSum;
	private Double discSumFc;
	private String docCur;
	private Double docRate;
	private Double docTotal;
	private Double docTotalFc;
	private Double paidToDate;
	private Double paidFc;
	private Double grosProfit;
	private Double grosProfFc;
	private String ref1;
	private String ref2;
	private String comments;
	private String jrnlMemo;
	private Integer transId;
	private Integer receiptNum;
	private Short groupNum;
	private Short docTime;
	private Short slpCode;
	private Short trnspCode;
	private String partSupply;
	private String confirmed;
	private Short grossBase;
	private Integer importEnt;
	private String createTran;
	private String summryType;
	private String updInvnt;
	private String updCardBal;
	private Short instance;
	private Integer flags;
	private String invntDirec;
	private Integer cntctCode;
	private String showScn;
	private String fatherCard;
	private Double sysRate;
	private String curSource;
	private Double vatSumSy;
	private Double discSumSy;
	private Double docTotalSy;
	private Double paidSys;
	private String fatherType;
	private Double grosProfSy;
	private Timestamp updateDate;
	private String isIct;
	private Timestamp createDate;
	private Double volume;
	private Short volUnit;
	private Double weight;
	private Short weightUnit;
	private Short series;
	private Timestamp taxDate;
	private String filler;
	private String dataSource;
	private String stampNum;
	private String isCrin;
	private Integer finncPriod;
	private Short userSign;
	private String selfInv;
	private Double vatPaid;
	private Double vatPaidFc;
	private Double vatPaidSys;
	private Short userSign2;
	private String wddStatus;
	private Integer draftKey;
	private Double totalExpns;
	private Double totalExpFc;
	private Double totalExpSc;
	private Integer dunnLevel;
	private String address2;
	private Short logInstanc;
	private String exported;
	private Integer stationId;
	private String indicator;
	private String netProc;
	private Double aqcsTax;
	private Double aqcsTaxFc;
	private Double aqcsTaxSc;
	private Double cashDiscPr;
	private Double cashDiscnt;
	private Double cashDiscFc;
	private Double cashDiscSc;
	private String shipToCode;
	private String licTradNum;
	private String paymentRef;
	private Double wtsum;
	private Double wtsumFc;
	private Double wtsumSc;
	private Double roundDif;
	private Double roundDifFc;
	private Double roundDifSy;
	private String checkDigit;
	private Integer form1099;
	private String box1099;
	private String submitted;
	private String poPrss;
	private String rounding;
	private String revisionPo;
	private Short segment;
	private Timestamp reqDate;
	private Timestamp cancelDate;
	private String pickStatus;
	private String pick;
	private String blockDunn;
	private String peyMethod;
	private String payBlock;
	private Integer payBlckRef;
	private String maxDscn;
	private String reserve;
	private Double max1099;
	private String cntrlBnk;
	private String pickRmrk;
	private String isrcodLine;
	private Double expAppl;
	private Double expApplFc;
	private Double expApplSc;
	private String project;
	private String deferrTax;
	private String letterNum;
	private Timestamp fromDate;
	private Timestamp toDate;
	private Double wtapplied;
	private Double wtappliedF;
	private String boeReserev;
	private String agentCode;
	private Double wtappliedS;
	private Double equVatSum;
	private Double equVatSumF;
	private Double equVatSumS;
	private Short installmnt;
	private String vatfirst;
	private Double nnSbAmnt;
	private Double nnSbAmntSc;
	private Double nbSbAmntFc;
	private Double exepAmnt;
	private Double exepAmntSc;
	private Double exepAmntFc;
	private Timestamp vatDate;
	private String corrExt;
	private Integer corrInv;
	private Integer ncorrInv;
	private String ceecflag;
	private Double baseAmnt;
	private Double baseAmntSc;
	private Double baseAmntFc;
	private String ctlAccount;
	private Integer bplid;
	private String bplname;
	private String vatregNum;
	private String txInvRptNo;
	private Timestamp txInvRptDt;
	private String kvvatcode;
	private String wtdetails;
	private Integer sumAbsId;
	private Timestamp sumRptDate;
	private String pindicator;
	private String manualNum;
	private String useShpdGd;
	private Double baseVtAt;
	private Double baseVtAtSc;
	private Double baseVtAtFc;
	private Double nnSbVat;
	private Double nnSbVatSc;
	private Double nbSbVatFc;
	private Double exptVat;
	private Double exptVatSc;
	private Double exptVatFc;
	private Double lypmtAt;
	private Double lypmtAtSc;
	private Double lypmtAtFc;
	private Double expAnSum;
	private Double expAnSys;
	private Double expAnFrgn;
	private String docSubType;
	private String dpmStatus;
	private Double dpmAmnt;
	private Double dpmAmntSc;
	private Double dpmAmntFc;
	private String dpmDrawn;
	private Double dpmPrcnt;
	private Double paidSum;
	private Double paidSumFc;
	private Double paidSumSc;
	private String folioPref;
	private Integer folioNum;
	private Double dpmAppl;
	private Double dpmApplFc;
	private Double dpmApplSc;
	private Integer lpgFolioN;
	private String header;
	private String footer;
	private String posted;
	private Integer ownerCode;
	private String bpchCode;
	private Integer bpchCntc;
	private String payToCode;
	private String isPaytoBnk;
	private String bnkCntry;
	private String bankCode;
	private String bnkAccount;
	private String bnkBranch;
	private String isIns;
	private String trackNo;
	private String versionNum;
	private Integer langCode;
	private String bpnameOw;
	private String billToOw;
	private String shipToOw;
	private String retInvoice;
	private Timestamp clsDate;
	private Integer minvNum;
	private Timestamp minvDate;
	private Short seqCode;
	private Integer serial;
	private String seriesStr;
	private String subStr;
	private String model;
	private Double taxOnExp;
	private Double taxOnExpFc;
	private Double taxOnExpSc;
	private Double taxOnExAp;
	private Double taxOnExApF;
	private Double taxOnExApS;
	private String lastPmnTyp;
	private Integer lndCstNum;
	private String useCorrVat;
	private String blkCredMmo;
	private String openForLaC;
	private String excised;
	private Timestamp excRefDate;
	private String excRmvTime;
	private Double srvGpPrcnt;
	private Integer depositNum;
	private String certNum;
	private String dutyStatus;
	private String autoCrtFlw;
	private Timestamp flwRefDate;
	private String flwRefNum;
	private Integer vatJenum;
	private Double dpmVat;
	private Double dpmVatFc;
	private Double dpmVatSc;
	private Double dpmAppVat;
	private Double dpmAppVatF;
	private Double dpmAppVatS;
	private String insurOp347;
	private String ignRelDoc;
	private String buildDesc;
	private String residenNum;
	private Integer checker;
	private Integer payee;
	private Integer copyNumber;
	private String ntsapprov;
	private Short ntswebSite;
	private String ntseTaxNo;
	private String ntsapprNo;
	private String payDuMonth;
	private Short extraMonth;
	private Short extraDays;
	private Short cdcOffset;
	private String signMsg;
	private String signDigest;
	private String certifNum;
	private Integer keyVersion;
	private String edocGenTyp;
	private Short eseries;
	private String edocNum;
	private Integer edocExpFrm;
	private String USms;
	private String UYsdxdjlx;
	private Timestamp UDhdate;
	private String UDq;

	// Constructors

	/** default constructor */
	public Odrf() {
	}

	/** minimal constructor */
	public Odrf(Integer docEntry) {
		this.docEntry = docEntry;
	}

	/** full constructor */
	public Odrf(Integer docEntry, Integer docNum, String docType,
			String canceled, String handwrtten, String printed,
			String docStatus, String invntSttus, String transfered,
			String objType, Timestamp docDate, Timestamp docDueDate,
			String cardCode, String cardName, String address, String numAtCard,
			Double vatPercent, Double vatSum, Double vatSumFc,
			Double discPrcnt, Double discSum, Double discSumFc, String docCur,
			Double docRate, Double docTotal, Double docTotalFc,
			Double paidToDate, Double paidFc, Double grosProfit,
			Double grosProfFc, String ref1, String ref2, String comments,
			String jrnlMemo, Integer transId, Integer receiptNum,
			Short groupNum, Short docTime, Short slpCode, Short trnspCode,
			String partSupply, String confirmed, Short grossBase,
			Integer importEnt, String createTran, String summryType,
			String updInvnt, String updCardBal, Short instance, Integer flags,
			String invntDirec, Integer cntctCode, String showScn,
			String fatherCard, Double sysRate, String curSource,
			Double vatSumSy, Double discSumSy, Double docTotalSy,
			Double paidSys, String fatherType, Double grosProfSy,
			Timestamp updateDate, String isIct, Timestamp createDate,
			Double volume, Short volUnit, Double weight, Short weightUnit,
			Short series, Timestamp taxDate, String filler, String dataSource,
			String stampNum, String isCrin, Integer finncPriod, Short userSign,
			String selfInv, Double vatPaid, Double vatPaidFc,
			Double vatPaidSys, Short userSign2, String wddStatus,
			Integer draftKey, Double totalExpns, Double totalExpFc,
			Double totalExpSc, Integer dunnLevel, String address2,
			Short logInstanc, String exported, Integer stationId,
			String indicator, String netProc, Double aqcsTax, Double aqcsTaxFc,
			Double aqcsTaxSc, Double cashDiscPr, Double cashDiscnt,
			Double cashDiscFc, Double cashDiscSc, String shipToCode,
			String licTradNum, String paymentRef, Double wtsum, Double wtsumFc,
			Double wtsumSc, Double roundDif, Double roundDifFc,
			Double roundDifSy, String checkDigit, Integer form1099,
			String box1099, String submitted, String poPrss, String rounding,
			String revisionPo, Short segment, Timestamp reqDate,
			Timestamp cancelDate, String pickStatus, String pick,
			String blockDunn, String peyMethod, String payBlock,
			Integer payBlckRef, String maxDscn, String reserve, Double max1099,
			String cntrlBnk, String pickRmrk, String isrcodLine,
			Double expAppl, Double expApplFc, Double expApplSc, String project,
			String deferrTax, String letterNum, Timestamp fromDate,
			Timestamp toDate, Double wtapplied, Double wtappliedF,
			String boeReserev, String agentCode, Double wtappliedS,
			Double equVatSum, Double equVatSumF, Double equVatSumS,
			Short installmnt, String vatfirst, Double nnSbAmnt,
			Double nnSbAmntSc, Double nbSbAmntFc, Double exepAmnt,
			Double exepAmntSc, Double exepAmntFc, Timestamp vatDate,
			String corrExt, Integer corrInv, Integer ncorrInv, String ceecflag,
			Double baseAmnt, Double baseAmntSc, Double baseAmntFc,
			String ctlAccount, Integer bplid, String bplname, String vatregNum,
			String txInvRptNo, Timestamp txInvRptDt, String kvvatcode,
			String wtdetails, Integer sumAbsId, Timestamp sumRptDate,
			String pindicator, String manualNum, String useShpdGd,
			Double baseVtAt, Double baseVtAtSc, Double baseVtAtFc,
			Double nnSbVat, Double nnSbVatSc, Double nbSbVatFc, Double exptVat,
			Double exptVatSc, Double exptVatFc, Double lypmtAt,
			Double lypmtAtSc, Double lypmtAtFc, Double expAnSum,
			Double expAnSys, Double expAnFrgn, String docSubType,
			String dpmStatus, Double dpmAmnt, Double dpmAmntSc,
			Double dpmAmntFc, String dpmDrawn, Double dpmPrcnt, Double paidSum,
			Double paidSumFc, Double paidSumSc, String folioPref,
			Integer folioNum, Double dpmAppl, Double dpmApplFc,
			Double dpmApplSc, Integer lpgFolioN, String header, String footer,
			String posted, Integer ownerCode, String bpchCode,
			Integer bpchCntc, String payToCode, String isPaytoBnk,
			String bnkCntry, String bankCode, String bnkAccount,
			String bnkBranch, String isIns, String trackNo, String versionNum,
			Integer langCode, String bpnameOw, String billToOw,
			String shipToOw, String retInvoice, Timestamp clsDate,
			Integer minvNum, Timestamp minvDate, Short seqCode, Integer serial,
			String seriesStr, String subStr, String model, Double taxOnExp,
			Double taxOnExpFc, Double taxOnExpSc, Double taxOnExAp,
			Double taxOnExApF, Double taxOnExApS, String lastPmnTyp,
			/*Integer lndCstNum, String useCorrVat, String blkCredMmo,
			String openForLaC, String excised, Timestamp excRefDate,
			String excRmvTime, Double srvGpPrcnt, Integer depositNum,
			String certNum, String dutyStatus, String autoCrtFlw,
			Timestamp flwRefDate, String flwRefNum, Integer vatJenum,
			Double dpmVat, Double dpmVatFc, Double dpmVatSc, Double dpmAppVat,
			Double dpmAppVatF, Double dpmAppVatS, String insurOp347,
			String ignRelDoc, String buildDesc, String residenNum,
			Integer checker, Integer payee, Integer copyNumber,
			String ntsapprov, Short ntswebSite, String ntseTaxNo,
			String ntsapprNo, String payDuMonth, Short extraMonth,
			Short extraDays, Short cdcOffset, String signMsg,
			String signDigest, String certifNum, Integer keyVersion,
			String edocGenTyp, Short eseries, String edocNum,
			Integer edocExpFrm, */
			String USms, String UYsdxdjlx,
			Timestamp UDhdate, String UDq) {
		this.docEntry = docEntry;
		this.docNum = docNum;
		this.docType = docType;
		this.canceled = canceled;
		this.handwrtten = handwrtten;
		this.printed = printed;
		this.docStatus = docStatus;
		this.invntSttus = invntSttus;
		this.transfered = transfered;
		this.objType = objType;
		this.docDate = docDate;
		this.docDueDate = docDueDate;
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.address = address;
		this.numAtCard = numAtCard;
		this.vatPercent = vatPercent;
		this.vatSum = vatSum;
		this.vatSumFc = vatSumFc;
		this.discPrcnt = discPrcnt;
		this.discSum = discSum;
		this.discSumFc = discSumFc;
		this.docCur = docCur;
		this.docRate = docRate;
		this.docTotal = docTotal;
		this.docTotalFc = docTotalFc;
		this.paidToDate = paidToDate;
		this.paidFc = paidFc;
		this.grosProfit = grosProfit;
		this.grosProfFc = grosProfFc;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.comments = comments;
		this.jrnlMemo = jrnlMemo;
		this.transId = transId;
		this.receiptNum = receiptNum;
		this.groupNum = groupNum;
		this.docTime = docTime;
		this.slpCode = slpCode;
		this.trnspCode = trnspCode;
		this.partSupply = partSupply;
		this.confirmed = confirmed;
		this.grossBase = grossBase;
		this.importEnt = importEnt;
		this.createTran = createTran;
		this.summryType = summryType;
		this.updInvnt = updInvnt;
		this.updCardBal = updCardBal;
		this.instance = instance;
		this.flags = flags;
		this.invntDirec = invntDirec;
		this.cntctCode = cntctCode;
		this.showScn = showScn;
		this.fatherCard = fatherCard;
		this.sysRate = sysRate;
		this.curSource = curSource;
		this.vatSumSy = vatSumSy;
		this.discSumSy = discSumSy;
		this.docTotalSy = docTotalSy;
		this.paidSys = paidSys;
		this.fatherType = fatherType;
		this.grosProfSy = grosProfSy;
		this.updateDate = updateDate;
		this.isIct = isIct;
		this.createDate = createDate;
		this.volume = volume;
		this.volUnit = volUnit;
		this.weight = weight;
		this.weightUnit = weightUnit;
		this.series = series;
		this.taxDate = taxDate;
		this.filler = filler;
		this.dataSource = dataSource;
		this.stampNum = stampNum;
		this.isCrin = isCrin;
		this.finncPriod = finncPriod;
		this.userSign = userSign;
		this.selfInv = selfInv;
		this.vatPaid = vatPaid;
		this.vatPaidFc = vatPaidFc;
		this.vatPaidSys = vatPaidSys;
		this.userSign2 = userSign2;
		this.wddStatus = wddStatus;
		this.draftKey = draftKey;
		this.totalExpns = totalExpns;
		this.totalExpFc = totalExpFc;
		this.totalExpSc = totalExpSc;
		this.dunnLevel = dunnLevel;
		this.address2 = address2;
		this.logInstanc = logInstanc;
		this.exported = exported;
		this.stationId = stationId;
		this.indicator = indicator;
		this.netProc = netProc;
		this.aqcsTax = aqcsTax;
		this.aqcsTaxFc = aqcsTaxFc;
		this.aqcsTaxSc = aqcsTaxSc;
		this.cashDiscPr = cashDiscPr;
		this.cashDiscnt = cashDiscnt;
		this.cashDiscFc = cashDiscFc;
		this.cashDiscSc = cashDiscSc;
		this.shipToCode = shipToCode;
		this.licTradNum = licTradNum;
		this.paymentRef = paymentRef;
		this.wtsum = wtsum;
		this.wtsumFc = wtsumFc;
		this.wtsumSc = wtsumSc;
		this.roundDif = roundDif;
		this.roundDifFc = roundDifFc;
		this.roundDifSy = roundDifSy;
		this.checkDigit = checkDigit;
		this.form1099 = form1099;
		this.box1099 = box1099;
		this.submitted = submitted;
		this.poPrss = poPrss;
		this.rounding = rounding;
		this.revisionPo = revisionPo;
		this.segment = segment;
		this.reqDate = reqDate;
		this.cancelDate = cancelDate;
		this.pickStatus = pickStatus;
		this.pick = pick;
		this.blockDunn = blockDunn;
		this.peyMethod = peyMethod;
		this.payBlock = payBlock;
		this.payBlckRef = payBlckRef;
		this.maxDscn = maxDscn;
		this.reserve = reserve;
		this.max1099 = max1099;
		this.cntrlBnk = cntrlBnk;
		this.pickRmrk = pickRmrk;
		this.isrcodLine = isrcodLine;
		this.expAppl = expAppl;
		this.expApplFc = expApplFc;
		this.expApplSc = expApplSc;
		this.project = project;
		this.deferrTax = deferrTax;
		this.letterNum = letterNum;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.wtapplied = wtapplied;
		this.wtappliedF = wtappliedF;
		this.boeReserev = boeReserev;
		this.agentCode = agentCode;
		this.wtappliedS = wtappliedS;
		this.equVatSum = equVatSum;
		this.equVatSumF = equVatSumF;
		this.equVatSumS = equVatSumS;
		this.installmnt = installmnt;
		this.vatfirst = vatfirst;
		this.nnSbAmnt = nnSbAmnt;
		this.nnSbAmntSc = nnSbAmntSc;
		this.nbSbAmntFc = nbSbAmntFc;
		this.exepAmnt = exepAmnt;
		this.exepAmntSc = exepAmntSc;
		this.exepAmntFc = exepAmntFc;
		this.vatDate = vatDate;
		this.corrExt = corrExt;
		this.corrInv = corrInv;
		this.ncorrInv = ncorrInv;
		this.ceecflag = ceecflag;
		this.baseAmnt = baseAmnt;
		this.baseAmntSc = baseAmntSc;
		this.baseAmntFc = baseAmntFc;
		this.ctlAccount = ctlAccount;
		this.bplid = bplid;
		this.bplname = bplname;
		this.vatregNum = vatregNum;
		this.txInvRptNo = txInvRptNo;
		this.txInvRptDt = txInvRptDt;
		this.kvvatcode = kvvatcode;
		this.wtdetails = wtdetails;
		this.sumAbsId = sumAbsId;
		this.sumRptDate = sumRptDate;
		this.pindicator = pindicator;
		this.manualNum = manualNum;
		this.useShpdGd = useShpdGd;
		this.baseVtAt = baseVtAt;
		this.baseVtAtSc = baseVtAtSc;
		this.baseVtAtFc = baseVtAtFc;
		this.nnSbVat = nnSbVat;
		this.nnSbVatSc = nnSbVatSc;
		this.nbSbVatFc = nbSbVatFc;
		this.exptVat = exptVat;
		this.exptVatSc = exptVatSc;
		this.exptVatFc = exptVatFc;
		this.lypmtAt = lypmtAt;
		this.lypmtAtSc = lypmtAtSc;
		this.lypmtAtFc = lypmtAtFc;
		this.expAnSum = expAnSum;
		this.expAnSys = expAnSys;
		this.expAnFrgn = expAnFrgn;
		this.docSubType = docSubType;
		this.dpmStatus = dpmStatus;
		this.dpmAmnt = dpmAmnt;
		this.dpmAmntSc = dpmAmntSc;
		this.dpmAmntFc = dpmAmntFc;
		this.dpmDrawn = dpmDrawn;
		this.dpmPrcnt = dpmPrcnt;
		this.paidSum = paidSum;
		this.paidSumFc = paidSumFc;
		this.paidSumSc = paidSumSc;
		this.folioPref = folioPref;
		this.folioNum = folioNum;
		this.dpmAppl = dpmAppl;
		this.dpmApplFc = dpmApplFc;
		this.dpmApplSc = dpmApplSc;
		this.lpgFolioN = lpgFolioN;
		this.header = header;
		this.footer = footer;
		this.posted = posted;
		this.ownerCode = ownerCode;
		this.bpchCode = bpchCode;
		this.bpchCntc = bpchCntc;
		this.payToCode = payToCode;
		this.isPaytoBnk = isPaytoBnk;
		this.bnkCntry = bnkCntry;
		this.bankCode = bankCode;
		this.bnkAccount = bnkAccount;
		this.bnkBranch = bnkBranch;
		this.isIns = isIns;
		this.trackNo = trackNo;
		this.versionNum = versionNum;
		this.langCode = langCode;
		this.bpnameOw = bpnameOw;
		this.billToOw = billToOw;
		this.shipToOw = shipToOw;
		this.retInvoice = retInvoice;
		this.clsDate = clsDate;
		this.minvNum = minvNum;
		this.minvDate = minvDate;
		this.seqCode = seqCode;
		this.serial = serial;
		this.seriesStr = seriesStr;
		this.subStr = subStr;
		this.model = model;
		this.taxOnExp = taxOnExp;
		this.taxOnExpFc = taxOnExpFc;
		this.taxOnExpSc = taxOnExpSc;
		this.taxOnExAp = taxOnExAp;
		this.taxOnExApF = taxOnExApF;
		this.taxOnExApS = taxOnExApS;
		this.lastPmnTyp = lastPmnTyp;
		/*this.lndCstNum = lndCstNum;
		this.useCorrVat = useCorrVat;
		this.blkCredMmo = blkCredMmo;
		this.openForLaC = openForLaC;
		this.excised = excised;
		this.excRefDate = excRefDate;
		this.excRmvTime = excRmvTime;
		this.srvGpPrcnt = srvGpPrcnt;
		this.depositNum = depositNum;
		this.certNum = certNum;
		this.dutyStatus = dutyStatus;
		this.autoCrtFlw = autoCrtFlw;
		this.flwRefDate = flwRefDate;
		this.flwRefNum = flwRefNum;
		this.vatJenum = vatJenum;
		this.dpmVat = dpmVat;
		this.dpmVatFc = dpmVatFc;
		this.dpmVatSc = dpmVatSc;
		this.dpmAppVat = dpmAppVat;
		this.dpmAppVatF = dpmAppVatF;
		this.dpmAppVatS = dpmAppVatS;
		this.insurOp347 = insurOp347;
		this.ignRelDoc = ignRelDoc;
		this.buildDesc = buildDesc;
		this.residenNum = residenNum;
		this.checker = checker;
		this.payee = payee;
		this.copyNumber = copyNumber;
		this.ntsapprov = ntsapprov;
		this.ntswebSite = ntswebSite;
		this.ntseTaxNo = ntseTaxNo;
		this.ntsapprNo = ntsapprNo;
		this.payDuMonth = payDuMonth;
		this.extraMonth = extraMonth;
		this.extraDays = extraDays;
		this.cdcOffset = cdcOffset;
		this.signMsg = signMsg;
		this.signDigest = signDigest;
		this.certifNum = certifNum;
		this.keyVersion = keyVersion;
		this.edocGenTyp = edocGenTyp;
		this.eseries = eseries;
		this.edocNum = edocNum;
		this.edocExpFrm = edocExpFrm;*/
		this.USms = USms;
		this.UYsdxdjlx = UYsdxdjlx;
		this.UDhdate = UDhdate;
		this.UDq = UDq;
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

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getCanceled() {
		return this.canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getHandwrtten() {
		return this.handwrtten;
	}

	public void setHandwrtten(String handwrtten) {
		this.handwrtten = handwrtten;
	}

	public String getPrinted() {
		return this.printed;
	}

	public void setPrinted(String printed) {
		this.printed = printed;
	}

	public String getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public String getInvntSttus() {
		return this.invntSttus;
	}

	public void setInvntSttus(String invntSttus) {
		this.invntSttus = invntSttus;
	}

	public String getTransfered() {
		return this.transfered;
	}

	public void setTransfered(String transfered) {
		this.transfered = transfered;
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

	public Timestamp getDocDueDate() {
		return this.docDueDate;
	}

	public void setDocDueDate(Timestamp docDueDate) {
		this.docDueDate = docDueDate;
	}

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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumAtCard() {
		return this.numAtCard;
	}

	public void setNumAtCard(String numAtCard) {
		this.numAtCard = numAtCard;
	}

	public Double getVatPercent() {
		return this.vatPercent;
	}

	public void setVatPercent(Double vatPercent) {
		this.vatPercent = vatPercent;
	}

	public Double getVatSum() {
		return this.vatSum;
	}

	public void setVatSum(Double vatSum) {
		this.vatSum = vatSum;
	}

	public Double getVatSumFc() {
		return this.vatSumFc;
	}

	public void setVatSumFc(Double vatSumFc) {
		this.vatSumFc = vatSumFc;
	}

	public Double getDiscPrcnt() {
		return this.discPrcnt;
	}

	public void setDiscPrcnt(Double discPrcnt) {
		this.discPrcnt = discPrcnt;
	}

	public Double getDiscSum() {
		return this.discSum;
	}

	public void setDiscSum(Double discSum) {
		this.discSum = discSum;
	}

	public Double getDiscSumFc() {
		return this.discSumFc;
	}

	public void setDiscSumFc(Double discSumFc) {
		this.discSumFc = discSumFc;
	}

	public String getDocCur() {
		return this.docCur;
	}

	public void setDocCur(String docCur) {
		this.docCur = docCur;
	}

	public Double getDocRate() {
		return this.docRate;
	}

	public void setDocRate(Double docRate) {
		this.docRate = docRate;
	}

	public Double getDocTotal() {
		return this.docTotal;
	}

	public void setDocTotal(Double docTotal) {
		this.docTotal = docTotal;
	}

	public Double getDocTotalFc() {
		return this.docTotalFc;
	}

	public void setDocTotalFc(Double docTotalFc) {
		this.docTotalFc = docTotalFc;
	}

	public Double getPaidToDate() {
		return this.paidToDate;
	}

	public void setPaidToDate(Double paidToDate) {
		this.paidToDate = paidToDate;
	}

	public Double getPaidFc() {
		return this.paidFc;
	}

	public void setPaidFc(Double paidFc) {
		this.paidFc = paidFc;
	}

	public Double getGrosProfit() {
		return this.grosProfit;
	}

	public void setGrosProfit(Double grosProfit) {
		this.grosProfit = grosProfit;
	}

	public Double getGrosProfFc() {
		return this.grosProfFc;
	}

	public void setGrosProfFc(Double grosProfFc) {
		this.grosProfFc = grosProfFc;
	}

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getJrnlMemo() {
		return this.jrnlMemo;
	}

	public void setJrnlMemo(String jrnlMemo) {
		this.jrnlMemo = jrnlMemo;
	}

	public Integer getTransId() {
		return this.transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	public Integer getReceiptNum() {
		return this.receiptNum;
	}

	public void setReceiptNum(Integer receiptNum) {
		this.receiptNum = receiptNum;
	}

	public Short getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Short groupNum) {
		this.groupNum = groupNum;
	}

	public Short getDocTime() {
		return this.docTime;
	}

	public void setDocTime(Short docTime) {
		this.docTime = docTime;
	}

	public Short getSlpCode() {
		return this.slpCode;
	}

	public void setSlpCode(Short slpCode) {
		this.slpCode = slpCode;
	}

	public Short getTrnspCode() {
		return this.trnspCode;
	}

	public void setTrnspCode(Short trnspCode) {
		this.trnspCode = trnspCode;
	}

	public String getPartSupply() {
		return this.partSupply;
	}

	public void setPartSupply(String partSupply) {
		this.partSupply = partSupply;
	}

	public String getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public Short getGrossBase() {
		return this.grossBase;
	}

	public void setGrossBase(Short grossBase) {
		this.grossBase = grossBase;
	}

	public Integer getImportEnt() {
		return this.importEnt;
	}

	public void setImportEnt(Integer importEnt) {
		this.importEnt = importEnt;
	}

	public String getCreateTran() {
		return this.createTran;
	}

	public void setCreateTran(String createTran) {
		this.createTran = createTran;
	}

	public String getSummryType() {
		return this.summryType;
	}

	public void setSummryType(String summryType) {
		this.summryType = summryType;
	}

	public String getUpdInvnt() {
		return this.updInvnt;
	}

	public void setUpdInvnt(String updInvnt) {
		this.updInvnt = updInvnt;
	}

	public String getUpdCardBal() {
		return this.updCardBal;
	}

	public void setUpdCardBal(String updCardBal) {
		this.updCardBal = updCardBal;
	}

	public Short getInstance() {
		return this.instance;
	}

	public void setInstance(Short instance) {
		this.instance = instance;
	}

	public Integer getFlags() {
		return this.flags;
	}

	public void setFlags(Integer flags) {
		this.flags = flags;
	}

	public String getInvntDirec() {
		return this.invntDirec;
	}

	public void setInvntDirec(String invntDirec) {
		this.invntDirec = invntDirec;
	}

	public Integer getCntctCode() {
		return this.cntctCode;
	}

	public void setCntctCode(Integer cntctCode) {
		this.cntctCode = cntctCode;
	}

	public String getShowScn() {
		return this.showScn;
	}

	public void setShowScn(String showScn) {
		this.showScn = showScn;
	}

	public String getFatherCard() {
		return this.fatherCard;
	}

	public void setFatherCard(String fatherCard) {
		this.fatherCard = fatherCard;
	}

	public Double getSysRate() {
		return this.sysRate;
	}

	public void setSysRate(Double sysRate) {
		this.sysRate = sysRate;
	}

	public String getCurSource() {
		return this.curSource;
	}

	public void setCurSource(String curSource) {
		this.curSource = curSource;
	}

	public Double getVatSumSy() {
		return this.vatSumSy;
	}

	public void setVatSumSy(Double vatSumSy) {
		this.vatSumSy = vatSumSy;
	}

	public Double getDiscSumSy() {
		return this.discSumSy;
	}

	public void setDiscSumSy(Double discSumSy) {
		this.discSumSy = discSumSy;
	}

	public Double getDocTotalSy() {
		return this.docTotalSy;
	}

	public void setDocTotalSy(Double docTotalSy) {
		this.docTotalSy = docTotalSy;
	}

	public Double getPaidSys() {
		return this.paidSys;
	}

	public void setPaidSys(Double paidSys) {
		this.paidSys = paidSys;
	}

	public String getFatherType() {
		return this.fatherType;
	}

	public void setFatherType(String fatherType) {
		this.fatherType = fatherType;
	}

	public Double getGrosProfSy() {
		return this.grosProfSy;
	}

	public void setGrosProfSy(Double grosProfSy) {
		this.grosProfSy = grosProfSy;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getIsIct() {
		return this.isIct;
	}

	public void setIsIct(String isIct) {
		this.isIct = isIct;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Short getWeightUnit() {
		return this.weightUnit;
	}

	public void setWeightUnit(Short weightUnit) {
		this.weightUnit = weightUnit;
	}

	public Short getSeries() {
		return this.series;
	}

	public void setSeries(Short series) {
		this.series = series;
	}

	public Timestamp getTaxDate() {
		return this.taxDate;
	}

	public void setTaxDate(Timestamp taxDate) {
		this.taxDate = taxDate;
	}

	public String getFiller() {
		return this.filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getStampNum() {
		return this.stampNum;
	}

	public void setStampNum(String stampNum) {
		this.stampNum = stampNum;
	}

	public String getIsCrin() {
		return this.isCrin;
	}

	public void setIsCrin(String isCrin) {
		this.isCrin = isCrin;
	}

	public Integer getFinncPriod() {
		return this.finncPriod;
	}

	public void setFinncPriod(Integer finncPriod) {
		this.finncPriod = finncPriod;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

	public String getSelfInv() {
		return this.selfInv;
	}

	public void setSelfInv(String selfInv) {
		this.selfInv = selfInv;
	}

	public Double getVatPaid() {
		return this.vatPaid;
	}

	public void setVatPaid(Double vatPaid) {
		this.vatPaid = vatPaid;
	}

	public Double getVatPaidFc() {
		return this.vatPaidFc;
	}

	public void setVatPaidFc(Double vatPaidFc) {
		this.vatPaidFc = vatPaidFc;
	}

	public Double getVatPaidSys() {
		return this.vatPaidSys;
	}

	public void setVatPaidSys(Double vatPaidSys) {
		this.vatPaidSys = vatPaidSys;
	}

	public Short getUserSign2() {
		return this.userSign2;
	}

	public void setUserSign2(Short userSign2) {
		this.userSign2 = userSign2;
	}

	public String getWddStatus() {
		return this.wddStatus;
	}

	public void setWddStatus(String wddStatus) {
		this.wddStatus = wddStatus;
	}

	public Integer getDraftKey() {
		return this.draftKey;
	}

	public void setDraftKey(Integer draftKey) {
		this.draftKey = draftKey;
	}

	public Double getTotalExpns() {
		return this.totalExpns;
	}

	public void setTotalExpns(Double totalExpns) {
		this.totalExpns = totalExpns;
	}

	public Double getTotalExpFc() {
		return this.totalExpFc;
	}

	public void setTotalExpFc(Double totalExpFc) {
		this.totalExpFc = totalExpFc;
	}

	public Double getTotalExpSc() {
		return this.totalExpSc;
	}

	public void setTotalExpSc(Double totalExpSc) {
		this.totalExpSc = totalExpSc;
	}

	public Integer getDunnLevel() {
		return this.dunnLevel;
	}

	public void setDunnLevel(Integer dunnLevel) {
		this.dunnLevel = dunnLevel;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Short getLogInstanc() {
		return this.logInstanc;
	}

	public void setLogInstanc(Short logInstanc) {
		this.logInstanc = logInstanc;
	}

	public String getExported() {
		return this.exported;
	}

	public void setExported(String exported) {
		this.exported = exported;
	}

	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getIndicator() {
		return this.indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getNetProc() {
		return this.netProc;
	}

	public void setNetProc(String netProc) {
		this.netProc = netProc;
	}

	public Double getAqcsTax() {
		return this.aqcsTax;
	}

	public void setAqcsTax(Double aqcsTax) {
		this.aqcsTax = aqcsTax;
	}

	public Double getAqcsTaxFc() {
		return this.aqcsTaxFc;
	}

	public void setAqcsTaxFc(Double aqcsTaxFc) {
		this.aqcsTaxFc = aqcsTaxFc;
	}

	public Double getAqcsTaxSc() {
		return this.aqcsTaxSc;
	}

	public void setAqcsTaxSc(Double aqcsTaxSc) {
		this.aqcsTaxSc = aqcsTaxSc;
	}

	public Double getCashDiscPr() {
		return this.cashDiscPr;
	}

	public void setCashDiscPr(Double cashDiscPr) {
		this.cashDiscPr = cashDiscPr;
	}

	public Double getCashDiscnt() {
		return this.cashDiscnt;
	}

	public void setCashDiscnt(Double cashDiscnt) {
		this.cashDiscnt = cashDiscnt;
	}

	public Double getCashDiscFc() {
		return this.cashDiscFc;
	}

	public void setCashDiscFc(Double cashDiscFc) {
		this.cashDiscFc = cashDiscFc;
	}

	public Double getCashDiscSc() {
		return this.cashDiscSc;
	}

	public void setCashDiscSc(Double cashDiscSc) {
		this.cashDiscSc = cashDiscSc;
	}

	public String getShipToCode() {
		return this.shipToCode;
	}

	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}

	public String getLicTradNum() {
		return this.licTradNum;
	}

	public void setLicTradNum(String licTradNum) {
		this.licTradNum = licTradNum;
	}

	public String getPaymentRef() {
		return this.paymentRef;
	}

	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}

	public Double getWtsum() {
		return this.wtsum;
	}

	public void setWtsum(Double wtsum) {
		this.wtsum = wtsum;
	}

	public Double getWtsumFc() {
		return this.wtsumFc;
	}

	public void setWtsumFc(Double wtsumFc) {
		this.wtsumFc = wtsumFc;
	}

	public Double getWtsumSc() {
		return this.wtsumSc;
	}

	public void setWtsumSc(Double wtsumSc) {
		this.wtsumSc = wtsumSc;
	}

	public Double getRoundDif() {
		return this.roundDif;
	}

	public void setRoundDif(Double roundDif) {
		this.roundDif = roundDif;
	}

	public Double getRoundDifFc() {
		return this.roundDifFc;
	}

	public void setRoundDifFc(Double roundDifFc) {
		this.roundDifFc = roundDifFc;
	}

	public Double getRoundDifSy() {
		return this.roundDifSy;
	}

	public void setRoundDifSy(Double roundDifSy) {
		this.roundDifSy = roundDifSy;
	}

	public String getCheckDigit() {
		return this.checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	public Integer getForm1099() {
		return this.form1099;
	}

	public void setForm1099(Integer form1099) {
		this.form1099 = form1099;
	}

	public String getBox1099() {
		return this.box1099;
	}

	public void setBox1099(String box1099) {
		this.box1099 = box1099;
	}

	public String getSubmitted() {
		return this.submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getPoPrss() {
		return this.poPrss;
	}

	public void setPoPrss(String poPrss) {
		this.poPrss = poPrss;
	}

	public String getRounding() {
		return this.rounding;
	}

	public void setRounding(String rounding) {
		this.rounding = rounding;
	}

	public String getRevisionPo() {
		return this.revisionPo;
	}

	public void setRevisionPo(String revisionPo) {
		this.revisionPo = revisionPo;
	}

	public Short getSegment() {
		return this.segment;
	}

	public void setSegment(Short segment) {
		this.segment = segment;
	}

	public Timestamp getReqDate() {
		return this.reqDate;
	}

	public void setReqDate(Timestamp reqDate) {
		this.reqDate = reqDate;
	}

	public Timestamp getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getPickStatus() {
		return this.pickStatus;
	}

	public void setPickStatus(String pickStatus) {
		this.pickStatus = pickStatus;
	}

	public String getPick() {
		return this.pick;
	}

	public void setPick(String pick) {
		this.pick = pick;
	}

	public String getBlockDunn() {
		return this.blockDunn;
	}

	public void setBlockDunn(String blockDunn) {
		this.blockDunn = blockDunn;
	}

	public String getPeyMethod() {
		return this.peyMethod;
	}

	public void setPeyMethod(String peyMethod) {
		this.peyMethod = peyMethod;
	}

	public String getPayBlock() {
		return this.payBlock;
	}

	public void setPayBlock(String payBlock) {
		this.payBlock = payBlock;
	}

	public Integer getPayBlckRef() {
		return this.payBlckRef;
	}

	public void setPayBlckRef(Integer payBlckRef) {
		this.payBlckRef = payBlckRef;
	}

	public String getMaxDscn() {
		return this.maxDscn;
	}

	public void setMaxDscn(String maxDscn) {
		this.maxDscn = maxDscn;
	}

	public String getReserve() {
		return this.reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public Double getMax1099() {
		return this.max1099;
	}

	public void setMax1099(Double max1099) {
		this.max1099 = max1099;
	}

	public String getCntrlBnk() {
		return this.cntrlBnk;
	}

	public void setCntrlBnk(String cntrlBnk) {
		this.cntrlBnk = cntrlBnk;
	}

	public String getPickRmrk() {
		return this.pickRmrk;
	}

	public void setPickRmrk(String pickRmrk) {
		this.pickRmrk = pickRmrk;
	}

	public String getIsrcodLine() {
		return this.isrcodLine;
	}

	public void setIsrcodLine(String isrcodLine) {
		this.isrcodLine = isrcodLine;
	}

	public Double getExpAppl() {
		return this.expAppl;
	}

	public void setExpAppl(Double expAppl) {
		this.expAppl = expAppl;
	}

	public Double getExpApplFc() {
		return this.expApplFc;
	}

	public void setExpApplFc(Double expApplFc) {
		this.expApplFc = expApplFc;
	}

	public Double getExpApplSc() {
		return this.expApplSc;
	}

	public void setExpApplSc(Double expApplSc) {
		this.expApplSc = expApplSc;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
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

	public Double getWtapplied() {
		return this.wtapplied;
	}

	public void setWtapplied(Double wtapplied) {
		this.wtapplied = wtapplied;
	}

	public Double getWtappliedF() {
		return this.wtappliedF;
	}

	public void setWtappliedF(Double wtappliedF) {
		this.wtappliedF = wtappliedF;
	}

	public String getBoeReserev() {
		return this.boeReserev;
	}

	public void setBoeReserev(String boeReserev) {
		this.boeReserev = boeReserev;
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public Double getWtappliedS() {
		return this.wtappliedS;
	}

	public void setWtappliedS(Double wtappliedS) {
		this.wtappliedS = wtappliedS;
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

	public Short getInstallmnt() {
		return this.installmnt;
	}

	public void setInstallmnt(Short installmnt) {
		this.installmnt = installmnt;
	}

	public String getVatfirst() {
		return this.vatfirst;
	}

	public void setVatfirst(String vatfirst) {
		this.vatfirst = vatfirst;
	}

	public Double getNnSbAmnt() {
		return this.nnSbAmnt;
	}

	public void setNnSbAmnt(Double nnSbAmnt) {
		this.nnSbAmnt = nnSbAmnt;
	}

	public Double getNnSbAmntSc() {
		return this.nnSbAmntSc;
	}

	public void setNnSbAmntSc(Double nnSbAmntSc) {
		this.nnSbAmntSc = nnSbAmntSc;
	}

	public Double getNbSbAmntFc() {
		return this.nbSbAmntFc;
	}

	public void setNbSbAmntFc(Double nbSbAmntFc) {
		this.nbSbAmntFc = nbSbAmntFc;
	}

	public Double getExepAmnt() {
		return this.exepAmnt;
	}

	public void setExepAmnt(Double exepAmnt) {
		this.exepAmnt = exepAmnt;
	}

	public Double getExepAmntSc() {
		return this.exepAmntSc;
	}

	public void setExepAmntSc(Double exepAmntSc) {
		this.exepAmntSc = exepAmntSc;
	}

	public Double getExepAmntFc() {
		return this.exepAmntFc;
	}

	public void setExepAmntFc(Double exepAmntFc) {
		this.exepAmntFc = exepAmntFc;
	}

	public Timestamp getVatDate() {
		return this.vatDate;
	}

	public void setVatDate(Timestamp vatDate) {
		this.vatDate = vatDate;
	}

	public String getCorrExt() {
		return this.corrExt;
	}

	public void setCorrExt(String corrExt) {
		this.corrExt = corrExt;
	}

	public Integer getCorrInv() {
		return this.corrInv;
	}

	public void setCorrInv(Integer corrInv) {
		this.corrInv = corrInv;
	}

	public Integer getNcorrInv() {
		return this.ncorrInv;
	}

	public void setNcorrInv(Integer ncorrInv) {
		this.ncorrInv = ncorrInv;
	}

	public String getCeecflag() {
		return this.ceecflag;
	}

	public void setCeecflag(String ceecflag) {
		this.ceecflag = ceecflag;
	}

	public Double getBaseAmnt() {
		return this.baseAmnt;
	}

	public void setBaseAmnt(Double baseAmnt) {
		this.baseAmnt = baseAmnt;
	}

	public Double getBaseAmntSc() {
		return this.baseAmntSc;
	}

	public void setBaseAmntSc(Double baseAmntSc) {
		this.baseAmntSc = baseAmntSc;
	}

	public Double getBaseAmntFc() {
		return this.baseAmntFc;
	}

	public void setBaseAmntFc(Double baseAmntFc) {
		this.baseAmntFc = baseAmntFc;
	}

	public String getCtlAccount() {
		return this.ctlAccount;
	}

	public void setCtlAccount(String ctlAccount) {
		this.ctlAccount = ctlAccount;
	}

	public Integer getBplid() {
		return this.bplid;
	}

	public void setBplid(Integer bplid) {
		this.bplid = bplid;
	}

	public String getBplname() {
		return this.bplname;
	}

	public void setBplname(String bplname) {
		this.bplname = bplname;
	}

	public String getVatregNum() {
		return this.vatregNum;
	}

	public void setVatregNum(String vatregNum) {
		this.vatregNum = vatregNum;
	}

	public String getTxInvRptNo() {
		return this.txInvRptNo;
	}

	public void setTxInvRptNo(String txInvRptNo) {
		this.txInvRptNo = txInvRptNo;
	}

	public Timestamp getTxInvRptDt() {
		return this.txInvRptDt;
	}

	public void setTxInvRptDt(Timestamp txInvRptDt) {
		this.txInvRptDt = txInvRptDt;
	}

	public String getKvvatcode() {
		return this.kvvatcode;
	}

	public void setKvvatcode(String kvvatcode) {
		this.kvvatcode = kvvatcode;
	}

	public String getWtdetails() {
		return this.wtdetails;
	}

	public void setWtdetails(String wtdetails) {
		this.wtdetails = wtdetails;
	}

	public Integer getSumAbsId() {
		return this.sumAbsId;
	}

	public void setSumAbsId(Integer sumAbsId) {
		this.sumAbsId = sumAbsId;
	}

	public Timestamp getSumRptDate() {
		return this.sumRptDate;
	}

	public void setSumRptDate(Timestamp sumRptDate) {
		this.sumRptDate = sumRptDate;
	}

	public String getPindicator() {
		return this.pindicator;
	}

	public void setPindicator(String pindicator) {
		this.pindicator = pindicator;
	}

	public String getManualNum() {
		return this.manualNum;
	}

	public void setManualNum(String manualNum) {
		this.manualNum = manualNum;
	}

	public String getUseShpdGd() {
		return this.useShpdGd;
	}

	public void setUseShpdGd(String useShpdGd) {
		this.useShpdGd = useShpdGd;
	}

	public Double getBaseVtAt() {
		return this.baseVtAt;
	}

	public void setBaseVtAt(Double baseVtAt) {
		this.baseVtAt = baseVtAt;
	}

	public Double getBaseVtAtSc() {
		return this.baseVtAtSc;
	}

	public void setBaseVtAtSc(Double baseVtAtSc) {
		this.baseVtAtSc = baseVtAtSc;
	}

	public Double getBaseVtAtFc() {
		return this.baseVtAtFc;
	}

	public void setBaseVtAtFc(Double baseVtAtFc) {
		this.baseVtAtFc = baseVtAtFc;
	}

	public Double getNnSbVat() {
		return this.nnSbVat;
	}

	public void setNnSbVat(Double nnSbVat) {
		this.nnSbVat = nnSbVat;
	}

	public Double getNnSbVatSc() {
		return this.nnSbVatSc;
	}

	public void setNnSbVatSc(Double nnSbVatSc) {
		this.nnSbVatSc = nnSbVatSc;
	}

	public Double getNbSbVatFc() {
		return this.nbSbVatFc;
	}

	public void setNbSbVatFc(Double nbSbVatFc) {
		this.nbSbVatFc = nbSbVatFc;
	}

	public Double getExptVat() {
		return this.exptVat;
	}

	public void setExptVat(Double exptVat) {
		this.exptVat = exptVat;
	}

	public Double getExptVatSc() {
		return this.exptVatSc;
	}

	public void setExptVatSc(Double exptVatSc) {
		this.exptVatSc = exptVatSc;
	}

	public Double getExptVatFc() {
		return this.exptVatFc;
	}

	public void setExptVatFc(Double exptVatFc) {
		this.exptVatFc = exptVatFc;
	}

	public Double getLypmtAt() {
		return this.lypmtAt;
	}

	public void setLypmtAt(Double lypmtAt) {
		this.lypmtAt = lypmtAt;
	}

	public Double getLypmtAtSc() {
		return this.lypmtAtSc;
	}

	public void setLypmtAtSc(Double lypmtAtSc) {
		this.lypmtAtSc = lypmtAtSc;
	}

	public Double getLypmtAtFc() {
		return this.lypmtAtFc;
	}

	public void setLypmtAtFc(Double lypmtAtFc) {
		this.lypmtAtFc = lypmtAtFc;
	}

	public Double getExpAnSum() {
		return this.expAnSum;
	}

	public void setExpAnSum(Double expAnSum) {
		this.expAnSum = expAnSum;
	}

	public Double getExpAnSys() {
		return this.expAnSys;
	}

	public void setExpAnSys(Double expAnSys) {
		this.expAnSys = expAnSys;
	}

	public Double getExpAnFrgn() {
		return this.expAnFrgn;
	}

	public void setExpAnFrgn(Double expAnFrgn) {
		this.expAnFrgn = expAnFrgn;
	}

	public String getDocSubType() {
		return this.docSubType;
	}

	public void setDocSubType(String docSubType) {
		this.docSubType = docSubType;
	}

	public String getDpmStatus() {
		return this.dpmStatus;
	}

	public void setDpmStatus(String dpmStatus) {
		this.dpmStatus = dpmStatus;
	}

	public Double getDpmAmnt() {
		return this.dpmAmnt;
	}

	public void setDpmAmnt(Double dpmAmnt) {
		this.dpmAmnt = dpmAmnt;
	}

	public Double getDpmAmntSc() {
		return this.dpmAmntSc;
	}

	public void setDpmAmntSc(Double dpmAmntSc) {
		this.dpmAmntSc = dpmAmntSc;
	}

	public Double getDpmAmntFc() {
		return this.dpmAmntFc;
	}

	public void setDpmAmntFc(Double dpmAmntFc) {
		this.dpmAmntFc = dpmAmntFc;
	}

	public String getDpmDrawn() {
		return this.dpmDrawn;
	}

	public void setDpmDrawn(String dpmDrawn) {
		this.dpmDrawn = dpmDrawn;
	}

	public Double getDpmPrcnt() {
		return this.dpmPrcnt;
	}

	public void setDpmPrcnt(Double dpmPrcnt) {
		this.dpmPrcnt = dpmPrcnt;
	}

	public Double getPaidSum() {
		return this.paidSum;
	}

	public void setPaidSum(Double paidSum) {
		this.paidSum = paidSum;
	}

	public Double getPaidSumFc() {
		return this.paidSumFc;
	}

	public void setPaidSumFc(Double paidSumFc) {
		this.paidSumFc = paidSumFc;
	}

	public Double getPaidSumSc() {
		return this.paidSumSc;
	}

	public void setPaidSumSc(Double paidSumSc) {
		this.paidSumSc = paidSumSc;
	}

	public String getFolioPref() {
		return this.folioPref;
	}

	public void setFolioPref(String folioPref) {
		this.folioPref = folioPref;
	}

	public Integer getFolioNum() {
		return this.folioNum;
	}

	public void setFolioNum(Integer folioNum) {
		this.folioNum = folioNum;
	}

	public Double getDpmAppl() {
		return this.dpmAppl;
	}

	public void setDpmAppl(Double dpmAppl) {
		this.dpmAppl = dpmAppl;
	}

	public Double getDpmApplFc() {
		return this.dpmApplFc;
	}

	public void setDpmApplFc(Double dpmApplFc) {
		this.dpmApplFc = dpmApplFc;
	}

	public Double getDpmApplSc() {
		return this.dpmApplSc;
	}

	public void setDpmApplSc(Double dpmApplSc) {
		this.dpmApplSc = dpmApplSc;
	}

	public Integer getLpgFolioN() {
		return this.lpgFolioN;
	}

	public void setLpgFolioN(Integer lpgFolioN) {
		this.lpgFolioN = lpgFolioN;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return this.footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getPosted() {
		return this.posted;
	}

	public void setPosted(String posted) {
		this.posted = posted;
	}

	public Integer getOwnerCode() {
		return this.ownerCode;
	}

	public void setOwnerCode(Integer ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getBpchCode() {
		return this.bpchCode;
	}

	public void setBpchCode(String bpchCode) {
		this.bpchCode = bpchCode;
	}

	public Integer getBpchCntc() {
		return this.bpchCntc;
	}

	public void setBpchCntc(Integer bpchCntc) {
		this.bpchCntc = bpchCntc;
	}

	public String getPayToCode() {
		return this.payToCode;
	}

	public void setPayToCode(String payToCode) {
		this.payToCode = payToCode;
	}

	public String getIsPaytoBnk() {
		return this.isPaytoBnk;
	}

	public void setIsPaytoBnk(String isPaytoBnk) {
		this.isPaytoBnk = isPaytoBnk;
	}

	public String getBnkCntry() {
		return this.bnkCntry;
	}

	public void setBnkCntry(String bnkCntry) {
		this.bnkCntry = bnkCntry;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBnkAccount() {
		return this.bnkAccount;
	}

	public void setBnkAccount(String bnkAccount) {
		this.bnkAccount = bnkAccount;
	}

	public String getBnkBranch() {
		return this.bnkBranch;
	}

	public void setBnkBranch(String bnkBranch) {
		this.bnkBranch = bnkBranch;
	}

	public String getIsIns() {
		return this.isIns;
	}

	public void setIsIns(String isIns) {
		this.isIns = isIns;
	}

	public String getTrackNo() {
		return this.trackNo;
	}

	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}

	public String getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public Integer getLangCode() {
		return this.langCode;
	}

	public void setLangCode(Integer langCode) {
		this.langCode = langCode;
	}

	public String getBpnameOw() {
		return this.bpnameOw;
	}

	public void setBpnameOw(String bpnameOw) {
		this.bpnameOw = bpnameOw;
	}

	public String getBillToOw() {
		return this.billToOw;
	}

	public void setBillToOw(String billToOw) {
		this.billToOw = billToOw;
	}

	public String getShipToOw() {
		return this.shipToOw;
	}

	public void setShipToOw(String shipToOw) {
		this.shipToOw = shipToOw;
	}

	public String getRetInvoice() {
		return this.retInvoice;
	}

	public void setRetInvoice(String retInvoice) {
		this.retInvoice = retInvoice;
	}

	public Timestamp getClsDate() {
		return this.clsDate;
	}

	public void setClsDate(Timestamp clsDate) {
		this.clsDate = clsDate;
	}

	public Integer getMinvNum() {
		return this.minvNum;
	}

	public void setMinvNum(Integer minvNum) {
		this.minvNum = minvNum;
	}

	public Timestamp getMinvDate() {
		return this.minvDate;
	}

	public void setMinvDate(Timestamp minvDate) {
		this.minvDate = minvDate;
	}

	public Short getSeqCode() {
		return this.seqCode;
	}

	public void setSeqCode(Short seqCode) {
		this.seqCode = seqCode;
	}

	public Integer getSerial() {
		return this.serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getSeriesStr() {
		return this.seriesStr;
	}

	public void setSeriesStr(String seriesStr) {
		this.seriesStr = seriesStr;
	}

	public String getSubStr() {
		return this.subStr;
	}

	public void setSubStr(String subStr) {
		this.subStr = subStr;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getTaxOnExp() {
		return this.taxOnExp;
	}

	public void setTaxOnExp(Double taxOnExp) {
		this.taxOnExp = taxOnExp;
	}

	public Double getTaxOnExpFc() {
		return this.taxOnExpFc;
	}

	public void setTaxOnExpFc(Double taxOnExpFc) {
		this.taxOnExpFc = taxOnExpFc;
	}

	public Double getTaxOnExpSc() {
		return this.taxOnExpSc;
	}

	public void setTaxOnExpSc(Double taxOnExpSc) {
		this.taxOnExpSc = taxOnExpSc;
	}

	public Double getTaxOnExAp() {
		return this.taxOnExAp;
	}

	public void setTaxOnExAp(Double taxOnExAp) {
		this.taxOnExAp = taxOnExAp;
	}

	public Double getTaxOnExApF() {
		return this.taxOnExApF;
	}

	public void setTaxOnExApF(Double taxOnExApF) {
		this.taxOnExApF = taxOnExApF;
	}

	public Double getTaxOnExApS() {
		return this.taxOnExApS;
	}

	public void setTaxOnExApS(Double taxOnExApS) {
		this.taxOnExApS = taxOnExApS;
	}

	public String getLastPmnTyp() {
		return this.lastPmnTyp;
	}

	public void setLastPmnTyp(String lastPmnTyp) {
		this.lastPmnTyp = lastPmnTyp;
	}

	public Integer getLndCstNum() {
		return this.lndCstNum;
	}

	public void setLndCstNum(Integer lndCstNum) {
		this.lndCstNum = lndCstNum;
	}

	public String getUseCorrVat() {
		return this.useCorrVat;
	}

	public void setUseCorrVat(String useCorrVat) {
		this.useCorrVat = useCorrVat;
	}

	public String getBlkCredMmo() {
		return this.blkCredMmo;
	}

	public void setBlkCredMmo(String blkCredMmo) {
		this.blkCredMmo = blkCredMmo;
	}

	public String getOpenForLaC() {
		return this.openForLaC;
	}

	public void setOpenForLaC(String openForLaC) {
		this.openForLaC = openForLaC;
	}

	public String getExcised() {
		return this.excised;
	}

	public void setExcised(String excised) {
		this.excised = excised;
	}

	public Timestamp getExcRefDate() {
		return this.excRefDate;
	}

	public void setExcRefDate(Timestamp excRefDate) {
		this.excRefDate = excRefDate;
	}

	public String getExcRmvTime() {
		return this.excRmvTime;
	}

	public void setExcRmvTime(String excRmvTime) {
		this.excRmvTime = excRmvTime;
	}

	public Double getSrvGpPrcnt() {
		return this.srvGpPrcnt;
	}

	public void setSrvGpPrcnt(Double srvGpPrcnt) {
		this.srvGpPrcnt = srvGpPrcnt;
	}

	public Integer getDepositNum() {
		return this.depositNum;
	}

	public void setDepositNum(Integer depositNum) {
		this.depositNum = depositNum;
	}

	public String getCertNum() {
		return this.certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getDutyStatus() {
		return this.dutyStatus;
	}

	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}

	public String getAutoCrtFlw() {
		return this.autoCrtFlw;
	}

	public void setAutoCrtFlw(String autoCrtFlw) {
		this.autoCrtFlw = autoCrtFlw;
	}

	public Timestamp getFlwRefDate() {
		return this.flwRefDate;
	}

	public void setFlwRefDate(Timestamp flwRefDate) {
		this.flwRefDate = flwRefDate;
	}

	public String getFlwRefNum() {
		return this.flwRefNum;
	}

	public void setFlwRefNum(String flwRefNum) {
		this.flwRefNum = flwRefNum;
	}

	public Integer getVatJenum() {
		return this.vatJenum;
	}

	public void setVatJenum(Integer vatJenum) {
		this.vatJenum = vatJenum;
	}

	public Double getDpmVat() {
		return this.dpmVat;
	}

	public void setDpmVat(Double dpmVat) {
		this.dpmVat = dpmVat;
	}

	public Double getDpmVatFc() {
		return this.dpmVatFc;
	}

	public void setDpmVatFc(Double dpmVatFc) {
		this.dpmVatFc = dpmVatFc;
	}

	public Double getDpmVatSc() {
		return this.dpmVatSc;
	}

	public void setDpmVatSc(Double dpmVatSc) {
		this.dpmVatSc = dpmVatSc;
	}

	public Double getDpmAppVat() {
		return this.dpmAppVat;
	}

	public void setDpmAppVat(Double dpmAppVat) {
		this.dpmAppVat = dpmAppVat;
	}

	public Double getDpmAppVatF() {
		return this.dpmAppVatF;
	}

	public void setDpmAppVatF(Double dpmAppVatF) {
		this.dpmAppVatF = dpmAppVatF;
	}

	public Double getDpmAppVatS() {
		return this.dpmAppVatS;
	}

	public void setDpmAppVatS(Double dpmAppVatS) {
		this.dpmAppVatS = dpmAppVatS;
	}

	public String getInsurOp347() {
		return this.insurOp347;
	}

	public void setInsurOp347(String insurOp347) {
		this.insurOp347 = insurOp347;
	}

	public String getIgnRelDoc() {
		return this.ignRelDoc;
	}

	public void setIgnRelDoc(String ignRelDoc) {
		this.ignRelDoc = ignRelDoc;
	}

	public String getBuildDesc() {
		return this.buildDesc;
	}

	public void setBuildDesc(String buildDesc) {
		this.buildDesc = buildDesc;
	}

	public String getResidenNum() {
		return this.residenNum;
	}

	public void setResidenNum(String residenNum) {
		this.residenNum = residenNum;
	}

	public Integer getChecker() {
		return this.checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public Integer getPayee() {
		return this.payee;
	}

	public void setPayee(Integer payee) {
		this.payee = payee;
	}

	public Integer getCopyNumber() {
		return this.copyNumber;
	}

	public void setCopyNumber(Integer copyNumber) {
		this.copyNumber = copyNumber;
	}

	public String getNtsapprov() {
		return this.ntsapprov;
	}

	public void setNtsapprov(String ntsapprov) {
		this.ntsapprov = ntsapprov;
	}

	public Short getNtswebSite() {
		return this.ntswebSite;
	}

	public void setNtswebSite(Short ntswebSite) {
		this.ntswebSite = ntswebSite;
	}

	public String getNtseTaxNo() {
		return this.ntseTaxNo;
	}

	public void setNtseTaxNo(String ntseTaxNo) {
		this.ntseTaxNo = ntseTaxNo;
	}

	public String getNtsapprNo() {
		return this.ntsapprNo;
	}

	public void setNtsapprNo(String ntsapprNo) {
		this.ntsapprNo = ntsapprNo;
	}

	public String getPayDuMonth() {
		return this.payDuMonth;
	}

	public void setPayDuMonth(String payDuMonth) {
		this.payDuMonth = payDuMonth;
	}

	public Short getExtraMonth() {
		return this.extraMonth;
	}

	public void setExtraMonth(Short extraMonth) {
		this.extraMonth = extraMonth;
	}

	public Short getExtraDays() {
		return this.extraDays;
	}

	public void setExtraDays(Short extraDays) {
		this.extraDays = extraDays;
	}

	public Short getCdcOffset() {
		return this.cdcOffset;
	}

	public void setCdcOffset(Short cdcOffset) {
		this.cdcOffset = cdcOffset;
	}

	public String getSignMsg() {
		return this.signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getSignDigest() {
		return this.signDigest;
	}

	public void setSignDigest(String signDigest) {
		this.signDigest = signDigest;
	}

	public String getCertifNum() {
		return this.certifNum;
	}

	public void setCertifNum(String certifNum) {
		this.certifNum = certifNum;
	}

	public Integer getKeyVersion() {
		return this.keyVersion;
	}

	public void setKeyVersion(Integer keyVersion) {
		this.keyVersion = keyVersion;
	}

	public String getEdocGenTyp() {
		return this.edocGenTyp;
	}

	public void setEdocGenTyp(String edocGenTyp) {
		this.edocGenTyp = edocGenTyp;
	}

	public Short getEseries() {
		return this.eseries;
	}

	public void setEseries(Short eseries) {
		this.eseries = eseries;
	}

	public String getEdocNum() {
		return this.edocNum;
	}

	public void setEdocNum(String edocNum) {
		this.edocNum = edocNum;
	}

	public Integer getEdocExpFrm() {
		return this.edocExpFrm;
	}

	public void setEdocExpFrm(Integer edocExpFrm) {
		this.edocExpFrm = edocExpFrm;
	}

	public String getUSms() {
		return this.USms;
	}

	public void setUSms(String USms) {
		this.USms = USms;
	}

	public String getUYsdxdjlx() {
		return this.UYsdxdjlx;
	}

	public void setUYsdxdjlx(String UYsdxdjlx) {
		this.UYsdxdjlx = UYsdxdjlx;
	}

	public Timestamp getUDhdate() {
		return this.UDhdate;
	}

	public void setUDhdate(Timestamp UDhdate) {
		this.UDhdate = UDhdate;
	}

	public String getUDq() {
		return this.UDq;
	}

	public void setUDq(String UDq) {
		this.UDq = UDq;
	}

}