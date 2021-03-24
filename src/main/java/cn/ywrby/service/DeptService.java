package cn.ywrby.service;

import cn.ywrby.domain.Department;

import java.util.List;

public interface DeptService {
    public List<Department> deptList();

    public Department findDeptById(Long deptId);

    public void save(Department dept);
}
