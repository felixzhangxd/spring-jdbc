package biz.ezcom.spring.jdbc.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.ezcom.spring.jdbc.dao.api.IUserDao;
import biz.ezcom.spring.jdbc.po.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class UserDaoTest {
    @Resource
    private IUserDao userDao;

//    @Test
    public void testSave() {
        Date birthday = new Date(System.currentTimeMillis());
        User user = new User(2, "felix", "pass", birthday);
        int affect = userDao.save(user);
        Assert.assertEquals(1, affect);
    }
    
//    @Test
    public void testSaveAutoIncrement() {
        Date birthday = new Date(System.currentTimeMillis());
        User user = new User(null, "felix", "pass", birthday);
        int affect = userDao.save(user);
        Assert.assertEquals(1, affect);
    }
    
    @Test
    public void testSaveList() {
        List<User> users = new ArrayList<User>(3);
        Date birthday = new Date(System.currentTimeMillis());
        users.add(new User(11, "felix", "pass", birthday));
        users.add(new User(12, "alice", "pass", birthday));
        users.add(new User(13, "justin", "pass", birthday));
        int[] affect = userDao.save(users);
        Assert.assertEquals(users.size(), affect.length);
    }

//    @Test
    public void testRemove() {
        Integer id = 1;
        int affect = userDao.remove(id);
        Assert.assertEquals(1, affect);
    }

//    @Test
    public void testModify() {
        Date birthday = new Date(System.currentTimeMillis());
        User user = new User(1, "felix", "pass", birthday);
        int affect = userDao.modify(user);
        Assert.assertEquals(1, affect);
    }

//    @Test
    public void testModifys() {
        Date birthday = new Date(System.currentTimeMillis());
        List<User> users = new ArrayList<User>();
        users.add(new User(1, "felix", "pass", birthday));
        users.add(new User(2, "justin", "word", birthday));
        int[] affect = userDao.modifys(users);
        for(int i : affect) {
            Assert.assertEquals(1, i);
        }
    }

//    @Test
    public void testFindCount() {
        int count = userDao.findCount();
        System.out.println("findCount: " + count);
    }

//    @Test
    public void testFind() {
        Integer id = 1;
        User user = userDao.find(id);
        System.out.println("find:" + user);
    }
}
