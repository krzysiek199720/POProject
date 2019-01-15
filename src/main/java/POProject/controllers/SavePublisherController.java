package POProject.controllers;

import POProject.customNodes.AnchorPublisher;
import POProject.db.app.core.Publisher;
import POProject.db.app.db.PublisherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SavePublisherController {

    @FXML
    private AnchorPublisher anchor;

    @FXML
    private TextField name;

    @FXML
    private TextArea about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Publisher publisher = anchor.getPublisher();

        doneStatus.setText("");

        boolean terminate = false;

        if(name.getText() == null || name.getText().equals("")){
            setNodeToErrorColor(name);
            terminate = true;
        }else setNodeToNormal(name);

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
    private void setNodeToNormal(Node node){node.setStyle("");}

    public void prepareNode( Publisher publisher){
        anchor.setPublisher(publisher);

        name.setText(publisher.getName());
        about.setText(publisher.getAbout());

        doneStatus.setText("");

        for(Node node : anchor.getChildren())
            node.setStyle("");
    }

    public static Parent getNode(Publisher publisher) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/savePublisher.fxml"));
        Parent root = loader.load();
        SavePublisherController controller = loader.getController();
        controller.prepareNode(publisher);
        return root;
    }
}
