package cn.ywrby.test;

import cn.ywrby.domain.Role;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.RoleMapper;
import cn.ywrby.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {

    private RoleMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession(true);
        //通过getMapper方法传入接口类，获得MyBatis返回的实现类
        mapper = sqlSession.getMapper(RoleMapper.class);

    }

    @Test
    public void test(){
        //执行接口方法
        List<Role> roleList = mapper.findAll();
        //mapper.delete((long) 4);
        //mapper.findAll();
        //mapper.save(user);

        //sqlSession.commit();
        System.out.println(roleList);
    }

    @Test
    public void test2(){
        Role role=new Role();
        role.setRoleDesc("无权限进行任何操作");
        role.setRoleName("工具人");
        mapper.insert(role);
    }

    @Test
    public void test3(){
        Role role=new Role();
        role.setId((long) 10);
        role.setRoleDesc("无权限进行任何操作QAQ");
        role.setRoleName("工具人233");
        mapper.update(role);

    }

    @Test
    public void test4(){
        mapper.delUserRole((long) 10);
        mapper.delRole((long) 10);
    }

    @Test
    public void test5() {
        Role role = mapper.findRoleById((long) 2);
        System.out.println(role);
    }
}
