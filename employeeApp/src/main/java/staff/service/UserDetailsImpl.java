package staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import staff.domain.Employee;
import staff.repository.EmployeeRepository;

import java.util.List;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository empRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = empRep.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return (UserDetails) user;
    }

    public List<Employee> allUsers() {
        return empRep.findAll();
    }
}
