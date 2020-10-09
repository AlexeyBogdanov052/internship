package staff.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
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

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setEmp_surname(String emp_surname){
        this.emp_surname = emp_surname;
    }

    public String getEmp_surname(){
        return emp_surname;
    }

    public void setEmp_name(String emp_name){
        this.emp_name = emp_name;
    }

    public String getEmp_name(){
        return emp_name;
    }

    public void setEmp_patronymic(String emp_patronymic){
        this.emp_patronymic = emp_patronymic;
    }

    public String getEmp_patronymic(){
        return emp_patronymic;
    }

    public void setEmp_hash(String emp_hash){
        this.emp_hash = emp_hash;
    }

    public String getEmp_hash(){
        return emp_hash;
    }
}
