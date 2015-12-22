package erp.ws.sbo.utilsTest;

import erp.ws.sbo.client.swing.model.ColDocLine;
import erp.ws.sbo.client.swing.model.DocLine;
import erp.ws.sbo.utils.DbUtils;


public class DbUtilsTest extends junit.framework.TestCase{
    
	private DbUtils<ColDocLine, ?> dbu;
	private String[] s2={"ANNOTATION","ENUM"};
	//private Ordr od;
	@SuppressWarnings("rawtypes")
	public void setUp()
	{
		dbu=new DbUtils<ColDocLine,DocLine>();
		//od=new Ordr();
		//s1[]={"a","b"};
	}	
	public void testGetColumnNames()
	{			
	//	assertTrue(Arrays.equals(s1, dbu.getColumnNames(od)));
	}
	
	public void testgetDataTypes()
	{
	//	assertTrue(Arrays.equals(s2, dbu.getDataTypes(od)));
	}
	
	public void testExecuteQuery()
	{
		assertEquals(s2,dbu.executeQuery(""));
	}
}
