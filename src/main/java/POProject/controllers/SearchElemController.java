package POProject.controllers;

import POProject.customNodes.AnchorSearchElem;
import POProject.customNodes.AuthorLabel;
import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SearchElemController {

    @FXML
    private AnchorSearchElem anchor;

    @FXML
    private ImageView cover;

    @FXML
    private Label title;

    @FXML
    private HBox authorNameHBox;

    @FXML
    private Label about;

    @FXML
    private void goToBook(){
        try{
        MainController.getInstance().openBook(anchor.getBook());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void prepareNode( Book book){
        anchor.setBook(book);
        cover.setImage((book.getCover() != null) ? new Image(new ByteArrayInputStream(book.getCover())) : null);
        title.setText(book.getTitle());
        about.setText(book.getAbout());
        for(Author author : book.getAuthorList()){
            AuthorLabel name = new AuthorLabel(author.toString(), author);

            name.setOnMouseClicked(event -> {
                try{
                    MainController.getInstance().openAuthor(name.getAuthor());
                }catch(IOException e){}
            });

            name.paddingProperty().setValue(new Insets(0,20,0,0));
            name.setStyle("-fx-text-fill: blue");

            authorNameHBox.getChildren().add(name);
        }
    }

    public static Parent getNode(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader(AuthorController.class.getResource("/fxmls/searchElem.fxml"));
        Parent root = loader.load();
        SearchElemController controller = loader.getController();
        controller.prepareNode(book);
        return root;
    }
}
