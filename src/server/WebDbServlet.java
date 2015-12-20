package server;

import beans.AvtorService;
import beans.BookService;
import tables.Avtor;
import tables.Book;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Belogod on 20.12.2015.
 */
@WebServlet(name = "WebDbServlet", urlPatterns = {"/main"})
public class WebDbServlet extends HttpServlet {
    @EJB
    AvtorService as;
    BookService bs;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("books_by_aid") != null) {
            showBooksByAvtor(request, response);
        } else {
            showAvtors(request, response);
        }
        if (request.getParameter("izdatelstvo_by_aid") !=null){
            showIzdatelstvo(request, response);
        }else {
            showBooksByAvtor(request, response);
        }
    }



    private void showAvtors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Avtor> avtors = as.findAll();
        request.setAttribute("avtors", avtors);
        request.getRequestDispatcher("/avtors.jsp").forward(request, response);

    }

    private void showBooksByAvtor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bs.findAll();
        String books_by_aid = request.getParameter("books_by_aid");
        try {
            Integer aid = Integer.valueOf(books_by_aid);
            Avtor avtor = as.find(aid);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/booksbyavtor.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            showAvtors(request, response);
        }
    }

    private void showIzdatelstvo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String izdatelstvo_by_aid = request.getParameter("izdatelstvo_by_aid");
        try {
            Integer aid = Integer.valueOf(izdatelstvo_by_aid);
            Book book = bs.find(aid);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/izdatelstvo.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            showBooksByAvtor(request, response);
        }
    }

}
