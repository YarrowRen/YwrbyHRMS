package cn.ywrby.dao.impl;

import cn.ywrby.dao.UserDao;
import cn.ywrby.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        //执行数据库查询操作，获取所有用户信息并封装到集合中
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public Long insert(User user){
        //没有主键ID表明是新用户加入
        //由于后续需要将用户的角色信息插入中间表sys_user_role,
        //所以这里在插入用户基本信息的同时要获取用户插入数据库时自动生成的主键ID并返回

        //创建PreparedSatementCreator
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始的JDBC完成PreparedStatement的创建
                //PreparedStatement.RETURN_GENERATED_KEYS表示返回生成主键
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                //设置属性
                preparedStatement.setObject(1, null);
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getPhoneNum());
                return preparedStatement;
            }
        };

        //创建KeyHolder
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //执行数据库插入操作并利用keyHolder获取主键
        jdbcTemplate.update(creator, keyHolder);

        //获取主键
        Long userId = keyHolder.getKey().longValue();

        return userId;
    }

    @Override
    public void update(User user) {
        //执行数据库更新操作，利用主键确定更新的用户
        jdbcTemplate.update("update sys_user set username=?,email=?,password=?,phoneNum=? where id=?",
                user.getUsername(),user.getEmail(),user.getPassword(), user.getPhoneNum(), user.getId());
    }

    @Override
    public void updateUserRole(Long userId,Long roleId) { jdbcTemplate.update("insert into sys_user_role values (?,?)",userId,roleId); }

    @Override
    public void delUserRole(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId = ?",userId);
    }

    @Override
    public void delUser(Long userId) {
        jdbcTemplate.update("delete from sys_user where id = ?",userId);
    }

    //此处queryForObject在查询不到数据的情况下会抛出异常EmptyResultDataAccessException
    //建议不再DAO层处理异常，而是将异常抛出，交由service层处理
    @Override
    public User findByUsernameAndPassword(String username, String password) throws EmptyResultDataAccessException {
        //根据用户名与密码匹配数据库中是否有相同数据
        User user= jdbcTemplate.queryForObject("select * from sys_user where username= ? and password = ?",
                new BeanPropertyRowMapper<User>(User.class),username,password);
        //返回user对象
        return user;
    }

    @Override
    public User findUserById(Long userId) {
        User user=jdbcTemplate.queryForObject("select * from sys_user where id = ?",new BeanPropertyRowMapper<User>(User.class),userId);
        return user;
    }

    @Override
    public List<User> findByUsername(String username) {
        //执行数据库查询操作，获取符合条件的用户信息并封装到集合中
        List<User> userList = jdbcTemplate.query("select * from sys_user where username like concat('%',?,'%')",
                new BeanPropertyRowMapper<User>(User.class),username);
        return userList;
    }


}
