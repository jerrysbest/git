package erp.ws.sbo.client.swing.model;

/**
 * SrgcId entity. @author MyEclipse Persistence Tools
 */

public class SrgcId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dbName;
	private String dbUser;

	// Constructors

	/** default constructor */
	public SrgcId() {
	}

	/** full constructor */
	public SrgcId(String dbName, String dbUser) {
		this.dbName = dbName;
		this.dbUser = dbUser;
	}

	// Property accessors

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return this.dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SrgcId))
			return false;
		SrgcId castOther = (SrgcId) other;

		return ((this.getDbName() == castOther.getDbName()) || (this
				.getDbName() != null && castOther.getDbName() != null && this
				.getDbName().equals(castOther.getDbName())))
				&& ((this.getDbUser() == castOther.getDbUser()) || (this
						.getDbUser() != null && castOther.getDbUser() != null && this
						.getDbUser().equals(castOther.getDbUser())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDbName() == null ? 0 : this.getDbName().hashCode());
		result = 37 * result
				+ (getDbUser() == null ? 0 : this.getDbUser().hashCode());
		return result;
	}

}