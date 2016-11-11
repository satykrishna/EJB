package bean.entity.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BOOKS database table.
 * 
 */
@Entity
@Table(name="BOOKS")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public Book() {
	}

	public int getId() {
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
	return "(" + id + " , " + name + " )";
	}
	
}