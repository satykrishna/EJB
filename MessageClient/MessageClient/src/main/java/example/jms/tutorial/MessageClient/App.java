package example.jms.tutorial.MessageClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jms.bean.session.LibrarySessionFacade;
import jms.weblogic.pojo.Book;

public class App {
  
  private BufferedReader bufferedReader;
  private InitialContext initialConext;
  
  private void initialize() throws NamingException {
    
    Hashtable< String, String > table = new Hashtable<String, String>();
    table.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
    table.put("java.naming.provider.url", "t3://localhost:7001");
    table.put("java.naming.factory.url.pkgs", "weblogic.corba.j2ee.naming.url:weblogic.corba.client.naming");
    table.put("java.naming.security.principal", "admin");
    table.put("java.naming.security.credentials", "Infy!123");
    initialConext = new InitialContext(table);
  }

  private void showMenu() {
    System.out.println("**********************");
    System.out.println("Welcome to Book Store");
    System.out.println("**********************");
    System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
  }
  
  private void test() {
    try {
      int choice = 1; 
      Queue queue = (Queue) initialConext.lookup("jndi/queue");
      QueueConnectionFactory factory =
      (QueueConnectionFactory) initialConext.lookup("jndi/connectionFactory");
      QueueConnection connection =  factory.createQueueConnection();
      QueueSession session = 
      connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
      QueueSender sender = session.createSender(queue);

      while (choice != 2) {
         String bookName;
         showMenu();
         String strChoice = bufferedReader.readLine();
         choice = Integer.parseInt(strChoice);
         if (choice == 1) {
            System.out.print("Enter book name: ");
            bookName = bufferedReader.readLine();
            Book book = new Book();
            book.setName(bookName);
            ObjectMessage objectMessage = 
               session.createObjectMessage(book);
            sender.send(objectMessage); 
         } else if (choice == 2) {
            break;
         }
      }

      LibrarySessionFacade libraryBean = 
      (LibrarySessionFacade)initialConext.lookup("libraryEndPoint#jms.bean.session.LibrarySessionFacade");

      List<Book> booksList = libraryBean.getAllBooks();

      System.out.println("Book(s) entered so far: " + booksList.size());
      int i = 0;
      for (Book book:booksList) {
         System.out.println((i+1)+". " + book.getName());
         i++;
      }           
   } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
   }finally {
      try {
         if(bufferedReader !=null){
            bufferedReader.close();
         }
      } catch (IOException ex) {
         System.out.println(ex.getMessage());
      }
   }
  }
  
  public App() {
    try {
      initialize();
    }
    catch(NamingException exception ) {
      exception.printStackTrace();
    }
    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  }
  
  public static void main(String[] args) {
    App app = new App();
    app.test();
  }
}
