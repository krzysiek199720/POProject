package POProject.app.core;


//https://dzone.com/articles/how-load-or-save-image-using

import POProject.core.core.AbstractModel;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
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
    private Date releaseYear;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] cover;

    @ManyToOne
    @JoinColumn(name="seriesId")
    private Series series;

    @ManyToOne(optional = false)
    @JoinColumn(name="publisherId", nullable = false)
    private Publisher publisher;

    //TODO manytomany

    @ManyToMany(mappedBy = "bookList")
    private List<Author> authorList;

    @ManyToMany(mappedBy = "bookList")
    private List<Category> categoryList;

    //Custom setters

    public void setReleaseYear(Integer year){
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, 0,0);
        this.releaseYear = new Date();
        this.releaseYear = calendar.getTime();
    }

    //Custom getters

    public Integer getReleaseYear(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.releaseYear);
        return calendar.get(Calendar.YEAR);
    }

}
