package erp.ws.sbo.client.swing.model;



/**
 * @bcsd entity. @author MyEclipse Persistence Tools
 */

public class bcsd  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5795612798292921328L;
	private String code;
     private String name;
     private String UBhdSd;
     private String USkdSd;


    // Constructors

    /** default constructor */
    public bcsd() {
    }

	/** minimal constructor */
    public bcsd(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /** full constructor */
    public bcsd(String code, String name, String UBhdSd, String USkdSd) {
        this.code = code;
        this.name = name;
        this.UBhdSd = UBhdSd;
        this.USkdSd = USkdSd;
    }

   
    // Property accessors

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUBhdSd() {
        return this.UBhdSd;
    }
    
    public void setUBhdSd(String UBhdSd) {
        this.UBhdSd = UBhdSd;
    }

    public String getUSkdSd() {
        return this.USkdSd;
    }
    
    public void setUSkdSd(String USkdSd) {
        this.USkdSd = USkdSd;
    }
   








}