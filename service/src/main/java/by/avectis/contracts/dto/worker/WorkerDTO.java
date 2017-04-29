package by.avectis.contracts.dto.worker;

import by.avectis.contracts.dto.worker.modelDTO.InfoWorker;
import by.avectis.contracts.dto.worker.modelDTO.ShortInfoWorker;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class WorkerDTO{

    @Autowired
    private WorkerService workerService;

    private Set<ShortInfoWorker> workerSet;

    /*
    * Get WORKER DTO object
    */
    public InfoWorker getWorker(Worker worker) {
        return new InfoWorker(worker);
    }

    /*
    * Get WORKERs list DTO objects
    */
    public Set<ShortInfoWorker> getWorkerSet(int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerSet(count, setNumber, sortingColumn, sortingType);
        return workerSet;
    }

    /*
    * Get WORKERs list DTO objects  by subdivisionId
    */
    public Set<ShortInfoWorker> getWorkerSetBySubdivision(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerSet(subdivisionId, count, setNumber, sortingColumn, sortingType);
        return workerSet;
    }


    /*
    * Get WORKERs list DTO objects  by lastName
    */
    public Set<ShortInfoWorker> getWorkerSetByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
        setWorkerSet(lastName, count, setNumber, sortingColumn, sortingType);
        return workerSet;
    }


    private void setWorkerSet(int count, int setNumber, String sortingColumn, int sortingType) {
        workerSet = new LinkedHashSet<>();
        Set<Worker> workerList = workerService.findAll(count, setNumber, sortingColumn, sortingType);
        workerList.forEach( worker -> this.workerSet.add(new ShortInfoWorker(worker)) );
    }

    private void setWorkerSet(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) {
        workerSet = new LinkedHashSet<>();
        Set<Worker> workerList = workerService.findAllBySubdivisionId(subdivisionId, count, setNumber, sortingColumn, sortingType);
        workerList.forEach( worker -> this.workerSet.add(new ShortInfoWorker(worker)) );
    }

    private void setWorkerSet(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
        workerSet = new LinkedHashSet<>();
        Set<Worker> workerList = workerService.findAllByLastName(lastName, count, setNumber, sortingColumn, sortingType);
        workerList.forEach( worker -> this.workerSet.add(new ShortInfoWorker(worker)) );
    }

}
