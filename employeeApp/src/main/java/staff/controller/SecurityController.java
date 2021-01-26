package staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import staff.domain.Employee;
import staff.repository.EmployeeRepository;

@Controller
public class SecurityController {
    @Autowired
    private EmployeeRepository empRep;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String getLogin(Model model) {
        return "login";
    }

}
