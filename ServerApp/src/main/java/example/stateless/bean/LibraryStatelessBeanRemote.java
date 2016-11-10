package example.stateless.bean;

import javax.ejb.Remote;

import example.stateless.pojo.Book;

@Remote
public interface LibraryStatelessBeanRemote {
  
  public abstract void add(Book book);

}
