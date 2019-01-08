package POProject.db.app.db;

import POProject.db.app.core.Series;
import POProject.db.core.db.AbstractDAO;

public class SeriesDAO extends AbstractDAO<Series> {

    @Override
    public Series findById(Long id) {
        openCurrentSession();
        Series series = getCurrentSession().get(Series.class, id);
        closeCurrentSession();
        return series;
    }

    public static SeriesDAO getDAO(){
        return new SeriesDAO();
    }
}
