package staff.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "operations")
public class Operation {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "operation_name", nullable = false)
    private String name;

    public Operation() {}

    public Operation(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
