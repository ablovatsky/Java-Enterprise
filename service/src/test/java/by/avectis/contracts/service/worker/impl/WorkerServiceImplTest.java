package by.avectis.contracts.service.worker.impl;


import by.avectis.contracts.config.TestControllerConfiguration;
import by.avectis.contracts.config.TestHibernateConfiguration;
import by.avectis.contracts.dto.worker.WorkerDTO;
import by.avectis.contracts.model.Profile;
import by.avectis.contracts.model.ProfileType;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.worker.ProfileService;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import by.avectis.contracts.service.worker.WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateConfiguration.class)
@WebAppConfiguration
@Transactional
public class WorkerServiceImplTest {


    @Autowired
    private WorkerDTO workerDTO;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SubdivisionService subdivisionService;

    private Worker worker;



    @Test
    @Rollback(false)
    public void deleteUserBySSO(){

    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void isUserSSOUnique(){

    }

    @Test
    @Rollback(false)
    public void saveUser(){

    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void findBySSO(){

    }

    @Test
    @Rollback(false)
    public void updateUser(){

    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void findById(){

    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void findAllUsers(){

    }

    @Test
    public void getCountWorkers() throws Exception {

    }



}