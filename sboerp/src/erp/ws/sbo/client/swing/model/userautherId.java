package erp.ws.sbo.client.swing.model;



/**
 * @userautherId entity. @author MyEclipse Persistence Tools
 */

public class userautherId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -809860067801373286L;
	private String usercode;
     private String autherid;


    // Constructors

    /** default constructor */
    public userautherId() {
    }

    
    /** full constructor */
    public userautherId(String usercode, String autherid) {
        this.usercode = usercode;
        this.autherid = autherid;
    }

   
    // Property accessors

    public String getUsercode() {
        return this.usercode;
    }
    
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getAutherid() {
        return this.autherid;
    }
    
    public void setAutherid(String autherid) {
        this.autherid = autherid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof userautherId) ) return false;
		 userautherId castOther = ( userautherId ) other; 
         
		 return ( (this.getUsercode()==castOther.getUsercode()) || ( this.getUsercode()!=null && castOther.getUsercode()!=null && this.getUsercode().equals(castOther.getUsercode()) ) )
 && ( (this.getAutherid()==castOther.getAutherid()) || ( this.getAutherid()!=null && castOther.getAutherid()!=null && this.getAutherid().equals(castOther.getAutherid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUsercode() == null ? 0 : this.getUsercode().hashCode() );
         result = 37 * result + ( getAutherid() == null ? 0 : this.getAutherid().hashCode() );
         return result;
   }   





}