package cn.ywrby.controller;


import cn.ywrby.domain.Department;
import cn.ywrby.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/editUI/{deptId}")
    public ModelAndView editUI(@PathVariable(value = "deptId",required = true)Long deptId){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //根据角色ID获取角色对象
        Department dept = deptService.findDeptById(deptId);
        //向模型中写入数据
        modelAndView.addObject("dept",dept);
        //指定跳转视图，跳转到新增用户界面
        modelAndView.setViewName("dept-edit");
        return modelAndView;
    }

    @RequestMapping("/saveUI")
    public String saveUI(){
        return "redirect:/pages/dept-add.jsp";
    }

    @RequestMapping("/save")
    public String save(Department dept){
        deptService.save(dept);
        return "redirect:/dept/list";
    }
}
