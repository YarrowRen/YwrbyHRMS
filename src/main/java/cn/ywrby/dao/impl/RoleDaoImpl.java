package cn.ywrby.dao.impl;

import cn.ywrby.dao.RoleDao;
import cn.ywrby.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Role> findAll() {
        //执行数据库查询操作并将返回数据封装到集合中
        List<Role> list=jdbcTemplate.query("select * from sys_role",new BeanPropertyRowMapper<Role>(Role.class));
        return list;
    }

    public void update(Role role){
        //执行数据库更新操作，更新旧角色信息
        jdbcTemplate.update("update sys_role set roleName=?,roleDesc=? where id=?",
                role.getRoleName(),role.getRoleDesc(),role.getId());
    }

    @Override
    public void insert(Role role) {
        //执行数据库插入操作，向数据库中插入新角色数据
        jdbcTemplate.update("insert into sys_role values (?,?,?)",
                null,role.getRoleName(),role.getRoleDesc());
    }

    @Override
    public List<Role> findRolesByUserId(Long id) {
        //这里利用到了两表的联查，通过用户ID获取用户所有的角色ID，再通过角色ID获取所有的角色信息并封装到集合
        List<Role> roleList = jdbcTemplate.query("select * from sys_user_role ur,sys_role r where ur.roleId=r.id and ur.userId=?", new BeanPropertyRowMapper<Role>(Role.class), id);
        return roleList;
    }

    @Override
    public void delUserRole(Long roleId) {
        jdbcTemplate.update("delete from sys_user_role where roleId = ?",roleId);
    }

    @Override
    public void delRole(Long roleId) {
        jdbcTemplate.update("delete from sys_role where id = ?",roleId);
    }

    @Override
    public Role findRoleById(Long roleId) {
        Role role=jdbcTemplate.queryForObject("select * from sys_role where id = ?",new BeanPropertyRowMapper<Role>(Role.class),roleId);
        return role;
    }


}
