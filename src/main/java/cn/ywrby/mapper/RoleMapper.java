package cn.ywrby.mapper;

import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//角色数据访问层
public interface RoleMapper {
    /**
     * 获取所有角色数据
     * @return 封装为集合的所有用户数据
     */
    public List<Role> findAll() ;

    /**
     * 更新数据库中的角色数据
     * @param role 要插入或更新的角色对象
     */
    public void update(Role role);

    /**
     * 向数据库插入新角色
     * @param role 角色对象
     */
    public void insert(Role role);

    /**
     * 通过用户ID获取用户当前角色
     * @param id　用户的ID
     * @return 该用户的角色集合
     */
    public List<Role> findRolesByUserId(Long id);

    /**
     * 删除所有指定角色ID的数据条目
     * @param roleId 角色ID
     */
    public void delUserRole(Long roleId);

    /**
     * 删除指定角色ID的角色
     * @param roleId 角色ID
     */
    public void delRole(Long roleId);

    /**
     * 通过角色ID获取角色对象
     * @param roleId 角色ID
     * @return 返回角色对象
     */
    public Role findRoleById(Long roleId);

    /**
     * 利用部门ID获取部门对象
     * @param id 部门ID
     * @return 部门对象
     */
    public Department findDeptByRoleId(Long id);

    /**
     * 向sys_role_dept表中插入职务与部门的对应关系
     * @param roleId 职务ID
     * @param deptId 部门ID
     */
    public void insertRoleDept(@Param("roleId") Long roleId,@Param("deptId") Long deptId);

    /**
     * 删除职务与部门的对应关系
     * @param id 职务ID
     */
    public void delRoleDept(Long id);

}
