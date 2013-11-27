package biz.ezcom.spring.jdbc.dao.api;

import biz.ezcom.spring.jdbc.po.User;

public interface IUserDao {
    int save(User user);

    int remove(Integer id);

    int modify(User user);

    int findCount();

    User find(Integer id);
}
