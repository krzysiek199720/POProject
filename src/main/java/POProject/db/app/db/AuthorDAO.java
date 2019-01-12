package POProject.db.app.db;

import POProject.db.app.core.Author;
import POProject.db.core.db.AbstractDAO;

import java.util.List;

public class AuthorDAO extends AbstractDAO<Author> {

    @Override
    public Author findById(Long id) {
        openCurrentSession();
        Author author = getCurrentSession().get(Author.class, id);
        closeCurrentSession();
        return author;
    }

    @Override
    public List<Author> getAll(){
        openCurrentSession();

        List<Author> res = (List<Author>) getCurrentSession().createQuery("from Author").list();

        closeCurrentSession();
        return res;
    }

    public static AuthorDAO getDAO(){
        return new AuthorDAO();
    }
}
