package erp.ws.sbo.client.swing.store;

public class Tree1s {
	
	private Object[] value =
	      {  "",
			 new Object[] { "����"},
			 new Object[] { "���ۻ���"},	       
	         new Object[] { "����-Ӧ���˿�",
	    		           "���۶���",
	                       "������",
	                       "Ӧ�մ���ƾ֤",
	                       "����������"
	                   },        
            new Object[] { "���",
			    		  "���к����",
			    		  "��������",
			    		  "�������",
			    		  "���к����"},  
           new Object[] { "����",
	    		       "��������",	
		               "��������",
		               "�����ջ�"		             
                  }, 
          
          new Object[] { 
	    		  "��������ƻ�",
	               "����Ԥ�ⶩ��",
	               "��������ƻ���",
	               "���������ʽһ",  
	               "���������ʽ��",
	               "���������ʽ��",
	               "�����������б�"
                 },   
          
	        new Object[] {  "����",
	    		         "�����տ"	                       
	       },
	      
          new Object[] { "����", 
	    		  new Object[] {"����",
        		  "��Ŀ�������"
                   },
	               new Object[] {"����",
        		  "�����ձ���",
        		  "���ڷ�����"
                   },
                   new Object[] {"����",
                   "�����ձ���",
                   "��������������"
                   },
	               new Object[] {"���",
        		  "��������ϸ",
        		  "�ֿ������",
        		  "���кŸ�����ϸ",
        		  "���к�״̬"}                
                 }
	       };
	private static Tree1s t1s;
	private Tree1s()
	{
		
	}
	public static  Tree1s getHierarchy(){
	if(t1s==null)
	{
		t1s=new  Tree1s();
	}
	return t1s;
	}
	public Object[] getValue()
	{
		return value;
	}
	public void setValue(Object[] value)
	{
	 this.value=value;
	}

}
