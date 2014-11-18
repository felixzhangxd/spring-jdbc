package biz.ezcom.spring.jdbc.dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.ezcom.spring.jdbc.po.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    // @Test
    public void save() {
        long now = System.currentTimeMillis();
        User user = new User();
        user.setId(1);
        user.setName("felix");
        user.setOnlineTime(new Time(now));
        user.setBirthday(new Date(now));
        user.setAddTime(new Timestamp(now));
        userDao.save(user);
    }

    // @Test
    public void saveAutoIncrement() {
        long now = System.currentTimeMillis();
        User user = new User();
        user.setName("felix");
        user.setIsDeleted(false);
        user.setOnlineTime(new Time(now));
        user.setBirthday(new Date(now));
        user.setAddTime(new Timestamp(now));
        userDao.saveAutoIncrement(user);
        System.out.println(user);
    }

//    @Test
    public void saves() {
        long now = System.currentTimeMillis();
        List<User> users = new LinkedList<User>();
        User user = new User();
        user.setName("felix");
        user.setIsDeleted(false);
        user.setOnlineTime(new Time(now));
        user.setBirthday(new Date(now));
        user.setAddTime(new Timestamp(now));
        users.add(user);
        //
        userDao.save(users);
    }

    // @Test
    public void remove() {
        userDao.remove(1);
    }

    // @Test
    public void modifyById() {
        User user = new User();
        user.setId(1);
        user.setName("felix zhang");
        userDao.modifyById(user);
    }

//    @Test
    public void modifysByIds() {
        int[] ids = { 1, 2, 3, 4 };
        userDao.modifyByIds(true, ids);
    }

    // @Test
    public void find() {
        int id = 1;
        User user = userDao.find(id);
        System.out.println(user);
    }

    // @Test
    public void finds() {
        List<User> users = userDao.find();
        for (User user : users) {
            System.out.println(user);
        }
    }

    // @Test
    public void findMap() {
        Map<String, Object> user = userDao.findMap(1);
        System.out.println(user);
    }

    // @Test
    public void findMaps() {
        List<Map<String, Object>> users = userDao.findMap();
        for (Map<String, Object> user : users) {
            System.out.println(user);
        }
    }

    // @Test
    public void findsByRowCallbackHandler() {
        List<User> users = userDao.findByRowCallbackHandler();
        for (User user : users) {
            System.out.println(user);
        }
    }

    // @Test
    public void findsByResultSetExtractor() {
        List<User> users = userDao.findByResultSetExtractor();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
