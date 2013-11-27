package biz.ezcom.spring.jdbc.po;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class User implements RowMapper<User> {
    private Integer id;
    private String  username;
    private String  password;
    private Date    birthday;

    public User() {}

    public User(Integer id, String username, String password, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return this.getId() + " : " + this.getUsername() + " : " + this.getPassword() + " : " + this.getBirthday();
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        ResultSetMetaData metaDate = rs.getMetaData();
        for (int i = 1; i <= metaDate.getColumnCount(); i++) {
            final String columnName = metaDate.getColumnName(i);
            if ("id".equals(columnName)) {
                user.setId(rs.getInt(i));
            } else if ("username".equals(columnName)) {
                user.setUsername(rs.getString(i));
            } else if ("password".equals(columnName)) {
                user.setPassword(rs.getString(i));
            } else if ("birthday".equals(columnName)) {
                user.setBirthday(rs.getDate(i));
            }
        }
        return user;
    }
}
