package POProject.controllers;

import POProject.customNodes.FilteredComboBox;
import POProject.db.app.api.SearchApi;
import POProject.db.app.core.*;
import POProject.db.app.db.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SearchController implements Initializable {

    @FXML
    private ScrollPane searchList;

    @FXML
    private TextField title;

    @FXML
    private TextField ISBN;

    @FXML
    private TextField year;

    @FXML
    private FilteredComboBox<Author> author;

    @FXML
    private FilteredComboBox<Series> series;

    @FXML
    private FilteredComboBox<Category> category;

    @FXML
    private FilteredComboBox<Publisher> publisher;

    @FXML
    private CheckBox advCheck;

    @FXML
    private AnchorPane adv;

    @FXML
    private void advCheck(){
        adv.setDisable(!advCheck.isSelected());
        if(!adv.isDisabled())
            return;
        reset();
    }

    @FXML
    private void reset(ActionEvent event){
        reset();
    }

    private void reset(){
        title.setText("");
        year.setText("");
        ISBN.setText("");

        author.getSelectionModel().clearSelection();
        author.setValue(null);
        series.getSelectionModel().clearSelection();
        series.setValue(null);
        category.getSelectionModel().clearSelection();
        category.setValue(null);
        publisher.getSelectionModel().clearSelection();
        publisher.setValue(null);
    }

    @FXML
    public void search(ActionEvent actionEvent) throws IOException{
        //TODO api i takie tam
        if(adv.isDisabled())
        {
            List<Book> bookList = BookDAO.getDAO().getByTitle( title.getText() );
            searchList.setContent(generateList(bookList));
            return;
        }
        SearchApi api = new SearchApi();
        api.setTitle(title.getText());
        api.setISBN(ISBN.getText());
        if(year.getText() == null || year.getText().equals(""))
            api.setYear(null);
        else
            api.setYear(Integer.valueOf(year.getText()));
        api.setSeries(series.getSelectedItem());
        api.setPublisher(publisher.getSelectedItem());
        api.setAuthor(author.getSelectedItem());
        api.setCategory(category.getSelectedItem());
        List<Book> bookList = BookDAO.getDAO().getBySearchApi(api );
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

    private void prepareNode(){

        List<Series> seriesList = SeriesDAO.getDAO().getAll();
        series.set(seriesList);
        series.getSelectionModel().select(null);
        series.setValue(null);

        List<Publisher> publisherList = PublisherDAO.getDAO().getAll();
        publisher.set(publisherList);
        publisher.getSelectionModel().select(null);
        publisher.setValue(null);

        List<Author> authorList = AuthorDAO.getDAO().getAll();
        author.set(authorList);
        author.getSelectionModel().select(null);
        author.setValue(null);

        List<Category> categoryList = CategoryDAO.getDAO().getAll();
        category.set(categoryList);
        category.getSelectionModel().select(null);
        category.setValue(null);
    }

    public static Parent getNode() throws IOException {
        FXMLLoader loader = new FXMLLoader(AuthorController.class.getResource("/fxmls/search.fxml"));
        Parent root = loader.load();
        SearchController controller = loader.getController();
        controller.prepareNode();
        return root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TextFormatter<Integer> YearFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                null,
                c -> Pattern.matches("\\d*", c.getText()) ? c : null );
        year.setTextFormatter(YearFormatter);
    }

}
