package com.example.exp5.domain;

/**
 * 实体类
 */
import java.util.Date;


public class Milepost {
    private int id; // 里程碑编号
    private String name; // 里程碑名称
    private Date launchtime; // 发射时间
    private String depict; // 描述
    private int state; // 状态

    public Milepost() {
    }

    public Milepost(int id, String name, Date launchtime, String depict, int state) {
        this.id = id;
        this.name = name;
        this.launchtime = launchtime;
        this.depict = depict;
        this.state = state;
    }

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

    public Date getLaunchtime() {
        return launchtime;
    }

    public void setLaunchtime(Date launchtime) {
        this.launchtime = launchtime;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Milepost{" + "id=" + id + ", name='" + name + '\'' + ", launchtime=" + launchtime + ", depict='"
                + depict + '\'' + ", state=" + state + '}';
    }
}
