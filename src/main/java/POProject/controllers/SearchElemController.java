package POProject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SearchElemController {

    @FXML
    private ImageView cover;

    @FXML
    private Label title;

    @FXML
    private Label authorName;

    @FXML
    private Label about;

    //Przy kliknieciu na tytul
    public void goToBook(){
        //TODO zmieniamy okno na to ze strona ksiazki
    }

    //Przy kliknieciu na autora
    public void goToAuthor(){
        //TODO zmieniamy okno na to ze strona autora
    }
}
