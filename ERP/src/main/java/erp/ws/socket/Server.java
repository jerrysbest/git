package erp.ws.socket;
/**
 * SOCKET��̱�����TCP server��TCP client��������ڷ�����ѡ��tcp clientģʽ����ôjava�������TCP SERVER����֮��Ȼ
 */ 
import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.net.ServerSocket;  
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	public static final int PORT = 4197;//�����Ķ˿ں�     
	  
    
    public static void main(String[] args) {    
        System.out.println("����������...\n");    
        Server server = new Server();    
        server.init();    
    }    
    
    public void init() {    
        try {    
            ServerSocket serverSocket = new ServerSocket(PORT);      
            System.out.println("����������");
            while (true) {   
            	// һ���ж���, ���ʾ��������ͻ��˻��������    
                Socket client = serverSocket.accept();                   
                System.out.println("��������ͻ��˻������"+client.getInetAddress()); 
                new HandlerThread(client);    
            }    
        } catch (Exception e) {    
            System.out.println("�������쳣: " + e.getMessage());    
        }    
    }    
    
    private class HandlerThread implements Runnable {    
        private Socket socket;    
        public HandlerThread(Socket client) {    
            socket = client;  
          //�ж��뱾�ͻ����Ƿ�һ��
            new Thread(this).start();    
        }    
    
        public void run() {    
            try { 
            	System.out.println("�ͻ��˵�ַ"+socket.getInetAddress()); 
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
                out.close();    
                input.close();    
                
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
}
