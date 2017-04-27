package by.avectis.contracts.controller.mappingController;

import by.avectis.contracts.dto.subdivision.SubdivisionDTO;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/administration/")
@SessionAttributes("roles")
public class SubdivisionController {

    @Autowired
    private SubdivisionService subdivisionService;

    @Autowired
    private SubdivisionDTO subdivisionDTO;

    @RequestMapping(value = { "/subdivisions" }, method = RequestMethod.GET)
    public String listSubdivisions() {
        return "/administration/subdivisionslist";
    }

    @RequestMapping(value = { "/getSubdivisions" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getListSubdivisions() {
        return subdivisionDTO.getSubdivisionListToJson();
    }

    @RequestMapping(value = {"/new-subdivision"}, method = RequestMethod.GET)
    public String newSubdivision(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("id", -1);
        return "/administration/subdivisionRegistration";
    }

    @RequestMapping(value = {"/new-subdivision"}, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject saveSubdivision(@RequestBody  Subdivision subdivision){
        JSONObject jsonObject = new JSONObject();
        if(!subdivisionService.isSubdivisionUnique(subdivision.getName())){
            return jsonObject.put("state", "not unique");
        }
        try {
            subdivisionService.addSubdivision(subdivision);
            jsonObject.put("state", "ok");
        } catch (ServiceException e) {
            jsonObject.put("state", "fail");
            e.printStackTrace();
        }
        return jsonObject;
    }

    @RequestMapping(value = { "/edit-subdivision-{id}" }, method = RequestMethod.GET)
    public String editSubdivision(@PathVariable long id, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("id", id);
        return "/administration/subdivisionRegistration";
    }

    @RequestMapping(value = { "/get-edit-subdivision-{id}" }, method = RequestMethod.GET,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public JSONObject getEditSubdivision(@PathVariable long id) {
       Subdivision subdivision = subdivisionService.findSubdivisionById(id);
        if (subdivision != null) {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("subdivision", subdivisionDTO.getSubdivisionToJson(subdivision));
            return jsonObject;
        }
        return null;
    }

    @RequestMapping(value = { "/edit-subdivision" }, method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public JSONObject updateSubdivision(@RequestBody Subdivision subdivision, BindingResult bindingResult){
        JSONObject jsonObject = new JSONObject();
        if (!bindingResult.hasErrors()) {
            if (subdivisionService.isSubdivisionUnique(subdivision.getId())) {
                return jsonObject.put("state", "not found");
            }
            try {
                subdivisionService.updateSubdivision(subdivision);
                jsonObject.put("state", "ok");
            } catch (ServiceException e) {
                jsonObject.put("state", "fail");
                e.printStackTrace();
            }
            return jsonObject;
        }
        jsonObject.put("state", "not valid");
        return jsonObject;
    }

    @RequestMapping(value = { "/delete-subdivision-{id}" }, method = RequestMethod.GET)
    public String deleteSubdivision(@PathVariable long id) {
        if (subdivisionService.findSubdivisionById(id).getWorkerList().size() == 0) {
            subdivisionService.deleteSubdivision(id);
        } else {
            return "administration/deleteSubdivisionError";
        }
        return "redirect:/administration/subdivisions";
    }

}
