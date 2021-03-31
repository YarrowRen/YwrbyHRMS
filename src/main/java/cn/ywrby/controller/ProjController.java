package cn.ywrby.controller;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Project;
import cn.ywrby.domain.User;
import cn.ywrby.service.DeptService;
import cn.ywrby.service.ProjService;
import cn.ywrby.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

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

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        List<User> userList = userService.userList();
        List<Department> departmentList = deptService.deptList();

        modelAndView.addObject("userList",userList);
        modelAndView.addObject("departmentList",departmentList);

        modelAndView.setViewName("proj-add");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Project project,Long userId,Long deptId){
        User user=new User();
        user.setId(userId);
        Department department=new Department();
        department.setId(deptId);
        project.setChargeUser(user);
        project.setDepartment(department);
        System.out.println(project);
        projService.save(project);
        return "redirect:/proj/list";
    }

}
