package erp.ws.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPServerThread extends Thread{
	 Socket client;
	 volatile static int num = 0;

	 public TCPServerThread(Socket c) {
		  this.client = c;
		  System.out.println(client.getInetAddress().getHostAddress() + ":"+ client.getPort());
	 }
	 @Override
	 public void run() {
	  int i = ++num;
	  System.out.println("Thread " + i + " is starting");
	  try {
	   DataInputStream in = new DataInputStream(client.getInputStream());
	   DataOutputStream out = new DataOutputStream(client.getOutputStream());
	   BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
	   // Mutil User but can parallel
	   while (true) {
	    if (in.available() > 0) {
		     String str = in.readUTF();
		     System.out.println(str);
		     out.writeUTF(str + " has receive....");
		     out.flush();
		     if (str.equals("end") || (null == str)) {
		      break;
		     }
	    } else {
	     if (wt.ready()) {
		      String str = wt.readLine();
		      out.writeUTF(str);
		      out.flush();
	     }else{
		      try {
		       client.sendUrgentData(0xFF);
		       Thread.sleep(100);
		      } catch (Exception ex) {
		       ex.printStackTrace();
		       break;
		      }
	     }
	    }
	   }
	   client.close();
	  } catch (Exception ex) {
	   ex.printStackTrace();
	  } finally {
	   System.out.println("Thread " + i++ + " is ending");
	  }
	 }
}
