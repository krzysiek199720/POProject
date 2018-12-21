package POProject.app.core;


import POProject.core.core.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "series")
public class Series extends AbstractModel {

    @Column(length = 255, nullable = false)
    private String name;

    private String about;
}
