package staff.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "scheme_rights")
public class SchemeRights {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    public SchemeRights() {}

    public SchemeRights(String name) {
        this.name = name;
    }

    public SchemeRights(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
