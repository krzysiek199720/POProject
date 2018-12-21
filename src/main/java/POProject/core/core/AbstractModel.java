package POProject.core.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_generator",sequenceName="AModel_id_generator")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id_generator")
    protected Long id;
}
