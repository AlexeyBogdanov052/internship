package staff.dao;

import staff.domain.SchemeOperations;
import org.springframework.data.repository.CrudRepository;

public interface SchemeOperationsReposiroty extends CrudRepository<SchemeOperations, Long> {
    SchemeOperations findById(long id);
}
