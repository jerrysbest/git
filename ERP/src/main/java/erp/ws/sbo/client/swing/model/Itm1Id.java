package erp.ws.sbo.client.swing.model;

/**
 * Itm1Id entity. @author MyEclipse Persistence Tools
 */

public class Itm1Id implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1825275240214988098L;
	private String itemCode;
	private Short priceList;

	// Constructors

	/** default constructor */
	public Itm1Id() {
	}

	/** full constructor */
	public Itm1Id(String itemCode, Short priceList) {
		this.itemCode = itemCode;
		this.priceList = priceList;
	}

	// Property accessors

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Short getPriceList() {
		return this.priceList;
	}

	public void setPriceList(Short priceList) {
		this.priceList = priceList;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Itm1Id))
			return false;
		Itm1Id castOther = (Itm1Id) other;

		return ((this.getItemCode() == castOther.getItemCode()) || (this
				.getItemCode() != null && castOther.getItemCode() != null && this
				.getItemCode().equals(castOther.getItemCode())))
				&& ((this.getPriceList() == castOther.getPriceList()) || (this
						.getPriceList() != null
						&& castOther.getPriceList() != null && this
						.getPriceList().equals(castOther.getPriceList())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		result = 37 * result
				+ (getPriceList() == null ? 0 : this.getPriceList().hashCode());
		return result;
	}

}