package by.avectis.contracts.controller.restController;

import by.avectis.contracts.dto.worker.WorkerDTO;
import by.avectis.contracts.dto.worker.modelDTO.ShortInfoWorker;
import by.avectis.contracts.model.Profile;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.worker.ProfileService;
import by.avectis.contracts.service.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/administration/")
public class WorkerRestController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private WorkerDTO workerDTO;
	
	@Autowired
	private ProfileService profileService;


	@GetMapping(value = { "/workers-{pageNumber}-{itemsOnPage}" })
	public ResponseEntity<Set<ShortInfoWorker>> getWorkers(@PathVariable int pageNumber, @PathVariable int itemsOnPage) {
		Set<ShortInfoWorker> workers = workerDTO.getWorkerSet(itemsOnPage, pageNumber, "ssoId", 0);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("workerCount", String.valueOf(workerService.getCountRows()));
		return new ResponseEntity<>(workers, multiValueMap, HttpStatus.OK);
	}

	@GetMapping(value = { "/workers/subdivision/{subdivisionId}-{pageNumber}-{itemsOnPage}" })
	public ResponseEntity<Set<ShortInfoWorker>> getWorkersBySubdivision(@PathVariable int subdivisionId, @PathVariable int pageNumber, @PathVariable int itemsOnPage) {
		Set<ShortInfoWorker> workers = workerDTO.getWorkerSetBySubdivision(subdivisionId,itemsOnPage, pageNumber, "ssoId", 0);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("workerCount", String.valueOf(workerService.getCountRowsBySubdivisionId(subdivisionId)));
		return new ResponseEntity<>(workers, multiValueMap, HttpStatus.OK);
	}

	@GetMapping(value = { "/workers/lastname/{lastName}-{pageNumber}-{itemsOnPage}" })
	public ResponseEntity<Set<ShortInfoWorker>> getWorkersByLastName(@PathVariable String lastName, @PathVariable int pageNumber, @PathVariable int itemsOnPage) {
		Set<ShortInfoWorker> workers = workerDTO.getWorkerSetByLastName(lastName, itemsOnPage, pageNumber, "ssoId", 0);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("workerCount", String.valueOf(workerService.getCountRowsByLastName(lastName)));
		return new ResponseEntity<>(workers, multiValueMap, HttpStatus.OK);
	}

	@DeleteMapping(value = { "/workers/{ssoId}" })
	public ResponseEntity deleteWorker(@PathVariable String ssoId) {
		try{
			workerService.removeBySSOID(ssoId);
			return new ResponseEntity(HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.SEE_OTHER);
		}
	}

	@GetMapping(value = "/workers/worker/{ssoId}")
	public ResponseEntity<Worker> getWorkerBySsoId(@PathVariable String ssoId) {
		Worker worker = workerService.findBySSOID(ssoId);
		return new ResponseEntity<>(worker, HttpStatus.OK);
	}

	@GetMapping(value = "/profiles")
	public ResponseEntity<List<Profile>> getProfiles() {
		return new ResponseEntity<>(profileService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = {"/workers/worker/new"})
	public ResponseEntity addWorker(@RequestBody Worker worker, BindingResult bindingResult){
		if (!bindingResult.hasErrors()) {
			if (!workerService.isWorkerSSOIDUnique(worker.getSsoId())) {
				return new ResponseEntity<>(HttpStatus.FOUND);
			}
			try {
				workerService.add(worker);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (ServiceException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.SEE_OTHER);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping(value = {"/workers/worker/edit"})
	public ResponseEntity editWorker(@RequestBody Worker worker, BindingResult bindingResult){
		if (!bindingResult.hasErrors()) {
			if (workerService.isWorkerSSOIDUnique(worker.getSsoId())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			try {
				workerService.update(worker);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (ServiceException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.SEE_OTHER);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}