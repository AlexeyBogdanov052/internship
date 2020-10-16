package staff.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "scheme_rights")
public class SchemeRights {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "emp_id", nullable = false)
    private Long emp_id;
    @Column(name = "operation_name", nullable = false)
    private String operation_name;
    @Column(name = "operation_description", nullable = false)
    private String operation_description;

    protected SchemeRights() {}

    public SchemeRights(Long id, Long emp_id, String operation_name, String operation_description) {
        this.id = id;
        this.emp_id = emp_id;
        this.operation_name = operation_name;
        this.operation_description = operation_description;
    }

}
