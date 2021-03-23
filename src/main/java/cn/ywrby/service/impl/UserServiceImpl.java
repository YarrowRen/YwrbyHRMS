package cn.ywrby.service.impl;


import cn.ywrby.domain.Role;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.RoleMapper;
import cn.ywrby.mapper.UserMapper;
import cn.ywrby.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService{

    /*@Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;*/

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> userList(Integer page,Integer pageSize) {
        //获取分页插件对象
        PageHelper pageHelper=new PageHelper();
        //开始分页，指定分页参数
        PageMethod.startPage(page,pageSize);

        //获取所有用户的集合（此时获取的集合，其角色属性为空）
        List<User> userList = userMapper.findAll();
        //遍历集合获取每个用户的角色集合
        for (User user:userList) {
            //获取用户的ID
            Long id=user.getId();
            //利用用户ID获取其所有的角色集合
            List<Role> roleList=roleMapper.findRolesByUserId(id);
            //将获取到的角色集合存入其属性值中
            user.setRoleList(roleList);
        }
        //返回用户集合
        return userList;
    }

    @Override
    public void save(User user,Long[] roleIds) {
        //保存用户基本信息（在sys_user表中进行）并返回数据库中自动生成的主键ID
        Long userId=user.getId();
        //判断是否有主键，以区分是新用户加入还是旧用户重新编辑
        if(user.getId()!=null) {
            //有主键表明是旧用户重新编辑
            //先删除原ID与角色之间的绑定关系（清空该用户在sys_user_role中的角色信息）
            userMapper.delUserRole(userId);
            userMapper.update(user);
        }else {
            //没有主键ID表明是新用户加入,将新用户插入数据库并获得新生成的主键
            userMapper.insert(user);
            userId=user.getId();
        }
        //将用户ID与其每个角色ID列表存入中间表sy_user_role
        for(Long roleId:roleIds) {
            userMapper.updateUserRole(userId, roleId);
        }
    }

    @Override
    public void save(User user, Long userId, Long[] roleIds) {
        //由于是重新编辑，所以不需要重新分配主键,将原主键存入user对象
        user.setId(userId);
        //保存用户基本信息（在sys_user表中进行）
        userMapper.update(user);
        //将用户ID与其角色ID列表存入中间表sy_user_role
        for(Long roleId:roleIds) {
            userMapper.updateUserRole(userId, roleId);
        }
    }

    @Override
    public void delUser(Long userId) {
        //删除sys_user_role表中该用户的角色信息
        userMapper.delUserRole(userId);
        //删除sys_user表中该用户的基本信息
        userMapper.delUser(userId);
    }

    @Override
    public User login(String username, String password) {
        //针对DAO层queryForObject可能出现的空对象异常，在此处进行抓取并处理
        User user=null;
        try {
            user=userMapper.findByUsernameAndPassword(username,password);
        }catch (EmptyResultDataAccessException e){
            //不需要进行处理，返回异常表示数据库没有该对象，直接返回空对象即可
        }
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        //获取用户基本信息
        User user=userMapper.findUserById(userId);
        //获取用户角色信息
        List<Role> roleList=roleMapper.findRolesByUserId(userId);
        //写入用户的角色信息
        user.setRoleList(roleList);
        //返回用户对象
        return user;
    }

    @Override
    public List<User> findUserByUsername(String username) {
        List<User> userList=userMapper.findByUsername(username);
        return userList;
    }
}
