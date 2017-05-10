package by.avectis.contracts.facade.model;

import by.avectis.contracts.dto.worker.modelDTO.ShortInfoWorker;
import by.avectis.contracts.model.Employment;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class TimeSheetDate implements Serializable{

    private Set<String> contractNumberSet;

    private Set<ShortInfoWorker> workerSet;

    private Set<String> selectedContractNumberSet;

    private Set<ShortEmployment> employmentSet;

    public TimeSheetDate() {
        this.contractNumberSet = new LinkedHashSet<>();
        this.workerSet = new LinkedHashSet<>();
        this.selectedContractNumberSet = new LinkedHashSet<>();
        this.employmentSet = new LinkedHashSet<>();
    }

    public Set<String> getContractNumberSet() {
        return contractNumberSet;
    }

    public void setContractNumberSet(Set<String> contractNumberSet) {
        this.contractNumberSet = contractNumberSet;
    }

    public Set<ShortInfoWorker> getWorkerSet() {
        return workerSet;
    }

    public void setWorkerSet(Set<ShortInfoWorker> workerSet) {
        this.workerSet = workerSet;
    }

    public Set<String> getSelectedContractNumberSet() {
        return selectedContractNumberSet;
    }

    public void setSelectedContractNumberSet(Set<String> selectedContractNumberSet) {
        this.selectedContractNumberSet = selectedContractNumberSet;
    }

    public Set<ShortEmployment> getEmploymentSet() {
        return employmentSet;
    }

    public void setEmploymentSet(Set<Employment> employmentSet) {
        employmentSet.forEach(employment -> this.employmentSet.add(new ShortEmployment(employment)));

    }


}
