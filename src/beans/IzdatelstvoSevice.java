package beans;

import tables.Izdatelstvo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yevhen on 22.12.2015.
 */
@Stateless
public class IzdatelstvoSevice {
    @PersistenceContext
    EntityManager em;

    public List findAll() {
        return em.createNamedQuery("Izdat.findAll").getResultList();
    }

    public Izdatelstvo create(String nazvanie, String adres) {
        Izdatelstvo izdatelstvo = new Izdatelstvo(nazvanie, adres);
        em.persist(izdatelstvo);
        return izdatelstvo;
    }

    public Izdatelstvo find(Integer id) {
        return em.find(Izdatelstvo.class, id);
    }
}
