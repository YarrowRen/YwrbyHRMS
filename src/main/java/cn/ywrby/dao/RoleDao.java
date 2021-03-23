package cn.ywrby.dao;

import cn.ywrby.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;

//角色数据访问层
public interface RoleDao {
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

}
