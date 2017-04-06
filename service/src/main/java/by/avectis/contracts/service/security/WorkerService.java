package by.avectis.contracts.service.security;

import by.avectis.contracts.model.Worker;

import java.util.List;


public interface WorkerService {
	
	Worker findById(Long id);
	
	Worker findBySSO(String sso);
	
	void saveWorker(Worker worker);
	
	void updateWorker(Worker worker);
	
	void deleteWorkerBySSO(String sso);

	List<Worker> findAllWorkers();
	
	boolean isWorkerSSOUnique(String sso);

}