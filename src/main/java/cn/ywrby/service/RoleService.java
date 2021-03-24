package cn.ywrby.service;

import cn.ywrby.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;

//角色服务层
public interface RoleService {
    /**
     * 获取DAO层返回的角色集合
     * @return 所有角色构成的集合
     */
    public List<Role> roleList();

    /**
     * 保存角色
     * @param role 保存的角色
     */
    public void save(Role role);

    /**
     * 删除指定角色ID的信息以及和用户的绑定关系,并从其部门关系中删除
     * @param roleId 角色ID
     */
    public void delRole(Long roleId);

    /**
     * 通过角色ID获取角色对象
     * @param roleId 角色ID
     * @return 角色对象
     */
    public Role getRoleById(Long roleId);
}
