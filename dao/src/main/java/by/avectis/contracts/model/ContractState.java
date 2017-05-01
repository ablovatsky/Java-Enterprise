package by.avectis.contracts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CONTRACTS_STATE")
public class ContractState {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Transient
    @OneToMany(mappedBy = "contractState")
    private Set<Contract> contractsList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Contract> getContractsList() {
        return contractsList;
    }

    public void setContractsList(Set<Contract> contractsList) {
        this.contractsList = contractsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractState that = (ContractState) o;
        return id.equals(that.id) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ContractsState{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", contractsList=" + contractsList +
                '}';
    }
}
