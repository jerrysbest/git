package erp.ws.sbo.client.swing.util.general;

public class IsInt {
	public IsInt(String s)
	{
		isInt(s);
	}
	public static Boolean   isInt(String   str)
	{
	try   {
	//System.out.println( "你输入的整数是 "+   i)   ;
	return   true;
	}   catch   (NumberFormatException   e)   {
	System.out.println( "你输入的不是整数。。。。。。。 ")   ;
	return   false;
	}
	}
}
