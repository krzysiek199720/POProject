package POProject.db.app.core;


import POProject.db.core.core.AbstractModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "series")
public class Series extends AbstractModel {

    @Column(length = 255, nullable = false)
    private String name;

    private String about;

    @Override
    public String toString() {
        return name;
    }
}
