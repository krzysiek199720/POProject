package POProject.controllers;

import POProject.customNodes.AnchorPublisher;
import POProject.customNodes.AnchorSeries;
import POProject.db.app.core.Publisher;
import POProject.db.app.core.Series;
import POProject.db.app.db.PublisherDAO;
import POProject.db.app.db.SeriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class PublisherController {

    @FXML
    private AnchorPublisher anchor;

    @FXML
    private Label name;

    @FXML
    private TextArea about;

    @FXML
    private void refresh(ActionEvent event){
        prepareNode(PublisherDAO.getDAO().findById(anchor.getPublisher().getId()));
    }

    @FXML
    private void modify(ActionEvent event)throws IOException{
        MainController.getInstance().openSavePublisherStage(anchor.getPublisher());
    }

    @FXML
    private void remove(ActionEvent event){
        PublisherDAO.getDAO().delete(anchor.getPublisher());
        MainController.getInstance().closeSeries();
    }

    public void prepareNode( Publisher publisher){
        anchor.setPublisher(publisher);

        name.setText(publisher.getName());
        about.setText(publisher.getAbout());

    }

    public static Parent getNode(Publisher publisher) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/publisher.fxml"));
        Parent root = loader.load();
        PublisherController controller = loader.getController();
        controller.prepareNode(publisher);
        return root;
    }
}
