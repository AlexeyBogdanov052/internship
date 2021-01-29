package staff.repository;

import staff.domain.SchemeOperations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SchemeOperationsReposiroty extends CrudRepository<SchemeOperations, Long> {
    List<SchemeOperations> findAll();
}
