package by.avectis.contracts.dto.subdivision.modelDTO;


import by.avectis.contracts.model.SubdivisionCost;

import java.io.Serializable;
import java.time.LocalDate;

public class ShortInfoSubdivisionCost implements Serializable {

    private Long id;

    private ShortInfoSubdivision subdivision;

    private float cost;

    private LocalDate date;

    public ShortInfoSubdivisionCost(SubdivisionCost subdivisionCost) {
        id = subdivisionCost.getId();
        subdivision = new ShortInfoSubdivision(subdivisionCost.getSubdivision());
        cost = subdivisionCost.getCost();
        date = subdivisionCost.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShortInfoSubdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(ShortInfoSubdivision subdivision) {
        this.subdivision = subdivision;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortInfoSubdivisionCost that = (ShortInfoSubdivisionCost) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return subdivision != null ? subdivision.equals(that.subdivision) : that.subdivision == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subdivision != null ? subdivision.hashCode() : 0);
        return result;
    }
}
