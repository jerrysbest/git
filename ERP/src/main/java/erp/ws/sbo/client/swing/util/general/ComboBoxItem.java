package erp.ws.sbo.client.swing.util.general;


public   class   ComboBoxItem   {
private   Object   value   =   null;
private   String   text   =   null;

public   ComboBoxItem(Object   value,   String   text)
{
this.value   =   value;
this.text   =   text;
}

public   Object   getValue()
{
return   value;
}

public   String   toString()
{
return   text   ==   null   ?   " "   :   text;
}
} 
