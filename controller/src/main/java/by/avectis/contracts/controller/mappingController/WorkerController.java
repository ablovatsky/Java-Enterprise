package by.avectis.contracts.controller.mappingController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/administration/")
public class WorkerController {

    @GetMapping( value = { "/workers" } )
    public String listWorkersPage() {
        return "/administration/workerslist";
    }

    @GetMapping( value = {"/workers/new"} )
    public String newWorkerPage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("ssoId", "newWorker");
        return "/administration/workerRegistration";
    }

    @GetMapping( value = { "/workers/{ssoId}" } )
    public String editWorkerPage(@PathVariable String ssoId, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("ssoId", ssoId);
        return "/administration/workerRegistration";
    }
}
