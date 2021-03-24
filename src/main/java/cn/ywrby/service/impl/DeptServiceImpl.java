package cn.ywrby.service.impl;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;
import cn.ywrby.mapper.DeptMapper;
import cn.ywrby.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Department> deptList() {
        List<Department> departmentList=deptMapper.findAll();
        //获取所有部门的下属职务
        for(Department dept:departmentList){
            List<Role> roleList=deptMapper.findRolesByDeptId(dept.getId());
            dept.setRoleList(roleList);
        }
        return departmentList;
    }

    @Override
    public Department findDeptById(Long deptId) {
        Department department=deptMapper.findDeptById(deptId);
        return department;
    }

    @Override
    public void save(Department dept) {
        if(dept.getId()!=null){
            deptMapper.update(dept);
        }else{
            deptMapper.insert(dept);
        }
    }
}
