package erp.ws.sbo.client.swing.model;

/**
 * Oslp entity. @author MyEclipse Persistence Tools
 */

public class Oslp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5988039194914448079L;
	private Short slpCode;
	private String slpName;
	private String memo;
	private Double commission;
	private Short groupCode;
	private String locked;
	private String dataSource;
	private Short userSign;
	private Integer empId;
	private Double UFz1;
	private Double UFz2;
	private Double UYfz1;
	private Double UYfz2;

	// Constructors

	/** default constructor */
	public Oslp() {
	}

	/** minimal constructor */
	public Oslp(Short slpCode, String slpName) {
		this.slpCode = slpCode;
		this.slpName = slpName;
	}

	/** full constructor */
	public Oslp(Short slpCode, String slpName, String memo, Double commission,
			Short groupCode, String locked, String dataSource, Short userSign,
			Integer empId, Double UFz1, Double UFz2, Double UYfz1, Double UYfz2) {
		this.slpCode = slpCode;
		this.slpName = slpName;
		this.memo = memo;
		this.commission = commission;
		this.groupCode = groupCode;
		this.locked = locked;
		this.dataSource = dataSource;
		this.userSign = userSign;
		this.empId = empId;
		this.UFz1 = UFz1;
		this.UFz2 = UFz2;
		this.UYfz1 = UYfz1;
		this.UYfz2 = UYfz2;
	}

	// Property accessors

	public Short getSlpCode() {
		return this.slpCode;
	}

	public void setSlpCode(Short slpCode) {
		this.slpCode = slpCode;
	}

	public String getSlpName() {
		return this.slpName;
	}

	public void setSlpName(String slpName) {
		this.slpName = slpName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Short getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(Short groupCode) {
		this.groupCode = groupCode;
	}

	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Short getUserSign() {
		return this.userSign;
	}

	public void setUserSign(Short userSign) {
		this.userSign = userSign;
	}

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Double getUFz1() {
		return this.UFz1;
	}

	public void setUFz1(Double UFz1) {
		this.UFz1 = UFz1;
	}

	public Double getUFz2() {
		return this.UFz2;
	}

	public void setUFz2(Double UFz2) {
		this.UFz2 = UFz2;
	}

	public Double getUYfz1() {
		return this.UYfz1;
	}

	public void setUYfz1(Double UYfz1) {
		this.UYfz1 = UYfz1;
	}

	public Double getUYfz2() {
		return this.UYfz2;
	}

	public void setUYfz2(Double UYfz2) {
		this.UYfz2 = UYfz2;
	}

}