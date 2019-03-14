import static junit.framework.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import se.kth.sda5.serena.dto.User;
import se.kth.sda5.serena.hibernate.util.HibernateQuery;
import se.kth.sda5.serena.hibernate.util.Validation;

import java.util.Date;

public class TestRunner {

    @Test
    public void testUser(){

        assertEquals(!HibernateQuery.getAllData(User.class).isEmpty(),true);
    }

    @Test
    public void testEmailNotValid(){
        String wrongEmail = "test.com";
        Validation valid = new Validation();
        assertEquals(valid.validateEmail(wrongEmail), false);
    }

    @Test
    public void testEmailValid(){
        String rightEmail = "mea@gmail.com";
        assertEquals(new Validation().validateEmail(rightEmail), true);
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setEmail("me@gmail.com");
        user.setFirstName("me");
        user.setLastName("meme");
        user.setPassword("123");
        user.setCreated(new Date());
        user.setRole("1");
        HibernateQuery.addObject(User.class, user);
        assertEquals(HibernateQuery.validateLogin(user.getEmail(), user.getPassword()), true);
    }

    @Test
    public void testDeleteUser(){
        HibernateQuery.deleteUser("me@gmail.com", "123");
        assertEquals(HibernateQuery.validateLogin("me@gmail.com", "123"), false);
    }
}
