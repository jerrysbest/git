package erp.ws.sbo.client.swing.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
     
	private List<String> privilages = new ArrayList<String>(); 
	private Integer id;
	private String code;
	private String name;
	private String name1;
	
	public boolean isPermission(String pri){  
         Iterator<String> it = privilages.iterator();  
         String p = "";  
         boolean pass=false;  
         while(it.hasNext()){  
             p=(String)it.next();  
             System.out.println(p);  
             if(p.equals(pri)){  
                 pass = true;  
                 break;  
             }  
         }  
         return pass;  
     }  
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}

	public List<String> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(List<String> privilages) {
		this.privilages = privilages;
	}
	
}
