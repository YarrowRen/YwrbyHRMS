package cn.ywrby.domain;

import java.util.Date;

/**
 * 访问日志对象
 */
public class Log {
    private Long id;  //访问ID，只与访问顺序有关
    private Date accessTime; //访问时间
    private Long userId;  //访问的用户ID
    private String username;  //访问用户的用户名
    private String ipAddress;  //访问的IP地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", accessTime=" + accessTime +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
