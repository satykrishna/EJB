package jms.bean.message;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import jms.bean.session.LibrarySessionFacade;
import jms.weblogic.pojo.Book;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "jndi/queue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "jndi/queue")
public class LibraryMessageBean implements MessageListener {

  @Resource
  private MessageDrivenContext mdc;

  @EJB
  private LibrarySessionFacade libraryFacade;

  public LibraryMessageBean() {
  }

  public void onMessage(Message message) {
    ObjectMessage objectMessage =  (ObjectMessage) message;
    try {
      Book book = (Book) objectMessage.getObject();
      libraryFacade.addNewBook(book);
    }
    catch (JMSException e) {
      mdc.setRollbackOnly();
    }

  }

}
