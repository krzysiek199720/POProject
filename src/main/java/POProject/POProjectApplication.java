package POProject;

import POProject.db.app.core.*;
import POProject.db.app.core.enums.Sex;
import POProject.db.app.db.*;
import POProject.db.core.utils.HibernateUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POProjectApplication extends Application {


    public static void main(String[] args){
        HibernateUtils.OpenConnection("hibernate.cfg.xml");


        //TESTING TESTING

        Date date = new Date();
        date.setTime(56465465L);

        Category category = new Category("name","about",null);
        Author author1 = new Author("fn","ln",date,null,"Africa", Sex.male,"about", null,null);

        Series series = new Series("nazwa", null);
        Publisher publisher = new Publisher("pub",null);
        List<Author> authorList = new ArrayList();
        authorList.add(author1);
        List<Category> categoryList = new ArrayList();
        categoryList.add(category);

        Book newBook = new Book("abc",2,"0123456789101",null,2000,null,series,publisher,authorList,categoryList);

        CategoryDAO categoryDAO = new CategoryDAO();
        AuthorDAO authorDAO = new AuthorDAO();

        categoryDAO.saveOrUpdate(category);

        authorDAO.saveOrUpdate(author1);

        PublisherDAO publisherDAO = new PublisherDAO();
        SeriesDAO seriesDAO = new SeriesDAO();

        publisherDAO.saveOrUpdate(publisher);

        seriesDAO.saveOrUpdate(series);

        BookDAO bookDAO = new BookDAO();
        bookDAO.saveOrUpdate(newBook);


        //~~TESTING ~~TESTING


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxmls/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.initStyle(StageStyle.UTILITY);

        primaryStage.setX(0.0);
        primaryStage.setY(0.0);

        primaryStage.show();
    }

}
