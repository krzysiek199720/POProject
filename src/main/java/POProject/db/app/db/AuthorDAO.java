package POProject.db.app.db;

import POProject.db.app.core.Author;
import POProject.db.core.db.AbstractDAO;

public class AuthorDAO extends AbstractDAO<Author> {

    @Override
    public Author findById(Long id) {
        openCurrentSession();
        Author author = getCurrentSession().get(Author.class, id);
        closeCurrentSession();
        return author;
    }
}
