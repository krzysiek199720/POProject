package POProject.controllers;

import POProject.customNodes.AnchorAuthor;
import POProject.db.app.core.Author;
import POProject.db.app.core.enums.Sex;
import POProject.db.app.db.AuthorDAO;
import POProject.db.app.db.BookDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class AuthorController {

    @FXML
    private AnchorAuthor anchor;

    @FXML
    private Label name;

    @FXML
    private Label sex;

    @FXML
    private Label dob;

    @FXML
    private Label dod;

    @FXML
    private Label pob;

    @FXML
    private ImageView photo;

    @FXML
    private TextArea about;

    @FXML
    private void openModify()throws IOException{
        MainController.getInstance().openSaveAuthorStage(anchor.getAuthor());
    }

    @FXML
    private void remove(){
        AuthorDAO.getDAO().delete(anchor.getAuthor());
        MainController.getInstance().closeAuthor();
    }

    @FXML
    private void refresh(){
        prepareNode(AuthorDAO.getDAO().findById(anchor.getAuthor().getId()));
    }

    public void prepareNode( Author author){
        anchor.setAuthor(author);

        name.setText(author.toString());
        sex.setText((author.getSex() == Sex.male) ? "Male" : "Female");
        dob.setText((author.getDateOfBirth() != null) ? new SimpleDateFormat("dd/MM/yyyy").format(author.getDateOfBirth()) : "Unknown");
        dod.setText((author.getDateOfDeath() != null) ? new SimpleDateFormat("dd/MM/yyyy").format(author.getDateOfDeath()) : "Unknown");
        pob.setText((author.getPlaceOfBirth() != null && !author.getPlaceOfBirth().equals("")) ? author.getPlaceOfBirth() : "Unknown");
        photo.setImage((author.getPhoto() != null) ? new Image(new ByteArrayInputStream(author.getPhoto())) : null);
        about.setText(author.getAbout());
    }

    public static Parent getNode(Author author) throws IOException {
        FXMLLoader loader = new FXMLLoader(AuthorController.class.getResource("/fxmls/author.fxml"));
        Parent root = loader.load();
        AuthorController controller = loader.getController();
        controller.prepareNode(author);
        return root;
    }

}
