package tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Belogod on 20.12.2015.
 */
@Entity(name = "book")
@NamedQuery(name = "Book.findAll", query = "select a from book a")
public class Book  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nazvanie;
    private Integer pages;

    public Book() {
    }

    public Book(String nazvanie, Integer pages) {
        this.nazvanie = nazvanie;
        this.pages = pages;
    }

    public Book(String nazvanie, Integer pages, Avtor avtor, Izdatelstvo izdatelstvo) {
        this.nazvanie = nazvanie;
        this.pages = pages;
        this.avtor = avtor;
        this.izdatelstvo = izdatelstvo;
    }

    @ManyToOne
    @JoinColumn(name = "Avtor_id")
    private Avtor avtor;

    @ManyToOne
    @JoinColumn(name = "Izdatelstvo_id")
    private Izdatelstvo izdatelstvo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Avtor getAvtor() {
        return avtor;
    }

    public void setAvtor(Avtor avtor) {
        this.avtor = avtor;
    }

    public Izdatelstvo getIzdatelstvo() {
        return izdatelstvo;
    }

    public void setIzdatelstvo(Izdatelstvo izdatelstvo) {
        this.izdatelstvo = izdatelstvo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(nazvanie, book.nazvanie) &&
                Objects.equals(pages, book.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, pages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nazvanie='" + nazvanie + '\'' +
                ", pages=" + pages +
                ", avtor=" + avtor +
                ", izdatelstvo=" + izdatelstvo +
                '}';
    }
}
