package by.avectis.contracts.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "SUBDIVISION")
public class Subdivision implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "NAME", unique = true, nullable=false)
    private String name ;

    @Transient
    @OneToMany(mappedBy = "subdivision")
    private Set<Worker> workerList = new HashSet<>();

    @Transient
    @OneToMany(mappedBy = "subdivision")
    private Set<LaborIntensity> laborIntensitySet = new HashSet<>();

    @Transient
    @OneToMany(mappedBy = "subdivision", fetch = FetchType.LAZY)
    private Set<TimeSheet> timeSheetSet = new HashSet<>();

    @Transient
    @OneToMany(mappedBy = "subdivision", fetch = FetchType.LAZY)
    private Set<SubdivisionCost> subdivisionCostSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Worker> getWorkerList() {
        return workerList;
    }


    public Set<LaborIntensity> getLaborIntensitySet() {
        return laborIntensitySet;
    }

    public void setLaborIntensitySet(Set<LaborIntensity> laborIntensitySet) {
        this.laborIntensitySet = laborIntensitySet;
    }

    public void setWorkerList(Set<Worker> workerList) {
        this.workerList = workerList;
    }

    public Set<TimeSheet> getTimeSheetSet() {
        return timeSheetSet;
    }

    public void setTimeSheetSet(Set<TimeSheet> timeSheetSet) {
        this.timeSheetSet = timeSheetSet;
    }

    public Set<SubdivisionCost> getSubdivisionCostSet() {
        return subdivisionCostSet;
    }

    public void setSubdivisionCostSet(Set<SubdivisionCost> subdivisionCostSet) {
        this.subdivisionCostSet = subdivisionCostSet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Subdivision))
            return false;
        Subdivision other = (Subdivision) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Subdivision{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workerList=" + workerList +
                '}';
    }
}
