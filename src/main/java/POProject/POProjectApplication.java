package POProject;

import POProject.app.core.*;
import POProject.app.core.enums.Sex;
import POProject.app.db.*;
import POProject.core.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POProjectApplication {


    public static void main(String[] args){
        HibernateUtils.OpenConnection("hibernate.cfg.xml");


        //TESTING TESTING

        Date date = new Date();
        date.setTime(56465465L);

        Category category = new Category("name",null);
        Author author1 = new Author("fn","ln",date,null,"Africa", Sex.male,null,null);

        Series series = new Series("nazwa", null);
        Publisher publisher = new Publisher("pub",null);
        List<Author> authorList = new ArrayList();
        authorList.add(author1);
        List<Category> categoryList = new ArrayList();
        categoryList.add(category);

        Book newBook = new Book("abc",2,"0123456789101",null,2000,null,series,publisher,authorList,categoryList);

        CategoryDAO categoryDAO = new CategoryDAO();
        AuthorDAO authorDAO = new AuthorDAO();

        categoryDAO.saveOrUpdate(category);

        authorDAO.saveOrUpdate(author1);

        PublisherDAO publisherDAO = new PublisherDAO();
        SeriesDAO seriesDAO = new SeriesDAO();

        publisherDAO.saveOrUpdate(publisher);

        seriesDAO.saveOrUpdate(series);

        BookDAO bookDAO = new BookDAO();
        bookDAO.saveOrUpdate(newBook);


        //~~TESTING ~~TESTING

    }

}
