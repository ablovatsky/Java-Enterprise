package by.avectis.contracts.controller.restController;

import by.avectis.contracts.facade.TimeSheetFacadeService;
import by.avectis.contracts.facade.model.ShortEmployment;
import by.avectis.contracts.facade.model.ShortTimeSheet;
import by.avectis.contracts.facade.model.TimeSheetDate;
import by.avectis.contracts.model.TimeSheet;
import by.avectis.contracts.service.timeSheet.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/timesheet")
public class TimeSheetRestController {

    @Autowired
    private TimeSheetService timeSheetService;

    @Autowired
    private TimeSheetFacadeService timeSheetFacadeService;

    @GetMapping(value = { "/timesheets/{pageNumber}-{itemsOnPage}" })
    public ResponseEntity<Set<ShortTimeSheet>> getTimeSheets(@PathVariable int pageNumber, @PathVariable int itemsOnPage){
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("timeSheetCount", String.valueOf(timeSheetService.getCountRows()));
        Set<ShortTimeSheet> responseEntity =timeSheetFacadeService.getTimeSheets(itemsOnPage, pageNumber, "date", 1);
        return new ResponseEntity<>(responseEntity , multiValueMap, HttpStatus.OK);
    }

    @GetMapping(value = "/newTimesheet")
    public ResponseEntity<TimeSheetDate> getNewTimeSheet() {
        return new ResponseEntity<>(timeSheetFacadeService.getNewTimeSheetData(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTimesheet-{timeSheetId}")
    public ResponseEntity<TimeSheetDate> getTimeSheet(@PathVariable long timeSheetId) {
        return new ResponseEntity<>(timeSheetFacadeService.getTimeSheetData(timeSheetId), HttpStatus.OK);
    }

    @PostMapping(value = "/timesheet/new")
    public ResponseEntity addNewTimeSheet(@RequestBody Set<ShortEmployment> employments,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            timeSheetFacadeService.saveTimeSheet(employments);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/timesheet/edit-{timeSheetId}")
    public ResponseEntity editTimeSheet(@RequestBody Set<ShortEmployment> employments, @PathVariable long timeSheetId,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            timeSheetFacadeService.editTimeSheet(employments, timeSheetId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
