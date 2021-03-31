package cn.ywrby.domain;

import java.util.Date;

public class Project {
    private Long id;
    private String projName;
    private User chargeUser;
    private Department department;
    private String clientName;
    private Date startTime;
    private Date deadline;
    private int state;
    private int scale;
    private String projDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public User getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(User chargeUser) {
        this.chargeUser = chargeUser;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadLine(Date deadline) {
        this.deadline = deadline;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getProjDesc() {
        return projDesc;
    }

    public void setProjDesc(String projDesc) {
        this.projDesc = projDesc;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projName='" + projName + '\'' +
                ", chargeUser=" + chargeUser +
                ", department=" + department +
                ", clientName='" + clientName + '\'' +
                ", startTime=" + startTime +
                ", deadLine=" + deadline +
                ", state=" + state +
                ", scale=" + scale +
                ", projDesc='" + projDesc + '\'' +
                '}';
    }
}
