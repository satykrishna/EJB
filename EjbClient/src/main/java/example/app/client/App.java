package example.app.client;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import example.stateless.bean.LibraryStatelessBeanRemote;

public class App {
  
  public static void main(String[] args) throws NamingException {
    
    Hashtable< String, String > table = new Hashtable<String, String>();
    table.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
    table.put("java.naming.provider.url", "t3://localhost:7001");
    table.put("java.naming.factory.url.pkgs", "weblogic.corba.j2ee.naming.url:weblogic.corba.client.naming");
    table.put("java.naming.security.principal", "*****");
    table.put("java.naming.security.credentials", "****");
    
    InitialContext initContext = new InitialContext(table);
    
    LibraryStatelessBeanRemote bean = (LibraryStatelessBeanRemote) initContext.lookup("libraryStatelessBean#example.stateless.bean.LibraryStatelessBeanRemote");
    bean.add(null);
  }

}
