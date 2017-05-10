package by.avectis.contracts.model;

import by.avectis.contracts.dao.Util.LocalDateConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYMENT")
public class Employment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(name = "hours_worked")
    private int hoursWorked;

    @Column(name = "date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "time_sheet_id")
    private TimeSheet timeSheet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TimeSheet getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(TimeSheet timeSheet) {
        this.timeSheet = timeSheet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employment that = (Employment) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
