package erp.ws.sbo.client.swing.dao;

import erp.ws.sbo.client.swing.dao.abs.AbsDoc;
import erp.ws.sbo.client.swing.dao.abs.OrderAbsDoc;
import erp.ws.sbo.client.swing.dao.abs.SninAbsDoc;

public abstract class DaoFactory<T> {
	
	public IDoc<T> idoc;
	public IAdvSN<T> IAdvSN;
	public static DaoFactory<?> getInstance(String classname) {      
		DaoFactory<?> dao=null;      
        try {  
            dao = (DaoFactory<?>) Class.forName(classname).newInstance();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }        
        return dao;  
    }  
    public  abstract IDoc<T> getDoc();   
    public  abstract IAdvSN<T> getAdnSN(); 
    public  abstract AbsDoc getAbsDoc();    
    public  abstract OrderAbsDoc getOrdrAbsDoc();    
    public  abstract SninAbsDoc getSninAbsDoc();     
    public  abstract IQDoc getQDoc();  
    public  abstract IWFlowDao workflow();
	public IDoc<T> getIdoc() {
		return idoc;
	}
	public void setIdoc(IDoc<T> idoc) {
		this.idoc = idoc;
	}
	public IAdvSN<T> getIAdvSN() {
		return IAdvSN;
	}
	public void setIAdvSN(IAdvSN<T> iAdvSN) {
		IAdvSN = iAdvSN;
	} 
   
     
}
