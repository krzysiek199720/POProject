package POProject.app.core;

import POProject.core.core.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractModel {

    @Column(length = 255, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categoryList")
    private List<Book> bookList;
}
