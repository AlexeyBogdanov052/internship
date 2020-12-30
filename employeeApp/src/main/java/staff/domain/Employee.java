package staff.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "staff")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Column(name = "hash", nullable = false)
    private String hash;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "salt", nullable = false)
    private String salt;
    @Column(name = "id_scheme_rights", nullable = true)
    private Long id_scheme_rights;

    public Employee() {}

    public Employee(String surname, String name, String patronymic, String hash, String login, String salt, Long id_scheme_rights) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.hash = hash;
        this.login = login;
        this.salt = salt;
        this.id_scheme_rights = id_scheme_rights;
    }

    public Employee(long id, String surname, String name, String patronymic, String hash, String login, String salt, Long id_scheme_rights) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.hash = hash;
        this.login = login;
        this.salt = salt;
        this.id_scheme_rights = id_scheme_rights;
    }
}
