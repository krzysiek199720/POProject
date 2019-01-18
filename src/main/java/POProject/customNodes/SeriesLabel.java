package POProject.customNodes;

import POProject.db.app.core.Category;
import POProject.db.app.core.Series;
import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesLabel extends Label {
    private Series series;

    public SeriesLabel(){
        super();
        series = new Series();
    }
    public SeriesLabel(String text){
        super(text);
        series = new Series();
    }
    public SeriesLabel(Series series){
        super();
        this.series = series;
    }
    public SeriesLabel(String text, Series series){
        super(text);
        this.series = series;
    }
    public SeriesLabel(String text, Node graphic) {
        super(text, graphic);
        series = new Series();
    }
    public SeriesLabel(String text, Series series, Node graphic) {
        super(text, graphic);
        this.series = series;
    }
}
