package POProject.controllers;

import POProject.customNodes.AnchorAuthor;
import POProject.customNodes.AnchorCategory;
import POProject.customNodes.AnchorPublisher;
import POProject.customNodes.AnchorSeries;
import POProject.db.app.core.Author;
import POProject.db.app.core.Category;
import POProject.db.app.core.Publisher;
import POProject.db.app.core.Series;
import POProject.db.app.db.CategoryDAO;
import POProject.db.app.db.SeriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button searchButton;

    private Stage searchStage;
    private Stage savePublisherStage;
    private Stage saveCategoryStage;
    private Stage saveSeriesStage;
    private Stage saveAuthorStage;


    private static MainController mainController;

    public static MainController getInstance(){
        return mainController;
    }

    public void searchAction(ActionEvent event){

        if(searchStage != null){
            searchStage.show();
            System.out.println("aaa");
            return;
        }

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxmls/search.fxml"));
            searchStage = new Stage();
            searchStage.setTitle("My New Stage Title");
            searchStage.setScene(new Scene(root));
            searchStage.resizableProperty().setValue(Boolean.FALSE);
            searchStage.initStyle(StageStyle.UTILITY);
            searchStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPublisherStageAction(ActionEvent event){
        setPublisherStage(new Publisher());
    }
    public void setPublisherStage(Publisher publisher){

        AnchorPublisher root;

        if(savePublisherStage != null){

            root = (AnchorPublisher) savePublisherStage.getScene().getRoot();
            root.setPublisher(publisher);

            savePublisherStage.show();
            System.out.println("aaa");
            return;
        }

        try {
            root = FXMLLoader.load(getClass().getResource("/fxmls/savePublisher.fxml"));
            savePublisherStage = new Stage();
            savePublisherStage.setTitle("My New Stage Title");
            savePublisherStage.setScene(new Scene(root));
            savePublisherStage.resizableProperty().setValue(Boolean.FALSE);
            savePublisherStage.initStyle(StageStyle.UTILITY);
            savePublisherStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openSeriesStageAction(ActionEvent event) throws IOException{
        setSeriesStage(new Series());
    }
    public void setSeriesStage(Series series) throws IOException{

        AnchorSeries root;

        if(saveSeriesStage != null){
            root = (AnchorSeries) saveSeriesStage.getScene().getRoot();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveSeries.fxml"));
            saveSeriesStage = new Stage();
            saveSeriesStage.setTitle("My New Stage Title");
            saveSeriesStage.setScene(new Scene(root));
            saveSeriesStage.resizableProperty().setValue(Boolean.FALSE);
            saveSeriesStage.initStyle(StageStyle.UTILITY);
        }
        root.setSeries(series);

        //  0 - name
        //  1 - about
        //  2 - save Status

        TextField name = (TextField)root.getChildren().get(0);
        TextField about = (TextField)root.getChildren().get(1);

        name.setText(series.getName());
        about.setText(series.getAbout());

        Label saveStatus = (Label) root.getChildren().get(2);
        saveStatus.setText("");

        saveSeriesStage.show();
    }

    public void openCategoryStageAction(ActionEvent event)throws IOException{
        setCategoryStage(new Category());
    }
    public void setCategoryStage(Category category) throws IOException{

        AnchorCategory root;

        if(saveCategoryStage != null){

            root = (AnchorCategory) saveCategoryStage.getScene().getRoot();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveCategory.fxml"));
            saveCategoryStage = new Stage();
            saveCategoryStage.setTitle("My New Stage Title");
            saveCategoryStage.setScene(new Scene(root));
            saveCategoryStage.resizableProperty().setValue(Boolean.FALSE);
            saveCategoryStage.initStyle(StageStyle.UTILITY);

        }

        root.setCategory(category);

        //  0 - name
        //  1 - about
        //  2 - save Status
        TextField name = (TextField)root.getChildren().get(0);
        TextField about = (TextField)root.getChildren().get(1);

        name.setText(category.getName());
        about.setText(category.getAbout());

        Label saveStatus = (Label) root.getChildren().get(2);
        saveStatus.setText("");
        saveCategoryStage.show();
    }

    public void openAuthorStageAction(ActionEvent event){
        setAuthorStage(new Author());
    }
    public void setAuthorStage(Author author){

        AnchorAuthor root;

        if(saveAuthorStage != null){

            root = (AnchorAuthor) saveAuthorStage.getScene().getRoot();
            root.setAuthor(author);


            saveAuthorStage.show();
            System.out.println("aaa");
            return;
        }

        try {
            root = FXMLLoader.load(getClass().getResource("/fxmls/saveAuthor.fxml"));
            root.setAuthor(author);
            saveAuthorStage = new Stage();
            saveAuthorStage.setTitle("My New Stage Title");
            saveAuthorStage.setScene(new Scene(root));
            saveAuthorStage.resizableProperty().setValue(Boolean.FALSE);
            saveAuthorStage.initStyle(StageStyle.UTILITY);
            saveAuthorStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO this is tmp pls remove as well as the fxml button which triggers it
    public void tmpFunction(ActionEvent event)throws IOException{
        Series series = SeriesDAO.getDAO().findById(1L);
        setSeriesStage(series);
    }

    public void initialize(URL location, ResourceBundle resources) {
        mainController = this;
    }
}
