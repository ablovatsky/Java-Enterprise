package by.avectis.contracts.controller.mappingController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contracts/laboriousness/")
public class LaborIntensityController {

    @GetMapping( value = { "/{contractId}" } )
    public String laborIntensityPage(@PathVariable long contractId, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("contractId", contractId);
        return "/contract/laborIntensity/laborIntensity";
    }
}
