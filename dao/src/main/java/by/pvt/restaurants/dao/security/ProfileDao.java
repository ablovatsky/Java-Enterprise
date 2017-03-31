package by.pvt.restaurants.dao.security;

import by.pvt.restaurants.model.Profile;

import java.util.List;


public interface ProfileDao {

	List<Profile> findAll();
	
	Profile findByType(String type);
	
	Profile findById(int id);
}
