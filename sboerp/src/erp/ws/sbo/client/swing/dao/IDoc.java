/**
 * @auther: Jerry si
 * <p>
 * @date: 2012-06-07
 * <p>
 * this is the interface for Documents,which would defined all the method like crud in documents operation
 */
package erp.ws.sbo.client.swing.dao;


import erp.ws.sbo.client.swing.model.ParaList;


public interface IDoc<T> {
  //create doc
  public void create(T v);
  //read doc by id
  public Integer read(int id,String dod);
  //update doc by id
  public void update(T v);
  //delete doc by id
  public void delete(int id);  
  //get list of doc
  public Object[][] getDocLists(ParaList p);
  //get  first doc
  public Integer getfirst();
  //get preview doc
  public Integer getprev(int id);
  //get next doc
  public Integer getnext(int id);
  //get last doc
  public Integer getlast();
  //set values for view
  public void setValues(T v,Integer id,String dod);
  //close the doc
  public void close(T v);
  //print the doc
  public void print(T v);
  //Create target doc
  public void ctarget(T v,Integer docid);

 
}
