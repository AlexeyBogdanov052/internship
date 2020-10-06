package staff;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends CrudRepository<employee, Long> {
}
