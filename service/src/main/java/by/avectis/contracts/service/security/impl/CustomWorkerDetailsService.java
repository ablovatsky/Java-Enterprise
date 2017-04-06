package by.avectis.contracts.service.security.impl;


import by.avectis.contracts.model.Profile;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.security.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customWorkerDetailsService")
public class CustomWorkerDetailsService implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(CustomWorkerDetailsService.class);
	
	@Autowired
	private WorkerService workerService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		Worker user = workerService.findBySSO(ssoId);
		logger.info("Worker : {}", user);
		if(user==null){
			logger.info("Worker not found");
			throw new UsernameNotFoundException("Workername not found");
		}
			return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Worker worker){
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(Profile userProfile : worker.getWorkerProfiles()){
			logger.info("WorkerProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}
	
}
