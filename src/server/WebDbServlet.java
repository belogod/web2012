package server;

import beans.AvtorService;
import beans.BookService;
import beans.IzdatelstvoSevice;
import tables.Avtor;
import tables.Book;
import tables.Izdatelstvo;

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
 * Сервлет
 */
@WebServlet(name = "WebDbServlet", urlPatterns = {"/main", "/avtors", "/izdat"})
public class WebDbServlet extends HttpServlet {
    @EJB
    AvtorService as;
    @EJB
    BookService bs;
    @EJB
    IzdatelstvoSevice is;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/avtors".equals(servletPath)) {
            doAvtors(request, response);
        } else if ("/izdat".equals(servletPath)) {
            doIzdat(request,response);
        } else {
            doBooks(request,response);
        }

    }

    private void doBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showBooks(request,response);
    }

    private void showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bs.findAll();
        request.setAttribute("books",books);
        request.getRequestDispatcher("/books.jsp").forward(request,response);
    }

    private void doIzdat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("sities_by_izdat")!=null) {
            showCitiesOfIzdat(request, response);
        } else {
            showIzdatelstvo(request, response);
        }
    }

    private void showCitiesOfIzdat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sities_by_izdat = request.getParameter("sities_by_izdat");
        Integer id = Integer.valueOf(sities_by_izdat);
        Izdatelstvo izdatelstvo = is.find(id);
        request.setAttribute("iz",izdatelstvo);
        request.getRequestDispatcher("/showcitiesbyizdat.jsp").forward(request,response);
    }

    private void doAvtors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("books_by_aid") != null) {
            showBooksByAvtor(request, response);
        } else if (request.getParameter("avtors_by_comment") != null ) {
            showAvtorsByComment(request, response);
        } else {
            showAvtors(request, response);
        }
    }

    private void showAvtorsByComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        List avtorByComment = as.findAvtorByComment(comment);
        request.setAttribute("avtors", avtorByComment);
        request.getRequestDispatcher("/avtors.jsp").forward(request, response);
    }


    private void showAvtors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Avtor> avtors = as.findAll();
        request.setAttribute("avtors", avtors);
        request.getRequestDispatcher("/avtors.jsp").forward(request, response);
    }

    private void showBooksByAvtor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Book> books = bs.findAll();
        String books_by_aid = request.getParameter("books_by_aid");
        try {
            Integer aid = Integer.valueOf(books_by_aid);
            Avtor avtor = as.find(aid);
            request.setAttribute("avtor", avtor);
            request.getRequestDispatcher("/booksbyavtor.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            showAvtors(request, response);
        }
    }

    private void showIzdatelstvo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Izdatelstvo> izdats = is.findAll();
        request.setAttribute("izdats", izdats);
        request.getRequestDispatcher("/izdatelstvo.jsp").forward(request, response);
    }

}
