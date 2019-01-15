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


    private static MainController mainController;

    public static MainController getInstance(){
        return mainController;
    }

    public void searchAction(ActionEvent event){

        if(searchStage != null){
            searchStage.show();
            System.out.println("aaa");
            return;
        }

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxmls/search.fxml"));
            searchStage = new Stage();
            searchStage.setTitle("My New Stage Title");
            searchStage.setScene(new Scene(root));
            searchStage.resizableProperty().setValue(Boolean.FALSE);
            searchStage.initStyle(StageStyle.UTILITY);
            searchStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPublisherStageAction(ActionEvent event) throws IOException{
        setPublisherStage(new Publisher());
    }
    public void setPublisherStage(Publisher publisher) throws IOException{

        AnchorPublisher root;

        if(savePublisherStage != null){

            root = (AnchorPublisher) savePublisherStage.getScene().getRoot();

        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/savePublisher.fxml"));
            savePublisherStage = new Stage();
            savePublisherStage.setTitle("My New Stage Title");
            savePublisherStage.setScene(new Scene(root));
            savePublisherStage.resizableProperty().setValue(Boolean.FALSE);
            savePublisherStage.initStyle(StageStyle.UTILITY);
        }

        root.setPublisher(publisher);

        //  0 - name
        //  1 - about
        //  2 - save Status

        TextField name = (TextField)root.getChildren().get(0);
        TextArea about = (TextArea)root.getChildren().get(1);

        name.setText(publisher.getName());
        about.setText(publisher.getAbout());

        Label saveStatus = (Label) root.getChildren().get(2);

        for(Node node : root.getChildren())
            node.setStyle("");

        saveStatus.setText("");
        savePublisherStage.hide();
        savePublisherStage.show();
    }


    public void openSeriesStageAction(ActionEvent event) throws IOException{
        setSeriesStage(new Series());
    }
    public void setSeriesStage(Series series) throws IOException{

        AnchorSeries root;

        if(saveSeriesStage != null){
            root = (AnchorSeries) saveSeriesStage.getScene().getRoot();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveSeries.fxml"));
            saveSeriesStage = new Stage();
            saveSeriesStage.setTitle("My New Stage Title");
            saveSeriesStage.setScene(new Scene(root));
            saveSeriesStage.resizableProperty().setValue(Boolean.FALSE);
            saveSeriesStage.initStyle(StageStyle.UTILITY);
        }
        root.setSeries(series);

        //  0 - name
        //  1 - about
        //  2 - save Status

        TextField name = (TextField)root.getChildren().get(0);
        TextArea about = (TextArea)root.getChildren().get(1);

        name.setText(series.getName());
        about.setText(series.getAbout());

        Label saveStatus = (Label) root.getChildren().get(2);

        for(Node node : root.getChildren())
            node.setStyle("");

        saveStatus.setText("");
        saveSeriesStage.hide();
        saveSeriesStage.show();
    }

    public void openCategoryStageAction(ActionEvent event)throws IOException{
        setCategoryStage(new Category());
    }
    public void setCategoryStage(Category category) throws IOException{

        AnchorCategory root;

        if(saveCategoryStage != null){
            root = (AnchorCategory) saveCategoryStage.getScene().getRoot();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveCategory.fxml"));
            saveCategoryStage = new Stage();
            saveCategoryStage.setTitle("My New Stage Title");
            saveCategoryStage.setScene(new Scene(root));
            saveCategoryStage.resizableProperty().setValue(Boolean.FALSE);
            saveCategoryStage.initStyle(StageStyle.UTILITY);

        }

        root.setCategory(category);

        //  0 - name
        //  1 - about
        //  2 - save Status
        TextField name = (TextField)root.getChildren().get(0);
        TextArea about = (TextArea)root.getChildren().get(1);

        name.setText(category.getName());
        about.setText(category.getAbout());

        Label saveStatus = (Label) root.getChildren().get(2);

        for(Node node : root.getChildren())
            node.setStyle("");

        saveStatus.setText("");
        saveCategoryStage.hide();
        saveCategoryStage.show();
    }

    public void openAuthorStageAction(ActionEvent event) throws IOException{
        setAuthorStage(new Author());
    }
    public void setAuthorStage(Author author)throws IOException{
        AnchorAuthor root;

        if(saveAuthorStage != null){
            root = (AnchorAuthor) saveAuthorStage.getScene().getRoot();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveAuthor.fxml"));
            saveAuthorStage = new Stage();
            saveAuthorStage.setTitle("My New Stage Title");
            saveAuthorStage.setScene(new Scene(root));
            saveAuthorStage.resizableProperty().setValue(Boolean.FALSE);
            saveAuthorStage.initStyle(StageStyle.UTILITY);
        }


        root.setAuthor(author);

        //  0 - firstName
        //  1 - lastName
        //  2 - dOB
        //  3 - dOD
        //  4 - placeOB
        //  5 - sex button male
        //  6 - sex button female
        //  7 - about
        //  8 - photo

        TextField firstName = (TextField)root.getChildren().get(0);
        TextField lastName = (TextField)root.getChildren().get(1);
        DatePicker dOB = (DatePicker)root.getChildren().get(2);
        DatePicker dOD = (DatePicker)root.getChildren().get(3);
        TextField placeOB = (TextField)root.getChildren().get(4);
        RadioButton male = (RadioButton)root.getChildren().get(5);
        RadioButton female = (RadioButton)root.getChildren().get(6);
        TextArea about = (TextArea)root.getChildren().get(7);
        ImageView photo = (ImageView)root.getChildren().get(8);
        Label saveStatus = (Label)root.getChildren().get(9);

        firstName.setText(author.getFirstName());
        lastName.setText(author.getLastName());
        dOB.setValue( (author.getDateOfBirth() == null) ? null : author.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() );
        dOD.setValue((author.getDateOfDeath() == null) ? null : author.getDateOfDeath().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        placeOB.setText(author.getPlaceOfBirth());
        if(author.getSex() == Sex.male)
            male.selectedProperty().set(true);
        else
            female.selectedProperty().set(true);
        about.setText(author.getAbout());
        photo.setImage((author.getPhoto() == null) ? null : new Image(new ByteArrayInputStream(author.getPhoto())));

        for(Node node : root.getChildren())
            node.setStyle("");

        saveStatus.setText("");
        saveAuthorStage.hide();
        saveAuthorStage.show();
    }

    public void openABookStageAction(ActionEvent event) throws IOException{
        setBookStage(new Book());
    }

    public void setBookStage(Book book) throws IOException{
        AnchorBook root;

        if(saveBookStage != null){
            root = (AnchorBook) saveBookStage.getScene().getRoot();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveBook.fxml"));
            saveBookStage = new Stage();
            saveBookStage.setTitle("My New Stage Title");
            saveBookStage.setScene(new Scene(root));
            saveBookStage.resizableProperty().setValue(Boolean.FALSE);
            saveBookStage.initStyle(StageStyle.UTILITY);
        }
        root.setBook(book);


        // 0  - title
        // 1  - ISBN
        // 2  - pageCount
        // 3  - releaseYear
        // 4  - series
        // 5  - publisher
        // 6  - authors
        // 7  - authorsList
        // 8  - categories
        // 9  - categoriesList
        // 10 - cover
        // 11 - about
        // 12 - doneStatus

        TextField title = (TextField) root.getChildren().get(0);
        TextField ISBN = (TextField) root.getChildren().get(1);
        TextField pageCount = (TextField) root.getChildren().get(2);
        TextField releaseYear = (TextField) root.getChildren().get(3);
        FilteredComboBox<Series> series = (FilteredComboBox) root.getChildren().get(4);
        FilteredComboBox<Publisher> publisher = (FilteredComboBox) root.getChildren().get(5);
        FilteredComboBox<Author> authors = (FilteredComboBox) root.getChildren().get(6);
        ListView<Author> authorsList = (ListView) root.getChildren().get(7);
        FilteredComboBox<Category> categories = (FilteredComboBox) root.getChildren().get(8);
        ListView<Category> categoriesList = (ListView) root.getChildren().get(9);
        ImageView cover = (ImageView) root.getChildren().get(10);
        TextArea about = (TextArea) root.getChildren().get(11);
        Label doneStatus = (Label) root.getChildren().get(12);

        //te co juz mam w ksiazce

        title.setText(book.getTitle());
        ISBN.setText(book.getISBN());
        pageCount.setText((book.getPageCount() == null) ? null : book.getPageCount().toString());
        releaseYear.setText((book.getReleaseYear() == null) ? null : book.getReleaseYear().toString());

        cover.setImage((book.getCover() == null) ? null : new Image(new ByteArrayInputStream(book.getCover())));

        about.setText(book.getAbout());

        // te co trzeba wszystkie z bazy

        List<Series> seriesList = SeriesDAO.getDAO().getAll();
        series.set(seriesList);
        series.getSelectionModel().select(book.getSeries());
        series.setValue(book.getSeries());

        List<Publisher> publisherList = PublisherDAO.getDAO().getAll();
        publisher.set(publisherList);
        publisher.getSelectionModel().select(book.getPublisher());
        publisher.setValue(book.getPublisher());

        List<Author> authorList = AuthorDAO.getDAO().getAll();
        if(book.getAuthorList() != null){
            authorList.removeAll(book.getAuthorList());
            authorsList.setItems(FXCollections.observableArrayList(book.getAuthorList()));
        }
        authors.set(authorList);

        List<Category> categoryList = CategoryDAO.getDAO().getAll();
        if(book.getCategoryList() != null){
            categoryList.removeAll(book.getCategoryList());
            categoriesList.setItems(FXCollections.observableArrayList(book.getCategoryList()));
        }
        categories.set(categoryList);



        for(Node node : root.getChildren())
            node.setStyle("");

        doneStatus.setText("");
        saveBookStage.hide();
        saveBookStage.show();
    }



    // TODO this is tmp pls remove as well as the fxml button which triggers it
    public void tmpFunction(ActionEvent event)throws IOException{

        Book book = BookDAO.getDAO().findById(1L);
        setBookStage(book);
    }

    public void initialize(URL location, ResourceBundle resources) {
        mainController = this;
    }
}
