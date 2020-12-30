package staff.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import staff.dao.OperationsRepository;
import staff.dao.SchemeOperationsReposiroty;
import staff.dao.SchemeRightsRepository;
import staff.domain.Operations;
import staff.domain.SchemeOperations;
import staff.domain.SchemeRights;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SchemeRightsController {
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

    @RequestMapping(value={"/addscheme"}, method=RequestMethod.GET)
    public String empForm(Model model) {
        Map<Long, Operations> alloperations = getAllOperations();
        model.addAttribute("alloperations", alloperations.values());
        ArrayList<Operations> operations = new ArrayList<Operations>();
        model.addAttribute("operations", operations);

        model.addAttribute("addscheme", new SchemeRights());

        return "addscheme";
    }

    @RequestMapping(value={"/addscheme"}, method=RequestMethod.POST)
    public String empSubmit(@ModelAttribute SchemeRights addscheme, Model model,
                            @ModelAttribute Operations operations) {
//        srRep.save(new SchemeRights(addscheme.getName()));
//        System.out.println(addscheme.getName());
//        Iterable<Operations> opq = Collections.singleton(operations);
//        for (Operations op: operations) {
//            soRep.save(new SchemeOperations(addscheme.getId(), op.getId()));
//            System.out.println(op);
//        }
        System.out.println(operations);

        return "redirect:/scheme_rights";
    }

    private Map<Long, Operations> getAllOperations(){
        Map<Long, Operations> result = new HashMap<>();
        Iterable<Operations> storage = opRep.findAll();

        for (Operations op: storage) {
            result.put(op.getId(), op);
        }
        return result;
    }

    @GetMapping(value = {"/sheme_rights/{id}/update"})
    public String upListForm(Model model, @PathVariable long id) {
        SchemeRights right = srRep.findById(id);

        model.addAttribute("right", right);

        return "/updateScheme";
    }

    @RequestMapping(value = {"/sheme_rights/{id}/update"}, method = {RequestMethod.POST})
    public String upListSubmit(Model model, @PathVariable long id,
                               @ModelAttribute("right") SchemeRights right) {
        SchemeRights rightToUpdate = srRep.findById(id);
        //...

        return "redirect:/SchemeRights/";
    }
}
