package cn.ywrby.controller;


import cn.ywrby.domain.Department;
import cn.ywrby.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 部门信息控制层，负责部门信息的编辑删除查看等功能
 */
@RequestMapping("/dept")
@Controller("deptController")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取全部的部门信息并显示
     */
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

    /**
     * 部门信息编辑的UI展示方法，负责传入部门的原信息，方便编辑
     * @param deptId 部门的ID（用于获取部门对象）
     */
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

    /**
     * 重定向到新建部门信息页面
     */
    @RequestMapping("/saveUI")
    public String saveUI(){
        return "redirect:/pages/dept-add.jsp";
    }

    /**
     * 保存部门信息
     * @param dept 部门对象
     * @return 重定向到部门列表以刷新数据库，保证新增对象的显示
     */
    @RequestMapping("/save")
    public String save(Department dept){
        deptService.save(dept);
        return "redirect:/dept/list";
    }
}
