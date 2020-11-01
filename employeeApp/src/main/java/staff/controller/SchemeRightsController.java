package staff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import staff.dao.SchemeRightsRepository;
import staff.domain.SchemeRights;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class SchemeRightsController {
    @Autowired
    private SchemeRightsRepository srRep;

    @RequestMapping(value = {"/scheme_rights"}, method = RequestMethod.GET)
    public String getStaff(Model model){
        Map<Long, SchemeRights> rights = getRights();

        model.addAttribute("rights", rights.values());

        return "index";
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
