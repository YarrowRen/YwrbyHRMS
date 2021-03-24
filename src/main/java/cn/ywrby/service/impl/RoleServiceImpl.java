package cn.ywrby.service.impl;


import cn.ywrby.domain.Department;
import cn.ywrby.domain.Role;
import cn.ywrby.mapper.RoleMapper;
import cn.ywrby.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

    //@Autowired
    //private RoleDao roleDao;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> roleList() {
        //获取所有角色的集合
        List<Role> list=roleMapper.findAll();
        //通过角色ID获取角色所属部门
        for(Role role:list){
            Department department=roleMapper.findDeptByRoleId(role.getId());
            role.setDepartment(department);
        }
        return list;
    }

    @Override
    public void save(Role role) {
        //插入或更新角色数据
        if(role.getId()!=null){
            //角色ID不为空表示是编辑旧角色信息
            //先删除原先的部门角色关系
            roleMapper.delRoleDept(role.getId());
            //更新新的角色数据
            roleMapper.update(role);
            //更新新的部门角色关系
            roleMapper.insertRoleDept(role.getId(),role.getDepartment().getId());
        }else {
            //ID为空，创建新的角色并存入数据库
            roleMapper.insert(role);
            //获取角色ID
            Long roleId=role.getId();
            //根据角色ID和部门ID，保存角色与部门关系到sys_role_dept表
            roleMapper.insertRoleDept(roleId,role.getDepartment().getId());
        }
    }

    @Override
    public void delRole(Long roleId) {
        //删除使用,该角色的用户的使用信息（删除sys_user_role中的内容）
        roleMapper.delUserRole(roleId);
        //删除对应的部门角色信息(删除sys_role_dept中的内容)
        roleMapper.delRoleDept(roleId);
        //删除角色信息（删除sys_role中的内容）
        roleMapper.delRole(roleId);
    }

    @Override
    public Role getRoleById(Long roleId) {
        Role role=roleMapper.findRoleById(roleId);
        return role;
    }
}
