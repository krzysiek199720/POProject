package POProject.controllers;

import POProject.customNodes.AnchorSearchElem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SearchElemController {

    @FXML
    private AnchorSearchElem anchor;

    @FXML
    private ImageView cover;

    @FXML
    private Label title;

    @FXML
    private Label authorName;

    @FXML
    private Label about;

    @FXML
    private void goToBook(){
        System.out.println("I am the Glob-glo-gab-galab\nI love Books\n");
        //TODO zmieniamy okno na to ze strona ksiazki
    }

    @FXML
    private void goToAuthor(){
        System.out.println("author");
        //TODO zmieniamy okno na to ze strona autora
    }
}
