package POProject.customNodes;

import POProject.db.app.core.Author;
import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorLabel extends Label {
    private Author author;

    public AuthorLabel(){
        super();
        author = new Author();
    }
    public AuthorLabel(String text){
        super(text);
        author = new Author();
    }
    public AuthorLabel(Author author){
        super();
        this.author = author;
    }
    public AuthorLabel(String text, Author author){
        super(text);
        this.author = author;
    }
    public AuthorLabel(String text, Node graphic) {
        super(text, graphic);
        author = new Author();
    }
    public AuthorLabel(String text, Author author, Node graphic) {
        super(text, graphic);
        this.author = author;
    }
}
