package cn.ywrby.mapper;

import cn.ywrby.domain.Log;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LogMapper {

    /**
     * 从sys_log表获取所有日志信息
     * @return 返回所有日志对象构成的集合
     */
    public List<Log> findAll();

    /**
     * 将ip地址，用户ID和登录日期保存至sys_log表
     * @param ipAddress ip地址
     * @param id 用户ID
     * @param date 登录日期
     */
    public void insert(@Param("ipAddress") String ipAddress, @Param("userId") Long id,@Param("accessTime") Date date);

    public void delUserLog(Long userId);
}
