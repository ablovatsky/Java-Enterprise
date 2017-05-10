package by.avectis.contracts.model;

import by.avectis.contracts.dao.Util.LocalDateConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TIMESHEET")
public class TimeSheet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subdivision_id")
    private Subdivision subdivision;

    @Column(name = "state")
    private boolean state;

    @Column(name = "date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;

    @Transient
    @OneToMany(mappedBy = "timeSheet", cascade = CascadeType.REMOVE)
    private Set<Employment> employmentSet= new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isState() {
        return state;
    }

    public Set<Employment> getEmploymentSet() {
        return employmentSet;
    }

    public void setEmploymentSet(Set<Employment> employmentSet) {
        this.employmentSet = employmentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSheet timeSheet = (TimeSheet) o;

        return id != null ? id.equals(timeSheet.id) : timeSheet.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
