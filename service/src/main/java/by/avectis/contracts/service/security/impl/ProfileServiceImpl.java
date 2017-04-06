package by.avectis.contracts.service.security.impl;

import by.avectis.contracts.model.Profile;
import by.avectis.contracts.dao.security.ProfileDao;
import by.avectis.contracts.service.security.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("workerProfileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao dao;

	@Override
	public Profile findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Profile findByType(String type){
		return dao.findByType(type);
	}

	@Override
	public List<Profile> findAll() {
		return dao.findAll();
	}
}
