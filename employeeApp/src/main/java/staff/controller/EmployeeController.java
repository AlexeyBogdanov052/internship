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
public class EmployeeController {
    @Autowired
    private EmployeeRepository empRep;

    @RequestMapping(value = {"/staff"}, method = RequestMethod.GET)
    public String getStaff(Model model){
        Map<Long, Employee> staff = getEmp();

        model.addAttribute("staff", staff.values());

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

    @RequestMapping(value={"/addemp"}, method=RequestMethod.GET)
    public String empForm(Model model) {
        model.addAttribute("addemp", new Employee());
        return "addemp";
    }

    @RequestMapping(value={"/addemp"}, method=RequestMethod.POST)
    public String empSubmit(@ModelAttribute Employee addemp, Model model) {
        Employee result = empRep.save(new Employee(addemp.getEmp_surname(), addemp.getEmp_name(), addemp.getEmp_patronymic(), addemp.getEmp_hash()));

        return "redirect:/staff";
    }

    @GetMapping(value = {"/emp/{id}/update"})
    public String upEmpForm(Model model, @PathVariable long id) {
        Employee emp = empRep.findById(id);

        model.addAttribute("emp", emp);

        return "/updateEmployee";
    }

    @RequestMapping(value = {"/emp/{id}/update"}, method = {RequestMethod.POST})
    public String upEmpSubmit(Model model, @PathVariable long id,
                               @ModelAttribute("emp") Employee emp) {
        Employee empToUpdate = empRep.findById(id);
        empToUpdate.setEmp_surname(emp.getEmp_surname());
        empToUpdate.setEmp_name(emp.getEmp_name());
        empToUpdate.setEmp_patronymic(emp.getEmp_patronymic());
        empToUpdate.setEmp_hash(emp.getEmp_hash());
        empRep.save(empToUpdate);

        return "redirect:/staff";
    }
}
