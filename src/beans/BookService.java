package beans;

import tables.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/**
 * Created by Belogod on 20.12.2015.
 */
@Stateless
public class BookService {
    @PersistenceContext
    EntityManager em;
    public List<Book> findAll(){
        return em.createNamedQuery("Book.findAll").getResultList();

    }
    public Book find(Integer id){
        return em.find(Book.class, id);
    }
    public Book create(String nazvanie, Integer pages){
        Book book = new Book(nazvanie, pages);
        em.persist(book);
        return book;
    }
}
