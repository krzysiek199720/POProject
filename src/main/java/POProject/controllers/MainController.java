package POProject.controllers;

import POProject.customNodes.*;
import POProject.db.app.core.*;
import POProject.db.app.core.enums.Sex;
import POProject.db.app.db.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button searchButton;

    private Stage searchStage;
    private Stage savePublisherStage;
    private Stage saveCategoryStage;
    private Stage saveSeriesStage;
    private Stage saveAuthorStage;
    private Stage saveBookStage;

    private Stage authorStage;
    private Stage bookStage;


    private static MainController mainController;

    public static MainController getInstance(){
        return mainController;
    }

    public void searchAction(ActionEvent event){

        if(searchStage != null){
            if(!searchStage.isShowing())
                searchStage.show();
            else
                searchStage.requestFocus();
            return;
        }

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxmls/search.fxml"));
            searchStage = new Stage();
            searchStage.setTitle("Search");
            searchStage.setScene(new Scene(root));
            searchStage.resizableProperty().setValue(Boolean.FALSE);
            searchStage.initStyle(StageStyle.UTILITY);
            searchStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSavePublisherStageAction(ActionEvent event) throws IOException{
        openSavePublisherStage(new Publisher());
    }
    public void openSavePublisherStage(Publisher publisher) throws IOException{
        Parent root;
        root = SavePublisherController.getNode(publisher);
        if(savePublisherStage == null){
            savePublisherStage = new Stage();
            savePublisherStage.setTitle("Save publisher");
            savePublisherStage.setScene(new Scene(root));
            savePublisherStage.resizableProperty().setValue(Boolean.FALSE);
            savePublisherStage.initStyle(StageStyle.UTILITY);
        }else
            savePublisherStage.getScene().setRoot(root);

        if(!savePublisherStage.isShowing())
            savePublisherStage.show();
        else
            savePublisherStage.requestFocus();
    }


    public void openSaveSeriesStageAction(ActionEvent event) throws IOException{
        openSaveSeriesStage(new Series());
    }
    public void openSaveSeriesStage(Series series) throws IOException{
        Parent root;
        root = SaveSeriesController.getNode(series);
        if(saveSeriesStage == null){
            saveSeriesStage = new Stage();
            saveSeriesStage.setTitle("Save series");
            saveSeriesStage.setScene(new Scene(root));
            saveSeriesStage.resizableProperty().setValue(Boolean.FALSE);
            saveSeriesStage.initStyle(StageStyle.UTILITY);
        }else
            saveSeriesStage.getScene().setRoot(root);

        if(!saveSeriesStage.isShowing())
            saveSeriesStage.show();
        else
            saveSeriesStage.requestFocus();
    }

    public void openSaveCategoryStageAction(ActionEvent event)throws IOException{
        openSaveCategoryStage(new Category());
    }
    public void openSaveCategoryStage(Category category) throws IOException{
        Parent root;
        root = SaveCategoryController.getNode(category);
        if(saveCategoryStage == null){
            saveCategoryStage = new Stage();
            saveCategoryStage.setTitle("Save category");
            saveCategoryStage.setScene(new Scene(root));
            saveCategoryStage.resizableProperty().setValue(Boolean.FALSE);
            saveCategoryStage.initStyle(StageStyle.UTILITY);
        }else
            saveCategoryStage.getScene().setRoot(root);


        if(!saveCategoryStage.isShowing())
            saveCategoryStage.show();
        else
            saveCategoryStage.requestFocus();
    }

    public void openSaveAuthorStageAction(ActionEvent event) throws IOException{
        openSaveAuthorStage(new Author());
    }
    public void openSaveAuthorStage(Author author)throws IOException{
        Parent root;
        root = SaveAuthorController.getNode(author);
        if(saveAuthorStage == null){
            saveAuthorStage = new Stage();
            saveAuthorStage.setTitle("Save author");
            saveAuthorStage.setScene(new Scene(root));
            saveAuthorStage.resizableProperty().setValue(Boolean.FALSE);
            saveAuthorStage.initStyle(StageStyle.UTILITY);
        }else
            saveAuthorStage.getScene().setRoot(root);


        if(!saveAuthorStage.isShowing())
            saveAuthorStage.show();
        else
            saveAuthorStage.requestFocus();
    }

    public void openSaveBookStageAction(ActionEvent event) throws IOException{
        openSaveBookStage(new Book());
    }

    public void openSaveBookStage(Book book) throws IOException{
        Parent root;
        root = SaveBookController.getNode(book);
        if(saveBookStage == null){
            saveBookStage = new Stage();
            saveBookStage.setTitle("Save book");
            saveBookStage.setScene(new Scene(root));
            saveBookStage.resizableProperty().setValue(Boolean.FALSE);
            saveBookStage.initStyle(StageStyle.UTILITY);
        }else
            saveBookStage.getScene().setRoot(root);

        if(!saveBookStage.isShowing())
            saveBookStage.show();
        else
            saveBookStage.requestFocus();
    }



    public void openAuthor(Author author) throws IOException{
        Parent root;
        root = AuthorController.getNode(author);
        if(authorStage == null){
            authorStage = new Stage();
            authorStage.setTitle("Author");
            authorStage.setScene(new Scene(root));
            authorStage.resizableProperty().setValue(Boolean.FALSE);
            authorStage.initStyle(StageStyle.UTILITY);
        }else
            authorStage.getScene().setRoot(root);

        if(!authorStage.isShowing())
            authorStage.show();
        else
            authorStage.requestFocus();
    }

    public void openBook(Book book) throws IOException{

        Parent root;
        root = BookController.getNode(book);
        if(bookStage == null){
            bookStage = new Stage();
            bookStage.setTitle("Book");
            bookStage.setScene(new Scene(root));
            bookStage.resizableProperty().setValue(Boolean.FALSE);
            bookStage.initStyle(StageStyle.UTILITY);
        }else
            bookStage.getScene().setRoot(root);

        if(!bookStage.isShowing())
            bookStage.show();
        else
            bookStage.requestFocus();
    }



    // TODO this is tmp pls remove as well as the fxml button which triggers it
    public void tmpFunction(ActionEvent event)throws IOException{

        Book book = BookDAO.getDAO().findById(1L);
        openSaveBookStage(book);
    }

    public void initialize(URL location, ResourceBundle resources) {
        mainController = this;
    }
}
