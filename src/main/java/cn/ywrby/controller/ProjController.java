package cn.ywrby.controller;

import cn.ywrby.domain.Project;
import cn.ywrby.domain.User;
import cn.ywrby.service.ProjService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/proj")
@Controller("projController")
public class ProjController {

    @Autowired
    private ProjService projService;

    @RequestMapping("/list")
    public ModelAndView list(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用service层方法获得项目列表
        List<Project> projectList=projService.list();
        //获取分页信息对象
        PageInfo<Project> pageInfo=new PageInfo<Project>(projectList);
        //向模型中写入日志信息以及数据
        modelAndView.addObject("projectList",projectList);
        modelAndView.addObject("pageInfo",pageInfo);
        //指定跳转视图，跳转到用户展示页面
        modelAndView.setViewName("proj-list");
        //返回模型与视图
        return modelAndView;
    }
}
