package by.avectis.contracts.controller.restController;

import by.avectis.contracts.dto.subdivision.SubdivisionDTO;
import by.avectis.contracts.dto.subdivision.modelDTO.ShortInfoSubdivision;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/administration/")
public class SubdivisionRestController {

    @Autowired
    private SubdivisionService subdivisionService;

    @Autowired
    private SubdivisionDTO subdivisionDTO;

    @GetMapping( value = { "/subdivisionLists" })
    public ResponseEntity<Set<ShortInfoSubdivision>> getSubdivisions(){
        return new ResponseEntity<>(subdivisionDTO.getSubdivisionSet(), HttpStatus.OK);
    }

    @PostMapping(value = {"/subdivisions/subdivision/new"})
    public ResponseEntity addSubdivision(@RequestBody Subdivision subdivision, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            if (!subdivisionService.isSubdivisionUnique(subdivision.getName())) {
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
            try {
                subdivisionService.add(subdivision);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.SEE_OTHER);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value = { "/subdivisions/subdivision/{id}" })
    public ResponseEntity<ShortInfoSubdivision> getEditSubdivision(@PathVariable long id) {
        ShortInfoSubdivision subdivision = subdivisionDTO.getSubdivision(subdivisionService.findById(id));
        return new ResponseEntity<>(subdivision, HttpStatus.OK);
    }

    @PutMapping(value = {"/subdivisions/subdivision/edit"})
    public ResponseEntity editSubdivision(@RequestBody Subdivision subdivision, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            if (subdivisionService.isSubdivisionUnique(subdivision.getId())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            try {
                if (!subdivisionService.isSubdivisionUnique(subdivision.getName())) {
                    return new ResponseEntity<>(HttpStatus.FOUND);
                }
                subdivisionService.update(subdivision);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.SEE_OTHER);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value = { "/subdivisions/{id}" })
    public ResponseEntity deleteSubdivision(@PathVariable Long id) {
        try {
            subdivisionService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }
}
