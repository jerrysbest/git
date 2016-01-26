package erp.ws.sbo.client.swing.model;



/**
 * @wflist1Id entity. @author MyEclipse Persistence Tools
 */

public class wflist1Id  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7327839250509340068L;
	private wflist docEntry;
    private Integer lineId;


    // Constructors

    /** default constructor */
    public wflist1Id() {
    }

    
    /** full constructor */
    public wflist1Id(wflist docEntry, Integer lineId) {
        this.docEntry = docEntry;
        this.lineId = lineId;
    }

   
    // Property accessors

    public wflist getDocEntry() {
        return this.docEntry;
    }
    
    public void setDocEntry(wflist docEntry) {
        this.docEntry = docEntry;
    }

    public Integer getLineId() {
        return this.lineId;
    }
    
    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof wflist1Id) ) return false;
		 wflist1Id castOther = ( wflist1Id ) other; 
         
		 return ( (this.getDocEntry()==castOther.getDocEntry()) || ( this.getDocEntry()!=null && castOther.getDocEntry()!=null && this.getDocEntry().equals(castOther.getDocEntry()) ) )
 && ( (this.getLineId()==castOther.getLineId()) || ( this.getLineId()!=null && castOther.getLineId()!=null && this.getLineId().equals(castOther.getLineId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocEntry() == null ? 0 : this.getDocEntry().hashCode() );
         result = 37 * result + ( getLineId() == null ? 0 : this.getLineId().hashCode() );
         return result;
   }   





}