package by.avectis.contracts.service.security.impl;

/*import by.pvt.restaurants.config.ControllerConfiguration;
import Profile;
import ProfileType;
import Worker;
import ProfileService;
import WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;*/

/*@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ControllerConfiguration.class)
@WebAppConfiguration*/
public class UserServiceImplTest  {

/*

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ProfileService profileService;


    private Worker worker;

    @Test
    public void deleteUserBySSO() throws Exception {
        workerService.deleteWorkerBySSO("workerTest");
    }

    @Test
    public void isUserSSOUnique() throws Exception {
        if (workerService.isWorkerSSOUnique("admin")){
            System.out.println("SSO - уникальное");
        } else {
            System.out.println("SSO - неуникальное");
        }
    }

    @Test
    public void saveUser() throws Exception {
        worker = new Worker();
        Set<Profile> workerProfiles = new HashSet<>();
        workerProfiles.add(profileService.findByType(ProfileType.USER.getProfileType()));
        worker.setPassword("workerTest");
        worker.setWorkerProfiles(workerProfiles);
        workerService.saveWorker(worker);
    }

    @Test
    public void findBySSO() throws Exception {
        worker = workerService.findBySSO("workerTest");
        if (worker == null) {
            System.out.println("Пользователь не найден");
        } else {
            System.out.println(worker.toString());
        }
    }

    @Test
    public void updateUser() throws Exception {
        worker = workerService.findBySSO("workerTest");
        workerService.updateWorker(worker);
    }

    @Test
    public void findById() throws Exception {
        worker = workerService.findById(176);
        if (worker == null) {
            System.out.println("Пользователь не найден");
        } else {
            System.out.println(worker.toString());
        }
    }

    @Test
    public void findAllUsers() throws Exception {
        System.out.println("Testing findAllWorkers()");
        List<Worker> workerList =  workerService.findAllWorkers();
        for (Worker worker : workerList) {
            System.out.println(worker.toString());
        }
    }

*/


}