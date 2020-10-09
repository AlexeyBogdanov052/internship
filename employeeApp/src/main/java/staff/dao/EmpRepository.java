package staff.dao;

import staff.domain.employee;
import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends CrudRepository<employee, Long> {
}
