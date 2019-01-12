package POProject.db.app.db;

import POProject.db.app.core.Publisher;
import POProject.db.core.db.AbstractDAO;

import java.util.List;

public class PublisherDAO extends AbstractDAO<Publisher> {

    @Override
    public Publisher findById(Long id) {
        openCurrentSession();
        Publisher publisher = getCurrentSession().get(Publisher.class, id);
        closeCurrentSession();
        return publisher;
    }

    @Override
    public List<Publisher> getAll(){
        openCurrentSession();

        List<Publisher> res = (List<Publisher>) getCurrentSession().createQuery("from Publisher").list();

        closeCurrentSession();
        return res;
    }

    public static PublisherDAO getDAO(){
        return new PublisherDAO();
    }
}
