package bean.stateless.ejb;

import java.util.List;

import javax.ejb.Remote;

import bean.entity.ejb.Book;

@Remote
public interface BookFacade {
  
  public abstract void add(Book booktoAdd);
  
  public List<Book> getBooks();
  
  public void remove(Book bookToRemove);
  
  public void update(Book bookTobeUpdated);
  
  public List<Book> find(int id);
  
  public void deleteAll();
  

}
