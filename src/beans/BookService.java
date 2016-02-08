package beans;

import tables.Avtor;
import tables.Book;
import tables.Izdatelstvo;

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
    public Book create(String nazvanie, Integer pages, Avtor avtor, Izdatelstvo izdat) {
        Book book = new Book(nazvanie, pages);
        book.setAvtor(avtor);
        book.setIzdatelstvo(izdat);
        em.persist(book);
        return book;
    }

    public void remove(Integer id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }
}
