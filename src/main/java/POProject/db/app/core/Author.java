package POProject.db.app.core;


import POProject.db.app.core.enums.Sex;
import POProject.db.core.core.AbstractModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends AbstractModel {

    @NonNull
    @Column(length = 255, nullable = false)
    private String firstName;

    @NonNull
    @Column(length = 255, nullable = false)
    private String lastName;

    private Date dateOfBirth;

    private Date dateOfDeath;

    @Column(length = 255)
    private String placeOfBirth;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String about;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] photo;


    @ManyToMany(mappedBy = "authorList")
    private List<Book> bookList;

    @Override
    public String toString() {
        return (firstName + " " + lastName);
    }
}
