package POProject.customNodes;

import POProject.db.app.core.Book;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnchorBook extends AnchorPane {

    private Book book;
}
