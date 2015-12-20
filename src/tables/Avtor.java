package tables;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by Belogod on 20.12.2015.
 */
@Entity
@NamedQuery(name = "Avtor.findAll", query = "select a from Avtor a")
public class Avtor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String comment;

    @OneToMany(mappedBy = "avtor")
    private List<Book> books;

    public Avtor() {
    }

    public Avtor(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Avtor(String name, String comment, List<Book> books) {
        this.name = name;
        this.comment = comment;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avtor avtor = (Avtor) o;
        return Objects.equals(id, avtor.id) &&
                Objects.equals(name, avtor.name) &&
                Objects.equals(comment, avtor.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comment);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Avtor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
