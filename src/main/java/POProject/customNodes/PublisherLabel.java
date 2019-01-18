package POProject.customNodes;

import POProject.db.app.core.Publisher;
import POProject.db.app.core.Series;
import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherLabel extends Label {
    private Publisher publisher;

    public PublisherLabel(){
        super();
        publisher = new Publisher();
    }
    public PublisherLabel(String text){
        super(text);
        publisher = new Publisher();
    }
    public PublisherLabel(Publisher publisher){
        super();
        this.publisher = publisher;
    }
    public PublisherLabel(String text, Publisher publisher){
        super(text);
        this.publisher = publisher;
    }
    public PublisherLabel(String text, Node graphic) {
        super(text, graphic);
        publisher = new Publisher();
    }
    public PublisherLabel(String text, Publisher publisher, Node graphic) {
        super(text, graphic);
        this.publisher = publisher;
    }
}
