package cn.ywrby.service.impl;

import cn.ywrby.domain.Log;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.LogMapper;
import cn.ywrby.mapper.UserMapper;
import cn.ywrby.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Log> logList(Integer page,Integer pageSize) {
        //进行分页
        PageHelper pageHelper=new PageHelper();
        //开始分页，指定分页参数
        PageMethod.startPage(page,pageSize);


        //利用持久层获取日志对象集合
        List<Log> logList = logMapper.findAll();
        //再根据表中的用户ID获取用户名
        for(Log log:logList){
            //通过日志对象获取用户ID，再通过用户ID查找对应用户
            User user = userMapper.findUserById(log.getUserId());
            //将用户传入日志对象（便于获取用户名数据）
            log.setUsername(user.getUsername());
        }
        return logList;
    }

    @Override
    public void save(User user, HttpServletRequest request) {
        //获取用户IP地址
        String ipAddress=request.getRemoteAddr();
        //获取用户ID
        Long id=user.getId();
        //获取用户登录时间
        Date date=new Date();
        //利用持久层写入用户登录信息
        logMapper.insert(ipAddress,id,date);
    }
}
