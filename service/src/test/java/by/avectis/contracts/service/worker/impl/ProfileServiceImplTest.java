package by.avectis.contracts.service.worker.impl;

import by.avectis.contracts.config.TestHibernateConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateConfiguration.class)
@WebAppConfiguration
@Transactional
public class ProfileServiceImplTest {
    @Test
    public void findById() throws Exception {
    }

    @Test
    public void findByType() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

}