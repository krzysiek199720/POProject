package POProject.db.app.db;

import POProject.db.app.core.Series;
import POProject.db.core.db.AbstractDAO;

import java.util.List;

public class SeriesDAO extends AbstractDAO<Series> {

    @Override
    public Series findById(Long id) {
        openCurrentSession();
        Series series = getCurrentSession().get(Series.class, id);
        closeCurrentSession();
        return series;
    }

    @Override
    public List<Series> getAll(){
        openCurrentSession();

        List<Series> res = (List<Series>) getCurrentSession().createQuery("from Series").list();

        closeCurrentSession();
        return res;
    }

    public static SeriesDAO getDAO(){
        return new SeriesDAO();
    }
}
