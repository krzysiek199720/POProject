package POProject.db.app.db;

import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.db.core.db.AbstractDAO;

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
