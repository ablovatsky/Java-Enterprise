package by.avectis.contracts.dto.subdivision;

import by.avectis.contracts.dto.subdivision.modelDTO.ShortInfoSubdivisionCost;
import by.avectis.contracts.model.SubdivisionCost;
import by.avectis.contracts.service.subdivision.SubdivisionCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class SubdivisionCostDTO {

    @Autowired
    private SubdivisionCostService subdivisionCostService;

    private Set<ShortInfoSubdivisionCost> subdivisionCostSet;

    private void setSubdivisionCostSet(LocalDate date) {
        subdivisionCostSet = new LinkedHashSet<>();
        Set<SubdivisionCost> subdivisionCostList = subdivisionCostService.findAllByDate(date);
        subdivisionCostList.forEach( subdivisionCost -> this.subdivisionCostSet.add(new ShortInfoSubdivisionCost(subdivisionCost)) );
    }

    public Set<ShortInfoSubdivisionCost> getSubdivisionCostSet(LocalDate date) {
        setSubdivisionCostSet(date);
        return subdivisionCostSet;
    }
}
