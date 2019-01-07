package POProject.controllers;

import POProject.customNodes.AnchorCategory;
import POProject.db.app.core.Category;
import POProject.db.app.db.CategoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SaveCategoryController {

    @FXML
    private AnchorCategory anchor;

    @FXML
    private TextField name;

    @FXML
    private TextField about;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){

        Category category = anchor.getCategory();

        category.setName(name.getText());
        category.setAbout(about.getText());

        //TODO saveOrUpdate

        CategoryDAO.getDAO().saveOrUpdate(category);

        doneStatus.setText("Saved");

    }

}
