/**
 * @author: Jerry si
 * <p>
 * @date: 2012-06-07
 * <p>
 * this is the entity for list of parameter,which will be used to query documents,and  all the parameters will be defined
 * @param begindate 
 * @param  enddate
 * @param  begindocid
 * @param  enddocid
 * @param  beginsalepersion
 * @param  endsaleperson
 */
package erp.ws.sbo.client.swing.model;

import java.util.Date;

public class ParaList {
	private Date begindate;
	private Date enddate;
	private String begindocid;
	private String enddocid;
	private String beginsaleperson;
	private String endsaleperson;
	private String begincardCode;
	private String endcardCode;
	private String draft;
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getBegindocid() {
		return begindocid;
	}
	public void setBegindocid(String begindocid) {
		this.begindocid = begindocid;
	}
	public String getEnddocid() {
		return enddocid;
	}
	public void setEnddocid(String enddocid) {
		this.enddocid = enddocid;
	}
	public String getBeginsaleperson() {
		return beginsaleperson;
	}
	public void setBeginsaleperson(String beginsaleperson) {
		this.beginsaleperson = beginsaleperson;
	}
	public String getEndsaleperson() {
		return endsaleperson;
	}
	public void setEndsaleperson(String endsaleperson) {
		this.endsaleperson = endsaleperson;
	}
	public String getBegincardCode() {
		return begincardCode;
	}
	public void setBegincardCode(String begincardCode) {
		this.begincardCode = begincardCode;
	}
	public String getEndcardCode() {
		return endcardCode;
	}
	public void setEndcardCode(String endcardCode) {
		this.endcardCode = endcardCode;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}

}
