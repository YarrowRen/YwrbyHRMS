package cn.ywrby.mapper;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;

import java.util.List;

public interface DeptMapper {
    public List<Department> findAll();

    public Department findDeptById(Long deptId);

    public List<Role> findRolesByDeptId(Long deptId);

    public void insert(Department dept);

    public void update(Department dept);
}
