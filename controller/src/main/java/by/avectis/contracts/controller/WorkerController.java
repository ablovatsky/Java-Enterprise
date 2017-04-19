package by.avectis.contracts.controller;


import by.avectis.contracts.dto.worker.UtilDTO;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.worker.ProfileService;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import by.avectis.contracts.service.worker.WorkerService;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class WorkerController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private UtilDTO utilDTO;
	
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
	public String listWorkers() {
		return "workerslist";
	}

	@RequestMapping(value = { "/getWorkers" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getListWorkers() {
		JSONObject jsonObject = utilDTO.getWorkerListToJson();
		jsonObject.put("loggedinworker", getPrincipal());
		return jsonObject.toString();
	}

    @RequestMapping(value = {"/new-worker" }, method = RequestMethod.GET)
    public String newWorker(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("ssoId", "newWorker");
        return "registration";
    }

    @ResponseBody
	@RequestMapping(value = {"/getNewWorker" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getNewWorker() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loggedinworker", getPrincipal());
		jsonObject.put("roles", profileService.findAll());
		jsonObject.put("subdivision", subdivisionService.findAll());
		return jsonObject.toString();
	}

    @ResponseBody
	@RequestMapping(value = { "/new-worker" }, method = RequestMethod.POST)
	public Worker saveWorker(@RequestBody Worker jsonString) {

        int a = 0;
		/*if (result.hasErrors()) {
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
		workerService.addWorker(worker);
		model.addAttribute("success", "Worker " + worker.getFirstName() + " "+ worker.getLastName() + " registered successfully");
		model.addAttribute("loggedinworker", getPrincipal());*/
		return jsonString;
	}


	@RequestMapping(value = { "/edit-worker-{ssoId}" }, method = RequestMethod.GET)
	public String editWorker(@PathVariable String ssoId, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("ssoId", ssoId);
		return "registration";
	}

	@RequestMapping(value = { "/get-edit-worker-{ssoId}" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getEditWorker(@PathVariable String ssoId) {
		Worker worker = workerService.findWorkerBySSO(ssoId);
		if (worker != null) {
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("worker", utilDTO.getWorkerToJson(worker));
			jsonObject.put("loggedinworker", getPrincipal());
			jsonObject.put("roles", profileService.findAll());
			jsonObject.put("subdivision", subdivisionService.findAll());
			return jsonObject.toString();
		}
		return "workerslist";
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