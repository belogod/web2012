package tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Belogod on 20.12.2015.
 */
@Entity(name = "izdatelstvo")
@NamedQuery(name = "Izdat.findAll", query = "select iz from izdatelstvo iz")
public class Izdatelstvo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nazvanie;
    private String adres;

    public Izdatelstvo() {
    }

    public Izdatelstvo(String nazvanie, String adres) {
        this.nazvanie = nazvanie;
        this.adres = adres;
    }

    public Izdatelstvo(String nazvanie, String adres, List<Book> books) {
        this.nazvanie = nazvanie;
        this.adres = adres;
        this.books = books;
    }

    @OneToMany(mappedBy = "izdatelstvo")
    private List<Book> books;

    @JoinTable(name = "city_has_izdatelstvo")
    @ManyToMany(mappedBy = "izdatelstva")
    private List<City> cities;
    public List<City> getCities() {
    return cities;
}

    public void setCities(List<City> cities) {
        this.cities = cities;
    }


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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Izdatelstvo that = (Izdatelstvo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nazvanie, that.nazvanie) &&
                Objects.equals(adres, that.adres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, adres);
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return nazvanie + " (" + adres + ")";
    }
}
