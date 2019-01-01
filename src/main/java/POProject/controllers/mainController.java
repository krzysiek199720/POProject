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

public class mainController implements Initializable {

    @FXML
    private Button searchButton;

    public void searchAction(ActionEvent event){
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxmls/main.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("aaa");
    }


    //Nie wiem czy to na pewno trzeba miec
    public void initialize(URL location, ResourceBundle resources) {


    }
}
