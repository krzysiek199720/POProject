package POProject.controllers;

import POProject.customNodes.*;
import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.db.app.core.Category;
import POProject.db.app.core.Series;
import POProject.db.app.db.BookDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BookController {

    @FXML
    private AnchorBook anchor;

    @FXML
    private Label title;

    @FXML
    private Label ISBN;

    @FXML
    private Label pages;

    @FXML
    private Label year;

    @FXML
    private HBox series;

    @FXML
    private HBox publisher;

    @FXML
    private HBox authors;

    @FXML
    private HBox categories;

    @FXML
    private ImageView cover;

    @FXML
    private TextArea about;

    @FXML
    private void openModify()throws IOException{
        MainController.getInstance().openSaveBookStage(anchor.getBook());
    }

    @FXML
    private void remove(){
        BookDAO.getDAO().delete(anchor.getBook());
        MainController.getInstance().closeBook();
    }

    @FXML
    private void refresh(){
        prepareNode(BookDAO.getDAO().findById(anchor.getBook().getId()));
    }

    public void prepareNode( Book book){
        anchor.setBook(book);

        title.setText(book.getTitle());
        ISBN.setText(book.getISBN());
        pages.setText(book.getPageCount().toString());
        year.setText(book.getReleaseYear().toString());

        series.getChildren().removeAll(series.getChildren());
        SeriesLabel serName = new SeriesLabel(book.getSeries().toString(), book.getSeries());
        serName.setOnMouseClicked(event -> {
            try{
                MainController.getInstance().openSeries(serName.getSeries());
            }catch(IOException e){}
        });
        serName.paddingProperty().setValue(new Insets(0,20,0,0));
        serName.setStyle("-fx-text-fill: blue");
        series.getChildren().add(serName);

        publisher.getChildren().removeAll(publisher.getChildren());
        PublisherLabel pubName = new PublisherLabel(book.getPublisher().toString(), book.getPublisher());
        pubName.setOnMouseClicked(event -> {
            try{
                MainController.getInstance().openPublisher(pubName.getPublisher());
            }catch(IOException e){}
        });
        pubName.paddingProperty().setValue(new Insets(0,20,0,0));
        pubName.setStyle("-fx-text-fill: blue");
        publisher.getChildren().add(pubName);

        cover.setImage((book.getCover() != null) ? new Image(new ByteArrayInputStream(book.getCover())) : null);
        about.setText(book.getAbout());

        categories.getChildren().removeAll(categories.getChildren());
        for(Category category : book.getCategoryList()){
            CategoryLabel name = new CategoryLabel(category.toString(), category);

            name.setOnMouseClicked(event -> {
                try{
                    MainController.getInstance().openCategory(name.getCategory());
                }catch(IOException e){}
            });

            name.paddingProperty().setValue(new Insets(0,20,0,0));
            name.setStyle("-fx-text-fill: blue");

            categories.getChildren().add(name);
        }

        authors.getChildren().removeAll(authors.getChildren());
        for(Author author : book.getAuthorList()){
            AuthorLabel name = new AuthorLabel(author.toString(), author);

            name.setOnMouseClicked(event -> {
                try{
                    MainController.getInstance().openAuthor(name.getAuthor());
                }catch(IOException e){}
            });

            name.paddingProperty().setValue(new Insets(0,20,0,0));
            name.setStyle("-fx-text-fill: blue");

            authors.getChildren().add(name);
        }


    }

    public static Parent getNode(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/book.fxml"));
        Parent root = loader.load();
        BookController controller = loader.getController();
        controller.prepareNode(book);
        return root;
    }


}
