package biz.ezcom.spring.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import biz.ezcom.spring.jdbc.po.User;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public int save(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO user SET id = ?");
        sql.append(", name = ?");
        sql.append(", is_deleted = ?");
        sql.append(", online_time = ?");
        sql.append(", birthday = ?");
        sql.append(", add_time = ?");
        sql.append(", latest_time = ?");
        Object[] args = new Object[] { user.getId(), user.getName(), user.getIsDeleted(), user.getOnlineTime(), user.getBirthday(),
                user.getAddTime(), user.getLatestTime() };
        return jdbcTemplate.update(sql.toString(), args);
    }

    public int saveAutoIncrement(final User user) {
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO user SET name = ?");
        sql.append(", is_deleted = ?");
        sql.append(", online_time = ?");
        sql.append(", birthday = ?");
        sql.append(", add_time = ?");
        sql.append(", latest_time = ?");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affect = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setBoolean(2, user.getIsDeleted());
                ps.setTime(3, user.getOnlineTime());
                ps.setDate(4, user.getBirthday());
                ps.setTimestamp(5, user.getAddTime());
                ps.setTimestamp(6, user.getLatestTime());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return affect;
    }

    public int[] save(final List<User> users) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO user SET name = ?");
        sql.append(", is_deleted = ?");
        sql.append(", online_time = ?");
        sql.append(", birthday = ?");
        sql.append(", add_time = ?");
        sql.append(", latest_time = ?");
        return jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = users.get(i);
                ps.setString(1, user.getName());
                ps.setBoolean(2, user.getIsDeleted());
                ps.setTime(3, user.getOnlineTime());
                ps.setDate(4, user.getBirthday());
                ps.setTimestamp(5, user.getAddTime());
                ps.setTimestamp(6, user.getLatestTime());
            }

            public int getBatchSize() {
                return users.size();
            }
        });
    }

    public int remove(Integer id) {
        String sql = "DELETE FROM user WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    public int modifyById(User user) {
        List<Object> args = new LinkedList<Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE user SET id = id");
        if (user.getName() != null) {
            sql.append(", name = ?");
            args.add(user.getName());
        }
        sql.append(", is_deleted = ?");
        args.add(user.getIsDeleted());
        if (user.getOnlineTime() != null) {
            sql.append(", online_time = ?");
            args.add(user.getOnlineTime());
        }
        if (user.getBirthday() != null) {
            sql.append(", birthday = ?");
            args.add(user.getBirthday());
        }
        // sql.append(", add_time = ?");
        // sql.append(", latest_time = ?");
        sql.append(" WHERE id = ?");
        args.add(user.getId());
        return jdbcTemplate.update(sql.toString(), args.toArray());
    }

    public int[] modifyByIds(final boolean isDeleted, final int[] ids) {
        String sql = "UPDATE user SET is_deleted=? WHERE id=?";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setBoolean(1, isDeleted);
                ps.setInt(2, ids[i]);
            }

            public int getBatchSize() {
                return ids.length;
            }
        });
    }

    public User find(Integer id) {
        // 必需返回一条记录,否则出异常
        String sql = "SELECT * FROM user WHERE id=?";
        return jdbcTemplate.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(User.class), id);
    }

    public List<User> find() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
    }

    public Map<String, Object> findMap(Integer id) {
        // 必需返回1条记录,否则出异常
        String sql = "SELECT * FROM user WHERE id=?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public List<Map<String, Object>> findMap() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.queryForList(sql);
    }

    public List<User> findByRowCallbackHandler() {
        String sql = "SELECT * FROM user";
        final List<User> users = new LinkedList<User>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setOnlineTime(rs.getTime("online_time"));
                user.setBirthday(rs.getDate("birthday"));
                user.setAddTime(rs.getTimestamp("add_time"));
                user.setLatestTime(rs.getTimestamp("latest_time"));
                users.add(user);
            }
        });
        return users;
    }

    public List<User> findByResultSetExtractor() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<User> users = new LinkedList<User>();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setOnlineTime(rs.getTime("online_time"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setAddTime(rs.getTimestamp("add_time"));
                    user.setLatestTime(rs.getTimestamp("latest_time"));
                    users.add(user);
                }
                return users;
            }
        });
    }
}
