package erp.ws.sbo.client.swing.model;

/**
 * Ufd1Id entity. @author MyEclipse Persistence Tools
 */

public class Ufd1Id implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6953081026685100453L;
	private String tableId;
	private Short fieldId;
	private Short indexId;

	// Constructors

	/** default constructor */
	public Ufd1Id() {
	}

	/** full constructor */
	public Ufd1Id(String tableId, Short fieldId, Short indexId) {
		this.tableId = tableId;
		this.fieldId = fieldId;
		this.indexId = indexId;
	}

	// Property accessors

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public Short getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Short fieldId) {
		this.fieldId = fieldId;
	}

	public Short getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Short indexId) {
		this.indexId = indexId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Ufd1Id))
			return false;
		Ufd1Id castOther = (Ufd1Id) other;

		return ((this.getTableId() == castOther.getTableId()) || (this
				.getTableId() != null && castOther.getTableId() != null && this
				.getTableId().equals(castOther.getTableId())))
				&& ((this.getFieldId() == castOther.getFieldId()) || (this
						.getFieldId() != null && castOther.getFieldId() != null && this
						.getFieldId().equals(castOther.getFieldId())))
				&& ((this.getIndexId() == castOther.getIndexId()) || (this
						.getIndexId() != null && castOther.getIndexId() != null && this
						.getIndexId().equals(castOther.getIndexId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTableId() == null ? 0 : this.getTableId().hashCode());
		result = 37 * result
				+ (getFieldId() == null ? 0 : this.getFieldId().hashCode());
		result = 37 * result
				+ (getIndexId() == null ? 0 : this.getIndexId().hashCode());
		return result;
	}

}