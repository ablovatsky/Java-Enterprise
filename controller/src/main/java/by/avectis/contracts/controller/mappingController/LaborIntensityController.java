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

    @GetMapping( value = { "/{contractNumber}" } )
    public String laborIntensityPage(@PathVariable String contractNumber, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("contractNumber", contractNumber);
        return "/contract/laborIntensity/laborIntensity";
    }
}
