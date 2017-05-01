package by.avectis.contracts.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "LABOR_INTENSITY")
public class LaborIntensity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "subdivision_id")
    private Subdivision subdivision;

    @Column(name = "planned_labor_intensity")
    private float plannedLaborIntensity;

    @Column(name = "planned_cost")
    private float plannedCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public float getPlannedLaborIntensity() {
        return plannedLaborIntensity;
    }

    public void setPlannedLaborIntensity(float plannedLaborIntensity) {
        this.plannedLaborIntensity = plannedLaborIntensity;
    }

    public float getPlannedCost() {
        return plannedCost;
    }

    public void setPlannedCost(float plannedCost) {
        this.plannedCost = plannedCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaborIntensity that = (LaborIntensity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
