package scoket.client;

import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;   
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;



public class Client {
	public static final String IP_ADDR = "localhost";//服务器地址   
    public static final int PORT = 4197;//服务器端口号    
      
    public static void main(String[] args) {    
        System.out.println("客户端启动...");    
        System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");   
        Socket socket = null;  
        try {  
            //创建一个流套接字并将其连接到指定主机上的指定端口号  ,erp登陆即占用端口，防止两个人登陆一个电子称
            socket = new Socket();   
            SocketAddress localAddr=new InetSocketAddress("127.0.0.1",12358);
            SocketAddress RemoteAddr=new InetSocketAddress(IP_ADDR, PORT);
            socket.bind(localAddr);
            socket.connect(RemoteAddr);  
            System.out.println("客户端本地IP地址: " + socket.getLocalSocketAddress());    
            System.out.println("客户端远程IP地址: " + socket.getRemoteSocketAddress());   
        }catch (Exception e) {  
            System.out.println("客户端异常:" + e.getMessage());   
        } 
        while (true) {    
           
            try {  
                 
                //读取服务器端数据    
                DataInputStream input = new DataInputStream(socket.getInputStream());    
                //向服务器端发送数据    
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
                System.out.print("请输入: \t");    
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
                out.writeUTF(str);    
                    
                String ret = input.readUTF();     
                System.out.println("服务器端返回过来的是: " + ret);    
                // 如接收到 "OK" 则断开连接    
                if ("OK".equals(ret)) {    
                    System.out.println("客户端将关闭连接");    
                    Thread.sleep(500);    
                    break;    
                }    
                  
//                out.close();  
//                input.close();  
            } catch (Exception e) {  
                System.out.println("客户端异常:" + e.getMessage());   
            } finally {  
//                if (socket != null) {  
//                    try {  
//                        socket.close();  
//                    } catch (IOException e) {  
//                        socket = null;   
//                        System.out.println("客户端 finally 异常:" + e.getMessage());   
//                    }  
//                }  
            }  
        }    
    } 
}