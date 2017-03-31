package by.pvt.restaurants.converter;


import by.pvt.restaurants.model.Profile;
import by.pvt.restaurants.service.security.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class RoleToUserProfileConverter implements Converter<Object, Profile>{

	private static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	private
	ProfileService profileService;

	public Profile convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Profile profile= profileService.findById(id);
		logger.info("Profile : {}",profile);
		return profile;
	}
	
}