package by.avectis.contracts.controller.restController;

import by.avectis.contracts.model.LaborIntensity;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.laborIntensuty.LaborIntensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping("/contracts/laboriousness/")
public class LaborIntensityRestController {

    @Autowired
    private LaborIntensityService laborIntensityService;

    @GetMapping("/contract-{contractId}")
    public ResponseEntity<Set<LaborIntensity>> getStates(@PathVariable long contractId) {
        return new ResponseEntity<>(laborIntensityService.findAllByContract(contractId), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity addLaborIntensity(@RequestBody LaborIntensity laborIntensity, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (!laborIntensityService.isLaborIntensityUnique(laborIntensity)) {
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
            try {
                laborIntensityService.add(laborIntensity);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.SEE_OTHER);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/edit")
    public ResponseEntity editLaborIntensity(@RequestBody LaborIntensity laborIntensity, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (laborIntensityService.isLaborIntensityUnique(laborIntensity)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            try {
                laborIntensityService.update(laborIntensity);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.SEE_OTHER);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value = { "/{id}" })
    public ResponseEntity deleteWorker(@PathVariable long id) {
        try{
            laborIntensityService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }
}
