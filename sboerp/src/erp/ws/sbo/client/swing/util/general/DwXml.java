package erp.ws.sbo.client.swing.util.general;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class DwXml {
	/**    
     * ��̬����һ��bean�����ļ�������HelloWorld���bean������ӳ�ʼֵ    
     * */    
    public void createXML(String xmlPath, String msg) throws IOException {     
        Document XmlDoc = DocumentHelper.createDocument();     
        XmlDoc.addDocType("beans", "-//SPRING//DTD BEAN//EN",     
                "http://www.springframework.org/dtd/spring-beans.dtd");     
        //���ȴ���beans���ڵ�     
       // Element beansEle = XmlDoc.addElement("beans");     
             
        //ע�⣺dom4j��֧��������jQueryһ������ʽ������     
       // Element beanHelloWorld = beansEle.addElement("bean")     
         //       .addAttribute("id", "HelloWorld")     
           //     .addAttribute("class", "com.iteye.bolide74.action.HelloWorld");     
       // Element propertyHelloWorld = beanHelloWorld.addElement("property")     
         //       .addAttribute("name", "msg");     
       // Element valueHelloWorld = propertyHelloWorld.addElement("value")     
         //       .addText(msg);     
        XMLWriter outXml = new XMLWriter(new FileWriter(new File(xmlPath)));     
        outXml.write(XmlDoc);     
        outXml.close();     
    }     
    
    /**    
     * ���ȱ���һ��bean�����ļ��������bean����ȡid��class��ֵ�� Ȼ���޸�HelloWorld���bean��msg��ֵ    
     * @throws IOException     
     * */    
    public void editXML(String xmlPath, String[] msg) throws DocumentException, IOException {     
        Document XmlDoc = new SAXReader().read(new File(xmlPath)); 
        Element root = XmlDoc.getRootElement();      
        /*    System.out.println("\r\n�������е�bean���id��class��");       
        for ( Iterator i = root.elementIterator(); i.hasNext();) {
		       Element foo = (Element) i.next();
		       System.out.println("id:" + foo.attributeValue("id")     
	                      + " / class:" + foo.attributeValue("class"));     
		       // do something
		    }
        System.out.println("\r\n��̬�޸�HelloWorld���bean��msgֵ��");     
    
//��XPath����ȡ��һ�ڵ�     
    Node valueLserver = XmlDoc     
                .selectSingleNode("/beans/bean[@id='appconfig']/property[@name='lserver']/value");     
        System.out.println("ԭʼֵΪ��" + valueLserver.getText());     
        valueLserver.setText(msg[0]);     
        System.out.println("�޸ĺ��ֵΪ��" + valueLserver.getText());  
        Node valueDserver = XmlDoc     
                .selectSingleNode("/beans/bean[@id='appconfig']/property[@name='dserver']/value");     
        System.out.println("ԭʼֵΪ��" + valueDserver.getText());     
        valueDserver.setText(msg[1]);     
        System.out.println("�޸ĺ��ֵΪ��" + valueDserver.getText());   
        
        Node valueComdb = XmlDoc     
                .selectSingleNode("/beans/bean[@id='appconfig']/property[@name='comdb']/value");     
        System.out.println("ԭʼֵΪ��" + valueComdb.getText());     
        valueComdb.setText(msg[2]);     
        System.out.println("�޸ĺ��ֵΪ��" + valueComdb.getText()); */
        for (Iterator<?> i = root.elementIterator(); i.hasNext();){  
		       Element foo = (Element) i.next();
		       System.out.println("id:" + foo.attributeValue("id")     
	                      + " / class:" + foo.attributeValue("class")); 
		      if(foo.attributeValue("id")==null)
		      {
		    	  continue;
		      }
		       if(foo.attribute("id").getText().equals("appconfig"))
		       {
		    	   for(Iterator<?> j = foo.elementIterator(); j.hasNext();)
		    	   {Element foo1 = (Element) j.next();
		    		  
		    		   if(foo1.attributeValue("name").equals("lserver"))
		    		   {
		    		   foo1.attribute("value").setText(msg[0]);
		    		   }
		    		   else if(foo1.attributeValue("name").equals("dserver"))
		    		   {
		    		   foo1.attribute("value").setText(msg[1]);
		    		   }
		    		   else if(foo1.attributeValue("name").equals("comdb"))
		    		   {
		    		   foo1.attribute("value").setText(msg[2]);
		    		   }
		    		   else
		    		   {
		    		   
		    		   }
		    	   }
		       }
		    }
       //�޸������Ժ�ǵñ��棬Ҫ��Ȼ�������ΪʲôXML�ļ�û��ģ�����     
        XMLWriter outXml = new XMLWriter(new FileWriter(new File(xmlPath)));     
        outXml.write(XmlDoc);     
        outXml.close();     
    }   
}
