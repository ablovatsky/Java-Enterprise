package by.avectis.contracts.dto.worker.modelDTO;

import by.avectis.contracts.model.Worker;

import java.io.Serializable;

public class ShortInfoWorker  implements Serializable {

    private String ssoId;

    private String firstName;

    private String lastName;

    private String subdivision;

    public ShortInfoWorker(Worker worker) {
        ssoId = worker.getSsoId();
        firstName = worker.getFirstName();
        lastName = worker.getLastName();
        subdivision = worker.getSubdivision().getName();
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    @Override
    public String toString() {
        return "ShortInfoWorker{" +
                "ssoId='" + ssoId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subdivision='" + subdivision + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ShortInfoWorker that = (ShortInfoWorker) obj;

        return ssoId.equals(that.ssoId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }
}
