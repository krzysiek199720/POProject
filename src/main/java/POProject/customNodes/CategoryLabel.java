package POProject.customNodes;

import POProject.db.app.core.Author;
import POProject.db.app.core.Category;
import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryLabel extends Label {
    private Category category;

    public CategoryLabel(){
        super();
        category = new Category();
    }
    public CategoryLabel(String text){
        super(text);
        category = new Category();
    }
    public CategoryLabel(Category category){
        super();
        this.category = category;
    }
    public CategoryLabel(String text, Category category){
        super(text);
        this.category = category;
    }
    public CategoryLabel(String text, Node graphic) {
        super(text, graphic);
        category = new Category();
    }
    public CategoryLabel(String text, Category category, Node graphic) {
        super(text, graphic);
        this.category = category;
    }
}
