package erp.ws.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThreadServer {
	 //�˿ں�

    static final int portNo = 3333;

    public static void main(String[] args) throws IOException 
    {

           //�������˵�socket

           ServerSocket s = new ServerSocket(portNo);

           System.out.println("The Server is start: " + s);      

           try 

           {

                  for(;;)                          
                  {

                    //����,ֱ���пͻ�������

                    Socket socket = s.accept();

                    //ͨ�����캯���������߳�

                     new ServerThreadCode(socket);

                  }

           }

        finally 

           {

                  s.close();

           }

    }
}
