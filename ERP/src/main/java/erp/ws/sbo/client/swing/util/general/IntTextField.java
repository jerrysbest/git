package erp.ws.sbo.client.swing.util.general;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntTextField extends JTextField {
	  /**
	 * 
	 */
	private static final long serialVersionUID = -4982764099398385191L;
	public IntTextField(int defval, int size) {
	    super("" + defval, size);
	  }
	  protected Document createDefaultModel() {
	    return new IntTextDocument();
	  }
	  public boolean isValid() {
		  try {
			  getText();
		   } catch (NullPointerException e) {
		      return false;
		    }
	    try {

	      Double.parseDouble(getText());
	      return true;
	    } catch (NumberFormatException e) {
	      return false;
	    }
	  }
	  public Double getValue() {
	    try {
	      return Double.parseDouble(getText());
	    } catch (NumberFormatException e) {
	      return Double.valueOf(0);
	    }
	  }
	  class IntTextDocument extends PlainDocument {
		
		    /**
		 * 
		 */
		private static final long serialVersionUID = 1958813762203249778L;

			public void insertString(int offs, String str, AttributeSet a)
		        throws BadLocationException {
		      if (str == null)
		        return;
		      String oldString = getText(0, getLength());
		      String newString = oldString.substring(0, offs) + str
		          + oldString.substring(offs);
		      try {
		    	  Double.parseDouble(newString + "0");		    
		        //向Document中插入文本前判断
		        super.insertString(offs, str, a);
		      } catch (NumberFormatException e) {
		      }
		    }
     }
}
