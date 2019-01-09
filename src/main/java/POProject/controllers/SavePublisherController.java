package POProject.controllers;

import POProject.customNodes.AnchorPublisher;
import POProject.db.app.core.Publisher;
import POProject.db.app.db.PublisherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SavePublisherController {

    @FXML
    private AnchorPublisher anchor;

    @FXML
    private TextField name;

    @FXML
    private TextField about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Publisher publisher = anchor.getPublisher();

        boolean terminate = false;

        if(name.getText() == null || name.getText().equals("")){
            setNodeToErrorColor(name);
            terminate = true;
        }

        if(terminate)
            return;

        publisher.setName(name.getText());
        publisher.setAbout(about.getText());

        PublisherDAO.getDAO().saveOrUpdate(publisher);

        doneStatus.setText("Saved");
    }

    private void setNodeToErrorColor(Node node){
        node.setStyle("-fx-background-color: #ff0000");
    }
}
