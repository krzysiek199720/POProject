package POProject.db.app.db;

import POProject.db.app.api.SearchApi;
import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.db.core.db.AbstractDAO;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class BookDAO extends AbstractDAO<Book> {

    @Override
    public Book findById(Long id) {
        openCurrentSession();
        Book book = getCurrentSession().get(Book.class, id);
        closeCurrentSession();
        return book;
    }

    @Override
    public List<Book> getAll(){
        openCurrentSession();

        List<Book> res = (List<Book>) getCurrentSession().createQuery("from Book").list();

        closeCurrentSession();
        return res;
    }

    public List<Book> getByISBN(String isbn){
        openCurrentSession();

        List<Book> res = getCurrentSession().createQuery("from Book where isbn like '"+isbn+ "' order by title" ).list();

        closeCurrentSession();
        return res;
    }

    public List<Book> getByTitle(String title){

        if( title == null || title.isEmpty())
            return getAll();

        openCurrentSession();

        String searchVal = "%" + title + "%";

        List<Book> res = (List<Book>) getCurrentSession().createQuery("from Book where title like '"+searchVal+ "' order by title" ).list();

        closeCurrentSession();
        return res;
    }

    public List<Book> getBySearchApi(SearchApi api){
        openCurrentSession();

        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root);

        if(api.getTitle() != null && !api.getTitle().equals("")){
            String tmp = "%" + api.getTitle() + "%";
            query.where(builder.like(root.get("title"),tmp));
        }
        if(api.getISBN() != null && !api.getISBN().equals("")){
            String tmp = "%" + api.getISBN() + "%";
            query.where(builder.like(root.get("ISBN"),tmp));
        }
        if(api.getYear() != null){
            query.where(builder.equal(root.get("releaseYear"),api.getYear()));
        }

        if(api.getSeries() != null){
            query.where(builder.equal(root.get("series"),api.getSeries()));
        }

        if(api.getPublisher() != null){
            query.where(builder.equal(root.get("publisher"),api.getPublisher()));
        }

        if(api.getAuthor() != null){
            query.where(root.join("authorList").in(api.getAuthor()));
        }

        if(api.getCategory() != null){
            query.where(root.join("categoryList").in(api.getCategory()));
        }

        Query<Book> q = getCurrentSession().createQuery(query);

        List<Book> res = q.getResultList();

        closeCurrentSession();
        return res;
    }

    public Book getBookByAuthor(Author author){
        openCurrentSession();

        Book bookRes = getCurrentSession().get(Book.class,author);

        closeCurrentSession();
        return bookRes;
    }

    public static BookDAO getDAO(){
        return new BookDAO();
    }
}
