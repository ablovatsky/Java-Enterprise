package by.avectis.contracts.dao.security;

import by.avectis.contracts.model.Worker;

import java.util.List;


public interface WorkerDao {

	Worker findById(Long id);
	
	Worker findBySSO(String sso);
	
	void addWorker(Worker worker);

	void updateWorker(Worker worker);
	
	void deleteBySSO(String sso);
	
	List<Worker> findAllWorkers();

}

