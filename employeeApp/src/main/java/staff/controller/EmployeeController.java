package staff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import staff.repository.EmployeeRepository;
import staff.repository.SchemeRightsRepository;
import staff.domain.Employee;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;
import staff.domain.SchemeRights;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository empRep;
    @Autowired
    private SchemeRightsRepository srRep;

    @RequestMapping(value = {"/staff"}, method = RequestMethod.GET)
    public String getStaff(Model model){
        Map<Long, Employee> staff = getEmp();

        model.addAttribute("staff", staff.values());

        return "staff";
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
        return "addemployee";
    }

    @RequestMapping(value={"/addemp"}, method=RequestMethod.POST)
    public String empSubmit(@ModelAttribute Employee addemp, Model model) {
        String psswd = DigestUtils.sha256Hex(addemp.getHash());
        String salt = randomString();
        String hash = DigestUtils.sha256Hex(psswd + salt);
        empRep.save(new Employee(addemp.getSurname(), addemp.getName(), addemp.getPatronymic(), hash, addemp.getLogin(), salt, null));

        return "redirect:/staff";
    }

    private String randomString(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @GetMapping(value = {"/emp/{id}/update"})
    public String upEmpForm(Model model, @PathVariable long id) {
        Employee emp = empRep.findById(id);
        model.addAttribute("emp", emp);
        Map<Long, SchemeRights> sr = getRights();
        model.addAttribute("schemes", sr.values());

        return "/updateEmployee";
    }

    @RequestMapping(value = {"/emp/{id}/update"}, method = {RequestMethod.POST})
    public String upEmpSubmit(Model model, @PathVariable long id,
                               @ModelAttribute("emp") Employee emp,
                               @RequestParam(value = "idsr" , required = false) long idsr) {
        Employee empToUpdate = empRep.findById(id);
        empToUpdate.setSurname(emp.getSurname());
        empToUpdate.setName(emp.getName());
        empToUpdate.setPatronymic(emp.getPatronymic());
        empToUpdate.setLogin(emp.getLogin());
        String psswd = DigestUtils.sha256Hex(emp.getHash());
        String salt = randomString();
        String hash = DigestUtils.sha256Hex(psswd + salt);
        empToUpdate.setHash(hash);
        empToUpdate.setSalt(salt);
        if (idsr > 0){
            empToUpdate.setId_scheme_rights(idsr);
        } else {
            empToUpdate.setId_scheme_rights(null);
        }
        empRep.save(empToUpdate);

        return "redirect:/staff/";
    }

    private Map<Long, SchemeRights> getRights(){
        Map<Long, SchemeRights> result = new HashMap<>();
        Iterable<SchemeRights> storage = srRep.findAll();

        for (SchemeRights right: storage) {
            result.put(right.getId(), right);
        }
        return result;
    }
}
