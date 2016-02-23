package scoket.server;

/**
 * SOCKET编程必须有TCP server和TCP client，如果串口服务器选择tcp client模式，那么java程序就是TCP SERVER，反之亦然
 */ 
import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.net.ServerSocket;  
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server {
	public static final int PORT = 4197;//监听的端口号     
	public static Map<String,Socket> map=new HashMap<String,Socket>();
	public String ipadress,ret;
    
    public static void main(String[] args) {    
        System.out.println("服务器启动...\n");  
        Server server = new Server();    
        server.init();    
    }    
    
    public void init() {    
        try {    
            ServerSocket serverSocket = new ServerSocket(PORT);      
            System.out.println("服务器建立");
            while (true) {   
            	// 一旦有堵塞, 则表示服务器与客户端获得了连接    
                Socket client = serverSocket.accept();                     
                System.out.println("服务器与客户端获得连接"+client.getLocalSocketAddress().toString().replace("/", "")); 
                System.out.println("服务器与客户端获得连接"+client.getRemoteSocketAddress().toString().replace("/", "")); 
                map.put(client.getRemoteSocketAddress().toString().replace("/", ""),client);
                new HandlerThread(client);    
            }    
        } catch (Exception e) {    
            System.out.println("服务器异常: " + e.getMessage());    
        }    
    } 
    //最后三位是id
    private synchronized String GetId(Socket client){
    	return client.getRemoteSocketAddress().toString().replace("/", "").substring(client.getRemoteSocketAddress().toString().replace("/", "").length()-3);
    } 
    //判断是否是erp客户端
    private synchronized boolean IfErpClient(Socket client){
    	if(client.getRemoteSocketAddress().toString().indexOf("127.0.0.1")!=-1)
    	{
    	    return true;
    	}
    	else{
    		return false;
    	}
    }
    private synchronized Socket GetSocket(String ip){
    	return map.get(ip);
    }
    private class HandlerThread implements Runnable {    
        private Socket socket,commsocket;    
        private Object lock=new Object();;
        public HandlerThread(Socket client) {    
            socket = client; 
            new Thread(this).start();    
        }    
       
        public void run() { 
        	synchronized(lock)
            {

	            try { 
	            	System.out.println("客户端地址"+socket.getLocalSocketAddress().toString().replace("/", "")); 
	            	System.out.println("客户端地址"+socket.getRemoteSocketAddress().toString().replace("/", "")); 
	            	if(IfErpClient(socket))
	            	{
	            		// 向客户端回复信息  
	            		System.out.println("127.0.0.1"+socket.getRemoteSocketAddress().toString().substring(socket.getRemoteSocketAddress().toString().indexOf(":")));   
	                	
	            		commsocket=GetSocket("127.0.0.1"+socket.getRemoteSocketAddress().toString().substring(socket.getRemoteSocketAddress().toString().indexOf(":")));
	            		System.out.println("callsocket: " + commsocket.getRemoteSocketAddress().toString());   
	            		DataOutputStream out = new DataOutputStream(commsocket.getOutputStream());    
	
	                    out.writeUTF("R");   
	            	
		               // 读取客户端数据    
		               
		            	DataInputStream input = new DataInputStream(commsocket.getInputStream());  
		                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		            	byte[] bty=new byte[100];
		            	int len=input.read(bty);
		            	String clientInputStr=new String(bty,0,len);
		            	System.out.println(df.format(new Date())+"客户端发过来的内容:" + clientInputStr); 
		            	if(clientInputStr.indexOf("wn")!=-1)
		            	{
		            		ret=clientInputStr.substring(2, clientInputStr.length()-2);
		            		out = new DataOutputStream(socket.getOutputStream());    
		
		                    out.writeUTF(ret);  
		            	}
		            	else{
		            		
		            	
		            	}
	            	}
	                
	            } catch (Exception e) {    
	                System.out.println("服务器 run 异常: " + e.getMessage());    
	            } finally {    
	   
	            }   
            }
        }    
    }  
}
