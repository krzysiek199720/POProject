package POProject.app.core;


import POProject.core.core.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher extends AbstractModel {

    @Column(length = 255,nullable = false)
    private String name;

    private String about;
}
