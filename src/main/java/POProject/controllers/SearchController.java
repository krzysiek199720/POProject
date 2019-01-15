package POProject.controllers;

import POProject.db.app.core.Book;
import POProject.db.app.db.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
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
