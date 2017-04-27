package by.avectis.contracts.dto.subdivision;

import by.avectis.contracts.dto.subdivision.modelDTO.ShortInfoSubdivision;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class SubdivisionDTO {

    @Autowired
    private SubdivisionService subdivisionService;

    private ShortInfoSubdivision subdivision;

    private Set<ShortInfoSubdivision> subdivisionList;

    public ShortInfoSubdivision getSubdivision(Subdivision subdivision) {
        this.subdivision = new ShortInfoSubdivision(subdivision);
        return this.subdivision;
    }

    public JSONObject getSubdivisionToJson(Subdivision subdivision) {
        this.subdivision = new ShortInfoSubdivision(subdivision);
        return new JSONObject(new Gson().toJson(this.subdivision));
    }

    public Set<ShortInfoSubdivision> getSubdivisionSet() {
        setSubdivisionList();
        return subdivisionList;
    }

    public JSONObject getSubdivisionListToJson() {
        setSubdivisionList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subdivisionList", subdivisionList);
        return jsonObject ;
    }

    private void setSubdivisionList() {
        subdivisionList = new LinkedHashSet<>();
        List<Subdivision> subdivisionList = subdivisionService.findAllSubdivisions();
        subdivisionList.forEach( subdivision -> this.subdivisionList.add(new ShortInfoSubdivision(subdivision)) );
    }



}
