package staff.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@IdClass(SchemeOperationsId.class)
@Data
@Table(name = "scheme_operations")
public class SchemeOperations {

    @Id
    @Column(name = "scheme_id", nullable = false)
    private Long scheme_id;
    @Id
    @Column(name = "operation_id", nullable = false)
    private Long operation_id;

    public SchemeOperations() {}

    public SchemeOperations (Long scheme_id, Long operation_id){
        this.scheme_id = scheme_id;
        this.operation_id = operation_id;
    }

    public static SchemeOperationsBuilder builder(){
        return new SchemeOperationsBuilder();
    }

    public static class SchemeOperationsBuilder{
        private SchemeOperations so;

        public SchemeOperationsBuilder(){
            so = new SchemeOperations();
        }

        public SchemeOperationsBuilder scheme_id(Long scheme_id){
            so.setScheme_id(scheme_id);
            return this;
        }

        public SchemeOperationsBuilder operation_id(Long operation_id){
            so.setOperation_id(operation_id);
            return this;
        }

        public SchemeOperations build(){
            return so;
        }
    }
}
