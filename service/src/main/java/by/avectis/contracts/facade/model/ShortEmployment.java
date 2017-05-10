package by.avectis.contracts.facade.model;

import by.avectis.contracts.model.Employment;
import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;
public class ShortEmployment implements Serializable{

    private String contractNumber;

    private String workerSSOID;

    private int workedHours;

    public ShortEmployment() {
    }

    public ShortEmployment(@JsonProperty("contractNumber") String contractNumber, @JsonProperty("workerSSOID") String workerSSOID, @JsonProperty("workedHours") int workedHours) {
        this.contractNumber = contractNumber;
        this.workerSSOID = workerSSOID;
        this.workedHours = workedHours;
    }

    public ShortEmployment(Employment employment) {
        contractNumber = employment.getContract().getNumber();
        workerSSOID = employment.getWorker().getSsoId();
        workedHours = employment.getHoursWorked();
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getWorkerSSOID() {
        return workerSSOID;
    }

    public void setWorkerSSOID(String workerSSOID) {
        this.workerSSOID = workerSSOID;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortEmployment that = (ShortEmployment) o;

        if (contractNumber != null ? !contractNumber.equals(that.contractNumber) : that.contractNumber != null)
            return false;
        return workerSSOID != null ? workerSSOID.equals(that.workerSSOID) : that.workerSSOID == null;
    }

    @Override
    public int hashCode() {
        int result = contractNumber != null ? contractNumber.hashCode() : 0;
        result = 31 * result + (workerSSOID != null ? workerSSOID.hashCode() : 0);
        return result;
    }
}
