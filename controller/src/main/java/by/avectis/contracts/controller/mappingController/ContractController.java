package by.avectis.contracts.controller.mappingController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contracts/")
public class ContractController {

    @GetMapping(value = "/")
    public String listContractsPage(){
        return "/contract/contractslist";
    }

    @GetMapping( value = {"/contract/new"} )
    public String newWorkerPage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("contractId", "newContract");
        return "/contract/contractRegistration";
    }

    @GetMapping( value = { "/contract/{contractId}" } )
    public String editWorkerPage(@PathVariable Long contractId, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("contractId", contractId);
        return "/contract/contractRegistration";
    }
}
