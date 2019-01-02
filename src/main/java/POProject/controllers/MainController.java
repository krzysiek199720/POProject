package POProject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button searchButton;

    private Stage searchStage;

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


    //Nie wiem czy to na pewno trzeba miec
    public void initialize(URL location, ResourceBundle resources) {


    }
}
