package erp.ws.sbo.client.swing.model;



/**
 * listsn entity. author MyEclipse Persistence Tools
 */

public class listsn  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1184264206287063435L;
	private Integer id;
     private String sn;
     private Boolean direction;
     private Boolean ifPaSn;
     private String paSn;
     private String wareHouse;
     private String cardCode;


    // Constructors

    /** default constructor */
    public listsn() {
    }

	/** minimal constructor */
    public listsn(Integer id, String sn, Boolean direction, Boolean ifPaSn) {
        this.id = id;
        this.sn = sn;
        this.direction = direction;
        this.ifPaSn = ifPaSn;
    }
    
    /** full constructor */
    public listsn(Integer id, String sn, Boolean direction, Boolean ifPaSn, String paSn, String wareHouse, String cardCode) {
        this.id = id;
        this.sn = sn;
        this.direction = direction;
        this.ifPaSn = ifPaSn;
        this.paSn = paSn;
        this.wareHouse = wareHouse;
        this.cardCode = cardCode;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return this.sn;
    }
    
    public void setSn(String sn) {
        this.sn = sn;
    }

    public Boolean getDirection() {
        return this.direction;
    }
    
    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public Boolean getIfPaSn() {
        return this.ifPaSn;
    }
    
    public void setIfPaSn(Boolean ifPaSn) {
        this.ifPaSn = ifPaSn;
    }

    public String getPaSn() {
        return this.paSn;
    }
    
    public void setPaSn(String paSn) {
        this.paSn = paSn;
    }

    public String getWareHouse() {
        return this.wareHouse;
    }
    
    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public String getCardCode() {
        return this.cardCode;
    }
    
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
   








}