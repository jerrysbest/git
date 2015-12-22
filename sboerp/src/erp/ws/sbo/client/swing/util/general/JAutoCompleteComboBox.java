package erp.ws.sbo.client.swing.util.general;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JAutoCompleteComboBox extends JComboBox{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AutoCompleter completer;

	public JAutoCompleteComboBox() {
	    super();	 
        addCompleter();
   
	  
}
	public JAutoCompleteComboBox(String hql,String hql1) {
	    super();
	    addSCompleter(hql,hql1);
	}

	public JAutoCompleteComboBox(String hql,String hql1,int i) {
	    super();
	    addSCompleter(hql,hql1,i);
	}
	public JAutoCompleteComboBox(ComboBoxModel cm) {
	    super(cm);
	    addCompleter();
	}

	public JAutoCompleteComboBox(Object[] items) {
	    super(items);
	    addCompleter();
	}

	public JAutoCompleteComboBox(List<?> v) {
	    super( (ComboBoxModel) v);
	    addCompleter();
	}

	
	private void addCompleter() {		
	        	setEditable(true);	    	    
	    	    completer = new AutoCompleter(this);
	   
	}
	
	private void addSCompleter(String hql,String hql1) {
		
    	setEditable(true);	    	    
	    completer = new AutoCompleter(hql,hql1,3,this);

}
private void addSCompleter(String hql,String hql1,int i) {
		
    	setEditable(true);	    	    
	    completer = new AutoCompleter(hql,hql1,i,this);

}

	public void autoComplete(String str) {
	    this.completer.autoComplete(str, str.length());
	}

	public String getText() {
	    return ( (JTextField) getEditor().getEditorComponent()).getText();
	}

	public void setText(String text) {
	    ( (JTextField) getEditor().getEditorComponent()).setText(text);
	}

	public boolean containsItem(String itemString) {
	    for (int i = 0; i < this.getModel().getSize(); i++) {
	      String _item = "" + this.getModel().getElementAt(i);
	      if (_item.equals(itemString)) {
	        return true;
	      }
	    }
	    return false;
	}
	public void setValues(String hql,String hql1,int i)
	{
		 addSCompleter(hql,hql1,i);
	}
}