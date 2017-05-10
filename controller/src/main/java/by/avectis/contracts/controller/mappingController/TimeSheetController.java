package by.avectis.contracts.controller.mappingController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/timesheet")
public class TimeSheetController {

    @GetMapping(value = "/")
    public String getTimeSheetsPage() {
        return "timesheet/timesheetlist";
    }

    @GetMapping(value = "/new")
    public String newTimeSheetPage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("timeSheetId", "newTimeSheet");
        return "timesheet/timesheetRegistration";
    }

    @GetMapping(value = "/{timeSheetId}")
    public String editTimeSheetPage(HttpServletRequest req, @PathVariable long timeSheetId) {
        HttpSession session = req.getSession();
        session.setAttribute("timeSheetId", timeSheetId);
        return "timesheet/timesheetRegistration";
    }
}
