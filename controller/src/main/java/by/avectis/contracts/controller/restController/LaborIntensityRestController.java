package by.avectis.contracts.controller.restController;

import by.avectis.contracts.model.LaborIntensity;
import by.avectis.contracts.service.laborIntensuty.LaborIntensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/contracts/laboriousness/")
public class LaborIntensityRestController {

    @Autowired
    private LaborIntensityService laborIntensityService;

    @GetMapping("/contract-{contractNumber}")
    public ResponseEntity<Set<LaborIntensity>> getStates(@PathVariable String contractNumber) {
        return new ResponseEntity<>(laborIntensityService.findAllByContract(contractNumber), HttpStatus.OK);
    }
}
