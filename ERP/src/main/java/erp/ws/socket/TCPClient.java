package erp.ws.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	static Socket socket;

	 public static void main(String[] args) throws Exception {
	  if (args.length == 2) {
	   try {
		    Configuration.TCPIP = args[0];
		    Configuration.TCPPort = Integer.parseInt(args[1]);
	   } catch (Exception ex) {
	        System.exit(1);
	   }
	  }
	  socket = new Socket();
	  socket.connect(new InetSocketAddress(Configuration.TCPIP,Configuration.TCPPort), 2000);

	  DataInputStream in = new DataInputStream(socket.getInputStream());
	  DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	  BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

	  while (true) {
	     if (wt.ready()) {
	        String str = wt.readLine();
		    out.writeUTF(str);
		    out.flush();

		    if (str.equals("end")) {
		     break;
		    }

	   } else {
	    try {
	     socket.sendUrgentData(0xFF);
	     Thread.sleep(100);
	    } catch (Exception ex) {
	     ex.printStackTrace();
	     break;
	    }
	   }
	   if (in.available() > 0)
	    System.out.println(in.readUTF());
	  }
	  socket.close();
	 }
}
