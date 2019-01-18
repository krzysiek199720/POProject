package POProject.controllers;

import POProject.customNodes.AnchorCategory;
import POProject.customNodes.AnchorSeries;
import POProject.db.app.core.Category;
import POProject.db.app.core.Series;
import POProject.db.app.db.CategoryDAO;
import POProject.db.app.db.SeriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class SeriesController {

    @FXML
    private AnchorSeries anchor;

    @FXML
    private Label name;

    @FXML
    private TextArea about;

    @FXML
    private void refresh(ActionEvent event){
        prepareNode(SeriesDAO.getDAO().findById(anchor.getSeries().getId()));
    }

    @FXML
    private void modify(ActionEvent event)throws IOException{
        MainController.getInstance().openSaveSeriesStage(anchor.getSeries());
    }

    @FXML
    private void remove(ActionEvent event){
        SeriesDAO.getDAO().delete(anchor.getSeries());
        MainController.getInstance().closeSeries();
    }

    public void prepareNode( Series series){
        anchor.setSeries(series);

        name.setText(series.getName());
        about.setText(series.getAbout());

    }

    public static Parent getNode(Series series) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/series.fxml"));
        Parent root = loader.load();
        SeriesController controller = loader.getController();
        controller.prepareNode(series);
        return root;
    }
}
