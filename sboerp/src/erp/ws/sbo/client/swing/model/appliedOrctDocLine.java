package erp.ws.sbo.client.swing.model;


public class appliedOrctDocLine {
	
	private Integer id;	
    private String cardCode;
    private String cardName;
    private String account; 
	private String accountName;
	private Double applied;  

	public appliedOrctDocLine(){
		
	}
	public appliedOrctDocLine(Integer id) {
		this.setId(id);
	}
	public appliedOrctDocLine(Integer id,
		     String cardCode,
		     String cardName,
		     String account, 
			 String accountName,
			 Double applied
     )
	{
		this.id=id;		
	    this.cardCode=cardCode;
	    this.cardCode=cardName;
	    this.account=account; 
	    this.accountName=accountName;
	    this.applied=applied;
	}

	
	public Object getByName(String s) {
		if(s=="id")
		{
			return id;
		}
		else if(s=="cardCode")
		{
			return cardCode;
		}
		else if(s=="cardName")
		{
			return cardName;
		}
	    else if(s=="account")
		{
			return account;
		}		
		else if(s=="accountName")
		{
			return accountName;
		}
		else if(s=="applied")
		{
			return applied;
		}	
		else {
			return null;
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getApplied() {
		return applied;
	}
	public void setApplied(Double applied) {
		this.applied = applied;
	}
	
	
}
