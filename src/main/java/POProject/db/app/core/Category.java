package POProject.db.app.core;

import POProject.db.core.core.AbstractModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractModel {

    @Column(length = 255, nullable = false)
    private String name;

    private String about;

    @ManyToMany(mappedBy = "categoryList")
    private List<Book> bookList;

    @Override
    public String toString() {
        return name;
    }
}
