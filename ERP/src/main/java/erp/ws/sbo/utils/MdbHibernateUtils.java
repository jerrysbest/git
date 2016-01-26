package erp.ws.sbo.utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/** 
 * 提供 数据库交互的可复用代码 
 * 
 */  
public class MdbHibernateUtils {
	private static SessionFactory factory;     
    public MdbHibernateUtils() {
    
    }        
    static {  
       /* Configuration cfg = new Configuration().configure();  
        factory = cfg.buildSessionFactory();  */
    	//读取配置文件
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/config/applicationContext.xml");
		//获取对象
	    factory = (SessionFactory) ctx.getBean("sessionFactory");
    }  
      
    public static SessionFactory getSessionFactory() {  
        return factory;  
    }  
      
    public static Session getSession() {  
        return factory.openSession();  
    }  
      
    public static void closeSession(Session session) {  
        if (session != null) {  
            if (session.isOpen()) {  
                session.close();  
            }  
        }  
    }  
      
    public static interface ActionInSession {  
        Object doSomething(Session session);  
    }  
      
    public static Object templatedExec(ActionInSession ais)  
    {  
        Session session = null;  
        try{  
            session = getSession();  
            session.beginTransaction();  
            Object obj = ais.doSomething(session);  
            session.getTransaction().commit();  
            return obj;  
        } catch (Exception e) {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        } finally {  
           closeSession(session);  
        }  
        return null;  
    }  
      
    public static void save(final Object obj){  
        templatedExec(new ActionInSession() {  
  
            @Override  
            public Object doSomething(Session session) {  
                session.save(obj);  
                return null;  
            }  
              
        });  
    }  
      
      
    public static void deleteById(final Class<?> c, final long id)  
    {  
        templatedExec(new ActionInSession() {  
  
            @Override  
            public Object doSomething(Session session) {  
                Object obj = session.load(c, id);  
                session.delete(obj);  
                return null;  
            }  
              
        });  
    }  
      
    public static void delete(final Object obj)  
    {  
        templatedExec(new ActionInSession() {  
  
            @Override  
            public Object doSomething(Session session) {  
                session.delete(obj);  
                return null;      
            }  
              
        });  
    }  
      
    public static Object findById(final Class<?> c, final long id)  
    {  
        return templatedExec(new ActionInSession(){  
  
            @Override  
            public Object doSomething(Session session) {  
                return session.get(c, id);  
            }  
              
        });  
    }  
      
    public static Object findByCondition(final String hql, final String param1, final String param2)  
    {  
        return templatedExec(new ActionInSession() {  
  
            @Override  
            public Object doSomething(Session session) {  
                return session.createQuery(hql)  
                        .setParameter(0, param1)  
                        .setParameter(1, param2)  
                        .uniqueResult();  
            }  
              
        }) ;  
    }  
      
      
    public  List<?> findAll(final String hql)  
    {  
        return (List<?>) templatedExec(new ActionInSession() {  
  
            @Override  
            public Object doSomething(Session session) {  
                return session.createQuery(hql).list();   
            }  
              
        }) ;      
    }  
}
