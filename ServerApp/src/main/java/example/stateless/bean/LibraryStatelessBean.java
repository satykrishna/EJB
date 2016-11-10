package example.stateless.bean;

import javax.ejb.Stateless;

import example.stateless.pojo.Book;

/**
 * Session Bean implementation class LibraryStatelessBean
 */
@Stateless(mappedName = "libraryStatelessBean")
public class LibraryStatelessBean implements LibraryStatelessBeanRemote {

    /**
     * Default constructor. 
     */
    public LibraryStatelessBean() {
    }

    @Override
    public void add(Book book) {
      System.out.println("New book is added");
    }

}
