package POProject.app.db;

import POProject.app.core.Category;
import POProject.core.db.AbstractDAO;

public class CategoryDAO extends AbstractDAO<Category> {

    @Override
    public Category findById(Long id) {
        openCurrentSession();
        Category category = getCurrentSession().get(Category.class, id);
        closeCurrentSession();
        return category;
    }
}
