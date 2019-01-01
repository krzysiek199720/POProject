package POProject.db.core.db;

import POProject.db.core.core.AbstractModel;
import POProject.db.core.utils.HibernateUtils;
import POProject.db.core.utils.Utils;

import java.util.List;

public abstract class AbstractDAO<T extends AbstractModel> extends HibernateUtils {


    public void saveOrUpdate(T entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().saveOrUpdate(Utils.requireNonNull(entity));
        closeCurrentSessionWithTransaction();
    }

    public abstract T findById(Long id);

    public void delete(T entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    public void deleteAll(List<T> entityList) {
        for (T entity : entityList) {
            delete(entity);
        }
    }

}
