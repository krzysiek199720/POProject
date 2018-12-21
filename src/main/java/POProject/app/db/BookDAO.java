package POProject.app.db;

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
}
