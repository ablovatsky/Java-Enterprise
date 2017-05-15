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

    public ShortInfoSubdivisionCost(Long id, ShortInfoSubdivision subdivision, float cost, LocalDate date) {
        this.id = id;
        this.subdivision = subdivision;
        this.cost = cost;
        this.date = date;
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

        if (!subdivision.equals(that.subdivision)) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = subdivision.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
