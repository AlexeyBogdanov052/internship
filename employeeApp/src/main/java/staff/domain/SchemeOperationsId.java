package staff.domain;

import java.io.Serializable;

public class SchemeOperationsId implements Serializable {

    private Long scheme_id;
    private Long operation_id;

    public SchemeOperationsId(){}

    public SchemeOperationsId(Long scheme_id, Long operation_id){
        this.scheme_id = scheme_id;
        this.operation_id = operation_id;
    }
}
