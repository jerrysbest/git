package erp.ws.socket;
/**
 * SOCKET编程必须有TCP server和TCP client，如果串口服务器选择tcp client模式，那么java程序就是TCP SERVER，反之亦然
 */ 
import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.net.ServerSocket;  
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	public static final int PORT = 4197;//监听的端口号     
	  
    
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
                System.out.println("服务器与客户端获得连接"+client.getInetAddress()); 
                new HandlerThread(client);    
            }    
        } catch (Exception e) {    
            System.out.println("服务器异常: " + e.getMessage());    
        }    
    }    
    
    private class HandlerThread implements Runnable {    
        private Socket socket;    
        public HandlerThread(Socket client) {    
            socket = client;  
          //判断与本客户端是否一致
            new Thread(this).start();    
        }    
    
        public void run() {    
            try { 
            	System.out.println("客户端地址"+socket.getInetAddress()); 
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
                out.close();    
                input.close();    
                
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
}
