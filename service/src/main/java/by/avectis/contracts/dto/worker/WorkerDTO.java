package by.avectis.contracts.dto.worker;

import by.avectis.contracts.dto.worker.modelDTO.InfoWorker;
import by.avectis.contracts.dto.worker.modelDTO.ShortInfoWorker;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.worker.WorkerService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class WorkerDTO{

    @Autowired
    private WorkerService workerService;

    private Set<ShortInfoWorker> workerList;

    private InfoWorker worker;

    /*
    * Get WORKER DTO object
    */
    public InfoWorker getWorker(Worker worker) {
        this.worker = new InfoWorker(worker);
        return this.worker;
    }

    /*
    * Get WORKER DTO jsonObject
    */
    public JSONObject getWorkerToJson(Worker worker) {
        this.worker = new InfoWorker(worker);
        return new JSONObject(new Gson().toJson(this.worker));
    }

    /*
    * Get WORKERs list DTO objects
    */
    public Set<ShortInfoWorker> getWorkerList(int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerList(count, setNumber, sortingColumn, sortingType);
        return workerList;
    }

    /*
    * Get WORKERs list DTO jsonObjects
    */
    public JSONObject getWorkerListToJson(int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerList(count, setNumber, sortingColumn, sortingType);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workerList", workerList);
        return jsonObject ;
    }

    /*
    * Get WORKERs list DTO objects  by subdivisionId
    */
    public Set<ShortInfoWorker> getWorkerListBySubdivision(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerList(subdivisionId, count, setNumber, sortingColumn, sortingType);
        return workerList;
    }

    /*
    * Get WORKERs list DTO jsonObjects by subdivisionId
    */
    public JSONObject getWorkerListBySubdivisionToJson(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerList(subdivisionId, count, setNumber, sortingColumn, sortingType);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workerList", workerList);
        return jsonObject ;
    }

    /*
    * Get WORKERs list DTO objects  by lastName
    */
    public Set<ShortInfoWorker> getWorkerListByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerList(lastName, count, setNumber, sortingColumn, sortingType);
        return workerList;
    }

    /*
    * Get WORKERs list DTO jsonObjects by lastName
    */
    public JSONObject getWorkerListByLastNameToJson(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerList(lastName, count, setNumber, sortingColumn, sortingType);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workerList", workerList);
        return jsonObject ;
    }

    private void setWorkerList(int count, int setNumber, String sortingColumn, int sortingType) {
        workerList = new LinkedHashSet<>();
        List<Worker> workerList = workerService.findAllWorkers(count, setNumber, sortingColumn, sortingType);
        workerList.forEach( worker -> this.workerList.add(new ShortInfoWorker(worker)) );
    }

    private void setWorkerList(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) {
        workerList = new LinkedHashSet<>();
        List<Worker> workerList = workerService.findAllWorkersBySubdivisionId(subdivisionId, count, setNumber, sortingColumn, sortingType);
        workerList.forEach( worker -> this.workerList.add(new ShortInfoWorker(worker)) );
    }

    private void setWorkerList(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
        workerList = new LinkedHashSet<>();
        List<Worker> workerList = workerService.findAllWorkersByLastName(lastName, count, setNumber, sortingColumn, sortingType);
        workerList.forEach( worker -> this.workerList.add(new ShortInfoWorker(worker)) );
    }

}
