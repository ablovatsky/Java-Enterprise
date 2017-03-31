package by.pvt.restaurants.service.security.impl;

import by.pvt.restaurants.config.ControllerConfiguration;
import by.pvt.restaurants.model.Profile;
import by.pvt.restaurants.model.ProfileType;
import by.pvt.restaurants.model.User;
import by.pvt.restaurants.service.security.ProfileService;
import by.pvt.restaurants.service.security.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ControllerConfiguration.class)
@WebAppConfiguration
public class UserServiceImplTest  {

    @Resource
    @Autowired
    private	UserService userService;

    @Autowired
    private ProfileService profileService;


    private User user;

    @Test
    public void deleteUserBySSO() throws Exception {
        userService.deleteUserBySSO("userTest");
    }

    @Test
    public void isUserSSOUnique() throws Exception {
        if (userService.isUserSSOUnique("admin")){
            System.out.println("SSO - уникальное");
        } else {
            System.out.println("SSO - неуникальное");
        }
    }

    @Test
    public void saveUser() throws Exception {
        user = new User();
        Set<Profile> userProfiles = new HashSet<>();
        userProfiles.add(profileService.findByType(ProfileType.USER.getProfileType()));
        user.setFirstName("userTest");
        user.setSsoId("userTest");
        user.setLastName("userTest");
        user.setEmail("userTest@user.com");
        user.setPassword("userTest");
        user.setUserProfiles(userProfiles);
        userService.saveUser(user);
    }

    @Test
    public void findBySSO() throws Exception {
        user = userService.findBySSO("userTest");
        if (user == null) {
            System.out.println("Пользователь не найден");
        } else {
            System.out.println(user.toString());
        }
    }

    @Test
    public void updateUser() throws Exception {
        user = userService.findBySSO("userTest");
        user.setFirstName("newUser");
        userService.updateUser(user);
    }

    @Test
    public void findById() throws Exception {
        user = userService.findById(176);
        if (user == null) {
            System.out.println("Пользователь не найден");
        } else {
            System.out.println(user.toString());
        }
    }

    @Test
    public void findAllUsers() throws Exception {
        System.out.println("Testing findAllUsers()");
        List<User> userList =  userService.findAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }



}