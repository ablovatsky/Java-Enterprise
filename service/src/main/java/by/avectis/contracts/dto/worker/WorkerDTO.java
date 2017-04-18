package by.avectis.contracts.dto.worker;

import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.worker.WorkerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class WorkerDTO  implements Serializable {

    @Autowired
    private WorkerService workerService;

    private Set<ShortInfoWorker> workerList;

    public WorkerDTO() {
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

    public JSONObject getWorkerListToJson() {
        setWorkerList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workerList", workerList);
        return jsonObject ;
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
