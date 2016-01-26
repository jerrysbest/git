package erp.ws.sbo.client.swing.util.general;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;
public class JerrysSQLServerDialect extends SQLServerDialect{

	public JerrysSQLServerDialect(){
        super();
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
        registerHibernateType(Types.DATE, Hibernate.DATE.getName());
        registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
        registerHibernateType(Types.INTEGER, Hibernate.INTEGER.getName());
        registerHibernateType(Types.NVARCHAR, Hibernate.STRING.getName());
        registerHibernateType(Types.TIMESTAMP, Hibernate.DATE.getName());
        registerHibernateType(Types.DOUBLE, Hibernate.DOUBLE.getName());
        registerHibernateType(Types.SMALLINT, Hibernate.SHORT.getName());
        registerHibernateType(Types.BOOLEAN, Hibernate.BOOLEAN.getName());
        registerHibernateType(Types.FLOAT, Hibernate.FLOAT.getName());
        registerHibernateType(Types.TINYINT, Hibernate.SHORT.getName());
        registerHibernateType(Types.NUMERIC, Hibernate.BIG_DECIMAL.getName());
        registerHibernateType(Types.LONGNVARCHAR, Hibernate.STRING.getName());
        registerHibernateType(Types.NCHAR, Hibernate.STRING.getName());
      }
}
