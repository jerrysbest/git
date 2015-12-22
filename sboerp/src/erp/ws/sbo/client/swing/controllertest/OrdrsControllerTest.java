package erp.ws.sbo.client.swing.controllertest;




import erp.ws.sbo.client.swing.controller.OrdrsController;
import erp.ws.sbo.client.swing.view.Ordr.OrdrView;


public class OrdrsControllerTest extends junit.framework.TestCase 
{	private OrdrView v;
	public void setUp() {
	         new OrdrsController(v);
	   }	
	
	//需要UI客户端实现的方法，可能需要在controller里实现
	 public void testMouseClicked()
	 {
		 
	 }

	
}
