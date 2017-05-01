package by.avectis.contracts.model;

import by.avectis.contracts.dao.Util.LocalDateConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CONTRACTS")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "denomination")
    private String denomination;

    @Column(name = "ax_code")
    private String axCode;

    @Column(name = "time_start")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate timeStart;

    @Column(name = "time_stop")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate timeStop;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "planned_surcharge")
    private Float plannedSurcharge;

    @Column(name = "planned_Profit")
    private Float plannedProfit;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "state")
    private ContractState contractState;

    @Column(name = "comment_1")
    @Type(type="text")
    private String commentFirst;

    @Column(name = "comment_2")
    @Type(type="text")
    private String commentSecond;

    @Transient
    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    private Set<LaborIntensity> laborIntensitySet = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getAxCode() {
        return axCode;
    }

    public void setAxCode(String axCode) {
        this.axCode = axCode;
    }

    public LocalDate getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDate timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeStart(String timeStart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.timeStart = LocalDate.parse(timeStart, formatter);
    }

    public LocalDate getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(LocalDate timeStop) {
        this.timeStop = timeStop;
    }

    public void setTimeStop(String timeStop) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.timeStop = LocalDate.parse(timeStop, formatter);
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPlannedSurcharge() {
        return plannedSurcharge;
    }

    public void setPlannedSurcharge(Float plannedSurcharge) {
        this.plannedSurcharge = plannedSurcharge;
    }

    public Float getPlannedProfit() {
        return plannedProfit;
    }

    public void setPlannedProfit(Float planned_profit) {
        this.plannedProfit = planned_profit;
    }

    public ContractState getContractState() {
        return contractState;
    }

    public void setContractState(ContractState contractsState) {
        this.contractState = contractsState;
    }

    public String getCommentFirst() {
        return commentFirst;
    }

    public void setCommentFirst(String commentFirst) {
        this.commentFirst = commentFirst;
    }

    public String getCommentSecond() {
        return commentSecond;
    }

    public void setCommentSecond(String commentSecond) {
        this.commentSecond = commentSecond;
    }

    public Set<LaborIntensity> getLaborIntensitySet() {
        return laborIntensitySet;
    }

    public void setLaborIntensitySet(Set<LaborIntensity> laborIntensitySet) {
        this.laborIntensitySet = laborIntensitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (id != null ? !id.equals(contract.id) : contract.id != null) return false;
        return number.equals(contract.number);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + number.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contracts{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", denomination='" + denomination + '\'' +
                ", axCode='" + axCode + '\'' +
                ", timeStart=" + timeStart +
                ", timeStop=" + timeStop +
                ", cost=" + cost +
                ", plannedSurcharge=" + plannedSurcharge +
                ", planned_profit=" + plannedProfit +
                ", contractState=" + contractState +

                '}';
    }
}
