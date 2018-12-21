package POProject.app.db;

import POProject.app.core.Author;
import POProject.core.db.AbstractDAO;

public class AuthorDAO extends AbstractDAO<Author> {

    @Override
    public Author findById(Long id) {
        openCurrentSession();
        Author author = getCurrentSession().get(Author.class, id);
        closeCurrentSession();
        return author;
    }
}
