package POProject.controllers;

import POProject.customNodes.AnchorCategory;
import POProject.db.app.core.Category;
import POProject.db.app.db.CategoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class CategoryController {

    @FXML
    private AnchorCategory anchor;

    @FXML
    private Label name;

    @FXML
    private TextArea about;

    @FXML
    private void refresh(ActionEvent event){
        prepareNode(CategoryDAO.getDAO().findById(anchor.getCategory().getId()));
    }

    @FXML
    private void modify(ActionEvent event)throws IOException{
        MainController.getInstance().openSaveCategoryStage(anchor.getCategory());
    }

    @FXML
    private void remove(ActionEvent event){
        CategoryDAO.getDAO().delete(anchor.getCategory());
        MainController.getInstance().closeCategory();
    }

    public void prepareNode( Category category){
        anchor.setCategory(category);

        name.setText(category.getName());
        about.setText(category.getAbout());

    }

    public static Parent getNode(Category category) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/category.fxml"));
        Parent root = loader.load();
        CategoryController controller = loader.getController();
        controller.prepareNode(category);
        return root;
    }
}
