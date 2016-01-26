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
     * 动态创建一个bean配置文件，包含HelloWorld这个bean，并添加初始值    
     * */    
    public void createXML(String xmlPath, String msg) throws IOException {     
        Document XmlDoc = DocumentHelper.createDocument();     
        XmlDoc.addDocType("beans", "-//SPRING//DTD BEAN//EN",     
                "http://www.springframework.org/dtd/spring-beans.dtd");     
        //首先创建beans根节点     
       // Element beansEle = XmlDoc.addElement("beans");     
             
        //注意：dom4j是支持类似于jQuery一样的链式操作的     
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
     * 首先遍历一个bean配置文件里的所有bean，获取id和class的值， 然后修改HelloWorld这个bean的msg的值    
     * @throws IOException     
     * */    
    public void editXML(String xmlPath, String[] msg) throws DocumentException, IOException {     
        Document XmlDoc = new SAXReader().read(new File(xmlPath)); 
        Element root = XmlDoc.getRootElement();      
        /*    System.out.println("\r\n遍历所有的bean获得id和class：");       
        for ( Iterator i = root.elementIterator(); i.hasNext();) {
		       Element foo = (Element) i.next();
		       System.out.println("id:" + foo.attributeValue("id")     
	                      + " / class:" + foo.attributeValue("class"));     
		       // do something
		    }
        System.out.println("\r\n动态修改HelloWorld这个bean的msg值：");     
    
//用XPath来获取单一节点     
    Node valueLserver = XmlDoc     
                .selectSingleNode("/beans/bean[@id='appconfig']/property[@name='lserver']/value");     
        System.out.println("原始值为：" + valueLserver.getText());     
        valueLserver.setText(msg[0]);     
        System.out.println("修改后的值为：" + valueLserver.getText());  
        Node valueDserver = XmlDoc     
                .selectSingleNode("/beans/bean[@id='appconfig']/property[@name='dserver']/value");     
        System.out.println("原始值为：" + valueDserver.getText());     
        valueDserver.setText(msg[1]);     
        System.out.println("修改后的值为：" + valueDserver.getText());   
        
        Node valueComdb = XmlDoc     
                .selectSingleNode("/beans/bean[@id='appconfig']/property[@name='comdb']/value");     
        System.out.println("原始值为：" + valueComdb.getText());     
        valueComdb.setText(msg[2]);     
        System.out.println("修改后的值为：" + valueComdb.getText()); */
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
       //修改完了以后记得保存，要不然你会纳闷为什么XML文件没变的，哈哈     
        XMLWriter outXml = new XMLWriter(new FileWriter(new File(xmlPath)));     
        outXml.write(XmlDoc);     
        outXml.close();     
    }   
}
