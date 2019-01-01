package POProject.db.app.db;

import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.db.core.db.AbstractDAO;

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
