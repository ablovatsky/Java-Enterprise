package by.pvt.restaurants.service.security.impl;

import by.pvt.restaurants.dao.security.ProfileDao;
import by.pvt.restaurants.model.Profile;
import by.pvt.restaurants.service.security.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userProfileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao dao;

	@Override
	public Profile findById(int id) {
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
