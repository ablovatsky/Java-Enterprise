package by.avectis.contracts.dto.contract.modelDTO;


import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;

import java.time.LocalDate;

public class ShortInfoContract {

    private long id;

    private String number;

    private String name;

    private String axCode;

    private LocalDate timeStart;

    private LocalDate timeStop;

    private ContractState contractState;

    public ShortInfoContract(Contract contract) {
        id = contract.getId();
        number = contract.getNumber();
        name = contract.getName();
        axCode = contract.getAxCode();
        timeStart = contract.getTimeStart();
        timeStop = contract.getTimeStop();
        contractState = contract.getContractState();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(LocalDate timeStop) {
        this.timeStop = timeStop;
    }

    public ContractState getContractState() {
        return contractState;
    }

    public void setContractState(ContractState contractState) {
        this.contractState = contractState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortInfoContract that = (ShortInfoContract) o;

        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
