package by.avectis.contracts.controller.restController;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@RequestMapping("/")
public class MainRestController {

    @GetMapping(value = {"/loggedinworker" })
    public String getAuthWorker() {
        return getPrincipal();
    }

    private String getPrincipal(){
        String workerName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            workerName = ((UserDetails)principal).getUsername();
        } else {
            workerName = principal.toString();
        }
        return workerName;
    }
}
