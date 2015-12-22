/**
 * @auther: Jerry si
 * <p>
 * @date: 2012-06-13
 * <p>
 * this is the interface for window of Query,which will be used to query all the docs 
 */
package erp.ws.sbo.client.swing.dao;

import erp.ws.sbo.client.swing.model.ParaList;
import erp.ws.sbo.client.swing.view.QueryWindow.QueryWindowView;

public interface IQDoc {
    public void doclist(ParaList plist,QueryWindowView<?> v);
}
