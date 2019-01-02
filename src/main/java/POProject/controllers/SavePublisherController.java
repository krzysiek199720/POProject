package POProject.controllers;

import POProject.customNodes.AnchorPublisher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SavePublisherController {

    @FXML
    private AnchorPublisher anchor;

    @FXML
    private Button doneButton;

    @FXML
    private TextField name;

    @FXML
    private TextField about;

    public void done(ActionEvent actionEvent){
        //TODO saveOrUpdate
        // pewnie zrobie tak ze przy tworzeniu bedzie sie podawac obiekt Publisher albo nie
        // wiec jak null to nowy
    }

}
