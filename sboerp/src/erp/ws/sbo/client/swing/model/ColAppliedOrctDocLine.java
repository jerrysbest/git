package erp.ws.sbo.client.swing.model;



public class ColAppliedOrctDocLine {
	
	private String id="序号";	
    private String cardCode="伙伴代码";
    private String cardName="伙伴名称";
    private String account="科目代码"; 
    private String accountName="科目名称"; 
	private String applied="科目收款金额";

    
    private static ColAppliedOrctDocLine CLine;
	public ColAppliedOrctDocLine(){
		
	}
	 public static ColAppliedOrctDocLine getCLine()
    {
    	if(CLine==null)
    	{
    		CLine=new  ColAppliedOrctDocLine();
    	}
    	return CLine;
    }
	public ColAppliedOrctDocLine(String id) {
		this.setId(id);
	}
	public ColAppliedOrctDocLine(String id,
		     String cardCode,
		     String cardName,
		     String account, 
			 String accountName,
			 String applied
     )
	{
		this.id=id;		
	    this.cardCode=cardCode;
	    this.cardName=cardName;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getApplied() {
		return applied;
	}
	public void setApplied(String applied) {
		this.applied = applied;
	}

		
}
