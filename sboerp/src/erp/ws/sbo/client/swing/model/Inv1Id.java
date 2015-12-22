package erp.ws.sbo.client.swing.model;



/**
 * Inv1Id entity. @author MyEclipse Persistence Tools
 */

public class Inv1Id  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 158329088014876185L;
	private Integer docEntry;
     private Integer lineNum;


    // Constructors

    /** default constructor */
    public Inv1Id() {
    }

    
    /** full constructor */
    public Inv1Id(Integer docEntry, Integer lineNum) {
        this.docEntry = docEntry;
        this.lineNum = lineNum;
    }

   
    // Property accessors

    public Integer getDocEntry() {
        return this.docEntry;
    }
    
    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public Integer getLineNum() {
        return this.lineNum;
    }
    
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Inv1Id) ) return false;
		 Inv1Id castOther = ( Inv1Id ) other; 
         
		 return ( (this.getDocEntry()==castOther.getDocEntry()) || ( this.getDocEntry()!=null && castOther.getDocEntry()!=null && this.getDocEntry().equals(castOther.getDocEntry()) ) )
 && ( (this.getLineNum()==castOther.getLineNum()) || ( this.getLineNum()!=null && castOther.getLineNum()!=null && this.getLineNum().equals(castOther.getLineNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocEntry() == null ? 0 : this.getDocEntry().hashCode() );
         result = 37 * result + ( getLineNum() == null ? 0 : this.getLineNum().hashCode() );
         return result;
   }   





}