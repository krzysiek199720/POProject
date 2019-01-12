package POProject.controllers;

import POProject.customNodes.AnchorAuthor;
import POProject.db.app.core.Author;
import POProject.db.app.core.Category;
import POProject.db.app.core.Publisher;
import POProject.db.app.core.Series;
import POProject.db.app.core.enums.Sex;
import POProject.db.app.db.AuthorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SaveBookController  implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextField ISBN;

    @FXML
    private TextField pageCount;

    @FXML
    private TextField releaseYear;

    @FXML
    private ComboBox<Series> series;

    @FXML
    private ComboBox<Publisher> publisher;

    @FXML
    private ComboBox<Author> authors;

    @FXML
    private ListView<Author> authorsList;

    @FXML
    private ComboBox<Category> categories;

    @FXML
    private ListView<Category> categoriesList;

    @FXML
    private ImageView cover;

    @FXML
    private TextArea about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){

        doneStatus.setText("Saved");
    }

    private void setNodeToErrorColor(Node node){
        node.setStyle("-fx-background-color: #ff0000");
    }

    public void setCover(ActionEvent event) throws IOException {
//        FileChooser filechooser = new FileChooser();
//        File selected = filechooser.showOpenDialog(null);
//        if(selected == null)
//            throw new NullPointerException();
//
//        BufferedImage bImage = ImageIO.read(new File(selected.getPath()));
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        String fileType = selected.getName().substring(selected.getName().lastIndexOf(".")+1);
//        ImageIO.write(bImage, fileType , bos );
//        byte [] data = bos.toByteArray();
//
//        anchor.getAuthor().setPhoto( data );
//
//        Image image = new Image(new ByteArrayInputStream(data));
//        photo.setImage( image );

    }

    public void removeCover(ActionEvent event){
//        photo.setImage(null);
//        anchor.getAuthor().setPhoto(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFormatter<Integer> intFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                c -> Pattern.matches("\\d*", c.getText()) ? c : null );
        pageCount.setTextFormatter(intFormatter);

        TextFormatter<Integer> ISBNFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                c -> Pattern.matches("\\d{13}", c.getText()) ? c : null );
        ISBN.setTextFormatter(ISBNFormatter);
    }
}
