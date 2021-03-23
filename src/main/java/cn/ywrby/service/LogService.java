package cn.ywrby.service;

import cn.ywrby.domain.Log;
import cn.ywrby.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LogService {
    /**
     * 获取所有日志对象的集合,并进行分页
     * @return 返回所有日志对象集合
     */
    public List<Log> logList(Integer page,Integer pageSize);

    /**
     * 保存用户登录信息
     * @param user 登录用户
     * @param request 用于获取用户IP地址
     */
    public void save(User user, HttpServletRequest request);
}
