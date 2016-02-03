package tables;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

/**
 * Created by Yevhen on 25.12.2015.
 */
@Entity(name = "city")
public class City  implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public City(String name) {
        this.name = name;
    }

    public City() {
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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @JoinTable(name = "city_has_izdatelstvo",
            joinColumns = { @JoinColumn(name = "city_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "izdatelstvo_id", referencedColumnName = "id")}
    )
    @ManyToMany
    private List<Izdatelstvo> izdatelstva;

    public List<Izdatelstvo> getIzdatelstva() {
        return izdatelstva;
    }

    public void setIzdatelstva(List<Izdatelstvo> izdatelstva) {
        this.izdatelstva = izdatelstva;
    }
}
