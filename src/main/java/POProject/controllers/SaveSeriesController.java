package POProject.controllers;

import POProject.customNodes.AnchorSeries;
import POProject.db.app.core.Series;
import POProject.db.app.db.SeriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    public void prepareNode( Series series){
        anchor.setSeries(series);

        name.setText(series.getName());
        about.setText(series.getAbout());

        doneStatus.setText("");

        for(Node node : anchor.getChildren())
            node.setStyle("");
    }

    public static Parent getNode(Series series) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/saveSeries.fxml"));
        Parent root = loader.load();
        SaveSeriesController controller = loader.getController();
        controller.prepareNode(series);
        return root;
    }
}
