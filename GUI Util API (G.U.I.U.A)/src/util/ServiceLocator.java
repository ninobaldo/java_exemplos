/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.naming.InitialContext;

/**
 *
 * @author avsilva
 */
public class ServiceLocator {

    private static final String baseString = "java:global/gap.control/";
    
    private static Properties getProperty() {
        Properties jndiProps = new Properties();
        jndiProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        jndiProps.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        jndiProps.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        jndiProps.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        jndiProps.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        return jndiProps;
    }
    
    public static <T> T getBean(final Class<T> clazz, String nameBean) throws Exception { 
        InitialContext ctx = new InitialContext(getProperty());
        return (T)ctx.lookup(baseString + nameBean);
    }
}
