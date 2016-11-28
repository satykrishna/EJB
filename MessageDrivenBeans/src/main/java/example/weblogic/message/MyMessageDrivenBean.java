package example.weblogic.message;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "jndi/queue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "jndi/queue")
public class MyMessageDrivenBean implements MessageListener {

  private Logger logger = Logger.getLogger(MyMessageDrivenBean.class.getSimpleName());
  
  public MyMessageDrivenBean() {
  }

  public void onMessage(Message message) {
    TextMessage textMessage = (TextMessage) message;
    
    try {
      logger.info("Message received : " + textMessage.getText());
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
    
     
  }

}
