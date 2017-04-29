package by.avectis.contracts.dto.subdivision;

import by.avectis.contracts.dto.subdivision.modelDTO.ShortInfoSubdivision;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class SubdivisionDTO {

    @Autowired
    private SubdivisionService subdivisionService;

    private Set<ShortInfoSubdivision> subdivisionList;

    public ShortInfoSubdivision getSubdivision(Subdivision subdivision) {
        return new ShortInfoSubdivision(subdivision);
    }

    public Set<ShortInfoSubdivision> getSubdivisionSet() {
        setSubdivisionList();
        return subdivisionList;
    }

    private void setSubdivisionList() {
        subdivisionList = new LinkedHashSet<>();
        Set<Subdivision> subdivisionList = subdivisionService.findAll();
        subdivisionList.forEach( subdivision -> this.subdivisionList.add(new ShortInfoSubdivision(subdivision)) );
    }



}
