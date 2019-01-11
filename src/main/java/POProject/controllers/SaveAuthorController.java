package POProject.controllers;

import POProject.customNodes.AnchorAuthor;
import POProject.db.app.core.Author;
import POProject.db.app.core.enums.Sex;
import POProject.db.app.db.AuthorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private ImageView photo;

    @FXML
    private Label doneStatus;

    public void done(ActionEvent actionEvent){
        Author author = anchor.getAuthor();

        boolean terminate = false;

        if(firstName.getText() == null || firstName.getText().equals("")){
            setNodeToErrorColor(firstName);
            terminate = true;
        }

        if(lastName.getText() == null || lastName.getText().equals("")){
            setNodeToErrorColor(lastName);
            terminate = true;
        }

        if(placeOB.getText() == null || placeOB.getText().equals("")){
            setNodeToErrorColor(placeOB);
            terminate = true;
        }

        if(dOB.getValue() == null){
            setNodeToErrorColor(dOB);
            terminate = true;
        }

        if(dOD.getValue() != null) {
            if(dOB.getValue().isAfter(dOD.getValue()))
            {
                setNodeToErrorColor(dOD);
                terminate = true;
            }
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
