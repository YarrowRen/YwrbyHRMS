package cn.ywrby.mapper;

import cn.ywrby.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用户数据访问层
public interface UserMapper {
    /**
     * 获取所有用户的数据
     * @return 所有用户数据组成的集合
     */
    public List<User> findAll() ;

    /**
     * 新用户插入数据库并返回新生成主键
     * @param user 用户对象
     * @return 返回数据库自动生成的主键
     */
    public void insert(User user);

    /**
     * 更新数据库中的用户数据
     * @param user 要插入或更新的用户数据
     */
    public void update(User user);

    /**
     * 利用用户ID与其角色ID将信息存入sys_user_role表中
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    public void updateUserRole(@Param("userId") Long userId,@Param("roleId") Long roleId);

    /**
     * 利用用户ID删除用户角色的中间表（由于用户角色表作为中间表，与两表关联，
     * 所以要删除用户信息必须先删中间表）
     * @param userId 用户ID
     */
    public void delUserRole(Long userId);

    /**
     * 利用用户ID 删除用户信息
     * @param userId 用户ID
     */
    public void delUser(Long userId);

    /**
     * 通过用户名与密码查询数据库
     * @param username 用户名
     * @param password 密码
     * @return 存在返回用户对象，不存在返回null
     */
    public User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    /**
     * 利用用户ID获取用户基本信息
     * @param userId 用户ID
     * @return 获取到的用户对象
     */
    public User findUserById(Long userId);

    /**
     * 利用模糊用户名查询可能的用户列表并返回
     * @param username 模糊用户名
     * @return 与模糊用户名匹配的用户列表
     */
    public List<User> findByUsername(String username);
}
