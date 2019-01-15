package POProject.controllers;

import POProject.customNodes.AnchorCategory;
import POProject.db.app.core.Category;
import POProject.db.app.db.CategoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

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

        doneStatus.setText("");

        boolean terminate = false;

        if(name.getText() == null || name.getText().equals("")){
            setNodeToErrorColor(name);
            terminate = true;
        }else setNodeToNormal(name);

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
    private void setNodeToNormal(Node node){node.setStyle("");}

    public void prepareNode( Category category){
        anchor.setCategory(category);

        name.setText(category.getName());
        about.setText(category.getAbout());

        doneStatus.setText("");

        for(Node node : anchor.getChildren())
            node.setStyle("");
    }

    public static Parent getNode(Category category) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/saveCategory.fxml"));
        Parent root = loader.load();
        SaveCategoryController controller = loader.getController();
        controller.prepareNode(category);
        return root;
    }
}
