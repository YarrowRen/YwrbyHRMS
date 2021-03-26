package cn.ywrby.service;

import cn.ywrby.domain.Department;

import java.util.List;

public interface DeptService {
    /**
     * 获取部门列表
     * @return 全部部门列表
     */
    public List<Department> deptList();

    /***
     * 利用部门ID获取部门对象
     * @param deptId 部门ID
     * @return 部门对象
     */
    public Department findDeptById(Long deptId);

    /**
     * 保存对应的部门对象
     * @param dept 部门对象
     */
    public void save(Department dept);
}
