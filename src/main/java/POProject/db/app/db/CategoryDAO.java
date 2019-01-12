package POProject.db.app.db;

import POProject.db.app.core.Category;
import POProject.db.core.db.AbstractDAO;

import java.util.List;

public class CategoryDAO extends AbstractDAO<Category> {

    @Override
    public Category findById(Long id) {
        openCurrentSession();
        Category category = getCurrentSession().get(Category.class, id);
        closeCurrentSession();
        return category;
    }

    @Override
    public List<Category> getAll(){
        openCurrentSession();

        List<Category> res = (List<Category>) getCurrentSession().createQuery("from Category").list();

        closeCurrentSession();
        return res;
    }

    public static CategoryDAO getDAO(){
        return new CategoryDAO();
    }

}
