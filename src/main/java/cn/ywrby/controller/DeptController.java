package cn.ywrby.controller;


import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;
import cn.ywrby.mapper.DeptMapper;
import cn.ywrby.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/dept")
@Controller("deptController")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public ModelAndView deptList(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用service层方法获得部门列表
        List<Department> list=deptService.deptList();
        //向模型中写入数据
        modelAndView.addObject("deptList",list);
        //指定跳转视图，跳转到用户展示页面
        modelAndView.setViewName("dept-list");
        //返回模型与视图
        return modelAndView;
    }
}
