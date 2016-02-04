package erp.ws.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadCode extends Thread{
	 //�ͻ��˵�socket

    private Socket clientSocket;
   
    //IO���

    private BufferedReader sin;

    private PrintWriter sout;    

    //Ĭ�ϵĹ��캯��

    public ServerThreadCode()

    {}  

    public ServerThreadCode(Socket s) throws IOException 

    {

           clientSocket = s;            

           //��ʼ��sin��sout�ľ��

           sin = new BufferedReader(new InputStreamReader(clientSocket

                         .getInputStream()));

     sout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);             

           //�����߳�

           start(); 

    }

    //�߳�ִ�е����庯��

    public void run() 

    {

           try 

           {

                  //��ѭ��������ͨѶ����

                  for(;;) 

                  {

             String str = sin.readLine();

                         //������յ�����byebye���˳�����ͨѶ

                         if (str.equals("byebye"))

                         {     

                                break;

                         }     

                         System.out.println("In Server reveived the info: " + str);

                         sout.println(str);

                  }

                  System.out.println("closing the server socket!");

           } 

     catch (IOException e) 

           {

                  e.printStackTrace();

           } 

           finally 

           {

                  System.out.println("close the Server socket and the io.");

                  try 

         {

                         clientSocket.close();

                  } 

                  catch (IOException e) 

                  {

                         e.printStackTrace();

                  }

           }

    }
}
