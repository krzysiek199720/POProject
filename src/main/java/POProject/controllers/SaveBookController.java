package POProject.controllers;

import POProject.customNodes.AnchorBook;
import POProject.customNodes.FilteredComboBox;
import POProject.db.app.core.*;
import POProject.db.app.db.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SaveBookController  implements Initializable {

    @FXML
    private AnchorBook anchor;

    @FXML
    private TextField title;

    @FXML
    private TextField ISBN;

    @FXML
    private TextField pageCount;

    @FXML
    private TextField releaseYear;

    @FXML
    private FilteredComboBox<Series> series;

    @FXML
    private FilteredComboBox<Publisher> publisher;

    @FXML
    private FilteredComboBox<Author> authors;

    @FXML
    private ListView<Author> authorsList;

    @FXML
    private FilteredComboBox<Category> categories;

    @FXML
    private ListView<Category> categoriesList;

    @FXML
    private ImageView cover;

    @FXML
    private TextArea about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Book book = anchor.getBook();

        doneStatus.setText("");

        boolean terminate = false;

        if(title.getText() == null || title.getText().equals("")){
            setNodeToErrorColor(title);
            terminate = true;
        }else setNodeToNormal(title);

        if(ISBN.getText() == null || ISBN.getText().equals("") || !ISBN.getText().matches("\\d*") || ISBN.getText().length() != 13){
            setNodeToErrorColor(ISBN);
            terminate = true;
        }else setNodeToNormal(ISBN);

        if(pageCount.getText() == null || pageCount.getText().equals("")){
            setNodeToErrorColor(pageCount);
            terminate = true;
        }else setNodeToNormal(pageCount);

        if(releaseYear.getText() == null || releaseYear.getText().equals("")){
            setNodeToErrorColor(releaseYear);
            terminate = true;
        }else  setNodeToNormal(releaseYear);

        if(series.getValue() == null){
            setNodeToErrorColor(series);
            terminate = true;
        }else setNodeToNormal(series);

        if(publisher.getValue() == null){
            setNodeToErrorColor(publisher);
            terminate = true;
        }else setNodeToNormal(publisher);

        if(authorsList.getItems().size() < 1){
            setNodeToErrorColor(authorsList);
            terminate = true;
        }else setNodeToNormal(authorsList);

        if(categoriesList.getItems().size() < 1){
            setNodeToErrorColor(categoriesList);
            terminate = true;
        }else setNodeToNormal(categoriesList);

        if(terminate) return;

        book.setTitle(title.getText());
        book.setISBN(ISBN.getText());
        book.setPageCount(Integer.valueOf(pageCount.getText()));
        book.setReleaseYear(Integer.valueOf(releaseYear.getText()));
        book.setSeries(series.getValue());
        book.setPublisher(publisher.getValue());
        book.setAbout(about.getText());

        List<Author> authorList = new ArrayList<>();
        for(Object author : authorsList.getItems().toArray())
            authorList.add((Author)author);
        book.setAuthorList( authorList );

        List<Category> categoryList = new ArrayList<>();
        for(Object category : categoriesList.getItems().toArray())
            categoryList.add((Category)category);
        book.setCategoryList( categoryList );

        BookDAO.getDAO().saveOrUpdate(book);

        doneStatus.setText("Saved");
    }

    private void setNodeToErrorColor(Node node){
        node.setStyle("-fx-background-color: #ff0000");
    }
    private void setNodeToNormal(Node node){node.setStyle("");}

    @FXML
    private void refreshSeries(ActionEvent event){
        List<Series> seriesList = SeriesDAO.getDAO().getAll();
        series.set(seriesList);
    }

    @FXML
    private void refreshPublisher(ActionEvent event){
        List<Publisher> publisherList = PublisherDAO.getDAO().getAll();
        publisher.set(publisherList);
    }

    @FXML
    private void refreshAuthor(ActionEvent event){
        List<Author> authorList = AuthorDAO.getDAO().getAll();
        authorList.removeAll(authorsList.getItems());
        authors.set(authorList);
    }

    @FXML
    private void refreshCategory(ActionEvent event){
        List<Category> categoryList = CategoryDAO.getDAO().getAll();
        categoryList.removeAll(categoriesList.getItems());
        categories.set(categoryList);
    }

    @FXML
    private void addAuthor(ActionEvent event){
        Author newAuthor = authors.getValue();
        authorsList.getItems().add(newAuthor);
        authors.getSelectionModel().clearSelection();
        authors.setValue(null);
        authors.remD(newAuthor);
    }

    @FXML
    private void addCategory(ActionEvent event){
        Category newCategory = categories.getValue();
        categoriesList.getItems().add(newCategory);
        categories.getSelectionModel().clearSelection();
        categories.setValue(null);
        categories.remD(newCategory);

    }

    @FXML
    private void removeAuthor(ActionEvent event){
        Author remAuthor = authorsList.getSelectionModel().getSelectedItem();
        authors.addD(remAuthor);
        authorsList.getItems().remove(remAuthor);
    }

    @FXML
    private void removeCategory(ActionEvent event){
        Category remCategory = categoriesList.getSelectionModel().getSelectedItem();
        categoriesList.getItems().remove(remCategory);
        categories.addD(remCategory);
    }

    @FXML
    private void setCover(ActionEvent event) throws IOException {
        FileChooser filechooser = new FileChooser();
        File selected = filechooser.showOpenDialog(null);
        if(selected == null)
            throw new NullPointerException();

        BufferedImage bImage = ImageIO.read(new File(selected.getPath()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String fileType = selected.getName().substring(selected.getName().lastIndexOf(".")+1);
        ImageIO.write(bImage, fileType , bos );
        byte [] data = bos.toByteArray();

        anchor.getBook().setCover( data );

        Image image = new Image(new ByteArrayInputStream(data));
        cover.setImage( image );

    }

    @FXML
    private void removeCover(ActionEvent event){
        cover.setImage(null);
        anchor.getBook().setCover(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFormatter<Integer> intFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                c -> Pattern.matches("\\d*", c.getText()) ? c : null );
        pageCount.setTextFormatter(intFormatter);

        TextFormatter<Integer> YearFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                9999,
                c -> Pattern.matches("\\d*", c.getText()) ? c : null );
        releaseYear.setTextFormatter(YearFormatter);
    }
}
