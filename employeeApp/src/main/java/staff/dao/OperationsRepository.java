package staff.dao;

import staff.domain.Operations;
import org.springframework.data.repository.CrudRepository;

public interface OperationsRepository extends CrudRepository<Operations, Long> {
    Operations findById(long id);
}
