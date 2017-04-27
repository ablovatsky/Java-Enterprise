package by.avectis.contracts.controller.restController;

import by.avectis.contracts.dto.subdivision.SubdivisionDTO;
import by.avectis.contracts.dto.subdivision.modelDTO.ShortInfoSubdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Set;

@RestController
@RequestMapping("/administration/")
@SessionAttributes("roles")
public class SubdivisionRestController {

    @Autowired
    private SubdivisionDTO subdivisionDTO;

    @GetMapping( value = { "/subdivisionLists" })
    public ResponseEntity<Set<ShortInfoSubdivision>> getSubdivisions(){
        return new ResponseEntity<>(subdivisionDTO.getSubdivisionSet(), HttpStatus.OK);
    }
}
