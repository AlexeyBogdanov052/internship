package staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import staff.repository.EmployeeRepository;
import staff.repository.OperationsRepository;
import staff.repository.SchemeOperationsReposiroty;
import staff.repository.SchemeRightsRepository;
import staff.domain.Employee;
import staff.domain.Operation;
import staff.domain.SchemeOperations;
import staff.domain.SchemeRights;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SchemeRightsController {
    @Autowired
    private EmployeeRepository empRep;
    @Autowired
    private SchemeRightsRepository srRep;
    @Autowired
    private OperationsRepository opRep;
    @Autowired
    private SchemeOperationsReposiroty soRep;

    @RequestMapping(value = {"/scheme_rights"}, method = RequestMethod.GET)
    public String getStaff(Model model){
        Map<Long, SchemeRights> rights = getRights();
        model.addAttribute("rights", rights.values());
        Set<Long> currentIds = getCurrentIds();
        model.addAttribute("currentIds", currentIds);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != null) {
            Employee active = empRep.findByLogin(auth.getName());
            model.addAttribute("active", active);
            model.addAttribute("isActive", true);
        } else {
            model.addAttribute("isActive", false);
        }

        return "schemerights";
    }

    private Map<Long, SchemeRights> getRights(){
        Map<Long, SchemeRights> result = new HashMap<>();
        Iterable<SchemeRights> storage = srRep.findAll();

        for (SchemeRights right: storage) {
            result.put(right.getId(), right);
        }
        return result;
    }

    private Set<Long> getCurrentIds(){
        Set<Long> result = new HashSet<>();
        Iterable<Employee> storage = empRep.findAll();

        for (Employee emp: storage){
            result.add(emp.getId_scheme_rights());
        }
        return result;
    }

    @RequestMapping(value={"/addscheme"}, method=RequestMethod.GET)
    public String empForm(Model model) {
//        ArrayList<Operation> operations = new ArrayList<Operation>();
//        model.addAttribute("operations", operations);

        model.addAttribute("addscheme", new SchemeRights());

        return "addscheme";
    }

    @RequestMapping(value={"/addscheme"}, method=RequestMethod.POST)
    public String empSubmit(@ModelAttribute SchemeRights addscheme, Model model) {
        srRep.save(new SchemeRights(addscheme.getName()));
        return "redirect:/scheme_rights";
    }

    private Map<Long, Operation> getAllOperations(){
        Map<Long, Operation> result = new HashMap<>();
        Iterable<Operation> storage = opRep.findAll();

        for (Operation op: storage) {
            result.put(op.getId(), op);
        }
        return result;
    }

    @GetMapping(value = {"/sheme_rights/{id}/update"})
    public String upListForm(Model model, @PathVariable long id) {
        SchemeRights scheme = srRep.findById(id);
        model.addAttribute("scheme", scheme);
        Map<Long, Operation> alloperations = getAllOperations();
        model.addAttribute("alloperations", alloperations.values());
        ArrayList<Long> currentOperations = getCurrentOperations(id);
        model.addAttribute("currentoperations", currentOperations);

        return "/updateScheme";
    }

    @RequestMapping(value = {"/sheme_update/{id}"}, method = {RequestMethod.POST})
    public String upListSubmit(Model model, @PathVariable long id,
                               @RequestParam(value = "operations" , required = false) long[] operations) {
        ArrayList<SchemeOperations> result = new ArrayList<>();
        if (operations != null){
            refresh(id);
            for (int i = 0; i < operations.length; i++) {
                SchemeOperations a = SchemeOperations.builder().scheme_id(id).operation_id(operations[i]).build();
                result.add(a);
            }
        }
        soRep.saveAll(result);
        //...
        return "redirect:/scheme_rights/";
    }

    private ArrayList<Long> getCurrentOperations(Long id){
        ArrayList<Long> result = new ArrayList<>();
        Iterable<SchemeOperations> currentOperations = soRep.findAll();

        for (SchemeOperations so: currentOperations) {
            if (so.getScheme_id() == id){
                result.add(so.getOperation_id());
            }
        }
        return result;
    }

    private void refresh(Long id){
        Iterable<SchemeOperations> all = soRep.findAll();
        for (SchemeOperations so: all) {
            if (so.getScheme_id() == id){
                soRep.delete(so);
            }
        }
    }

    @RequestMapping(value = {"/scheme/{id}/delete"})
    public String removeScheme(@PathVariable Long id) {
        srRep.deleteById(id);
        return "redirect:/scheme_rights";
    }
}
