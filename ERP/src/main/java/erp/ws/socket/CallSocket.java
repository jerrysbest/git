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
	          //判断与本客户端是否一致
	            new Thread(this).start();    
	        }    
	       
	        public void run() {    
	            try { 
	            	
	                // 向客户端回复信息    
	                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    

	                out.writeUTF("R");    
	             // 读取客户端数据    
	               
	            	DataInputStream input = new DataInputStream(socket.getInputStream());  
	                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	            	byte[] bty=new byte[100];
	            	int len=input.read(bty);
	            	String clientInputStr=new String(bty,0,len);
	            	System.out.println(df.format(new Date())+"客户端发过来的内容:" + clientInputStr); 
	            	if(clientInputStr.indexOf("wn")!=-1)
	            	{
	            		ret=clientInputStr.substring(2, clientInputStr.length()-2);
	            	}
	               
	                
	            } catch (Exception e) {    
	                System.out.println("服务器 run 异常: " + e.getMessage());    
	            } finally {    
	                if (socket != null) {    
	                    try {    
	                        socket.close();    
	                    } catch (Exception e) {    
	                        socket = null;    
	                        System.out.println("服务端 finally 异常:" + e.getMessage());    
	                    }    
	                }    
	            }   
	        }    
	    
}
