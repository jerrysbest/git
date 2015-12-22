package erp.ws.sbo.utils;

public class ToHexString {
	public static String toHexString(String s) 
	{ 
		String str=""; 
		for (int i=0;i<s.length();i++) 
		{ 
		int ch = (int)s.charAt(i); 
		String s4 = Integer.toHexString(ch); 
		str = str + s4; 
		} 
		return str; 
	} 
}
