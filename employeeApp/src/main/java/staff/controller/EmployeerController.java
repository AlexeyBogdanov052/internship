package staff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import staff.dao.EmployeeRepository;
import staff.domain.Employee;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeerController {
    @Autowired
    private EmployeeRepository empRep;

    @RequestMapping(value = {"/employeers"}, method = RequestMethod.GET)
    public String getIndex(Model model){
        Map<Long, Employee> employeers = getEmp();

        model.addAttribute("employeers", employeers.values());

        return "index";
    }

    private Map<Long, Employee> getEmp(){
        Map<Long, Employee> result = new HashMap<>();
        Iterable<Employee> storage = empRep.findAll();

        for (Employee emp: storage) {
            result.put(emp.getId(), emp);
        }
        return result;
    }
}
