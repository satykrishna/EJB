package example.app.client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageDrivenBeanClient {

  private QueueConnectionFactory connectionFactory;

  private QueueConnection        connection;

  private QueueSession           session;

  private Queue                  queue;

  private QueueSender            sender;

  private TextMessage            textMessage;

  // Defines the JNDI context factory.
  public final static String     JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

  // Defines the JMS context factory.
  public final static String     JMS_FACTORY  = "jndi/connectionFactory";

  // Defines the queue.
  public final static String     QUEUE        = "jndi/queue";

  public void init(Context ctx, String queueName) throws NamingException, JMSException {
    connectionFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
    connection = connectionFactory.createQueueConnection();
    session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    sender = session.createSender(queue);
    textMessage = session.createTextMessage();
    connection.start();
  }

  public void send(String message, int count) throws JMSException {
    textMessage.setText(message);
    textMessage.setIntProperty("counter", count);
    sender.send(textMessage);
  }

  public void close() throws JMSException {
    sender.close();
    session.close();
    connection.close();
  }

  public MessageDrivenBeanClient() {

  }

  public static void main(String[] args) throws NamingException, JMSException {
   
    MessageDrivenBeanClient mdbc = new MessageDrivenBeanClient();
    mdbc.init(getInitialContext(), QUEUE);
    readandSend(mdbc);

  }

  private static void readandSend(MessageDrivenBeanClient mdbc) throws JMSException {
    String line = "Test Message Body with counter = ";
    for (int i = 0; i < 100; i++) {
      mdbc.send(line + i, i);
      System.out.println("JMS Message Sent: " + line + i + "n");
    }

  }

  private static InitialContext getInitialContext() throws NamingException {
    Hashtable<String, String> table = new Hashtable<String, String>();
    table.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
    table.put("java.naming.provider.url", "t3://localhost:7001");
    table.put("java.naming.factory.url.pkgs", "weblogic.corba.j2ee.naming.url:weblogic.corba.client.naming");
    table.put("java.naming.security.principal", "admin");
    table.put("java.naming.security.credentials", "Infy!123");
    return new InitialContext(table);
  }

}
