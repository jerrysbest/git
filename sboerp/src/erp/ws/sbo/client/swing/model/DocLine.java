package erp.ws.sbo.client.swing.model;

import java.util.Date;

public class DocLine<T> {
	
	private T id;	
	private Integer USnid;
	private Integer docEntry;
	private Integer lineNum;
    private Integer baseEntry;
    private Integer baseLine;
    private String cardCode;
    private String cardName; 
	private String itemCode;
	private String dscription;
	private String UYmd;
	private Double UMtmd;
	private String unitMsr;
	private Double numPerMsr;
	private String whsCode;
	private String unitMsr2;
    private Integer UGs;
    private Integer USGs;
	private Double UGjjg;
	private Double quantity;
	private Double USQuantity;
	private Double price;
	private Double UMjg;
    private Double UZc;
    private Double lineTotal; 
    private Double UZz;  
	private String USfhh;
    private Double UTsjg;
    private String UMtdl;
    private Integer UDjNo;
    private Short UDjNozj;
    private Double UDydj;
    private Double UJgf;
    private String UYdy; 
    private String UCkck;
    private String UCzy;
    private Double UScwc;
	private String URinzz;
    private Date UDhdate;
    private String Freetext;

	public DocLine(){
		
	}
	public DocLine(T id) {
		this.setId(id);
	}
	public DocLine(T id,
	 Integer USnid,
	 Integer docEntry,
	 Integer lineNum,
     Integer baseEntry,
     Integer baseLine,
     String cardCode,
     String cardName, 
	 String itemCode,
	 String dscription,
	 String UYmd,
	 Double UMtmd,
	 String unitMsr,
	 Double numPerMsr,
	 String whsCode,
	 String unitMsr2,  
     Integer UGs,
     Integer USGs,
	 Double quantity,
	 Double USQuantity,
	 Double UMjg,
	 Double UGjjg,
	 Double price,
     Double lineTotal, 
     Double UZc,
     Double UZz,  
	 String USfhh,
     Double UTsjg,
     String UMtdl,
     Integer UDjNo,
     Short UDjNozj,
     Double UDydj,
     Double UJgf,
     String UYdy, 
     String UCkck,
     String UCzy,
     Double UScwc,
	 String URinzz,
	 Date UDhdate,
	 String Freetext
     )
	{
		this.id=id;		
		this.USnid=USnid;
		this.docEntry=docEntry;
		this.lineNum=lineNum;
	    this.baseEntry=baseEntry;
	    this.baseLine=baseLine;
	    this.cardCode=cardCode;
	    this.cardName=cardName; 
		this.itemCode=itemCode;
		this.dscription=dscription;
		this.UYmd=UYmd;
		this.UMtmd=UMtmd;
		this.unitMsr=unitMsr;
		this.unitMsr2=unitMsr2;
	    this.UGs=UGs;
	    this.USGs=USGs;
	    this.whsCode=whsCode;
		this.quantity=quantity;
		this.USQuantity=USQuantity;
		this.numPerMsr=numPerMsr;
		this.UMjg=UMjg;
		this.UGjjg=UGjjg;
		this.price=price;
	    this.lineTotal=lineTotal; 
	    this.UZc=UZc;
	    this.UZz=UZz;  
		this.USfhh=USfhh;
	    this.UTsjg=UTsjg;
		this.UMtdl=UMtdl;
	    this.UDjNo=UDjNo;
	    this.UDjNozj=UDjNozj;
	    this.UDydj=UDydj;
	    this.UJgf=UJgf;
	    this.UYdy=UYdy; 
	    this.UCkck=UCkck;
	    this.UCzy=UCzy;
	    this.UScwc=UScwc;
		this.URinzz=URinzz;
	    this.UDhdate=UDhdate;
	    this.Freetext=Freetext;
	}
	public DocLine(String UMtdl,Double UMtmd,String itemCode)
	{
		this.UMtdl=UMtdl;
		this.UMtmd=UMtmd;
		this.itemCode=itemCode;	
	}
	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="USnid")
		{
			return USnid;
		}
		else if(s=="docEntry")
		{
			return docEntry;
		}
		
		else if(s=="lineNum")
		{
			return lineNum;
		}
		else if(s=="baseEntry")
		{
			return baseEntry;
		}
		else if(s=="baseLine")
		{
			return baseLine;
		}
		else if(s=="cardCode")
		{
			return cardCode;
		}
	    else if(s=="cardName")
		{
			return cardName;
		}		
		else if(s=="itemCode")
		{
			return itemCode;
		}
		else if(s=="dscription")
		{
			return dscription;
		}
		else if(s=="UYmd")
		{
			return UYmd;
		}
		else if(s=="UMtmd")
		{
			return UMtmd;
		}
		else if(s=="unitMsr")
		{
			return unitMsr;
		}
		else if(s=="unitMsr2")
		{
			return unitMsr2;
		}
		else if(s=="UGs")
		{
			return UGs;
		}
		else if(s=="USGs")
		{
			return USGs;
		}
		else if(s=="numPerMsr")
		{
			return numPerMsr;
		}
		else if(s=="whsCode")
		{
			return whsCode;
		}
		else if(s=="quantity")
		{
			return quantity;
		}
		else if(s=="USQuantity")
		{
			return USQuantity;
		}	
		else if(s=="UMjg")
		{
			return UMjg;
		}
		else if(s=="UGjjg")
		{
			return UGjjg;
		}
		else if(s=="price")
		{
			return price;
		}
		else if(s=="lineTotal")
		{
			return lineTotal;
		}
		else if(s=="UZc")
		{
			return UZc;
		}
		else if(s=="UZz")
		{
			return UZz;
		}
		else if(s=="USfhh")
		{
			return  USfhh;
		}
		else if(s=="UTsjg")
		{
			return UTsjg;
		}
		else if(s=="UDjNo")
		{
			return UDjNo;
		}
		else if(s=="UDjNozj")
		{
			return UDjNozj;
		}
		else if(s=="UDydj")
		{
			return UDydj;
		}
		
		else if(s=="UJgf")
		{
			return UJgf;
		}
		else if(s=="UYdy")
		{
			return UYdy;
		}
		else if(s=="UCkck")
		{
			return UCkck;
		}
		else if(s=="UCzy")
		{
			return UCzy;
		}
		else if(s=="UScwc")
		{
			return UScwc;
		}
		else if(s=="URinzz")
		{
			return URinzz;
		}
		else if(s=="UDhdate")
		{
			return  UDhdate;
		}
		else if(s=="Freetext")
		{
			return Freetext;
		}
		else {
			return null;
		}
	}
	
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public Double getUMtmd() {
		return UMtmd;
	}
	public void setUMtmd(Double uMtmd) {
		this.UMtmd = uMtmd;
	}
	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}
	 public Integer getUSnid() {
		return USnid;
	}
	public void setUSnid(Integer uSnid) {
		USnid = uSnid;
	}
	public Integer getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(Integer docEntry) {
		this.docEntry = docEntry;
	}
	public Integer getLineNum() {
		return lineNum;
	}
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	public Integer getBaseEntry() {
		return baseEntry;
	}
	public void setBaseEntry(Integer baseEntry) {
		this.baseEntry = baseEntry;
	}
	public Integer getBaseLine() {
		return baseLine;
	}
	public void setBaseLine(Integer baseLine) {
		this.baseLine = baseLine;
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
	public Double getQuantity() {
			return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUMjg() {
		return UMjg;
	}
	public void setUMjg(Double uMjg) {
		UMjg = uMjg;
	}
	public Double getUGjjg() {
		return UGjjg;
	}
	public void setUGjjg(Double uGjjg) {
		UGjjg = uGjjg;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(Double lineTotal) {
		this.lineTotal = lineTotal;
	}
	public Double getUZc() {
		return UZc;
	}
	public void setUZc(Double uZc) {
		UZc = uZc;
	}
	public Double getUZz() {
		return UZz;
	}
	public void setUZz(Double uZz) {
		UZz = uZz;
	}
	public String getUSfhh() {
		return USfhh;
	}
	public void setUSfhh(String uSfhh) {
		USfhh = uSfhh;
	}
	public Double getUTsjg() {
		return UTsjg;
	}
	public void setUTsjg(Double uTsjg) {
		UTsjg = uTsjg;
	}
	public String getUMtdl() {
		return UMtdl;
	}	
	public void setUMtdl(String uMtdl) {
		this.UMtdl = uMtdl;
	}
	public Integer getUDjNo() {
		return UDjNo;
	}
	public void setUDjNo(Integer uDjNo) {
		UDjNo = uDjNo;
	}
	public Short getUDjNozj() {
		return UDjNozj;
	}
	public void setUDjNozj(Short uDjNozj) {
		UDjNozj = uDjNozj;
	}
	public Double getUDydj() {
		return UDydj;
	}
	public void setUDydj(Double uDydj) {
		UDydj = uDydj;
	}
	public Integer getUGs() {
		return UGs;
	}
	public void setUGs(Integer uGs) {
		UGs = uGs;
	}
	public Double getUJgf() {
		return UJgf;
	}
	public void setUJgf(Double uJgf) {
		UJgf = uJgf;
	}
	public String getUYdy() {
		return UYdy;
	}
	public void setUYdy(String uYdy) {
		UYdy = uYdy;
	}
	public String getUCkck() {
		return UCkck;
	}
	public void setUCkck(String uCkck) {
		UCkck = uCkck;
	}
	public String getUCzy() {
		return UCzy;
	}
	public void setUCzy(String uCzy) {
		UCzy = uCzy;
	}
	public Double getUScwc() {
		return UScwc;
	}
	public void setUScwc(Double uScwc) {
		UScwc = uScwc;
	}
	public String getURinzz() {
		return URinzz;
	}
	public void setURinzz(String uRinzz) {
		URinzz = uRinzz;
	}
	public Date getUDhdate() {
		return UDhdate;
	}
	public void setUDhdate(Date uDhdate) {
		UDhdate = uDhdate;
	}
	public String getUnitMsr() {
		return unitMsr;
	}
	public void setUnitMsr(String unitMsr) {
		this.unitMsr = unitMsr;
	}
	public String getWhsCode() {
		return whsCode;
	}
	public void setWhsCode(String whsCode) {
		this.whsCode = whsCode;
	}
	public String getUnitMsr2() {
		return unitMsr2;
	}
	public void setUnitMsr2(String unitMsr2) {
		this.unitMsr2 = unitMsr2;
	}
	public String getUYmd() {
		return UYmd;
	}
	public void setUYmd(String uYmd) {
		UYmd = uYmd;
	}
	public Double getNumPerMsr() {
		return numPerMsr;
	}
	public void setNumPerMsr(Double numPerMsr) {
		this.numPerMsr = numPerMsr;
	}
	public Integer getUSGs() {
		return USGs;
	}
	public void setUSGs(Integer uSGs) {
		USGs = uSGs;
	}
	public Double getUSQuantity() {
		return USQuantity;
	}
	public void setUSQuantity(Double uSQuantity) {
		USQuantity = uSQuantity;
	}
	public String getFreetext() {
		return Freetext;
	}
	public void setFreetext(String freetext) {
		Freetext = freetext;
	}
	
}
