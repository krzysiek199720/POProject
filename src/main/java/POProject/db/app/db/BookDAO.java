package POProject.db.app.db;

import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.db.core.db.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BookDAO extends AbstractDAO<Book> {

    @Override
    public Book findById(Long id) {
        openCurrentSession();
        Book book = getCurrentSession().get(Book.class, id);
        closeCurrentSession();
        return book;
    }

    @Override
    public List<Book> getAll(){
        openCurrentSession();

        List<Book> res = (List<Book>) getCurrentSession().createQuery("from Book").list();

        closeCurrentSession();
        return res;
    }

    public List<Book> getByTitle(String title){

        if( title == null || title.isEmpty())
            return getAll();

        openCurrentSession();

        String searchVal = "%" + title + "%";

        List<Book> res = (List<Book>) getCurrentSession().createQuery("from Book where title like '"+searchVal+ "' order by title" ).list();

//        Criteria criteria = getCurrentSession().createCriteria(Book.class);
//        criteria.add(Restrictions.like("title",searchVal))
//                .addOrder(Order.asc(title));
//
//        List<Book> res = criteria.list();

        closeCurrentSession();
        return res;
    }

    public Book getBookByAuthor(Author author){
        openCurrentSession();

        Book bookRes = getCurrentSession().get(Book.class,author);

        closeCurrentSession();
        return bookRes;
    }

    public static BookDAO getDAO(){
        return new BookDAO();
    }
}
