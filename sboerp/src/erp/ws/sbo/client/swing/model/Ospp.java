package erp.ws.sbo.client.swing.model;

/**
 * Ospp entity. @author MyEclipse Persistence Tools
 */

public class Ospp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 765037064060606560L;
	private OsppId id;
	private Double price;
	private String currency;
	private Double discount;
	private Short listNum;
	private String autoUpdt;
	private String expand;
	private Short userSign;

	// Constructors

	/** default constructor */
	public Ospp() {
	}

	/** minimal constructor */
	public Ospp(OsppId id) {
		this.id = id;
	}

	/** full constructor */
	public Ospp(OsppId id, Double price, String currency, Double discount,
			Short listNum, String autoUpdt, String expand, Short userSign) {
		this.id = id;
		this.price = price;
		this.currency = currency;
		this.discount = discount;
		this.listNum = listNum;
		this.autoUpdt = autoUpdt;
		this.expand = expand;
		this.userSign = userSign;
	}

	// Property accessors

	public OsppId getId() {
		return this.id;
	}

	public void setId(OsppId id) {
		this.id = id;
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

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Short getListNum() {
		return this.listNum;
	}

	public void setListNum(Short listNum) {
		this.listNum = listNum;
	}

	public String getAutoUpdt() {
		return this.autoUpdt;
	}

	public void setAutoUpdt(String autoUpdt) {
		this.autoUpdt = autoUpdt;
	}

	public String getExpand() {
		return this.expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

}