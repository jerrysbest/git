package erp.ws.sbo.utils;

import com.sap.smb.sbo.api.IDocuments;

public class Queue {
	
	    private int maxSize; //���г��ȣ��ɹ��캯����ʼ��
	    private IDocuments[] queArray; // ����
	    private int front; //��ͷ
	    private int rear; //��β
	    private int nItems;  //Ԫ�صĸ���
	//--------------------------------------------------------------
	    public Queue(int s)           // ���캯��
       {
	       maxSize = s;
	       queArray = new IDocuments[maxSize];
	       front = 0;
	       rear = -1;
	       nItems = 0;
       }
	//--------------------------------------------------------------
	    public void insert(IDocuments j)    // ������
       {
	       if(rear == maxSize-1)          // ����ѭ��
	          rear = -1;
	       queArray[++rear] = j;          // ��βָ���1,��ֵj�����β
	       nItems++;                   
       }
	//--------------------------------------------------------------
	    public IDocuments remove()          // ȡ�ö��еĶ�ͷԪ�ء�
       {
	    	IDocuments temp = queArray[front++]; // ȡֵ���޸Ķ�ͷָ��
	        if(front == maxSize)            // ����ѭ��
	          front = 0;
	        nItems--;                     
	        return temp;
       }
	//--------------------------------------------------------------
	    public IDocuments peekFront()       // ȡ�ö��еĶ�ͷԪ�ء��������� remove()��ͬ������Ҫ�޸Ķ�ͷԪ��ָ�롣
	    {
	       return queArray[front];
	    }
	//--------------------------------------------------------------
	    public boolean isEmpty()     // �ж����Ƿ�Ϊ�ա���Ϊ�շ���һ����ֵ�����򷵻�һ����ֵ��
	    {
	       return (nItems==0);
	    }
	//--------------------------------------------------------------
	    public boolean isFull()      // �ж����Ƿ�����������������һ����ֵ�����򷵻�һ����ֵ��
	    {
	       return (nItems==maxSize);
	    }
	//--------------------------------------------------------------
	    public int size()            // ���ض��еĳ���
	    {
	       return nItems;
	    }
	//-------------------------------------------------------------- 
}
