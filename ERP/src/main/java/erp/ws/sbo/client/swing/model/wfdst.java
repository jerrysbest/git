package erp.ws.sbo.client.swing.model;



/**
 * @wfdst entity. @author MyEclipse Persistence Tools
 */

public class wfdst  implements java.io.Serializable {
    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1680971038740556675L;
	private String code;
     private String name;
     private Integer UWfvalue;
     private String UAcode1;
     private String UAcode2;


    // Constructors

    /** default constructor */
    public wfdst() {
    }

	/** minimal constructor */
    public wfdst(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /** full constructor */
    public wfdst(String code, String name, Integer UWfvalue, String UAcode1, String UAcode2) {
        this.code = code;
        this.name = name;
        this.UWfvalue = UWfvalue;
        this.UAcode1 = UAcode1;
        this.UAcode2 = UAcode2;
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

    public Integer getUWfvalue() {
        return this.UWfvalue;
    }
    
    public void setUWfvalue(Integer UWfvalue) {
        this.UWfvalue = UWfvalue;
    }

    public String getUAcode1() {
        return this.UAcode1;
    }
    
    public void setUAcode1(String UAcode1) {
        this.UAcode1 = UAcode1;
    }

    public String getUAcode2() {
        return this.UAcode2;
    }
    
    public void setUAcode2(String UAcode2) {
        this.UAcode2 = UAcode2;
    }
   








}