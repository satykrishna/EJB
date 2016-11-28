package jms.bean.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jms.weblogic.pojo.Book;

@Stateless(mappedName = "libraryEndPoint")
public class LibrarySessionFacadeHome implements LibrarySessionFacade {
  
  @PersistenceContext(name="Book")
  private EntityManager entityManager;
  
  public LibrarySessionFacadeHome() {
    }

  @Override
  public void addNewBook(Book newBook) {
    entityManager.persist(newBook);
  }

  @Override
  public List<Book> getAllBooks() {
    @SuppressWarnings("unchecked")
    List<Book> books =  entityManager.createNamedQuery("Book.findAll").getResultList();
    return books;
  }

}
