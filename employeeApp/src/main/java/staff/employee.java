package staff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String emp_surname;
    private String emp_name;
    private String emp_patronymic;
    private String emp_hash;

    protected employee() {}

    public employee(long id, String emp_surname, String emp_name, String emp_patronymic, String emp_hash) {
        this.id = id;
        this.emp_surname = emp_surname;
        this.emp_name = emp_name;
        this.emp_patronymic = emp_patronymic;
        this.emp_hash = emp_hash;
    }
}
