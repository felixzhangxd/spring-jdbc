package biz.ezcom.spring.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import biz.ezcom.spring.jdbc.dao.api.IUserDao;
import biz.ezcom.spring.jdbc.po.User;

@Repository
public class UserDao implements IUserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(final User user) {
        final String sql = "INSERT INTO User (username, password, birthday) VALUES(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affect = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setDate(3, user.getBirthday());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return affect;
    }

    @Override
    public int remove(Integer id) {
        String sql = "DELETE FROM User WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
    
    @Override
    public int modify(User user) {
        String sql = "UPDATE User SET username=?,password=?,birthday=? WHERE id=?";
        return jdbcTemplate.update(sql,user.getUsername(), user.getPassword(),user.getBirthday(),user.getId());
    }
    
    @Override
    public int findCount() {
        String sql = "SELECT count(*) FROM User";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    @Override
    public User find(Integer id) {
        String sql = "SELECT * FROM User WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new User(), id);
    }

    
}
