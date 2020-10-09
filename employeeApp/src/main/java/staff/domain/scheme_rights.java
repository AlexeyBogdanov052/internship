package staff.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class scheme_rights {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long emp_id;
    private String operation_name;
    private String operation_description;

    protected scheme_rights() {}

    public scheme_rights(Long id, Long emp_id, String operation_name, String operation_description) {
        this.id = id;
        this.emp_id = emp_id;
        this.operation_name = operation_name;
        this.operation_description = operation_description;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setEmp_Id(Long emp_id){
        this.emp_id = emp_id;
    }

    public Long getEmp_Id(){
        return emp_id;
    }

    public void setOperation_name(String operation_name){
        this.operation_name = operation_name;
    }

    public String getOperation_name(){
        return operation_name;
    }

    public void setOperation_discription(String operation_description){
        this.operation_description = operation_description;
    }

    public String getOperation_description(){
        return operation_description;
    }
}
