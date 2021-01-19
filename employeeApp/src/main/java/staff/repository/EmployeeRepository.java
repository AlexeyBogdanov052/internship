package staff.repository;

import staff.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findById(long id);
    Employee findByLogin(String login);
    List<Employee> findAll();
}
