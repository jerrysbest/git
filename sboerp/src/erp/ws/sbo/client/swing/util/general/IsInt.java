package erp.ws.sbo.client.swing.util.general;

public class IsInt {
	public IsInt(String s)
	{
		isInt(s);
	}
	public static Boolean   isInt(String   str)
	{
	try   {
	//System.out.println( "������������� "+   i)   ;
	return   true;
	}   catch   (NumberFormatException   e)   {
	System.out.println( "������Ĳ��������������������� ")   ;
	return   false;
	}
	}
}
