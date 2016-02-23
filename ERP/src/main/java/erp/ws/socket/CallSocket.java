package erp.ws.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallSocket implements Runnable{
	
	  
	        private Socket socket;    
	        private String ret;
	        public CallSocket(Socket client) {    
	            socket = client;  
	          //�ж��뱾�ͻ����Ƿ�һ��
	            new Thread(this).start();    
	        }    
	       
	        public void run() {    
	            try { 
	            	
	                // ��ͻ��˻ظ���Ϣ    
	                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    

	                out.writeUTF("R");    
	             // ��ȡ�ͻ�������    
	               
	            	DataInputStream input = new DataInputStream(socket.getInputStream());  
	                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	            	byte[] bty=new byte[100];
	            	int len=input.read(bty);
	            	String clientInputStr=new String(bty,0,len);
	            	System.out.println(df.format(new Date())+"�ͻ��˷�����������:" + clientInputStr); 
	            	if(clientInputStr.indexOf("wn")!=-1)
	            	{
	            		ret=clientInputStr.substring(2, clientInputStr.length()-2);
	            	}
	               
	                
	            } catch (Exception e) {    
	                System.out.println("������ run �쳣: " + e.getMessage());    
	            } finally {    
	                if (socket != null) {    
	                    try {    
	                        socket.close();    
	                    } catch (Exception e) {    
	                        socket = null;    
	                        System.out.println("����� finally �쳣:" + e.getMessage());    
	                    }    
	                }    
	            }   
	        }    
	    
}
