package by.avectis.contracts.dto.worker;

import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.worker.WorkerService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class UtilDTO implements Serializable {

    @Autowired
    private WorkerService workerService;

    private Set<ShortInfoWorker> workerList;

    private InfoWorker worker;

    public UtilDTO() {
        workerList = new LinkedHashSet<>();
    }

    private void setWorkerList() {
        List<Worker> workerList = workerService.findAllWorkers();
        workerList.forEach( worker -> this.workerList.add(new ShortInfoWorker(worker)) );
    }

    public Set<ShortInfoWorker> getWorkerList() {
        setWorkerList();
        return workerList;
    }

    public InfoWorker getWorker(Worker worker) {
        this.worker = new InfoWorker(worker);
        return this.worker;
    }

    public JSONObject getWorkerToJson(Worker worker) {
        this.worker = new InfoWorker(worker);
        return new JSONObject(new Gson().toJson(this.worker));
    }

    public JSONObject getWorkerListToJson() {
        setWorkerList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workerList", workerList);
        return jsonObject ;
    }

    public class InfoWorker implements Serializable {

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
            ssoId = worker.getSsoId();
            firstName = worker.getFirstName();
            lastName = worker.getLastName();
            patronymic = worker.getPatronymic();
            password = worker.getPassword();
            email = worker.getEmail();
            subdivision = worker.getSubdivision().getName();
            worker.getWorkerProfiles().forEach(profile -> workerProfiles.add(profile.getType()));
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
                    "ssoId='" + ssoId + '\'' +
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
}
