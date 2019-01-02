package POProject.controllers;

import POProject.db.app.core.Author;
import POProject.db.app.core.Book;
import POProject.test.AnchorElem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    private TextField textField;

    @FXML
    private Button searchButton;

    @FXML
    private ScrollPane searchList;

    public void search(ActionEvent actionEvent) throws IOException{

        // TESTING TESTING
        Author a = new Author();
        a.setFirstName("A");
        a.setLastName("B");
        List<Author> authorList = new ArrayList<>();
        authorList.add(a);

        Book b = new Book();

        BufferedImage bImage = ImageIO.read(new File("C:\\Users\\krzych\\Desktop\\example.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        byte [] data = bos.toByteArray();
        b.setCover(data);


        b.setAuthorList(authorList);

        b.setTitle("Tytulik xdd");

        b.setAbout("trolloolloolololololololololololololo");

        List<Book> bookList= new ArrayList<>();
        bookList.add(b);
        bookList.add(b);
        bookList.add(b);
        bookList.add(b);
        bookList.add(b);
        bookList.add(b);
        bookList.add(b);
        bookList.add(b);


        searchList.setContent(generateList(bookList));


        // ~~TESTING ~~TESTING

    }

    private VBox generateList(List<Book> bookList) throws IOException{
        VBox res = new VBox();
        for(Book book : bookList){
            AnchorElem node;
            node = FXMLLoader.load(SearchController.class.getResource("/fxmls/searchElem.fxml"));

            //0 - cover
            //1 - title
            //2 - UNEDITABLE    TODO mozna zmienic zeby sie Author: zmnienial na Authors: // ale to nie teraz bo misie nie chce
            //3 - authorName
            //4 - about

            ImageView cover = (ImageView) node.getChildren().get(0);
            Image image = new Image(new ByteArrayInputStream(book.getCover()));
            cover.setImage( image );

            Label title = (Label) node.getChildren().get(1);
            title.setText(book.getTitle());

            Label authorName = (Label) node.getChildren().get(3);
            authorName.setText("");
            for(Author author : book.getAuthorList())
                authorName.setText( authorName.getText() + author.getFirstName() + " " + author.getLastName() + "    ");

            Label about = (Label) node.getChildren().get(4);
            about.setText(book.getAbout());

            res.getChildren().add(node);
        }
        return res;
    }

}
