package POProject.db.app.core;

import POProject.db.core.core.AbstractModel;
import com.sun.org.apache.regexp.internal.RE;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends AbstractModel {

    @NonNull
    @Column(length = 255, nullable = false)
    private String title;

    @NonNull
    @Column(nullable = false)
    private Integer pageCount;

    @Column(length = 13, unique = true, nullable = false)
    private String ISBN;

    private String about;

    @Column(nullable = false)
    private Integer releaseYear;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] cover;

    @ManyToOne
    @JoinColumn(name="seriesId")
    private Series series;

    @ManyToOne(optional = false)
    @JoinColumn(name="publisherId", nullable = false)
    private Publisher publisher;


    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Author> authorList;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Category> categoryList;

}
