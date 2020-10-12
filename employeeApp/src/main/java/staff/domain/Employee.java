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

    protected Employee() {}

    public Employee(long id, String emp_surname, String emp_name, String emp_patronymic, String emp_hash) {
        this.id = id;
        this.emp_surname = emp_surname;
        this.emp_name = emp_name;
        this.emp_patronymic = emp_patronymic;
        this.emp_hash = emp_hash;
    }

    /*public void setId(Long id){
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
    }*/
}
