package cn.ywrby.mapper;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;

import java.util.List;

public interface DeptMapper {
    /**
     * 获取全部部门对象列表
     * @return 全部部门对象的列表
     */
    public List<Department> findAll();

    /**
     * 利用部门ID获取部门对象
     * @param deptId 部门ID
     * @return 部门对象
     */
    public Department findDeptById(Long deptId);

    /**
     * 获取所有部门ID为指定ID的职务（该部门的所有下属职务）
     * @param deptId 部门ID
     * @return 职务列表
     */
    public List<Role> findRolesByDeptId(Long deptId);

    /**
     * 向数据库中插入指定部门信息
     * @param dept 部门对象
     */
    public void insert(Department dept);

    /**
     * 更新数据库中对应部门的信息
     * @param dept 部门对象
     */
    public void update(Department dept);
}
