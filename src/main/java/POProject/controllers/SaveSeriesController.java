package POProject.controllers;

import POProject.customNodes.AnchorSeries;
import POProject.db.app.core.Series;
import POProject.db.app.db.SeriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SaveSeriesController {

    @FXML
    private AnchorSeries anchor;

    @FXML
    private TextField name;

    @FXML
    private TextField about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Series series = anchor.getSeries();

        series.setName(name.getText());
        series.setAbout(about.getText());

        //TODO saveOrUpdate

        SeriesDAO.getDAO().saveOrUpdate(series);

        doneStatus.setText("Saved");
    }

}
