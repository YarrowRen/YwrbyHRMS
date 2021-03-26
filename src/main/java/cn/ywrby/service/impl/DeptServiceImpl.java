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
            //向部门中存储下属职务对象
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
            //部门ID不为空表示是重新编辑该部门信息，调用更新方法
            deptMapper.update(dept);
        }else{
            //部门信息为空表示该部门是新增部门，调用插入方法
            deptMapper.insert(dept);
        }
    }
}
