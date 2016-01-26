package erp.ws.sbo.client.swing.store;

public class Ordrs {
	 private Object[] value={};
	 private static Ordrs instance;
	 private Ordrs()
	 {
		 
	 }
	 public static Ordrs getOrdrs()
	 {
		 if(instance==null)
		 {
			 instance=new Ordrs();
		 }
		 return instance;
	 }
	public Object[] getValue() {
		return value;
	}
	public void setValue(Object[] value) {
		this.value = value;
	}
}
