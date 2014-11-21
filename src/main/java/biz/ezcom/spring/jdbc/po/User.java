package biz.ezcom.spring.jdbc.po;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class User {
    private int       id;
    private String    name;
    private boolean   isDeleted;
    private Time      onlineTime;
    private Date      birthday;
    private Timestamp addTime;
    private Timestamp latestTime;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Time onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Timestamp latestTime) {
        this.latestTime = latestTime;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i != 0) {
                s.append(", ");
            }
            s.append(fields[i].getName()).append("=");
            try {
                Object value = fields[i].get(this);
                if(value != null) {
                    s.append(value);
                }
            } catch (Exception e) {}
        }
        return s.toString();
    }
}
