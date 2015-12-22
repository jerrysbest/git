package erp.ws.sbo.client.swing.model;

/**
 * Itm1 entity. @author MyEclipse Persistence Tools
 */

public class Itm1 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3846602383890484168L;
	private Itm1Id id;
	private Double price;
	private String currency;
	private String ovrwritten;
	private Double factor;
	private Short logInstanc;
	private String objType;

	// Constructors

	/** default constructor */
	public Itm1() {
	}

	/** minimal constructor */
	public Itm1(Itm1Id id) {
		this.id = id;
	}

	/** full constructor */
	public Itm1(Itm1Id id, Double price, String currency, String ovrwritten,
			Double factor, Short logInstanc, String objType) {
		this.id = id;
		this.price = price;
		this.currency = currency;
		this.ovrwritten = ovrwritten;
		this.factor = factor;
		this.logInstanc = logInstanc;
		this.objType = objType;
	}

	// Property accessors

	public Itm1Id getId() {
		return this.id;
	}

	public void setId(Itm1Id id) {
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

	public String getOvrwritten() {
		return this.ovrwritten;
	}

	public void setOvrwritten(String ovrwritten) {
		this.ovrwritten = ovrwritten;
	}

	public Double getFactor() {
		return this.factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
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

}