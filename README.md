# EJB

Add following runtime vm arguments to the EJB client

-Dcom.sun.xml.namespace.QName.useCompatibleSerialVersionUID=1.0
-Dsun.lang.ClassLoader.allowArraySyntax=true

Also add wlfullclient.jar to the EJBClient

  Invoke following table to the initial context

    Hashtable< String, String > table = new Hashtable<String, String>();
    table.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
    table.put("java.naming.provider.url", "t3://localhost:7001");
    table.put("java.naming.factory.url.pkgs", "weblogic.corba.j2ee.naming.url:weblogic.corba.client.naming");
    table.put("java.naming.security.principal", "*****");
    table.put("java.naming.security.credentials", "****");
    
    InitialContext initContext = new InitialContext(table);
    
