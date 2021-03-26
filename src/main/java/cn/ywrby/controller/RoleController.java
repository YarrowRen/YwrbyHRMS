package cn.ywrby.controller;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;
import cn.ywrby.domain.User;
import cn.ywrby.service.DeptService;
import cn.ywrby.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色控制层，负责角色管理页面的操作
 */
@RequestMapping("/role")
@Controller("roleController")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    /**
     * 获取角色列表
     * @return 返回角色列表和其视图
     */
    @RequestMapping("/list")
    public ModelAndView roleList(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //调用service层方法获得用户列表
        List<Role> list=roleService.roleList();
        //向模型中写入数据
        modelAndView.addObject("roleList",list);
        //指定跳转视图，跳转到用户展示页面
        modelAndView.setViewName("role-list");
        //返回模型与视图
        return modelAndView;
    }

    /**
     * 保存或编辑角色操作
     * @param role 从表单中获取到的角色对象
     * @return 返回重定向到获取角色列表部分（不可以直接返回jsp页面，那样数据没有刷新，仍是旧表单）
     */
    @RequestMapping("/save")
    public String saveRole(Role role,Long deptId){
        //通过ID查询对应部门
        Department department=deptService.findDeptById(deptId);
        //为职业传入部门信息
        role.setDepartment(department);
        //保存职业信息
        roleService.save(role);
        return "redirect:/role/list";
    }

    /**
     * 删除指定角色并将所有担任此角色的用户与该角色解绑，以及从其部门关系中删除
     * @param roleId 角色ID
     * @return 重定向到角色列表以实现数据刷新
     */
    @RequestMapping("/delete/{roleId}")
    public String delRole(@PathVariable(value = "roleId",required = true)Long roleId){
        roleService.delRole(roleId);
        return "redirect:/role/list";
    }


    /**
     * 跳转到新增职务的页面，并传入当前部门信息
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //获取当前部门列表
        List<Department> deptList = deptService.deptList();
        //存储部门列表
        modelAndView.addObject("deptList",deptList);
        //指定跳转到新增页面
        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    /**
     * 跳转到编辑职务的页面
      * @param roleId 要编辑的职务ID
     */
    @RequestMapping("/editUI/{roleId}")
    public ModelAndView editRole(@PathVariable(value = "roleId",required = true)Long roleId){
        //创建模型视图对象
        ModelAndView modelAndView=new ModelAndView();
        //根据角色ID获取角色对象
        Role role=roleService.getRoleById(roleId);
        //获取部门信息
        List<Department> deptList=deptService.deptList();
        //向模型中写入数据
        modelAndView.addObject("role",role);
        modelAndView.addObject("deptList",deptList);
        //指定跳转视图，跳转到新增用户界面
        modelAndView.setViewName("role-edit");
        return modelAndView;
    }


}
