package erp.ws.sbo.client.swing.model;

/**
 * Srgc entity. @author MyEclipse Persistence Tools
 */

public class Srgc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SrgcId id;
	private String cmpName;
	private String versStr;
	private String loc;

	// Constructors

	/** default constructor */
	public Srgc() {
	}

	/** minimal constructor */
	public Srgc(SrgcId id) {
		this.id = id;
	}

	/** full constructor */
	public Srgc(SrgcId id, String cmpName, String versStr, String loc) {
		this.id = id;
		this.cmpName = cmpName;
		this.versStr = versStr;
		this.loc = loc;
	}

	// Property accessors

	public SrgcId getId() {
		return this.id;
	}

	public void setId(SrgcId id) {
		this.id = id;
	}

	public String getCmpName() {
		return this.cmpName;
	}

	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}

	public String getVersStr() {
		return this.versStr;
	}

	public void setVersStr(String versStr) {
		this.versStr = versStr;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}