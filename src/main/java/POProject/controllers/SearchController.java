package POProject.controllers;

import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.customNodes.AnchorSearchElem;
import POProject.db.app.db.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    @FXML
    private TextField title;

    @FXML
    private ScrollPane searchList;

    public void search(ActionEvent actionEvent) throws IOException{

        List<Book> bookList = BookDAO.getDAO().getByTitle( title.getText() );

        searchList.setContent(generateList(bookList));
    }

    private VBox generateList(List<Book> bookList) throws IOException{
        VBox res = new VBox();
        for(Book book : bookList){
            AnchorSearchElem node;
            node = FXMLLoader.load(SearchController.class.getResource("/fxmls/searchElem.fxml"));

            node.setBook(book);

            //0 - cover
            //1 - title
            //2 - author: label
            //3 - authorName
            //4 - about

            ImageView cover = (ImageView) node.getChildren().get(0);
            if(book.getCover() != null){
                Image image = new Image(new ByteArrayInputStream(book.getCover()));
                cover.setImage( image );
            }else
                cover.setImage(null);

            Label title = (Label) node.getChildren().get(1);
            title.setText(book.getTitle());

            Label authorName = (Label) node.getChildren().get(3);
            authorName.setText("");
            for(Author author : book.getAuthorList())
                authorName.setText( authorName.getText() + author.getFirstName() + " " + author.getLastName() + "    ");

            Label about = (Label) node.getChildren().get(4);
            about.setText(book.getAbout());

            Label autLabel = (Label) node.getChildren().get(2);
            autLabel.setText( (book.getAuthorList().size() > 1) ? "Author:" : "Authors:" );

            res.getChildren().add(node);
        }
        return res;
    }

}
