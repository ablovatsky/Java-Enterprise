package by.pvt.restaurants.service.security.impl;

import by.pvt.restaurants.dao.security.UserDao;
import by.pvt.restaurants.model.User;
import by.pvt.restaurants.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public User findById(int id) {
		return dao.findById(id);
	}

    @Override
	public User findBySSO(String sso) {
		return dao.findBySSO(sso);
	}

    @Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.addUser(user);
	}

    @Override
	public void updateUser(User user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            dao.updateUser(user);
        }
		/*User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}*/

	}

    @Override
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

    @Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

    @Override
	public boolean isUserSSOUnique(String sso) {
		User user = findBySSO(sso);
		return  user == null;
	}
	
}
