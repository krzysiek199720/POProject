package POProject.app.db;

import POProject.app.core.Series;
import POProject.core.db.AbstractDAO;

public class SeriesDAO extends AbstractDAO<Series> {

    @Override
    public Series findById(Long id) {
        openCurrentSession();
        Series series = getCurrentSession().get(Series.class, id);
        closeCurrentSession();
        return series;
    }
}
