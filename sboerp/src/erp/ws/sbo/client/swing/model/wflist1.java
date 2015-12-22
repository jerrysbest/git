package erp.ws.sbo.client.swing.model;





/**
 * @wflist1 entity. @author MyEclipse Persistence Tools
 */

public class wflist1  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 8674568422650120728L;
	 private wflist1Id id;
	// private wflist wflist;
     private Integer visOrder;
     private String object;
     private Integer logInst;
     private Short UWfNode;
     private String UWfPerson;
     private String UApStatus;
    


    // Constructors

    /** default constructor */
    public wflist1() {
    }

	/** minimal constructor */
    public wflist1(wflist1Id id) {
        this.id = id;
    }
    
    /** full constructor */
    public wflist1(wflist1Id id, 
    		//wflist wflist,
    		Integer visOrder, String object, Integer logInst, Short UWfNode, String UWfPerson, String UApStatus) {
        this.id = id;
        //this.wflist=wflist;
        this.visOrder = visOrder;
        this.object = object;
        this.logInst = logInst;
        this.UWfNode = UWfNode;
        this.UWfPerson = UWfPerson;
        this.UApStatus = UApStatus;
    }

   
    // Property accessors

    public wflist1Id getId() {
        return this.id;
    }
    
    public void setId(wflist1Id id) {
        this.id = id;
    }

    /*public wflist getWflist() {
		return wflist;
	}

	public void setWflist(wflist wflist) {
		this.wflist = wflist;
	}*/

	public Integer getVisOrder() {
        return this.visOrder;
    }
    
    public void setVisOrder(Integer visOrder) {
        this.visOrder = visOrder;
    }

    public String getObject() {
        return this.object;
    }
    
    public void setObject(String object) {
        this.object = object;
    }

    public Integer getLogInst() {
        return this.logInst;
    }
    
    public void setLogInst(Integer logInst) {
        this.logInst = logInst;
    }

    public Short getUWfNode() {
        return this.UWfNode;
    }
    
    public void setUWfNode(Short UWfNode) {
        this.UWfNode = UWfNode;
    }

    public String getUWfPerson() {
        return this.UWfPerson;
    }
    
    public void setUWfPerson(String UWfPerson) {
        this.UWfPerson = UWfPerson;
    }

    public String getUApStatus() {
        return this.UApStatus;
    }
    
    public void setUApStatus(String UApStatus) {
        this.UApStatus = UApStatus;
    }

	
   








}