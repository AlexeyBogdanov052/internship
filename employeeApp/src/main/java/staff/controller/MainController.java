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
public class MainController {
    @Autowired
    private EmployeeRepository empRep;

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String mainStart(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != null) {
            Employee active = empRep.findByLogin(auth.getName());
            model.addAttribute("active", active);
            model.addAttribute("isActive", true);
        } else {
            model.addAttribute("isActive", false);
        }
        return "index";
    }
}
