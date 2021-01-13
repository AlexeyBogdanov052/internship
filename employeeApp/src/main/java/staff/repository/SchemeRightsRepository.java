package staff.repository;

import staff.domain.SchemeRights;
import org.springframework.data.repository.CrudRepository;

public interface SchemeRightsRepository extends CrudRepository<SchemeRights, Long> {
    SchemeRights findById(long id);
}
