package erp.ws.sbo.client.swing.biz.impl;

import erp.ws.sbo.client.swing.dao.impl.WFlowDao;
import erp.ws.sbo.client.swing.model.wflist;

public class WFlowBizImpl {
private WFlowDao wfd;
  public void add(wflist wfl)
  {
	  wfd.add(wfl);
  }
}
