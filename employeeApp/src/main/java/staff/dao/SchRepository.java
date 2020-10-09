package staff.dao;

import staff.domain.scheme_rights;
import org.springframework.data.repository.CrudRepository;

public interface SchRepository extends CrudRepository<scheme_rights, Long> {
}
