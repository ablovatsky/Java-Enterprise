package by.avectis.contracts.controller;


import by.avectis.contracts.model.Profile;
import by.avectis.contracts.model.ProfileType;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.security.ProfileService;
import by.avectis.contracts.service.security.SubdivisionService;
import by.avectis.contracts.service.security.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class SecurityController {

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private SubdivisionService subdivisionService;
	
	@Autowired
	private	MessageSource messageSource;

	@Autowired
	private	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	private	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listWorkers(ModelMap model) {
		List<Worker> workers = workerService.findAllWorkers();
		model.addAttribute("workers", workers);
		model.addAttribute("loggedinworker", getPrincipal());
		return "workerslist";
	}

	@RequestMapping(value = {"/newworker" }, method = RequestMethod.GET)
	public String newWorker(ModelMap model) {
		Worker worker = new Worker();
		model.addAttribute("worker", worker);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinworker", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/newworker" }, method = RequestMethod.POST)
	public String saveWorker(@Valid Worker worker, BindingResult result,
						   ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!workerService.isWorkerSSOUnique(worker.getSsoId())){
			FieldError ssoError = new FieldError("worker","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{worker.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}

		if(worker.getWorkerProfiles().isEmpty()) {
			Set<Profile> workerProfiles = new HashSet<>();
			workerProfiles.add(profileService.findByType(ProfileType.USER.getProfileType()));
			worker.setWorkerProfiles(workerProfiles);
		}
		workerService.saveWorker(worker);

		model.addAttribute("success", "Worker " + worker.getFirstName() + " "+ worker.getLastName() + " registered successfully");
		model.addAttribute("loggedinworker", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}


	@RequestMapping(value = { "/edit-worker-{ssoId}" }, method = RequestMethod.GET)
	public String editWorker(@PathVariable String ssoId, ModelMap model) {
		Worker worker = workerService.findBySSO(ssoId);
		model.addAttribute("worker", worker);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinworker", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/edit-worker-{ssoId}" }, method = RequestMethod.POST)
	public String updateWorker(@Valid Worker worker, BindingResult result,
							 ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		workerService.updateWorker(worker);

		model.addAttribute("success", "Worker " + worker.getFirstName() + " "+ worker.getLastName() + " updated successfully");
		model.addAttribute("loggedinworker", getPrincipal());
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/delete-worker-{ssoId}" }, method = RequestMethod.GET)
	public String deleteWorker(@PathVariable String ssoId) {
		workerService.deleteWorkerBySSO(ssoId);
		return "redirect:/list";
	}

	@ModelAttribute("roles")
	public List<Profile> initializeProfiles() {
		return profileService.findAll();
	}

	@ModelAttribute("subdivisions")
	public List<Subdivision> initializeSubdivisions() {
	    return subdivisionService.findAll();
    }

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinworker", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/list";
	    }
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal(){
		String workerName;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			workerName = ((UserDetails)principal).getUsername();
		} else {
			workerName = principal.toString();
		}
		return workerName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}


}