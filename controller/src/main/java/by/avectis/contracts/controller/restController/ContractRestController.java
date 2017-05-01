package by.avectis.contracts.controller.restController;

import by.avectis.contracts.dto.contract.ContractDTO;
import by.avectis.contracts.dto.contract.modelDTO.ShortInfoContract;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.service.contract.ContractService;
import by.avectis.contracts.service.contract.ContractStateService;
import by.avectis.contracts.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contracts/")
public class ContractRestController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractStateService contractStateService;

    @Autowired
    private ContractDTO contractDTO;

    @GetMapping("/states")
    public ResponseEntity<List<ContractState>> getStates() {
        return new ResponseEntity<>(contractStateService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Contract> addContracts (@RequestBody Contract contract, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            if (!contractService.isContractNumberUnique(contract.getNumber())) {
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
            try {
                contractService.add(contract);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.SEE_OTHER);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value = { "/{pageNumber}-{itemsOnPage}" })
    public ResponseEntity<Set<ShortInfoContract>> getWorkers(@PathVariable int pageNumber, @PathVariable int itemsOnPage) {
        Set<ShortInfoContract> contracts = contractDTO.getAll(itemsOnPage, pageNumber, "number", 0);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("contractCount", String.valueOf(contractService.getCountRows()));
        return new ResponseEntity<>(contracts, multiValueMap, HttpStatus.OK);
    }

    @GetMapping(value = { "/state/{state}-{pageNumber}-{itemsOnPage}" })
    public ResponseEntity<Set<ShortInfoContract>> getWorkersByContractState(@PathVariable int state, @PathVariable int pageNumber, @PathVariable int itemsOnPage) {
        Set<ShortInfoContract> contracts = contractDTO.getByState(state, itemsOnPage, pageNumber, "number", 0);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("contractCount", String.valueOf(contractService.getCountRows()));
        return new ResponseEntity<>(contracts, multiValueMap, HttpStatus.OK);
    }

    @GetMapping(value = { "/number/{number}-{pageNumber}-{itemsOnPage}" })
    public ResponseEntity<Set<ShortInfoContract>> getWorkersByNumber(@PathVariable String number, @PathVariable int pageNumber, @PathVariable int itemsOnPage) {
        Set<ShortInfoContract> contracts = contractDTO.getByNumber(number, itemsOnPage, pageNumber, "number", 0);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("contractCount", String.valueOf(contractService.getCountRows()));
        return new ResponseEntity<>(contracts, multiValueMap, HttpStatus.OK);
    }


    @GetMapping(value = "/contract/id-{contractId}")
    public ResponseEntity<Contract> getWorkerBySsoId(@PathVariable Long contractId) {
        Contract contract = contractService.findById(contractId);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @DeleteMapping(value = { "/contract/{contractId}" })
    public ResponseEntity deleteContract(@PathVariable Long contractId) {
        try{
            contractService.removeById(contractId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = {"/contract/edit"})
    public ResponseEntity editWorker(@RequestBody Contract contract, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            if (contractService.isContractNumberUnique(contract.getNumber())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            try {
                contractService.update(contract);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.SEE_OTHER);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
