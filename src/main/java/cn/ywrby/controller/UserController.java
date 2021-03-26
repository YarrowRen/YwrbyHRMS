package cn.ywrby.controller;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;
import cn.ywrby.domain.User;
import cn.ywrby.service.DeptService;
import cn.ywrby.service.LogService;
import cn.ywrby.service.RoleService;
import cn.ywrby.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器，负责控制用户管理界面
 */
@RequestMapping("/user")
@Controller("userController")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private LogService logService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 获取用户列表与其视图
     * @param page 分页开始页
     * @param pageSize 分页显示数量
     * @return 返回用户列表及其视图
     */
    @RequestMapping("/list")
    public ModelAndView userList(@RequestParam(required=true, defaultValue = "1")Integer page, @RequestParam(required=false,defaultValue="10")Integer pageSize){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用service层方法获得用户列表
        List<User> list=userService.userList(page,pageSize);

        //获取分页信息对象
        PageInfo<User> info=new PageInfo<User>(list);
        //向模型中写入日志信息
        modelAndView.addObject("pageInfo",info);

        //向模型中写入数据
        modelAndView.addObject("userList",list);
        //指定跳转视图，跳转到用户展示页面
        modelAndView.setViewName("user-list");
        //返回模型与视图
        return modelAndView;
    }

    /**
     * 获取当前数据库中保存的角色列表并跳转到新增用户的界面
     * @return 当前角色列表和新增用户界面
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用role的service层方法获得数据库中的角色列表
        List<Role> list=roleService.roleList();
        //获取所有部门信息
        List<Department> deptList = deptService.deptList();
        //向模型中写入数据
        modelAndView.addObject("roleList",list);
        modelAndView.addObject("deptList",deptList);
        //指定跳转视图，跳转到新增用户界面
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    /**
     * 保存新增对象
     * @param user 通过表单获取的用户对象
     * @param roleIds 通过表单获取的角色ID信息
     * @return 重定向到获取用户列表信息，以保证刷新页面显示新用户数据
     */
    @RequestMapping("/save")
    public String saveUser(User user,Long[] roleIds){
        //调用业务层方法，存储用户的所有信息
        userService.save(user,roleIds);
        //重定向到控制层加载用户列表的方法，以保证用户列表得到刷新
        return "redirect:/user/list";
    }


    /**
     * 利用restful方式获取用户ID，根据用户ID删除用户操作，同时要删除用户日志信息
     * @param userId 要删除的用户ID
     * @return 重定向到用户列表的获取以刷新页面
     */
    @RequestMapping("/delete/{userId}")
    public String delUser(@PathVariable(value = "userId",required = true) Long userId){
        userService.delUser(userId);
        return "redirect:/user/list";
    }

    /**
     * 实现用户登录操作,并在每次用户登录时，将登录状态信息记录到sys_log表中
     * @param username 用户名
     * @param password 密码
     * @param session HttpSession会话
     * @return 重定向到指定页面（密码正确到主页，不正确返回登陆页面）
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        //调用服务层方法检查数据库是否存在该用户
        User user=userService.login(username,password);
        //不为空表示用户密码正确，向会话中写入对象，并记录登录信息，重定向到主页
        if(user!=null){
            //保存登录信息
            logService.save(user,request);
            //写入数据
            session.setAttribute("user",user);
            return "redirect:/index.jsp";
        }
        //否则表示用户名/密码错误，重定向到登录页面重新登录
        return "redirect:/login.jsp";
    }

    /**
     * 进入重新编辑用户页面
     * @param userId 该用户ID
     * @return 返回重新编辑的原用户信息和编辑视图
     */
    @RequestMapping("/editUI/{userId}")
    public ModelAndView editUserUI(@PathVariable(value = "userId",required = true) Long userId){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用role的service层方法获得数据库中的角色列表
        List<Role> list=roleService.roleList();
        //根据用户ID获取用户对象
        User user=userService.getUserById(userId);
        //获取所有部门信息
        List<Department> deptList = deptService.deptList();
        //向模型中写入数据
        //  1. 写入角色列表
        modelAndView.addObject("roleList",list);
        //  2. 写入用户数据
        modelAndView.addObject("user",user);
        //  3. 写入部门信息
        modelAndView.addObject("deptList",deptList);
        //指定跳转视图，跳转到新增用户界面
        modelAndView.setViewName("user-edit");
        return modelAndView;
    }


    /**
     * 模糊搜索含指定用户名片段的用户对象
     * @param username 模糊用户名
     * @return 返回视图以及模糊查询的用户列表
     */
    @RequestMapping("/find/{searchName}")
    public ModelAndView findUserByUsername(@PathVariable(value = "searchName")String username){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用service层方法获得用户列表
        List<User> list=userService.findUserByUsername(username);
        //向模型中写入数据
        modelAndView.addObject("userList",list);
        //指定跳转视图，跳转到用户展示页面
        modelAndView.setViewName("user-search");
        //返回模型与视图
        return modelAndView;
    }

}
