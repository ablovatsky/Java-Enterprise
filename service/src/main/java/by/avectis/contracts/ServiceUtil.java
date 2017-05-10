package by.avectis.contracts;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class ServiceUtil {

    public static String getPrincipal(){
        String workerName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            workerName = ((UserDetails)principal).getUsername();
        } else {
            workerName = principal.toString();
        }
        return workerName;
    }

    public static String addZero(String value) {
        if (value.length() == 1) {
            return "0" + value;
        }
        return value;
    }
}
