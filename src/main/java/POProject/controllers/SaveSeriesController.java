package POProject.controllers;

import POProject.customNodes.AnchorSeries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SaveSeriesController {

    @FXML
    private AnchorSeries anchor;

    @FXML
    private Button doneButton;

    @FXML
    private TextField name;

    @FXML
    private TextField about;

    public void done(ActionEvent actionEvent){
        //TODO saveOrUpdate
        // pewnie zrobie tak ze przy tworzeniu bedzie sie podawac obiekt Series albo nie
        // wiec jak null to nowy
    }

}
