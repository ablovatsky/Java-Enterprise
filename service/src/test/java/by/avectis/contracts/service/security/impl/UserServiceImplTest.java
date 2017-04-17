package by.avectis.contracts.service.security.impl;


import by.avectis.contracts.config.TestControllerConfiguration;
import by.avectis.contracts.dao.DaoException;
import by.avectis.contracts.model.Profile;
import by.avectis.contracts.model.ProfileType;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.ServiceException;
import by.avectis.contracts.service.security.ProfileService;
import by.avectis.contracts.service.security.SubdivisionService;
import by.avectis.contracts.service.security.WorkerService;
import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfiguration.class)
@WebAppConfiguration
public class UserServiceImplTest  {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SubdivisionService subdivisionService;

    private Worker worker;

    @Test
    public void deleteUserBySSO(){
        try {
            workerService.deleteWorkerBySSO("workerTest");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isUserSSOUnique(){
        try {
            if (workerService.isWorkerSSOUnique("admin")){
                System.out.println("SSO - уникальное");
            } else {
                System.out.println("SSO - неуникальное");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveUser(){
        try {
            worker = new Worker();
            Set<Profile> workerProfiles = new HashSet<>();
            workerProfiles.add(profileService.findByType(ProfileType.USER.getProfileType()));
            Subdivision subdivision = subdivisionService.findByName("ОРПО");
            worker.setFirstName("workerTest");
            worker.setLastName("workerTest");
            worker.setSsoId("workerTest");
            worker.setPatronymic("workerTest");
            worker.setPassword("workerTest");
            worker.setWorkerProfiles(workerProfiles);
            worker.setSubdivision(subdivision);
            worker.setEmail("workerTest");
            workerService.addWorker(worker);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findBySSO(){
        try {
            worker = workerService.findWorkerBySSO("workerTest");
            if (worker == null) {
                System.out.println("Пользователь не найден");
            } else {
                System.out.println(worker.toString());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser(){
        try {
        worker = workerService.findWorkerBySSO("workerTest");
        workerService.updateWorker(worker);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById(){
        try {
            worker = workerService.findWorkerById((long) 176);
            if (worker == null) {
                System.out.println("Пользователь не найден");
            } else {
                System.out.println(worker.toString());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsers(){
        System.out.println("Testing findAllWorkers()");
        try {
            List<Worker> workerList = workerService.findAllWorkers();
            for (Worker worker : workerList) {
                System.out.println(worker.toString());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }



}