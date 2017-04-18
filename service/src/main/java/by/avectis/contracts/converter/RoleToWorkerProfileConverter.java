package by.avectis.contracts.converter;


import by.avectis.contracts.model.Profile;
import by.avectis.contracts.service.worker.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class RoleToWorkerProfileConverter implements Converter<Object, Profile>{

	private static final Logger logger = LoggerFactory.getLogger(RoleToWorkerProfileConverter.class);
	
	@Autowired
	private
	ProfileService profileService;

	public Profile convert(Object element) {
		Long id = Long.parseLong((String)element);
		Profile profile= profileService.findById(id);
		logger.info("Profile : {}",profile);
		return profile;
	}
	
}