package staff.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "staff")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "emp_surname", nullable = false)
    private String emp_surname;
    @Column(name = "emp_name", nullable = false)
    private String emp_name;
    @Column(name = "emp_patronymic", nullable = false)
    private String emp_patronymic;
    @Column(name = "emp_hash", nullable = false)
    private String emp_hash;

    public Employee() {}

    public Employee(String emp_surname, String emp_name, String emp_patronymic, String emp_hash) {
        this.emp_surname = emp_surname;
        this.emp_name = emp_name;
        this.emp_patronymic = emp_patronymic;
        this.emp_hash = emp_hash;
    }

    public Employee(long id, String emp_surname, String emp_name, String emp_patronymic, String emp_hash) {
        this.id = id;
        this.emp_surname = emp_surname;
        this.emp_name = emp_name;
        this.emp_patronymic = emp_patronymic;
        this.emp_hash = emp_hash;
    }

}
