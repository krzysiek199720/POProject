package POProject.controllers;

import POProject.customNodes.AnchorAuthor;
import POProject.db.app.core.Author;
import POProject.db.app.core.enums.Sex;
import POProject.db.app.db.AuthorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;

public class SaveAuthorController {

    @FXML// TODO for now anchor pane may want to change as written below
    private AnchorAuthor anchor;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker dOB;

    @FXML
    private DatePicker dOD;

    @FXML
    private TextArea about;

    @FXML
    private TextField placeOB;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private ImageView photo;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Author author = anchor.getAuthor();

        doneStatus.setText("");

        boolean terminate = false;

        if(firstName.getText() == null || firstName.getText().equals("")){
            setNodeToErrorColor(firstName);
            terminate = true;
        }else setNodeToNormal(firstName);

        if(lastName.getText() == null || lastName.getText().equals("")){
            setNodeToErrorColor(lastName);
            terminate = true;
        }else setNodeToNormal(lastName);

        if(placeOB.getText() == null || placeOB.getText().equals("")){
            setNodeToErrorColor(placeOB);
            terminate = true;
        }else setNodeToNormal(placeOB);

        if(dOB.getValue() == null){
            setNodeToErrorColor(dOB);
            terminate = true;
        }else setNodeToNormal(dOB);

        if(dOD.getValue() != null) {
            if(dOB.getValue().isAfter(dOD.getValue()))
            {
                setNodeToErrorColor(dOD);
                terminate = true;
            }else setNodeToNormal(dOB);
        }

        if(terminate)
            return;

        author.setFirstName(firstName.getText());
        author.setLastName(lastName.getText());
        author.setPlaceOfBirth(placeOB.getText());
        author.setAbout(about.getText());
        author.setSex( male.isSelected() ? Sex.male : Sex.female );
        author.setDateOfBirth(Date.valueOf(dOB.getValue()));
        author.setDateOfDeath( (dOD.getValue() == null) ? null :Date.valueOf(dOD.getValue()));

        AuthorDAO.getDAO().saveOrUpdate(author);

        doneStatus.setText("Saved");
    }

    private void setNodeToErrorColor(Node node){
        node.setStyle("-fx-background-color: #ff0000");
    }
    private void setNodeToNormal(Node node){node.setStyle("");}

    public void prepareNode( Author author){
        anchor.setAuthor(author);

        firstName.setText(author.getFirstName());
        lastName.setText(author.getLastName());
        dOB.setValue( (author.getDateOfBirth() == null) ? null : author.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() );
        dOD.setValue((author.getDateOfDeath() == null) ? null : author.getDateOfDeath().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        placeOB.setText(author.getPlaceOfBirth());
        if(author.getSex() == Sex.male)
            male.selectedProperty().set(true);
        else
            female.selectedProperty().set(true);
        about.setText(author.getAbout());
        photo.setImage((author.getPhoto() == null) ? null : new Image(new ByteArrayInputStream(author.getPhoto())));

        doneStatus.setText("");

        for(Node node : anchor.getChildren())
            node.setStyle("");
    }

    public static Parent getNode(Author author) throws IOException {
        FXMLLoader loader = new FXMLLoader(BookController.class.getResource("/fxmls/saveAuthor.fxml"));
        Parent root = loader.load();
        SaveAuthorController controller = loader.getController();
        controller.prepareNode(author);
        return root;
    }

    public void setPhoto(ActionEvent event) throws IOException {
        FileChooser filechooser = new FileChooser();
        File selected = filechooser.showOpenDialog(null);
        if(selected == null)
            throw new NullPointerException();

        BufferedImage bImage = ImageIO.read(new File(selected.getPath()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String fileType = selected.getName().substring(selected.getName().lastIndexOf(".")+1);
        ImageIO.write(bImage, fileType , bos );
        byte [] data = bos.toByteArray();

        anchor.getAuthor().setPhoto( data );

        Image image = new Image(new ByteArrayInputStream(data));
        photo.setImage( image );

    }

    public void removePhoto(ActionEvent event){
        photo.setImage(null);
        anchor.getAuthor().setPhoto(null);
    }

}
