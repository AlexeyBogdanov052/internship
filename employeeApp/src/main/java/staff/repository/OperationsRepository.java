package staff.repository;

import staff.domain.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationsRepository extends CrudRepository<Operation, Long> {
    Operation findById(long id);
}
