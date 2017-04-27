package by.avectis.contracts.dto.worker.modelDTO;

import by.avectis.contracts.model.Worker;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class InfoWorker implements Serializable {

    private long id;

    private String ssoId;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String subdivision;

    private Set<String> workerProfiles = new HashSet<>();

    public InfoWorker() {
    }

    public InfoWorker(Worker worker) {
        id = worker.getId();
        ssoId = worker.getSsoId();
        firstName = worker.getFirstName();
        lastName = worker.getLastName();
        patronymic = worker.getPatronymic();
        password = worker.getPassword();
        email = worker.getEmail();
        subdivision = worker.getSubdivision().getName();
        worker.getWorkerProfiles().forEach(profile -> workerProfiles.add(profile.getType()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public Set<String> getWorkerProfiles() {
        return workerProfiles;
    }

    public void setWorkerProfiles(Set<String> workerProfiles) {
        this.workerProfiles = workerProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoWorker that = (InfoWorker) o;

        return ssoId.equals(that.ssoId);
    }

    @Override
    public int hashCode() {
        return ssoId.hashCode();
    }

    @Override
    public String toString() {
        return "InfoWorker{" +
                "id=" + id +
                ", ssoId='" + ssoId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", subdivision='" + subdivision + '\'' +
                ", workerProfiles=" + workerProfiles +
                '}';
    }
}
