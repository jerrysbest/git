package erp.ws.sbo.client.swing.model;

import java.sql.Timestamp;


/**
 * log entity. @author MyEclipse Persistence Tools
 */

public class log  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 7470682289147239561L;
	private Integer id;
     private Integer usersign;
     private Timestamp createdate;
     private String content;
     private String operation;


    // Constructors

    /** default constructor */
    public log() {
    }

    
    /** full constructor */
    public log(Integer id, Integer usersign, Timestamp createdate, String content, String operation) {
        this.id = id;
        this.usersign = usersign;
        this.createdate = createdate;
        this.content = content;
        this.operation = operation;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsersign() {
        return this.usersign;
    }
    
    public void setUsersign(Integer usersign) {
        this.usersign = usersign;
    }

    public Timestamp getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
   








}