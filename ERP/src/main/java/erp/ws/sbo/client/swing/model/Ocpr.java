package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;

/**
 * Ocpr entity. @author MyEclipse Persistence Tools
 */

public class Ocpr implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cntctCode;
	private String cardCode;
	private String name;
	private String position;
	private String address;
	private String tel1;
	private String tel2;
	private String cellolar;
	private String fax;
	private String EMailL;
	private String pager;
	private String notes1;
	private String notes2;
	private String dataSource;
	private Short userSign;
	private String password;
	private Short logInstanc;
	private String objType;
	private String birthPlace;
	private Timestamp birthDate;
	private String gender;
	private String profession;
	private Timestamp updateDate;
	private Integer updateTime;
	private String title;
	private String birthCity;
	private String active;
	private String firstName;
	private String middleName;
	private String lastName;

	// Constructors

	/** default constructor */
	public Ocpr() {
	}

	/** minimal constructor */
	public Ocpr(Integer cntctCode, String cardCode, String name) {
		this.cntctCode = cntctCode;
		this.cardCode = cardCode;
		this.name = name;
	}

	/** full constructor */
	public Ocpr(Integer cntctCode, String cardCode, String name,
			String position, String address, String tel1, String tel2,
			String cellolar, String fax, String EMailL, String pager,
			String notes1, String notes2, String dataSource, Short userSign,
			String password, Short logInstanc, String objType,
			String birthPlace, Timestamp birthDate, String gender,
			String profession, Timestamp updateDate, Integer updateTime,
			String title, String birthCity, String active, String firstName,
			String middleName, String lastName) {
		this.cntctCode = cntctCode;
		this.cardCode = cardCode;
		this.name = name;
		this.position = position;
		this.address = address;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.cellolar = cellolar;
		this.fax = fax;
		this.EMailL = EMailL;
		this.pager = pager;
		this.notes1 = notes1;
		this.notes2 = notes2;
		this.dataSource = dataSource;
		this.userSign = userSign;
		this.password = password;
		this.logInstanc = logInstanc;
		this.objType = objType;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.gender = gender;
		this.profession = profession;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
		this.title = title;
		this.birthCity = birthCity;
		this.active = active;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	// Property accessors

	public Integer getCntctCode() {
		return this.cntctCode;
	}

	public void setCntctCode(Integer cntctCode) {
		this.cntctCode = cntctCode;
	}

	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return this.tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getCellolar() {
		return this.cellolar;
	}

	public void setCellolar(String cellolar) {
		this.cellolar = cellolar;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEMailL() {
		return this.EMailL;
	}

	public void setEMailL(String EMailL) {
		this.EMailL = EMailL;
	}

	public String getPager() {
		return this.pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getNotes1() {
		return this.notes1;
	}

	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}

	public String getNotes2() {
		return this.notes2;
	}

	public void setNotes2(String notes2) {
		this.notes2 = notes2;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirthCity() {
		return this.birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}