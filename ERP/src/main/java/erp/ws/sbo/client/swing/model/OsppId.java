package erp.ws.sbo.client.swing.model;

/**
 * OsppId entity. @author MyEclipse Persistence Tools
 */

public class OsppId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2832200283300255546L;
	private String cardCode;
	private String itemCode;

	// Constructors

	/** default constructor */
	public OsppId() {
	}

	/** full constructor */
	public OsppId(String cardCode, String itemCode) {
		this.cardCode = cardCode;
		this.itemCode = itemCode;
	}

	// Property accessors

	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OsppId))
			return false;
		OsppId castOther = (OsppId) other;

		return ((this.getCardCode() == castOther.getCardCode()) || (this
				.getCardCode() != null && castOther.getCardCode() != null && this
				.getCardCode().equals(castOther.getCardCode())))
				&& ((this.getItemCode() == castOther.getItemCode()) || (this
						.getItemCode() != null
						&& castOther.getItemCode() != null && this
						.getItemCode().equals(castOther.getItemCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCardCode() == null ? 0 : this.getCardCode().hashCode());
		result = 37 * result
				+ (getItemCode() == null ? 0 : this.getItemCode().hashCode());
		return result;
	}

}