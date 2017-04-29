package by.avectis.contracts.service.worker.impl;


import by.avectis.contracts.config.TestHibernateConfiguration;
import by.avectis.contracts.model.Profile;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.worker.ProfileService;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import by.avectis.contracts.service.worker.WorkerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateConfiguration.class)
@WebAppConfiguration
@Transactional
public class WorkerServiceImplTest extends Assert {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SubdivisionService subdivisionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void deleteWorkerBySSO(){
        workerService.add(createWorker("testWorker"));
        workerService.removeBySSOID("testWorker");
        assertNull(workerService.findBySSOID("testWorker"));
        workerService.removeBySSOID(null);
    }

    @Test
    public void isWorkerSSOUnique(){
        workerService.add(createWorker("testWorker"));
        assertFalse(workerService.isWorkerSSOIDUnique("testWorker"));
        assertTrue(workerService.isWorkerSSOIDUnique(null));

    }

    @Test(expected=ServiceException.class)
    public void addWorker(){
        workerService.add(createWorker("testWorker"));
        assertNotNull(workerService.findBySSOID("testWorker"));
        workerService.add(createWorker("testWorker"));
    }

    @Test
    public void findWorkerBySSO(){
        workerService.add(createWorker("testWorker"));
        assertNotNull(workerService.findBySSOID("testWorker"));
    }

    @Test(expected=ServiceException.class)
    public void updateWorker(){
        Worker worker = createWorker("testWorker");
        Worker editWorker = createWorker("testWorker");
        workerService.add(worker);
        editWorker.setId( workerService.findBySSOID("testWorker").getId());
        editWorker.setFirstName("testWorkerUpdateUser");
        editWorker.setPassword("test");
        workerService.update(editWorker);
        assertEquals("testWorkerUpdateUser", workerService.findBySSOID("testWorker").getFirstName());

        editWorker.setPassword(workerService.findBySSOID("testWorker").getPassword());
        workerService.update(editWorker);

        Worker emptyWorker = new Worker();
        emptyWorker.setId(99999L);
        workerService.update(emptyWorker);
        assertTrue(true);

        Worker nullWorker = new Worker();
        workerService.update(nullWorker);
    }

    @Test(expected=ServiceException.class)
    public void findWorkerById(){
        workerService.add(createWorker("testWorker"));
        assertNotNull(workerService.findById(1L));
        workerService.findById(null);
    }

    @Test
    public void findAllWorkers(){
        workerService.add(createWorker("testWorker"));
        assertNotNull( workerService.findAll(1000, 1,"ssoId", 0));
    }

    @Test
    public void getCountWorkers() throws Exception {
        workerService.add(createWorker("testWorker"));
        assertNotNull( workerService.getCountRows());
    }

    @Test
    public void findAllWorkersBySubdivisionId() {
        workerService.add(createWorker("testWorker"));
        assertNotNull(workerService.findAllBySubdivisionId(1,1000, 1,"ssoId", 0));
        assertNull(workerService.findAllBySubdivisionId(100,1000, 1,"ssoId", 0));
    }

    @Test
    public void findAllWorkersByLastName() {
        workerService.add(createWorker("testWorker"));
        assertNotEquals(0, workerService.findAllByLastName("testWorker",1000, 1,"ssoId", 0));
        assertEquals(0, workerService.findAllByLastName("null",1000, 1,"ssoId", 0).size());
    }


    @Test
    public void getCountWorkers1() {
        workerService.add(createWorker("testWorker"));
        assertEquals(1, workerService.getCountRowsByLastName("testWorker"));
        assertEquals(0, workerService.getCountRowsByLastName("null"));
    }

    @Test
    public void getCountWorkers2() {
        workerService.add(createWorker("testWorker"));
        assertNotEquals(0, workerService.getCountRowsBySubdivisionId(1));
        assertEquals(0, workerService.getCountRowsBySubdivisionId(100));
    }

    public Worker createWorker(String ssoId) {
        Worker worker = new Worker();
        worker.setSsoId(ssoId);
        worker.setFirstName("testWorker");
        worker.setLastName("testWorker");
        worker.setPatronymic("testWorker");
        worker.setPassword(passwordEncoder.encode("testWorker"));
        worker.setEmail("testWorker");
        worker.setSubdivision(subdivisionService.findByName("Administrators"));
        Set<Profile> profiles = new HashSet<>();
        profiles.add(profileService.findByType("ADMIN"));
        worker.setWorkerProfiles(profiles);
        return worker;
    }

}