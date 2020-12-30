package staff.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "scheme_operations")
public class SchemeOperations {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "scheme_id", nullable = false)
    private Long scheme_id;
    @Column(name = "operation_id", nullable = false)
    private Long operation_id;

    public SchemeOperations (Long scheme_id, Long operation_id){
        this.scheme_id = scheme_id;
        this.operation_id = operation_id;
    }
}
