package POProject.db.app.api;

import POProject.db.app.core.Author;
import POProject.db.app.core.Category;
import POProject.db.app.core.Publisher;
import POProject.db.app.core.Series;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchApi {

    private String title;

    private String ISBN;

    private Integer year;

    private Author author;

    private Series series;

    private Category category;

    private Publisher publisher;

}
