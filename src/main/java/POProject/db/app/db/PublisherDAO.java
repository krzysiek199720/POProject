package POProject.db.app.db;

import POProject.db.app.core.Publisher;
import POProject.db.core.db.AbstractDAO;

public class PublisherDAO extends AbstractDAO<Publisher> {

    @Override
    public Publisher findById(Long id) {
        openCurrentSession();
        Publisher publisher = getCurrentSession().get(Publisher.class, id);
        closeCurrentSession();
        return publisher;
    }
}
