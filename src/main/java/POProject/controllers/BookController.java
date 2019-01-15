package POProject.controllers;

import POProject.customNodes.AnchorBook;
import POProject.customNodes.AuthorLabel;
import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.db.app.core.Category;
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
    private Label series;

    @FXML
    private Label publisher;

    @FXML
    private HBox authors;

    @FXML
    private Label categories;

    @FXML
    private ImageView cover;

    @FXML
    private TextArea about;

    @FXML
    private void openModify()throws IOException{
        MainController.getInstance().openSaveBookStage(anchor.getBook());
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
        series.setText(book.getSeries().toString());
        publisher.setText(book.getPublisher().toString());
        cover.setImage((book.getCover() != null) ? new Image(new ByteArrayInputStream(book.getCover())) : null);
        about.setText(book.getAbout());

        categories.setText("");
        for(Category category : book.getCategoryList())
            categories.setText(categories.getText() + category.toString() + "\t");

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
