package cn.ywrby.controller;


import cn.ywrby.domain.Project;
import cn.ywrby.domain.User;
import cn.ywrby.service.ProjService;
import cn.ywrby.service.RoleService;
import cn.ywrby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@RequestMapping("/main")
@Controller("mainController")
public class MainController {

    @Autowired
    private ProjService projService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用于处理首页数据展示
     * @param request 获取request对象以判断当前登录用户
     */
    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        //获取用户集合与项目集合
        List<Project> projectList = projService.list();
        List<User> userList = userService.userList();
        //取出最后8个用户集合（不足则全部取出），表示最新的8个用户
        Collections.reverse(userList); //集合倒序处理
        if(userList.size()>8) { //长度判断
            userList = userList.subList(0, 8);
        }
        //取出最后7个项目集合（不足则全部取出），表示最新的七个项目
        Collections.reverse(projectList); //集合倒序处理
        if(projectList.size()>7) {  //长度判断
            projectList = projectList.subList(0, 7);
        }
        //获取当前登录的用户信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); 
        //获取用户角色信息
        user=userService.getUserById(user.getId());
        //向模型中传入三个需要使用的数据
        modelAndView.addObject("nowUser",user); //当前用户
        modelAndView.addObject("projList",projectList); //最新的七个项目集合
        modelAndView.addObject("userList",userList);  //最新的八个用户集合
        modelAndView.setViewName("main"); //指定项目视图名称
        return modelAndView;
    }
}
