package beans;

import tables.City;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yevhen on 17.02.2016.
 */
@Stateless
public class CityService {
    @PersistenceContext
    EntityManager em;

    public List<City> findAll() {
        return em.createNamedQuery("City.findAll").getResultList();
    }

    public City find(Integer cityId) {
        return em.find(City.class, cityId);
    }
}
