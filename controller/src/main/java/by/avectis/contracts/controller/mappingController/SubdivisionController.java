package by.avectis.contracts.controller.mappingController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/administration/")
public class SubdivisionController {

    @RequestMapping(value = {"/subdivisions"}, method = RequestMethod.GET)
    public String listSubdivisions() {
        return "/administration/subdivisionslist";
    }


    @RequestMapping(value = {"/subdivisions/new"}, method = RequestMethod.GET)
    public String newSubdivision(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("id", -1);
        return "/administration/subdivisionRegistration";
    }

    @RequestMapping(value = {"/subdivisions/{id}"}, method = RequestMethod.GET)
    public String editSubdivision(@PathVariable long id, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("id", id);
        return "/administration/subdivisionRegistration";
    }

    @RequestMapping(value = {"/subdivisions/cost"}, method = RequestMethod.GET)
    public String listSubdivisionsCost() {
        return "/administration/subdivisionCost/subdivisionCost";
    }
}
