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
        List<Project> projectList = projMapper.findAll();
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
        //System.out.println(project);
        projMapper.insert(project);
    }
}
