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

//项目控制层
@RequestMapping("/proj")
@Controller("projController")
public class ProjController {

    @Autowired
    private ProjService projService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    /**
     * 展示全部项目列表
     * @return
     */
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

    /**
     * 处理跳转到保存页面的信息与UI
     * @return
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();

        //获取所有用户对象列表与部门对象列表
        List<User> userList = userService.userList();
        List<Department> departmentList = deptService.deptList();

        //向模型中写入数据
        modelAndView.addObject("userList",userList);
        modelAndView.addObject("departmentList",departmentList);

        //跳转到新增项目方法
        modelAndView.setViewName("proj-add");
        return modelAndView;
    }

    //保存新项目
    @RequestMapping("/save")
    public String save(Project project,Long userId,Long deptId){
        //利用用户与部门ID分别获取对象
        User user=new User();
        user.setId(userId);
        Department department=new Department();
        department.setId(deptId);
        //将数据存入项目对象中，用户对象标识负责人，部门对象表示该项目归属的部门
        project.setChargeUser(user);
        project.setDepartment(department);
        //调用Service层方法对该对象进行保存
        projService.save(project);
        //重定向到项目列表页面，以刷新数据
        return "redirect:/proj/list";
    }

}
