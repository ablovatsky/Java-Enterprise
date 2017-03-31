package by.pvt.restaurants.dao.security;

import by.pvt.restaurants.model.User;

import java.util.List;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void addUser(User user);

	void updateUser(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

}

