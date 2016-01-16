package erp.ws.sbo.client.swing.dao;




public interface IAdvSN<T> {
	//add sn
	public void add(T v,String SN,boolean ifdraft,String objtype,String Direction,boolean ifpasn,int rowid);
//	//delete sn
//	public void delete(DeSNView v,String SN);
	//verification sn area
	public boolean verification(T v);
	//verification sn area and sn dialog
	public boolean snverification(T v);
	//verification sn area and item details
	public boolean bfcverification(T v);
	
    
	    
}
