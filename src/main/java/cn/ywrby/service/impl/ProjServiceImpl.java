package cn.ywrby.service.impl;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Project;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.DeptMapper;
import cn.ywrby.mapper.ProjMapper;
import cn.ywrby.mapper.UserMapper;
import cn.ywrby.service.ProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projService")
public class ProjServiceImpl implements ProjService {

    @Autowired
    private ProjMapper projMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Project> list() {
        //获取所有对象的集合，该集合中的负责人与部门属性只有ID，其他属性并没有实例化
        List<Project> projectList = projMapper.findAll();
        //通关ID分别查询负责人对象与部门对象并存入项目对象中
        for(Project p:projectList){
            User user = userMapper.findUserById(p.getChargeUser().getId());
            Department department=deptMapper.findDeptById(p.getDepartment().getId());
            p.setChargeUser(user);
            p.setDepartment(department);
        }
        return projectList;
    }

    @Override
    public void save(Project project) {
        //调用持久层方法保存项目对象到数据库中
        projMapper.insert(project);
    }
}
