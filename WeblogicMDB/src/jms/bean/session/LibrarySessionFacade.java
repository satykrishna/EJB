package jms.bean.session;

import java.util.List;

import javax.ejb.Remote;

import jms.weblogic.pojo.Book;

@Remote
public interface LibrarySessionFacade {

  public abstract void addNewBook(Book newBook);
  
  public abstract List<Book> getAllBooks();
  
}
