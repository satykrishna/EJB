package bean.stateless.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bean.entity.ejb.Book;

/**
 * Session Bean implementation class BookFacadeHome
 */
@Stateless(mappedName = "jndi/books")
public class BookFacadeHome implements BookFacade {

  @PersistenceContext(unitName = "Book")
  private EntityManager entityManager;

  public BookFacadeHome() {
      
  }

  @Override
  public void add(Book booktoAdd) {
    entityManager.persist(booktoAdd);
  }

  @Override
  public List<Book> getBooks() {
    return entityManager.createNamedQuery("Book.findAll").getResultList();
  }

  @Override
  public void remove(Book bookToRemove) {
    Book book = entityManager.find(Book.class, bookToRemove.getId());
    entityManager.remove(book);
  }

  @Override
  public void update(Book bookTobeUpdated) {
    Book book = entityManager.find(Book.class, bookTobeUpdated.getId());
    book.setName(bookTobeUpdated.getName());
  }

  @Override
  public List<Book> find(int id) {
    Book book = entityManager.find(Book.class, id);
    List<Book> l = new ArrayList<Book>();
    if (book != null)
      l.add(book);
    return l;
  }

  @Override
  public void deleteAll() {
    entityManager.createQuery("delete * from books");
  }

}
