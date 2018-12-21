package POProject.app.db;

import POProject.app.core.Publisher;
import POProject.core.db.AbstractDAO;

public class PublisherDAO extends AbstractDAO<Publisher> {

    @Override
    public Publisher findById(Long id) {
        openCurrentSession();
        Publisher publisher = getCurrentSession().get(Publisher.class, id);
        closeCurrentSession();
        return publisher;
    }
}
