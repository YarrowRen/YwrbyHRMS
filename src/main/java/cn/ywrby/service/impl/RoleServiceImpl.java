package cn.ywrby.service.impl;


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
        return list;
    }

    @Override
    public void save(Role role) {
        //插入或更新角色数据
        if(role.getId()!=null){
            //角色ID不为空表示是编辑旧角色信息
            roleMapper.update(role);
        }else {
            //ID为空，创建新的角色并存入数据库
            roleMapper.insert(role);
        }
    }

    @Override
    public void delRole(Long roleId) {
        //删除使用该角色的用户的使用信息（删除sys_user_role中的内容）
        roleMapper.delUserRole(roleId);
        //删除角色信息（删除sys_role中的内容）
        roleMapper.delRole(roleId);
    }

    @Override
    public Role getRoleById(Long roleId) {
        Role role=roleMapper.findRoleById(roleId);
        return role;
    }
}
