package POProject.app.core;


import POProject.app.core.enums.Sex;
import POProject.core.core.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
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

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] photo;


    @ManyToMany(mappedBy = "authorList")
    private List<Book> bookList;

}
