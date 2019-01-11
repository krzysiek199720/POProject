package POProject.controllers;

import POProject.customNodes.AnchorCategory;
import POProject.db.app.core.Category;
import POProject.db.app.db.CategoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SaveCategoryController {

    @FXML
    private AnchorCategory anchor;

    @FXML
    private TextField name;

    @FXML
    private TextArea about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){

        Category category = anchor.getCategory();

        boolean terminate = false;

        if(name.getText() == null || name.getText().equals("")){
            setNodeToErrorColor(name);
            terminate = true;
        }

        if(terminate)
            return;

        category.setName(name.getText());
        category.setAbout(about.getText());

        CategoryDAO.getDAO().saveOrUpdate(category);

        doneStatus.setText("Saved");

    }

    private void setNodeToErrorColor(Node node){
        node.setStyle("-fx-background-color: #ff0000");
    }

}
