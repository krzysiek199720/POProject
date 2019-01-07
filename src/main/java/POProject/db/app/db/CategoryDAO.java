package POProject.db.app.db;

import POProject.db.app.core.Category;
import POProject.db.core.db.AbstractDAO;

public class CategoryDAO extends AbstractDAO<Category> {

    @Override
    public Category findById(Long id) {
        openCurrentSession();
        Category category = getCurrentSession().get(Category.class, id);
        closeCurrentSession();
        return category;
    }

    public static CategoryDAO getDAO(){
        return new CategoryDAO();
    }

}
