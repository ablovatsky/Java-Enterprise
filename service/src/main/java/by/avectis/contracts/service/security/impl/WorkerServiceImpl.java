package by.avectis.contracts.service.security.impl;

import by.avectis.contracts.dao.security.WorkerDao;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.security.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("workerService")
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public Worker findById(Long id) {
		return dao.findById(id);
	}

    @Override
	public Worker findBySSO(String sso) {
		return dao.findBySSO(sso);
	}

    @Override
	public void saveWorker(Worker worker) {
		worker.setPassword(passwordEncoder.encode(worker.getPassword()));
		dao.addWorker(worker);
	}

    @Override
	public void updateWorker(Worker worker) {
        if (worker != null) {
            worker.setPassword(passwordEncoder.encode(worker.getPassword()));
            dao.updateWorker(worker);
        }
	}

    @Override
	public void deleteWorkerBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

    @Override
	public List<Worker> findAllWorkers() {
		return dao.findAllWorkers();
	}

    @Override
	public boolean isWorkerSSOUnique(String sso) {
		Worker user = findBySSO(sso);
		return  user == null;
	}
	
}
