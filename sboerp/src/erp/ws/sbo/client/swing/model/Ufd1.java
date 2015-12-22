package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Ufd1 entity. @author MyEclipse Persistence Tools
 */

public class Ufd1 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8859954955639689482L;
	private Ufd1Id id;
	private String fldValue;
	private String descr;
	private Timestamp fldDate;

	// Constructors

	/** default constructor */
	public Ufd1() {
	}

	/** minimal constructor */
	public Ufd1(Ufd1Id id) {
		this.id = id;
	}

	/** full constructor */
	public Ufd1(Ufd1Id id, String fldValue, String descr, Timestamp fldDate) {
		this.id = id;
		this.fldValue = fldValue;
		this.descr = descr;
		this.fldDate = fldDate;
	}

	// Property accessors

	public Ufd1Id getId() {
		return this.id;
	}

	public void setId(Ufd1Id id) {
		this.id = id;
	}

	public String getFldValue() {
		return this.fldValue;
	}

	public void setFldValue(String fldValue) {
		this.fldValue = fldValue;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Timestamp getFldDate() {
		return this.fldDate;
	}

	public void setFldDate(Timestamp fldDate) {
		this.fldDate = fldDate;
	}

}