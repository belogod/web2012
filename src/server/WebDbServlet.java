package server;

import beans.AvtorService;
import beans.BookService;
import beans.CityService;
import beans.IzdatelstvoSevice;
import tables.Avtor;
import tables.Book;
import tables.City;
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
@WebServlet(name = "WebDbServlet", urlPatterns = {"/main", "/avtors", "/izdat", "/books", "/delete"})
public class WebDbServlet extends HttpServlet {
    @EJB
    AvtorService as;
    @EJB
    BookService bs;
    @EJB
    IzdatelstvoSevice is;
    @EJB
    CityService cs;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/izdat".equals(request.getServletPath())) {
            if (request.getParameter("addcityforizdat")!=null) {
                addCityForIzdat(request,response);
            }
        } else if (request.getParameter("add_avtor")!=null) {
            addAvtor(request, response);
        } else if ("/avtors".equals(request.getServletPath())) {
            editAvtor(request, response, 2);
        } else if (request.getParameter("add_book")!=null) {
            addBook(request, response, 2);
        }
    }

    private void addCityForIzdat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer cityId = Integer.valueOf(request.getParameter("city_select"));
        Integer izdatId = Integer.valueOf(request.getParameter("addcityforizdat"));
        Izdatelstvo izdatelstvo = is.find(izdatId);
        City city = cs.find(cityId);
        izdatelstvo.getCities().add(city);
        is.edit(izdatelstvo);
        response.sendRedirect("izdat");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/avtors".equals(servletPath)) {
            doAvtors(request, response);
        } else if ("/izdat".equals(servletPath)) {
            doIzdat(request,response);
        } else if ("/delete".equals(servletPath)){
                if (request.getParameter("iz_id")!=null){
                    deleteIzdatelstvo(request, response);
                } else if (request.getParameter("book_id")!=null) {
                    deleteBook(request, response);
                }
        } else if ("/books".equals(servletPath)){
            addBook(request, response, 1);
        } else {
            doBooks(request,response);
        }
    }



    private void addAvtor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");
        as.create(name, comment);
        response.sendRedirect("avtors");
    }

    private void deleteIzdatelstvo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer iz_id = Integer.valueOf(request.getParameter("iz_id"));
        is.remove(iz_id);
        response.sendRedirect("main");
    }


    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer book_id = Integer.valueOf(request.getParameter("book_id"));
        bs.remove(book_id);
        response.sendRedirect("main");
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response, int stage) throws ServletException, IOException {
        if (stage == 1) {
            List avtors = as.findAll();
            List izdats = is.findAll();
            request.setAttribute("avtors", avtors);
            request.setAttribute("izdats", izdats);
            request.getRequestDispatcher("/addbook.jsp").forward(request, response);
        } else {
            Integer avtorId = Integer.valueOf(request.getParameter("avtor_select"));
            String nazvanie = request.getParameter("book_title");
            Integer pages = Integer.valueOf(request.getParameter("book_pages"));
            Integer izdatId = Integer.valueOf(request.getParameter("izdat_select"));
            Avtor avtor;
            if (avtorId!=-1) {
                avtor = as.find(avtorId);
            } else {
                avtor = as.create(request.getParameter("altavtor"),"");
            }
            Izdatelstvo izdat = is.find(izdatId);
            bs.create(nazvanie, pages, avtor, izdat);
            response.sendRedirect("main");
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
        } else if (request.getParameter("addcitybyizdat_id")!=null) {
            addCityByIzdat(request,response);
        } else {
            showIzdatelstvo(request, response);
        }
    }

    private void addCityByIzdat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer iid = Integer.valueOf(request.getParameter("addcitybyizdat_id"));
        Izdatelstvo izdatelstvo = is.find(iid);
        List<City> cities = cs.findAll();
        cities.removeAll(izdatelstvo.getCities());
        request.setAttribute("izdat", izdatelstvo);
        request.setAttribute("cities", cities);
        request.getRequestDispatcher("/selectandaddcity.jsp").forward(request,response);
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
        } else if (request.getParameter("edit_aid") != null) {
            editAvtor(request,response,1);
        } else {
            showAvtors(request, response);
        }
    }

    private void editAvtor(HttpServletRequest request, HttpServletResponse response, int phase) throws ServletException, IOException {
        Integer aid = Integer.valueOf(request.getParameter("edit_aid"));
        Avtor avtor = as.find(aid);
        if (phase == 1) {
            request.setAttribute("avtor", avtor);
            request.getRequestDispatcher("editavtor.jsp").forward(request, response);
        } else if (phase == 2){
            String avtorname = request.getParameter("avtorname");
            String avtorcomment = request.getParameter("avtorcomment");
            if (!(avtorname.equals(avtor.getName()) || avtorname.isEmpty())) {
                avtor.setName(avtorname);
            }
            if (!(avtorcomment.isEmpty() || avtorcomment.equals(avtor.getComment()))) {
                avtor.setComment(avtorcomment);
            }
            as.edit(avtor);
            response.sendRedirect("avtors");
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
