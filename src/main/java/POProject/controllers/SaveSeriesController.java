package POProject.controllers;

import POProject.customNodes.AnchorSeries;
import POProject.db.app.core.Series;
import POProject.db.app.db.SeriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SaveSeriesController {

    @FXML
    private AnchorSeries anchor;

    @FXML
    private TextField name;

    @FXML
    private TextArea about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Series series = anchor.getSeries();

        doneStatus.setText("");

        boolean terminate = false;

        if(name.getText() == null || name.getText().equals("")){
            setNodeToErrorColor(name);
            terminate = true;
        }else setNodeToNormal(name);

        if(terminate)
            return;

        series.setName(name.getText());
        series.setAbout(about.getText());

        SeriesDAO.getDAO().saveOrUpdate(series);

        doneStatus.setText("Saved");
    }

    private void setNodeToErrorColor(Node node){
        node.setStyle("-fx-background-color: #ff0000");
    }
    private void setNodeToNormal(Node node){node.setStyle("");}
}
