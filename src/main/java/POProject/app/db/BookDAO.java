package POProject.app.db;

import POProject.app.core.Author;
import POProject.app.core.Book;
import POProject.core.db.AbstractDAO;

public class BookDAO extends AbstractDAO<Book> {

    @Override
    public Book findById(Long id) {
        openCurrentSession();
        Book book = getCurrentSession().get(Book.class, id);
        closeCurrentSession();
        return book;
    }

    public Book getBookByAuthor(Author author){
        openCurrentSession();

        Book bookRes = getCurrentSession().get(Book.class,author);

        closeCurrentSession();
        return bookRes;
    }
}
