package erp.ws.sbo.utils;
/* @(#)SerialConnection.java	1.6 98/07/17 SMI
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license
 * to use, modify and redistribute this software in source and binary
 * code form, provided that i) this copyright notice and license appear
 * on all copies of the software; and ii) Licensee does not utilize the
 * software in a manner which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind.
 * ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THE
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS
 * BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING
 * OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control
 * of aircraft, air traffic, aircraft navigation or aircraft
 * communications; or in the design, construction, operation or
 * maintenance of any nuclear facility. Licensee represents and
 * warrants that it will not use or redistribute the Software for such
 * purposes.
 */

import javax.comm.*;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import erp.ws.sbo.client.swing.view.Oign.OignView;
import erp.ws.sbo.client.swing.view.Snin.SninView;

import java.io.*;
import java.math.BigDecimal;
import java.awt.event.*;
import java.util.TooManyListenersException;

/**
A class that handles the details of a serial connection. Reads from one 
TextArea and writes to a second TextArea. 
Holds the state of the connection.
*/
public class SerialConnection implements SerialPortEventListener, 
					 CommPortOwnershipListener {
    private JInternalFrame parent;

    private JTextArea messageAreaOut;
    private JTextArea messageAreaIn;
    private JTextField w;
    private JTextField pw;
    private JTextField dv;
    private SerialParameters parameters;
    private OutputStream os;
    private InputStream is;
    private KeyHandler keyHandler;
    private SninView v1;
    private OignView v2;
    private String view="";

   

    private CommPortIdentifier portId;
    private SerialPort sPort;

    private boolean open;
    /**
    Creates a SerialConnection object and initilizes variables passed in
    as params.

    @param parent A SerialDemo object.
    @param parameters A SerialParameters object.
    @param messageAreaOut The TextArea that messages that are to be sent out
    of the serial port are entered into.
    @param messageAreaIn The TextArea that messages comming into the serial
    port are displayed on.
    */
    public SerialConnection(JInternalFrame parent,
			    SerialParameters parameters,
			    JTextArea messageAreaOut,
			    JTextArea messageAreaIn,
			    JTextField w,
			    JTextField pw,
			    JTextField dv,
			    SninView v1) {
	this.setParent(parent);
	this.parameters = parameters;
	this.messageAreaOut = messageAreaOut;
	this.messageAreaIn = messageAreaIn;
	this.w=w;
	this.pw=pw;
	this.dv=dv;
	this.v1=v1;
	this.view="SninView";
	open = false;
   }
    
    public SerialConnection(JInternalFrame parent,
		    SerialParameters parameters,
		    JTextArea messageAreaOut,
		    JTextArea messageAreaIn,
		    JTextField w,
		    JTextField pw,
		    JTextField dv,
		    OignView v2) {
	this.setParent(parent);
	this.parameters = parameters;
	this.messageAreaOut = messageAreaOut;
	this.messageAreaIn = messageAreaIn;
	this.w=w;
	this.pw=pw;
	this.dv=dv;
	this.v2=v2;
	this.view="OignView";
	open = false;
	}
  

   /**
   Attempts to open a serial connection and streams using the parameters
   in the SerialParameters object. If it is unsuccesfull at any step it
   returns the port to a closed state, throws a 
   <code>SerialConnectionException</code>, and returns.

   Gives a timeout of 30 seconds on the portOpen to allow other applications
   to reliquish the port if have it open and no longer need it.
   */
   public void openConnection(String com) throws SerialConnectionException {

	// Obtain a CommPortIdentifier object for the port you want to open.
	try {
	    portId = 
		 CommPortIdentifier.getPortIdentifier(com);
	} catch (NoSuchPortException e) {
	    throw new SerialConnectionException(e.getMessage());
	}
	closeConnection();
	// Open the port represented by the CommPortIdentifier object. Give
	// the open call a relatively long timeout of 30 seconds to allow
	// a different application to reliquish the port if the user 
	// wants to.
	try {
	    sPort = (SerialPort)portId.open(com, 30000);
	} catch (PortInUseException e) {
	    throw new SerialConnectionException(e.getMessage());
	}

	// Set the parameters of the connection. If they won't set, close the
	// port before throwing an exception.
	try {
	    setConnectionParameters();
	} catch (SerialConnectionException e) {	
	    sPort.close();
	    throw e;
	}

	// Open the input and output streams for the connection. If they won't
	// open, close the port before throwing an exception.
	try {
	    os = sPort.getOutputStream();
	    is = sPort.getInputStream();
	} catch (IOException e) {
	    sPort.close();
	    throw new SerialConnectionException("Error opening i/o streams");
	}

	// Create a new KeyHandler to respond to key strokes in the 
	// messageAreaOut. Add the KeyHandler as a keyListener to the 
	// messageAreaOut.
	keyHandler = new KeyHandler(os);
	messageAreaOut.addKeyListener(keyHandler);

	// Add this object as an event listener for the serial port.
	try {
	    sPort.addEventListener(this);
	} catch (TooManyListenersException e) {
	    sPort.close();
	    throw new SerialConnectionException("too many listeners added");
	}

	// Set notifyOnDataAvailable to true to allow event driven input.
	sPort.notifyOnDataAvailable(true);

	// Set notifyOnBreakInterrup to allow event driven break handling.
	sPort.notifyOnBreakInterrupt(true);

	// Set receive timeout to allow breaking out of polling loop during
	// input handling.
	try {
	    sPort.enableReceiveTimeout(30);
	} catch (UnsupportedCommOperationException e) {
	}

	// Add ownership listener to allow ownership event handling.
	portId.addPortOwnershipListener(this);

	open = true;
    }

    /**
    Sets the connection parameters to the setting in the parameters object.
    If set fails return the parameters object to origional settings and
    throw exception.
    */
    public void setConnectionParameters() throws SerialConnectionException {

	// Save state of parameters before trying a set.
	int oldBaudRate = sPort.getBaudRate();
	int oldDatabits = sPort.getDataBits();
	int oldStopbits = sPort.getStopBits();
	int oldParity   = sPort.getParity();

	// Set connection parameters, if set fails return parameters object
	// to original state.
	try {
	    sPort.setSerialPortParams(parameters.getBaudRate(),
				      parameters.getDatabits(),
				      parameters.getStopbits(),
				      parameters.getParity());
	} catch (UnsupportedCommOperationException e) {
	    parameters.setBaudRate(oldBaudRate);
	    parameters.setDatabits(oldDatabits);
	    parameters.setStopbits(oldStopbits);
	    parameters.setParity(oldParity);
	    throw new SerialConnectionException("Unsupported parameter");
	}

	// Set flow control.
	try {
	    sPort.setFlowControlMode(parameters.getFlowControlIn() 
			           | parameters.getFlowControlOut());
	} catch (UnsupportedCommOperationException e) {
	    throw new SerialConnectionException("Unsupported flow control");
	}
    }

    /**
    Close the port and clean up associated elements.
    */
    public void closeConnection() {
	// If port is alread closed just return.
	if (!open) {

	    return;
	}

	// Remove the key listener.
	messageAreaOut.removeKeyListener(keyHandler);

	// Check to make sure sPort has reference to avoid a NPE.
	if (sPort != null) {
	    try {
		// close the i/o streams.
	    	os.close();
	    	is.close();
	    } catch (IOException e) {
		System.err.println(e);
	    }

	    // Close the port.
	    sPort.close();
	    // Remove the ownership listener.
	    portId.removePortOwnershipListener(this);
	}

	open = false;
    }

    public void opencloseConnection() {
    	

    		// Remove the key listener.
    		messageAreaOut.removeKeyListener(keyHandler);

    		// Check to make sure sPort has reference to avoid a NPE.

    		    try {
    			// close the i/o streams.
    		    	os.close();
    		    	is.close();
    		    } catch (IOException e) {
    			System.err.println(e);
    		    }

    		    // Close the port.
    		    sPort.close();
    		    // Remove the ownership listener.
    		    portId.removePortOwnershipListener(this);
    		

    		open = false;
    }
    /**
    Send a one second break signal.
    */
    public void sendBreak() {
	sPort.sendBreak(1000);
    }

    /**
    Reports the open status of the port.
    @return true if port is open, false if port is closed.
    */
    public boolean isOpen() {
	return open;
    }

    /**
    Handles SerialPortEvents. The two types of SerialPortEvents that this
    program is registered to listen for are DATA_AVAILABLE and BI. During 
    DATA_AVAILABLE the port buffer is read until it is drained, when no more
    data is availble and 30ms has passed the method returns. When a BI
    event occurs the words BREAK RECEIVED are written to the messageAreaIn.
    */

    public void serialEvent(SerialPortEvent e) {
 	// Create a StringBuffer and int to receive input data.
	StringBuffer inputBuffer = new StringBuffer();
	int newData = 0;

	// Determine type of event.
	switch (e.getEventType()) {

	    // Read data until -1 is returned. If \r is received substitute
	    // \n for correct newline handling.
	    case SerialPortEvent.DATA_AVAILABLE:
		    while (newData != -1) {
		    	try {
		    	    newData = is.read();
				    if (newData == -1) {
					break;
				    }
				    if ((char)newData!='w'&&(char)newData!='n'&&(char)newData!='k'&&(char)newData!='g'&&(char)newData!='\r'&&(char)newData!='\n') {
				   	
				    	inputBuffer.append((char)newData);
				    }
		    	} catch (IOException ex) {
		    	    System.err.println(ex);
		    	    return;
		      	}
   		    }

		// Append received data to messageAreaIn.
	    if(messageAreaOut.getText().equals("R"))
	    { 	     
		   w.setText(w.getText()+new String(inputBuffer));
		   if(view.equals("SninView"))
		   {
			   v1.getTxt_cweight().setText(String.valueOf(new BigDecimal(v1.getTxt_weight().getText()==null?"0":v1.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v1.getTxt_pweight().getText()==null?"0":v1.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
			   if(new BigDecimal(v1.getTxt_length().getText()==null?"0":v1.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
			   {
				  v1.getTxt_sweight().setText(v1.getTxt_cweight().getText());			  
			   }			   
			   dv.setText(String.valueOf(new BigDecimal(Double.valueOf(v1.getTxt_sweight().getText()==null?"0":v1.getTxt_sweight().getText())-Double.valueOf(v1.getTxt_cweight().getText()==null?"0":v1.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 		  
		   }
		   else if(view.equals("OignView"))
		   {
			   v2.getTxt_cweight().setText(String.valueOf(new BigDecimal(v2.getTxt_weight().getText()==null?"0":v2.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v2.getTxt_pweight().getText()==null?"0":v2.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
			   if(new BigDecimal(v2.getTxt_length().getText()==null?"0":v2.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
			   {
				  v2.getTxt_sweight().setText(v2.getTxt_cweight().getText());			  
			   }			   
			   dv.setText(String.valueOf(new BigDecimal(Double.valueOf(v2.getTxt_sweight().getText()==null?"0":v2.getTxt_sweight().getText())-Double.valueOf(v2.getTxt_cweight().getText()==null?"0":v2.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 		  
		  
		   }
		 }
	    else{
		  pw.setText(pw.getText()+new String(inputBuffer));	
		  if(view.equals("SninView"))
		  {
			  v1.getTxt_weight().setText(String.valueOf(new BigDecimal(v1.getTxt_cweight().getText()==null?"0":v1.getTxt_cweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(v1.getTxt_pweight().getText()==null?"0":v1.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
			  if(new BigDecimal(v1.getTxt_length().getText()==null?"0":v1.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
			  {
				 v1.getTxt_sweight().setText(v1.getTxt_cweight().getText());			  
			  }	
			  dv.setText(String.valueOf(new BigDecimal(Double.valueOf(v1.getTxt_sweight().getText())-Double.valueOf(v1.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 
		   }
		  else if(view.equals("OignView")){
			  v2.getTxt_weight().setText(String.valueOf(new BigDecimal(v2.getTxt_cweight().getText()==null?"0":v2.getTxt_cweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(v2.getTxt_pweight().getText()==null?"0":v2.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
			  if(new BigDecimal(v2.getTxt_length().getText()==null?"0":v2.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
			  {
				 v2.getTxt_sweight().setText(v2.getTxt_cweight().getText()==null?"0":v2.getTxt_cweight().getText());			  
			  }	
			  dv.setText(String.valueOf(new BigDecimal(Double.valueOf(v2.getTxt_sweight().getText()==null?"0":v2.getTxt_sweight().getText())-Double.valueOf(v2.getTxt_cweight().getText()==null?"0":v2.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 		
		  }
		}
	      
	    messageAreaIn.append(new String(inputBuffer));
		break;

	    // If break event append BREAK RECEIVED message.
	    case SerialPortEvent.BI:
	    	//messageAreaIn.setText("");
		messageAreaIn.append("\n--- BREAK RECEIVED ---\n");
	}

    }   
    public void serialEvent1(SerialPortEvent e) {
     	// Create a StringBuffer and int to receive input data.
    	StringBuffer inputBuffer = new StringBuffer();
    	int newData = 0;

    	// Determine type of event.
    	switch (e.getEventType()) {

    	    // Read data until -1 is returned. If \r is received substitute
    	    // \n for correct newline handling.
    	    case SerialPortEvent.DATA_AVAILABLE:
    		    while (newData != -1) {
    		    	try {
    		    	    newData = is.read();
    				    if (newData == -1) {
    					break;
    				    }
    				    if ((char)newData!='w'&&(char)newData!='n'&&(char)newData!='k'&&(char)newData!='g'&&(char)newData!='\r'&&(char)newData!='\n') {
    				   	
    				    	inputBuffer.append((char)newData);
    				    }
    		    	} catch (IOException ex) {
    		    	    System.err.println(ex);
    		    	    return;
    		      	}
       		    }

    		// Append received data to messageAreaIn.
    	    if(messageAreaOut.getText().equals("R"))
    	    {			  
    		   w.setText(w.getText()+new String(inputBuffer));
    		   v1.getTxt_cweight().setText(String.valueOf(new BigDecimal(v1.getTxt_weight().getText()==null?"0":v1.getTxt_weight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(v1.getTxt_pweight().getText()==null?"0":v1.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
    		   if(new BigDecimal(v1.getTxt_length().getText()==null?"0":v1.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
    		   {
    			  v1.getTxt_sweight().setText(v1.getTxt_cweight().getText());			  
    		   }		  
    		   dv.setText(String.valueOf(new BigDecimal(Double.valueOf(v1.getTxt_sweight().getText()==null?"0":v1.getTxt_sweight().getText())-Double.valueOf(v1.getTxt_cweight().getText()==null?"0":v1.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 		  
    		}
    	    else{
    		  pw.setText(pw.getText()+new String(inputBuffer));			
    		  v1.getTxt_weight().setText(String.valueOf(new BigDecimal(v1.getTxt_cweight().getText()==null?"0":v1.getTxt_cweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(v1.getTxt_pweight().getText()==null?"0":v1.getTxt_pweight().getText()).setScale(3,BigDecimal.ROUND_HALF_UP))));    	
    		  if(new BigDecimal(v1.getTxt_length().getText()==null?"0":v1.getTxt_length().getText()).setScale(3, BigDecimal.ROUND_HALF_UP).equals(new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP)))
    		  {
    			 v1.getTxt_sweight().setText(v1.getTxt_cweight().getText());			  
    		  }	
    		  dv.setText(String.valueOf(new BigDecimal(Double.valueOf(v1.getTxt_sweight().getText()==null?"0":v1.getTxt_sweight().getText())-Double.valueOf(v1.getTxt_cweight().getText()==null?"0":v1.getTxt_cweight().getText())).setScale(3, BigDecimal.ROUND_HALF_UP)));			 
    	    }
    	      
    	    messageAreaIn.append(new String(inputBuffer));
    		break;

    	    // If break event append BREAK RECEIVED message.
    	    case SerialPortEvent.BI:
    	    	//messageAreaIn.setText("");
    		messageAreaIn.append("\n--- BREAK RECEIVED ---\n");
    	}

        } 
    /**
    Handles ownership events. If a PORT_OWNERSHIP_REQUESTED event is
    received a dialog box is created asking the user if they are 
    willing to give up the port. No action is taken on other types
    of ownership events.
    */
    public void ownershipChange(int type) {
	if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
	    //PortRequestedDialog prd = new PortRequestedDialog(parent);
	}
    }

    public JInternalFrame getParent() {
		return parent;
	}

	public void setParent(JInternalFrame parent) {
		this.parent = parent;
	}

	/**
    A class to handle <code>KeyEvent</code>s generated by the messageAreaOut.
    When a <code>KeyEvent</code> occurs the <code>char</code> that is 
    generated by the event is read, converted to an <code>int</code> and 
    writen to the <code>OutputStream</code> for the port.
    */
    class KeyHandler extends KeyAdapter {
	OutputStream os;

	/**
	Creates the KeyHandler.
	@param os The OutputStream for the port.
	*/
	public KeyHandler(OutputStream os) {
	    super();
	    this.os = os;
	}

	/**
	Handles the KeyEvent.
	Gets the <code>char</char> generated by the <code>KeyEvent</code>,
	converts it to an <code>int</code>, writes it to the <code>
	OutputStream</code> for the port.
	*/
    public void keyTyped(KeyEvent evt) {
        char newCharacter = evt.getKeyChar();
        try {
    	  os.write((int)newCharacter);
        } catch (IOException e) {
	    System.err.println("OutputStream write error: " + e);
        }
     }
    }

	public SerialPort getsPort() {
		return sPort;
	}

	public void setsPort(SerialPort sPort) {
		this.sPort = sPort;
	}

	public JTextField getW() {
		return w;
	}

	public void setW(JTextField w) {
		this.w = w;
	}

	public JTextField getPw() {
		return pw;
	}

	public void setPw(JTextField pw) {
		this.pw = pw;
	}

	public JTextField getDv() {
		return dv;
	}

	public void setDv(JTextField dv) {
		this.dv = dv;
	}

	public SninView getV1() {
		return v1;
	}

	public void setV1(SninView v1) {
		this.v1 = v1;
	}

	public OignView getV2() {
		return v2;
	}

	public void setV2(OignView v2) {
		this.v2 = v2;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}


}
