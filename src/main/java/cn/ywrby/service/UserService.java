package cn.ywrby.service;

import cn.ywrby.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用户服务层
public interface UserService {
    /**
     * 获取DAO层返回的用户集合
     * @return 所有用户构成的集合
     */
    public List<User> userList(Integer page,Integer pageSize);
    public List<User> userList();

    /**
     * 保存用户
     * @param user 保存的用户
     */
    public void save(User user, Long[] roleIds);

    /**
     * 保存重新编辑的用户，其ID已经分配，不需要数据库重新获取
     * @param user 用户对象
     * @param userId 用户ID
     * @param roleIds 用户的角色列表
     */
    public void save(User user, Long userId, Long[] roleIds);

    /**
     * 利用用户ID删除指定用户
     * @param userId 用户ID
     */
    public void delUser(Long userId);

    /**
     * 利用用户名与密码匹配用户是否存在，若存在返回该用户，否则返回null
     * @param username 用户名
     * @param password 密码
     * @return 存在返回用户对象不存在返回null
     */
    public User login( String username,String password);

    /**
     * 通过用户ID获取用户所有信息（包括基本信息与角色信息）
     * @param userId 用户ID
     * @return 返回用户对象
     */
    public User getUserById(Long userId);

    /**
     * 利用模糊用户名模糊查询用户
     * @param username 模糊用户名
     * @return 查询到的匹配的用户列表
     */
    public List<User> findUserByUsername(String username);
}
