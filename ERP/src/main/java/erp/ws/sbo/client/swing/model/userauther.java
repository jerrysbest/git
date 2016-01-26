package erp.ws.sbo.client.swing.model;



/**
 * @userauther entity. @author MyEclipse Persistence Tools
 */

public class userauther  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -2592142252703529264L;
	private userautherId id;
     private Boolean enable;


    // Constructors

    /** default constructor */
    public userauther() {
    }

    
    /** full constructor */
    public userauther(userautherId id, Boolean enable) {
        this.id = id;
        this.enable = enable;
    }

   
    // Property accessors

    public userautherId getId() {
        return this.id;
    }
    
    public void setId(userautherId id) {
        this.id = id;
    }

    public Boolean getEnable() {
        return this.enable;
    }
    
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
   








}