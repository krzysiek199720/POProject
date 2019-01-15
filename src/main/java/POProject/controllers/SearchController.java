package POProject.controllers;

import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.customNodes.AnchorSearchElem;
import POProject.db.app.db.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
            Parent node = SearchElemController.getNode(book);
            res.getChildren().add(node);
        }
        return res;
    }

}
