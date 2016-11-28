package jms.weblogic.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the BOOKS database table.
 * 
 */
@Entity
@Table(name = "BOOKS")
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class Book implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int               id;

  private String            name;

  public Book() {
  }

  public long getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Book other = (Book) obj;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    }
    else if (!name.equals(other.name))
      return false;
    return true;
  }

}