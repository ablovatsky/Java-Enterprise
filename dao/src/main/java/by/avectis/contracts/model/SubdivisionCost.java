package by.avectis.contracts.model;

import by.avectis.contracts.dao.Util.LocalDateConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "SUBDIVISION_COST_PER_HOUR")
public class SubdivisionCost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subdivision_id")
    private Subdivision subdivision;

    @Column(name = "cost")
    private float cost;

    @Column(name = "date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;

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

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubdivisionCost that = (SubdivisionCost) o;

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
