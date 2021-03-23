package cn.ywrby.controller;


import cn.ywrby.domain.Log;
import cn.ywrby.service.LogService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 访问日志管理层，负责访问日志页面的管理
 */
@RequestMapping("/log")
@Controller("logController")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 获取访问日志
     * @return 返回ModelAndView
     */
    @RequestMapping("/list")
    public ModelAndView logList(@RequestParam(required=true, defaultValue = "1")Integer page, @RequestParam(required=false,defaultValue="10")Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        //利用service层获取全部日志对象集合,并传入分页所需数据
        List<Log> logList=logService.logList(page,pageSize);

        //获取分页信息
        PageInfo<Log> pageInfo=new PageInfo<Log>(logList);
        //将分页数据写入模型
        modelAndView.addObject("pageInfo",pageInfo);

        //写入日志数据
        modelAndView.addObject("logList",logList);
        //写入跳转视图
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
