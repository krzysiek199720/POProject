package POProject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SaveAuthorController {

    @FXML// TODO for now anchor pane may want to change as written below
    private AnchorPane anchor;

    @FXML
    private Button doneButton;

    @FXML
    private TextField name;

    @FXML
    private TextField about;

    public void done(ActionEvent actionEvent){
        //TODO saveOrUpdate
        // pewnie zrobie tak ze przy tworzeniu bedzie sie podawac obiekt Category albo nie
        // wiec jak null to nowy
    }

}
