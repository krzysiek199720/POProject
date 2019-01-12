package POProject.db.app.core;


import POProject.db.core.core.AbstractModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher extends AbstractModel {

    @Column(length = 255,nullable = false)
    private String name;

    private String about;

    @Override
    public String toString() {
        return name;
    }
}
